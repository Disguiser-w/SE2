package presentation.businessui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import businesslogic.businessbl.controller.AcceptCargoController;
import businesslogic.businessbl.controller.BusinessMainController;
import presentation.commonui.LocationHelper;
import presentation.commonui.OperationPanel;

public class OrderReceiveManagerPanel extends OperationPanel {
	private JLabel vehicleIDLabel;
	private JTextField vehicleIDField;

	private JLabel timeLabel;
	private JTextField timeField;

	private JLabel local;
	private JTextField localField;

	private JLabel inputLabel;
	private JTextField orderNumField;
	private JButton inputConfirmButton;

	private JLabel nextPageLabel;
	private JLabel numOfPage;
	private JLabel previousPageLabel;

	private JButton confirmButton;

	private JTable messageTable;
	private JLabel tableHead;

	private int num;
	private boolean isFirstTime;

	// 定为
	private LocationHelper helper;
	private AcceptCargoController controller;
	private ArrayList<String> orderNum;

	public OrderReceiveManagerPanel(AcceptCargoController acceptCargoController) {
		this.controller = acceptCargoController;

		vehicleIDLabel = new JLabel("司机ID");
		vehicleIDField = new JTextField();

		timeLabel = new JLabel("时间");
		timeField = new JTextField();

		local = new JLabel("营业厅ID");
		localField = new JTextField();

		inputLabel = new JLabel("输入订单号");
		orderNumField = new JTextField();
		inputConfirmButton = new JButton();

		nextPageLabel = new JLabel("<");
		numOfPage = new JLabel();
		previousPageLabel = new JLabel(">");

		confirmButton = new JButton();

		messageTable = new JTable();
		tableHead = new JLabel();

		orderNum = new ArrayList<String>();

		add(vehicleIDLabel);
		add(vehicleIDField);
		add(timeLabel);
		add(timeField);
		add(local);
		add(localField);

		add(inputLabel);
		add(orderNumField);
		add(inputConfirmButton);

		add(nextPageLabel);
		add(numOfPage);
		add(previousPageLabel);

		add(confirmButton);
		add(messageTable);
		add(tableHead);

		inputLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		nextPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		previousPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		confirmButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		orderNumField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		messageTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// helper = new LocationHelper(this);
		isFirstTime = true;
		setLayout(null);
		addListener();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 组件setBounds;
		vehicleIDLabel.setBounds((int) (width * 2.4007682458386683 / 25), (int) (height * 7.991071428571429 / 20),
				(int) (width * 2.144686299615877 / 25), (int) (height * 1.3392857142857142 / 20));
		vehicleIDField.setBounds((int) (width * 5.217669654289373 / 25), (int) (height * 7.991071428571429 / 20),
				(int) (width * 4.449423815620999 / 25), (int) (height * 1.3839285714285714 / 20));
		timeLabel.setBounds((int) (width * 2.4007682458386683 / 25), (int) (height * 10.669642857142858 / 20),
				(int) (width * 2.144686299615877 / 25), (int) (height * 1.3392857142857142 / 20));
		timeField.setBounds((int) (width * 5.217669654289373 / 25), (int) (height * 10.669642857142858 / 20),
				(int) (width * 4.41741357234315 / 25), (int) (height * 1.3392857142857142 / 20));
		local.setBounds((int) (width * 2.4007682458386683 / 25), (int) (height * 5.3125 / 20),
				(int) (width * 2.144686299615877 / 25), (int) (height * 1.3392857142857142 / 20));
		localField.setBounds((int) (width * 5.217669654289373 / 25), (int) (height * 5.3125 / 20),
				(int) (width * 4.41741357234315 / 25), (int) (height * 1.3392857142857142 / 20));
		inputLabel.setBounds((int) (width * 2.4007682458386683 / 25), (int) (height * 2.6339285714285716 / 20),
				(int) (width * 2.144686299615877 / 25), (int) (height * 1.3392857142857142 / 20));
		orderNumField.setBounds((int) (width * 5.217669654289373 / 25), (int) (height * 2.6339285714285716 / 20),
				(int) (width * 4.41741357234315 / 25), (int) (height * 1.3392857142857142 / 20));
		inputConfirmButton.setBounds((int) (width * 11.139564660691422 / 25), (int) (height * 2.6339285714285716 / 20),
				(int) (width * 1.9846350832266326 / 25), (int) (height * 1.3392857142857142 / 20));
		nextPageLabel.setBounds((int) (width * 17.34955185659411 / 25), (int) (height * 16.696428571428573 / 20),
				(int) (width * 1.1523687580025608 / 25), (int) (height * 1.5178571428571428 / 20));
		numOfPage.setBounds((int) (width * 18.50192061459667 / 25), (int) (height * 16.696428571428573 / 20),
				(int) (width * 1.1523687580025608 / 25), (int) (height * 1.5178571428571428 / 20));
		previousPageLabel.setBounds((int) (width * 19.65428937259923 / 25), (int) (height * 16.696428571428573 / 20),
				(int) (width * 1.1523687580025608 / 25), (int) (height * 1.5178571428571428 / 20));
		confirmButton.setBounds((int) (width * 6.434058898847631 / 25), (int) (height * 15.0 / 20),
				(int) (width * 2.848911651728553 / 25), (int) (height * 1.9642857142857142 / 20));
		messageTable.setBounds((int) (width * 14.852752880921894 / 25), (int) (height * 3.8392857142857144 / 20),
				(int) (width * 8.034571062740078 / 25), (int) (height * 11.785714285714286 / 20));
		tableHead.setBounds((int) (width * 14.852752880921894 / 25), (int) (height * 2.6339285714285716 / 20),
				(int) (width * 8.034571062740078 / 25), (int) (height * 1.25 / 20));
		setInfos();
	}

