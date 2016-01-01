package presentation.expressui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import businesslogic.expressbl.controller.ChargeCollectionController;
import businesslogic.expressbl.controller.ExpressMainController;
import presentation.commonui.LocationHelper;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import presentation.commonui.UserFrame;

public class ChargeMessageCollectionPanel extends OperationPanel {

	private MyTable messageTable;
	private JLabel totalMessageLabel;
	private MyLabel refreshLabel;

	private ChargeCollectionController controller;
	private LocationHelper helper;

	public ChargeMessageCollectionPanel(ChargeCollectionController controller) {
		this.controller = controller;

		totalMessageLabel = new MyTextLabel();
		totalMessageLabel.setBackground(new Color(200, 200, 200, 0));
		totalMessageLabel.setBorder(BorderFactory.createLineBorder(new Color(190, 190, 190, 125)));
		totalMessageLabel.setOpaque(true);
		refreshLabel = new MyLabel("刷新");

		add(totalMessageLabel);
		add(refreshLabel);

		// helper = new LocationHelper(this);

		setLayout(null);
		addListener();
		setBaseInfos();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 所有组件setBounds

		messageTable.setLocationAndSize((int) (width * 0.9890859481582538 / 25),
				(int) (height * 1.1827956989247312 / 20), (int) (width * 22.987721691678036 / 25),
				(int) (height * 15.376344086021506 / 20));

		totalMessageLabel.setBounds((int) (width * 1.2890859481582538 / 25), (int) (height * 17.434408602150536 / 20),
				(int) (width * 18.92905866302865 / 25), (int) (height * 1.3261648745519714 / 20));
		refreshLabel.setBounds((int) (width * 21.33506139154161 / 25), (int) (height * 17.434408602150536 / 20),
				(int) (width * 2.3417462482946794 / 25), (int) (height * 1.3261648745519714 / 20));

	}

	private void addListener() {
		refreshLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setLabelInfo();
				setBaseInfos();
			}
		});
	}

	// 设置载入动态的内容
	private void setBaseInfos() {

		setLabelInfo();
		String[] head = new String[] { "订单号", "收费" };

		int[] widths = { 314, 310 };

		messageTable = new MyTable(head, getInfos(), widths, false);
		add(messageTable);
	}

	private void setLabelInfo() {
		// 信息label
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
		String time = f.format(date);

		// expressID从expressVO处获

		if (ExpressMainController.expressVO.chargeCollection.size() != 0)
			totalMessageLabel.setText("日期 : " + time + "  快递员编号 : " + ExpressMainController.expressVO.userID
					+ "  金额总和 : " + ExpressMainController.expressVO.chargeCollection.get(0));
		else
			totalMessageLabel.setText(
					"日期 : " + time + "  快递员编号 : " + ExpressMainController.expressVO.userID + "  金额总和 : " + "0");
		totalMessageLabel.setHorizontalAlignment(JLabel.CENTER);
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
		if (UserFrame.type == UserFrame.TYPE_0)
			totalMessageLabel.setForeground(Color.BLACK);
		else if (UserFrame.type == UserFrame.TYPE_1)
			totalMessageLabel.setForeground(Color.WHITE);

	}
}