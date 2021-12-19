/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc
 * File: CalculatorObject.java
 * Created: Sep 07, 2004 11:25:38 AM
 * Author: lkovari 
 * 
 * Description:
 *  An object for calculate the value of arithmetic expressions
 *  My 1st java program :-)
 *  
 */

package com.lkovari.desktop.app.calc;

import com.lkovari.desktop.app.calc.expressiontreeparser.ExpressionTreeBuilder;
import com.lkovari.desktop.app.calc.expressiontreeparser.InfixToPostfix;
import com.lkovari.desktop.app.calc.refresh.RefreshErrorEventListener;
import com.lkovari.desktop.app.calc.refresh.RefreshExprTreeModelEventListener;
import com.lkovari.desktop.app.calc.refresh.RefreshInfoEventListener;
import com.lkovari.desktop.app.calc.refresh.RefreshExpressionEventListener;
import com.lkovari.desktop.app.calc.refresh.RefreshDisplayEventListener;
import com.lkovari.desktop.app.calc.expressiontreeparser.OperatorKind;
import javax.swing.event.EventListenerList;
import com.lkovari.desktop.app.calc.expressiontreeparser.ExpressionTreeNode;
import java.util.Stack;
import com.lkovari.desktop.app.calc.expressiontreemodel.ExpressionTreeModel;
import com.lkovari.desktop.app.calc.expressiontreeparser.utils.ExpressionParserUtils;

public class CalculatorObject extends ExpressionParserUtils
{
    private String expression;
    private String displayableValue;
    @SuppressWarnings("unused")
	private String lastStoredExprElement;
    @SuppressWarnings("unused")
	private String lastDisplayableExpression;
    private String lastStoredTilOp;
    private String postfixExpression;
    private ExpressionTreeModel expressionTreeModel;
    private double currentValue;
    private Stack<String> expressionStack;
    private Stack<String> postfixStack;
    ExpressionTreeNode rootNode;
    public String displayableExpression;
    protected EventListenerList listenerList;
    
    private String postfixExpressionToString(final Stack<String> postfix) {
        String res = "";
        final Object[] elementList = postfix.toArray();
        for (int l = 0; l <= elementList.length - 1; ++l) {
            String element = (String)elementList[l];
            if (element.length() == 1) {
                final OperatorKind ok = OperatorKind.getOperatorKind(element.charAt(0));
                if (ok != OperatorKind.ERR) {
                    element = ok.getText();
                }
            }
            if (res == "") {
                res = String.valueOf(res) + element;
            }
            else {
                res = String.valueOf(res) + "," + element;
            }
        }
        return res;
    }
    
    public void addRefreshDisplayEventListener(final RefreshDisplayEventListener listener) {
        this.listenerList.add(RefreshDisplayEventListener.class, listener);
    }
    
    public void addRefreshExpressionEventListener(final RefreshExpressionEventListener listener) {
        this.listenerList.add(RefreshExpressionEventListener.class, listener);
    }
    
    public void removeRefreshDisplayEventListener(final RefreshDisplayEventListener listener) {
        this.listenerList.remove(RefreshDisplayEventListener.class, listener);
    }
    
    public void addRefreshInfoEventListener(final RefreshInfoEventListener listener) {
        this.listenerList.add(RefreshInfoEventListener.class, listener);
    }
    
    public void removeRefreshInfoEventListener(final RefreshInfoEventListener listener) {
        this.listenerList.remove(RefreshInfoEventListener.class, listener);
    }
    
    public void removeRefreshExpressionEventListener(final RefreshExpressionEventListener listener) {
        this.listenerList.remove(RefreshExpressionEventListener.class, listener);
    }
    
    public void addRefreshExprTreeModelEventListener(final RefreshExprTreeModelEventListener listener) {
        this.listenerList.add(RefreshExprTreeModelEventListener.class, listener);
    }
    
    public void removeRefreshExprTreeModelEventListener(final RefreshExprTreeModelEventListener listener) {
        this.listenerList.remove(RefreshExprTreeModelEventListener.class, listener);
    }
    
