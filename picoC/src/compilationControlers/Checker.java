package compilationControlers;

import antlr.TranslationVisitor;
import antlr.picoCParser;
import tools.FunctionsAnalyser;
import tools.Variable;
import constants.MemoryClassEnum;
import java.util.List;
import tools.ExpressionObject;

/**
 *
 * @author aleksandar
 */
public class Checker 
{

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
                            TranslationVisitor.curFuncAna.getFunctionName(), "Too few arguments in function call");
                return false;
            }
            if (paramCount < argumentList.size()) {
                CompilationControler.errorOcured
                    (ctx.getStart(),
                            TranslationVisitor.curFuncAna.getFunctionName(), "Too many arguments in function call");
                return false;
            }
        } else {
            if (funcAnalyser.getNumberOfParameters() > 0) {
                CompilationControler.errorOcured
                    (ctx.getStart(),
                            TranslationVisitor.curFuncAna.getFunctionName(), "Too few arguments in function call");
                return false;
            }
        }
        return true;
    }
    /* This shoud check for all external functions.
        That could be some functions from gcc's standard library */
    public static boolean externalFunctionCheck(String functionName) 
    {
        switch (functionName) {
            case "printf":
                return true;
            case "scanf":
                return true;
        }
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
            CompilationControler.warningOcured
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

    public static boolean varSizeCheck(picoCParser.DirDeclContext ctx, int varSize) 
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
    
    public static boolean varDeclCheck(picoCParser.DirDeclContext ctx, String name) 
    {
        if (TranslationVisitor.curFuncAna.getLocalVariables().containsKey(name)
                || TranslationVisitor.curFuncAna.getParameterVariables().containsKey(name)) 
        {
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

    public static boolean varLocalAndParamCheck
    (boolean local, boolean param, picoCParser.IdContext ctx, String id) 
    {
        if (!local && !param) {
            CompilationControler.errorOcured
                (ctx.getStart(), TranslationVisitor.curFuncAna.getFunctionName(),
                        "Variable " + "'" + id  + "'" 
                        + " is not declared");
            return false;
        }
        return true;
    }

    public static void varInitCheck
    (boolean local, Variable newVar, String id, picoCParser.IdContext ctx) 
    {
        if (local && newVar != null && !newVar.isInitialized()) {
            CompilationControler.warningOcured
                (ctx.getStart(), TranslationVisitor.curFuncAna.getFunctionName(),
                        "Variable " + "'" + id  + "'" 
                        + " is used uninitialized");
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
                            "Trying to dereference non-pointer type");
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
    
}
