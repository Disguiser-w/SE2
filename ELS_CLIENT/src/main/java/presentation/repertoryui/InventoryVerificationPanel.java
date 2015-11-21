package presentation.repertoryui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import presentation.commonui.LocationHelper;

public class InventoryVerificationPanel extends JPanel {
	//
	private JLabel endTimeLabel;
	private JLabel timeLabel;

	private JTable messageTable;

	private JLabel previousPageLabel;
	private JLabel nextPageLabel;

	private LocationHelper helper;

	public InventoryVerificationPanel() {
		endTimeLabel = new JLabel();
		timeLabel = new JLabel();

		messageTable = new JTable();
		previousPageLabel = new JLabel();
		nextPageLabel = new JLabel();

		add(endTimeLabel);
		add(timeLabel);
		add(messageTable);
		add(previousPageLabel);
		add(nextPageLabel);

		setLayout(null);

		helper = new LocationHelper(this);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		//
		endTimeLabel.setBounds((int) (width * 8.162612035851472 / 25), (int) (height * 1.6071428571428572 / 20),
				(int) (width * 2.848911651728553 / 25), (int) (height * 1.5178571428571428 / 20));
		timeLabel.setBounds((int) (width * 12.003841229193341 / 25), (int) (height * 1.6071428571428572 / 20),
				(int) (width * 4.353393085787452 / 25), (int) (height * 1.5178571428571428 / 20));
		messageTable.setBounds((int) (width * 1.2163892445582587 / 25), (int) (height * 4.285714285714286 / 20),
				(int) (width * 22.823303457106274 / 25), (int) (height * 13.125 / 20));
		previousPageLabel.setBounds((int) (width * 21.15877080665813 / 25), (int) (height * 17.901785714285715 / 20),
				(int) (width * 0.8642765685019206 / 25), (int) (height * 1.2053571428571428 / 20));
		nextPageLabel.setBounds((int) (width * 22.98335467349552 / 25), (int) (height * 17.901785714285715 / 20),
				(int) (width * 0.8642765685019206 / 25), (int) (height * 1.2053571428571428 / 20));
	}
}
