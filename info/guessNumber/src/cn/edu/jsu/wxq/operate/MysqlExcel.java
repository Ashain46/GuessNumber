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
 * 数据库导出Excel
 * @author 86135
 *
 */
public class MysqlExcel {
	// 创建可写入的Excel工作薄
	private static String fileName = "E:\\数字猜一猜-游戏记录表.xls";
	private static String fileName2 = "E:\\数字猜一猜-用户表.xls";
	
	public static void toWriteExcel1() {
		try {
    		WritableWorkbook excelBook = null;
    		// 打开文件
    		File file = new File(fileName);
    		// 查看文件是否存在
    		if(!file.exists()) {
    			// 不存在创建
    			file.createNewFile();
    		}
    		
    		//以fileName为文件名来创建一个Workbook
    		excelBook = Workbook.createWorkbook(file);
    		
    		//创建工作表
    		WritableSheet excelSheet = excelBook.createSheet("Test 1", 0);
    		
    		//导出数据中所有数据
    		String sqlTableName = "record";
    		
    		List<Record> list = SqlDao.getAllRecord();
    		
    		//要插入到的Excel表格的行号，默认从0开始
    		Label labelName = new Label(0, 0 , "name");
    		Label labelIntegral = new Label(1, 0 , "integral");
    		Label labelPlaytime = new Label(2, 0 , "playtime");
    		
    		//添加第一列到单元格
    		excelSheet.addCell(labelName);
    		excelSheet.addCell(labelIntegral);
    		excelSheet.addCell(labelPlaytime);
    		
    		// 导入
    		for(int i = 0; i < list.size(); i++) {
    			//要插入到的Excel表格的行号，默认从0开始
        		Label label_Name = new Label(0, i+1 , list.get(i).getName());
        		Label label_Integral= new Label(1, i+1 , list.get(i).getIntegral() + "");
        		Label label_Playtime = new Label(2, i+1 , list.get(i).getPlaytime());
        		
        		//添加第一列到单元格
        		excelSheet.addCell(label_Name);
        		excelSheet.addCell(label_Integral);
        		excelSheet.addCell(label_Playtime); 
    		}
    		
    		//写入文档
    		excelBook.write();
    		//关闭Excel工作薄对象
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
    		// 打开文件
    		File file = new File(fileName2);
    		// 查看文件是否存在
    		if(!file.exists()) {
    			// 不存在创建
    			file.createNewFile();
    		}
    		
    		//以fileName为文件名来创建一个Workbook
    		excelBook = Workbook.createWorkbook(file);
    		
    		//创建工作表
    		WritableSheet excelSheet = excelBook.createSheet("Test 1", 0);
    		
    		//导出数据中所有数据
    		String sqlTableName = "user";
    		
    		List<User> list = SqlDao.getAllUser();
    		
    		//要插入到的Excel表格的行号，默认从0开始
    		Label labelName = new Label(0, 0 , "name");
    		Label labelAccount = new Label(1, 0 , "account");
    		Label labelPassword = new Label(2, 0 , "password");
    		Label labelIntegral = new Label(3, 0 , "integral");
    		Label labelFlower = new Label(4, 0 , "flower");
    		
    		//添加第一列到单元格
    		excelSheet.addCell(labelName);
    		excelSheet.addCell(labelAccount);
    		excelSheet.addCell(labelPassword);
    		excelSheet.addCell(labelIntegral);
    		excelSheet.addCell(labelFlower);
    		
    		// 导入
    		for(int i = 0; i < list.size(); i++) {
    			//要插入到的Excel表格的行号，默认从0开始
        		Label label_Name = new Label(0, i+1 , list.get(i).getName());
        		Label label_Account = new Label(1, i+1 , list.get(i).getAccount());
        		Label label_Password = new Label(2, i+1 , list.get(i).getPassword());
        		Label label_Integral= new Label(3, i+1 , list.get(i).getIntegral() + "");
        		Label label_Flower = new Label(4, i+1 , list.get(i).getFlower()+"");
        		
        		//添加第一列到单元格
        		excelSheet.addCell(label_Name);
        		excelSheet.addCell(label_Account);
        		excelSheet.addCell(label_Password);
        		excelSheet.addCell(label_Integral);
        		excelSheet.addCell(label_Flower);
    		}
    		
    		//写入文档
    		excelBook.write();
    		//关闭Excel工作薄对象
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
