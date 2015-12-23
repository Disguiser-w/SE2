
package init;

import presentation.mainui.MainFrame;

public class Client {
	private MainFrame frame;

	public Client() {
		frame = new MainFrame();
		frame.showFrame();
	}

	public static void main(String[] args) {
		new Client();
	}
}
