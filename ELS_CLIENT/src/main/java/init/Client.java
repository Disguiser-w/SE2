package init;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import common.FileGetter;
import presentation.mainui.MainFrame;
import testConnection.TestConnection;

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
