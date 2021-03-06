package presentation.financeui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vo.AccountVO;
import businesslogic.financebl.controller.AccountBLController;

public class AccountManagementPanel_modify extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton infoOKButton;
	private JButton cancelButton;

	private JLabel function;
	private JLabel account_name;
	private JLabel account_money;

	private JTextField account_name_Input;
	private JTextField account_money_Input;

//	private LocationHelper helper;

	public String name;
	public String money;
	public String nameInit;
	
	  AccountBLController controller;
	  FinanceFrame financeFrame;
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

 public AccountManagementPanel_modify(AccountBLController controller,String money,String nameInit,FinanceFrame parent) {
		// TODO Auto-generated constructor stub
		this.controller=controller;
		this.money=money;
		this.nameInit=nameInit;
		this.financeFrame=parent;
		
		infoOKButton = new JButton("确认");
		cancelButton =new JButton("取消");

		function = new JLabel("修改账户");
		account_name = new JLabel("账户名");
		account_money = new JLabel("账户金额");

		account_name_Input = new JTextField("");
		account_money_Input = new JTextField(money);
		account_money_Input.setEditable(false);
		
		setCmpLocation();

		
	

		add(infoOKButton);
		add(cancelButton);
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
		
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cancelui();
			}
		});


	}

	public void setCmpLocation() {
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		infoOKButton.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 25 / 48,
				PANEL_WIDTH / 16, PANEL_HEIGHT / 16);
		cancelButton.setBounds(PANEL_WIDTH * 54 / 72, PANEL_HEIGHT * 25 / 48,
				PANEL_WIDTH / 16, PANEL_HEIGHT / 16);
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
		if(name.equals("")){
			JOptionPane.showMessageDialog(null, "请输入完整信息！", "提示",
					JOptionPane.CLOSED_OPTION);
		}
			else{
				AccountVO accountVO=new AccountVO(nameInit, Double.parseDouble(money));
				int result=controller.modifyAccount(accountVO, name);
//				System.out.println(accountVO.getName()+" "+name);
				if(result==0){
					JOptionPane.showMessageDialog(null, "修改账户成功！", "提示",
							JOptionPane.CLOSED_OPTION);
				financeFrame.toMainPanel();
				}
				else{
					JOptionPane.showMessageDialog(null, "修改账户失败！", "提示",
							JOptionPane.WARNING_MESSAGE);
				}
				
			}
		}
	
	public void cancelui(){
		financeFrame.toMainPanel();
	}
		
	
	


	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation();
		repaint();
	}
	
/*	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		AccountBLController controller=new AccountBLController();
	JFrame frame = new JFrame();
//	frame.setSize(600, 400);
	frame.setBounds(300, 200, 600, 400);
	frame.add(new AccountManagementPanel_modify(controller,null,null,null));
	frame.setVisible(true);
}
*/

}