    public void addRefreshErrorEventListener(final RefreshErrorEventListener listener) {
        this.listenerList.add(RefreshErrorEventListener.class, listener);
    }
    
    public void removeRefreshErrorEventListener(final RefreshErrorEventListener listener) {
        this.listenerList.remove(RefreshErrorEventListener.class, listener);
    }
    
    void fireRefreshErrorEvent(final String vlu) {
        final Object[] listeners = this.listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == RefreshErrorEventListener.class && listeners[i + 1] != null) {
                ((RefreshErrorEventListener)listeners[i + 1]).RefreshErrorEventOccurred(vlu);
            }
        }
    }
    
    void fireRefreshDisplayEvent(final String vlu) {
        final Object[] listeners = this.listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == RefreshDisplayEventListener.class && listeners[i + 1] != null) {
                ((RefreshDisplayEventListener)listeners[i + 1]).RefreshDisplayEventOccurred(vlu);
            }
        }
    }
    
    void fireRefreshExpressionEvent(final String expr) {
        final Object[] listeners = this.listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == RefreshExpressionEventListener.class && listeners[i + 1] != null) {
                ((RefreshExpressionEventListener)listeners[i + 1]).RefreshExpressionEventOccurred(expr);
            }
        }
    }
    
    void fireRefreshInfoEvent(final String vlu) {
        final Object[] listeners = this.listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == RefreshInfoEventListener.class && listeners[i + 1] != null) {
                ((RefreshInfoEventListener)listeners[i + 1]).RefreshInfoEventOccurred(vlu);
            }
        }
    }
    
    void fireRefreshExprTreeModelEvent(final ExpressionTreeModel vlu) {
        final Object[] listeners = this.listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == RefreshExprTreeModelEventListener.class && listeners[i + 1] != null) {
                ((RefreshExprTreeModelEventListener)listeners[i + 1]).RefreshExprTreeModelEventOccurred(vlu);
            }
        }
    }
    
    public void Clear() {
        this.fireRefreshInfoEvent(this.postfixExpression = "");
        this.postfixStack.clear();
        this.expressionStack.clear();
        this.DisplayExpression(this.displayableExpression = "");
        this.displayableValue = "";
        this.expression = "";
        this.lastStoredTilOp = "";
        this.DisplayNumber(this.displayableValue);
        this.ClearExpressionTree();
        this.fireRefreshErrorEvent("");
    }
    
    public void signChange() {
        final int l = this.expression.length();
        if (this.isNumeric(this.expression)) {
            this.expression = "-" + this.expression;
        }
        else if (this.isNumericNeg(this.expression)) {
            final char[] expr = this.expression.toCharArray();
            this.expression = String.copyValueOf(expr, 1, l - 1);
        }
        if (this.lastStoredTilOp != null) {
            this.displayableExpression = this.lastStoredTilOp;
        }
        else {
            this.displayableExpression = "";
        }
        this.DisplayExpression(this.displayableExpression = String.valueOf(this.displayableExpression) + this.expression);
    }
    
    public void removeLast() {
    }
    
    public void ClearExpressionTree() {
        this.fireRefreshExprTreeModelEvent(null);
    }
    
    public void DisplayNumber(final String num) {
        this.fireRefreshDisplayEvent(num);
    }
    
    public void DisplayExpression(final String expr) {
        this.fireRefreshExpressionEvent(expr);
    }
    
    public void DisplayNumber(final double num) {
        String s = "";
        s = String.valueOf(num);
        this.DisplayNumber(s);
    }
    
    public void Hit(final char o, final boolean keyboard) {
        String opr = "";
        if (o == 'C' || o == 'c') {
            this.Clear();
            System.out.println(String.valueOf(o) + "pressed");
        }
        if (o == '=' || o == '\r') {
            if (this.expression != "") {
                this.expressionStack.push(this.expression);
                this.lastStoredExprElement = this.expression;
                this.expression = "";
            }
            try {
                this.postfixStack = (Stack<String>)InfixToPostfix.Convert(this.expressionStack);
            }
            catch (Exception e) {
                this.fireRefreshErrorEvent(e.getMessage());
            }
            try {
                this.postfixExpression = this.postfixExpressionToString(this.postfixStack);
            }
            catch (Exception e) {
                this.fireRefreshErrorEvent(e.getMessage());
            }
            this.fireRefreshInfoEvent(this.postfixExpression);
            this.rootNode = ExpressionTreeBuilder.Build(this.postfixStack);
            if (this.expressionTreeModel != null) {
                this.expressionTreeModel = null;
            }
            this.expressionTreeModel = new ExpressionTreeModel(this.rootNode);
            this.currentValue = this.rootNode.getValue();
            this.expression = String.valueOf(this.currentValue);
            this.DisplayNumber(this.currentValue);
            this.fireRefreshExprTreeModelEvent(this.expressionTreeModel);
        }
        else {
            if (this.IsNumber(o)) {
                this.expression = String.valueOf(this.expression) + o;
            }
            else {
                if (o == '.') {
                    this.expression = String.valueOf(this.expression) + o;
                }
                else if (this.expression != "") {
                    this.expressionStack.push(this.expression);
                    this.lastStoredExprElement = this.expression;
                    this.expression = "";
                }
                final boolean isOperatorToken = OperatorKind.getOperatorKind(o) != OperatorKind.ERR && OperatorKind.getOperatorKind(o) != OperatorKind.LPAR && OperatorKind.getOperatorKind(o) != OperatorKind.RPAR;
                if (isOperatorToken) {
                    opr = "";
                    opr = String.valueOf(opr) + o;
                    this.expressionStack.push(opr);
                    this.lastStoredExprElement = opr;
                }
                if (this.isParenthesis(o)) {
                    opr = "";
                    opr = String.valueOf(opr) + o;
                    this.expressionStack.push(opr);
                    this.lastStoredExprElement = opr;
                }
            }
            this.lastDisplayableExpression = this.displayableExpression;
            if (OperatorKind.getOperatorKind(o) != OperatorKind.ERR) {
                this.displayableExpression = String.valueOf(this.displayableExpression) + OperatorKind.getOperatorKind(o).getText();
                this.lastStoredTilOp = this.displayableExpression;
            }
            else {
                this.displayableExpression = String.valueOf(this.displayableExpression) + o;
            }
            if (!keyboard) {
                this.DisplayExpression(this.displayableExpression);
            }
        }
    }
    
    public void EnterExpression(final String expr) throws Exception {
        char c = '\0';
        char cc = '\0';
        for (int l = 0; l < expr.length(); ++l) {
            c = expr.charAt(l);
            cc = OperatorKind.getOperatorToken(c);
            if (cc != '\0') {
                this.Hit(cc, false);
            }
            else {
                this.Hit(c, false);
            }
        }
    }
    
    public String getPostfixExpression() {
        if (this.postfixExpression == "" && !this.postfixStack.isEmpty()) {
            this.postfixExpression = this.postfixExpressionToString(this.postfixStack);
        }
        return this.postfixExpression;
    }
    
    public ExpressionTreeModel getExpressionTreeModel() {
        return this.expressionTreeModel;
    }
    
    public CalculatorObject() {
        this.expression = "";
        this.displayableValue = "";
        this.lastStoredExprElement = "";
        this.lastDisplayableExpression = null;
        this.lastStoredTilOp = null;
        this.postfixExpression = "";
        this.expressionTreeModel = null;
        this.currentValue = 0.0;
        this.expressionStack = new Stack<String>();
        this.postfixStack = new Stack<String>();
        this.rootNode = null;
        this.displayableExpression = "";
        this.listenerList = new EventListenerList();
        this.expression = "";
    }
    
    @Override
    protected void finalize() throws Throwable {
        this.expressionStack.empty();
        this.expressionStack = null;
        this.postfixStack.empty();
        this.postfixStack = null;
    }
}
