package presentation.expressui;

import java.awt.Color;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import presentation.commonui.LocationHelper;

public class ChargeMessageCollectionPanel extends JPanel {
	private ArrayList<String> chargeInfos;
	private ArrayList<String> infos;
	private int num;

	private JTable messageTable;
	private JLabel totalMessageLabel;
	private JButton collectionButton;

	private LocationHelper helper;

	public ChargeMessageCollectionPanel() {
		// chargeInfo从ExpressMainController.expressVO获得
		infos = new ArrayList<String>();
		infos.add("712");
		infos.add("1234567 89");
		infos.add("1234567 89");
		infos.add("1234567 89");
		infos.add("1234567 89");
		infos.add("1234567 89");
		infos.add("1234567 89");
		infos.add("1234567 89");
		infos.add("1234567 89");

		num = 0;

		MessgeTableModel model = new MessgeTableModel();
		messageTable = new JTable(model);
		totalMessageLabel = new JLabel();
		collectionButton = new JButton("确认");

		messageTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		totalMessageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		messageTable.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(messageTable.getTableHeader());
		add(messageTable);

		add(totalMessageLabel);
		add(collectionButton);

		// helper = new LocationHelper(this);
		// chargeCollections = ExpressMainController.expressVO.chargeCollection;
		setLayout(null);

		setInfos();

		addListener();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 所有组件setBounds

		messageTable.getTableHeader().setBounds((int) (width * 1.123234916559692 / 25),
				(int) (height * 1.415929203539823 / 20), (int) (width * 22.464698331193837 / 25),
				(int) (height * 1.1946902654867257 / 20));
		messageTable.setBounds((int) (width * 1.123234916559692 / 25), (int) (height * 2.566371681415929 / 20),
				(int) (width * 22.464698331193837 / 25), (int) (height * 13.097345132743364 / 20));
		totalMessageLabel.setBounds((int) (width * 1.123234916559692 / 25), (int) (height * 16.991150442477878 / 20),
				(int) (width * 16.944801026957638 / 25), (int) (height * 1.9469026548672566 / 20));
		collectionButton.setBounds((int) (width * 21.630295250320923 / 25), (int) (height * 17.123893805309734 / 20),
				(int) (width * 1.957637997432606 / 25), (int) (height * 1.5486725663716814 / 20));

		setBaseInfo();

	}

	private void addListener() {
		// jbutton
		// ExpressMainController.chargeCollection

	}

	// 设置table的基本内容，图片，什么的
	private void setBaseInfo() {

		// 设置成不可编辑不可改变位置，大小
		// messageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		messageTable.getTableHeader().setReorderingAllowed(false);
		messageTable.getTableHeader().setResizingAllowed(false);

		// 第一列中对齐,第二列右对齐
		TableColumn column1 = messageTable.getColumnModel().getColumn(0);
		DefaultTableCellRenderer render1 = new DefaultTableCellRenderer();
		render1.setHorizontalAlignment(JLabel.CENTER);
		column1.setCellRenderer(render1);

		DefaultTableCellRenderer render2 = new DefaultTableCellRenderer();
		TableColumn column2 = messageTable.getColumnModel().getColumn(1);
		// render1.setHorizontalAlignment(JLabel.RIGHT);
		column2.setCellRenderer(render1);

		// 设置宽度
		column1.setPreferredWidth(messageTable.getWidth() * 9 / 10);
		column2.setPreferredWidth(messageTable.getWidth() / 10);
		column2.setResizable(false);

		messageTable.setRowHeight((messageTable.getHeight()) / 8);

	}

	// 设置载入动态的内容
	private void setInfos() {
		// 信息label
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
		String time = f.format(date);

		// expressID从expressVO处获
		totalMessageLabel.setText("日期 : " + time + "  快递员编号 : " + "kdy-00001" + "  金额总和 : " + infos.get(0));
		totalMessageLabel.setHorizontalAlignment(JLabel.CENTER);

		// table
		chargeInfos = new ArrayList<String>();
		for (int i = 0; i < 8; i++) {
			int next = 8 * num + i;
			if (next + 1 <= infos.size())
				chargeInfos.add(infos.get(next + 1));
			else
				chargeInfos.add(null);
		}

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
			String infos = chargeInfos.get(rowIndex);

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

	// private class MessageLabel extends JLabel {
	// private String data;
	// public MessageLabel(){
	// Date date = new Date();
	// SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
	// data = f.format(date);
	// }
	//
	// public void paintComponent(Graphics g){
	//
	// }
	// }
}
