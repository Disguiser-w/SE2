package presentation.intermediateui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import presentation.commonui.UserFrame;
import vo.PlaneVO;
import businesslogic.intermediatebl.controller.IntermediateMainController;

public class PlaneManagementPanel extends OperationPanel {
	private IntermediateMainController controller;

	private UserFrame frame;

	private ArrayList<PlaneVO> planeList;

	private JLabel addLabel;
	private JLabel delLabel;
	private JLabel modifyLabel;

	private JTextField inputField;
	private JLabel confirmButton;

	private MyTable messageTable;

	private int selectedIndex;

	public PlaneManagementPanel(IntermediateMainController c, UserFrame f) {
		this.controller = c;
		this.frame = f;

		planeList = c.getPlaneList();

		addLabel = new JLabel("增");
		delLabel = new JLabel("删");
		modifyLabel = new JLabel("改");
		addLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		delLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		modifyLabel.setBorder(BorderFactory.createLineBorder(Color.black));

		searchLabel = new JLabel();
		inputField = new JTextField();
		confirmButton = new JButton();
		MessgeTableModel model = new MessgeTableModel();
		messageTable = new JTable(model);
		previousPageLabel = new JLabel(" < ");
		nextPageLabel = new JLabel(" > ");
		selectItem = new ArrayList<JCheckBox>();
		for (int i = 0; i < 8; i++) {
			JCheckBox box = new JCheckBox();
			selectItem.add(box);
			add(box);
		}
		numOfPage = new JLabel();

		add(addLabel);
		add(delLabel);
		add(modifyLabel);
		add(searchLabel);
		add(inputField);
		add(confirmButton);
		add(messageTable);
		add(previousPageLabel);
		add(nextPageLabel);
		add(messageTable.getTableHeader());
		add(numOfPage);
		messageTable.setBackground(getBackground());

		num = 0;
		numOfChoose = 0;
		isModify = false;
		isFirstTime = true;

		planeList = controller.getPlaneList();

		setLayout(null);
		addListener();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		numOfPage.setBounds((int) (width * 12.32394366197183 / 25),
				(int) (height * 17.321428571428573 / 20),
				(int) (width * 1.088348271446863 / 25),
				(int) (height * 1.4732142857142858 / 20));
		selectItem.get(0).setBounds((int) (width * 0.2560819462227913 / 25),
				(int) (height * 5.625 / 20),
				(int) (width * 0.6722151088348272 / 25),
				(int) (height * 0.8035714285714286 / 20));
		selectItem.get(1).setBounds((int) (width * 0.2560819462227913 / 25),
				(int) (height * 6.830357142857143 / 20),
				(int) (width * 0.6402048655569782 / 25),
				(int) (height * 0.8928571428571429 / 20));
		selectItem.get(2).setBounds((int) (width * 0.2560819462227913 / 25),
				(int) (height * 8.125 / 20),
				(int) (width * 0.6402048655569782 / 25),
				(int) (height * 0.8928571428571429 / 20));
		selectItem.get(3).setBounds((int) (width * 0.2560819462227913 / 25),
				(int) (height * 9.419642857142858 / 20),
				(int) (width * 0.6402048655569782 / 25),
				(int) (height * 0.8928571428571429 / 20));
		selectItem.get(4).setBounds((int) (width * 0.2560819462227913 / 25),
				(int) (height * 10.758928571428571 / 20),
				(int) (width * 0.6402048655569782 / 25),
				(int) (height * 0.8928571428571429 / 20));
		selectItem.get(5).setBounds((int) (width * 0.2560819462227913 / 25),
				(int) (height * 12.098214285714286 / 20),
				(int) (width * 0.6402048655569782 / 25),
				(int) (height * 0.8928571428571429 / 20));
		selectItem.get(6).setBounds((int) (width * 0.2560819462227913 / 25),
				(int) (height * 13.348214285714286 / 20),
				(int) (width * 0.6402048655569782 / 25),
				(int) (height * 0.8928571428571429 / 20));
		selectItem.get(7).setBounds((int) (width * 0.2560819462227913 / 25),
				(int) (height * 14.642857142857142 / 20),
				(int) (width * 0.6402048655569782 / 25),
				(int) (height * 0.8928571428571429 / 20));
		addLabel.setBounds((int) (width * 2.624839948783611 / 25),
				(int) (height * 1.1607142857142858 / 20),
				(int) (width * 1.3124199743918055 / 25),
				(int) (height * 1.8303571428571428 / 20));
		delLabel.setBounds((int) (width * 6.594110115236876 / 25),
				(int) (height * 1.1607142857142858 / 20),
				(int) (width * 1.3124199743918055 / 25),
				(int) (height * 1.8303571428571428 / 20));
		modifyLabel.setBounds((int) (width * 10.56338028169014 / 25),
				(int) (height * 1.1607142857142858 / 20),
				(int) (width * 1.3124199743918055 / 25),
				(int) (height * 1.8303571428571428 / 20));
		searchLabel.setBounds((int) (width * 15.781049935979514 / 25),
				(int) (height * 1.3392857142857142 / 20),
				(int) (width * 0.9282970550576184 / 25),
				(int) (height * 1.2946428571428572 / 20));
		inputField.setBounds((int) (width * 16.677336747759284 / 25),
				(int) (height * 1.3392857142857142 / 20),
				(int) (width * 4.321382842509603 / 25),
				(int) (height * 1.3392857142857142 / 20));
		confirmButton.setBounds((int) (width * 22.247119078104994 / 25),
				(int) (height * 1.3392857142857142 / 20),
				(int) (width * 1.7285531370038412 / 25),
				(int) (height * 1.2946428571428572 / 20));
		messageTable.setBounds((int) (width * 1.0243277848911652 / 25),
				(int) (height * 5.401785714285714 / 20),
				(int) (width * 22.98335467349552 / 25),
				(int) (height * 10.535714285714286 / 20));
		messageTable.getTableHeader().setBounds(
				(int) (width * 1.0243277848911652 / 25),
				(int) (height * 5.401785714285714 / 20)
						- (int) (height * 1.435714285714286 / 20),
				(int) (width * 22.98335467349552 / 25),
				(int) (height * 1.435714285714286 / 20));
		previousPageLabel.setBounds((int) (width * 11.331626120358514 / 25),
				(int) (height * 17.321428571428573 / 20),
				(int) (width * 1.0243277848911652 / 25),
				(int) (height * 1.4732142857142858 / 20));
		nextPageLabel.setBounds((int) (width * 13.380281690140846 / 25),
				(int) (height * 17.321428571428573 / 20),
				(int) (width * 1.0243277848911652 / 25),
				(int) (height * 1.4732142857142858 / 20));

	}

