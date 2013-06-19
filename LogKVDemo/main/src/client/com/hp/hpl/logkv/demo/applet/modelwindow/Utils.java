package com.hp.hpl.logkv.demo.applet.modelwindow;


import java.awt.*;
import java.awt.image.*;
import java.net.*;
import java.util.*;

import javax.swing.*;
import javax.swing.plaf.basic.*;


/**
 * <p>Utility class. </p>
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
public class Utils
{
  /**
   * Safety margin for window positioning in case of operating system toolbars and so on.
   */
  final public static int DEFAULT_SCREEN_SAFETY_MARGIN = 100;

  /**
   * Safety margin for internal positioning.
   */
  final public static int DEFAULT_DESKTOP_SAFETY_MARGIN = 2;

  final private static HashMap IMAGE_CACHE = new HashMap();

  private static Utils instance;

  final private static int STOP_SIGN_SIZE = 32;
  final private static int[] POLYGON_SIGN_X = new int[]
                                              {8, 23, 31, 31, 23, 8, 0, 0};
  final private static int[] POLYGON_SIGN_Y = new int[]
                                              {0, 0, 8, 23, 31, 31, 23, 8};
  final private static int[] POLYGON_SIGN_BORDER_X = new int[]
                                                     {8, 23, 30, 30, 23, 8, 1, 1};
  final private static int[] POLYGON_SIGN_BORDER_Y = new int[]
                                                     {1, 1, 8, 23, 30, 30, 23, 8};
  final private static int[] POLYGON_SIGN_S_X = new int[]
                                                {4, 5, 7, 8, 8, 4, 4, 5, 7, 8};
  final private static int[] POLYGON_SIGN_S_Y = new int[]
                                                {21, 22, 22, 21, 17, 13, 9, 8, 8, 9};
  final private static int[] POLYGON_SIGN_T_X = new int[]
                                                {10, 14, 12, 12};
  final private static int[] POLYGON_SIGN_T_Y = new int[]
                                                {8, 8, 8, 22};
  final private static int[] POLYGON_SIGN_O_X = new int[]
                                                {16, 17, 19, 20, 20, 19, 17, 16, 16};
  final private static int[] POLYGON_SIGN_O_Y = new int[]
                                                {9, 8, 8, 9, 21, 22, 22, 21, 9};
  final private static int[] POLYGON_SIGN_P_X = new int[]
                                                {22, 22, 25, 26, 26, 25, 22};
  final private static int[] POLYGON_SIGN_P_Y = new int[]
                                                {22, 8, 8, 9, 14, 15, 15};
  final private static int[] POLYGON_BORDER_S_X = new int[]
                                                  {3, 5, 7, 9, 9, 5, 5, 7, 8, 9, 7, 5, 3, 3, 7, 7, 5, 4, 3};
  final private static int[] POLYGON_BORDER_S_Y = new int[]
                                                  {21, 23, 23, 21, 17, 13, 9, 9, 10, 9, 7, 7, 9, 13, 17, 21, 21, 20, 21};
  final private static int[] POLYGON_BORDER_T_X = new int[]
                                                  {9, 15, 15, 13, 13, 11, 11, 9, 9};
  final private static int[] POLYGON_BORDER_T_Y = new int[]
                                                  {7, 7, 9, 9, 23, 23, 9, 9, 7};
  final private static int[] POLYGON_BORDER_O_X = new int[]
                                                  {15, 17, 19, 21, 21, 19, 17, 15, 15};
  final private static int[] POLYGON_BORDER_O_Y = new int[]
                                                  {9, 7, 7, 9, 21, 23, 23, 21, 9};
  final private static int[] POLYGON_INNERBORDER_O_X = new int[]
                                                       {17, 19, 19, 17, 17};
  final private static int[] POLYGON_INNERBORDER_O_Y = new int[]
                                                       {9, 9, 21, 21, 9};
  final private static int[] POLYGON_OUTERBORDER_P_X = new int[]
                                                       {21, 21, 25, 27, 27, 25, 23, 23, 21};
  final private static int[] POLYGON_OUTERBORDER_P_Y = new int[]
                                                       {23, 7, 7, 9, 14, 16, 16, 23, 23};
  final private static int[] POLYGON_INNERBORDER_P_X = new int[]
                                                       {23, 25, 25, 23, 23};
  final private static int[] POLYGON_INNERBORDER_P_Y = new int[]
                                                       {9, 9, 14, 14, 9};

  final private static int MISSING_IMAGE_SIGN_SIZE = 16;
  final private static int CROSS_TOP_LEFT_X = 2;
  final private static int CROSS_TOP_LEFT_Y = 2;
  final private static int CROSS_BOTTOM_RIGHT_X = MISSING_IMAGE_SIGN_SIZE - 3;
  final private static int CROSS_BOTTOM_RIGHT_Y = MISSING_IMAGE_SIGN_SIZE - 3;
  final private static int CROSS_TOP_RIGHT_X = MISSING_IMAGE_SIGN_SIZE - 3;
  final private static int CROSS_TOP_RIGHT_Y = 2;
  final private static int CROSS_BOTTOM_LEFT_X = 2;
  final private static int CROSS_BOTTOM_LEFT_Y = MISSING_IMAGE_SIGN_SIZE - 3;

  final private static Color DARK_RED = new Color(173, 57, 57);
  final private static Color MEDIUM_RED = new Color(198, 115, 115);
  final private static Color LIGHT_RED = new Color(214, 156, 156);
  final private static Color WHITE = Color.white;

  /**
   * <p>Private default constructor to create the notion of a singleton. </p>
   */
  private Utils()
  {
  }


  /**
   * <p>Retrieval of the <code>Utils</code> singleton instance. </p>
   *
   * @return instance of <code>Utils</code> object.
   */
  public static Utils getInstance()
  {
    if (instance == null)
    {
      instance = new Utils();
    }

    return instance;
  }


  /**
   * <p>Return a component's window. </p>
   *
   * <p><b>Note:</b> Fix for SwingUtilities.windowForComponent which returns null for iconified internal frames. </p>
   *
   * @param cmp Component
   * @return Window
   */
  public static Window windowForComponent(Component cmp)
  {
    Window window = SwingUtilities.windowForComponent(cmp);

    if (window == null)
    {
      if (cmp instanceof JInternalFrame)
      {
        Component desktop = ((JInternalFrame)cmp).getDesktopPane();

        if (desktop != null)
        {
          window = SwingUtilities.windowForComponent(desktop);
        }
      }
    }

    if (window == null)
    {
      Component parent = parentForComponentRootPane(cmp);

      if (parent instanceof JInternalFrame)
      {
        Component desktop = ((JInternalFrame)parent).getDesktopPane();

        if (desktop != null)
        {
          window = SwingUtilities.windowForComponent(desktop);
        }
      }
    }

    return window;
  }


  /**
   * <p>Retrieve the component that is the parent of the rootpane in which the supplied component resides. </p>
   *
   * @param cmp Component the parent of the rootpane in which the supplied component resides.
   * @return Component the component for which we want to locate its parent: window, frame or internal frame.
   */
  public static Component parentForComponentRootPane(Component cmp)
  {
    JRootPane rootPane = SwingUtilities.getRootPane(cmp);

    if (rootPane == null)
    {
      return null;
    }

    return rootPane.getParent();
  }


  /**
   * <p>A simple minded look and feel change: ask each node in the tree to updateUI()
   * - that is, to initialize its UI property with the current look and feel.
   * (Copy of swing version)</p>
   *
   * @param cmp component which UI must be updated.
   */
  public static void updateComponentTreeUI(Component cmp)
  {
    updateComponentTreeUI0(cmp);

    cmp.invalidate();
    cmp.validate();
    cmp.repaint();
  }


  /**
   * <p>A simple minded look and feel change: ask each node in the tree to updateUI()
   * - that is, to initialize its UI property with the current look and feel.
   * (Copy of swing version)</p>
   *
   * <p>Note: With one minor change, that the component itself is changed last to make
   * changes to children immediately visible instead of requiring a repaint of the window or frame
   * triggered by the user. </p>
   *
   * @param cmp component which UI must be updated.
   */
  private static void updateComponentTreeUI0(Component cmp)
  {
    Component[] children = null;

    if (cmp instanceof JMenu)
    {
      children = ((JMenu)cmp).getMenuComponents();
    }
    else if (cmp instanceof Container)
    {
      children = ((Container)cmp).getComponents();
    }

    if (children != null)
    {
      for (int i = 0; i < children.length; i++)
      {
        updateComponentTreeUI0(children[i]);
      }
    }

    if (cmp instanceof JComponent)
    {
      ((JComponent)cmp).updateUI();
    }
  }


  /**
   * <p>Create a cursor for blocked modal component.
   * The default one which is a STOP sign.
   * Alternatively you could set a String with the resource location for
   * the default busy icon filed under the key <b>"swingx.busy.cursor"</b> in the UIManager. </p>
   *
   * @return busy cursor.
   */
  public static Cursor getBusyCursor()
  {
    Cursor busyCursor;
    Image cursor;
    String optionalCursorIcon = UIManager.getString("swingx.busy.cursor");

    if (optionalCursorIcon == null)
    {
      cursor = getStopImage();
    }
    else
    {
      cursor = getIcon(optionalCursorIcon).getImage();
    }

    busyCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0, 0), "BUSY");

    if (busyCursor.getType() != Cursor.CUSTOM_CURSOR)
    {
      busyCursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }

    return busyCursor;
  }


  /**
   * <p>Create a default image in case the specified image couldn't be found. </p>
   *
   * @return default image.
   */
  public static Image getMissingImage()
  {
    int size = MISSING_IMAGE_SIGN_SIZE;
    Image rv = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
    Graphics gfx = rv.getGraphics();

    gfx.setColor(Color.red);
    gfx.fillRect(0, 0, size, size);
    gfx.setColor(Color.white);

    gfx.drawLine(CROSS_TOP_LEFT_X, CROSS_TOP_LEFT_Y, CROSS_BOTTOM_RIGHT_X, CROSS_BOTTOM_RIGHT_Y);
    gfx.drawLine(CROSS_TOP_LEFT_X + 1, CROSS_TOP_LEFT_Y, CROSS_BOTTOM_RIGHT_X, CROSS_BOTTOM_RIGHT_Y - 1);
    gfx.drawLine(CROSS_TOP_LEFT_X, CROSS_TOP_LEFT_Y + 1, CROSS_BOTTOM_RIGHT_X - 1, CROSS_BOTTOM_RIGHT_Y);

    gfx.drawLine(CROSS_BOTTOM_LEFT_X, CROSS_BOTTOM_LEFT_Y, CROSS_TOP_RIGHT_X, CROSS_TOP_RIGHT_Y);
    gfx.drawLine(CROSS_BOTTOM_LEFT_X, CROSS_BOTTOM_LEFT_Y - 1, CROSS_TOP_RIGHT_X - 1, CROSS_TOP_RIGHT_Y);
    gfx.drawLine(CROSS_BOTTOM_LEFT_X + 1, CROSS_BOTTOM_LEFT_Y, CROSS_TOP_RIGHT_X, CROSS_TOP_RIGHT_Y + 1);

    return rv;
  }


  /**
   * <p>Create a STOP sign image. </p>
   *
   * @return STOP sign image.
   */
  public static Image getStopImage()
  {
    int size = STOP_SIGN_SIZE;
    Image img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
    Graphics gfx = img.getGraphics();

    gfx.setColor(DARK_RED);
    gfx.fillPolygon(POLYGON_SIGN_X, POLYGON_SIGN_Y, POLYGON_SIGN_X.length);

    gfx.setColor(LIGHT_RED);
    gfx.drawPolygon(POLYGON_SIGN_X, POLYGON_SIGN_Y, POLYGON_SIGN_X.length);

    gfx.setColor(WHITE);
    gfx.drawPolygon(POLYGON_SIGN_BORDER_X, POLYGON_SIGN_BORDER_Y, POLYGON_SIGN_BORDER_X.length);
    gfx.drawPolyline(POLYGON_SIGN_S_X, POLYGON_SIGN_S_Y, POLYGON_SIGN_S_X.length);
    gfx.drawPolyline(POLYGON_SIGN_T_X, POLYGON_SIGN_T_Y, POLYGON_SIGN_T_X.length);
    gfx.drawPolyline(POLYGON_SIGN_O_X, POLYGON_SIGN_O_Y, POLYGON_SIGN_O_X.length);
    gfx.drawPolyline(POLYGON_SIGN_P_X, POLYGON_SIGN_P_Y, POLYGON_SIGN_P_X.length);

    gfx.setColor(MEDIUM_RED);
    gfx.drawPolyline(POLYGON_BORDER_S_X, POLYGON_BORDER_S_Y, POLYGON_BORDER_S_X.length);
    gfx.drawPolyline(POLYGON_BORDER_T_X, POLYGON_BORDER_T_Y, POLYGON_BORDER_T_X.length);
    gfx.drawPolyline(POLYGON_BORDER_O_X, POLYGON_BORDER_O_Y, POLYGON_BORDER_O_X.length);
    gfx.drawPolyline(POLYGON_INNERBORDER_O_X, POLYGON_INNERBORDER_O_Y, POLYGON_INNERBORDER_O_X.length);
    gfx.drawPolyline(POLYGON_OUTERBORDER_P_X, POLYGON_OUTERBORDER_P_Y, POLYGON_OUTERBORDER_P_X.length);
    gfx.drawPolyline(POLYGON_INNERBORDER_P_X, POLYGON_INNERBORDER_P_Y, POLYGON_INNERBORDER_P_X.length);

    return img;
  }


  /**
   * <p>Retrieve an icon based on the name. </p>
   *
   * @param name name of icon to locate.
   * @return icon for name.
   */
  public static ImageIcon getIcon(String name)
  {
    return getIcon(name, new ImageIcon(getMissingImage()));
  }


  /**
   * <p>Retrieve an icon based on the name.
   * If the icon couldn't be located use the specified default icon. </p>
   *
   * @param name name of icon to locate.
   * @param defaultIcon default icon if named one couldn't be found.
   * @return icon for name.
   */
  public static ImageIcon getIcon(String name, ImageIcon defaultIcon)
  {
    ImageIcon icon;
    URL url;

    try
    {
      icon = (ImageIcon)IMAGE_CACHE.get(name);

      if (icon != null)
      {
        return icon;
      }

      url = getInstance().getClass().getClassLoader().getResource(name);

      if (url == null)
      {
        return defaultIcon;
      }

      icon = new ImageIcon(url);
      IMAGE_CACHE.put(name, icon);

      return icon;
    }
    catch (NullPointerException npe)
    {
      System.out.println("Error retrieving icon:" + name);

      return defaultIcon;
    }
  }


  /**
   * <p>Place the window in the center of the screen. </p>
   *
   * @param win window that must be positioned on screen.
   */
  public static void centerOfScreen(Window win)
  {
    Rectangle screenBounds = win.getGraphicsConfiguration().getBounds();

    win.setLocation((int)(screenBounds.getX() + (screenBounds.getWidth() / 2) - (win.getWidth() / 2)),
                    (int)(screenBounds.getY() + (screenBounds.getHeight() / 2) - (win.getHeight() / 2)));
  }


  /**
   * <p>Try to center the window in the owner window. </p>
   *
   * @param win window that must be positioned on screen.
   * @param owner window that must be used as virtual screen.
   */
  public static void centerOfOwner(Window win, Window owner)
  {
    double x = 0;
    double y = 0;
    Dimension winSize, ownerSize;
    Point p;

    if (owner != null)
    {
      winSize = win.getSize();
      ownerSize = owner.getSize();

      x = (ownerSize.getWidth() - winSize.getWidth()) / 2;
      y = (ownerSize.getHeight() - winSize.getHeight()) / 2;

      p = owner.getLocation();

      x += p.getX();
      y += p.getY();
    }

    keepWindowCompletelyOnScreen(win, (int)x, (int)y);
  }


  /**
   * <p>Try to position the window relative to the specified component. </p>
   *
   * @param win window that must be positioned on screen.
   * @param owner window that must be used as virtual screen.
   * @param child component where the window should be positioned just below.
   */
  public static void relativeToOwnerChild(Window win, Window owner, Component child)
  {
    double x = 0;
    double y = 0;
    Component parent;
    Dimension childSize;
    Point p;

    if ((owner != null) && (child != null))
    {
      p = child.getLocation();
      childSize = child.getSize();

      x = p.getX();
      y = p.getY() + childSize.getHeight();
      parent = child.getParent();

      while ((parent != null) && (parent != owner))
      {
        p = parent.getLocation();
        x += p.getX();
        y += p.getY();
        parent = parent.getParent();
      }

      p = owner.getLocation();

      x += p.getX();
      y += p.getY();
    }

    keepWindowCompletelyOnScreen(win, (int)x, (int)y);
  }


  /**
   * <p>Check if new location keeps the window at least partially on the screen,
   * otherwise adjust position and place window. </p>
   *
   * @param win window that must be repositioned on screen.
   * @param x new x location for window.
   * @param y new y location for window.
   */
  public static void keepWindowPartiallyOnScreen(Window win, int x, int y)
  {
    int windowDragScreenSafetyMargin = JModalConfiguration.getWindowDragScreenSafetyMargin();
    Dimension winSize;

    Rectangle screenBounds = win.getGraphicsConfiguration().getBounds();
    winSize = win.getSize();

    if (x + windowDragScreenSafetyMargin > screenBounds.getX() + screenBounds.getWidth())
    {
      x = (int)(screenBounds.getX() + screenBounds.getWidth() - windowDragScreenSafetyMargin);
    }

    if (x + winSize.getWidth() - windowDragScreenSafetyMargin < screenBounds.getX())
    {
      x = (int)(screenBounds.getX() + windowDragScreenSafetyMargin - winSize.getWidth());
    }

    if (y + windowDragScreenSafetyMargin > screenBounds.getY() + screenBounds.getHeight())
    {
      y = (int)(screenBounds.getY() + screenBounds.getHeight() - windowDragScreenSafetyMargin);
    }

    if (y + winSize.getHeight() - windowDragScreenSafetyMargin < screenBounds.getY())
    {
      y = (int)(screenBounds.getY() + windowDragScreenSafetyMargin - winSize.getHeight());
    }

    win.setLocation(x, y);
  }


  /**
   * <p>Check if new location keeps the window as complete as possible on the screen,
   * otherwise adjust position and place window. </p>
   *
   * @param win window that must be repositioned on screen.
   * @param x new x location for window.
   * @param y new y location for window.
   */
  public static void keepWindowCompletelyOnScreen(Window win, int x, int y)
  {
    Dimension winSize;

    Rectangle screenBounds = win.getGraphicsConfiguration().getBounds();
    winSize = win.getSize();

    if (x + winSize.getWidth() > screenBounds.getX() + screenBounds.getWidth())
    {
      x = (int)(screenBounds.getX() + screenBounds.getWidth() - winSize.getWidth());
    }

    if (x < screenBounds.getX())
    {
      x = (int)(screenBounds.getX());
    }

    if (y + winSize.getHeight() > screenBounds.getY() + screenBounds.getHeight())
    {
      y = (int)(screenBounds.getY() + screenBounds.getHeight() - winSize.getHeight());
    }

    if (y < screenBounds.getY())
    {
      y = (int)(screenBounds.getY());
    }

    win.setLocation(x, y);
  }


  /**
   * <p>Place the internal frame in the center of the desktop. </p>
   *
   * <p>If the frame that contains the desktop isn't activated yet this action will position the internal frame in top left corner instead. </p>
   *
   * @param iframe internal frame that must be positioned on the desktop.
   * @param desktop desktop for supplied internal frame.
   */
  public static void centerOfDesktop(JInternalFrame iframe, JDesktopPane desktop)
  {
    double x = 0;
    double y = 0;
    Point p;

    if (desktop != null)
    {
      Rectangle desktopBounds = desktop.getBounds();

      iframe.setLocation((int)(desktopBounds.getX() + (desktopBounds.getWidth() / 2) - (iframe.getWidth() / 2)),
                         (int)(desktopBounds.getY() + (desktopBounds.getHeight() / 2) - (iframe.getHeight() / 2)));

      p = iframe.getLocation();

      x = p.getX();
      y = p.getY();
    }

    keepIFrameCompletelyOnDesktop(iframe, desktop, (int)x, (int)y);
  }


  /**
   * <p>Try to center the internal frame in the owner window. </p>
   *
   * @param iframe internal frame that must be positioned on the desktop.
   * @param desktop desktop for supplied internal frame.
   * @param owner window that must be used as virtual screen.
   */
  public static void centerOfOwner(JInternalFrame iframe, JDesktopPane desktop, JInternalFrame owner)
  {
    double x = 0;
    double y = 0;
    Dimension winSize, ownerSize;
    Point p;

    if (owner != null)
    {
      winSize = iframe.getSize();
      ownerSize = owner.getSize();

      x = (ownerSize.getWidth() - winSize.getWidth()) / 2;
      y = (ownerSize.getHeight() - winSize.getHeight()) / 2;

      p = owner.getLocation();

      x += p.getX();
      y += p.getY();
    }

    keepIFrameCompletelyOnDesktop(iframe, desktop, (int)x, (int)y);
  }


  /**
   * <p>Try to position the window relative to the specified component. </p>
   *
   * @param iframe internal frame that must be positioned on the desktop.
   * @param desktop desktop for supplied internal frame.
   * @param owner window that must be used as virtual screen.
   * @param child component where the internal frame should be positioned just below.
   */
  public static void relativeToOwnerChild(JInternalFrame iframe, JDesktopPane desktop, JInternalFrame owner, Component child)
  {
    double x = 0;
    double y = 0;
    Component parent;
    Dimension childSize;
    Point p;

    if ((owner != null) && (child != null))
    {
      p = child.getLocation();
      childSize = child.getSize();

      x = p.getX();
      y = p.getY() + childSize.getHeight();
      parent = child.getParent();

      while ((parent != null) && (parent != owner))
      {
        p = parent.getLocation();
        x += p.getX();
        y += p.getY();
        parent = parent.getParent();
      }

      p = owner.getLocation();

      x += p.getX();
      y += p.getY();
    }

    keepIFrameCompletelyOnDesktop(iframe, desktop, (int)x, (int)y);
  }


  /**
   * <p>Check if new location keeps the window as complete as possible on the screen,
   * otherwise adjust position and place window. </p>
   *
   * @param iframe internal frame that must be repositioned on the desktop.
   * @param desktop desktop for supplied internal frame.
   * @param x new x location for window.
   * @param y new y location for window.
   */
  public static void keepIFramePartiallyOnDesktop(JInternalFrame iframe, BasicInternalFrameTitlePane titlePane,
                                                  JDesktopPane desktop, int x, int y)
  {
    int iframeDragDesktopSafetyMargin = DEFAULT_DESKTOP_SAFETY_MARGIN;
    Insets iframeDragDesktopSafetyMarginInsets = iframe.getInsets();
    Dimension winSize;

    if (titlePane != null)
    {
      iframeDragDesktopSafetyMargin = titlePane.getHeight();
    }

    Rectangle desktopBounds = new Rectangle(0, 0, 0, 0);

    if (desktop != null)
    {
      desktopBounds = desktop.getBounds();
    }

    winSize = iframe.getSize();

    if (x + iframeDragDesktopSafetyMarginInsets.left + iframeDragDesktopSafetyMargin
        > desktopBounds.getX() + desktopBounds.getWidth())
    {
      x = (int)(desktopBounds.getX() + desktopBounds.getWidth() - iframeDragDesktopSafetyMarginInsets.left
                - iframeDragDesktopSafetyMargin);
    }

    if (x + winSize.getWidth() - iframeDragDesktopSafetyMarginInsets.right - iframeDragDesktopSafetyMargin < desktopBounds.getX())
    {
      x = (int)(desktopBounds.getX() + iframeDragDesktopSafetyMarginInsets.right + iframeDragDesktopSafetyMargin
                - winSize.getWidth());
    }

    if (y + iframeDragDesktopSafetyMarginInsets.top + iframeDragDesktopSafetyMargin
        > desktopBounds.getY() + desktopBounds.getHeight())
    {
      y = (int)(desktopBounds.getY() + desktopBounds.getHeight() - iframeDragDesktopSafetyMarginInsets.top
                - iframeDragDesktopSafetyMargin);
    }

    if (y < desktopBounds.getY())
    {
      y = 0;
    }

    iframe.setLocation(x, y);
  }


  /**
   * <p>Check if new location keeps the window as complete as possible on the screen,
   * otherwise adjust position and place window. </p>
   *
   * @param iframe internal frame that must be repositioned on the desktop.
   * @param desktop desktop for supplied internal frame.
   * @param x new x location for window.
   * @param y new y location for window.
   */
  public static void keepIFrameCompletelyOnDesktop(JInternalFrame iframe, JDesktopPane desktop, int x, int y)
  {
    Dimension winSize;

    Rectangle desktopBounds = new Rectangle(0, 0, 0, 0);

    if (desktop != null)
    {
      desktopBounds = desktop.getBounds();
    }

    winSize = iframe.getSize();

    if (x + winSize.getWidth() > desktopBounds.getX() + desktopBounds.getWidth())
    {
      x = (int)(desktopBounds.getX() + desktopBounds.getWidth() - winSize.getWidth());
    }

    if (x < desktopBounds.getX())
    {
      x = (int)(desktopBounds.getX());
    }

    if (y + winSize.getHeight() > desktopBounds.getY() + desktopBounds.getHeight())
    {
      y = (int)(desktopBounds.getY() + desktopBounds.getHeight() - winSize.getHeight());
    }

    if (y < desktopBounds.getY())
    {
      y = (int)(desktopBounds.getY());
    }

    iframe.setLocation(x, y);
  }
}
