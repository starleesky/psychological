package cn.com.tsjx.common.util.lang;

import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtils {

	/**
	 * 边框
	 * @param workbook
	 * @return
	 */
	public static CellStyle createStyleCell(Workbook workbook) {
		CellStyle cellStyle = workbook.createCellStyle();
		// 设置一个单元格边框样式
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		// 设置一个单元格边框颜色
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		return cellStyle;
	}

	/**
	 * 设置文字在单元格里面的位置 CellStyle.ALIGN_CENTER CellStyle.VERTICAL_CENTER
	 * 
	 * @param cellStyle
	 * @param halign
	 * @param valign
	 * @return
	 */
	public static CellStyle setCellStyleAlignment(CellStyle cellStyle, short halign, short valign) {
		cellStyle.setAlignment(halign);// 设置左右
		cellStyle.setVerticalAlignment(valign);// 设置上下
		return cellStyle;
	}

	/**
	 * 格式化单元格 如,0.00,m/d/yy去HSSFDataFormat或XSSFDataFormat里面找
	 * 
	 * @param cellStyle
	 * @param fmt
	 * @return
	 */
	public static CellStyle setCellFormat(CreationHelper helper, CellStyle cellStyle, String fmt) {
		// 还可以用其它方法创建format
		cellStyle.setDataFormat(helper.createDataFormat().getFormat(fmt));
		return cellStyle;
	}

	/**
	 * 前景和背景填充的着色
	 * 
	 * @param cellStyle
	 * @param bg IndexedColors.ORANGE.getIndex();
	 * @param fg IndexedColors.ORANGE.getIndex();
	 * @param fp CellStyle.SOLID_FOREGROUND
	 * @return
	 */
	public static CellStyle setFillBackgroundColors(CellStyle cellStyle, short bg, short fg, short fp) {
		cellStyle.setFillBackgroundColor(bg);
		cellStyle.setFillForegroundColor(fg);
		cellStyle.setFillPattern(fp);
		return cellStyle;
	}

	/**
	 * 设置表头字体
	 * 
	 * @param wb
	 * @return
	 */
	public static Font createTitleFonts(Workbook workbook) {
		Font font = workbook.createFont();// 创建Font对象
		font.setFontName("宋体");// 设置字体
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.BLACK.index);// 着色
		font.setItalic(false);// 斜体
		font.setFontHeight((short) 200);// 字体大小
		return font;
	}

	/**
	 * 设置数据字体
	 * @param workbook
	 * @return
	 */
	public static Font createDataFonts(Workbook workbook) {
		Font font = workbook.createFont();// 创建Font对象
		font.setFontName("宋体");// 设置字体
		font.setColor(HSSFColor.BLACK.index);// 着色
		font.setItalic(false);// 斜体
		font.setFontHeight((short) 200);// 字体大小
		return font;
	}

	/**
	 * 获取单元格的值
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		if (cell == null)
			return "";
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue().trim();
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue()).trim();
		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			return cell.getCellFormula();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			DecimalFormat df = new DecimalFormat("#");
			return String.valueOf(df.format(cell.getNumericCellValue())).trim();
		}
		return "";
	}
}
