package presentation.expressui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import businesslogic.expressbl.controller.ExpressMainController;
import businesslogic.expressbl.controller.ReceiptOrderController;
import businesslogic.financebl.controller.LogDiaryBLController;
import businesslogic.receiptbl.GetDate;
import presentation.commonui.LocationHelper;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTextArea;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import presentation.special_ui.MySearchField;
import type.OrderState;
import vo.LogDiaryVO;
import vo.OrderVO;

public class FinishedOrderPanel extends OperationPanel {
	private MyTextLabel senderInfo;

	private MyTextLabel senderName;
	private MyTextField senderNameField;
	private MyTextLabel senderAddress;
	private MyTextField senderAddressField;
	private MyTextLabel senderPhoneNum;
	private MyTextField senderPhoneNumField;

	private MyTextLabel receiverInfo;

	private MyTextLabel receiverName;
	private MyTextField receiverNameF;
	private MyTextLabel receiverAddress;
	private MyTextField receiverAddressF;
	private MyTextLabel receiverPhoneNum;
	private MyTextField receiverPhoneNumF;

	private MySearchField orderIDField;
	private JLabel clearLabel;

	private JLabel receiveTimeLabel;
	private JLabel receiverNameLabel;
	// private JLabel timeSetLabel;
	private JLabel receiverPhoneNumLabel;
	private MyTextField timeField;
	private MyTextField receiverNameField;
	private MyTextField receiverPhoneNumField;

	private JLabel confirmLabel;

	private OrderVO vo;
	// private LocationHelper helper;
	private ReceiptOrderController controller;
	private LogDiaryBLController log;

