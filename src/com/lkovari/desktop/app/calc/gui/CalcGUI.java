/**
 * 
 * Copyright 2005-2007 by L?szl? K?v?ri
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.gui
 * File: CalcGUI.java
 * Created: Jun 17, 2007 8:10:05 PM
 * Author: lkovari 
 * 
 * Description:
 * 
 * 
 */
package com.lkovari.desktop.app.calc.gui;

import javax.swing.JFrame;
import com.lkovari.desktop.app.calc.gui.calcbutton.events.CalculatorButtonPressEvent;
import com.lkovari.desktop.app.calc.expressiontreemodel.ExpressionTreeModel;
import java.awt.event.KeyEvent;
import java.awt.event.ComponentEvent;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;

import com.jgoodies.forms.layout.CellConstraints;
import com.lkovari.desktop.app.calc.gui.kinds.ButtonType;
import com.lkovari.desktop.app.calc.gui.kinds.ButtonId;
import com.lkovari.desktop.app.calc.gui.calcbutton.CalculatorButton;
import com.jgoodies.forms.layout.FormLayout;
import javax.swing.JComboBox;
import javax.swing.JTree;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import com.lkovari.desktop.app.calc.CalculatorObject;
import com.lkovari.desktop.app.calc.gui.calcbutton.events.CalcButtonListener;
import com.lkovari.desktop.app.calc.refresh.RefreshExpressionEventListener;
import com.lkovari.desktop.app.calc.refresh.RefreshDisplayEventListener;
import com.lkovari.desktop.app.calc.refresh.RefreshInfoEventListener;
import com.lkovari.desktop.app.calc.refresh.RefreshExprTreeModelEventListener;
import com.lkovari.desktop.app.calc.refresh.RefreshErrorEventListener;
import java.awt.event.KeyListener;
import java.awt.event.ComponentListener;
import javax.swing.JPanel;

/**
 * @author lkovari
 * CalcGUI 
 */
public class CalcGUI extends JPanel implements ComponentListener, KeyListener, RefreshErrorEventListener, RefreshExprTreeModelEventListener, RefreshInfoEventListener, RefreshDisplayEventListener, RefreshExpressionEventListener, CalcButtonListener
{
	private static final long serialVersionUID = 1L;
	private ButtonsManager buttonsManager;
    private CalculatorObject calculatorObject;
    private JPanel buttonsPanel;
    private JSplitPane calculatorSplitPane;
    private JTextField expressionTextField;
    private JTree expressionTree;
    private JLabel footerLabel;
    private JPanel keysPanel;
    private JComboBox<String> numericalSystemComboBox;
    private JLabel numericalSystemLabel;
    private JPanel numericalSystemPanel;
    private JPanel valuesPanel;
    private JTextField postfixExpressionTextField;
    private JTextField valueTextField;
    private FormLayout keysPanelLayout;
    private NumericalSystem selectedNumericalSystem;
    private String[][] buttonCaptions;
    private int colCount;
    private int rowCount;
    
    public CalcGUI(final CalculatorObject calcObj) {
        this.buttonsManager = null;
        this.calculatorObject = null;
        this.keysPanelLayout = null;
        this.selectedNumericalSystem = NumericalSystem.DECIMAL;
        this.buttonCaptions = new String[][] { { "Pi", "Sqrt", "x^2", "M+", " ", "D", "E", "F", "Mod", "Clr" }, { "Sin", "Exp", "x^3", "M-", " ", "A", "B", "C", "And", "+" }, { "Tan", "Log", "x^y", "Mr", " ", "7", "8", "9", "Or", "-" }, { "Cos", "Ln", "1/x", "Mc", " ", "4", "5", "6", "Xor", "*" }, { "(", ")", " ", " ", " ", "1", "2", "3", "Not", "/" }, { " ", " ", " ", " ", " ", "0", "+/-", ".", "Lsh", "=" } };
        this.colCount = 10;
        this.rowCount = 6;
        this.initComponents();
        this.initLayout();
        this.initButtons();
        this.calculatorObject = calcObj;
        this.buttonsManager = new ButtonsManager(this.keysPanel);
        this.numericalSystemComboBox.setSelectedIndex(2);
        this.addComponentListener(this);
    }
    
