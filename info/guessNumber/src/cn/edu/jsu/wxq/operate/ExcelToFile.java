package cn.edu.jsu.wxq.operate;

import java.io.File;

import cn.edu.jsu.wxq.dao.database.SqlIInfo;
import cn.edu.jsu.wxq.user.Record;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelToFile {

	/**
	 * ��ȡExcel������Record�����ݲ�д���ı��ļ���
	 * 
	 */
	public static void getAllRecordByExcel() {
       StringBuffer fileName1 = new StringBuffer("E:\\���ֲ�һ��-��Ϸ��¼��.xls");
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
					String integral = sheet.getCell(1, i).getContents();
					String playtime = sheet.getCell(2, i).getContents();
					String record=name+"\t"+integral+"\t"+playtime+"\n";
					FileOperate.addRecord(record);
			}
					System.out.println("����ɹ�Record");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡExcel������User�����ݲ�д���ı��ļ���
	 * 
	 */
	public static void getAllUserByExcel() {
		StringBuffer fileName2 = new StringBuffer("E:\\���ֲ�һ��-�û���.xls");
		Record data = new Record();
		try {
			// ��ù�����
			Workbook workBook = Workbook.getWorkbook(new File(fileName2.toString()));
			// ��ù�����
			Sheet sheet = workBook.getSheet(0);

			// ����к��еĳ���
			int col = sheet.getColumns();
			int row = sheet.getRows();
			int i,j;

			for (i = 0; i < row; i++) {
					// �������
					String name = sheet.getCell(0, i).getContents();
					String account = sheet.getCell(1, i).getContents();
					String password = sheet.getCell(2, i).getContents();
					String integral = sheet.getCell(3, i).getContents();
					String flower = sheet.getCell(4, i).getContents();
					String User=name+"\t"+account+"\t"+password+"\t"+integral+"\t"+flower+"\n";
					FileOperate.addUser(User);
			}
				System.out.println("����ɹ�User");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		getAllRecordByExcel();
		//getAllUserByExcel();
	}
}
