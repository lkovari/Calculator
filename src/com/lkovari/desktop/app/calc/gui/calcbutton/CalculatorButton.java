/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.expressiontreeparser
 * File: CalculatorButton.java
 * Created: Jan 14, 2006 10:25:38 AM
 * Author: lkovari 
 * 
 * Description:
 * 	Build an expression tree from a postfix expression	
 *
 */
package com.lkovari.desktop.app.calc.gui.calcbutton;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import com.lkovari.desktop.app.calc.gui.calcbutton.events.CalcButtonListener;
import com.lkovari.desktop.app.calc.gui.calcbutton.events.CalculatorButtonPressEvent;
import java.awt.event.MouseListener;
import com.lkovari.desktop.app.calc.gui.NumericalSystem;
import java.util.HashSet;
import com.lkovari.desktop.app.calc.gui.kinds.ButtonType;
import javax.swing.event.EventListenerList;
import com.lkovari.desktop.app.calc.gui.kinds.ButtonId;
import java.awt.Color;
import javax.swing.JLabel;

public class CalculatorButton extends JLabel
{
	private static final long serialVersionUID = 1L;
	private Color selectionColor;
    private boolean selected;
    private ButtonId buttonId;
    private char operationCode;
    protected EventListenerList listenerList;
    private ButtonType buttonType;
    private HashSet<NumericalSystem> relatedNumericalSystems;
    private MouseListener mouseListener;
    
    private void fireButtonPressed(final CalculatorButtonPressEvent e) {
        synchronized (this) {
        	// Guaranteed to return a non-null array        	
            final Object[] listeners = this.listenerList.getListenerList();
            // Process the listeners last to first, notifying
            // those that are interested in this event            
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == CalcButtonListener.class) {
                    ((CalcButtonListener)listeners[i + 1]).buttonPressed(e);
                }
            }
        }
    }
    
    public synchronized void addButtonPressListener(final CalcButtonListener l) {
        this.listenerList.add(CalcButtonListener.class, l);
    }
    
    public synchronized void removeButtonPressListener(final CalcButtonListener l) {
        this.listenerList.remove(CalcButtonListener.class, l);
    }
    
    private void fireCalculatorButtonPressed() {
        final CalculatorButtonPressEvent calculatorButtonPressEvent = new CalculatorButtonPressEvent(this);
        this.fireButtonPressed(calculatorButtonPressEvent);
    }
    
    public void addNumericalSystem(final NumericalSystem numericalSystems) {
        this.relatedNumericalSystems.add(numericalSystems);
    }
    
    public boolean containNumericalSystem(final NumericalSystem numericalSystem) {
        return this.relatedNumericalSystems.contains(numericalSystem);
    }
    
    public ButtonType getButtonType() {
        return this.buttonType;
    }
    
    public void setButtonType(final ButtonType buttonType) {
        this.buttonType = buttonType;
    }
    
    public boolean isSelected() {
        return this.selected;
    }
    
    public void setSelected(final boolean selected) {
        this.selected = selected;
    }
    
    public char getOperationCode() {
        return this.operationCode;
    }
    
    public void setOperationCode(final char opcode) {
        this.operationCode = opcode;
    }
    
    public ButtonId getButtonId() {
        return this.buttonId;
    }
    
    public void setButtonId(final ButtonId buttonId) {
        this.buttonId = buttonId;
    }
    
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Color backupColor = g.getColor();
        final int x = g.getClipBounds().x;
        final int y = g.getClipBounds().y;
        final int w = g.getClipBounds().width - 1;
        final int h = g.getClipBounds().height - 1;
        if (this.getText() != " ") {
            g.setColor(Color.lightGray);
            g.drawRect(x, y, w, h);
        }
        if (this.selected) {
            g.setColor(this.selectionColor);
            g.drawRect(x, y, w, h);
        }
        else {
            g.setColor(backupColor);
        }
    }
    
    public CalculatorButton(final String text) {
        super(text);
        this.selectionColor = Color.blue;
        this.selected = false;
        this.buttonId = null;
        this.operationCode = '\0';
        this.listenerList = new EventListenerList();
        this.buttonType = null;
        this.relatedNumericalSystems = new HashSet<NumericalSystem>();
        this.mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final Object src = e.getSource();
                if (src instanceof CalculatorButton) {
                    final CalculatorButton calcButton = (CalculatorButton)src;
                    CalculatorButton.this.fireCalculatorButtonPressed();
                    System.out.println("Clicked " + calcButton.getButtonId());
                }
            }
            
            @Override
            public void mousePressed(final MouseEvent e) {
                final Object src = e.getSource();
                if (src instanceof CalculatorButton) {
                    final CalculatorButton calcButton = (CalculatorButton)src;
                    if (calcButton.getText() != " ") {
                        calcButton.setForeground(CalculatorButton.this.selectionColor);
                    }
                }
            }
            
            @Override
            public void mouseReleased(final MouseEvent e) {
                final Object src = e.getSource();
                if (src instanceof CalculatorButton) {
                    final CalculatorButton calcButton = (CalculatorButton)src;
                    if (calcButton.getText() != " ") {
                        calcButton.setForeground(Color.black);
                    }
                }
            }
            
            @Override
            public void mouseEntered(final MouseEvent e) {
                final Object src = e.getSource();
                if (src instanceof CalculatorButton) {
                    final CalculatorButton calcButton = (CalculatorButton)src;
                    if (calcButton.isEnabled() && !calcButton.getText().endsWith(" ")) {
                        calcButton.setCursor(Cursor.getPredefinedCursor(12));
                        calcButton.setSelected(true);
                        calcButton.repaint();
                    }
                }
            }
            
            @Override
            public void mouseExited(final MouseEvent e) {
                final Object src = e.getSource();
                if (src instanceof CalculatorButton) {
                    final CalculatorButton calcButton = (CalculatorButton)src;
                    if (calcButton.isEnabled() && !calcButton.getText().endsWith(" ")) {
                        calcButton.setCursor(Cursor.getPredefinedCursor(0));
                        calcButton.setSelected(false);
                        calcButton.repaint();
                    }
                }
            }
        };
        this.setVerticalAlignment(0);
        this.setHorizontalAlignment(0);
        this.setOpaque(true);
        this.addMouseListener(this.mouseListener);
    }
    
    public void resizeLabel() {
    	
	}

    @Override
    protected void finalize() throws Throwable {
        this.relatedNumericalSystems.clear();
        this.relatedNumericalSystems = null;
    }
}
