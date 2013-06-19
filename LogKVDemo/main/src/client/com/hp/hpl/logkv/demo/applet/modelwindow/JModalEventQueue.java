package com.hp.hpl.logkv.demo.applet.modelwindow;


import java.awt.*;
import java.util.*;

import javax.swing.*;


/**
 * <p>Custom EventQueue which enables halting the calling thread in the same way as the JDialog does. </p>
 * <p>Project: JModalWindow - window-specific modality. </p>
 * <p>Copyright: Copyright (c) 2001-2006. </p>
 *
 * <p>This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version. </p>
 *
 * <p>This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details. </p>
 *
 * <p>You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA </p>
 *
 * @author Jene Jasper
 * @version 2.0
 */
public class JModalEventQueue extends EventQueue
{
  /**
   * Constructor JModalWindow custom EventQueue.
   */
  public JModalEventQueue()
  {
    super();
  }


  /**
   * <p>Replaces the existing EventQueue with the specified one.
   * Any pending events are transferred to the new EventQueue for processing by it. </p>
   *
   * @param newEventQueue EventQueue
   */
  public synchronized void push(EventQueue newEventQueue)
  {
    super.push(newEventQueue);
  }


  /**
   * <p>Stops dispatching events using this EventQueue.
   * Any pending events are transferred to the previous EventQueue for processing. </p>
   *
   * <p>Warning: To avoid deadlock, do not declare this method synchronized in a subclass. </p>
   *
   * @throws EmptyStackException
   */
  public void pop() throws EmptyStackException
  {
    super.pop();
  }


  /**
   * <p>Dispatch the next event on the queue through the normally protected {@link #dispatchEvent(AWTEvent) dispatchEvent} method. </p>
   */
  public void dispatchNextEventOnQueue()
  {
    try
    {
      dispatchEvent(getNextEvent());
    }
    catch (InterruptedException ie)
    {
      ie.printStackTrace(System.out);
    }
  }


  /**
   * <p>Dispatches an event.
   * The manner in which the event is dispatched depends upon the type of the event and the type of the event's source object. </p>
   *
   * @param event AWTEvent
   */
  protected void dispatchEvent(AWTEvent event)
  {
    super.dispatchEvent(event);
  }


  /**
   * <p>Pump events until specified window is closed. And thus simulate halting even the event dispatcher thread. </p>
   *
   * @param window Window
   */
  final public void waitForClose(Window window)
  {
    while (window.isVisible() && window.isDisplayable())
    {
      dispatchNextEventOnQueue();
    }
  }


  /**
   * <p>Pump events until specified internal frame is closed. And thus simulate halting even the event dispatcher thread. </p>
   *
   * @param iframe JInternalFrame
   */
  final public void waitForClose(JInternalFrame iframe)
  {
    while (iframe.isVisible() && iframe.isDisplayable() && !iframe.isClosed())
    {
      dispatchNextEventOnQueue();
    }
  }
}
