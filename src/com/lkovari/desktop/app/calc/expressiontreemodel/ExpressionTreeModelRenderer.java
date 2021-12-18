/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.expressiontreemodel
 * File: ExpressionTreeModelRenderer.java
 * Created: Mar 16, 2005 8:51:57 PM
 * Author: lkovari 
 * 
 * Description:
 * 
 * 
 */
package com.lkovari.desktop.app.calc.expressiontreemodel;

import javax.swing.Icon;
import com.lkovari.desktop.app.calc.expressiontreeparser.ExpressionTreeNode;
import java.awt.Component;
import javax.swing.JTree;
import com.lkovari.desktop.app.calc.expressiontreeparser.OperatorKind;
import java.net.URL;
import javax.swing.ImageIcon;
import java.util.HashMap;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * @author lkovari
 * ExpressionTreeModelRenderer 
 */
public class ExpressionTreeModelRenderer extends DefaultTreeCellRenderer
{
    private static final long serialVersionUID = 1L;
    private String ICONPATH;
    public HashMap<Integer, ImageIcon> IconCollection;
    
    protected static ImageIcon createImageIcon(final String path) {
        final URL imgURL = ExpressionTreeModelRenderer.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        }
        return null;
    }
    
    public void addIcon(final OperatorKind kind, final String icon) {
        this.IconCollection.put(kind.ordinal(), createImageIcon(String.valueOf(this.ICONPATH) + icon + ".gif"));
    }
    
    public ExpressionTreeModelRenderer() {
        this.ICONPATH = "/ExpressionTreeModelIcons/";
        this.IconCollection = new HashMap<Integer, ImageIcon>();
        this.addIcon(OperatorKind.ADD, "Add");
        this.addIcon(OperatorKind.SUB, "Sub");
        this.addIcon(OperatorKind.MUL, "Mul");
        this.addIcon(OperatorKind.DIV, "Div");
        this.addIcon(OperatorKind.MOD, "Mod");
        this.addIcon(OperatorKind.AND, "And");
        this.addIcon(OperatorKind.OR, "Or");
        this.addIcon(OperatorKind.NOT, "Not");
        this.addIcon(OperatorKind.XOR, "Xor");
        this.addIcon(OperatorKind.POW, "Pow");
        this.addIcon(OperatorKind.NEG, "Neg");
        this.addIcon(OperatorKind.SQRT, "Sqrt");
        this.addIcon(OperatorKind.REC, "Rec");
    }
    
    @Override
    public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean sel, final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        if (value instanceof ExpressionTreeNode) {
            final Icon nodeIcon = this.getTreeNodeIcon((ExpressionTreeNode)value);
            this.setIcon(nodeIcon);
        }
        return this;
    }
    
    protected Icon getTreeNodeIcon(final ExpressionTreeNode expressionNode) {
        final int operatorKind = new Integer(expressionNode.getOperatorKind().ordinal());
        if (this.IconCollection.containsKey(operatorKind)) {
            return this.IconCollection.get(operatorKind);
        }
        return null;
    }
}
