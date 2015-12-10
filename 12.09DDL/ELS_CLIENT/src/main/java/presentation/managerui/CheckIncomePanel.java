package presentation.managerui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import presentation.commonui.LocationHelper;

public class CheckIncomePanel extends JPanel {
	private JTable messageTable;

	// 查询信息输入
	private JTextField queryField;

	// 查询按钮
	private JButton queryButton;

	// 上一面
	private JLabel previousPageLabel;

	// 下一面
	private JLabel nextPageLabel;

	// 位置设置
//	private LocationHelper helper;

	public CheckIncomePanel() {
		messageTable = new JTable();
		queryField = new JTextField();
		queryButton = new JButton();
		previousPageLabel = new JLabel();
		nextPageLabel = new JLabel();

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
		messageTable.setBounds((int) (width * 1.9846350832266326 / 25), (int) (height * 4.017857142857143 / 20),
				(int) (width * 22.0550576184379 / 25), (int) (height * 13.258928571428571 / 20));
		queryField.setBounds((int) (width * 16.005121638924457 / 25), (int) (height * 1.5625 / 20),
				(int) (width * 4.353393085787452 / 25), (int) (height * 1.3392857142857142 / 20));
		queryButton.setBounds((int) (width * 21.286811779769526 / 25), (int) (height * 1.5625 / 20),
				(int) (width * 1.6005121638924455 / 25), (int) (height * 1.2946428571428572 / 20));
		previousPageLabel.setBounds((int) (width * 17.6696542893726 / 25), (int) (height * 17.857142857142858 / 20),
				(int) (width * 1.120358514724712 / 25), (int) (height * 1.5178571428571428 / 20));
		nextPageLabel.setBounds((int) (width * 20.774647887323944 / 25), (int) (height * 17.857142857142858 / 20),
				(int) (width * 1.088348271446863 / 25), (int) (height * 1.5178571428571428 / 20));

	}

}
