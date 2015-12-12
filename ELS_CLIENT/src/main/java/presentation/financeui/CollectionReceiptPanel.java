package presentation.financeui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import type.ReceiptState;
import type.ReceiptType;
import vo.CollectionReceiptVO;
import vo.GatheringReceiptVO;
import businesslogic.financebl.controller.CollectionReceiptBLController;
import businesslogic.receiptbl.getDate;
/**
 * 暂时先把根据营业厅筛选的去掉了，以后有时间再说吧
 * */
public class CollectionReceiptPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

//	private JButton dateChooseButton;
	private JLabel dateChooseLabel;
	private JButton infoOKButton;
	private JButton collectionOKButton;
	private JButton next;
	private JButton previous;
	private JButton totalButton;
	private JButton cancelButton;

	private JLabel function;
	private JLabel date;
	private JLabel businessHall;
	private JLabel infoLine;

	private JTextField date_Input;
	private JTextField businessHall_ID_Input;
	private JTable table;
	
//	private LocationHelper helper;

	public CollectionReceiptBLController controller;
	public FinanceFrame financeFrame;


	String hallID_str;
	String date_str;
	CollectionModel cm;
	 ArrayList<ArrayList<String>> c=new ArrayList<ArrayList<String>>();
	public CollectionReceiptPanel(CollectionReceiptBLController controller,FinanceFrame parent) {
		this.controller=controller;
		this.financeFrame=parent;
//		dateChooseButton = new JButton("日期");
		dateChooseLabel =new JLabel("日期");
		infoOKButton = new JButton("营业厅");
		collectionOKButton = new JButton("确认");
		next = new JButton("next");
		previous = new JButton("previous");
		totalButton = new JButton("合计");
		cancelButton = new JButton("返回");

		function = new JLabel("新建入款单");
		date = new JLabel("日期");
		businessHall = new JLabel("营业厅");
		infoLine = new JLabel("时间："+getDate.getdate().substring(0,4)+"-"+getDate.getdate().substring(4, 6)+"-"+getDate.getdate()
				.substring(6)+ " 合计金额：970");

		date_Input = new JTextField("");
		businessHall_ID_Input = new JTextField("");

		setLayout(null);

//		add(dateChooseButton);
		add(dateChooseLabel);
		add(infoOKButton);
		add(collectionOKButton);
		add(next);
		add(previous);
		add(totalButton);
		add(cancelButton);
	
		
		add(function);
		add(date);
		add(businessHall);
		add(infoLine);

		add(date_Input);
		add(businessHall_ID_Input);

		addListener();
//		refreshGatheringTable(controller.getGathering(""));
		cm=new CollectionModel(c);
		table=new JTable(cm);
//		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(table.getTableHeader());
		add(table);
//		setInfos();
		
//		addListener();
		dateChooseLabel.setLayout(new BorderLayout());
		dateChooseLabel.add(new DateChooser(date_Input),BorderLayout.CENTER);
		
	}


	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 所有组件setBounds

