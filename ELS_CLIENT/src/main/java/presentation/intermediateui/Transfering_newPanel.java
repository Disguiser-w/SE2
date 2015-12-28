package presentation.intermediateui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import businesslogic.intermediatebl.controller.IntermediateMainController;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import presentation.commonui.UserFrame;

public class Transfering_newPanel extends OperationPanel {
	protected IntermediateMainController controller;

	protected UserFrame frame;

	protected MyTable messageTable;

	protected JButton OKButton;

	protected JLabel function;

	protected JLabel order_ID;

	protected JTextField order_ID_input;

	protected int PANEL_WIDTH = 720;
	protected int PANEL_HEIGHT = 480;

	public Transfering_newPanel(IntermediateMainController c, UserFrame f,
			MyTable m) {
		this.controller = c;
		this.frame = f;
		this.messageTable = m;

		OKButton = new JButton("ok");

		function = new JLabel("新增中转订单");

		order_ID = new JLabel("订单编号");

		order_ID_input = new JTextField();

		OKButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					controller.getTransferingBL().addOrder(
							order_ID_input.getText());
				} catch (RemoteException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				frame.toMainPanel();
			}
		});

		setLayout(null);

		add(OKButton);
		add(function);
		add(order_ID);
		add(order_ID_input);

		setCmpLocation();
	}

	protected void setCmpLocation() {
		// TODO 自动生成的方法存根
		OKButton.setBounds((int) (PANEL_WIDTH * 21.37784090909091 / 25),
				(int) (PANEL_HEIGHT * 1.8140589569160999 / 20),
				(int) (PANEL_WIDTH * 1.7400568181818181 / 25),
				(int) (PANEL_HEIGHT * 1.4965986394557824 / 20));
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		order_ID.setBounds((int) (PANEL_WIDTH * 3.90625 / 25),
				(int) (PANEL_HEIGHT * 5.26077097505669 / 20),
				(int) (PANEL_WIDTH * 4.261363636363637 / 25),
				(int) (PANEL_HEIGHT * 1.9047619047619047 / 20));
		order_ID_input.setBounds((int) (PANEL_WIDTH * 10.15625 / 25),
				(int) (PANEL_HEIGHT * 5.215419501133787 / 20),
				(int) (PANEL_WIDTH * 9.907670454545455 / 25),
				(int) (PANEL_HEIGHT * 1.8594104308390023 / 20));
	}
}
