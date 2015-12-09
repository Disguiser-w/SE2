package presentation.financeui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import businesslogic.financebl.controller.CollectionReceiptBLController;
import businesslogic.financebl.controller.PaymentReceiptBLController;
import vo.CollectionReceiptVO;
import vo.PaymentReceiptVO;

public class ReceiptPanel_new extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JButton sendButton;
	private JButton printButton;
	private JButton collectionReceiptButton_new;
	private JButton paymentReceiptButton_new;
//	private JButton costIncomeReceiptButton_new;
	
	private JButton next;
	private JButton previous;

	private JLabel function;
	private JLabel collectionReceiptInfo;
	private JLabel paymentReceiptInfo;
//	private JLabel costIncomeReceiptInfo;
	
	private JTable table1;
	private JTable table2;

	
	private CollectionModel cm;
	private PaymentModel pm;
	 ArrayList<ArrayList<String>> collection=new ArrayList<ArrayList<String>>();
	 ArrayList<ArrayList<String>> payment=new ArrayList<ArrayList<String>>();
	
	 public CollectionReceiptBLController collectionController;
	 public PaymentReceiptBLController paymentReceiptBLController;
	 public FinanceFrame financeFrame;

	public ReceiptPanel_new(CollectionReceiptBLController collectionController, PaymentReceiptBLController paymentReceiptBLController
			            ,FinanceFrame parent) {
		this.collectionController=collectionController;
		this.paymentReceiptBLController=paymentReceiptBLController;
		this.financeFrame=parent;
		
		sendButton = new JButton("send");
		printButton = new JButton("print");
		collectionReceiptButton_new = new JButton("new1");
		paymentReceiptButton_new = new JButton("new2");
//		costIncomeReceiptButton_new = new JButton("new3");
		next = new JButton("next");
		previous = new JButton("pre");
		function = new JLabel("新建表单");
		collectionReceiptInfo = new JLabel("入款单");
		paymentReceiptInfo = new JLabel("付款单");
//		costIncomeReceiptInfo = new JLabel("成本收益表");

		cm=new CollectionModel(collection);
		table1=new JTable(cm);

//		table1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		table1.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		pm=new PaymentModel(payment);
		table2=new JTable(pm);

//		table2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		table2.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
		setLayout(null);

		add(sendButton);
		add(printButton);
	
		add(collectionReceiptButton_new);
		add(paymentReceiptButton_new);
//		add(costIncomeReceiptButton_new);
	
		add(next);
		add(previous);
		add(function);
		add(collectionReceiptInfo);
		add(paymentReceiptInfo);
//		add(costIncomeReceiptInfo);
		add(table1.getTableHeader());
		add(table1);
		
		add(table2.getTableHeader());
		add(table2);
		
//		helper = new LocationHelper(this);

		addListener();
	}

	

	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);

		printButton.setBounds((int)(width * 22.034438775510203/25),(int)(height * 0.9784735812133072/20),(int)(width *  2.1364795918367347 /25),(int)(height *  1.5655577299412915/20));
		collectionReceiptButton_new.setBounds((int)(width * 8.258928571428571/25),(int)(height * 1.0567514677103718/20),(int)(width *  2.1364795918367347 /25),(int)(height *  1.4090019569471623/20));
		paymentReceiptButton_new.setBounds((int)(width * 10.746173469387756/25),(int)(height * 1.0176125244618395/20),(int)(width *  2.104591836734694 /25),(int)(height *  1.4090019569471623/20));
//		costIncomeReceiptButton_new.setBounds((int)(width * 13.201530612244898/25),(int)(height * 1.0567514677103718/20),(int)(width *  2.2002551020408165 /25),(int)(height *  1.36986301369863/20));
		next.setBounds((int)(width * 22.5765306122449/25),(int)(height * 17.76908023483366/20),(int)(width *  1.2436224489795917 /25),(int)(height *  1.487279843444227/20));
		previous.setBounds((int)(width * 20.982142857142858/25),(int)(height * 17.847358121330725/20),(int)(width *  1.2436224489795917 /25),(int)(height *  1.487279843444227/20));
		function.setBounds((int)(width * 1.2755102040816326/25),(int)(height * 0.7045009784735812/20),(int)(width *  5.133928571428571 /25),(int)(height *  2.0743639921722115/20));
		collectionReceiptInfo.setBounds((int)(width * 1.371173469387755/25),(int)(height * 4.031311154598826/22),(int)(width *  3.858418367346939 /25),(int)(height *  1.2524461839530332/20));
		paymentReceiptInfo.setBounds((int)(width * 5.165816326530612/25),(int)(height * 4.031311154598826/22),(int)(width *  3.985969387755102 /25),(int)(height *  1.2524461839530332/20));
