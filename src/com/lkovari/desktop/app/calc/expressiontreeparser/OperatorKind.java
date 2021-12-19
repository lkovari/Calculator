/**
 *
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 *
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.expressiontreeparser
 * File: OperatorKind.java
 * Created: Marc 02, 2005 11:25:38 AM
 * Author: lkovari
 *
 * Description:
 *  Build an expression tree from a postfix expression
 *
 */
package com.lkovari.desktop.app.calc.expressiontreeparser;

/**
 * OperatorKind
 * @author lkovari
 * Description:
 *
 */
public enum OperatorKind
{
    ERR("ERR", 0, -1), 
    NOP("NOP", 1, 0), 
    LPAR("LPAR", 2, 40), 
    RPAR("RPAR", 3, 41), 
    ADD("ADD", 4, 71), 
    SUB("SUB", 5, 72), 
    MUL("MUL", 6, 73), 
    DIV("DIV", 7, 74), 
    MOD("MOD", 8, 75), 
    AND("AND", 9, 76), 
    OR("OR", 10, 77), 
    NOT("NOT", 11, 78), 
    XOR("XOR", 12, 79), 
    NEG("NEG", 13, 80), 
    POW("POW", 14, 81), 
    SQRT("SQRT", 15, 82), 
    REC("REC", 16, 83);
    
    @SuppressWarnings("unused")
	private final int value;
    
    private OperatorKind(final String name, final int ordinal, final int value) {
        this.value = value;
    }
    
    public String getText() {
        String res = "";
        switch (this) {
            case ERR: {
                res = " ERR ";
                break;
            }
            case NOP: {
                res = " NOP ";
                break;
            }
            case LPAR: {
                res = " ( ";
                break;
            }
            case RPAR: {
                res = " ) ";
                break;
            }
            case ADD: {
                res = " + ";
                break;
            }
            case SUB: {
                res = " - ";
                break;
            }
            case MUL: {
                res = " * ";
                break;
            }
            case DIV: {
                res = " / ";
                break;
            }
            case MOD: {
                res = " % ";
                break;
            }
            case NEG: {
                res = "NEG ";
                break;
            }
            case POW: {
                res = " Pow ";
                break;
            }
            case AND: {
                res = " and ";
                break;
            }
            case OR: {
                res = " or ";
                break;
            }
            case NOT: {
                res = "not ";
                break;
            }
            case XOR: {
                res = " xor ";
                break;
            }
            case SQRT: {
                res = "Sqr ";
                break;
            }
            case REC: {
                res = "1/";
                break;
            }
        }
        return res;
    }
    
    /**
     * Method: getPrecedence
     * @return the precedence of an operator
     *
     * origin of precedence order:
     *
     *  http://cs.stmarys.ca/~porter/csc/ref/java_operators.html
     *  http://java.about.com/od/beginningjava/l/aa_operator_1.htm
     *  http://www.csc.calpoly.edu/~csturner/courses/101/Hints/operator_precedence.html
     *  http://www.particle.kth.se/~lindsey/JavaCourse/Book/Part1/Java/Chapter02/operators.html#Precedence
     *  http://www.uni-bonn.de/~manfear/javaoperators.php
     *  http://www.csc.calpoly.edu/~csc101/Studynotes/JavaOperators.htm
     */    
    public int getPrecedence() {
        int res = -1;
        switch (this) {
            case ERR: {
                res = -1;
                break;
            }
            case NOP: {
                res = -1;
                break;
            }
            case LPAR: {
                res = -1;
                break;
            }
            case RPAR: {
                res = -1;
                break;
            }
            case ADD: {
                res = 11;
                break;
            }
            case SUB: {
                res = 11;
                break;
            }
            case MUL: {
                res = 12;
                break;
            }
            case DIV: {
                res = 12;
                break;
            }
            case MOD: {
                res = 12;
                break;
            }
            case NEG: {
                res = 14;
                break;
            }
            case POW: {
                res = 99;
                break;
            }
            case AND: {
                res = 7;
                break;
            }
            case OR: {
                res = 5;
                break;
            }
            case NOT: {
                res = 14;
                break;
            }
            case XOR: {
                res = 6;
                break;
            }
            case SQRT: {
                res = 99;
                break;
            }
            case REC: {
                res = 99;
                break;
            }
        }
        return res;
    }
    
