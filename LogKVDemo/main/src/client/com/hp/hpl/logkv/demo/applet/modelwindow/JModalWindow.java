package com.hp.hpl.logkv.demo.applet.modelwindow;


import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;



/**
 * <p>An extension of JWindow which supports window-specific modality. </p>
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
 * <p><b>JModalWindow</b> is based on the following ideas: </p>
 *
 * <ol>
 *   <li>from Sandip Chitale found at <a href="http://www.jguru.com/jguru/faq/">jGuru FAQ</a>
 *       to disable the <code>modalOwner</code> (but this results in flickering windows because of
 *       the <code>peer.disable()</code> call. </li>
 *   <li>from <a href="mailto:dsyrstad@vscorp.com">Dan Syrstad</a> to make a JFrame "busy". </li>
 *   <li>from <a href="mailto:msmits@quobell.nl">Maks Smits</a> to blur the <code>modalOwner</code>. </li>
 *   <li>from Ronald Kurz to blur using an alpha channel. </li>
 * </ol>
 *
 * <p>Because the placement of the window could block the view on a window below it,
 * I added the possiblity to drag a window. </p>
 *
 * @author Jene Jasper
 * @version 2.0
 */
public class JModalWindow extends JWindow implements InputBlocker
{
  /**
   * <p>Distance from border which activates drag cursor. </p>
   */
  final public static int DEFAULT_DRAG_BORDER_DISTANCE = 1;

  private static Window sharedOwner;

  private Window modalToOwner;
  private Vector modalToWindowsAndInputBlockers;
  private Vector blockingWindows;
  private boolean notifiedModalToWindow;
  private Component returnFocus;

  private JPanel contentPanel;
  private Point priorDragLocation;
  private Point cursorDragAnchor;
  private int windowDragBorderDistance;

  private JPanel busyPanel;
  private Color blurColor;

  private Component oldGlassPane = null;
  private boolean oldGlassPaneVisible = false;

  /**
   * <p>Default constructor for modal window. </p>
   */
  public JModalWindow()
  {
    this(null, null, true);
  }


  /**
   * <p>Constructor for modal window. </p>
   *
   * @param modal indication if windows should be modal.
   */
  public JModalWindow(boolean modal)
  {
    this(null, null, modal);
  }


  /**
   * <p>Constructor for modal window. </p>
   *
   * @param owner related window which could be blocked by this one.
   */
  public JModalWindow(Window owner)
  {
    this(owner, null, true);
  }


  /**
   * <p>Constructor for modal window. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param modal indication if windows should be modal.
   */
  public JModalWindow(Window owner, boolean modal)
  {
    this(owner, null, modal);
  }


  /**
   * <p>Constructor for modal window. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this window.
   */
  public JModalWindow(Window owner, Component returnFocus)
  {
    this(owner, returnFocus, true);
  }


  /**
   * <p>Constructor for modal window. </p>
   *
   * @param gc the <code>GraphicsConfiguration</code> that is used
   *     to construct the new window with; if gc is <code>null</code>,
   *     the system default <code>GraphicsConfiguration</code>
   *    is assumed, unless <code>owner</code> is also null, in which
   *          case the <code>GraphicsConfiguration</code> from the
   *          shared owner frame will be used.
   * @throws HeadlessException if
   *         <code>GraphicsEnvironment.isHeadless()</code> returns true.
   * @throws IllegalArgumentException if <code>gc</code> is not from
   *          a screen device.
   *
   * @see java.awt.GraphicsEnvironment#isHeadless
   */
  public JModalWindow(GraphicsConfiguration gc)
  {
    this(null, null, true, gc);
  }


  /**
   * <p>Constructor for modal window. </p>
   *
   * @param modal indication if windows should be modal.
   * @param gc the <code>GraphicsConfiguration</code> that is used
   *     to construct the new window with; if gc is <code>null</code>,
   *     the system default <code>GraphicsConfiguration</code>
   *    is assumed, unless <code>owner</code> is also null, in which
   *          case the <code>GraphicsConfiguration</code> from the
   *          shared owner frame will be used.
   * @throws HeadlessException if
   *         <code>GraphicsEnvironment.isHeadless()</code> returns true.
   * @throws IllegalArgumentException if <code>gc</code> is not from
   *          a screen device.
   *
   * @see java.awt.GraphicsEnvironment#isHeadless
   */
  public JModalWindow(boolean modal, GraphicsConfiguration gc)
  {
    this(null, null, modal, gc);
  }


