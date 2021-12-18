/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.refresh
 * File: RefreshErrorEventListener.java
 * Created: Marc 21, 2005 11:25:38 AM
 * Author: lkovari 
 * 
 * Description:
 *  Refresh an error event
 *
 */
package com.lkovari.desktop.app.calc.refresh;

import java.util.EventListener;

public interface RefreshErrorEventListener extends EventListener
{
    void RefreshErrorEventOccurred(final String p0);
}
