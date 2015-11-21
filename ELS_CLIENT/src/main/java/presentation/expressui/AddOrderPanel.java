package presentation.expressui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import businesslogic.expressbl.controller.AddOrderController;

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
	private JComboBox<String> senderCityList;
	private JComboBox<String> receiverCountryList;
	private JComboBox<String> receiverCityList;
	private JComboBox<String> expressTypeList;
	private JComboBox<String> packageTypeList;

	private JButton calcuButton;
	private JButton confirmButton;

	private AddOrderController controller;
	// private LocationHelper helper;

	public AddOrderPanel() {
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
		senderCityList = new JComboBox<String>();

		receiverCountryList = new JComboBox<String>();
		receiverCityList = new JComboBox<String>();
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
		add(senderCityList);
		add(receiverCountryList);
		add(receiverCityList);
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

		controller = new AddOrderController();
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
		senderCityList.setBounds(width * 6 / 25, height * 5 / 25, width * 7 / 50, height / 20);

		receiverLabel.setBounds(width / 50, height * 7 / 25, width / 8, height / 20);
		receiverNameLabel.setBounds(width * 1 / 25, height * 9 / 25, width / 20, height / 20);
		receiverOrganizationLabel.setBounds(width * 7 / 25, height * 9 / 25, width / 20, height / 20);
		receiverPhoneLabel.setBounds(width * 13 / 25, height * 9 / 25, width / 20, height / 20);
		receiverMobilePhoneLabel.setBounds(width * 19 / 25, height * 9 / 25, width / 20, height / 20);

		receiverAddressLabel.setBounds(width * 1 / 25, height * 11 / 25, width / 20, height / 20);
		receiverCountryList.setBounds(width * 1 / 10, height * 11 / 25, width * 7 / 50, height / 20);
		receiverCityList.setBounds(width * 6 / 25, height * 11 / 25, width * 7 / 50, height / 20);

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

	}

	private void addListener() {

	}

}