//		serialVersionUID.setBounds((int)(width * 2.5510204081632653/25),(int)(height * 5.205479452054795/20),(int)(width *  17.92091836734694 /25),(int)(height *  1.0176125244618395/20));
//		dateChooseButton.setBounds((int)(width * 7.174744897959184/25),(int)(height * 3.5616438356164384/20),(int)(width *  1.0204081632653061 /25),(int)(height *  0.9001956947162426/20));
		dateChooseLabel.setBounds((int)(width * 7.174744897959184/25),(int)(height * 3.5616438356164384/20),(int)(width *  1.0204081632653061 /25),(int)(height *  0.9001956947162426/20));
		infoOKButton.setBounds((int)(width * 14.09438775510204/25),(int)(height * 3.5616438356164384/20),(int)(width *  0.9885204081632653 /25),(int)(height *  0.9784735812133072/20));
		collectionOKButton.setBounds((int)(width * 20.535714285714285/25),(int)(height * 3.522504892367906/20),(int)(width *  1.6262755102040816 /25),(int)(height *  0.9393346379647749/20));
		next.setBounds((int)(width * 21.1734693877551/25),(int)(height * 18.747553816046967/20),(int)(width *  1.0204081632653061 /25),(int)(height *  0.821917808219178/20));
		previous.setBounds((int)(width * 22.544642857142858/25),(int)(height * 18.747553816046967/20),(int)(width *  1.0204081632653061 /25),(int)(height *  0.821917808219178/20));
		totalButton.setBounds((int)(width * 18.239795918367346/25),(int)(height * 18.434442270058707/20),(int)(width *  2.072704081632653 /25),(int)(height *  0.9784735812133072/20));
		cancelButton.setBounds((int)(width * 18.239795918367346/28), (int)(height * 18.434442270058707/20),(int)(width *  2.072704081632653 /25),(int)(height *  0.9784735812133072/20));
		function.setBounds((int)(width * 0.6696428571428571/25),(int)(height * 0.821917808219178/20),(int)(width *  6.919642857142857 /25),(int)(height *  1.643835616438356/20));
		date.setBounds((int)(width * 2.391581632653061/25),(int)(height * 3.5616438356164384/20),(int)(width *  1.594387755102041 /25),(int)(height *  0.9001956947162426/20));
		businessHall.setBounds((int)(width * 9.056122448979592/25),(int)(height * 3.6007827788649704/20),(int)(width *  1.6581632653061225 /25),(int)(height *  0.8610567514677103/20));
		infoLine.setBounds((int)(width * 2.5191326530612246/25),(int)(height * 18.12133072407045/20),(int)(width *  13.424744897959183 /25),(int)(height *  1.2915851272015655/20));
		date_Input.setBounds((int)(width * 4.177295918367347/25),(int)(height * 3.679060665362035/20),(int)(width *  2.774234693877551 /25),(int)(height *  0.821917808219178/20));
		businessHall_ID_Input.setBounds((int)(width * 11.033163265306122/25),(int)(height * 3.5616438356164384/20),(int)(width *  2.5510204081632653 /25),(int)(height *  0.9001956947162426/20));
		table.getTableHeader().setBounds(((int)(width * 2.5510204081632653/25)),(int)(height * 6.14481409001957/20),(int)(width *  17.92091836734694 /25),(int)(height *  1.095890410958904/20));
		table.setBounds((int)(width * 2.5510204081632653/25),(int)(height * 6.14481409001957/20)+(int)(height *  1.095890410958904/20),(int)(width *  17.92091836734694 /25),(int)(height *  10.95890410958904/20));
		setBaseInfo();
	}
	
	    /**
	     * 设置表格的基本内容
	     * */
		private void setBaseInfo() {

			// 设置成不可编辑不可改变位置，大小
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setResizingAllowed(false);

			TableColumn column1 = table.getColumnModel().getColumn(0);
			TableColumn column2 = table.getColumnModel().getColumn(1);
			TableColumn column3 = table.getColumnModel().getColumn(2);
			TableColumn column4 = table.getColumnModel().getColumn(3);

			// 设置宽度
			column1.setPreferredWidth(table.getWidth() * 2 / 10);
			column2.setPreferredWidth(table.getWidth() * 2/ 10);
			column3.setPreferredWidth(table.getWidth() * 4 / 10);
			column3.setPreferredWidth(table.getWidth() * 2 / 10);

			table.setRowHeight((table.getHeight() - table.getTableHeader().getHeight()) / 8);
		
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
		}

		
		private void addListener(){
			/**
			 * 监听
			 * */
	                  /**
	                    * 选择日期
	                   * */
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
			
			cancelButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					cancelui();
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
		}
		
	
		
		/**
		 * 监听方法
		 * */
	public void dateChooseui() {

	}

	/**
	 * 输入（选择）日期的方法,这个怎么写
	 * */
	public void infookui() {
	}

	/**
	 * 确定
	 * */
	public void okui() {
		//这里需要格式的转化"2015/11/10——20151110"
		date_str=date_Input.getText();
		String test=date_str.substring(0, 4)+date_str.substring(5, 7)+date_str.substring(8);

		if(date_str.equals("")){
			JOptionPane.showMessageDialog(null, "请输入完整信息！", "提示",
					JOptionPane.CLOSED_OPTION);
		}
		else if(test.length()!=8){
			JOptionPane.showMessageDialog(null, "请输入正确的日期！", "提示",
					JOptionPane.CLOSED_OPTION);
			date_Input.setText("");
		}
		else if(test.substring(0,4).compareTo("2015")>0||test.substring(4,6).compareTo("12")>0||test.substring(6,8).compareTo("31")>0){
			JOptionPane.showMessageDialog(null, "请输入正确的日期！", "提示",
					JOptionPane.CLOSED_OPTION);
			date_Input.setText("");
		}
		else{
			//我可以把获取数据的过程放在这里啊2333
			int temp=c.size();
			System.out.println(date_str);
			ArrayList<GatheringReceiptVO> gatherings=controller.getGathering(date_str);
	      refreshGatheringTable(gatherings);
	      cm=new CollectionModel(c);
	      for(int i=0;i<temp;i++){
	    	  cm.removeRow(0);
	      }
	      table.repaint();
		}

	}
	
	/**
	 * 取消
	 * */
	public void cancelui(){
		financeFrame.toMainPanel();
	}
	

	public void totalui() {
//		double money=controller.getTotalMoney(controller.getGathering(date_str));
		double money=0;
		infoLine.setText("日期："+getDate.getdate().substring(0,4)+"-"+getDate.getdate().substring(4, 6)+"-"+getDate.getdate()
				.substring(6)+"    金额总和："+money);
		CollectionReceiptVO vo=new CollectionReceiptVO(controller.getCollectionListID(), "CW-00001", ReceiptType.COLLECTIONRECEIPT, ReceiptState.SUBMIT, money, getDate.getdate(), "boss");
		controller.creatCollection(vo);
	}

	public void nextui() {

	}

	public void previousui() {

	}
	
	class CollectionModel extends AbstractTableModel{

		
		private static final long serialVersionUID = 1L;
//		ArrayList<ArrayList<String>> c = new ArrayList<ArrayList<String>>();
		//操作人还要吗
		String head[]={"收款单编号","日期","营业厅编号","金额"};
		
		public CollectionModel(ArrayList<ArrayList<String>> content){
			c=content;
		}
		//行数
		public int getRowCount() {
			// TODO Auto-generated method stub
		
			return 10;
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
//			System.out.println(head.length);
			return head.length;
		}
		
		public String getValueAt(int row, int col) {
			if(row>c.size()-1){
				return null;
			}
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
	

	public void refreshGatheringTable(ArrayList<GatheringReceiptVO> gatherings){
		for(GatheringReceiptVO gvo:gatherings){
			ArrayList<String> lineInfo=new ArrayList<String>();
			lineInfo.add(gvo.receiptID);
			lineInfo.add(gvo.time);
			lineInfo.add(gvo.businesshall.organizationID);
			lineInfo.add(gvo.totalmoney+"");
			c.add(lineInfo);
		}
		
	}

//	public static void main(String[] args) {
//		CollectionReceiptBLController controller=new CollectionReceiptBLController();
//		JFrame frame = new JFrame();
//		frame.setSize(800, 550);
//		frame.add(new CollectionReceiptPanel(controller));
//		frame.setVisible(true);
//	}
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

//info.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 4 / 15,
//		PANEL_WIDTH * 5 / 6, PANEL_HEIGHT * 13 / 20);
}
*/


/**
 * 获取c：因为c要在panel之前声明
 * */
/*public ArrayList<ArrayList<String>> getInfo(ArrayList<GatheringReceiptVO> gatherings){
//	ArrayList<GatheringReceiptVO> gatherings=service.getGathering(time);


//	GatheringReceiptVO vo1=new GatheringReceiptVO(null, "20151203", null, null, 299, "SKD-20151203", ReceiptState.SUBMIT);
//	GatheringReceiptVO vo2=new GatheringReceiptVO(null, "20151201", null, null, 200, "SKD-20151201", ReceiptState.APPROVE);
//	ArrayList<GatheringReceiptVO> gatherings=new ArrayList<GatheringReceiptVO>();
//	gatherings.add(vo1);
//	gatherings.add(vo2);
	for(GatheringReceiptVO gvo:gatherings){
		ArrayList<String> lineInfo=new ArrayList<String>();
		lineInfo.add(gvo.receiptID);
		lineInfo.add(gvo.time);
//		lineInfo.add(gvo.businesshall.organizationID);
		lineInfo.add(gvo.totalmoney+"");
		c.add(lineInfo);
	}
	return c;
}
*/
