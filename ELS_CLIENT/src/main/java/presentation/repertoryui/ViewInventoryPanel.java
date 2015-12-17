package presentation.repertoryui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import presentation.commonui.DateChooser;
import vo.InventoryCheckVO;
import vo.UserVO;
import businesslogic.repertorybl.RepertoryBL;

public class ViewInventoryPanel extends JPanel {
	
	private static final long serialVersionUID = 99L;
	
	private RepertoryBL repertoryBL;
	
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	
	private JLabel dateRange;
	
	private JLabel startDateLabel;
	private JTextField startDateField;
	private JLabel startDateChooseLabel;

	private JLabel endDateLabel;
	private JTextField endDateField;
	private JLabel endDateChooseLabel;
	
	private JButton confirmDateButton;
	
	private JTable messageTable;

	private InventoryCheckVO inventoryCheckVO;
	
	private DefaultTableCellRenderer tcr;

// private LocationHelper helper;

	public ViewInventoryPanel(UserVO userVO) {
		
		repertoryBL = new RepertoryBL(userVO.userID);
		
		dateRange = new JLabel("日期范围");
		
		startDateLabel = new JLabel();
		startDateField = new JTextField("", 11);
		startDateField.setToolTipText("例:2015-10-01");
		startDateChooseLabel = new JLabel("开始日期");

		endDateLabel = new JLabel();
		endDateField = new JTextField("", 11);
		endDateField.setToolTipText("例:2015-10-01");
		endDateChooseLabel = new JLabel("结束日期");

		confirmDateButton = new JButton("确认");
		
		MessageTableModel model = new MessageTableModel();
		messageTable = new JTable(model);
		
		
		confirmDateButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				checkUI();
			}
		});
		
		
		setLayout(null);
		
		add(dateRange);
		add(startDateLabel);
		add(startDateField);
		add(startDateChooseLabel);
		add(endDateLabel);
		add(endDateField);
		add(endDateChooseLabel);
		add(confirmDateButton);
		add(messageTable.getTableHeader());
		add(messageTable);
		
		// helper = new LocationHelper(this);
		
		setTableColor();
		setBorder();
        setCmpLocation();
        setBaseInfo();
	}

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
		messageTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		messageTable.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		startDateLabel.setLayout(new BorderLayout());
		startDateLabel.add(new DateChooser(startDateField),BorderLayout.CENTER);
		endDateLabel.setLayout(new BorderLayout());
		endDateLabel.add(new DateChooser(endDateField),BorderLayout.CENTER);
	}
	
	public void setCmpLocation() {

		dateRange.setBounds((int)(PANEL_WIDTH * 1.4987244897959184/25),(int)(PANEL_HEIGHT * 2.544031311154599/20),
				(int)(PANEL_WIDTH *  3.1568877551020407 /25),(int)(PANEL_HEIGHT *  1.2524461839530332/20));
		
		startDateLabel.setBounds((int)(PANEL_WIDTH * 8.896683673469388/25),(int)(PANEL_HEIGHT * 2.5048923679060664/20),
				(int)(PANEL_WIDTH *  0.9247448979591837 /25),(int)(PANEL_HEIGHT *  1.1741682974559686/20));
        endDateLabel.setBounds((int)(PANEL_WIDTH * 14.572704081632653/25),(int)(PANEL_HEIGHT * 2.5048923679060664/20),
        		(int)(PANEL_WIDTH *  1.052295918367347 /25),(int)(PANEL_HEIGHT *  1.2524461839530332/20));
        startDateField.setBounds((int)(PANEL_WIDTH * 5.0063775510204085/25),(int)(PANEL_HEIGHT * 2.544031311154599/20),
				(int)(PANEL_WIDTH *  3.1568877551020407 /25),(int)(PANEL_HEIGHT *  1.1741682974559686/20));
		endDateField.setBounds((int)(PANEL_WIDTH * 10.809948979591837/25),(int)(PANEL_HEIGHT * 2.5048923679060664/20),
				(int)(PANEL_WIDTH *  3.2206632653061225 /25),(int)(PANEL_HEIGHT *  1.2915851272015655/20));
		
		confirmDateButton.setBounds((int)(PANEL_WIDTH * 19.6109693877551/25),(int)(PANEL_HEIGHT * 2.5048923679060664/20),
				(int)(PANEL_WIDTH *  2.1683673469387754 /25),(int)(PANEL_HEIGHT *  1.1350293542074363/20));
		
		messageTable.getTableHeader().setBounds((int)(PANEL_WIDTH * 1.4987244897959184/25),(int)(PANEL_HEIGHT * 4.148727984344423/20),
				(int)(PANEL_WIDTH *  21.0140306122449 /25),(int)(PANEL_HEIGHT *  1.3424657534246576/20));
		messageTable.setBounds((int)(PANEL_WIDTH * 1.4987244897959184/25),(int)(PANEL_HEIGHT * 4.148727984344423/20)+(int)(PANEL_HEIGHT *  1.3424657534246576/20),
				(int)(PANEL_WIDTH *  21.0140306122449 /25),(int)(PANEL_HEIGHT *  13.424657534246576/20)-(int)(PANEL_HEIGHT *  1.3424657534246576/20));
	}
	
	private void setBaseInfo(){

		// 设置成不可编辑不可改变位置，大小
		messageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		messageTable.getTableHeader().setReorderingAllowed(false);
		messageTable.getTableHeader().setResizingAllowed(false);

		TableColumn column0 = messageTable.getColumnModel().getColumn(0);
		TableColumn column1 = messageTable.getColumnModel().getColumn(1);
		TableColumn column2 = messageTable.getColumnModel().getColumn(2);
		TableColumn column3 = messageTable.getColumnModel().getColumn(3);
		TableColumn column4 = messageTable.getColumnModel().getColumn(4);
		TableColumn column5 = messageTable.getColumnModel().getColumn(5);
		TableColumn column6 = messageTable.getColumnModel().getColumn(6);
		TableColumn column7 = messageTable.getColumnModel().getColumn(7);
		
		// 设置宽度
		column0.setPreferredWidth(messageTable.getWidth() / 8);
		column1.setPreferredWidth(messageTable.getWidth() / 8);
		column2.setPreferredWidth(messageTable.getWidth() / 8);
		column3.setPreferredWidth(messageTable.getWidth() / 8);
		column4.setPreferredWidth(messageTable.getWidth() / 8);
		column5.setPreferredWidth(messageTable.getWidth() / 8);
		column6.setPreferredWidth(messageTable.getWidth() / 8);
		column7.setPreferredWidth(messageTable.getWidth() / 8);
		

		messageTable.setRowHeight((messageTable.getHeight() - messageTable.getTableHeader().getHeight()) / 10);

		tcr.setHorizontalAlignment(JLabel.CENTER);
		
		column0.setCellRenderer(tcr);
		column1.setCellRenderer(tcr);
		column2.setCellRenderer(tcr);
		column3.setCellRenderer(tcr);
		column4.setCellRenderer(tcr);
		column5.setCellRenderer(tcr);
		column6.setCellRenderer(tcr);
		column7.setCellRenderer(tcr);
	}
	
	public void checkUI(){
		
		String startDateStr = startDateField.getText();
		String endDateStr = endDateField.getText();
		
		inventoryCheckVO = repertoryBL.inventoryCheck(startDateStr, endDateStr);

		if(startDateStr.equals("") || endDateStr.equals("")){
			JOptionPane.showMessageDialog(null, "请输入完整信息！", "提示", JOptionPane.CLOSED_OPTION);
		}
		else{
			inventoryCheckVO = repertoryBL.inventoryCheck(startDateStr, endDateStr);
			repaint();
		}
	}
	
	private class MessageTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 4945586293640191297L;

		public int getRowCount() {
			return 1;
		}

		public int getColumnCount() {
			return 8;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			
			String startDateStr = startDateField.getText();
			String endDateStr = endDateField.getText();
			
			inventoryCheckVO = repertoryBL.inventoryCheck(startDateStr, endDateStr);

			switch (columnIndex) {
			case 0:
				return inventoryCheckVO.enterTotal;
			case 1:
				return inventoryCheckVO.enterFeeTotal;
			case 2:
				return inventoryCheckVO.leaveTotal;
			case 3:
				return inventoryCheckVO.leaveFeeTotal;
			case 4:
				return inventoryCheckVO.stockNum[0];
			case 5:
				return inventoryCheckVO.stockNum[1];
			case 6:
				return inventoryCheckVO.stockNum[2];
			default:
				return inventoryCheckVO.stockNum[3];
			}
		}

		public String getColumnName(int c) {
			switch (c) {
			case 0:
				return "入库数量总计";
			case 1:
				return "入库金额总计";
			case 2:
				return "出库数量总计";
			case 3:
				return "出库金额总计";
			case 4:
				return "飞机区数量";
			case 5:
				return "火车区数量";
			case 6:
				return "汽车区数量";
			default:
				return "机动区数量";
			}
		}

	}
	
}
