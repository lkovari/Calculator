/**
 * 
 * Copyright 2005 by Laszlo Kovari
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc
 * File: Calculator.java
 * Created: Sep 07, 2004 11:25:38 AM
 * Author: lkovari 
 * 
 * Description:
 *  A simple calculator
 *  My 1st java program :-)
 *  
 *  ToDo:
 *  
 *  -raise an exception when unbalanced parenthesis occurred
 *  -add numerical system selection
 *  -add unimplemented parts
 *  
 */
package com.lkovari.desktop.app.calc;

import javax.swing.JFrame;

import com.lkovari.desktop.app.calc.gui.CalcPanel;

public class Calculator
{
    public static void main(final String[] args) {
        final CalculatorObject calcObj = new CalculatorObject();
        final CalcPanel calc = new CalcPanel(calcObj);
        calcObj.addRefreshDisplayEventListener(calc);
        calcObj.addRefreshExpressionEventListener(calc);
        calcObj.addRefreshInfoEventListener(calc);
        calcObj.addRefreshExprTreeModelEventListener(calc);
        calcObj.addRefreshErrorEventListener(calc);
        final JFrame mainFrame = new JFrame();
        mainFrame.getContentPane().add(calc);
        calcObj.ClearExpressionTree();
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(3);
        mainFrame.pack();
        mainFrame.setTitle("RPN Calculator, Copyright © 2007 by Laszlo Kovari (still not an exhaustive solution, contains known bugs!)");
        mainFrame.setSize(1024, 572);
    }
}
