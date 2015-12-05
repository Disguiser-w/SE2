package presentation.financeui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import type.ReceiptState;
import vo.GatheringReceiptVO;
import businesslogicservice.financeblservice.CollectionReceiptBLService;
/**
 * 暂时先把根据营业厅筛选的去掉了，以后有时间再说吧
 * */
public class CollectionReceiptPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private JButton dateChooseButton;
	private JButton infoOKButton;
	private JButton collectionOKButton;
	private JButton next;
	private JButton previous;
	private JButton totalButton;

	private JLabel function;
	private JLabel date;
	private JLabel businessHall;
	private JLabel infoLine;

	private JTextField date_Input;
	private JTextField businessHall_ID_Input;
	private JTable table;
	
//	private LocationHelper helper;



	String hallID_str;
	String date_str;
	CollectionReceiptBLService service;
	CollectionModel cm;
	 ArrayList<ArrayList<String>> c=new ArrayList<ArrayList<String>>();
	public CollectionReceiptPanel() {
		dateChooseButton = new JButton("date");
		infoOKButton = new JButton("infook");
		collectionOKButton = new JButton("ok");
		next = new JButton("next");
		previous = new JButton("previous");
		totalButton = new JButton("total");

		function = new JLabel("新建入款单");
		date = new JLabel("日期");
		businessHall = new JLabel("营业厅");
		infoLine = new JLabel("时间：2015/11/1  合计金额：970");

		date_Input = new JTextField("");
		businessHall_ID_Input = new JTextField("");

		
//		if(c!=null){
//			System.out.println(c);
//		}
//		else{
//			System.out.println("空的hhhhh");
//		}
		//新建table
		

		
//		System.out.println("has got here");
//		setCmpLocation();
	
		/**
		 * 监听
		 * */
                  /**
                    * 选择日期
                   * */
		dateChooseButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				dateChooseui();
			}
		});

		/**
		 * 确认日期输入
		 * */
		infoOKButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				infookui();
			}
		});

		collectionOKButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				okui();
			}
		});

		totalButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				totalui();
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

		setLayout(null);

		add(dateChooseButton);
		add(infoOKButton);
		add(collectionOKButton);
		add(next);
		add(previous);
		add(totalButton);
	
		
		add(function);
		add(date);
		add(businessHall);
		add(infoLine);

		add(date_Input);
		add(businessHall_ID_Input);
//		add(table);
//		add(info);
//		helper = new LocationHelper(this);
		//table
		cm=new CollectionModel(c);
		System.out.println(c);
		table=new JTable(cm);
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(table.getTableHeader());
		add(table);
//		setInfos();
		
	}

/*	public void setCmpLocation() {
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 5 / 18, PANEL_HEIGHT / 12);
		date.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 18, PANEL_HEIGHT / 24);
		date_Input.setBounds(PANEL_WIDTH * 7 / 36, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 12, PANEL_HEIGHT / 24);
		businessHall.setBounds(PANEL_WIDTH * 13 / 36, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 18, PANEL_HEIGHT / 24);
		businessHall_ID_Input.setBounds(PANEL_WIDTH * 4 / 9,
				PANEL_HEIGHT * 3 / 16, PANEL_WIDTH / 12, PANEL_HEIGHT / 24);
		collectionOKButton.setBounds(PANEL_WIDTH * 11 / 18, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		infoOKButton.setBounds(PANEL_WIDTH * 11 / 36, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		next.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		previous.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		totalButton.setBounds(PANEL_WIDTH / 18, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		infoLine.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH * 5 / 12, PANEL_HEIGHT / 24);

//		info.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 4 / 15,
//				PANEL_WIDTH * 5 / 6, PANEL_HEIGHT * 13 / 20);
	}
	*/

	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 所有组件setBounds

