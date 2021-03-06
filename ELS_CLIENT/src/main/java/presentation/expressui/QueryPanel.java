package presentation.expressui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import businesslogic.expressbl.controller.LogisticQueryController;
import businesslogic.financebl.controller.LogDiaryBLController;
import presentation.commonui.DateChooser;
import presentation.commonui.LocationHelper;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import presentation.special_ui.MySearchField;
import vo.OrderVO;

public class QueryPanel extends OperationPanel {

	private MyLabel clearLabel;
	private MySearchField timeField;
	private MyLabel timeSetLabel;

	private MyTable messageTable;

	private ArrayList<OrderVO> submitOrders;
	private ArrayList<String[]> queryOrders;
	private LocationHelper help;

	private LogisticQueryController controller;
	private LogDiaryBLController log;

	public QueryPanel(LogisticQueryController controller) {

		this.controller = controller;
		log = new LogDiaryBLController();

		clearLabel = new MyLabel("清空");
		timeField = new MySearchField();
		timeSetLabel = new MyLabel("时间");
		DateChooser chooser = new DateChooser(timeField, timeSetLabel);

		queryOrders = new ArrayList<String[]>();

		timeField.setToolTipText("例:2001-12-12");

		// 测试位置时使用
		add(clearLabel);
		add(timeField);
		add(timeSetLabel);

		timeSetLabel.setLayout(new BorderLayout());
		// timeSetLabel.add(new DateChooser(timeField), BorderLayout.CENTER);

		setLayout(null);
		addListener();

		// help = new LocationHelper(this);

		setBaseInfos();

	}

	private void queryOrder(int row) {

		if (messageTable.getValueAt(row, 0) != null) {
			for (OrderVO i : submitOrders)
				if (i.ID.equals(messageTable.getValueAt(row, 0))) {
					showHistory(i.history);

				}
		}
	}

	private void showHistory(ArrayList<String> history) {

		for (String i : history) {
			System.out.println(i);
		}
	}

	public void setBounds(int x, int y, int width, int height) {
		// setBounds
		super.setBounds(x, y, width, height);

		clearLabel.setBounds((int) (width * 1.593724420190996 / 25), (int) (height * 0.967741935483871 / 20),
				(int) (width * 1.9888949522510232 / 25), (int) (height * 0.967741935483871 / 20));
		timeField.setBounds((int) (width * 14.82537517053206 / 25), (int) (height * 1.039426523297491 / 20),
				(int) (width * 4.843110504774898 / 25), 30);
		timeSetLabel.setBounds((int) (width * 21.504911323328785 / 25), (int) (height * 1.039426523297491 / 20),
				(int) (width * 1.9890859481582538 / 25), (int) (height * 0.967741935483871 / 20));

		messageTable.setLocationAndSize((int) (width * 1.2278308321964528 / 25),
				(int) (height * 2.8315412186379927 / 20), (int) (width * 22.68076398362892 / 25),
				(int) (height * 15.878136200716845 / 20));
	}

	private void setBaseInfos() {
		String[] head = new String[] { "订单号", "订单状态" };

		int[] widths = { 305, 310 };

		messageTable = new MyTable(head, getInfos(), widths, false);
		add(messageTable);
	}

	private ArrayList<String[]> getInfos() {
		ArrayList<String[]> infos = new ArrayList<String[]>();

		for (String[] i : queryOrders)
			infos.add(i);

		return infos;
	}

	private void addListener() {

		timeField.getImageLabel().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				submitOrders = controller.query();
				String date = timeField.getText().trim();

				if (date.equals("")) {
					warnning("请输入日期");
					return;
				}

				DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

				// 无语，偷懒

				try {
					Date d = fmt.parse(date);
				} catch (Exception e1) {

					warnning("日期格式不正确");
					return;
				}

				queryOrders = new ArrayList<String[]>();
				for (OrderVO i : submitOrders) {
					if (i.builtDate.equals(date)) {
						String state = null;
						switch (i.order_state) {

						case WAITING_ENVEHICLE:
							state = "等待转运";
							break;
						case TRANSFERING:
							state = "转运中";
							break;
						case WAITING_DISTRIBUTE:
							state = "等待派件";
							break;
						case DISTRIBUEING:
							state = "派件中";
							break;
						case FINISHED:
							state = "完成";
							break;
						}

						queryOrders.add(new String[] { i.ID, state });

					}

				}

				if (queryOrders.isEmpty()) {
					warnning("该天添加的订单数为0");
					return;
				}

				messageTable.setInfos(queryOrders);

			}
		});

		clearLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				queryOrders = new ArrayList<String[]>();
				messageTable.setInfos(queryOrders);
				timeField.setText("");
			}
		});

	}

	private void warnning(String msg) {
		JOptionPane.showMessageDialog(null, msg, "订单信息错误", JOptionPane.INFORMATION_MESSAGE);
	}

}
