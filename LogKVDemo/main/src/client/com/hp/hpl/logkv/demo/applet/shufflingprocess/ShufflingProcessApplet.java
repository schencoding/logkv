package com.hp.hpl.logkv.demo.applet.shufflingprocess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.hp.hpl.logkv.demo.applet.StringUtil;
import com.hp.hpl.logkv.demo.applet.UIUtil;
import com.hp.hpl.logkv.demo.applet.URLRequester;
import com.hp.hpl.logkv.demo.model.ShufflingProcessWrapRequest;
import com.hp.hpl.logkv.demo.model.WorkerNode;
import com.hp.hpl.logkv.ingestkv.ShuffleStatus;
import com.hp.hpl.logkv.ui.GetNodesRequest;
import com.hp.hpl.logkv.ui.ShuffleDestroyRequest;
import com.hp.hpl.logkv.ui.ShuffleStatusRequest;
import com.hp.hpl.logkv.ui.ShuffleStatusResponse;

/**
 * Shuffling process applet.
 * @author Edmond
 *
 */
public class ShufflingProcessApplet extends JApplet {

	/**
	 * shuffling process panel.
	 */
	ShufflingProcessPanel shufflingProcessPanel;

	/**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * URL requester.
	 */
	private URLRequester urlRequester;

	/**
	 * receive URL requester.
	 */
	private URLRequester receiveUrlRequester;

	/**
	 * destroy URL requester.
	 */
	private URLRequester destroyUrlRequester;

	/**
	 * shuffling process servlet URL.
	 */
	private URL shufflingProcessServletURL;

	/**
	 * shuffling process receive servlet URL.
	 */
	private URL shufflingProcessReceiveServletURL;

	/**
	 * shuffling process destroy servlet URL.
	 */
	private URL shufflingProcessDestroyServletURL;

	/**
	 * request client id.
	 */
	private String requestClientId = UUID.randomUUID().toString();


	 @Override
	 public void init() {

		 String serverIP = this.getParameter("serverIP");
		 String serverPort = this.getParameter("serverPort");
		 try {
			 shufflingProcessServletURL = new URL("http://" + serverIP + ":" + serverPort + "/LogKVDemo/ShufflingProcessServlet");
			 shufflingProcessReceiveServletURL = new URL("http://" + serverIP + ":" + serverPort + "/LogKVDemo/ShufflingProcessReceiveServlet");
			 shufflingProcessDestroyServletURL = new URL("http://" + serverIP + ":" + serverPort + "/LogKVDemo/ShufflingProcessDestroyServlet");
		 } catch (Exception e) {
			 throw new RuntimeException(e);
		 }
		 urlRequester = new URLRequester(shufflingProcessServletURL);
		 receiveUrlRequester = new URLRequester(shufflingProcessReceiveServletURL);
		 destroyUrlRequester = new URLRequester(shufflingProcessDestroyServletURL);

		 new ThreadStarter().start();
	 }

	 @Override
	 public void start() {
		 validate();
	 }

	 /**
	  * shuffling summary dialog.
	  */
	 private ShufflingSummaryDialog shufflingSummaryDialog;

	 /**
	  * Threader starter.
	  * @author Edmond
	  */
	 public class ThreadStarter extends Thread {

		@Override
		public void run() {

			shufflingSummaryDialog = new ShufflingSummaryDialog(
					 UIUtil.getParentFrame(ShufflingProcessApplet.this), "Console");

			int parentX = ShufflingProcessApplet.this.getLocationOnScreen().x;
			int parentY = ShufflingProcessApplet.this.getLocationOnScreen().y;

			int parentWidth = (int) shufflingSummaryDialog.getParent().getWidth();

			int dialogX = parentX + (parentWidth - shufflingSummaryDialog.getWidth());
			int dialogY = parentY;

			shufflingSummaryDialog.setLocation(dialogX, dialogY);

			shufflingSummaryDialog.setVisible(true);

			try {
				Thread.sleep(50);
			} catch (Exception ignore) {
				ignore.printStackTrace();
			}

			GetNodesRequest requestObj = new GetNodesRequest();

			 Object object = urlRequester.request(requestObj);

			 if (object == null) {
				 System.err.println("GetNodesResponse object is null");
				 return;
			 }

			 String[] ipStrArray = (String[]) object;

			 shufflingProcessPanel = new ShufflingProcessPanel(ShufflingProcessApplet.this.getSize());

			 List<WorkerNode> workerNodeList = new ArrayList<WorkerNode>();
			 for (int i = 0; i < ipStrArray.length; i++) {
				 workerNodeList.add(new WorkerNode(StringUtil.convertNodeName(ipStrArray[i])));
			 }

			 shufflingProcessPanel.insertWorkerNodeToGraph(workerNodeList);

			 shufflingProcessPanel.setBackground(Color.white);
			 ShufflingProcessApplet.this.getContentPane().add(shufflingProcessPanel, BorderLayout.CENTER);

			 ShufflingProcessApplet.this.getContentPane().validate();

			 ShufflingSignSender shufflingSignSender = new ShufflingSignSender(60 * 1000);
			 shufflingSignSender.setInitialDelay(0);
			 shufflingSignSender.start();

			 ShufflingResultReceiver shufflingResultReceiver = new ShufflingResultReceiver(1 * 1000);
			 shufflingResultReceiver.start();
		}

	 }

