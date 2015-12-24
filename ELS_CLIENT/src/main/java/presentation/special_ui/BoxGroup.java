package presentation.special_ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import presentation.commonui.MyCheckBox;

public class BoxGroup {
	private ArrayList<MyCheckBox> myBoxes;
	private int selectedIndex;

	public BoxGroup() {
		myBoxes = new ArrayList<MyCheckBox>();
		selectedIndex = 0;
	}

	public void add(MyCheckBox myBox) {
		if (myBoxes.size() == 0)
			myBox.setSelected(true);
		myBoxes.add(myBox);
		myBox.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				myBoxes.get(selectedIndex).setSelected(false);
				((MyCheckBox) (e.getSource())).setSelected(true);
				selectedIndex = myBoxes.indexOf((MyCheckBox) e.getSource());
			}
		});
	}
}