  /**
   * <p>Constructor for modal window. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param gc the <code>GraphicsConfiguration</code> that is used
   *     to construct the new window with; if gc is <code>null</code>,
   *     the system default <code>GraphicsConfiguration</code>
   *    is assumed, unless <code>owner</code> is also null, in which
   *          case the <code>GraphicsConfiguration</code> from the
   *          shared owner frame will be used.
   * @throws HeadlessException if
   *         <code>GraphicsEnvironment.isHeadless()</code> returns true.
   * @throws IllegalArgumentException if <code>gc</code> is not from
   *          a screen device.
   *
   * @see java.awt.GraphicsEnvironment#isHeadless
   */
  public JModalWindow(Window owner, GraphicsConfiguration gc)
  {
    this(owner, null, true, gc);
  }


  /**
   * <p>Constructor for modal window. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param modal indication if windows should be modal.
   * @param gc the <code>GraphicsConfiguration</code> that is used
   *     to construct the new window with; if gc is <code>null</code>,
   *     the system default <code>GraphicsConfiguration</code>
   *    is assumed, unless <code>owner</code> is also null, in which
   *          case the <code>GraphicsConfiguration</code> from the
   *          shared owner frame will be used.
   * @throws HeadlessException if
   *         <code>GraphicsEnvironment.isHeadless()</code> returns true.
   * @throws IllegalArgumentException if <code>gc</code> is not from
   *          a screen device.
   *
   * @see java.awt.GraphicsEnvironment#isHeadless
   */
  public JModalWindow(Window owner, boolean modal, GraphicsConfiguration gc)
  {
    this(owner, null, modal, gc);
  }


  /**
   * <p>Constructor for modal window. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this window.
   * @param gc the <code>GraphicsConfiguration</code> that is used
   *     to construct the new window with; if gc is <code>null</code>,
   *     the system default <code>GraphicsConfiguration</code>
   *    is assumed, unless <code>owner</code> is also null, in which
   *          case the <code>GraphicsConfiguration</code> from the
   *          shared owner frame will be used.
   * @throws HeadlessException if
   *         <code>GraphicsEnvironment.isHeadless()</code> returns true.
   * @throws IllegalArgumentException if <code>gc</code> is not from
   *          a screen device.
   *
   * @see java.awt.GraphicsEnvironment#isHeadless
   */
  public JModalWindow(Window owner, Component returnFocus, GraphicsConfiguration gc)
  {
    this(owner, returnFocus, true, gc);
  }


  /**
   * <p>Constructor for modal window. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this window.
   * @param modal indication if windows should be modal.
   */
  public JModalWindow(Window owner, Component returnFocus, boolean modal)
  {
    super((owner == null) ? getSharedOwnerFrame() : owner);

    jmodalWindowInit(owner, returnFocus, modal);
  }


  /**
   * <p>Constructor for modal window. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this window.
   * @param modal indication if windows should be modal.
   * @param gc the <code>GraphicsConfiguration</code> that is used
   *     to construct the new window with; if gc is <code>null</code>,
   *     the system default <code>GraphicsConfiguration</code>
   *    is assumed, unless <code>owner</code> is also null, in which
   *          case the <code>GraphicsConfiguration</code> from the
   *          shared owner frame will be used.
   * @throws HeadlessException if
   *         <code>GraphicsEnvironment.isHeadless()</code> returns true.
   * @throws IllegalArgumentException if <code>gc</code> is not from
   *          a screen device.
   *
   * @see java.awt.GraphicsEnvironment#isHeadless
   */
  public JModalWindow(Window owner, Component returnFocus, boolean modal, GraphicsConfiguration gc)
  {
    super((owner == null) ? getSharedOwnerFrame() : owner, gc);

    jmodalWindowInit(owner, returnFocus, modal);
  }


