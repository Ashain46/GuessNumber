package cn.edu.jsu.wxq.operate;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import cn.edu.jsu.wxq.dao.database.ConnectionDatabase;
import cn.edu.jsu.wxq.dao.database.SqlIInfo;
import cn.edu.jsu.wxq.user.Record;
import jxl.Sheet;

import jxl.Workbook;

public class ExcelMysql {
	private static StringBuffer fileName1 = new StringBuffer("E:\\数字猜一猜-游戏记录表.xls");
	//private static StringBuffer fileName2 = new StringBuffer("E:\\数字猜一猜-用户表.xls");
	// 返回有几条重复或者出错的数据
	private static int count = 0;

	public static int toReadRecordExcel(String name) {
		// 插入Excel中的值
		getAllRecordByExcel();
		return count;
	}

	/**
	 * 获取Excel中所有的数据
	 * 

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
			int i,j;

			System.out.println("col = " + col + " rows = " + row);

			for (i = 0; i < row; i++) {
					// 获得数据
					String name = sheet.getCell(0, i).getContents();
					String integral1 = sheet.getCell(1, i).getContents();
					String playtime = sheet.getCell(2, i).getContents();
					int integral = 0;
					int a=0,b=0;
					if(integral1.equals(""))return;
					else{
						//判断输入的是否是数字
						char[] c=integral1.toCharArray();          //将字符串转换为字符数组
						for (int k=0;k<c.length;k++) {
							if (c[k]>='0'&&c[k]<='9')  a++;
							else b++;
						}
						if(a==c.length){
						integral=Integer.parseInt(integral1);
						}
					}
					

					/*
					 * 添加到数据库 如果数据库中的表设置主键 应该先判断插入的值是否在数据库中存在， 存在就更新，没有就直接插入
					 * 
					 */
					//if (canInsert(name,playtime)) {		
						// 插入数据库
						insertToDB(name,integral, playtime);
					/*}
					else {
						System.out.println("插入失败");
						count++;
					}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void insertToDB(String name,int integral,String playtime) {
		String sql = "insert into record (name, integral, playtime) values(?,?,?)";
		// 添加到数据库
		SqlIInfo.addRecord(sql, name, integral, playtime);
	}

	/**
	 * 判断是否在user表,判断record是否有重复数据
	 */
	/*private static boolean canInsert(String name, String playtime) {
		try {
			ResultSet res1 = SqlIInfo.search("select * from user where name=?", name);
			ResultSet res2 = SqlIInfo.searchRecord("select * from record where name=? and playtime=?", name,playtime);
			if (res1.next() && !res2.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
*/
	public static void main(String args[]) {
		toReadRecordExcel(fileName1.toString());
	}
}
