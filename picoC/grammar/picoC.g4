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
        |   assignmentExpression
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

parameterList 
        :   parameter (',' parameter)*  ;

parameter 
        :   typeSpecifier ID  ;

functionBody 
        :   '{' statements '}'  ;

statements 
        :   (statement)*  ;

statement 
        :   declarationList   
        |   expressionStatement
        |   returnStat                         
        ;

returnStat 
        :   'return' expression?  ';'  ;


functionCall 
        :   functionName '(' argumentList? ')' ;

functionName 
        :   ID ;

argumentList 
        :   argument (',' argument)*  ;

argument : assignmentExpression 
         | STRING_LITERAL
         ;    

primaryExpression 
        :   ID                 #Id
        |   INT                #Int
        |   functionCall       #FuncCall
        |   '(' expression ')' #Parens
        ;

unaryExpression 
        :   primaryExpression      #DropUnary
        |   '-' primaryExpression  #Negation
        ;

multiplicativeExpression 
        :   unaryExpression                                           #DropMulDiv
        |   multiplicativeExpression op=('*'|'/') unaryExpression     #MulDiv
        ;

additiveExpression 
        :   multiplicativeExpression                                  #DropAddSub
        |   additiveExpression op=('+'|'-') multiplicativeExpression  #AddSub
        ;

relationalExpression 
        :   additiveExpression					             #DropRelational	
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
        : expression? ';'  ;

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
ADD : '+' ;
SUB : '-' ;

EQUAL :          '==' ;
NOT_EQUAL :      '!=' ;
LESS :            '<' ;
LESS_EQUAL :     '<=' ;
GREATER :         '>' ;
GREATER_EQUAL :  '>=' ;

/* Type specifiers */
fragment
VOIDTYPE : 'void' ;
fragment
INTTYPE  : 'int'  ;
