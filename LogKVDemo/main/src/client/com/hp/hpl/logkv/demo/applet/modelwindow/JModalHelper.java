package com.hp.hpl.logkv.demo.applet.modelwindow;


import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;

import javax.swing.*;



/**
 * <p>Helper class created to reduce code duplication. </p>
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
 * <hr>
 *
 * <p><b>JModalHelper</b> support class to handle activating, iconfying and/or
 * deiconfying of modal windows and their blocked ancestors and blocking children. </p>
 *
 * @author Jene Jasper
 * @version 2.0
 */
class JModalHelper implements PropertyChangeListener
{
  final private static String FOCUSED_WINDOW_PROPERTY = "focusedWindow";
  final private static String PERMANENT_FOCUS_OWNER_PROPERTY = "permanentFocusOwner";

  final protected static JModalHelper jmodalHelper = new JModalHelper();

  private Window flagActivateWindow;
  private JInternalFrame flagActivateInternalFrame;
  private Window activateTriggerFrame;
  private Window ignoreTriggerFrame;

  private JModalHelper()
  {
    setDelayedActivation(null, null, null);

    KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener(FOCUSED_WINDOW_PROPERTY, this);
    KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener(PERMANENT_FOCUS_OWNER_PROPERTY, this);
  }


  /**
   * <p>Block all supplied windows and inputblockers by the blocking internal frame. </p>
   *
   * @param modalToWindowsAndInputBlockers Collection Set of windows and inputblockers that should be blocked.
   * @param iframe JModalInternalFrame Internal frame that issues the blocking action.
   */
  protected static void blockWindows(Collection modalToWindowsAndInputBlockers, JModalInternalFrame iframe)
  {
    Iterator iterator = modalToWindowsAndInputBlockers.iterator();

    while (iterator.hasNext())
    {
      blockWindow(iterator.next(), iframe);
    }
  }


  /**
   * <p>Block the window by setting it to busy = true if it implements InputBlocker.
   * Otherwise disable the window. </p>
   *
   * @param windowOrInputBlocker Object Window or InputBlocker that should be blocked.
   * @param iframe JModalInternalFrame Internal frame that issues the blocking action.
   */
  private static void blockWindow(Object windowOrInputBlocker, JModalInternalFrame iframe)
  {
    if (windowOrInputBlocker instanceof InputBlocker)
    {
      ((InputBlocker)windowOrInputBlocker).setBusy(true, iframe);
    }
    else
    {
      ((Window)windowOrInputBlocker).setEnabled(false);
    }
  }


  /**
   * <p>Block all supplied windows and inputblockers by the blocking window. </p>
   *
   * @param modalToWindowsAndInputBlockers Collection Set of windows and inputblockers that should be blocked.
   * @param blockingWindow Window Window that issues the blocking action.
   */
  protected static void blockWindows(Collection modalToWindowsAndInputBlockers, Window blockingWindow)
  {
    Iterator iterator = modalToWindowsAndInputBlockers.iterator();

    while (iterator.hasNext())
    {
      blockWindow(iterator.next(), blockingWindow);
    }
  }


  /**
   * <p>Block the window by setting it to busy = true if it implements InputBlocker.
   * Otherwise disable the window. </p>
   *
   * @param windowOrInputBlocker Object Window or InputBlocker that should be blocked.
   * @param blockingWindow Window Window that issues the blocking action.
   */
  private static void blockWindow(Object windowOrInputBlocker, Window blockingWindow)
  {
    if (windowOrInputBlocker instanceof InputBlocker)
    {
      ((InputBlocker)windowOrInputBlocker).setBusy(true, blockingWindow);
    }
    else
    {
      ((Window)windowOrInputBlocker).setEnabled(false);
    }
  }


  /**
   * <p>Unblock all supplied windows by the blocking internal frame. And return focus to the proper component. </p>
   *
   * @param modalToWindowsAndInputBlockers Collection Set of windows and inputblockers that should be unblocked.
   * @param iframe JModalInternalFrame Internal frame that issues the unblocking action.
   * @param returnFocus Component in owner which should regain focus after closing the blocking internal frame.
   */
  protected static void unblockWindows(Collection modalToWindowsAndInputBlockers, JModalInternalFrame iframe,
                                       Component returnFocus)
  {
    Iterator iterator = modalToWindowsAndInputBlockers.iterator();

    while (iterator.hasNext())
    {
      unblockWindow(iterator.next(), iframe);
    }

    returnFocusToComponent(modalToWindowsAndInputBlockers, returnFocus);
  }


