/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.expressiontreeparser
 * File: ExpressionTreeNodeBinary.java
 * Created: Mar 3, 2005 10:52:42 PM
 * Author: lkovari 
 * 
 * Description:
 *  represent a binary operation
 * 
 */
package com.lkovari.desktop.app.calc.expressiontreeparser;

/**
 * @author lkovari
 * ExpressionTreeNodeBinary 
 */
public class ExpressionTreeNodeBinary extends ExpressionTreeNode
{
    /**
     * Field <code>left</code> The expression for its left operand
     */
    public ExpressionTreeNode left;
    
    /**
     * Field <code>right</code> The expression for its right operand
     */    
    public ExpressionTreeNode right;
    
    /**
     * Constructor ExpressionTreeNodeBinary
     * @param k operation kind
     */    
    private OperatorKind operator;
    
    public ExpressionTreeNodeBinary(final OperatorKind k) {
        this.operator = k;
    }
    
    /** 
     * Overriden method
     * @return evaluated value
     * 
     */    
    @Override
    public double getValue() {
        final double operand1 = this.left.getValue();
        final double operand2 = this.right.getValue();
        double res = -1.0;
        res = Operations.Execute(operand1, operand2, this.operator);
        return res;
    }
    
    /** 
     * Overriden method
     * @see com.lkovari.desktop.app.calc.expressiontreeparser.ExpressionTreeNode#toString()
     * 
     */    
    @Override
    public String toString() {
        final Double v = this.getValue();
        return "bin =" + v.toString();
    }
    
    /** 
     * Overriden method
     * @see com.lkovari.desktop.app.calc.expressiontreeparser.ExpressionTreeNode#getChild(int)
     * 
     */    
    @Override
    public ExpressionTreeNode getChild(final int index) {
        ExpressionTreeNode child = null;
        switch (index) {
            case 0: {
                child = this.left;
                break;
            }
            case 1: {
                child = this.right;
                break;
            }
        }
        return child;
    }
    
    /** 
     * Overriden method
     * @see com.lkovari.desktop.app.calc.expressiontreeparser.ExpressionTreeNode#getChildCount()
     * 
     */    
    @Override
    public int getChildCount() {
        return 2;
    }
    
    /** 
     * Overriden method
     * @see com.lkovari.desktop.app.calc.expressiontreeparser.ExpressionTreeNode#getIndexOfChild(com.lkovari.desktop.app.calc.expressiontreeparser.ExpressionTreeNode)
     * 
     */    
    @Override
    public int getIndexOfChild(final ExpressionTreeNode treeNode) {
        int ix = 0;
        if (treeNode == this.left) {
            ix = 0;
        }
        if (treeNode == this.right) {
            ix = 1;
        }
        return ix;
    }
    
    /** 
     * Overriden method
     * @see com.lkovari.desktop.app.calc.expressiontreeparser.ExpressionTreeNode#getOperatorKind()
     * 
     */    
    @Override
    public OperatorKind getOperatorKind() {
        return this.operator;
    }
}
