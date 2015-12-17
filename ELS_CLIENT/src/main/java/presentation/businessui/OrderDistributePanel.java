package presentation.businessui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import businesslogic.businessbl.controller.DistributeOrderController;
import presentation.commonui.LocationHelper;
import presentation.commonui.OperationPanel;

public class OrderDistributePanel extends OperationPanel {
	private JTable messageTable;

	private JLabel nextPageLabel;
	private JLabel numOfPage;
	private JLabel previousPageLabel;

	private JButton distributeButton;
	private JButton confirmButton;

	private ArrayList<String> result;
	private DistributeOrderController controller;
	private int num;

	private boolean isFirstTime;
	private LocationHelper helper;

	public OrderDistributePanel(DistributeOrderController controller) {
		this.controller = controller;
		messageTable = new JTable();

		nextPageLabel = new JLabel("<");
		numOfPage = new JLabel();
		previousPageLabel = new JLabel(">");

		distributeButton = new JButton();
		confirmButton = new JButton();

		num = 0;
		result = new ArrayList<String>();

		messageTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nextPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		previousPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(messageTable);
		add(messageTable.getTableHeader());

		add(nextPageLabel);
		add(numOfPage);
		add(previousPageLabel);

		add(distributeButton);
		add(confirmButton);

		isFirstTime = true;
		// helper = new LocationHelper(this);
		setLayout(null);
		addListener();

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 设置组件位置

		messageTable.setBounds((int) (width * 1.9526248399487836 / 25), (int) (height * 3.392857142857143 / 20),
				(int) (width * 21.414852752880922 / 25), (int) (height * 12.142857142857142 / 20));
		messageTable.getTableHeader().setBounds((int) (width * 1.9526248399487836 / 25),
				(int) (height * 2.0535714285714284 / 20), (int) (width * 21.414852752880922 / 25),
				(int) (height * 1.3839285714285714 / 20));
		nextPageLabel.setBounds((int) (width * 11.267605633802816 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.056338028169014 / 25), (int) (height * 1.4732142857142858 / 20));
		numOfPage.setBounds((int) (width * 12.32394366197183 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.056338028169014 / 25), (int) (height * 1.4732142857142858 / 20));
		previousPageLabel.setBounds((int) (width * 13.380281690140846 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.056338028169014 / 25), (int) (height * 1.4732142857142858 / 20));
		distributeButton.setBounds((int) (width * 18.758002560819463 / 25), (int) (height * 16.964285714285715 / 20),
				(int) (width * 1.7285531370038412 / 25), (int) (height * 1.2946428571428572 / 20));
		confirmButton.setBounds((int) (width * 21.606914212548016 / 25), (int) (height * 16.964285714285715 / 20),
				(int) (width * 1.7285531370038412 / 25), (int) (height * 1.2946428571428572 / 20));
		setInfos();
	}

	public void paintComponent(Graphics g) {
		if (isFirstTime) {
			isFirstTime = false;
			setInfos();
		}
		super.paintComponent(g);

	}

	public void setInfos() {
		numOfPage.setText(num + 1 + "/" + ((result.size() - 1) / 8 + 1));
		messageTable.setModel(new MessageTableModel());
		setBaseInfos();

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
				result = new ArrayList<String>();
				setInfos();

			}
		});
		distributeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result = controller.distributeOrder();
				if (result.size() == 0)
					warnning("不存在需要派件的订单");
				else
					setInfos();
			}
		});

	}

	private void setBaseInfos() {

		// 设置成不可编辑不可改变位置，大小
		messageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		messageTable.getTableHeader().setReorderingAllowed(false);
		messageTable.getTableHeader().setResizingAllowed(false);

		TableColumn column1 = messageTable.getColumnModel().getColumn(0);
		TableColumn column2 = messageTable.getColumnModel().getColumn(1);
		TableColumn column3 = messageTable.getColumnModel().getColumn(2);

		// 设置宽度
		column1.setPreferredWidth(messageTable.getWidth() / 4);
		column2.setPreferredWidth(messageTable.getWidth() / 2);
		column3.setPreferredWidth(messageTable.getWidth() / 4);

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
		column2.setCellRenderer(tcr);
		column3.setCellRenderer(tcr);
	}

	private class MessageTableModel extends AbstractTableModel {

		public int getRowCount() {
			// TODO Auto-generated method stub
			return 8;
		}

		@Override
		public int getColumnCount() {
			return 3;
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
				return "快递员ID";
			case 1:
				return "地址";
			case 2:
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
}
