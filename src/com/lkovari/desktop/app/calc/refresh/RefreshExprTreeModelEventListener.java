/**
 * 
 * Copyright 2005 by László Kövári
 * laszlo.kovary@gmail.com
 * 
 * Project: Calculator
 * Package: com.lkovari.desktop.app.calc.refresh
 * File: RefreshExprTreeModelEventListener.java
 * Created: Mar 15, 2005 8:49:01 PM
 * Author: lkovari 
 * 
 * Description:
 *  Refresh the expression tree model
 * 
 */
package com.lkovari.desktop.app.calc.refresh;

import com.lkovari.desktop.app.calc.expressiontreemodel.ExpressionTreeModel;
import java.util.EventListener;

public interface RefreshExprTreeModelEventListener extends EventListener
{
    void RefreshExprTreeModelEventOccurred(final ExpressionTreeModel p0);
}
