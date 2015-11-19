package presentation.businessui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import presentation.commonui.LocationHelper;

public class EnVehiclePanel extends JPanel {
	private JTable messageTable;
	private JLabel nextPageLabel;
	private JLabel previousPageLabel;
	private JButton confirmButton;
	private JButton distributeButton;
	private LocationHelper helper;

	public EnVehiclePanel() {
		messageTable = new JTable();
		nextPageLabel = new JLabel();
		previousPageLabel = new JLabel();
		confirmButton = new JButton();
		distributeButton = new JButton();

		add(messageTable);
		add(nextPageLabel);
		add(previousPageLabel);
		add(confirmButton);
		add(distributeButton);

		setLayout(null);

		helper = new LocationHelper(this);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		messageTable.setBounds((int) (width * 0.9923175416133163 / 25), (int) (height * 1.2946428571428572 / 20),
				(int) (width * 23.01536491677337 / 25), (int) (height * 14.642857142857142 / 20));
		nextPageLabel.setBounds((int) (width * 13.380281690140846 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.0243277848911652 / 25), (int) (height * 1.4732142857142858 / 20));
		previousPageLabel.setBounds((int) (width * 11.555697823303458 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.0243277848911652 / 25), (int) (height * 1.4732142857142858 / 20));

		confirmButton.setBounds((int) (width * 21.9910371318822 / 25), (int) (height * 17.232142857142858 / 20),
				(int) (width * 1.9846350832266326 / 25), (int) (height * 1.3839285714285714 / 20));
		distributeButton.setBounds((int) (width * 19.206145966709347 / 25), (int) (height * 17.232142857142858 / 20),
				(int) (width * 1.9846350832266326 / 25), (int) (height * 1.3839285714285714 / 20));
	}
}
