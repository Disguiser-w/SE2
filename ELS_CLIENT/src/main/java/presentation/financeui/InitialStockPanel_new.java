package presentation.financeui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import businesslogic.financebl.controller.InitialStockBLController;

public class InitialStockPanel_new extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	private JButton InfoOKButton;
	private JButton next;
	private JButton previous;

	private JCheckBox all;

	private JLabel function;
	private JLabel organizationInfo;
	private JLabel humanInfo;
	private JLabel vehicleInfo;
	private JLabel stockInfo;
	private JLabel accountInfo;

	private InitialStockInfoTable_new info;
	
	public InitialStockBLController controller;

	public InitialStockPanel_new(InitialStockBLController controller) {
		this.controller=controller;
		InfoOKButton = new JButton("ok");
		next = new JButton("next");
		previous = new JButton("pre");

		all = new JCheckBox("all");

		function = new JLabel("期初建账");
		organizationInfo = new JLabel("机构信息");
		humanInfo = new JLabel("人员信息");
		vehicleInfo = new JLabel("车辆信息");
		stockInfo = new JLabel("库存信息");
		accountInfo = new JLabel("银行账户");

		info = new InitialStockInfoTable_new(13, 3);
		
		setCmpLocation();

		InfoOKButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				okui();
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

		add(InfoOKButton);
		add(all);
		add(function);
		add(organizationInfo);
		add(humanInfo);
		add(vehicleInfo);
		add(stockInfo);
		add(accountInfo);
		add(next);
		add(previous);
		add(info);
	}

	public void setCmpLocation() {
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		organizationInfo.setBounds(PANEL_WIDTH / 6, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
		humanInfo.setBounds(PANEL_WIDTH * 5 / 18, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
		vehicleInfo.setBounds(PANEL_WIDTH * 7 / 18, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
		stockInfo.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
		accountInfo.setBounds(PANEL_WIDTH * 11 / 18, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
		InfoOKButton.setBounds(PANEL_WIDTH * 15 / 18, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		next.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		previous.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		all.setBounds(PANEL_WIDTH * 5 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);

		info.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 4 / 15,
				PANEL_WIDTH * 5 / 6, PANEL_HEIGHT * 13 / 20);
	}

	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation();
		repaint();
	}

	public void okui() {

	}

	public void previousui() {

	}

	public void nextui() {

	}

//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.setSize(800, 550);
//		frame.add(new InitialStockPanel_new());
//		frame.setVisible(true);
//	}
}
