package com.hp.hpl.logkv.demo.util;

import java.awt.Font;

/**
 * Constants class.
 * @author Edmond
 */
public final class Constants {

	/**
	 * prevent to instance.
	 */
	private Constants() {
	}

	/**
	 * chart title label font.
	 */
	public static final Font CHART_TITLE_LABEL_FONT = new Font("SansSerif", 1, 24);

	/**
	 * axis tick label font.
	 */
	public static final Font AXIS_TICK_LABEL_FONT = new Font(Font.MONOSPACED,  Font.PLAIN, 10);

	/**
	 * axis label font.
	 */
	public static final Font AXIS_LABEL_FONT = new Font("SansSerif", Font.PLAIN, 14);

	/**
	 * JMS request client ID.
	 */
	public static final String JMS_REQUEST_CLIENT_ID = "requestClientID";

	/**
	 * system line separator.
	 */
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
}
