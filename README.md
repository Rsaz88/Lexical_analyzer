# Lexical_analyzer
This is a simple lexical analyzer borrowed from Concepts of Programming Languages 11th Edition and edited to recognize the following list of reserved words and return their respective token codes:

for (FOR_CODE, 30),
if (IF_CODE, 31), 
else (ELSE_CODE, 32),
while (WHILE_CODE, 33), 
do (DO_CODE, 34), 
int (INT_CODE, 35),
float (FLOAT_CODE, 36),
switch (SWITCH_CODE, 37)implemented

## The implementation is split into 2 parts as follows :

## Part1 : 
Modify the lexical analyzer from the book (section 4.2) to recognize additional reserved words and return their respective token codes

## Part2 : 
Implement the resulting lexical analyzer from part#1 in Java

### How can I run this code ?
This code takes a text file as an input and outputs the anlyzed expressions : 
### simple example input :
do good for people while they are still alive 

### example output : 
 Next token is 12 Next lexeme is do
 Next token is 11 Next lexeme is good
 Next token is 12 Next lexeme is for
 Next token is 11 Next lexeme is people
 Next token is 12 Next lexeme is while
 Next token is 11 Next lexeme is they
 Next token is 11 Next lexeme is are
 Next token is 11 Next lexeme is still
 Next token is 11 Next lexeme is alive

