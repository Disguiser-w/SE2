package presentation.businessui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import businesslogic.businessbl.controller.AcceptCargoController;
import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.financebl.controller.LogDiaryBLController;
import businesslogic.receiptbl.GetDate;
import presentation.commonui.LocationHelper;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import vo.LogDiaryVO;

public class OrderReceiveManagerPanel extends OperationPanel {
	private JLabel vehicleIDLabel;
	private MyTextField vehicleIDField;

	private JLabel timeLabel;
	private MyTextField timeField;

	private JLabel local;
	private MyTextField localField;

	private JLabel inputLabel;
	private MyTextField orderNumField;
	private MyLabel inputConfirmButton;

	private MyLabel confirmButton;

	private MyTable messageTable;

	// 定为
	private LocationHelper helper;
	private AcceptCargoController controller;
	private ArrayList<String[]> orderNum;
	private LogDiaryBLController log;

	public OrderReceiveManagerPanel(AcceptCargoController acceptCargoController) {
		this.controller = acceptCargoController;

		log = new LogDiaryBLController();
		vehicleIDLabel = new MyTextLabel("车辆ID");
		vehicleIDField = new MyTextField();

		timeLabel = new MyTextLabel("时间");
		timeField = new MyTextField();

		local = new MyTextLabel("营业厅ID");
		localField = new MyTextField();

		inputLabel = new MyTextLabel("输入订单号");
		orderNumField = new MyTextField();
		inputConfirmButton = new MyLabel("确认");

		confirmButton = new MyLabel("提交");

		orderNum = new ArrayList<String[]>();

		add(vehicleIDLabel);
		add(vehicleIDField);
		add(timeLabel);
		add(timeField);
		add(local);
		add(localField);

		add(inputLabel);
		add(orderNumField);
		add(inputConfirmButton);
		// helper = new LocationHelper(this);

		add(confirmButton);

		setLayout(null);
		addListener();

		setBaseInfos();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 组件setBounds;
		vehicleIDLabel.setBounds((int) (width * 2.694406548431105 / 25), (int) (height * 7.706093189964157 / 20),
				(int) (width * 2.1145975443383356 / 25), (int) (height * 1.3261648745519714 / 20));
		vehicleIDField.setBounds((int) (width * 5.525238744884038 / 25), (int) (height * 7.670250896057348 / 20),
				(int) (width * 4.433833560709413 / 25), (int) (height * 1.3620071684587813 / 20));
		timeLabel.setBounds((int) (width * 2.694406548431105 / 25), (int) (height * 10.824372759856631 / 20),
				(int) (width * 2.1145975443383356 / 25), (int) (height * 1.3261648745519714 / 20));
		timeField.setBounds((int) (width * 5.525238744884038 / 25), (int) (height * 10.824372759856631 / 20),
				(int) (width * 4.399727148703956 / 25), (int) (height * 1.3261648745519714 / 20));
		local.setBounds((int) (width * 2.728512960436562 / 25), (int) (height * 4.659498207885305 / 20),
				(int) (width * 2.1145975443383356 / 25), (int) (height * 1.3261648745519714 / 20));
		localField.setBounds((int) (width * 5.525238744884038 / 25), (int) (height * 4.659498207885305 / 20),
				(int) (width * 4.399727148703956 / 25), (int) (height * 1.3261648745519714 / 20));
		inputLabel.setBounds((int) (width * 2.762619372442019 / 25), (int) (height * 1.7204301075268817 / 20),
				(int) (width * 2.6145975443383356 / 25), (int) (height * 1.3261648745519714 / 20));
		orderNumField.setBounds((int) (width * 5.559345156889496 / 25), (int) (height * 1.7204301075268817 / 20),
				(int) (width * 4.399727148703956 / 25), (int) (height * 1.3261648745519714 / 20));
		inputConfirmButton.setBounds((int) (width * 10.879945429740792 / 25), (int) (height * 1.8279569892473118 / 20),
				(int) (width * 2.2851296043656206 / 25), (int) (height * 1.075268817204301 / 20));
		confirmButton.setBounds((int) (width * 4.38881309686221 / 25), (int) (height * 15.161290322580646 / 20),
				(int) (width * 4.796725784447476 / 25), (int) (height * 2.8487455197132617 / 20));
		messageTable.setLocationAndSize((int) (width * 14.972714870395635 / 25),
				(int) (height * 1.7562724014336917 / 20), (int) (width * 8.526603001364256 / 25),
				(int) (height * 16.666666666666668 / 20));

	}

	private void setBaseInfos() {
		timeField.setText(getDate());
		timeField.setEditable(false);
		localField.setEditable(false);

		localField.setText(BusinessMainController.businessVO.organizationVO.organizationID);

		String[] head = new String[] { "订单号" };

		int[] widths = { 200 };

		messageTable = new MyTable(head, new ArrayList<String[]>(), widths, false);
		add(messageTable);

	}

	private void addListener() {

		inputConfirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String orderNumber = orderNumField.getText();
				if (orderNumber.equals("")) {
					warnning("请输入订单号");
					return;
				}
				if (!controller.orderExist(orderNumber)) {
					warnning("不存在此订单号，请检查订单号是否有误");
					return;
				}

				for (int i = 0; i < orderNum.size(); i++)
					if (orderNum.get(i)[0].equals(orderNumber)) {
						warnning("已经添加该订单");
						return;
					}

				orderNum.add(new String[] { orderNumber });
				orderNumField.setText("");
				messageTable.addRow(new String[] { orderNumber });

			}
		});

		confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String vehicleID = vehicleIDField.getText();
				if (vehicleID.equals("")) {
					warnning("请输入车辆ID");
					return;
				}

				if (!controller.vehicleExist(vehicleID)) {
					warnning("该司机不存在,请检查司机ID是否输入错误");
					return;
				}

				ArrayList<String> str = new ArrayList<String>();
				for (String[] i : orderNum) {
					str.add(i[0]);
				}

				if (str.size() == 0) {
					warnning("订单列表为空");
					return;
				}
				if (controller.acceptCargo(vehicleID, str)) {
					log.addLogDiary(new LogDiaryVO(GetDate.getTime(), BusinessMainController.businessVO, "接收了一批货物"),
							GetDate.getTime());
					success("收货单已提交!");
					clear();
				}

			}
		});

	}

	private void clear() {
		messageTable.setInfos(new ArrayList<String[]>());
		vehicleIDField.setText("");
		orderNumField.setText("");
	}

	private void warnning(String message) {
		// fix 放到底部信息栏
		JOptionPane.showMessageDialog(null, message, "", JOptionPane.ERROR_MESSAGE);
	}

	private void success(String message) {
		JOptionPane.showMessageDialog(null, message, "", JOptionPane.DEFAULT_OPTION);
	}

	private String getDate() {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(new Date());
	}
}
