package common;

import java.io.File;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 创建Excel简单实例
 * 
 * @author luqin * @version
 * @time 2012-6-20 上午11:12:02
 * 
 */
public class CreateExcel {
	public static void main(String[] args) {
		String excelName = "table.xls";
		try {
			File excelFile = new File(excelName);
			// 如果文件存在就删除它
			if (excelFile.exists())
				excelFile.delete();
			// 打开文件
			WritableWorkbook book = Workbook.createWorkbook(excelFile);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet(" 第一页 ", 0);
			// 合并单元格
			sheet.mergeCells(5, 5, 6, 6);
			// 文字样式
			jxl.write.WritableFont wfc = new jxl.write.WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,
					UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.RED);

			jxl.write.WritableCellFormat wcfFC = new jxl.write.WritableCellFormat(wfc);

			// 设置单元格样式
			wcfFC.setBackground(jxl.format.Colour.GRAY_25);// 单元格颜色
			wcfFC.setAlignment(jxl.format.Alignment.CENTRE);// 单元格居中

			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为
			Label label = new Label(0, 0, "Head1", wcfFC);

			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);

			/**//*
				 * 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
				 */
			jxl.write.Number number = new jxl.write.Number(1, 0, 555.12541);
			sheet.addCell(number);

			// 写入数据并关闭文件
			book.write();
			book.close();
			System.out.println("Excel创建成功");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}