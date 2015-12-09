package presentation.managerui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import vo.OrganizationVO;
import type.OrganizationType;
import businesslogic.managebl.OrganizationBL;

//import presentation.commonui.LocationHelper;

public class OrganizationManagePanel extends JPanel implements ListSelectionListener{
	
	private static final long serialVersionUID = 20L;

	private ManageFrame manageFrame;
	
	private OrganizationBL organizationBL;
	
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	
	private int num;
	private boolean isFindPattern;
	
	private JLabel function;

	private JButton addButton;
	private JButton deleteButton;
//	private JButton modifyButton;
	private JButton searchButton;
	private JTextField searchTextField;
	private JButton refreshButton;
	
	private AllMessageTableModel allModel;
	private FindMessageTableModel findModel;
	private JTable allInfoTable;
	private JTable findInfoTable;
	private JLabel messageLabel;
	private JButton previousPage;
	private JButton nextPage;

	ListSelectionModel selectionMode;

	public OrganizationManagePanel(ManageFrame manageFrame) {
		
		this.manageFrame = manageFrame;
		
		organizationBL = new OrganizationBL();
		
		num = 0;
		isFindPattern = false;
		
        function = new JLabel("机构管理 ");
        
        addButton = new JButton("新增机构");
        deleteButton = new JButton("删除机构");
//      modifyButton = new JButton("修改机构信息");
        searchTextField = new JTextField("机构编号");
        searchButton = new JButton("查找");
        refreshButton = new JButton("刷新");
        
        previousPage = new JButton("上一页");
        nextPage = new JButton("下一页");
        
        allModel = new AllMessageTableModel();
		allInfoTable = new JTable(allModel);
		findModel = new FindMessageTableModel();
		findInfoTable = new JTable(findModel);
		messageLabel = new JLabel();
        
		previousPage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nextPage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		messageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		allInfoTable.setBackground(getBackground());
		allInfoTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		allInfoTable.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		findInfoTable.setBackground(getBackground());
		findInfoTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		findInfoTable.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addui();
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int chosenRow = -1; 
				String ID = "";
				if(isFindPattern){
					 chosenRow = findInfoTable.getSelectedRow();
					 ID = (String)(findModel.getValueAt(chosenRow, 2));
				}
				else{
					chosenRow = allInfoTable.getSelectedRow();
					ID = (String)(allModel.getValueAt(chosenRow,2));
				}
				
				if(chosenRow != -1){
					organizationBL.deleteOrganization(ID);
					if(isFindPattern)
						findInfoTable.updateUI();
					allInfoTable.updateUI();//刷新
				}
				else{
        			warnning("没有选择要删除的机构");
        		}
			}
		});
		
/*		modifyButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent arg0){
        		int chosenRow = -1; 
				if(isFindPattern)
					 chosenRow = findInfoTable.getSelectedRow();
				else
					chosenRow = allInfoTable.getSelectedRow();
				
        		if(chosenRow != -1){
        			modifyui(chosenRow);
        		}
        		else{
        			warnning("没有选择待修改的机构");
        		}
        	}
        });*/
        
        searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String organizationID = searchTextField.getText();
				if(organizationID.equals("机构编号") || organizationID.equals("")){
					allui();
				}
				else
					searchui();
			}
		});
        
        refreshButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ae){
        		findInfoTable.updateUI();
				allInfoTable.updateUI();
        	}
        });
        
        previousPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (num == 0)
					return;
				else {
					num--;
					allInfoTable.setModel(new AllMessageTableModel());
					setBaseInfo(allInfoTable);
				}
			}
		});

		nextPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (num >= (organizationBL.showAllOrganizations().size() - 2) / 12)
					return;
				else {
					num++;
					allInfoTable.setModel(new AllMessageTableModel());
					setBaseInfo(allInfoTable);
				}
			}
		});
		
		setLayout(null);

        add(function);
        add(addButton);
        add(deleteButton);
