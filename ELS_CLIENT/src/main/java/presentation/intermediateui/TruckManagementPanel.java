package presentation.intermediateui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import vo.TruckVO;
import businesslogic.intermediatebl.controller.IntermediateMainController;

public class TruckManagementPanel extends JPanel {
	private IntermediateMainController controller;

	private IntermediateFrame frame;

	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	private JButton addButton;
	private JButton deleteButton;
	private JButton next;
	private JButton previous;
	private JButton delete_ok;
	private JButton modifyButton;

	private JLabel function;

	private JTextField searchTextField;

	private JCheckBox[] isDelete;
	private JButton[] isModify;

	private VehicleManagementInfoTable info;
	private VehicleManagementTableModel model;

	private int pageNum;
	private int pageNum_max;

	public TruckManagementPanel(IntermediateMainController c,
			IntermediateFrame f) {
		this.controller = c;
		this.frame = f;

		addButton = new JButton("add");
		deleteButton = new JButton("delete");
		next = new JButton("next");
		previous = new JButton("previous");
		delete_ok = new JButton("ok");
		modifyButton = new JButton("modify");

		function = new JLabel("汽车信息管理 ");

		searchTextField = new JTextField("KD-00001");

		model = new VehicleManagementTableModel();
		info = new VehicleManagementInfoTable(model);

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

		isModify = new JButton[12];
		for (int i = 0; i < isModify.length; i++) {
			isModify[i] = new JButton();
			isModify[i].setBounds(PANEL_WIDTH * 2 / 33, PANEL_HEIGHT * 29 / 120
					+ PANEL_HEIGHT * (i + 1) / 22 + PANEL_HEIGHT / 80,
					PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
			add(isModify[i]);
			isModify[i].setVisible(false);
		}

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

		modifyButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				modifyui();
			}
		});

		next.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				pageNum_max = (controller.getTruckList().size()) / 12;
				if (pageNum >= pageNum_max)
					return;
				else {
					pageNum++;
					info.setModel(new VehicleManagementTableModel());
					info.setuiInfo();
				}
			}
		});

		previous.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				pageNum_max = (controller.getTruckList().size()) / 12;
				if (pageNum == 0)
					return;
				else {
					pageNum--;
					info.setModel(new VehicleManagementTableModel());
					info.setuiInfo();
				}
			}
		});

		delete_ok.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				delete_okui();
			}
		});

		info.setBackground(getBackground());
		setLayout(null);

		add(addButton);
		add(deleteButton);
		add(modifyButton);
		add(next);
		add(previous);
		add(function);
		add(searchTextField);
		add(info);
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
		modifyButton.setBounds(PANEL_WIDTH * 11 / 18, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		searchTextField.setBounds(PANEL_WIDTH * 13 / 18, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH * 2 / 9, PANEL_HEIGHT / 24);
		previous.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		next.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 45 / 48,
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

	public void addui() {
		frame.changePanel(new TrainManagement_newPanel(controller, frame));
	}

	public void deleteui() {
		delete_ok.setVisible(true);
		for (int i = 0; i < controller.getTruckList().size() - 12 * pageNum; i++)
			isDelete[i].setVisible(true);
		delete_ok.setVisible(true);
	}

	public void delete_okui() {
		for (int i = 0; i < isDelete.length; i++) {
			int delete_num = 0;
			if (isDelete[i].isSelected()) {
				try {
					controller.getTruckManagerBL().deleteTruck(
							controller.getTruckList().get(
									i + pageNum * 12 - delete_num++));
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			isDelete[i].setVisible(false);
		}

		delete_ok.setVisible(false);

		frame.toMainPanel();
	}

	public void modifyui() {
		for (int i = 0; i < controller.getTruckList().size() - 12 * pageNum; i++) {
			isModify[i].setVisible(true);
			isModify[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int count = 0;
					JButton temp = (JButton) e.getSource();
					for (; count < isModify.length; count++) {
						if (temp == isModify[count]) {
							break;
						}
					}

					frame.changePanel(new TruckManagement_modifyPanel(
							controller, frame, controller.getTruckList().get(
									pageNum * 12 + count)));

					for (JButton button : isModify)
						button.setVisible(false);
				}
			});
		}

	}

	private class VehicleManagementTableModel extends AbstractTableModel {

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
			// System.out.println(controller.getTruckList().size());
			int index = pageNum * 12 + rowIndex;
			if (index > controller.getTruckList().size() - 1) {
				// System.out.println("gaga");
				return null;
			}
			TruckVO truck = controller.getTruckList().get(index);
			if (truck != null) {
				switch (columnIndex) {
				case 0:
					return truck.ID;
				case 1:
					return controller.getIntermediateCentre().name;
				case 2:
					return truck.destination;
				case 3:
					return truck.farePrice;
				case 4:
					return "工作中";
				}
			} else
				return null;
			return null;
		}

		public String getColumnName(int c) {
			if (c == 0)
				return "汽车编号";
			if (c == 1)
				return "所属机构";
			if (c == 2)
				return "到达地";
			if (c == 3)
				return "运送价格";
			if (c == 4)
				return "状态";

			return null;
		}
	}
	// public static void main(String[] args) {
	// JFrame frame = new JFrame();
	// frame.setSize(800, 550);
	// frame.add(new TruckManagementPanel(null));
	// frame.setVisible(true);
	// }
}
