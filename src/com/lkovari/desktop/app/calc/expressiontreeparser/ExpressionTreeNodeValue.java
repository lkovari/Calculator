/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.expressiontreeparser
 * File: ExpressionTreeNodeValue.java
 * Created: Mar 3, 2005 10:58:51 PM
 * Author: lkovari 
 * 
 * Description:
 *  represent the value of an expression
 * 
 */
package com.lkovari.desktop.app.calc.expressiontreeparser;

/**
 * ExpressionTreeNodeValue
 * @author lkovari
 * Description:
 *
 */
public class ExpressionTreeNodeValue extends ExpressionTreeNode
{
    private double number;
    
    public ExpressionTreeNodeValue(final double value) {
        this.number = -1.0;
        this.number = value;
    }
    
    @Override
    public String toString() {
        return new StringBuilder().append(this.number).toString();
    }
    
    @Override
    public ExpressionTreeNode getChild(final int index) {
        return null;
    }
    
    @Override
    public double getValue() {
        return this.number;
    }
    
    @Override
    public int getChildCount() {
        return 0;
    }
    
    @Override
    public int getIndexOfChild(final ExpressionTreeNode treeNode) {
        return -1;
    }
    
    @Override
    public OperatorKind getOperatorKind() {
        return OperatorKind.NOP;
    }
}
