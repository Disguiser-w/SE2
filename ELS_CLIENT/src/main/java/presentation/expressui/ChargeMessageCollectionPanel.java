package presentation.expressui;

import java.awt.Color;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;

import businesslogic.expressbl.controller.ChargeCollectionController;
import businesslogic.expressbl.controller.ExpressMainController;
import presentation.commonui.LocationHelper;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;

public class ChargeMessageCollectionPanel extends OperationPanel {

	private MyTable messageTable;
	private JLabel totalMessageLabel;

	private ChargeCollectionController controller;
	private LocationHelper helper;

	public ChargeMessageCollectionPanel(ChargeCollectionController controller) {
		this.controller = controller;

		totalMessageLabel = new MyTextLabel();
		totalMessageLabel.setBackground(new Color(235, 235, 235));
		totalMessageLabel.setOpaque(true);

		add(totalMessageLabel);

		// helper = new LocationHelper(this);

		setLayout(null);
		setBaseInfos();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 所有组件setBounds
		totalMessageLabel.setBounds((int) (width * 1.0572987721691678 / 25), (int) (height * 17.56272401433692 / 20),
				(int) (width * 22.919508867667123 / 25), (int) (height * 1.7921146953405018 / 20));
		messageTable.setLocationAndSize((int) (width * 1.0572987721691678 / 25),
				(int) (height * 1.146953405017921 / 20), (int) (width * 22.919508867667123 / 25),
				(int) (height * 15.770609318996415 / 20));

	}

	// 设置载入动态的内容
	private void setBaseInfos() {
		// 信息label
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
		String time = f.format(date);

		// expressID从expressVO处获
		if (ExpressMainController.expressVO.chargeCollection.size() != 0)
			totalMessageLabel.setText("日期 : " + time + "  快递员编号 : " + ExpressMainController.expressVO.ID + "  金额总和 : "
					+ ExpressMainController.expressVO.chargeCollection.get(0));
		else
			totalMessageLabel
					.setText("日期 : " + time + "  快递员编号 : " + ExpressMainController.expressVO.ID + "  金额总和 : " + "0");
		totalMessageLabel.setHorizontalAlignment(JLabel.CENTER);
		String[] head = new String[] { "订单号", "收费" };

		int[] widths = { 330, 292 };

		messageTable = new MyTable(head, getInfos(), widths, false);
		add(messageTable);

	}

	private ArrayList<String[]> getInfos() {
		ArrayList<String[]> infos = new ArrayList<String[]>();
		ArrayList<String> info = ExpressMainController.expressVO.chargeCollection;
		info.remove(0);
		for (String i : info)
			infos.add(i.split(" "));

		return infos;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ExpressMainController.updateExpressInfo();
		messageTable.setInfos(getInfos());
	}
}