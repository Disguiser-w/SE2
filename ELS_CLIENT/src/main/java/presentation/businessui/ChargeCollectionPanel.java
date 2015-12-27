package presentation.businessui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.businessbl.controller.GatheringController;
import businesslogic.logdiarybl.controller.LogDiaryBLController;
import businesslogic.receiptbl.GetDate;
import presentation.commonui.LocationHelper;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import vo.LogDiaryVO;

public class ChargeCollectionPanel extends OperationPanel {
	// private JLabel tablehead;
	private MyTable messageTable;
	private JLabel totalMessageLabel;
	private MyLabel collectionLabel;

	private LocationHelper helper;

	private ArrayList<String[]> chargeInfos;
	private GatheringController controller;
	private LogDiaryBLController log;

	public ChargeCollectionPanel(GatheringController controller) {

		this.controller = controller;
		log = new LogDiaryBLController();

		totalMessageLabel = new MyTextLabel();
		totalMessageLabel.setBackground(new Color(160, 160, 160, 100));
		totalMessageLabel.setFont(new Font("WenQuanYi Micro Hei", Font.PLAIN, 15));
		totalMessageLabel.setForeground(Color.WHITE);
		totalMessageLabel.setOpaque(true);
		collectionLabel = new MyLabel();

		add(totalMessageLabel);
		add(collectionLabel);
		// helper = new LocationHelper(this);
		setLayout(null);

		chargeInfos = controller.getChargeInfo();
		if (chargeInfos == null) {
			chargeInfos = new ArrayList<String[]>();
		}

		addListener();
		setBaseInfos();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 所有组件setBounds

		messageTable.setLocationAndSize((int) (width * 0.9890859481582538 / 25),
				(int) (height * 1.1827956989247312 / 20), (int) (width * 22.987721691678036 / 25),
				(int) (height * 15.376344086021506 / 20));
		totalMessageLabel.setBounds((int) (width * 0.9890859481582538 / 25), (int) (height * 17.634408602150536 / 20),
				(int) (width * 18.92905866302865 / 25), (int) (height * 1.3261648745519714 / 20));
		collectionLabel.setBounds((int) (width * 22.13506139154161 / 25), (int) (height * 17.634408602150536 / 20),
				(int) (width * 1.8417462482946794 / 25), (int) (height * 1.3261648745519714 / 20));
	}

	private void setBaseInfos() {
		String[] head = new String[] { "快递员", "当日收费" };

		int[] widths = { 314, 310 };

		messageTable = new MyTable(head, getInfos(), widths, false);
		add(messageTable);

	}

	private ArrayList<String[]> getInfos() {
		ArrayList<String[]> infos = controller.getChargeInfo();
		return infos;
	}

	public void addListener() {

		collectionLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				double total = controller.gathering();
				String date = getDate();
				String oID = BusinessMainController.businessVO.organizationVO.organizationID;

				if (total == 0) {
					warnning("已完成今日收款汇总或今日接单树为0");
					return;
				}

				totalMessageLabel.setText(" 日期 : " + date + "  营业厅编号 : " + oID + "  金额总和 : " + total);

				//
				log.addLogDiary(new LogDiaryVO(GetDate.getTime(), BusinessMainController.businessVO, "进行了本日的收款汇总"),
						GetDate.getTime());
			}
		});

	}

	private void warnning(String message) {
		// fix 放到底部信息栏
		JOptionPane.showMessageDialog(null, message, "车辆信息错误", JOptionPane.ERROR_MESSAGE);
	}

	private String getDate() {
		return (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
	}

}
