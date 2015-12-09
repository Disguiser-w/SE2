package presentation.repertoryui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ViewInventoryPanel extends JPanel {
	private JTable messageTable;
	private JButton confirmButton;

	// 起始时间
	private JLabel startTimeLabel;
	private JTextField startTImeField;
	private JLabel startTimeChooseLabel;

	// 结束时间
	private JLabel endTimeLabel;
	private JTextField endTImeField;
	private JLabel endTimeChooseLabel;

	// 上一面
	private JLabel previousPageLabel;

	// 下一面
	private JLabel nextPageLabel;

	// 位置设置
	// private LocationHelper helper;

	public ViewInventoryPanel() {
		messageTable = new JTable();
		confirmButton = new JButton();

		startTimeLabel = new JLabel();
		startTImeField = new JTextField();
		startTimeChooseLabel = new JLabel();

		// 结束时间
		endTimeLabel = new JLabel();
		endTImeField = new JTextField();
		endTimeChooseLabel = new JLabel();

		previousPageLabel = new JLabel();
		nextPageLabel = new JLabel();

		add(messageTable);
		add(confirmButton);
		add(startTimeLabel);
		add(startTImeField);
		add(startTimeChooseLabel);
		add(endTimeLabel);
		add(endTImeField);
		add(endTimeChooseLabel);
		add(previousPageLabel);
		add(nextPageLabel);

		// helper = new LocationHelper(this);
		setLayout(null);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		//
		messageTable.setBounds((int) (width * 1.5044814340588988 / 25), (int) (height * 4.017857142857143 / 20),
				(int) (width * 22.0550576184379 / 25), (int) (height * 13.258928571428571 / 20));
		confirmButton.setBounds((int) (width * 21.76696542893726 / 25), (int) (height * 1.3839285714285714 / 20),
				(int) (width * 1.792573623559539 / 25), (int) (height * 1.2946428571428572 / 20));
		startTimeLabel.setBounds((int) (width * 1.5685019206145967 / 25), (int) (height * 1.3839285714285714 / 20),
				(int) (width * 2.240717029449424 / 25), (int) (height * 1.2946428571428572 / 20));
		startTImeField.setBounds((int) (width * 4.097311139564661 / 25), (int) (height * 1.3839285714285714 / 20),
				(int) (width * 3.297055057618438 / 25), (int) (height * 1.3392857142857142 / 20));
		startTimeChooseLabel.setBounds((int) (width * 7.554417413572343 / 25), (int) (height * 1.3839285714285714 / 20),
				(int) (width * 0.9603072983354674 / 25), (int) (height * 1.2946428571428572 / 20));
		endTimeLabel.setBounds((int) (width * 11.971830985915492 / 25), (int) (height * 1.3839285714285714 / 20),
				(int) (width * 2.240717029449424 / 25), (int) (height * 1.2946428571428572 / 20));
		endTImeField.setBounds((int) (width * 14.500640204865556 / 25), (int) (height * 1.3839285714285714 / 20),
				(int) (width * 3.297055057618438 / 25), (int) (height * 1.3392857142857142 / 20));
		endTimeChooseLabel.setBounds((int) (width * 17.95774647887324 / 25), (int) (height * 1.3839285714285714 / 20),
				(int) (width * 0.9603072983354674 / 25), (int) (height * 1.2946428571428572 / 20));
		previousPageLabel.setBounds((int) (width * 17.6696542893726 / 25), (int) (height * 17.857142857142858 / 20),
				(int) (width * 1.120358514724712 / 25), (int) (height * 1.5178571428571428 / 20));
		nextPageLabel.setBounds((int) (width * 20.774647887323944 / 25), (int) (height * 17.857142857142858 / 20),
				(int) (width * 1.088348271446863 / 25), (int) (height * 1.5178571428571428 / 20));

	}
}
