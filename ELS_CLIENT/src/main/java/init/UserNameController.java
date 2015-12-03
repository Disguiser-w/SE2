package init;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserNameController {
	public ArrayList<String> getUserName() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("users.dat")));
			ArrayList<String> userName = (ArrayList<String>) in.readObject();
			in.close();

			return userName;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public boolean addNewName(String userName) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("users.dat")));
			ArrayList<String> userNames = (ArrayList<String>) in.readObject();
			in.close();

			userNames.add(userName);

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("users.dat")));
			out.writeObject(userNames);
			out.close();

			return true;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	public Image getImageByName(String name) {
		String type = name.substring(1, 2);
		switch (type) {
		case "KDY":
			return null;
		case "YYT":
			return null;
		default:
			return null;
		}
	}
}
