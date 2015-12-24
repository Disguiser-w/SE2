package presentation.financeui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import businesslogic.financebl.controller.AccountBLController;
import businesslogic.logdiarybl.LogDiaryBL;
import businesslogic.receiptbl.getDate;
import presentation.commonui.OperationPanel;
import vo.AccountVO;
import vo.LogDiaryVO;
import vo.UserVO;

public class AccountManagementPanel_modify extends OperationPanel{

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
	
	private AccountManagementPanel_main accountMainPanel;


	public String name;
	public String money;
	public String nameInit;
	
	  AccountBLController controller;
	  FinanceFrame financeFrame;
	  public UserVO userVO;
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

 public AccountManagementPanel_modify(AccountBLController controller,String money,String nameInit,FinanceFrame parent,AccountManagementPanel_main accountMainPanel,
		 UserVO userVO) {
		this.controller=controller;
		this.money=money;
		this.nameInit=nameInit;
		this.financeFrame=parent;
		this.accountMainPanel=accountMainPanel;
		this.userVO = userVO;
		
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
		
		setLayout(null);
		infoOKButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
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
				if(result==0){
					accountMainPanel.refreshui();
					LogDiaryBL log = new LogDiaryBL();
					LogDiaryVO vo = new LogDiaryVO(getDate.getdate(), userVO, "修改了一个账户");
					log.addLogDiary(vo, getDate.getdate());
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

}