//		serialVersionUID.setBounds((int)(width * 2.5510204081632653/25),(int)(height * 5.205479452054795/20),(int)(width *  17.92091836734694 /25),(int)(height *  1.0176125244618395/20));
		dateChooseButton.setBounds((int)(width * 7.174744897959184/25),(int)(height * 3.5616438356164384/20),(int)(width *  1.0204081632653061 /25),(int)(height *  0.9001956947162426/20));
		infoOKButton.setBounds((int)(width * 14.09438775510204/25),(int)(height * 3.5616438356164384/20),(int)(width *  0.9885204081632653 /25),(int)(height *  0.9784735812133072/20));
		collectionOKButton.setBounds((int)(width * 20.535714285714285/25),(int)(height * 3.522504892367906/20),(int)(width *  1.6262755102040816 /25),(int)(height *  0.9393346379647749/20));
		next.setBounds((int)(width * 21.1734693877551/25),(int)(height * 18.747553816046967/20),(int)(width *  1.0204081632653061 /25),(int)(height *  0.821917808219178/20));
		previous.setBounds((int)(width * 22.544642857142858/25),(int)(height * 18.747553816046967/20),(int)(width *  1.0204081632653061 /25),(int)(height *  0.821917808219178/20));
		totalButton.setBounds((int)(width * 18.239795918367346/25),(int)(height * 18.434442270058707/20),(int)(width *  2.072704081632653 /25),(int)(height *  0.9784735812133072/20));
		function.setBounds((int)(width * 0.6696428571428571/25),(int)(height * 0.821917808219178/20),(int)(width *  6.919642857142857 /25),(int)(height *  1.643835616438356/20));
		date.setBounds((int)(width * 2.391581632653061/25),(int)(height * 3.5616438356164384/20),(int)(width *  1.594387755102041 /25),(int)(height *  0.9001956947162426/20));
		businessHall.setBounds((int)(width * 9.056122448979592/25),(int)(height * 3.6007827788649704/20),(int)(width *  1.6581632653061225 /25),(int)(height *  0.8610567514677103/20));
		infoLine.setBounds((int)(width * 2.5191326530612246/25),(int)(height * 18.12133072407045/20),(int)(width *  13.424744897959183 /25),(int)(height *  1.2915851272015655/20));
		date_Input.setBounds((int)(width * 4.177295918367347/25),(int)(height * 3.679060665362035/20),(int)(width *  2.774234693877551 /25),(int)(height *  0.821917808219178/20));
		businessHall_ID_Input.setBounds((int)(width * 11.033163265306122/25),(int)(height * 3.5616438356164384/20),(int)(width *  2.5510204081632653 /25),(int)(height *  0.9001956947162426/20));
		table.setBounds((int)(width * 2.5510204081632653/25),(int)(height * 6.14481409001957/20),(int)(width *  17.92091836734694 /25),(int)(height *  10.95890410958904/20));



		setBaseInfo();

	}
	
	    /**
	     * 设置表格的基本内容
	     * */
		private void setBaseInfo() {

			// 设置成不可编辑不可改变位置，大小
			// messageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setResizingAllowed(false);

			// 第一列中对齐,第二列右对齐
			TableColumn column1 = table.getColumnModel().getColumn(0);
			DefaultTableCellRenderer render1 = new DefaultTableCellRenderer();
			render1.setHorizontalAlignment(JLabel.CENTER);
			column1.setCellRenderer(render1);

			TableColumn column2 = table.getColumnModel().getColumn(1);
			// render1.setHorizontalAlignment(JLabel.RIGHT);
			column2.setCellRenderer(render1);

			// 设置宽度
			column1.setPreferredWidth(table.getWidth() * 9 / 10);
			column2.setPreferredWidth(table.getWidth() / 10);
			column2.setResizable(false);

			table.setRowHeight((table.getHeight()) / 8);

		}
		
		/**
		 * 设置表格中载入的动态内容(动态内容还是现实不进去。。。)
		 * */
		
