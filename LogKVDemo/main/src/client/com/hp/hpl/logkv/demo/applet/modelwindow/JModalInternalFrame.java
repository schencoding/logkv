package com.hp.hpl.logkv.demo.applet.modelwindow;


import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;

import javax.swing.*;
import javax.swing.plaf.basic.*;



/**
 * <p>An extension of JInternalFrame which supports iframe-specific modality. </p>
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
public class JModalInternalFrame extends JInternalFrame implements InputBlocker
{
  private BasicInternalFrameTitlePane titlePane;
  private JModalInternalFrame modalToOwner;
  private Vector modalToWindowsAndInputBlockers;
  private Vector blockingWindows;
  private boolean notifiedModalToWindow;
  private Component returnFocus;

  private JPanel busyPanel;
  private Color blurColor;

  private Component oldGlassPane = null;
  private boolean oldGlassPaneVisible = false;
  private int minWidth, minHeight;
  private boolean mouseCurrentlyPressed = false;
  private boolean componentIsResizedButMinSizeNotChecked = false;

  /**
   * Creates a non-resizable, non-closable, non-maximizable,
   * non-iconifiable <code>JModalInternalFrame</code> with no title.
   */
  public JModalInternalFrame()
  {
    this("", false, false, false, false);
  }


  /**
   * Creates a non-resizable, non-closable, non-maximizable,
   * non-iconifiable <code>JModalInternalFrame</code> with the specified title.
   * Note that passing in a <code>null</code> <code>title</code> results in
   * unspecified behavior and possibly an exception.
   *
   * @param title  the non-<code>null</code> <code>String</code>
   *     to display in the title bar
   */
  public JModalInternalFrame(String title)
  {
    this(title, false, false, false, false);
  }


  /**
   * Creates a non-closable, non-maximizable, non-iconifiable
   * <code>JModalInternalFrame</code> with the specified title
   * and resizability.
   *
   * @param title      the <code>String</code> to display in the title bar
   * @param resizable  if <code>true</code>, the internal frame can be resized
   */
  public JModalInternalFrame(String title, boolean resizable)
  {
    this(title, resizable, false, false, false);
  }


  /**
   * Creates a non-maximizable, non-iconifiable <code>JModalInternalFrame</code>
   * with the specified title, resizability, and
   * closability.
   *
   * @param title      the <code>String</code> to display in the title bar
   * @param resizable  if <code>true</code>, the internal frame can be resized
   * @param closable   if <code>true</code>, the internal frame can be closed
   */
  public JModalInternalFrame(String title, boolean resizable, boolean closable)
  {
    this(title, resizable, closable, false, false);
  }


  /**
   * Creates a non-iconifiable <code>JModalInternalFrame</code>
   * with the specified title,
   * resizability, closability, and maximizability.
   *
   * @param title       the <code>String</code> to display in the title bar
   * @param resizable   if <code>true</code>, the internal frame can be resized
   * @param closable    if <code>true</code>, the internal frame can be closed
   * @param maximizable if <code>true</code>, the internal frame can be maximized
   */
  public JModalInternalFrame(String title, boolean resizable, boolean closable, boolean maximizable)
  {
    this(title, resizable, closable, maximizable, false);
  }


  /**
   * Creates a <code>JModalInternalFrame</code> with the specified title,
   * resizability, closability, maximizability, and iconifiability.
   *
   * @param title       the <code>String</code> to display in the title bar
   * @param resizable   if <code>true</code>, the internal frame can be resized
   * @param closable    if <code>true</code>, the internal frame can be closed
   * @param maximizable if <code>true</code>, the internal frame can be maximized
   * @param iconifiable if <code>true</code>, the internal frame can be iconified
   */
  public JModalInternalFrame(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable)
  {
    this(null, null, title, resizable, closable, maximizable, iconifiable);
  }


  /**
   * Creates a non-resizable, non-closable, non-maximizable,
   * non-iconifiable modal <code>JModalInternalFrame</code> with no title.
   * The internal frame is modal to it owner.
   *
   * @param owner related internal frame which will be blocked by this one.
   */
  public JModalInternalFrame(JModalInternalFrame owner)
  {
    this(owner, null, "", false, false, false, false);
  }


  /**
   * Creates a non-resizable, non-closable, non-maximizable,
   * non-iconifiable modal <code>JModalInternalFrame</code> with the specified title.
   * Note that passing in a <code>null</code> <code>title</code> results in
   * unspecified behavior and possibly an exception.
   *
   * The internal frame is modal to it owner.
   *
   * @param owner related internal frame which will be blocked by this one.
   * @param title  the non-<code>null</code> <code>String</code>
   *     to display in the title bar
   */
  public JModalInternalFrame(JModalInternalFrame owner, String title)
  {
    this(owner, null, title, false, false, false, false);
  }


  /**
   * Creates a non-closable, non-maximizable, non-iconifiable
   * modal <code>JModalInternalFrame</code> with the specified title
   * and resizability.
   *
   * The internal frame is modal to it owner.
   *
   * @param owner related internal frame which will be blocked by this one.
   * @param title      the <code>String</code> to display in the title bar
   * @param resizable  if <code>true</code>, the internal frame can be resized
   */
  public JModalInternalFrame(JModalInternalFrame owner, String title, boolean resizable)
  {
    this(owner, null, title, resizable, false, false, false);
  }


  /**
   * Creates a non-maximizable, non-iconifiable modal <code>JModalInternalFrame</code>
   * with the specified title, resizability, and
   * closability.
   *
   * The internal frame is modal to it owner.
   *
   * @param owner related internal frame which will be blocked by this one.
   * @param title      the <code>String</code> to display in the title bar
   * @param resizable  if <code>true</code>, the internal frame can be resized
   * @param closable   if <code>true</code>, the internal frame can be closed
   */
  public JModalInternalFrame(JModalInternalFrame owner, String title, boolean resizable, boolean closable)
  {
    this(owner, null, title, resizable, closable, false, false);
  }


  /**
   * Creates a non-iconifiable modal <code>JModalInternalFrame</code>
   * with the specified title,
   * resizability, closability, and maximizability.
   *
   * The internal frame is modal to it owner.
   *
   * @param owner related internal frame which will be blocked by this one.
   * @param title       the <code>String</code> to display in the title bar
   * @param resizable   if <code>true</code>, the internal frame can be resized
   * @param closable    if <code>true</code>, the internal frame can be closed
   * @param maximizable if <code>true</code>, the internal frame can be maximized
   */
  public JModalInternalFrame(JModalInternalFrame owner, String title, boolean resizable, boolean closable, boolean maximizable)
  {
    this(owner, null, title, resizable, closable, maximizable, false);
  }


  /**
   * Creates a modal <code>JModalInternalFrame</code> with the specified title,
   * resizability, closability, maximizability, and iconifiability.
   *
   * The internal frame is modal to it owner.
   *
   * @param owner related internal frame which will be blocked by this one.
   * @param title       the <code>String</code> to display in the title bar
   * @param resizable   if <code>true</code>, the internal frame can be resized
   * @param closable    if <code>true</code>, the internal frame can be closed
   * @param maximizable if <code>true</code>, the internal frame can be maximized
   * @param iconifiable if <code>true</code>, the internal frame can be iconified
   */
  public JModalInternalFrame(JModalInternalFrame owner, String title, boolean resizable, boolean closable, boolean maximizable,
                             boolean iconifiable)
  {
    this(owner, null, title, resizable, closable, maximizable, iconifiable);
  }


  /**
   * Creates a non-resizable, non-closable, non-maximizable,
   * non-iconifiable modal <code>JModalInternalFrame</code> with no title.
   * The internal frame is modal to it owner.
   *
   * @param owner related internal frame which will be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this internal frame.
   */
  public JModalInternalFrame(JModalInternalFrame owner, Component returnFocus)
  {
    this(owner, returnFocus, "", false, false, false, false);
  }


  /**
   * Creates a non-resizable, non-closable, non-maximizable,
   * non-iconifiable modal <code>JModalInternalFrame</code> with the specified title.
   * Note that passing in a <code>null</code> <code>title</code> results in
   * unspecified behavior and possibly an exception.
   *
   * The internal frame is modal to it owner.
   *
   * @param owner related internal frame which will be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this internal frame.
   * @param title  the non-<code>null</code> <code>String</code>
   *     to display in the title bar
   */
  public JModalInternalFrame(JModalInternalFrame owner, Component returnFocus, String title)
  {
    this(owner, returnFocus, title, false, false, false, false);
  }


  /**
   * Creates a non-closable, non-maximizable, non-iconifiable
   * modal <code>JModalInternalFrame</code> with the specified title
   * and resizability.
   *
   * The internal frame is modal to it owner.
   *
   * @param owner related internal frame which will be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this internal frame.
   * @param title      the <code>String</code> to display in the title bar
   * @param resizable  if <code>true</code>, the internal frame can be resized
   */
  public JModalInternalFrame(JModalInternalFrame owner, Component returnFocus, String title, boolean resizable)
  {
    this(owner, returnFocus, title, resizable, false, false, false);
  }


  /**
   * Creates a non-maximizable, non-iconifiable modal <code>JModalInternalFrame</code>
   * with the specified title, resizability, and
   * closability.
   *
   * The internal frame is modal to it owner.
   *
   * @param owner related internal frame which will be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this internal frame.
   * @param title      the <code>String</code> to display in the title bar
   * @param resizable  if <code>true</code>, the internal frame can be resized
   * @param closable   if <code>true</code>, the internal frame can be closed
   */
  public JModalInternalFrame(JModalInternalFrame owner, Component returnFocus, String title, boolean resizable, boolean closable)
  {
    this(owner, returnFocus, title, resizable, closable, false, false);
  }


  /**
   * Creates a non-iconifiable modal <code>JModalInternalFrame</code>
   * with the specified title,
   * resizability, closability, and maximizability.
   *
   * The internal frame is modal to it owner.
   *
   * @param owner related internal frame which will be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this internal frame.
   * @param title       the <code>String</code> to display in the title bar
   * @param resizable   if <code>true</code>, the internal frame can be resized
   * @param closable    if <code>true</code>, the internal frame can be closed
   * @param maximizable if <code>true</code>, the internal frame can be maximized
   */
  public JModalInternalFrame(JModalInternalFrame owner, Component returnFocus, String title, boolean resizable, boolean closable,
                             boolean maximizable)
  {
    this(owner, returnFocus, title, resizable, closable, maximizable, false);
  }


  /**
   * Creates a modal <code>JModalInternalFrame</code> with the specified title,
   * resizability, closability, maximizability, and iconifiability.
   *
   * The internal frame is modal to it owner.
   *
   * @param owner related internal frame which will be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this internal frame.
   * @param title       the <code>String</code> to display in the title bar
   * @param resizable   if <code>true</code>, the internal frame can be resized
   * @param closable    if <code>true</code>, the internal frame can be closed
   * @param maximizable if <code>true</code>, the internal frame can be maximized
   * @param iconifiable if <code>true</code>, the internal frame can be iconified
   */
  public JModalInternalFrame(JModalInternalFrame owner, Component returnFocus, String title, boolean resizable, boolean closable,
                             boolean maximizable, boolean iconifiable)
  {
    super(title, resizable, closable, maximizable, iconifiable);

    jmodalInternalFrameInit(owner, returnFocus);
  }


  /**
   * <p>Initialize modal internal frame. </p>
   *
   * @param owner related internal frame which could be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this internal frame.
   */
  private void jmodalInternalFrameInit(JModalInternalFrame owner, Component returnFocus)
  {
    final JModalInternalFrame iframe = this;
    Component[] components = getComponents();

    for (int i = 0; i < components.length; i++)
    {
      if (components[i] instanceof BasicInternalFrameTitlePane)
      {
        titlePane = (BasicInternalFrameTitlePane)components[i];

        titlePane.addMouseListener(new MouseAdapter()
        {
          public void mouseReleased(MouseEvent mouseEvent)
          {
            Utils.keepIFramePartiallyOnDesktop(iframe, iframe.titlePane, iframe.getDesktopPane(), iframe.getX(), iframe.getY());
          }
        });
      }
    }

    synchronized (JModalInternalFrame.this)
    {
      notifiedModalToWindow = true;
      modalToWindowsAndInputBlockers = new Vector();
      blockingWindows = new Vector();

      if (owner != null)
      {
        modalToOwner = owner;
        modalToWindowsAndInputBlockers.add(owner);
        setIconifiable(false);
      }
    }

    this.returnFocus = returnFocus;

    enableEvents(AWTEvent.COMPONENT_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK);

    Icon optionalIFrameIcon = JModalConfiguration.getOptionalIFrameIcon();

    if (optionalIFrameIcon != null)
    {
      setFrameIcon(optionalIFrameIcon);
    }

    initBusyPanel();
  }


  /**
   * <p>Helper method for JModalHelper to get a hold of the possible owner that this internal frame is modal to. </p>
   *
   * @return JModalInternalFrame owner
   */
  protected JModalInternalFrame getModalToOwner()
  {
    return modalToOwner;
  }


  /**
   * <p>Initialize blurring glass pane. </p>
   */
  private void initBusyPanel()
  {
    blurColor = JModalConfiguration.getBlurColor(getContentPane().getBackground());
    busyPanel = new JBusyPanel(blurColor, JModalConfiguration.getBlurStyle());

    busyPanel.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent mouseEvent)
      {
        checkActivationAllowed();
      }
    });
  }


  public void addAdditionalModalToWindow(Window window)
  {
    if ((window != null) && !modalToWindowsAndInputBlockers.contains(window))
    {
      modalToWindowsAndInputBlockers.add(window);
      setIconifiable(false);
    }
  }


  public void addAdditionalModalToComponent(InputBlocker inputBlocker)
  {
    if ((inputBlocker != null) && !modalToWindowsAndInputBlockers.contains(inputBlocker))
    {
      modalToWindowsAndInputBlockers.add(inputBlocker);
      setIconifiable(false);
    }
  }


  public boolean activateFirstAvailableBlockingWindow(WindowEvent windowEvent)
  {
    return JModalHelper.activateFirstAvailableBlockingWindow(blockingWindows, windowEvent);
  }


  public boolean isBusy()
  {
    return ((blockingWindows != null) && (blockingWindows.size() > 0));
  }


  public void setBusy(boolean busy, Window blockingWindow)
  {
    setBusyStatus(busy, blockingWindow);
  }


  public void setBusy(boolean busy, JModalInternalFrame blockingIFrame)
  {
    setBusyStatus(busy, blockingIFrame);
  }


  /**
   * <p>Should be called by blocking modal window/frame children. </p>
   *
   * <p>Note:</p>
   *
   * <ul>
   *   <li>Setting the frame cursor <b>and</b> the glass pane cursor in this order works around
   *   the Win32 problem where you have to move the mouse 1 pixel to get the Cursor to change.
   *   <li>Force glass pane to get focus so that we consume <code>KeyEvents</code>.
   * </ul>
   *
   * @param busy indication if a modal window/frame is opened or closed and this frame is blocked or unblocked.
   * @param blockingWindowOrInternalFrame child window/frame that blocks owner.
   */
  private void setBusyStatus(boolean busy, Component blockingWindowOrInternalFrame)
  {
    if (busy)
    {
      if (blockingWindows.size() == 0)
      {
        oldGlassPane = this.getGlassPane();
        oldGlassPaneVisible = this.getGlassPane().isVisible();
        this.setGlassPane(busyPanel);
      }

      this.getGlassPane().setVisible(true);

      if (!blockingWindows.contains(blockingWindowOrInternalFrame))
      {
        blockingWindows.add(blockingWindowOrInternalFrame);
      }

      if (this.equals(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()))
      {
        busyPanel.grabFocus();
      }

      if (isSelected())
      {
        try
        {
          setSelected(false);
        }
        catch (PropertyVetoException pve)
        {
          pve.printStackTrace(System.out);
        }
      }
    }
    else
    {
      blockingWindows.remove(blockingWindowOrInternalFrame);

      if (blockingWindows.size() == 0)
      {
        this.getGlassPane().setVisible(false);
        this.setGlassPane(oldGlassPane);
        this.getGlassPane().setVisible(oldGlassPaneVisible);
      }
    }
    
    Frame owner = (Frame)Utils.windowForComponent(this);
    
    if (owner instanceof CloseBlocker)
    {
      ((CloseBlocker)owner).setClosable(this);
    }
  }


  /**
   * <p>Place the internal frame in the center of the desktop. </p>
   */
  public void centerOfDesktop()
  {
    Utils.centerOfDesktop(this, this.getDesktopPane());
  }


  /**
   * <p>Try to center the internal frame in the owner internal frame. </p>
   *
   * <p>If there is no owner the internal frame is centered on the desktop. </p>
   */
  public void centerOfOwner()
  {
    Utils.centerOfOwner(this, this.getDesktopPane(), getModalToOwner());
  }


  /**
   * <p>Try to position the internal frame relative to the specified component. </p>
   *
   * <p>If there is no owner the internal frame is centered on the desktop. </p>
   *
   * @param child component where the internal frame should be positioned just below.
   */
  public void relativeToOwnerChild(Component child)
  {
    Utils.relativeToOwnerChild(this, this.getDesktopPane(), getModalToOwner(), child);
  }


  /**
   * <p>Set a minimal internal frame size. </p>
   *
   * @param dim minimal internal frame size.
   */
  public void setMinSize(Dimension dim)
  {
    setMinSize(dim.width, dim.height);
  }


  /**
   * <p>Set a minimal internal frame size. </p>
   *
   * @param width minimal internal frame width.
   * @param height minimal internal frame height.
   */
  public void setMinSize(int width, int height)
  {
    minWidth = width;
    minHeight = height;
  }


  /**
   * <p>Apparently this internal frame is closed and thus owner is no longer blocked by this one. </p>
   */
  private void restoreOwner()
  {
    synchronized (JModalInternalFrame.this)
    {
      if ((modalToWindowsAndInputBlockers != null) && (modalToWindowsAndInputBlockers.size() > 0) && !notifiedModalToWindow)
      {
        JModalHelper.unblockWindows(modalToWindowsAndInputBlockers, this, returnFocus);

        notifiedModalToWindow = true;
      }
    }
  }


  /**
   * Fires an
   * <code>INTERNAL_FRAME_CLOSING</code> event
   * and then performs the action specified by
   * the internal frame's default close operation.
   * This method is typically invoked by the
   * look-and-feel-implemented action handler
   * for the internal frame's close button.
   *
   * @since 1.3
   * @see #setDefaultCloseOperation
   */
  public void doDefaultCloseAction()
  {
    if (isBusy())
    {
      return;
    }
    else
    {
      if (getDefaultCloseOperation() == DISPOSE_ON_CLOSE)
      {
        restoreOwner();
        release();
      }

      super.doDefaultCloseAction();
    }
  }


  /**
   * <p>Overrule <code>DefaultCloseOperation</code> when internal frame is blocked. </p>
   *
   * @return the default close operation.
   */
  public int getDefaultCloseOperation()
  {
    if (isBusy())
    {
      return WindowConstants.DO_NOTHING_ON_CLOSE;
    }
    else
    {
      return super.getDefaultCloseOperation();
    }
  }


  /**
   * Closes this internal frame if the argument is <code>true</code>.
   * Do not invoke this method with a <code>false</code> argument;
   * the result of invoking <code>setClosed(false)</code>
   * is unspecified.
   *
   * <p>
   *
   * If the internal frame is already closed,
   * this method does nothing and returns immediately.
   * Otherwise,
   * this method begins by firing
   * an <code>INTERNAL_FRAME_CLOSING</code> event.
   * Then this method sets the <code>closed</code> property to <code>true</code>
   * unless a listener vetoes the property change.
   * This method finishes by making the internal frame
   * invisible and unselected,
   * and then firing an <code>INTERNAL_FRAME_CLOSED</code> event.
   *
   * <p>
   *
   * <b>Note:</b>
   * To reuse an internal frame that has been closed,
   * you must add it to a container
   * (even if you never removed it from its previous container).
   * Typically, this container will be the <code>JDesktopPane</code>
   * that previously contained the internal frame.
   *
   * @param closed must be <code>true</code>
   *
   * @exception PropertyVetoException when the attempt to set the
   *            property is vetoed by the <code>JInternalFrame</code>
   *
   * @see #isClosed()
   * @see #setDefaultCloseOperation
   * @see #dispose
   */
  public void setClosed(boolean closed) throws PropertyVetoException
  {
    if (closed && isBusy())
    {
      JModalHelper.activateFirstAvailableBlockingWindow(blockingWindows, null);
    }
    else
    {
      restoreOwner();
      release();

      super.setClosed(closed);
    }
  }


  /**
   * Selects or deselects the internal frame
   * if it's showing.
   * A <code>JInternalFrame</code> normally draws its title bar
   * differently if it is
   * the selected frame, which indicates to the user that this
   * internal frame has the focus.
   * When this method changes the state of the internal frame
   * from deselected to selected, it fires an
   * <code>InternalFrameEvent.INTERNAL_FRAME_ACTIVATED</code> event.
   * If the change is from selected to deselected,
   * an <code>InternalFrameEvent.INTERNAL_FRAME_DEACTIVATED</code> event
   * is fired.
   *
   * @param selected  a boolean, where <code>true</code> means this internal frame
   *                  should become selected (currently active)
   *                  and <code>false</code> means it should become deselected
   * @exception PropertyVetoException when the attempt to set the
   *            property is vetoed by the <code>JInternalFrame</code>
   *
   * @see #isShowing
   */
  public void setSelected(boolean selected) throws PropertyVetoException
  {
    if (selected && isBusy())
    {
      toFront();
      super.setSelected(false);

      deselectAnySelectedIFrame();

      JModalHelper.activateFirstAvailableBlockingWindow(blockingWindows, null);
    }
    else
    {
      Component mostRecentFocusOwner = null;

      if (selected)
      {
        mostRecentFocusOwner = getMostRecentFocusOwner();
      }

      super.setSelected(selected);

      if (isSelected())
      {
        needToActivateModalToWindow();

        if ((mostRecentFocusOwner != null) && (getMostRecentFocusOwner() == null))
        {
          JModalHelper.setDelayedReturnFocus(mostRecentFocusOwner);
        }
      }
    }
  }


  /**
   * Iconifies or de-iconifies this internal frame,
   * if the look and feel supports iconification.
   * If the internal frame's state changes to iconified,
   * this method fires an <code>INTERNAL_FRAME_ICONIFIED</code> event.
   * If the state changes to de-iconified,
   * an <code>INTERNAL_FRAME_DEICONIFIED</code> event is fired.
   *
   * @param iconify a boolean, where <code>true</code> means to iconify this internal frame and
   *          <code>false</code> means to de-iconify it
   * @exception PropertyVetoException when the attempt to set the
   *            property is vetoed by the <code>JInternalFrame</code>
   */
  public void setIcon(boolean iconify) throws PropertyVetoException
  {
    if ((modalToWindowsAndInputBlockers != null) && (modalToWindowsAndInputBlockers.size() > 0))
    {
      setIconifiable(false);

      return;
    }
    else if (iconify && isBusy() && !JModalConfiguration.allowIconifyForBlockedInternalFrame()) 
    {
      return;
    }


    super.setIcon(iconify);
  }


  /**
   * <p>Check if the internal frame may be activated. Otherwise activate one of the blocking windows. </p>
   */
  private void checkActivationAllowed()
  {
    if (isBusy())
    {
      toFront();

      try
      {
        super.setSelected(false);

        deselectAnySelectedIFrame();
      }
      catch (PropertyVetoException pve)
      {
        pve.printStackTrace(System.out);
      }

      JModalHelper.activateFirstAvailableBlockingWindow(blockingWindows, null);
    }
  }


  private void deselectAnySelectedIFrame() throws PropertyVetoException
  {
    JInternalFrame selectedIFrame = getDesktopPane().getSelectedFrame();

    if (selectedIFrame != null)
    {
      selectedIFrame.setSelected(false);
    }
  }


  /**
   * <p>Option to make another thread wait for this internal frame to close. </p>
   *
   * <p>This method shouldn't be called from the event dispatcher thread
   * (see <a href="http://java.sun.com/docs/books/tutorial/uiswing/misc/threads.html">How to Use Threads</a>).
   * As an alternative use {@link Window#addWindowListener addWindowListener}. </p>
   */
  synchronized final public void waitForClose()
  {
    if (EventQueue.isDispatchThread())
    {
      if (JModalConfiguration.simulateWaitOnEDT())
      {
        EventQueue queue = getToolkit().getSystemEventQueue();

        if (queue instanceof JModalEventQueue)
        {
          ((JModalEventQueue)queue).waitForClose(this);
        }
        else
        {
          throw new Error("Cannot call wait from the event dispatcher thread due to wrong EventQueue class.");
        }
      }
      else
      {
        throw new Error(
            "Cannot call wait from the event dispatcher thread or use JModalConfiguration.enableWaitOnEDT([JModalEventQueue]).");
      }
    }
    else
    {
      try
      {
        wait();
      }
      catch (InterruptedException ie)
      {
        ie.printStackTrace(System.out);
      }
    }
  }


  /**
   * <p>Notify threads that are waiting for this internal frame to close, that the internal frame is closing. </p>
   */
  synchronized final public void release()
  {
    notifyAll();
  }


  /**
   * @deprecated As of JDK version 1.1,
   * replaced by <code>setVisible(boolean)</code>.
   *
   * <p>Handle blocking of owner when this internal frame is shown. </p>
   *
   * <p><b>Note:</b>Unlike JDialog calling this method doesn't halt the calling thread.
   * To create the same effect, call the method {@link #waitForClose() waitForClose}
   * afterwards. </p>
   */
  public void show()
  {
    synchronized (JModalInternalFrame.this)
    {
      if ((modalToWindowsAndInputBlockers.size() > 0) && notifiedModalToWindow)
      {
        JModalHelper.blockWindows(modalToWindowsAndInputBlockers, this);

        notifiedModalToWindow = false;
      }
    }

    super.show();
  }


  /**
   * <p>Disable closing of internal frame when child window/frame has this internal frame blocked. </p>
   *
   * <p><b>Note:</b>Unlike JDialog calling this method doesn't halt the calling thread.
   * To create the same effect, call the method {@link #waitForClose() waitForClose}
   * afterwards. </p>
   *
   * @param visible new internal frame visibility.
   */
  public void setVisible(boolean visible)
  {
    if (!visible)
    {
      if (isBusy())
      {
        setVisible(true);

        return;
      }

      restoreOwner();
      release();
    }

    super.setVisible(visible);
  }


  synchronized void startModal()
  {
    waitForClose();
  }


  synchronized void stopModal()
  {
    release();
  }


  /**
   * <p>In case the internal frame is resized make sure that isn't resized smaller than the minimum width and/or height. </p>
   *
   * @param componentEvent ComponentEvent
   */
  protected void processComponentEvent(ComponentEvent componentEvent)
  {
    int eventId = componentEvent.getID();

    switch (eventId)
    {
      case ComponentEvent.COMPONENT_RESIZED:
        componentIsResizedButMinSizeNotChecked = true;

        if (!mouseCurrentlyPressed)
        {
          if (!hasAtLeastMinimumSize())
          {
            return;
          }
        }

        break;
      case ComponentEvent.COMPONENT_MOVED:
      case ComponentEvent.COMPONENT_SHOWN:
      case ComponentEvent.COMPONENT_HIDDEN:
      default:
        break;
    }

    super.processComponentEvent(componentEvent);
  }


  /**
   * <p>In case the internal frame is resized make sure that isn't resized smaller than the minimum width and/or height. </p>
   *
   * @param mouseEvent the mouse event
   */
  protected void processMouseEvent(MouseEvent mouseEvent)
  {
    int eventId = mouseEvent.getID();

    switch (eventId)
    {
      case MouseEvent.MOUSE_PRESSED:
        mouseCurrentlyPressed = true;
        break;
      case MouseEvent.MOUSE_RELEASED:
        mouseCurrentlyPressed = false;

        Utils.keepIFramePartiallyOnDesktop(this, this.titlePane, this.getDesktopPane(), getX(), getY());

        break;
      case MouseEvent.MOUSE_CLICKED:
        mouseCurrentlyPressed = false;
        break;
      case MouseEvent.MOUSE_EXITED:
        break;
      case MouseEvent.MOUSE_ENTERED:
        break;
    }

    if (!mouseCurrentlyPressed && componentIsResizedButMinSizeNotChecked)
    {
      hasAtLeastMinimumSize();
    }

    super.processMouseEvent(mouseEvent);
  }


  /**
   * <p>Compare the internal frame size against the minimum width and/or height, when the minimum size is specified. </p>
   *
   * @return boolean Indication whether size is at least the minimum width and/or height.
   */
  private boolean hasAtLeastMinimumSize()
  {
    int width, height;

    componentIsResizedButMinSizeNotChecked = false;

    width = getWidth();
    height = getHeight();

    if ((minWidth > 0) && (width < minWidth))
    {
      width = minWidth;
    }

    if ((minHeight > 0) && (height < minHeight))
    {
      height = minHeight;
    }

    if ((width != getWidth()) || (height != getHeight()))
    {
      setSize(width, height);

      return false;
    }

    return true;
  }


  /**
   * Fix for internal frame dispose() to activate another internal frame in the same way as a call to doDefaultCloseAction().
   */
  public void dispose()
  {
    if (!isClosed)
    {
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      doDefaultCloseAction();

      return;
    }

    super.dispose();
  }


  /**
   * <p>If configured keep the modalToWindow also in the front when its blocking window is reactivated
   * as a result of clicking in a blocked window. Unless it is currently iconified. </p>
   */
  private void needToActivateModalToWindow()
  {
    if (JModalConfiguration.keepModalToWindowInFront())
    {
      JInternalFrame modalToOwner = getModalToOwner();

      if (modalToOwner != null)
      {
        modalToOwner.toFront();
        toFront();
      }
    }
  }
}
