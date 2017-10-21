package antlr;

import tools.FunctionsAnalyser;
import constants.MemoryClassEnumeration;
import nasm.NasmTools;
import java.util.HashMap;
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
    /* List that contains informations about all functions
        that are beeing compiled */
    public static Map<String, FunctionsAnalyser> lisFuncAna;
    
    /* Curent function context.  */
    public static FunctionsAnalyser curFuncCtx = null;
    
    public static MemoryClassEnumeration currentDeclaratorType;
    
    public TranslationListener()
    {
        lisFuncAna = new HashMap<>();
    }
    
    @Override
    public void enterFunctionDefinition(picoCParser.FunctionDefinitionContext ctx) 
    {
        String functionName;
        if (lisFuncAna.containsKey(functionName = ctx.functionName().getText()))
            return ;
        FunctionsAnalyser fa = new FunctionsAnalyser(functionName);
        lisFuncAna.put(functionName, fa);
        curFuncCtx = fa;
    }

    @Override
    public void enterParameterList(picoCParser.ParameterListContext ctx) 
    {
        /* Get list of parameters */
        List<picoCParser.ParameterContext> parameterList;
        parameterList = ctx.parameter();
        /* Calculate displacement for parameters */
        int sizeOfParams, sizeOfVar, paramsNum, i;
        sizeOfParams = sizeOfVar = 0;
        paramsNum = parameterList.size();
        
        /* Iterate over list and calculate total size of parameters in bytes */
        for (i = 0; i < paramsNum; ++i) {
            /* Get type specifier's name and convert it to bytes */
            String paramText = parameterList.get(i).typeSpecifier().getText();
            MemoryClassEnumeration type = FunctionsAnalyser.getMemoryClass(paramText);
            sizeOfVar = NasmTools.getSize(type);
            /* Add it to overall size */
            sizeOfParams += sizeOfVar;
        }
        /* Set in function analyser */
        curFuncCtx.setNumberOfParameters(sizeOfParams);
        curFuncCtx.setSpaceForParams(sizeOfParams);
    }
    
    
    /* Get memoryClass to set current Declarator type for case of multiple
        declarations of same type. Example: int a, b, c = 10; */
    @Override
    public void enterDeclarationList(picoCParser.DeclarationListContext ctx) 
    {
        currentDeclaratorType = 
                FunctionsAnalyser.getMemoryClass(ctx.typeSpecifier().getText());
    }

    @Override
    public void enterDeclaration(picoCParser.DeclarationContext ctx) 
    {
        /* Calculate space on stack for local variables  */
        if (curFuncCtx == null) {
            DataSegment.DeclareExtern(null);
            return ;
        }
        int locals = curFuncCtx.getSpaceForLocals();
        int sizeOfVar = NasmTools.getSize(currentDeclaratorType);
        /* Set displacement in current function context */
        curFuncCtx.setSpaceForLocals(locals + sizeOfVar);
    }

    /* If selectionStatement is visited, it's depth is stored in list
        in LabelsMaker class. It is needed for calculating else if () statement 
        labels. */
    @Override
    public void enterSelectionStatement(picoCParser.SelectionStatementContext ctx) {
        LabelsMaker.insertDepth();
        LabelsMaker.increaseDepth();
    }
    /* Counter of depth in LabelsMaker class is reset to 0. */
    @Override
    public void exitSelectionStatement(picoCParser.SelectionStatementContext ctx) {
        LabelsMaker.resetSelectionDepthCounter();
    }
    
    
    
}
