package presentation.repertoryui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import businesslogic.repertorybl.controller.EnterRepertoryReceiptController;
import businesslogic.repertorybl.controller.LeaveRepertoryReceiptController;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import presentation.special_ui.MySearchField;
import type.ReceiptState;
import vo.EnterRepertoryReceiptVO;
import vo.LeaveRepertoryReceiptVO;
import vo.UserVO;

public class LookReceiptPanel extends OperationPanel {
	
	private static final long serialVersionUID = 2L;

	private RepertoryFrame repertoryFrame;
	
	private EnterRepertoryReceiptController enterRepertoryReceiptControl;
	private LeaveRepertoryReceiptController leaveRepertoryReceiptControl;
	
	private String userID;
	
	private MyLabel lookEnterReceiptLabel;
	private MyLabel lookLeaveReceiptLabel;
	
	int patternNum;
	
	private MyLabel detailedInfoLabel; 
	private MyLabel sendLabel;
	private MySearchField searchField;
	
	private MyTable messageTable;
	private MyTable currentTable;
	
	private ArrayList<EnterRepertoryReceiptVO> errvos;
	private ArrayList<LeaveRepertoryReceiptVO> lrrvos;
	
	private int tableWidth;
	private int tableHeight;
	
	private int selectedIndex;
	
	public LookReceiptPanel(RepertoryFrame repertoryFrame, EnterRepertoryReceiptController enterRepertoryReceiptController,
							LeaveRepertoryReceiptController leaveRepertoryReceiptController, UserVO userVO) {
		
		this.repertoryFrame = repertoryFrame;
		
		userID = userVO.userID;
		
		enterRepertoryReceiptControl = enterRepertoryReceiptController;
		leaveRepertoryReceiptControl = leaveRepertoryReceiptController;
		
		lookEnterReceiptLabel = new MyLabel("入库单");
		lookLeaveReceiptLabel = new MyLabel("出库单");
		
		detailedInfoLabel = new MyLabel("详细信息");
		sendLabel = new MyLabel("发送");
		
		searchField = new MySearchField();
		
		setLayout(null);
		
		add(lookEnterReceiptLabel);
		add(lookLeaveReceiptLabel);
		add(detailedInfoLabel);
		add(searchField);
		add(sendLabel);
		
		patternNum = 1;
		addListener();
		setBaseInfos(patternNum);
		
	}

