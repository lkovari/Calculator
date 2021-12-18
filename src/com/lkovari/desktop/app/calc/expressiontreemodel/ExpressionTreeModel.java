/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.expressiontreemodel
 * File: ExpressionTreeModel.java
 * Created: Mar 6, 2005 7:05:13 PM
 * Author: lkovari 
 * 
 * Description:
 * Expression tree model
 * The tree model of the ExpressionTree
 * 
 */
package com.lkovari.desktop.app.calc.expressiontreemodel;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;
import com.lkovari.desktop.app.calc.expressiontreeparser.ExpressionTreeNode;
import javax.swing.tree.TreeModel;

public class ExpressionTreeModel implements TreeModel
{
    private ExpressionTreeNode root;
    
    private void setRoot(final ExpressionTreeNode r) {
        this.root = r;
    }
    
    public ExpressionTreeModel(final ExpressionTreeNode root) {
        this.root = null;
        this.setRoot(root);
    }
    
    @Override
    public Object getRoot() {
        return this.root;
    }
    
    @Override
    public Object getChild(final Object parent, final int index) {
        return ((ExpressionTreeNode)parent).getChild(index);
    }
    
    @Override
    public int getChildCount(final Object parent) {
        return ((ExpressionTreeNode)parent).getChildCount();
    }
    
    @Override
    public boolean isLeaf(final Object node) {
        return ((ExpressionTreeNode)node).getChildCount() == 0;
    }
    
    @Override
    public void valueForPathChanged(final TreePath path, final Object newValue) {
    }
    
    @Override
    public int getIndexOfChild(final Object parent, final Object child) {
        final ExpressionTreeNode expressionTreeNode = (ExpressionTreeNode)child;
        return ((ExpressionTreeNode)parent).getIndexOfChild(expressionTreeNode);
    }
    
    @Override
    public void addTreeModelListener(final TreeModelListener l) {
    	// not implemented yes
    }
    
    @Override
    public void removeTreeModelListener(final TreeModelListener l) {
    	// not implemented yes
    }
}
