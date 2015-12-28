package common;

import java.io.File;
import java.io.IOException;

public class FileGetter {
	public static File getFile(String path) {
		String workspace = System.getProperty("user.dir");
//		 File file = new File(workspace+"/.ELSInfo/" + path);
		File file = new File(workspace + "/info/" + path);
		return file;
	}

	public static void createFile(File file) {
		file.getParentFile().mkdirs();
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
