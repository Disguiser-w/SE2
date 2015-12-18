package presentation.financeui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import businesslogic.financebl.controller.PaymentReceiptBLController;
import presentation.commonui.OperationPanel;
import type.ReceiptState;
import type.ReceiptType;
import vo.PaymentReceiptVO;
import vo.UserVO;

public class PaymentReceiptPanel extends OperationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private JButton dateChooseButton;
	private JButton dateOKButton;
	private JButton cancelButton;
	private JButton printButton;
	private JButton sendButton;
	private JButton next;
	private JButton previous;

	private JLabel function;
	private JLabel clause;
	private JLabel date;

	private JTextField date_Input;

	@SuppressWarnings("rawtypes")
	private JComboBox clause_choose;
	private JTable table;
//	private LocationHelper helper;

//	private PaymentReceiptInfoTable info;
	
	
	PaymentModel pm;
	ArrayList<ArrayList<String>> c=new ArrayList<ArrayList<String>>();
	private String[] clauseType = { "运费", "租金", "工资" };

	public PaymentReceiptBLController controller;
	public FinanceFrame financeFrame;
	public UserVO user;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PaymentReceiptPanel(PaymentReceiptBLController controller,FinanceFrame parent,UserVO user) {
		this.controller=controller;
		this.financeFrame=parent;
		this.user = user;
		dateChooseButton = new JButton("日期");
		dateOKButton = new JButton("确认");
		cancelButton =new JButton("取消");
		printButton = new JButton("打印");
		sendButton = new JButton("发送");
		next = new JButton("下");
		previous = new JButton("上");

		function = new JLabel("新建付款单");
		clause = new JLabel("条目");
		date = new JLabel("日期");

		date_Input = new JTextField("2015/11", 8);

		clause_choose = new JComboBox(clauseType);

//		info = new PaymentReceiptInfoTable(13, 6);
		
		pm=new PaymentModel(c);
		//新建table
		table=new JTable(pm);
//		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(table.getTableHeader());
		add(table);

//		setCmpLocation();

		

		setLayout(null);

		add(dateChooseButton);
		add(dateOKButton);
		add(cancelButton);
		add(printButton);
		add(sendButton);
		add(next);
		add(previous);
		add(function);
		add(clause);
		add(date);
		add(date_Input);
		add(clause_choose);
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
		
//	serialVersionUID.setBounds((int)(width * 2.104591836734694/25),(int)(height * 5.088062622309198/20),(int)(width *  20.34438775510204 /25),(int)(height *  1.1741682974559686/20));
		dateChooseButton.setBounds((int)(width * 0.5420918367346939/25),(int)(height * 13.737769080234834/20),(int)(width *  14.317602040816327 /25),(int)(height *  -10.215264187866927/20));
		dateOKButton.setBounds((int)(width * 15.752551020408163/25),(int)(height * 3.3268101761252447/20),(int)(width *  2.1364795918367347 /25),(int)(height *  1.1741682974559686/20));
		cancelButton.setBounds((int)(width * 19.419642857142858/25),(int)(height * 3.3268101761252447/20),(int)(width *  2.1364795918367347 /25),(int)(height *  1.1741682974559686/20));
		printButton.setBounds((int)(width * 19.419642857142858/25),(int)(height * 0.821917808219178/20),(int)(width *  2.295918367346939 /25),(int)(height *  1.5264187866927592/20));
		sendButton.setBounds((int)(width * 22.225765306122447/25),(int)(height * 0.821917808219178/20),(int)(width *  2.0408163265306123 /25),(int)(height *  1.487279843444227/20));
		next.setBounds((int)(width * 20.950255102040817/25),(int)(height * 18.356164383561644/20),(int)(width *  1.1798469387755102 /25),(int)(height *  1.1350293542074363/20));
		previous.setBounds((int)(width * 22.5765306122449/25),(int)(height * 18.395303326810176/20),(int)(width *  1.1479591836734695 /25),(int)(height *  1.0567514677103718/20));
		function.setBounds((int)(width * 1.913265306122449/25),(int)(height * 0.6653620352250489/20),(int)(width *  6.919642857142857 /25),(int)(height *  1.643835616438356/20));
		clause.setBounds((int)(width * 2.74234693877551/25),(int)(height * 3.483365949119374/20),(int)(width *  1.594387755102041 /25),(int)(height *  1.0567514677103718/20));
		date.setBounds((int)(width * 7.844387755102041/25),(int)(height * 3.4442270058708413/20),(int)(width *  1.4349489795918366 /25),(int)(height *  1.095890410958904/20));
		date_Input.setBounds((int)(width * 9.948979591836734/25),(int)(height * 3.4050880626223092/20),(int)(width *  2.2002551020408165 /25),(int)(height *  1.1741682974559686/20));
		clause_choose.setBounds((int)(width * 4.878826530612245/25),(int)(height * 3.6007827788649704/20),(int)(width *  2.072704081632653 /25),(int)(height *  0.821917808219178/20));
		table.getTableHeader().setBounds((int)(width * 2.104591836734694/25),(int)(height * 5.088062622309198/20),(int)(width *  20.37627551020408 /25),(int)(height *  1.1937377690802348/20));
		table.setBounds((int)(width * 2.104591836734694/25),(int)(height * 5.088062622309198/20)+(int)(height *  1.1937377690802348/20),(int)(width *  20.37627551020408 /25),(int)(height *  11.937377690802348/20)-(int)(height *  1.1937377690802348/20));
		
		setBaseInfo();
	}
	
	public void setBaseInfo(){
		// 设置成不可编辑不可改变位置，大小
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getTableHeader().setReorderingAllowed(false);
					table.getTableHeader().setResizingAllowed(false);

					TableColumn column1 = table.getColumnModel().getColumn(0);
					TableColumn column2 = table.getColumnModel().getColumn(1);
					TableColumn column3 = table.getColumnModel().getColumn(2);
					TableColumn column4 = table.getColumnModel().getColumn(3);
					TableColumn column5 = table.getColumnModel().getColumn(4);
					TableColumn column6 = table.getColumnModel().getColumn(5);

					// 设置宽度
					column1.setPreferredWidth(table.getWidth() * 2 / 10);
					column2.setPreferredWidth(table.getWidth() * 2/ 10);
					column3.setPreferredWidth(table.getWidth() * 3 / 20);
					column4.setPreferredWidth(table.getWidth() * 3 / 20);
					column5.setPreferredWidth(table.getWidth() * 3 / 20);
					column6.setPreferredWidth(table.getWidth() * 3 / 20);
					


					table.setRowHeight((table.getHeight() - table.getTableHeader().getHeight()) / 8);
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
					column6.setCellRenderer(tcr);

	}
	
	/**
	 * 监听方法
	 * */
	
	public void addListener(){
		dateChooseButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				dateChooseui();
			}
		});

		dateOKButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				ok();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cancelui();
			}
		});

		printButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				printui();
			}
		});

		sendButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				sendui();
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

	public void dateChooseui() {

	}

	public void ok() {
		String time =date_Input.getText();
		PaymentReceiptVO vo = new PaymentReceiptVO(controller.getPaymentReceiptListID(), user.userID, ReceiptType.PAYMENTRECEIPT,
				ReceiptState.DRAFT, controller.getRent(time), controller.getFare(time), controller.getSalary(time),time,"总账", user.userName);
		controller.creatPaymentReceipt(vo);
	}
	
	public void cancelui(){
		financeFrame.toMainPanel();
	}

	public void printui() {

	}

	public void sendui() {

	}

	public void nextui() {

	}

	public void previousui() {

	}
	
	
