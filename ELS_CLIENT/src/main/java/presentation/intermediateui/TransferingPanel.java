package presentation.intermediateui;

import javax.swing.*;

import vo.TransferingReceiptVO;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferingPanel extends JPanel {
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	
	TransferingReceiptVO transferingReceipt;
	
	JButton addButton;
	JButton deleteButton;
	JButton sendButton;
//	JButton[] modify;
	
	JButton next;
	JButton previous;
	TransferingInfoTable info;
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

		info = new TransferingInfoTable(15, 3);

		setCmpLocation();

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

	public void setCmpLocation() {
		addButton.setBounds(PANEL_WIDTH / 8, PANEL_HEIGHT / 30,
				PANEL_WIDTH * 3 / 28, PANEL_HEIGHT / 16);
		deleteButton.setBounds(PANEL_WIDTH * 5 / 16, PANEL_HEIGHT / 30,
				PANEL_WIDTH * 3 / 28, PANEL_HEIGHT / 16);
		sendButton.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT / 30,
				PANEL_WIDTH * 3 / 28, PANEL_HEIGHT / 16);
		searchTextField.setBounds(PANEL_WIDTH * 3 / 4, PANEL_HEIGHT / 30,
				PANEL_WIDTH * 2 / 9, PANEL_HEIGHT / 16);
		next.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		previous.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);

		info.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT / 6, PANEL_WIDTH * 5 / 6,
				PANEL_HEIGHT * 3 / 4);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation();
		repaint();
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
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new TransferingPanel());
		frame.setVisible(true);
	}
}
