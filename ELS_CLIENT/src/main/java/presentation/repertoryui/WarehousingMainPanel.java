package presentation.repertoryui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import presentation.special_ui.MySearchField;
import vo.GoodsVO;
import vo.UserVO;
import businesslogic.repertorybl.controller.GoodsController;
import businesslogic.repertorybl.controller.RepertoryController;

public class WarehousingMainPanel extends OperationPanel {
	
	private static final long serialVersionUID = 2L;

	private RepertoryFrame repertoryFrame;
	
	private RepertoryController repertoryControl;
	private GoodsController goodsControl;
	
	private UserVO stockMan;
	
	private MyTextLabel chooseLabel;
	private MyLabel planeLabel;
	private MyLabel trainLabel;
	private MyLabel vehicleLabel;
	private MyLabel motorLabel;
	private int blockNum;
	
	private MyLabel enterRepertoryLabel;
	private MyLabel detailedInfoLabel;
	private MySearchField searchField;

	public MyTable warehousingMessageTable;
	
	private ArrayList<GoodsVO> goods;
	
	private int selectedIndex;
	
	private boolean isSearchPattern;
	private String keyword;
	
	public WarehousingMainPanel(RepertoryFrame repertoryFrame, RepertoryController repertoryController, GoodsController goodsController, UserVO uservo) {
		
		this.repertoryFrame = repertoryFrame;
		
		this.repertoryControl = repertoryController;
		this.goodsControl = goodsController;
		
		stockMan = uservo;
		
		detailedInfoLabel = new MyLabel("详细物流信息");
		searchField = new MySearchField();
		
		chooseLabel = new MyTextLabel("选择分区");
		planeLabel = new MyLabel("飞机区");
		trainLabel = new MyLabel("火车区");
		vehicleLabel = new MyLabel("汽车区");
		motorLabel = new MyLabel("机动区");
		blockNum = 0;
		
		String warningStr = repertoryControl.inventoryWarning();
		if(warningStr.contains("0"))
			planeLabel.setEnabled(false);
		if(warningStr.contains("1"))
			trainLabel.setEnabled(false);
		if(warningStr.contains("2"))
			vehicleLabel.setEnabled(false);
		
		enterRepertoryLabel = new MyLabel("入库");
		
		setLayout(null);
	
		add(chooseLabel);
		add(planeLabel);
		add(trainLabel);
		add(vehicleLabel);
		add(motorLabel);
		add(enterRepertoryLabel);
		add(detailedInfoLabel);
		add(searchField);

		isSearchPattern = false;
		addListener();
		setBaseInfos();
	}
	

