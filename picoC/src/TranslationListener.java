
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleksandar
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
    
    static {
        
    }
    
    public TranslationListener()
    {
        lisFuncAna = new HashMap<>();
    }
    
    /* Da ne zaboravim: Napravi prvi obilazak pomocu listenera 
        i odredi potrebnu memoriju na stack-u za lokalne varijable i
        parametre funkcije. Onda kad budes ulazio u visitFunctionDefinition i 
        odstampas functionSetup, odma oduzmi rsp za izracunati pomeraj, da ne
        bi svaki put kad se deklarisu varijable oduzimao po 4 bajta.
        Jos nesto:
        Prvo idu lokalne varijable na stack, pa onda kopije argumenata
        prosledjenih preko registara u redosledu->
        parametri:    func(arg1, arg2, arg3, arg4, arg5, arg6,  arg7,  arg8,  arg9)   
        registri :         rdi   rsi   rdx    rcx   r8    r9 |3.push<-2.push<-1.push
        registri :intVars  edi   esi   edx    ecx   r8d   r9d
    
        ako ima preko 6 parametara, onda se oni guraju u obrnutom redosledu 
        na stack kao kod 32-bitnih asemblera.
        
    */

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
        int sizeOfParams, sizeOfVar, paramsNum, i, currStackDisp;
        sizeOfParams = sizeOfVar = 0;
        paramsNum = parameterList.size();
        currStackDisp = curFuncCtx.getStackVariablesDisplacement();
        System.out.println
            ("Number of params in " + 
                    curFuncCtx.getFunctionName() +
                        ": " + paramsNum);
        /* Iterate over list and calculate total size of parameters in bytes */
        for (i = 0; i < paramsNum; ++i) {
            /* Get type specifier's name and convert it to bytes */
            String paramText = parameterList.get(i).typeSpecifier().getText();
            MemoryClassEnumeration type = FunctionsAnalyser.getMemoryClass(paramText);
            sizeOfVar = NasmTools.getSize(type);
            sizeOfParams += sizeOfVar;
        }
        /* Set in function analyser */
        curFuncCtx.setNumberOfParameters(sizeOfParams);
        curFuncCtx.setStackVariablesDisplacement(currStackDisp + sizeOfParams);
        
        System.out.println("Total space for local fariables in function " + 
                curFuncCtx.getFunctionName() + ": " + (currStackDisp + sizeOfParams));
    }
    
    
    
    @Override
    public void enterDeclarationList(picoCParser.DeclarationListContext ctx) 
    {
        currentDeclaratorType = 
                FunctionsAnalyser.getMemoryClass(ctx.typeSpecifier().getText());
    }

    @Override
    public void enterDeclaration(picoCParser.DeclarationContext ctx) 
    {
        /* Calculate space on stack for local variables */
        int stackDisplacement = curFuncCtx.getStackVariablesDisplacement();
        int sizeOfVar = NasmTools.getSize(currentDeclaratorType);
        /* Set displacement in current function context */
        curFuncCtx.setStackVariablesDisplacement(stackDisplacement + sizeOfVar);
        System.out.println
            ("Function " + curFuncCtx.getFunctionName()
                + " space for locals : " + (stackDisplacement + sizeOfVar));
    }
    
}
