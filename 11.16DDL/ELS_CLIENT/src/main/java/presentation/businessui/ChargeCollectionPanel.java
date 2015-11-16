package presentation.businessui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class ChargeCollectionPanel extends JPanel {
	private JLabel printLabel;
	private JLabel sendLabel;
	private JTable messageTable;
	private JLabel totalMessageLabel;
	private JButton collectionButton;

	public ChargeCollectionPanel() {
		printLabel = new JLabel();
		sendLabel = new JLabel();
		messageTable = new JTable();
		totalMessageLabel = new JLabel();
		collectionButton = new JButton();

		messageTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		totalMessageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		printLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sendLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(messageTable);
		add(totalMessageLabel);
		add(collectionButton);
		add(printLabel);
		add(sendLabel);

		setLayout(null);

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 所有组件setBounds
		printLabel.setBounds(width * 19 / 25, height / 30, width * 2 / 25, height * 2 / 15);
		sendLabel.setBounds(width * 22 / 25, height / 30, width * 2 / 25, height * 2 / 15);
		messageTable.setBounds(width / 25, height * 3 / 15, width * 23 / 25, height * 9 / 15);
		totalMessageLabel.setBounds(width / 25, height * 21 / 25, width * 17 / 25, height * 1 / 10);
		collectionButton.setBounds(width * 22 / 25, height * 17 / 20, width * 2 / 25, height * 2 / 25);

	}

}
