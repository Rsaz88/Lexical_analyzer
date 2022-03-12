// The original code: (From Concepts of Programming Languages 11th Edition)

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

Note : The original version was written in C language then converted here to Java while keeping same variable naming for better consistency 
*/


import java.io.IOException;
import java.io.File;  
import java.io.FileReader;

public class lexicalAnalyzer
{  
    //declare variables globally
    public static int charClass;
	public static char[] lexeme = new char[100];  
	public static char nextChar;
	public static int lexLen;
	public static int token;
	public static int nextToken;
	public static File infp;
	public static FileReader fReader;
	
	//defining a list of reserved words
	
	public static String[] resWords =  {"for", "if", "else", "while", "do", "int", "float", "switch"};
	
	
	
	

    // Character classes 
    public static final int LETTER = 0; 
    public static final int DIGIT =1; 
    public static final int UNKNOWN =99;
    public static final int EOF = -1; // EOF stands for end of file
    
    // Token codes 
    public static final int INT_LIT = 10;
    public static final int IDENT =11;
    public static final int KEYWORD =12; 
    public static final int ASSIGN_OP =20 ;
    public static final int ADD_OP =21;
	public static final int SUB_OP =22 ;
	public static final int MULT_OP =23 ;
	public static final int DIV_OP =24 ;
	public static final int LEFT_PAREN =25 ;
	public static final int  RIGHT_PAREN =26;
	
	
	// lookup - a function to lookup operators and parentheses and return the token 
 static int lookup(char ch) throws IOException{
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
	    return nextToken;
	}

 
	// addChar - a function to add nextChar to lexeme 
 static void addChar() throws IOException{
    if (lexLen <= 98) {
        lexeme[lexLen++] = nextChar;
        lexeme[lexLen] = 0;
    }
    else
       System.out.println("Error - lexeme is too long \n");
  }

// getChar - a function to get the next character of input and determine its character class */ 
 static void getChar()  throws IOException{ 
	    
	    int c; //variable to store the next char in the file
	    if((c = fReader.read()) != EOF)
	     {
          nextChar = (char) c;
          if(Character.isLetter(nextChar))
             charClass = LETTER;
          else if (Character.isDigit(nextChar))
              	charClass = DIGIT;
               else charClass = UNKNOWN;
        }
       else
          charClass = EOF;         
  }

// getNonBlank - a function to call getChar until it returns a non-whitespace character 
 static void getNonBlank() throws IOException
  {
    while (Character.isWhitespace(nextChar))
    getChar();
  }  


 static String ConcatChars(){   // this method to print the character in lexeme as string
        String LexmeString = "";
        for(int i = 0; i < lexeme.length; i++){
            if(lexeme[i] == '\u0000')
                break;
else
            LexmeString= LexmeString+lexeme[i];
        }
        return LexmeString;
    }

//lex - a simple lexical analyzer for arithmetic expressions 

 static int lex() throws IOException
{
	lexLen = 0;
    getNonBlank();
    switch (charClass) 
    {

      // Parse identifiers 
        case LETTER:
        addChar();
        getChar();
        while (charClass == LETTER || charClass == DIGIT) 
        {
            addChar();
            getChar();
        }
        
     
        
        if(ConcatChars().equals(resWords[0])){
            nextToken = KEYWORD; 
          }
        else if (ConcatChars().equals(resWords[1])){
        	nextToken = KEYWORD;
        }
        
        else if(ConcatChars().equals(resWords[2])) {
            nextToken = KEYWORD; 
          }
        else if(ConcatChars().equals(resWords[3])) {
            nextToken = KEYWORD; 
          }
        else if(ConcatChars().equals(resWords[4])) {
            nextToken = KEYWORD; 
          }
        else if(ConcatChars().equals(resWords[5])) {
            nextToken = KEYWORD; 
          }
        else if(ConcatChars().equals(resWords[6])) {
            nextToken = KEYWORD; 
          }
        else if(ConcatChars().equals(resWords[7])) {
            nextToken = KEYWORD; 
          }
        else {
            nextToken = IDENT; 
        }
        
        /***
        
        for (int i = 0; i < strArray.length; i++) {
        	if(ConcatChars().equals(strArray[i])){
                nextToken = KEYWORD;                
                }
            else{
                nextToken = IDENT;
               }	
         }
         ***/
        
    break;
    //Parse integer literals 
    case DIGIT:
    addChar();
    getChar();
    while (charClass == DIGIT) 
    {
        addChar();
        getChar();
    }
    nextToken = INT_LIT;
    break;
    // Parentheses and operators 
    case UNKNOWN:
    lookup(nextChar);
    getChar();
    break;
   //  EOF 
    case EOF:
    nextToken = EOF;
    lexeme[0] = 'E';
    lexeme[1] = 'O';
    lexeme[2] = 'F';
    lexeme[3] = 0;
    break;
    }  //End of switch 
    System.out.printf("\n Next token is " 
    + nextToken);
    System.out.printf(" Next lexeme is " + ConcatChars() ); // use ConcatChars to print lexeme as a string
             
    return nextToken;
} 

 public static void main(String[] args) throws IOException{  // main method 
	    
	   
		 infp = new File("front.txt");
	     fReader = new FileReader(infp);
	     if (infp.length() == 0) 
		   System.out.println(" File is empty or wrong file is used");
		 else {
                getChar();
                do {
                    lex();
                   } while (nextToken != EOF); ;  
               } 

	  }
}
