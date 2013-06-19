package com.hp.hpl.logkv.demo.applet.modelwindow;


import java.awt.*;


/**
 * <p>An extension of JModalFrame which supports application-wide modality.  </p>
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
 * <p><b>JModalDialog</b> was created to implement the same functionality as the JDialog,
 * in other words blocking all active frames and windows.
 * But use the same approach as the JModalFrame and thus the visual effect is the same. </p>
 *
 * @author Jene Jasper
 * @version 2.0
 */
public class JModalDialog extends JModalFrame
{
  /**
   * <p>Default constructor for modal dialog. </p>
   */
  public JModalDialog()
  {
    this(null, null, "");
  }


  /**
   * <p>Constructor for modal dialog. </p>
   *
   * @param title frame title.
   */
  public JModalDialog(String title)
  {
    this(null, null, title);
  }


  /**
   * <p>Constructor for modal dialog. </p>
   *
   * @param owner related window which could be blocked by this one.
   */
  public JModalDialog(Window owner)
  {
    this(owner, null, "");
  }


  /**
   * <p>Constructor for modal dialog. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this frame.
   */
  public JModalDialog(Window owner, Component returnFocus)
  {
    this(owner, returnFocus, "");
  }


  /**
   * <p>Constructor for modal dialog. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param title frame title.
   */
  public JModalDialog(Window owner, String title)
  {
    this(owner, null, title);
  }


  /**
   * <p>Constructor for modal dialog. </p>
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
  public JModalDialog(GraphicsConfiguration gc)
  {
    this(null, null, "", gc);
  }


  /**
   * <p>Constructor for modal dialog. </p>
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
  public JModalDialog(String title, GraphicsConfiguration gc)
  {
    this(null, null, title, gc);
  }


  /**
   * <p>Constructor for modal dialog. </p>
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
  public JModalDialog(Window owner, GraphicsConfiguration gc)
  {
    this(owner, null, "", gc);
  }


  /**
   * <p>Constructor for modal dialog. </p>
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
  public JModalDialog(Window owner, Component returnFocus, GraphicsConfiguration gc)
  {
    this(owner, returnFocus, "", gc);
  }


  /**
   * <p>Constructor for modal dialog. </p>
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
  public JModalDialog(Window owner, String title, GraphicsConfiguration gc)
  {
    this(owner, null, title, gc);
  }


  /**
   * <p>Constructor for modal dialog. </p>
   *
   * @param owner related window which could be blocked by this one.
   * @param returnFocus component in owner which should regain focus after closing this frame.
   * @param title frame title.
   */
  public JModalDialog(Window owner, Component returnFocus, String title)
  {
    super(owner, returnFocus, title, true);

    markAllWindows();
  }


  /**
   * <p>Constructor for modal dialog. </p>
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
  public JModalDialog(Window owner, Component returnFocus, String title, GraphicsConfiguration gc)
  {
    super(owner, returnFocus, title, true, gc);

    markAllWindows();
  }


  /**
   * <p>Iterate all available frames. </p>
   */
  private void markAllWindows()
  {
    Frame[] frames = getFrames();

    for (int i = 0; i < frames.length; i++)
    {
      markWindowAndChildren(frames[i]);
    }
  }


  /**
   * <p>If the window isn't disposed yet then add it to the list of windows that should be blocked. </p>
   *
   * <p>Apply the same check recursively to the windows children. </p>
   *
   * @param window Window
   */
  private void markWindowAndChildren(Window window)
  {
    if (window.isDisplayable())
    {
      Window[] children = window.getOwnedWindows();

      for (int i = 0; i < children.length; i++)
      {
        markWindowAndChildren(children[i]);
      }

      if (!window.equals(JModalWindow.getSharedOwnerFrame()) && !this.equals(window))
      {
        addAdditionalModalToWindow(window);
      }
    }
  }
}
