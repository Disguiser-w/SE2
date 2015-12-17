package presentation.intermediateui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import vo.OrderVO;
import vo.TransferingReceiptVO;
import businesslogic.intermediatebl.controller.IntermediateMainController;

public class TransferingPanel extends JPanel {
	private IntermediateMainController controller;

	private IntermediateFrame frame;

	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	private TransferingReceiptVO transferingReceipt;

	private JButton addButton;
	private JButton deleteButton;
	private JButton printButton;
	private JButton sendButton;
	private JButton delete_ok;

	private JButton next;
	private JButton previous;

	private JLabel function;

	private JCheckBox[] isDelete;

	private TransferingInfoTable info;
	private TransferingTableModel model;

	private int pageNum;
	private int pageNum_max;

	private JTextField searchTextField;

	private JDialog addDialog;

	public TransferingPanel(IntermediateMainController c, IntermediateFrame f) {
		this.controller = c;
		this.frame = f;

		addButton = new JButton("new");
		deleteButton = new JButton("dele");
		sendButton = new JButton("send");
		printButton = new JButton("print");
		searchTextField = new JTextField("Input", 10);
		next = new JButton("next");
		previous = new JButton("pre");
		delete_ok = new JButton();

		function = new JLabel("中转接收");

		model = new TransferingTableModel();
		info = new TransferingInfoTable(model);

		pageNum = 0;

		isDelete = new JCheckBox[12];
		for (int i = 0; i < isDelete.length; i++) {
			isDelete[i] = new JCheckBox();
			isDelete[i].setBounds(PANEL_WIDTH * 2 / 33, PANEL_HEIGHT * 29 / 120
					+ PANEL_HEIGHT * (i + 1) / 22 + PANEL_HEIGHT / 80,
					PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
			add(isDelete[i]);
			isDelete[i].setVisible(false);
		}
		delete_ok.setVisible(false);

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
				try {
					sendui();
				} catch (RemoteException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});

		printButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				printui();
			}
		});

		next.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				pageNum_max = (controller.getTransferingReceipt().orderList
						.size()) / 12;
				if (pageNum >= pageNum_max)
					return;
				else {
					pageNum++;
					info.setModel(new TransferingTableModel());
					info.setuiInfo();
				}
			}
		});

		previous.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				pageNum_max = (controller.getTransferingReceipt().orderList
						.size()) / 12;
				if (pageNum == 0)
					return;
				else {
					pageNum--;
					info.setModel(new TransferingTableModel());
					info.setuiInfo();
				}
			}
		});

		delete_ok.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				delete_ok();
			}
		});

		setLayout(null);

		add(addButton);
		add(deleteButton);
		add(sendButton);
		add(printButton);
		add(searchTextField);
		add(info);
		add(function);
		add(next);
		add(previous);
		add(info.getTableHeader());
		add(delete_ok);
	}

	public void setCmpLocation() {
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		addButton.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		deleteButton.setBounds(PANEL_WIDTH * 5 / 9, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		printButton.setBounds(PANEL_WIDTH * 7 / 9, PANEL_HEIGHT / 24,
				PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
		sendButton.setBounds(PANEL_WIDTH * 8 / 9, PANEL_HEIGHT / 24,
				PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
		searchTextField.setBounds(PANEL_WIDTH * 13 / 18, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH * 2 / 9, PANEL_HEIGHT / 24);
		next.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		previous.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);

		info.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 4 / 15 + PANEL_HEIGHT
				/ 20, PANEL_WIDTH * 5 / 6, PANEL_HEIGHT * 12 / 20);
		info.getTableHeader().setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 4 / 15,
				PANEL_WIDTH * 5 / 6, PANEL_HEIGHT / 20);
		delete_ok.setBounds(PANEL_WIDTH / 18, PANEL_HEIGHT * 4 / 15
				+ PANEL_HEIGHT / 240, PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
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
		frame.changePanel(new Transfering_newPanel(controller, frame));
	}

	public void deleteui() {
		delete_ok.setVisible(true);
		for (int i = 0; i < controller.getTransferingReceipt().orderList.size()
				- 12 * pageNum; i++)
			isDelete[i].setVisible(true);
		delete_ok.setVisible(true);
	}

	public void printui() {

	}

	public void sendui() throws RemoteException {
		controller.getTransferingBL().saveTransferingReceipt();
	}

	public void delete_ok() {
		for (int i = 0; i < isDelete.length; i++) {
			int delete_num = 0;
			if (isDelete[i].isSelected()) {
				try {
					controller.getTransferingBL().deleteOrder(
							controller.getTransferingReceipt().orderList.get(i
									+ pageNum * 12 - delete_num++).ID);
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			isDelete[i].setVisible(false);
		}
		delete_ok.setVisible(false);
	}

	private class TransferingTableModel extends AbstractTableModel {

		public int getRowCount() {
			// TODO 自动生成的方法存根
			return 12;
		}

		public int getColumnCount() {
			// TODO 自动生成的方法存根
			return 5;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO 自动生成的方法存根
			int index = pageNum * 12 + rowIndex;
			if (index > controller.getTransferingReceipt().orderList.size() - 1)
				return null;
			OrderVO order = controller.getTransferingReceipt().orderList
					.get(index);
			if (order != null) {
				switch (columnIndex) {
				case 0:
					return order.ID;
				case 1:
					String[] senderAddress = order.senderAddress.split(" ");
					return senderAddress[0];
				case 2:
					String[] recipientAddress = order.recipientAddress
							.split(" ");
					return recipientAddress[0];
				case 3:
					switch (order.order_state) {
					case DISTRIBUEING:
						return "派件中";
					case FINISHED:
						return "已完成";
					case TRANSFERING:
						return "中转中";
					case WAITING_DISTRIBUTE:
						return "等待中转";
					case WAITING_ENVEHICLE:
						return "等待装车";
					}
				case 4:
					switch (order.expressType) {
					case ECONOMIC:
						return "经济型";
					case FAST:
						return "特快型";
					case STANDARD:
						return "标准型";
					}
				}
			} else
				return null;
			return null;
		}

		public String getColumnName(int c) {
			if (c == 0)
				return "订单号";
			if (c == 1)
				return "出发地";
			if (c == 2)
				return "到达地";
			if (c == 3)
				return "状态";
			if (c == 4)
				return "订单种类";

			return null;
		}
	}
	//
	// public static void main(String[] args) {
	// JFrame frame = new JFrame();
	// frame.setSize(800, 550);
	// frame.add(new TransferingPanel(null));
	// frame.setVisible(true);
	// }
}
