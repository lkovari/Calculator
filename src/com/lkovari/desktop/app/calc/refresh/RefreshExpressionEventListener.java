/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.refresh
 * File: RefreshExpressionEventListener.java
 * Created: Marc 02, 2005 11:25:38 AM
 * Author: lkovari 
 * 
 * Description:
 *  Expression event's listener  
 *
 */
package com.lkovari.desktop.app.calc.refresh;

import java.util.EventListener;

public interface RefreshExpressionEventListener extends EventListener
{
    void RefreshExpressionEventOccurred(final String p0);
}
