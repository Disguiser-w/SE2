package presentation.financeui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import businesslogic.financebl.controller.BusinessStatementReceiptBLController;
import presentation.commonui.LocationHelper;
import presentation.financeui.PaymentReceiptPanel.PaymentModel;
import presentation.intermediateui.TransferingPanel;
import vo.CollectionReceiptVO;
import vo.PaymentReceiptVO;

public class BusinessStateReceiptPanel extends JPanel {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton startDateButton;
	private JButton endDateButton;
	private JButton dateOKButton;
	private JButton printButton;
	private JButton sendButton;
	private JButton next;
	private JButton previous;

	private JLabel function;
	private JLabel dateRange;

	private JTextField startDate_Input;
	private JTextField endDate_Input;
	
	private JTable table;

//	private BusinessStateReceiptInfoTable info;
//	private LocationHelper helper;
//	private int PANEL_WIDTH = 720;
//	private int PANEL_HEIGHT = 480;

	private BusinessStatementModel bm;
	ArrayList<ArrayList<String>> c=new ArrayList<ArrayList<String>>();

	public BusinessStatementReceiptBLController controller;


	public BusinessStateReceiptPanel(BusinessStatementReceiptBLController controller) {
		this.controller=controller;
		
		startDateButton = new JButton("start");
		endDateButton = new JButton("end");
		dateOKButton = new JButton("ok");
		printButton = new JButton("print");
		sendButton = new JButton("send");
		next = new JButton("next");
		previous = new JButton("previous");

		function = new JLabel("经营情况表");
		dateRange = new JLabel("日期范围");

		startDate_Input = new JTextField("2015/10/30", 11);
		endDate_Input = new JTextField("2015/11/05", 11);

//		info = new BusinessStateReceiptInfoTable(13, 4);
		
		//model
		bm=new BusinessStatementModel(c);
		//新建table
		table=new JTable(bm);
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(table.getTableHeader());
		add(table);


//		setCmpLocation();

		startDateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				startui();
			}
		});

		endDateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				endui();
			}
		});

		dateOKButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				dateOK();
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

		setLayout(null);

		add(startDateButton);
		add(endDateButton);
		add(dateOKButton);
		add(printButton);
		add(sendButton);
		add(next);
		add(previous);
		add(function);
		add(dateRange);
		add(startDate_Input);
		add(endDate_Input);
		
		add(table);
//		add(info);
		
//		helper = new LocationHelper(this);

	}

/*	public void setCmpLocation() {
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 5 / 18, PANEL_HEIGHT / 12);
		printButton.setBounds(PANEL_WIDTH * 7 / 9, PANEL_HEIGHT / 24,
				PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
		sendButton.setBounds(PANEL_WIDTH * 8 / 9, PANEL_HEIGHT / 24,
				PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
		dateRange.setBounds(PANEL_WIDTH / 4, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
		startDateButton.setBounds(PANEL_WIDTH * 19 / 36, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		startDate_Input.setBounds(PANEL_WIDTH * 7 / 18, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
		endDateButton.setBounds(PANEL_WIDTH * 13 / 18, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		endDate_Input.setBounds(PANEL_WIDTH * 7 / 12, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
		next.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		previous.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);

//		info.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 4 / 15,
//				PANEL_WIDTH * 5 / 6, PANEL_HEIGHT * 13 / 20);
	}
	*/

	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);
//		PANEL_WIDTH = width;
//		PANEL_HEIGHT = height;
//		setCmpLocation();
//		repaint();
//	serialVersionUID.setBounds((int)(width * 1.530612244897959/25),(int)(height * 4.7908745247148286/20),(int)(width *  20.854591836734695 /25),(int)(height *  1.1406844106463878/20));
		startDateButton.setBounds((int)(width * 9.02423469387755/25),(int)(height * 3.307984790874525/20),(int)(width *  0.9247448979591837 /25),(int)(height *  1.1787072243346008/20));
		endDateButton.setBounds((int)(width * 14.41326530612245/25),(int)(height * 3.307984790874525/20),(int)(width *  1.052295918367347 /25),(int)(height *  1.2547528517110267/20));
		dateOKButton.setBounds((int)(width * 19.419642857142858/25),(int)(height * 3.307984790874525/20),(int)(width *  2.1683673469387754 /25),(int)(height *  1.1406844106463878/20));
		printButton.setBounds((int)(width * 19.419642857142858/25),(int)(height * 0.7984790874524715/20),(int)(width *  2.2002551020408165 /25),(int)(height *  1.55893536121673/20));
		sendButton.setBounds((int)(width * 22.193877551020407/25),(int)(height * 0.7984790874524715/20),(int)(width *  2.1683673469387754 /25),(int)(height *  1.55893536121673/20));
		next.setBounds((int)(width * 21.1734693877551/25),(int)(height * 18.745247148288975/20),(int)(width *  1.0204081632653061 /25),(int)(height *  0.7984790874524715/20));
		previous.setBounds((int)(width * 22.544642857142858/25),(int)(height * 18.745247148288975/20),(int)(width *  1.0204081632653061 /25),(int)(height *  0.7984790874524715/20));
		function.setBounds((int)(width * 0.6696428571428571/25),(int)(height * 0.7984790874524715/20),(int)(width *  6.919642857142857 /25),(int)(height *  1.6349809885931559/20));
		dateRange.setBounds((int)(width * 1.4987244897959184/25),(int)(height * 3.2699619771863118/20),(int)(width *  3.0931122448979593 /25),(int)(height *  1.3307984790874525/20));
		startDate_Input.setBounds((int)(width * 5.261479591836735/25),(int)(height * 3.3840304182509504/20),(int)(width *  3.1568877551020407 /25),(int)(height *  1.1787072243346008/20));
		endDate_Input.setBounds((int)(width * 10.778061224489797/25),(int)(height * 3.2699619771863118/20),(int)(width *  3.2206632653061225 /25),(int)(height *  1.2927756653992395/20));
		table.setBounds((int)(width * 1.530612244897959/25),(int)(height * 4.828897338403042/20),(int)(width *  20.886479591836736 /25),(int)(height *  12.357414448669202/20));



		
	}

	public void setList(ArrayList<CollectionReceiptVO> collectionReceiptList,
			ArrayList<PaymentReceiptVO> paymentReceiptList) {
		
//		info.setList(collectionReceiptList, paymentReceiptList);
	}

	public void startui() {

	}

	public void endui() {

	}

	public void dateOK() {

	}

	public void printui() {

	}

	public void sendui() {

	}

	public void nextui() {

	}

	public void previousui() {

	}
	
class BusinessStatementModel extends AbstractTableModel{

		
		private static final long serialVersionUID = 1L;
		ArrayList<ArrayList<String>> c = new ArrayList<ArrayList<String>>();
		//操作人还要吗
		String head[]={"入款单编号","收入","付款单编号","支出"};
		
	 public BusinessStatementModel(ArrayList<ArrayList<String>> content) {
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
//		frame.add(new BusinessStateReceiptPanel());
//		frame.setVisible(true);
//	}
}