class PaymentModel extends AbstractTableModel{

		
		private static final long serialVersionUID = 1L;
		ArrayList<ArrayList<String>> c = new ArrayList<ArrayList<String>>();
		//操作人还要吗
		String head[]={"付款单编号","日期","租金金额","运费金额","工资金额","金额总和"};
		
	 public PaymentModel(ArrayList<ArrayList<String>> content) {
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
	

//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.setSize(800, 550);
//		frame.add(new PaymentReceiptPanel());
//		frame.setVisible(true);
//	}
}


/*	public void setCmpLocation() {
function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
		PANEL_WIDTH * 5 / 18, PANEL_HEIGHT / 12);
printButton.setBounds(PANEL_WIDTH * 7 / 9, PANEL_HEIGHT / 24,
		PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
sendButton.setBounds(PANEL_WIDTH * 8 / 9, PANEL_HEIGHT / 24,
		PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
clause.setBounds(PANEL_WIDTH/9, PANEL_HEIGHT*3/16, PANEL_WIDTH/18, PANEL_HEIGHT/24);
clause_choose.setBounds(PANEL_WIDTH*7/36, PANEL_HEIGHT*3/16, PANEL_WIDTH/12, PANEL_HEIGHT/24);
date.setBounds(PANEL_WIDTH *11/36, PANEL_HEIGHT * 3 / 16, PANEL_WIDTH / 18,
		PANEL_HEIGHT / 24);
date_Input.setBounds(PANEL_WIDTH * 7 / 18, PANEL_HEIGHT * 3 / 16,
		PANEL_WIDTH / 12, PANEL_HEIGHT / 24);
dateOKButton.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 3 / 16,
		PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
next.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
		PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
previous.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 45 / 48,
		PANEL_WIDTH / 24, PANEL_HEIGHT / 24);

//info.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 4 / 15,
//		PANEL_WIDTH * 5 / 6, PANEL_HEIGHT * 13 / 20);
}
*/
