package presentation.financeui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import businesslogic.financebl.controller.LogDiaryBLController;
import presentation.commonui.DateChooser;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextField;
import presentation.commonui.OperationPanel;
import vo.LogDiaryVO;

public class LogDiaryPanel extends OperationPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1217858217226372209L;

	private MyLabel startDateLabel;
	private MyLabel dateOKLabel;
	private MyLabel refreshLabel;

	private MyTextField startDate_Input;

	private MyTable logDiaryTable;

	public ArrayList<LogDiaryVO> logDiaryVOs;

	public LogDiaryBLController controller;

	public LogDiaryPanel(LogDiaryBLController controller) {
		this.controller = controller;

		startDateLabel = new MyLabel("日期");
		dateOKLabel = new MyLabel("确认");
		refreshLabel = new MyLabel("刷新");

		startDate_Input = new MyTextField();

		startDateLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				startui();
			}
		});

		dateOKLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dateOK();
			}
		});

		refreshLabel.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				refreshui();
			}
		});

		logDiaryVOs = controller.getAllLogDiaryVOs();
		setBaseInfo();

		setLayout(null);

		add(startDateLabel);
		add(dateOKLabel);
		add(refreshLabel);
		add(startDate_Input);

		startDateLabel.setLayout(new BorderLayout());
		new DateChooser(startDate_Input, startDateLabel);

	}

	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);

		refreshLabel.setBounds((int) (width * 21.01019387755102 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 2.7683673469387754 / 25), (int) (height * 1.1350293542074363 / 20));
		startDateLabel.setBounds((int) (width * 6.896683673469388 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 2.6247448979591837 / 25), (int) (height * 1.1350293542074363 / 20));
		dateOKLabel.setBounds((int) (width * 17.0109693877551 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 2.7683673469387754 / 25), (int) (height * 1.1350293542074363 / 20));
		startDate_Input.setBounds((int) (width * 2.2063775510204085 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 4.0568877551020407 / 25), (int) (height * 1.1350293542074363 / 20));
		logDiaryTable.setLocationAndSize((int) (width * 1.1002551020408165 / 25),
				(int) (height * 2.505479452054795 / 20), (int) (width * 23.007397959183675 / 25),
				(int) (height * 15.921154598825832 / 20));

	}

	private void setBaseInfo() {
		String[] head = new String[] { "日期", "操作人", "操作" };
		int[] widths = new int[] { 210, 110, 304 };

		logDiaryTable = new MyTable(head, getInfos(logDiaryVOs), widths, false);
		add(logDiaryTable);
	}

	private ArrayList<String[]> getInfos(ArrayList<LogDiaryVO> vos) {
		ArrayList<String[]> lineInfo = new ArrayList<String[]>();
		if (vos != null) {
			for (LogDiaryVO v : vos) {
				lineInfo.add(new String[] { v.time, v.userVO.userID, v.info });
			}
			return lineInfo;
		} else {
			return new ArrayList<String[]>();
		}
	}

	public void startui() {

	}

	public void dateOK() {
		String beginTime = startDate_Input.getText();
		if (beginTime.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入需要查询的时间！", "提示", JOptionPane.WARNING_MESSAGE);
		} else {
			ArrayList<LogDiaryVO> vos = controller.getLogDiaryVO(beginTime);
			logDiaryTable.setInfos(getInfos(vos));
		}
	}

	public void refreshui() {
		refreshLabel.reSet();
		ArrayList<LogDiaryVO> logDiaryVOs = controller.getAllLogDiaryVOs();
		logDiaryTable.setInfos(getInfos(logDiaryVOs));
	}

}
