package presentation.repertoryui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;

import vo.GoodsVO;
import businesslogic.repertorybl.controller.GoodsController;

public class GoodsDetailedInfoPanel extends OperationPanel{

	private static final long serialVersionUID = 54L;
	
	private RepertoryFrame repertoryFrame;

	private GoodsController goodsControl;
	
	private String goodsID;
	
	private MyTextLabel basicMessageLabel;
	private MyTextLabel IDLabel;
	private MyTextLabel feeLabel;
	private MyTextLabel departureLabel;
	private MyTextLabel destinationLabel;
	private MyTextLabel intermediateMessageLabel;

	private MyTextField IDField;
	private MyTextField feeField;
	private MyTextField departureField;
	private MyTextField destinationField;
	
	private MyTable intermediateMessageTable;
	
	private MyLabel returnLabel;
	
	public GoodsDetailedInfoPanel(RepertoryFrame repertoryFrame,  GoodsController goodsController, String orderID){
		
		this.repertoryFrame = repertoryFrame;
		
		goodsControl = goodsController;
		
		this.goodsID = orderID;
		
		GoodsVO goodsvo = goodsControl.findGoodsByID(orderID);
		
		basicMessageLabel = new MyTextLabel("该订单基本信息");
		IDLabel = new MyTextLabel("订单号");
		feeLabel = new MyTextLabel("费用");
		departureLabel = new MyTextLabel("出发地");
		destinationLabel = new MyTextLabel("目的地");
		intermediateMessageLabel = new MyTextLabel("该订单物流中转信息");

		IDField = new MyTextField();
		IDField.setText(orderID);
		IDField.setEditable(false);
		feeField = new MyTextField();
		feeField.setText(goodsvo.fee+"");
		feeField.setEditable(false);
		departureField = new MyTextField();
		departureField.setText(goodsvo.departurePlace);
		departureField.setEditable(false);
		destinationField = new MyTextField();
		destinationField.setText(goodsvo.destination);
		destinationField.setEditable(false);
		
		returnLabel = new MyLabel("返回");
		
		setLayout(null);
		
		add(basicMessageLabel);
		add(IDLabel);
		add(feeLabel);
		add(departureLabel);
		add(destinationLabel);
		add(intermediateMessageLabel);
		add(IDField);
		add(feeField);
		add(departureField);
		add(destinationField);
		add(returnLabel);
		
		addListeners();
		setBaseInfos();
	}
	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		basicMessageLabel.setBounds((int) (width * 3.880921895006402 / 25), (int) (height * 2.0035714285714284 / 20),
				(int) (width * 3.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		IDLabel.setBounds((int) (width * 6.880921895006402 / 25), (int) (height * 3.5035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		feeLabel.setBounds((int) (width * 6.880921895006402 / 25), (int) (height * 5.0035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		departureLabel.setBounds((int) (width * 6.880921895006402 / 25), (int) (height * 6.5035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		destinationLabel.setBounds((int) (width * 6.880921895006402 / 25), (int) (height * 8.0035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		intermediateMessageLabel.setBounds((int) (width * 3.880921895006402 / 25), (int) (height * 9.5035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		IDField.setBounds((int) (width * 12.880921895006402 / 25), (int) (height * 3.5035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		feeField.setBounds((int) (width * 12.880921895006402 / 25), (int) (height * 5.0035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		departureField.setBounds((int) (width * 12.880921895006402 / 25), (int) (height * 6.5035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		destinationField.setBounds((int) (width * 12.880921895006402 / 25), (int) (height * 8.0035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		intermediateMessageTable.setLocationAndSize((int) (width * 3.880921895006402 / 25), (int) (height * 11.0035714285714284 / 20),
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
		String[] head = {"物流信息"};
		int[] widths = {472};
		
		intermediateMessageTable = new MyTable(head, getInfos(), widths, true);
		add(intermediateMessageTable);
	}
	
	private ArrayList<String[]> getInfos(){
		ArrayList<String[]> infos = new ArrayList<String[]> ();
		String messageStr = goodsControl.showGoodIntermidiateMessage(goodsID);
		if(messageStr.equals("暂无该订单号对应的物流中转信息")){
			infos.add(new String[]{messageStr});
			return infos;
		}
		
		String[] lineParts = messageStr.split("\n");
		for(int i=0;i<lineParts.length; i++){
			infos.add(new String[]{lineParts[i]});
		}
		return infos;
	}
	
	public void returnui(){
		repertoryFrame.toMainPanel();
	}
	
}