	public FinishedOrderPanel(ReceiptOrderController controller) {
		this.controller = controller;
		log = new LogDiaryBLController();

		senderInfo = new MyTextLabel("寄件人信息");

		senderName = new MyTextLabel("寄件人姓名");
		senderNameField = new MyTextField();
		senderNameField.setEditable(false);
		senderAddress = new MyTextLabel("寄件人地址");
		senderAddressField = new MyTextField();
		senderAddressField.setEditable(false);
		senderPhoneNum = new MyTextLabel("寄件人手机号");
		senderPhoneNumField = new MyTextField();
		senderPhoneNumField.setEditable(false);

		receiverInfo = new MyTextLabel("收件人信息");

		receiverName = new MyTextLabel("收件人姓名");
		receiverNameF = new MyTextField();
		receiverNameF.setEditable(false);
		receiverAddress = new MyTextLabel("收件人地址");
		receiverAddressF = new MyTextField();
		receiverAddressF.setEditable(false);
		receiverPhoneNum = new MyTextLabel("收件人手机号");
		receiverPhoneNumF = new MyTextField();

		receiverPhoneNumF.setEditable(false);
		orderIDField = new MySearchField();
		clearLabel = new MyLabel("清空");

		receiveTimeLabel = new MyTextLabel("收件时间");
		receiverNameLabel = new MyTextLabel("实际收件人");
		receiverPhoneNumLabel = new MyTextLabel("收件人号码");
		timeField = new MyTextField();
		receiverNameField = new MyTextField();
		receiverPhoneNumField = new MyTextField();

		confirmLabel = new MyLabel("确认");

		orderIDField.setToolTipText("例如:DD-20151204-2");

		receiveTimeLabel.setVerticalAlignment(JLabel.CENTER);
		receiverNameLabel.setVerticalAlignment(JLabel.CENTER);
		receiverPhoneNumLabel.setVerticalAlignment(JLabel.CENTER);
		add(senderInfo);
		add(senderName);
		add(senderNameField);
		add(senderAddress);
		add(senderAddressField);
		add(senderPhoneNum);
		add(senderPhoneNumField);

		add(receiverInfo);
		add(receiverName);
		add(receiverNameF);
		add(receiverAddress);
		add(receiverAddressF);
		add(receiverPhoneNum);
		add(receiverPhoneNumF);

		add(orderIDField);
		add(clearLabel);

		add(receiveTimeLabel);
		add(receiverNameLabel);
		add(receiverPhoneNumLabel);
		add(timeField);
		add(receiverNameField);
		add(receiverPhoneNumField);

		add(confirmLabel);
//		LocationHelper helper = new LocationHelper(this);
		timeField.setEditable(false);

		setLayout(null);

		setInfos();
		addListener();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		senderInfo.setBounds((int) (width * 0.9890859481582538 / 25), (int) (height * 3.6917562724014337 / 20),
				(int) (width * 2.592087312414734 / 25), (int) (height * 1.1111111111111112 / 20));
		senderName.setBounds((int) (width * 1.4324693042291952 / 25), (int) (height * 5.483870967741935 / 20),
				(int) (width * 2.592087312414734 / 25), (int) (height * 1.1111111111111112 / 20));
		senderNameField.setBounds((int) (width * 3.956343792633015 / 25), (int) (height * 5.483870967741935 / 20),
				(int) (width * 2.592087312414734 / 25), (int) (height * 1.1111111111111112 / 20));
		senderAddress.setBounds((int) (width * 6.957708049113234 / 25), (int) (height * 5.483870967741935 / 20),
				(int) (width * 2.592087312414734 / 25), (int) (height * 1.1111111111111112 / 20));
		senderAddressField.setBounds((int) (width * 9.51568894952251 / 25), (int) (height * 5.483870967741935 / 20),
				(int) (width * 6.3096862210095495 / 25), (int) (height * 1.1111111111111112 / 20));
		senderPhoneNum.setBounds((int) (width * 16.200545702592088 / 25), (int) (height * 5.483870967741935 / 20),
				(int) (width * 3.2401091405184173 / 25), (int) (height * 1.1111111111111112 / 20));
		senderPhoneNumField.setBounds((int) (width * 19.440654843110504 / 25), (int) (height * 5.483870967741935 / 20),
				(int) (width * 5.013642564802183 / 25), (int) (height * 1.1111111111111112 / 20));
		receiverInfo.setBounds((int) (width * 0.9549795361527967 / 25), (int) (height * 7.813620071684587 / 20),
				(int) (width * 2.592087312414734 / 25), (int) (height * 1.1111111111111112 / 20));
		receiverName.setBounds((int) (width * 1.4324693042291952 / 25), (int) (height * 9.53405017921147 / 20),
				(int) (width * 2.592087312414734 / 25), (int) (height * 1.1111111111111112 / 20));
		receiverNameF.setBounds((int) (width * 3.956343792633015 / 25), (int) (height * 9.53405017921147 / 20),
				(int) (width * 2.592087312414734 / 25), (int) (height * 1.1111111111111112 / 20));
		receiverAddress.setBounds((int) (width * 6.957708049113234 / 25), (int) (height * 9.53405017921147 / 20),
				(int) (width * 2.592087312414734 / 25), (int) (height * 1.1111111111111112 / 20));
		receiverAddressF.setBounds((int) (width * 9.51568894952251 / 25), (int) (height * 9.53405017921147 / 20),
				(int) (width * 6.3096862210095495 / 25), (int) (height * 1.1111111111111112 / 20));
		receiverPhoneNum.setBounds((int) (width * 16.200545702592088 / 25), (int) (height * 9.53405017921147 / 20),
				(int) (width * 3.2401091405184173 / 25), (int) (height * 1.1111111111111112 / 20));
		receiverPhoneNumF.setBounds((int) (width * 19.440654843110504 / 25), (int) (height * 9.53405017921147 / 20),
				(int) (width * 5.013642564802183 / 25), (int) (height * 1.1111111111111112 / 20));

		orderIDField.setBounds((int) (width * 1.3819095477386936 / 25), (int) (height * 1.2608695652173914 / 20),
				(int) (width * 6.21859296482412 / 25), 30);
		clearLabel.setBounds((int) (width * 8.8203517587939695 / 25), (int) (height * 1.2608695652173914 / 20),
				(int) (width * 1.6017587939698492 / 25), (int) (height * 1.0 / 20));
		receiveTimeLabel.setBounds((int) (width * 1.4133165829145728 / 25), (int) (height * 12.869565217391305 / 20),
				(int) (width * 3.957286432160804 / 25), (int) (height * 1.0434782608695652 / 20));
		receiverNameLabel.setBounds((int) (width * 1.4133165829145728 / 25), (int) (height * 14.869565217391305 / 20),
				(int) (width * 3.957286432160804 / 25), (int) (height * 1.0434782608695652 / 20));
		receiverPhoneNumLabel.setBounds((int) (width * 1.4133165829145728 / 25),
				(int) (height * 16.869565217391305 / 20), (int) (width * 3.957286432160804 / 25),
				(int) (height * 1.0434782608695652 / 20));
		timeField.setBounds((int) (width * 4.778894472361809 / 25), (int) (height * 12.869565217391305 / 20),
				(int) (width * 3.5175879396984926 / 25), (int) (height * 1.0869565217391304 / 20));
		receiverNameField.setBounds((int) (width * 4.778894472361809 / 25), (int) (height * 14.869565217391305 / 20),
				(int) (width * 3.548994974874372 / 25), (int) (height * 1.0869565217391304 / 20));
		receiverPhoneNumField.setBounds((int) (width * 4.778894472361809 / 25),
				(int) (height * 16.869565217391305 / 20), (int) (width * 3.548994974874372 / 25),
				(int) (height * 1.0869565217391304 / 20));
		confirmLabel.setBounds((int) (width * 19.236016371077763 / 25), (int) (height * 15.523297491039425 / 20),
				(int) (width * 4.297407912687586 / 25), (int) (height * 2.2222222222222223 / 20));

	}

