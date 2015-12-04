package presentation.expressui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import businesslogic.datafactory.DataFactory;
import businesslogic.expressbl.controller.AddOrderController;
import businesslogic.expressbl.controller.ExpressMainController;
import dataservice.managedataservice.CityDistanceDataService;
import type.ExpressType;
import type.PackType;
import vo.OrderVO;

public class AddOrderPanel extends JPanel {
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
	private JLabel cost;
	private JLabel time;

	private JTextField senderNameField;
	private JTextField senderOrganizationField;
	private JTextField senderPhoneField;
	private JTextField senderMobilePhoneField;
	private JTextField senderAddressField;

	private JTextField receiverNameField;
	private JTextField receiverOrganizationField;
	private JTextField receiverPhoneField;
	private JTextField receiverMobilePhoneField;
	private JTextField receiverAddressField;

	private JTextField goodsField;
	private JTextField numberField;
	private JTextField weightField;
	private JTextField volumnField;
	private JTextField goodNameField;

	private JComboBox<String> senderCountryList;
	private JComboBox<String> senderCityField;
	private JComboBox<String> receiverCountryList;
	private JComboBox<String> receiverCityField;
	private JComboBox<String> expressTypeList;
	private JComboBox<String> packageTypeList;

	private JButton calcuButton;
	private JButton confirmButton;

	private AddOrderController controller;
	private OrderVO newOrder;

	// private LocationHelper helper;

