package presentation.intermediateui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import presentation.userui.userInfoTable_main;
import presentation.userui.userPanel_main;

public class TruckManagementPanel extends JLabel {
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	private JButton addButton;
	private JButton deleteButton;
	private JButton next;
	private JButton previous;

	private JLabel function;

	private JTextField searchTextField;
	
	private VehicleManagementInfoTable info;
	
	public TruckManagementPanel() {
        addButton = new JButton("add");
        deleteButton = new JButton("delete");
        next = new JButton("next");
        previous = new JButton("previous");
        
        function = new JLabel("汽车信息管理 ");
        
        searchTextField = new JTextField("KD-00001");
        
        info = new VehicleManagementInfoTable(13,5);
        
        setCmpLocation();
        
        addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				addui();
			}
		});
        
        deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				deleteui();
			}
		});
        
        next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				nextui();
			}
		});
        
        previous.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				previousui();
			}
		});
        
        setLayout(null);

        add(addButton);
        add(deleteButton);
        add(next);
        add(previous);
        add(function);
        add(searchTextField);
        add(info);
	}
	
	public void setCmpLocation(){
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		addButton.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		deleteButton.setBounds(PANEL_WIDTH * 5 / 9, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
		searchTextField.setBounds(PANEL_WIDTH * 13 / 18, PANEL_HEIGHT * 3 / 16,
				PANEL_WIDTH * 2 / 9, PANEL_HEIGHT / 24);
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

	public void addui() {

	}

	public void deleteui() {

	}

	public void nextui() {

	}

	public void previousui() {

	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new TruckManagementPanel());
		frame.setVisible(true);
	}
}
