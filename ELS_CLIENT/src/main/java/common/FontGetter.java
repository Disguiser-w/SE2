package common;

import java.awt.*;

public class FontGetter {// 获得当前系统字体

	public FontGetter() {
	}// 构造器

	public void getfont() {//

		String[] fontnames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();// 获得当前系统字体

		for (int i = 0; i < fontnames.length; i++) {// 输出所有字体
			System.out.println(fontnames[i]);
		}
	}

	public static void main(String[] args) {
		FontGetter f = new FontGetter();
		f.getfont();
	}
}