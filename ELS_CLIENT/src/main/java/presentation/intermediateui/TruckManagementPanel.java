package presentation.intermediateui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import businesslogic.intermediatebl.controller.IntermediateMainController;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextField;
import presentation.commonui.OperationPanel;
import presentation.commonui.UserFrame;
import vo.TruckVO;

public class TruckManagementPanel extends OperationPanel {
	private IntermediateMainController controller;

	private UserFrame frame;

	private ArrayList<TruckVO> truckList;

	private MyLabel addButton;
	private MyLabel delButton;
	private MyLabel modifyButton;

	private MyTextField inputField;
	private MyLabel confirmButton;

	private MyTable messageTable;

	private int selectedIndex;

	public TruckManagementPanel(IntermediateMainController c, UserFrame f) {
		this.controller = c;
		this.frame = f;

		truckList = c.getTruckList();

		addButton = new MyLabel("增");
		delButton = new MyLabel("删");
		modifyButton = new MyLabel("改");

		inputField = new MyTextField();
		confirmButton = new MyLabel();

		selectedIndex = -1;

		add(addButton);
		add(delButton);
		add(modifyButton);

		add(inputField);
		add(confirmButton);

		setLayout(null);
		addListener();

		setBaseInfos();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		addButton.setBounds((int) (width * 1.2278308321964528 / 25),
				(int) (height * 1.039426523297491 / 20),
				(int) (width * 1.4324693042291952 / 25),
				(int) (height * 1.3978494623655915 / 20));
		delButton.setBounds((int) (width * 6.207366984993179 / 25),
				(int) (height * 1.039426523297491 / 20),
				(int) (width * 1.4324693042291952 / 25),
				(int) (height * 1.3978494623655915 / 20));
		modifyButton.setBounds((int) (width * 11.084583901773533 / 25),
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

	private void setBaseInfos() {
		String[] head = new String[] { "汽车编号", "所属机构", "目的地", "运送费用", "工作状态" };

		int[] widths = { 80, 196, 60, 197, 60 };
		
		messageTable = new MyTable(head, getInfos(), widths, true);
		add(messageTable);
	}

	private ArrayList<String[]> getInfos() {
		ArrayList<String[]> infos = new ArrayList<String[]>();
		truckList = controller.getTruckList();
		for (TruckVO vo : truckList) {
			String sex = null;
			infos.add(new String[] { vo.ID,
					controller.getIntermediateCentre().name, vo.destination,
					vo.farePrice + "", "工作中" });
		}

		return infos;
	}

	private void addListener() {

		addButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
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
					messageTable.setInfos(getInfos());
				}
			}
		});

		confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String id = inputField.getText();
				if (id.equals(""))
					warnning("请输入汽车");

				for (TruckVO vo : truckList) {
					if (vo.ID.equals(id)) {
						frame.changePanel(new WatchPanel_Management(controller,
								frame, messageTable, vo, 0));
					}
				}
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

	public static void main(String[] args) throws MalformedURLException,
			RemoteException, NotBoundException {
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new TruckManagementPanel(new IntermediateMainController(
				"141250185"), new UserFrame("任婧雯", "CW-00001")));
		frame.setVisible(true);
	}
}
