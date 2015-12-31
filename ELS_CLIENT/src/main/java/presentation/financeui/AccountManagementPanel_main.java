package presentation.financeui;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import businesslogic.financebl.LogDiaryBL;
import businesslogic.financebl.controller.AccountBLController;
import businesslogic.receiptbl.GetDate;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import presentation.special_ui.AddLabel;
import presentation.special_ui.DeleteLabel;
import presentation.special_ui.ModifyLabel;
import presentation.special_ui.MySearchField;
import vo.AccountVO;
import vo.LogDiaryVO;
import vo.UserVO;

public class AccountManagementPanel_main extends OperationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private AddLabel addLabel;
	private DeleteLabel deleteLabel;
	private ModifyLabel modifyLabel;
	private MyLabel refreshLabel;
	
	private MySearchField searchTextField;
//	private JLabel function;
	
	private MyTable accountTable;

	AccountBLController controller;
	ArrayList<AccountVO> accountVOs;
	FinanceFrame financeFrame;
	UserVO userVO;
	private int selectedIndex;
	
	public LogDiaryPanel logDiaryPanel;
	
	public AccountManagementPanel_main(AccountBLController controller,FinanceFrame parent,UserVO userVO,LogDiaryPanel logDiaryPanel) {
		this.controller=controller;
		this.financeFrame=parent;
		this.userVO = userVO;
		this.logDiaryPanel = logDiaryPanel;
		addLabel = new AddLabel("添加");
		deleteLabel = new DeleteLabel("删除");
		modifyLabel=new ModifyLabel("修改");
		refreshLabel=new MyLabel("刷新");

		
//		function = new JLabel("账户管理");

		searchTextField = new MySearchField();
		
		setLayout(null);

		add(addLabel);
		add(deleteLabel);
		add(modifyLabel);
		add(refreshLabel);
		add(searchTextField);
//		add(function);
		accountVOs = controller.showAll();
		
		addListener();
		setBaseInfo();
	}

	public void addListener(){
		addLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				addui();
			}
		});

		deleteLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				deleteui();
			}
		});
		
		modifyLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				modifyui();
			}
		});

		searchTextField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				searchui();
			}
		});
		
		refreshLabel.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				refreshui();
			}
		});
	}


	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		addLabel.setBounds(70, 30, 30, 30);
		deleteLabel.setBounds(185, 30, 30, 30);
		modifyLabel.setBounds(300,30, 30, 30);
		refreshLabel.setBounds((int)(width * 21.557653061224488/25),(int)(height * 0.9001956947162426/20),(int)(width *  2.232142857142857 /25),(int)(height *  1.0890019569471623/20));
		searchTextField.setBounds((int)(width * 14.85969387755102/25),(int)(height *0.9001956947162426/20),(int)(width *   4.232142857142857 /25),(int)(height *  1.08986301369863/20));

		accountTable.setLocationAndSize((int)(width * 1.1002551020408165/25),(int)(height * 2.505479452054795/20),(int)(width *  23.007397959183675 /25),(int)(height *  15.921154598825832/20));
	}
	
	 /**
     * 设置表格的基本内容
     * 设置MyTable 的基本信息
     * */
	private void setBaseInfo() {
		String head[] = new String[]{"名称","金额"};
		int[] width ={297,297};
		accountTable = new MyTable(head,getInfos(), width, true);
		add(accountTable);
	}
	
	/**
	 * Mytable中的信息设置
	 * */
	public ArrayList<String[]> getInfos(){
		ArrayList<String[]> lineInfo = new ArrayList<String[]>();
		accountVOs = controller.showAll();
		if(accountVOs!= null){
		for(AccountVO v :  accountVOs){
			lineInfo.add(new String[]{v.name,v.money+""});
		}
		return lineInfo;
		}
		else{
			return new ArrayList<String[]>();
		}
		
	}
		
	
	/**
	 *设置 Mytable 中信息的改写
	 * */
	public ArrayList<String[]> newGetInfos(ArrayList<AccountVO> vos){
		ArrayList<String[]> lineInfo = new ArrayList<String[]>();
		if(vos == null){
			return null;
		}
		else{
		for(AccountVO v :  vos){
			lineInfo.add(new String[]{v.name,v.money+""});
		}
		return lineInfo;
		}
		}
	
	/**
	 * Mytable中信息更新
	 * */
	public void updateTable(){
		accountTable.setInfos(getInfos());
	}

	
	

	public void addui() {
		addLabel.reSet();
		financeFrame.changePanel(new AccountManagement_new(controller,financeFrame,this,userVO,logDiaryPanel));
	}

	public void deleteui() {

		ArrayList<Integer> selectedIndexs = accountTable.getSelectedIndex();
		int size = selectedIndexs.size();
		if (size == 0){
			JOptionPane.showMessageDialog(null, "选中某一个或某一些账户后再删除哦！",
					"没有选择账户", JOptionPane.WARNING_MESSAGE);
			return ;
		}
		else {
			if(JOptionPane.showConfirmDialog(null, "确认删除该用户信息？", "",
					JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
				return;
			LogDiaryBL log = new LogDiaryBL();
			LogDiaryVO vo = new LogDiaryVO(GetDate.getTime(), userVO, "删除了一个账户");
			log.addLogDiary(vo,GetDate.getTime());
			logDiaryPanel.refreshui();
			for (int i : selectedIndexs){
				controller.deleteAccount(accountVOs.get(i).name);
				}
		}
		accountVOs = controller.showAll();
		accountTable.setInfos(getInfos());
		deleteLabel.reSet();
	}
	
	public void modifyui(){
	
		ArrayList<Integer> selectedIndexs = accountTable.getSelectedIndex();
		int size = selectedIndexs.size();
		if(size!= 1){
			JOptionPane.showMessageDialog(null, "选中某个账户后再删除哦！", 
					"没有选择账户", JOptionPane.WARNING_MESSAGE);
			return ;
		}
		selectedIndex = selectedIndexs.get(0);
		AccountVO  vo = accountVOs.get(selectedIndex);
		modifyLabel.reSet();
		financeFrame.changePanel(new AccountManagementPanel_modify(controller, vo.money+"", vo.name, financeFrame,this,userVO,logDiaryPanel));		
	}
	
	public void searchui(){
		String name = searchTextField.getText();
		if(name.equals("")){
			JOptionPane.showMessageDialog(null, "请输入查找名称", "提示",
					JOptionPane.WARNING_MESSAGE);
		}
		ArrayList<AccountVO> vos = controller.findByKeyword(name);
		accountTable.setInfos(newGetInfos(vos));
	}

	public void refreshui(){
		updateTable();
	}


	/**
	 *这个方法导致了刷新时的错误 
	 * */
	//不能刷新的解决
	public void paintComponent(Graphics g){
		super.paintComponent(g);
//		updateTable();
//		accountTable.setInfos(getInfos());
		
	}
	

}

     
     
 
	
