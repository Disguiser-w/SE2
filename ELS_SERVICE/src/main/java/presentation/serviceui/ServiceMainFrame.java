package presentation.serviceui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import init.Service;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTextField;

public class ServiceMainFrame extends JFrame {
	private boolean isStart;
	private Service service;
	private MyLabel startServiceLabel;
	private MyLabel stopServiceLabel;
	private MyTextField message;

	public ServiceMainFrame() {
		isStart = false;
		service = new Service();
		setSize(400, 300);
		setTitle("ELS快递物流系统服务端");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		startServiceLabel = new MyLabel("打开服务");
		stopServiceLabel = new MyLabel("停止服务");
		message = new MyTextField();

		message.setEditable(false);
		startServiceLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!isStart) {
					if (service.startService()) {
						isStart = true;
						showMessage("服务进程已启动!");
						(new Thread(new Runnable() {
							public void run() {
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
								showMessage("服务进程正在运行中!");
							}
						})).start();
					} else {

						warnning("服务进程启动失败,请检查网络连接或IP地址！");
						(new Thread(new Runnable() {
							public void run() {
								try {
									Thread.sleep(3000);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
								showMessage("");
							}
						})).start();

					}
				} else {

					warnning("服务已启动!");

					(new Thread(new Runnable() {
						public void run() {
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							showMessage("服务进程正在运行中!");
						}
					})).start();

				}
			}
		});

		stopServiceLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (isStart) {
					isStart = false;
					if (service.stopService()) {

						showMessage("服务已停止!");
						(new Thread(new Runnable() {
							public void run() {
								try {
									Thread.sleep(3000);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
								showMessage("");
							}
						})).start();

					}
				} else {
					warnning("服务未开启");

					(new Thread(new Runnable() {
						public void run() {
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							showMessage("");
						}
					})).start();
				}
			}
		});

		panel.setLayout(null);
		panel.add(startServiceLabel);
		panel.add(stopServiceLabel);
		panel.add(message.getLabel());
		panel.setSize(400, 300);

		startServiceLabel.setBounds(70, 175, 80, 30);
		stopServiceLabel.setBounds(250, 175, 80, 30);
		message.setBounds(50, 75, 300, 30);

		add(panel);

		setSize(400, 300);
		setVisible(true);
	}

	public void showMessage(String str) {
		message.setForeground(Color.BLACK);
		message.setText(str);
		repaint();
	}

	public void warnning(String str) {
		message.setForeground(Color.RED);
		message.setText(str);
		repaint();
	}

}
