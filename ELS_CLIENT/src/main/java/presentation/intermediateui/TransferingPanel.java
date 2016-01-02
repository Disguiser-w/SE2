package presentation.intermediateui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.sql.Savepoint;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import businesslogic.intermediatebl.controller.IntermediateMainController;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextField;
import presentation.commonui.OperationPanel;
import presentation.commonui.UserFrame;
import presentation.special_ui.AddLabel;
import presentation.special_ui.DeleteLabel;
import vo.OrderVO;

public class TransferingPanel extends OperationPanel {
	private IntermediateMainController controller;

	private UserFrame frame;

	private ArrayList<OrderVO> orderList;

	private MyLabel addButton;
	private MyLabel delButton;
	private MyLabel saveButton;

	private MyTextField inputField;
	private MyLabel confirmButton;

	private MyTable messageTable;

	private int selectedIndex;

	public TransferingPanel(IntermediateMainController c, IntermediateFrame f) {
		this.controller = c;
		this.frame = f;

		orderList = controller.getTransferingReceipt().orderList;

		addButton = new AddLabel();
		delButton = new DeleteLabel();
		saveButton = new MyLabel("存");
		inputField = new MyTextField();
		confirmButton = new MyLabel();

		selectedIndex = -1;

		add(addButton);
		add(delButton);
		add(saveButton);

		add(inputField);
		add(confirmButton);

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
		saveButton.setBounds((int) (width * 11.084583901773533 / 25),
				(int) (height * 1.039426523297491 / 20),
				(int) (width * 1.4324693042291952 / 25),
				(int) (height * 1.3978494623655915 / 20));

		inputField.setBounds((int) (width * 15.654843110504775 / 25),
				(int) (height * 1.2186379928315412 / 20),
				(int) (width * 5.320600272851296 / 25),
				(int) (height * 1.075268817204301 / 20));
		confirmButton.setBounds((int) (width * 21.350613915416098 / 25),
				(int) (height * 1.2186379928315412 / 20),
				(int) (width * 1.6371077762619373 / 25),
				(int) (height * 1.039426523297491 / 20));
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
				} catch (RemoteException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});

		confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String id = inputField.getText();
				if (id.equals(""))
					warnning("请输入订单号");

				for (OrderVO vo : orderList) {
					if (vo.ID.equals(id)) {
						frame.changePanel(new WatchPanel_Order(controller,
								frame, messageTable, vo.ID));
					}
				}
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
				JOptionPane.DEFAULT_OPTION);
	}
}
