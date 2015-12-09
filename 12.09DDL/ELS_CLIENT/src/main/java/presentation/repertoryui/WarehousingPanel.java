package presentation.repertoryui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import presentation.commonui.LocationHelper;

public class WarehousingPanel extends JPanel {
	private JLabel inputLabel;
	private JLabel searchLabel;
	private JTextField inputField;
	private JButton confirmButton;

	private JLabel messageLabel;
	private JTable messageTable;

	private JLabel chooseLabel;

	private JButton planeButton;
	private JButton trainButton;
	private JButton vehicleButton;
	private JButton motorButton;

	private JLabel addressLabel;

	private JTextField partField;
	private JLabel partLabel;

	private JTextField rowField;
	private JLabel rowLabel;

	private JTextField shelfField;
	private JLabel shelfLabel;

	private JTextField seatField;
	private JLabel seatLabel;

	private JButton confirmWarehousingButton;

	private LocationHelper helper;

	public WarehousingPanel() {
		inputLabel = new JLabel("请输入订单号");
		searchLabel = new JLabel();
		inputField = new JTextField();
		confirmButton = new JButton("确认");

		messageLabel = new JLabel("该订单详细信息");
		messageTable = new JTable();

		chooseLabel = new JLabel("选择分区");

		planeButton = new JButton("飞机区");
		trainButton = new JButton("火车区");
		vehicleButton = new JButton("汽车区");
		motorButton = new JButton("机动区");

		addressLabel = new JLabel("为该商品分配的地址为");

		partField = new JTextField();
		partLabel = new JLabel("区");

		rowField = new JTextField();
		rowLabel = new JLabel("排");

		shelfField = new JTextField();
		shelfLabel = new JLabel("架");

		seatField = new JTextField();
		seatLabel = new JLabel("位");

		confirmWarehousingButton = new JButton("确认入库");

		add(inputLabel);
		add(searchLabel);
		add(inputField);
		add(confirmButton);
		add(messageLabel);
		add(messageTable);
		add(chooseLabel);
		add(planeButton);
		add(trainButton);
		add(vehicleButton);
		add(motorButton);
		add(addressLabel);
		add(partField);
		add(partLabel);
		add(rowField);
		add(rowLabel);
		add(shelfField);
		add(shelfLabel);
		add(seatField);
		add(seatLabel);
		add(confirmWarehousingButton);

		setLayout(null);
		helper = new LocationHelper(this);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		//
		inputLabel.setBounds((int) (width * 2.880921895006402 / 25), (int) (height * 1.2053571428571428 / 20),
				(int) (width * 3.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		searchLabel.setBounds((int) (width * 13.092189500640204 / 25), (int) (height * 1.2053571428571428 / 20),
				(int) (width * 0.8642765685019206 / 25), (int) (height * 1.2053571428571428 / 20));
		inputField.setBounds((int) (width * 13.924455825864277 / 25), (int) (height * 1.2053571428571428 / 20),
				(int) (width * 5.60179257362356 / 25), (int) (height * 1.25 / 20));
		confirmButton.setBounds((int) (width * 20.070422535211268 / 25), (int) (height * 1.2053571428571428 / 20),
				(int) (width * 1.6645326504481435 / 25), (int) (height * 1.2053571428571428 / 20));
		messageLabel.setBounds((int) (width * 2.880921895006402 / 25), (int) (height * 3.3035714285714284 / 20),
				(int) (width * 3.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		messageTable.setBounds((int) (width * 2.880921895006402 / 25), (int) (height * 5.223214285714286 / 20),
				(int) (width * 18.85403329065301 / 25), (int) (height * 4.151785714285714 / 20));
		chooseLabel.setBounds((int) (width * 2.880921895006402 / 25), (int) (height * 10.892857142857142 / 20),
				(int) (width * 3.649167733674776 / 25), (int) (height * 1.2053571428571428 / 20));
		planeButton.setBounds((int) (width * 3.0409731113956466 / 25), (int) (height * 13.080357142857142 / 20),
				(int) (width * 2.6888604353393086 / 25), (int) (height * 1.6071428571428572 / 20));
		trainButton.setBounds((int) (width * 7.746478873239437 / 25), (int) (height * 13.348214285714286 / 20),
				(int) (width * 2.496798975672215 / 25), (int) (height * 1.25 / 20));
		vehicleButton.setBounds((int) (width * 12.195902688860436 / 25), (int) (height * 13.482142857142858 / 20),
				(int) (width * 2.496798975672215 / 25), (int) (height * 1.1160714285714286 / 20));
		motorButton.setBounds((int) (width * 16.389244558258643 / 25), (int) (height * 13.348214285714286 / 20),
				(int) (width * 3.0409731113956466 / 25), (int) (height * 1.25 / 20));
		addressLabel.setBounds((int) (width * 2.880921895006402 / 25), (int) (height * 15.892857142857142 / 20),
				(int) (width * 4.673495518565941 / 25), (int) (height * 1.3839285714285714 / 20));
		partField.setBounds((int) (width * 7.5864276568501925 / 25), (int) (height * 15.892857142857142 / 20),
				(int) (width * 1.9846350832266326 / 25), (int) (height * 1.4285714285714286 / 20));
		partLabel.setBounds((int) (width * 9.571062740076824 / 25), (int) (height * 15.892857142857142 / 20),
				(int) (width * 0.9923175416133163 / 25), (int) (height * 1.3839285714285714 / 20));
		rowField.setBounds((int) (width * 10.947503201024327 / 25), (int) (height * 15.892857142857142 / 20),
				(int) (width * 1.2163892445582587 / 25), (int) (height * 1.4285714285714286 / 20));
		rowLabel.setBounds((int) (width * 12.163892445582587 / 25), (int) (height * 15.892857142857142 / 20),
				(int) (width * 0.9923175416133163 / 25), (int) (height * 1.3839285714285714 / 20));
		shelfField.setBounds((int) (width * 13.54033290653009 / 25), (int) (height * 15.892857142857142 / 20),
				(int) (width * 1.1843790012804096 / 25), (int) (height * 1.4285714285714286 / 20));
		shelfLabel.setBounds((int) (width * 14.7247119078105 / 25), (int) (height * 15.892857142857142 / 20),
				(int) (width * 0.9923175416133163 / 25), (int) (height * 1.3839285714285714 / 20));
		seatField.setBounds((int) (width * 16.101152368758 / 25), (int) (height * 15.892857142857142 / 20),
				(int) (width * 1.1523687580025608 / 25), (int) (height * 1.4285714285714286 / 20));
		seatLabel.setBounds((int) (width * 17.253521126760564 / 25), (int) (height * 15.9375 / 20),
				(int) (width * 0.9603072983354674 / 25), (int) (height * 1.3392857142857142 / 20));
		confirmWarehousingButton.setBounds((int) (width * 10.979513444302176 / 25),
				(int) (height * 18.214285714285715 / 20), (int) (width * 3.23303457106274 / 25),
				(int) (height * 1.3839285714285714 / 20));
	}
}
