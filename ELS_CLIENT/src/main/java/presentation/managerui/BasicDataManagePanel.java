package presentation.managerui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import businesslogic.financebl.controller.LogDiaryBLController;
import businesslogic.managebl.controller.BasicSalaryController;
import businesslogic.managebl.controller.CityDistanceController;
import businesslogic.managebl.controller.CostController;
import businesslogic.managebl.controller.PerWageController;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import presentation.special_ui.AddLabel;
import presentation.special_ui.DeleteLabel;
import presentation.special_ui.ModifyLabel;
import presentation.special_ui.MySearchField;
import type.AuthorityType;
import type.ExpressType;
import type.ProfessionType;
import type.SalaryPlanType;
import vo.BasicSalaryVO;
import vo.CityDistanceVO;
import vo.CostVO;
import vo.LogDiaryVO;
import vo.PerWageVO;
import vo.UserVO;

public class BasicDataManagePanel extends OperationPanel {

	private static final long serialVersionUID = 30L;
	
	/*在基础数据修改界面上，只有城市间距离有增删改查操作, 每次工资，基础月薪，运费系数只能被修改 */
	
	private ManageFrame manageFrame;
	
	private PerWageController perWageControl;
	private BasicSalaryController basicSalaryControl;
	private CityDistanceController distancesControl;
	private CostController baseFreightControl;
	private LogDiaryBLController logDiaryControl;

	private LogDiaryVO logDiary;
	private String logDiaryTime;
	
	private MyLabel perWageLabel;
	private MyLabel basicSalaryLabel;
	private MyLabel distancesLabel;
	private MyLabel baseFreightLabel;

	private AddLabel addLabel;
	private DeleteLabel delLabel;
	private ModifyLabel modifyLabel;
	private MySearchField searchField;

	private MyTable messageTable;
	private MyTable currentTable;
	private int patternNum;

	private ArrayList<PerWageVO> perWages;
	private ArrayList<BasicSalaryVO> basicSalarys;
	private ArrayList<CityDistanceVO> distances;
	private ArrayList<CostVO> baseFreights;
	
	private int selectedIndex;
	
	private int tableWidth;
	private int tableHeight;
	
	public BasicDataManagePanel(ManageFrame manageFrame, PerWageController perWageController, BasicSalaryController basicSalaryController, 
														CityDistanceController cityDistanceController, CostController costController){
		
		this.manageFrame = manageFrame;
		
		perWageControl = perWageController;
		basicSalaryControl = basicSalaryController;
		distancesControl = cityDistanceController;
		baseFreightControl = costController;
		logDiaryControl = new LogDiaryBLController();
		
		perWageLabel = new MyLabel("每次工资");
		basicSalaryLabel = new MyLabel("基础月薪");
		distancesLabel = new MyLabel("城市间距离");
		baseFreightLabel = new MyLabel("运费系数");

		addLabel = new AddLabel("新增城市间距离");
		delLabel = new DeleteLabel("删除城市间距离");
		modifyLabel = new ModifyLabel("修改城市间距离");
		searchField = new MySearchField();
		
		setLayout(null);
		
		add(perWageLabel);
		add(basicSalaryLabel);
		add(distancesLabel);
		add(baseFreightLabel);
		
		add(addLabel);
		add(delLabel);
		add(modifyLabel);
		add(searchField);
		
		perWages = perWageControl.showAllPerWages();
		basicSalarys = basicSalaryControl.showAllBasicSalarys();
		distances = distancesControl.showAllCityDistances();
		baseFreights = baseFreightControl.showAllCosts();
		
		addListener();
		//给setBaseInfos加上参数，不同的int值表示MyTable加载不同内容， 1代表PerWage， 2代表BasicSalary，3代表CityDistance，4代表Cost
		patternNum = 1;
		changeLabelsEnabled(patternNum);
		setBaseInfos(patternNum);
	}
	
