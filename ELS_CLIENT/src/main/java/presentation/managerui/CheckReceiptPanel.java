package presentation.managerui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

//import presentation.commonui.LocationHelper;

public class CheckReceiptPanel extends JPanel {
	
	private static final long serialVersionUID = 2489522262816361616L;
	
	// 刷新
	private JLabel refreshLabel;
	// 时间
	private JLabel timeLabel;

	// 更改
	private JButton modifyButton;
	// 通过
	private JButton passButton;

	// 机构信息
	private JTable messageTable;

	// 查询信息输入
	private JTextField queryField;

	// 查询按钮
	private JButton queryButton;

	private JLabel previousPageLabel;
	private JLabel nextPageLabel;

//	private LocationHelper helper;

	public CheckReceiptPanel() {

		refreshLabel = new JLabel();
		timeLabel = new JLabel();
		modifyButton = new JButton();
		passButton = new JButton();
		messageTable = new JTable();

		queryField = new JTextField();
		queryButton = new JButton();
		previousPageLabel = new JLabel();
		nextPageLabel = new JLabel();

		add(refreshLabel);
		add(timeLabel);
		add(modifyButton);
		add(passButton);

		add(messageTable);

		add(queryField);
		add(queryButton);
		add(previousPageLabel);
		add(nextPageLabel);

//		helper = new LocationHelper(this);
		
		setLayout(null);

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		//

		refreshLabel.setBounds((int) (width * 1.9846350832266326 / 25), (int) (height * 1.1607142857142858 / 20),
				(int) (width * 1.6965428937259923 / 25), (int) (height * 2.3660714285714284 / 20));
		timeLabel.setBounds((int) (width * 7.522407170294494 / 25), (int) (height * 1.1607142857142858 / 20),
				(int) (width * 1.6965428937259923 / 25), (int) (height * 2.3660714285714284 / 20));
		modifyButton.setBounds((int) (width * 10.755441741357235 / 25), (int) (height * 17.991071428571427 / 20),
				(int) (width * 1.824583866837388 / 25), (int) (height * 1.3392857142857142 / 20));
		passButton.setBounds((int) (width * 14.084507042253522 / 25), (int) (height * 17.991071428571427 / 20),
				(int) (width * 1.824583866837388 / 25), (int) (height * 1.3392857142857142 / 20));
		messageTable.setBounds((int) (width * 1.9846350832266326 / 25), (int) (height * 4.464285714285714 / 20),
				(int) (width * 22.02304737516005 / 25), (int) (height * 12.723214285714286 / 20));
		queryField.setBounds((int) (width * 16.005121638924457 / 25), (int) (height * 1.7410714285714286 / 20),
				(int) (width * 4.353393085787452 / 25), (int) (height * 1.3392857142857142 / 20));
		queryButton.setBounds((int) (width * 21.286811779769526 / 25), (int) (height * 1.7410714285714286 / 20),
				(int) (width * 1.6005121638924455 / 25), (int) (height * 1.2946428571428572 / 20));
		previousPageLabel.setBounds((int) (width * 18.85403329065301 / 25), (int) (height * 17.857142857142858 / 20),
				(int) (width * 1.120358514724712 / 25), (int) (height * 1.5178571428571428 / 20));
		nextPageLabel.setBounds((int) (width * 21.959026888604352 / 25), (int) (height * 17.857142857142858 / 20),
				(int) (width * 1.088348271446863 / 25), (int) (height * 1.5178571428571428 / 20));
	}

}
