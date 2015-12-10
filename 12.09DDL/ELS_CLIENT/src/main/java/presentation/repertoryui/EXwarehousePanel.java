package presentation.repertoryui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import presentation.commonui.LocationHelper;

public class EXwarehousePanel extends JPanel {
	private JLabel inputLabel;
	private JLabel searchLabel;
	private JTextField inputField;
	private JButton confirmButton;

	private JLabel messageLabel;
	private JTable messageTable;

	private JButton confirm;
	private LocationHelper helper;

	public EXwarehousePanel() {
		inputLabel = new JLabel("请输入订单号");
		searchLabel = new JLabel();
		inputField = new JTextField();
		confirmButton = new JButton("确认");

		messageLabel = new JLabel("该订单详细信息");
		messageTable = new JTable();
		confirm = new JButton("确认出库");

		add(inputLabel);
		add(searchLabel);
		add(inputField);
		add(confirmButton);
		add(messageLabel);
		add(messageTable);
		add(confirm);

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
		confirm.setBounds((int) (width * 10.979513444302176 / 25), (int) (height * 17.410714285714285 / 20),
				(int) (width * 2.592829705505762 / 25), (int) (height * 1.4285714285714286 / 20));
	}

}
