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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.businessbl.controller.GatheringController;
import presentation.commonui.LocationHelper;
import presentation.commonui.OperationPanel;

public class ChargeCollectionPanel extends OperationPanel {
	// private JLabel tablehead;
	private JTable messageTable;
	private JLabel totalMessageLabel;
	private JButton collectionButton;
	private JLabel previousPage;
	private JLabel numOfPage;
	private JLabel nextPage;

	private int num;
	private LocationHelper helper;

	private ArrayList<String> chargeInfos;
	private GatheringController controller;
	private boolean isFirstTime;

	public ChargeCollectionPanel(GatheringController controller) {

		this.controller = controller;

		// tablehead = new JLabel();
		messageTable = new JTable();
		totalMessageLabel = new JLabel();
		collectionButton = new JButton();
		previousPage = new JLabel("<");
		numOfPage = new JLabel();
		nextPage = new JLabel(">");

		messageTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		totalMessageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// add(tablehead);
		add(messageTable.getTableHeader());
		add(messageTable);
		add(totalMessageLabel);
		add(collectionButton);
		add(previousPage);
		add(numOfPage);
		add(nextPage);

		// helper = new LocationHelper(this);
		setLayout(null);

		chargeInfos = controller.getChargeInfo();
		if (chargeInfos == null) {
			chargeInfos = new ArrayList<String>();
		}

		isFirstTime = true;

		addListener();

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 所有组件setBounds

		messageTable.getTableHeader().setBounds((int) (width * 1.120358514724712 / 25),
				(int) (height * 1.3839285714285714 / 20), (int) (width * 22.791293213828425 / 25),
				(int) (height * 1.2946428571428572 / 20));
		messageTable.setBounds((int) (width * 1.120358514724712 / 25), (int) (height * 2.6339285714285716 / 20),
				(int) (width * 22.759282970550576 / 25), (int) (height * 12.857142857142858 / 20));
		totalMessageLabel.setBounds((int) (width * 1.088348271446863 / 25), (int) (height * 16.607142857142858 / 20),
				(int) (width * 12.96414852752881 / 25), (int) (height * 2.3214285714285716 / 20));
		collectionButton.setBounds((int) (width * 21.927016645326503 / 25), (int) (height * 17.142857142857142 / 20),
				(int) (width * 1.9846350832266326 / 25), (int) (height * 1.3839285714285714 / 20));
		previousPage.setBounds((int) (width * 16.645326504481435 / 25), (int) (height * 17.142857142857142 / 20),
				(int) (width * 0.9923175416133163 / 25), (int) (height * 1.3839285714285714 / 20));
		numOfPage.setBounds((int) (width * 17.63764404609475 / 25), (int) (height * 17.142857142857142 / 20),
				(int) (width * 0.9923175416133163 / 25), (int) (height * 1.3839285714285714 / 20));
		nextPage.setBounds((int) (width * 18.629961587708067 / 25), (int) (height * 17.142857142857142 / 20),
				(int) (width * 0.9923175416133163 / 25), (int) (height * 1.3839285714285714 / 20));
		setInfos();
	}

	public void paintComponent(Graphics g) {
		if (isFirstTime) {
			setInfos();
			isFirstTime = false;
		}
		super.paintComponent(g);

	}

	public void setInfos() {
		numOfPage.setText(num + 1 + "/" + ((chargeInfos.size() - 1) / 8 + 1));
		messageTable.setModel(new MessageTableModel());
		setTableInfos();

	}

	public void addListener() {
		previousPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (num == 0)
					return;
				else {
					num--;
					setInfos();

				}
			}
		});

		nextPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (num >= (chargeInfos.size() - 1) / 8)
					return;
				else {
					num++;
					setInfos();
				}
			}
		});

		collectionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double total = controller.gathering();
				String date = getDate();
				String oID = BusinessMainController.businessVO.organizationVO.organizationID;

				if (total == 0) {
					warnning("已完成今日收款汇总或今日接单树为0");
					return;
				}

				totalMessageLabel.setText(" 日期 : " + date + "  营业厅编号 : " + oID + "  金额总和 : " + total);
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

		// 设置宽度
		column1.setPreferredWidth(messageTable.getWidth() / 2);
		column2.setPreferredWidth(messageTable.getWidth() / 2);

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

	}

	private class MessageTableModel extends AbstractTableModel {

		public int getRowCount() {
			// TODO Auto-generated method stub
			return 8;
		}

		@Override
		public int getColumnCount() {
			return 2;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {

			int index = num * 8 + rowIndex;
			if (index > chargeInfos.size() - 1)
				return null;
			String[] infos = chargeInfos.get(index).split(" ");

			return infos[columnIndex];

		}

		public String getColumnName(int c) {
			switch (c) {
			case 0:
				return "快递员ID";
			case 1:
				return "当日收款总额";
			case 2:

			default:
				return null;

			}
		}

	}

	private void warnning(String message) {
		// fix 放到底部信息栏
		JOptionPane.showMessageDialog(null, message, "车辆信息错误", JOptionPane.ERROR_MESSAGE);
	}

	private String getDate() {
		return (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
	}

}
