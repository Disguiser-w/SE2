package presentation.repertoryui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
//import presentation.commonui.LocationHelper;

import businesslogic.repertorybl.GoodsBL;
import businesslogic.repertorybl.RepertoryBL;
import vo.GoodsVO;
import vo.UserVO;

public class WarehousingPanel extends OperationPanel {

	private static final long serialVersionUID = 92L;

	private RepertoryFrame repertoryFrame;
	private WarehousingMainPanel warehousingMainPanel;
	
	private RepertoryBL repertoryBL;
	private GoodsBL goodsBL;
	
	private MyTextLabel promptLabel;
	
	private MyTable messageTable;
	
	private MyLabel returnLabel;
	
	private ArrayList<GoodsVO> goods;

	private int blockNum;
	
	public WarehousingPanel(RepertoryFrame repertoryFrame, WarehousingMainPanel warehousingMainPanel, UserVO userVO, String[] IDList, int blockNum) {
		
		this.repertoryFrame = repertoryFrame;
		this.warehousingMainPanel = warehousingMainPanel;
		
		repertoryBL = new RepertoryBL(userVO.userID);
		goodsBL = new GoodsBL();
		
		this.blockNum = blockNum;
		
		promptLabel = new MyTextLabel("货物已入库，以下是它们在仓库中的位置信息");
		returnLabel = new MyLabel("返回");
		
		setLayout(null);
		
		add(promptLabel);
		add(returnLabel);

		goods = new ArrayList<GoodsVO>();
		for(String ID : IDList){
			GoodsVO good = goodsBL.findGoodsByID(ID);
			goods.add(good);
		}

		addListener();
		setBaseInfos();
		
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		promptLabel.setBounds((int) (width * 6.979513444302176 / 25),(int) (height * 2.814285714285715 / 20), 
				(int) (width * 10.23303457106274 / 25),(int) (height * 1.3839285714285714 / 20));
		messageTable.setLocationAndSize((int) (width * 1.0243277848911652 / 25), (int) (height * 4.401785714285714 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 12.035714285714286 / 20));
		returnLabel.setBounds((int) (width * 10.979513444302176 / 25),(int) (height * 17.814285714285715 / 20), 
				(int) (width * 2.23303457106274 / 25),(int) (height * 1.3839285714285714 / 20));
	}
	

	public void addListener(){
		returnLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				repertoryFrame.toMainPanel();
				warehousingMainPanel.updateui(1);
			}
		});
	}
	
	private void setBaseInfos() {
		String[] head = new String[]{"货物编号", "所在区名", "排号", "架号", "位号"};
		int[] widths = {150, 120, 108, 108, 107};
		
		messageTable = new MyTable(head, getInfos(), widths, true);
		add(messageTable);
	}

	private ArrayList<String[]> getInfos(){
		ArrayList<String[]> infos = new ArrayList<String[]>();
		for(GoodsVO goodsvo: goods){
			String location = repertoryBL.searchVacantLocation(blockNum);
			String[] locationParts = location.split(" ");
			infos.add(new String[]{goodsvo.Order_ID, blockName(blockNum), locationParts[0], locationParts[1], locationParts[2]});
			//顺便帮它们入库
			repertoryBL.enterRepertory(goodsvo.Order_ID, blockNum, Integer.parseInt(locationParts[0]), Integer.parseInt(locationParts[1]), Integer.parseInt(locationParts[2]));
		}
		return infos;
	}
	
	
	public String blockName(int blockInt){
		String[] blockNameList = {"飞机区", "火车区", "汽车区", "机动区"};
		return blockNameList[blockInt];
	}
	
}
