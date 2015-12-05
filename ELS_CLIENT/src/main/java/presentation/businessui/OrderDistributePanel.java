package presentation.businessui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import businesslogic.businessbl.controller.DistributeOrderController;
import presentation.commonui.LocationHelper;

public class OrderDistributePanel extends JPanel {
	private JTable messageTable;
	private JLabel nextPageLabel;
	private JLabel previousPageLabel;
	private JButton confirmButton;
	private LocationHelper helper;
	private JLabel sendLabel;
	private JLabel printLabel;
	
	private DistributeOrderController controller;

	public OrderDistributePanel(DistributeOrderController controller) {
		this.controller = controller;
		messageTable = new JTable();
		nextPageLabel = new JLabel();
		previousPageLabel = new JLabel();
		confirmButton = new JButton();
		sendLabel = new JLabel();
		printLabel = new JLabel();

		messageTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nextPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		previousPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(messageTable);
		add(nextPageLabel);
		add(previousPageLabel);
		add(confirmButton);
		add(sendLabel);
		add(printLabel);

		helper = new LocationHelper(this);
		setLayout(null);

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 设置组件位置
		messageTable.setBounds((int) (width * 0.9923175416133163 / 25), (int) (height * 3.9732142857142856 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 11.964285714285714 / 20));
		nextPageLabel.setBounds((int) (width * 13.380281690140846 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.0243277848911652 / 25), (int) (height * 1.4732142857142858 / 20));
		previousPageLabel.setBounds((int) (width * 11.555697823303458 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.0243277848911652 / 25), (int) (height * 1.4732142857142858 / 20));

		confirmButton.setBounds((int) (width * 21.9910371318822 / 25), (int) (height * 17.232142857142858 / 20),
				(int) (width * 1.9846350832266326 / 25), (int) (height * 1.3839285714285714 / 20));

		sendLabel.setBounds((int) (width * 22.37516005121639 / 25), (int) (height * 0.8482142857142857 / 20),
				(int) (width * 1.5685019206145967 / 25), (int) (height * 2.1875 / 20));
		printLabel.setBounds((int) (width * 20.03841229193342 / 25), (int) (height * 0.8482142857142857 / 20),
				(int) (width * 1.5685019206145967 / 25), (int) (height * 2.1875 / 20));
	}
}
