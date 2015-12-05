package presentation.businessui;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import businesslogic.businessbl.controller.DriverManagerController;
import presentation.commonui.LocationHelper;
import vo.DriverVO;

public class DriverManagerPanel extends JPanel {
	private JLabel addLabel;
	private JLabel delLabel;
	private JLabel modifyLabel;

	private JLabel searchLabel;
	private JTextField inputField;
	private JButton confirmButton;

	private JTable messageTable;
	private JLabel previousPageLabel;
	private JLabel nextPageLabel;
	private ArrayList<JCheckBox> selectDriver;
	private ArrayList<JLabel> totalInfo;
	private DriverManagerController controller;

	private LocationHelper helper;
	//修改后返回的modifierVO 存在这里面
	private DriverVO modifierVO;
	
	public DriverManagerPanel(DriverManagerController controller) {
		this.controller = controller;
		addLabel = new JLabel("增");
		delLabel = new JLabel("删");
		modifyLabel = new JLabel("改");

		searchLabel = new JLabel();
		inputField = new JTextField();
		confirmButton = new JButton();

		messageTable = new JTable();
		previousPageLabel = new JLabel();
		nextPageLabel = new JLabel();
		selectDriver = new ArrayList<JCheckBox>();
		totalInfo = new ArrayList<JLabel>();

		add(addLabel);
		add(delLabel);
		add(modifyLabel);
		add(searchLabel);
		add(inputField);
		add(confirmButton);
		add(messageTable);
		add(previousPageLabel);
		add(nextPageLabel);

		helper = new LocationHelper(this);
		setLayout(null);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		addLabel.setBounds((int) (width * 2.624839948783611 / 25), (int) (height * 1.1607142857142858 / 20),
				(int) (width * 1.3124199743918055 / 25), (int) (height * 1.8303571428571428 / 20));
		delLabel.setBounds((int) (width * 6.594110115236876 / 25), (int) (height * 1.1607142857142858 / 20),
				(int) (width * 1.3124199743918055 / 25), (int) (height * 1.8303571428571428 / 20));
		modifyLabel.setBounds((int) (width * 10.56338028169014 / 25), (int) (height * 1.1607142857142858 / 20),
				(int) (width * 1.3124199743918055 / 25), (int) (height * 1.8303571428571428 / 20));
		searchLabel.setBounds((int) (width * 15.781049935979514 / 25), (int) (height * 1.3392857142857142 / 20),
				(int) (width * 0.9282970550576184 / 25), (int) (height * 1.2946428571428572 / 20));
		inputField.setBounds((int) (width * 16.677336747759284 / 25), (int) (height * 1.3392857142857142 / 20),
				(int) (width * 4.321382842509603 / 25), (int) (height * 1.3392857142857142 / 20));
		confirmButton.setBounds((int) (width * 22.247119078104994 / 25), (int) (height * 1.3392857142857142 / 20),
				(int) (width * 1.7285531370038412 / 25), (int) (height * 1.2946428571428572 / 20));
		messageTable.setBounds((int) (width * 1.0243277848911652 / 25), (int) (height * 3.9732142857142856 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 11.964285714285714 / 20));
		previousPageLabel.setBounds((int) (width * 11.331626120358514 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.0243277848911652 / 25), (int) (height * 1.4732142857142858 / 20));
		nextPageLabel.setBounds((int) (width * 13.380281690140846 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.0243277848911652 / 25), (int) (height * 1.4732142857142858 / 20));

	}
}
