grammar picoC;

primaryExpression 
    :   ID                 #Id
    |   INT                #Int
    |   STRING_LITERAL     #Str
    |   '(' expression ')' #Parens
    ;

postfixExpression
    :   primaryExpression                        #DropPostfix
    |   postfixExpression '(' argumentList? ')'  #FuncCall
    |   postfixExpression '++'                   #PostInc
    |   postfixExpression '--'                   #PostDec
    ;

unaryExpression 
    :   postfixExpression       #DropUnary
    |   '++' unaryExpression    #PreInc
    |   '--' unaryExpression    #PreDec
    |   '*'  unaryExpression    #Deref
    |   '!'  unaryExpression    #Negation
    |   '&'  unaryExpression    #Address
    |   '-'  unaryExpression    #Minus
    |   '+'  unaryExpression    #Plus
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

conditionalExpression
    :   logicalOrExpression                                           #DropConditional
    |   logicalOrExpression '?' expression ':' conditionalExpression  #Conditional
    ;

assignmentExpression
    :   conditionalExpression                                       #DropAssign
    |   unaryExpression  assignmentOperator assignmentExpression    #Assign
    ;

assignmentOperator
    :   op=('=' | '+=' | '-=' | '*=' | '/=' | '%=')
    ;

expression
    :   assignmentExpression
    |   expression ',' assignmentExpression
    ;      

declaration
    :   typeSpecifier initDeclarationList  ';'
    ;

initDeclarationList
    :   initDeclarator
    |   initDeclarationList ',' initDeclarator
    ;

initDeclarator
    :   declarator                              #Decl
    |   declarator '=' assignmentExpression     #DeclWithInit
    ;

declarator
    :   pointer declarator      #PtrDecl
    |   ID                      #DirDecl
    ;

pointer
    :   '*'             #SimplePtr
    |   pointer '*'     #MultiplePrt
    ;


typeSpecifier 
    : type=('int'
           | 'char'
           | 'void')
    ;

parameterList 
    :   parameter (',' parameter)*  ;

parameter 
    :   typeSpecifier declarator  ;

functionBody 
    :   compoundStatement  ;

statement 
    :   compoundStatement
    |   expressionStatement
    |   selectionStatement
    |   iterationStatement
    |   jumpStatement                         
    ;

jumpStatement
    :   'return' expression?  ';'      #Return
    |   'break'               ';'      #Break
    |   'continue'            ';'      #Continue
    ;

compoundStatement
    :   '{' blockItemList? '}'
    ;

blockItemList
    :   blockItem
    |   blockItemList blockItem
    ;

blockItem
    :   declaration
    |   statement
    ;

expressionStatement 
    :   expression? ';'  ;

selectionStatement
    :   'if' '(' expression ')' statement ('else' statement)? ;

iterationStatement
    :   'for' '(' forInit? ';' forCheck? ';' forInc? ')' statement ;

forInit
    :   expression 
    ;

forCheck
    :   expression 
    ;

forInc
    :   expression 
    ;

argumentList 
    :   argument (',' argument)*  ;

argument 
    :   assignmentExpression 
    ;    


compilationUnit 
    :   translationUnit? EOF  ;

translationUnit 
    :   externalDeclaration
    |   translationUnit externalDeclaration
    ;

externalDeclaration 
    :   functionDefinition
    |   expressionStatement
    |   declaration
    |   ';'
    ;

functionDefinition 
    :   typeSpecifier functionName '(' parameterList? ')' functionBody 
    ;

functionName 
    :   ID 
    ;

/* Type specifiers */
VOIDTYPE : 'void' ;
INTTYPE  : 'int'  ;
CHARTYPE : 'char' ;

ASSIGN     : '=' ;
ASSIGN_ADD : '+=' ;
ASSIGN_SUB : '-=' ;
ASSIGN_MUL : '*=' ;
ASSIGN_DIV : '/=' ;
ASSIGN_MOD : '%=' ;

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


