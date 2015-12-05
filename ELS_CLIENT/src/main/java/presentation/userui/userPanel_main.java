package presentation.userui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

import java.util.ArrayList;

import businesslogic.userbl.UserBL;
import vo.UserVO;

public class userPanel_main extends JPanel {
	UserBL userBL;
	
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	
	private JLabel function;

	private JButton addButton;
	private JButton deleteButton;
	private JButton searchButton;
	private JButton previous;
	private JButton next;

	private JTextField searchTextField;

	private userInfoTable_main infoTable;
	private JLabel MessageLabel;

	public userPanel_main() {
		this.userBL = new UserBL();
		
        function = new JLabel("用户管理 ");
        
        addButton = new JButton("新增用户");
        deleteButton = new JButton("删除用户");
        searchTextField = new JTextField("用户编号");
        searchButton = new JButton("查找");
        
        previous = new JButton("上一页");
        next = new JButton("下一页");
        
        infoTable = new userInfoTable_main(13,7);
        
        setCmpLocation();
        
        addButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				addui();
			}
		});
        
        deleteButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				deleteui();
			}
		});
        
        previous.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				previousui();
			}
		});

        next.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				nextui();
			}
		});
        
        
        setLayout(null);

        add(function);
        add(addButton);
        add(deleteButton);
        add(searchTextField);
        add(searchButton);
        add(infoTable);
        add(previous);
        add(next);
        
	}
	
	public void setCmpLocation(){
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		addButton.setBounds(PANEL_WIDTH * 3 / 18, PANEL_HEIGHT * 2 / 16,
				PANEL_WIDTH * 4 / 27, PANEL_HEIGHT / 16);
		deleteButton.setBounds(PANEL_WIDTH * 7 / 18, PANEL_HEIGHT * 2 / 16,
				PANEL_WIDTH * 4 / 27, PANEL_HEIGHT / 16);
		searchTextField.setBounds(PANEL_WIDTH * 12 / 18, PANEL_HEIGHT * 2 / 16,
				PANEL_WIDTH * 4 / 27, PANEL_HEIGHT / 16);
		searchButton.setBounds(PANEL_WIDTH * 22 / 27, PANEL_HEIGHT * 2 / 16,
				PANEL_WIDTH * 3 / 27, PANEL_HEIGHT / 16);
		infoTable.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 4 / 15,
				PANEL_WIDTH * 5 / 6, PANEL_HEIGHT * 13 / 20);
		previous.setBounds(PANEL_WIDTH * 52 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 16);
		next.setBounds(PANEL_WIDTH * 62 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 16);
	}
	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation();
		repaint();
	}

	public void addui() {

	}

	public void deleteui() {

	}

	public void nextui() {

	}

	public void previousui() {

	}
	
	public void showAllUserInfo(){
		ArrayList<UserVO> userList = userBL.showAllUsers();
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new userPanel_main());
		frame.setVisible(true);
	}
}
