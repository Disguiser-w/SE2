package presentation.financeui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import businesslogic.financebl.LogDiaryBL;
import businesslogic.financebl.controller.AccountBLController;
import businesslogic.receiptbl.GetDate;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import vo.AccountVO;
import vo.LogDiaryVO;
import vo.UserVO;

public class AccountManagementPanel_modify extends OperationPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MyLabel infoOKButton;
	private MyLabel cancelButton;

//	private JLabel function;
	private MyTextLabel account_name;
	private MyTextLabel account_money;

	private MyTextField account_name_Input;
	private MyTextField account_money_Input;
	
	private AccountManagementPanel_main accountMainPanel;
	public LogDiaryPanel logDiaryPanel;


	public String name;
	public String money;
	public String nameInit;
	
	  AccountBLController controller;
	  FinanceFrame financeFrame;
	  public UserVO userVO;
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

 public AccountManagementPanel_modify(AccountBLController controller,String money,String nameInit,FinanceFrame parent,AccountManagementPanel_main accountMainPanel,
		 UserVO userVO,LogDiaryPanel logDiaryPanel) {
		this.controller=controller;
		this.money=money;
		this.nameInit=nameInit;
		this.financeFrame=parent;
		this.accountMainPanel=accountMainPanel;
		this.userVO = userVO;
		this.logDiaryPanel = logDiaryPanel;
		infoOKButton = new MyLabel("确认");
		cancelButton =new MyLabel("取消");

		account_name = new MyTextLabel("账户名");
		account_money = new MyTextLabel("账户金额");

		account_name_Input = new MyTextField("");
		account_money_Input = new MyTextField(money);
		account_money_Input.setEditable(false);
		
		setCmpLocation();

		
	

		add(infoOKButton);
		add(cancelButton);
		add(account_name);
		add(account_money);
		add(account_name_Input);
		add(account_money_Input);
		
		setLayout(null);
		infoOKButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				okui();
				}
		});
		
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cancelui();
				}
		});


	}

	public void setCmpLocation() {
		
		infoOKButton.setBounds(PANEL_WIDTH * 53 / 72, PANEL_HEIGHT * 25 / 48,
				PANEL_WIDTH / 10, PANEL_HEIGHT / 18);
		cancelButton.setBounds(PANEL_WIDTH *45/ 72,  PANEL_HEIGHT * 25 / 48, 
				PANEL_WIDTH / 10, PANEL_HEIGHT / 18);
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
					LogDiaryVO vo = new LogDiaryVO(GetDate.getTime(), userVO, "修改了一个账户");
					log.addLogDiary(vo,GetDate.getTime());
					logDiaryPanel.refreshui();
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
