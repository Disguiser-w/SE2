package edu.nju.express.init;

import javax.swing.JOptionPane;

import edu.nju.express.presentation.ClientFrame;

public class Client {

    public static void main(String[] args) {
        try {
            RMIHelper.init();
            new ClientFrame();
        } catch (ClientInitException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Client boots fail!\nCause: " + e.getMessage(),
                    "Fatal Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }
}
