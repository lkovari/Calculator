/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.expressiontreeparser
 * File: InfixToPostfix.java
 * Created: Marc 02, 2005 11:25:38 AM
 * Author: lkovari 
 * 
 * Description:
 *  Convert an infix arithmetic expression to an postfix format 
 *
 * prefix operators  - ! 
 * multiplicative  * / % 
 * additive + - 
 * relational  < > >= <= 
 * equality == != 
 * logical and  && 
 * logical or  || 
 * conditional  ? ... : ...  
 */
package com.lkovari.desktop.app.calc.expressiontreeparser;

import java.util.Stack;
import com.lkovari.desktop.app.calc.expressiontreeparser.utils.ExpressionParserUtils;

/**
 * InfixToPostfix
 * @author lkovari
 * Description:
 *  Convert an infix arithmetic expression to an postfix format
 */
public class InfixToPostfix extends ExpressionParserUtils
{
    private Stack infixStack;
    
    public InfixToPostfix(final Stack infix) {
        this.infixStack = null;
        this.infixStack = infix;
    }
    
    /**
     * 
     * Method: precedenceCheck 
     * @param token1 1st operator
     * @param token2 2nd operator
     * @param lessequel true if check the 1st operator is less or equel then 2nd operator
     * @return true if the condition is true
     */    
    private boolean precedenceCheck(final char token1, final char token2, final boolean lessequel) {
        boolean res = false;
        int tokenIndex1 = -1;
        int tokenIndex2 = -1;
        final OperatorKind operatorKind1Token = OperatorKind.getOperatorKind(token1);
        final OperatorKind operatorKind2Token = OperatorKind.getOperatorKind(token2);
        tokenIndex1 = operatorKind1Token.getPrecedence();
        tokenIndex2 = operatorKind2Token.getPrecedence();
        if (lessequel) {
            res = (tokenIndex1 <= tokenIndex2);
        }
        else {
            res = (tokenIndex1 > tokenIndex2);
        }
        return res;
    }
    
	/**
	 * Method: convertToPostfix 
	 * @param postfixStack the result of conversion
	 * @throws Exception 
	 */    
    public void convertToPostfix(final Stack<String> postfixStack) throws Exception {
        final Stack<String> tempStack = new Stack<String>();
        final String onTopOfStack = "";
        String exprElement = "";
        this.ReverseStack(this.infixStack);
        while (!this.infixStack.isEmpty()) {
            exprElement = (String)this.infixStack.pop();
            if (this.isNumericNeg(exprElement) || this.isNumeric(exprElement)) {
                postfixStack.push(exprElement);
            }
            else {
                boolean isOperatorToken = false;
                isOperatorToken = (OperatorKind.getOperatorKind(exprElement.charAt(0)) != OperatorKind.ERR && OperatorKind.getOperatorKind(exprElement.charAt(0)) != OperatorKind.LPAR && OperatorKind.getOperatorKind(exprElement.charAt(0)) != OperatorKind.RPAR);
                if (isOperatorToken) {
                    if (OperatorKind.getOperatorKind(exprElement.charAt(0)) != OperatorKind.POW) {
                        while (!tempStack.isEmpty()) {
                            if (!this.precedenceCheck(exprElement.charAt(0), tempStack.peek().charAt(0), true)) {
                                break;
                            }
                            postfixStack.push(tempStack.pop());
                        }
                    }
                    else {
                        while (!tempStack.isEmpty() && this.precedenceCheck(exprElement.charAt(0), tempStack.peek().charAt(0), false)) {
                            postfixStack.push(tempStack.pop());
                        }
                    }
                    tempStack.push(exprElement);
                }
                else {
                    if (exprElement.charAt(0) == '(') {
                        tempStack.push(exprElement);
                    }
                    if (exprElement.charAt(0) != ')') {
                        continue;
                    }
                    while (!tempStack.isEmpty() && tempStack.peek().charAt(0) != '(') {
                        postfixStack.push(tempStack.pop());
                    }
                    if (tempStack.isEmpty()) {
                        throw new Exception("Unbalanced parenthesis! Missing left parenthesis");
                    }
                    tempStack.pop();
                }
            }
        }
        String ee = null;
        while (!tempStack.isEmpty()) {
            ee = tempStack.pop();
            if (ee.charAt(0) == '(') {
                throw new Exception("Unbalanced parenthesis! Missing right parenthesis");
            }
            postfixStack.push(ee);
        }
    }
    
    /**
     * 
     * Method: Convert 
     * @param infix infix expression in a stack
     * @return postfix expression in a stack
	 * 
	 * Test
	 * 	infix		
	 * 		(9+7)/(5-3)
	 * 		1+2*3+4+5*6
	 * 	postfix 	
	 * 		97+53-/
	 *  	123*+4+56*+	
     * @throws Exception 
     */    
    public static Stack<String> Convert(final Stack<String> infix) throws Exception {
        final Stack<String> res = new Stack<String>();
        InfixToPostfix converter = new InfixToPostfix(infix);
        try {
            converter.convertToPostfix(res);
        }
        finally {
            converter = null;
        }
        converter = null;
        return res;
    }
}
