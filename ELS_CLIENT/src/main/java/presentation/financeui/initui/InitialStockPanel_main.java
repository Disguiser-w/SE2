package presentation.financeui.initui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import presentation.financeui.FinanceFrame;
import businesslogic.businessbl.controller.VehicleManagerController;
import businesslogic.financebl.controller.AccountBLController;
import businesslogic.financebl.controller.InitialStockBLController;
import businesslogic.managebl.controller.OrganizationController;
import businesslogic.repertorybl.RepertoryBL;
import businesslogic.userbl.UserBL;
import vo.InitInfoVO;

public class InitialStockPanel_main extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton newButton;
	private JButton detailButton;
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
	
	InitialStockModel im;
	ArrayList<ArrayList<String>> c=new ArrayList<ArrayList<String>>();
	 InitialStockBLController controller;
	 UserBL userController;
	 OrganizationController organizationController;
	 VehicleManagerController vehicleController;
	 RepertoryBL repertoryController;
	 AccountBLController accountController;
	 FinanceFrame financeFrame;
	
//	private LocationHelper helper;

	
	public InitialStockPanel_main(InitialStockBLController controller,UserBL userController,OrganizationController organizationController,
			VehicleManagerController vehicleController,RepertoryBL repertoryController,AccountBLController accountController,
			FinanceFrame parent){
//	 public InitialStockPanel_main(InitialStockBLController controller,UserBL userController,OrganizationController organizationController,VehicleManagerController vehicleController,AccountBLController accountController,FinanceFrame parent){
		this.controller=controller;
		this.userController=userController;
		this.organizationController=organizationController;
		this.vehicleController=vehicleController;
//		this.repertoryController=repertoryController;
		this.accountController=accountController;
		this.financeFrame=parent;
		newButton = new JButton("新建");
		detailButton =new JButton("详情");
		startDateButton = new JButton("开始时间");
		endDateButton = new JButton("结束时间");
		dateOKButton = new JButton("确认");
		next = new JButton("下");
		previous = new JButton("上");
		
		function = new JLabel("期初建账");
		dateRange = new JLabel("日期范围");

		startDate_Input = new JTextField("", 11);
		endDate_Input = new JTextField("", 11);
		
		im=new InitialStockModel(c);
		table=new JTable(im);
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(table.getTableHeader());
		add(table);
		

		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				newInitInfoui();
			}
		});
		
		detailButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				detailui();
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
		add(detailButton);
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
	}

	
	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);

		detailButton.setBounds((int)(width * 21.269132653061224/25),(int)(height * 0.7436399217221135/20),(int)(width *  2.391581632653061 /25),(int)(height *  1.643835616438356/20));
		newButton.setBounds((int)(width * 21.269132653061224/30),(int)(height * 0.7436399217221135/20),(int)(width *  2.391581632653061 /25),(int)(height *  1.643835616438356/20));
		startDateButton.setBounds((int)(width * 9.279336734693878/25),(int)(height * 3.679060665362035/20),(int)(width *  0.9885204081632653 /25),(int)(height *  1.1350293542074363/20));
		endDateButton.setBounds((int)(width * 15.561224489795919/25),(int)(height * 3.639921722113503/20),(int)(width *  0.9566326530612245 /25),(int)(height *  1.213307240704501/20));
		dateOKButton.setBounds((int)(width * 20.727040816326532/25),(int)(height * 3.522504892367906/20),(int)(width *  1.753826530612245 /25),(int)(height *  1.3307240704500978/20));
		next.setBounds((int)(width * 21.10969387755102/25),(int)(height * 17.495107632093934/20),(int)(width *  1.211734693877551 /25),(int)(height *  1.36986301369863/20));
		previous.setBounds((int)(width * 22.5765306122449/25),(int)(height * 17.495107632093934/20),(int)(width *  1.1798469387755102 /25),(int)(height *  1.36986301369863/20));
		function.setBounds((int)(width * 0.44642857142857145/25),(int)(height * 0.46966731898238745/20),(int)(width *  6.026785714285714 /25),(int)(height *  2.0743639921722115/20));
		dateRange.setBounds((int)(width * 1.594387755102041/25),(int)(height * 3.24853228962818/20),(int)(width *  3.4119897959183674 /25),(int)(height *  1.761252446183953/20));
		startDate_Input.setBounds((int)(width * 5.48469387755102/25),(int)(height * 3.6007827788649704/20),(int)(width *  3.3482142857142856 /25),(int)(height *  1.2915851272015655/20));
		endDate_Input.setBounds((int)(width * 11.543367346938776/25),(int)(height * 3.5616438356164384/20),(int)(width *  3.6033163265306123 /25),(int)(height *  1.3307240704500978/20));
		table.getTableHeader().setBounds((int)(width * 1.530612244897959/25),(int)(height * 5.244618395303327/20),(int)(width *  21.07780612244898 /25),(int)(height *  1.1232876712328768/20));
		table.setBounds((int)(width * 1.530612244897959/25),(int)(height * 5.244618395303327/20)+(int)(height *  1.1232876712328768/20),(int)(width *  21.07780612244898 /25),(int)(height *  11.232876712328768/21));

		setBaseInfo();
	}
	

	private void setBaseInfo() {

		// 设置成不可编辑不可改变位置，大小
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		TableColumn column1 = table.getColumnModel().getColumn(0);
		TableColumn column2 = table.getColumnModel().getColumn(1);
		// 设置宽度
		column1.setPreferredWidth(table.getWidth() * 4 / 10);
		column2.setPreferredWidth(table.getWidth() * 6/ 10);


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

	}
	
	public void setList(ArrayList<InitInfoVO> initInfoList){
	}
	

	public void startui() {

	}

	public void endui() {

	}

	public void dateOK() {

	}

	public void newInitInfoui() {
		financeFrame.changePanel(new InitialStockPanel_new(controller, userController,organizationController,vehicleController,repertoryController,accountController, financeFrame));
	}
	
	public void detailui(){
		financeFrame.changePanel(new InitialStockPanel_detail(controller, financeFrame));
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
	
	public void refreshTable(){
		
	}

/*	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		InitialStockBLController controller=new InitialStockBLController();
		FinanceFrame financeFrame=new FinanceFrame();
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new InitialStockPanel_main(controller,financeFrame));
		frame.setVisible(true);
	}
	*/
	
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

//	info.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 4 / 15,
//			PANEL_WIDTH * 5 / 6, PANEL_HEIGHT * 13 / 20);
}
*/
