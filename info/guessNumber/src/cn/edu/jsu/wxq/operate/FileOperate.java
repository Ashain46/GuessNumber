package cn.edu.jsu.wxq.operate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import cn.edu.jsu.wxq.user.User;
/**
 * 注册时,如果注册成功,将注册信息存入文本文件中
 */
public class FileOperate {
	final static File file1 = new File("e:/usertest.txt");
	final static File file2 = new File("e:/recordtest.txt");
	/**
	 * 增加用户信息到文本文件
	 * @param user the String用户信息
	 * @return boolean boolean是用来返回
	 */
	  public static boolean addUser(String user) {//定义增加用户的方法，user为内容
		    try(FileWriter fw=new FileWriter(file1,true)){
		      fw.write(user);
		    }catch(Exception e1) {e1.printStackTrace();}
		    return true;
		}
	  public static boolean addRecord(String record) {//定义增加用户的方法，user为内容
		    try(FileWriter fw=new FileWriter(file2,true)){
		      fw.write(record);
		    }catch(Exception e1) {e1.printStackTrace();}
		    return true;
		}
	public static void initTable(DefaultTableModel model) {
		// TODO Auto-generated method stub
		try (FileReader fr= new FileReader(file1);
		     BufferedReader br=new BufferedReader(fr);){//实例化缓冲流
			 String row=null;
	      while((row=br.readLine())!=null) {//按行读取数据
	    	  model.addRow(row.split("\t"));//将读取的行按分隔符拆分成字符串数组存入数据模型，关键代码
	    	  
	      }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//实例化字符文件流
	}
	/**
	 * 初始化表格数据
	 * @return 返回表格数据
	 */
	public static Vector<Vector> initTable1() {//初始化表格数据
		  Vector<Vector> rows=new Vector<Vector>();//创建行数据容器
		  try(FileReader fr=new FileReader(file1);
		      BufferedReader br=new BufferedReader(fr);){//使用缓冲流读取文件
		    String line=null;
		    while((line=br.readLine())!=null) {//按行读取
		      String[] col=line.split("\t");//读取的行按分隔符拆分成字符串数组
		      Vector row=new Vector();//定义行数据
		      for(int i=0;i<col.length;i++) {
		        if(i==3||i==4) {row.add(Integer.valueOf(col[3]));
		        row.add(Integer.valueOf(col[4]));//将积分转换成整数加入
		        }else {row.add(col[i]);}//增加行中其它数据
		      }
		      rows.add(row);//将行数据增加到行容器中
		    }
		  }catch(Exception e1) {e1.printStackTrace();}
		  return rows;
	}//返回所有的行
	/*public static DefaultTableModel getModel() {
		Vector<String> titles1 = new Vector<String>();
		Collections.addAll(titles1,  "昵称", "账号", "密码","积分","鲜花");//定义表格列头
		DefaultTableModel model1 = new DefaultTableModel(FileOperate.initTable1(), titles1) {//使用Vector装载表格数据模型，覆写getColumnClass方法，实现按数值排序
		  public Class getColumnClass(int column) {
		    Class returnValue;
		    if ((column >= 0) && (column < getColumnCount())) {
		      returnValue = getValueAt(0, column).getClass();
		    } else { returnValue = Object.class; }
		       return returnValue;
		   }
	    };// 定义表格数据模型的表格标题和行数(为0行)
		return model1;
	}
	public static void sortTable(JTable table)
	{
		TableRowSorter sorter = new TableRowSorter<DefaultTableModel>(FileOperate.getModel());//设置表格模型排序器
		table.setRowSorter(sorter);//设置表格排序器
		table.setModel(FileOperate.getModel());//设置表格数据模型
	}*/
	public static void newFile(JTable table)
	   {
		   
		   DefaultTableModel model=(DefaultTableModel)table.getModel();
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(FileOperate.getFile()))) {
				  for (int i = 0; i < model.getRowCount(); i++) {// 遍历表格数据，加入集合中
			  StringBuffer row=new StringBuffer(); 	    
			  for(int j=0;j<model.getColumnCount();j++)
			  { row.append(model.getValueAt(i, j).toString() + "\t");}
			    String str=row.toString();
				    bw.write(str);// 向文本文件中增加数据
				    bw.newLine();// 写入回车换行
				  }
				} catch (Exception e1) {
				  e1.printStackTrace();
				}
	   }
	public static File getFile()
	{
        File file=new File("e:/usertest.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return file;
	}
}
