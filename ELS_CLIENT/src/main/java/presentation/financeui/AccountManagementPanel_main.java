package presentation.financeui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import businesslogic.financebl.controller.AccountBLController;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import vo.AccountVO;

public class AccountManagementPanel_main extends OperationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private JButton addButton;
	private JButton deleteButton;
	private JButton modifyButton;
	private JButton searchButton;
	private JButton refreshButton;
//	private JButton next;
	private JLabel next;
//	private JButton previous;
	private JLabel previous;

	
	private JTextField searchTextField;
	private JLabel function;
//	private JTable table;
	
	private MyTable accountTable;

//	private ArrayList<JCheckBox> selected;
	AccountModel am;
	ArrayList<ArrayList<String>> c=new ArrayList<ArrayList<String>>();
	AccountBLController controller;
	ArrayList<AccountVO> accountVOs;
	FinanceFrame financeFrame;
	int count;
	private int selectedIndex;
	

	public AccountManagementPanel_main(AccountBLController controller,FinanceFrame parent) {
		this.controller=controller;
		this.financeFrame=parent;
		addButton = new JButton("添加");
		deleteButton = new JButton("删除");
		modifyButton=new JButton("修改");
		searchButton=new JButton("查询");
		refreshButton=new JButton("刷新");
		next = new JLabel(">");
		previous = new JLabel("<");

		function = new JLabel("账户管理");

		searchTextField = new JTextField("");
		
		selectedIndex = -1;

		accountVOs = controller.showAll();
		
		setLayout(null);

		add(addButton);
		add(deleteButton);
		add(modifyButton);
		add(searchButton);
		add(refreshButton);
		add(next);
		add(previous);
		add(searchTextField);
		add(function);

		addListener();
		setBaseInfo();
	}



	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		addButton.setBounds((int)(width * 2.3278061224489797/25),(int)(height * 2.4442270058708413/20),(int)(width *   2.232142857142857 /25),(int)(height *  1.087279843444227/20));
		deleteButton.setBounds((int)(width * 5.07015306122449/25),(int)(height * 2.4442270058708413/20),(int)(width *  2.232142857142857 /25),(int)(height *  1.087279843444227/20));
		modifyButton.setBounds((int)(width * 7.940051020408164/25),(int)(height * 2.4442270058708413/20),(int)(width *  2.232142857142857 /25),(int)(height *  1.087279843444227/20));
		searchButton.setBounds((int)(width * 20.918367346938776/25),(int)(height * 2.4442491193737767/20),(int)(width *  2.232142857142857 /25),(int)(height *  1.087279843444227/20));
		refreshButton.setBounds((int)(width * 22.257653061224488/25),(int)(height * 0.9001956947162426/20),(int)(width *  2.232142857142857 /25),(int)(height *  1.0890019569471623/20));
		next.setBounds((int)(width * 13.544642857142858/25),(int)(height * 17.690802348336597/20),(int)(width *  1.3392857142857142 /25),(int)(height *  1.487279843444227/20));
		previous.setBounds((int)(width * 11.354591836734695/25),(int)(height * 17.690802348336597/20),(int)(width *  1.3392857142857142 /25),(int)(height *  1.5264187866927592/20));
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
			return null;
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
//		accountVOs = controller.showAll();
		accountTable.setInfos(getInfos());
	}

	
	public void addListener(){
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addui();
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteui();
			}
		});
		
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyui();
			}
		});
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    searchui();
			}
		});

		refreshButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				refreshui();
			}
		});
	}

	public void addui() {
		financeFrame.changePanel(new AccountManagement_new(controller,financeFrame));
	}

	public void deleteui() {

		ArrayList<Integer> selectedIndexs = accountTable.getSelectedIndex();
		int size = selectedIndexs.size();
		if (size == 0)
			return ;
		else {
			for (int i : selectedIndexs){
				controller.deleteAccount(accountVOs.get(i).name);
				ArrayList<AccountVO> vos = controller.showAll();
				accountTable.setInfos(newGetInfos(vos));
//			updateTable();
			}
		}
	}
	
	public void modifyui(){
	
		ArrayList<Integer> selectedIndexs = accountTable.getSelectedIndex();
		int size = selectedIndexs.size();
		if(size!= 1){
			return ;
		}
		selectedIndex = selectedIndexs.get(0);
		AccountVO  vo = accountVOs.get(selectedIndex);
		accountTable.cancelSelected(selectedIndex);
		financeFrame.changePanel(new AccountManagementPanel_modify(controller, vo.money+"", vo.name, financeFrame));		
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


	
	//不能刷新的解决
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		updateTable();
	}
	
class AccountModel extends AbstractTableModel{
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
}
     
     
 
	
