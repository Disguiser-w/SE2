package presentation.intermediateui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import presentation.special_ui.MySearchField;
import vo.OrderVO;
import businesslogic.intermediatebl.controller.IntermediateMainController;

@SuppressWarnings("serial")
public class EnvehiclePanel extends OperationPanel {
	private IntermediateMainController controller;

	private ArrayList<OrderVO> waitingOrderList;

	private MyLabel envehicle;
	private MyLabel saveButton;

	private MySearchField inputField;

	private MyTable messageTable;

	public EnvehiclePanel(IntermediateMainController c) {
		this.controller = c;

		waitingOrderList = controller.getEnvehicleBL().getWaitingOrderList();

		envehicle = new MyLabel("do");
		saveButton = new MyLabel("cun");

		inputField = new MySearchField();

		add(envehicle);
		add(inputField);
		add(saveButton);

		setLayout(null);
		addListener();
		setBaseInfo();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		envehicle.setBounds((int) (width * 1.2278308321964528 / 25),
				(int) (height * 1.039426523297491 / 20),
				(int) (width * 1.4324693042291952 / 25),
				(int) (height * 1.3978494623655915 / 20));
		saveButton.setBounds((int) (width * 6.207366984993179 / 25),
				(int) (height * 1.039426523297491 / 20),
				(int) (width * 1.4324693042291952 / 25),
				(int) (height * 1.3978494623655915 / 20));
		inputField.setBounds((int) (width * 15.654843110504775 / 25),
				(int) (height * 1.2186379928315412 / 20),
				(int) (width * 5.320600272851296 / 25), 30);
		messageTable.setLocationAndSize(
				(int) (width * 1.0243277848911652 / 25),
				(int) (height * 3.369175627240143 / 20),
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
		waitingOrderList = controller.getEnvehicleBL().getWaitingOrderList();
		for (OrderVO vo : waitingOrderList) {
			infos.add(new String[] { vo.ID, vo.senderAddress,
					vo.recipientAddress, vo.order_state.toString(),
					vo.expressType.toString() });
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
