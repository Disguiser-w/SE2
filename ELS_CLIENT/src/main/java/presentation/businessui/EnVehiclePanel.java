package presentation.businessui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.businessbl.controller.EnVehicleController;
import businesslogic.financebl.controller.LogDiaryBLController;
import businesslogic.receiptbl.GetDate;
import presentation.commonui.LocationHelper;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import vo.LogDiaryVO;

public class EnVehiclePanel extends OperationPanel {
	private MyTable messageTable;

	private MyLabel distributeLabel;
	private MyLabel confirmLabel;

	private ArrayList<String[]> result;
	private EnVehicleController controller;

	private LocationHelper helper;
	private LogDiaryBLController log;

	public EnVehiclePanel(EnVehicleController controller) {
		this.controller = controller;
		log = new LogDiaryBLController();

		distributeLabel = new MyLabel("一键装车");
		confirmLabel = new MyLabel("确认");

		result = new ArrayList<String[]>();
		add(distributeLabel);
		add(confirmLabel);

		// helper = new LocationHelper(this);
		setLayout(null);
		addListener();
		setBaseInfos();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 设置组件位置

		messageTable.setLocationAndSize((int) (width * 1.159618008185539 / 25),
				(int) (height * 1.2903225806451613 / 20), (int) (width * 22.646657571623464 / 25),
				(int) (height * 15.878136200716845 / 20));
		distributeLabel.setBounds((int) (width * 17.32605729877217 / 25), (int) (height * 17.491039426523297 / 20),
				(int) (width * 2.830832196452933 / 25), (int) (height * 1.3261648745519714 / 20));
		confirmLabel.setBounds((int) (width * 20.6343792633015 / 25), (int) (height * 17.491039426523297 / 20),
				(int) (width * 2.830832196452933 / 25), (int) (height * 1.3261648745519714 / 20));
	}

	public void addListener() {

		confirmLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				result = new ArrayList<String[]>();
				messageTable.setInfos(result);

			}
		});
		distributeLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				result = controller.autoTruckLoading();
				if (result.size() == 0)
					warnning("不存在需要装车的订单");
				else {
					messageTable.setInfos(result);
					log.addLogDiary(new LogDiaryVO(GetDate.getTime(), BusinessMainController.businessVO, "进行了一次装车"),
							GetDate.getTime());
				}
			}
		});

	}

	private void setBaseInfos() {
		String[] head = new String[] { "车辆ID", "出发地", "目的地", "订单号" };

		int[] widths = { 180, 127, 128, 180 };

		messageTable = new MyTable(head, new ArrayList<String[]>(), widths, false);
		add(messageTable);
	}

	//
	//
	// int index = num * 8 + rowIndex;
	// if (index > result.size() - 1)
	// return null;
	// String[] infos = result.get(index).split(" ");
	//
	// return infos[columnIndex];

	private void warnning(String message) {
		// fix 放到底部信息栏
		JOptionPane.showMessageDialog(null, message, "", JOptionPane.ERROR_MESSAGE);
	}
}
