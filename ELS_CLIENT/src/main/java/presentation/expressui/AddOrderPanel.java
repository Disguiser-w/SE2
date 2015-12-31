package presentation.expressui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import businesslogic.expressbl.controller.AddOrderController;
import businesslogic.expressbl.controller.ExpressMainController;
import businesslogic.financebl.controller.LogDiaryBLController;
import businesslogic.receiptbl.GetDate;
import presentation.commonui.MyComboBox;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import type.ExpressType;
import type.PackType;
import vo.LogDiaryVO;
import vo.OrderVO;

public class AddOrderPanel extends OperationPanel {
	/**
	 * 
	 */
	private JLabel senderLabel;
	private JLabel senderNameLabel;
	private JLabel senderOrganizationLabel;
	private JLabel senderPhoneLabel;
	private JLabel senderMobilePhoneLabel;
	private JLabel senderAddressLabel;

	private JLabel receiverLabel;
	private JLabel receiverNameLabel;
	private JLabel receiverOrganizationLabel;
	private JLabel receiverPhoneLabel;
	private JLabel receiverMobilePhoneLabel;
	private JLabel receiverAddressLabel;

	private JLabel goodsLabel;
	private JLabel numberLabel;
	private JLabel weightLabel;
	private JLabel volumnLabel;
	private JLabel goodNameLabel;

	private JLabel expressTypeLabel;
	private JLabel packageTypeLabel;
	private JLabel totalSumLabel;

	private JLabel costLabel;
	private JLabel timeLabel;
	private MyTextField cost;
	private MyTextField time;

	private MyTextField senderNameField;
	private MyTextField senderOrganizationField;
	private MyTextField senderPhoneField;
	private MyTextField senderMobilePhoneField;
	private MyTextField senderAddressField;

	private MyTextField receiverNameField;
	private MyTextField receiverOrganizationField;
	private MyTextField receiverPhoneField;
	private MyTextField receiverMobilePhoneField;
	private MyTextField receiverAddressField;

	private MyTextField goodsField;
	private MyTextField numberField;
	private MyTextField weightField;
	private MyTextField volumnField;
	private MyTextField goodNameField;

	private JComboBox<String> senderCountryList;
	private JComboBox<String> senderCityField;
	private JComboBox<String> receiverCountryList;
	private JComboBox<String> receiverCityField;
	private JComboBox<String> expressTypeList;
	private JComboBox<String> packageTypeList;

	private JLabel calcuButton;
	private JLabel confirmButton;

	private AddOrderController controller;
	private OrderVO newOrder;

	private ArrayList<ArrayList<String>> places;

	// private LocationHelper helper;
	private LogDiaryBLController log;

