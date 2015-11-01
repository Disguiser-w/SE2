package presentation.businessui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class OrderReceiveManagerPanel extends JPanel {
	private JLabel inputLabel;
	private JLabel sendLabel;
	private JLabel printLabel;
	private JLabel nextPageLabel;
	private JLabel previousPageLabel;

	private JButton confirmButton;
	private JTable messageTable;
	private JTextField orderNumField;

	public OrderReceiveManagerPanel() {
		inputLabel = new JLabel("输入订单号");
		sendLabel = new JLabel();
		printLabel = new JLabel();
		nextPageLabel = new JLabel();
		previousPageLabel = new JLabel();

		confirmButton = new JButton();
		messageTable = new JTable();
		orderNumField = new JTextField();

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 组件setBounds;
	}

}