//		public void setInfos(){
//			c=new ArrayList<ArrayList<String>>();
//			
//			
//			
//			
//		}


		
		/**
		 * 监听方法
		 * */
	public void dateChooseui() {

	}

	/**
	 * 输入（选择）日期的方法,这个怎么写
	 * */
	public void infookui() {
//		date_str=date_Input.getText();
//		if(date_str.equals("")){
//			JOptionPane.showMessageDialog(null, "请输入完整信息！", "提示",
//					JOptionPane.CLOSED_OPTION);
//		}

	}

	/**
	 * 确定输入日期的方法
	 * */
	public void okui() {
		//这里需要格式的转化"2015/11/10——20151110"
		date_str=date_Input.getText();
		

		if(date_str.equals("")){
			JOptionPane.showMessageDialog(null, "请输入完整信息！", "提示",
					JOptionPane.CLOSED_OPTION);
		}
		else if(date_str.length()!=8){
			JOptionPane.showMessageDialog(null, "请输入正确的日期！", "提示",
					JOptionPane.CLOSED_OPTION);
			date_Input.setText("");
		}
		else if(date_str.substring(0,4).compareTo("2015")>0||date_str.substring(4,6).compareTo("12")>0||date_str.substring(6,8).compareTo("31")>0){
			JOptionPane.showMessageDialog(null, "请输入正确的日期！", "提示",
					JOptionPane.CLOSED_OPTION);
			date_Input.setText("");
		}
		else{
			CollectionReceiptPanel crpanel=new CollectionReceiptPanel();
	        crpanel.refreshGatheringTable(date_str);
		}

	}

	public void totalui() {

	}

	public void nextui() {

	}

	public void previousui() {

	}
	
	class CollectionModel extends AbstractTableModel{

		
		private static final long serialVersionUID = 1L;
		ArrayList<ArrayList<String>> c = new ArrayList<ArrayList<String>>();
		//操作人还要吗
		String head[]={"收款单编号","日期","营业厅编号","金额"};
		
		public CollectionModel(ArrayList<ArrayList<String>> content){
			c=content;
		}
		//行数
		public int getRowCount() {
			// TODO Auto-generated method stub
			return c.size();
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
//			System.out.println(head.length);
			return head.length;
		}
		
		public String getValueAt(int row, int col) {
			return c.get(row).get(col);
		}

		public String getColumnName(int col) {
			return head[col];
		}

		public void addRow(ArrayList<String> v) {

			c.add(v);
		}

		public void removeRow(int row) {
			c.remove(row);
		}
		
	}
	
	public ArrayList<ArrayList<String>> getInfo(){
		return c;
	}
	
	public void refreshGatheringTable(String time){
//		ArrayList<GatheringReceiptVO> gatherings=service.getGathering(time);
		GatheringReceiptVO vo1=new GatheringReceiptVO(null, "20151203", null, null, 299, "SKD-20151203", ReceiptState.SUBMIT);
		GatheringReceiptVO vo2=new GatheringReceiptVO(null, "20151201", null, null, 200, "SKD-20151201", ReceiptState.APPROVE);
		ArrayList<GatheringReceiptVO> gatherings=new ArrayList<GatheringReceiptVO>();
		gatherings.add(vo1);
		gatherings.add(vo2);
		for(GatheringReceiptVO gvo:gatherings){
			ArrayList<String> lineInfo=new ArrayList<String>();
			lineInfo.add(gvo.receiptID);
			lineInfo.add(gvo.time);
//			lineInfo.add(gvo.businesshall.organizationID);
			lineInfo.add(gvo.totalmoney+"");
			c.add(lineInfo);
			System.out.println(c.get(0).toString());
//			System.out.println(c.get(1).toString());
		}
		
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new CollectionReceiptPanel());
		frame.setVisible(true);
	}
}



