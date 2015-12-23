package presentation.commonui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class IComboBoxRenderer implements ListCellRenderer {

	private DefaultListCellRenderer defaultCellRenderer = new DefaultListCellRenderer();

	public IComboBoxRenderer() {
		super();
	}

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		JLabel renderer = (JLabel) defaultCellRenderer.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		if (isSelected) {
			//
			renderer.setBackground(Color.WHITE);
			renderer.setForeground(Color.WHITE);
		} else {
			renderer.setBackground(Color.WHITE);
		}
		//
		list.setSelectionBackground(Color.WHITE);
		list.setBorder(null);
		//
		renderer.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		renderer.setHorizontalAlignment(JLabel.CENTER);
		return renderer;
	}
}