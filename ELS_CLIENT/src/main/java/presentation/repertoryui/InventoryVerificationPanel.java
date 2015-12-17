package presentation.repertoryui;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import businesslogic.repertorybl.RepertoryBL;
import vo.InventoryVO;
import vo.UserVO;
//import presentation.commonui.LocationHelper;

public class InventoryVerificationPanel extends JPanel {
	
	private static final long serialVersionUID = 38L;
	
	private RepertoryBL repertoryBL;
	
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	
	private JLabel endTimeLabel;
	private JLabel timeLabel;

	private InventoryModel inventoryModel;
	private JTable messageTable;
	private DefaultTableCellRenderer tcr;

	private JLabel previousPageLabel;
	private JLabel nextPageLabel;
	
	ArrayList<InventoryVO> inventoryList;
	
	private int num;
	
//	private LocationHelper helper;

	public InventoryVerificationPanel(UserVO uservo){
		
		repertoryBL = new RepertoryBL(uservo.userID);
		
		String time = RepertoryBL.getTimeNow();
		endTimeLabel = new JLabel("截止到");
		timeLabel = new JLabel(time);

		inventoryModel = new InventoryModel();
		messageTable = new JTable(inventoryModel);
		
		previousPageLabel = new JLabel();
		nextPageLabel = new JLabel();

		add(endTimeLabel);
		add(timeLabel);
		add(messageTable.getTableHeader());
		add(messageTable);
		add(previousPageLabel);
		add(nextPageLabel);

		setLayout(null);

//		helper = new LocationHelper(this);
		
		setBorder();
		setTableColor();
		setCmpLocation();
		setBaseInfos();
		
	}

	//给表加颜色，隔一行一个颜色
	public void setTableColor(){
			
		tcr = new DefaultTableCellRenderer(){
		
			private static final long serialVersionUID = 6L;

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (row % 2 == 0)
					setBackground(Color.cyan);
				else
					setBackground(Color.white);
	
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		};
	}
		
	//设置边界
	public void setBorder(){
		previousPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nextPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	//设置位置
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, PANEL_WIDTH, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation();
	}
		
	public void setCmpLocation() {
		endTimeLabel.setBounds((int) (PANEL_WIDTH * 8.162612035851472 / 25), (int) (PANEL_HEIGHT * 1.6071428571428572 / 20),
				(int) (PANEL_WIDTH * 2.848911651728553 / 25), (int) (PANEL_HEIGHT * 1.5178571428571428 / 20));
		timeLabel.setBounds((int) (PANEL_WIDTH * 11.503841229193341 / 25), (int) (PANEL_HEIGHT * 1.6071428571428572 / 20),
				(int) (PANEL_WIDTH * 6.353393085787452 / 25), (int) (PANEL_HEIGHT * 1.5178571428571428 / 20));
		messageTable.getTableHeader().setBounds((int) (PANEL_WIDTH * 1.2163892445582587 / 25), (int) (PANEL_HEIGHT * 3.285714285714286 / 20),
				(int) (PANEL_WIDTH * 20.823303457106274 / 25), (int) (PANEL_HEIGHT * 1 / 20));
		messageTable.setBounds((int) (PANEL_WIDTH * 1.2163892445582587 / 25), (int) (PANEL_HEIGHT * 4.285714285714286 / 20),
				(int) (PANEL_WIDTH * 20.823303457106274 / 25), (int) (PANEL_HEIGHT * 11.125 / 20));
		previousPageLabel.setBounds((int) (PANEL_WIDTH * 21.15877080665813 / 25), (int) (PANEL_HEIGHT * 17.901785714285715 / 20),
				(int) (PANEL_WIDTH * 0.8642765685019206 / 25), (int) (PANEL_HEIGHT * 1.2053571428571428 / 20));
		nextPageLabel.setBounds((int) (PANEL_WIDTH * 22.98335467349552 / 25), (int) (PANEL_HEIGHT * 17.901785714285715 / 20),
				(int) (PANEL_WIDTH * 0.8642765685019206 / 25), (int) (PANEL_HEIGHT * 1.2053571428571428 / 20));
	}
	
	public String blockName(int blockNum){
		String[] blockNameList = {"飞机区","火车区","汽车区","机动区"};
		return blockNameList[blockNum];
	}
	
	
	private void setBaseInfos() {

		// 设置成不可编辑不可改变位置，大小
		// messageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		messageTable.getTableHeader().setReorderingAllowed(false);
		messageTable.getTableHeader().setResizingAllowed(false);

		TableColumn column0 = messageTable.getColumnModel().getColumn(0);
		TableColumn column1 = messageTable.getColumnModel().getColumn(1);
		TableColumn column2 = messageTable.getColumnModel().getColumn(2);
		TableColumn column3 = messageTable.getColumnModel().getColumn(3);
		TableColumn column4 = messageTable.getColumnModel().getColumn(4);
		TableColumn column5 = messageTable.getColumnModel().getColumn(5);
		TableColumn column6 = messageTable.getColumnModel().getColumn(6);

		// 设置宽度
		int tWidth = messageTable.getWidth();
		column0.setPreferredWidth(tWidth / 7);
		column1.setPreferredWidth(tWidth / 7);
		column2.setPreferredWidth(tWidth / 7);
		column3.setPreferredWidth(tWidth / 7);
		column4.setPreferredWidth(tWidth / 7);
		column5.setPreferredWidth(tWidth / 7);
		column6.setPreferredWidth(tWidth / 7);

		messageTable.setRowHeight(messageTable.getHeight() / 8);

		tcr.setHorizontalAlignment(JLabel.CENTER);
		
		column0.setCellRenderer(tcr);
		column1.setCellRenderer(tcr);
		column2.setCellRenderer(tcr);
		column3.setCellRenderer(tcr);
		column4.setCellRenderer(tcr);
		column5.setCellRenderer(tcr);
		column6.setCellRenderer(tcr);
		
	}
	
	public class InventoryModel extends AbstractTableModel{

		private static final long serialVersionUID = 37L;

		public int getRowCount() {
			return 8;
		}

		public int getColumnCount() {
			return 7;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			int index = num * 8 + rowIndex;
			
			inventoryList = repertoryBL.inventoryStockTaking();

			if(index > inventoryList.size() - 1)
				return null;
			InventoryVO inventoryvo = inventoryList.get(index);

			switch(columnIndex){
			case 0:
				return blockName(inventoryvo.blockNum);
			case 1:
				return inventoryvo.good.Order_ID;
			case 2:
				return inventoryvo.good.enterDate[0];
			case 3:
				return inventoryvo.good.destination;
			case 4:
				return inventoryvo.rowNum;
			case 5:
				return inventoryvo.shelfNum;
			case 6:
				return inventoryvo.rowNum;
			default:
				return null;
			}
		}
		
		public String getColumnName(int c) {
			switch (c) {
			case 0:
				return "区名";
			case 1:
				return "订单号";
			case 2:
				return "入库日期";
			case 3:
				return "目的地";
			case 4:
				return "排号";
			case 5:
				return "架号";
			case 6:
				return "位号";
			default:
				return null;
			}
		}
		
	}
	
	
}



