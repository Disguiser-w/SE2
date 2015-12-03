package presentation.expressui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import businesslogic.expressbl.controller.ReceiptOrderController;

public class FinishedOrderPanel extends JPanel {
	private JTextField orderIDField;
	private JButton queryButton;
	private JTextArea messageArea;

	private JLabel receiveTimeLabel;
	private JLabel receiverNameLabel;
	private JLabel timeSetLabel;
	private JLabel receiverPhoneNumLabel;
	private JTextField timeFieldField;
	private JTextField receiverNameField;
	private JTextField receiverPhoneNumField;

	private JButton confirmButton;

	// private LocationHelper helper;
	private ReceiptOrderController controller;

	public FinishedOrderPanel(ReceiptOrderController controller) {
		this.controller = controller;
		orderIDField = new JTextField();
		queryButton = new JButton();
		messageArea = new JTextArea();

		receiveTimeLabel = new JLabel("收件时间");
		receiverNameLabel = new JLabel("实际收件人");
		receiverPhoneNumLabel = new JLabel("收件人号码");
		timeFieldField = new JTextField();
		receiverNameField = new JTextField();
		receiverPhoneNumField = new JTextField();

		confirmButton = new JButton();
		timeSetLabel = new JLabel();

		receiveTimeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		receiverNameLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		timeSetLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		receiverPhoneNumLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(orderIDField);
		add(queryButton);
		add(messageArea);

		add(receiveTimeLabel);
		add(receiverNameLabel);
		add(timeSetLabel);
		add(receiverPhoneNumLabel);
		add(timeFieldField);
		add(receiverNameField);
		add(receiverPhoneNumField);

		add(confirmButton);

		// helper = new LocationHelper(this);
		setLayout(null);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		orderIDField.setBounds((int) (width * 1.408450704225352 / 25), (int) (height * 1.0267857142857142 / 20),
				(int) (width * 6.722151088348271 / 25), (int) (height * 1.4285714285714286 / 20));
		queryButton.setBounds((int) (width * 14.084507042253522 / 25), (int) (height * 1.0267857142857142 / 20),
				(int) (width * 1.7605633802816902 / 25), (int) (height * 1.3839285714285714 / 20));
		messageArea.setBounds((int) (width * 1.408450704225352 / 25), (int) (height * 3.392857142857143 / 20),
				(int) (width * 22.151088348271447 / 25), (int) (height * 9.508928571428571 / 20));
		receiveTimeLabel.setBounds((int) (width * 1.440460947503201 / 25), (int) (height * 13.883928571428571 / 20),
				(int) (width * 4.257362355953905 / 25), (int) (height * 1.3839285714285714 / 20));
		receiverNameLabel.setBounds((int) (width * 1.440460947503201 / 25), (int) (height * 15.803571428571429 / 20),
				(int) (width * 4.257362355953905 / 25), (int) (height * 1.3839285714285714 / 20));
		timeSetLabel.setBounds((int) (width * 10.723431498079385 / 25), (int) (height * 13.883928571428571 / 20),
				(int) (width * 0.9923175416133163 / 25), (int) (height * 1.3839285714285714 / 20));
		receiverPhoneNumLabel.setBounds((int) (width * 1.440460947503201 / 25),
				(int) (height * 17.723214285714285 / 20), (int) (width * 4.257362355953905 / 25),
				(int) (height * 1.3839285714285714 / 20));
		timeFieldField.setBounds((int) (width * 6.658130601792574 / 25), (int) (height * 13.883928571428571 / 20),
				(int) (width * 3.649167733674776 / 25), (int) (height * 1.4285714285714286 / 20));
		receiverNameField.setBounds((int) (width * 6.658130601792574 / 25), (int) (height * 15.803571428571429 / 20),
				(int) (width * 5.121638924455826 / 25), (int) (height * 1.4285714285714286 / 20));
		receiverPhoneNumField.setBounds((int) (width * 6.658130601792574 / 25),
				(int) (height * 17.723214285714285 / 20), (int) (width * 5.121638924455826 / 25),
				(int) (height * 1.4285714285714286 / 20));
		confirmButton.setBounds((int) (width * 21.542893725992318 / 25), (int) (height * 17.5 / 20),
				(int) (width * 2.0166453265044813 / 25), (int) (height * 1.4285714285714286 / 20));

	}
}
