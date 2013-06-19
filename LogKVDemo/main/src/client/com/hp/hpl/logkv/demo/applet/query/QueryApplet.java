package com.hp.hpl.logkv.demo.applet.query;

import java.awt.BorderLayout;
import java.net.URL;

import javax.swing.JApplet;

import com.hp.hpl.logkv.demo.applet.UIUtil;
import com.hp.hpl.logkv.demo.applet.URLRequester;

/**
 * Query applet.
 * @author Edmond
 */
public class QueryApplet extends JApplet {

	/**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * query panel.
	 */
	QueryPanel queryPanel;

	/**
	 * query servlet URL.
	 */
	private URL queryServletURL;

	/**
	 * URL request.
	 */
	private URLRequester urlRequester;

	 @Override
	 public void init() {

		 String serverIP = this.getParameter("serverIP");
		 String serverPort = this.getParameter("serverPort");

		 try {
			 queryServletURL = new URL("http://" + serverIP + ":" + serverPort + "/LogKVDemo/QueryServlet");
		 } catch (Exception e) {
			 throw new RuntimeException(e);
		 }

		 urlRequester = new URLRequester(queryServletURL);

		 queryPanel = new QueryPanel(UIUtil.getParentFrame(this), urlRequester);

		 this.getContentPane().add(queryPanel, BorderLayout.CENTER);
	 }

}
