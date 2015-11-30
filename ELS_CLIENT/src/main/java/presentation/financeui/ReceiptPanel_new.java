package presentation.financeui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ReceiptPanel_new extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	private JButton sendButton;
	private JButton printButton;
	private JButton costIncomeReceiptButton_new;
	private JButton collectionReceiptButton_new;
	private JButton paymentReceiptButton_new;
	private JButton next;
	private JButton previous;

	private JLabel function;
	private JLabel collectionReceiptInfo;
	private JLabel paymentReceiptInfo;
	private JLabel costIncomeReceiptInfo;

	private ReceiptInfoTable_new info;

	public ReceiptPanel_new() {
		sendButton = new JButton("send");
		printButton = new JButton("print");
		costIncomeReceiptButton_new = new JButton("new3");
		paymentReceiptButton_new = new JButton("new2");
		collectionReceiptButton_new = new JButton("new1");
		next = new JButton("next");
		previous = new JButton("pre");
		function = new JLabel("新建表单");
		collectionReceiptInfo = new JLabel("入款单");
		paymentReceiptInfo = new JLabel("付款单");
		costIncomeReceiptInfo = new JLabel("成本收益表");

		info = new ReceiptInfoTable_new(13, 5);
		
		setCmpLocation();

		sendButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				sendui();
			}
		});

		printButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				printui();
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

		collectionReceiptButton_new.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				new1ui();
			}
		});

		paymentReceiptButton_new.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				new2ui();
			}
		});

		costIncomeReceiptButton_new.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				new3ui();
			}
		});

		setLayout(null);

		add(sendButton);
		add(printButton);
		add(next);
		add(previous);
		add(collectionReceiptButton_new);
		add(costIncomeReceiptButton_new);
		add(paymentReceiptButton_new);
		add(function);
		add(collectionReceiptInfo);
		add(paymentReceiptInfo);
		add(costIncomeReceiptInfo);
		add(info);
	}

	public void setCmpLocation() {
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		next.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		previous.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		collectionReceiptInfo.setBounds(PANEL_WIDTH / 6, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
		paymentReceiptInfo.setBounds(PANEL_WIDTH * 5 / 18,
				PANEL_HEIGHT * 3 / 16, PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
		costIncomeReceiptInfo.setBounds(PANEL_WIDTH * 7 / 18,
				PANEL_HEIGHT * 3 / 16, PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
		printButton.setBounds(PANEL_WIDTH * 7 / 9, PANEL_HEIGHT / 24,
				PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
		sendButton.setBounds(PANEL_WIDTH * 8 / 9, PANEL_HEIGHT / 24,
				PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
		costIncomeReceiptButton_new.setBounds(PANEL_WIDTH * 5 / 9,
				PANEL_HEIGHT / 24, PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
		paymentReceiptButton_new.setBounds(PANEL_WIDTH * 4 / 9,
				PANEL_HEIGHT / 24, PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
		collectionReceiptButton_new.setBounds(PANEL_WIDTH * 3 / 9,
				PANEL_HEIGHT / 24, PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
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

	public void sendui() {

	}

	public void printui() {

	}

	public void new1ui() {

	}

	public void new2ui() {

	}

	public void new3ui() {

	}

	public void nextui() {

	}

	public void previousui() {

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new ReceiptPanel_new());
		frame.setVisible(true);
	}
}
