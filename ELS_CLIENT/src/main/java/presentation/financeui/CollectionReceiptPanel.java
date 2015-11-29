package presentation.financeui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 暂时先把根据营业厅筛选的去掉了，以后有时间再说吧
 * */
public class CollectionReceiptPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String hallID_str;
	String date_str;
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	private JButton dateChooseButton;
	private JButton infoOKButton;
	private JButton collectionOKButton;
	private JButton next;
	private JButton previous;
	private JButton totalButton;

	private JLabel function;
	private JLabel date;
//	private JLabel businessHall;
	private JLabel infoLine;

	private JTextField date_Input;
//	private JTextField businessHall_ID_Input;

	private CollectionReceiptInfoTable info;

	public CollectionReceiptPanel() {
		dateChooseButton = new JButton("date");
		infoOKButton = new JButton("infook");
		collectionOKButton = new JButton("ok");
		next = new JButton("next");
		previous = new JButton("previous");
		totalButton = new JButton("total");

		function = new JLabel("新建入款单");
		date = new JLabel("日期");
//		businessHall = new JLabel("营业厅");
		infoLine = new JLabel("时间：2015/11/1  合计金额：970");

		date_Input = new JTextField("");
//		businessHall_ID_Input = new JTextField("");

		info = new CollectionReceiptInfoTable(13, 4);

		setCmpLocation();
/**
 * 选择日期
 * */
		dateChooseButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				dateChooseui();
			}
		});

		/**
		 * 确认日期输入
		 * */
		infoOKButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				infookui();
			}
		});

		collectionOKButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				okui();
			}
		});

		totalButton.addActionListener(new ActionListener() {

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

		add(dateChooseButton);
		add(infoOKButton);
		add(collectionOKButton);
		add(totalButton);
		add(next);
		add(previous);
		add(function);
		add(date);
		add(infoLine);
//		add(businessHall);
		add(date_Input);
//		add(businessHall_ID_Input);
		add(info);
	}

	public void setCmpLocation() {
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 5 / 18, PANEL_HEIGHT / 12);
		date.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 18, PANEL_HEIGHT / 24);
		date_Input.setBounds(PANEL_WIDTH * 7 / 36, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 12, PANEL_HEIGHT / 24);
//		businessHall.setBounds(PANEL_WIDTH * 13 / 36, PANEL_HEIGHT * 3 / 16,
//				PANEL_WIDTH / 18, PANEL_HEIGHT / 24);
//		businessHall_ID_Input.setBounds(PANEL_WIDTH * 4 / 9,
//				PANEL_HEIGHT * 3 / 16, PANEL_WIDTH / 12, PANEL_HEIGHT / 24);
		collectionOKButton.setBounds(PANEL_WIDTH * 11 / 18, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		infoOKButton.setBounds(PANEL_WIDTH * 11 / 36, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		next.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		previous.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		totalButton.setBounds(PANEL_WIDTH / 18, PANEL_HEIGHT * 45 / 48,
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

	/**
	 * 输入（选择）日期的方法,这个怎么写
	 * */
	public void infookui() {
//		date_str=date_Input.getText();
//		if(date_str.equals("")){
//			JOptionPane.showMessageDialog(null, "请输入完整信息！", "提示",
//					JOptionPane.CLOSED_OPTION);
//		}

	}

	/**
	 * 确定输入日期的方法
	 * */
	public void okui() {
		//这里需要格式的转化"2015/11/10——20151110"
		date_str=date_Input.getText();
		System.out.println(date_str);

		if(date_str.equals("")){
			JOptionPane.showMessageDialog(null, "请输入完整信息！", "提示",
					JOptionPane.CLOSED_OPTION);
		}
		else if(date_str.length()!=8){
			JOptionPane.showMessageDialog(null, "请输入正确的日期！", "提示",
					JOptionPane.CLOSED_OPTION);
			date_Input.setText("");
		}
		else if(date_str.substring(0,4).compareTo("2015")>0||date_str.substring(4,6).compareTo("12")>0||date_str.substring(6,8).compareTo("31")>0){
			JOptionPane.showMessageDialog(null, "请输入正确的日期！", "提示",
					JOptionPane.CLOSED_OPTION);
			date_Input.setText("");
		}
		else{
			
			
		}

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
