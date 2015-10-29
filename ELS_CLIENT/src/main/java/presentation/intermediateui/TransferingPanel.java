package presentation.intermediateui;

import javax.swing.*;

import vo.TransferingReceiptVO;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferingPanel extends JPanel {
	public static final int PANEL_WIDTH = 720;
	public static final int PANEL_HEIGHT = 480;
	TransferingReceiptVO transferingReceipt;
	JButton addButton;
	JButton deleteButton;
	JButton sendButton;
	JButton[] modify;
	JButton next;
	JButton previous;
	InfoTable info;
	JTextField searchTextField;
	JDialog addDialog;

	public TransferingPanel() {
		// int numOfOrder = transferingReceiptVO.orderList.size();
		// for (int i = 0; i < numOfOrder; i++) {
		// modify[i] = new JButton("M");
		// }

		addButton = new JButton("new");
		deleteButton = new JButton("dele");
		sendButton = new JButton("send");
		searchTextField = new JTextField("Input", 10);
		next = new JButton("next");
		previous = new JButton("pre");
		// info = new JLabel[15][3];

		info = new InfoTable(15, 3);
		// JScrollPane pane = new JScrollPane(info);
		info.setTransferingReceipt(transferingReceipt);

		addButton.setBounds(PANEL_WIDTH / 8, PANEL_HEIGHT / 30,
				PANEL_HEIGHT / 7, PANEL_WIDTH / 20);
		deleteButton.setBounds(PANEL_WIDTH * 5 / 16, PANEL_HEIGHT / 30,
				PANEL_HEIGHT / 7, PANEL_WIDTH / 20);
		sendButton.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT / 30,
				PANEL_HEIGHT / 7, PANEL_WIDTH / 20);
		searchTextField.setBounds(PANEL_WIDTH * 3 / 4, PANEL_HEIGHT / 30,
				PANEL_HEIGHT / 3, PANEL_WIDTH / 20);
		next.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_HEIGHT / 16, PANEL_HEIGHT / 24);
		previous.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_HEIGHT / 16, PANEL_HEIGHT / 24);

		info.setBounds(PANEL_HEIGHT / 6, PANEL_HEIGHT / 6,
				PANEL_HEIGHT * 5 / 4, PANEL_HEIGHT * 3 / 4);

		// for (int i = 0; i < 15; i++) {
		// info[i][0] = new JLabel();
		// info[i][1] = new JLabel();
		// info[i][2] = new JLabel();
		//
		// info[i][0].setBounds(PANEL_HEIGHT / 6 + i * PANEL_HEIGHT / 20,
		// PANEL_HEIGHT / 6, PANEL_HEIGHT * 5 / 8, PANEL_HEIGHT / 20);
		// info[i][1].setBounds(PANEL_HEIGHT / 6 + PANEL_HEIGHT * 5 / 8,
		// PANEL_HEIGHT / 6 + i * PANEL_HEIGHT / 20,
		// PANEL_HEIGHT * 5 / 16, PANEL_HEIGHT / 20);
		// info[i][2].setBounds(PANEL_HEIGHT / 6 + PANEL_HEIGHT * 5 / 4,
		// PANEL_HEIGHT / 6 + i * PANEL_HEIGHT / 20,
		// PANEL_HEIGHT * 5 / 16, PANEL_HEIGHT / 20);
		//
		// info[i][0].setBorder(BorderFactory.createLineBorder(Color.black));
		// info[i][1].setBorder(BorderFactory.createLineBorder(Color.black));
		// info[i][2].setBorder(BorderFactory.createLineBorder(Color.black));
		//
		// add(info[i][0]);
		// add(info[i][1]);
		// add(info[i][2]);
		// }

		addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				addui();
			}
		});

		deleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				deleteui();
			}
		});

		sendButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				sendui();
			}
		});

		next.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				nextui();
			}
		});

		previous.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				preui();
			}
		});

		setLayout(null);

		add(addButton);
		add(deleteButton);
		add(sendButton);
		add(searchTextField);
		add(info);
		add(next);
		add(previous);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new TransferingPanel());
		frame.setVisible(true);
	}

	public void setTransferingReceipt(TransferingReceiptVO transferingReceipt) {
		this.transferingReceipt = transferingReceipt;
	}

	public void addui() {
		if (addDialog == null)
			addDialog = new JDialog(addDialog, "add");
		addDialog.setVisible(true);
		addDialog.setSize(400, 300);
	}

	public void deleteui() {

	}

	public void sendui() {

	}

	public void nextui() {

	}

	public void preui() {

	}
}