	public void addListener(){
		lookEnterReceiptLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				patternNum = 1;
				setBaseInfos(1);
			}	
		});
		
		lookLeaveReceiptLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				patternNum = 2;
				setBaseInfos(2);
			}	
		});
		
		detailedInfoLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(patternNum == 1){	//查看入库单详细信息
					detailedui(1);
				}
				else{		//查看出库单详细信息
					detailedui(2);
				}
			}	
		});
		
		searchField.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(patternNum == 1){	//查看入库单详细信息
					searchui(1);
				}
				else{		//查看出库单详细信息
					searchui(2);
				}
			}	
		});
		
		sendLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(patternNum == 1){	//查看入库单详细信息
					sendui(1);
				}
				else{		//查看出库单详细信息
					sendui(2);
				}
			}	
		});
	}
	
	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		
		tableWidth = width;
		tableHeight = height;
		
		lookEnterReceiptLabel.setBounds((int) (width * 2.070422535211268 / 25), (int) (height * 1.20053571428571428 / 20),
				(int) (width * 2.6645326504481435 / 25), (int) (height * 1.2053571428571428 / 20));
		lookLeaveReceiptLabel.setBounds((int) (width * 5.070422535211268 / 25), (int) (height * 1.20053571428571428 / 20),
				(int) (width * 2.6645326504481435 / 25), (int) (height * 1.2053571428571428 / 20));
		detailedInfoLabel.setBounds((int) (width * 12.070422535211268 / 25), (int) (height * 1.20053571428571428 / 20),
				(int) (width * 2.6645326504481435 / 25), (int) (height * 1.2053571428571428 / 20));
		searchField.setBounds((int) (width * 18.070422535211268 / 25), (int) (height * 1.20053571428571428 / 20), 100, 30);
		messageTable.setLocationAndSize((int) (width * 1.0243277848911652 / 25), (int) (height * 2.8053571428571428 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 13.83571428571428 / 20));
		sendLabel.setBounds((int) (width * 10.979513444302176 / 25), (int) (height * 17.410714285714285 / 20),
				(int) (width * 2.592829705505762 / 25), (int) (height * 1.4285714285714286 / 20));
	}
	
	private void setBaseInfos(int patternNum){
		String[] head = new String[]{"单据编号", "创建时间", "单据状态"};
		
		int[] widths = {200, 200, 193};
		
		currentTable = new MyTable(head, getInfos(patternNum), widths, true);
		changeTable(currentTable);
	}
	
	private ArrayList<String[]> getInfos(int patterNum){
		
		errvos = enterRepertoryReceiptControl.findEnterReceiptByCreatorID(userID);
		lrrvos = leaveRepertoryReceiptControl.findLeaveReceiptByCreatorID(userID);
		
		ArrayList<String[]> infos = new ArrayList<String[]>();
		if(patternNum == 1){
			for(EnterRepertoryReceiptVO errvo: errvos){
				infos.add(new String[]{errvo.receiptID, errvo.createTime, stateName(errvo.state)});
			}
		}
		else{
			for(LeaveRepertoryReceiptVO lrrvo: lrrvos){
				infos.add(new String[]{lrrvo.receiptID, lrrvo.createTime, stateName(lrrvo.state)});
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
	
	public void detailedui(int patternNum){
		ArrayList<Integer> selectedIndexs = messageTable.getSelectedIndex();
		
		if(selectedIndexs.size() == 0){
			JOptionPane.showMessageDialog(null, "亲爱的仓库管理员，选中某一个单据后再查看详情哦！", "没有选择单据", JOptionPane.WARNING_MESSAGE);
			return;
		}
		else if(selectedIndexs.size() > 1){
			JOptionPane.showMessageDialog(null, "亲爱的仓库管理员，只能选中一个单据查看详情哦！", "选择单据过多", JOptionPane.WARNING_MESSAGE);
			return;
		}
		selectedIndex = selectedIndexs.get(0);
		if(patternNum == 1){
			String enterReceiptID = errvos.get(selectedIndex).receiptID;
			System.out.println(enterReceiptID);
			repertoryFrame.changePanel(new EnterReceiptDetailedInfoPanel(repertoryFrame, enterRepertoryReceiptControl, enterReceiptID));
		}
		else{
			String leaveReceiptID = lrrvos.get(selectedIndex).receiptID;
			repertoryFrame.changePanel(new LeaveReceiptDetailedInfoPanel(repertoryFrame, leaveRepertoryReceiptControl, leaveReceiptID));
		}
	}
	
	public void searchui(int patternNum){
		String keyword = searchField.getText();
		if(keyword.equals(""))
			return; 
		if(patternNum == 1){
			errvos = enterRepertoryReceiptControl.findEnterReceiptByCreatorAndKeyword(userID, keyword);
			setBaseInfos(1);
		}
		else{
			lrrvos = leaveRepertoryReceiptControl.findLeaveReceiptByCreatorAndKeyword(userID, keyword);
			setBaseInfos(2);
		}
	}

	public void sendui(int patternNum){
		ArrayList<Integer> selectedIndexs = messageTable.getSelectedIndex();
		int returnNum = 0;
		
		if(selectedIndexs.size() == 0){
			JOptionPane.showMessageDialog(null, "亲爱的仓库管理员，选中某一个或多个单据后再发送给总经理审批哦！", "没有选择单据", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(patternNum == 1){
			for(int i: selectedIndexs){
				String receiptID = errvos.get(i).receiptID;
				 returnNum += enterRepertoryReceiptControl.sendEnterReceipt(receiptID);
			}
		}
		else{
			for(int i: selectedIndexs){
				String receiptID = lrrvos.get(i).receiptID;
				returnNum += leaveRepertoryReceiptControl.sendLeaveReceipt(receiptID);
			}
		}
		if(returnNum == 0)
			successSend(patternNum);
		else
			failedSend(patternNum);
	}
	
	public String stateName(ReceiptState receiptState){
		String[] receiptStateNameList = {"草稿", "待审批", "审批通过", "审批未通过"};
		int stateInt = receiptState.ordinal();
		return receiptStateNameList[stateInt];
	}
	
	public void updateui(boolean updateOthers){
		messageTable.setInfos(getInfos(1));
		messageTable.setInfos(getInfos(2));
		messageTable.setInfos(getInfos(patternNum));
		if(updateOthers == true){
			repertoryFrame.warehousingMainPanel.updateui(false);
			repertoryFrame.warehousingMainPanel.updateui(false);
			repertoryFrame.inventoryVertificationPanel.updateui(false);
			repertoryFrame.createReceiptPanel.updateui(false);
		}
	}
	
	public void successSend(int patternNum){
		if(patternNum == 1){
			JOptionPane.showMessageDialog(null, "入库单已成功发送给总经理审批！", "发送成功", JOptionPane.WARNING_MESSAGE);
			setBaseInfos(1);
		}
		else{
			JOptionPane.showMessageDialog(null, "出库单已成功发送给总经理审批！", "发送成功", JOptionPane.WARNING_MESSAGE);
			setBaseInfos(2);
		}
	}
	
	public void failedSend(int patternNum){
		if(patternNum == 1){
			JOptionPane.showMessageDialog(null, "入库单发送失败！", "发送失败", JOptionPane.WARNING_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(null, "出库单发送失败！", "发送失败", JOptionPane.WARNING_MESSAGE);
		}
	}
	
}
