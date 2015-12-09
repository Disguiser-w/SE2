package presentation.businessui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import businesslogic.businessbl.controller.EnVehicleController;
import presentation.commonui.LocationHelper;

public class EnVehiclePanel extends JPanel {
	private JTable messageTable;
	private JLabel nextPageLabel;
	private JLabel previousPageLabel;
	private JButton distributeButton;
	private JButton confirmButton;
	private JLabel numOfPage;

	private LocationHelper helper;
	private EnVehicleController controller;
	private ArrayList<String> result;
	private int num;
	private boolean hasEnVehicle;

	public EnVehiclePanel(EnVehicleController controller) {
		this.controller = controller;
		messageTable = new JTable();

		nextPageLabel = new JLabel();
		previousPageLabel = new JLabel();

		distributeButton = new JButton("确认(清空)");
		confirmButton = new JButton("装车");

		result = new ArrayList<String>();

		num = 0;
		hasEnVehicle = false;
		numOfPage = new JLabel();

		add(messageTable);
		add(messageTable.getTableHeader());
		add(nextPageLabel);
		add(previousPageLabel);

		add(distributeButton);
		add(confirmButton);
		add(numOfPage);

		setLayout(null);
		addListener();

		helper = new LocationHelper(this);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		messageTable.setBounds((int) (width * 1.0243277848911652 / 25), (int) (height * 3.169642857142857 / 20),
				(int) (width * 23.111395646606915 / 25), (int) (height * 13.214285714285714 / 20));
		nextPageLabel.setBounds((int) (width * 13.412291933418693 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.056338028169014 / 25), (int) (height * 1.4732142857142858 / 20));
		previousPageLabel.setBounds((int) (width * 11.267605633802816 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.056338028169014 / 25), (int) (height * 1.4732142857142858 / 20));
		distributeButton.setBounds((int) (width * 22.43918053777209 / 25), (int) (height * 17.589285714285715 / 20),
				(int) (width * 1.6965428937259923 / 25), (int) (height * 1.25 / 20));
		confirmButton.setBounds((int) (width * 20.166453265044815 / 25), (int) (height * 17.589285714285715 / 20),
				(int) (width * 1.6965428937259923 / 25), (int) (height * 1.25 / 20));
		numOfPage.setBounds((int) (width * 12.32394366197183 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.088348271446863 / 25), (int) (height * 1.4732142857142858 / 20));
		messageTable.getTableHeader().setBounds((int) (width * 1.0243277848911652 / 25),
				(int) (height * 1.7857142857142858 / 20), (int) (width * 23.111395646606915 / 25),
				(int) (height * 1.3839285714285714 / 20));
		setInfos();
	}

	public void setInfos() {
		messageTable.setModel(new MessageTableModel());
		setTableInfos();
		numOfPage.setText(num + 1 + "/" + ((result.size() - 1) / 8 + 1));
		repaint();
	}

	public void addListener() {
		previousPageLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (num == 0)
					return;
				else {
					num--;
					setInfos();

				}
			}
		});

		nextPageLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (num >= (result.size() - 1) / 8)
					return;
				else {
					num++;
					setInfos();
				}
			}
		});
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!hasEnVehicle) {
					hasEnVehicle = true;
					result = controller.autoTruckLoading();
					setInfos();
				} else
					warnning("已完成装车或不存在需要撞车的订单");
				if (result.size() == 0)
					warnning("已完成装车或不存在需要撞车的订单");
			}
		});
		distributeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				result = new ArrayList<String>();
				setInfos();
			}
		});

	}

	private void setTableInfos() {

		// 设置成不可编辑不可改变位置，大小
		messageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		messageTable.getTableHeader().setReorderingAllowed(false);
		messageTable.getTableHeader().setResizingAllowed(false);

		TableColumn column1 = messageTable.getColumnModel().getColumn(0);
		TableColumn column2 = messageTable.getColumnModel().getColumn(1);
		TableColumn column3 = messageTable.getColumnModel().getColumn(2);
		TableColumn column4 = messageTable.getColumnModel().getColumn(3);

		// 设置宽度
		column1.setPreferredWidth(messageTable.getWidth() / 3);
		column2.setPreferredWidth(messageTable.getWidth() / 3);
		column3.setPreferredWidth(messageTable.getWidth() / 6);
		column4.setPreferredWidth(messageTable.getWidth() / 6);

		messageTable.setRowHeight(messageTable.getHeight() / 8);
		// tablePanel.setSize(tablePanel.getWidth(), h * 8 +
		// messageTable.getTableHeader().getHeight() + 4);

		//
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (row % 2 == 0)
					setBackground(Color.cyan);
				else
					setBackground(Color.white);

				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		};

		tcr.setHorizontalAlignment(JLabel.CENTER);
		column1.setCellRenderer(tcr);
		column2.setCellRenderer(tcr);
		column3.setCellRenderer(tcr);
		column4.setCellRenderer(tcr);
	}

	private class MessageTableModel extends AbstractTableModel {

		public int getRowCount() {
			// TODO Auto-generated method stub
			return 8;
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {

			int index = num * 8 + rowIndex;
			if (index > result.size() - 1)
				return null;
			String[] infos = result.get(index).split(" ");

			return infos[columnIndex];

		}

		public String getColumnName(int c) {
			switch (c) {
			case 0:
				return "车辆编号";
			case 1:
				return "出发地";
			case 2:
				return "目的地";
			case 3:
				return "订单号";
			default:
				return null;

			}
		}

	}

	private void warnning(String message) {
		// fix 放到底部信息栏
		JOptionPane.showMessageDialog(null, message, "车辆信息错误", JOptionPane.ERROR_MESSAGE);
	}
}
