package presentation.expressui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.expressbl.controller.ExpressMainController;
import businesslogic.expressbl.controller.ReceiptOrderController;
import businesslogic.logdiarybl.controller.LogDiaryBLController;
import businesslogic.receiptbl.GetDate;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTextArea;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import presentation.special_ui.MySearchField;
import vo.LogDiaryVO;
import vo.OrderVO;

public class FinishedOrderPanel extends OperationPanel {
	private MySearchField orderIDField;
	private JLabel clearLabel;
	private JTextArea messageArea;

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
		orderIDField = new MySearchField();
		clearLabel = new MyLabel();
		messageArea = new MyTextArea();

		receiveTimeLabel = new MyTextLabel("收件时间");
		receiverNameLabel = new MyTextLabel("实际收件人");
		receiverPhoneNumLabel = new MyTextLabel("收件人号码");
		timeField = new MyTextField();
		receiverNameField = new MyTextField();
		receiverPhoneNumField = new MyTextField();

		confirmLabel = new MyLabel();

		orderIDField.setToolTipText("例如:DD-20151204-2");

		receiveTimeLabel.setVerticalAlignment(JLabel.CENTER);
		receiverNameLabel.setVerticalAlignment(JLabel.CENTER);
		receiverPhoneNumLabel.setVerticalAlignment(JLabel.CENTER);

		add(orderIDField);
		add(clearLabel);
		add(messageArea);

		add(receiveTimeLabel);
		add(receiverNameLabel);
		// add(timeSetLabel);
		add(receiverPhoneNumLabel);
		add(timeField);
		add(receiverNameField);
		add(receiverPhoneNumField);

		add(confirmLabel);

		messageArea.setEditable(false);
		timeField.setEditable(false);
		// helper = new LocationHelper(this);
		setLayout(null);

		setInfos();
		addListener();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		orderIDField.setBounds((int) (width * 1.3819095477386936 / 25), (int) (height * 1.2608695652173914 / 20),
				(int) (width * 6.21859296482412 / 25), 30);
		clearLabel.setBounds((int) (width * 8.8203517587939695 / 25), (int) (height * 1.2608695652173914 / 20),
				(int) (width * 1.6017587939698492 / 25), (int) (height * 1.0 / 20));
		messageArea.setBounds((int) (width * 1.3819095477386936 / 25), (int) (height * 3.391304347826087 / 20),
				(int) (width * 22.141959798994975 / 25), (int) (height * 9.478260869565217 / 20));
		receiveTimeLabel.setBounds((int) (width * 1.4133165829145728 / 25), (int) (height * 13.869565217391305 / 20),
				(int) (width * 3.957286432160804 / 25), (int) (height * 1.0434782608695652 / 20));
		receiverNameLabel.setBounds((int) (width * 1.4133165829145728 / 25), (int) (height * 15.869565217391305 / 20),
				(int) (width * 3.957286432160804 / 25), (int) (height * 1.0434782608695652 / 20));
		// timeSetLabel.setBounds((int) (width * 18.27889447236181 / 25), (int)
		// (height * 3.4347826086956523 / 20),
		// (int) (width * 0.9736180904522613 / 25), (int) (height *
		// 1.3478260869565217 / 20));
		receiverPhoneNumLabel.setBounds((int) (width * 1.4133165829145728 / 25),
				(int) (height * 17.869565217391305 / 20), (int) (width * 3.957286432160804 / 25),
				(int) (height * 1.0434782608695652 / 20));
		timeField.setBounds((int) (width * 4.778894472361809 / 25), (int) (height * 13.869565217391305 / 20),
				(int) (width * 3.5175879396984926 / 25), (int) (height * 1.0869565217391304 / 20));
		receiverNameField.setBounds((int) (width * 4.778894472361809 / 25), (int) (height * 15.869565217391305 / 20),
				(int) (width * 3.548994974874372 / 25), (int) (height * 1.0869565217391304 / 20));
		receiverPhoneNumField.setBounds((int) (width * 4.778894472361809 / 25),
				(int) (height * 17.869565217391305 / 20), (int) (width * 3.548994974874372 / 25),
				(int) (height * 1.0869565217391304 / 20));
		confirmLabel.setBounds((int) (width * 19.236016371077763 / 25), (int) (height * 16.523297491039425 / 20),
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

				String msg = "          订单号: " + vo.ID + "\n" + "\n           收件人: \n" + "                姓名: "
						+ vo.senderName + "                地址: " + vo.senderAddress + "                电话:　"
						+ vo.senderPhoneNumber + "                手机号: " + vo.senderMobilePhoneNumber + "\n"
						+ "\n           寄件人: \n" + "                姓名: " + vo.recipientName + "                地址: "
						+ vo.recipientAddress + "                电话:　" + vo.recipientPhoneNumber
						+ "                手机号: " + vo.recipientMobilePhoneNumber + "\n" + "\n           货物信息:\n"
						+ "                货物名: " + vo.goodsName + "                件数: " + vo.numOfGoods
						+ "                质量: " + vo.weight + "                体积: " + vo.volume + "\n"
						+ "\n           快递类型: " + expressType + "           包装费: " + vo.packingExpense
						+ "           运费: " + vo.freight;

				messageArea.setText(msg);

			}
		});
		confirmLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

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
					log.addLogDiary(new LogDiaryVO(GetDate.getTime(),ExpressMainController.expressVO,"完成了一份订单"), GetDate.getTime());	

				} else {
					warnning("提交失败,请重新提交");
				}
			}
		});

		clearLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				orderIDField.setText("");
				messageArea.setText("");
				// timeField.setText(null);
				receiverNameField.setText("");
				receiverPhoneNumField.setText("");
			}
		});
	}

	private void clear() {
		orderIDField.setText("");
		messageArea.setText("");
		timeField.setText("");
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
