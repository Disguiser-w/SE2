package common;

import java.io.File;

public class FileGetter {
	public static File getFile(String path) {
		String workspace = System.getProperty("user.dir");
		// File file = new File(workspace+"/.ELSInfo/" + path);
		File file = new File(workspace + "/" + path);
		return file;
	}
}
