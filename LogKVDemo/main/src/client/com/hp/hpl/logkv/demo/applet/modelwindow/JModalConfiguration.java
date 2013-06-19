package com.hp.hpl.logkv.demo.applet.modelwindow;


import java.awt.*;

import javax.swing.*;


/**
 * <p>Component that is used to configure the different window-specific related settings. </p>
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
public class JModalConfiguration
{
  private int blurStyle;
  private Color blurColor;
  private Cursor busyCursor;
  private boolean busyCursorEnabled;

  private Image optionalFrameIcon;
  private Icon optionalIFrameIcon;

  private boolean windowDragEnabled;
  private int windowDragBorderDistance;
  private int windowDragScreenSafetyMargin;

  private boolean alsoKeepModalToWindowInFront;

  private boolean allowIconifyForBlockedInternalFrame;

  private boolean enableWaitOnEDT;

  private static JModalConfiguration configuration;

  /**
   * <p>Constructor for the default configuration object. </p>
   */
  private JModalConfiguration()
  {
    blurStyle = JBusyPanel.BLUR_STYLE_ALPHA_CHANNEL;
    blurColor = null;
    busyCursor = null;
    busyCursorEnabled = true;

    optionalFrameIcon = null;
    optionalIFrameIcon = null;

    windowDragEnabled = true;
    windowDragBorderDistance = JModalWindow.DEFAULT_DRAG_BORDER_DISTANCE;
    windowDragScreenSafetyMargin = Utils.DEFAULT_SCREEN_SAFETY_MARGIN;

    alsoKeepModalToWindowInFront = false;
    
    allowIconifyForBlockedInternalFrame = true;

    enableWaitOnEDT = false;
  }


  /**
   * <p>Returns the configuration object. </p>
   *
   * @return JModalConfiguration
   */
  private static JModalConfiguration getInstance()
  {
    if (configuration == null)
    {
      configuration = new JModalConfiguration();
    }

    return configuration;
  }


  /**
   * <p>Retrieve the current setting for blurring. </p>
   *
   * @return int Current blurring style.
   */
  public static int getBlurStyle()
  {
    return getInstance().blurStyle;
  }


  /**
   * <p>Change the current setting for blurring. </p>
   *
   * <p>There are three options:</p>
   *
   * <ol>
   *   <li>{@link JBusyPanel#BLUR_STYLE_ALPHA_CHANNEL BLUR_STYLE_ALPHA_CHANNEL}: Blur window 
   *   by applying the {@link JBusyPanel#DEFAULT_ALPHA_TRANSPARENCY_FACTOR DEFAULT_ALPHA_TRANSPARENCY_FACTOR} 
   *   to the window's background color [default].
   *   <li>{@link JBusyPanel#BLUR_STYLE_NONE BLUR_STYLE_NONE}: Disabled blurring.
   *   <li>Or set a fixed blur color (see {@link JModalConfiguration#setBlurColor(Color) setBlurColor} ).
   * </ol>
   *
   * @param blurStyle int New blurring style.
   */
  public synchronized static void setBlurStyle(int blurStyle)
  {
    getInstance().blurStyle = blurStyle;
  }


  /**
   * <p>Change the current setting for blurring to off. </p>
   */
  public synchronized static void setBlurStyleNone()
  {
    setBlurStyle(JBusyPanel.BLUR_STYLE_NONE);
  }


  /**
   * <p>Change the current setting for blurring to alpha channel. </p>
   */
  public synchronized static void setBlurStyleAlphaChannel()
  {
    setBlurStyle(JBusyPanel.BLUR_STYLE_ALPHA_CHANNEL);
  }

  /**
   * <p>Retrieve the current color for blurring. </p>
   *
   * @return int Current blurring color.
   */
  public static Color getBlurColor()
  {
    return getInstance().blurColor;
  }


  /**
   * <p>Determine the color used for blurring. 
   * If the no blurColor is set (see {@link JModalConfiguration#setBlurColor(Color) setBlurColor})
   * then the {@link JBusyPanel#DEFAULT_ALPHA_TRANSPARENCY_FACTOR DEFAULT_ALPHA_TRANSPARENCY_FACTOR} 
   * is applied to the supplied backgroundColor. </p>
   *
   * @param backgroundColor Color Optional base color for blurring.
   * @return int Current blurring color.
   */
  public static Color getBlurColor(Color backgroundColor)
  {
    if (getBlurColor() == null)
    {
      return new Color(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), JBusyPanel.DEFAULT_ALPHA_TRANSPARENCY_FACTOR);
    }
    else
    {
      return getBlurColor();
    }
  }


  /**
   * <p>Set a fixed blurring color. If the blurColor has 
   * no alpha transparency factor (see {@link JBusyPanel#DEFAULT_ALPHA_NO_TRANSPARENCY_FACTOR DEFAULT_ALPHA_NO_TRANSPARENCY_FACTOR} )
   * the {@link JBusyPanel#DEFAULT_ALPHA_TRANSPARENCY_FACTOR DEFAULT_ALPHA_TRANSPARENCY_FACTOR} 
   * is applied. </p>
   *
   * <p>If the blurColor is NULL the {@link JBusyPanel#DEFAULT_ALPHA_TRANSPARENCY_FACTOR DEFAULT_ALPHA_TRANSPARENCY_FACTOR} 
   * is applied to the window's background color in {@link JBusyPanel#BLUR_STYLE_ALPHA_CHANNEL BLUR_STYLE_ALPHA_CHANNEL} mode. </p>
   *
   * @param blurColor Color New blurring color.
   */
  public synchronized static void setBlurColor(Color blurColor)
  {
     if ((blurColor == null) || (blurColor.getAlpha() != JBusyPanel.DEFAULT_ALPHA_NO_TRANSPARENCY_FACTOR))
     {
      getInstance().blurColor = blurColor;
    }
    else
    {
      getInstance().blurColor = new Color(blurColor.getRed(), blurColor.getGreen(), blurColor.getBlue(), JBusyPanel.DEFAULT_ALPHA_TRANSPARENCY_FACTOR);
    }
  }


  /**
   * <p>Retrieve the current busy cursor for a blocked modal component. </p>
   *
   * <p>The following 3 steps are used to determine the current busy cursor:</p>
   *
   * <ol>
   *   <li>The busy cursor set with {@link #setBusyCursor(java.awt.Cursor) setBusyCursor} if it is not null.
   *   <li>The default busy icon filed under the key <b>"swingx.busy.cursor"</b> in the UIManager.
   *   <li>The default one which is a STOP sign.
   * </ol>
   *
   * @return Current busy cursor.
   */
  public static Cursor getBusyCursor()
  {
    if (getInstance().busyCursor == null)
    {
      return Utils.getBusyCursor();
    }
    else
    {
      return getInstance().busyCursor;
    }
  }


  /**
   * <p>Change the current busy cursor for a blocked modal component. </p>
   *
   * <p>If the new value is <code>null</code> the busy cursor is retrieved from {@link Utils#getBusyCursor() getBusyCursor} in Utils. </p>
   *
   * @param busyCursor Cursor New busy cursor.
   */
  public synchronized static void setBusyCursor(Cursor busyCursor)
  {
    getInstance().busyCursor = busyCursor;
  }


  /**
   * <p>Should the busy cursor be displayed for a blocked window. </p>
   *
   * @return boolean Indication if the busy cursor should be used.
   */
  public static boolean isBusyCursorEnabled()
  {
    return getInstance().busyCursorEnabled;
  }


  /**
   * <p>Enable the use of the busy cursor for a blocked window. </p>
   */
  public synchronized static void enableBusyCursor()
  {
    getInstance().busyCursorEnabled = true;
  }


  /**
   * <p>Disable the use of the busy cursor for a blocked window. </p>
   */
  public synchronized static void disableBusyCursor()
  {
    getInstance().busyCursorEnabled = false;
  }


  /**
   * <p>Retrieve the optional frame icon. </p>
   *
   *
   * <p>The following 2 steps are used to determine the optional frame icon:</p>
   *
   * <ol>
   *   <li>The frame icon set with {@link #setOptionalFrameIcon(java.awt.Image) setOptionalFrameIcon} if it is not null.
   *   <li>The optional frame icon filed under the key <b>"swingx.frame.icon"</b> in the UIManager.
   * </ol>
   *
   * @return Image An optional frame icon.
   */
  public static Image getOptionalFrameIcon()
  {
    Image optionalFrameIcon = getInstance().optionalFrameIcon;

    if (optionalFrameIcon != null)
    {
      return optionalFrameIcon;
    }

    String uiOptionalFrameIcon = UIManager.getString("swingx.frame.icon");

    if (uiOptionalFrameIcon != null)
    {
      return Utils.getIcon(uiOptionalFrameIcon).getImage();
    }

    return null;
  }


  /**
   * <p>Change the optional frame icon. </p>
   *
   * <p>If the new value is <code>null</code> the UIManager is used to check for an optional <b>"swingx.frame.icon"</b>. </p>
   *
   * @param optionalFrameIcon Image New optional frame icon.
   */
  public synchronized static void setOptionalFrameIcon(Image optionalFrameIcon)
  {
    getInstance().optionalFrameIcon = optionalFrameIcon;
  }


  /**
   * <p>Retrieve the optional internal frame icon. </p>
   *
   *
   * <p>The following 2 steps are used to determine the optional internal frame icon:</p>
   *
   * <ol>
   *   <li>The internal frame icon set with {@link #setOptionalIFrameIcon(javax.swing.Icon) setOptionalIFrameIcon} if it is not null.
   *   <li>The optional internal frame icon filed under the key <b>"swingx.iframe.icon"</b> in the UIManager.
   * </ol>
   *
   * @return Image An optional internal frame icon.
   */
  public static Icon getOptionalIFrameIcon()
  {
    Icon optionalIFrameIcon = getInstance().optionalIFrameIcon;

    if (optionalIFrameIcon != null)
    {
      return optionalIFrameIcon;
    }

    String uiOptionalIFrameIcon = UIManager.getString("swingx.iframe.icon");

    if (uiOptionalIFrameIcon != null)
    {
      return Utils.getIcon(uiOptionalIFrameIcon);
    }

    return null;
  }


  /**
   * <p>Change the optional internal frame icon. </p>
   *
   * <p>If the new value is <code>null</code> the UIManager is used to check for an optional <b>"swingx.iframe.icon"</b>. </p>
   *
   * @param optionalIFrameIcon Image New optional internal frame icon.
   */
  public synchronized static void setOptionalIFrameIcon(Icon optionalIFrameIcon)
  {
    getInstance().optionalIFrameIcon = optionalIFrameIcon;
  }


  /**
   * <p>Is it allowed to drag windows. </p>
   *
   * @return boolean Indication if window dragging is allowed.
   */
  public static boolean isWindowDraggingEnabled()
  {
    return getInstance().windowDragEnabled;
  }


  /**
   * <p>Enable the dragging of windows. </p>
   */
  public synchronized static void enableWindowDragging()
  {
    getInstance().windowDragEnabled = true;
  }


  /**
   * <p>Disable the dragging of windows. </p>
   */
  public synchronized static void disableWindowDragging()
  {
    getInstance().windowDragEnabled = false;
  }


  /**
   * <p>Retrieve the current drag border distance from border which activates a window drag cursor. </p>
   *
   * @return int Current drag border distance.
   */
  public static int getWindowDragBorderDistance()
  {
    return getInstance().windowDragBorderDistance;
  }


  /**
   * <p>Change the current drag border distance from border which activates a window drag cursor. </p>
   *
   * <p>The default distance is set to {@link JModalWindow#DEFAULT_DRAG_BORDER_DISTANCE DEFAULT_DRAG_BORDER_DISTANCE}.
   *
   * @param windowDragBorderDistance int New drag border distance for a window drag cursor.
   */
  public synchronized static void setWindowDragBorderDistance(int windowDragBorderDistance)
  {
    getInstance().windowDragBorderDistance = windowDragBorderDistance;
  }


  /**
   * <p>Retrieve the current window drag screen safety margin for window positioning
   * in case of operating system toolbars and so on. </p>
   *
   * @return int Current drag screen safety margin.
   */
  public static int getWindowDragScreenSafetyMargin()
  {
    return getInstance().windowDragScreenSafetyMargin;
  }


  /**
   * <p>Retrieve the current window drag screen safety margin for window positioning
   * in case of operating system toolbars and so on. </p>
   *
   * @param windowDragScreenSafetyMargin int New window drag screen safety margin.
   */
  public synchronized static void setWindowDragScreenSafetyMargin(int windowDragScreenSafetyMargin)
  {
    getInstance().windowDragScreenSafetyMargin = windowDragScreenSafetyMargin;
  }


  /**
   * <p>Should the modalToWindow also stay in the front when its blocking window is reactivated
   * as a result of clicking in a blocked window. </p>
   *
   * @return boolean Indication if the modalToWindow should also stay in the front.
   */
  public static boolean keepModalToWindowInFront()
  {
    return getInstance().alsoKeepModalToWindowInFront;
  }


  /**
   * <p>Enable the placing of the modalToWindow in the front when its blocking window is reactivated
   * as a result of clicking in a blocked window. </p>
   */
  public synchronized static void enableKeepModalToWindowInFront()
  {
    getInstance().alsoKeepModalToWindowInFront = true;
  }


  /**
   * <p>Disable the placing of the modalToWindow in the front when its blocking window is reactivated
   * as a result of clicking in a blocked window. </p>
   */
  public synchronized static void disableKeepModalToWindowInFront()
  {
    getInstance().alsoKeepModalToWindowInFront = false;
  }


  /**
   * <p>May a blocked internal frame iconify when minimize is selected. </p>
   *
   * @return boolean Indication if a blocked internal frame may iconify when minimize is selected.
   */
  public static boolean allowIconifyForBlockedInternalFrame()
  {
    return getInstance().allowIconifyForBlockedInternalFrame;
  }


  /**
   * <p>Do not allow a blocked internal frame to iconify when minimize is selected. </p>
   */
  public synchronized static void disableIconifyForBlockedInternalFrame()
  {
    getInstance().allowIconifyForBlockedInternalFrame = false;
  }


  /**
   * <p>Allow a blocked internal frame to iconify when minimize is selected. </p>
   */
  public synchronized static void enableIconifyForBlockedInternalFrame()
  {
    getInstance().allowIconifyForBlockedInternalFrame = true;
  }


  /**
   * <p>Simulate JDialog like halting of the event dispatcher thread. </p>
   *
   * @return boolean
   */
  public static boolean simulateWaitOnEDT()
  {
    return getInstance().enableWaitOnEDT;
  }


  /**
   * <p>Enable simulating JDialog like halting of the event dispatcher thread. </p>
   *
   * @throws  SecurityException
   *          if a security manager exists and its <code>{@link
   *          java.lang.SecurityManager#checkAwtEventQueueAccess}</code> method denies
   *          access to the EventQueue.
   * @see     java.awt.AWTPermission
   */
  public synchronized static void enableWaitOnEDT()
  {
    enableWaitOnEDT(new JModalEventQueue());
  }


  /**
   * <p>Enable simulating JDialog like halting of the event dispatcher thread.
   * And in case you need your own custom EventQueue supply one based on the {@link JModalEventQueue JModalEventQueue}
   * that is need to enable this feature. </p>
   *
   * @param customEventQueue JModalEventQueue
   * @throws  SecurityException
   *          if a security manager exists and its <code>{@link
   *          java.lang.SecurityManager#checkAwtEventQueueAccess}</code> method denies
   *          access to the EventQueue.
   * @see     java.awt.AWTPermission
   */
  public synchronized static void enableWaitOnEDT(JModalEventQueue customEventQueue)
  {
    EventQueue currentQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();

    if (!(currentQueue instanceof JModalEventQueue))
    {
      Toolkit.getDefaultToolkit().getSystemEventQueue().push(customEventQueue);
    }

    getInstance().enableWaitOnEDT = true;
  }


  /**
   * <p>Disable simulating JDialog like halting of the event dispatcher thread. </p>
   */
  public synchronized static void disableWaitOnEDT()
  {
    getInstance().enableWaitOnEDT = false;
  }
}
