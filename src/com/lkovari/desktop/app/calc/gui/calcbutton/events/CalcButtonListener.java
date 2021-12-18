/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.gui.calcbutton.events
 * File: CalcButtonListener.java
 * Created: Jan 18, 2006 10:25:38 AM
 * Author: lkovari 
 * 
 * Description:
 * 		
 *
 */
package com.lkovari.desktop.app.calc.gui.calcbutton.events;

import java.util.EventListener;

public interface CalcButtonListener extends EventListener
{
    void buttonPressed(final CalculatorButtonPressEvent p0);
}
