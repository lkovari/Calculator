/**
 * 
 * Copyright 2005 by L�szl� K�v�ri
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.refresh
 * File: RefreshExpressionEvent.java
 * Created: Marc 02, 2005 11:25:38 AM
 * Author: lkovari 
 * 
 * Description:
 *  Refresh the arithmetic expression 
 *
 */
package com.lkovari.desktop.app.calc.refresh;

import java.util.EventObject;

public class RefreshExpressionEvent extends EventObject
{
    private static final long serialVersionUID = 1L;
    
    public RefreshExpressionEvent(final Object source) {
        super(source);
    }
}
