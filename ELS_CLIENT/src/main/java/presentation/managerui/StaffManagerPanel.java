package presentation.managerui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import presentation.commonui.LocationHelper;

public class StaffManagerPanel extends JPanel {
	// 确认按钮
	private JButton confirmButton;

	// 机构信息
	private JTable messageTable;

	// 添加
	private JLabel addLabel;

	// 删除
	private JLabel delLabel;

	// 修改
	private JLabel modifyLabel;

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

	public StaffManagerPanel() {
		confirmButton = new JButton();
		messageTable = new JTable();

		addLabel = new JLabel();
		delLabel = new JLabel();
		modifyLabel = new JLabel();

		queryField = new JTextField();
		queryButton = new JButton();
		previousPageLabel = new JLabel();
		nextPageLabel = new JLabel();

		add(confirmButton);
		add(messageTable);
		add(addLabel);
		add(delLabel);
		add(modifyLabel);
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
		confirmButton.setBounds((int) (width * 12.163892445582587 / 25), (int) (height * 17.946428571428573 / 20),
				(int) (width * 1.9206145966709347 / 25), (int) (height * 1.25 / 20));
		messageTable.setBounds((int) (width * 1.9846350832266326 / 25), (int) (height * 4.464285714285714 / 20),
				(int) (width * 22.02304737516005 / 25), (int) (height * 12.723214285714286 / 20));
		addLabel.setBounds((int) (width * 4.12932138284251 / 25), (int) (height * 1.2946428571428572 / 20),
				(int) (width * 1.5364916773367479 / 25), (int) (height * 2.142857142857143 / 20));
		delLabel.setBounds((int) (width * 8.482714468629961 / 25), (int) (height * 1.2946428571428572 / 20),
				(int) (width * 1.5364916773367479 / 25), (int) (height * 2.142857142857143 / 20));
		modifyLabel.setBounds((int) (width * 12.836107554417413 / 25), (int) (height * 1.2946428571428572 / 20),
				(int) (width * 1.5364916773367479 / 25), (int) (height * 2.142857142857143 / 20));
		queryField.setBounds((int) (width * 16.005121638924457 / 25), (int) (height * 1.7410714285714286 / 20),
				(int) (width * 4.353393085787452 / 25), (int) (height * 1.3392857142857142 / 20));
		queryButton.setBounds((int) (width * 21.286811779769526 / 25), (int) (height * 1.7410714285714286 / 20),
				(int) (width * 1.6005121638924455 / 25), (int) (height * 1.2946428571428572 / 20));
		previousPageLabel.setBounds((int) (width * 17.6696542893726 / 25), (int) (height * 17.857142857142858 / 20),
				(int) (width * 1.120358514724712 / 25), (int) (height * 1.5178571428571428 / 20));
		nextPageLabel.setBounds((int) (width * 20.774647887323944 / 25), (int) (height * 17.857142857142858 / 20),
				(int) (width * 1.088348271446863 / 25), (int) (height * 1.5178571428571428 / 20));

	}

}
