/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.gui.kinds
 * File: ButtonId.java
 * Created: Jan 14, 2006 091:06:38 PM
 * Author: lkovari 
 * 
 * Description:
 * 	
 *
 */
package com.lkovari.desktop.app.calc.gui.kinds;

public enum ButtonId
{
    EMPTY("EMPTY", 0, " "), 
    NUM0("NUM0", 1, "0"), 
    NUM1("NUM1", 2, "1"), 
    NUM2("NUM2", 3, "2"), 
    NUM3("NUM3", 4, "3"), 
    NUM4("NUM4", 5, "4"), 
    NUM5("NUM5", 6, "5"), 
    NUM6("NUM6", 7, "6"), 
    NUM7("NUM7", 8, "7"), 
    NUM8("NUM8", 9, "8"), 
    NUM9("NUM9", 10, "9"), 
    ADD("ADD", 11, "+"), 
    SUB("SUB", 12, "-"), 
    MUL("MUL", 13, "*"), 
    DIV("DIV", 14, "/"), 
    EQU("EQU", 15, "="), 
    SIGN("SIGN", 16, "+/-"), 
    CLR("CLR", 17, "Clr"), 
    MEMA("MEMA", 18, "M+"), 
    MEMS("MEMS", 19, "M-"), 
    MEMR("MEMR", 20, "Mr"), 
    MEMC("MEMC", 21, "Mc"), 
    DOT("DOT", 22, "."), 
    MOD("MOD", 23, "Mod"), 
    XOR("XOR", 24, "Xor"), 
    AND("AND", 25, "And"), 
    NOT("NOT", 26, "Not"), 
    LSH("LSH", 27, "Lsh"), 
    OR("OR", 28, "Or"), 
    LN("LN", 29, "Ln"), 
    LOG("LOG", 30, "Log"), 
    SQRT("SQRT", 31, "Sqrt"), 
    PI("PI", 32, "Pi"), 
    FACT("FACT", 33, "n!"), 
    SIN("SIN", 34, "Sin"), 
    TAN("TAN", 35, "Tan"), 
    COS("COS", 36, "Cos"), 
    REC("REC", 37, "1/x"), 
    POW("POW", 38, "x^y"), 
    POW2("POW2", 39, "x^2"), 
    POW3("POW3", 40, "x^3"), 
    EXP("EXP", 41, "Exp"), 
    PARL("PARL", 42, "("), 
    PARR("PARR", 43, ")"), 
    NUMA("NUMA", 44, "A"), 
    NUMB("NUMB", 45, "B"), 
    NUMC("NUMC", 46, "C"), 
    NUMD("NUMD", 47, "D"), 
    NUME("NUME", 48, "E"), 
    NUMF("NUMF", 49, "F");
    
    private final String value;
    
    public String getValue() {
        return this.value;
    }
    
    private ButtonId(final String name, final int ordinal, final String value) {
        this.value = value;
    }
}