  /**
   * <p>Initialize modal window. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this window.
   * @param modal indication if windows should be modal.
   */
  private void jmodalWindowInit(Window owner, Component returnFocus, boolean modal)
  {
    this.returnFocus = returnFocus;

    windowDragBorderDistance = JModalConfiguration.getWindowDragBorderDistance();

    contentPanel = new JPanel();

    contentPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

    contentPanel.setLayout(new BorderLayout()
    {
      public void addLayoutComponent(Component comp, Object constraints)
      {
        if (constraints == null)
        {
          constraints = BorderLayout.CENTER;
        }

        super.addLayoutComponent(comp, constraints);
      }
    });

    setContentPane(contentPanel);

    synchronized (JModalWindow.this)
    {
      notifiedModalToWindow = true;
      modalToWindowsAndInputBlockers = new Vector();
      blockingWindows = new Vector();

      if (modal && (owner != null))
      {
        modalToOwner = owner;
        modalToWindowsAndInputBlockers.add(owner);
      }
    }

    enableEvents(AWTEvent.WINDOW_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);

    initBusyPanel();
  }


  /**
   * <p>Helper method for JModalHelper to get a hold of the possible owner that this frame is modal to. </p>
   *
   * @return Window owner
   */
  protected Window getModalToOwner()
  {
    return modalToOwner;
  }


