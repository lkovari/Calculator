/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.gui
 * File: ButtonsManager.java
 * Created: Jan 16, 2006 11:23:38 AM
 * Author: lkovari 
 * 
 * Description:
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 * 
 */
package com.lkovari.desktop.app.calc.gui;

import java.awt.Component;
import com.lkovari.desktop.app.calc.gui.kinds.ButtonType;
import com.lkovari.desktop.app.calc.gui.calcbutton.CalculatorButton;
import java.awt.Container;

public class ButtonsManager
{
    private Container buttons;
    
    public void activateButtonsByNumericalSystems(final NumericalSystem numericalSystem) {
        for (int l = 0; l < this.buttons.getComponentCount(); ++l) {
            final Component c = this.buttons.getComponent(l);
            if (c instanceof CalculatorButton) {
                final CalculatorButton calculatorButton = (CalculatorButton)c;
                final ButtonType buttonType = calculatorButton.getButtonType();
                if (buttonType == null) {
                    System.err.println("ButtonType is NULL!");
                }
                if (buttonType.equals(ButtonType.NUMERIC) || buttonType.equals(ButtonType.ALFANUMERIC)) {
                    if (calculatorButton.containNumericalSystem(numericalSystem)) {
                        calculatorButton.setEnabled(true);
                    }
                    else {
                        calculatorButton.setEnabled(false);
                    }
                }
            }
        }
    }
    
    public ButtonsManager(final Container container) {
        this.buttons = null;
        this.buttons = container;
    }
}
