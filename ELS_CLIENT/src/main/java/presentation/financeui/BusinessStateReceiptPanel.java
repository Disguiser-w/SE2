package presentation.financeui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import presentation.intermediateui.TransferingPanel;
import vo.CollectionReceiptVO;
import vo.PaymentReceiptVO;

public class BusinessStateReceiptPanel extends JPanel {
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	ArrayList<CollectionReceiptVO> collectionReceiptList;
	ArrayList<PaymentReceiptVO> paymentReceiptList;

	JButton startDate;
	JButton endDate;
	JButton dateOKButton;
	JButton printButton;
	JButton sendButton;
	JButton next;
	JButton previous;

	JLabel function;
	JLabel dateRange;

	JTextField inputStartDate;
	JTextField inputEndDate;

	BusinessStateReceiptInfoTable info;

	public BusinessStateReceiptPanel() {
		startDate = new JButton("start");
		endDate = new JButton("end");
		dateOKButton = new JButton("ok");
		printButton = new JButton("print");
		sendButton = new JButton("send");
		next = new JButton("next");
		previous = new JButton("previous");

		function = new JLabel("经营情况表");
		dateRange = new JLabel("日期范围");

		inputStartDate = new JTextField("2015/10/30", 11);
		inputEndDate = new JTextField("2015/11/05", 11);

		info = new BusinessStateReceiptInfoTable(13, 4);
		info.setList(collectionReceiptList, paymentReceiptList);

		setCmpLocation();

		startDate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				startui();
			}
		});

		endDate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				endui();
			}
		});

		dateOKButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				dateOK();
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

		add(startDate);
		add(endDate);
		add(dateOKButton);
		add(printButton);
		add(sendButton);
		add(next);
		add(previous);
		add(function);
		add(dateRange);
		add(inputEndDate);
		add(inputStartDate);
		add(info);
	}

	public void setCmpLocation() {
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 5 / 18, PANEL_HEIGHT / 12);
		printButton.setBounds(PANEL_WIDTH * 7 / 9, PANEL_HEIGHT / 24,
				PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
		sendButton.setBounds(PANEL_WIDTH * 8 / 9, PANEL_HEIGHT / 24,
				PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
		dateRange.setBounds(PANEL_WIDTH / 4, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
		startDate.setBounds(PANEL_WIDTH * 19 / 36, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		inputStartDate.setBounds(PANEL_WIDTH * 7 / 18, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
		endDate.setBounds(PANEL_WIDTH * 13 / 18, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		inputEndDate.setBounds(PANEL_WIDTH * 7 / 12, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
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

	public void setList(ArrayList<CollectionReceiptVO> collectionReceiptList,
			ArrayList<PaymentReceiptVO> paymentReceiptList) {
		this.collectionReceiptList = collectionReceiptList;
		this.paymentReceiptList = paymentReceiptList;
	}

	public void startui() {

	}

	public void endui() {

	}

	public void dateOK() {

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
		frame.add(new BusinessStateReceiptPanel());
		frame.setVisible(true);
	}
}