package presentation.financeui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import businesslogic.financebl.controller.CollectionReceiptBLController;
import businesslogic.financebl.controller.PaymentReceiptBLController;
import presentation.commonui.OperationPanel;
import type.ReceiptState;
import vo.CollectionReceiptVO;
import vo.PaymentReceiptVO;
import vo.UserVO;

public class ReceiptPanel_new extends  OperationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JButton sendButton;
	private JButton printButton;
	private JButton collectionReceiptButton_new;
	private JButton paymentReceiptButton_new;
//	private JButton costIncomeReceiptButton_new;
	
//	private JButton next;
	private JLabel next;
//	private JButton previous;
	private JLabel previous;

	private JLabel function;
	private JLabel collectionReceiptInfo;
	private JLabel paymentReceiptInfo;
//	private JLabel costIncomeReceiptInfo;
	
	private JTable currentTable;
	private JTableHeader currentTableHeader;
	private JTable table1;
	private JTable table2;

	
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	int count1=0;
	int count2=0;
	ListSelectionModel selectionModel;
	private DefaultTableCellRenderer tcr;
	private CollectionModel cm;
	private PaymentModel pm;
	 ArrayList<ArrayList<String>> collection=new ArrayList<ArrayList<String>>();
	 ArrayList<ArrayList<String>> payment=new ArrayList<ArrayList<String>>();
	
	 public CollectionReceiptBLController collectionController;
	 public PaymentReceiptBLController paymentReceiptBLController;
	 public FinanceFrame financeFrame;
	 public UserVO user;

	public ReceiptPanel_new(CollectionReceiptBLController collectionController, PaymentReceiptBLController paymentReceiptBLController
			            ,FinanceFrame parent,UserVO user) {
		this.collectionController=collectionController;
		this.paymentReceiptBLController=paymentReceiptBLController;
		this.financeFrame=parent;
		this.user = user;
		
		sendButton = new JButton("发送");
		printButton = new JButton("导出");
		collectionReceiptButton_new = new JButton("新建入款单");
		paymentReceiptButton_new = new JButton("新建付款单");
		next = new JLabel(">");
		previous = new JLabel("<");
		function = new JLabel("新建表单");
		collectionReceiptInfo = new JLabel("入款单");
		paymentReceiptInfo = new JLabel("付款单");
		
		cm=new CollectionModel(collection);
		table1=new JTable(cm);
		pm=new PaymentModel(payment);
		table2=new JTable(pm);
				
		addListener();

		setLayout(null);

		add(sendButton);
		add(printButton);
	
		add(collectionReceiptButton_new);
		add(paymentReceiptButton_new);
	
		add(next);
		add(previous);
		add(function);
		add(collectionReceiptInfo);
		add(paymentReceiptInfo);
		add(table1.getTableHeader());
		add(table1);
		
		add(table2.getTableHeader());
		add(table2);
		
		currentTable = table1;
		currentTableHeader = table1.getTableHeader();
		
//		helper = new LocationHelper(this);
		setTableColor();
		setCmpLocation(currentTable);
		setBaseInfo(currentTable);
		
		setVisible(true);

	}

	

	public void setCmpLocation(JTable table){
		printButton.setBounds((int)(PANEL_WIDTH * 22.034438775510203/25),(int)(PANEL_HEIGHT * 0.9784735812133072/20),(int)(PANEL_WIDTH *  2.8364795918367347 /25),(int)(PANEL_HEIGHT *  1.4090019569471623/20));
		collectionReceiptButton_new.setBounds((int)(PANEL_WIDTH * 8.258928571428571/25),(int)(PANEL_HEIGHT * 1.0567514677103718/20),(int)(PANEL_WIDTH *  3.1364795918367347 /25),(int)(PANEL_HEIGHT *  1.4090019569471623/20));
		paymentReceiptButton_new.setBounds((int)(PANEL_WIDTH * 12.746173469387756/25),(int)(PANEL_HEIGHT * 1.0176125244618395/20),(int)(PANEL_WIDTH *  3.1364795918367347 /25),(int)(PANEL_HEIGHT *  1.4090019569471623/20));
		next.setBounds((int)(PANEL_WIDTH * 22.5765306122449/25),(int)(PANEL_HEIGHT * 16.96908023483366/20),(int)(PANEL_WIDTH *  1.2436224489795917 /25),(int)(PANEL_HEIGHT *  1.087279843444227/20));
		previous.setBounds((int)(PANEL_WIDTH * 20.982142857142858/25),(int)(PANEL_HEIGHT * 16.969358121330725/20),(int)(PANEL_WIDTH *  1.2436224489795917 /25),(int)(PANEL_HEIGHT *  1.087279843444227/20));
		function.setBounds((int)(PANEL_WIDTH * 1.2755102040816326/25),(int)(PANEL_HEIGHT * 0.7045009784735812/20),(int)(PANEL_WIDTH *  5.133928571428571 /25),(int)(PANEL_HEIGHT *  2.0743639921722115/20));
		collectionReceiptInfo.setBounds((int)(PANEL_WIDTH * 1.371173469387755/25),(int)(PANEL_HEIGHT * 3.031311154598826/22),(int)(PANEL_WIDTH *  3.858418367346939 /25),(int)(PANEL_HEIGHT *  1.2524461839530332/20));
		paymentReceiptInfo.setBounds((int)(PANEL_WIDTH * 5.165816326530612/25),(int)(PANEL_HEIGHT * 3.031311154598826/22),(int)(PANEL_WIDTH *  3.985969387755102 /25),(int)(PANEL_HEIGHT *  1.2524461839530332/20));
//		 table.getTableHeader().setBounds((int)(PANEL_WIDTH * 1.371173469387755/25),(int)(PANEL_HEIGHT * 5.283757338551859/20),(int)(PANEL_WIDTH *  21.651785714285715 /25),(int)(PANEL_HEIGHT *  1.1819960861056751/20));
		 table.setBounds((int)(PANEL_WIDTH * 1.371173469387755/25),(int)(PANEL_HEIGHT * 4.383757338551859/20)+(int)(PANEL_HEIGHT *  1.1819960861056751/20),(int)(PANEL_WIDTH *  23.651785714285715 /25),(int)(PANEL_HEIGHT *  11.819960861056751/22));
		
		currentTableHeader.setColumnModel(table.getColumnModel());
		currentTableHeader.setBounds((int)(PANEL_WIDTH * 1.371173469387755/25),(int)(PANEL_HEIGHT * 4.383757338551859/20),(int)(PANEL_WIDTH *  23.651785714285715 /25),(int)(PANEL_HEIGHT *  1.1819960861056751/20));

		table.setBackground(getBackground());
//		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		currentTableHeader.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		table.setRowSelectionAllowed(true);
        selectionModel = table.getSelectionModel();
//        selectionModel.addListSelectionListener((ListSelectionListener) this);
		
	}
	public void setBounds(int x, int y, int width, int height,JTable table) {

		super.setBounds(x, y, width, height);
		super.setBounds(x, y, PANEL_WIDTH, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation(table);
	}
	
	/**
	 * 表格跳转
	 * */
	public void changeTable(JTable table){
		remove(currentTable);
		currentTable = table;
		add(currentTable);
		setCmpLocation(table);
		setBaseInfo(currentTable);
		repaint();
	}

   
		

	//设置table颜色
	private void setTableColor(){
		tcr = new DefaultTableCellRenderer(){

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
		
		
	}
	
	//设置table的基本属性
		private void setBaseInfo(JTable table) {

			// 设置成不可编辑不可改变位置，大小
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setResizingAllowed(false);

			table.setRowHeight((table.getHeight() - table1.getTableHeader().getHeight()) / 8);
			tcr.setHorizontalAlignment(JLabel.CENTER);
			
			if(table.equals(table1)){
				TableColumn column1 = table1.getColumnModel().getColumn(0);
				TableColumn column2 = table1.getColumnModel().getColumn(1);
				TableColumn column3 = table1.getColumnModel().getColumn(2);
				TableColumn column4 = table1.getColumnModel().getColumn(3);
				TableColumn column5 = table1.getColumnModel().getColumn(4);
				
				column1.setPreferredWidth(table1.getWidth() * 3 / 10);
				column2.setPreferredWidth(table1.getWidth() * 2 / 10);
				column3.setPreferredWidth(table1.getWidth() * 1 / 10);
				column4.setPreferredWidth(table1.getWidth() * 2 / 10);
				column5.setPreferredWidth(table1.getWidth() * 2 / 10);
				
				column1.setCellRenderer(tcr);
				column2.setCellRenderer(tcr);
				column3.setCellRenderer(tcr);
				column4.setCellRenderer(tcr);
				column5.setCellRenderer(tcr);
			}
			else{
			TableColumn column1 = table2.getColumnModel().getColumn(0);
			TableColumn column2 = table2.getColumnModel().getColumn(1);
			TableColumn column3 = table2.getColumnModel().getColumn(2);
			TableColumn column4 = table2.getColumnModel().getColumn(3);
			TableColumn column5 = table2.getColumnModel().getColumn(4);
			TableColumn column6 = table2.getColumnModel().getColumn(5);
			TableColumn column7 = table2.getColumnModel().getColumn(6);
			TableColumn column8 = table2.getColumnModel().getColumn(7);
				
		    column1.setPreferredWidth(table2.getWidth() * 2 / 10);
			column2.setPreferredWidth(table2.getWidth() * 1 / 10);
			column3.setPreferredWidth(table2.getWidth() * 1 / 10);
			column4.setPreferredWidth(table2.getWidth() * 1 / 10);
			column5.setPreferredWidth(table2.getWidth() * 1 / 10);
			column6.setPreferredWidth(table2.getWidth() * 1 / 10);
			column7.setPreferredWidth(table2.getWidth() * 1 / 10);
			column8.setPreferredWidth(table2.getWidth() * 2 / 10);
				
			column1.setCellRenderer(tcr);
			column2.setCellRenderer(tcr);
			column3.setCellRenderer(tcr);
			column4.setCellRenderer(tcr);
			column5.setCellRenderer(tcr);
			column6.setCellRenderer(tcr);
			column7.setCellRenderer(tcr);
			column8.setCellRenderer(tcr);
			}

		}
		
	public void addListener(){
		/**
		 * 显示所有入款单
		 * */
		collectionReceiptInfo.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				changeTable(table1);
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
				changeTable(table2);
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

		//下一页
		 next.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					nextui();
				}
			});

		//上一页
		 previous.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
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
		financeFrame.changePanel(new CollectionReceiptPanel(collectionController, financeFrame,user));
	}

	public void new2ui() {
		financeFrame.changePanel(new PaymentReceiptPanel(paymentReceiptBLController,financeFrame,user));
	}

	/**
	 * 向下翻页
	 * */
	public void nextui() {
		if(currentTable == table1){
			count1++;
			if(getCollectionOnThisPage(count1)!=null){
				int total=collection.size();
				ArrayList<CollectionReceiptVO> temp=getCollectionOnThisPage(count1);
				refreshCollection(temp);
				cm=new CollectionModel(collection);
				for(int i=0;i<total;i++){
					cm.removeRow(0);
				}
				table1.repaint();
			}
			else{
				count1--;
				return;
			}
		}
		else{
			count2++;
			if(getPaymentOnThisPage(count2)!=null){
				int total=payment.size();
				ArrayList<PaymentReceiptVO> temp=getPaymentOnThisPage(count2);
				refreshPayment(temp);
				pm=new PaymentModel(payment);
				for(int i=0;i<total;i++){
					cm.removeRow(0);
				}
				table2.repaint();
			}
			else{
				count2--;
				return;
			}
			
		}
	}

	/**
	 * 向上翻页
	 * */
	public void previousui() {
		if(currentTable == table1){
			count1--;
			if(getCollectionOnThisPage(count1)!=null&&count1>=0){
				int total=collection.size();
				ArrayList<CollectionReceiptVO> temp=getCollectionOnThisPage(count1);
				refreshCollection(temp);
				cm=new CollectionModel(collection);
				for(int i=0;i<total;i++){
					cm.removeRow(0);
				}
				table1.repaint();
			}
			else{
				count1++;
				return;
			}
		}
		else{
			count2--;
			if(getPaymentOnThisPage(count2)!=null&&count2>=0){
				int total=payment.size();
				ArrayList<PaymentReceiptVO> temp=getPaymentOnThisPage(count2);
				refreshPayment(temp);
				pm=new PaymentModel(payment);
				for(int i=0;i<total;i++){
					cm.removeRow(0);
				}
				table2.repaint();
			}
			else{
				count2++;
				return;
			}

	}
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
			return 9;
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return head.length;
		}
		
		public String getValueAt(int row, int col) {
			if(row>collection.size()-1){
				return null;
			}
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
		String head[]={"编号","日期","租金","运费","薪水","金额","提交人","审批状态"};
		
		public PaymentModel(ArrayList<ArrayList<String>> content){
			payment=content;
		}
		//行数
		public int getRowCount() {
			// TODO Auto-generated method stub
			return 9;
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return head.length;
		}
		
		public String getValueAt(int row, int col) {
			if(row>payment.size()-1){
				return null;
			}
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
			lineInfo.add(v.ID);
			lineInfo.add(v.date);
			lineInfo.add(v.totalMoney+"");
			lineInfo.add(v.userID);
			lineInfo.add(EnumChange(v.state));
			
			collection.add(lineInfo);
		}
	}
	
	public void refreshPayment(ArrayList<PaymentReceiptVO> pvos){
		for(PaymentReceiptVO v:pvos){
			ArrayList<String> lineInfo=new ArrayList<String>();
			lineInfo.add(v.ID);
			lineInfo.add(v.date);
			lineInfo.add(v.rent+"");
			lineInfo.add(v.fare+"");
			lineInfo.add(v.salary+"");
			lineInfo.add(v.cost+"");
			lineInfo.add(v.userID);
			lineInfo.add(EnumChange(v.state));
			
			payment.add(lineInfo);
		}
	}
	
	//单据审批状态由枚举类变中文
	public String EnumChange(ReceiptState state){
		if(state.equals(ReceiptState.APPROVE)){
			return "审批通过";
		}
		else if(state.equals(ReceiptState.DISAPPROVE)){
			return "审批未通过";
		}
		else if(state.equals(ReceiptState.SUBMIT)){
			return "提交待审批";
		}
		else{
			return "草稿状态";
		}
	}
	
	  /*
     * 为了翻页而写的若干个方法
     * 统计当前count页上的数据
     * **/
    public ArrayList<CollectionReceiptVO> getCollectionOnThisPage(int num){
   	 ArrayList<CollectionReceiptVO> vos = collectionController.getAllCollection();
   	 ArrayList<CollectionReceiptVO> collectionTemp = new ArrayList<CollectionReceiptVO>();
   	 if(vos.size()<=num*9||num<0){
   		 return null;
   	 }
   	 else{
   	 for(int i=9*num;i<=9*num+8;i++){
   		 if(vos.size()>i){
   			collectionTemp.add(vos.get(i));
   		 }
   	 }
   	 return collectionTemp;
    }
    }
   	 
    public ArrayList<PaymentReceiptVO> getPaymentOnThisPage(int num){
      	 ArrayList<PaymentReceiptVO> vos = paymentReceiptBLController.getAllPaymentReceipt();
      	 ArrayList<PaymentReceiptVO> paymentTemp = new ArrayList<PaymentReceiptVO>();
      	 if(vos.size()<=num*8||num<0){
      		 return null;
      	 }
      	 else{
      	 for(int i=8*num;i<=8*num+7;i++){
      		 if(vos.size()>i){
      			paymentTemp.add(vos.get(i));
      		 }
      	 }
      	 return paymentTemp;
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