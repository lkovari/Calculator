/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.refresh
 * File: RefreshInfoEvent.java
 * Created: Mar 6, 2005 6:44:57 PM
 * Author: lkovari 
 * 
 * Description:
 *  refresh a postfix expression and the tree
 * 
 */
package com.lkovari.desktop.app.calc.refresh;

import java.util.EventObject;

public class RefreshInfoEvent extends EventObject
{
    private static final long serialVersionUID = 1L;
    
    public RefreshInfoEvent(final Object source) {
        super(source);
    }
}
