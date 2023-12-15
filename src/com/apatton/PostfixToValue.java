package com.apatton;

/* Class PostfixToValue

 * 
 * Converts Postfix expressions into values.
 * 
 * Postfix stack evaluation algorithm:
 * 		Scan Postfix string from left to right.
 * 		Initialize an empty stack for operands.
 * 		If the scanned character is an operand, add it to the stack.
 * 		If the scanned character is an operator, store topStack as temp.
 * 		(There will be at least two operands on the stack at this point.  Operators never go on the stack.)
 * 			Pop the stack.
 * 			Evaluate topStack(operator)temp and store as lastVal.
 * 			Pop the stack.
 * 			Push lastVal onto the stack.
 * 		Repeat until all characters have been scanned.
 * 		After all characters have been scanned, there will be one element in the stack (the answer).
 * 		Return topStack.
 */

import java.math.BigDecimal;
import java.util.*;


public class PostfixToValue {
	
	private Deque<Double> args;
	public Functions funcs = new Functions();
	private double ans = 0;
	 
    public PostfixToValue() {
        args = new LinkedList<>();
    }
 
    public BigDecimal convertToValue(String postfix) {
        args.clear();
        try (Scanner scanner = new Scanner(postfix)) {
            while (scanner.hasNext()) {
                String token = scanner.next();
                processToken(token);
            }
        }
 
        if (args.size() == 1) {
        	ans = args.pop();
        	return BigDecimal.valueOf(ans).setScale(8, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
        } else {
        	String error = "Invalid number of operators.";
            throw new IllegalArgumentException(error);
        }
    }
	
	
	private void processToken(String token)
	{
        switch (token)
        {
            case "+":
                addArgs();
                break;
            case "-":
                subArgs();
                break;
            case "*":
                mulArgs();
                break;
            case "x":
                mulArgs();
                break;
            case "/":
                divArgs();
                break;
            case "^":
                powArgs();
                break;
            case "%":
                modArgs();
                break;
            case "e":
                eArgs();
                break;
            case "pi":
                piArgs();
                break;
            case "ans":
        		ansArgs();
        		break;
            
                
            default:
                try {
                	if(!varArgs(token))
                	{
                		double arg = Double.parseDouble(token);
                		args.push(arg);
                	}
                } catch (NumberFormatException e) {
                	String error = "Invalid number: "+token;
                    throw new IllegalArgumentException(error, e);
                }
        }//end switch
        
    }
	
	
	private void addArgs() {
        checkArgumentsSize();
        double arg2 = args.pop();
        double arg1 = args.pop();
        args.push(arg1 + arg2);
    }
 
    private void subArgs() {
        checkArgumentsSize();
        double arg2 = args.pop();
        double arg1 = args.pop();
        args.push(arg1 - arg2);
    }
 
    private void mulArgs() {
        checkArgumentsSize();
        double arg2 = args.pop();
        double arg1 = args.pop();
        args.push(arg1 * arg2);
    }
 
    private void divArgs() {
        checkArgumentsSize();
        double arg2 = args.pop();
        double arg1 = args.pop();
        args.push(arg1 / arg2);
    }
    
    private void powArgs() {
        checkArgumentsSize();
        double arg2 = args.pop();
        double arg1 = args.pop();
        args.push(Math.pow(arg1, arg2));
    }
    
    private void modArgs() {
        checkArgumentsSize();
        double arg2 = args.pop();
        double arg1 = args.pop();
        args.push(arg1%arg2);
    }
    
    private void eArgs() {
        args.push(Math.E);
    }
    
    private void piArgs() {
        args.push(Math.PI);
    }
    
    private void ansArgs() {
        args.push(ans);
    }
    
    private boolean varArgs(String token)
    {
    	for(Variable var : funcs.getVarList())
		{
			if( token.equals(var.getName()) )
			{
				args.push(var.getValue());
				return true;
			}
		}
        return false;
    }
    
    
    
 
    private void checkArgumentsSize() {
        if (args.size() < 2) {
        	String error = "Not enough parameters for operation.";
            throw new IllegalArgumentException(error);
        }
    }

}
