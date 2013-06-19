package com.hp.hpl.logkv.demo.applet.modelwindow;


import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;



/**
 * <p>An extension of JFrame which supports window-specific modality. </p>
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
 * <p><b>JModalFrame</b> is based on the following ideas: </p>
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
 * <p>The frame uses the default icon image, which may vary with platform.
 * As usual you could set the icon with the standard JFrame method <code>setIconImage</code>.
 * Alternatively you could set a String with the resource location for
 * the default JModalFrame icon filed under the key <b>"swingx.frame.icon"</b> in the UIManager
 * or call {@link JModalConfiguration#setOptionalFrameIcon(java.awt.Image) setOptionalFrameIcon} in JModalConfiguration. </p>
 *
 * @author Jene Jasper
 * @version 2.0
 */
public class JModalFrame extends JFrame implements InputBlocker, CloseBlocker
{
  private Window modalToOwner;
  private Vector modalToWindowsAndInputBlockers;
  private Vector blockingWindows;
  private Vector blockedIFrames;
  private boolean notifiedModalToWindow;
  private Component returnFocus;

  private JPanel busyPanel;
  private Color blurColor;

  private Component oldGlassPane = null;
  private boolean oldGlassPaneVisible = false;
  private int minWidth, minHeight;

  /**
   * <p>Default constructor for modal frame. </p>
   */
  public JModalFrame()
  {
    this(null, null, "", true);
  }


  /**
   * <p>Constructor for modal frame. </p>
   *
   * @param modal indication if windows should be modal.
   */
  public JModalFrame(boolean modal)
  {
    this(null, null, "", modal);
  }


  /**
   * <p>Constructor for modal frame. </p>
   *
   * @param title frame title.
   */
  public JModalFrame(String title)
  {
    this(null, null, title, true);
  }


  /**
   * <p>Constructor for modal frame. </p>
   *
   * @param title frame title.
   * @param modal indication if windows should be modal.
   */
  public JModalFrame(String title, boolean modal)
  {
    this(null, null, title, modal);
  }


  /**
   * <p>Constructor for modal frame. </p>
   *
   * @param owner related window which could be blocked by this one.
   */
  public JModalFrame(Window owner)
  {
    this(owner, null, "", true);
  }


  /**
   * <p>Constructor for modal frame. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param modal indication if windows should be modal.
   */
  public JModalFrame(Window owner, boolean modal)
  {
    this(owner, null, "", modal);
  }


  /**
   * <p>Constructor for modal frame. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this frame.
   */
  public JModalFrame(Window owner, Component returnFocus)
  {
    this(owner, returnFocus, "", true);
  }


  /**
   * <p>Constructor for modal frame. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this frame.
   * @param modal indication if windows should be modal.
   */
  public JModalFrame(Window owner, Component returnFocus, boolean modal)
  {
    this(owner, returnFocus, "", modal);
  }


  /**
   * <p>Constructor for modal frame. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param title frame title.
   */
  public JModalFrame(Window owner, String title)
  {
    this(owner, null, title, true);
  }


  /**
   * <p>Constructor for modal frame. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param title frame title.
   * @param modal indication if windows should be modal.
   */
  public JModalFrame(Window owner, String title, boolean modal)
  {
    this(owner, null, title, modal);
  }


  /**
   * <p>Constructor for modal frame. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this frame.
   * @param title frame title.
   */
  public JModalFrame(Window owner, Component returnFocus, String title)
  {
    this(owner, returnFocus, title, true);
  }


  /**
   * <p>Constructor for modal frame. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this frame.
   * @param title frame title.
   * @param modal indication if windows should be modal.
   */
  public JModalFrame(Window owner, Component returnFocus, String title, boolean modal)
  {
    super(title);

    jmodalFrameInit(owner, returnFocus, modal);
  }


