package presentation.expressui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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

	public FinishedOrderPanel() {
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

		setLayout(null);
	}
	
	public void setBounds(int x,int y,int width,int height){
		super.setBounds(x, y, width, height);
	
		
	}
}
