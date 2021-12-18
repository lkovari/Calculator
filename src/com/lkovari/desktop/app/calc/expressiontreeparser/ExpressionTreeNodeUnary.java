/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.expressiontreeparser
 * File: ExpressionTreeNodeUnary.java
 * Created: Mar 3, 2005 10:58:51 PM
 * Author: lkovari 
 * 
 * Description:
 *  represent an unary operation
 * 
 */
package com.lkovari.desktop.app.calc.expressiontreeparser;

/**
 * @author lkovari
 * ExpressionTreeNodeUnary 
 */
public class ExpressionTreeNodeUnary extends ExpressionTreeNode
{
    public ExpressionTreeNode operand;
    private OperatorKind operator;
    
    public ExpressionTreeNodeUnary(final OperatorKind k) {
        this.operator = k;
    }
    
    @Override
    public String toString() {
        final Double v = this.getValue();
        return "un =" + v.toString();
    }
    
    @Override
    public ExpressionTreeNode getChild(final int index) {
        ExpressionTreeNode child = null;
        switch (index) {
            case 0: {
                child = this.operand;
                break;
            }
            case 1: {
                child = null;
                break;
            }
        }
        return child;
    }
    
    @Override
    public double getValue() {
        final double operand1 = this.operand.getValue();
        double res = -1.0;
        res = Operations.Execute(operand1, this.operator);
        return res;
    }
    
    @Override
    public int getChildCount() {
        return 1;
    }
    
    @Override
    public int getIndexOfChild(final ExpressionTreeNode treeNode) {
        int ix = -1;
        if (treeNode == this.operand) {
            ix = 0;
        }
        return ix;
    }
    
    @Override
    public OperatorKind getOperatorKind() {
        return this.operator;
    }
}
