package com.apatton;

/* Class InfixToPostfix

 * 
 * Converts Infix expressions to Postfix expressions.
 * 
 * Infix notation: normal algebraic form, "in order".
 * 		Format: a+b*c	1+2*3
 * Postfix notation: altered form allowing for stack evaluation of expressions
 * 		Format: abc*+	123*+
 * 
 * 
 * InfixToPostfix algorithm:
 * 		Scan String infix from left to right.
 * 		Initialize an empty String postfix.
 * 		Initialize an empty Stack operators.
 * 		If the scanned character c is not an operator, add it to the string.
 * 		If the scanned character c is an operator and the stack is empty or contains a left parenthesis on top,
 * 		Push c onto the stack.
 * 		If c is a left parenthesis, push it onto the stack.
 * 		If c is a right parentheses, pop the stack and add operators to string until you reach the left parenthesis.
 * 		Discard the pair of parenthesis.
 * 		If c has higher precedence than topStack, push it onto the stack.
 * 		If c has equal precedence with topStack, use association.
 * 			If the association is left to right, pop the stack and add to string, then push c onto the stack.
 * 			If the association is right to left, push c onto the stack.
 * 		If c has lower precedence than topStack, pop the stack and add topOperator to string.
 * 			Test the c against the new topStack.
 * 		Pop and add all elements of the stack to the string.
 * 		(No parentheses should remain)
 * 		
 */

import java.util.*;


public class InfixToPostfix
{//97 to 122 are variables

   private boolean isOperator(char c)
   {
	   return ( c == '('  ||  c == ')'  ||  c == '^'  ||  c == '*'  ||  c == 'x'  ||  c == '/' ||  c == '%' || c=='+' || c=='-' );
   }
   
   private boolean isSpace(char c)
   {
	   return ( c == ' ' );
   }
   
   private boolean isEqualSign(char c)
   {
	   return ( c == '=' );
   }



   private boolean lowerPrecedence(char op1, char op2) {
      // Tell whether op1 has lower precedence than op2, where op1 is an
      // operator on the left and op2 is an operator on the right.
      
      switch (op1) {

         case '+':
         case '-':
            return !(op2=='+' || op2=='-') ;

         case '*':
         case 'x':
         case '/':
         case '%':
            return op2=='^' || op2=='(';

         case '^':
            return op2=='(';

         case '(': return true;

         default:  // (shouldn't happen)
            return false;
      }
 
   }



	public String convertToPostfix(String i)
   	{

     Stack<String> operatorStack = new Stack<String>();
     char c; // the first character of a token
   
     StringTokenizer infix = new StringTokenizer(i, "=()^*x/%+- ", true);
     StringBuffer postfix = new StringBuffer(i.length());  // result
 
     // Process the infix tokens.
     while (infix.hasMoreTokens())
     {     
    	 
    	 String token = infix.nextToken();      // get the next token and let c be
         c = token.charAt(0);         			// the first character of this token
   
         if ( (token.length() == 1) && isOperator(c) )
         {// if token is an operator
     
              while (!operatorStack.empty() && !lowerPrecedence(((String)operatorStack.peek()).charAt(0), c))
              // (Operator on the stack does not have lower precedence, so it goes before this one.)
                 postfix.append(" ").append((String)operatorStack.pop());

              if (c==')')
              {// Output the remaining operators in the quantity.
            	  String operator = (String)operatorStack.pop();
            	  
                  while (operator.charAt(0)!='(')
                  {
                	  postfix.append(" ").append(operator);
                	  operator = (String)operatorStack.pop();  
                  }
              }
              else
                 operatorStack.push(token);// Push this operator onto the stack.
   
           }
           else if ( (token.length() == 1) && isSpace(c) )
           {// else if token was a space ignore it
        	   ;
           }
           else if ( (token.length() == 1) && (infix.countTokens()==0) && isEqualSign(c) )
           {// else if token was an equal sign at the end ignore it
        	   ;
           }
           else
           {// (token is an operand or variable)
             if(!(postfix.length()==0))  //don't add a space before the first token
            	 postfix.append(" ").append(token);  // output the operand
             else
            	 postfix.append(token);
            	 
           }
         
     }
 
     // Output the remaining operators on the stack.
     while (!operatorStack.empty())
    	 postfix.append(" ").append((String)operatorStack.pop());
     
     // Return the result.
     System.out.println(postfix.toString());
     return postfix.toString();


   }//end convertToPostfix


}//end class InfixToPostfix


