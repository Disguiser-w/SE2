package presentation.repertoryui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;

import type.ReceiptState;
import vo.EnterRepertoryReceiptVO;
import businesslogic.repertorybl.controller.EnterRepertoryReceiptController;

public class EnterReceiptDetailedInfoPanel extends OperationPanel{

	private static final long serialVersionUID = 50L;
	
	private RepertoryFrame repertoryFrame;

	private EnterRepertoryReceiptController enterRepertoryReceiptControl;
	
	private String receiptID;
	
	private MyTextLabel categoryLabel;
	private MyTextLabel receiptIDLabel;
	private MyTextLabel userIDLabel;
	private MyTextLabel createTimeLabel;
	private MyTextLabel stateLabel;
	private MyTextLabel enterMessageLabel;

	private MyTextField receiptIDField;
	private MyTextField userIDField;
	private MyTextField createTimeField;
	private MyTextField stateField;
	
	private MyTable messageTable;
	
	private MyLabel returnLabel;
	
	private EnterRepertoryReceiptVO errvo;
	
	public EnterReceiptDetailedInfoPanel(RepertoryFrame repertoryFrame, EnterRepertoryReceiptController enterRepertoryReceiptController, String enterReceiptID){
		
		this.repertoryFrame = repertoryFrame;
		
		enterRepertoryReceiptControl = enterRepertoryReceiptController;
		
		this.receiptID = enterReceiptID;
		
		errvo = enterRepertoryReceiptControl.findEnterReceiptByReceiptID(receiptID);
		
		categoryLabel = new MyTextLabel("入库单");
		receiptIDLabel = new MyTextLabel("单据编号");
		userIDLabel = new MyTextLabel("创建者编号");
		createTimeLabel = new MyTextLabel("创建时间");
		stateLabel = new MyTextLabel("当前状态");
		enterMessageLabel = new MyTextLabel("详细入库信息");

		receiptIDField = new MyTextField();
		receiptIDField.setText(enterReceiptID);
		receiptIDField.setEditable(false);
		userIDField = new MyTextField();
		userIDField.setText(errvo.userID);
		userIDField.setEditable(false);
		createTimeField = new MyTextField();
		createTimeField.setText(errvo.createTime);
		createTimeField.setEditable(false);
		stateField = new MyTextField();
		stateField.setText(stateName(errvo.state));
		stateField.setEditable(false);
		
		returnLabel = new MyLabel("返回");
		
		setLayout(null);
		
		add(categoryLabel);
		add(receiptIDLabel);
		add(userIDLabel);
		add(createTimeLabel);
		add(stateLabel);
		add(enterMessageLabel);
		add(receiptIDField);
		add(userIDField);
		add(createTimeField);
		add(stateField);
		add(returnLabel);
		
		addListeners();
		setBaseInfos();
	}
	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		categoryLabel.setBounds((int) (width * 3.880921895006402 / 25), (int) (height * 2.0035714285714284 / 20),
				(int) (width * 3.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		receiptIDLabel.setBounds((int) (width * 6.880921895006402 / 25), (int) (height * 3.5035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		userIDLabel.setBounds((int) (width * 6.880921895006402 / 25), (int) (height * 5.0035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		createTimeLabel.setBounds((int) (width * 6.880921895006402 / 25), (int) (height * 6.5035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		stateLabel.setBounds((int) (width * 6.880921895006402 / 25), (int) (height * 8.0035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		enterMessageLabel.setBounds((int) (width * 3.880921895006402 / 25), (int) (height * 9.5035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		receiptIDField.setBounds((int) (width * 12.880921895006402 / 25), (int) (height * 3.5035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		userIDField.setBounds((int) (width * 12.880921895006402 / 25), (int) (height * 5.0035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		createTimeField.setBounds((int) (width * 12.880921895006402 / 25), (int) (height * 6.5035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		stateField.setBounds((int) (width * 12.880921895006402 / 25), (int) (height * 8.0035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		messageTable.setLocationAndSize((int) (width * 3.880921895006402 / 25), (int) (height * 11.0035714285714284 / 20),
				(int) (width * 18.85403329065301 / 25), (int) (height * 6.651785714285714 / 20));
		
		returnLabel.setBounds((int) (width * 11.479513444302176 / 25),(int) (height * 17.20285714285715 / 20), 
				(int) (width * 3.23303457106274 / 25),(int) (height * 1.3839285714285714 / 20));
	}
	
	
	public void addListeners(){
		returnLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				returnui();
			}
		});
	}
	
	private void setBaseInfos(){
		String[] head = {"货物订单号", "入库时间"};
		int[] widths = {236, 236};
		
		messageTable = new MyTable(head, getInfos(), widths, true);
		add(messageTable);
	}
	
	private ArrayList<String[]> getInfos(){
		ArrayList<String[]> infos = new ArrayList<String[]>();
		
		String[] IDList = errvo.expressIDList;
		String[] timeList = errvo.timeList;
		int size = errvo.expressIDList.length;
		
		for(int i=0;i<size; i++){
			infos.add(new String[]{IDList[i], timeList[i]});
		}
		return infos;
	}
	
	public void returnui(){
		repertoryFrame.toMainPanel();
	}
	
	public String stateName(ReceiptState receiptState){
		String[] receiptStateNameList = {"草稿", "待审批", "审批通过", "审批未通过"};
		int stateInt = receiptState.ordinal();
		return receiptStateNameList[stateInt];
	}
	
}
