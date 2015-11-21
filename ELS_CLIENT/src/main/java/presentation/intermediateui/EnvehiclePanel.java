package presentation.intermediateui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class EnvehiclePanel extends JLabel {
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	private JButton envehicle;
	private JButton next;
	private JButton previous;

	private JLabel function;

	private EnvehicleInfoTable info;

	public EnvehiclePanel() {
		envehicle = new JButton("do");
		next = new JButton("next");
		previous = new JButton("previous");

		function = new JLabel("装车分配");

		info = new EnvehicleInfoTable(13, 5);

		setCmpLocation();

		envehicle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				envehicleui();
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
				previous();
			}
		});

		setLayout(null);

		add(envehicle);
		add(next);
		add(previous);
		add(function);
		add(info);
	}

	public void setCmpLocation() {
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		next.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		previous.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		envehicle.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 3 / 16,
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
	
	public void envehicleui(){
		
	}
	
	public void nextui(){
		
	}
	
	public void previous(){
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new EnvehiclePanel());
		frame.setVisible(true);
	}
}
