package presentation.financeui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class PaymentReceiptPanel extends JLabel {
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	private String[] clauseType = { "运费", "租金", "工资" };

	JButton dateChoose;
	JButton ok;
	JButton printButton;
	JButton sendButton;
	JButton next;
	JButton previous;

	JLabel function;
	JLabel clause;
	JLabel date;

	JTextField dateInput;

	JComboBox clauseChoose;

	PaymentReceiptInfoTable info;

	public PaymentReceiptPanel() {
		dateChoose = new JButton("date");
		ok = new JButton("ok");
		printButton = new JButton("print");
		sendButton = new JButton("send");
		next = new JButton("next");
		previous = new JButton("previous");

		function = new JLabel("新建付款单");
		clause = new JLabel("条目");
		date = new JLabel("日期");

		dateInput = new JTextField("2015/11", 8);

		clauseChoose = new JComboBox(clauseType);

		info = new PaymentReceiptInfoTable(13, 6);

		setCmpLocation();

		dateChoose.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				dateChooseui();
			}
		});

		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				ok();
			}
		});

		printButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				printui();
			}
		});

		sendButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				sendui();
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

		add(dateChoose);
		add(ok);
		add(printButton);
		add(sendButton);
		add(next);
		add(previous);
		add(function);
		add(clause);
		add(date);
		add(dateInput);
		add(clauseChoose);
		add(info);
	}

	public void setCmpLocation() {
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 5 / 18, PANEL_HEIGHT / 12);
		printButton.setBounds(PANEL_WIDTH * 7 / 9, PANEL_HEIGHT / 24,
				PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
		sendButton.setBounds(PANEL_WIDTH * 8 / 9, PANEL_HEIGHT / 24,
				PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
		clause.setBounds(PANEL_WIDTH/9, PANEL_HEIGHT*3/16, PANEL_WIDTH/18, PANEL_HEIGHT/24);
		clauseChoose.setBounds(PANEL_WIDTH*7/36, PANEL_HEIGHT*3/16, PANEL_WIDTH/12, PANEL_HEIGHT/24);
		date.setBounds(PANEL_WIDTH *11/36, PANEL_HEIGHT * 3 / 16, PANEL_WIDTH / 18,
				PANEL_HEIGHT / 24);
		dateInput.setBounds(PANEL_WIDTH * 7 / 18, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 12, PANEL_HEIGHT / 24);
		ok.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		next.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		previous.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);

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

	public void dateChooseui() {

	}

	public void ok() {

	}

	public void printui() {

	}

	public void sendui() {

	}

	public void nextui() {

	}

	public void previousui() {

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new PaymentReceiptPanel());
		frame.setVisible(true);
	}
}
