/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.expressiontreeparser
 * File: OperatorType.java
 * Created: Mar 18, 2005 12:56:34 AM
 * Author: lkovari 
 * 
 * Description:
 * 
 * 
 */
package com.lkovari.desktop.app.calc.expressiontreeparser;

/**
 * @author lkovari
 * OperatorType 
 */
public enum OperatorType
{
    UNARY("UNARY", 0, 1), 
    BINARY("BINARY", 1, 2), 
    TERNARY("TERNARY", 2, 3);
    
    private final int value;
    
    /**
     * 
     * Constructor OperatorKind
     * @param value value of enum
     */    
    private OperatorType(final String name, final int ordinal, final int value) {
        this.value = value;
    }
    
    /**
     * Method: getText 
     * @return type in text format
     */    
    public String getText() {
        String res = "";
        switch (this) {
            case UNARY: {
                res = "Unary";
                break;
            }
            case BINARY: {
                res = "Binary";
                break;
            }
            case TERNARY: {
                res = "Binary";
                break;
            }
        }
        return res;
    }
}
