grammar picoC;

compilationUnit 
    :   translationUnit? EOF  ;

translationUnit 
    :   externalDeclaration
    |   translationUnit externalDeclaration
    ;

externalDeclaration 
    :   functionDefinition
    |   declarationList
    |   expressionStatement
    |   ';'
    ;

declarationList 
    :   typeSpecifier declaration (',' declaration)*  ';'  ;

declaration 
    :   ID
    |   assignmentExpression
    ;

functionDefinition 
    :   typeSpecifier functionName '(' parameterList? ')' functionBody 
    ;

typeSpecifier 
    :   'int'   
    |   'void'
    ;

functionName 
    :   ID ;

parameterList 
    :   parameter (',' parameter)*  ;

parameter 
    :   typeSpecifier ID  ;

functionBody 
    :   compoundStatement  ;

statement 
    :   compoundStatement
    |   expressionStatement
    |   selectionStatement
    |   iterationStatement
    |   returnStat                         
    ;

compoundStatement
    :   '{' blockItemList? '}'
    ;

blockItemList
    :   blockItem
    |   blockItemList blockItem
    ;

blockItem
    :   declarationList
    |   statement
    ;

selectionStatement
    :   'if' '(' expression ')' statement ('else' statement)? ;

iterationStatement
    :   'for' '(' expression? ';' expression? ';' expression? ')' statement ;

returnStat 
    :   'return' expression?  ';'  ;


functionCall 
    :   functionName '(' argumentList? ')' ;

argumentList 
    :   argument (',' argument)*  ;

argument 
    : assignmentExpression 
    | STRING_LITERAL
    ;    

primaryExpression 
    :   ID                 #Id
    |   INT                #Int
    |   functionCall       #FuncCall
    |   '(' expression ')' #Parens
    ;

postfixExpression
    :   primaryExpression       #DropPostfix
    |   postfixExpression '++'  #PostInc
    |   postfixExpression '--'  #PostDec
    ;

unaryExpression 
    :   postfixExpression       #DropUnary
    |   '-'  unaryExpression    #Negation
    |   '+'  unaryExpression    #Plus
    |   '++' unaryExpression    #PreInc
    |   '--' unaryExpression    #PreDec
    ;

multiplicativeExpression 
    :   unaryExpression                                               #DropMulDivMod
    |   multiplicativeExpression op=('*'|'/'|'%') unaryExpression     #MulDivMod
    ;

additiveExpression 
    :   multiplicativeExpression                                  #DropAddSub
    |   additiveExpression op=('+'|'-') multiplicativeExpression  #AddSub
    ;

relationalExpression 
    :   additiveExpression					         #DropRelational	
    |   relationalExpression rel=('<'|'<='|'>='|'>') additiveExpression  #Relation
    ;    

equalityExpression
    :   relationalExpression                                       #DropEquality
    |   equalityExpression rel=('=='|'!=') relationalExpression    #Equality
    ;

logicalAndExpression
    :   equalityExpression                                  #DropLogicalAND
    |   logicalAndExpression '&&' equalityExpression        #LogicalAND
    ;

logicalOrExpression
    :   logicalAndExpression                                #DropLogicalOR
    |   logicalOrExpression '||' logicalAndExpression       #LogicalOR
    ;

assignmentExpression
    :   logicalOrExpression                     #DropAssign
    |   ID '=' assignmentExpression             #Assign
    ;

expression
    :   assignmentExpression
    |   expression ',' assignmentExpression
    ;      


expressionStatement 
    :   expression? ';'  ;

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
    :   '//' .*? '\r'? '\n' -> skip  // match anything after // until newline
    ;

MUL : '*' ;
DIV : '/' ;
MOD : '%' ;
ADD : '+' ;
SUB : '-' ;

EQUAL :          '==' ;
NOT_EQUAL :      '!=' ;
LESS :            '<' ;
LESS_EQUAL :     '<=' ;
GREATER :         '>' ;
GREATER_EQUAL :  '>=' ;

LOGICAL_AND :   '&&'  ;
LOGICAL_OR  :   '||'  ; 

/* Type specifiers */
fragment
VOIDTYPE : 'void' ;
fragment
INTTYPE  : 'int'  ;