	public AddOrderPanel(AddOrderController controller) {
		this.controller = controller;

		senderLabel = new JLabel("寄件人信息");
		senderNameLabel = new JLabel("姓名");
		senderOrganizationLabel = new JLabel("单位");
		senderPhoneLabel = new JLabel("电话");
		senderMobilePhoneLabel = new JLabel("手机");
		senderAddressLabel = new JLabel("住址");

		receiverLabel = new JLabel("收件人信息");
		receiverNameLabel = new JLabel("姓名");
		receiverOrganizationLabel = new JLabel("单位");
		receiverPhoneLabel = new JLabel("电话");
		receiverMobilePhoneLabel = new JLabel("手机");
		receiverAddressLabel = new JLabel("住址");

		goodsLabel = new JLabel("货物信息");
		numberLabel = new JLabel("原件数");
		weightLabel = new JLabel("实际重量");
		volumnLabel = new JLabel("体积");
		goodNameLabel = new JLabel("内件品名");

		expressTypeLabel = new JLabel("快递类型");
		packageTypeLabel = new JLabel("包装种类");
		totalSumLabel = new JLabel("");

		senderNameField = new JTextField();
		senderOrganizationField = new JTextField();
		senderPhoneField = new JTextField();
		senderMobilePhoneField = new JTextField();
		senderAddressField = new JTextField();

		receiverNameField = new JTextField();
		receiverOrganizationField = new JTextField();
		receiverPhoneField = new JTextField();
		receiverMobilePhoneField = new JTextField();
		receiverAddressField = new JTextField();

		goodsField = new JTextField();
		numberField = new JTextField();
		weightField = new JTextField();
		volumnField = new JTextField();
		goodNameField = new JTextField();

		senderCountryList = new JComboBox<String>();
		senderCityField = new JComboBox<String>();

		receiverCountryList = new JComboBox<String>();
		receiverCityField = new JComboBox<String>();
		expressTypeList = new JComboBox<String>();
		packageTypeList = new JComboBox<String>();

		calcuButton = new JButton("补全");
		confirmButton = new JButton("确认");

		costLabel = new JLabel("总费用");
		timeLabel = new JLabel("预计时间");
		cost = new JLabel("");
		time = new JLabel("");

		// senderLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// senderNameLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// senderOrganizationLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// senderPhoneLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// senderMobilePhoneLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// senderAddressLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//
		// receiverLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// receiverNameLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// receiverOrganizationLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// receiverPhoneLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// receiverMobilePhoneLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// receiverAddressLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//
		// goodsLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// numberLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// weightLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// volumnLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// goodNameLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//
		// expressTypeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// packageTypeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// totalSum.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// senderLabel.setHorizontalAlignment(JLabel.CENTER);
		// senderNameLabel.setHorizontalAlignment(JLabel.CENTER);
		// senderOrganizationLabel.setHorizontalAlignment(JLabel.CENTER);
		// senderPhoneLabel.setHorizontalAlignment(JLabel.CENTER);
		// senderMobilePhoneLabel.setHorizontalAlignment(JLabel.CENTER);
		// senderAddressLabel.setHorizontalAlignment(JLabel.CENTER);
		//
		// receiverLabel.setHorizontalAlignment(JLabel.CENTER);
		// receiverNameLabel.setHorizontalAlignment(JLabel.CENTER);
		// receiverOrganizationLabel.setHorizontalAlignment(JLabel.CENTER);
		// receiverPhoneLabel.setHorizontalAlignment(JLabel.CENTER);
		// receiverMobilePhoneLabel.setHorizontalAlignment(JLabel.CENTER);
		// receiverAddressLabel.setHorizontalAlignment(JLabel.CENTER);
		//
		// goodsLabel.setHorizontalAlignment(JLabel.CENTER);
		// numberLabel.setHorizontalAlignment(JLabel.CENTER);
		// weightLabel.setHorizontalAlignment(JLabel.CENTER);
		// volumnLabel.setHorizontalAlignment(JLabel.CENTER);
		// goodNameLabel.setHorizontalAlignment(JLabel.CENTER);
		//
		// expressTypeLabel.setHorizontalAlignment(JLabel.CENTER);
		// packageTypeLabel.setHorizontalAlignment(JLabel.CENTER);
		// totalSum.setHorizontalAlignment(JLabel.CENTER);

		//
		// senderNameField;
		// senderOrganizationField;
		// senderPhoneField;
		// senderMobilePhoneField;
		// senderAddressField;
		//
		// receiverNameField;
		// receiverOrganizationField;
		// receiverPhoneField;
		// receiverMobilePhoneField;
		// receiverAddressField;
		//
		// goodsField;
		// numberField;
		// weightField;
		// volumnField;
		// goodNameField;
		//
		// senderCountryList;
		// senderCityList;
		// receiverCountryList;
		// receiverCityList;
		// expressTypeList;
		// packageTypeList;
		//
		// calcuButton;
		// confirmButton;

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

		calcuButton.setBounds(width * 19 / 25, height * 22 / 25, width / 12, height / 15);
		confirmButton.setBounds(width * 22 / 25, height * 22 / 25, width / 12, height / 15);

	}

