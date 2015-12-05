package presentation.businessui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import businesslogic.businessbl.controller.AcceptCargoController;
import presentation.commonui.LocationHelper;

public class OrderReceiveManagerPanel extends JPanel {
	private JLabel inputLabel;
	private JLabel sendLabel;
	private JLabel printLabel;
	private JLabel nextPageLabel;
	private JLabel previousPageLabel;

	private JButton inputComfirmButton;
	private JButton confirmButton;
	private JTable messageTable;
	private JTextField orderNumField;

	// 定为
	private LocationHelper helper;
	private AcceptCargoController acceptCargoController;
	
	public OrderReceiveManagerPanel(AcceptCargoController acceptCargoController) {
		this.acceptCargoController = acceptCargoController;
		inputLabel = new JLabel("输入订单号");
		sendLabel = new JLabel();
		printLabel = new JLabel();
		nextPageLabel = new JLabel();
		previousPageLabel = new JLabel();

		inputComfirmButton = new JButton();
		confirmButton = new JButton();

		orderNumField = new JTextField();
		messageTable = new JTable();

		add(inputLabel);
		add(sendLabel);
		add(printLabel);
		add(nextPageLabel);
		add(previousPageLabel);
		add(inputComfirmButton);
		add(confirmButton);
		add(orderNumField);
		add(messageTable);

		inputLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sendLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		printLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nextPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		previousPageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		confirmButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		orderNumField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		messageTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		helper = new LocationHelper(this);

	
		setLayout(null);
	}

	

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		// 组件setBounds;

		inputLabel.setBounds((int) (width * 1.0243277848911652 / 25), (int) (height * 1.4285714285714286 / 20),
				(int) (width * 2.9449423815621 / 25), (int) (height * 1.4285714285714286 / 20));
		sendLabel.setBounds((int) (width * 22.37516005121639 / 25), (int) (height * 0.8482142857142857 / 20),
				(int) (width * 1.5685019206145967 / 25), (int) (height * 2.1875 / 20));
		printLabel.setBounds((int) (width * 20.03841229193342 / 25), (int) (height * 0.8482142857142857 / 20),
				(int) (width * 1.5685019206145967 / 25), (int) (height * 2.1875 / 20));
		nextPageLabel.setBounds((int) (width * 13.380281690140846 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.0243277848911652 / 25), (int) (height * 1.4732142857142858 / 20));
		previousPageLabel.setBounds((int) (width * 11.555697823303458 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.0243277848911652 / 25), (int) (height * 1.4732142857142858 / 20));
		inputComfirmButton.setBounds((int) (width * 10.787451984635084 / 25), (int) (height * 1.4285714285714286 / 20),
				(int) (width * 1.8565941101152368 / 25), (int) (height * 1.4285714285714286 / 20));
		confirmButton.setBounds((int) (width * 21.9910371318822 / 25), (int) (height * 17.232142857142858 / 20),
				(int) (width * 1.9846350832266326 / 25), (int) (height * 1.3839285714285714 / 20));
		messageTable.setBounds((int) (width * 0.9923175416133163 / 25), (int) (height * 3.9732142857142856 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 11.964285714285714 / 20));
		orderNumField.setBounds((int) (width * 5.153649167733675 / 25), (int) (height * 1.4285714285714286 / 20),
				(int) (width * 4.161331626120359 / 25), (int) (height * 1.4732142857142858 / 20));
	}
}