	public void paintComponent(Graphics g) {
		if (isFirstTime) {
			setInfos();
			isFirstTime = false;
		}
		super.paintComponent(g);

	}

	private void setInfos() {
		for (JCheckBox i : selectItem) {
			i.setVisible(false);
		}
		planeList = controller.getPlaneList();
		numOfPage.setText(num + 1 + "/" + ((planeList.size() - 1) / 8 + 1));

		messageTable.setModel(new MessgeTableModel());
		setBaseInfo();
	}

	private void addListener() {
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		previousPageLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (num == 0)
					return;
				else {
					num--;
					for (JCheckBox i : selectItem)
						i.setSelected(false);
					setInfos();
				}
			}
		});

		nextPageLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (num >= (planeList.size() - 1) / 8)
					return;
				else {
					num++;
					for (JCheckBox i : selectItem)
						i.setSelected(false);
					setInfos();
				}
			}
		});

		addLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PlaneManagement_newPanel addPanel = new PlaneManagement_newPanel(
						controller, frame);
				frame.changePanel(addPanel);
			}
		});
		ItemListener listener = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				JCheckBox button = ((JCheckBox) (e.getSource()));
				if (button.isSelected()) {
					numOfChoose++;
				} else {
					numOfChoose--;
				}

				if (numOfChoose == 1) {
					isModify = true;
				} else
					isModify = false;

				if (numOfChoose >= 1)
					isDel = true;

			}

		};

		for (JCheckBox i : selectItem) {
			i.addItemListener(listener);
		}

		modifyLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (isModify) {
					int n = 0;

					for (JCheckBox i : selectItem) {

						if (i.isSelected()) {
							i.setSelected(false);
							break;
						}
						n++;

					}
					PlaneVO vo = planeList.get(num * 8 + n);
					PlaneManagement_modifyPanel modifyPanel = new PlaneManagement_modifyPanel(
							controller, frame, vo);
					frame.changePanel(modifyPanel);
				}
			}
		});

		delLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (isDel) {
					if (JOptionPane.showConfirmDialog(null, "确认删除该飞机信息？", "",
							JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
						return;
					}
					int n = 0;
					int m = 0;
					for (JCheckBox i : selectItem) {
						if (i.isSelected()) {
							i.setSelected(false);
							PlaneVO vo = planeList.get(num * 8 + n);
							try {
								controller.getPlaneManagerBL().deletePlane(vo);
							} catch (Exception e1) {
								// TODO 自动生成的 catch 块
								e1.printStackTrace();
							}
							m++;
						}
						n++;
						isDel = false;
					}

					if ((planeList.size() - m - 1) / 8 + 1 < num + 1) {
						num--;
					}
					setInfos();
				}

			}
		});
	}

	private void setBaseInfo() {

		// 设置成不可编辑不可改变位置，大小
		// messageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		messageTable.getTableHeader().setReorderingAllowed(false);
		messageTable.getTableHeader().setResizingAllowed(false);

		TableColumn column1 = messageTable.getColumnModel().getColumn(0);
		TableColumn column2 = messageTable.getColumnModel().getColumn(1);
		TableColumn column3 = messageTable.getColumnModel().getColumn(2);
		TableColumn column4 = messageTable.getColumnModel().getColumn(3);

		// 设置宽度
		int tWidth = messageTable.getWidth();
		column1.setPreferredWidth(tWidth * 5 / 18);
		column2.setPreferredWidth(tWidth * 5 / 18);
		column3.setPreferredWidth(tWidth * 5 / 18);
		column4.setPreferredWidth(tWidth / 6);

		messageTable.setRowHeight(messageTable.getHeight() / 8);

		//
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				if (row % 2 == 0)
					setBackground(Color.cyan);
				else
					setBackground(Color.white);

				return super.getTableCellRendererComponent(table, value,
						isSelected, hasFocus, row, column);
			}
		};

		tcr.setHorizontalAlignment(JLabel.CENTER);
		column1.setCellRenderer(tcr);
		column2.setCellRenderer(tcr);
		column3.setCellRenderer(tcr);
		column4.setCellRenderer(tcr);

	}

	private class MessgeTableModel extends AbstractTableModel {

		@Override
		public int getRowCount() {
			return 8;
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			int index = num * 8 + rowIndex;

			if (index > planeList.size() - 1)
				return null;

			PlaneVO vo = planeList.get(index);

			switch (columnIndex) {
			case 0:
				// add(selectVehicle.get(rowIndex));
				selectItem.get(rowIndex).setVisible(true);
				return vo.ID;
			case 1:
				return controller.getIntermediateCentre().name;
			case 2:
				return vo.destination;
			case 3:
				return vo.farePrice;
			default:
				return null;
			}
		}

		public String getColumnName(int c) {
			switch (c) {
			case 0:
				return "飞机编号";
			case 1:
				return "所属机构";
			case 2:
				return "目的地";
			case 3:
				return "运送价格";

			default:
				return null;
			}
		}

	}

	public static void main(String[] args) throws MalformedURLException,
			RemoteException, NotBoundException {
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new PlaneManagementPanel(new IntermediateMainController(
				"141250185"), new UserFrame("任婧雯", "CW-00001")));
		frame.setVisible(true);
	}
}
