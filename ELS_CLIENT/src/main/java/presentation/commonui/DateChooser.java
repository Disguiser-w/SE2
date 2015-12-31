package presentation.commonui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;

public class DateChooser extends JFrame {
	private MyTextField textField;
	private MyLabel label;
	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

	public DateChooser(MyTextField t, MyLabel label) {
		final DateChooserPanel dc = new DateChooserPanel(this, true);

		this.textField = t;
		this.label = label;

		this.label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dc.setLocationRelativeTo(textField);
				
				dc.setLocation(dc.getX() + 25, dc.getY() + 150);
				dc.setVisible(true);
				Calendar cal = dc.getSelectedDate();
				if (cal != null) {
					textField.setText(FORMATTER.format(cal.getTime()));
				}

			}
		});

		pack();
		// setLocation
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	//
	// public static void main(String args[]) {
	// new DataChoose().setVisible(true);
	// }

}