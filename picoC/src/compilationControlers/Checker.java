package compilationControlers;

import constants.Constants;
import antlr.TranslationVisitor;
import antlr.picoCParser;
import tools.FunctionsAnalyser;
import tools.Variable;
import constants.MemoryClassEnum;
import java.util.List;
import java.util.Map;
import nasm.IncludeSegment;
import tools.ExpressionObject;

/**
 *
 * @author aleksandar
 */
public class Checker 
{

    private static boolean varInitCheck = true;
    
    
    public static boolean varInitCheck() 
    {
        return varInitCheck;
    }

    public static void setVarInitCheck(boolean avarInitCheck) 
    {
        varInitCheck = avarInitCheck;
    }
    
    public static boolean functionCallCheck
    (picoCParser.FuncCallContext ctx, List<picoCParser.ArgumentContext> argumentList) 
    {
        int paramCount;
        FunctionsAnalyser funcAnalyser;
        String functionName = ctx.postfixExpression().getText();
        if (Checker.externalFunctionCheck(functionName))
            return true;
        if ((funcAnalyser = TranslationVisitor.functions.get(functionName)) == null) {
            CompilationControler.errorOcured
                (ctx.getStart(), TranslationVisitor.curFuncAna.getFunctionName(),
                        "Function " + functionName + " doesn't exist");
            return false;
        }
        /* Check if number of arguments matches number of parameters */
        if (argumentList != null) {
            if ((paramCount = funcAnalyser.getNumberOfParameters()) > argumentList.size()) {
                CompilationControler.errorOcured
                    (ctx.getStart(),
                            TranslationVisitor.curFuncAna.getFunctionName(), 
                                "Too few arguments in function call");
                return false;
            }
            if (paramCount < argumentList.size()) {
                CompilationControler.errorOcured
                    (ctx.getStart(),
                            TranslationVisitor.curFuncAna.getFunctionName(), 
                                "Too many arguments in function call");
                return false;
            }
        } else {
            if (funcAnalyser.getNumberOfParameters() > 0) {
                CompilationControler.errorOcured
                    (ctx.getStart(),
                            TranslationVisitor.curFuncAna.getFunctionName(), 
                                "Too few arguments in function call");
                return false;
            }
        }
        return true;
    }
    /* This shoud check for all external functions.
        That could be some functions from gcc's standard library */
    public static boolean externalFunctionCheck(String functionName) 
    {
        if (Constants.GCC_LIB_CTYPE.contains(functionName) && IncludeSegment.isIncludedCtype())
            return true;
        if (Constants.GCC_LIB_STRING.contains(functionName) && IncludeSegment.isIncludedString())
            return true;
        if (Constants.GCC_LIB_STDIO.contains(functionName) && IncludeSegment.isIncludedStdio())
            return true;
        if (Constants.GCC_LIB_STDLIB.contains(functionName) && IncludeSegment.isIncludedStdlib())
            return true;
        
        return false;
    }
    /* Check for multiple definitions of function */
    public static boolean funcDefCheck(picoCParser.FunctionDefinitionContext ctx, String name) 
    {
        if (TranslationVisitor.functions.containsKey(name)) {
            String error = "Multiple definitions of " + name;
            System.err.println(error);
            CompilationControler.errorOcured
                (ctx.getStart(), 
                        name,
                            error);
            return false;
        } 
        return true;
    }

    public static void funcRetStatCheck(picoCParser.FunctionDefinitionContext ctx) 
    {
        if (TranslationVisitor.curFuncAna.getMemoryClass() != MemoryClassEnum.VOID && 
                !TranslationVisitor.curFuncAna.isHasReturn()) 
        {
            CompilationControler.errorOcured
                (ctx.getStart(), 
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Missing return statement in function " + 
                                    TranslationVisitor.curFuncAna.getFunctionName());
        }
        
    }

    public static boolean paramCheck(picoCParser.ParameterContext ctx, String name) 
    {
        if (TranslationVisitor.curFuncAna.getParameterVariables().containsKey(name)) {
            CompilationControler.errorOcured
                (ctx.getStart(), 
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Two or more params with the same name: " + name);
            return false;    
        }
        return true;
    }

    public static boolean varSizeCheck
    (picoCParser.DirDeclContext ctx, MemoryClassEnum type) 
    {
        if (type == MemoryClassEnum.VOID) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Void variable not alowed!");
            return false;
        }
        return true;
    }