  /**
   * <p>Unblock the window by setting it to busy = false if it implements InputBlocker.
   * Otherwise enable the window again. </p>
   *
   * <p><b>Note:</b> The last option doesn't work with mutiple blockingWindows. </p>
   *
   * @param windowOrInputBlocker Object Window or InputBlocker that should be blocked.
   * @param iframe JModalInternalFrame Internal frame that issues the unblocking action.
   */
  private static void unblockWindow(Object windowOrInputBlocker, JModalInternalFrame iframe)
  {
    if (windowOrInputBlocker instanceof InputBlocker)
    {
      ((InputBlocker)windowOrInputBlocker).setBusy(false, iframe);
    }
    else
    {
      ((Window)windowOrInputBlocker).setEnabled(true);
    }
  }


  /**
   * <p>Unblock all supplied windows by the blocking window. And return focus to the proper component. </p>
   *
   * @param modalToWindowsAndInputBlockers Collection Set of windows and inputblockers that should be unblocked.
   * @param blockingWindow Window Window that issues the unblocking action.
   * @param returnFocus Component in owner which should regain focus after closing the blocking window.
   */
  protected static void unblockWindows(Collection modalToWindowsAndInputBlockers, Window blockingWindow, Component returnFocus)
  {
    Iterator iterator = modalToWindowsAndInputBlockers.iterator();

    while (iterator.hasNext())
    {
      unblockWindow(iterator.next(), blockingWindow);
    }

    returnFocusToComponent(modalToWindowsAndInputBlockers, returnFocus);
  }


  /**
   * <p>Unblock the window by setting it to busy = false if it implements InputBlocker.
   * Otherwise enable the window again. </p>
   *
   * <p><b>Note:</b> The last option doesn't work with mutiple blockingWindows. </p>
   *
   * @param windowOrInputBlocker Object Window or InputBlocker that should be blocked.
   * @param blockingWindow Window Window that issues the blocking action.
   */
  private static void unblockWindow(Object windowOrInputBlocker, Window blockingWindow)
  {
    if (windowOrInputBlocker instanceof InputBlocker)
    {
      ((InputBlocker)windowOrInputBlocker).setBusy(false, blockingWindow);
    }
    else
    {
      ((Window)windowOrInputBlocker).setEnabled(true);
    }
  }


  /**
   * <p>Activate the window with the optional returnFocus component if its no longer blocked.
   * Otherwise activate the first window in the chain of blocking windows that isn't busy
   * for the list of modalToWindowsAndInputBlockers. </p>
   *
   * @param modalToWindowsAndInputBlockers Collection Set of windows and inputblockers that should be unblocked.
   * @param returnFocus Component in owner which should regain focus after closing the blocking window.
   */
  private static void returnFocusToComponent(Collection modalToWindowsAndInputBlockers, Component returnFocus)
  {
    if (returnFocus == null)
    {
      activateFirstAvailableBlockingWindow(modalToWindowsAndInputBlockers, null);
    }
    else
    {
      Window returnFocusWindow = Utils.windowForComponent(returnFocus);

      boolean stillBusy;

      if (returnFocusWindow == null)
      {
        stillBusy = true;
      }
      else if (returnFocusWindow instanceof InputBlocker)
      {
        stillBusy = ((InputBlocker)returnFocusWindow).isBusy();
      }
      else
      {
        stillBusy = !returnFocusWindow.isEnabled();
      }

      if (stillBusy)
      {
        activateFirstAvailableBlockingWindow(modalToWindowsAndInputBlockers, null);
      }
      else
      {
        JInternalFrame childIFrame = null;
        Component parent = Utils.parentForComponentRootPane(returnFocus);

        if (parent instanceof JInternalFrame)
        {
          childIFrame = (JInternalFrame)parent;
        }

        activate(returnFocusWindow, childIFrame, null);

        setDelayedReturnFocus(returnFocus);
      }
    }
  }