    @Override
    protected void finalize() throws Throwable {
        this.calculatorObject = null;
        this.buttonsManager = null;
    }
    
    private void initLayout() {
        final String colSpec = "c:p:g, c:p:g, c:p:g, c:p:g, c:p:g, c:p:g, c:p:g, c:p:g, c:p:g, c:p:g, c:p:g";
        final String rowSpec = "c:p:g, c:p:g, c:p:g, c:p:g, c:p:g, c:p:g";
        (this.keysPanelLayout = new FormLayout(colSpec, rowSpec)).setColumnGroups(new int[][] { { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 } });
        this.keysPanelLayout.setRowGroups(new int[][] { { 1, 2, 3, 4, 5, 6 } });
        this.keysPanel.setLayout(this.keysPanelLayout);
        this.keysPanel.setSize(1024,  768);
    }
    
    private void storeButtonProperties(final CalculatorButton calculatorButton) {
        char c = '\0';
        if (calculatorButton.getText().equals(ButtonId.ADD.getValue())) {
            calculatorButton.setButtonId(ButtonId.ADD);
            calculatorButton.setButtonType(ButtonType.OPERATOR);
            calculatorButton.setToolTipText("Addition");
            c = 'G';
            calculatorButton.setOperationCode(c);
        }
        if (calculatorButton.getText().equals(ButtonId.EQU.getValue())) {
            calculatorButton.setButtonId(ButtonId.EQU);
            calculatorButton.setButtonType(ButtonType.OPERATOR);
            calculatorButton.setToolTipText("Equal");
            c = '=';
        }
        if (calculatorButton.getText().equals(ButtonId.LSH.getValue())) {
            calculatorButton.setButtonId(ButtonId.LSH);
            calculatorButton.setButtonType(ButtonType.OPERATOR);
            calculatorButton.setToolTipText("Left shift");
        }
        if (calculatorButton.getText().equals(ButtonId.AND.getValue())) {
            calculatorButton.setButtonId(ButtonId.AND);
            calculatorButton.setButtonType(ButtonType.OPERATOR);
            calculatorButton.setToolTipText("And");
            c = 'L';
        }
        if (calculatorButton.getText().equals(ButtonId.CLR.getValue())) {
            calculatorButton.setButtonId(ButtonId.CLR);
            calculatorButton.setButtonType(ButtonType.OPERATOR);
            calculatorButton.setToolTipText("Clear");
            c = '\uffff';
        }
        if (calculatorButton.getText().equals(ButtonId.COS.getValue())) {
            calculatorButton.setButtonId(ButtonId.COS);
            calculatorButton.setButtonType(ButtonType.FUNCTION);
            calculatorButton.setToolTipText("Cosinus");
        }
        if (calculatorButton.getText().equals(ButtonId.DIV.getValue())) {
            calculatorButton.setButtonId(ButtonId.DIV);
            calculatorButton.setButtonType(ButtonType.OPERATOR);
            calculatorButton.setToolTipText("Divide");
            c = 'J';
        }
        if (calculatorButton.getText().equals(ButtonId.DOT.getValue())) {
            calculatorButton.setButtonId(ButtonId.DOT);
            calculatorButton.setButtonType(ButtonType.MODIFIER);
            calculatorButton.setToolTipText("fraction point, dot");
            c = '.';
        }
        if (calculatorButton.getText().equals(ButtonId.EMPTY.getValue())) {
            calculatorButton.setButtonId(ButtonId.EMPTY);
            calculatorButton.setButtonType(ButtonType.MODIFIER);
        }
        if (calculatorButton.getText().equals(ButtonId.EXP.getValue())) {
            calculatorButton.setButtonId(ButtonId.EXP);
            calculatorButton.setButtonType(ButtonType.FUNCTION);
            calculatorButton.setToolTipText("Exponent");
        }
        if (calculatorButton.getText().equals(ButtonId.FACT.getValue())) {
            calculatorButton.setButtonId(ButtonId.FACT);
            calculatorButton.setButtonType(ButtonType.FUNCTION);
            calculatorButton.setToolTipText("Factorial");
        }
        if (calculatorButton.getText().equals(ButtonId.LN.getValue())) {
            calculatorButton.setButtonId(ButtonId.LN);
            calculatorButton.setButtonType(ButtonType.FUNCTION);
            calculatorButton.setToolTipText("Natural logarythm");
        }
        if (calculatorButton.getText().equals(ButtonId.LOG.getValue())) {
            calculatorButton.setButtonId(ButtonId.LOG);
            calculatorButton.setButtonType(ButtonType.FUNCTION);
            calculatorButton.setToolTipText("Log");
        }
        if (calculatorButton.getText().equals(ButtonId.MEMA.getValue())) {
            calculatorButton.setButtonId(ButtonId.MEMA);
            calculatorButton.setButtonType(ButtonType.OPERATOR);
            calculatorButton.setToolTipText("Memory add");
        }
        if (calculatorButton.getText().equals(ButtonId.MEMR.getValue())) {
            calculatorButton.setButtonId(ButtonId.AND);
            calculatorButton.setButtonType(ButtonType.OPERATOR);
            calculatorButton.setToolTipText("Memory read");
        }
        if (calculatorButton.getText().equals(ButtonId.MEMS.getValue())) {
            calculatorButton.setButtonId(ButtonId.MEMS);
            calculatorButton.setButtonType(ButtonType.OPERATOR);
            calculatorButton.setToolTipText("Memory sub add as negative value");
        }
        if (calculatorButton.getText().equals(ButtonId.MEMC.getValue())) {
            calculatorButton.setButtonId(ButtonId.MEMC);
            calculatorButton.setButtonType(ButtonType.OPERATOR);
            calculatorButton.setToolTipText("Memory clear");
        }
        if (calculatorButton.getText().equals(ButtonId.MOD.getValue())) {
            calculatorButton.setButtonId(ButtonId.MOD);
            calculatorButton.setButtonType(ButtonType.OPERATOR);
            calculatorButton.setToolTipText("Modulo");
            c = 'K';
        }
        if (calculatorButton.getText().equals(ButtonId.MUL.getValue())) {
            calculatorButton.setButtonId(ButtonId.MUL);
            calculatorButton.setButtonType(ButtonType.OPERATOR);
            calculatorButton.setToolTipText("Multiplication");
            c = 'I';
        }
        if (calculatorButton.getText().equals(ButtonId.NOT.getValue())) {
            calculatorButton.setButtonId(ButtonId.NOT);
            calculatorButton.setButtonType(ButtonType.OPERATOR);
            calculatorButton.setToolTipText("Not");
            c = 'N';
        }
        if (calculatorButton.getText().equals(ButtonId.NUM0.getValue())) {
            calculatorButton.setButtonId(ButtonId.NUM0);
            calculatorButton.setButtonType(ButtonType.NUMERIC);
            calculatorButton.setToolTipText("Zero value");
            calculatorButton.addNumericalSystem(NumericalSystem.BINARY);
            calculatorButton.addNumericalSystem(NumericalSystem.OCTAL);
            calculatorButton.addNumericalSystem(NumericalSystem.DECIMAL);
            calculatorButton.addNumericalSystem(NumericalSystem.HEXADECIMAL);
            c = '0';
        }
        if (calculatorButton.getText().equals(ButtonId.NUM1.getValue())) {
            calculatorButton.setButtonId(ButtonId.NUM1);
            calculatorButton.setButtonType(ButtonType.NUMERIC);
            calculatorButton.setToolTipText("one");
            calculatorButton.addNumericalSystem(NumericalSystem.BINARY);
            calculatorButton.addNumericalSystem(NumericalSystem.OCTAL);
            calculatorButton.addNumericalSystem(NumericalSystem.DECIMAL);
            calculatorButton.addNumericalSystem(NumericalSystem.HEXADECIMAL);
            c = '1';
        }
        if (calculatorButton.getText().equals(ButtonId.NUM2.getValue())) {
            calculatorButton.setButtonId(ButtonId.NUM2);
            calculatorButton.setButtonType(ButtonType.NUMERIC);
            calculatorButton.setToolTipText("two");
            calculatorButton.addNumericalSystem(NumericalSystem.OCTAL);
            calculatorButton.addNumericalSystem(NumericalSystem.DECIMAL);
            calculatorButton.addNumericalSystem(NumericalSystem.HEXADECIMAL);
            c = '2';
        }
        if (calculatorButton.getText().equals(ButtonId.NUM3.getValue())) {
            calculatorButton.setButtonId(ButtonId.NUM3);
            calculatorButton.setButtonType(ButtonType.NUMERIC);
            calculatorButton.setToolTipText("three");
            calculatorButton.addNumericalSystem(NumericalSystem.OCTAL);
            calculatorButton.addNumericalSystem(NumericalSystem.DECIMAL);
            calculatorButton.addNumericalSystem(NumericalSystem.HEXADECIMAL);
            c = '3';
        }
        if (calculatorButton.getText().equals(ButtonId.NUM4.getValue())) {
            calculatorButton.setButtonId(ButtonId.NUM4);
            calculatorButton.setButtonType(ButtonType.NUMERIC);
            calculatorButton.setToolTipText("four");
            calculatorButton.addNumericalSystem(NumericalSystem.OCTAL);
            calculatorButton.addNumericalSystem(NumericalSystem.DECIMAL);
            calculatorButton.addNumericalSystem(NumericalSystem.HEXADECIMAL);
            c = '4';
        }
        if (calculatorButton.getText().equals(ButtonId.NUM5.getValue())) {
            calculatorButton.setButtonId(ButtonId.NUM5);
            calculatorButton.setButtonType(ButtonType.NUMERIC);
            calculatorButton.setToolTipText("five");
            calculatorButton.addNumericalSystem(NumericalSystem.OCTAL);
            calculatorButton.addNumericalSystem(NumericalSystem.DECIMAL);
            calculatorButton.addNumericalSystem(NumericalSystem.HEXADECIMAL);
            c = '5';
        }
        if (calculatorButton.getText().equals(ButtonId.NUM6.getValue())) {
            calculatorButton.setButtonId(ButtonId.NUM6);
            calculatorButton.setButtonType(ButtonType.NUMERIC);
            calculatorButton.setToolTipText("six");
            calculatorButton.addNumericalSystem(NumericalSystem.OCTAL);
            calculatorButton.addNumericalSystem(NumericalSystem.DECIMAL);
            calculatorButton.addNumericalSystem(NumericalSystem.HEXADECIMAL);
            c = '6';
        }
        if (calculatorButton.getText().equals(ButtonId.NUM7.getValue())) {
            calculatorButton.setButtonId(ButtonId.NUM7);
            calculatorButton.setButtonType(ButtonType.NUMERIC);
            calculatorButton.setToolTipText("seven");
            calculatorButton.addNumericalSystem(NumericalSystem.OCTAL);
            calculatorButton.addNumericalSystem(NumericalSystem.DECIMAL);
            calculatorButton.addNumericalSystem(NumericalSystem.HEXADECIMAL);
            c = '7';
        }
        if (calculatorButton.getText().equals(ButtonId.NUM8.getValue())) {
            calculatorButton.setButtonId(ButtonId.NUM8);
            calculatorButton.setButtonType(ButtonType.NUMERIC);
            calculatorButton.setToolTipText("eight");
            calculatorButton.addNumericalSystem(NumericalSystem.DECIMAL);
            calculatorButton.addNumericalSystem(NumericalSystem.HEXADECIMAL);
            c = '8';
        }
        if (calculatorButton.getText().equals(ButtonId.NUM9.getValue())) {
            calculatorButton.setButtonId(ButtonId.NUM9);
            calculatorButton.setButtonType(ButtonType.NUMERIC);
            calculatorButton.setToolTipText("nine");
            calculatorButton.addNumericalSystem(NumericalSystem.DECIMAL);
            calculatorButton.addNumericalSystem(NumericalSystem.HEXADECIMAL);
            c = '9';
        }
        if (calculatorButton.getText().equals(ButtonId.NUMA.getValue())) {
            calculatorButton.setButtonId(ButtonId.NUMA);
            calculatorButton.setButtonType(ButtonType.NUMERIC);
            calculatorButton.setToolTipText("ten");
            calculatorButton.addNumericalSystem(NumericalSystem.HEXADECIMAL);
            c = 'A';
        }
        if (calculatorButton.getText().equals(ButtonId.NUMB.getValue())) {
            calculatorButton.setButtonId(ButtonId.NUMB);
            calculatorButton.setButtonType(ButtonType.NUMERIC);
            calculatorButton.setToolTipText("eleven");
            calculatorButton.addNumericalSystem(NumericalSystem.HEXADECIMAL);
            c = 'B';
        }
        if (calculatorButton.getText().equals(ButtonId.NUMC.getValue())) {
            calculatorButton.setButtonId(ButtonId.NUMC);
            calculatorButton.setButtonType(ButtonType.NUMERIC);
            calculatorButton.setToolTipText("twelve");
            calculatorButton.addNumericalSystem(NumericalSystem.HEXADECIMAL);
            c = 'C';
        }
        if (calculatorButton.getText().equals(ButtonId.NUMD.getValue())) {
            calculatorButton.setButtonId(ButtonId.NUMD);
            calculatorButton.setButtonType(ButtonType.NUMERIC);
            calculatorButton.setToolTipText("thirteen");
            calculatorButton.addNumericalSystem(NumericalSystem.HEXADECIMAL);
            c = 'D';
        }
        if (calculatorButton.getText().equals(ButtonId.NUME.getValue())) {
            calculatorButton.setButtonId(ButtonId.NUME);
            calculatorButton.setButtonType(ButtonType.NUMERIC);
            calculatorButton.setToolTipText("fourteen");
            calculatorButton.addNumericalSystem(NumericalSystem.HEXADECIMAL);
            c = 'E';
        }
        if (calculatorButton.getText().equals(ButtonId.NUMF.getValue())) {
            calculatorButton.setButtonId(ButtonId.NUMF);
            calculatorButton.setButtonType(ButtonType.NUMERIC);
            calculatorButton.setToolTipText("fifteen");
            calculatorButton.addNumericalSystem(NumericalSystem.HEXADECIMAL);
            c = 'F';
        }
        if (calculatorButton.getText().equals(ButtonId.OR.getValue())) {
            calculatorButton.setButtonId(ButtonId.OR);
            calculatorButton.setButtonType(ButtonType.OPERATOR);
            calculatorButton.setToolTipText("Or");
            c = 'M';
        }
        if (calculatorButton.getText().equals(ButtonId.PARL.getValue())) {
            calculatorButton.setButtonId(ButtonId.PARL);
            calculatorButton.setButtonType(ButtonType.OPERATOR);
            calculatorButton.setToolTipText("Left parenthesis");
            c = '(';
        }
        if (calculatorButton.getText().equals(ButtonId.PARR.getValue())) {
            calculatorButton.setButtonId(ButtonId.PARR);
            calculatorButton.setButtonType(ButtonType.OPERATOR);
            calculatorButton.setToolTipText("Right parenthesis");
            c = ')';
        }
        if (calculatorButton.getText().equals(ButtonId.PI.getValue())) {
            calculatorButton.setButtonId(ButtonId.PI);
            calculatorButton.setButtonType(ButtonType.FUNCTION);
            calculatorButton.setToolTipText("Pi value");
        }
        if (calculatorButton.getText().equals(ButtonId.POW.getValue())) {
            calculatorButton.setButtonId(ButtonId.POW);
            calculatorButton.setButtonType(ButtonType.FUNCTION);
            calculatorButton.setToolTipText("x ^ y");
            c = 'Q';
        }
        if (calculatorButton.getText().equals(ButtonId.POW2.getValue())) {
            calculatorButton.setButtonId(ButtonId.POW2);
            calculatorButton.setButtonType(ButtonType.FUNCTION);
            calculatorButton.setToolTipText("x ^ 2");
        }
        if (calculatorButton.getText().equals(ButtonId.POW3.getValue())) {
            calculatorButton.setButtonId(ButtonId.POW3);
            calculatorButton.setButtonType(ButtonType.FUNCTION);
            calculatorButton.setToolTipText("x ^ 3");
        }
        if (calculatorButton.getText().equals(ButtonId.REC.getValue())) {
            calculatorButton.setButtonId(ButtonId.REC);
            calculatorButton.setButtonType(ButtonType.FUNCTION);
            calculatorButton.setToolTipText("Reciproc");
            c = 'S';
        }
        if (calculatorButton.getText().equals(ButtonId.SIGN.getValue())) {
            calculatorButton.setButtonId(ButtonId.SIGN);
            calculatorButton.setButtonType(ButtonType.MODIFIER);
            calculatorButton.setToolTipText("Sign * -1");
        }
        if (calculatorButton.getText().equals(ButtonId.SIN.getValue())) {
            calculatorButton.setButtonId(ButtonId.SIN);
            calculatorButton.setButtonType(ButtonType.FUNCTION);
            calculatorButton.setToolTipText("Sinus");
        }
        if (calculatorButton.getText().equals(ButtonId.SQRT.getValue())) {
            calculatorButton.setButtonId(ButtonId.SQRT);
            calculatorButton.setButtonType(ButtonType.FUNCTION);
            calculatorButton.setToolTipText("Square root");
            c = 'R';
        }
        if (calculatorButton.getText().equals(ButtonId.SUB.getValue())) {
            calculatorButton.setButtonId(ButtonId.SUB);
            calculatorButton.setButtonType(ButtonType.OPERATOR);
            calculatorButton.setToolTipText("Substraction");
            c = 'H';
        }
        if (calculatorButton.getText().equals(ButtonId.TAN.getValue())) {
            calculatorButton.setButtonId(ButtonId.TAN);
            calculatorButton.setButtonType(ButtonType.FUNCTION);
            calculatorButton.setToolTipText("Tangent");
        }
        if (calculatorButton.getText().equals(ButtonId.XOR.getValue())) {
            calculatorButton.setButtonId(ButtonId.XOR);
            calculatorButton.setButtonType(ButtonType.FUNCTION);
            calculatorButton.setToolTipText("Exclusive or");
        }
        calculatorButton.setOperationCode(c);
        calculatorButton.repaint();
    }
    
