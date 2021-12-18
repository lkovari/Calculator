/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.refresh
 * File: RefreshErrorEvent.java
 * Created: Marc 21, 2005 11:25:38 AM
 * Author: lkovari 
 * 
 * Description:
 *  Refresh an error event
 *
 */
package com.lkovari.desktop.app.calc.refresh;

import java.util.EventObject;

public class RefreshErrorEvent extends EventObject
{
    private static final long serialVersionUID = 1L;
    
    public RefreshErrorEvent(final Object source) {
        super(source);
    }
}
