/**
 * 
 * Copyright 2005-2007 by László Kövári
 * laszlo.kovari@mail.tvnet.hu
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.gui
 * File: NumericalSystem.java
 * Created: Jun 17, 2007 8:10:05 PM
 * Author: llkovari 
 * 
 * Description:
 * 
 * 
 */
package com.lkovari.desktop.app.calc.gui;

public enum NumericalSystem
{
    BINARY("BINARY", 0, 2), 
    OCTAL("OCTAL", 1, 8), 
    DECIMAL("DECIMAL", 2, 10), 
    HEXADECIMAL("HEXADECIMAL", 3, 16);
    
    private final int value;
    
    private NumericalSystem(final String name, final int ordinal, final int value) {
        this.value = value;
    }
}
