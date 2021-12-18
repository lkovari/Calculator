/**
 * 
 * Copyright 2005 by L�szl� K�v�ri
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.refresh
 * File: RefreshExpressionEvent.java
 * Created: Sep 07, 2004 11:25:38 AM
 * Author: lkovari 
 * 
 * Description:
 *  Display event's listener 
 *
 */
package com.lkovari.desktop.app.calc.refresh;

import java.util.EventListener;

public interface RefreshDisplayEventListener extends EventListener
{
    void RefreshDisplayEventOccurred(final String p0);
}
