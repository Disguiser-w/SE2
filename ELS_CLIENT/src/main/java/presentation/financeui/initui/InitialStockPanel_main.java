package presentation.financeui.initui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import presentation.commonui.OperationPanel;
import presentation.financeui.FinanceFrame;
import presentation.financeui.initui.InitialStockPanel_new.UserModel;
import businesslogic.businessbl.controller.VehicleManagerController;
import businesslogic.financebl.controller.AccountBLController;
import businesslogic.financebl.controller.InitialStockBLController;
import businesslogic.managebl.controller.OrganizationController;
import businesslogic.repertorybl.RepertoryBL;
import businesslogic.userbl.UserBL;
import vo.InitInfoVO;
import vo.UserVO;

public class InitialStockPanel_main extends OperationPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton newButton;
	private JButton detailButton;
	private JLabel next;
	private JLabel previous;
	
	private JLabel function;

	private JTable table;
	int count = 0;
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
		this.controller=controller;
		this.userController=userController;
		this.organizationController=organizationController;
		this.vehicleController=vehicleController;
		this.repertoryController=repertoryController;
		this.accountController=accountController;
		this.financeFrame=parent;
		newButton = new JButton("新建");
		detailButton =new JButton("详情");

		next = new JLabel(">");
		previous = new JLabel("<");
		
		function = new JLabel("期初建账");
		
		refreshTable(controller.getAllInitInfo());
		im=new InitialStockModel(c);
		table=new JTable(im);

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
		

		
		 next.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					nextui();
				}
			});

		 previous.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					previousui();
				}
			});
		
		setLayout(null);
		
		add(newButton);
		add(detailButton);

		add(next);
		add(previous);
		add(function);

		add(table);
//		helper = new LocationHelper(this);
	}

	
	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);

		detailButton.setBounds((int)(width * 21.269132653061224/25),(int)(height * 0.7436399217221135/20),(int)(width *  2.391581632653061 /25),(int)(height *  1.643835616438356/20));
		newButton.setBounds((int)(width * 21.269132653061224/30),(int)(height * 0.7436399217221135/20),(int)(width *  2.391581632653061 /25),(int)(height *  1.643835616438356/20));

		previous.setBounds((int)(width * 10.10969387755102/25),(int)(height * 17.495107632093934/20),(int)(width *  1.211734693877551 /25),(int)(height *  1.36986301369863/20));
		next.setBounds((int)(width * 12.5765306122449/25),(int)(height * 17.495107632093934/20),(int)(width *  1.1798469387755102 /25),(int)(height *  1.36986301369863/20));
		function.setBounds((int)(width * 0.44642857142857145/25),(int)(height * 0.46966731898238745/20),(int)(width *  6.026785714285714 /25),(int)(height *  2.0743639921722115/20));

		table.getTableHeader().setBounds((int)(width * 1.530612244897959/25),(int)(height * 3.6007827788649704/20),(int)(width *  21.07780612244898 /25),(int)(height *  1.1232876712328768/20));
		table.setBounds((int)(width * 1.530612244897959/25),(int)(height * 3.6007827788649704/20)+(int)(height *  1.1232876712328768/20),(int)(width *  21.07780612244898 /25),(int)(height *  13.232876712328768/21));

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
		column1.setPreferredWidth(table.getWidth() * 5/ 10);
		column2.setPreferredWidth(table.getWidth() * 5/ 10);


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
		int row =table.getSelectedRow();
		if(row==-1){
			JOptionPane.showMessageDialog(null, "请选择需要查看的行！", "提示",
					JOptionPane.CLOSED_OPTION);
		}
		else{
			String time = im.getValueAt(row, 0);
			if(time==null){
				JOptionPane.showMessageDialog(null, "请选择需要查看的行！", "提示",
						JOptionPane.CLOSED_OPTION);
			}
			else{
				time=time.substring(0,4)+time.substring(5,7)+time.substring(8,10);
				financeFrame.changePanel(new InitialStockPanel_detail(controller, financeFrame,time));
			}
		}
	}

	public void nextui() {
		count++;
		if(getInitInfoOnThisPage(count)!=null){
			int total=c.size();
			ArrayList<InitInfoVO> temp=getInitInfoOnThisPage(count);
			refreshTable(temp);
			im=new InitialStockModel(c);
			for(int i=0;i<total;i++){
				im.removeRow(0);
			}
			table.repaint();
		}
		else{
			count--;
			return;
		}

	}

	public void previousui() {
		count--;
		if(getInitInfoOnThisPage(count)!=null&&count>=0){
			int total=c.size();
			ArrayList<InitInfoVO> temp=getInitInfoOnThisPage(count);
			refreshTable(temp);
			im=new InitialStockModel(c);
			for(int i=0;i<total;i++){
				im.removeRow(0);
			}
			table.repaint();
		}
		else{
			count++;
			return;
		}

	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		refresh();
	}
	
	
	public void refresh(){
		int temp = c.size();
		refreshTable(controller.getAllInitInfo());
		im = new InitialStockModel(c);
		for(int i=0;i<temp;i++){
			im.removeRow(0);
		}
		table.repaint();
	}
	class InitialStockModel extends AbstractTableModel{

		
		private static final long serialVersionUID = 1L;
		ArrayList<ArrayList<String>> c = new ArrayList<ArrayList<String>>();
		//操作人还要吗
		String head[]={"建账时间","操作员"};
		
		public InitialStockModel(ArrayList<ArrayList<String>> content){
			c=content;
		}
		//行数
		public int getRowCount() {
			// TODO Auto-generated method stub
			return 8;
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return head.length;
		}
		
		public String getValueAt(int row, int col) {
			if(row>c.size()-1){
				return null;
			}
			else{
			return c.get(row).get(col);
			}
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
	
	public void refreshTable(ArrayList<InitInfoVO> vos){
		if(vos==null){
			return;
		}
		else{
		for(InitInfoVO v:vos){
			ArrayList<String> lineInfo=new ArrayList<String>();
			lineInfo.add(v.time.substring(0,4)+"-"+v.time.substring(4,6)+"-"+v.time.substring(6));
			lineInfo.add("CW-00001");
			c.add(lineInfo);
		}
		}
	}
	
	public ArrayList<InitInfoVO> getInitInfoOnThisPage(int num){
		ArrayList<InitInfoVO> vos = controller.getAllInitInfo();
		ArrayList<InitInfoVO> initInfoTemp = new ArrayList<InitInfoVO>();
		if(vos.size()<=num*8||num<0){
   		 return null;
   	 }
   	 else{
   	 for(int i=8*num;i<=8*num+7;i++){
   		 if(vos.size()>i){
   		 initInfoTemp.add(vos.get(i));
   		 }
   	 }
   	 return initInfoTemp;
    }
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
