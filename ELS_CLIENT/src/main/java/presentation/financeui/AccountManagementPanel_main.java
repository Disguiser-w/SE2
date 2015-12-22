package presentation.financeui;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import businesslogic.financebl.controller.AccountBLController;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import presentation.special_ui.MySearchField;
import vo.AccountVO;

public class AccountManagementPanel_main extends OperationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private MyLabel addLabel;
	private MyLabel deleteLabel;
	private MyLabel modifyLabel;
	private MyLabel searchLabel;
	private MyLabel refreshLabel;
	
	private MySearchField searchTextField;
	private JLabel function;
	
	private MyTable accountTable;

	AccountBLController controller;
	ArrayList<AccountVO> accountVOs;
	FinanceFrame financeFrame;
	private int selectedIndex;
	

	public AccountManagementPanel_main(AccountBLController controller,FinanceFrame parent) {
		this.controller=controller;
		this.financeFrame=parent;
		addLabel = new MyLabel("添加");
		deleteLabel = new MyLabel("删除");
		modifyLabel=new MyLabel("修改");
		searchLabel=new MyLabel("查询");
		refreshLabel=new MyLabel("刷新");

		
		function = new JLabel("账户管理");

		searchTextField = new MySearchField();
		
		setLayout(null);

		add(addLabel);
		add(deleteLabel);
		add(modifyLabel);
		add(searchLabel);
		add(refreshLabel);
		add(searchTextField);
		add(function);
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
		
		searchLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
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

		addLabel.setBounds((int)(width * 2.3278061224489797/25),(int)(height * 2.4442270058708413/20),(int)(width *   2.232142857142857 /25),(int)(height *  1.087279843444227/20));
		deleteLabel.setBounds((int)(width * 5.07015306122449/25),(int)(height * 2.4442270058708413/20),(int)(width *  2.232142857142857 /25),(int)(height *  1.087279843444227/20));
		modifyLabel.setBounds((int)(width * 7.940051020408164/25),(int)(height * 2.4442270058708413/20),(int)(width *  2.232142857142857 /25),(int)(height *  1.087279843444227/20));
		searchLabel.setBounds((int)(width * 20.918367346938776/25),(int)(height * 2.4442491193737767/20),(int)(width *  2.232142857142857 /25),(int)(height *  1.087279843444227/20));
		refreshLabel.setBounds((int)(width * 22.257653061224488/25),(int)(height * 0.9001956947162426/20),(int)(width *  2.232142857142857 /25),(int)(height *  1.0890019569471623/20));
		searchTextField.setBounds((int)(width * 14.85969387755102/25),(int)(height *2.4442270058708413/20),(int)(width *   4.232142857142857 /25),(int)(height *  1.08986301369863/20));
		function.setBounds((int)(width * 0.6696428571428571/25),(int)(height * 0.821917808219178/20),(int)(width *  5.548469387755102 /25),(int)(height *  1.643835616438356/20));

		accountTable.setLocationAndSize((int)(width * 1.1002551020408165/25),(int)(height * 4.205479452054795/20),(int)(width *  23.007397959183675 /25),(int)(height *  13.421154598825832/20));
	}
	
	 /**
     * 设置表格的基本内容
     * 设置MyTable 的基本信息
     * */
	private void setBaseInfo() {
		String head[] = new String[]{"名称","金额"};
		int[] width ={290,290};
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
		financeFrame.changePanel(new AccountManagement_new(controller,financeFrame,this));
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
			for (int i : selectedIndexs){
				controller.deleteAccount(accountVOs.get(i).name);
				}
		}
		accountVOs = controller.showAll();
		accountTable.setInfos(getInfos());
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
		financeFrame.changePanel(new AccountManagementPanel_modify(controller, vo.money+"", vo.name, financeFrame,this));		
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
	
/*class AccountModel extends AbstractTableModel{
		private static final long serialVersionUID = 1L;
		ArrayList<ArrayList<String>> c = new ArrayList<ArrayList<String>>();
		//操作人还要吗
		String head[]={"账户","金额"};
		
	 public AccountModel(ArrayList<ArrayList<String>> content) {
			c=content;
		}
		//行数
		public int getRowCount() {
			// TODO Auto-generated method stub
			return 8;
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return head.length;
		}
		
		public Object getValueAt(int row, int col) {
			if(row>c.size()-1){
				return null;
			}
			return c.get(row).get(col);
		}

		public String getColumnName(int col) {
			return head[col];
		}

		public void addRow(ArrayList<String> v) {
			c.add(v);
		}

		public void removeRow(int row) {
			c.remove(row);
		}
		
	}


     
     public void refreshTable(ArrayList<AccountVO> vos){
    	 //每次刷新时c清0
    	 if(vos==null){
    		 return;
    	 }
    	 else{
    	 for(AccountVO v:vos){
    		 ArrayList<String> lineInfo = new ArrayList<String>();
 			lineInfo.add(v.name);
 			lineInfo.add(String.valueOf(v.money));
 			c.add(lineInfo);
    	 }
    	 }
     }
     
     public ArrayList<AccountVO> getAccountOnThisPage(int num){
    	 ArrayList<AccountVO> vos=controller.showAll();
    	 ArrayList<AccountVO> accountTemp=new ArrayList<AccountVO>();
    	 if(vos.size()<=num*8||num<0){
    		 return null;
    	 }
    	 else{
    	 for(int i=8*num;i<=8*num+7;i++){
    		 if(vos.size()>i){
    		 accountTemp.add(vos.get(i));
    		 }
    	 }
		return accountTemp;
    	 }
     }
     */
}

     
     
 
	
