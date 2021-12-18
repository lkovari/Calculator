// 
// Decompiled by Procyon v0.5.36
// 

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
