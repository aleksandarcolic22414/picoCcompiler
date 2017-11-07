package antlr;

import tools.FunctionsAnalyser;
import constants.MemoryClassEnum;
import nasm.NasmTools;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import nasm.DataSegment;
import tools.LabelsMaker;


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
    
    /* Curent function context.  */
    public static FunctionsAnalyser curFuncCtx = null;
    
    public static MemoryClassEnum currentDeclaratorType = null;
    
    /* List represent current pointer declarator type */
    public static LinkedList<MemoryClassEnum> pointerInitializator;
    
    public TranslationListener()
    {
        pointerInitializator = new LinkedList<>();
        mapFuncAna = new HashMap<>();
    }

    @Override
    public void enterFunctionBody(picoCParser.FunctionBodyContext ctx) 
    {
        curFuncCtx.setFunctionContext(true);
        curFuncCtx.setParameterContext(false);
    }
    
    @Override
    public void enterFunctionDefinition(picoCParser.FunctionDefinitionContext ctx) 
    {
        String functionName;
        if (mapFuncAna.containsKey(functionName = ctx.functionName().getText()))
            return ;
        FunctionsAnalyser fa = new FunctionsAnalyser(functionName);
        mapFuncAna.put(functionName, fa);
        curFuncCtx = fa;
    }

    @Override
    public void enterParameterList(picoCParser.ParameterListContext ctx) 
    {
        curFuncCtx.setParameterContext(true);
        curFuncCtx.setFunctionContext(false);
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
            boolean ptr = parameterList.get(i).declarator().getChildCount() > 1;
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
    }
    
    @Override
    public void enterDirDecl(picoCParser.DirDeclContext ctx) 
    {
        if (curFuncCtx.isParameterContext()) {
            return;       
        }
        int locals = curFuncCtx.getSpaceForLocals();
        int sizeOfVar;
        /* Calculate space on stack for local variables  */
        if (curFuncCtx == null) {
            DataSegment.DeclareExtern(null);
            return ;
        }
        
        if (pointerInitializator.isEmpty())
            sizeOfVar = NasmTools.getSize(currentDeclaratorType);
        else 
            sizeOfVar = NasmTools.getSize(MemoryClassEnum.POINTER);
        /* Set displacement in current function context */
        curFuncCtx.setSpaceForLocals(locals + sizeOfVar);
        pointerInitializator.clear();
    }

    @Override
    public void enterSimplePtr(picoCParser.SimplePtrContext ctx) 
    {
        if (curFuncCtx.isParameterContext()) {
            return;       
        }
        MemoryClassEnum type;
        type = curFuncCtx.getCurrentDeclaratorType();
        NasmTools.insertPointerType(pointerInitializator, type);
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
