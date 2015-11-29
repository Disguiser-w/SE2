package presentation.businessui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import presentation.commonui.LocationHelper;

public class ChargeCollectionPanel extends JPanel {
	// private JLabel printLabel;
	// private JLabel sendLabel;
	private JTable messageTable;
	private JLabel totalMessageLabel;
	private JButton collectionButton;
	private LocationHelper helper;

	public ChargeCollectionPanel() {
		// printLabel = new JLabel();
		// sendLabel = new JLabel();
		messageTable = new JTable();
		totalMessageLabel = new JLabel();
		collectionButton = new JButton();

		messageTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		totalMessageLabel
				.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// printLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// sendLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(messageTable);
		add(totalMessageLabel);
		add(collectionButton);
		// add(printLabel);
		// add(sendLabel);

		helper = new LocationHelper(this);
		setLayout(null);

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 所有组件setBounds

		totalMessageLabel.setBounds((int) (width * 0.9923175416133163 / 25),
				(int) (height * 16.785714285714285 / 20),
				(int) (width * 16.99743918053777 / 25),
				(int) (height * 1.9642857142857142 / 20));

		// sendLabel.setBounds((int) (width * 22.37516005121639 / 25), (int)
		// (height * 0.8482142857142857 / 20),
		// (int) (width * 1.5685019206145967 / 25), (int) (height * 2.1875 /
		// 20));
		// printLabel.setBounds((int) (width * 20.03841229193342 / 25), (int)
		// (height * 0.8482142857142857 / 20),
		// (int) (width * 1.5685019206145967 / 25), (int) (height * 2.1875 /
		// 20));

		collectionButton.setBounds((int) (width * 21.9910371318822 / 25),
				(int) (height * 17.232142857142858 / 20),
				(int) (width * 1.9846350832266326 / 25),
				(int) (height * 1.3839285714285714 / 20));
		messageTable.setBounds((int) (width * 0.9923175416133163 / 25),
				(int) (height * 1.2946428571428572 / 20),
				(int) (width * 23.01536491677337 / 25),
				(int) (height * 14.642857142857142 / 20));

	}

}
