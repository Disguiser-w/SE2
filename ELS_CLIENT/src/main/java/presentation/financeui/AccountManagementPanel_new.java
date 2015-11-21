package presentation.financeui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AccountManagementPanel_new extends JLabel {
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	private JButton infoOKButton;

	private JLabel function;
	private JLabel account_name;
	private JLabel account_money;

	private JTextField account_name_Input;
	private JTextField account_money_Input;

	public AccountManagementPanel_new() {
		infoOKButton = new JButton("ok");

		function = new JLabel("新增账户");
		account_name = new JLabel("账户名");
		account_money = new JLabel("账户金额");

		account_name_Input = new JTextField("CW-00001");
		account_money_Input = new JTextField("2500");
		
		setCmpLocation();

		infoOKButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				okui();
			}
		});

		setLayout(null);

		add(infoOKButton);
		add(function);
		add(account_money);
		add(account_money_Input);
		add(account_name);
		add(account_name_Input);
	}

	public void setCmpLocation() {
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		infoOKButton.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 25 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		account_name.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT / 4,
				PANEL_WIDTH * 7 / 24, PANEL_HEIGHT / 16);
		account_money.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 17 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		account_name_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT / 4,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		account_money_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 17 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
	}
	
	public void okui(){
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new AccountManagementPanel_new());
		frame.setVisible(true);
	}

	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation();
		repaint();
	}
}
