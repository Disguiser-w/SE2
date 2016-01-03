package presentation.intermediateui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import vo.OrderVO;
import businesslogic.intermediatebl.controller.IntermediateMainController;

@SuppressWarnings("serial")
public class EnvehiclePanel extends OperationPanel {
	private IntermediateMainController controller;

	private ArrayList<OrderVO> waitingOrderList;

	private MyLabel envehicle;
	private MyLabel saveButton;
	private MyLabel refresh;

	private MyTable messageTable;

	public EnvehiclePanel(IntermediateMainController c) {
		this.controller = c;

		waitingOrderList = controller.getEnvehicleBL().getWaitingOrderList();

		envehicle = new MyLabel("一键装车");
		saveButton = new MyLabel("保存单据");
		refresh = new MyLabel("刷新");

		add(envehicle);
		add(saveButton);
		add(refresh);

		setLayout(null);
		addListener();
		setBaseInfo();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		envehicle
				.setBounds((int) (width * 20 / 25), (int) (height * 18 / 20),
						(int) (width * 2.830832196452933 / 25),
						(int) (height * 1 / 20));
		saveButton
				.setBounds((int) (width * 17 / 25), (int) (height * 18 / 20),
						(int) (width * 2.830832196452933 / 25),
						(int) (height * 1 / 20));
		refresh.setBounds((int) (width * 14 / 25), (int) (height * 18 / 20),
				(int) (width * 2.830832196452933 / 25), (int) (height * 1 / 20));
		messageTable.setLocationAndSize(
				(int) (width * 1.0243277848911652 / 25),
				(int) (height * 5 / 40),
				(int) (width * 22.98335467349552 / 25),
				(int) (height * 15.412186379928315 / 20));
	}

	private void setBaseInfo() {
		String[] head = new String[] { "订单编号", "出发地", "到达地", "状态", "订单种类" };

		int[] widths = { 233, 90, 90, 90, 90 };

		messageTable = new MyTable(head, getInfos(), widths, true);
		add(messageTable);
	}

	private ArrayList<String[]> getInfos() {
		ArrayList<String[]> infos = new ArrayList<String[]>();
		waitingOrderList = controller.getEnvehicleBL()
				.getAllocateWwaitingOrderBL().updateWaitingList();
		for (OrderVO vo : waitingOrderList) {
			String state = "";
			switch (vo.order_state) {
			case WAITING_ENVEHICLE:
				state = "等待装车";
				break;
			case TRANSFERING:
				state = "中转中";
				break;
			case WAITING_DISTRIBUTE:
				state = "等待派件";
				break;
			case DISTRIBUEING:
				state = "派件中";
				break;
			case FINISHED:
				state = "已完成";
				break;
			}
			String type = "";
			switch (vo.expressType) {
			case ECONOMIC:
				type = "经济型";
				break;
			case FAST:
				type = "特快型";
				break;
			case STANDARD:
				type = "标准型";
				break;
			}
			infos.add(new String[] { vo.ID, vo.senderAddress,
					vo.recipientAddress, state, type });
		}

		return infos;
	}

	private void addListener() {
		envehicle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					controller.getEnvehicleBL().envehicle();
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});

		saveButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					controller.getTransferingBL().saveTransferingReceipt();
					saveSuccessing("装车单信息已保存");
				} catch (RemoteException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});

		refresh.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				messageTable.setInfos(getInfos());
			}
		});
	}

	public void warnning(String message) {
		// fix 放到底部信息栏
		JOptionPane.showMessageDialog(null, message, "装车信息错误",
				JOptionPane.ERROR_MESSAGE);
	}

	public void successing(String message) {
		JOptionPane.showMessageDialog(null, message, "装车成功",
				JOptionPane.DEFAULT_OPTION);
	}

	public void saveSuccessing(String message) {
		JOptionPane.showMessageDialog(null, message, "保存成功",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
