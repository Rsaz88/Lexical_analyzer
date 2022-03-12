/* The original code: (From Concepts of Programming Languages 11th Edition)*/

/* The original code is edited to recognize to recognize the following list of 
reserved words and return their respective token codes:

for (FOR_CODE, 30),
if (IF_CODE, 31), 
else (ELSE_CODE, 32),
while (WHILE_CODE, 33), 
do (DO_CODE, 34), 
int (INT_CODE, 35),
float (FLOAT_CODE, 36),
switch (SWITCH_CODE, 37)
*/




/* front.c - a lexical analyzer system for simple

arithmetic expressions */

#include <stdio.h>

#include<ctype.h>

#include <string.h>

/* Global declarations */

/* Variables */

int charClass;

char lexeme [100];

char nextChar;

int lexLen;

int token;

int nextToken;

FILE *in_fp, *fopen();

/* New Variables declarations */

char strings[8][7] = {"for", "if", "else", "while", "do", "int", "float", "switch"};

int i ; 

/* Function declarations */

void addChar();

void getChar();

void getNonBlank();

int lex();

/* Character classes */

#define LETTER 0

#define DIGIT 1

#define UNKNOWN 99

/* Token codes */

#define INT_LIT 10

#define IDENT 11

#define KEYWORD 12

#define ASSIGN_OP 20

#define ADD_OP 21

#define SUB_OP 22

#define MULT_OP 23

#define DIV_OP 24

#define LEFT_PAREN 25

#define RIGHT_PAREN 26

/******************************************************/

/* modification step 1 :ading new Token codes  */

#define FOR_CODE 30

#define IF_CODE 31

#define ELSE_CODE 32

#define WHILE_CODE 33 

#define DO_CODE 34 

#define INT_CODE 35 

#define FLOAT_CODE 36

#define SWITCH_CODE 37

/******************************************************/



/* main driver */

main() {

/* Open the input data file and process its contents */

if ((in_fp = fopen("front.in", "r")) == NULL)

printf("ERROR - cannot open front.in ");

else {

getChar();

do {

lex();

} while (nextToken != EOF);

}

}

/*****************************************************/

/* lookup - a function to lookup operators and parentheses

and return the token */

int lookup(char ch) {

switch (ch) {

case '(':

addChar();

nextToken = LEFT_PAREN;

break;

case ')':

addChar();

nextToken = RIGHT_PAREN;

break;

case '+':

addChar();

nextToken = ADD_OP;

break;

case '-':

addChar();

nextToken = SUB_OP;

break;

case '*':

addChar();

nextToken = MULT_OP;

break;

case '/':

addChar();

nextToken = DIV_OP;

break;

    default:
      addChar();
      nextToken = EOF;
      break;
  }
  return  nextToken;
}
/*****************************************************/
/* addChar - a function to add nextChar to lexeme */
void  addChar() {
  if  (lexLen <= 98) {
    lexeme[lexLen++] = nextChar;
    lexeme[lexLen] = 0;
  }
  else
    printf("Error - lexeme is too long ");
}
/*****************************************************/
/* getChar - a function to get the next character of 
             input and determine its character class */
 void getChar() {
   if  ((nextChar = getc(in_fp)) != EOF) {
     if  (isalpha(nextChar))
      charClass = LETTER;
     else if  (isdigit(nextChar))
           charClass = DIGIT;
          else  charClass = UNKNOWN;
   }
   else
     charClass = EOF;
}
/*****************************************************/
/* getNonBlank - a function to call getChar until it
                 returns a non-whitespace character */
void getNonBlank() {
  while  (isspace(nextChar))
    getChar();
}

/****************************************************/
/* lex - a simple lexical analyzer for arithmetic 
         expressions */
int  lex() {
  lexLen = 0;
  getNonBlank();
  switch  (charClass) {
/* Parse identifiers */
    case  LETTER:
      addChar();
      getChar();
      while  (charClass == LETTER || charClass == DIGIT) {
        addChar();
        getChar();
      }
      /****************************************************/
     /* Checking for list of reserved words */
     
     /* function strcmp takes two strings as input 
        and outpts 0 if they are similar"*/
        
      if (strcmp(lexeme,strings[0]) == 0){
        nextToken = KEYWORD; 
      }
      else if (strcmp(lexeme,strings[1]) == 0){
           nextToken = KEYWORD; 
      }
      else if (strcmp(lexeme,strings[2]) == 0){
           nextToken = KEYWORD; 
      }
      else if (strcmp(lexeme,strings[3]) == 0){
           nextToken = KEYWORD; 
      }
      else if (strcmp(lexeme,strings[4]) == 0){
           nextToken = KEYWORD; 
      }
      else if (strcmp(lexeme,strings[5]) == 0){
           nextToken = KEYWORD; 
      }
      else if (strcmp(lexeme,strings[6]) == 0){
           nextToken = KEYWORD; 
      }
      else if (strcmp(lexeme,strings[7]) == 0){
           nextToken = KEYWORD; 
      }
      else{
         nextToken = IDENT; 
      }
      
      /* for loop might be better for more compact code*/
      /*
      for (i = 0; i < 9; i++) {
          if (strcmp(lexeme, strings[i]) == 0) {
          nextToken = KEYWORD;
          }
          else{
          nextToken = IDENT;
         }
        } 
        */
        
    break;
/* Parse integer literals */
    case  DIGIT:
      addChar();
      getChar();
      while  (charClass == DIGIT) {
       addChar();
       getChar();
   }
   nextToken = INT_LIT;
   break;
/* Parentheses and operators */
    case  UNKNOWN:
      lookup(nextChar);
      getChar();
      break;
/* EOF */
    case  EOF:
      nextToken = EOF;
      lexeme[0] = 'E';
      lexeme[1] = 'O';
      lexeme[2] = 'F';
      lexeme[3] = 0;
      break;
 } /* End of switch */
 printf("Next token is: %d, Next lexeme is %s\n", 
         nextToken, lexeme);
         
  return  nextToken;
} /* End of function lex */
