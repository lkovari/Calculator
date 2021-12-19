/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.expressiontreeparser
 * File: Operations.java
 * Created: Marc 02, 2005 11:25:38 AM
 * Author: lkovari 
 * 
 * Description:
 *  Build an expression tree from a postfix expression  
 *
 */
package com.lkovari.desktop.app.calc.expressiontreeparser;

/**
 * @author lkovari
 * Date 2004.08.24.
 * 
 * Description	This class contain the arithmetic & other operations for 
 * 				calculator class
 */
public final class Operations
{
    /**
     * Binary 
     * Method: ExecuteOperation 
     * @param opr1 operant 1st
     * @param opr2 operand 2nd (if available)
     * @param kind
     * @return result of operation
     */	
    private double ExecuteOperation(final double opr1, final double opr2, final OperatorKind kind) {
        double res = -1.0;
        switch (kind) {
            case ADD: {
                res = opr1 + opr2;
                System.out.println("+ executed");
                break;
            }
            case SUB: {
                res = opr1 - opr2;
                System.out.println("- executed");
                break;
            }
            case MUL: {
                res = opr1 * opr2;
                System.out.println("* executed");
                break;
            }
            case DIV: {
                res = opr1 / opr2;
                System.out.println("/ executed");
                break;
            }
            case MOD: {
                res = opr1 % opr2;
                System.out.println("% executed");
                break;
            }
            case POW: {
                res = Math.pow(opr1, opr2);
                System.out.println("Pow executed");
                break;
            }
            case AND: {
                int o1 = (int)opr1;
                final int o2 = (int)opr2;
                o1 &= o2;
                res = o1;
                System.out.println("&= executed");
                break;
            }
            case OR: {
                int o1 = (int)opr1;
                final int o2 = (int)opr2;
                o1 |= o2;
                res = o1;
                System.out.println("|= executed");
                break;
            }
            case XOR: {
                final int o1 = (int)opr1;
                final int o2 = (int)opr2;
                res = (o1 ^ o2);
                System.out.println("^ executed");
                break;
            }
		default:
			break;
        }
        return res;
    }
    
    /**
     * Unary
     * Method: ExecuteOperation 
     * @param opr1 operand 1st
     * @param kind kind of operation
     * @return result of operation
     */    
    private double ExecuteOperation(final double opr1, final OperatorKind kind) {
        double res = -1.0;
        switch (kind) {
            case NEG: {
                res = opr1 * -1.0;
                System.out.println("*-1 executed");
                break;
            }
            case NOT: {
                final int o1 = (int)opr1;
                res = ~o1;
                System.out.println("~ executed");
                break;
            }
            case SQRT: {
                final int o1 = (int)opr1;
                res = Math.sqrt(o1);
                System.out.println("# executed");
                break;
            }
            case REC: {
                res = 1.0 / opr1;
                System.out.println("1/x executed");
                break;
            }
		default:
			break;
        }
        return res;
    }
    
    /**
     * Binary
     * Method: Execute 
     * @param opr1 operand 1st
     * @param opr2 operand 2nd
     * @param kind kind of operator
     * @return value of operation result
     */    
    public static double Execute(final double opr1, final double opr2, final OperatorKind kind) {
        double res = -1.0;
        Operations opr3 = null;
        try {
            opr3 = new Operations();
            res = opr3.ExecuteOperation(opr1, opr2, kind);
        }
        finally {
            opr3 = null;
        }
        opr3 = null;
        return res;
    }
    
    /**
     * Unary
     * Method: Execute 
     * @param opr1 operand 1st
     * @param kind kind of operation
     * @return the result of operation
     */    
    public static double Execute(final double opr1, final OperatorKind kind) {
        double res = -1.0;
        Operations opr2 = null;
        try {
            opr2 = new Operations();
            res = opr2.ExecuteOperation(opr1, kind);
        }
        finally {
            opr2 = null;
        }
        opr2 = null;
        return res;
    }
}