	public AddOrderPanel(AddOrderController controller) {
		this.controller = controller;
		log = new LogDiaryBLController();
		
		senderLabel = new MyTextLabel("寄件人信息");
		senderNameLabel = new MyTextLabel("姓名");
		senderOrganizationLabel = new MyTextLabel("单位");
		senderPhoneLabel = new MyTextLabel("电话");
		senderMobilePhoneLabel = new MyTextLabel("手机");
		senderAddressLabel = new MyTextLabel("住址");

		receiverLabel = new MyTextLabel("收件人信息");
		receiverNameLabel = new MyTextLabel("姓名");
		receiverOrganizationLabel = new MyTextLabel("单位");
		receiverPhoneLabel = new MyTextLabel("电话");
		receiverMobilePhoneLabel = new MyTextLabel("手机");
		receiverAddressLabel = new MyTextLabel("住址");

		goodsLabel = new MyTextLabel("货物信息");
		numberLabel = new MyTextLabel("原件数");
		weightLabel = new MyTextLabel("实际重量");
		volumnLabel = new MyTextLabel("体积");
		goodNameLabel = new MyTextLabel("内件品名");

		expressTypeLabel = new MyTextLabel("快递类型");
		packageTypeLabel = new MyTextLabel("包装种类");
		totalSumLabel = new MyTextLabel("");

		senderNameField = new MyTextField();
		senderOrganizationField = new MyTextField();
		senderPhoneField = new MyTextField();
		senderMobilePhoneField = new MyTextField();
		senderAddressField = new MyTextField();

		receiverNameField = new MyTextField();
		receiverOrganizationField = new MyTextField();
		receiverPhoneField = new MyTextField();
		receiverMobilePhoneField = new MyTextField();
		receiverAddressField = new MyTextField();

		goodsField = new MyTextField();
		numberField = new MyTextField();
		weightField = new MyTextField();
		volumnField = new MyTextField();
		goodNameField = new MyTextField();

		senderCountryList = new MyComboBox<String>();
		senderCityField = new MyComboBox<String>();

		receiverCountryList = new MyComboBox<String>();
		receiverCityField = new MyComboBox<String>();
		expressTypeList = new MyComboBox<String>();
		packageTypeList = new MyComboBox<String>();

		calcuButton = new MyLabel("补全");
		confirmButton = new MyLabel("确认");

		costLabel = new MyTextLabel("总费用");
		timeLabel = new MyTextLabel("预计时间");
		cost = new MyTextField();
		cost.setText("-");
		time = new MyTextField();
		time.setText("-");

		cost.setEditable(false);
		time.setEditable(false);

		add(senderLabel);
		add(senderNameLabel);
		add(senderOrganizationLabel);

		add(senderPhoneLabel);
		add(senderMobilePhoneLabel);
		add(senderAddressLabel);
		add(receiverLabel);
		add(receiverNameLabel);
		add(receiverOrganizationLabel);
		add(receiverPhoneLabel);
		add(receiverMobilePhoneLabel);
		add(receiverAddressLabel);
		add(goodsLabel);
		add(numberLabel);
		add(weightLabel);
		add(volumnLabel);
		add(goodNameLabel);
		add(expressTypeLabel);
		add(packageTypeLabel);
		add(totalSumLabel);
		add(senderNameField);
		add(senderOrganizationField);
		add(senderPhoneField);
		add(senderMobilePhoneField);
		add(senderAddressField);
		add(receiverNameField);
		add(receiverOrganizationField);
		add(receiverPhoneField);
		add(receiverMobilePhoneField);
		add(receiverAddressField);
		add(goodsField);
		add(numberField);
		add(weightField);
		add(volumnField);
		add(goodNameField);
		add(senderCountryList);
		add(senderCityField);
		add(receiverCountryList);
		add(receiverCityField);
		add(expressTypeList);
		add(packageTypeList);
		add(calcuButton);
		add(confirmButton);

		add(costLabel);
		add(timeLabel);
		add(cost);
		add(time);
		// helper = new LocationHelper(this);

		setBaseInfo();
		addListener();
		setLayout(null);

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 设之所有组件的bounds

		senderLabel.setBounds(width / 50, height / 25, width / 8, height / 20);
		senderNameLabel.setBounds(width * 1 / 25, height * 3 / 25, width / 20, height / 20);
		senderOrganizationLabel.setBounds(width * 7 / 25, height * 3 / 25, width / 20, height / 20);
		senderPhoneLabel.setBounds(width * 13 / 25, height * 3 / 25, width / 20, height / 20);
		senderMobilePhoneLabel.setBounds(width * 19 / 25, height * 3 / 25, width / 20, height / 20);

		senderAddressLabel.setBounds(width * 1 / 25, height * 5 / 25, width / 20, height / 20);
		senderCountryList.setBounds(width * 1 / 10, height * 5 / 25, width * 7 / 50, height / 20);
		senderCityField.setBounds(width * 6 / 25, height * 5 / 25, width * 7 / 50, height / 20);

		receiverLabel.setBounds(width / 50, height * 7 / 25, width / 8, height / 20);
		receiverNameLabel.setBounds(width * 1 / 25, height * 9 / 25, width / 20, height / 20);
		receiverOrganizationLabel.setBounds(width * 7 / 25, height * 9 / 25, width / 20, height / 20);
		receiverPhoneLabel.setBounds(width * 13 / 25, height * 9 / 25, width / 20, height / 20);
		receiverMobilePhoneLabel.setBounds(width * 19 / 25, height * 9 / 25, width / 20, height / 20);

		receiverAddressLabel.setBounds(width * 1 / 25, height * 11 / 25, width / 20, height / 20);
		receiverCountryList.setBounds(width * 1 / 10, height * 11 / 25, width * 7 / 50, height / 20);
		receiverCityField.setBounds(width * 6 / 25, height * 11 / 25, width * 7 / 50, height / 20);

		senderNameField.setBounds(width * 1 / 10, height * 3 / 25, width * 7 / 50, height / 20);
		senderOrganizationField.setBounds(width * 17 / 50, height * 3 / 25, width * 7 / 50, height / 20);
		senderPhoneField.setBounds(width * 29 / 50, height * 3 / 25, width * 7 / 50, height / 20);
		senderMobilePhoneField.setBounds(width * 41 / 50, height * 3 / 25, width * 7 / 50, height / 20);

		senderAddressField.setBounds(width * 19 / 50, height * 5 / 25, width * 21 / 50, height / 20);

		receiverNameField.setBounds(width * 1 / 10, height * 9 / 25, width * 7 / 50, height / 20);
		receiverOrganizationField.setBounds(width * 17 / 50, height * 9 / 25, width * 7 / 50, height / 20);
		receiverPhoneField.setBounds(width * 29 / 50, height * 9 / 25, width * 7 / 50, height / 20);
		receiverMobilePhoneField.setBounds(width * 41 / 50, height * 9 / 25, width * 7 / 50, height / 20);

		receiverAddressField.setBounds(width * 19 / 50, height * 11 / 25, width * 21 / 50, height / 20);

		goodsLabel.setBounds(width / 50, height * 13 / 25, width / 8, height / 20);

		numberLabel.setBounds(width * 1 / 25, height * 3 / 5, width / 15, height / 20);
		weightLabel.setBounds(width * 6 / 25, height * 3 / 5, width * 2 / 25, height / 20);
		volumnLabel.setBounds(width * 12 / 25, height * 3 / 5, width / 15, height / 20);
		goodNameLabel.setBounds(width * 17 / 25, height * 3 / 5, width * 2 / 25, height / 20);
		expressTypeLabel.setBounds(width / 50, height * 17 / 25, width / 8, height / 20);
		packageTypeLabel.setBounds(width / 50, height * 19 / 25, width / 8, height / 20);

		totalSumLabel.setBounds(width, height, width, height);

		// goodsField.setBounds(width, height, width, height);
		numberField.setBounds(width * 3 / 25, height * 3 / 5, width / 15, height / 20);
		weightField.setBounds(width * 17 / 50, height * 3 / 5, width / 15, height / 20);
		volumnField.setBounds(width * 14 / 25, height * 3 / 5, width / 15, height / 20);
		goodNameField.setBounds(width * 39 / 50, height * 3 / 5, width * 9 / 50, height / 20);

		expressTypeList.setBounds(width * 4 / 25, height * 17 / 25, width / 8, height / 20);
		packageTypeList.setBounds(width * 4 / 25, height * 19 / 25, width / 8, height / 20);

		costLabel.setBounds(width / 2, height * 17 / 25, width / 8, height / 20);
		timeLabel.setBounds(width / 2, height * 19 / 25, width / 8, height / 20);
		cost.setBounds(width * 16 / 25, height * 17 / 25, width / 8, height / 20);
		time.setBounds(width * 16 / 25, height * 19 / 25, width / 8, height / 20);

		calcuButton.setBounds(width * 19 / 25, height * 22 / 25, width / 12, height / 20);
		confirmButton.setBounds(width * 22 / 25, height * 22 / 25, width / 12, height / 20);

	}

