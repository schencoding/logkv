package com.hp.hpl.logkv.demo.applet.modelwindow;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


/**
 * <p>JPanel used to block MouseEvents and KeyEvents. </p>
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
public class JBusyPanel extends JPanel
{
  /**
   * <p>Screen blurring is turned off.
   * Use this option if blurring is not desired. </p>
   */
  final public static int BLUR_STYLE_NONE = -1;

  /**
   * <p>Blur screen with a color with alpha channel.</p>
   */
  final public static int BLUR_STYLE_ALPHA_CHANNEL = 2;

  /**
   * <p>Default alpha transparency factor. </p>
   */
  final public static int DEFAULT_ALPHA_TRANSPARENCY_FACTOR = 128;
  final public static int DEFAULT_ALPHA_NO_TRANSPARENCY_FACTOR = 255;

  private Color blurColor;
  private int blurStyle;

  /**
   * <p>Used to blur a modal component. </p>
   *
   * @param blurColor color used for blurring.
   * @param blurStyle blurring style.
   */
  public JBusyPanel(Color blurColor, int blurStyle)
  {
    this.blurColor = blurColor;
    this.blurStyle = blurStyle;

    this.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent me)
      {
        JBusyPanel.this.grabFocus();
      }
    });

    this.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent keyEvent)
      {
        keyEvent.consume();
      }
    });

    this.setOpaque(false);

    this.setFocusTraversalKeysEnabled(false);
    this.setFocusCycleRoot(true);

    if (JModalConfiguration.isBusyCursorEnabled())
    {
      this.setCursor(JModalConfiguration.getBusyCursor());
    }
  }


  /**
   * <p>Block any focusmanager. </p>
   *
   * @deprecated As of 1.4, replaced by
   *   <code>Component.setFocusTraversalKeys(int, Set)</code> and
   *   <code>Container.setFocusCycleRoot(boolean)</code>.
   * @return indication that the <code>JBusyPanel</code> handles the focus events.
   */
  public boolean isManagingFocus()
  {
    return true;
  }


  /**
   * <p>Blur panel with required style. </p>
   *
   * @param gfx panel <code>Graphics</code>.
   */
  protected void paintComponent(Graphics gfx)
  {
    super.paintComponent(gfx);

    switch (blurStyle)
    {
      case BLUR_STYLE_NONE:
        paintStyleNone(gfx);

        break;
      case BLUR_STYLE_ALPHA_CHANNEL:
      default:
        paintStyleAlphaChannel(gfx);
    }
  }


  /**
   * <p>Do not blur panel. </p>
   *
   * @param gfx panel <code>Graphics</code>.
   */
  private void paintStyleNone(Graphics gfx)
  {
  }

  /**
   * <p>Blur panel with a color with alpha channel.</p>
   * 
   * @param gfx panel <code>Graphics</code>.
   */
  private void paintStyleAlphaChannel(Graphics gfx) 
  {
    gfx.setColor(blurColor);
    gfx.fillRect(0, 0, getWidth(), getHeight());
  }
}
