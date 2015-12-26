package presentation.financeui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import businesslogic.financebl.controller.AccountBLController;
import businesslogic.logdiarybl.LogDiaryBL;
import businesslogic.receiptbl.getDate;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import presentation.logdiaryui.LogDiaryPanel;
import vo.AccountVO;
import vo.LogDiaryVO;
import vo.UserVO;

public class AccountManagement_new extends OperationPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MyLabel infoOKButton;
	private MyLabel cancelButton;

	private MyTextLabel account_name;
	private MyTextLabel account_money;

	private MyTextField account_name_Input;
	private MyTextField account_money_Input;
	
	private AccountManagementPanel_main accountMainPanel;


	public String name;
	public String money;
	
	  AccountBLController controller;
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	
	FinanceFrame financeFrame;
	public UserVO userVO;
	public LogDiaryPanel logDiaryPanel;

	public AccountManagement_new(AccountBLController controller,FinanceFrame parent,AccountManagementPanel_main accountMainPanel,
			UserVO userVO,LogDiaryPanel logDiaryPanel) {
		this.controller=controller;
		this.financeFrame=parent;
		this.accountMainPanel=accountMainPanel;
		this.logDiaryPanel = logDiaryPanel;
		this.userVO=userVO;
		infoOKButton = new MyLabel("确认");
		cancelButton =new MyLabel("返回");

		account_name = new MyTextLabel("账户名");
		account_money = new MyTextLabel("账户金额");

		account_name_Input = new MyTextField("");
		account_money_Input = new MyTextField("");
		
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
				PANEL_WIDTH *7/ 24, PANEL_HEIGHT / 16);
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
					JOptionPane.WARNING_MESSAGE);
		}
		else{
			/**
			 * 如果输入的是汉字
			 * */
			try{
			double moneyD=Double.parseDouble(money);
			if(moneyD<0){
				JOptionPane.showMessageDialog(null, "请输入正确的金额！", "提示",
						JOptionPane.WARNING_MESSAGE);
				account_money_Input.setText("");
			}
			else{
				AccountVO accountVO=new AccountVO(name, moneyD);
				int result=controller.addAccount(accountVO);

				if(result==0){
					returnui();
					LogDiaryBL log = new LogDiaryBL();
					LogDiaryVO vo = new LogDiaryVO(getDate.getdate().substring(0, 4)+"-"+getDate.getdate().substring(4, 6)+"-"+getDate.getdate().substring(6), userVO, "新增了一个账户");
					log.addLogDiary(vo, getDate.getdate().substring(0, 4)+"-"+getDate.getdate().substring(4, 6)+"-"+getDate.getdate().substring(6));
					logDiaryPanel.refreshui();
					JOptionPane.showMessageDialog(null, "添加账户成功！", "提示",
							JOptionPane.DEFAULT_OPTION);
					account_name_Input.setText("");
					account_money_Input.setText("");
				}
				else{
					JOptionPane.showMessageDialog(null, "该账户名已存在！", "提示",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "请输入正确的金额数字！", "提示",
					JOptionPane.WARNING_MESSAGE);
			account_money_Input.setText("");
		}
		}
	}
	
	public void cancelui(){
		financeFrame.toMainPanel();
	}
	
/*	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		AccountBLController controller=new AccountBLController();
		FinanceFrame financeFrame=new FinanceFrame();
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new AccountManagement_new(controller,financeFrame));
		frame.setVisible(true);
	}
	*/

	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation();
		repaint();
	}
	
	/**
	 * 返回上一层界面
	 * */
	public void returnui(){
		accountMainPanel.refreshui();
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
