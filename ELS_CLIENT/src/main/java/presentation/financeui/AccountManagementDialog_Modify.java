package presentation.financeui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import presentation.commonui.LocationHelper;
import businesslogic.financebl.controller.AccountBLController;

public class AccountManagementDialog_Modify extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel function;
	private JLabel nameLabel;
	private JLabel moneyLabel;
	private JButton okButton;
	private JButton cancelButton;
	
	private JTextField name_input;
	private JTextField money_input;
	
	public String name;
	public String money;
	
	AccountBLController controller;
	AccountManagementPanel_main accountMain;
	
//	private LocationHelper helper;

	
	 public AccountManagementDialog_Modify(AccountManagementPanel_main accountMain,AccountBLController controller) {
		// TODO Auto-generated constructor stub
		 this.controller=controller;
		 this.accountMain=accountMain;
		 
		 function=new JLabel("修改用户");
		 nameLabel=new JLabel("账户名");
		 moneyLabel=new JLabel("金额");
		 okButton=new JButton("确认");
		 cancelButton=new JButton("取消");
		 
		 name_input=new JTextField("");
		 money_input=new JTextField("");
		 
		 add(function);
		 add(nameLabel);
		 add(moneyLabel);
		 add(okButton);
		 add(cancelButton);
		 add(name_input);
		 add(money_input);
		 
//			helper = new LocationHelper(this);

		 addListener();
	}
	
	public void addListener(){
		//确认按钮
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
			}
		});
		
		//取消修改按钮
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	public static void main(String[] args){
		AccountBLController controller = null;
		AccountManagementPanel_main accountMain = null;
		JFrame test=new JFrame();
		test.setSize(600, 400);
		test.add(new AccountManagementDialog_Modify(accountMain, controller));
		test.setVisible(true);
	}

}