    /**
     * Method: getOperatorType
     * @return the type of operator
     */    
    public OperatorType getOperatorType() {
        OperatorType res = OperatorType.UNARY;
        switch (this) {
            case NOP: {
                res = OperatorType.UNARY;
                break;
            }
            case ADD: {
                res = OperatorType.BINARY;
                break;
            }
            case SUB: {
                res = OperatorType.BINARY;
                break;
            }
            case MUL: {
                res = OperatorType.BINARY;
                break;
            }
            case DIV: {
                res = OperatorType.BINARY;
                break;
            }
            case MOD: {
                res = OperatorType.BINARY;
                break;
            }
            case NEG: {
                res = OperatorType.UNARY;
                break;
            }
            case POW: {
                res = OperatorType.BINARY;
                break;
            }
            case AND: {
                res = OperatorType.BINARY;
                break;
            }
            case OR: {
                res = OperatorType.BINARY;
                break;
            }
            case NOT: {
                res = OperatorType.UNARY;
                break;
            }
            case XOR: {
                res = OperatorType.BINARY;
                break;
            }
            case SQRT: {
                res = OperatorType.UNARY;
                break;
            }
            case REC: {
                res = OperatorType.UNARY;
                break;
            }
		default:
			break;
        }
        return res;
    }
    
    /**
     * Method: getOperatorKind
     * @param c character
     * @return OperatorKind
     */    
    public static OperatorKind getOperatorKind(final char c) {
        OperatorKind res = OperatorKind.NOP;
        switch (c) {
            case '(': {
                res = OperatorKind.LPAR;
                break;
            }
            case ')': {
                res = OperatorKind.RPAR;
                break;
            }
            case 'G': {
                res = OperatorKind.ADD;
                break;
            }
            case 'H': {
                res = OperatorKind.SUB;
                break;
            }
            case 'I': {
                res = OperatorKind.MUL;
                break;
            }
            case 'J': {
                res = OperatorKind.DIV;
                break;
            }
            case 'K': {
                res = OperatorKind.MOD;
                break;
            }
            case 'L': {
                res = OperatorKind.AND;
                break;
            }
            case 'M': {
                res = OperatorKind.OR;
                break;
            }
            case 'N': {
                res = OperatorKind.NOT;
                break;
            }
            case 'O': {
                res = OperatorKind.XOR;
                break;
            }
            case 'P': {
                res = OperatorKind.NEG;
                break;
            }
            case 'Q': {
                res = OperatorKind.POW;
                break;
            }
            case 'R': {
                res = OperatorKind.SQRT;
                break;
            }
            case 'S': {
                res = OperatorKind.REC;
                break;
            }
            default: {
                res = OperatorKind.ERR;
                break;
            }
        }
        return res;
    }
    
    /**
    *
    * Method: getOperatorToken
    * @param c real operator character
    * @return internal operator token -> the ordinal value of OperatorKind
    */    
    public static char getOperatorToken(final char c) {
        char res = '\0';
        switch (c) {
            case '(': {
                res = '(';
                break;
            }
            case ')': {
                res = ')';
                break;
            }
            case '+': {
                res = 'G';
                break;
            }
            case '-': {
                res = 'H';
                break;
            }
            case '*': {
                res = 'I';
                break;
            }
            case '/': {
                res = 'J';
                break;
            }
            case '%': {
                res = 'K';
                break;
            }
            case '&': {
                res = 'L';
                break;
            }
            case '!': {
                res = 'M';
                break;
            }
            case '~': {
                res = 'P';
                break;
            }
            case '^': {
                res = 'Q';
                break;
            }
        }
        return res;
    }
}
