package common;

import java.io.File;
import java.io.IOException;

public class FileGetter {
	public static File getFile(String path) {
		File file = new File("info/" + path);

		return file;
	}
}
