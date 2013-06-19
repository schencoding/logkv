package com.hp.hpl.logkv.demo.applet.logsources;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.hp.hpl.logkv.ui.AddLogSourceRequest;

/**
 * Add LogShource Dialog.
 * @author Edmond
 */
public class AddLogSourceDialog extends JDialog {

	/**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * require add flag.
	 */
	private boolean requireAdd;

	/**
	 * add log source request.
	 */
	private AddLogSourceRequest addLogSourceRequest;

	/**
	 * source IP text field.
	 */
	private JTextField txtSourceIp;

	/**
	 * log source id text field.
	 */
	private JTextField txtLogSourceId;

	/**
	 * split check box.
	 */
	private JCheckBox checkSplitable;

	/**
	 * through put text field.
	 */
	private JTextField txtThroughput;

	/**
	 * constructor method.
	 * @param owner			owner frame
	 * @param dialogTitle	dialog title
	 */
	public AddLogSourceDialog(Frame owner, String dialogTitle) {
		super(owner, dialogTitle, true);

		this.setLocation(200, 300);

		JLabel lblSourceIp = new JLabel("Source IP :");
		txtSourceIp = new JTextField();
		txtSourceIp.setPreferredSize(new Dimension(200, 10));

		JLabel lblLogSourceId = new JLabel("Log Source ID:");
		txtLogSourceId = new JTextField();

		JLabel lblSplitable = new JLabel("Dividable:");
		checkSplitable = new JCheckBox();

		JLabel lblThroughput = new JLabel("Throughput:");
		txtThroughput = new JTextField();

		JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
		formPanel.add(lblSourceIp);
		formPanel.add(txtSourceIp);
		formPanel.add(lblLogSourceId);
		formPanel.add(txtLogSourceId);
		formPanel.add(lblSplitable);
		formPanel.add(checkSplitable);
		formPanel.add(lblThroughput);
		formPanel.add(txtThroughput);

		formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Log Source Inforamtion"));

		JButton btnSave = new JButton("Save");
		btnSave.setPreferredSize(new Dimension(75, 20));
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String sourceIp = txtSourceIp.getText().trim();

				if (sourceIp.length() == 0) {
					JOptionPane.showMessageDialog(AddLogSourceDialog.this, "Please input source IP.");
					txtSourceIp.requestFocus();
					return;
				}

				String logSourceIdStr = txtLogSourceId.getText().trim();

				if (logSourceIdStr.length() == 0) {
					JOptionPane.showMessageDialog(AddLogSourceDialog.this, "Please log source id.");
					txtLogSourceId.requestFocus();
					return;
				}

				int logSourceId = 0;

				try {
					logSourceId = Integer.parseInt(logSourceIdStr);
				} catch (Exception ignore) {
					JOptionPane.showMessageDialog(AddLogSourceDialog.this, "Please numeric log source id.");
					txtLogSourceId.requestFocus();
					return;
				}

				String throughputStr = txtThroughput.getText().trim();

				if (throughputStr.length() == 0) {
					JOptionPane.showMessageDialog(AddLogSourceDialog.this, "Please throughput.");
					txtThroughput.requestFocus();
					return;
				}

				int throughput = 0;

				try {
					throughput = Integer.parseInt(throughputStr);
				} catch (Exception ignore) {
					JOptionPane.showMessageDialog(AddLogSourceDialog.this, "Please numeric throughput.");
					txtThroughput.requestFocus();
					return;
				}

				boolean splitable = checkSplitable.isSelected();

				addLogSourceRequest = new AddLogSourceRequest(sourceIp, logSourceId, splitable, throughput);

				requireAdd = true;

				AddLogSourceDialog.this.setVisible(false);
			}
		});
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AddLogSourceDialog.this.setVisible(false);
			}
		});
		btnCancel.setPreferredSize(new Dimension(75, 20));

		JPanel commandPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		commandPanel.add(btnSave);
		commandPanel.add(btnCancel);

		this.getContentPane().add(formPanel, BorderLayout.CENTER);
		this.getContentPane().add(commandPanel, BorderLayout.SOUTH);

		this.pack();
	}

	@Override
	public void setVisible(boolean b) {

		if (b) {

			this.txtSourceIp.setText("");
			this.txtLogSourceId.setText("");
			this.checkSplitable.setSelected(false);
			this.txtThroughput.setText("");

			this.addLogSourceRequest = null;
			this.requireAdd = false;
		}
		super.setVisible(b);
	}

	/**
	 * @return the requireAdd
	 */
	public boolean isRequireAdd() {
		return requireAdd;
	}

	/**
	 * @return the addLogSourceRequest
	 */
	public AddLogSourceRequest getAddLogSourceRequest() {
		return addLogSourceRequest;
	}

	/**
	 * main method.
	 * @param args	arguments array
	 */
	public static void main(String[] args) {

	    JFrame frame = new JFrame("Add Log Source Dailog");

	    final AddLogSourceDialog addLogSourceDialog = new AddLogSourceDialog(frame, "Add LogSource");
	    JButton btnPopup = new JButton("Popup");
	    btnPopup.addActionListener(
    		new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					addLogSourceDialog.setVisible(true);
				}

    		});
	    frame.getContentPane().add(btnPopup, BorderLayout.CENTER);
	    frame.setBounds(200, 120, 600, 280);
	    frame.setVisible(true);
	    addLogSourceDialog.setLocationRelativeTo(frame);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}

}
