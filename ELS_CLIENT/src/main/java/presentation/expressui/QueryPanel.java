package presentation.expressui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import presentation.commonui.LocationHelper;

public class QueryPanel extends JPanel {
	private JLabel timeInputLabel;
	private JTextField timeField;
	private JLabel timeSetLabel;
	private JButton confirmButton;
	private JLabel nextPageLabel;
	private JLabel previousPageLabel;
	private JTable messageTable;
	private ArrayList<JLabel> queryLabel;
	private LocationHelper help;

	public QueryPanel() {
		timeInputLabel = new JLabel("请输入时间");
		timeField = new JTextField();
		timeSetLabel = new JLabel();
		confirmButton = new JButton();
		nextPageLabel = new JLabel(">");
		previousPageLabel = new JLabel("<");
		messageTable = new JTable();
		queryLabel = new ArrayList<JLabel>();

		// 测试位置时使用
		timeInputLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		timeSetLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		nextPageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		previousPageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		messageTable.setBorder(BorderFactory.createLineBorder(Color.black));

		for (JLabel i : queryLabel)
			i.setBorder(BorderFactory.createLineBorder(Color.black));
		timeInputLabel.setHorizontalAlignment(JLabel.CENTER);

		add(timeInputLabel);
		add(timeField);
		add(timeSetLabel);
		add(confirmButton);
		add(nextPageLabel);
		add(previousPageLabel);
		add(messageTable);
		for (JLabel i : queryLabel) {
			// add(i);
		}

		help = new LocationHelper(this);

		setLayout(null);
	}

	public void setBounds(int x, int y, int width, int height) {
		// setBounds
		super.setBounds(x, y, width, height);
		timeInputLabel.setBounds((int) (width * 1.408450704225352 / 25), (int) (height * 0.9821428571428571 / 20),
				(int) (width * 3.7451984635083226 / 25), (int) (height * 1.4285714285714286 / 20));
		timeField.setBounds((int) (width * 6.113956466069142 / 25), (int) (height * 0.9821428571428571 / 20),
				(int) (width * 4.481434058898848 / 25), (int) (height * 1.4732142857142858 / 20));
		timeSetLabel.setBounds((int) (width * 10.59539052496799 / 25), (int) (height * 0.9821428571428571 / 20),
				(int) (width * 1.0243277848911652 / 25), (int) (height * 1.4285714285714286 / 20));
		confirmButton.setBounds((int) (width * 21.222791293213827 / 25), (int) (height * 0.9821428571428571 / 20),
				(int) (width * 2.3367477592829706 / 25), (int) (height * 1.4285714285714286 / 20));
		nextPageLabel.setBounds((int) (width * 21.9910371318822 / 25), (int) (height * 17.589285714285715 / 20),
				(int) (width * 1.088348271446863 / 25), (int) (height * 1.5178571428571428 / 20));
		previousPageLabel.setBounds((int) (width * 20.230473751600513 / 25), (int) (height * 17.589285714285715 / 20),
				(int) (width * 1.088348271446863 / 25), (int) (height * 1.5178571428571428 / 20));
		messageTable.setBounds((int) (width * 1.408450704225352 / 25), (int) (height * 3.392857142857143 / 20),
				(int) (width * 22.151088348271447 / 25), (int) (height * 13.258928571428571 / 20));
		for (JLabel i : queryLabel) {
			i.setBounds(width, height, width, height);

		}

		// 每个组件setBounds
	}

}