	public void paintComponent(Graphics g) {
		if (isFirstTime) {
			isFirstTime = false;
			setInfos();
		}
		super.paintComponent(g);

	}

	private void setBaseInfos() {
		timeField.setText(getDate());
		timeField.setEditable(false);

		localField.setText(BusinessMainController.businessVO.organizationVO.organizationID);
		localField.setEditable(false);

		// 设置成不可编辑不可改变位置，大小
		messageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		messageTable.getTableHeader().setReorderingAllowed(false);
		messageTable.getTableHeader().setResizingAllowed(false);

		TableColumn column1 = messageTable.getColumnModel().getColumn(0);

		messageTable.setRowHeight(messageTable.getHeight() / 8);

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

	}

	private void setInfos() {
		numOfPage.setText(num + 1 + "/" + ((orderNum.size() - 1) / 8 + 1));
		messageTable.setModel(new MessageTableModel());
		setBaseInfos();

	}

	private void addListener() {
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

				if (num >= (orderNum.size() - 1) / 8)
					return;
				else {
					num++;
					setInfos();
				}
			}
		});
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (orderNum.size() == 0) {
					warnning("当前订单数为0");
					return;
				}

				String vehicleID = vehicleIDField.getText();
				if (vehicleID.equals("")) {
					warnning("请输入车辆ID");
					return;
				}

				if (!controller.vehicleExist(vehicleID)) {
					warnning("该车辆不存在,请检查车辆ID是否有误");
					return;
				}

				if (controller.acceptCargo(vehicleID, orderNum)) {
					success("成功");
				} else {
					warnning("失败");
				}

				setInfos();

			}
		});

		inputConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String orderNumber = orderNumField.getText();
				if (orderNumber.equals("")) {
					warnning("请输入订单号");
					return;
				}
				if (!controller.orderExist(orderNumber)) {
					warnning("不存在此订单号，请检查订单号是否有误");
					return;
				}

				orderNum.add(orderNumber);
				setInfos();
				orderNumField.setText("");
			}
		});

	}

	private class MessageTableModel extends AbstractTableModel {

		public int getRowCount() {
			// TODO Auto-generated method stub
			return 8;
		}

		@Override
		public int getColumnCount() {
			return 1;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {

			int index = num * 8 + rowIndex;
			if (index > orderNum.size() - 1)
				return null;
			String[] infos = orderNum.get(index).split(" ");

			return infos[columnIndex];

		}

		public String getColumnName(int c) {
			switch (c) {
			case 0:
				return "订单号";
			default:
				return null;

			}
		}

	}

	private void warnning(String message) {
		// fix 放到底部信息栏
		JOptionPane.showMessageDialog(null, message, "", JOptionPane.ERROR_MESSAGE);
	}

	private void success(String message) {
		JOptionPane.showMessageDialog(null, message, "", JOptionPane.DEFAULT_OPTION);
	}

	private String getDate() {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(new Date());
	}
}