//		costIncomeReceiptInfo.setBounds((int)(width * 9.119897959183673/25),(int)(height * 4.031311154598826/22),(int)(width *  3.443877551020408 /25),(int)(height *  1.2915851272015655/20));
		table1.getTableHeader().setBounds((int)(width * 1.371173469387755/25),(int)(height * 5.283757338551859/20),(int)(width *  21.651785714285715 /25),(int)(height *  1.1819960861056751/20));
		table1.setBounds((int)(width * 1.371173469387755/25),(int)(height * 5.283757338551859/20)+(int)(height *  1.1819960861056751/20),(int)(width *  21.651785714285715 /25),(int)(height *  11.819960861056751/22));
		table2.getTableHeader().setBounds((int)(width * 1.371173469387755/25),(int)(height * 5.283757338551859/20),(int)(width *  21.651785714285715 /25),(int)(height *  1.1819960861056751/20));
		table2.setBounds((int)(width * 1.371173469387755/25),(int)(height * 5.283757338551859/20)+(int)(height *  1.1819960861056751/20),(int)(width *  21.651785714285715 /25),(int)(height *  11.819960861056751/22));

		setBaseInfo();
	}
	
	
	// 设置table的基本内容，图片，什么的
		private void setBaseInfo() {

			// 设置成不可编辑不可改变位置，大小
			table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table1.getTableHeader().setReorderingAllowed(false);
			table1.getTableHeader().setResizingAllowed(false);

			TableColumn column1 = table1.getColumnModel().getColumn(0);
			TableColumn column2 = table1.getColumnModel().getColumn(1);
			TableColumn column3 = table1.getColumnModel().getColumn(2);
			TableColumn column4 = table1.getColumnModel().getColumn(3);
			TableColumn column5 = table1.getColumnModel().getColumn(4);

			TableColumn column11 = table2.getColumnModel().getColumn(0);
			TableColumn column12 = table2.getColumnModel().getColumn(1);
			TableColumn column13 = table2.getColumnModel().getColumn(2);
			TableColumn column14 = table2.getColumnModel().getColumn(3);
			TableColumn column15 = table2.getColumnModel().getColumn(4);
//			TableColumn column16 = table2.getColumnModel().getColumn(5);
//			TableColumn column17 = table2.getColumnModel().getColumn(6);
//			TableColumn column18 = table2.getColumnModel().getColumn(7);


			// 设置宽度
			column1.setPreferredWidth(table1.getWidth() * 3 / 10);
			column2.setPreferredWidth(table1.getWidth() * 2 / 10);
			column3.setPreferredWidth(table1.getWidth() * 1 / 10);
			column4.setPreferredWidth(table1.getWidth() * 2 / 10);
			column5.setPreferredWidth(table1.getWidth() * 2 / 10);

			
			column11.setPreferredWidth(table2.getWidth() * 3 / 10);
			column12.setPreferredWidth(table2.getWidth() * 2 / 10);
			column13.setPreferredWidth(table2.getWidth() * 1 / 10);
			column14.setPreferredWidth(table2.getWidth() * 2 / 10);
			column15.setPreferredWidth(table2.getWidth() * 2 / 10);
//			column16.setPreferredWidth(table2.getWidth() * 1 / 10);
//			column17.setPreferredWidth(table2.getWidth() * 1 / 10);
//			column18.setPreferredWidth(table2.getWidth() * 2 / 10);


			table1.setRowHeight((table1.getHeight() - table1.getTableHeader().getHeight()) / 8);
			table2.setRowHeight((table2.getHeight() - table2.getTableHeader().getHeight()) / 8);

			// tablePanel.setSize(tablePanel.getWidth(), h * 8 +
			// messageTable.getTableHeader().getHeight() + 4);

			//
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

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
			column5.setCellRenderer(tcr);
			
			column11.setCellRenderer(tcr);
			column12.setCellRenderer(tcr);
			column13.setCellRenderer(tcr);
			column14.setCellRenderer(tcr);
			column15.setCellRenderer(tcr);
//			column16.setCellRenderer(tcr);
//			column17.setCellRenderer(tcr);
//			column18.setCellRenderer(tcr);

		}
		
	
	
	public void addListener(){
		/**
		 * 显示所有入款单
		 * */
		collectionReceiptInfo.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int temp=collection.size();
				refreshCollection(collectionController.getAllCollection());
				cm=new CollectionModel(collection);
				for(int i=0;i<temp;i++){
					cm.removeRow(0);
				}
				table1.repaint();
			}
		});
		
		/**
		 * 显示所有付款单
		 * */
		paymentReceiptInfo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				int temp=payment.size();
				refreshPayment(paymentReceiptBLController.getAllPaymentReceipt());
				pm=new PaymentModel(payment);
				for(int i=0;i<temp;i++){
					pm.removeRow(0);
				}
				table2.repaint();
			}
		});
		sendButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				sendui();
			}
		});

		printButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				printui();
			}
		});

		next.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				nextui();
			}
		});

		previous.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				previousui();
			}
		});

		collectionReceiptButton_new.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				new1ui();
			}
		});

		paymentReceiptButton_new.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				new2ui();
			}
		});

		
	}

	public void sendui() {

	}

	public void printui() {

	}

	public void new1ui() {
		financeFrame.changePanel(new CollectionReceiptPanel(collectionController, financeFrame));
	}

	public void new2ui() {
		financeFrame.changePanel(new PaymentReceiptPanel(paymentReceiptBLController,financeFrame));
	}

	public void nextui() {

	}

	public void previousui() {

	}
	

