package com.hp.hpl.logkv.demo.applet.shufflingprocess;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.hp.hpl.logkv.demo.applet.StringUtil;
import com.hp.hpl.logkv.ingestkv.ShuffleStatus;


/**
 * Shuffling summary dialog.
 * @author Edmond
 */
public class ShufflingSummaryDialog extends JDialog {

	/**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * console message queue.
	 */
	private LinkedList<String> consoleMessageQueue = new LinkedList<String>();

	/**
	 * summary text area.
	 */
	private JTextArea taSummary = new JTextArea();

	/**
	 * constructor method.
	 * @param parent	parent frame.
	 * @param title		dialog title
	 */
	public ShufflingSummaryDialog(Frame parent, String title) {
		super(parent, title, false);

		JScrollPane scrollPane = new JScrollPane(taSummary);
		scrollPane.setPreferredSize(new Dimension(400, 200));

		this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.CENTER);
		this.setSize(400, 160);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}

	/**
	 * refresh ingested TRU.
	 * @param ingestedTRUs	ingested TRU array
	 */
	public void refreshIngestedTRU(Long[] ingestedTRUs) {

		System.out.println("ingestedTRUs.length = " + ingestedTRUs.length);
		
		if (ingestedTRUs != null) {

			Set<Long> truSet = new TreeSet<Long>();

			for (Long tru : ingestedTRUs) {

				truSet.add(tru);
			}

			for (Long tru : truSet) {

				if (consoleMessageQueue.size() >= 200) {
					consoleMessageQueue.removeFirst();
				}

				consoleMessageQueue.add("Ingested TRU-" + tru);

			}

		}

	}

	/**
	 * refresh shuffled TRU.
	 * @param shuffleStatuses	shuffle statues
	 */
	public void refreshShuffled(ShuffleStatus[] shuffleStatuses) {

		System.out.println("shuffleStatuses.length = " + shuffleStatuses.length);
		
		if (shuffleStatuses != null) {

			for (ShuffleStatus s : shuffleStatuses) {

				if (!s.isbStart()) {
					continue;
				}

				if (consoleMessageQueue.size() >= 200) {
					consoleMessageQueue.removeFirst();
				}

				consoleMessageQueue.add("Shuffled TRU-" + s.getTruId()
						+ " from " + StringUtil.convertNodeName(s.getSrcIpStr()) + " to " + StringUtil.convertNodeName(s.getDestIpStr()));
			}

		}

	}

	/**
	 * refresh console.
	 */
	public void refreshConsole() {

		StringBuffer wholeMessage = new StringBuffer();

		for (String message : consoleMessageQueue) {
			wholeMessage.append("\r\n");
			wholeMessage.append(message);
		}

		this.taSummary.setText(wholeMessage.toString());
	}

	/**
	 * main method.
	 * @param args	arguments array
	 */
	public static void main(String[] args) {

		LinkedList<String> consoleMessageQueue = new LinkedList<String>();
		
		consoleMessageQueue.add("1");
		consoleMessageQueue.add("2");
		consoleMessageQueue.add("3");
		
		consoleMessageQueue.removeFirst();
		consoleMessageQueue.add("4");
		
		System.out.println(consoleMessageQueue);
		
		if (true) {
			return;
		}
		
	    final JFrame frame = new JFrame("Summary Demo");

	    final ShufflingSummaryDialog shufflingSummaryDialog = new ShufflingSummaryDialog(frame, "Shuffling Summary");

	    JButton btnOpen = new JButton("Open");
	    btnOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int parentX = shufflingSummaryDialog.getParent().getLocation().x;
				int parentY = shufflingSummaryDialog.getParent().getLocation().y;

				int parentWidth = (int) shufflingSummaryDialog.getParent().getWidth();
				int parentHeight = (int) shufflingSummaryDialog.getParent().getHeight();

				System.out.println("(" + parentX + "," + parentY + ")[" + parentWidth + "," + parentHeight + "]");

				int dialogX = parentX + (parentWidth - shufflingSummaryDialog.getWidth());
				int dialogY = parentY;

				shufflingSummaryDialog.setLocation(dialogX, dialogY);

				shufflingSummaryDialog.setVisible(true);
			}
	    });

	    frame.getContentPane().add(btnOpen, BorderLayout.CENTER);
	    frame.setBounds(200, 120, 1200, 800);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
