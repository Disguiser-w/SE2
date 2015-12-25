package presentation.repertoryui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import businesslogic.repertorybl.GoodsBL;
import businesslogic.repertorybl.RepertoryBL;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import vo.GoodsVO;
import vo.UserVO;

public class CreateReceiptPanel extends OperationPanel{

	private static final long serialVersionUID = 4635802896951041367L;
	
	private RepertoryBL repertoryBL;
	private GoodsBL goodsBL;
	
	private String repertoryID;
	
	private MyTextLabel promptLabel;
	private MyLabel createEnterReceiptLabel;
	private MyLabel createLeaveReceiptLabel;
	
	private MyTable messageTable;
	private MyTable currentTable;
	int patternNum;
	
	private MyLabel createReceiptLabel;
	
	private ArrayList<GoodsVO> goods;
	
	private int tableWidth;
	private int tableHeight;
	
	private String[] goodsIDList;
	private String[] timeList;
	
	public CreateReceiptPanel(UserVO userVO){
		
		repertoryBL = new RepertoryBL(userVO.userID);
		goodsBL = new GoodsBL();
		
		repertoryID = userVO.organization;
		
		String nowTime = RepertoryBL.getTimeNow();
		promptLabel = new MyTextLabel("从上次生成出/入库单据的时间到现在: "+nowTime);
		createEnterReceiptLabel = new MyLabel("入库货物");
		createLeaveReceiptLabel = new MyLabel("出库货物");
		createReceiptLabel = new MyLabel("生成单据");
		
		setLayout(null);
		
		add(promptLabel);
		add(createEnterReceiptLabel);
		add(createLeaveReceiptLabel);
		add(createReceiptLabel);
		
		addListener();
		setBaseInfos(1);
		
	}

	public void addListener(){
		createEnterReceiptLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				patternNum = 1;
				setBaseInfos(1);
			}	
		});
		
		createLeaveReceiptLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				patternNum = 2;
				setBaseInfos(2);
			}	
		});
		
		createReceiptLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(patternNum == 1){	//生成入库单
					int returnNum = repertoryBL.AddEnterRepertoryReceipt(goodsIDList, timeList);
					if(returnNum == 0)
						successCreateReceipt(1);
				}
				else{		//生成出库单
					int returnNum = repertoryBL.AddLeaveRepertoryReceipt(goodsIDList, timeList);
					if(returnNum == 0)
						successCreateReceipt(2);
				}
			}	
		});
		
	}
	
	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		
		tableWidth = width;
		tableHeight = height;
		
		promptLabel.setBounds((int) (width * 6.370422535211268 / 25), (int) (height * 1.2053571428571428 / 20),
				(int) (width * 12.6645326504481435 / 25), (int) (height * 1.2053571428571428 / 20));
		createEnterReceiptLabel.setBounds((int) (width * 6.070422535211268 / 25), (int) (height * 2.80053571428571428 / 20),
				(int) (width * 4.6645326504481435 / 25), (int) (height * 1.2053571428571428 / 20));
		createLeaveReceiptLabel.setBounds((int) (width * 13.070422535211268 / 25), (int) (height * 2.80053571428571428 / 20),
				(int) (width * 4.6645326504481435 / 25), (int) (height * 1.2053571428571428 / 20));
		messageTable.setLocationAndSize((int) (width * 1.0243277848911652 / 25), (int) (height * 4.5053571428571428 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 12.53571428571428 / 20));
		createReceiptLabel.setBounds((int) (width * 10.979513444302176 / 25), (int) (height * 17.410714285714285 / 20),
				(int) (width * 2.592829705505762 / 25), (int) (height * 1.4285714285714286 / 20));
	}
	
	private void setBaseInfos(int patternNum){
		String[] head;
		if(patternNum == 1)
			head = new String[]{"订单号","进入本仓库时间"};
		else
			head = new String[]{"订单号","离开本仓库时间"};
		
		int[] widths = {296, 297};
		
		currentTable = new MyTable(head, getInfos(patternNum), widths, true);
		currentTable.setAllSelected(true);
		currentTable.setEnabled(false);
		changeTable(currentTable);
	}
	
	private ArrayList<String[]> getInfos(int patterNum){
		ArrayList<String[]> infos = new ArrayList<String[]>();
		
		if(patternNum == 1){
			goods = repertoryBL.getEnterRepertoryGoods();
			
			int size = goods.size();
			goodsIDList = new String[size];
			timeList = new String[size];
			
			int i = 0;			
			for(GoodsVO goodsvo: goods){
				goodsIDList[i] = goodsvo.Order_ID;
				timeList[i] = goodsBL.getEnterSpecificRepertoryDate(goodsvo.Order_ID, repertoryID);
				i++;
				infos.add(new String[]{goodsvo.Order_ID, goodsBL.getEnterSpecificRepertoryDate(goodsvo.Order_ID, repertoryID)});
			}
		}
		else{
			goods = repertoryBL.getLeaveRepertoryGoods();
			System.out.println(goods.size());
			
			int size = goods.size();
			goodsIDList = new String[size];
			timeList = new String[size];
			
			int i = 0;	
			for(GoodsVO goodsvo: goods){
				goodsIDList[i] = goodsvo.Order_ID;
				timeList[i] = goodsBL.getLeaveSpecificRepertoryDate(goodsvo.Order_ID, repertoryID);
				i++;
				infos.add(new String[]{goodsvo.Order_ID, goodsBL.getLeaveSpecificRepertoryDate(goodsvo.Order_ID, repertoryID)});
			}
		}
		
		return infos;
	}
	
	
	public void changeTable(MyTable currentTable){
		if(messageTable != null){
			remove(messageTable);
		}
		messageTable = currentTable;
		messageTable.setLocationAndSize((int) (tableWidth * 1.0243277848911652 / 25), (int) (tableHeight * 4.5053571428571428 / 20),
				(int) (tableWidth * 22.98335467349552 / 25), (int) (tableHeight * 12.53571428571428 / 20));
		add(messageTable);
		repaint();
		updateUI();
	}
	
	public void successCreateReceipt(int patternNum){
		if(patternNum == 1){
			setBaseInfos(1);
			JOptionPane.showMessageDialog(null, "生成入库单成功(●'◡'●)", "生成单据成功", JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			setBaseInfos(2);
			JOptionPane.showMessageDialog(null, "生成出库单成功(●'◡'●)", "生成单据成功", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
}
