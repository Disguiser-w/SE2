package presentation.intermediateui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import vo.PlaneVO;
import businesslogic.intermediatebl.controller.IntermediateMainController;

public class PlaneManagementPanel extends JLabel {
	private IntermediateMainController controller;

	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	private JButton addButton;
	private JButton deleteButton;
	private JButton next;
	private JButton previous;

	private JLabel function;

	private JTextField searchTextField;

	private VehicleManagementInfoTable info;
	private VehicleManagementTableModel model;

	private int pageNum;
	private int pageNum_max;

	public PlaneManagementPanel(IntermediateMainController controller) {
		this.controller = controller;

		addButton = new JButton("add");
		deleteButton = new JButton("delete");
		next = new JButton("next");
		previous = new JButton("previous");

		function = new JLabel("飞机信息管理 ");

		searchTextField = new JTextField("KD-00001");

		model = new VehicleManagementTableModel();
		info = new VehicleManagementInfoTable(model);

		pageNum = 0;
		// pageNum_max = (controller.getPlaneList().size() - 2) / 12;
		pageNum_max = 3;
		setCmpLocation();

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				addui();
			}
		});

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				deleteui();
			}
		});

		next.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println(pageNum);
				if (pageNum >= pageNum_max)
					return;
				else {
					pageNum++;
					info.setModel(new VehicleManagementTableModel());
					info.setuiInfo();
					;
				}
			}
		});

		previous.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println(pageNum);
				if (pageNum == 0)
					return;
				else {
					pageNum--;
					info.setModel(new VehicleManagementTableModel());
					info.setuiInfo();
				}
			}
		});
		info.setBackground(getBackground());
		setLayout(null);

		add(addButton);
		add(deleteButton);
		add(next);
		add(previous);
		add(function);
		add(searchTextField);
		add(info);
		add(info.getTableHeader());
	}

	public void setCmpLocation() {
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		addButton.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		deleteButton.setBounds(PANEL_WIDTH * 5 / 9, PANEL_HEIGHT * 3 / 16,
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
		// System.out.println(controller.getIntermediateCentre().name);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation();
		repaint();
	}

	public void addui() {

	}

	public void deleteui() {

	}

	private class VehicleManagementTableModel extends AbstractTableModel {
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
			// System.out.println(controller.getPlaneList().size());
			int index = pageNum * 12 + rowIndex;
			if (index > controller.getPlaneList().size() - 1) {
				// System.out.println("gaga");
				return null;
			}
			PlaneVO plane = controller.getPlaneList().get(index);
			// System.out.println(plane.ID);
			if (plane != null) {
				switch (columnIndex) {
				case 0:
					return plane.ID;
				case 1:
					return controller.getIntermediateCentre().name;
				case 2:
					return plane.destination;
				case 3:
					return plane.farePrice;
				case 4:
					return "工作中";
				}
			} else
				return null;
			return null;
		}

		public String getColumnName(int c) {
			if (c == 0)
				return "飞机编号";
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

	public static void main(String[] args) throws MalformedURLException,
			RemoteException, NotBoundException {
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new PlaneManagementPanel(new IntermediateMainController(
				"141250185")));
		frame.setVisible(true);
	}
}
