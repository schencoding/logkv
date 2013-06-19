package com.hp.hpl.logkv.demo.applet.modelwindow;


import java.awt.*;
import java.beans.*;
import java.util.*;

import javax.swing.*;



/**
 * <p>An extension of JModalInternalFrame which supports application-wide modality.  </p>
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
public class JModalInternalDialog extends JModalInternalFrame
{
  private Vector disabledMenuItems;

  /**
   * Creates a non-resizable, non-closable, non-maximizable,
   * non-iconifiable modal <code>JModalInternalDialog</code> with no title.
   * The internal frame is modal to it ownerIFrame.
   *
   * @param ownerIFrame related internal frame which will be blocked by this one.
   */
  public JModalInternalDialog(JModalInternalFrame ownerIFrame)
  {
    this(ownerIFrame, null, "", false, false, false, false);
  }


  /**
   * Creates a non-resizable, non-closable, non-maximizable,
   * non-iconifiable modal <code>JModalInternalDialog</code> with the specified title.
   * Note that passing in a <code>null</code> <code>title</code> results in
   * unspecified behavior and possibly an exception.
   *
   * The internal frame is modal to it ownerIFrame.
   *
   * @param ownerIFrame related internal frame which will be blocked by this one.
   * @param title  the non-<code>null</code> <code>String</code>
   *     to display in the title bar
   */
  public JModalInternalDialog(JModalInternalFrame ownerIFrame, String title)
  {
    this(ownerIFrame, null, title, false, false, false, false);
  }


  /**
   * Creates a non-closable, non-maximizable, non-iconifiable
   * modal <code>JModalInternalDialog</code> with the specified title
   * and resizability.
   *
   * The internal frame is modal to it ownerIFrame.
   *
   * @param ownerIFrame related internal frame which will be blocked by this one.
   * @param title      the <code>String</code> to display in the title bar
   * @param resizable  if <code>true</code>, the internal frame can be resized
   */
  public JModalInternalDialog(JModalInternalFrame ownerIFrame, String title, boolean resizable)
  {
    this(ownerIFrame, null, title, resizable, false, false, false);
  }


  /**
   * Creates a non-maximizable, non-iconifiable modal <code>JModalInternalDialog</code>
   * with the specified title, resizability, and
   * closability.
   *
   * The internal frame is modal to it ownerIFrame.
   *
   * @param ownerIFrame related internal frame which will be blocked by this one.
   * @param title      the <code>String</code> to display in the title bar
   * @param resizable  if <code>true</code>, the internal frame can be resized
   * @param closable   if <code>true</code>, the internal frame can be closed
   */
  public JModalInternalDialog(JModalInternalFrame ownerIFrame, String title, boolean resizable, boolean closable)
  {
    this(ownerIFrame, null, title, resizable, closable, false, false);
  }


  /**
   * Creates a non-iconifiable modal <code>JModalInternalDialog</code>
   * with the specified title,
   * resizability, closability, and maximizability.
   *
   * The internal frame is modal to it ownerIFrame.
   *
   * @param ownerIFrame related internal frame which will be blocked by this one.
   * @param title       the <code>String</code> to display in the title bar
   * @param resizable   if <code>true</code>, the internal frame can be resized
   * @param closable    if <code>true</code>, the internal frame can be closed
   * @param maximizable if <code>true</code>, the internal frame can be maximized
   */
  public JModalInternalDialog(JModalInternalFrame ownerIFrame, String title, boolean resizable, boolean closable,
                              boolean maximizable)
  {
    this(ownerIFrame, null, title, resizable, closable, maximizable, false);
  }


  /**
   * Creates a modal <code>JModalInternalDialog</code> with the specified title,
   * resizability, closability, maximizability, and iconifiability.
   *
   * The internal frame is modal to it ownerIFrame.
   *
   * @param ownerIFrame related internal frame which will be blocked by this one.
   * @param title       the <code>String</code> to display in the title bar
   * @param resizable   if <code>true</code>, the internal frame can be resized
   * @param closable    if <code>true</code>, the internal frame can be closed
   * @param maximizable if <code>true</code>, the internal frame can be maximized
   * @param iconifiable if <code>true</code>, the internal frame can be iconified
   */
  public JModalInternalDialog(JModalInternalFrame ownerIFrame, String title, boolean resizable, boolean closable,
                              boolean maximizable, boolean iconifiable)
  {
    this(ownerIFrame, null, title, resizable, closable, maximizable, iconifiable);
  }


  /**
   * Creates a non-resizable, non-closable, non-maximizable,
   * non-iconifiable modal <code>JModalInternalDialog</code> with no title.
   * The internal frame is modal to it ownerIFrame.
   *
   * @param ownerIFrame related internal frame which will be blocked by this one.
   * @param returnFocus component in ownerIFrame which should regain focus after closing this internal frame.
   */
  public JModalInternalDialog(JModalInternalFrame ownerIFrame, Component returnFocus)
  {
    this(ownerIFrame, returnFocus, "", false, false, false, false);
  }


  /**
   * Creates a non-resizable, non-closable, non-maximizable,
   * non-iconifiable modal <code>JModalInternalDialog</code> with the specified title.
   * Note that passing in a <code>null</code> <code>title</code> results in
   * unspecified behavior and possibly an exception.
   *
   * The internal frame is modal to it ownerIFrame.
   *
   * @param ownerIFrame related internal frame which will be blocked by this one.
   * @param returnFocus component in ownerIFrame which should regain focus after closing this internal frame.
   * @param title  the non-<code>null</code> <code>String</code>
   *     to display in the title bar
   */
  public JModalInternalDialog(JModalInternalFrame ownerIFrame, Component returnFocus, String title)
  {
    this(ownerIFrame, returnFocus, title, false, false, false, false);
  }


  /**
   * Creates a non-closable, non-maximizable, non-iconifiable
   * modal <code>JModalInternalDialog</code> with the specified title
   * and resizability.
   *
   * The internal frame is modal to it ownerIFrame.
   *
   * @param ownerIFrame related internal frame which will be blocked by this one.
   * @param returnFocus component in ownerIFrame which should regain focus after closing this internal frame.
   * @param title      the <code>String</code> to display in the title bar
   * @param resizable  if <code>true</code>, the internal frame can be resized
   */
  public JModalInternalDialog(JModalInternalFrame ownerIFrame, Component returnFocus, String title, boolean resizable)
  {
    this(ownerIFrame, returnFocus, title, resizable, false, false, false);
  }


  /**
   * Creates a non-maximizable, non-iconifiable modal <code>JModalInternalDialog</code>
   * with the specified title, resizability, and
   * closability.
   *
   * The internal frame is modal to it ownerIFrame.
   *
   * @param ownerIFrame related internal frame which will be blocked by this one.
   * @param returnFocus component in ownerIFrame which should regain focus after closing this internal frame.
   * @param title      the <code>String</code> to display in the title bar
   * @param resizable  if <code>true</code>, the internal frame can be resized
   * @param closable   if <code>true</code>, the internal frame can be closed
   */
  public JModalInternalDialog(JModalInternalFrame ownerIFrame, Component returnFocus, String title, boolean resizable,
                              boolean closable)
  {
    this(ownerIFrame, returnFocus, title, resizable, closable, false, false);
  }


  /**
   * Creates a non-iconifiable modal <code>JModalInternalDialog</code>
   * with the specified title,
   * resizability, closability, and maximizability.
   *
   * The internal frame is modal to it ownerIFrame.
   *
   * @param ownerIFrame related internal frame which will be blocked by this one.
   * @param returnFocus component in ownerIFrame which should regain focus after closing this internal frame.
   * @param title       the <code>String</code> to display in the title bar
   * @param resizable   if <code>true</code>, the internal frame can be resized
   * @param closable    if <code>true</code>, the internal frame can be closed
   * @param maximizable if <code>true</code>, the internal frame can be maximized
   */
  public JModalInternalDialog(JModalInternalFrame ownerIFrame, Component returnFocus, String title, boolean resizable,
                              boolean closable, boolean maximizable)
  {
    this(ownerIFrame, returnFocus, title, resizable, closable, maximizable, false);
  }


  /**
   * Creates a modal <code>JModalInternalDialog</code> with the specified title,
   * resizability, closability, maximizability, and iconifiability.
   *
   * The internal frame is modal to it ownerIFrame.
   *
   * @param ownerIFrame related internal frame which will be blocked by this one.
   * @param returnFocus component in ownerIFrame which should regain focus after closing this internal frame.
   * @param title       the <code>String</code> to display in the title bar
   * @param resizable   if <code>true</code>, the internal frame can be resized
   * @param closable    if <code>true</code>, the internal frame can be closed
   * @param maximizable if <code>true</code>, the internal frame can be maximized
   * @param iconifiable if <code>true</code>, the internal frame can be iconified
   */
  public JModalInternalDialog(JModalInternalFrame ownerIFrame, Component returnFocus, String title, boolean resizable,
                              boolean closable, boolean maximizable, boolean iconifiable)
  {
    super(title, resizable, closable, maximizable, iconifiable);

    markAllInternalFrames(ownerIFrame);
    markAllWindows(ownerIFrame);
    disableMenu(ownerIFrame);
  }


  /**
   * <p>Iterate all available internal frames. </p>
   *
   * @param ownerIFrame JModalInternalFrame
   */
  private void markAllInternalFrames(JModalInternalFrame ownerIFrame)
  {
    JInternalFrame[] frames = ownerIFrame.getDesktopPane().getAllFrames();

    for (int i = 0; i < frames.length; i++)
    {
      if (!this.equals(frames[i]) && (frames[i] instanceof InputBlocker))
      {
        addAdditionalModalToComponent((InputBlocker)frames[i]);
      }
    }
  }


  /**
   * <p>Iterate all available frames. </p>
   *
   * @param ownerIFrame JModalInternalFrame
   */
  private void markAllWindows(JModalInternalFrame ownerIFrame)
  {
    Frame owner = (Frame)Utils.windowForComponent(ownerIFrame);
    Frame[] frames = owner.getFrames();

    for (int i = 0; i < frames.length; i++)
    {
      markWindowAndChildren(frames[i], owner);
    }
  }


  /**
   * <p>If the window isn't disposed yet then add it to the list of windows that should be blocked. </p>
   *
   * <p>Apply the same check recursively to the windows children. </p>
   *
   * @param window Window
   * @param excludeFrame Frame
   */
  private void markWindowAndChildren(Window window, Frame excludeFrame)
  {
    if (window.isDisplayable())
    {
      Window[] children = window.getOwnedWindows();

      for (int i = 0; i < children.length; i++)
      {
        markWindowAndChildren(children[i], excludeFrame);
      }

      if (!window.equals(JModalWindow.getSharedOwnerFrame()) && !this.equals(window) && !excludeFrame.equals(window))
      {
        addAdditionalModalToWindow(window);
      }
    }
  }


  /**
   * <p>Disable any active menu item in the menubar. </p>
   *
   * @param ownerIFrame JModalInternalFrame
   */
  private void disableMenu(JModalInternalFrame ownerIFrame)
  {

    disabledMenuItems = new Vector();
    Window window = Utils.windowForComponent(ownerIFrame);

    if ((window != null) && (window instanceof Frame))
    {
      Frame frame = (Frame)window;
      MenuBar menuBar = frame.getMenuBar();

      if (menuBar != null)
      {
        Menu menu;

        for (int i = 0; i < menuBar.getMenuCount(); i++)
        {
          menu = menuBar.getMenu(i);

          if (menu.isEnabled())
          {
            menu.setEnabled(false);
            disabledMenuItems.add(menu);
          }
        }
      }
    }

    if ((window != null) && (window instanceof JFrame))
    {
      JFrame frame = (JFrame)window;
      JMenuBar menuBar = frame.getJMenuBar();

      if (menuBar != null)
      {
        Component[] items = menuBar.getComponents();

        for (int i = 0; i < items.length; i++)
        {
          if (items[i].isEnabled())
          {
            items[i].setEnabled(false);
            disabledMenuItems.add(items[i]);
          }
        }
      }
    }
  }


  /**
   * <p>Enable any menu item in the menubar that were active previously. </p>
   */
  private void restoreMenu()
  {
    if (disabledMenuItems == null)
    {
      return;
    }

    for (Iterator menuIterator = disabledMenuItems.iterator(); menuIterator.hasNext(); )
    {
      Object obj = menuIterator.next();

      if (obj instanceof Component)
      {
        ((Component)obj).setEnabled(true);
      }
      else if (obj instanceof MenuItem)
      {
        ((MenuItem)obj).setEnabled(true);
      }
    }
  }


  public void doDefaultCloseAction()
  {
    if (!isBusy())
    {
      if (getDefaultCloseOperation() == DISPOSE_ON_CLOSE)
      {
        restoreMenu();
      }
    }

    super.doDefaultCloseAction();
  }


  public void setClosed(boolean closed) throws PropertyVetoException
  {
    if (!isBusy())
    {
      if (closed)
      {
        restoreMenu();
      }
    }

    super.setClosed(closed);
  }


  public void setVisible(boolean visible)
  {
    if (!isBusy())
    {
      if (!visible)
      {
        restoreMenu();
      }
    }

    super.setVisible(visible);
  }


  synchronized void stopModal()
  {
    restoreMenu();

    super.release();
  }
}
