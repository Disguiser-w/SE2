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

import businesslogic.financebl.controller.InitialStockBLController;
import vo.InitInfoVO;

public class InitialStockPanel_main extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton newButton;
	private JButton startDateButton;
	private JButton endDateButton;
	private JButton dateOKButton;
	private JButton next;
	private JButton previous;
	
	private JLabel function;
	private JLabel dateRange;

	private JTextField startDate_Input;
	private JTextField endDate_Input;
	private JTable table;
	
//	private InitialStockInfoTable_main info;
//	private int PANEL_WIDTH = 720;
//	private int PANEL_HEIGHT = 480;
//	
//	private ArrayList<InitInfoVO> initInfoList;
	InitialStockModel im;
	ArrayList<ArrayList<String>> c=new ArrayList<ArrayList<String>>();
	public InitialStockBLController controller;
	
//	private LocationHelper helper;

	
	public InitialStockPanel_main(InitialStockBLController controller){
		this.controller=controller;
		newButton = new JButton("new");
		startDateButton = new JButton("start");
		endDateButton = new JButton("end");
		dateOKButton = new JButton("ok");
		next = new JButton("next");
		previous = new JButton("previous");
		
		function = new JLabel("期初建账");
		dateRange = new JLabel("日期范围");

		startDate_Input = new JTextField("2015/10/30", 11);
		endDate_Input = new JTextField("2015/11/05", 11);
		
		im=new InitialStockModel(c);
		table=new JTable(im);
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(table.getTableHeader());
		add(table);
		
//		info = new InitialStockInfoTable_main(13,2);
		
//		setCmpLocation();
		
		newButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				newInitInfoui();
			}
		});
		
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
		
		add(newButton);
		add(startDateButton);
		add(endDateButton);
		add(dateOKButton);
		add(next);
		add(previous);
		add(function);
		add(dateRange);
		add(startDate_Input);
		add(endDate_Input);
		add(table);
//		helper = new LocationHelper(this);

//		add(info);
	}
	
/*	public void setCmpLocation(){
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		newButton.setBounds(PANEL_WIDTH * 8 / 9, PANEL_HEIGHT / 24,
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
//		serialVersionUID.setBounds((int)(width * 2.1683673469387754/25),(int)(height * 5.518590998043053/20),(int)(width *  0.6377551020408163 /25),(int)(height *  0.7827788649706457/20));
		newButton.setBounds((int)(width * 21.269132653061224/25),(int)(height * 0.7436399217221135/20),(int)(width *  2.391581632653061 /25),(int)(height *  1.643835616438356/20));
		startDateButton.setBounds((int)(width * 9.279336734693878/25),(int)(height * 3.679060665362035/20),(int)(width *  0.9885204081632653 /25),(int)(height *  1.1350293542074363/20));
		endDateButton.setBounds((int)(width * 15.561224489795919/25),(int)(height * 3.639921722113503/20),(int)(width *  0.9566326530612245 /25),(int)(height *  1.213307240704501/20));
		dateOKButton.setBounds((int)(width * 20.727040816326532/25),(int)(height * 3.522504892367906/20),(int)(width *  1.753826530612245 /25),(int)(height *  1.3307240704500978/20));
		next.setBounds((int)(width * 21.10969387755102/25),(int)(height * 17.495107632093934/20),(int)(width *  1.211734693877551 /25),(int)(height *  1.36986301369863/20));
		previous.setBounds((int)(width * 22.5765306122449/25),(int)(height * 17.495107632093934/20),(int)(width *  1.1798469387755102 /25),(int)(height *  1.36986301369863/20));
		function.setBounds((int)(width * 0.44642857142857145/25),(int)(height * 0.46966731898238745/20),(int)(width *  6.026785714285714 /25),(int)(height *  2.0743639921722115/20));
		dateRange.setBounds((int)(width * 1.594387755102041/25),(int)(height * 3.24853228962818/20),(int)(width *  3.4119897959183674 /25),(int)(height *  1.761252446183953/20));
		startDate_Input.setBounds((int)(width * 5.48469387755102/25),(int)(height * 3.6007827788649704/20),(int)(width *  3.3482142857142856 /25),(int)(height *  1.2915851272015655/20));
		endDate_Input.setBounds((int)(width * 11.543367346938776/25),(int)(height * 3.5616438356164384/20),(int)(width *  3.6033163265306123 /25),(int)(height *  1.3307240704500978/20));
		table.setBounds((int)(width * 1.530612244897959/25),(int)(height * 5.244618395303327/20),(int)(width *  21.07780612244898 /25),(int)(height *  11.232876712328768/20));


	}
	
	public void setList(ArrayList<InitInfoVO> initInfoList){
//		this.initInfoList = initInfoList;
		
//		info.setList(initInfoList);
	}
	

	public void startui() {

	}

	public void endui() {

	}

	public void dateOK() {

	}

	public void newInitInfoui() {

	}

	public void nextui() {

	}

	public void previousui() {

	}
	
	class InitialStockModel extends AbstractTableModel{

		
		private static final long serialVersionUID = 1L;
		ArrayList<ArrayList<String>> c = new ArrayList<ArrayList<String>>();
		//操作人还要吗
		String head[]={"账名称","建账日期"};
		
		public InitialStockModel(ArrayList<ArrayList<String>> content){
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
//		frame.add(new InitialStockPanel_main());
//		frame.setVisible(true);
//	}
}
