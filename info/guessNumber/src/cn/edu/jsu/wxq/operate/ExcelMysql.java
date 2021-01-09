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
	private static StringBuffer fileName1 = new StringBuffer("E:\\���ֲ�һ��-��Ϸ��¼��.xls");
	//private static StringBuffer fileName2 = new StringBuffer("E:\\���ֲ�һ��-�û���.xls");
	// �����м����ظ����߳��������
	private static int count = 0;

	public static int toReadRecordExcel(String name) {
		// ����Excel�е�ֵ
		getAllRecordByExcel();
		return count;
	}

	/**
	 * ��ȡExcel�����е�����
	 * 

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
			int i,j;

			System.out.println("col = " + col + " rows = " + row);

			for (i = 0; i < row; i++) {
					// �������
					String name = sheet.getCell(0, i).getContents();
					String integral1 = sheet.getCell(1, i).getContents();
					String playtime = sheet.getCell(2, i).getContents();
					int integral = 0;
					int a=0,b=0;
					if(integral1.equals(""))return;
					else{
						//�ж�������Ƿ�������
						char[] c=integral1.toCharArray();          //���ַ���ת��Ϊ�ַ�����
						for (int k=0;k<c.length;k++) {
							if (c[k]>='0'&&c[k]<='9')  a++;
							else b++;
						}
						if(a==c.length){
						integral=Integer.parseInt(integral1);
						}
					}
					

					/*
					 * ��ӵ����ݿ� ������ݿ��еı��������� Ӧ�����жϲ����ֵ�Ƿ������ݿ��д��ڣ� ���ھ͸��£�û�о�ֱ�Ӳ���
					 * 
					 */
					//if (canInsert(name,playtime)) {		
						// �������ݿ�
						insertToDB(name,integral, playtime);
					/*}
					else {
						System.out.println("����ʧ��");
						count++;
					}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void insertToDB(String name,int integral,String playtime) {
		String sql = "insert into record (name, integral, playtime) values(?,?,?)";
		// ��ӵ����ݿ�
		SqlIInfo.addRecord(sql, name, integral, playtime);
	}

	/**
	 * �ж��Ƿ���user��,�ж�record�Ƿ����ظ�����
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
