package presentation.intermediateui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import presentation.commonui.UserFrame;
import presentation.special_ui.AddLabel;
import presentation.special_ui.DeleteLabel;
import presentation.special_ui.ModifyLabel;
import presentation.special_ui.MySearchField;
import vo.TruckVO;
import businesslogic.intermediatebl.controller.IntermediateMainController;

@SuppressWarnings("serial")
public class TruckManagementPanel extends OperationPanel {
	private IntermediateMainController controller;

	private UserFrame frame;

	private ArrayList<TruckVO> truckList;

	private MyLabel addButton;
	private MyLabel delButton;
	private MyLabel modifyButton;

	private MySearchField inputField;

	private MyTable messageTable;

	private int selectedIndex;

	public TruckManagementPanel(IntermediateMainController c, UserFrame f) {
		this.controller = c;
		this.frame = f;

		truckList = c.getTruckList();

		addButton = new AddLabel();
		delButton = new DeleteLabel();
		modifyButton = new ModifyLabel();

		inputField = new MySearchField();

		selectedIndex = -1;

		add(addButton);
		add(delButton);
		add(modifyButton);

		add(inputField);

		setLayout(null);
		addListener();

		setBaseInfos();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		addButton.setBounds((int) (width * 1.2278308321964528 / 25),
				(int) (height * 1.039426523297491 / 20), 30, 30);
		delButton.setBounds((int) (width * 6.207366984993179 / 25),
				(int) (height * 1.039426523297491 / 20), 30, 30);
		modifyButton.setBounds((int) (width * 11.084583901773533 / 25),
				(int) (height * 1.039426523297491 / 20), 30, 30);

		inputField.setBounds((int) (width * 15.654843110504775 / 25),
				(int) (height * 1.2186379928315412 / 20), width / 4, 30);
		messageTable.setLocationAndSize(
				(int) (width * 1.0243277848911652 / 25),
				(int) (height * 3.369175627240143 / 20),
				(int) (width * 22.98335467349552 / 25),
				(int) (height * 15.412186379928315 / 20));
	}

	private void setBaseInfos() {
		String[] head = new String[] { "汽车编号", "所属机构", "目的地", "运送费用", "工作状态" };

		int[] widths = { 80, 196, 60, 197, 60 };

		messageTable = new MyTable(head, getInfos(truckList), widths, true);
		add(messageTable);
	}

	private ArrayList<String[]> getInfos(ArrayList<TruckVO> truckList) {
		ArrayList<String[]> infos = new ArrayList<String[]>();
		for (TruckVO vo : truckList) {
			infos.add(new String[] { vo.ID,
					controller.getIntermediateCentre().name, vo.destination,
					vo.farePrice + "", "工作中" });
		}

		return infos;
	}

	private void addListener() {

		addButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				messageTable.setInfos(getInfos(truckList));
				Management_newPanel newPanel = new Management_newPanel(
						controller, frame, messageTable, "truck");
				frame.changePanel(newPanel);
			}
		});

		modifyButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ArrayList<Integer> selectedIndexs = messageTable
						.getSelectedIndex();
				int size = selectedIndexs.size();

				if (size != 1)
					return;

				selectedIndex = selectedIndexs.get(0);
				Management_modifyPanel modifyPanel = new Management_modifyPanel(
						controller, frame, messageTable, truckList
								.get(selectedIndex), selectedIndex);
				messageTable.cancelSelected(selectedIndex);
				frame.changePanel(modifyPanel);
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
						IDs.add(truckList.get(i).ID);
					}

					for (String s : IDs) {
						try {
							controller.getTruckManagerBL().deleteTruck(s);
						} catch (Exception e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
					}
					truckList = controller.getTruckList();
					messageTable.setInfos(getInfos(truckList));
				}
			}
		});

		inputField.getImageLabel().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String id = inputField.getText();
				if (id.equals(""))
					warnning("请输入汽车编号");
				boolean searchSuccessful = false;
				for (TruckVO i : controller.getTruckList()) {
					if (i.ID.equals(id)) {
						WatchPanel_Management watch = new WatchPanel_Management(
								controller, frame, messageTable, i, 0);
						searchSuccessful = true;
						frame.changePanel(watch);
					}
				}
				
				if (!searchSuccessful)
					warnning("输入火车编号有误");
			}
		});
	}

	public void warnning(String message) {
		// fix 放到底部信息栏
		JOptionPane.showMessageDialog(null, message, "汽车信息错误",
				JOptionPane.ERROR_MESSAGE);
	}

	public void successing(String message) {
		JOptionPane.showMessageDialog(null, message, "提交成功",
				JOptionPane.DEFAULT_OPTION);
	}
}
