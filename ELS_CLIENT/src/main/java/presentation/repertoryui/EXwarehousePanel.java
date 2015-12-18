package presentation.repertoryui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import presentation.commonui.OperationPanel;
import businesslogic.repertorybl.RepertoryBL;
import vo.InventoryVO;
import vo.UserVO;

//import presentation.commonui.LocationHelper;


public class EXwarehousePanel extends OperationPanel {
	
	private static final long serialVersionUID = 23L;
	
	private RepertoryBL repertoryBL;
	
	private JLabel inputLabel;
	private JLabel searchLabel;
	private JTextField inputField;
	private JButton confirmButton;

	private JLabel basicMessageLabel;
	private JLabel intermediateMessageLabel;
	private JTextArea basicMessageArea;
	private JTextArea intermediateMessageArea;

	private JLabel addressLabel;
	
	private JTextField blockField;
	private JLabel blockLabel;

	private JTextField rowField;
	private JLabel rowLabel;

	private JTextField shelfField;
	private JLabel shelfLabel;

	private JTextField digitField;
	private JLabel digitLabel;
	
	private JButton confirmExwarehouseButton;
	
	private String goodsID;
	
//	private LocationHelper helper;

	public EXwarehousePanel(UserVO userVO) {
		
		repertoryBL = new RepertoryBL(userVO.userID);
		
		inputLabel = new JLabel("请输入订单号");
		searchLabel = new JLabel();
		inputField = new JTextField();
		confirmButton = new JButton("确认");

		basicMessageLabel = new JLabel("该订单基本信息");
		intermediateMessageLabel = new JLabel("该订单物流中转信息");
		basicMessageArea = new JTextArea();
		basicMessageArea.setEditable(false);
		intermediateMessageArea = new JTextArea();
		intermediateMessageArea.setEditable(false);
		
		addressLabel = new JLabel("该商品在仓库中的地址为");
		
		blockField = new JTextField();
		blockField.setEditable(false);
		blockLabel = new JLabel("区");

		rowField = new JTextField();
		rowField.setEditable(false);
		rowLabel = new JLabel("排");

		shelfField = new JTextField();
		shelfField.setEditable(false);
		shelfLabel = new JLabel("架");

		digitField = new JTextField();
		digitField.setEditable(false);
		digitLabel = new JLabel("位");
		
		confirmExwarehouseButton = new JButton("确认出库");

		
		confirmButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				goodsID = inputField.getText();
				if(goodsID != "" && goodsID != "订单号"){
					String basicMessageStr = repertoryBL.showGoodBasicMessage(goodsID);
					basicMessageArea.setText(basicMessageStr);
					String intermediateMessageStr = repertoryBL.showGoodIntermidiateMessage(goodsID);
					intermediateMessageArea.setText(intermediateMessageStr);
					InventoryVO inventoryvo = repertoryBL.findInventory(goodsID);
					blockField.setText(blockName(inventoryvo.blockNum));
					rowField.setText(inventoryvo.rowNum+"");
					shelfField.setText(inventoryvo.shelfNum+"");
					digitField.setText(inventoryvo.digitNum+"");
				}
			}
		});
		
		confirmExwarehouseButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				
				int returnNum = repertoryBL.leaveRepertory(goodsID);
				
				inputField.setText("");
				basicMessageArea.setText("");
				intermediateMessageArea.setText("");
				blockField.setText("");
				rowField.setText("");
				shelfField.setText("");
				digitField.setText("");
				
				if(returnNum==0)
					successLeave();
				else
					failedLeave();
			}
		});
		
		add(inputLabel);
		add(searchLabel);
		add(inputField);
		add(confirmButton);
		add(basicMessageLabel);
		add(intermediateMessageLabel);
		add(basicMessageArea);
		add(intermediateMessageArea);
		add(addressLabel);
		add(blockField);
		add(blockLabel);
		add(rowField);
		add(rowLabel);
		add(shelfField);
		add(shelfLabel);
		add(digitField);
		add(digitLabel);
		add(confirmExwarehouseButton);

		setLayout(null);
		