  /**
   * <p>Activate the first window in the chain of blocking windows that isn't busy. </p>
   *
   * @param blockingWindows Collection Set of blocking windows.
   * @param windowEvent WindowEvent optional window event that triggered this call.
   * @return boolean Indication if a window was activated.
   */
  protected static boolean activateFirstAvailableBlockingWindow(Collection blockingWindows, WindowEvent windowEvent)
  {
    Iterator iteratorDirectBlockingWindows = blockingWindows.iterator();

    while (iteratorDirectBlockingWindows.hasNext())
    {
      if (tryToActivate(iteratorDirectBlockingWindows.next(), windowEvent, false))
      {
        return true;
      }
    }

    Iterator iteratorIndirectBlockingWindows = blockingWindows.iterator();

    while (iteratorIndirectBlockingWindows.hasNext())
    {
      if (tryToActivate(iteratorIndirectBlockingWindows.next(), windowEvent, true))
      {
        return true;
      }
    }

    return false;
  }


  /**
   * <p>Activate the supplied window if it isn't marked as busy or disabled.
   * If the window couldn't be activated and it implements the InputBlocker
   * interface, and the option recursive is set to true,
   * try its blocking windows also before returning a failure indication. </p>
   *
   * @param windowOrInputBlocker Object
   * @param windowEvent WindowEvent optional window event that triggered this call.
   * @param recursive boolean In case of InputBlocker that isBusy activate recursively up the chain of blocking windows.
   * @return boolean Indication if window is activated.
   */
  private static boolean tryToActivate(Object windowOrInputBlocker, WindowEvent windowEvent, boolean recursive)
  {
    if (windowOrInputBlocker instanceof Window)
    {
      return tryToActivate((Window)windowOrInputBlocker, null, windowEvent, recursive);
    }
    else if ((windowOrInputBlocker instanceof InputBlocker) && ((InputBlocker)windowOrInputBlocker).isBusy())
    {
      return ((InputBlocker)windowOrInputBlocker).activateFirstAvailableBlockingWindow(windowEvent);
    }
    else if (windowOrInputBlocker instanceof Component)
    {
      Window window = Utils.windowForComponent((Component)windowOrInputBlocker);
      JInternalFrame childIFrame = null;

      if (windowOrInputBlocker instanceof JInternalFrame)
      {
        childIFrame = (JInternalFrame)windowOrInputBlocker;
      }

      return tryToActivate(window, childIFrame, windowEvent, recursive);
    }

    return false;
  }


  /**
   * <p>Activate the supplied window if it isn't marked as busy or disabled.
   * If the window couldn't be activated and it implements the InputBlocker
   * interface, and the option recursive is set to true,
   * try its blocking windows also before returning a failure indication. </p>
   *
   * @param window Window
   * @param childIFrame JInternalFrame
   * @param windowEvent WindowEvent optional window event that triggered this call.
   * @param recursive boolean In case of InputBlocker that isBusy activate recursively up the chain of blocking windows.
   * @return boolean Indication if window is activated.
   */
  private static boolean tryToActivate(Window window, JInternalFrame childIFrame, WindowEvent windowEvent, boolean recursive)
  {
    if (window instanceof InputBlocker)
    {
      InputBlocker inputBlocker = (InputBlocker)window;

      if (inputBlocker.isBusy())
      {
        if (recursive)
        {
          return inputBlocker.activateFirstAvailableBlockingWindow(windowEvent);
        }
        else
        {
          return false;
        }
      }
      else
      {
        Frame ownerFrame = getOwnerFrame(window);

        if ((windowEvent != null) && (ownerFrame.equals(windowEvent.getOppositeWindow())))
        {
          if (isIconified(windowEvent.getOppositeWindow()))
          {
            return false;
          }
        }

        activate(window, childIFrame, delayFocusWindow(windowEvent));

        return true;
      }
    }
    else if (window.isEnabled())
    {
      activate(window, childIFrame, delayFocusWindow(windowEvent));

      return true;
    }
    else
    {
      return false;
    }
  }


  /**
   * <p>Determine window for non-null windowevent. </p>
   *
   * @param windowEvent WindowEvent
   * @return Window Trigger window.
   */
  private static Window delayFocusWindow(WindowEvent windowEvent)
  {
    if (windowEvent == null)
    {
      return null;
    }
    else
    {
      return windowEvent.getWindow();
    }
  }


