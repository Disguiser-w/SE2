package presentation.expressui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class ChargeMessageCollectionPanel extends JPanel {

	private JTable messageTable;
	private JLabel totalMessageLabel;
	private JButton collectionButton;

	public ChargeMessageCollectionPanel() {

		messageTable = new JTable();
		totalMessageLabel = new JLabel();
		collectionButton = new JButton();

		messageTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		totalMessageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(messageTable);
		add(totalMessageLabel);
		add(collectionButton);

		setLayout(null);

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 所有组件setBounds
		messageTable.setBounds(width / 25, height / 15, width * 23 / 25, height * 11 / 15);
		totalMessageLabel.setBounds(width / 25, height * 21 / 25, width * 17 / 25, height * 1 / 10);
		collectionButton.setBounds(width * 22 / 25, height * 17 / 20, width * 2 / 25, height * 2 / 25);
		
		
	}

}