	 /**
	  * Shuffling sign sender.
	  * @author Edmond
	  */
	 private class ShufflingSignSender extends Timer implements ActionListener {

		/**
		 * serial version UID.
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * constructor method.
		 * @param delay			timer delay in millisecond
		 */
		public ShufflingSignSender(int delay) {
	      super(delay, null);
	      addActionListener(this);
	    }

		@Override
		public void actionPerformed(ActionEvent e) {

			try {

				ShufflingProcessWrapRequest wrapRequestObj = new ShufflingProcessWrapRequest();

				ShuffleStatusRequest requestObj = new ShuffleStatusRequest(true, null);

				wrapRequestObj.requestClientId = requestClientId;
				wrapRequestObj.shufflingStatusRequest = requestObj;

				 Object object = urlRequester.request(wrapRequestObj);

				 if (object == null) {
					 System.err.println("GetNodesResponse object is null");
					 return;
				 }

				 ShuffleStatusResponse shuffleStatusResponse = (ShuffleStatusResponse) object;

				 ShuffleStatus[] shuffleStatusArray = shuffleStatusResponse.getShuffleStatuses();

//				 System.out.println(Arrays.toString(shuffleStatusArray));

				 shufflingSummaryDialog.refreshIngestedTRU(shuffleStatusResponse.getIngestedTRUs());
				 shufflingSummaryDialog.refreshShuffled(shuffleStatusArray);
				 shufflingSummaryDialog.refreshConsole();

				 shufflingProcessPanel.removeAllEdge();

				 for (ShuffleStatus shuffleStatus : shuffleStatusArray) {
					 shufflingProcessPanel.refreshEdgeInGraph(shuffleStatus);
				 }

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	 }

	 /**
	  * Shuffling result receiver.
	  * @author Edmond
	  */
	 private class ShufflingResultReceiver extends Timer implements ActionListener {

		/**
		 * serial version UID.
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * constructor method.
		 * @param delay			timer delay in millisecond
		 */
		public ShufflingResultReceiver(int delay) {
	      super(delay, null);
	      addActionListener(this);
	    }

		@Override
		public void actionPerformed(ActionEvent e) {

			try {

				 Object object = receiveUrlRequester.request(requestClientId);

				 if (object == null) {
					 System.err.println("GetNodesResponse object is null");
					 return;
				 }

				 ShuffleStatusResponse shuffleStatusResponse = (ShuffleStatusResponse) object;

				 ShuffleStatus[] shuffleStatusArray = shuffleStatusResponse.getShuffleStatuses();

//				 System.out.println(Arrays.toString(shuffleStatusArray));

				 shufflingSummaryDialog.refreshIngestedTRU(shuffleStatusResponse.getIngestedTRUs());
				 shufflingSummaryDialog.refreshShuffled(shuffleStatusArray);
				 shufflingSummaryDialog.refreshConsole();

				 shufflingProcessPanel.removeAllEdge();

				 for (ShuffleStatus shuffleStatus : shuffleStatusArray) {
					 shufflingProcessPanel.refreshEdgeInGraph(shuffleStatus);
				 }

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	 }

	 @Override
	 public void destroy() {

		 System.out.println("Shuffling destroy.......");

		 try {
			 ShuffleDestroyRequest requestObj = new ShuffleDestroyRequest();
			 requestObj.setRequestID(requestClientId);
			 destroyUrlRequester.post(requestObj);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }

		 super.destroy();
	 }

}