//		helper = new LocationHelper(this);

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		//
		inputLabel.setBounds((int) (width * 2.880921895006402 / 25), (int) (height * 1.2053571428571428 / 20),
				(int) (width * 3.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		searchLabel.setBounds((int) (width * 13.092189500640204 / 25), (int) (height * 1.2053571428571428 / 20),
				(int) (width * 0.8642765685019206 / 25), (int) (height * 1.2053571428571428 / 20));
		inputField.setBounds((int) (width * 13.924455825864277 / 25), (int) (height * 1.2053571428571428 / 20),
				(int) (width * 5.60179257362356 / 25), (int) (height * 1.25 / 20));
		confirmButton.setBounds((int) (width * 20.070422535211268 / 25), (int) (height * 1.2053571428571428 / 20),
				(int) (width * 1.6645326504481435 / 25), (int) (height * 1.2053571428571428 / 20));
		basicMessageLabel.setBounds((int) (width * 2.880921895006402 / 25), (int) (height * 3.3035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		intermediateMessageLabel.setBounds((int) (width * 12.880921895006402 / 25), (int) (height * 3.3035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		basicMessageArea.setBounds((int) (width * 2.880921895006402 / 25), (int) (height * 5.223214285714286 / 20),
				(int) (width * 9.85403329065301 / 25), (int) (height * 5.151785714285714 / 20));
		intermediateMessageArea.setBounds((int) (width * 12.880921895006402 / 25), (int) (height * 5.223214285714286 / 20),
				(int) (width * 9.85403329065301 / 25), (int) (height * 5.151785714285714 / 20));
		addressLabel.setBounds((int) (width * 2.880921895006402 / 25), (int) (height * 11.892857142857142 / 20),
				(int) (width * 4.673495518565941 / 25), (int) (height * 1.3839285714285714 / 20));
		blockField.setBounds((int) (width * 7.5864276568501925 / 25), (int) (height * 12.392857142857142 / 20),
				(int) (width * 1.9846350832266326 / 25), (int) (height * 1.4285714285714286 / 20));
		blockLabel.setBounds((int) (width * 9.571062740076824 / 25), (int) (height * 12.392857142857142 / 20),
				(int) (width * 0.9923175416133163 / 25), (int) (height * 1.3839285714285714 / 20));
		rowField.setBounds((int) (width * 10.947503201024327 / 25), (int) (height * 12.392857142857142 / 20),
				(int) (width * 1.2163892445582587 / 25), (int) (height * 1.4285714285714286 / 20));
		rowLabel.setBounds((int) (width * 12.163892445582587 / 25), (int) (height * 12.392857142857142 / 20),
				(int) (width * 0.9923175416133163 / 25), (int) (height * 1.3839285714285714 / 20));
		shelfField.setBounds((int) (width * 13.54033290653009 / 25), (int) (height * 12.392857142857142 / 20),
				(int) (width * 1.1843790012804096 / 25), (int) (height * 1.4285714285714286 / 20));
		shelfLabel.setBounds((int) (width * 14.7247119078105 / 25), (int) (height * 12.392857142857142 / 20),
				(int) (width * 0.9923175416133163 / 25), (int) (height * 1.3839285714285714 / 20));
		digitField.setBounds((int) (width * 16.101152368758 / 25), (int) (height * 12.392857142857142 / 20),
				(int) (width * 1.1523687580025608 / 25), (int) (height * 1.4285714285714286 / 20));
		digitLabel.setBounds((int) (width * 17.253521126760564 / 25), (int) (height * 12.3375 / 20),
				(int) (width * 0.9603072983354674 / 25), (int) (height * 1.3392857142857142 / 20));

		confirmExwarehouseButton.setBounds((int) (width * 10.979513444302176 / 25), (int) (height * 17.410714285714285 / 20),
				(int) (width * 2.592829705505762 / 25), (int) (height * 1.4285714285714286 / 20));
	}

	public String blockName(int blockNum){
		String[] blockNameList = {"飞机","火车","汽车","机动"};
		return blockNameList[blockNum];
	}
	
	public void successLeave(){
		JOptionPane.showMessageDialog(null, "出库成功(●'◡'●)", "出库成功", JOptionPane.INFORMATION_MESSAGE);
		repaint();
	}
	
	public void failedLeave(){
		JOptionPane.showMessageDialog(null, "出库失败(T_T)", "出库失败", JOptionPane.INFORMATION_MESSAGE);
		repaint();
	}
	
}
