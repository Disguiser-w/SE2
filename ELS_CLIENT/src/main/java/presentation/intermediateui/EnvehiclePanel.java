package presentation.intermediateui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

import vo.OrderVO;
import businesslogic.intermediatebl.controller.IntermediateMainController;

public class EnvehiclePanel extends JPanel {
	private IntermediateMainController controller;

	private IntermediateFrame frame;

	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	private JButton envehicle;
	private JButton next;
	private JButton previous;

	private JLabel function;

	private EnvehicleInfoTable info;
	private EnvehicleTableModel model;

	private int pageNum;
	private int pageNum_max;

	public EnvehiclePanel(IntermediateMainController c, IntermediateFrame f) {
		this.controller = c;
		this.frame = f;

		envehicle = new JButton("do");
		next = new JButton("next");
		previous = new JButton("previous");

		function = new JLabel("装车分配");

		model = new EnvehicleTableModel();
		info = new EnvehicleInfoTable(model);

		pageNum = 0;

		setCmpLocation();

		envehicle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				try {
					envehicleui();
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});

		next.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				pageNum_max = (controller.getEnvehicleBL()
						.getWaitingOrderList().size()) / 12;

				if (pageNum >= pageNum_max)
					return;
				else {
					pageNum++;
					info.setModel(new EnvehicleTableModel());
					info.setuiInfo();
				}
			}
		});

		previous.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				pageNum_max = (controller.getEnvehicleBL()
						.getWaitingOrderList().size()) / 12;

				if (pageNum == 0)
					return;
				else {
					pageNum--;
					info.setModel(new EnvehicleTableModel());
					info.setuiInfo();
				}
			}
		});

		info.setBackground(getBackground());
		setLayout(null);

		add(envehicle);
		add(next);
		add(previous);
		add(function);
		add(info);
		add(info.getTableHeader());
	}

	public void setCmpLocation() {
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		next.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		previous.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		envehicle.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);

		info.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 4 / 15 + PANEL_HEIGHT
				/ 20, PANEL_WIDTH * 5 / 6, PANEL_HEIGHT * 12 / 20);
		info.getTableHeader().setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 4 / 15,
				PANEL_WIDTH * 5 / 6, PANEL_HEIGHT / 20);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation();
		repaint();
	}

	public void envehicleui() throws Exception {
		controller.getEnvehicleBL().envehicle();
	}

	private class EnvehicleTableModel extends AbstractTableModel {
		@Override
		public int getRowCount() {
			// TODO 自动生成的方法存根
			return 12;
		}

		@Override
		public int getColumnCount() {
			// TODO 自动生成的方法存根
			return 5;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO 自动生成的方法存根
			int index = pageNum * 12 + rowIndex;
			if (index > controller.getEnvehicleBL().getWaitingOrderList()
					.size() - 1)
				return null;
			OrderVO order = controller.getEnvehicleBL()
					.getAllocateWwaitingOrderBL().updateWaitingList()
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

	// public static void main(String[] args) {
	// JFrame frame = new JFrame();
	// frame.setSize(800, 550);
	// frame.add(new EnvehiclePanel(null));
	// frame.setVisible(true);
	// }
}