	private void setBaseInfo() {
		// 获取城市信息，

		ArrayList<String> citys = null;
		places = new ArrayList<ArrayList<String>>();

		try {
			;
			citys = controller.getAllCitys();

			for (String i : citys) {
				places.add(controller.getBelongCitys(i));
				// ArrayList<String> str = new ArrayList<String>();
				// str.add("这里");
				// str.add("那里");
				// places.add(str);
			}

			places.add(new ArrayList<String>());
			// places = cityDistanceData.getPlaces();

		} catch (Exception e) {
			e.printStackTrace();
		}

		for (String i : citys) {
			senderCountryList.addItem(i);
			receiverCountryList.addItem(i);
		}

		for (String i : places.get(0)) {
			senderCityField.addItem(i);
			receiverCityField.addItem(i);
		}

		senderCountryList.addItem("");

		// PackType.CARTONS
		packageTypeList.addItem("纸箱");
		// WOODCASE
		packageTypeList.addItem("木箱");
		// COURIERBAGS
		packageTypeList.addItem("快递袋");

		// ECONOMICAL
		expressTypeList.addItem("经济");
		// STANDARD
		expressTypeList.addItem("标准");
		// VERY_FAST
		expressTypeList.addItem("特快");

	}

	private void addListener() {

		confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (cost.getText().equals("-")) {
					warnning("订单价格尚未计算");
					return;
				}

				if (controller.addOrder(newOrder)) {
					addSuccessful("订单提交成功！");
					clear();
					log.addLogDiary(new LogDiaryVO(GetDate.getTime(),ExpressMainController.expressVO,"增加了一份订单"), GetDate.getTime());	
				} else
					warnning("订单提交失败，请重新提交");

			}
		});
		// 费用计算
		calcuButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String senderName = senderNameField.getText();

				if (senderName.trim().equals("")) {
					warnning("寄件人姓名不可为空！");
					return;
				}

				String senderOrganization = senderOrganizationField.getText();
				if (senderOrganization.equals(""))
					senderOrganizationField.setText("-");

				String senderPhone = senderPhoneField.getText();
				if (senderPhone.equals(""))
					senderPhoneField.setText("-");

				String senderMobilePhone = senderMobilePhoneField.getText();

				if (senderMobilePhone.equals("")) {
					warnning("寄件人手机号码不可为空！");
					return;

				}
				boolean result = true;

				if (senderMobilePhone.length() != 11)
					result = false;
				else
					for (int i = 0; i < 11; i++) {
						if ('0' > senderMobilePhone.charAt(i) || senderMobilePhone.charAt(i) > '9')
							result = false;
					}
				if (!result) {
					warnning("寄件人手机号码格式有误！");
					return;
				}

				String senderAddress = senderAddressField.getText();
				if (senderAddress.equals("")) {
					senderAddressField.setText("-");
				}

				String receiverName = receiverNameField.getText();
				if (receiverName.equals("")) {
					warnning("收件人姓名不可为空！");
					return;
				}

				String receiverOrganization = receiverOrganizationField.getText();
				if (receiverOrganization.equals(""))
					receiverOrganizationField.setText("-");

				String receiverPhone = receiverPhoneField.getText();
				if (receiverPhone.equals(""))
					receiverPhoneField.setText("-");

				String receiverMobilePhone = receiverMobilePhoneField.getText();
				if (receiverMobilePhone.equals("")) {
					warnning("收件人手机号码不可为空！");
					return;
				}

				if (receiverMobilePhone.length() != 11)
					result = false;
				else
					for (int i = 0; i < 11; i++) {
						if ('0' > receiverMobilePhone.charAt(i) || receiverMobilePhone.charAt(i) > '9')
							result = false;
					}
				if (!result) {
					warnning("收件人手机号码格式有误！");
					return;
				}

				String receiverAddress = receiverAddressField.getText();
				if (receiverAddress.equals("")) {
					warnning("收件人地址不可为空！");
					return;
				}

				String number = numberField.getText();

				String weight = weightField.getText();

				String volumn = volumnField.getText();

				String goodName = goodNameField.getText();
				if (goodName.equals(""))
					goodNameField.setText("-");

				if (number.equals("") || weight.equals("") || volumn.equals("")) {
					warnning("货物信息不完整！");
					return;
				}

				if (!(isInteger(number) && isInteger(weight) && isInteger(volumn))) {
					warnning("货物信息格式不正确！");
					return;
				}

				String senderCountry = (String) (senderCountryList.getSelectedItem());
				String senderCity = (String) senderCityField.getSelectedItem();

				String receiverCountry = (String) (receiverCountryList.getSelectedItem());
				String receiverCity = (String) receiverCityField.getSelectedItem();

				String expressType = (String) (expressTypeList.getSelectedItem());
				String packageType = (String) (packageTypeList.getSelectedItem());

				ExpressType expressT = null;
				switch (expressType) {

				case "经济":
					expressT = ExpressType.ECONOMIC;
				case "标准":
					expressT = ExpressType.STANDARD;
				case "特快":
					expressT = ExpressType.FAST;
				}

				PackType packageT = null;
				switch (packageType) {
				case "纸箱":
					packageT = PackType.CARTONS;
				case "木箱":
					packageT = PackType.WOODCASE;
				case "快递带":
					packageT = PackType.COURIERBAGS;
				}

				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String dateNowStr = sdf.format(d);

				SimpleDateFormat build = new SimpleDateFormat("yyyy-MM-dd");
				String buildData = build.format(d);

				String ID = null;
				try {
					ID = "DD-" + dateNowStr + "-" + (ExpressMainController.expressData
							.getOrderNum(ExpressMainController.expressVO.organizationVO.organizationID) + 1);

				} catch (RemoteException e2) {

					e2.printStackTrace();
				}

				newOrder = new OrderVO(ID, senderName,
						senderCountry + " " + senderCity + " " + senderAddressField.getText(),
						senderOrganizationField.getText(), senderPhoneField.getText(), senderMobilePhone, receiverName,
						receiverCountry + " " + receiverCity + " " + receiverAddressField.getText(),
						receiverOrganizationField.getText(), receiverPhoneField.getText(), receiverMobilePhone,
						Integer.parseInt(number), weight, volumn, goodNameField.getText(), expressT, packageT, 0f, 0f,
						buildData, "", "", null, null, new ArrayList<String>());
				// 计算

				controller.calculateCost(newOrder);

				String costStr = ((int) ((newOrder.freight + newOrder.packingExpense) * 10)) / 10 + "";
				cost.setText(newOrder.freight + newOrder.packingExpense + "元");

				if (senderCountry.equals(receiverCountry))
					time.setText("一天");
				else
					time.setText("两天");

				repaint();

				// 显示价格

			}
		});

		senderCountryList.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				senderCityField.removeAllItems();

				for (String i : places.get(senderCountryList.getSelectedIndex())) {
					senderCityField.addItem(i);

				}
			}

		});

		receiverCountryList.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				receiverCityField.removeAllItems();

				for (String i : places.get(receiverCountryList.getSelectedIndex())) {
					receiverCityField.addItem(i);

				}
			}

		});
	}

	private void clear() {

		senderNameField.setText("");

		senderOrganizationField.setText("");

		senderPhoneField.setText("");

		senderMobilePhoneField.setText("");

		senderAddressField.setText("");

		receiverNameField.setText("");

		receiverOrganizationField.setText("");

		receiverPhoneField.setText("");

		receiverMobilePhoneField.setText("");

		receiverAddressField.setText("");

		numberField.setText("");

		weightField.setText("");

		volumnField.setText("");

		goodNameField.setText("");

		senderCountryList.setSelectedIndex(0);

		senderCityField.setSelectedIndex(0);

		receiverCountryList.setSelectedIndex(0);

		receiverCityField.setSelectedIndex(0);

		expressTypeList.setSelectedIndex(0);

		packageTypeList.setSelectedIndex(0);

		cost.setText("-");

		time.setText("-");

	}

	public void showMessage(String msg) {
		// 先显示在控制台

	}

	public boolean isMsgEmpty(String message) {
		if (message.trim().equals(""))
			return true;
		return false;

	}

	public void warnning(String message) {
		// fix 放到底部信息栏
		JOptionPane.showMessageDialog(null, message, "订单信息错误", JOptionPane.ERROR_MESSAGE);
	}

	public void addSuccessful(String message) {
		JOptionPane.showMessageDialog(null, message, "订单提交成功", JOptionPane.INFORMATION_MESSAGE);
	}

	public boolean isInteger(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != '.' && (str.charAt(i) < '0' || str.charAt(i) > '9'))
				return false;
		}
		return true;
	}

}
