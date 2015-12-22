package presentation.repertoryui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import presentation.commonui.MyLabel;
import presentation.commonui.MyTextField;
import presentation.commonui.OperationPanel;

import businesslogic.repertorybl.RepertoryBL;
import vo.UserVO;

//import presentation.commonui.LocationHelper;

public class WarehousingPanel extends OperationPanel {

	private static final long serialVersionUID = 92L;

	private RepertoryBL repertoryBL;
	
	private MyTextField inputField;
	private MyLabel searchLabel;

	private JLabel basicMessageLabel;
	private JLabel intermediateMessageLabel;
	private JTextArea basicMessageArea;
	private JTextArea intermediateMessageArea;

	private JLabel chooseLabel;

	private MyLabel planeLabel;
	private MyLabel trainLabel;
	private MyLabel vehicleLabel;
	private MyLabel motorLabel;

	private JLabel addressLabel;
	private JLabel blockLabel;
	private JLabel rowLabel;
	private JLabel shelfLabel;
	private JLabel digitLabel;
	
	private MyTextField blockField;
	private MyTextField rowField;
	private MyTextField shelfField;
	private MyTextField digitField;

	private MyLabel OKLabel;
	
	String goodsID;
	int blockNum;
	int rowNum;
	int shelfNum;
	int digitNum;

//	private LocationHelper helper;

	public WarehousingPanel(UserVO userVO) {
		
		repertoryBL = new RepertoryBL(userVO.userID);
		
		inputField = new MyTextField();
		inputField.setText("订单号");
		searchLabel = new MyLabel("确认");

		basicMessageLabel = new JLabel("该订单基本信息");
		intermediateMessageLabel = new JLabel("该订单物流中转信息");
		basicMessageArea = new JTextArea();
		basicMessageArea.setEditable(false);
		intermediateMessageArea = new JTextArea();
		intermediateMessageArea.setEditable(false);
		
		chooseLabel = new JLabel("选择分区");

		planeLabel = new MyLabel("飞机区");
		trainLabel = new MyLabel("火车区");
		vehicleLabel = new MyLabel("汽车区");
		motorLabel = new MyLabel("机动区");
		
		String warningStr = repertoryBL.inventoryWarning();
		if(warningStr.contains("0"))
			planeLabel.setEnabled(false);
		if(warningStr.contains("1"))
			trainLabel.setEnabled(false);
		if(warningStr.contains("2"))
			vehicleLabel.setEnabled(false);

		addressLabel = new JLabel("为该商品分配的地址为");

		blockField = new MyTextField();
		blockField.setEditable(false);
		blockLabel = new JLabel("区");

		rowField = new MyTextField();
		rowField.setEditable(false);
		rowLabel = new JLabel("排");

		shelfField = new MyTextField();
		shelfField.setEditable(false);
		shelfLabel = new JLabel("架");

		digitField = new MyTextField();
		digitField.setEditable(false);
		digitLabel = new JLabel("位");

		OKLabel = new MyLabel("确认入库");

		planeLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				blockNum = 0;
				String locationStr = repertoryBL.searchVacantLocation(0);
				String[] parts = locationStr.split(" ");
				blockField.setText("飞机");
				rowField.setText(parts[0]);
				shelfField.setText(parts[1]);
				digitField.setText(parts[2]);
			}
		});
		
		trainLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				blockNum = 1;
				String locationStr = repertoryBL.searchVacantLocation(1);
				String[] parts = locationStr.split(" ");
				blockField.setText("火车");
				rowField.setText(parts[0]);
				shelfField.setText(parts[1]);
				digitField.setText(parts[2]);
			}
		});
		
		vehicleLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				blockNum = 2;
				String locationStr = repertoryBL.searchVacantLocation(2);
				String[] parts = locationStr.split(" ");
				blockField.setText("汽车");
				rowField.setText(parts[0]);
				shelfField.setText(parts[1]);
				digitField.setText(parts[2]);
			}
		});
		
		motorLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				blockNum = 3;
				String locationStr = repertoryBL.searchVacantLocation(3);
				String[] parts = locationStr.split(" ");
				blockField.setText("机动");
				rowField.setText(parts[0]);
				shelfField.setText(parts[1]);
				digitField.setText(parts[2]);
			}
		});
		
		searchLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				goodsID = inputField.getText();
				if(goodsID != "" && goodsID != "订单号"){
					String basicMessageStr = repertoryBL.showGoodBasicMessage(goodsID);
					basicMessageArea.setText(basicMessageStr);
					String intermediateMessageStr = repertoryBL.showGoodIntermidiateMessage(goodsID);
					intermediateMessageArea.setText(intermediateMessageStr);
				}
			}
		});
		
		OKLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int rowNum = Integer.parseInt(rowField.getText());
				int shelfNum = Integer.parseInt(shelfField.getText());
				int digitNum = Integer.parseInt(digitField.getText());
				
				int returnNum = repertoryBL.enterRepertory(goodsID, blockNum, rowNum, shelfNum, digitNum);
				
				inputField.setText("");
				basicMessageArea.setText("");
				intermediateMessageArea.setText("");
				blockField.setText("");
				rowField.setText("");
				shelfField.setText("");
				digitField.setText("");
				
				if(returnNum==0)
					successEnter();
				else
					failedEnter();
			}
		});
		
		
		add(inputField);
		add(searchLabel);
		add(basicMessageLabel);
		add(intermediateMessageLabel);
		add(basicMessageArea);
		add(intermediateMessageArea);
		add(chooseLabel);
		add(planeLabel);
		add(trainLabel);
		add(vehicleLabel);
		add(motorLabel);
		add(addressLabel);
		add(blockField);
		add(blockLabel);
		add(rowField);
		add(rowLabel);
		add(shelfField);
		add(shelfLabel);
		add(digitField);
		add(digitLabel);
		add(OKLabel);

		setLayout(null);
		
