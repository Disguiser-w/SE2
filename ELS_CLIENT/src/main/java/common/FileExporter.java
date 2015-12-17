package common;

import java.io.File;

import javax.swing.JFileChooser;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class FileExporter {
	public static void exportExcel(String name, String[] head, String[][] content) throws Exception {
		// FileChooser
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG | JFileChooser.DIRECTORIES_ONLY);
		chooser.showDialog(null, null);
		File fi = chooser.getSelectedFile();
		String f = fi.getAbsolutePath() + "/" + name;
		File file = new File(f);
		if (!file.exists()) {
			file.createNewFile();
		}

		WritableWorkbook book = Workbook.createWorkbook(file);
		WritableSheet sheet = book.createSheet("第一页", 0);
		SheetSettings setting = sheet.getSettings();
		setting.setVerticalFreeze(1);

		// 第一行字体
		WritableFont headFont = new WritableFont(WritableFont.createFont("微软雅黑"), 10, WritableFont.BOLD);
		WritableCellFormat headF = new WritableCellFormat(headFont);
		headF.setAlignment(Alignment.CENTRE); // 平行居中
		headF.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中

		// 表格内容字体
		WritableFont contentFont = new WritableFont(WritableFont.createFont("微软雅黑"), 9, WritableFont.NO_BOLD);
		WritableCellFormat contentF = new WritableCellFormat(contentFont);
		contentF.setAlignment(Alignment.CENTRE); // 平行居中
		contentF.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中

		int column = head.length;
		// 不包括头
		int row = content.length;

		for (int i = 0; i < column; i++)
			sheet.addCell(new Label(i, 0, head[i], headF));
		for (int i = 0; i < row; i++)
			for (int j = 0; j < column; j++)
				sheet.addCell(new Label(j, i + 1, content[i][j], contentF));

		book.write();
		book.close();

	}

	// public static void main(String[] args) {
	// String name = "test.xls";
	// String[] head = { "第一列", "第二列", "第三列" };
	// String[][] content = { { "1x1", "1x2", "1x3" }, { "2x1", "2x2", "3x2" }
	// };
	// try {
	// FileExporter.exportExcel(name, head, content);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
}