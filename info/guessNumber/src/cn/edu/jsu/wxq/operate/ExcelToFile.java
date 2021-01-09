package cn.edu.jsu.wxq.operate;

import java.io.File;

import cn.edu.jsu.wxq.dao.database.SqlIInfo;
import cn.edu.jsu.wxq.user.Record;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelToFile {

	/**
	 * 获取Excel中所有Record的数据并写入文本文件中
	 * 
	 */
	public static void getAllRecordByExcel() {
       StringBuffer fileName1 = new StringBuffer("E:\\数字猜一猜-游戏记录表.xls");
		Record data = new Record();
		try {
			// 获得工作薄
			Workbook workBook = Workbook.getWorkbook(new File(fileName1.toString()));
			// 获得工作表
			Sheet sheet = workBook.getSheet(0);

			// 获得行和列的长度
			int col = sheet.getColumns();
			int row = sheet.getRows();
			int i,j;

			System.out.println("col = " + col + " rows = " + row);

			for (i = 0; i < row; i++) {
					// 获得数据
					String name = sheet.getCell(0, i).getContents();
					String integral = sheet.getCell(1, i).getContents();
					String playtime = sheet.getCell(2, i).getContents();
					String record=name+"\t"+integral+"\t"+playtime+"\n";
					FileOperate.addRecord(record);
			}
					System.out.println("导入成功Record");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取Excel中所有User的数据并写入文本文件中
	 * 
	 */
	public static void getAllUserByExcel() {
		StringBuffer fileName2 = new StringBuffer("E:\\数字猜一猜-用户表.xls");
		Record data = new Record();
		try {
			// 获得工作薄
			Workbook workBook = Workbook.getWorkbook(new File(fileName2.toString()));
			// 获得工作表
			Sheet sheet = workBook.getSheet(0);

			// 获得行和列的长度
			int col = sheet.getColumns();
			int row = sheet.getRows();
			int i,j;

			for (i = 0; i < row; i++) {
					// 获得数据
					String name = sheet.getCell(0, i).getContents();
					String account = sheet.getCell(1, i).getContents();
					String password = sheet.getCell(2, i).getContents();
					String integral = sheet.getCell(3, i).getContents();
					String flower = sheet.getCell(4, i).getContents();
					String User=name+"\t"+account+"\t"+password+"\t"+integral+"\t"+flower+"\n";
					FileOperate.addUser(User);
			}
				System.out.println("导入成功User");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		getAllRecordByExcel();
		//getAllUserByExcel();
	}
}
