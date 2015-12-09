package presentation.repertoryui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import presentation.commonui.LocationHelper;
import businesslogic.repertorybl.RepertoryBL;


public class InitializeInformationPanel extends JPanel {
	
	private static final long serialVersionUID = -147L;

	private RepertoryFrame fatherFrame;
	
	private RepertoryBL repertoryBL;
	
	private JLabel maxRowLabel;
	private JTextField maxRowField;

	private JLabel maxShelfLabel;
	private JTextField maxShelfField;

	private JLabel maxBitLabel;
	private JTextField maxBitField;

	private JLabel maxRatioLabel;
	private JTextField maxRatioField;
	private JLabel maxRatioTip;

	private JButton confirmButton;

	private LocationHelper helper;

	public InitializeInformationPanel(RepertoryFrame frame) {
		repertoryBL = new RepertoryBL("CK-00001");
		
		maxRowLabel = new JLabel("请输入最大排数");
		maxRowField = new JTextField();

		maxShelfLabel = new JLabel("请输入最大架数");
		maxShelfField = new JTextField();

		maxBitLabel = new JLabel("请输入最大位数");
		maxBitField = new JTextField();
		
		maxRatioLabel = new JLabel("请输入警戒比例");
		maxRatioField = new JTextField();
		maxRatioTip = new JLabel("100以内的正整数");

		confirmButton = new JButton();

		add(maxRowLabel);
		add(maxRowField);
		add(maxShelfLabel);
		add(maxShelfField);
		add(maxBitLabel);
		add(maxBitField);
		add(maxRatioLabel);
		add(maxRatioField);
		add(maxRatioTip);
		add(confirmButton);

		helper = new LocationHelper(this);

		setLayout(null);
		
		confirmButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				int maxRow = Integer.parseInt(maxRowField.getText());
				int maxShelf = Integer.parseInt(maxShelfField.getText());
				int maxDigit = Integer.parseInt(maxBitField.getText());
				int warningRatio = Integer.parseInt(maxRatioField.getText());
				repertoryBL.inventoryInitialization(maxRow, maxShelf, maxDigit, warningRatio);
			}
		});
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		//

		maxRowLabel.setBounds((int) (width * 2.848911651728553 / 25), (int) (height * 2.544642857142857 / 20),
				(int) (width * 5.60179257362356 / 25), (int) (height * 1.5178571428571428 / 20));
		maxRowField.setBounds((int) (width * 9.891165172855313 / 25), (int) (height * 2.544642857142857 / 20),
				(int) (width * 5.857874519846351 / 25), (int) (height * 1.5625 / 20));
		maxShelfLabel.setBounds((int) (width * 2.848911651728553 / 25), (int) (height * 4.642857142857143 / 20),
				(int) (width * 5.60179257362356 / 25), (int) (height * 1.5178571428571428 / 20));
		maxShelfField.setBounds((int) (width * 9.891165172855313 / 25), (int) (height * 4.642857142857143 / 20),
				(int) (width * 5.857874519846351 / 25), (int) (height * 1.5625 / 20));
		maxBitLabel.setBounds((int) (width * 2.848911651728553 / 25), (int) (height * 6.651785714285714 / 20),
				(int) (width * 5.60179257362356 / 25), (int) (height * 1.5178571428571428 / 20));
		maxBitField.setBounds((int) (width * 9.891165172855313 / 25), (int) (height * 6.651785714285714 / 20),
				(int) (width * 5.857874519846351 / 25), (int) (height * 1.5625 / 20));
		maxRatioLabel.setBounds((int) (width * 2.848911651728553 / 25), (int) (height * 8.705357142857142 / 20),
				(int) (width * 5.60179257362356 / 25), (int) (height * 1.5178571428571428 / 20));
		maxRatioField.setBounds((int) (width * 9.891165172855313 / 25), (int) (height * 8.705357142857142 / 20),
				(int) (width * 5.857874519846351 / 25), (int) (height * 1.5625 / 20));
		maxRatioTip.setBounds((int) (width * 17.509603072983353 / 25), (int) (height * 8.705357142857142 / 20),
				(int) (width * 4.801536491677337 / 25), (int) (height * 1.5178571428571428 / 20));
		confirmButton.setBounds((int) (width * 11.107554417413573 / 25), (int) (height * 13.125 / 20),
				(int) (width * 2.272727272727273 / 25), (int) (height * 1.8303571428571428 / 20));
		
	}

}
