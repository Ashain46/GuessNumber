package cn.edu.jsu.wxq.operate;

import java.io.File;
import java.util.List;
import java.util.Vector;

import cn.edu.jsu.wxq.dao.database.SqlDao;
import cn.edu.jsu.wxq.user.Record;
import cn.edu.jsu.wxq.user.User;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
/**
 * ���ݿ⵼��Excel
 * @author 86135
 *
 */
public class MysqlExcel {
	// ������д���Excel������
	private static String fileName = "E:\\���ֲ�һ��-��Ϸ��¼��.xls";
	private static String fileName2 = "E:\\���ֲ�һ��-�û���.xls";
	
	public static void toWriteExcel1() {
		try {
    		WritableWorkbook excelBook = null;
    		// ���ļ�
    		File file = new File(fileName);
    		// �鿴�ļ��Ƿ����
    		if(!file.exists()) {
    			// �����ڴ���
    			file.createNewFile();
    		}
    		
    		//��fileNameΪ�ļ���������һ��Workbook
    		excelBook = Workbook.createWorkbook(file);
    		
    		//����������
    		WritableSheet excelSheet = excelBook.createSheet("Test 1", 0);
    		
    		//������������������
    		String sqlTableName = "record";
    		
    		List<Record> list = SqlDao.getAllRecord();
    		
    		//Ҫ���뵽��Excel�����кţ�Ĭ�ϴ�0��ʼ
    		Label labelName = new Label(0, 0 , "name");
    		Label labelIntegral = new Label(1, 0 , "integral");
    		Label labelPlaytime = new Label(2, 0 , "playtime");
    		
    		//��ӵ�һ�е���Ԫ��
    		excelSheet.addCell(labelName);
    		excelSheet.addCell(labelIntegral);
    		excelSheet.addCell(labelPlaytime);
    		
    		// ����
    		for(int i = 0; i < list.size(); i++) {
    			//Ҫ���뵽��Excel�����кţ�Ĭ�ϴ�0��ʼ
        		Label label_Name = new Label(0, i+1 , list.get(i).getName());
        		Label label_Integral= new Label(1, i+1 , list.get(i).getIntegral() + "");
        		Label label_Playtime = new Label(2, i+1 , list.get(i).getPlaytime());
        		
        		//��ӵ�һ�е���Ԫ��
        		excelSheet.addCell(label_Name);
        		excelSheet.addCell(label_Integral);
        		excelSheet.addCell(label_Playtime); 
    		}
    		
    		//д���ĵ�
    		excelBook.write();
    		//�ر�Excel����������
    		excelBook.close();
    		System.out.println("OK");
    	}
    	 catch (Exception e) {
             e.printStackTrace();
         }
	}
	
	public static void toWriteExcel() {
		try {
    		WritableWorkbook excelBook = null;
    		// ���ļ�
    		File file = new File(fileName2);
    		// �鿴�ļ��Ƿ����
    		if(!file.exists()) {
    			// �����ڴ���
    			file.createNewFile();
    		}
    		
    		//��fileNameΪ�ļ���������һ��Workbook
    		excelBook = Workbook.createWorkbook(file);
    		
    		//����������
    		WritableSheet excelSheet = excelBook.createSheet("Test 1", 0);
    		
    		//������������������
    		String sqlTableName = "user";
    		
    		List<User> list = SqlDao.getAllUser();
    		
    		//Ҫ���뵽��Excel�����кţ�Ĭ�ϴ�0��ʼ
    		Label labelName = new Label(0, 0 , "name");
    		Label labelAccount = new Label(1, 0 , "account");
    		Label labelPassword = new Label(2, 0 , "password");
    		Label labelIntegral = new Label(3, 0 , "integral");
    		Label labelFlower = new Label(4, 0 , "flower");
    		
    		//��ӵ�һ�е���Ԫ��
    		excelSheet.addCell(labelName);
    		excelSheet.addCell(labelAccount);
    		excelSheet.addCell(labelPassword);
    		excelSheet.addCell(labelIntegral);
    		excelSheet.addCell(labelFlower);
    		
    		// ����
    		for(int i = 0; i < list.size(); i++) {
    			//Ҫ���뵽��Excel�����кţ�Ĭ�ϴ�0��ʼ
        		Label label_Name = new Label(0, i+1 , list.get(i).getName());
        		Label label_Account = new Label(1, i+1 , list.get(i).getAccount());
        		Label label_Password = new Label(2, i+1 , list.get(i).getPassword());
        		Label label_Integral= new Label(3, i+1 , list.get(i).getIntegral() + "");
        		Label label_Flower = new Label(4, i+1 , list.get(i).getFlower()+"");
        		
        		//��ӵ�һ�е���Ԫ��
        		excelSheet.addCell(label_Name);
        		excelSheet.addCell(label_Account);
        		excelSheet.addCell(label_Password);
        		excelSheet.addCell(label_Integral);
        		excelSheet.addCell(label_Flower);
    		}
    		
    		//д���ĵ�
    		excelBook.write();
    		//�ر�Excel����������
    		excelBook.close();
    		System.out.println("OK");
    	}
    	 catch (Exception e) {
             e.printStackTrace();
         }
	}
    public static void main(String args[]) {
    	toWriteExcel();
    	toWriteExcel1();
    }
}
