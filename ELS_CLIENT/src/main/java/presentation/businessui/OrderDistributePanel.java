package presentation.businessui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import businesslogic.businessbl.controller.DistributeOrderController;
import presentation.commonui.LocationHelper;

public class OrderDistributePanel extends JPanel {
	private JTable messageTable;
	private JLabel messageTableHeader;

	// private JLabel

	// private JLabel orderNumLabel;
	private JTextField orderNumField;

	private JButton distributeButton;
	private JButton confirmButton;
	private LocationHelper helper;
	private JLabel sendLabel;
	private JLabel printLabel;

	private JLabel nextPageLabel;
	private JLabel previousPageLabel;

	private JLabel numOfPage;

	private ArrayList<String> result;
	private DistributeOrderController controller;
	private int num;

	public OrderDistributePanel(DistributeOrderController controller) {
		this.controller = controller;
		messageTable = new JTable();
		messageTableHeader = new JLabel();

		nextPageLabel = new JLabel();
		previousPageLabel = new JLabel();
		confirmButton = new JButton();
		sendLabel = new JLabel();
		printLabel = new JLabel();

		num = 0;
		result = new ArrayList<String>();

		messageTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nextPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		previousPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(messageTable);
		add(nextPageLabel);
		add(previousPageLabel);
		add(confirmButton);
		add(sendLabel);
		add(printLabel);

		helper = new LocationHelper(this);
		setLayout(null);
		addListener();

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 设置组件位置
		messageTable.setBounds((int) (width * 0.9923175416133163 / 25), (int) (height * 3.9732142857142856 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 11.964285714285714 / 20));
		nextPageLabel.setBounds((int) (width * 13.380281690140846 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.0243277848911652 / 25), (int) (height * 1.4732142857142858 / 20));
		previousPageLabel.setBounds((int) (width * 11.555697823303458 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.0243277848911652 / 25), (int) (height * 1.4732142857142858 / 20));

		confirmButton.setBounds((int) (width * 21.9910371318822 / 25), (int) (height * 17.232142857142858 / 20),
				(int) (width * 1.9846350832266326 / 25), (int) (height * 1.3839285714285714 / 20));

		sendLabel.setBounds((int) (width * 22.37516005121639 / 25), (int) (height * 0.8482142857142857 / 20),
				(int) (width * 1.5685019206145967 / 25), (int) (height * 2.1875 / 20));
		printLabel.setBounds((int) (width * 20.03841229193342 / 25), (int) (height * 0.8482142857142857 / 20),
				(int) (width * 1.5685019206145967 / 25), (int) (height * 2.1875 / 20));
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

				result = controller.distributeOrder();
				warnning("不存在需要派件的订单");
			}
		});
		distributeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
