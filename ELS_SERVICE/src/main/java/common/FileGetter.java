package common;

import java.io.File;
import java.io.IOException;

public class FileGetter {
	public static File getFile(String path) {
		File file = new File("target/info/" + path);

		return file;
	}
}