    public static boolean varSizeCheck(picoCParser.ParameterContext ctx, int varSize) 
    {
        if (varSize == -1) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Void variable not alowed!");
            return false;
        }
        return true;
    }
    
    public static boolean varSizeCheck(picoCParser.DeclarationContext ctx, int varSize) 
    {
        if (varSize == -1) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Void variable not alowed!");
            return false;
        }
        return true;
    }
    
    /* Checks whether variable is already declared. If it is not declared
        in current compound block, it can be declared. If we are in
        first compound block, then variable can't have the same name
        as one the parameters. */
    public static boolean varDeclCheck(picoCParser.DirDeclContext ctx, String name) 
    {
        FunctionsAnalyser fa = TranslationVisitor.curFuncAna;
        
        if (fa.getLocalVariablesInLastBlock().containsKey(name)
                || 
                fa.getLocalVariables().size() == 1 && 
                fa.getParameterVariables().containsKey(name)) {
            
            CompilationControler.errorOcured
                (ctx.getStart(), 
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Multiple declaration of " + name);
            return false;    
        }
        return true;
    }

    public static boolean varDeclCheck
    (picoCParser.AssignContext ctx, String id, Variable var) 
    {
        if (var == null) {
            CompilationControler.errorOcured(
                    ctx.getStart(), 
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Variable " + "'" + id + "'" + " not declared");
            return false;
        }
        return true;
    }

    public static boolean varCheck
    (boolean local, boolean param, boolean extern, 
     picoCParser.IdContext ctx, String id) 
    {
        if (local || param || extern)
            return true;
        
        CompilationControler.errorOcured
            (ctx.getStart(), TranslationVisitor.curFuncAna.getFunctionName(),
                "Variable " + "'" + id + "'"
                    + " is not declared");
        return false;
    }

    public static void varInitCheck
    (boolean localOrExtern, Variable newVar, String id, picoCParser.IdContext ctx) 
    {
        if (newVar.isArray())
            return;
        
        if (varInitCheck && localOrExtern && !newVar.isInitialized()) {
            CompilationControler.warningOcured
                (ctx.getStart(), TranslationVisitor.curFuncAna.getFunctionName(),
                        "Variable " + "'" + id  + "'" 
                        + " may not have been initialized");
        }
    }

    public static boolean checkPreDec(ExpressionObject res, picoCParser.PreDecContext ctx) 
    {
        if (!res.isStackVariable()) {
            CompilationControler.errorOcured
                    (ctx.getStart(), TranslationVisitor.curFuncAna.getFunctionName(),
                            "Only variables can be pre-decremened");
            return false;
        }
        return true;
    }

    public static boolean checkPreInc(ExpressionObject res, picoCParser.PreIncContext ctx) 
    {
        if (!res.isStackVariable()) {
            CompilationControler.errorOcured
                    (ctx.getStart(), TranslationVisitor.curFuncAna.getFunctionName(),
                            "Only variables can be pre-incremened");
            return false;
        }
        return true;
    }

    public static boolean checkDivisionByZero
    (int a, int b, picoCParser.MulDivModContext ctx) 
    {
        if (b == 0) {
            CompilationControler.errorOcured
                    (ctx.getStart(), TranslationVisitor.curFuncAna.getFunctionName(),
                            "Division by zero error occurs");
            return false;
        }
        return true;
    }

    public static boolean checkPostInc(ExpressionObject res, picoCParser.PostIncContext ctx) 
    {
        if (!res.isStackVariable()) {
            CompilationControler.errorOcured
                    (ctx.getStart(), TranslationVisitor.curFuncAna.getFunctionName(),
                            "Only variables can be post-incremened");
            return false;
        }
        return true;
    }

    public static boolean checkPostDec(ExpressionObject res, picoCParser.PostDecContext ctx) 
    {
        if (!res.isStackVariable()) {
            CompilationControler.errorOcured
                (ctx.getStart(), TranslationVisitor.curFuncAna.getFunctionName(),
                    "Only variables can be post-decremened");
            return false;
        }
        return true;
    }

    public static void checkVarMatch(ExpressionObject left, ExpressionObject right) 
    {
        
    }

    public static boolean checkAddress(ExpressionObject expr, picoCParser.AddressContext ctx) 
    {
        if (!expr.isStackVariable() && !expr.isExternVariable()) {
            CompilationControler.errorOcured
                (ctx.getStart(), TranslationVisitor.curFuncAna.getFunctionName(), 
                        "Address of non-variable type");
            return false;
        }
        return true;
    }

    public static boolean varDeclCheck
    (picoCParser.DeclWithInitContext ctx, String id, Variable var) 
    {
        if (var == null) {
            CompilationControler.errorOcured(
                    ctx.getStart(), 
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Variable " + "'" + id + "'" + " not declared");
            return false;
        }
        return true;
    }

    public static boolean checkPointer
    (picoCParser.DerefContext ctx, ExpressionObject expr) 
    {
        if (!expr.isPointer()) {
            CompilationControler.errorOcured(
                    ctx.getStart(), 
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Trying to dereference non-pointer type " + ctx.getText());
            return false;
        }
        if (expr.getPointer().getType() == MemoryClassEnum.VOID) {
            CompilationControler.errorOcured(
                    ctx.getStart(), 
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Dereferencing void pointer " + ctx.getText());
            return false;
        }
        return true;
    }

    public static boolean checkAddSubPointers
    (ExpressionObject leftExpr, ExpressionObject rightExpr, 
    picoCParser.AddSubContext ctx) 
    {
        if (leftExpr.isPointer() 
                && rightExpr.isPointer() 
                    && ctx.op.getType() == picoCParser.ADD) 
        {
            CompilationControler.errorOcured(
                    ctx.getStart(), 
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Adding two pointer-type operands");
            return false;
        }
        
        if (!leftExpr.isPointer() 
                && rightExpr.isPointer() 
                    && ctx.op.getType() == picoCParser.SUB) 
        {
            CompilationControler.errorOcured(
                    ctx.getStart(), 
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Substracting pointer from non-pointer type");
            return false;
        }
        
        return true;
    }

    /* ... */
    public static boolean checkAssign
    (ExpressionObject leftExpr, ExpressionObject rightExpr, 
    picoCParser.AssignContext ctx, int operation) 
    {
        if (operation == picoCParser.ASSIGN) {
            if (leftExpr.getType() != rightExpr.getType()) {
                CompilationControler.warningOcured(
                        ctx.getStart(), 
                            TranslationVisitor.curFuncAna.getFunctionName(),
                                "Different type of variables in assign context");
            }
            return true;
        }
        
        if (leftExpr.isPointer()) 
        {
            if (rightExpr.isPointer()) {
                if (operation == picoCParser.ASSIGN_SUB)
                    return true;
                CompilationControler.errorOcured(
                        ctx.getStart(), 
                            TranslationVisitor.curFuncAna.getFunctionName(),
                                "Wrong type of operands");
                return false;
            }
            if (operation == picoCParser.ASSIGN_ADD 
                    || operation == picoCParser.ASSIGN_SUB)
                return true;
            CompilationControler.errorOcured(
                        ctx.getStart(), 
                            TranslationVisitor.curFuncAna.getFunctionName(),
                                "Wrong type of operands");
            return false;
        }
        
        if (!leftExpr.isPointer() 
                && rightExpr.isPointer() 
                    && operation == picoCParser.ASSIGN_SUB) 
        {
            CompilationControler.errorOcured(
                    ctx.getStart(), 
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Substracting pointer from non-pointer type");
            return false;
        }
        
        return true;
    }

    public static boolean checkCharSequence(picoCParser.CharContext ctx, String charSeq) 
    {
        if (charSeq.length() == 4) {
            char ch = charSeq.charAt(2);
            if (ch != 'n' && ch != 'r' && ch != 't' && ch != '0') {
                CompilationControler.errorOcured(
                        ctx.getStart(), 
                            TranslationVisitor.curFuncAna.getFunctionName(),
                                "Not valid char constant");
                return false;
            }
        }
        return true;
    }

    public static boolean varExternDeclCheck(picoCParser.DirDeclContext ctx, String name) 
    {
        if (TranslationVisitor.externVariables.containsKey(name)) 
        {
            CompilationControler.errorOcured
                (ctx.getStart(), 
                        "External declaration",
                            "Multiple declaration of " + name);
            return false;    
        }
        return true;
    }

    public static boolean varTypeCheck
    (picoCParser.DirDeclContext ctx, MemoryClassEnum typeSpecifier) 
    {
        if (typeSpecifier == MemoryClassEnum.VOID) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Void variables not alowed!");
            return false;
        }
        return true;
    }


    public static boolean checkConstantExpression
    (picoCParser.DeclWithInitContext ctx, ExpressionObject expr) 
    {
        if (!expr.isInteger() && !expr.isStringLiteral()) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        "External declaration",
                            "Initialization expression not constant");
            return false;
        }
        return true;
    }

    public static boolean checkVariableExistance
    (ExpressionObject expr, picoCParser.AssignContext ctx) 
    {
        if (expr.isInteger() || expr.isStringLiteral()) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Try to assign to non-variable type");
            return false;
        }
        return true;
    }

    public static boolean checkReturnValue
    (MemoryClassEnum memoryClass, picoCParser.ReturnContext ctx) 
    {
        if (ctx.expression() == null && memoryClass != MemoryClassEnum.VOID) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Returning void type in non-void function");
            return false;
        }
        return true;
    }

    public static boolean checkArrayAssign
    (ExpressionObject left, picoCParser.AssignContext ctx) 
    {
        if (left.isArray()) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Trying to assign to an array type");
            return false;
        }
        return true;
    }

    public static boolean checkSubscript
    (ExpressionObject array, picoCParser.SubscriptContext ctx) 
    {
        if (!array.isArray() && !array.isPointer()) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Subscripting non-array type");
            return false;
        }
        return true;
    }

    public static boolean checkSubscriptingType
    (ExpressionObject expr, picoCParser.SubscriptContext ctx) 
    {
        if (expr.isPointer()) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Subscripting with non-integer type");
            return false;
        }
        return true;
    }

    public static boolean checkComplement
    (ExpressionObject expr, picoCParser.ComplementContext ctx) 
    {
        if (expr.isPointer()) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Wrong type of argument for '~' operation");
            return false;
        }
        return true;
    }

    public static boolean checkCast
    (ExpressionObject castExpr, picoCParser.CastContext ctx) 
    {
        if (castExpr.isArray()) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Casting array type");
            return false;
        }
        return true;
    }

    public static boolean checkAnd
    (ExpressionObject leftExpr, ExpressionObject rightExpr, 
    picoCParser.AndContext ctx) 
    {
        if (leftExpr.isPointer() || rightExpr.isPointer()) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Trying to AND pointer type");
            return false;
        }
        return true;
    }
    
    public static boolean checkExclusiveOr
    (ExpressionObject leftExpr, ExpressionObject rightExpr, 
    picoCParser.ExclusiveOrContext ctx) 
    {
        if (leftExpr.isPointer() || rightExpr.isPointer()) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Trying to XOR pointer type");
            return false;
        }
        return true;
    }

    public static boolean checkOr
    (ExpressionObject leftExpr, ExpressionObject rightExpr, 
    picoCParser.InclusiveOrContext ctx) 
    {
        if (leftExpr.isPointer() || rightExpr.isPointer()) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Trying to OR pointer type");
            return false;
        }
        return true;
    }

    public static boolean checkShift
    (ExpressionObject leftExpr, ExpressionObject rightExpr, 
    picoCParser.ShiftContext ctx) 
    {
        if (leftExpr.isPointer()) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Shifting pointer type");
            return false;
        } else if (rightExpr.isPointer()) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Shifting with pointer type");
            return false;
        } else
            return true;
    }

    public static void checkInitWithAssign
    (ExpressionObject left, ExpressionObject right, 
     picoCParser.DeclWithInitContext ctx) 
    {
        if (left.getType() != right.getType()) {
            /* This one could be extern error, so we must check if curFuncAna
                is initialized. */
            FunctionsAnalyser fa = TranslationVisitor.curFuncAna;
            CompilationControler.warningOcured(
                    ctx.getStart(),
                        fa != null ? fa.getFunctionName() : null,
                            "Different type of variables in assign context");
        }
    }

    public static boolean checkVarMatch
    (picoCParser.ConditionalContext ctx, ExpressionObject expr2, ExpressionObject expr3) 
    {
        if (expr2.getType() != expr3.getType()) {
            CompilationControler.errorOcured
                (ctx.getStart(), TranslationVisitor.curFuncAna.getFunctionName(),
                    "Different types in conditional expression");
            return false;
        }
        return true;
    }

    public static boolean CheckParameter(picoCParser.DirDeclContext ctx, String name) 
    {
        if (TranslationVisitor.curFuncAna.getParameterVariables().containsKey(name)) 
        {
            CompilationControler.errorOcured
                (ctx.getStart(), 
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Multiple declaration of " + name);
            return false;    
        }
        return true;
    }
    
}