	private void setBaseInfo() {
		// 获取城市信息，
		CityDistanceDataService cityDistanceData = null;
		ArrayList<String> citys = null;
		ArrayList<String> places = null;

		try {
			cityDistanceData = DataFactory.getCityDistanceData();
			citys = cityDistanceData.getAllCitys();
			// places = cityDistanceData.getPlaces();

		} catch (Exception e) {
			e.printStackTrace();
		}

		for (String i : citys) {
			senderCountryList.addItem(i);
			receiverCountryList.addItem(i);
		}
		senderCountryList.addItem("");

		senderCityField.addItem("区１");

		receiverCityField.addItem("区1");

		senderCityField.addItem("区2");

		receiverCityField.addItem("区2");

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

		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (controller.addOrder(newOrder)) {
					warnning(new String[] { "订单提交成功" });
					clear();
				} else {
					
				}
			}
		});
		// 费用计算
		calcuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String senderName = senderNameField.getText();

				if (senderName.trim().equals("")) {
					warnning(new String[] { "寄件人姓名不可为空！" });
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
					warnning(new String[] { "寄件人手机号码不可为空！" });
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
					warnning(new String[] { "寄件人手机号码格式有误！" });
					return;
				}

				String senderAddress = senderAddressField.getText();
				if (senderAddress.equals("")) {
					senderAddressField.setText("-");
				}

				String receiverName = receiverNameField.getText();
				if (receiverName.equals("")) {
					warnning(new String[] { "收件人姓名不可为空！" });
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
					warnning(new String[] { "收件人手机号码不可为空！" });
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
					warnning(new String[] { "寄件人手机号码格式有误！" });
					return;
				}

				String receiverAddress = receiverAddressField.getText();
				if (receiverAddress.equals("")) {
					warnning(new String[] { "收件人地址不可为空！" });
					return;
				}

				String number = numberField.getText();

				String weight = weightField.getText();

				String volumn = volumnField.getText();

				String goodName = goodNameField.getText();
				if (goodName.equals(""))
					goodNameField.setText("-");

				if (number.equals("") || weight.equals("") || volumn.equals("")) {
					warnning(new String[] { "货物信息不完整！" });
					return;
				}

				if (!(isInteger(number) && isInteger(weight) && isInteger(volumn))) {
					warnning(new String[] { "货物信息格式不正确！" });
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
							.getOrderNum(ExpressMainController.expressVO.organization.organizationID) + 1);
				} catch (RemoteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				newOrder = new OrderVO(ID, senderName, senderCountry + senderCity + senderAddress, senderOrganization,
						senderPhone, senderMobilePhone, receiverName, receiverCountry + receiverCity + receiverAddress,
						receiverOrganization, receiverPhone, receiverMobilePhone, Integer.parseInt(number), weight,
						volumn, goodName, expressT, packageT, 0f, 0f, buildData, "", "", null, null,
						new ArrayList<String>());
				// 计算

				controller.calculateCost(newOrder);

				cost.setText((int) (newOrder.freight + newOrder.packingExpense) + "元");
				if (senderCountry.equals(receiverCountry))
					time.setText("一天");
				else
					time.setText("两天");

				repaint();
				try {
					CityDistanceDataService cityDistanceData = DataFactory.getCityDistanceData();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// 显示价格

			}
		});

		senderCountryList.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int count = senderCountryList.getSelectedIndex();
				// 测试
				ArrayList<String> places = new ArrayList<String>();
				switch (count) {
				case 0:
					places.add("区1");
					places.add("区2");
					break;
				case 1:
					places.add("区3");
					places.add("区4");
					break;
				case 2:
					places.add("区5");
					places.add("区6");
					break;
				case 3:
					places.add("区7");
					places.add("区8");
					break;
				default:
				}

				senderCityField.removeAllItems();

				for (String i : places) {
					senderCityField.addItem(i);

				}
			}

		});

		receiverCountryList.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int count = receiverCountryList.getSelectedIndex();
				// 测试
				ArrayList<String> places = new ArrayList<String>();
				switch (count) {
				case 0:
					places.add("区1");
					places.add("区2");
					break;
				case 1:
					places.add("区3");
					places.add("区4");
					break;
				case 2:
					places.add("区5");
					places.add("区6");
					break;
				case 3:
					places.add("区7");
					places.add("区8");
					break;
				}

				receiverCityField.removeAllItems();
				for (String i : places) {
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

		// cost = new JLabel("");
		cost.setText("");
		// time = new JLabel("");
		time.setText("");
	}

	public void showMessage(String msg) {
		// 先显示在控制台

	}

	public boolean isMsgEmpty(String message) {
		if (message.trim().equals(""))
			return true;
		return false;

	}

	public void warnning(String[] message) {
		//之后全部放到底部
		String msg = "";
		for (int i = 0; i < message.length; i++) {
			msg += message[i] + "\n";
		}
		JOptionPane.showMessageDialog(null, msg, "订单信息错误", JOptionPane.ERROR_MESSAGE);
	}

	public boolean isInteger(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != '.' && (str.charAt(i) < '0' || str.charAt(i) > '9'))
				return false;
		}
		return true;
	}

	public void paintComponent(Graphics g) {
	}

}