  /**
   * <p>Default owner for windows that don't have a physical owner. </p>
   *
   * @return default owner.
   */
  protected static Window getSharedOwnerFrame()
  {
    if (sharedOwner == null)
    {
      sharedOwner = (new JWindow()).getOwner();
    }

    return sharedOwner;
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
    }
  }


  public void addAdditionalModalToComponent(InputBlocker inputBlocker)
  {
    if ((inputBlocker != null) && !modalToWindowsAndInputBlockers.contains(inputBlocker))
    {
      modalToWindowsAndInputBlockers.add(inputBlocker);
    }
  }


  public boolean activateFirstAvailableBlockingWindow(WindowEvent windowEvent)
  {
    return JModalHelper.activateFirstAvailableBlockingWindow(blockingWindows, windowEvent);
  }


  public boolean isBusy()
  {
    return (blockingWindows.size() > 0);
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
  }


  /**
   * <p>Place the window in the center of the screen. </p>
   */
  public void centerOfScreen()
  {
    Utils.centerOfScreen(this);
  }


  /**
   * <p>Try to center the window in the owner window/frame. </p>
   */
  public void centerOfOwner()
  {
    Utils.centerOfOwner(this, getOwner());
  }


  /**
   * <p>Try to position the window relative to the specified component. </p>
   *
   * @param child component where the window should be positioned just below.
   */
  public void relativeToOwnerChild(Component child)
  {
    Utils.relativeToOwnerChild(this, getOwner(), child);
  }


  /**
   * <p>Apparently this window is closed and thus owner is no longer blocked by this one. </p>
   */
  private void restoreOwner()
  {
    synchronized (JModalWindow.this)
    {
      if ((modalToWindowsAndInputBlockers.size() > 0) && !notifiedModalToWindow)
      {
        JModalHelper.unblockWindows(modalToWindowsAndInputBlockers, this, returnFocus);

        notifiedModalToWindow = true;
      }
    }
  }


  /**
   * <p>Determine whether the window should be dragged or the drag cursor should be turned on or off. </p>
   *
   * @param mouseEvent the event that triggered this method call.
   */
  protected void processMouseMotionEvent(MouseEvent mouseEvent)
  {
    switch (mouseEvent.getID())
    {
      case MouseEvent.MOUSE_MOVED:
        checkDragZone(mouseEvent);
        break;
      case MouseEvent.MOUSE_DRAGGED:
        dragWindow(mouseEvent);
        break;
      default:
        break;
    }

    super.processMouseMotionEvent(mouseEvent);
  }


  /**
   * <p>Determine based on the mouse position if the drag cursor should be turned on or off. </p>
   *
   * @param mouseEvent the event that triggered this method call.
   */
  private void checkDragZone(MouseEvent mouseEvent)
  {
    if (!JModalConfiguration.isWindowDraggingEnabled())
    {
      return;
    }

    priorDragLocation = mouseEvent.getPoint();
    cursorDragAnchor = null;

    if ((mouseEvent.getX() < windowDragBorderDistance) || (mouseEvent.getX() >= getWidth() - windowDragBorderDistance))
    {
      this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }
    else if ((mouseEvent.getY() < windowDragBorderDistance) || (mouseEvent.getY() >= getHeight() - windowDragBorderDistance))
    {
      this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }
    else
    {
      this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }


  /**
   * <p>Determine based on the mouse position the new window location. </p>
   *
   * <p>Note: It is no longer possible to drag the window off the screen. </p>
   *
   * @param mouseEvent the event that triggered this method call.
   */
  private void dragWindow(MouseEvent mouseEvent)
  {
    if (!JModalConfiguration.isWindowDraggingEnabled())
    {
      return;
    }

    Point cursorRelativePosition = mouseEvent.getPoint();

    if (cursorDragAnchor == null)
    {
      cursorDragAnchor = cursorRelativePosition;
    }

    int x, y, deltaX, deltaY;

    if (this.getCursor().equals(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR)))
    {
      if (priorDragLocation == null)
      {
        priorDragLocation = mouseEvent.getPoint();
      }
      else
      {
        deltaX = mouseEvent.getX() - priorDragLocation.x;
        deltaY = mouseEvent.getY() - priorDragLocation.y;

        deltaX += cursorRelativePosition.x - cursorDragAnchor.x;
        deltaY += cursorRelativePosition.y - cursorDragAnchor.y;

        x = getX() + deltaX;
        y = getY() + deltaY;

        if ((x != getX()) || (y != getY()))
        {
          Utils.keepWindowPartiallyOnScreen(this, x, y);
        }

        priorDragLocation = null;
      }
    }
  }


  /**
   * <p>Monitor window events and trigger appropriate actions. </p>
   *
   * @param windowEvent the event that triggered this method call.
   */
  protected void processWindowEvent(WindowEvent windowEvent)
  {
    switch (windowEvent.getID())
    {
      case WindowEvent.WINDOW_CLOSING:
        tryToDispose(windowEvent);
        break;
      case WindowEvent.WINDOW_CLOSED:
        close(windowEvent);
        break;
      default:
        super.processWindowEvent(windowEvent);
        break;
    }
  }


  /**
   * <p>Try to dispose the window. If the window is currently blocked it is not allowed to. </p>
   *
   * @param windowEvent the event that triggered this method call.
   */
  private void tryToDispose(WindowEvent windowEvent)
  {
    if (isBusy())
    {
      return;
    }
    else
    {
      dispose();
    }

    super.processWindowEvent(windowEvent);
  }


  /**
   * <p>Close the window and unblock the owner. </p>
   *
   * @param windowEvent the event that triggered this method call.
   */
  private void close(WindowEvent windowEvent)
  {
    restoreOwner();
    release();

    super.processWindowEvent(windowEvent);
  }


  /**
   * <p>Option to make another thread wait for this window to close. </p>
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
   * <p>Notify threads that are waiting for this window to close, that the window is closing. </p>
   */
  synchronized final public void release()
  {
    notifyAll();
  }


  /**
   * <p>Paint a border around the window. </p>
   *
   * @param gfx window <code>Graphics</code>.
   */
  public void paint(Graphics gfx)
  {
    gfx.draw3DRect(0, 0, getWidth(), getHeight(), false);

    super.paint(gfx);
  }


  /**
   * <p>Set the window background color. </p>
   *
   * @param color background color.
   */
  public void setBackground(Color color)
  {
    super.setBackground(color);

    this.contentPanel.setBackground(color);
  }


  /**
   * <p>Set the window foreground color. </p>
   *
   * @param color foreground color.
   */
  public void setForeground(Color color)
  {
    super.setForeground(color);

    this.contentPanel.setForeground(color);
  }


  /**
   * @deprecated As of JDK version 1.1,
   * replaced by <code>setVisible(boolean)</code>.
   *
   * <p>Handle blocking of owner when this window is shown. </p>
   *
   * <p><b>Note:</b>Unlike JDialog calling this method doesn't halt the calling thread.
   * To create the same effect, call the method {@link #waitForClose() waitForClose}
   * afterwards. </p>
   */
  public void show()
  {
    synchronized (JModalWindow.this)
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
   * <p>Disable closing of window when child window/frame has this window blocked. </p>
   *
   * <p><b>Note:</b>Unlike JDialog calling this method doesn't halt the calling thread.
   * To create the same effect, call the method {@link #waitForClose() waitForClose}
   * afterwards. </p>
   *
   * @param visible new window visibility.
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


  /**
   * <p>Check if the window may be activated. Otherwise activate one of the blocking windows. </p>
   */
  private void checkActivationAllowed()
  {
    if (isBusy())
    {
      JModalHelper.activateFirstAvailableBlockingWindow(blockingWindows, null);
    }
  }
}
