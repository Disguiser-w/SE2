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
		// timeInputLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		// timeSetLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		// nextPageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		// previousPageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		// messageTable.setBorder(BorderFactory.createLineBorder(Color.black));

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

		// timeInputLabel.setBounds(width / 20, height * 2 / 25, width * 2 / 20,
		// height * 2 / 25);
		// timeField.setBounds(width / 6, height * 2 / 25, width * 3 / 20,
		// height * 2 / 25);
		// timeSetLabel.setBounds(width * 7 / 20 + 5, height * 2 / 25, width * 1
		// / 20, height * 2 / 25);
		// confirmButton.setBounds(width * 2 / 3, height * 2 / 25, width * 1 /
		// 15, height * 2 / 25);
		// messageTable.setBounds(width / 20, height * 6 / 25, width * 9 / 10,
		// height * 3 / 5);
		// nextPageLabel.setBounds(width * 5 / 6, height * 22 / 25, height * 2 /
		// 25, height * 2 / 25);
		// previousPageLabel.setBounds(width * 43 / 48, height * 22 / 25, height
		// * 2 / 25, height * 2 / 25);

		for (JLabel i : queryLabel) {
			i.setBounds(width, height, width, height);

		}

		// 每个组件setBounds
	}

}
