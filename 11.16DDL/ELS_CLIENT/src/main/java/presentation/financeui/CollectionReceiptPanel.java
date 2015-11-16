package presentation.financeui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CollectionReceiptPanel extends JPanel {
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	JButton dateChoose;
	JButton infook;
	JButton ok;
	JButton next;
	JButton previous;
	JButton total;

	JLabel function;
	JLabel date;
	JLabel businessHall;
	JLabel infoLine;

	JTextField dateInput;
	JTextField businessHallIDInput;

	CollectionReceiptInfoTable info;

	public CollectionReceiptPanel() {
		dateChoose = new JButton("date");
		infook = new JButton("infook");
		ok = new JButton("ok");
		next = new JButton("next");
		previous = new JButton("previous");
		total = new JButton("total");

		function = new JLabel("新建入款单");
		date = new JLabel("日期");
		businessHall = new JLabel("营业厅");
		infoLine = new JLabel("时间：2015/11/1  合计金额：970");

		dateInput = new JTextField("2015/11/6");
		businessHallIDInput = new JTextField("025-000");

		info = new CollectionReceiptInfoTable(13, 4);

		setCmpLocation();

		dateChoose.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				dateChooseui();
			}
		});

		infook.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				infookui();
			}
		});

		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				okui();
			}
		});

		total.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				totalui();
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
		add(infook);
		add(ok);
		add(total);
		add(next);
		add(previous);
		add(function);
		add(date);
		add(infoLine);
		add(businessHall);
		add(dateInput);
		add(businessHallIDInput);
		add(info);
	}

	public void setCmpLocation() {
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 5 / 18, PANEL_HEIGHT / 12);
		date.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 18, PANEL_HEIGHT / 24);
		dateInput.setBounds(PANEL_WIDTH * 7 / 36, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 12, PANEL_HEIGHT / 24);
		businessHall.setBounds(PANEL_WIDTH * 13 / 36, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 18, PANEL_HEIGHT / 24);
		businessHallIDInput.setBounds(PANEL_WIDTH * 4 / 9,
				PANEL_HEIGHT * 3 / 16, PANEL_WIDTH / 12, PANEL_HEIGHT / 24);
		ok.setBounds(PANEL_WIDTH * 11 / 18, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		infook.setBounds(PANEL_WIDTH * 11 / 36, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		next.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		previous.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		total.setBounds(PANEL_WIDTH / 18, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		infoLine.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH * 5 / 12, PANEL_HEIGHT / 24);

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

	public void infookui() {

	}

	public void okui() {

	}

	public void totalui() {

	}

	public void nextui() {

	}

	public void previousui() {

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new CollectionReceiptPanel());
		frame.setVisible(true);
	}
}
