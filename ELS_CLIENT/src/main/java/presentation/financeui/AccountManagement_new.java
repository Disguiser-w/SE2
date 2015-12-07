package presentation.financeui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import businesslogic.financebl.controller.AccountBLController;
import vo.AccountVO;

public class AccountManagement_new extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton infoOKButton;

	private JLabel function;
	private JLabel account_name;
	private JLabel account_money;

	private JTextField account_name_Input;
	private JTextField account_money_Input;

//	private LocationHelper helper;

	public String name;
	public String money;
	
	  AccountBLController controller;
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	public AccountManagement_new(AccountBLController controller) {
		this.controller=controller;
		
		infoOKButton = new JButton("ok");

		function = new JLabel("新增账户");
		account_name = new JLabel("账户名");
		account_money = new JLabel("账户金额");

		account_name_Input = new JTextField("");
		account_money_Input = new JTextField("");
		
		setCmpLocation();

		
	

		add(infoOKButton);
		add(function);
		add(account_name);
		add(account_money);
		add(account_name_Input);
		add(account_money_Input);
//		helper = new LocationHelper(this);
		
		setLayout(null);
		infoOKButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				okui();
			}
		});


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
		name=account_name_Input.getText();
		money=account_money_Input.getText();
		if(name.equals("")||money.equals("")){
			JOptionPane.showMessageDialog(null, "请输入完整信息！", "提示",
					JOptionPane.CLOSED_OPTION);
		}
		else{
			double moneyD=Double.parseDouble(money);
			if(moneyD<0){
				JOptionPane.showMessageDialog(null, "请输入正确的金额！", "提示",
						JOptionPane.CLOSED_OPTION);
				account_money_Input.setText("");
			}
			else{
//				System.out.println("name :"+name);
				AccountVO accountVO=new AccountVO(name, moneyD);
				int result=controller.addAccount(accountVO);
//				System.out.println("name:"+accountVO.getName());

				if(result==0){
					JOptionPane.showMessageDialog(null, "添加账户成功！", "提示",
							JOptionPane.CLOSED_OPTION);
					account_name_Input.setText("");
					account_money_Input.setText("");
				}
				else{
					JOptionPane.showMessageDialog(null, "添加账户失败！", "提示",
							JOptionPane.WARNING_MESSAGE);
				}
				
			}
		}
		
	}
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.setSize(800, 550);
//		frame.add(new AccountManagementPanel_new());
//		frame.setVisible(true);
//	}

	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation();
		repaint();
	}
	
/*	class AddListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			name=account_name_Input.getText();
			money=account_money_Input.getText();
			if(name.equals("")||money.equals("")){
				JOptionPane.showMessageDialog(null, "请输入完整信息！", "提示",
						JOptionPane.CLOSED_OPTION);
			}
			else{
				double moneyD=Double.parseDouble(money);
				if(moneyD<0){
					JOptionPane.showMessageDialog(null, "请输入正确的金额！", "提示",
							JOptionPane.CLOSED_OPTION);
					account_money_Input.setText("");
				}
				else{
					AccountVO accountVO=new AccountVO(name, moneyD);
					int result=abService.addAccount(accountVO);
					if(result==0){
						JOptionPane.showMessageDialog(null, "添加账户成功！", "提示",
								JOptionPane.CLOSED_OPTION);
					}
					else{
						JOptionPane.showMessageDialog(null, "添加账户失败！", "提示",
								JOptionPane.WARNING_MESSAGE);
					}
					
				}
			}
		}
		
	}
	*/
}