  /**
   * <p>Activate and deiconify the frame in that order or there will be
   * an <i>incomplete repaint</i> problem. </p>
   *
   * @param window Window
   * @param childIFrame JInternalFrame
   * @param delayFocusedWindow Window
   */
  private static void activate(Window window, JInternalFrame childIFrame, Window delayFocusedWindow)
  {
    if (jmodalHelper.ignore(delayFocusedWindow))
    {
      return;
    }

    if (window == null)
    {
      return;
    }

    if (delayFocusedWindow != null)
    {
      if (KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() == null)
      {
        jmodalHelper.setDelayedActivation(window, childIFrame, delayFocusedWindow);

        return;
      }
    }

    activate(window, childIFrame);
  }


  /**
   * <p>Activate and deiconify the frame in that order or there will be
   * an <i>incomplete repaint</i> problem. </p>
   *
   * @param window Window
   * @param childIFrame JInternalFrame
   */
  private static void activate(Window window, JInternalFrame childIFrame)
  {
    if (window == null)
    {
      return;
    }

    if (window instanceof Frame)
    {
      window.toFront();
      tryToDeiconify(window);
    }
    else
    {
      Frame ownerFrame = getOwnerFrame(window);

      if ((ownerFrame != null) && isIconified(ownerFrame))
      {
        ownerFrame.toFront();
        tryToDeiconify(ownerFrame);
      }

      window.toFront();
    }

    if (childIFrame != null)
    {
      try
      {
        childIFrame.setIcon(false);
        childIFrame.toFront();
        childIFrame.setSelected(true);
      }
      catch (PropertyVetoException pve)
      {
        pve.printStackTrace(System.out);
      }
    }
  }


  /**
   * <p>Deiconify the window if it references a ICONIFIED frame.
   * Otherwise check if the window first ancestor frame is deiconfied. </p>
   *
   * @param window Window
   */
  private static void tryToDeiconify(Window window)
  {
    if (window instanceof Frame)
    {
      Frame frame = (Frame)window;

      if (frame.getState() != Frame.NORMAL)
      {
        frame.setState(Frame.NORMAL);
      }
    }
  }


  /**
   * <p>Iconify the window if it references a NORMAL frame. </p>
   *
   * @param window Window
   */
  private static void tryToIconify(Window window)
  {
    if (window instanceof Frame)
    {
      Frame frame = (Frame)window;

      if (frame.getState() != Frame.ICONIFIED)
      {
        frame.setState(Frame.ICONIFIED);
      }
    }
  }


  /**
   * <p>Check if the window is iconified. </p>
   *
   * @param window Window
   * @return boolean Indication is window is iconified.
   */
  private static boolean isIconified(Window window)
  {
    if (window == null)
    {
      return false;
    }
    else if (window instanceof Frame)
    {
      return isIconified((Frame)window);
    }
    else
    {
      return isIconified(window.getOwner());
    }
  }


  /**
   * <p>Check if the frame is iconified. </p>
   *
   * @param frame Frame
   * @return boolean Indication is frame is iconified.
   */
  private static boolean isIconified(Frame frame)
  {
    return (frame.getState() != Frame.NORMAL);
  }


  /**
   * <p>Determine the owner frame of a window. </p>
   *
   * @param window Window
   * @return Frame The owner frame of the supplied window.
   */
  private static Frame getOwnerFrame(Window window)
  {
    if (window instanceof Frame)
    {
      return (Frame)window;
    }

    Window owner = window.getOwner();

    while ((owner != null) && !(owner instanceof Frame))
    {
      owner = owner.getOwner();
    }

    return (Frame)owner;
  }


  /**
   * <p>Set delayed activation of flagged window until focusedWindow is available and equals trigger frame. </p>
   *
   * @param flagActivateWindow Window Flag alternative activation window.
   * @param flagActivateInternalFrame JInternalFrame Flag alternative activation internal frame in activation window.
   * @param activateTriggerFrame Window Trigger frame for alternative activation.
   */
  private void setDelayedActivation(Window flagActivateWindow, JInternalFrame flagActivateInternalFrame,
                                    Window activateTriggerFrame)
  {
    this.flagActivateWindow = flagActivateWindow;
    this.flagActivateInternalFrame = flagActivateInternalFrame;
    this.activateTriggerFrame = activateTriggerFrame;
  }


  /**
   * <p>Set delayed focus action for supplied component. </p>
   *
   * @param returnFocus Component Focusable component.
   */
  protected static void setDelayedReturnFocus(final Component returnFocus)
  {
    if (returnFocus != null)
    {
      SwingUtilities.invokeLater(new Runnable()
      {
        public void run()
        {
          returnFocus.requestFocus();
        }
      });
    }
  }


