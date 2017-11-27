package antlr;

import compilationControlers.Writers;
import tools.FunctionsAnalyser;
import tools.Variable;
import nasm.DataSegment;
import compilationControlers.Checker;
import compilationControlers.CompilationControler;
import constants.Constants;
import constants.MemoryClassEnum;
import nasm.NasmTools;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.RuleContext;
import tools.Emitter;
import tools.ExpressionObject;
import tools.LabelsMaker;
import tools.Pointer;
import tools.PointerTools;
import tools.RelationHelper;

/**
 *
 * @author Aleksandar Colic
 */
public class TranslationVisitor extends picoCBaseVisitor<ExpressionObject> 
{
    /* List that contains informations about all functions
        that are beeing compiled */
    public static Map<String, FunctionsAnalyser> functions;
   
    /* Curent function context.  */
    public static FunctionsAnalyser curFuncAna;
    
    /* Mapping for extern variables */
    public static Map<String, Variable> externVariables;
    
    /* List represent current pointer declarator type */
    public static LinkedList<Pointer> pointerTo;
    
    /* If variable is array this list holds it's sizes.
        For example: If variable is int ar[5][6][3], this list would hold
        5, 6 and 3 */
    public static LinkedList<Integer> arrayDecl;
    
    /* Current variable name */
    public static String currentVariableName;
    
    /* Helps during declaration */
    public static MemoryClassEnum curTypeSpecifier;
    
    
    public TranslationVisitor() 
    {
        pointerTo = new LinkedList<>();
        arrayDecl = new LinkedList<>();
        functions = new HashMap<>();
        externVariables = new HashMap<>();
        curFuncAna = null;
    }
    