  /**
   * <p>Constructor for modal frame. </p>
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
  public JModalFrame(GraphicsConfiguration gc)
  {
    this(null, null, "", true, gc);
  }


  /**
   * <p>Constructor for modal frame. </p>
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
  public JModalFrame(boolean modal, GraphicsConfiguration gc)
  {
    this(null, null, "", modal, gc);
  }


  /**
   * <p>Constructor for modal frame. </p>
   *
   * @param title frame title.
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
  public JModalFrame(String title, GraphicsConfiguration gc)
  {
    this(null, null, title, true, gc);
  }


  /**
   * <p>Constructor for modal frame. </p>
   *
   * @param title frame title.
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
  public JModalFrame(String title, boolean modal, GraphicsConfiguration gc)
  {
    this(null, null, title, modal, gc);
  }


  /**
   * <p>Constructor for modal frame. </p>
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
  public JModalFrame(Window owner, GraphicsConfiguration gc)
  {
    this(owner, null, "", true, gc);
  }


  /**
   * <p>Constructor for modal frame. </p>
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
  public JModalFrame(Window owner, boolean modal, GraphicsConfiguration gc)
  {
    this(owner, null, "", modal, gc);
  }


  /**
   * <p>Constructor for modal frame. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this frame.
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
  public JModalFrame(Window owner, Component returnFocus, GraphicsConfiguration gc)
  {
    this(owner, returnFocus, "", true, gc);
  }


  /**
   * <p>Constructor for modal frame. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this frame.
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
  public JModalFrame(Window owner, Component returnFocus, boolean modal, GraphicsConfiguration gc)
  {
    this(owner, returnFocus, "", modal, gc);
  }


  /**
   * <p>Constructor for modal frame. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param title frame title.
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
  public JModalFrame(Window owner, String title, GraphicsConfiguration gc)
  {
    this(owner, null, title, true, gc);
  }


  /**
   * <p>Constructor for modal frame. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param title frame title.
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
  public JModalFrame(Window owner, String title, boolean modal, GraphicsConfiguration gc)
  {
    this(owner, null, title, modal, gc);
  }


  /**
   * <p>Constructor for modal frame. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this frame.
   * @param title frame title.
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
  public JModalFrame(Window owner, Component returnFocus, String title, GraphicsConfiguration gc)
  {
    this(owner, returnFocus, title, true, gc);
  }


  /**
   * <p>Constructor for modal frame. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this frame.
   * @param title frame title.
   * @param modal indication if windows should be modal.
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
   */
  public JModalFrame(Window owner, Component returnFocus, String title, boolean modal, GraphicsConfiguration gc)
  {
    super(title, gc);

    jmodalFrameInit(owner, returnFocus, modal);
  }


