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
	private static StringBuffer fileName1 = new StringBuffer("E:\\���ֲ�һ��-��Ϸ��¼��.xls");
	private static StringBuffer fileName2 = new StringBuffer("E:\\���ֲ�һ��-�û���.xls");
	// �����м����ظ����߳��������
	private static int count = 0;

	public static int toReadRecordExcel(String name) {
		// ����Excel�е�ֵ
		getAllRecordByExcel();
		return count;
	}

	/*
	 * ��ȡExcel�����е�����
	 * 
	 * @param fileName Excel�ļ�����·��
	 * 
	 * @return
	 */
	private static void getAllRecordByExcel() {

		Record data = new Record();
		try {
			// ��ù�����
			Workbook workBook = Workbook.getWorkbook(new File(fileName1.toString()));
			// ��ù�����
			Sheet sheet = workBook.getSheet(0);

			// ����к��еĳ���
			int col = sheet.getColumns();
			int row = sheet.getRows();

			System.out.println("col = " + col + " rows = " + row);

			for (int i = 1; i < row; i++) {
				for (int j = 0; j < col; j++) {
					// �������
					String name = sheet.getCell(j++, i).getContents();
					String integral1 = sheet.getCell(j++, i).getContents();
					String playtime = sheet.getCell(j, i).getContents();
					int integral=Integer.parseInt(integral1);
					// �������
					System.out.println(" name = " + name + " integral = " + integral + " playtime = "
							+ playtime);

					/*
					 * ��ӵ����ݿ� ������ݿ��еı��������� Ӧ�����жϲ����ֵ�Ƿ������ݿ��д��ڣ� ���ھ͸��£�û�о�ֱ�Ӳ���
					 * 
					 */
					if (canInsert(name,playtime)) {		
						// �������ݿ�
						insertToDB(name,integral, playtime);
					}
					else {
						System.out.println("����ʧ��");
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
		// ��ӵ����ݿ�
		SqlDao.addRecord(sql, name, integral, playtime);
	}

	/*
	 * �ж��Ƿ���user��,�ж�record�Ƿ����ظ�����
	 */
	private static boolean canInsert(String name, String playtime) {
		try {
			ResultSet res1 = SqlDao.search("select * from user where name=?", name);
			ResultSet res2 = SqlDao.search("select * from record where name=? and playtime=?", playtime);
			/*
			 *  res1Ҫ�ҵ�name
			 *  res2�����ҵ���ͬ����
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
