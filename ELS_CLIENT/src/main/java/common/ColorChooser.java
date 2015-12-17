package common;

import java.awt.Color;

import javax.swing.JColorChooser;

public class ColorChooser {
	public static void main(String[] args) {
		while (true) {
			Color c = JColorChooser.showDialog(null, "Choose a Color", null);
			if (c == null)
				break;
			System.out.println(c.getRed() + "," + c.getGreen() + "," + c.getBlue());
		}
	}
}
