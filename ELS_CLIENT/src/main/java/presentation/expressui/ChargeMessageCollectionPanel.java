package presentation.expressui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import businesslogic.expressbl.controller.ChargeCollectionController;

public class ChargeMessageCollectionPanel extends JPanel {

	private JTable messageTable;
	private JLabel totalMessageLabel;
	private JButton collectionButton;
	// private LocationHelper helper;

	private ChargeCollectionController controller;

	public ChargeMessageCollectionPanel() {

		messageTable = new JTable();
		totalMessageLabel = new JLabel();
		collectionButton = new JButton();

		messageTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		totalMessageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(messageTable);
		add(totalMessageLabel);
		add(collectionButton);

		// helper = new LocationHelper(this);
		setLayout(null);
		controller = new ChargeCollectionController();
		setBaseInfo();
		addListener();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 所有组件setBounds

		messageTable.setBounds((int) (width * 1.1523687580025608 / 25), (int) (height * 1.4285714285714286 / 20),
				(int) (width * 22.471190781049938 / 25), (int) (height * 14.375 / 20));
		totalMessageLabel.setBounds((int) (width * 1.1523687580025608 / 25), (int) (height * 17.008928571428573 / 20),
				(int) (width * 16.99743918053777 / 25), (int) (height * 1.9642857142857142 / 20));
		collectionButton.setBounds((int) (width * 21.638924455825865 / 25), (int) (height * 17.142857142857142 / 20),
				(int) (width * 1.9846350832266326 / 25), (int) (height * 1.5625 / 20));

	}

	private void setBaseInfo() {

	}

	private void addListener() {

	}

}
