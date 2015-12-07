package presentation.financeui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import businesslogic.financebl.controller.CostIncomeReceiptBLController;
import businesslogic.financebl.controller.PaymentReceiptBLController;
import presentation.commonui.LocationHelper;
import vo.CollectionReceiptVO;

public class ReceiptPanel_new extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JButton sendButton;
	private JButton printButton;
	private JButton collectionReceiptButton_new;
	private JButton paymentReceiptButton_new;
	private JButton costIncomeReceiptButton_new;
	
	private JButton next;
	private JButton previous;

	private JLabel function;
	private JLabel collectionReceiptInfo;
	private JLabel paymentReceiptInfo;
	private JLabel costIncomeReceiptInfo;
	
	private JTable table;

//	private ReceiptInfoTable_new info;
	
//	private LocationHelper helper;
//
//	private int PANEL_WIDTH = 720;
//	private int PANEL_HEIGHT = 480;
	
	ReceiptModel rm;
	//其实这个类应该需要三个ArrayList的
//	 ArrayList<ArrayList<String>> c=new ArrayList<ArrayList<String>>();
	
	private ArrayList<String> collections;
	 public CollectionReceiptBLController collectionController;
	 public PaymentReceiptBLController paymentReceiptBLController;
	 public CostIncomeReceiptBLController costIncomeController;

	public ReceiptPanel_new(CollectionReceiptBLController collectionController, PaymentReceiptBLController paymentReceiptBLController, CostIncomeReceiptBLController costIncomeController) {
		this.collectionController=collectionController;
		this.paymentReceiptBLController=paymentReceiptBLController;
		this.costIncomeController=costIncomeController;
		
		
		sendButton = new JButton("send");
		printButton = new JButton("print");
		collectionReceiptButton_new = new JButton("new1");
		paymentReceiptButton_new = new JButton("new2");
		costIncomeReceiptButton_new = new JButton("new3");
		next = new JButton("next");
		previous = new JButton("pre");
		function = new JLabel("新建表单");
		collectionReceiptInfo = new JLabel("入款单");
		paymentReceiptInfo = new JLabel("付款单");
		costIncomeReceiptInfo = new JLabel("成本收益表");

		table=new JTable();
		collections=new ArrayList<String>();

		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
//		setCmpLocation();

		
		setLayout(null);

		add(sendButton);
		add(printButton);
	
		add(collectionReceiptButton_new);
		add(paymentReceiptButton_new);
		add(costIncomeReceiptButton_new);
	
		add(next);
		add(previous);
		add(function);
		add(collectionReceiptInfo);
		add(paymentReceiptInfo);
		add(costIncomeReceiptInfo);
		add(table.getTableHeader());
		add(table);
//		add(info);
		
//		helper = new LocationHelper(this);

		addListener();
	}

	

	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);
//		PANEL_WIDTH = width;
//		PANEL_HEIGHT = height;
//		setCmpLocation();
//		repaint();
		
