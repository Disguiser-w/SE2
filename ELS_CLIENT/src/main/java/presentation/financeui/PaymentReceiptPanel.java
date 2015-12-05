package presentation.financeui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import businesslogic.financebl.controller.PaymentReceiptBLController;

public class PaymentReceiptPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private JButton dateChooseButton;
	private JButton dateOKButton;
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PaymentReceiptPanel(PaymentReceiptBLController controller) {
		this.controller=controller;
		
		dateChooseButton = new JButton("date");
		dateOKButton = new JButton("ok");
		printButton = new JButton("print");
		sendButton = new JButton("send");
		next = new JButton("next");
		previous = new JButton("previous");

		function = new JLabel("新建付款单");
		clause = new JLabel("条目");
		date = new JLabel("日期");

		date_Input = new JTextField("2015/11", 8);

		clause_choose = new JComboBox(clauseType);

//		info = new PaymentReceiptInfoTable(13, 6);
		
		pm=new PaymentModel(c);
		//新建table
		table=new JTable(pm);
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(table.getTableHeader());
		add(table);

//		setCmpLocation();

		

		setLayout(null);

		add(dateChooseButton);
		add(dateOKButton);
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
		printButton.setBounds((int)(width * 19.419642857142858/25),(int)(height * 0.821917808219178/20),(int)(width *  2.295918367346939 /25),(int)(height *  1.5264187866927592/20));
		sendButton.setBounds((int)(width * 22.225765306122447/25),(int)(height * 0.821917808219178/20),(int)(width *  2.0408163265306123 /25),(int)(height *  1.487279843444227/20));
		next.setBounds((int)(width * 20.950255102040817/25),(int)(height * 18.356164383561644/20),(int)(width *  1.1798469387755102 /25),(int)(height *  1.1350293542074363/20));
		previous.setBounds((int)(width * 22.5765306122449/25),(int)(height * 18.395303326810176/20),(int)(width *  1.1479591836734695 /25),(int)(height *  1.0567514677103718/20));
		function.setBounds((int)(width * 1.913265306122449/25),(int)(height * 0.6653620352250489/20),(int)(width *  6.919642857142857 /25),(int)(height *  1.643835616438356/20));
		clause.setBounds((int)(width * 2.74234693877551/25),(int)(height * 3.483365949119374/20),(int)(width *  1.594387755102041 /25),(int)(height *  1.0567514677103718/20));
		date.setBounds((int)(width * 7.844387755102041/25),(int)(height * 3.4442270058708413/20),(int)(width *  1.4349489795918366 /25),(int)(height *  1.095890410958904/20));
		date_Input.setBounds((int)(width * 9.948979591836734/25),(int)(height * 3.4050880626223092/20),(int)(width *  2.2002551020408165 /25),(int)(height *  1.1741682974559686/20));
		clause_choose.setBounds((int)(width * 4.878826530612245/25),(int)(height * 3.6007827788649704/20),(int)(width *  2.072704081632653 /25),(int)(height *  0.821917808219178/20));
		table.setBounds((int)(width * 2.104591836734694/25),(int)(height * 5.088062622309198/20),(int)(width *  20.37627551020408 /25),(int)(height *  11.937377690802348/20));




		

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
		String head[]={"付款单编号","日期","条目","金额"};
		
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
