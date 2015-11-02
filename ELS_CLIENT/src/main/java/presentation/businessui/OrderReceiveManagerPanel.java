package presentation.businessui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import presentation.commonui.LocationHelper;

public class OrderReceiveManagerPanel extends JPanel {
	private JLabel inputLabel;
	private JLabel sendLabel;
	private JLabel printLabel;
	private JLabel nextPageLabel;
	private JLabel previousPageLabel;

	private JButton confirmButton;
	private JTable messageTable;
	private JTextField orderNumField;

	// 定为
	private LocationHelper helper;

	public OrderReceiveManagerPanel() {

		inputLabel = new JLabel("输入订单号");
		sendLabel = new JLabel();
		printLabel = new JLabel();
		nextPageLabel = new JLabel();
		previousPageLabel = new JLabel();

		confirmButton = new JButton();

		orderNumField = new JTextField();
		messageTable = new JTable();

		add(inputLabel);
		add(sendLabel);
		add(printLabel);
		add(nextPageLabel);
		add(previousPageLabel);
		add(confirmButton);
		add(orderNumField);
		add(messageTable);

		// inputLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// sendLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// printLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// nextPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// previousPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// confirmButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// orderNumField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// messageTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		helper = new LocationHelper(this);

		setLayout(null);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 组件setBounds;
	}

}
