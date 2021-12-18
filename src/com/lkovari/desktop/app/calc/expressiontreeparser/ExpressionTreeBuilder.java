/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.expressiontreeparser
 * File: ExpressionTreeBuilder.java
 * Created: Marc 02, 2005 11:25:38 AM
 * Author: lkovari 
 * 
 * Description:
 * 	Build an expression tree from a postfix expression	
 *
 */
package com.lkovari.desktop.app.calc.expressiontreeparser;

import java.util.Stack;
import com.lkovari.desktop.app.calc.expressiontreeparser.utils.ExpressionParserUtils;

/**
 * ExpressionTreeBuilder
 * @author lkovari
 * Description:
 *  build an expression tree for evaluate postfix arithmetic 
 *  expression
 */
public class ExpressionTreeBuilder extends ExpressionParserUtils
{
    private Stack postfixStack;
    private ExpressionTreeNode currentNode;
    private ExpressionTreeNode rootNode;
    
	/**
	 * Constructor ExpressionTreeBuilder
	 * @param postfix postfix arithmetic expression
	 */    
    public ExpressionTreeBuilder(final Stack postfix) {
        this.postfixStack = null;
        this.currentNode = null;
        this.rootNode = null;
        this.postfixStack = postfix;
    }
    
    /**
     * 
     * Method: getNextExpressionElement 
     * @return the next expression element
     */    
    private String getNextExpressionElement() {
        return (String)this.postfixStack.pop();
    }
    
    /**
     * 
     * Method: buildTree 
     * @return an expression tree node
     */    
    public ExpressionTreeNode buildTree() {
        String element = "";
        OperatorKind kind = null;
        ExpressionTreeNode treeNode = null;
        char opr = '\0';
        if (!this.postfixStack.isEmpty()) {
            element = this.getNextExpressionElement();
            opr = element.charAt(0);
            if (element.length() == 1 && OperatorKind.getOperatorKind(opr) != OperatorKind.ERR) {
                kind = OperatorKind.getOperatorKind(opr);
                if (this.isBinaryOperatorToken(opr)) {
                    treeNode = new ExpressionTreeNodeBinary(kind);
                    ((ExpressionTreeNodeBinary)treeNode).right = this.buildTree();
                    ((ExpressionTreeNodeBinary)treeNode).left = this.buildTree();
                }
                if (this.isUnaryOperatorToken(opr)) {
                    treeNode = new ExpressionTreeNodeUnary(kind);
                    ((ExpressionTreeNodeUnary)treeNode).operand = this.buildTree();
                }
            }
            else if (treeNode == null) {
                double v = 0.0;
                v = Double.valueOf(element.trim());
                treeNode = new ExpressionTreeNodeValue(v);
            }
        }
        return treeNode;
    }
    
    /**
     * 
     * Method: Build 
     * @param postfix a postfix arithmetic expression
     * @return an a root expression tree node
     */    
    public static ExpressionTreeNode Build(final Stack postfix) {
        ExpressionTreeNode rootNode = null;
        ExpressionTreeBuilder expressionTreeBuilder = null;
        try {
            expressionTreeBuilder = new ExpressionTreeBuilder(postfix);
            rootNode = expressionTreeBuilder.buildTree();
        }
        finally {
            expressionTreeBuilder = null;
        }
        expressionTreeBuilder = null;
        return rootNode;
    }
}