//	serialVersionUID.setBounds((int)(width * 19.770408163265305/25),(int)(height * 0.9784735812133072/20),(int)(width *  2.2002551020408165 /25),(int)(height *  1.5655577299412915/20));
		printButton.setBounds((int)(width * 22.034438775510203/25),(int)(height * 0.9784735812133072/20),(int)(width *  2.1364795918367347 /25),(int)(height *  1.5655577299412915/20));
		collectionReceiptButton_new.setBounds((int)(width * 8.258928571428571/25),(int)(height * 1.0567514677103718/20),(int)(width *  2.1364795918367347 /25),(int)(height *  1.4090019569471623/20));
		paymentReceiptButton_new.setBounds((int)(width * 10.746173469387756/25),(int)(height * 1.0176125244618395/20),(int)(width *  2.104591836734694 /25),(int)(height *  1.4090019569471623/20));
		costIncomeReceiptButton_new.setBounds((int)(width * 13.201530612244898/25),(int)(height * 1.0567514677103718/20),(int)(width *  2.2002551020408165 /25),(int)(height *  1.36986301369863/20));
		next.setBounds((int)(width * 22.5765306122449/25),(int)(height * 17.76908023483366/20),(int)(width *  1.2436224489795917 /25),(int)(height *  1.487279843444227/20));
		previous.setBounds((int)(width * 20.982142857142858/25),(int)(height * 17.847358121330725/20),(int)(width *  1.2436224489795917 /25),(int)(height *  1.487279843444227/20));
		function.setBounds((int)(width * 1.2755102040816326/25),(int)(height * 0.7045009784735812/20),(int)(width *  5.133928571428571 /25),(int)(height *  2.0743639921722115/20));
		collectionReceiptInfo.setBounds((int)(width * 1.371173469387755/25),(int)(height * 4.031311154598826/20),(int)(width *  3.858418367346939 /25),(int)(height *  1.2524461839530332/20));
		paymentReceiptInfo.setBounds((int)(width * 5.165816326530612/25),(int)(height * 4.031311154598826/20),(int)(width *  3.985969387755102 /25),(int)(height *  1.2524461839530332/20));
		costIncomeReceiptInfo.setBounds((int)(width * 9.119897959183673/25),(int)(height * 4.031311154598826/20),(int)(width *  3.443877551020408 /25),(int)(height *  1.2915851272015655/20));
		table.getTableHeader().setBounds((int)(width * 1.371173469387755/25),(int)(height * 5.283757338551859/20),(int)(width *  21.651785714285715 /25),(int)(height *  1.1819960861056751/20));
		table.setBounds((int)(width * 1.371173469387755/25),(int)(height * 5.283757338551859/20)+(int)(height *  1.1819960861056751/20),(int)(width *  21.651785714285715 /25),(int)(height *  11.819960861056751/20));

		setInfos();

	}
	
	private void setInfos() {

		table.setModel(new ReceiptModel());
		setBaseInfo();
	}
	
	// 设置table的基本内容，图片，什么的
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
			column1.setPreferredWidth(table.getWidth() * 4 / 10);
			column2.setPreferredWidth(table.getWidth() * 2 / 10);
			column3.setPreferredWidth(table.getWidth() * 2 / 10);
			column4.setPreferredWidth(table.getWidth() * 2 / 10);


//			table.setRowHeight((table.getHeight() - table.getTableHeader().getHeight()) / 8);
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
			column4.setCellRenderer(tcr);
			

		}
	
	
	public void addListener(){
		collectionReceiptInfo.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				ArrayList<CollectionReceiptVO> vos=collectionController.getAllCollection();
				for(CollectionReceiptVO v:vos){
					System.out.println(v.getID());
				}
				collections=new ArrayList<String>();
				for(CollectionReceiptVO v:vos){
				collections.add(v.getID()+" "+v.getDate()+" "+v.getAccount()+" "+v.getUserID());
				}
				
				setInfos();
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

		costIncomeReceiptButton_new.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				new3ui();
			}
		});
	}

	public void sendui() {

	}

	public void printui() {

	}

	public void new1ui() {

	}

	public void new2ui() {

	}

	public void new3ui() {

	}

	public void nextui() {

	}

	public void previousui() {

	}
	

	
	class ReceiptModel extends AbstractTableModel{

		
		private static final long serialVersionUID = 1L;
//		ArrayList<ArrayList<String>> c = new ArrayList<ArrayList<String>>();
		//操作人还要吗
//		String head[]={"编号","日期","交易金额","提交人"};
		
		//行数
		public int getRowCount() {
			// TODO Auto-generated method stub
			return 10;
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 4;
		}
		
		public String getValueAt(int row, int col) {
//			if(index>collections.size()-1)
//				return null;
		
			String infos=collections.get(row);
			if(infos!=null){
				String[] info = infos.split(" ");
				if (col == 0)
					return info[0];
				else if (col == 1)
					return info[1];
				else if (col==2)
					return info[2];
				else if(col==3)
					return info[3];
				else
					return null;
			}
			else{
				return null;
			}
			
		}
			public String getColumnName(int c) {
				if (c == 0)
					return "编号";
				else if (c == 1)
					return "日期";
				else if(c==2)
					return "金额";
				else 
					return "收款人";
				}
			
		}
		
	

	/*public static void main(String[] args) {
		
		 CollectionReceiptBLController collectionController = null ;
		 PaymentReceiptBLController paymentReceiptBLController = null ;
		 CostIncomeReceiptBLController costIncomeController = null ;
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new ReceiptPanel_new(collectionController,paymentReceiptBLController,costIncomeController));
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