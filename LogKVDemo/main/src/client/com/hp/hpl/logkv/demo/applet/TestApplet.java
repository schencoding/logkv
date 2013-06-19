package com.hp.hpl.logkv.demo.applet;

import java.awt.BorderLayout;

import javax.swing.JApplet;

import com.hp.hpl.logkv.demo.chart.ColorMeterDialPanel;
import com.hp.hpl.logkv.demo.chart.ColorMeterDialPanel.RandomDataGenerator;

/**
 * Test applet.
 * @author Edmond
 *
 */
public class TestApplet extends JApplet {

	/**
	 * color meter dial panel.
	 */
	ColorMeterDialPanel colorMeterDialPanel = new ColorMeterDialPanel("Test Char", "Temperature");

	/**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	 @Override
	 public void init() {
		 this.getContentPane().add(colorMeterDialPanel, BorderLayout.CENTER);
	 }

	 @Override
	 public void start() {
		 validate();
		 new RandomDataGenerator(1000, colorMeterDialPanel).start();
	 }

}