	public void addListener() {
		
		detailedInfoLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				detailedInfoui();
			}
		});
		
		searchField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				keyword = searchField.getText();
				if(keyword.equals("")){
					isSearchPattern = false;
					return;
				}
				isSearchPattern = true;
				warehousingMessageTable.setInfos(getInfos());
			}
		});
		
		planeLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				blockNum = 0;
			}
		});
		
		trainLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				blockNum = 1;
			}
		});
		
		vehicleLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				blockNum = 2;
			}
		});
		
		motorLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				blockNum = 3;
			}
		});
		
		enterRepertoryLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				enterui();
			}
		});

	}
	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		detailedInfoLabel.setBounds((int) (width * 5.624839948783611 / 25), (int) (height * 1.0607142857142858 / 20),
				(int) (width * 1.8303571428571428 / 25), (int) (height * 1.5003571428571428 / 22));
		searchField.setBounds((int) (width * 17.677336747759284 / 25), (int) (height * 1.0007142857142858 / 20),
				(int) (width * 4.321382842509603 / 25), (int) (height * 1.5003571428571428 / 22));
		warehousingMessageTable.setLocationAndSize((int) (width * 1.0243277848911652 / 25), (int) (height * 2.401785714285714 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 12.535714285714286 / 20));
		chooseLabel.setBounds((int) (width * 1.0409731113956466 / 25), (int) (height * 14.580357142857142 / 20),
				(int) (width * 4.496798975672215 / 25), (int) (height * 1.6071428571428572 / 20));
		planeLabel.setBounds((int) (width * 4.5409731113956466 / 25), (int) (height * 16.080357142857142 / 20),
				(int) (width * 2.496798975672215 / 25), (int) (height * 1.3071428571428572 / 20));
		trainLabel.setBounds((int) (width * 9.246478873239437 / 25), (int) (height * 16.080357142857142 / 20),
				(int) (width * 2.496798975672215 / 25), (int) (height * 1.3071428571428572 / 20));
		vehicleLabel.setBounds((int) (width * 13.945902688860436 / 25), (int) (height * 16.080357142857142 / 20),
				(int) (width * 2.496798975672215 / 25), (int) (height * 1.3071428571428572 / 20));
		motorLabel.setBounds((int) (width * 18.649244558258643 / 25), (int) (height * 16.080357142857142 / 20),
				(int) (width * 2.496798975672215 / 25), (int) (height * 1.3071428571428572 / 20));
		enterRepertoryLabel.setBounds((int) (width * 11.224839948783611 / 25), (int) (height * 17.7607142857142858 / 20),
				(int) (width * 2.8303571428571428 / 25), (int) (height * 1.503571428571428 / 22));
	}
	

	private void setBaseInfos() {
		String[] head = new String[]{"货物编号", "货物费用", "出发地", "目的地"};
		int[] widths = {150, 149, 147, 147};
		
		warehousingMessageTable = new MyTable(head, getInfos(), widths, true);
		add(warehousingMessageTable);
	}

	private ArrayList<String[]> getInfos(){
		if(!isSearchPattern)
			goods = goodsControl.getAllFreeGoods();
		else{
			goods = goodsControl.findFreeGoodsByKeyword(keyword);
		}
		ArrayList<String[]> infos = new ArrayList<String[]>();
		
		for(GoodsVO goodsvo: goods){
			infos.add(new String[]{goodsvo.Order_ID, goodsvo.fee+"", goodsvo.departurePlace, goodsvo.destination});
		}
		return infos;
	}
	
	
	//入库界面
	public void enterui() {
		ArrayList<Integer> selectedIndexs = warehousingMessageTable.getSelectedIndex();
		int size = selectedIndexs.size();
		
		if(size == 0){
			JOptionPane.showMessageDialog(null, "亲爱的仓库管理员，选中某一个或某一些货物后再进行入库操作哦！", "没有选择货物", JOptionPane.WARNING_MESSAGE);
			return;
		}
		else{
			String[] IDStr = new String[size];
			int count = 0;
			for(int i : selectedIndexs){
				String orderID = goods.get(i).Order_ID;
				IDStr[count] = orderID;
				count++;
			}
			repertoryFrame.changePanel(new WarehousingPanel(repertoryFrame, this, repertoryControl, goodsControl, this.stockMan, IDStr, blockNum));
		}
	}
	
	//详细信息界面
	public void detailedInfoui(){
		ArrayList<Integer> selectedIndexs = warehousingMessageTable.getSelectedIndex();
		int size = selectedIndexs.size();
		
		if(size == 0){
			JOptionPane.showMessageDialog(null, "亲爱的仓库管理员，选中一个货物后再查看详细信息哦！", "没有选择货物", JOptionPane.WARNING_MESSAGE);
			return;
		}
		else if(size > 1){
			JOptionPane.showMessageDialog(null, "亲爱的仓库管理员，只能选择一个货物查看详细信息哦！", "选择货物过多", JOptionPane.WARNING_MESSAGE);
			return;
		}else{
			selectedIndex = selectedIndexs.get(0);
			GoodsVO goodsvo = goods.get(selectedIndex);
			String orderID = goodsvo.Order_ID;
			repertoryFrame.changePanel(new GoodsDetailedInfoPanel(repertoryFrame, goodsControl, orderID));
		}
	}

	//刷新界面
	public void updateui(boolean updateOthers){
		isSearchPattern = false;
		warehousingMessageTable.setInfos(getInfos());
		if(updateOthers == true){
			repertoryFrame.exwarehousePanel.updateui(false);
			repertoryFrame.inventoryVertificationPanel.updateui(false);
			repertoryFrame.createReceiptPanel.updateui(false);
			repertoryFrame.lookReceiptPanel.updateui(false);
		}
		repaint();
	}
	
	//出现错误时给用户的提示信息
	public void warnning(String message) {
		JOptionPane.showMessageDialog(null, message, "货物信息错误", JOptionPane.ERROR_MESSAGE);
	}

}
