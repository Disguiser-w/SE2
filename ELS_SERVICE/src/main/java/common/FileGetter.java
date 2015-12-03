package common;

import java.io.File;

public class FileGetter {
	public static File getFile(String path){
		return new File("target/info/"+path);
	}
}
