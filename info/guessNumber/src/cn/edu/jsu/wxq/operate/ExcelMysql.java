package cn.edu.jsu.wxq.operate;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.jsu.wxq.dao.database.ConnectionDatabase;
import cn.edu.jsu.wxq.dao.database.SqlDao;
import cn.edu.jsu.wxq.user.Record;
import jxl.Sheet;

import jxl.Workbook;

public class ExcelMysql {
	private static StringBuffer fileName1 = new StringBuffer("E:\\数字猜一猜-游戏记录表.xls");
	private static StringBuffer fileName2 = new StringBuffer("E:\\数字猜一猜-用户表.xls");
	// 返回有几条重复或者出错的数据
	private static int count = 0;

	public static int toReadRecordExcel(String name) {
		// 插入Excel中的值
		getAllRecordByExcel();
		return count;
	}

	/*
	 * 获取Excel中所有的数据
	 * 
	 * @param fileName Excel文件完整路径
	 * 
	 * @return
	 */
	private static void getAllRecordByExcel() {

		Record data = new Record();
		try {
			// 获得工作薄
			Workbook workBook = Workbook.getWorkbook(new File(fileName1.toString()));
			// 获得工作表
			Sheet sheet = workBook.getSheet(0);

			// 获得行和列的长度
			int col = sheet.getColumns();
			int row = sheet.getRows();

			System.out.println("col = " + col + " rows = " + row);

			for (int i = 1; i < row; i++) {
				for (int j = 0; j < col; j++) {
					// 获得数据
					String name = sheet.getCell(j++, i).getContents();
					String integral1 = sheet.getCell(j++, i).getContents();
					String playtime = sheet.getCell(j, i).getContents();
					int integral=Integer.parseInt(integral1);
					// 输出数据
					System.out.println(" name = " + name + " integral = " + integral + " playtime = "
							+ playtime);

					/*
					 * 添加到数据库 如果数据库中的表设置主键 应该先判断插入的值是否在数据库中存在， 存在就更新，没有就直接插入
					 * 
					 */
					if (canInsert(name,playtime)) {		
						// 插入数据库
						insertToDB(name,integral, playtime);
					}
					else {
						System.out.println("插入失败");
						count++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void insertToDB(String name,int integral,String playtime) {
		String sql = "insert into record (name, integral, playtime) values(?,?,?)";
		// 添加到数据库
		SqlDao.addRecord(sql, name, integral, playtime);
	}

	/*
	 * 判断是否在user表,判断record是否有重复数据
	 */
	private static boolean canInsert(String name, String playtime) {
		try {
			ResultSet res1 = SqlDao.search("select * from user where name=?", name);
			ResultSet res2 = SqlDao.search("select * from record where name=? and playtime=?", playtime);
			/*
			 *  res1要找到name
			 *  res2不能找到相同数据
			 */
			if (res1.next() && !res2.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String args[]) {
		toReadRecordExcel(fileName1.toString());
	}
}
