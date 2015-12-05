package presentation.expressui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import businesslogic.expressbl.controller.LogisticQueryController;
import presentation.commonui.DateChooser;
import vo.OrderVO;

public class QueryPanel extends JPanel {
	private JLabel timeInputLabel;
	private JTextField timeField;
	private JLabel timeSetLabel;
	private JButton confirmButton;
	private JLabel nextPageLabel;
	private JLabel previousPageLabel;
	private JTable messageTable;
	private JLabel messageHeader;

	private ArrayList<OrderVO> submitOrders;
	private ArrayList<String> queryOrders;
	// private LocationHelper help;
	private int num;

	private LogisticQueryController controller;

	public QueryPanel(LogisticQueryController controller) {
		num = 0;
		this.controller = controller;
		timeInputLabel = new JLabel("请输入时间");
		timeField = new JTextField();
		timeSetLabel = new JLabel();
		confirmButton = new JButton();
		nextPageLabel = new JLabel(">");
		previousPageLabel = new JLabel("<");

		messageTable = new JTable();

		queryOrders = new ArrayList<String>();

		timeField.setToolTipText("例:2001-12-12");

		// 测试位置时使用
		timeInputLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		timeSetLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		nextPageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		previousPageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		// messageTable.setBorder(BorderFactory.createLineBorder(Color.black));
		messageTable.setBackground(this.getBackground());

		timeInputLabel.setHorizontalAlignment(JLabel.CENTER);

		add(timeInputLabel);
		add(timeField);
		add(timeSetLabel);
		add(confirmButton);
		add(nextPageLabel);
		add(previousPageLabel);
		add(messageTable);
		add(messageTable.getTableHeader());
		timeSetLabel.setLayout(new BorderLayout());
		timeSetLabel.add(new DateChooser(timeField), BorderLayout.CENTER);

		// help = new LocationHelper(this);
		setLayout(null);
		addListener();

	}

	public void setBounds(int x, int y, int width, int height) {
		// setBounds
		super.setBounds(x, y, width, height);
		timeInputLabel.setBounds((int) (width * 1.3764404609475032 / 25), (int) (height * 1.8303571428571428 / 20),
				(int) (width * 3.8092189500640203 / 25), (int) (height * 1.1160714285714286 / 20));
		timeField.setBounds((int) (width * 6.370038412291933 / 25), (int) (height * 1.8303571428571428 / 20),
				(int) (width * 4.481434058898848 / 25), (int) (height * 1.1607142857142858 / 20));
		timeSetLabel.setBounds((int) (width * 11.331626120358514 / 25), (int) (height * 1.8303571428571428 / 20),
				(int) (width * 0.7682458386683739 / 25), (int) (height * 1.1160714285714286 / 20));
		confirmButton.setBounds((int) (width * 13.316261203585148 / 25), (int) (height * 1.8303571428571428 / 20),
				(int) (width * 1.5685019206145967 / 25), (int) (height * 1.1160714285714286 / 20));
		nextPageLabel.setBounds((int) (width * 21.9910371318822 / 25), (int) (height * 17.589285714285715 / 20),
				(int) (width * 1.088348271446863 / 25), (int) (height * 1.5178571428571428 / 20));
		previousPageLabel.setBounds((int) (width * 20.230473751600513 / 25), (int) (height * 17.589285714285715 / 20),
				(int) (width * 1.088348271446863 / 25), (int) (height * 1.5178571428571428 / 20));
		messageTable.setBounds((int) (width * 1.3764404609475032 / 25), (int) (height * 6.026785714285714 / 20),
				(int) (width * 22.151088348271447 / 25), (int) (height * 10.982142857142858 / 20));
		messageTable.getTableHeader().setBounds((int) (width * 1.3764404609475032 / 25),
				(int) (height * 4.642857142857143 / 20), (int) (width * 22.151088348271447 / 25),
				(int) (height * 1.4285714285714286 / 20));

		setInfos();

	}

	private void setInfos() {

		messageTable.setModel(new MessgeTableModel());
		setTableInfos();
	}

	private void setTableInfos() {
		// 设置成不可编辑不可改变位置，大小
		messageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		messageTable.getTableHeader().setReorderingAllowed(false);
		messageTable.getTableHeader().setResizingAllowed(false);

		TableColumn column1 = messageTable.getColumnModel().getColumn(0);
		TableColumn column2 = messageTable.getColumnModel().getColumn(1);
		TableColumn column3 = messageTable.getColumnModel().getColumn(2);

		// 设置宽度
		column1.setPreferredWidth(messageTable.getWidth() * 3 / 4);
		column2.setPreferredWidth(messageTable.getWidth() / 20);
		column3.setPreferredWidth(messageTable.getWidth() / 5);
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

	}

	private void addListener() {

		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitOrders = controller.query();
				String date = timeField.getText().trim();

				if (date.equals("")) {
					warnning("请输入日期");
					return;
				}

				DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

				// 无语，偷懒

				try {
					Date d = fmt.parse(date);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					warnning("日期格式不正确");
					return;
				}

				queryOrders = new ArrayList<String>();
				for (OrderVO i : submitOrders) {
					if (i.builtDate.equals(date)) {
						String state = null;
						switch (i.order_state) {

						case WAITING_ENVEHICLE:
							state = "等待转运";
							break;
						case TRANSFERING:
							state = "转运中";
							break;
						case WAITING_DISTRIBUTE:
							state = "等待派件";
							break;
						case DISTRIBUEING:
							state = "派件中";
							break;
						case FINISHED:
							state = "完成";
							break;
						}

						queryOrders.add(i.ID + " " + state);

					}

				}

				if (queryOrders.isEmpty()) {
					warnning("该天添加的订单数为0");
				}

				setInfos();

			}
		});

		// messageTable.addMouseListener(new MouseAdapter() {
		// public void mouseClicked(MouseEvent e) {
		// System.out.println(e.getX() + " " + e.getY());
		// }
		// });

	}

	private class MessgeTableModel extends AbstractTableModel {

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
			if (index > queryOrders.size() - 1)
				return null;
			String infos = queryOrders.get(index);

			if (infos != null) {
				String[] info = infos.split(" ");
				if (columnIndex == 0)
					return info[0];
				else if (columnIndex == 2)
					return info[1];
				else
					return null;
			} else
				return null;

		}

		public String getColumnName(int c) {
			if (c == 0)
				return "订单号";
			else if (c == 1)
				return "货运状态";
			else
				return "";
		}

	}

	private void warnning(String msg) {
		JOptionPane.showMessageDialog(null, msg, "订单信息错误", JOptionPane.INFORMATION_MESSAGE);
	}

}
