/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.refresh
 * File: RefreshDisplayEvent.java
 * Created: Sep 07, 2004 11:25:38 AM
 * Author: lkovari 
 * 
 * Description:
 *  Refresh a display event
 *
 */
package com.lkovari.desktop.app.calc.refresh;

import java.util.EventObject;

/**
 * 
 * @author lkovari
 * RefreshDisplayEvent
 */
public class RefreshDisplayEvent extends EventObject
{
    private static final long serialVersionUID = 1L;
    
    public RefreshDisplayEvent(final Object source) {
        super(source);
    }
}