//		helper = new LocationHelper(this);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		inputField.setBounds((int) (width * 13.924455825864277 / 25), (int) (height * 1.2053571428571428 / 20),
				(int) (width * 5.60179257362356 / 25), (int) (height * 1.25 / 20));
		searchLabel.setBounds((int) (width * 20.070422535211268 / 25), (int) (height * 1.2053571428571428 / 20),
				(int) (width * 1.6645326504481435 / 25), (int) (height * 1.2053571428571428 / 20));
		basicMessageLabel.setBounds((int) (width * 2.880921895006402 / 25), (int) (height * 3.3035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		intermediateMessageLabel.setBounds((int) (width * 12.880921895006402 / 25), (int) (height * 3.3035714285714284 / 20),
				(int) (width * 5.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		basicMessageArea.setBounds((int) (width * 2.880921895006402 / 25), (int) (height * 5.223214285714286 / 20),
				(int) (width * 9.85403329065301 / 25), (int) (height * 5.151785714285714 / 20));
		intermediateMessageArea.setBounds((int) (width * 12.880921895006402 / 25), (int) (height * 5.223214285714286 / 20),
				(int) (width * 9.85403329065301 / 25), (int) (height * 5.151785714285714 / 20));
		chooseLabel.setBounds((int) (width * 2.880921895006402 / 25), (int) (height * 10.892857142857142 / 20),
				(int) (width * 3.649167733674776 / 25), (int) (height * 1.6071428571428572 / 20));
		planeLabel.setBounds((int) (width * 3.0409731113956466 / 25), (int) (height * 13.080357142857142 / 20),
				(int) (width * 2.496798975672215 / 25), (int) (height * 1.6071428571428572 / 20));
		trainLabel.setBounds((int) (width * 7.746478873239437 / 25), (int) (height * 13.080357142857142 / 20),
				(int) (width * 2.496798975672215 / 25), (int) (height * 1.6071428571428572 / 20));
		vehicleLabel.setBounds((int) (width * 12.445902688860436 / 25), (int) (height * 13.080357142857142 / 20),
				(int) (width * 2.496798975672215 / 25), (int) (height * 1.6071428571428572 / 20));
		motorLabel.setBounds((int) (width * 17.149244558258643 / 25), (int) (height * 13.080357142857142 / 20),
				(int) (width * 2.496798975672215 / 25), (int) (height * 1.6071428571428572 / 20));
		addressLabel.setBounds((int) (width * 2.880921895006402 / 25), (int) (height * 15.892857142857142 / 20),
				(int) (width * 4.673495518565941 / 25), (int) (height * 1.3839285714285714 / 20));
		blockField.setBounds((int) (width * 7.5864276568501925 / 25), (int) (height * 15.892857142857142 / 20),
				(int) (width * 1.9846350832266326 / 25), (int) (height * 1.4285714285714286 / 20));
		blockLabel.setBounds((int) (width * 9.571062740076824 / 25), (int) (height * 15.892857142857142 / 20),
				(int) (width * 0.9923175416133163 / 25), (int) (height * 1.3839285714285714 / 20));
		rowField.setBounds((int) (width * 10.947503201024327 / 25), (int) (height * 15.892857142857142 / 20),
				(int) (width * 1.2163892445582587 / 25), (int) (height * 1.4285714285714286 / 20));
		rowLabel.setBounds((int) (width * 12.163892445582587 / 25), (int) (height * 15.892857142857142 / 20),
				(int) (width * 0.9923175416133163 / 25), (int) (height * 1.3839285714285714 / 20));
		shelfField.setBounds((int) (width * 13.54033290653009 / 25), (int) (height * 15.892857142857142 / 20),
				(int) (width * 1.1843790012804096 / 25), (int) (height * 1.4285714285714286 / 20));
		shelfLabel.setBounds((int) (width * 14.7247119078105 / 25), (int) (height * 15.892857142857142 / 20),
				(int) (width * 0.9923175416133163 / 25), (int) (height * 1.3839285714285714 / 20));
		digitField.setBounds((int) (width * 16.101152368758 / 25), (int) (height * 15.892857142857142 / 20),
				(int) (width * 1.1523687580025608 / 25), (int) (height * 1.4285714285714286 / 20));
		digitLabel.setBounds((int) (width * 17.253521126760564 / 25), (int) (height * 15.9375 / 20),
				(int) (width * 0.9603072983354674 / 25), (int) (height * 1.3392857142857142 / 20));
		OKLabel.setBounds((int) (width * 10.979513444302176 / 25),
				(int) (height * 18.214285714285715 / 20), (int) (width * 3.23303457106274 / 25),
				(int) (height * 1.3839285714285714 / 20));
	}
	
	public void successEnter(){
		JOptionPane.showMessageDialog(null, "入库成功(●'◡'●)", "入库成功", JOptionPane.INFORMATION_MESSAGE);
		repaint();
	}
	
	public void failedEnter(){
		JOptionPane.showMessageDialog(null, "入库失败(T_T)", "入库失败", JOptionPane.INFORMATION_MESSAGE);
		repaint();
	}
}
