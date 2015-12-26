package presentation.special_ui;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import common.ImageGetter;
import presentation.commonui.MyLabel;

public class ExitLabel extends MyLabel {
	public ExitLabel() {
		this("");
	}

	public ExitLabel(String str) {
		setText(str);
		Image normal = ImageGetter.getImage("close_0.png").getImage();
		Image hover = ImageGetter.getImage("close_1.png").getImage();
		Image press = ImageGetter.getImage("close_2.png").getImage();

		setImages(normal, hover, press);
		
		
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.exit(0);
			}
		});
	}
}
