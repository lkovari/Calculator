/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.gui.kinds
 * File: ButtonType.java
 * Created: Mar 18, 2005 12:56:34 AM
 * Author: lkovari 
 * 
 * Description:
 * 
 * 
 */
package com.lkovari.desktop.app.calc.gui.kinds;

public enum ButtonType
{
    NUMERIC("NUMERIC", 0), 
    ALFANUMERIC("ALFANUMERIC", 1), 
    OPERATOR("OPERATOR", 2), 
    MODIFIER("MODIFIER", 3), 
    FUNCTION("FUNCTION", 4);
    
    private ButtonType(final String name, final int ordinal) {
    }
}
