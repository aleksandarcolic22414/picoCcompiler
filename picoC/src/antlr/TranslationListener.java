package antlr;

import tools.FunctionsAnalyser;
import constants.MemoryClassEnum;
import nasm.NasmTools;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import tools.LabelsMaker;
import tools.Pointer;
import tools.PointerTools;


/**
 *
 * @author Aleksandar Colic
 */
public class TranslationListener extends picoCBaseListener 
{
    picoCParser parser;
    TranslationVisitor visitor;
    /* Map that contains informations about all functions
        that are beeing compiled */
    public static Map<String, FunctionsAnalyser> mapFuncAna;
    
    /* Current function context.  */
    public static FunctionsAnalyser curFuncCtx = null;
    
    /* Current declarator  */
    public static MemoryClassEnum currentDeclaratorType = null;
    
    /* List represent current pointer  */
    public static LinkedList<Pointer> pointerInitializator;
    
    /* List represent current array sizes */
    public static LinkedList<Integer> arrayInitializator;
    
    public TranslationListener()
    {
        pointerInitializator = new LinkedList<>();
        arrayInitializator = new LinkedList<>();
        mapFuncAna = new HashMap<>();
    }

    @Override
    public void enterFunctionBody(picoCParser.FunctionBodyContext ctx) 
    {
        curFuncCtx.setFunctionContext(true);
    }

    @Override
    public void exitFunctionBody(picoCParser.FunctionBodyContext ctx) 
    {
        curFuncCtx.setFunctionContext(false);
    }

    @Override
    public void enterDeclarator(picoCParser.DeclaratorContext ctx) 
    {
        pointerInitializator.clear();
        arrayInitializator.clear();
    }

    @Override
    public void exitDeclarator(picoCParser.DeclaratorContext ctx) 
    {
        pointerInitializator.clear();
        arrayInitializator.clear();
    }

    @Override
    public void enterFunctionDefinition(picoCParser.FunctionDefinitionContext ctx) 
    {
        /* Since declarator.getText() can return function name with
            pointer prefix (**func for example), that prefix needs to be
            removed from this string. */
        String functionName = ctx.declarator().getText();
        functionName = functionName.replaceAll("\\*+", "");
        if (mapFuncAna.containsKey(functionName))
            return ;
        FunctionsAnalyser fa = new FunctionsAnalyser(functionName);
        mapFuncAna.put(functionName, fa);
        curFuncCtx = fa;
    }

    @Override
    public void enterParameterList(picoCParser.ParameterListContext ctx) 
    {
        curFuncCtx.setParameterContext(true);
        /* Get list of parameters */
        List<picoCParser.ParameterContext> parameterList = ctx.parameter();
        /* Calculate displacement for parameters */
        int sizeOfParams, sizeOfVar, paramsNum, i;
        sizeOfParams = sizeOfVar = 0;
        paramsNum = parameterList.size();
        MemoryClassEnum type;
        /* Iterate over list and calculate total size of parameters in bytes */
        for (i = 0; i < paramsNum; ++i) {
            /* Get type of var and convert it to bytes */
            int tokenType = parameterList.get(i).typeSpecifier().type.getType();
            /* Variable is pointer if it has more than 1 child in declarator
                context or has more than 1 child in directDeclarator context */
            boolean ptr = parameterList.get(i).declarator().getChildCount() > 1;
            ptr = ptr || 
                    parameterList.get(i)
                    .declarator()
                    .directDeclarator()
                    .getChildCount() > 1;
            if (ptr)
                type = MemoryClassEnum.POINTER;
            else
                type = NasmTools.getTypeOfVar(tokenType);
            sizeOfVar = NasmTools.getSize(type);
            /* Add it to overall size */
            sizeOfParams += sizeOfVar;
        }
        /* Set in function analyser */
        curFuncCtx.setNumberOfParameters(paramsNum);
        curFuncCtx.setSpaceForParams(sizeOfParams);
        /* Clear lists */
        arrayInitializator.clear();
        pointerInitializator.clear();
    }

    @Override
    public void exitParameterList(picoCParser.ParameterListContext ctx) 
    {
        curFuncCtx.setParameterContext(false);
    }
    
    @Override
    public void enterDirDecl(picoCParser.DirDeclContext ctx) 
    {
        if (curFuncCtx == null || !curFuncCtx.isFunctionContext())
            return;       
        
        int locals = curFuncCtx.getSpaceForLocals();
        int sizeOfVar;
        int totalSize;
        
        /* Calculate space on stack for local variables  */
        if (pointerInitializator.isEmpty())
            sizeOfVar = NasmTools.getSize(currentDeclaratorType);
        else 
            sizeOfVar = NasmTools.getSize(MemoryClassEnum.POINTER);
        
        /* If variable is array, than it's total size is calculated by
            multiplying size of one variable and number of variables */
        if (arrayInitializator.isEmpty())
            totalSize = sizeOfVar;
        else
            totalSize = sizeOfVar * NasmTools.multiplyList(arrayInitializator);
        /* Set displacement in current function context */
        curFuncCtx.setSpaceForLocals(locals + totalSize);
        pointerInitializator.clear();
        arrayInitializator.clear();
    }

    @Override
    public void enterTypeSpecifier(picoCParser.TypeSpecifierContext ctx) 
    {
        currentDeclaratorType = NasmTools.getTypeOfVar(ctx.type.getType());
    }

    /*
        directDeclarator
            |   directDeclarator '[' constant? ']'   #ArrayDecl
            ;
    */
    /* Just inserting size of array into arrayInitializator list */
    /* If there is no constant, just simulate 0 sized array in order to 
        calculate correct size */
    @Override
    public void enterArrayDecl(picoCParser.ArrayDeclContext ctx) 
    {
        String hsize;
        int size = 0;
        if (ctx.constant() != null) {
            hsize = ctx.constant().getText();
            size = Integer.parseInt(hsize);
        }
        arrayInitializator.push(size);
    }

    
    
    @Override
    public void enterSimplePtr(picoCParser.SimplePtrContext ctx) 
    {
        if (curFuncCtx == null || !curFuncCtx.isFunctionContext())
            return;       
        
        PointerTools.insertPointerType(pointerInitializator, currentDeclaratorType);
    }
    
    @Override
    public void enterDeclaration(picoCParser.DeclarationContext ctx) 
    {
        int tokenType = ctx.typeSpecifier().type.getType();
        currentDeclaratorType = NasmTools.getTypeOfVar(tokenType);
    }

    /* If selectionStatement is visited, it's depth is stored in list
        in LabelsMaker class. It is needed for calculating else if () statement 
        labels. */
    @Override
    public void enterSelectionStatement(picoCParser.SelectionStatementContext ctx) 
    {
        LabelsMaker.insertDepth();
        LabelsMaker.increaseDepth();
    }
    /* Counter of depth in LabelsMaker class is reset to 0. */
    @Override
    public void exitSelectionStatement(picoCParser.SelectionStatementContext ctx) 
    {
        LabelsMaker.resetSelectionDepthCounter();
    }

}
