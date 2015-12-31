package presentation.financeui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import businesslogic.financebl.controller.CollectionReceiptBLController;
import businesslogic.logdiarybl.LogDiaryBL;
import businesslogic.managebl.controller.OrganizationManageController;
import businesslogic.receiptbl.GetDate;
import presentation.commonui.DateChooser;
import presentation.commonui.MyComboBox;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import presentation.logdiaryui.LogDiaryPanel;
import type.OrganizationType;
import type.ReceiptState;
import type.ReceiptType;
import vo.CollectionReceiptVO;
import vo.GatheringReceiptVO;
import vo.LogDiaryVO;
import vo.OrganizationVO;
import vo.UserVO;

/**
 * 暂时先把根据营业厅筛选的去掉了，以后有时间再说吧
 */
public class CollectionReceiptPanel extends OperationPanel {

	private static final long serialVersionUID = 1L;

	private MyLabel dateChooseLabel;
	private MyLabel collectionOKButton;
	private MyLabel totalButton;
	private MyLabel cancelButton;

	// private JLabel function;
	private MyTextLabel date;
	private MyTextLabel businessHall;
	private MyTextLabel infoLine;

	private MyTextField date_Input;
	private String hallID;

	private MyComboBox<String> businessHallID_Input;;

	private MyTable collectionTable;

	private int businesshallInt;
	public CollectionReceiptBLController controller;
	public OrganizationManageController organizationController;
	public FinanceFrame financeFrame;
	public UserVO user;

	private ArrayList<GatheringReceiptVO> gatheringReceiptVOs;

	public AccountManagementPanel_main accountManagementPanel_main;
	public CostIncomeReceiptPanel_new costIncomeReceiptPanel_new;
	public LogDiaryPanel logDiaryPanel;
	public ReceiptPanel_new receiptPanel_new;
	String hallID_str;
	String date_str;

	public CollectionReceiptPanel(CollectionReceiptBLController controller, FinanceFrame parent, UserVO user,
			OrganizationManageController organizationController,
			AccountManagementPanel_main accountManagementPanel_main,
			CostIncomeReceiptPanel_new costIncomeReceiptPanel_new, LogDiaryPanel logDiaryPanel,
			ReceiptPanel_new receiptPanel_new) {
		this.controller = controller;
		this.financeFrame = parent;
		this.user = user;
		this.organizationController = organizationController;

		this.accountManagementPanel_main = accountManagementPanel_main;
		this.costIncomeReceiptPanel_new = costIncomeReceiptPanel_new;
		this.logDiaryPanel = logDiaryPanel;
		this.receiptPanel_new = receiptPanel_new;
		dateChooseLabel = new MyLabel("日期");
		collectionOKButton = new MyLabel("确认");
		totalButton = new MyLabel("合计");
		cancelButton = new MyLabel("返回");

		// function = new JLabel("新建入款单");
		date = new MyTextLabel("日期");
		businessHall = new MyTextLabel("营业厅");
		infoLine = new MyTextLabel();
		infoLine.setText("时间：" + GetDate.getdate() + " 合计金额：0");

		date_Input = new MyTextField("");
		businessHallID_Input = new MyComboBox<String>();
		ArrayList<OrganizationVO> organizationVOs = organizationController.showAllOrganizations();
		businessHallID_Input.addItem("");
		for (OrganizationVO v : organizationVOs) {
			if (v.category == OrganizationType.businessHall) {
				businessHallID_Input.addItem(v.name);
			}
		}

		businesshallInt = -1;
		hallID = "";

		setLayout(null);

		add(dateChooseLabel);
		add(collectionOKButton);
		add(totalButton);
		add(cancelButton);

		// add(function);
		add(date);
		add(businessHall);
		add(infoLine);

		add(date_Input);
		add(businessHallID_Input);
		dateChooseLabel.setLayout(new BorderLayout());
		new DateChooser(date_Input, dateChooseLabel);

		addListener();
		setBaseInfo();

	}

	public void setBaseInfo() {
		String[] head = new String[] { "收款单编号", "营业厅编号", "收款金额" };
		int[] widths = new int[] { 221, 200, 200 };
		collectionTable = new MyTable(head, getInfos(gatheringReceiptVOs), widths, false);
		add(collectionTable);
	}

