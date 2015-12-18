package presentation.managerui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListSelectionModel;

//import presentation.commonui.LocationHelper;


import presentation.commonui.OperationPanel;
import vo.PerWageVO;
import vo.BasicSalaryVO;
import vo.CityDistanceVO;
import vo.CostVO;
import type.ProfessionType;
import type.ExpressType;
import businesslogic.managebl.PerWageBL;
import businesslogic.managebl.BasicSalaryBL;
import businesslogic.managebl.CityDistanceBL;
import businesslogic.managebl.CostBL;

public class BasicDataManagePanel extends OperationPanel implements ListSelectionListener{

	private static final long serialVersionUID = 30L;
	
	/*在基础数据修改界面上，只有城市间距离有增删改查操作, 每次工资，基础月薪，运费系数只能被修改 */
	
	ManageFrame manageFrame;
	
	private PerWageBL perWageBL;
	private BasicSalaryBL basicSalaryBL;
	private CityDistanceBL distancesBL;
	private CostBL baseFreightBL;
	
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	
	int pageNum;	//pageNum用来计表格的页数，从0开始计数
	int patternNum;	//patternNum表示是哪一种模式，0表示perWage模式, 1表示basicSalary模式, 2表示cityDistance模式, 3表示cost模式
	boolean isFindDistanceBySinglePattern;	//isFindDistanceBySinglePattern表示是不是在城市距离界面下的单城市搜索模式
	boolean isFindDistanceByBothPattern;	//isFindDistanceBySinglePattern表示是不是在城市距离界面下的双城市搜索模式
	
	private JLabel perWageLabel;
	private JLabel basicSalaryLabel;
	private JLabel distancesLabel;
	private JLabel baseFreightLabel;

	private JTable perWageTable;
	private JTable basicSalaryTable;
	private JTable distancesTable;
	private JTable baseFreightTable;
	
	private JTable currentTable;	//currentTable指要显示的JTable，每次调用changeTable方法重新设置currentTable
	private JTableHeader currentTableHeader;	//currentTableHeader指要显示的JTable的表头，每次需要重新设置，否则不会自动刷新（即使用了UpdateUI或者repaint方法）
	private JLabel messageLabel;
	private DefaultTableCellRenderer tcr;
	
	private JLabel addLabel;
	private JLabel delLabel;
	private JLabel modifyLabel;
	private JTextField queryField;
	private JLabel queryLabel;
	private JLabel refreshLabel;

	private PerWageMessageTableModel perWageModel;
	private BasicSalaryMessageTableModel basicSalaryModel;
	private DistancesMessageTableModel distancesModel;
	private BaseFreightMessageTableModel baseFreightModel;
	
	private JLabel previousPageLabel;
	private JLabel nextPageLabel;

	ListSelectionModel selectionMode;
	
