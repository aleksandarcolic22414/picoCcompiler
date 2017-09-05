grammar picoC;

compilationUnit:    main;

main:   'int' 'main' '(' parameterList? ')' functionBody
    ;

parameterList:  parameter (',' parameter)*
            ;
parameter:   TYPE ID
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

declaration:  TYPE ID ;

initialization: TYPE ID '=' INT
              | ID '=' INT ;

returnStat: 'return' INT
          | 'return' ID ;


functionCall: functionName '(' argumentList? ')' ;

functionName: ID ;

argumentList: argument (',' argument)* ;

argument: ID    
        | STRING_LITERAL
        | INT
        ;

expression: simpleExpression;

simpleExpression:  simpleExpression '*' simpleExpression    #Mul
                |  simpleExpression '/' simpleExpression    #Div
                |  simpleExpression '+' simpleExpression    #Add
                |  simpleExpression '-' simpleExpression    #Sub
                |  ID                                       #Id
                |  INT                                      #Int
                |  '(' simpleExpression ')'                 #Parens          
                ;

TYPE    : 'int'  ;
ID      : [a-zA-Z]+ ; 
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