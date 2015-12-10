package init;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import common.FileGetter;

public class UserNameController {
	public ArrayList<String> getUserName() {

		try {
			File file = FileGetter.getFile("users.dat");

			if (!file.exists())
				return null;

			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<String> userName = (ArrayList<String>) in.readObject();
			in.close();

			return userName;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteName(String userName) {

		try {
			File file = FileGetter.getFile("users.dat");

			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<String> userNames = (ArrayList<String>) in.readObject();
			in.close();

			int i;
			for (i = 0; i < userNames.size(); i++)
				if (userNames.get(i).equals(userName)) {
					break;
				}

			userNames.remove(i);
			if (userNames.isEmpty()) {
				file.delete();
			}

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(userNames);
			out.close();

			return true;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	public boolean addNewName(String userName) {
		if (userName.equals(""))
			return false;
		try {
			File file = FileGetter.getFile("users.dat");

			if (!file.exists()) {
				file.createNewFile();
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("users.dat")));
				ArrayList<String> userNames = new ArrayList<String>();
				userNames.add(userName);

				out.writeObject(userNames);
				out.close();
				return true;
			}

			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<String> userNames = (ArrayList<String>) in.readObject();
			in.close();

			for (String i : userNames)
				if (i.equals(userName))
					return false;

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
