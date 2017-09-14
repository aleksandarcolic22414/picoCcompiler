grammar picoC;

/* TODO: main would go into standard function declarations */
compilationUnit : translationUnit? EOF
                ;

translationUnit : externalDeclaration
                | translationUnit externalDeclaration
                ;

externalDeclaration : functionDefinition
                    | declaration
                    | ';'
                    ;

functionDefinition : MEMORY_CLASS functionName '(' parameterList? ')' functionBody 
                   ;

parameterList:  parameter (',' parameter)*
             ;

parameter:  MEMORY_CLASS ID
         ;

functionBody: '{' statements '}'
            ;

statements: (statement ';')* 
          ;

statement:  declaration 
         |  initialization
         |  functionCall
         |  returnStat
         |  expression
         ;

declaration:  MEMORY_CLASS ID ;

initialization: MEMORY_CLASS ID '=' INT
              | ID '=' INT ;

returnStat: 'return' expression 
          ;


functionCall: functionName '(' argumentList? ')' ;

functionName: ID ;

argumentList: argument (',' argument)* ;

argument: ID    
        | STRING_LITERAL
        | INT
        ;

expression: simpleExpression;

simpleExpression:  simpleExpression op=('*'|'/') simpleExpression    #MulDiv
                |  simpleExpression op=('+'|'-') simpleExpression    #AddSub
                |  ID                                                #Id
                |  INT                                               #Int
                |  '(' simpleExpression ')'                          #Parens              
                ;

MEMORY_CLASS : 'int'  
             | 'void'
             ;
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