	public void addListener(){
		perWageLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				patternNum = 1;
				changeLabelsEnabled(patternNum);
				setBaseInfos(patternNum);
			}
		});
		
		//如果是基础月薪界面，新增、删除、查找、翻页Label全部不可点击,将模式改为基础月薪模式
		basicSalaryLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				patternNum = 2;
				changeLabelsEnabled(patternNum);
				setBaseInfos(patternNum);
			}
		});
		
		//如果是城市间距离界面，新增、删除、查找、翻页Label全部可点击,将模式改为城市间距离模式
		distancesLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				patternNum = 3;
				changeLabelsEnabled(patternNum);
				setBaseInfos(patternNum);
			}
		});
		
		//如果是运费系数界面，新增、删除、查找、翻页Label全部不可点击,将模式改为运费系数模式
		baseFreightLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				patternNum = 4;
				changeLabelsEnabled(patternNum);
				setBaseInfos(patternNum);
			}
		});
		
		
		//由于只有城市间距离有新增功能，所以跳转到新增城市间距离界面
		addLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(patternNum == 1 || patternNum == 2 || patternNum == 4)
					return;
				addui();
			}
		});
		
		//由于只有城市间距离有删除功能，所以删除被选中的城市间距离
		delLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(patternNum == 1 || patternNum == 2 || patternNum == 4)
					return;
				deleteui();
			}
		});
		
		modifyLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(patternNum == 1)			//修改每次工资
					modifyPerWageUI();
				else if(patternNum == 2)	//修改基础月薪
					modifyBasicSalaryUI();
				else if(patternNum == 3)	//修改城市距离
					modifyDistancesUI();
				else						//修改运费系数
					modifyBaseFreightUI();	
			}
		});
		
		searchField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String queryStr = searchField.getText();
				if(queryStr.equals("") || queryStr.equals("城市名称"))
					allDistanceUI();
				else if(isSingleSearch(queryStr))	//单城市的城市距离搜索
					singleCityDistanceUI();
				else
					bothCityDistanceUI();	//双城市的城市距离搜索
			}
		});

	}
	
	
	public void setBounds(int x, int y, int width, int height){
		super.setBounds(x, y, width, height);
		
		this.tableWidth = width;
		this.tableHeight = height;
		 
		perWageLabel.setBounds((int) (width * 1.9526248399487836 / 25), (int) (height * 1.5625 / 20),
				(int) (width * 3.1690140845070425 / 25), (int) (height * 1.4285714285714286 / 20));
		basicSalaryLabel.setBounds((int) (width * 7.458386683738796 / 25), (int) (height * 1.5625 / 20),
				(int) (width * 3.1690140845070425 / 25), (int) (height * 1.4285714285714286 / 20));
		distancesLabel.setBounds((int) (width * 12.900128040973112 / 25), (int) (height * 1.5625 / 20),
				(int) (width * 3.1370038412291934 / 25), (int) (height * 1.4285714285714286 / 20));
		baseFreightLabel.setBounds((int) (width * 18.405889884763123 / 25), (int) (height * 1.5625 / 20),
				(int) (width * 3.1370038412291934 / 25), (int) (height * 1.4285714285714286 / 20));
		addLabel.setBounds((int) (width * 3.12932138284251 / 25), (int) (height * 4.019642857142857 / 20),
				(int) (width * 1.8303571428571428 / 25), (int) (height * 1.8303571428571428 / 22));
		delLabel.setBounds((int) (width * 6.482714468629961 / 25), (int) (height * 4.019642857142857 / 20),
				(int) (width * 1.8303571428571428 / 25), (int) (height * 1.8303571428571428 / 22));
		modifyLabel.setBounds((int) (width * 9.836107554417413 / 25), (int) (height * 4.019642857142857 / 20),
				(int) (width * 1.8303571428571428 / 25), (int) (height * 1.8303571428571428 / 22));
		searchField.setBounds((int) (width * 16.005121638924457 / 25), (int) (height * 4.219642857142857 / 20),
				(int) (width * 4.321382842509603 / 25), (int) (height * 1.5303571428571428 / 22));
		messageTable.setLocationAndSize((int) (width * 2.0166453265044813 / 25), (int) (height * 6.521428571428571 / 20),
				(int) (width * 21.862996158770805 / 25), (int) (height * 11.821428571428571 / 20));
	}
	
	
	private void setBaseInfos(int patternNum) {
		String[] head;
		int[] widths;
		
		switch(patternNum){
			case 1:	head = new String[]{"职业类型", "每次工资（元）"};
					widths = new int[]{275, 285};
					currentTable = new MyTable(head, getInfos(1), widths, true);
					break;
			case 2: head = new String[]{"职业类型", "基础月薪（元）"};
					widths = new int[]{275, 285};
					currentTable = new MyTable(head, getInfos(2), widths, true);
					break;
			case 3: head = new String[]{"城市A", "城市B", "城市间距离（公里）"};
					widths = new int[]{200, 200, 160};
					currentTable = new MyTable(head, getInfos(3), widths, true);
					break;
			case 4: head = new String[]{"快递类型", "运费系数（元/（千克*公里））"};
			 		widths = new int[]{275, 285};
			 		currentTable = new MyTable(head, getInfos(4), widths, true);
			 		break;
		}
		
		changeTable(currentTable);
	}

	private ArrayList<String[]> getInfos(int patternNum){
		ArrayList<String[]> infos = new ArrayList<String[]>();
		
		switch(patternNum){
			case 1: for(PerWageVO perWagevo: perWages){
						infos.add(new String[]{professionName(perWagevo.profession), perWagevo.perWage+""});
					}
					break;
			case 2: for(BasicSalaryVO basicSalaryvo: basicSalarys){
						infos.add(new String[]{professionName(basicSalaryvo.profession), basicSalaryvo.basicSalary+""});
					}
					break;
			case 3: for(CityDistanceVO cityDistancevo: distances){
						infos.add(new String[]{cityDistancevo.cityA, cityDistancevo.cityB, cityDistancevo.distance+""});
					}
					break;
			case 4: for(CostVO costvo: baseFreights){
						infos.add(new String[]{expressCategoryName(costvo.category), costvo.cost+""});
					}
			 		break;
		}
		
		return infos;
	}
	
	private void changeLabelsEnabled(int patternNum){
		if(patternNum == 1 || patternNum == 2 || patternNum == 4){
			addLabel.setEnabled(false);
			delLabel.setEnabled(false);
			searchField.setText("");
			searchField.setEnabled(false);
		}
		else if(patternNum == 3){
			addLabel.setEnabled(true);
			delLabel.setEnabled(true);
			searchField.setText("城市名");
			searchField.setEnabled(true);
		}
	}
	
	private boolean rightChoose(){
		ArrayList<Integer> selectedIndexs = messageTable.getSelectedIndex();
		int size = selectedIndexs.size();
		
		if(size == 0){
			JOptionPane.showMessageDialog(null, "亲爱的总经理，选中某一行信息后再修改哦！", "没有选择信息", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if(size > 1){
			JOptionPane.showMessageDialog(null, "亲爱的总经理，只能选中一行信息修改哦！", "选择过多", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		selectedIndex = selectedIndexs.get(0);
		return true;
	}
	
	//新增城市距离界面
	public void addui(){
		manageFrame.changePanel(new AddDistancePanel(manageFrame, this));
		
		logDiaryTime = getTimeNow();
		logDiary = new LogDiaryVO(logDiaryTime, new UserVO("刘钦", "JL-00001", "", 
				ProfessionType.manager, "", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0), "新增一个城市距离");
		logDiaryControl.addLogDiary(logDiary, logDiaryTime);
	}
	
	//删除城市距离界面
	public void deleteui(){
		ArrayList<Integer> selectedIndexs = messageTable.getSelectedIndex();
		int size = selectedIndexs.size();
		
		if(size == 0){
			JOptionPane.showMessageDialog(null, "亲爱的总经理，选中某一项或多项后再删除哦！", "没有选择", JOptionPane.WARNING_MESSAGE);
			return;
		}
		else if(size == 1){
			if(JOptionPane.showConfirmDialog(null, "确认删除该信息？", "",
					JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
				return;
			selectedIndex = selectedIndexs.get(0);
			distancesControl.deleteCityDistance(distances.get(selectedIndex));
			
			logDiaryTime = getTimeNow();
			logDiary = new LogDiaryVO(logDiaryTime, new UserVO("刘钦", "JL-00001", "", 
					ProfessionType.manager, "", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0), "删除一个城市距离");
			logDiaryControl.addLogDiary(logDiary, logDiaryTime);
		}
		else{
			if(JOptionPane.showConfirmDialog(null, "确认删除这些信息？", "",
					JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
				return;
			for(int i: selectedIndexs){
				distancesControl.deleteCityDistance(distances.get(i));
				
				logDiaryTime = getTimeNow();
				logDiary = new LogDiaryVO(logDiaryTime, new UserVO("刘钦", "JL-00001", "", 
						ProfessionType.manager, "", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0), "删除一个城市距离");
				logDiaryControl.addLogDiary(logDiary, logDiaryTime);
			}
		}
		
		distances = distancesControl.showAllCityDistances();
		messageTable.setInfos(getInfos(3));
	}
	
	//修改每次工资界面
	public void modifyPerWageUI(){
		if(rightChoose()){
			PerWageVO vo = perWages.get(selectedIndex);
			manageFrame.changePanel(new ModifyPerWagePanel(manageFrame, this, professionName(vo.profession)));
			
			logDiaryTime = getTimeNow();
			logDiary = new LogDiaryVO(logDiaryTime, new UserVO("刘钦", "JL-00001", "", 
					ProfessionType.manager, "", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0), "修改一个每次工资");
			logDiaryControl.addLogDiary(logDiary, logDiaryTime);
		}
	}
	
	//修改基础月薪界面
	public void modifyBasicSalaryUI(){
		if(rightChoose()){
			BasicSalaryVO vo = basicSalarys.get(selectedIndex);
			manageFrame.changePanel(new ModifyBasicSalaryPanel(manageFrame, this, professionName(vo.profession)));
			
			logDiaryTime = getTimeNow();
			logDiary = new LogDiaryVO(logDiaryTime, new UserVO("刘钦", "JL-00001", "", 
					ProfessionType.manager, "", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0), "修改一个基础月薪");
			logDiaryControl.addLogDiary(logDiary, logDiaryTime);
		}
	}
	
	//修改城市距离界面
	public void modifyDistancesUI(){
		if(rightChoose()){
			CityDistanceVO vo = distances.get(selectedIndex);
			manageFrame.changePanel(new ModifyDistancePanel(manageFrame, this, vo.cityA, vo.cityB));
			
			logDiaryTime = getTimeNow();
			logDiary = new LogDiaryVO(logDiaryTime, new UserVO("刘钦", "JL-00001", "", 
					ProfessionType.manager, "", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0), "修改一个城市距离");
			logDiaryControl.addLogDiary(logDiary, logDiaryTime);
		}
	}
	
	//修改运费系数界面
	public void modifyBaseFreightUI(){
		if(rightChoose()){
			CostVO vo = baseFreights.get(selectedIndex);
			manageFrame.changePanel(new ModifyBaseFreightPanel(manageFrame, this, expressCategoryName(vo.category)));
			
			logDiaryTime = getTimeNow();
			logDiary = new LogDiaryVO(logDiaryTime, new UserVO("刘钦", "JL-00001", "", 
					ProfessionType.manager, "", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0), "修改一个运费系数");
			logDiaryControl.addLogDiary(logDiary, logDiaryTime);
		}
	}
	
	//显示全部城市间距离页面
	public void allDistanceUI(){
		distances = distancesControl.showAllCityDistances();
		setBaseInfos(3);
	}
	
	//单城市搜索下的城市间距离页面
	public void singleCityDistanceUI(){
		String keyCity = searchField.getText();
		distances = distancesControl.findCityDistanceBySingle(keyCity);
		setBaseInfos(3);
	}
	
	//双城市搜索下的城市间距离页面
	public void bothCityDistanceUI(){
		String keyCitys = searchField.getText();
		String[] parts = keyCitys.split("-");
		distances = distancesControl.findCityDistanceByBoth(parts[0], parts[1]);
		setBaseInfos(3);
	}
	
	public void changeTable(MyTable currentTable){
		if(messageTable != null){
			remove(messageTable);
		}
		messageTable = currentTable;
		messageTable.setLocationAndSize((int) (tableWidth * 2.0166453265044813 / 25), (int) (tableHeight * 7.321428571428571 / 20),
				(int) (tableWidth * 21.862996158770805 / 25), (int) (tableHeight * 9.821428571428571 / 20));
		add(messageTable);
		repaint();
		updateUI();
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

	
	//获取当前时间
	public static String getTimeNow(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String timeNow = dateFormat.format(now); 
		return timeNow;
	}
	
	
}
