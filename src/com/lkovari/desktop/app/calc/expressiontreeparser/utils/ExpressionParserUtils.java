/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.expressiontreeparser.utils
 * File: ExpressionParserUtils.java
 * Created: Marc 02, 2005 11:25:38 AM
 * Author: lkovari 
 * 
 * Description:
 *  Build an expression tree from a postfix expression  
 *
 */
package com.lkovari.desktop.app.calc.expressiontreeparser.utils;

import java.util.Stack;
import com.lkovari.desktop.app.calc.expressiontreeparser.OperatorType;
import com.lkovari.desktop.app.calc.expressiontreeparser.OperatorKind;

/**
 * ExpressionParserUtils
 * @author lkovari
 * Description:
 *
 */
public class ExpressionParserUtils
{
    protected final char LEFT_PARENTHESIS = '(';
    protected final char RIGHT_PARENTHESIS = ')';
    // the . /dot/
    protected final char DOT_SIGN = '.';
    
    protected final boolean isBinaryOperatorToken(final char opr) {
        final OperatorKind ok = OperatorKind.getOperatorKind(opr);
        return ok.getOperatorType() == OperatorType.BINARY;
    }
    
    protected final boolean isUnaryOperatorToken(final char opr) {
        final OperatorKind ok = OperatorKind.getOperatorKind(opr);
        return ok.getOperatorType() == OperatorType.UNARY;
    }
    
    /**
     * 
     * Method: isParenthesis 
     * @param opr the character
     * @return true if it is a left or right parenthesis
     */    
    protected final boolean isParenthesis(final char opr) {
        return opr == '(' || opr == ')';
    }
    
	/**
	 * 
	 * Method: ReverseStack 
	 * @param ss stack which we want to revere 
	 */    
    protected final void ReverseStack(final Stack ss) {
        final Object[] elements = ss.toArray();
        ss.clear();
        for (int l = elements.length - 1; l >= 0; --l) {
            ss.push(elements[l]);
        }
    }
    
	/**
	 * 
	 * Method: isNumeric 
	 * @param exprElement expression element
	 * @return true if the expression is numeric else false
	 */    
    protected final boolean isNumeric(final String exprElement) {
        char c = '\0';
        final int start = 0;
        boolean res = true;
        for (int l = start; l <= exprElement.length() - 1; ++l) {
            c = exprElement.charAt(l);
            if (!this.IsNumber(c)) {
                res = false;
                break;
            }
        }
        return res;
    }
    
    /**
     * 
     * Method: isNumericNeg 
     * @param exprElement an expression element
     * @return true if theexpression element is a negative number
     */    
    protected final boolean isNumericNeg(final String exprElement) {
        char c = '\0';
        final int start = 1;
        boolean res = true;
        if (exprElement.charAt(0) == '-' && exprElement.length() > 1) {
            for (int l = start; l <= exprElement.length() - 1; ++l) {
                c = exprElement.charAt(l);
                if (!this.IsNumber(c)) {
                    res = false;
                    break;
                }
            }
        }
        else {
            res = false;
        }
        return res;
    }
    
	/**
	 * 
	 * Method: IsNumber 
	 * @param c a character
	 * @return true if a character is in the numeric range 
	 */    
    protected final boolean IsNumber(final char c) {
        boolean res = c >= '0' && c <= '9';
        res = (res || c == '.');
        return res;
    }
}