  /**
   * <p>Initialize modal frame. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this frame.
   * @param modal indication if windows should be modal.
   */
  private void jmodalFrameInit(Window owner, Component returnFocus, boolean modal)
  {
    synchronized (JModalFrame.this)
    {
      notifiedModalToWindow = true;
      modalToWindowsAndInputBlockers = new Vector();
      blockingWindows = new Vector();
      blockedIFrames = new Vector();

      if (modal && (owner != null))
      {
        modalToOwner = owner;
        modalToWindowsAndInputBlockers.add(owner);
      }
    }

    this.returnFocus = returnFocus;

    enableEvents(AWTEvent.WINDOW_EVENT_MASK | AWTEvent.COMPONENT_EVENT_MASK);

    Image optionalFrameIcon = JModalConfiguration.getOptionalFrameIcon();

    if (optionalFrameIcon != null)
    {
      setIconImage(optionalFrameIcon);
    }

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

  public void setClosable(JModalInternalFrame modalIFrame)
  {
    if (modalIFrame.isBusy()) 
    {
      if (!blockedIFrames.contains(modalIFrame))
      {
        blockedIFrames.add(modalIFrame);
      }
    }
    else
    {
      if (blockedIFrames.contains(modalIFrame))
      {
        blockedIFrames.remove(modalIFrame);
      }
    }
  }


  public boolean isClosable()
  {
    return (blockedIFrames.size() == 0);
  }


  /**
   * <p>Place the frame in the center of the screen. </p>
   */
  public void centerOfScreen()
  {
    Utils.centerOfScreen(this);
  }


  /**
   * <p>Try to center the frame in the owner window/frame. </p>
   */
  public void centerOfOwner()
  {
    Utils.centerOfOwner(this, getOwner());
  }


  /**
   * <p>Try to position the frame relative to the specified component. </p>
   *
   * @param child component where the frame should be positioned just below.
   */
  public void relativeToOwnerChild(Component child)
  {
    Utils.relativeToOwnerChild(this, getOwner(), child);
  }


  /**
   * <p>Set a minimal frame size. </p>
   *
   * @param dim minimal frame size.
   */
  public void setMinSize(Dimension dim)
  {
    setMinSize(dim.width, dim.height);
  }


  /**
   * <p>Set a minimal frame size. </p>
   *
   * @param width minimal frame width.
   * @param height minimal frame height.
   */
  public void setMinSize(int width, int height)
  {
    minWidth = width;
    minHeight = height;
  }


  /**
   * <p>Apparently this frame is closed and thus owner is no longer blocked by this one. </p>
   */
  private void restoreOwner()
  {
    synchronized (JModalFrame.this)
    {
      if ((modalToWindowsAndInputBlockers.size() > 0) && !notifiedModalToWindow)
      {
        JModalHelper.unblockWindows(modalToWindowsAndInputBlockers, this, returnFocus);

        notifiedModalToWindow = true;
      }
    }
  }


  /**
   * <p>Overrule <code>DefaultCloseOperation</code> when frame is blocked. </p>
   *
   * @return the default close operation.
   */
  public int getDefaultCloseOperation()
  {
    if (isBusy() || !isClosable())
    {
      return WindowConstants.DO_NOTHING_ON_CLOSE;
    }
    else
    {
      return super.getDefaultCloseOperation();
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
      case WindowEvent.WINDOW_ACTIVATED:
        checkActivationAllowed(windowEvent);
        break;

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
   * <p>Check if the frame may be activated. Otherwise activate one of the blocking windows. </p>
   *
   * @param windowEvent the event that triggered this method call.
   */
  private void checkActivationAllowed(WindowEvent windowEvent)
  {
    super.processWindowEvent(windowEvent);

    if (isBusy())
    {
      JModalHelper.activateFirstAvailableBlockingWindow(blockingWindows, windowEvent);
    }
  }


  /**
   * <p>Check if the frame may be activated. Otherwise activate one of the blocking windows. </p>
   */
  private void checkActivationAllowed()
  {
    if (isBusy())
    {
      JModalHelper.activateFirstAvailableBlockingWindow(blockingWindows, null);
    }
  }


  /**
   * <p>Try to dispose the frame. If the frame is currently blocked it is not allowed to. </p>
   *
   * @param windowEvent the event that triggered this method call.
   */
  private void tryToDispose(WindowEvent windowEvent)
  {
    if (isBusy() || !isClosable())
    {
      return;
    }

    close(windowEvent);
  }


  /**
   * <p>Close the frame and unblock the owner. </p>
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
   * <p>Notify threads that are waiting for this frame to close, that the frame is closing. </p>
   */
  synchronized final public void release()
  {
    notifyAll();
  }


  /**
   * @deprecated As of JDK version 1.1,
   * replaced by <code>setVisible(boolean)</code>.
   *
   * <p>Handle blocking of owner when this frame is shown. </p>
   *
   * <p><b>Note:</b>Unlike JDialog calling this method doesn't halt the calling thread.
   * To create the same effect, call the method {@link #waitForClose() waitForClose}
   * afterwards. </p>
   */
  public void show()
  {
    synchronized (JModalFrame.this)
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
   * <p>Disable closing of frame when child window/frame has this frame blocked. </p>
   *
   * <p><b>Note:</b>Unlike JDialog calling this method doesn't halt the calling thread.
   * To create the same effect, call the method {@link #waitForClose() waitForClose}
   * afterwards. </p>
   *
   * @param visible new frame visibility.
   */
  public void setVisible(boolean visible)
  {
    if (!visible)
    {
      if (isBusy() || !isClosable())
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
   * <p>In case the frame is resized make sure that isn't resized smaller than the minimum
   * width and/or height. </p>
   *
   * @param componentEvent ComponentEvent
   */
  protected void processComponentEvent(ComponentEvent componentEvent)
  {
    int eventId = componentEvent.getID();

    switch (eventId)
    {
      case ComponentEvent.COMPONENT_RESIZED:
        if (!hasAtLeastMinimumSize())
        {
          return;
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
   * <p>Compare the frame size against the minimum width and/or height, when the minimum size is specified. </p>
   *
   * @return boolean Indication whether size is at least the minimum width and/or height.
   */
  private boolean hasAtLeastMinimumSize()
  {
    int width, height;

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
}