/**
 * 合计收款单的表格
 * */	
	class CollectionModel extends AbstractTableModel{
		private static final long serialVersionUID = 1L;
		//操作人还要吗
		String head[]={"编号","日期","金额","提交人","审批状态"};
		
		public CollectionModel(ArrayList<ArrayList<String>> content){
			collection=content;
		}
		//行数
		public int getRowCount() {
			// TODO Auto-generated method stub
			return collection.size();
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return head.length;
		}
		
		public String getValueAt(int row, int col) {
			return collection.get(row).get(col);
		}
		
		public String getColumnName(int col) {
			return head[col];
		}

		public void addRow(ArrayList<String> v) {
			collection.add(v);
		}

		public void removeRow(int row) {
			collection.remove(row);
		}
		}
	
	/**
	 * 付款单表格
	 * */
	class PaymentModel extends AbstractTableModel{
		private static final long serialVersionUID = 1L;
		//操作人还要吗
		String head[]={"编号","日期","金额","提交人","审批状态"};
		
		public PaymentModel(ArrayList<ArrayList<String>> content){
			payment=content;
		}
		//行数
		public int getRowCount() {
			// TODO Auto-generated method stub
			return payment.size();
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return head.length;
		}
		
		public String getValueAt(int row, int col) {
			return payment.get(row).get(col);
		}
		
		public String getColumnName(int col) {
			return head[col];
		}

		public void addRow(ArrayList<String> v) {
			payment.add(v);
		}

		public void removeRow(int row) {
			payment.remove(row);
		}
		}
	
	
	public void refreshCollection(ArrayList<CollectionReceiptVO> cvos){
		for(CollectionReceiptVO v:cvos){
			ArrayList<String> lineInfo=new ArrayList<String>();
			lineInfo.add(v.getID());
			lineInfo.add(v.getDate());
			lineInfo.add(v.getIncome()+"");
			lineInfo.add(v.getUserID());
			lineInfo.add(v.getState().toString());
			
			collection.add(lineInfo);
		}
	}
	
	public void refreshPayment(ArrayList<PaymentReceiptVO> pvos){
		for(PaymentReceiptVO v:pvos){
			ArrayList<String> lineInfo=new ArrayList<String>();
			lineInfo.add(v.getID());
			lineInfo.add(v.getDate());
//			lineInfo.add(v.getRent()+"");
//			lineInfo.add(v.getFare()+"");
//			lineInfo.add(v.getSalary()+"");
			lineInfo.add(v.getRent()+v.getCost()+v.getSalary()+"");
			lineInfo.add(v.getUserID());
			lineInfo.add(v.getState().toString());
			
			payment.add(lineInfo);
		}
	}
	
		
	

/*	public static void main(String[] args) throws Exception {
		 CollectionReceiptBLController collectionController = new CollectionReceiptBLController() ;
		 PaymentReceiptBLController paymentReceiptBLController = new PaymentReceiptBLController() ;
		 FinanceFrame financeFrame = new FinanceFrame();
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new ReceiptPanel_new(collectionController,paymentReceiptBLController,financeFrame));
		frame.setVisible(true);
	}
	*/
	
}


/*public void setCmpLocation() {
function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
		PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
next.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
		PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
previous.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 45 / 48,
		PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
collectionReceiptInfo.setBounds(PANEL_WIDTH / 6, PANEL_HEIGHT * 3 / 16,
		PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
paymentReceiptInfo.setBounds(PANEL_WIDTH * 5 / 18,
		PANEL_HEIGHT * 3 / 16, PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
costIncomeReceiptInfo.setBounds(PANEL_WIDTH * 7 / 18,
		PANEL_HEIGHT * 3 / 16, PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
printButton.setBounds(PANEL_WIDTH * 7 / 9, PANEL_HEIGHT / 24,
		PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
sendButton.setBounds(PANEL_WIDTH * 8 / 9, PANEL_HEIGHT / 24,
		PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
costIncomeReceiptButton_new.setBounds(PANEL_WIDTH * 5 / 9,
		PANEL_HEIGHT / 24, PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
paymentReceiptButton_new.setBounds(PANEL_WIDTH * 4 / 9,
		PANEL_HEIGHT / 24, PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
collectionReceiptButton_new.setBounds(PANEL_WIDTH * 3 / 9,
		PANEL_HEIGHT / 24, PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
//info.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 4 / 15,
//		PANEL_WIDTH * 5 / 6, PANEL_HEIGHT * 13 / 20);
}
*/