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
import static nasm.NasmTools.getNextRegForFuncCall;
import static nasm.NasmTools.resetRegisterPicker;
import tools.Emitter;
import tools.ExpressionObject;
import tools.LabelsMaker;
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
    
    /* List represent current pointer declarator type */
    public static LinkedList<MemoryClassEnum> curPointer;
    
    /* Current variable name */
    public static String currentVariableName;
    
    public TranslationVisitor() 
    {
        curPointer = new LinkedList<>();
        functions = new HashMap<>();
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
            :    typeSpecifier functionName '(' parameterList? ')' functionBody  
    */
    @Override
    public ExpressionObject visitFunctionDefinition(picoCParser.FunctionDefinitionContext ctx) 
    {
        /* Get name and memory class of function */
        String name = ctx.functionName().getText();
        int tokenType = ctx.typeSpecifier().type.getType();
        MemoryClassEnum memclass;
        memclass = NasmTools.getTypeOfVar(tokenType);
        
        /* Chech weather function is already defined */
        if (!Checker.funcDefCheck(ctx, name))
            return null;
        
        /* Create new function analyser object, and set it's type specifier
        to some type */
        FunctionsAnalyser fa = new FunctionsAnalyser(name);
        fa.setMemoryClass(memclass);
        
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
        /* Visit rest of the function. (visitChildren) */
        super.visitFunctionDefinition(ctx);
        
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
            reg = getNextRegForFuncCall(memclass);
            
            /* Emit copying from registers to stack for arguments */
            Writers.emitInstruction("mov", paramPos, reg);
        }
        resetRegisterPicker();
        
        
        /* Free all registers for function body */
        NasmTools.freeAllRegisters();
        curFuncAna.setParameterContext(false);
        return null;
    }

    /* parameter 
          :   typeSpecifier ID   
    */
    @Override
    public ExpressionObject visitParameter(picoCParser.ParameterContext ctx) 
    {
        /* Get variable's memory class  */
        int type = ctx.typeSpecifier().type.getType();
        MemoryClassEnum typeSpecifier = NasmTools.getTypeOfVar(type);;
        curFuncAna.setCurrentDeclaratorType(typeSpecifier);
        
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
        if (curFuncAna == null) {
            DataSegment.DeclareExtern(null);
            return null;
        }
        /* Get memory class for declaration */
        int tokenType = ctx.typeSpecifier().type.getType();
        MemoryClassEnum memclass = NasmTools.getTypeOfVar(tokenType);
        curFuncAna.setCurrentDeclaratorType(memclass);
        /* Keep declaring rest of the list */
        
        return super.visitDeclaration(ctx);
    }
    
    @Override
    public ExpressionObject visitDeclWithInit(picoCParser.DeclWithInitContext ctx) 
    {
        visit(ctx.declarator());
        /* Expression */
        ExpressionObject expr, newVariable;
        int operation = picoCParser.ASSIGN;
        String id = currentVariableName;
        /* Get variable context */
        Variable var = curFuncAna.getAnyVariable(id);
        /* Check if it is declared */
        if (!Checker.varDeclCheck(ctx, id, var))
            return null;
        
        /* Initialize new Expression object */
        var.setInitialized(true);
        newVariable = new ExpressionObject(var);
        /* Visit expression and try to recover from error. */
        if ((expr = visit(ctx.assignmentExpression())) == null) 
            return null;
        expr.comparisonCheck();
        /* Cast variable if needed */
        if (!expr.isInteger())
            expr.castVariable(var.getTypeSpecifier());
        
        /* See which operator is used for assign and emit proper instruction */
        Emitter.decideAssign(
                newVariable, expr, operation);
        
        if (expr.isRegister())
            expr.freeRegister();
        
        currentVariableName = null;
        /* Return new Object */
        return newVariable;
    }

    @Override
    public ExpressionObject visitDirDecl(picoCParser.DirDeclContext ctx) 
    {
        /* Variable name, stack position, and memory class */
        String name, stackPosition;
        name = ctx.ID().getText();
        currentVariableName = name;
        MemoryClassEnum typeSpecifier;
        Variable var;
        /* Chech if variable is already declared */
        if (!Checker.varDeclCheck(ctx, name))
            return null;
        
        /* Check if variable is pointer */
        if (curPointer.isEmpty())
            typeSpecifier = curFuncAna.getCurrentDeclaratorType();
        else
            typeSpecifier = MemoryClassEnum.POINTER;
        
        if (curFuncAna.isParameterContext()) {
            /* Get stack position */
            stackPosition = curFuncAna.declareParameterVariable(typeSpecifier);
            /* Create new variable object */
            var = new Variable
                (name, stackPosition, false, typeSpecifier, curPointer);
            /* Insert new variable in parameter variables */
            curFuncAna.getParameterVariables().put(name, var);
        } else {
            stackPosition = curFuncAna.declareLocalVariable(typeSpecifier);
            var = new Variable
                (name, stackPosition, false, typeSpecifier, curPointer);
            /* Insert new variable in local variables */
            curFuncAna.getLocalVariables().put(name, var);
        }
        /* Size in bytes */
        int varSize = NasmTools.getSize(typeSpecifier);
        /* Check size of variable */
        if (!Checker.varSizeCheck(ctx, varSize))
            return null;
        
        return new ExpressionObject(var);
    }

    @Override
    public ExpressionObject visitSimplePtr(picoCParser.SimplePtrContext ctx) 
    {
        MemoryClassEnum type;
        type = curFuncAna.getCurrentDeclaratorType();
        NasmTools.insertPointerType(curPointer, type);
        return super.visitSimplePtr(ctx);
    }

    @Override
    public ExpressionObject visitMultiplePrt(picoCParser.MultiplePrtContext ctx) 
    {
        MemoryClassEnum type;
        type = curFuncAna.getCurrentDeclaratorType();
        NasmTools.insertPointerType(curPointer, type);
        return super.visitMultiplePrt(ctx);
    }
    
    /*
        assignmentExpression
            :   unaryExpression assignmentOperator assignmentExpression    #Assign
            ;
    */
    @Override
    public ExpressionObject visitAssign(picoCParser.AssignContext ctx) 
    {
        
        /* Expression */
        ExpressionObject expr, newVariable;
        int operation = ctx.assignmentOperator().op.getType();
        Checker.setVarInitCheck(false);    // Prevent checking for initialization
        newVariable = visit(ctx.unaryExpression());
        Checker.setVarInitCheck(true);
        String id = newVariable.getName();
        /* Just to continue checking for init in visitID context. */
        
        /* Get variable context */
        Variable var = curFuncAna.getAnyVariable(id);
        var.setInitialized(true);
        
        /* Visit expression and try to recover from error. */
        if ((expr = visit(ctx.assignmentExpression())) == null) 
            return null;
        expr.comparisonCheck();
        /* Cast variable if needed */
        if (!expr.isInteger())
            expr.castVariable(newVariable.getType());
        if (!Checker.checkAssign(newVariable, expr, ctx, operation))
            return null;
        
        /* See which operator is used for assign and emit proper instruction */
        if (newVariable.isPointer())
            Emitter.decideAssignPointers(newVariable, expr, operation);
        else
            Emitter.decideAssign(
                    newVariable, expr, operation);
        
        
        
        if (expr.isRegister())
            expr.freeRegister();
        /* Return new Object */
        return newVariable;
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
        /* Try to recover from error */
        if ((expr = visit(ctx.expression())) == null)
            return null;
        expr.comparisonCheck();
        /* Set function has return  */
        curFuncAna.setHasReturn(true);
        /* Cast expression to proper size */
        expr.castVariable(curFuncAna.getMemoryClass());
        /* mov result to eax for return if result is not eax */
        if (!expr.isRegisterA())
            Writers.emitInstruction("mov", NasmTools.STRING_EAX, expr.getText());
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
        if (!Checker.externalFunctionCheck(functionName))
            type = functions.get(functionName).getMemoryClass();
        String nextFreeTemp = NasmTools.showNextFreeTemp(type);
        String areg = NasmTools.registerToString(NasmTools.AREG, type);
        /* Registers are saved and freed for further use */
        NasmTools.saveRegistersOnStack();
        NasmTools.moveArgsToRegisters(this, argumentList);
        /* Special setup */
        if (Checker.externalFunctionCheck(functionName))
            Writers.emitInstruction("mov", "eax", "0");
        Writers.emitInstruction("call", functionName);
        /* Avoid unnecessary move */
        if (!NasmTools.isRegisterA(nextFreeTemp))
            Writers.emitInstruction("mov", nextFreeTemp, areg);
        NasmTools.restoreRegisters();
        nextFreeTemp = NasmTools.getNextFreeTempStr(type);
        
        return new ExpressionObject
            (nextFreeTemp, 
             type, 
             ExpressionObject.REGISTER
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
            | STRING_LITERAL
            ;    
    */
    @Override
    public ExpressionObject visitArgument(picoCParser.ArgumentContext ctx) 
    {
        ExpressionObject expr = visit(ctx.assignmentExpression());
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
        int maxSize;
        /* Try to visit children and recover if error ocured */
        if ((leftExpr = visit(ctx.additiveExpression())) == null)
            return null;
        leftExpr.comparisonCheck();
        
        if ((rightExpr = visit(ctx.multiplicativeExpression())) == null)
            return null;
        rightExpr.comparisonCheck();
        
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
        /* If there is free registers, and leftExpr is variable, than it needs
            to be moved to one */
        if (!leftExpr.isRegister() && NasmTools.hasFreeRegisters())
            leftExpr.putInRegister();
        /* Arithmetic is done with minimum 4 bytes registers, so if both 
            variables are 1 byte long, left is cased to 4 bytes. */
        if (leftExpr.getType() == MemoryClassEnum.CHAR
                && rightExpr.getType() == MemoryClassEnum.CHAR)
            leftExpr.castVariable(MemoryClassEnum.INT);
        /* Cast to proper type if needed */
        maxSize = ExpressionObject.castVariablesToMaxSize(leftExpr, rightExpr);
        String operation = NasmTools.getOperation(ctx.op.getType());
        
        if (leftExpr.isPointer() || rightExpr.isPointer()) {
            /* Set left to be pointer and make it easier to code */
            if (rightExpr.isPointer() && !leftExpr.isPointer()) {
                help = rightExpr;
                rightExpr = leftExpr;
                leftExpr = help;
            }
            Emitter.emitPointersAddSub(leftExpr, rightExpr, operation);
        } else {
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
        /* Just for more readable code */
        boolean local = true, param = true;
        
        String id = ctx.ID().getText();
        /* Position of variable on stack */
        String stackPosition;
        /* Next free register */
        String nextFreeTemp;
        
        /* Check if variable is local */
        Variable newVar = curFuncAna.getLocalVariables().get(id);
        /* if variable is null, then it's sure that it is not local */
        if (newVar == null)
            local = false;
        /* If it is not local, maybe it's parameter */
        if (!local)
            newVar = curFuncAna.getParameterVariables().get(id);
        /* It is not parameter, if it is null */
        if (newVar == null)
            param = false;
        /* TODO: If it is not local and it is not param, than it shoud be checked
            if it is extern */
        if (!Checker.varLocalAndParamCheck(local, param, ctx, id))
            return null;
        if (newVar == null)
            return null;
        /* Check if variable is initialized */
        Checker.varInitCheck(local, newVar, id, ctx);
        
        /* Return new variable */
        return new ExpressionObject(newVar);
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
            type = res.getTypeOfPointer();
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
            type = res.getTypeOfPointer();
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
            type = res.getTypeOfPointer();
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
            type = res.getTypeOfPointer();
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
        if (ctx.forInit() != null)
            expr = visit(ctx.forInit());
        if (expr != null)
            expr.comparisonCheck();
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
            condition = visit(ctx.forCheck());
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
    
}
