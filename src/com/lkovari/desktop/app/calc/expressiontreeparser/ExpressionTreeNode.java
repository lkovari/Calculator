/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.expressiontreeparser
 * File: ExpressionTreeNode.java
 * Created: Mar 3, 2005 10:48:40 PM
 * Author: lkovari 
 * 
 * Description:
 *  An abstract class for arithmetic parser
 * 
 */
package com.lkovari.desktop.app.calc.expressiontreeparser;

/**
 * @author lkovari
 * ExpressionTreeNode 
 */
public abstract class ExpressionTreeNode
{
    /**
     * Method: getOperatorKind 
     * @return kind of operator
     */
    public abstract OperatorKind getOperatorKind();
    
    /**
     * Method: value 
     * @return the value of operation
     */    
    public abstract double getValue();
    
    /**
     * Method: getChild 
     * @param index the index of node 0 left 1 right
     * @return a child expression tree node
     */    
    public abstract ExpressionTreeNode getChild(final int p0);
    
    /**
     * Method: getChildCount 
     * @return number of childrens
     */    
    public abstract int getChildCount();
    
    /**
     * Method: getIndexOfChild 
     * @param treeNode
     * @return index of a child
     */    
    public abstract int getIndexOfChild(final ExpressionTreeNode p0);
    
    /** 
     * Overriden method
     * @see java.lang.Object#toString()
     * 
     */    
    @Override
    public abstract String toString();
}
