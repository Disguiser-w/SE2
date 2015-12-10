package presentation.commonui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.*;

public class MultiSpanHeaderColorTest extends JPanel {

	private static final long serialVersionUID = -4323306588309001976L;

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MultiSpanHeaderColorTest().makeUI();
			}
		});
	}

	public void makeUI() {
		String[] title = { "", "001", "002", "003", "004", "005", "006", "007", "008", "009", "010", "011", "012",
				"013", "014", "015", "016", "017", "018", "019", "020", "021", "022", "023" };
		String[][] data = {
				{ "A0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
						"18", "19", "20", "21", "22", "23" },
				{ "A1", "01", "02", "03", "04", "5", "", "1", "2", "3", "4", "5", "", "1", "2", "3", "4", "5" },
				{ "B", "001", "002", "003", "004", "5", "", "001", "002", "003", "004", "5", "", "001", "002", "003",
						"004", "5" },
				{ "C", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
						"18", "19", "20" },
				{ "D", "001", "002", "003", "004", "5", "", "001", "002", "003", "004", "5", "", "001", "002", "003",
						"004", "5" },
				{ "E", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
						"18", "19", "20" },
				{ "H", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
						"18", "19", "20" } };
		JTable table = new JTable();
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.setDataVector(data, title);
		table.setModel(defaultTableModel);

		final JTableHeader header = table.getTableHeader();
		header.setReorderingAllowed(false);
		final TableColumnModel model = table.getColumnModel();
		Enumeration<TableColumn> enumColumns = model.getColumns();
		final List<TableColumn> columns = Collections.list(enumColumns);

		String[] dummyTitle = { "", "String1", "String2" };
		final JTable dummyTable = new JTable(null, dummyTitle);
		final JTableHeader dummyHeader = dummyTable.getTableHeader();
		dummyHeader.setReorderingAllowed(false);
		dummyHeader.setResizingAllowed(false);
		final TableColumnModel dummyModel = dummyTable.getColumnModel();
		Enumeration<TableColumn> enumDummyColumns = dummyModel.getColumns();
		final List<TableColumn> dummyColumns = Collections.list(enumDummyColumns);

		model.addColumnModelListener(new TableColumnModelListener() {

			@Override
			public void columnAdded(TableColumnModelEvent e) {
			}

			@Override
			public void columnRemoved(TableColumnModelEvent e) {
			}

			@Override
			public void columnMoved(TableColumnModelEvent e) {
			}

			@Override
			public void columnMarginChanged(ChangeEvent e) {
				dummyColumns.get(0).setWidth(columns.get(0).getWidth());
				int nWidth = 0;
				for (int n = 1; n < 11; n++) {
					nWidth = nWidth + columns.get(n).getWidth();
				}
				dummyColumns.get(1).setWidth(nWidth);
				nWidth = 0;

				for (int n = 11; n < 24; n++) {
					nWidth = nWidth + columns.get(n).getWidth();
				}
				dummyColumns.get(2).setWidth(nWidth);
			}

			@Override
			public void columnSelectionChanged(ListSelectionEvent e) {
			}
		});

		// setTableFormat(table);// set color
		// setTableFormatDummy(dummyTable);// set color

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(700, 200));

		JFrame frame = new JFrame("Test 2Table Header");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(scrollPane);
		frame.pack();

		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.add(dummyHeader);
		panel.add(header);
		scrollPane.getColumnHeader().setView(panel);
		scrollPane.setBackground(Color.RED);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}