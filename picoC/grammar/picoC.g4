grammar picoC;

compilationUnit : translationUnit? EOF
                ;

translationUnit : externalDeclaration
                | translationUnit externalDeclaration
                ;

externalDeclaration : functionDefinition
                    | declarationList ';'
                    | assignment ';'
                    | ';'
                    ;

declarationList : typeSpecifier declaration (',' declaration)*;

declaration : ID
	    | assignment
	    ;

functionDefinition : typeSpecifier functionName '(' parameterList? ')' functionBody 
                   ;

typeSpecifier : 'int'   
              | 'void'
              ;

parameterList :  parameter (',' parameter)*
              ;

parameter :  typeSpecifier ID
          ;

functionBody : '{' statements '}'
             ;

statements : (statement ';')* 
           ;

statement :  declarationList        
          |  functionCall       
          |  returnStat         
          |  expression         
          |  assignment         
          ;

returnStat : 'return' expression? 
           ;


functionCall : functionName '(' argumentList? ')' ;

functionName : ID ;

argumentList : argument (',' argument)* ;

/*
argument : ID    
         | STRING_LITERAL
         | INT
         ;
*/

argument : expression
         | STRING_LITERAL
         ;    

expression : simpleExpression;

simpleExpression :  simpleExpression op=('*'|'/') simpleExpression    #MulDiv
                 |  simpleExpression op=('+'|'-') simpleExpression    #AddSub
                 |  ID                                                #Id
                 |  INT                                               #Int
                 |  '(' simpleExpression ')'                          #Parens              
                 |  assignment                                        #Assign  
                 ;

assignment : ID '=' expression ;

ID      : [a-zA-Z_] ( [a-zA-Z]+ | [0-9]+ )* ; 
INT     : [0-9]+ ;
WS      : [ \t\r\n]+ -> skip;

STRING_LITERAL  : '"' (ESC|.)*? '"' ;

fragment
ESC     : '\\"' | '\\\\' ; // 2-char sequences \" and \\

/* COMMENT used to be -> channel(HIDDEN) but it is skiped for now */
MULTY_LINE_COMMENT
    :   '/*' .*? '*/'       -> skip // match anything between /* and */
    ;

SINGLE_LINE_COMMENT
    :   '//' .*? '\r'? '\n' ->skip ; // match anything after // until newline

MUL : '*' ;
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;

/* Type specifiers */

fragment
VOIDTYPE : 'void' ;
fragment
INTTYPE  : 'int'  ;
