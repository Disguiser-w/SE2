package presentation.expressui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import businesslogic.expressbl.controller.ChargeCollectionController;
import businesslogic.expressbl.controller.ExpressMainController;

public class ChargeMessageCollectionPanel extends JPanel {

	private JTable messageTable;
	private JLabel totalMessageLabel;
	private JLabel previousPageLabel;
	private JLabel nextPageLabel;

	// private LocationHelper helper;
	// private ArrayList<String>
	// ExpressMainController.expressVO.chargeCollection;
	private int num;
	private ChargeCollectionController controller;

	public ChargeMessageCollectionPanel(ChargeCollectionController controller) {
		this.controller = controller;

		num = 0;

		MessgeTableModel model = new MessgeTableModel();
		messageTable = new JTable(model);
		totalMessageLabel = new JLabel();
		previousPageLabel = new JLabel();
		nextPageLabel = new JLabel();

		previousPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nextPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		totalMessageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		messageTable.setBackground(getBackground());

		add(messageTable.getTableHeader());
		add(messageTable);
		add(totalMessageLabel);
		add(previousPageLabel);
		add(nextPageLabel);

		// helper = new LocationHelper(this);

		setLayout(null);
		setInfos();
		addListener();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 所有组件setBounds

		messageTable.getTableHeader().setBounds((int) (width * 1.09375 / 25), (int) (height * 1.5217391304347827 / 20),
				(int) (width * 22.71875 / 25), (int) (height * 1.178260869565217 / 20));
		messageTable.setBounds((int) (width * 1.09375 / 25),
				(int) (height * 1.5217391304347827 / 20 + (int) (height * 1.178260869565217 / 20)),
				(int) (width * 22.71875 / 25), (int) (height * 13.378260869565217 / 20));
		totalMessageLabel.setBounds((int) (width * 1.09375 / 25), (int) (height * 16.956521739130434 / 20),
				(int) (width * 16.9375 / 25), (int) (height * 1.9130434782608696 / 20));

		previousPageLabel.setBounds((int) (width * 20.78125 / 25), (int) (height * 17.217391304347824 / 20),
				(int) (width * 1.03125 / 25), (int) (height * 1.434782608695652 / 20));
		nextPageLabel.setBounds((int) (width * 22.78125 / 25), (int) (height * 17.217391304347824 / 20),
				(int) (width * 1.03125 / 25), (int) (height * 1.434782608695652 / 20));

		setBaseInfo();

	}

	private void addListener() {
		previousPageLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (num == 0)
					return;
				else {
					num--;
					messageTable.setModel(new MessgeTableModel());
					setBaseInfo();
				}
			}
		});

		nextPageLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (num >= (ExpressMainController.expressVO.chargeCollection.size() - 2) / 8)
					return;
				else {
					num++;
					messageTable.setModel(new MessgeTableModel());
					setBaseInfo();
				}
			}
		});

	}

	// 设置table的基本内容，图片，什么的
	private void setBaseInfo() {

		// 设置成不可编辑不可改变位置，大小
		messageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		messageTable.getTableHeader().setReorderingAllowed(false);
		messageTable.getTableHeader().setResizingAllowed(false);

		TableColumn column1 = messageTable.getColumnModel().getColumn(0);
		TableColumn column2 = messageTable.getColumnModel().getColumn(1);

		// 设置宽度
		column1.setPreferredWidth(messageTable.getWidth() * 9 / 10);
		column2.setPreferredWidth(messageTable.getWidth() / 10);

		messageTable.setRowHeight((messageTable.getHeight() - messageTable.getTableHeader().getHeight()) / 8);
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

	// 设置载入动态的内容
	private void setInfos() {
		// 信息label
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
		String time = f.format(date);

		// expressID从expressVO处获
		if (ExpressMainController.expressVO.chargeCollection.size() != 0)
			totalMessageLabel.setText("日期 : " + time + "  快递员编号 : " + ExpressMainController.expressVO.ID + "  金额总和 : "
					+ ExpressMainController.expressVO.chargeCollection.get(0));
		else
			totalMessageLabel
					.setText("日期 : " + time + "  快递员编号 : " + ExpressMainController.expressVO.ID + "  金额总和 : " + "0");
		totalMessageLabel.setHorizontalAlignment(JLabel.CENTER);

	}

	private class MessgeTableModel extends AbstractTableModel {

		@Override
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
			int index = num * 8 + rowIndex + 1;

			if (index > ExpressMainController.expressVO.chargeCollection.size() - 1)
				return null;
			String infos = ExpressMainController.expressVO.chargeCollection.get(index);

			if (infos != null) {
				String[] info = infos.split(" ");
				return info[columnIndex];
			} else
				return null;
		}

		public String getColumnName(int c) {
			if (c == 0)
				return "订单号";
			else
				return "收费";
		}

	}

	public void paintComponent(Graphics g) {
		setInfos();
	}
	// }
}
