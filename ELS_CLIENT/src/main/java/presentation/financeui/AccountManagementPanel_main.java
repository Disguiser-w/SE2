package presentation.financeui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import vo.AccountVO;
import businesslogic.financebl.controller.AccountBLController;

public class AccountManagementPanel_main extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private JButton addButton;
	private JButton deleteButton;
	private JButton modifyButton;
	private JButton searchButton;
	private JButton next;
	private JButton previous;

	
	private JTextField searchTextField;
	private JLabel function;
	private JTable table;
//	private LocationHelper helper;

//	private AccountManagementInfoTable_main info;
	AccountModel am;
	ArrayList<ArrayList<String>> c=new ArrayList<ArrayList<String>>();
	AccountBLController controller;
	ArrayList<AccountVO> accountVOs;
	FinanceFrame financeFrame;
	int count;

	public AccountManagementPanel_main(AccountBLController controller) {
		this.controller=controller;
		addButton = new JButton("add");
		deleteButton = new JButton("delete");
		modifyButton=new JButton("modify");
		searchButton=new JButton("search");
		next = new JButton("next");
		previous = new JButton("pre");

		function = new JLabel("账户管理");

		searchTextField = new JTextField("CW-00001");

//		info = new AccountManagementInfoTable_main(13, 2);
		
//		refreshTable(controller.showAll());
		am=new AccountModel(c);
		System.out.println("Name :"+c.size());
		//新建table
		table=new JTable(am);
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(table.getTableHeader());
		add(table);


//		setCmpLocation();
		

	

		setLayout(null);

		add(addButton);
		add(deleteButton);
		add(modifyButton);
		add(searchButton);
		add(next);
		add(previous);
		add(searchTextField);
		add(function);
		add(table);
//		add(info);
//		helper = new LocationHelper(this);
		
		addListener();

	}



	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		addButton.setBounds((int)(width * 2.3278061224489797/25),(int)(height * 3.4442270058708413/20),(int)(width *  2.1364795918367347 /25),(int)(height *  1.487279843444227/20));
		deleteButton.setBounds((int)(width * 5.07015306122449/25),(int)(height * 3.4442270058708413/20),(int)(width *  2.232142857142857 /25),(int)(height *  1.487279843444227/20));
		modifyButton.setBounds((int)(width * 7.940051020408164/25),(int)(height * 3.4442270058708413/20),(int)(width *  2.3278061224489797 /25),(int)(height *  1.5264187866927592/20));
		searchButton.setBounds((int)(width * 20.918367346938776/25),(int)(height * 3.3659491193737767/20),(int)(width *  1.3392857142857142 /25),(int)(height *  1.4090019569471623/20));
		next.setBounds((int)(width * 20.854591836734695/25),(int)(height * 17.690802348336597/20),(int)(width *  1.3392857142857142 /25),(int)(height *  1.5264187866927592/20));
		previous.setBounds((int)(width * 22.544642857142858/25),(int)(height * 17.690802348336597/20),(int)(width *  1.3392857142857142 /25),(int)(height *  1.487279843444227/20));
		searchTextField.setBounds((int)(width * 14.85969387755102/25),(int)(height * 3.4050880626223092/20),(int)(width *  5.420918367346939 /25),(int)(height *  1.36986301369863/20));
		function.setBounds((int)(width * 0.6696428571428571/25),(int)(height * 0.821917808219178/20),(int)(width *  5.548469387755102 /25),(int)(height *  1.643835616438356/20));
		table.getTableHeader().setBounds((int)(width * 2.2002551020408165/25), (int)(height * 5.205479452054795/20), (int)(width *  20.057397959183675 /25), (int)(height *  1.1311154598825832/20));
		table.setBounds((int)(width * 2.2002551020408165/25),(int)(height * 5.205479452054795/20)+(int)(height *  1.1311154598825832/20),(int)(width *  20.057397959183675 /25),(int)(height *  11.311154598825832/20));

		setBaseInfo();
	}
	
	 /**
     * 设置表格的基本内容
     * */
	private void setBaseInfo() {

		// 设置成不可编辑不可改变位置，大小
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		TableColumn column1 = table.getColumnModel().getColumn(0);
		TableColumn column2 = table.getColumnModel().getColumn(1);
		// 设置宽度
		column1.setPreferredWidth(table.getWidth() * 5/ 10);
		column2.setPreferredWidth(table.getWidth() * 5/ 10);


		table.setRowHeight((table.getHeight() - table.getTableHeader().getHeight()) / 10);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (row % 2 == 0)
					setBackground(Color.cyan);
				else
					setBackground(Color.white);

				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		};

		tcr.setHorizontalAlignment(JLabel.CENTER);
		column1.setCellRenderer(tcr);
		column2.setCellRenderer(tcr);

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

		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nextui();
			}
		});

		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				previousui();
			}
		});
	}

	public void addui() {

		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 800, 550);
		frame.add(new AccountManagement_new(controller));
		frame.setVisible(true);
		refreshTable(controller.showAll());
		am=new AccountModel(c);
		table.repaint();
		
	
	}

	public void deleteui() {
		int row=table.getSelectedRow();
		if(row==-1){
			JOptionPane.showMessageDialog(null, "请选择需要删除的行！", "提示",
					JOptionPane.CLOSED_OPTION);
		}
		else{
			System.out.println(row);
			am.removeRow(row);
			String name=am.getValueAt(row, 0);
			System.out.println("Name: "+name);
			controller.deleteAccount(name);
			JOptionPane.showMessageDialog(null, "删除成功！", "提示",
					JOptionPane.CLOSED_OPTION);
			table.repaint();
		}
	}
	
	public void modifyui(){
		int row=table.getSelectedColumn();
		if(row==-1){
			JOptionPane.showMessageDialog(null, "请选择需要修改的行！", "提示",
					JOptionPane.CLOSED_OPTION);
		}
		else{
			
		}
		
	}
	
	public void searchui(){
		String name=searchTextField.getText();
		if(name.equals("")){
			JOptionPane.showMessageDialog(null, "请输入查找名称", "提示",
					JOptionPane.WARNING_MESSAGE);
		}
//		AccountManagementPanel_main panel=new AccountManagementPanel_main(controller);
		ArrayList<AccountVO> vos=controller.findByKeyword(name);
		for(AccountVO v:vos){
			System.out.println(v.getName());
		}
	   refreshTable(vos);
	   am=new AccountModel(c);
		table.repaint();
	}

	public void nextui() {

	}

	public void previousui() {

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
			return c.size();
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return head.length;
		}
		
		public String getValueAt(int row, int col) {
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

//     public void refreshingTable(){
//    	 ArrayList<AccountVO> vos=controller.showAll();
//    	 for(AccountVO v:vos){
////    		 System.out.println("Name: "+v.getName());
//    		 ArrayList<String> lineInfo=new ArrayList<String>();
//    		 lineInfo.add(v.getName());
//    		 lineInfo.add(v.getMoney()+"");
//    		 c.add(lineInfo);
//    	 }
//    	 count=c.size();
//	
//       }
     
     public void refreshTable(ArrayList<AccountVO> vos){
    	 for(AccountVO v:vos){
    		 ArrayList<String> lineInfo = new ArrayList<String>();
 			lineInfo.add(v.getName());
 			lineInfo.add(String.valueOf(v.getMoney()));
 			c.add(lineInfo);
    	 }
     }
     
     
 
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.setSize(800, 550);
//		frame.add(new AccountManagementPanel_main());
//		frame.setVisible(true);
//	}
}


/*	public void setCmpLocation() {
function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
		PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
addButton.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 3 / 16,
		PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
deleteButton.setBounds(PANEL_WIDTH * 5 / 9, PANEL_HEIGHT * 3 / 16,
		PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
searchTextField.setBounds(PANEL_WIDTH * 13 / 18, PANEL_HEIGHT * 3 / 16,
		PANEL_WIDTH * 2 / 9, PANEL_HEIGHT / 24);
next.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
		PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
previous.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 45 / 48,
		PANEL_WIDTH / 24, PANEL_HEIGHT / 24);

//info.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 4 / 15,
//		PANEL_WIDTH * 5 / 6, PANEL_HEIGHT * 13 / 20);
}
*/