    /*  
        compilationUnit 
            :   translationUnit? EOF  ;       
    */
    @Override
    public ExpressionObject visitCompilationUnit(picoCParser.CompilationUnitContext ctx) 
    {
        /* Compile */
        super.visitCompilationUnit(ctx); 
        /* Print informations about compilation */
        if (CompilationControler.warnings != 0) {
            System.err.println("Warnings : " + CompilationControler.warnings);
        }
        /* Check wheather compilation is successful */
        if (CompilationControler.errors == 0) {
            System.out.println("Compilation successful!");
        } else {
            System.err.println("Errors: " + CompilationControler.errors);
            System.err.println("Compilation failed!");
            /* If output is not needed for testing, then terminate process -> 
            System.exit(0); */
        }
        try {    
            Writers writers = new Writers();
            writers.writeOutput();
        } catch (IOException ex) {
            Logger.getLogger(TranslationVisitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /*  
        functionDefinition 
            :    typeSpecifier declarator '(' parameterList? ')' functionBody  
    */
    @Override
    public ExpressionObject visitFunctionDefinition(picoCParser.FunctionDefinitionContext ctx) 
    {
        /* Get name and memory class of function */
        int tokenType = ctx.typeSpecifier().type.getType();
        MemoryClassEnum memclass;
        memclass = NasmTools.getTypeOfVar(tokenType);
        /* Set global variable curTypeSpecifier
            to current memory class in order to
            visitSimplePointer() insert correct type in pointerTo list */
        curTypeSpecifier = memclass;
        String name = visit(ctx.declarator()).getText();
        
        
        /* Chech weather function is already defined */
        if (!Checker.funcDefCheck(ctx, name))
            return null;
        
        /* Create new function analyser object, and set it's type specifier
        to some type */
        FunctionsAnalyser fa = new FunctionsAnalyser(name);
        if (pointerTo.isEmpty())
            fa.setMemoryClass(memclass);
        else {
            fa.setMemoryClass(MemoryClassEnum.POINTER);
            PointerTools.switchStacks(pointerTo, fa.getPointerType());
        }
        
        functions.put(name, fa);       /* Add function to collection */
        
        FunctionsAnalyser.setFunctionInProcess(true); /* "In function context" */
        FunctionsAnalyser.setInProcess(name); /* Change function that is processed */
        curFuncAna = fa;
        /* Make room for local variables and arguments.
            Information about whole space on stack needed for
            local variables and parameters is hold within map of functions in class
            TranslationListener. */
        int localsAndArgsSize = 
                TranslationListener.mapFuncAna.get(name).
                    getSpaceForVariables();
                
        String ls = Integer.toString(localsAndArgsSize);
        
        /* Setup text segment for function definition */
        Writers.emitFunctionSetup(name);
        /* Substract number of bytes needed for variables if there is any */
        if (localsAndArgsSize > 0)
            Writers.emitInstruction("sub", "rsp", ls);    
        /* Visit rest of the function. (parameterList and functionBody) */
        if (ctx.parameterList() != null)
            visit(ctx.parameterList());
        visit(ctx.functionBody());
        
        /* Check for return statement */
        Checker.funcRetStatCheck(ctx);
        
        /* Emit return label and reset stack */
        Writers.emitLabelReturn(curFuncAna.getFunctionName());
        Writers.emitText(Constants.FUNCTION_EXIT);
        /* Set current function context to null */
        curFuncAna = null;
        FunctionsAnalyser.setFunctionInProcess(false);
        FunctionsAnalyser.setInProcess(null);
        
        return null;
    }

    /*  
        parameterList 
            :   parameter (',' parameter)*  
    */
    @Override
    public ExpressionObject visitParameterList(picoCParser.ParameterListContext ctx) 
    {
        /* Get list of parameters */
        List<picoCParser.ParameterContext> paramsList = ctx.parameter();
        int numberOfParams = paramsList.size();
        curFuncAna.setNumberOfParameters(numberOfParams);
        curFuncAna.setParameterContext(true);
        String reg, paramName, stackPos, paramPos, cast;
        MemoryClassEnum memclass;
        ExpressionObject arg;
        
        NasmTools.initializeNewPickers();
        for (int i = 0; i < numberOfParams; ++i) {
            /* Visit parameter (declare it) */
            arg = visit(paramsList.get(i));
            /* argument name needed for it's position on stack */
            paramName = arg.getName();
            /* get argument's position on stack */
            stackPos = arg.getStackDisp();
            /* Get memory class of typeSpecifier and register 
                in which it is passed to function */
            memclass = arg.getType();
            cast = NasmTools.getCast(memclass);
            paramPos = cast + " [" + stackPos + "]";
            reg = NasmTools.getNextRegForFuncCall(memclass);
            
            /* Emit copying from registers to stack for arguments */
            Writers.emitInstruction("mov", paramPos, reg);
        }
        NasmTools.resetRegisterPicker();
        
        
        /* Free all registers for function body */
        NasmTools.freeAllRegisters();
        curFuncAna.setParameterContext(false);
        return null;
    }

    /* parameter 
          :   typeSpecifier declarator  
    */
    @Override
    public ExpressionObject visitParameter(picoCParser.ParameterContext ctx) 
    {
        /* Get variable's memory class  */
        int type = ctx.typeSpecifier().type.getType();
        MemoryClassEnum typeSpecifier = NasmTools.getTypeOfVar(type);
        curTypeSpecifier = typeSpecifier;
        
        return super.visitParameter(ctx);
    }

    /*
        declaration
            :   typeSpecifier initDeclarationList  ';'
            ;
   */    
    @Override
    public ExpressionObject visitDeclaration(picoCParser.DeclarationContext ctx) 
    {
        /* Get memory class for declaration */
        int tokenType = ctx.typeSpecifier().type.getType();
        MemoryClassEnum memclass = NasmTools.getTypeOfVar(tokenType);
        curTypeSpecifier = memclass;
        /* Keep declaring rest of the list */
        
        return super.visitDeclaration(ctx);
    }
    
    /*
        initDeclarator
            :   declarator '=' assignmentExpression     #DeclWithInit
            ;
    */
    @Override
    public ExpressionObject visitDeclWithInit(picoCParser.DeclWithInitContext ctx) 
    {
        /* Expression */
        ExpressionObject expr, newVariable;
        if ((newVariable = visit(ctx.declarator())) == null)
            return null;
        
        String id = currentVariableName;
        /* Get variable context */
        if (newVariable.isExternVariable())
            externVariables.get(id).setInitialized(true);
        else
            curFuncAna.getAnyVariable(id).setInitialized(true);
        
        /* Visit expression and try to recover from error. */
        if ((expr = visit(ctx.assignmentExpression())) == null) 
            return null;
        expr.comparisonCheck();
        
        /* If it is external declaration, just check if expression is constant.
            If it is not external, emit initialization instruction. */
        if (newVariable.isExternVariable()) {
            if (!Checker.checkConstantExpression(ctx, expr))
                return null;
            DataSegment.declareExternVariable(newVariable, expr.getText());
        } else {
            /* Cast variable if needed */
            if (!expr.isInteger()) 
                expr.castVariable(newVariable.getType());
            /* Emit proper assignment instruction */
            Emitter.decideAssign(newVariable, expr, picoCParser.ASSIGN);

            if (expr.isRegister())
                expr.freeRegister();
        }
        
        currentVariableName = null;
        /* Return new Object */
        return newVariable;
    }
    
    /*
        directDeclarator
            :   ID                                  #DirDecl
    */
    @Override
    public ExpressionObject visitDirDecl(picoCParser.DirDeclContext ctx) 
    {
        /* Get index of the rule that invoked this state. If it is same
            rule as ctx, than go to the parent. Since rule declarator will
            always invoke this state, than one level above that rule needs
            to be reached. */
        RuleContext rule = ctx.parent;
        int ruleIndex;
        while (rule.getRuleIndex() == ctx.getRuleIndex() 
                || rule.getRuleIndex() == picoCParser.RULE_declarator)
            rule = rule.parent;
        ruleIndex = rule.getRuleIndex();
        /* Call proper function for the rule that invoked direct declaration */
        switch (ruleIndex) {
            case picoCParser.RULE_parameter:
                return declareParameter(ctx);
            case picoCParser.RULE_initDeclarator:
                return declareLocalOrExtern(ctx);
            case picoCParser.RULE_functionDefinition:
                return declareFunction(ctx);
            default:
                return null;
        }

    }

    /* Function that declares parameter of the function */
    private ExpressionObject declareParameter(picoCParser.DirDeclContext ctx) 
    {
        /* Variable name, stack position, and memory class */
        String name, stackPosition;
        name = ctx.ID().getText();
        currentVariableName = name;
        MemoryClassEnum typeSpecifier;
        MemoryClassEnum typeForDeclaration;
        Variable var;
        /* Chech if variable is already declared */
        if (!Checker.varDeclCheck(ctx, name))
            return null;
        
        /* Check if variable is pointer or array. In both cases variable
            is passed as pointer */
        if (pointerTo.isEmpty())
            typeSpecifier = curTypeSpecifier;
        else
            typeSpecifier = MemoryClassEnum.POINTER;
        
        /* Copy arrays as pointer, because parameter is always threated as
            pointer */
        PointerTools.insertArrays(pointerTo, arrayDecl, typeSpecifier);
        
        if (pointerTo.isEmpty() && arrayDecl.isEmpty())
            typeForDeclaration = curTypeSpecifier;
        else
            typeForDeclaration = MemoryClassEnum.POINTER;
        
        /* Get stack position */
        stackPosition = curFuncAna.declareParameterVariable(typeForDeclaration);
        
        /* Create new variable object */
        var = new Variable
            (name, stackPosition, typeSpecifier, pointerTo, arrayDecl, false);
        /* Insert new variable in parameter variables */
        curFuncAna.getParameterVariables().put(name, var);
        
        /* Check for void variables */
        if (!Checker.varSizeCheck(ctx, typeSpecifier))
            return null;
        
        /* Even thou variable constructor will clear both 
            pointerTo and arrayDecl lists, just to be sure that lists are empty
            let's clear them */
        pointerTo.clear();
        arrayDecl.clear();
        
        return new ExpressionObject(var);
    }

    /* Decides which declaration is done. */
    private ExpressionObject declareLocalOrExtern
    (picoCParser.DirDeclContext ctx) 
    {
        /* If no function is in progres, define extern. Otherwise declare local */
        if (curFuncAna == null)
            return declareExternVariable(ctx);
        else
            return declareLocalVariable(ctx);
    }
    
    /* Function that declares local variable of the function */
    private ExpressionObject declareLocalVariable(picoCParser.DirDeclContext ctx) 
    {
        /* Variable name, stack position, and memory class */
        String name, stackPosition;
        currentVariableName = name = ctx.ID().getText();
        MemoryClassEnum typeSpecifier;
        Variable var;
        /* Chech if variable is already declared */
        if (!Checker.varDeclCheck(ctx, name))
            return null;
        
        /* Check if variable is pointer */
        if (pointerTo.isEmpty())
            typeSpecifier = curTypeSpecifier;
        else
            typeSpecifier = MemoryClassEnum.POINTER;
        
        /* Check if variable is array */
        if (!arrayDecl.isEmpty())
            stackPosition = curFuncAna.declareLocalArray(typeSpecifier, arrayDecl);
        else
            stackPosition = curFuncAna.declareLocalVariable(typeSpecifier);
        
        var = new Variable
            (name, stackPosition, typeSpecifier, pointerTo, arrayDecl, false);
        /* Insert new variable in local variables */
        curFuncAna.getLocalVariables().put(name, var);
            
        /* Check size of variable */
        if (!Checker.varSizeCheck(ctx, typeSpecifier))
            return null;
        
        /* Even thou variable constructor will clear both 
            pointerTo and arrayDecl lists, just to be sure that lists are empty */
        pointerTo.clear();
        arrayDecl.clear();
        
        return new ExpressionObject(var);
    }

    private ExpressionObject declareExternVariable(picoCParser.DirDeclContext ctx) 
    {
        /* Variable name and memory class. Sizes represents total size
            needs to be reserver if variable is an array */
        String name = ctx.ID().getText();
        currentVariableName = name;
        int sizes = 0;
        
        MemoryClassEnum typeSpecifier;
        Variable var;
        /* Chech if variable is already declared */
        if (!Checker.varExternDeclCheck(ctx, name))
            return null;
        
        /* Check if variable is pointer */
        if (pointerTo.isEmpty())
            typeSpecifier = curTypeSpecifier;
        else
            typeSpecifier = MemoryClassEnum.POINTER;
        
        /* Calculate space for an array if variable is one */
        if (!arrayDecl.isEmpty())
            sizes = NasmTools.multiplyList(arrayDecl);
        /* Since extern variable is accessed through it's name, than stack 
            position is also name, so that expression object can cast
            it's stack postion later in cast[variablename] for example . */  
        var = new Variable
            (name, name, typeSpecifier, pointerTo, arrayDecl, true);
        /* Insert new variable in extern variables */
        externVariables.put(name, var);

        /* Check for void type */
        if (!Checker.varTypeCheck(ctx, typeSpecifier))
            return null;
        
        /* Go to start of the initialization */
        RuleContext rule = ctx.parent;
        while (rule.getRuleIndex() != picoCParser.RULE_initDeclarator)
            rule = rule.parent;
        /* If there is no initialization, initialize variable to 0. */
        if (rule.getChildCount() == 1)
            DataSegment.declareExternVariable(var, "0", sizes);
            
        /* Even thou variable constructor will clear both 
            pointerTo and arrayDecl lists, just to be sure that lists are empty
            let's clear them */
        pointerTo.clear();
        arrayDecl.clear();
        
        return new ExpressionObject(var);   
    }
    
    /* Function that declares new function. In case that function 
        is currently declaring, than just name of the 
        function needs to be returned. It doesn't matter which argumets
        are passed to Expression object, because only text of the
        object is used. */
    private ExpressionObject declareFunction(picoCParser.DirDeclContext ctx) 
    {
        return new ExpressionObject
            (ctx.ID().getText(), 
             MemoryClassEnum.POINTER, 0
            );
    }
    
    /*
        pointer
            :   '*'             #SimplePtr
            ;
    */
    @Override
    public ExpressionObject visitSimplePtr(picoCParser.SimplePtrContext ctx) 
    {
        PointerTools.insertPointerType(pointerTo, curTypeSpecifier);
        return super.visitSimplePtr(ctx);
    }

    /*
        pointer
            :   pointer '*'     #MultiplePrt
            ;
    */
    @Override
    public ExpressionObject visitMultiplePrt(picoCParser.MultiplePrtContext ctx) 
    {
        PointerTools.insertPointerType(pointerTo, curTypeSpecifier);
        return super.visitMultiplePrt(ctx);
    }
    
    /*
        assignmentExpression
            :   unaryExpression assignmentOperator assignmentExpression    #Assign
            ;
    
        assignmentOperator
            :   op=('=' | '+=' | '-=' | '*=' | '/=' | '%=')
            ;
    */
    @Override
    public ExpressionObject visitAssign(picoCParser.AssignContext ctx) 
    {
        
        /* Expressions */
        ExpressionObject left, right;
        /* Visit expression and try to recover from error. */
        if ((right = visit(ctx.assignmentExpression())) == null) 
            return null;
        right.comparisonCheck();
        
        Checker.setVarInitCheck(false);    // Prevent checking for initialization
        if ((left = visit(ctx.unaryExpression())) == null)
            return null;
        Checker.setVarInitCheck(true);
        String id = left.getName();
        
        /* Check if there is variable to assign value to */
        if (!Checker.checkVariableExistance(left, ctx))
            return null;
        /* Prevent assign to an array */
        if (!Checker.checkArrayAssign(left, ctx))
            return null;
        int operation = ctx.assignmentOperator().op.getType();
        /* Get variable context if it is local variable */
        Variable var = curFuncAna.getAnyVariable(id);
        /* If it is not, check if it is extern */
        if (var == null)
            var = externVariables.get(id);
        var.setInitialized(true);
        
        /* Cast variable if needed */
        if (!right.isInteger())
            right.castVariable(left.getType());
        if (!Checker.checkAssign(left, right, ctx, operation))
            return null;
        
        /* See which operator is used for assign and emit proper instruction */
        if (left.isPointer())
            Emitter.decideAssignPointers(left, right, operation);
        else
            Emitter.decideAssign(left, right, operation);
        
        if (right.isRegister())
            right.freeRegister();
        /* Return new Object */
        return left;
    }
    
    /*
        statement 
            :   compoundStatement
            |   expressionStatement
            |   selectionStatement
            |   iterationStatement
            |   jumpStatement                         
            ;
    */
    @Override
    public ExpressionObject visitStatement(picoCParser.StatementContext ctx) 
    {
        super.visitStatement(ctx); 
        return null;    // Let garbage collector free all expression objects
    }
    
    /*
        jumpStatement
            :   'return' expression?  ';'      #Return
            ;
    */
    @Override
    public ExpressionObject visitReturn(picoCParser.ReturnContext ctx) 
    {
        ExpressionObject expr;
        String retReg;
        /* Let checker decide wheather return value is correct */
        if (!Checker.checkReturnValue(curFuncAna.getMemoryClass(), ctx))
            return null;
        
        /* If ctx.expression is null, than it is void type */
        if (ctx.expression() == null) {
            Writers.emitJumpToExit(FunctionsAnalyser.getInProcess());
            NasmTools.freeAllRegisters();
            return null;
        } else if ((expr = visit(ctx.expression())) == null)
            return null;
            
        expr.comparisonCheck();
        
        /* Set function has return  */
        curFuncAna.setHasReturn(true);
        /* Cast expression to proper size */
        expr.castVariable(curFuncAna.getMemoryClass());
        /* mov result to eax for return if result is not eax */
        if (!expr.isRegisterA()) {
            retReg = NasmTools.getARegister(curFuncAna.getMemoryClass());
            Writers.emitInstruction("mov", retReg, expr.getText());
        }
        Writers.emitJumpToExit(FunctionsAnalyser.getInProcess());
        /* Free all registers for function exit */
        NasmTools.freeAllRegisters();
        return null;
    }

    
    /* Actual function call goes something like this: *******************/
        /* Save registers if any of them is needed for further calculations */
        /* If there is arguments, pass them through registers and stack */
        /* Emit function call */
        /* Store return value in free memory */
        /* Restore saved registers */
    /* Now the explanation for showNextFreeTemp function.
        It doesn't actually set any flags in flags variable in NasmTools, but
        just check which is next register (or position on stack if registers are filed)
        which can hold value. After that, register's are saved on stack, 
        but before they are restored, real method for getting
        registers is called which is getNextFreeTemp4Bytes. It can't be done without
        that, because, if getNextFreeTemp4Bytes is called insted of showNextFreeTemp, then
        restoreRegisters from NasmTools would override it's value, because
        that value would be pushed on stack along with other registers as
        saved registers from function. */
    /*
        postfixExpression
            :   postfixExpression '(' argumentList? ')'  #FuncCall
            ;
    */
    @Override
    public ExpressionObject visitFuncCall(picoCParser.FuncCallContext ctx) 
    {
        List<picoCParser.ArgumentContext> argumentList;
        String functionName = ctx.postfixExpression().getText();
        /* Check if there is arguments */
        if (ctx.argumentList() != null) {
            argumentList = ctx.argumentList().argument();
        } else
            argumentList = null;
        
        /* Check function and try to recover if it's bad call */
        if (!Checker.functionCallCheck(ctx, argumentList))
            return null;
        
        /* Little thing: View which is next free register for further calculation
            and move function's return value to it, to continue calculating.
            Function getNextFreeTemp4Bytes can't be used here because that
            function takes next free register if there is one and he'll be
            saved on stack with rest of registers with saveRegistersOnStack.
            Instead, just peek to see which is next free register and after
            function call move function return value to it. */
        MemoryClassEnum type = MemoryClassEnum.INT;
        /* If function is from GCC's lib, than func is null. */
        FunctionsAnalyser func = functions.get(functionName);
        if (!Checker.externalFunctionCheck(functionName))
            type = func.getMemoryClass();
        
        String nextFreeTemp = NasmTools.showNextFreeTemp(type);
        String areg = NasmTools.registerToString(NasmTools.AREG, type);
        /* Registers are saved and freed for further use */
        NasmTools.saveRegistersOnStack();
        NasmTools.moveArgsToRegisters(this, argumentList);
        /* Special setup if fucntion is from gcc's lib */
        if (Checker.externalFunctionCheck(functionName))
            Writers.emitInstruction("mov", "eax", "0");
        /* Function call */
        Writers.emitInstruction("call", functionName);
        /* Avoid unnecessary move */
        if (!NasmTools.isRegisterA(nextFreeTemp))
            Writers.emitInstruction("mov", nextFreeTemp, areg);
        NasmTools.restoreRegisters();
        nextFreeTemp = NasmTools.getNextFreeTempStr(type);
        
        /* Check if function is from GCC's lib. If it is, return 
            simple int && register object. If it is not, return object that
            represents functions value. */
        if (func == null)
            return new ExpressionObject(nextFreeTemp, type, 
                    ExpressionObject.REGISTER);
        
        return new ExpressionObject
            (nextFreeTemp,
             type,
             ExpressionObject.REGISTER,
             func.getPointerType()
            );
    }

    
    /*
        argumentList 
            :   argument (',' argument)*  ;
    */
    @Override
    public ExpressionObject visitArgumentList(picoCParser.ArgumentListContext ctx) 
    {
        return super.visitArgumentList(ctx);
    }

    /*
        argument 
            : assignmentExpression 
            ;    
    */
    @Override
    public ExpressionObject visitArgument(picoCParser.ArgumentContext ctx) 
    {
        ExpressionObject expr;
        if ((expr = visit(ctx.assignmentExpression())) == null)
            return null;
        expr.comparisonCheck();
            
        return expr;
    }
    /*
        expression
            :   assignmentExpression
            |   expression ',' assignmentExpression
            ;      
    */
    @Override
    public ExpressionObject visitExpression(picoCParser.ExpressionContext ctx)
    {
        ExpressionObject expr = super.visitExpression(ctx);
        return expr;
    }

    /*
        primaryExpression 
            :   '(' expression ')' #Parens
            ;
    */
    @Override
    public ExpressionObject visitParens(picoCParser.ParensContext ctx) 
    {
        ExpressionObject expr = visit(ctx.expression());
        return expr;
    }
    
    /*
        unaryExpression 
            :   '-'  unaryExpression    #Minus
            ;
    */
    @Override
    public ExpressionObject visitMinus(picoCParser.MinusContext ctx) 
    {
        /* Visit rest of expression */
        ExpressionObject expr = super.visitMinus(ctx);
        expr.comparisonCheck();
        /* If result is integer just put - prefix */
        if (expr.isInteger())
            return expr.insertIntMinusPrefix();
        /* Negate it */
        if (!expr.isRegister() || !expr.isStackVariable())
            expr.putInRegister();
        Writers.emitInstruction("neg", expr.getText());
        
        return expr;
    }
    
    /*
        additiveExpression 
            :   additiveExpression op=('+'|'-') multiplicativeExpression  #AddSub
            ;
    */
    @Override
    public ExpressionObject visitAddSub(picoCParser.AddSubContext ctx) 
    {
        ExpressionObject leftExpr, rightExpr, help;
        String nextFreeTemp;
        
        /* Try to visit children and recover if error ocured */
        if ((leftExpr = visit(ctx.additiveExpression())) == null)
            return null;
        leftExpr.comparisonCheck();
        
        if ((rightExpr = visit(ctx.multiplicativeExpression())) == null)
            return null;
        rightExpr.comparisonCheck();
        
        /* Make sure that pointer arithmetic is valid */
        if (!Checker.checkAddSubPointers(leftExpr, rightExpr, ctx))
            return null;
        /* Before all calculations, if left and right operand are numbers, 
            let java do the calculation and save some program's time */
        if (leftExpr.isInteger() && rightExpr.isInteger()) {
            String res = NasmTools.calculate
                (leftExpr.getText(), rightExpr.getText(), ctx.op.getType(), ctx);
            leftExpr.setText(res);
            return leftExpr;
        }
        /* If left one is integer and right one is not switch left and 
            right in order to optimize calculation */
        if (leftExpr.isInteger()) {
            help = rightExpr;
            rightExpr = leftExpr;
            leftExpr = help;
        }
        /* If there is free registers, and leftExpr is variable, than it needs
            to be moved to one */
        if (!leftExpr.isRegister() && NasmTools.hasFreeRegisters())
            leftExpr.putInRegister();
        /* Arithmetic is done with minimum 4 bytes registers, so if both 
            variables are 1 byte long, left is cased to 4 bytes. */
        if (leftExpr.getType() == MemoryClassEnum.CHAR
                && rightExpr.getType() == MemoryClassEnum.CHAR)
            leftExpr.castVariable(MemoryClassEnum.INT);
        /* Cast smaller variable to the type of bigger if needed */
        ExpressionObject.castVariablesToMaxSize(leftExpr, rightExpr);
        String operation = NasmTools.getOperation(ctx.op.getType());
        
        /* Set left to be pointer and make it easier to code.
                Only valid operation if right one is pointer and left is not,
                is add, so that doesn't change result. */
        if (leftExpr.isPointer() || rightExpr.isPointer()) {
            if (rightExpr.isPointer() && !leftExpr.isPointer()) {
                help = rightExpr;
                rightExpr = leftExpr;
                leftExpr = help;
            }
            /* Emit poiner add/sub */
            Emitter.emitPointersAddSub(leftExpr, rightExpr, operation);
        } else {
            /* Emit regular add sub */
            Emitter.emitAddSub(leftExpr, rightExpr, operation);
        }
        
        if (rightExpr.isRegister())
            rightExpr.freeRegister();
        
        return leftExpr;
    }

    /*
        multiplicativeExpression 
            :   multiplicativeExpression op=('*'|'/'|'%') unaryExpression  #MulDivMod
            ;
    */
    @Override
    public ExpressionObject visitMulDivMod(picoCParser.MulDivModContext ctx) 
    {
        boolean fake = false;
        String nextFreeTemp;
        ExpressionObject leftExpr, rightExpr;
        int operation = ctx.op.getType();
        /* Try to visit children and recover if error ocured */
        if ((leftExpr = visit(ctx.multiplicativeExpression())) == null)
            return null;
        leftExpr.comparisonCheck();
        if ((rightExpr = visit(ctx.unaryExpression())) == null)
            return null;
        rightExpr.comparisonCheck();
        /* Before all calculations, if left and right operand are numbers, 
            let java do the calculation and save some program's time */
        if (leftExpr.isInteger() && rightExpr.isInteger()) {
            String res = NasmTools.calculate
                (leftExpr.getText(), rightExpr.getText(), operation, ctx);
            leftExpr.setText(res);
            return leftExpr;
        }
        /* If there is free registers, and leftExpr is variable, than it needs
            to be moved to one */
        if (!leftExpr.isRegister() && NasmTools.hasFreeRegisters())
            leftExpr.putInRegister();
        
        if (leftExpr.getType() == MemoryClassEnum.CHAR
                && rightExpr.getType() == MemoryClassEnum.CHAR)
            leftExpr.castVariable(MemoryClassEnum.INT);
        /* Cast to proper type if needed */
        ExpressionObject.castVariablesToMaxSize(leftExpr, rightExpr);
        
        /* If right operand is integer number, than it needs to be moved to 
            regiser or stack. */
        if (rightExpr.isInteger())
            rightExpr.putInRegister();
        
        
        if (operation == picoCParser.MUL) 
            Emitter.multiply(leftExpr, rightExpr);
        else 
            Emitter.divideOrModulo(leftExpr, rightExpr, operation);
        
        if (rightExpr.isRegister())
            rightExpr.freeRegister();
        return leftExpr;
    }

    /*
        primaryExpression 
            :   ID                 #Id
            ;
    */
    @Override
    public ExpressionObject visitId(picoCParser.IdContext ctx) 
    {
        ExpressionObject res;
        /* Just for more readable code */
        boolean local, param, extern;
        local = param = extern = false;
        String id = ctx.ID().getText();
        
        /* Check if variable is local, extern or parameter */
        Variable newVar;
        if ((newVar = curFuncAna.getLocalVariables().get(id)) != null)
            local = true;
        else if ((newVar = curFuncAna.getParameterVariables().get(id)) != null)
            param = true;
        else if ((newVar = externVariables.get(id)) != null)
            extern = true;
        /* Check if variable is declared */
        if (!Checker.varCheck(local, param, extern, ctx, id))
            return null;
        /* Check if variable is initialized */
        Checker.varInitCheck(local || extern, newVar, id, ctx);
        
        /* If visited variable is an array, than it needs to be moved to register
            in order to do all calculations */
        res = new ExpressionObject(newVar);
        if (res.isArray())
            res.initArray();
        /* Return new variable */
        return res;
    }
    
    /*
        INT  : [0-9]+ ;
    */
    @Override
    public ExpressionObject visitInt(picoCParser.IntContext ctx) 
    {
        String val = ctx.INT().getText();
        return new ExpressionObject
            (val, 
             MemoryClassEnum.INT, 
             ExpressionObject.CONSTANT
            );
    }

    /*   
        CHAR   : '\'' . '\'' | '\'\\' . '\'' ;  
    */
    @Override
    public ExpressionObject visitChar(picoCParser.CharContext ctx) 
    {
        String charSeq = ctx.CHAR().getText();
        if (!Checker.checkCharSequence(ctx, charSeq))
            return null;
        String value = NasmTools.getConstantCharValue(charSeq);
        return new ExpressionObject
            (value, 
             MemoryClassEnum.CHAR, 
             ExpressionObject.CONSTANT
            );
    }
    
    /*
        STRING_LITERAL  : '"' (ESC|.)*? '"' ;
    */
    @Override
    public ExpressionObject visitStr(picoCParser.StrContext ctx) 
    {
        String strlit = ctx.STRING_LITERAL().getText();
        String literalName = NasmTools.defineStringLiteral(strlit);
        /* Return string literal */
        return new ExpressionObject
            (literalName, 
             MemoryClassEnum.POINTER, 
             ExpressionObject.STRING_LITERAL,
             MemoryClassEnum.CHAR       
            );
    }
    
    
    
    
    /*  Relation could be: '<' '<=' '>' '>=' ;
           
        Result of some comparison is stored in least significant part of register
        and in order to perform cmp operation, proper part of registers must be
        compared. For example: if left expression returned eax, and right 
        returned bl, than bl must be converted to ebx (4 byte size) in order
        to do comparison between them. 
    
        relationalExpression 
            :   relationalExpression rel=('<'|'<='|'>='|'>') additiveExpression  #Relation
            ;    
    */
    @Override
    public ExpressionObject visitRelation(picoCParser.RelationContext ctx) 
    {
        ExpressionObject left, right;
        int maxSizeOfVars;
        /* Visit expressions and try to recover if error ocurs */
        if ((left = visit(ctx.relationalExpression())) == null)
            return null;
        left.comparisonCheck();
        if ((right = visit(ctx.additiveExpression())) == null)
            return null;
        right.comparisonCheck();
        
        /* If left and right expressions are stack variable, than one need to be
            moved to register in order to do cmp operation */
        if (left.isStackVariable() && right.isStackVariable())
            left.putInRegister();
        
        
        /* If left and right expressions are Integer number, than one need to be
            moved to register in order to do cmp operation */
        if (left.isInteger() && right.isInteger())
            left.putInRegister();
        
        
        /* If sizes of variables doesn't match, than they need to be casted.
            Next line does nothing if sizes of variables match.
            Variable could be register or variable on stack. */
        ExpressionObject.castVariablesToMaxSize(left, right);
        /* Emit comparison */
        Writers.emitInstruction("cmp", left.getText(), right.getText());
        
        left.setCompared(true);
        right.setCompared(true);
        RelationHelper.setComparisonDone();
        RelationHelper.setRelation(ctx.rel.getType());
        
        /* Try to optimize */
        return ExpressionObject.freeOneOfTwo(left, right);
    }
    
    /*  Relation could be: '==' '!=' ; 
    
        equalityExpression
            :   equalityExpression rel=('=='|'!=') relationalExpression    #Equality
            ;
    */
    @Override
    public ExpressionObject visitEquality(picoCParser.EqualityContext ctx) 
    {
        ExpressionObject left, right;
        int maxSizeOfVars;
        /* Visit expressions and try to recover if error ocurs */
        if ((left = visit(ctx.equalityExpression())) == null)
            return null;
        left.comparisonCheck();
        if ((right = visit(ctx.relationalExpression())) == null)
            return null;
        right.comparisonCheck();
        
        /* If left and right expressions are stack variable, than one need to be
            moved to register in order to do cmp operation */
        if (left.isStackVariable() && right.isStackVariable())
            left.putInRegister();
        
        /* If left and right expressions are Integer number, than one need to be
            moved to register in order to do cmp operation */
        if (left.isInteger() && right.isInteger())
            left.putInRegister();
        /* If sizes of variables doesn't match, than they need to be casted.
            Next line does nothing if sizes of variables match.
            Variable could be register or variable on stack. */
        ExpressionObject.castVariablesToMaxSize(left, right);
        /* Emit comparison */
        Writers.emitInstruction("cmp", left.getText(), right.getText());
        
        left.setCompared(true);
        right.setCompared(true);
        RelationHelper.setComparisonDone();
        RelationHelper.setRelation(ctx.rel.getType());
        
        /* Try to optimize */
        return ExpressionObject.freeOneOfTwo(left, right);
    }

    /*
        logicalAndExpression
            :   logicalAndExpression '&&' equalityExpression        #LogicalAND
            ;
    */
    @Override
    public ExpressionObject visitLogicalAND(picoCParser.LogicalANDContext ctx) 
    {
        ExpressionObject left, right;
        String labelTrue, labelFalse, afterFalseLabel;
        int maxSizeOfVars;
        /* Visit left and right child and try to recover from error */
        if ((left = visit(ctx.logicalAndExpression())) == null)
            return null;
        left.comparisonCheck();
        if ((right = visit(ctx.equalityExpression())) == null) 
            return null;
        right.comparisonCheck();
        /* If left expression is not register it needs to be moved to one */
        if (!left.isRegister())
            left.putInRegister();
        if (right.isInteger())
            right.putInRegister();
        /* If sizes of variables doesn't match, than they need to be casted.
            Next line does nothing if sizes of variables match.
            Variable could be register or variable on stack. */
        ExpressionObject.castVariablesToMaxSize(left, right);
        
        /* Call nasm tools to emit standard procedure for evaluating 'AND' expression */
        Emitter.andExpressionEvaluation(left.getText(), right.getText());
        
        /* Free right if it is regiser and return left */
        if (right.isRegister())
            right.freeRegister();
        return left;
    }

    /*
        logicalOrExpression
            :   logicalOrExpression '||' logicalAndExpression       #LogicalOR
            ;
    */
    @Override
    public ExpressionObject visitLogicalOR(picoCParser.LogicalORContext ctx) 
    {
        ExpressionObject left, right;
        String labelTrue, labelFalse, afterFalseLabel;
        int maxSizeOfVars;
        /* Visit left and right child and try to recover from error */
        if ((left = visit(ctx.logicalOrExpression())) == null)
            return null;
        left.comparisonCheck();
        if ((right = visit(ctx.logicalAndExpression())) == null) 
            return null;
        right.comparisonCheck();
        /* If left expression is not register it needs to be moved to one */
        if (!left.isRegister())
            left.putInRegister();
        if (right.isInteger())
            right.putInRegister();
        /* If sizes of variables doesn't match, than they need to be casted.
            Next three lines does nothing if sizes of variables match.
            Variable could be register or variable on stack. */
        ExpressionObject.castVariablesToMaxSize(left, right);
        
        /* Call nasm tools to emit standard procedure for evaluating 'OR' expression */
        Emitter.orExpressionEvaluation(left.getText(), right.getText());
        
        /* Free right and return left */
        if (right.isRegister())
            right.freeRegister();
        return left;
    }

    /*
        selectionStatement
            :   'if' '(' expression ')' statement ('else' statement)? ;
    */
    @Override
    public ExpressionObject visitSelectionStatement(picoCParser.SelectionStatementContext ctx) 
    {
        ExpressionObject expr;
        String right, labelIf, labelElse, labelAfterElse, jump;
        long depthIfElse;
        /* Get depth of if else */
        depthIfElse = LabelsMaker.getIfDepth();
        /* Get all tree labels, to keep their counters equal for easier debugging */
        labelIf = LabelsMaker.getNextIfLabel();
        labelElse = LabelsMaker.getNextElseLabel();
        labelAfterElse = LabelsMaker.getNextAfterElseLabel();
        
        /* Visit statement and try to recover if error ocurs */
        if ((expr = visit(ctx.assignmentExpression())) == null)
            return null;
        if (!expr.isCompared())
            expr.compareWithZero();
        /* Free all registers taken by calculating the expression */
        NasmTools.freeAllRegisters();
        /* Get opposite jump */
        jump = RelationHelper.getFalseJump();
        /* Here goes emiting  */
        Writers.emitInstruction(jump, labelElse);
        Writers.emitLabel(labelIf);
        /* Insert code within if statement */
        visit(ctx.statement(0));
        /* Jump to after else label if expression is true */
        Writers.emitInstruction(Constants.JUMP_UNCODITIONAL, labelAfterElse);
        Writers.emitLabel(labelElse);
        /* Insert code within else statement if it is there */
        if (ctx.statement(1) != null)
            visit(ctx.statement(1));
        
        /* Stop multiple emiting of same label */
        if (depthIfElse == 0)
            Writers.emitLabel(labelAfterElse);
        
        RelationHelper.setComparisonUsed();
        expr.setCompared(false);
        return null;
    }

    /*
        unaryExpression 
            :   '++' unaryExpression    #PreInc
            ;
    */
    @Override
    public ExpressionObject visitPreInc(picoCParser.PreIncContext ctx) 
    {
        ExpressionObject res;
        MemoryClassEnum type;
        String value;
        /* Visit expression and try to recover from error */
        if ((res = visit(ctx.unaryExpression())) == null)   
            return null;
        res.comparisonCheck();
        /* Check if it is variable and add one to result */
        if (!Checker.checkPreInc(res, ctx))
            return null;
        if (res.isPointer()) {
            type = res.getPointer().getType();
            value = Integer.toString(NasmTools.getSize(type));
        } else {
            value = "1";
        }
        
        Writers.emitInstruction("add", res.getText(), value);
        
        return res;
    }

    /*
        unaryExpression 
            :   '--' unaryExpression    #PreDec
            ;
    */
    @Override
    public ExpressionObject visitPreDec(picoCParser.PreDecContext ctx) 
    {
        ExpressionObject res;
        MemoryClassEnum type;
        String value;
        /* Visit expression and try to recover from error */
        if ((res = visit(ctx.unaryExpression())) == null)   
            return null;
        res.comparisonCheck();
        /* Check if it is variable and add one to result */
        if (!Checker.checkPreDec(res, ctx))
            return null;
        if (res.isPointer()) {
            type = res.getPointer().getType();
            value = Integer.toString(NasmTools.getSize(type));
        } else {
            value = "1";
        }
        
        Writers.emitInstruction("sub", res.getText(), value);
        
        return res;
    }
    
    /* All that needs to be done for post incrementation is to first 
        put variable's value in some register for further calculation, 
        and than increment that variable.
    
        postfixExpression
            :   postfixExpression '++'                   #PostInc
            ;
    */
    @Override
    public ExpressionObject visitPostInc(picoCParser.PostIncContext ctx) 
    {
        ExpressionObject res;
        MemoryClassEnum type;
        String stackPosition, value;
        /* Visit expression and try to recover from error */
        if ((res = visit(ctx.postfixExpression())) == null)   
            return null;
        res.comparisonCheck();
        /* Check if it is variable */
        if (!Checker.checkPostInc(res, ctx))
            return null;
        
        if (res.isPointer()) {
            type = res.getPointer().getType();
            value = Integer.toString(NasmTools.getSize(type));
        } else {
            value = "1";
        }
        
        /* Get next free register or stack position */
        stackPosition = res.getText();
        res.putInRegister();
        
        Writers.emitInstruction("add", stackPosition, value);
        
        return res;
    }

    /* All that needs to be done for post decrementation is to first 
        put variable's value in some register for further calculation, 
        and than decrement that variable. 
        
        postfixExpression
            :   postfixExpression '--'                   #PostDec
            ;
    */
    @Override
    public ExpressionObject visitPostDec(picoCParser.PostDecContext ctx) 
    {
        ExpressionObject res;
        MemoryClassEnum type;
        String stackPosition, value;
        /* Visit expression and try to recover from error */
        if ((res = visit(ctx.postfixExpression())) == null)   
            return null;
        res.comparisonCheck();
        /* Check if it is variable */
        if (!Checker.checkPostDec(res, ctx))
            return null;
        
        if (res.isPointer()) {
            type = res.getPointer().getType();
            value = Integer.toString(NasmTools.getSize(type));
        } else {
            value = "1";
        }
        /* Get next free register or stack position */
        stackPosition = res.getText();
        res.putInRegister();
        
        Writers.emitInstruction("sub", stackPosition, value);
        
        return res;
    }

    /*
        iterationStatement
            :   'for' '(' forInit? ';' forCheck? ';' forInc? ')' statement ; #ForLoop
    */
    @Override
    public ExpressionObject visitForLoop
    (picoCParser.ForLoopContext ctx) 
    {
        ExpressionObject expr, condition;
        String jump; 
        String forStartLabel, forCheckLabel, forIncrementLabel, forEndLabel;
        forStartLabel = LabelsMaker.getNextForStartLabel();
        forCheckLabel = LabelsMaker.getNextForCheckLabel();
        forIncrementLabel = LabelsMaker.getNextForIncerementLabel();
        forEndLabel = LabelsMaker.getNextForEndLabel();
        expr = condition = null;
        LabelsMaker.setCurrentIterationLabels(forIncrementLabel, forEndLabel);
        /* Do first expression witch is "initialization" (it could be 
            any expression of course) */
        if (ctx.forInit() != null) {
            if ((expr = visit(ctx.forInit())) == null)
                return null;
            expr.comparisonCheck();
        }
        /* Jump to check condition */
        Writers.emitInstruction(Constants.JUMP_UNCODITIONAL, forCheckLabel);
        /* Loop start */
        Writers.emitLabel(forStartLabel);
        /* Visit 'for' body */
        if (ctx.statement() != null) 
            visit(ctx.statement());
        /* Increment label */
        Writers.emitLabel(forIncrementLabel);
        if (ctx.forInc() != null)
            visit(ctx.forInc());
        /* Check label */
        Writers.emitLabel(forCheckLabel);
        /* Default jump */
        jump = Constants.JUMP_UNCODITIONAL;        
        /* If comparison is not done, than result of visiting must be
            compared to 0. Something like for (i = 100; i; --i); */
        if (ctx.forCheck() != null) {
            if ((condition = visit(ctx.forCheck())) == null)
                return null;
            if (!condition.isCompared())
                condition.compareWithZero();
            jump = RelationHelper.getTrueJump();
        }
        Writers.emitInstruction(jump, forStartLabel);
        Writers.emitLabel(forEndLabel);
        LabelsMaker.unsetCurrentIterationLabels();
        return null;
    }    

    /*
        iterationStatement
            :   'while' '(' whileCheck? ')' statement   ;       #WhileLoop
    */
    @Override
    public ExpressionObject visitWhileLoop(picoCParser.WhileLoopContext ctx) 
    {
        String whileStartLabel, whileCheckLabel, whileEndLabel;
        String jump;
        ExpressionObject condition;
        whileStartLabel = LabelsMaker.getNextWhileStartLabel();
        whileCheckLabel = LabelsMaker.getNextWhileCheckLabel();
        whileEndLabel = LabelsMaker.getNextWhileEndLabel();
        LabelsMaker.setCurrentIterationLabels(whileCheckLabel, whileEndLabel);
        /* Jump to check condition */
        Writers.emitInstruction(Constants.JUMP_UNCODITIONAL, whileCheckLabel);
        /* Loop start */
        Writers.emitLabel(whileStartLabel);
        /* Visit 'while' body */
        if (ctx.statement() != null)
            visit(ctx.statement());
        /* Check label */
        Writers.emitLabel(whileCheckLabel);
        /* Default jump */
        jump = Constants.JUMP_UNCODITIONAL;        
        /* If comparison is not done, than result of visiting must be
            compared to 0. Something like while (*s++); */
        if (ctx.whileCheck() != null) {
            condition = visit(ctx.whileCheck());
            if (!condition.isCompared())
                condition.compareWithZero();
            jump = RelationHelper.getTrueJump();
        }
        Writers.emitInstruction(jump, whileStartLabel);
        Writers.emitLabel(whileEndLabel);
        LabelsMaker.unsetCurrentIterationLabels();
        return null;
    }

    /*
        iterationStatement
            :   'do' statement 'while' '(' whileCheck? ')'  ';'  #DoWhileLoop
    */
    @Override
    public ExpressionObject visitDoWhileLoop(picoCParser.DoWhileLoopContext ctx) 
    {
        String whileStartLabel, whileCheckLabel, whileEndLabel;
        String jump;
        ExpressionObject condition;
        whileStartLabel = LabelsMaker.getNextWhileStartLabel();
        whileCheckLabel = LabelsMaker.getNextWhileCheckLabel();
        whileEndLabel = LabelsMaker.getNextWhileEndLabel();
        LabelsMaker.setCurrentIterationLabels(whileCheckLabel, whileEndLabel);
        
        /* Loop start */
        Writers.emitLabel(whileStartLabel);
        /* Visit 'do-while' body */
        if (ctx.statement() != null)
            visit(ctx.statement());
        /* Check label */
        Writers.emitLabel(whileCheckLabel);
        /* Default jump */
        jump = Constants.JUMP_UNCODITIONAL;        
        /* If comparison is not done, than result of visiting must be
            compared to 0. Something like while (*s++); */
        if (ctx.whileCheck() != null) {
            condition = visit(ctx.whileCheck());
            if (!condition.isCompared())
                condition.compareWithZero();
            jump = RelationHelper.getTrueJump();
        }
        Writers.emitInstruction(jump, whileStartLabel);
        Writers.emitLabel(whileEndLabel);
        LabelsMaker.unsetCurrentIterationLabels();
        return null;
    }

    
    
    /*
        jumpStatement
            :   'break'     ';'      #Break
            ;
    
    Break instruction is just uncoditional jump to the end of loop */
    @Override
    public ExpressionObject visitBreak(picoCParser.BreakContext ctx) 
    {
        String label = LabelsMaker.getLastBreakLabel();
        Writers.emitInstruction("jmp", label);
        return null;
    }

    /*
        jumpStatement
            :   'continue'     ';'      #Continue
            ;
    
    Continue instruction is just uncoditional jump to 
    the incrementation/check of loop */
    @Override
    public ExpressionObject visitContinue(picoCParser.ContinueContext ctx) 
    {    
        String label = LabelsMaker.getLastContinueLabel();
        Writers.emitInstruction("jmp", label);
        return null;
    }
    
    
    /*
        conditionalExpression
            :   logicalOrExpression '?' expression ':' conditionalExpression  #Conditional
            ;
                    expr1                 expr2                expr3       
    */
    @Override
    public ExpressionObject visitConditional(picoCParser.ConditionalContext ctx) 
    {
        ExpressionObject expr1, expr2, expr3;
        /* Visit all children and do compare operation if it is not done */
        /* First visit conditional expression (expr3) */
        if ((expr3 = visit(ctx.conditionalExpression())) == null)
            return null;
        if (expr3.isInteger())  // right operand of cmovcc instruction can't be integer
            expr3.putInRegister();
        /* Than visit expression (expr2) */
        if ((expr2 = visit(ctx.expression())) == null)
            return null;
        /* And finaly logicalOrExpression (expr1) */
        if ((expr1 = visit(ctx.logicalOrExpression())) == null)
            return null;
        if (!expr1.isCompared())
            expr1.compareWithZero(); // do comparison if it is not done
        if (expr1.isRegister())              // free taken register
            expr1.freeRegister();
        
        if (!expr2.isRegister())    // and left must be register
            expr2.putInRegister();
        if (expr3.isStringLiteral())
            expr3.putInRegister();
        
        ExpressionObject.castVariablesToMaxSize(expr2, expr3);
        Emitter.setConditionalMoveOperator(expr2, expr3);
        
        return expr2;
    }

    /*
        blockItem
            :   declarationList
            |   statement
            ;
    */
    @Override
    public ExpressionObject visitBlockItem(picoCParser.BlockItemContext ctx) 
    {
        ExpressionObject expr = super.visitBlockItem(ctx); 
        NasmTools.freeAllRegisters();
        return expr;
    }

    /* Negation is done simply by comparing variable with zero and doing
        "conditional equal set" (setcc where cc is condition). 
        Something like:
        cmp     eax, 0
        sete    al     
        So if eax was any other than 0 it becomes 0.
        If eax was 0 it becomes 1.
    
        unaryExpression 
            :   '!'  unaryExpression    #Negation
            ;
    */
    @Override
    public ExpressionObject visitNegation(picoCParser.NegationContext ctx) 
    {
        ExpressionObject expr;
        /* Visiti expression and try to recover */
        if ((expr = visit(ctx.unaryExpression())) == null)
            return null;
        expr.comparisonCheck();
        /* Compare it with zero and emit proper instruction */
        expr.compareWithZero();
        expr.setNegation();
        
        return expr;
    }

    /*
        unaryExpression 
            :   '&'  unaryExpression    #Adress
            ;
    */
    @Override
    public ExpressionObject visitAddress(picoCParser.AddressContext ctx) 
    {
        ExpressionObject expr;
        String nextFreeTemp, help;
        if ((expr = visit(ctx.unaryExpression())) == null)
            return null;
        expr.comparisonCheck();
        
        /* Address of an array is the same as the array itself, so expressions
            like:
            printf("%p", array) and printf("%p", &array) are identical */
        if (expr.isArray())
            return expr;
        /* Check if expr is variable */
        if (!Checker.checkAddress(expr, ctx))
            return null;
        /* Get proper sintax for lea instruction: lea rax,[rbp-8] for example */
        nextFreeTemp = NasmTools.getNextFreeTempStr(MemoryClassEnum.POINTER);
        help = "[" + expr.getStackDisp() + "]";
        Writers.emitInstruction("lea", nextFreeTemp, help);
        /* Set new properties to variable */
        expr = new ExpressionObject
            (nextFreeTemp, 
             MemoryClassEnum.POINTER, 
             ExpressionObject.REGISTER,
             expr.getType()
            );
        return expr;
    }

    /*
        unaryExpression
            :   '*'  unaryExpression    #Deref   
    */
    @Override
    public ExpressionObject visitDeref(picoCParser.DerefContext ctx) 
    {
        ExpressionObject expr;
        if ((expr = visit(ctx.unaryExpression())) == null)
            return null;
        if (!Checker.checkPointer(ctx, expr))
            return null;
        
        expr.dereference();
        
        return expr;
    }

    /*
        directDeclarator
            |   directDeclarator '[' constant? ']'   #ArrayDecl
    */
    @Override
    public ExpressionObject visitArrayDecl(picoCParser.ArrayDeclContext ctx) 
    {
        ExpressionObject expr;
        int size = 0;
        
        if (ctx.constant() != null) {
            if ((expr = visit(ctx.constant())) == null)
                return null;
            size = Integer.parseInt(expr.getText());
        }
        /* Just push size to array declarator */
        arrayDecl.push(size);
        
        return visit(ctx.directDeclarator());
    }

    /*
        postfixExpression
            :   postfixExpression '[' expression ']'     #Subscript
            ;
    */    
    @Override
    public ExpressionObject visitSubscript(picoCParser.SubscriptContext ctx) 
    {
        ExpressionObject source, expr;
        String casted;
        
        if ((source = visit(ctx.postfixExpression())) == null)
            return null;
        if ((expr = visit(ctx.expression())) == null)
            return null;
        expr.comparisonCheck();
        
        /* Check if source is array or pointer  */
        if (!Checker.checkSubscript(source, ctx))
            return null;
        /* Check if expression is integer  */
        if (!Checker.checkSubscriptingType(expr, ctx))
            return null;
        
        /* If there is free registers, and source is not in register,
            than it's address needs to be moved to one. That is done in 
            case that source is pointer */
        if (!source.isRegister())
            source.putAddressInRegister();
        
        if (!expr.isInteger()) {
            expr.putInRegister();
            casted = NasmTools.castVariable(expr.getText(), Constants.SIZE_OF_POINTER);
            expr.setText(casted);
            expr.setType(MemoryClassEnum.POINTER);
        }
        /* If source is simple pointer (or one dimensional array) 
            than subscripting is done directly by calculating displacement
            of n-th element. */    
        if (PointerTools.isSimplePointer(source))
            source.simpleSubscript(expr);
        else
            source.complexSubscript(expr);
        
        return source;
    }
    
    
    
}