	public BasicDataManagePanel(ManageFrame manageFrame) {
		
		this.manageFrame = manageFrame;
		
		perWageBL = new PerWageBL();
		basicSalaryBL = new BasicSalaryBL();
		distancesBL = new CityDistanceBL();
		baseFreightBL = new CostBL();
		
		perWageLabel = new JLabel("每次工资");
		basicSalaryLabel = new JLabel("基础月薪");
		distancesLabel = new JLabel("城市间距离");
		baseFreightLabel = new JLabel("运费系数");

		addLabel = new JLabel("新增");
		delLabel = new JLabel("删除");
		modifyLabel = new JLabel("修改");
		refreshLabel = new JLabel("刷新");
		queryField = new JTextField();
		queryLabel = new JLabel("查询");
		
		perWageModel = new PerWageMessageTableModel();
		basicSalaryModel = new BasicSalaryMessageTableModel();
		distancesModel = new DistancesMessageTableModel();
		baseFreightModel = new BaseFreightMessageTableModel();
		messageLabel = new JLabel();
		
		perWageTable = new JTable(perWageModel);
		basicSalaryTable = new JTable(basicSalaryModel);
		distancesTable = new JTable(distancesModel);
		baseFreightTable = new JTable(baseFreightModel);
		
		previousPageLabel = new JLabel("上一页");
		nextPageLabel = new JLabel("下一页");

		
		//加监听
		
		//如果是每次工资界面，新增、删除、查找、翻页Label全部不可点击,将模式改为每次工资模式
		perWageLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				changeTable(perWageTable);
				addLabel.setEnabled(false);
				delLabel.setEnabled(false);
				queryField.setEnabled(false);
				queryLabel.setEnabled(false);
				previousPageLabel.setEnabled(false);
				nextPageLabel.setEnabled(false);
				patternNum = 0;
				isFindDistanceBySinglePattern = false;
				isFindDistanceByBothPattern = false;
			}
		});
		
		//如果是基础月薪界面，新增、删除、查找、翻页Label全部不可点击,将模式改为基础月薪模式
		basicSalaryLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				changeTable(basicSalaryTable);
				addLabel.setEnabled(false);
				delLabel.setEnabled(false);
				queryField.setEnabled(false);
				queryLabel.setEnabled(false);
				previousPageLabel.setEnabled(false);
				nextPageLabel.setEnabled(false);
				patternNum = 1;
				isFindDistanceBySinglePattern = false;
				isFindDistanceByBothPattern = false;
			}
		});
		
		//如果是城市间距离界面，新增、删除、查找、翻页Label全部可点击,将模式改为城市间距离模式
		distancesLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				changeTable(distancesTable);
				addLabel.setEnabled(true);
				delLabel.setEnabled(true);
				queryField.setEnabled(true);
				queryLabel.setEnabled(true);
				previousPageLabel.setEnabled(true);
				nextPageLabel.setEnabled(true);
				patternNum = 2;
				isFindDistanceBySinglePattern = true;
				isFindDistanceByBothPattern = true;
			}
		});
		
		//如果是运费系数界面，新增、删除、查找、翻页Label全部不可点击,将模式改为运费系数模式
		baseFreightLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				changeTable(baseFreightTable);
				addLabel.setEnabled(false);
				delLabel.setEnabled(false);
				queryField.setEnabled(false);
				queryLabel.setEnabled(false);
				previousPageLabel.setEnabled(false);
				nextPageLabel.setEnabled(false);
				patternNum = 3;
				isFindDistanceBySinglePattern = false;
				isFindDistanceByBothPattern = false;
			}
		});
		
		
		//由于只有城市间距离有新增功能，所以跳转到新增城市间距离界面
		addLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				addDistanceUI();
			}
		});
		
		//由于只有城市间距离有删除功能，所以删除被选中的城市间距离
		delLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int chosenRow = -1; 
				String cityA = "";
				String cityB = "";
				
				if(isFindDistanceBySinglePattern){
					 chosenRow = distancesTable.getSelectedRow();
					 cityA = (String)(distancesModel.getValueAt(chosenRow, 0));
					 cityB = (String)(distancesModel.getValueAt(chosenRow, 1));
				}
				else if(isFindDistanceByBothPattern){
					 chosenRow = distancesTable.getSelectedRow();
					 cityA = (String)(distancesModel.getValueAt(chosenRow, 0));
					 cityB = (String)(distancesModel.getValueAt(chosenRow, 1));
				}
				else{
					chosenRow = distancesTable.getSelectedRow();
					 cityA = (String)(distancesModel.getValueAt(chosenRow, 0));
					 cityB = (String)(distancesModel.getValueAt(chosenRow, 1));
				}

				if(chosenRow != -1){
					distancesBL.deleteCityDistance(new CityDistanceVO(cityA, cityB, 0));
					distancesTable.updateUI();//刷新
				}
				else{
        			warnning("没有选择要删除的城市间距离");
        		}
			}
		});
		
		modifyLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(patternNum == 0)			//修改每次工资
					modifyPerWageUI();
				else if(patternNum == 1)	//修改基础月薪
					modifyBasicSalaryUI();
				else if(patternNum == 2)	//修改城市距离
					modifyDistancesUI();
				else						//修改运费系数
					modifyBaseFreightUI();	
			}
		});
		
		//由于只有城市间距离有查找功能，所以查找城市间距离
		queryLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String queryStr = queryField.getText();
				if(queryStr.equals("") || queryStr.equals("城市名称"))
					allDistanceUI();
				else if(isSingleSearch(queryStr))	//单城市的城市距离搜索
					singleCityDistanceUI();
				else
					bothCityDistanceUI();	//双城市的城市距离搜索
			}
		});
				
		refreshLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				updateUI();
			}
		});

		
		//由于只有城市间距离有查找功能，其他基础数据数据量较小，不需要涉及到翻页功能，所以只调用了城市间距离信息Table的setModel和setBaseInfo方法
		previousPageLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (pageNum == 0)
					return;
				else {
					pageNum--;
					distancesTable.setModel(new DistancesMessageTableModel());
					setBaseInfo(distancesTable);
				}
			}
		});
		
		nextPageLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (pageNum >= (distancesBL.showAllCityDistances().size() - 2) / 10)
					return;
				else {
					pageNum++;
					distancesTable.setModel(new DistancesMessageTableModel());
					setBaseInfo(distancesTable);
				}
			}
		});
		
		
		//把组件加到Panel上
		setLayout(null);
		
		add(perWageLabel);
		add(basicSalaryLabel);
		add(distancesLabel);
		add(baseFreightLabel);
		
		add(addLabel);
		add(delLabel);
		add(modifyLabel);
		add(refreshLabel);
		add(queryField);
		add(queryLabel);
		
		add(perWageTable.getTableHeader());
		add(perWageTable);
		add(basicSalaryTable.getTableHeader());
		add(basicSalaryTable);
		add(distancesTable.getTableHeader());
		add(distancesTable);
		add(baseFreightTable.getTableHeader());
		add(baseFreightTable);
		
		add(previousPageLabel);
		add(nextPageLabel);
		
		setVisible(true);
		
		
		//一开始先显示每次工资
		pageNum = 0;										//页数从0开始计数
		patternNum = 0;										//设置为每次工资模式
		isFindDistanceBySinglePattern = false;				//不是城市间距离下的双城市搜索模式，置为false
		isFindDistanceByBothPattern = false;				//不是城市间距离下的单城市搜索模式，置为false
		currentTable = perWageTable;						//把currentTable设为每次工资Table
		currentTableHeader = perWageTable.getTableHeader();	//把currentTableHeader设为每次工资Table对应的TableHeader
		addLabel.setEnabled(false);							//新增不可以被点击
		delLabel.setEnabled(false);							//删除不可以被点击
		queryField.setEnabled(false);						//查询域不可以输入
		queryLabel.setEnabled(false);						//查询不可以被点击
		previousPageLabel.setEnabled(false);				//上一页不可以被点击
		nextPageLabel.setEnabled(false);					//下一页不可以被点击
		
		
		//颜色、边界、位置、JTable上信息显示设置
		setTableColor();
		setBorder();
		setCmpLocation(currentTable);
        setBaseInfo(currentTable);
        setInfos(currentTable);
	}
	
	
	//覆盖Selection事件被触发后原有的方法
	public void valueChanged(ListSelectionEvent lse){
     	//System.out.println("你选了第"+allInfoTable.getSelectedRow()+"行");
	}
	
	
	//给表加颜色，隔一行一个颜色
	public void setTableColor(){
			
		tcr = new DefaultTableCellRenderer(){
		
			private static final long serialVersionUID = 6L;

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
		
	//设置边界
	public void setBorder(){
		previousPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nextPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		messageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	//设置位置
	public void setBounds(int x, int y, int width, int height, JTable table) {
		super.setBounds(x, y, PANEL_WIDTH, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation(table);
	}
	
	//因为有不同类型的JTable传进来，每次重新设置表头的值，位置和表的位置
	public void setCmpLocation(JTable table){
		
		perWageLabel.setBounds((int) (PANEL_WIDTH * 1.9526248399487836 / 25), (int) (PANEL_HEIGHT * 1.5625 / 20),
				(int) (PANEL_WIDTH * 3.1690140845070425 / 25), (int) (PANEL_HEIGHT * 1.4285714285714286 / 20));
		basicSalaryLabel.setBounds((int) (PANEL_WIDTH * 7.458386683738796 / 25), (int) (PANEL_HEIGHT * 1.5625 / 20),
				(int) (PANEL_WIDTH * 3.1690140845070425 / 25), (int) (PANEL_HEIGHT * 1.4285714285714286 / 20));
		distancesLabel.setBounds((int) (PANEL_WIDTH * 12.900128040973112 / 25), (int) (PANEL_HEIGHT * 1.5625 / 20),
				(int) (PANEL_WIDTH * 3.1370038412291934 / 25), (int) (PANEL_HEIGHT * 1.4285714285714286 / 20));
		baseFreightLabel.setBounds((int) (PANEL_WIDTH * 18.405889884763123 / 25), (int) (PANEL_HEIGHT * 1.5625 / 20),
				(int) (PANEL_WIDTH * 3.1370038412291934 / 25), (int) (PANEL_HEIGHT * 1.4285714285714286 / 20));
		
		addLabel.setBounds((int) (PANEL_WIDTH * 3.12932138284251 / 25), (int) (PANEL_HEIGHT * 4.419642857142857 / 20),
				(int) (PANEL_WIDTH * 1.5364916773367479 / 25), (int) (PANEL_HEIGHT * 2.142857142857143 / 20));
		delLabel.setBounds((int) (PANEL_WIDTH * 6.482714468629961 / 25), (int) (PANEL_HEIGHT * 4.419642857142857 / 20),
				(int) (PANEL_WIDTH * 1.5364916773367479 / 25), (int) (PANEL_HEIGHT * 2.142857142857143 / 20));
		modifyLabel.setBounds((int) (PANEL_WIDTH * 9.836107554417413 / 25), (int) (PANEL_HEIGHT * 4.419642857142857 / 20),
				(int) (PANEL_WIDTH * 1.5364916773367479 / 25), (int) (PANEL_HEIGHT * 2.142857142857143 / 20));
		refreshLabel.setBounds((int) (PANEL_WIDTH * 12.836107554417413 / 25), (int) (PANEL_HEIGHT * 4.419642857142857 / 20),
				(int) (PANEL_WIDTH * 1.5364916773367479 / 25), (int) (PANEL_HEIGHT * 2.142857142857143 / 20));
		queryField.setBounds((int) (PANEL_WIDTH * 16.005121638924457 / 25), (int) (PANEL_HEIGHT * 4.821428571428571 / 20),
				(int) (PANEL_WIDTH * 4.3854033290653005 / 25), (int) (PANEL_HEIGHT * 1.3392857142857142 / 20));
		queryLabel.setBounds((int) (PANEL_WIDTH * 21.286811779769526 / 25), (int) (PANEL_HEIGHT * 4.821428571428571 / 20),
				(int) (PANEL_WIDTH * 1.6005121638924455 / 25), (int) (PANEL_HEIGHT * 1.2946428571428572 / 20));
		
		previousPageLabel.setBounds((int) (PANEL_WIDTH * 17.6696542893726 / 25), (int) (PANEL_HEIGHT * 17.857142857142858 / 20),
				(int) (PANEL_WIDTH * 1.120358514724712 / 25), (int) (PANEL_HEIGHT * 1.5178571428571428 / 20));
		nextPageLabel.setBounds((int) (PANEL_WIDTH * 20.774647887323944 / 25), (int) (PANEL_HEIGHT * 17.857142857142858 / 20),
				(int) (PANEL_WIDTH * 1.088348271446863 / 25), (int) (PANEL_HEIGHT * 1.5178571428571428 / 20));

		table.setBounds((int) (PANEL_WIDTH * 2.0166453265044813 / 25), (int) (PANEL_HEIGHT * 7.321428571428571 / 20),
				(int) (PANEL_WIDTH * 21.862996158770805 / 25), (int) (PANEL_HEIGHT * 9.821428571428571 / 20));
		
		currentTableHeader.setColumnModel(table.getColumnModel());
		currentTableHeader.setBounds((int) (PANEL_WIDTH * 2.0166453265044813 / 25), (int) (PANEL_HEIGHT * 6.321428571428571 / 20),
				(int) (PANEL_WIDTH * 21.862996158770805 / 25), (int) (PANEL_HEIGHT * 1 / 20));
		
		table.setBackground(getBackground());
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		currentTableHeader.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		table.setRowSelectionAllowed(true);
        selectionMode = table.getSelectionModel();
        selectionMode.addListSelectionListener(this);
	}
	
	//重设要显示的表
	private void changeTable(JTable table){
		remove(currentTable);
		currentTable = table;
		add(currentTable);
		setCmpLocation(table);
		setBaseInfo(currentTable);
        setInfos(currentTable);
		repaint();
	}
	
	//设置Table初始显示的信息
	private void setBaseInfo(JTable table) {

		//设置成不可编辑不可改变位置，大小
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
		table.setRowHeight((table.getHeight() - table.getTableHeader().getHeight()) / 10);
		
		tcr.setHorizontalAlignment(JLabel.CENTER);
		
		//判断是哪一种类型的表，如果是城市距离的表，列设置为3列
		if(table.equals(distancesTable)){
			TableColumn column0 = table.getColumnModel().getColumn(0);
			TableColumn column1 = table.getColumnModel().getColumn(1);
			TableColumn column2 = table.getColumnModel().getColumn(2);
			
			column0.setPreferredWidth(table.getWidth() / 3);
			column1.setPreferredWidth(table.getWidth() / 3);
			column2.setPreferredWidth(table.getWidth() / 3);

			column0.setCellRenderer(tcr);
			column1.setCellRenderer(tcr);
			column2.setCellRenderer(tcr);
		}
		//如果是其他3种类型的表，列设置为2列
		else{
			TableColumn column0 = table.getColumnModel().getColumn(0);
			TableColumn column1 = table.getColumnModel().getColumn(1);
			
			column0.setPreferredWidth(table.getWidth() / 2);
			column1.setPreferredWidth(table.getWidth() / 2);

			column0.setCellRenderer(tcr);
			column1.setCellRenderer(tcr);
		}
	}

	// 设置载入动态的内容
	private void setInfos(JTable table) {
		if(table.equals(perWageTable)){
			if (perWageBL.showAllPerWages().size() != 0)
				messageLabel.setText("职业: "+"  每次工资 : ");
			else
				messageLabel.setText("职业: 无"+"  每次工资 : 无");
		}
		else if(table.equals(basicSalaryTable)){
			if (basicSalaryBL.showAllBasicSalarys().size() != 0)
				messageLabel.setText("职业: "+"  基础月薪 : ");
			else
				messageLabel.setText("职业: 无"+"  基础月薪 : 无");
		}
		else if(table.equals(distancesTable)){
			if (distancesBL.showAllCityDistances().size() != 0)
				messageLabel.setText("城市A: "+"  城市B : "+"   城市距离: ");
			else
				messageLabel.setText("城市A: 无"+"  城市B : 无"+"  城市距离: 无");
		}
		else{
			if (baseFreightBL.showAllCosts().size() != 0)
				messageLabel.setText("快递类型: "+"   运费系数: ");
			else
				messageLabel.setText("快递类型: 无"+"  运费系数: 无");
		}
		messageLabel.setHorizontalAlignment(JLabel.CENTER);
	}
	
	
	
	//新增城市距离界面
	public void addDistanceUI(){
		manageFrame.changePanel(new AddDistancePanel(manageFrame, this));
	}
	
	//修改每次工资界面
	public void modifyPerWageUI(){
		int chosenRow = -1;
		String perWage = "";
		
		chosenRow = perWageTable.getSelectedRow();
		
		if(chosenRow!=-1){
			perWage = (String)(perWageTable.getValueAt(chosenRow, 0));
			manageFrame.changePanel(new ModifyPerWagePanel(manageFrame, this, perWage));
		}
		else{
			warnning("没有选择要修改的每次工资");
		}
	}
	
	//修改基础月薪界面
	public void modifyBasicSalaryUI(){
		int chosenRow = -1;
		String basicSalary = "";
		
		chosenRow = basicSalaryTable.getSelectedRow();
		
		if(chosenRow!=-1){
			basicSalary = (String)(basicSalaryTable.getValueAt(chosenRow, 0));
			manageFrame.changePanel(new ModifyBasicSalaryPanel(manageFrame, this, basicSalary));
		}
		else{
			warnning("没有选择要修改的基础月薪");
		}
	}
	
	//修改城市距离界面
	public void modifyDistancesUI(){
	
		int chosenRow = -1; 
		String cityA = "";
		String cityB = "";
		
		chosenRow = distancesTable.getSelectedRow();
			 
		if(chosenRow != -1){
			cityA = (String)(distancesModel.getValueAt(chosenRow, 0));
			cityB = (String)(distancesModel.getValueAt(chosenRow, 1));
			manageFrame.changePanel(new ModifyDistancePanel(manageFrame, this, cityA, cityB));
		}
		else{
			warnning("没有选择要修改的城市间距离");
		}
		
	}
	
	//修改运费系数界面
	public void modifyBaseFreightUI(){
		int chosenRow = -1;
		String expressType = "";
		
		chosenRow = baseFreightTable.getSelectedRow();
		
		if(chosenRow!=-1){
			expressType = (String)(baseFreightTable.getValueAt(chosenRow, 0));
			manageFrame.changePanel(new ModifyBaseFreightPanel(manageFrame, this, expressType));
		}
		else{
			warnning("没有选择要修改的运费系数");
		}
	}
	
	//显示全部城市间距离页面
	public void allDistanceUI(){
		System.out.println("全部城市间距离");
	}
	
	//单城市搜索下的城市间距离页面
	public void singleCityDistanceUI(){
		isFindDistanceBySinglePattern = true;
		isFindDistanceByBothPattern = false;
		System.out.println("单城市搜索下的城市间距离");
	}
	
	//双城市搜索下的城市间距离页面
	public void bothCityDistanceUI(){
		isFindDistanceBySinglePattern = false;
		isFindDistanceByBothPattern = true;
		System.out.println("双城市搜索下的城市间距离");
	}
	
	
	//根据不同的职业类型返回职业名，给表去显示
	public String professionName(ProfessionType profession){
		int n = profession.ordinal();
		String[] professionNameList = {"快递员","司机","仓库管理员","营业厅业务员","中转中心业务员","管理员","财务人员","总经理"};
		return professionNameList[n];
	}
	
	//根据不同的快递类型返回快递类型名称，给表去显示
	public String expressCategoryName(ExpressType express){
		int n = express.ordinal();
		String[] expressCategoryNameList = {"经济型","标准型","特快型"};
		return expressCategoryNameList[n];
	}
	
	//判断输入的搜索城市是一个还是多个，从而对应单城市搜索或者是双城市搜索模式
	public boolean isSingleSearch(String queryStr){
		queryStr.trim();
		boolean containsSplitSymbol = queryStr.contains("-");	
		if( (!containsSplitSymbol) || (queryStr.endsWith("-")))		//如果不含有分隔符，或者含有分隔符，但是分隔符后面没有第二个城市名称，即为单城市搜索模式
			return true;
		else 
			return false;
	}
	
	//出现错误时给用户的提示信息
	public void warnning(String message) {
		JOptionPane.showMessageDialog(null, message, "出错啦！( ▼-▼ )", JOptionPane.ERROR_MESSAGE);
	}
	
	
	
	//每次工资对应的表的Model
	public class PerWageMessageTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 4L;
		
		Vector<PerWageVO> perWageVector;
		
		public int getRowCount() {
			return 10;
		}

		public int getColumnCount() {
			return 2;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			
			perWageVector = new Vector<PerWageVO>();
			for(int i=0;i<perWageBL.showAllPerWages().size();i++){
				perWageVector.add(perWageBL.showAllPerWages().get(i));
			}
			
			int index = pageNum * 10 + rowIndex;

				if (index > perWageVector.size()-1)
					return null;
				PerWageVO perWagevo = (PerWageVO)perWageVector.get(index);
	
				if (perWagevo != null) {
					if(columnIndex==0)
						return professionName(perWagevo.profession);
					else 
						return perWagevo.perWage;
				} 
				else
					return null;
		}

		public String getColumnName(int num) {
			if (num == 0)
				return "职位";
			else 
				return "每次工资(元)";
		}

	}
	
	
	//基础月薪对应的表的Model
	public class BasicSalaryMessageTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = -4L;
		
		Vector<BasicSalaryVO> basicSalaryVector;
		
		public int getRowCount() {
			return 10;
		}

		public int getColumnCount() {
			return 2;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			
			basicSalaryVector = new Vector<BasicSalaryVO>();
			for(int i=0;i<basicSalaryBL.showAllBasicSalarys().size();i++){
				basicSalaryVector.add(basicSalaryBL.showAllBasicSalarys().get(i));
			}
			
			int index = pageNum * 10 + rowIndex;
			
			if (index > basicSalaryVector.size()-1)
				return null;
			BasicSalaryVO basicSalaryvo = (BasicSalaryVO)basicSalaryVector.get(index);
	
			if (basicSalaryvo != null){
				if(columnIndex==0)
					return professionName(basicSalaryvo.profession);
				else
					return basicSalaryvo.basicSalary;
			}
			else{
				return null;
			}
		}

		public String getColumnName(int num) {
			if (num == 0)
				return "职位";
			else 
				return "基础月薪(元)";
		}

	}

	
	//城市距离对应的表的Model
	public class DistancesMessageTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 4L;
		
		Vector<CityDistanceVO> cityDistanceVector;
		
		public int getRowCount() {
			return 10;
		}
	
		public int getColumnCount() {
			return 3;
		}
	
		public Object getValueAt(int rowIndex, int columnIndex) {
			
			cityDistanceVector = new Vector<CityDistanceVO>();
			for(int i=0;i<distancesBL.showAllCityDistances().size();i++){
				cityDistanceVector.add(distancesBL.showAllCityDistances().get(i));
			}
			
			int index = pageNum * 10 + rowIndex;
	
				if (index > cityDistanceVector.size()-1)
					return null;
				CityDistanceVO cityDistancevo = (CityDistanceVO)cityDistanceVector.get(index);
	
				if (cityDistancevo != null) {
					if(columnIndex==0)
						return cityDistancevo.cityA;
					else if(columnIndex==1)
						return cityDistancevo.cityB;
					else 
						return cityDistancevo.distance;
				} 
				else
					return null;
		}
	
		public String getColumnName(int num) {
			if (num == 0)
				return "城市A";
			else if(num==1)
				return "城市B";
			else 
				return "距离(KM)";
		}
	
	}

	
	//运费系数对应的表的Model
	public class BaseFreightMessageTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = -4L;
		
		Vector<CostVO> costVector;
		
		public int getRowCount() {
			return 10;
		}
	
		public int getColumnCount() {
			return 2;
		}
	
		public Object getValueAt(int rowIndex, int columnIndex) {
	
			costVector = new Vector<CostVO>();
			for(int i=0;i<baseFreightBL.showAllCosts().size();i++){
				costVector.add(baseFreightBL.showAllCosts().get(i));
			}
	
			int index = pageNum * 10 + rowIndex;
			
			if (index > costVector.size()-1)
				return null;
			CostVO costvo = (CostVO)costVector.get(index);
	
			if (costvo != null){
				if(columnIndex==0)
					return expressCategoryName(costvo.category);
				else 
					return costvo.cost;
			}
			else{
				return null;
			}
		}
	
		public String getColumnName(int num) {
			if (num == 0)
				return "快递类型";
			else 
				return "运费系数( 元/(公里*公斤) )";
		}
	
	}

	
}
