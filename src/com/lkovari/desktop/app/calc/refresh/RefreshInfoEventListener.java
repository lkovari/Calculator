/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.refresh
 * File: RefreshInfoEventListener.java
 * Created: Mar 6, 2005 6:47:52 PM
 * Author: lkovari 
 * 
 * Description:
 * 
 * 
 */
package com.lkovari.desktop.app.calc.refresh;

import java.util.EventListener;

public interface RefreshInfoEventListener extends EventListener
{
    void RefreshInfoEventOccurred(final String p0);
}