	private void setInfos() {
		timeField.setText(getTime());
	}

	private void addListener() {
		orderIDField.getImageLabel().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String orderID = orderIDField.getText();
				if (orderID.equals("")) {
					warnning("请输入订单号");
					return;
				}

				vo = controller.getOrderInfo(orderID);

				if (vo == null) {
					warnning("订单号不存在");
					return;
				}

				if (vo.order_state == OrderState.FINISHED) {
					warnning("该订单已完成");
					return;
				} else if (vo.order_state != OrderState.DISTRIBUEING) {
					warnning("订单号错误或尚在转运中");
					return;
				}

				String expressType = null;

				switch (vo.expressType) {
				case ECONOMIC:
					expressType = "经济";
				case STANDARD:
					expressType = "标准";
				case FAST:
					expressType = "特快";
				}

				vo.packingExpense = ((int) ((vo.packingExpense) * 10)) / 10;
				vo.freight = ((int) ((vo.freight) * 10)) / 10;

				// String msg = "\n" + " 订单号: " + vo.ID + "\n" + "\n 收件人: \n" +
				// " 姓名: "
				// + vo.senderName + " 地址: " + vo.senderAddress + " 电话: "
				// + vo.senderPhoneNumber + " 手机号: " +
				// vo.senderMobilePhoneNumber + "\n"
				// + "\n 寄件人: \n" + " 姓名: " + vo.recipientName + " 地址: "
				// + vo.recipientAddress + " 电话: " + vo.recipientPhoneNumber
				// + " 手机号: " + vo.recipientMobilePhoneNumber + "\n" + "\n
				// 货物信息:\n"
				// + " 货物名: " + vo.goodsName + " 件数: " + vo.numOfGoods
				// + " 质量: " + vo.weight + " 体积: " + vo.volume + "\n"
				// + "\n 快递类型: " + expressType + " 包装费: " + vo.packingExpense
				// + " 运费: " + vo.freight;

				senderNameField.setText(vo.senderName);
				senderAddressField.setText(vo.senderAddress);
				senderPhoneNumField.setText(vo.senderMobilePhoneNumber);

				receiverNameF.setText(vo.recipientName);
				receiverAddressF.setText(vo.recipientAddress);
				receiverPhoneNumF.setText(vo.recipientMobilePhoneNumber);

			}
		});
		confirmLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (vo == null) {
					warnning("请先输入订单号查询");
					return;
				}

				if (receiverNameField.getText().equals("")) {
					warnning("实际收件人不可为空");
					return;
				}

				String num = receiverPhoneNumField.getText();

				boolean isFommatRight = true;
				if (num.length() != 11) {
					warnning("实际收件人号码格式错误");
					return;
				}
				for (int i = 0; i < 11; i++)
					if (num.charAt(i) < '0' || num.charAt(i) > '9')
						isFommatRight = false;
				if (!isFommatRight) {
					warnning("实际收件人号码格式错误");

				}

				vo.finishedID = ExpressMainController.expressVO.userID + "-" + timeField.getText();
				vo.finishedDate = getTime();

				vo.tRecipient = receiverNameField.getText();
				vo.tPhoneNumber = num;

				if (controller.receiptOrder(vo)) {
					tip("提交成功!");

					clear();
					log.addLogDiary(new LogDiaryVO(GetDate.getTime(), ExpressMainController.expressVO, "完成了一份订单"),
							GetDate.getTime());

				} else {
					warnning("提交失败,请重新提交");
				}
			}
		});

		clearLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				clear();
			}
		});
	}

	private void clear() {
		orderIDField.setText("");
		senderNameField.setText("");
		senderAddressField.setText("");
		senderPhoneNumField.setText("");

		receiverNameF.setText("");
		receiverAddressF.setText("");
		receiverPhoneNumF.setText("");
		receiverNameField.setText("");
		receiverPhoneNumField.setText("");
		vo = null;

	}

	private String getTime() {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String d = f.format(date);
		return d;
	}

	private void warnning(String msg) {
		JOptionPane.showMessageDialog(null, msg, "订单信息错误", JOptionPane.INFORMATION_MESSAGE);
	}

	private void tip(String msg) {
		JOptionPane.showMessageDialog(null, msg, "完成收件", JOptionPane.PLAIN_MESSAGE);
	}
}