    public void initButtons() {
        String caption = null;
        for (int row = 0; row < this.rowCount; ++row) {
            for (int col = 0; col < this.colCount; ++col) {
                System.out.println(col + "," + row);
                caption = this.buttonCaptions[row][col];
                final CalculatorButton calcButton = new CalculatorButton(caption);
                calcButton.addButtonPressListener(this);
                final CellConstraints cellConstraints = new CellConstraints();
                this.storeButtonProperties(calcButton);
                this.keysPanel.add(calcButton, cellConstraints.xywh(col + 1, row + 1, 1, 1, "f,f"));
            }
        }
    }
    
    public void initComponents() {
        this.valueTextField = new JTextField();
        this.expressionTextField = new JTextField();
        this.footerLabel = new JLabel();
        this.calculatorSplitPane = new JSplitPane();
        this.buttonsPanel = new JPanel();
        this.numericalSystemPanel = new JPanel();
        this.numericalSystemLabel = new JLabel();
        this.numericalSystemComboBox = new JComboBox<String>();
        this.keysPanel = new JPanel();
        this.postfixExpressionTextField = new JTextField();
        this.expressionTree = new JTree();
        this.valuesPanel = new JPanel();
        this.setToolTipText("Copyright (c) 2005 by L\u00e1szl\u00f3 K\u00f6v\u00e1ri\n(Not implemented all functions yet)");
        this.setMinimumSize(new Dimension(435, 242));
        this.valueTextField.setHorizontalAlignment(4);
        this.expressionTextField.setHorizontalAlignment(4);
        this.footerLabel.setText("-");
        this.footerLabel.setAutoscrolls(true);
        this.footerLabel.setOpaque(true);
        this.calculatorSplitPane.setDividerLocation(177);
        this.calculatorSplitPane.setOneTouchExpandable(true);
        this.buttonsPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, new Color(0, 0, 0)));
        this.numericalSystemPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
        this.numericalSystemLabel.setText("Numerical system");
        this.numericalSystemComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "2", "8", "10", "16" }));
        this.numericalSystemComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent evt) {
                CalcGUI.this.numericalSystemComboBoxItemStateChanged(evt);
            }
        });
        this.numericalSystemComboBox.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                CalcGUI.this.numericalSystemComboBoxPropertyChange(evt);
            }
        });
        final SpringLayout springLayout = new SpringLayout();
        this.valuesPanel.setLayout(springLayout);
        this.valuesPanel.add(this.valueTextField);
        this.valuesPanel.add(this.expressionTextField);
        final SpringLayout.Constraints labelCons = springLayout.getConstraints(this.valueTextField);
        labelCons.setX(Spring.constant(5));
        labelCons.setY(Spring.constant(5));
        final SpringLayout.Constraints textFieldCons = springLayout.getConstraints(this.expressionTextField);
        textFieldCons.setX(Spring.sum(Spring.constant(5), labelCons.getConstraint("East")));
        textFieldCons.setY(Spring.constant(5));
        this.add(this.valuesPanel, "North");
    }
    
    private void numericalSystemComboBoxPropertyChange(final PropertyChangeEvent evt) {
    }
    
    private void numericalSystemComboBoxItemStateChanged(final ItemEvent evt) {
        final int selNumericalSystem = Integer.parseInt((String)this.numericalSystemComboBox.getSelectedItem());
        switch (selNumericalSystem) {
            case 2: {
                this.selectedNumericalSystem = NumericalSystem.BINARY;
                break;
            }
            case 8: {
                this.selectedNumericalSystem = NumericalSystem.OCTAL;
                break;
            }
            case 10: {
                this.selectedNumericalSystem = NumericalSystem.DECIMAL;
                break;
            }
            case 16: {
                this.selectedNumericalSystem = NumericalSystem.HEXADECIMAL;
                break;
            }
        }
        this.buttonsManager.activateButtonsByNumericalSystems(this.selectedNumericalSystem);
    }
    
    @Override
    public void componentHidden(final ComponentEvent e) {
    }
    
    @Override
    public void componentMoved(final ComponentEvent e) {
    }
    
    @Override
    public void componentResized(final ComponentEvent e) {
    }
    
    @Override
    public void componentShown(final ComponentEvent e) {
    }
    
    @Override
    public void keyPressed(final KeyEvent e) {
    }
    
    @Override
    public void keyReleased(final KeyEvent e) {
    }
    
    @Override
    public void keyTyped(final KeyEvent e) {
        if (e.getID() == 400) {
            final char c = e.getKeyChar();
            try {
                this.calculatorObject.Hit(c, true);
            }
            catch (Exception e2) {
                this.RefreshErrorEventOccurred(e2.getMessage());
            }
            System.out.println("KEY TYPED " + c);
        }
    }
    
    @Override
    public void RefreshErrorEventOccurred(final String errorMessage) {
        if (errorMessage != null) {
            this.footerLabel.setText(errorMessage);
        }
    }
    
    @Override
    public void RefreshExprTreeModelEventOccurred(final ExpressionTreeModel expressionTreeModel) {
        this.expressionTree.setModel(expressionTreeModel);
    }
    
    @Override
    public void RefreshInfoEventOccurred(final String mess) {
        this.postfixExpressionTextField.setText(mess);
    }
    
    @Override
    public void RefreshDisplayEventOccurred(final String mess) {
        this.valueTextField.setText(mess);
    }
    
    @Override
    public void RefreshExpressionEventOccurred(final String expr) {
        this.expressionTextField.setText(expr);
    }
    
    @Override
    public void buttonPressed(final CalculatorButtonPressEvent calculatorButtonPressEvent) {
        final char c = ((CalculatorButton)calculatorButtonPressEvent.getSource()).getOperationCode();
        if (c == '\uffff') {
            this.calculatorObject.Clear();
        }
        else {
            this.calculatorObject.Hit(c, false);
        }
    }
    
    public static void main(final String[] args) {
        final CalculatorObject calcObj = new CalculatorObject();
        final CalcGUI calcGUI = new CalcGUI(calcObj);
        calcObj.addRefreshDisplayEventListener(calcGUI);
        calcObj.addRefreshExpressionEventListener(calcGUI);
        calcObj.addRefreshInfoEventListener(calcGUI);
        calcObj.addRefreshExprTreeModelEventListener(calcGUI);
        calcObj.addRefreshErrorEventListener(calcGUI);
        final JFrame mainFrame = new JFrame();
        mainFrame.getContentPane().add(calcGUI);
        calcObj.ClearExpressionTree();
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(3);
        mainFrame.pack();
    }
}
