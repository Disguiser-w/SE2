package presentation.financeui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

public class AccountManagementPanel_main extends JLabel {
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

	public AccountManagementPanel_main() {
		addButton = new JButton("add");
		deleteButton = new JButton("delete");
		modifyButton=new JButton("modify");
		searchButton=new JButton("search");
		next = new JButton("next");
		previous = new JButton("pre");

		function = new JLabel("账户管理");

		searchTextField = new JTextField("CW-00001");

//		info = new AccountManagementInfoTable_main(13, 2);
		am=new AccountModel(c);
		//新建table
		table=new JTable(am);
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(table.getTableHeader());
		add(table);


//		setCmpLocation();
		

		addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				addui();
			}
		});

		deleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				deleteui();
			}
		});

		next.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				nextui();
			}
		});

		previous.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				previousui();
			}
		});

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

//		info.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 4 / 15,
//				PANEL_WIDTH * 5 / 6, PANEL_HEIGHT * 13 / 20);
	}
	*/

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
//		PANEL_WIDTH = width;
//		PANEL_HEIGHT = height;
//		setCmpLocation();
//		repaint();
		
//	serialVersionUID.setBounds((int)(width * 0.0/25),(int)(height * 0.0/20),(int)(width *  0.6377551020408163 /25),(int)(height *  0.7827788649706457/20));
		addButton.setBounds((int)(width * 2.3278061224489797/25),(int)(height * 3.4442270058708413/20),(int)(width *  2.1364795918367347 /25),(int)(height *  1.487279843444227/20));
		deleteButton.setBounds((int)(width * 5.07015306122449/25),(int)(height * 3.4442270058708413/20),(int)(width *  2.232142857142857 /25),(int)(height *  1.487279843444227/20));
		modifyButton.setBounds((int)(width * 7.940051020408164/25),(int)(height * 3.4442270058708413/20),(int)(width *  2.3278061224489797 /25),(int)(height *  1.5264187866927592/20));
		searchButton.setBounds((int)(width * 20.918367346938776/25),(int)(height * 3.3659491193737767/20),(int)(width *  1.3392857142857142 /25),(int)(height *  1.4090019569471623/20));
		next.setBounds((int)(width * 20.854591836734695/25),(int)(height * 17.690802348336597/20),(int)(width *  1.3392857142857142 /25),(int)(height *  1.5264187866927592/20));
		previous.setBounds((int)(width * 22.544642857142858/25),(int)(height * 17.690802348336597/20),(int)(width *  1.3392857142857142 /25),(int)(height *  1.487279843444227/20));
		searchTextField.setBounds((int)(width * 14.85969387755102/25),(int)(height * 3.4050880626223092/20),(int)(width *  5.420918367346939 /25),(int)(height *  1.36986301369863/20));
		function.setBounds((int)(width * 0.6696428571428571/25),(int)(height * 0.821917808219178/20),(int)(width *  5.548469387755102 /25),(int)(height *  1.643835616438356/20));
		table.setBounds((int)(width * 2.2002551020408165/25),(int)(height * 5.205479452054795/20),(int)(width *  20.057397959183675 /25),(int)(height *  11.311154598825832/20));

	}

	public void addui() {

	}

	public void deleteui() {

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
//			System.out.println(head.length);
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
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new AccountManagementPanel_main());
		frame.setVisible(true);
	}
}