  /**
   * <p>This method gets called when a bound property is changed. </p>
   *
   * @param evt A PropertyChangeEvent object describing the event source and the property that has changed.
   */
  public void propertyChange(PropertyChangeEvent evt)
  {
    if (flagActivateWindow != null)
    {
      if (FOCUSED_WINDOW_PROPERTY.equals(evt.getPropertyName()))
      {
        Window focusedWindow = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow();

        if (focusedWindow != null)
        {
          if (focusedWindow.equals(activateTriggerFrame))
          {
            needToActivateModalToWindow();
            activate(flagActivateWindow, flagActivateInternalFrame);
          }

          setDelayedActivation(null, null, null);
        }
      }
    }

    if (PERMANENT_FOCUS_OWNER_PROPERTY.equals(evt.getPropertyName()))
    {
      if ((evt.getNewValue() != null) && (evt.getNewValue() instanceof Component))
      {
        handleIFrameFocus((Component)evt.getNewValue());
      }
    }
  }


  private void handleIFrameFocus(Component cmp)
  {
    Component parent = Utils.parentForComponentRootPane(cmp);

    if (parent instanceof JInternalFrame)
    {
      JInternalFrame iframe = (JInternalFrame)parent;

      if (iframe instanceof InputBlocker)
      {
        InputBlocker inputBlocker = (InputBlocker)iframe;

        if (inputBlocker.isBusy())
        {
          inputBlocker.activateFirstAvailableBlockingWindow(null);

          return;
        }
      }

      if (!iframe.isSelected())
      {
        JDesktopPane desktop = iframe.getDesktopPane();

        if (desktop == null)
        {
          return;
        }

        JInternalFrame selectedIFrame = desktop.getSelectedFrame();

        if (selectedIFrame != null)
        {
          selectedIFrame.toFront();

          return;
        }
        else
        {
          JInternalFrame[] frames = iframe.getDesktopPane().getAllFrames();

          if ((frames != null) && (frames.length > 0))
          {
            try
            {
              frames[0].setSelected(true);
            }
            catch (PropertyVetoException pve)
            {
              pve.printStackTrace(System.out);
            }
          }
        }
      }
    }
  }


  /**
   * <p>If configured keep the modalToWindow also in the front when its blocking window is reactivated
   * as a result of clicking in a blocked window. Unless it is currently iconified. </p>
   */
  private void needToActivateModalToWindow()
  {
    if (JModalConfiguration.keepModalToWindowInFront())
    {
      if (flagActivateWindow instanceof JModalFrame)
      {
        Window modalToOwner = ((JModalFrame)flagActivateWindow).getModalToOwner();
        boolean activateOwner = !activateTriggerFrame.equals(modalToOwner);
        activateTriggerFrame = null;

        if (activateOwner)
        {
          if (setIgnoreTriggerFrame(modalToOwner))
          {
            activate(modalToOwner, null, null);
          }
        }
      }
    }
  }


  /**
   * <p>Activation of a modalToWindow in {@link #propertyChange(java.beans.PropertyChangeEvent) propertyChange}
   * will trigger a call to {@link #activate(java.awt.Window, JInternalFrame, java.awt.Window) activate} that should be ignored. </p>
   *
   * @param modalToWindow Window
   * @return boolean Indication if the modalToWindow owner frame is currently deiconified.
   */
  private boolean setIgnoreTriggerFrame(Window modalToWindow)
  {
    Frame ownerFrame = getOwnerFrame(modalToWindow);

    if ((ownerFrame == null) || isIconified(ownerFrame))
    {
      return false;
    }

    ignoreTriggerFrame = ownerFrame;

    return true;
  }


  /**
   * <p>Check if the possible activateTriggerFrame is activated itself
   * by some action in {@link #propertyChange(java.beans.PropertyChangeEvent) propertyChange}. </p>
   *
   * @param delayFocusedWindow Window possible activateTriggerFrame.
   * @return boolean Indication to ignore activation of a window based on the specified delayFocusedWindow
   */
  private boolean ignore(Window delayFocusedWindow)
  {
    boolean ignore = false;

    if (ignoreTriggerFrame != null)
    {
      if (ignoreTriggerFrame.equals(delayFocusedWindow))
      {
        ignore = true;
      }

      jmodalHelper.ignoreTriggerFrame = null;
    }

    return ignore;
  }
}