//package presentation.financeui;
//
//import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.table.AbstractTableModel;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.TableColumn;
//
//import type.ReceiptState;
//import vo.GatheringReceiptVO;
//import businesslogicservice.financeblservice.CollectionReceiptBLService;
///**
// * 暂时先把根据营业厅筛选的去掉了，以后有时间再说吧
// * */
//public class CollectionReceiptPanel extends JPanel {
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	String hallID_str;
//	String date_str;
//	CollectionReceiptBLService service;
//	CollectionModel cm;
//	ArrayList<ArrayList<String>> c1=new ArrayList<ArrayList<String>>();
//	ArrayList<ArrayList<String>> c2=new ArrayList<ArrayList<String>>();
//
// 	private int PANEL_WIDTH = 720;
//	private int PANEL_HEIGHT = 480;
//	
//	//声明表格
//	private JTable messageTable;
//	private JLabel totalMessageLabel;
//	
//	
//	JScrollPane jsp1;
//	private JButton dateChooseButton;
//	private JButton infoOKButton;
//	private JButton collectionOKButton;
//	private JButton next;
//	private JButton previous;
//	private JButton totalButton;
//
//	private JLabel function;
//	private JLabel date;
////	private JLabel businessHall;
//	private JLabel infoLine;
//
//	private JTextField date_Input;
//	
//
////	private JTextField businessHall_ID_Input;
//
//	private CollectionReceiptInfoTable info;
//
//	public CollectionReceiptPanel() {
//		dateChooseButton = new JButton("date");
//		infoOKButton = new JButton("infook");
//		collectionOKButton = new JButton("ok");
//		next = new JButton("next");
//		previous = new JButton("previous");
//		totalButton = new JButton("total");
//
//		function = new JLabel("新建入款单");
//		date = new JLabel("日期");
////		businessHall = new JLabel("营业厅");
//		infoLine = new JLabel("时间：2015/11/1  合计金额：970");
//
//		date_Input = new JTextField("");
////		businessHall_ID_Input = new JTextField("");
//		
//		
//
//		//声明一个新的表格样式
//		cm=new CollectionModel(c1);
//		messageTable=new JTable(cm);
//		totalMessageLabel=new JLabel();
//		
//		messageTable.setBorder(BorderFactory.createLineBorder(Color.black));
//		totalMessageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		messageTable.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		
//		add(messageTable.getTableHeader());
//		add(messageTable);
//		add(totalMessageLabel);
//		
//		info = new CollectionReceiptInfoTable(13, 4);
///**
// * 监听——选择日期
// * */
//		dateChooseButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO 自动生成的方法存根
//				dateChooseui();
//			}
//		});
//
//		/**
//		 * 确认日期输入
//		 * */
//		infoOKButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO 自动生成的方法存根
//				infookui();
//			}
//		});
//
//		collectionOKButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO 自动生成的方法存根
//				okui();
//			}
//		});
//
//		totalButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO 自动生成的方法存根
//				totalui();
//			}
//		});
//
//		next.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO 自动生成的方法存根
//				nextui();
//			}
//		});
//
//		previous.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO 自动生成的方法存根
//				previousui();
//			}
//		});
//
//		setLayout(null);
//
//		add(dateChooseButton);
//		add(infoOKButton);
//		add(collectionOKButton);
//		add(totalButton);
//		add(next);
//		add(previous);
//		add(function);
//		add(date);
//		add(infoLine);
////		add(businessHall);
//		add(date_Input);
////		add(businessHall_ID_Input);
//		
//		/**
//		 * 这里是把表格添加进panel的方法，，，但是本宝宝并不知道怎么把信息填进去
//		 * */
//		add(info);
//	}
//	
//	
//
//	
//
//	/*public void setCmpLocation() {
//		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
//				PANEL_WIDTH * 5 / 18, PANEL_HEIGHT / 12);
//		date.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 3 / 16,
//				PANEL_WIDTH / 18, PANEL_HEIGHT / 24);
//		date_Input.setBounds(PANEL_WIDTH * 7 / 36, PANEL_HEIGHT * 3 / 16,
//				PANEL_WIDTH / 12, PANEL_HEIGHT / 24);
////		businessHall.setBounds(PANEL_WIDTH * 13 / 36, PANEL_HEIGHT * 3 / 16,
////				PANEL_WIDTH / 18, PANEL_HEIGHT / 24);
////		businessHall_ID_Input.setBounds(PANEL_WIDTH * 4 / 9,
////				PANEL_HEIGHT * 3 / 16, PANEL_WIDTH / 12, PANEL_HEIGHT / 24);
//		collectionOKButton.setBounds(PANEL_WIDTH * 11 / 18, PANEL_HEIGHT * 3 / 16,
//				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
//		infoOKButton.setBounds(PANEL_WIDTH * 11 / 36, PANEL_HEIGHT * 3 / 16,
//				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
//		next.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
//				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
//		previous.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 45 / 48,
//				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
//		totalButton.setBounds(PANEL_WIDTH / 18, PANEL_HEIGHT * 45 / 48,
//				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
//		infoLine.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 45 / 48,
//				PANEL_WIDTH * 5 / 12, PANEL_HEIGHT / 24);
//
//		info.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 4 / 15,
//				PANEL_WIDTH * 5 / 6, PANEL_HEIGHT * 13 / 20);
//	}
//	*/
//
///*	public void setBounds(int x, int y, int width, int height) {
//
//		super.setBounds(x, y, width, height);
//		PANEL_WIDTH = width;
//		PANEL_HEIGHT = height;
//		setCmpLocation();
//		repaint();
//	}
//	*/
//	
//	
//	
//
//	public void dateChooseui() {
//
//	}
//
//	/**
//	 * 输入（选择）日期的方法,这个怎么写
//	 * */
//	public void infookui() {
////		date_str=date_Input.getText();
////		if(date_str.equals("")){
////			JOptionPane.showMessageDialog(null, "请输入完整信息！", "提示",
////					JOptionPane.CLOSED_OPTION);
////		}
//
//	}
//
//	/**
//	 * 确定输入日期的方法
//	 * */
//	public void okui() {
//		//这里需要格式的转化"2015/11/10——20151110"
//		date_str=date_Input.getText();
//		System.out.println(date_str);
//
//		if(date_str.equals("")){
//			JOptionPane.showMessageDialog(null, "请输入完整信息！", "提示",
//					JOptionPane.CLOSED_OPTION);
//		}
//		else if(date_str.length()!=8){
//			JOptionPane.showMessageDialog(null, "请输入正确的日期！", "提示",
//					JOptionPane.CLOSED_OPTION);
//			date_Input.setText("");
//		}
//		else if(date_str.substring(0,4).compareTo("2015")>0||date_str.substring(4,6).compareTo("12")>0||date_str.substring(6,8).compareTo("31")>0){
//			JOptionPane.showMessageDialog(null, "请输入正确的日期！", "提示",
//					JOptionPane.CLOSED_OPTION);
//			date_Input.setText("");
//		}
//		else{
//			CollectionReceiptPanel crpanel=new CollectionReceiptPanel();
//			crpanel.refreshGatheringTable(date_str);
//			System.out.println("refresh collectiontable");
//			
//		}
//
//	}
//
//	public void totalui() {
//
//	}
//
//	public void nextui() {
//
//	}
//
//	public void previousui() {
//
//	}
//	
//	//表格样式
//	class CollectionModel extends AbstractTableModel{
//
//		
//		private static final long serialVersionUID = 1L;
//		ArrayList<ArrayList<String>> c = new ArrayList<ArrayList<String>>();
//		//操作人还要吗
//		String head[]={"收款单编号","日期","营业厅编号","金额"};
//		
//		public CollectionModel(ArrayList<ArrayList<String>> content){
//			c=content;
//		}
//		
//		//表格行数
//		public int getRowCount() {
//			// TODO Auto-generated method stub
//			return c.size();
//		}
//
//		//表格列数
//		public int getColumnCount() {
//			// TODO Auto-generated method stub
//			return head.length;
//		}
//		
//		//定位到某行某列的元素
//		public String getValueAt(int row, int col) {
//			return c.get(row).get(col);
//		}
//
//		//表头项目
//		public String getColumnName(int col) {
//			return head[col];
//		}
//
//		public void addRow(ArrayList<String> v) {
//
//			c.add(v);
//		}
//
//		public void removeRow(int row) {
//			c.remove(row);
//		}
//		
//	}
//	
//	//先测试一下
//	public void refreshGatheringTable(String time){
//		GatheringReceiptVO vo1=new GatheringReceiptVO(null, "20151203", null, null, 299, "SKD-20151203", ReceiptState.SUBMIT);
//		GatheringReceiptVO vo2=new GatheringReceiptVO(null, "20151201", null, null, 200, "SKD-20151201", ReceiptState.APPROVE);
////		ArrayList<GatheringReceiptVO> gatherings=service.getGathering(time);
//		ArrayList<GatheringReceiptVO> gatherings=new ArrayList<GatheringReceiptVO>();
//		gatherings.add(vo1);
//		gatherings.add(vo2);
//		System.out.println("hhhh");
//		for(GatheringReceiptVO gvo:gatherings){
//			ArrayList<String> lineInfo=new ArrayList<String>();
//			System.out.println(gvo.receiptID);
//			lineInfo.add(gvo.receiptID);
//			lineInfo.add(gvo.time);
////			lineInfo.add(gvo.businesshall.organizationID);
//			lineInfo.add(gvo.totalmoney+"");
//			c1.add(lineInfo);
//		}
//		
//	}
//
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.setSize(800, 550);
//		frame.add(new CollectionReceiptPanel());
//		frame.setVisible(true);
//	}
//}
