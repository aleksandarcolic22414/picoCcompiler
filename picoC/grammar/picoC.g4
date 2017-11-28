grammar picoC;

compilationUnit 
    :   translationUnit? EOF  ;

translationUnit 
    :   externalDeclaration
    |   translationUnit externalDeclaration
    ;

externalDeclaration 
    :   functionDefinition
    |   declaration
    |   ';'
    ;

functionDefinition 
    :   typeSpecifier declarator '(' parameterList? ')' functionBody 
    ;

primaryExpression 
    :   ID                 #Id
    |   constant           #Const
    |   STRING_LITERAL     #Str
    |   '(' expression ')' #Parens
    ;

postfixExpression
    :   primaryExpression                        #DropPostfix
    |   postfixExpression '[' expression ']'     #Subscript
    |   postfixExpression '(' argumentList? ')'  #FuncCall
    |   postfixExpression '++'                   #PostInc
    |   postfixExpression '--'                   #PostDec
    ;

unaryExpression 
    :   postfixExpression       #DropUnary
    |   '++' unaryExpression    #PreInc
    |   '--' unaryExpression    #PreDec
    |   '*'  castExpression     #Deref
    |   '!'  castExpression     #Negation
    |   '&'  castExpression     #Address
    |   '~'  castExpression     #Complement
    |   '-'  castExpression     #Minus
    |   '+'  castExpression     #Plus
    ;

castExpression
    :   unaryExpression                                   #DropCast
    |   '(' typeSpecifier pointer? ')' castExpression     #Cast
    ;

multiplicativeExpression 
    :   castExpression                                             #DropMulDivMod
    |   multiplicativeExpression op=('*'|'/'|'%') unaryExpression  #MulDivMod
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

andExpression
    :   equalityExpression                                 #DropAnd
    |   andExpression '&' equalityExpression               #And
    ;

exclusiveOrExpression
    :   andExpression                                      #DropExclusiveOr  
    |   exclusiveOrExpression '^' andExpression            #ExclusiveOr  
    ;

inclusiveOrExpression
    :   exclusiveOrExpression                              #DropInclusiveOr
    |   inclusiveOrExpression '|' exclusiveOrExpression    #InclusiveOr
    ;

logicalAndExpression
    :   inclusiveOrExpression                               #DropLogicalAND
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

typeSpecifier 
    : type = ( 'int'
             | 'char'
             | 'void' )
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
    :   pointer? directDeclarator      
    ;

pointer
    :   '*'             #SimplePtr
    |   pointer '*'     #MultiplePrt
    ;

directDeclarator
    :   ID                                  #DirDecl
    |   directDeclarator '[' constant? ']'  #ArrayDecl
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
    :   'if' '(' assignmentExpression ')' statement ('else' statement)? ;

iterationStatement
    :   'for' '(' forInit? ';' forCheck? ';' forInc? ')' statement  #ForLoop
    |   'while' '(' whileCheck? ')' statement                       #WhileLoop
    |   'do' statement 'while' '(' whileCheck? ')'  ';'             #DoWhileLoop
    ;

forInit
    :   expression 
    ;

forCheck
    :   assignmentExpression  
    ;

forInc
    :   expression 
    ;

whileCheck
    :   assignmentExpression
    ;

argumentList 
    :   argument (',' argument)*  ;

argument 
    :   assignmentExpression 
    ;    

constant
    :   INT         #Int
    |   CHAR        #Char
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

ID              : [a-zA-Z_] ( [a-zA-Z]+ | [0-9]+ )* ; 
INT             : [0-9]+ ;
CHAR            : '\'' . '\'' | '\'\\' . '\'' ;
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

WHITE_SPACE  : [ \t\r\n]+ -> skip
    ;