	public ArrayList<String[]> getInfos(ArrayList<GatheringReceiptVO> gvos) {
		ArrayList<String[]> lineInfo = new ArrayList<String[]>();
		if (gvos != null) {
			for (GatheringReceiptVO v : gvos) {
				lineInfo.add(new String[] { v.receiptID, v.businesshall.organizationID, v.totalmoney + "" });
			}
			return lineInfo;
		} else {
			return new ArrayList<String[]>();
		}
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		dateChooseLabel.setBounds((int) (width * 7.174744897959184 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 2.7683673469387754 / 25), (int) (height * 1.1350293542074363 / 20));
		collectionOKButton.setBounds((int) (width * 21.01019387755102 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 2.7683673469387754 / 25), (int) (height * 1.1350293542074363 / 20));
		totalButton.setBounds((int) (width * 21.01019387755102 / 25), (int) (height * 18.434442270058707 / 20),
				(int) (width * 2.7683673469387754 / 25), (int) (height * 1.1350293542074363 / 20));
		cancelButton.setBounds((int) (width * 20.139795918367346 / 28), (int) (height * 18.434442270058707 / 20),
				(int) (width * 2.7683673469387754 / 25), (int) (height * 1.1350293542074363 / 20));
		// function.setBounds((int)(width * 0.6696428571428571/25),(int)(height
		// * 0.821917808219178/20),(int)(width * 6.919642857142857
		// /25),(int)(height * 1.643835616438356/20));
		date.setBounds((int) (width * 2.391581632653061 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 1.594387755102041 / 25), (int) (height * 0.9001956947162426 / 20));
		businessHall.setBounds((int) (width * 10.356122448979592 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 1.6581632653061225 / 25), (int) (height * 0.8610567514677103 / 20));
		infoLine.setBounds((int) (width * 2.5191326530612246 / 25), (int) (height * 18.12133072407045 / 20),
				(int) (width * 13.424744897959183 / 25), (int) (height * 1.2915851272015655 / 20));
		date_Input.setBounds((int) (width * 3.877295918367347 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 2.874234693877551 / 25), (int) (height * 1.0 / 20));
		businessHallID_Input.setBounds((int) (width * 12.333163265306122 / 25),
				(int) (height * 0.93052837573385516 / 20), (int) (width * 2.874234693877551 / 25),
				(int) (height * 1.0 / 20));
		collectionTable.setLocationAndSize((int) (width * 1.1002551020408165 / 25),
				(int) (height * 2.505479452054795 / 20), (int) (width * 22.92091836734694 / 25),
				(int) (height * 15.921154598825832 / 20));
	}

	private void addListener() {

		/**
		 * 确认日期输入
		 */

		businessHallID_Input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				infookui();
			}
		});

		/**
		 * 确认搜索按钮
		 */
		collectionOKButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				okui();
			}
		});

		/**
		 * 合计按钮
		 */
		totalButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				totalui();
			}
		});

		/**
		 * 取消按钮
		 */
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cancelui();
			}
		});

	}

	/**
	 * 监听方法
	 */
	public void dateChooseui() {

	}

	/**
	 * 输入营业厅信息的方法
	 */
	public void infookui() {
		ArrayList<OrganizationVO> businessHallVOs = new ArrayList<OrganizationVO>();
		for (OrganizationVO v : organizationController.showAllOrganizations()) {
			if (v.category == OrganizationType.businessHall) {
				businessHallVOs.add(v);
			}
		}
		businesshallInt = businessHallID_Input.getSelectedIndex();
		hallID = businessHallVOs.get(businesshallInt).organizationID;
	}

	/**
	 * 确定(只有仅输入日期查询时才可以合计)
	 */
	public void okui() {
		date_str = date_Input.getText();

		if (date_str.equals("") && hallID.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入至少一项信息哟！", "提示", JOptionPane.CLOSED_OPTION);
		}
		// 仅依据时间筛选
		else if (hallID.equals("")) {
			// 因为是采用日历格式所以不会有时间的错误（除非选择的时间已经超过当前时间）
			if (date_str.compareTo(GetDate.getdate()) > 0) {
				JOptionPane.showMessageDialog(null, "您输入的日期还没到呢，请重新输入！", "提示", JOptionPane.CLOSED_OPTION);
			} else {

				gatheringReceiptVOs = controller.getGatheringByTime(date_str);
				collectionTable.setInfos(getInfos(gatheringReceiptVOs));
			}
		}
		// 仅依据营业厅编号筛选
		else if (date_str.equals("")) {
			gatheringReceiptVOs = controller.getGatheringByHall(hallID);
			collectionTable.setInfos(getInfos(gatheringReceiptVOs));
			totalButton.setEnabled(false);
		} else {
			gatheringReceiptVOs = controller.getGatheingByBoth(date_str, hallID);
			collectionTable.setInfos(getInfos(gatheringReceiptVOs));
			totalButton.setEnabled(false);
		}
	}

	/**
	 * 取消
	 */
	public void cancelui() {
		financeFrame.toMainPanel();
	}

	public void totalui() {
		double money = controller.getTotalMoney(controller.getGatheringByTime(date_str));
		infoLine.setText("日期：" + GetDate.getdate() + "    金额总和：" + money);
		CollectionReceiptVO vo = new CollectionReceiptVO(controller.getCollectionListID(), user.userID,
				ReceiptType.COLLECTIONRECEIPT, ReceiptState.SUBMIT, money, GetDate.getdate(), "boss");
		int temp = controller.creatCollection(vo);
		if (temp == 0) {
			controller.excute(vo);
			accountManagementPanel_main.refreshui();
			// 这个refresh好像暂时没有什么用
			costIncomeReceiptPanel_new.refresh();
			LogDiaryBL bl = new LogDiaryBL();
			LogDiaryVO logvo = new LogDiaryVO(GetDate.getTime(), user, "创建了一张合计收款单");
			bl.addLogDiary(logvo, GetDate.getTime());
			logDiaryPanel.refreshui();
			receiptPanel_new.showCollection();
			JOptionPane.showMessageDialog(null, "创建入款单成功！", "提示", JOptionPane.DEFAULT_OPTION);
		} else {
			JOptionPane.showMessageDialog(null, "该入款单已经被创建了！", "提示", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void nextui() {

	}

	public void previousui() {

	}

}