//      add(modifyButton);
        add(searchTextField);
        add(searchButton);
        add(refreshButton);
        add(allInfoTable.getTableHeader());
        add(allInfoTable);
        add(findInfoTable);
        add(previousPage);
        add(nextPage);
        
        setCmpLocation(allInfoTable);
        setBaseInfo(allInfoTable);
        setInfos();
	}
	
	
	public void valueChanged(ListSelectionEvent lse){
     	//System.out.println("你选了第"+allInfoTable.getSelectedRow()+"行");
	}
	
	public void setCmpLocation(JTable table){
		
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		
		addButton.setBounds(PANEL_WIDTH * 3 / 54, PANEL_HEIGHT * 2 / 16,
				PANEL_WIDTH * 4 / 27, PANEL_HEIGHT / 16);
		deleteButton.setBounds(PANEL_WIDTH * 13 / 54, PANEL_HEIGHT * 2 / 16,
				PANEL_WIDTH * 4 / 27, PANEL_HEIGHT / 16);
//		modifyButton.setBounds(PANEL_WIDTH * 23 / 54, PANEL_HEIGHT * 2 / 16,
//				PANEL_WIDTH * 4 / 27, PANEL_HEIGHT / 16);
		searchTextField.setBounds(PANEL_WIDTH * 12 / 18, PANEL_HEIGHT * 2 / 16,
				PANEL_WIDTH * 4 / 27, PANEL_HEIGHT / 16);
		searchButton.setBounds(PANEL_WIDTH * 22 / 27, PANEL_HEIGHT * 2 / 16,
				PANEL_WIDTH * 3 / 27, PANEL_HEIGHT / 16);
		refreshButton.setBounds(PANEL_WIDTH * 24 / 27, PANEL_HEIGHT * 1 / 24,
				PANEL_WIDTH * 3 / 27, PANEL_HEIGHT / 16);
		allInfoTable.getTableHeader().setBounds(PANEL_WIDTH / 18, PANEL_HEIGHT * 13 / 60, 
				PANEL_WIDTH * 66 / 72, PANEL_HEIGHT * 1 / 20);
		
		previousPage.setBounds(PANEL_WIDTH * 52 / 72, PANEL_HEIGHT * 7 / 8,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 16);
		nextPage.setBounds(PANEL_WIDTH * 62 / 72, PANEL_HEIGHT * 7 / 8,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 16);
		
		table.setBounds(PANEL_WIDTH / 18, PANEL_HEIGHT * 4 / 15,
				PANEL_WIDTH * 66 / 72, PANEL_HEIGHT * 12 / 20);
		
		table.setRowSelectionAllowed(true);
        selectionMode = table.getSelectionModel();
        selectionMode.addListSelectionListener(this);
	}
	
	public void setBounds(int x, int y, int width, int height , JTable table) {
		super.setBounds(x, y, width, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation(table);
		repaint();
	}
	
	private void setBaseInfo(JTable table) {

		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		TableColumn column0 = table.getColumnModel().getColumn(0);
		TableColumn column1 = table.getColumnModel().getColumn(1);
		TableColumn column2 = table.getColumnModel().getColumn(2);
		TableColumn column3 = table.getColumnModel().getColumn(3);
		
		// 设置宽度
		column0.setPreferredWidth(table.getWidth() / 4);
		column1.setPreferredWidth(table.getWidth() / 4);
		column2.setPreferredWidth(table.getWidth() / 4);
		column3.setPreferredWidth(table.getWidth() / 4);
	
		table.setRowHeight((table.getHeight() - table.getTableHeader().getHeight()) / 10);

		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
			
			private static final long serialVersionUID = 6L;

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
		column0.setCellRenderer(tcr);
		column1.setCellRenderer(tcr);
		column2.setCellRenderer(tcr);
		column3.setCellRenderer(tcr);
	
	}

	// 设置载入动态的内容
	private void setInfos() {
		if (organizationBL.showAllOrganizations().size() != 0)
			messageLabel.setText("姓名: "+"  机构编号 : "+"  职位 : ");
		else
			messageLabel.setText("姓名: 无"+"  机构编号 : 无"+"  职位 : 无");
		
		messageLabel.setHorizontalAlignment(JLabel.CENTER);
	}
	
	public void addui(){
		manageFrame.changePanel(new AddOrganizationPanel(manageFrame, this));
	}
	
	/*public void modifyui(int chosenRow){
		String organizationCategory;
		String organizationName;
		String organizationID;
		String organizationRepertory;
		
		if(isFindPattern){
			organizationCategory = (String)findInfoTable.getModel().getValueAt(chosenRow, 0);
			organizationName = (String)findInfoTable.getModel().getValueAt(chosenRow, 1);
			organizationID = (String)findInfoTable.getModel().getValueAt(chosenRow, 2);
			organizationRepertory = (String)findInfoTable.getModel().getValueAt(chosenRow, 3);
		}
		else{
			organizationCategory = (String)allInfoTable.getModel().getValueAt(chosenRow, 0);
			organizationName = (String)allInfoTable.getModel().getValueAt(chosenRow, 1);
			organizationID = (String)allInfoTable.getModel().getValueAt(chosenRow, 2);
			organizationRepertory = (String)allInfoTable.getModel().getValueAt(chosenRow, 3);
		}
		
		manageFrame.changePanel(new ModifyOrganizationPanel(manageFrame, this, organizationCategory, organizationID, organizationName, organizationRepertory));
	}*/
	
	public void searchui(){
		this.remove(allInfoTable);
		this.add(findInfoTable);
		setCmpLocation(findInfoTable);
        setBaseInfo(findInfoTable);
        setInfos();
        isFindPattern = true;
	}
	
	public void allui(){
		this.remove(findInfoTable);
		this.add(allInfoTable);
		setCmpLocation(allInfoTable);
        setBaseInfo(allInfoTable);
        setInfos();
        isFindPattern = false;
	}

	public String categoryName(OrganizationType organizationType){
		if(organizationType.equals(OrganizationType.businessHall))
			return "营业厅";
		else
			return "中转中心";
	}
	
	public void warnning(String message) {
		JOptionPane.showMessageDialog(null, message, "机构信息错误", JOptionPane.ERROR_MESSAGE);
	}

	public class AllMessageTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 4L;
		
		Vector<OrganizationVO> organizationVector;
		
		public int getRowCount() {
			return 10;
		}

		public int getColumnCount() {
			return 4;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			
			organizationVector = new Vector<OrganizationVO>();
			for(int i=0;i<organizationBL.showAllOrganizations().size();i++){
				organizationVector.add(organizationBL.showAllOrganizations().get(i));
			}
			
			int index = num * 10 + rowIndex;

				if (index > organizationVector.size()-1)
					return null;
				OrganizationVO organizationvo = (OrganizationVO)organizationVector.get(index);
	
				if (organizationvo != null) {
					if(columnIndex==0)
						return categoryName(organizationvo.getCategory());
					else if(columnIndex==1)
						return organizationvo.getName();
					else if(columnIndex==2)
						return organizationvo.getOrganizationID();
					else {
						if(organizationvo.getRepertory() != null)
							return organizationvo.getRepertory().getRepertoryID();
						else
							return "/";
					}
				} 
				else
					return null;
		}

		public String getColumnName(int num) {
			if (num == 0)
				return "机构类别";
			else if(num==1)
				return "机构名称";
			else if(num==2)
				return "机构编号";
			else 
				return "下属仓库";
		}

	}
	
public class FindMessageTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = -4L;
		
		Vector<OrganizationVO> organizationVector;
		
		public int getRowCount() {
			return 10;
		}

		public int getColumnCount() {
			return 4;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			
			String organizationID = searchTextField.getText();
			organizationVector = new Vector<OrganizationVO>();
			organizationVector.add(organizationBL.findOrganization(organizationID));

			int index = num * 10 + rowIndex;
			
			if (index > organizationVector.size()-1)
				return null;
			OrganizationVO organizationvo = (OrganizationVO)organizationVector.get(index);
	
			if (organizationvo != null){
				if(columnIndex==0)
					return categoryName(organizationvo.getCategory());
				else if(columnIndex==1)
					return organizationvo.getName();
				else if(columnIndex==2)
					return organizationvo.getOrganizationID();
				else {
					if(organizationvo.getRepertory() != null)
						return organizationvo.getRepertory().getRepertoryID();
					else
						return "/";
				}
			}
			else{
				return null;
			}
		}

		public String getColumnName(int num) {
			if (num == 0)
				return "机构类别";
			else if(num==1)
				return "机构名称";
			else if(num==2)
				return "机构编号";
			else 
				return "下属仓库";
		}

	}

}
