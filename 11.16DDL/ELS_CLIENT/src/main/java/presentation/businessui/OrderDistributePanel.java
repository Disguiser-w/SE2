package presentation.businessui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class OrderDistributePanel extends JPanel {
	private JTable messageTable;
	private JLabel nextPageLabel;
	private JLabel previousPageLabel;
	private JButton confirmButton;

	public OrderDistributePanel() {
		messageTable = new JTable();
		nextPageLabel = new JLabel();
		previousPageLabel = new JLabel();
		confirmButton = new JButton();

		messageTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nextPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		previousPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(messageTable);
		add(nextPageLabel);
		add(previousPageLabel);
		add(confirmButton);

		setLayout(null);

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 设置组件位置
		messageTable.setBounds(width / 25, height / 20, width * 23 / 25, height * 3 / 4);
		nextPageLabel.setBounds(width * 27 / 50, height * 13 / 15, height / 15, height / 15);
		previousPageLabel.setBounds(width * 12 / 25, height * 13 / 15, height / 15, height / 15);
		confirmButton.setBounds(width * 22 / 25, height * 13 / 15, width * 2 / 25, height / 15);
	}
}
