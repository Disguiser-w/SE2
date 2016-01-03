package presentation.intermediateui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import presentation.commonui.UserFrame;
import presentation.special_ui.AddLabel;
import presentation.special_ui.DeleteLabel;
import presentation.special_ui.MySearchField;
import vo.OrderVO;
import businesslogic.intermediatebl.controller.IntermediateMainController;

@SuppressWarnings("serial")
public class TransferingPanel extends OperationPanel {
	private IntermediateMainController controller;

	private UserFrame frame;

	private ArrayList<OrderVO> orderList;

	private MyLabel addButton;
	private MyLabel delButton;
	private MyLabel saveButton;

	private MySearchField inputField;

	private MyTable messageTable;

	public TransferingPanel(IntermediateMainController c, IntermediateFrame f) {
		this.controller = c;
		this.frame = f;

		orderList = controller.getTransferingReceipt().orderList;

		addButton = new AddLabel();
		delButton = new DeleteLabel();
		saveButton = new MyLabel("保存单据");
		inputField = new MySearchField();

		add(addButton);
		add(delButton);
		add(saveButton);

		add(inputField);

		setLayout(null);
		addListener();

		setBaseInfo();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		addButton.setBounds((int) (width * 1.2278308321964528 / 25),
				(int) (height * 1.039426523297491 / 20), 30, 30);
		delButton.setBounds((int) (width * 6.207366984993179 / 25),
				(int) (height * 1.039426523297491 / 20), 30, 30);
		saveButton
				.setBounds((int) (width * 20.6343792633015 / 25),
						(int) (height * 18 / 20),
						(int) (width * 2.830832196452933 / 25),
						(int) (height * 1 / 20));

		inputField.setBounds((int) (width * 15.654843110504775 / 25),
				(int) (height * 1.2186379928315412 / 20), width / 4, 30);
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
		orderList = controller.getTransferingReceipt().orderList;
		for (OrderVO vo : orderList) {
			infos.add(new String[] { vo.ID, vo.senderAddress,
					vo.recipientAddress, vo.order_state.toString(),
					vo.expressType.toString() });
		}

		return infos;
	}

	private void addListener() {

		addButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				messageTable.setInfos(getInfos());
				Transfering_newPanel newPanel = new Transfering_newPanel(
						controller, frame, messageTable);
				frame.changePanel(newPanel);
			}
		});

		delButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				messageTable.setAllSelected(false);

				ArrayList<Integer> selectedIndexs = messageTable
						.getSelectedIndex();
				int size = selectedIndexs.size();
				if (size == 0)
					return;
				else {
					ArrayList<String> IDs = new ArrayList<String>();
					for (int i : selectedIndexs) {
						IDs.add(orderList.get(i).ID);
					}

					for (String s : IDs) {
						try {
							controller.getTransferingBL().deleteOrder(s);
						} catch (Exception e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
					}
					orderList = controller.getTransferingReceipt().orderList;
					messageTable.setInfos(getInfos());
				}
			}
		});

		saveButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					controller.getTransferingBL().saveTransferingReceipt();
					saveSuccessing("中转中心到达单已保存");
				} catch (RemoteException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});

		inputField.getImageLabel().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String id = inputField.getText();
				if (id.equals(""))
					warnning("请输入订单编号");
				boolean searchSuccessful = false;
				for (OrderVO i : controller.getTransferingReceipt().orderList) {
					if (i.ID.equals(id)) {
						WatchPanel_Order watch = new WatchPanel_Order(
								controller, frame, messageTable);
						searchSuccessful = true;
						frame.changePanel(watch);
					}
				}
				if (!searchSuccessful)
					warnning("输入飞机编号有误");
			}
		});
	}

	public void warnning(String message) {
		// fix 放到底部信息栏
		JOptionPane.showMessageDialog(null, message, "订单信息错误",
				JOptionPane.ERROR_MESSAGE);
	}

	public void successing(String message) {
		JOptionPane.showMessageDialog(null, message, "提交成功",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void saveSuccessing(String message) {
		JOptionPane.showMessageDialog(null, message, "保存成功",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
