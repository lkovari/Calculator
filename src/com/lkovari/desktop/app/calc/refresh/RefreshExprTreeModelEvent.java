/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.refres
 * File: RefreshExprTreeModelEvent.java
 * Created: Mar 15, 2005 8:47:16 PM
 * Author: lkovari 
 * 
 * Description:
 *  Refresh the Expression tree model
 * 
 */
package com.lkovari.desktop.app.calc.refresh;

import java.util.EventObject;

public class RefreshExprTreeModelEvent extends EventObject
{
    private static final long serialVersionUID = 1L;
    
    public RefreshExprTreeModelEvent(final Object source) {
        super(source);
    }
}
