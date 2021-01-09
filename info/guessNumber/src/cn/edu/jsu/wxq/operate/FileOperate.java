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
 * ע��ʱ,���ע��ɹ�,��ע����Ϣ�����ı��ļ���
 */
public class FileOperate {
	final static File file1 = new File("e:/usertest.txt");
	final static File file2 = new File("e:/recordtest.txt");
	/**
	 * �����û���Ϣ���ı��ļ�
	 * @param user the String�û���Ϣ
	 * @return boolean boolean����������
	 */
	  public static boolean addUser(String user) {//���������û��ķ�����userΪ����
		    try(FileWriter fw=new FileWriter(file1,true)){
		      fw.write(user);
		    }catch(Exception e1) {e1.printStackTrace();}
		    return true;
		}
	  public static boolean addRecord(String record) {//���������û��ķ�����userΪ����
		    try(FileWriter fw=new FileWriter(file2,true)){
		      fw.write(record);
		    }catch(Exception e1) {e1.printStackTrace();}
		    return true;
		}
	public static void initTable(DefaultTableModel model) {
		// TODO Auto-generated method stub
		try (FileReader fr= new FileReader(file1);
		     BufferedReader br=new BufferedReader(fr);){//ʵ����������
			 String row=null;
	      while((row=br.readLine())!=null) {//���ж�ȡ����
	    	  model.addRow(row.split("\t"));//����ȡ���а��ָ�����ֳ��ַ��������������ģ�ͣ��ؼ�����
	    	  
	      }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//ʵ�����ַ��ļ���
	}
	/**
	 * ��ʼ���������
	 * @return ���ر������
	 */
	public static Vector<Vector> initTable1() {//��ʼ���������
		  Vector<Vector> rows=new Vector<Vector>();//��������������
		  try(FileReader fr=new FileReader(file1);
		      BufferedReader br=new BufferedReader(fr);){//ʹ�û�������ȡ�ļ�
		    String line=null;
		    while((line=br.readLine())!=null) {//���ж�ȡ
		      String[] col=line.split("\t");//��ȡ���а��ָ�����ֳ��ַ�������
		      Vector row=new Vector();//����������
		      for(int i=0;i<col.length;i++) {
		        if(i==3||i==4) {row.add(Integer.valueOf(col[3]));
		        row.add(Integer.valueOf(col[4]));//������ת������������
		        }else {row.add(col[i]);}//����������������
		      }
		      rows.add(row);//�����������ӵ���������
		    }
		  }catch(Exception e1) {e1.printStackTrace();}
		  return rows;
	}//�������е���
	/*public static DefaultTableModel getModel() {
		Vector<String> titles1 = new Vector<String>();
		Collections.addAll(titles1,  "�ǳ�", "�˺�", "����","����","�ʻ�");//��������ͷ
		DefaultTableModel model1 = new DefaultTableModel(FileOperate.initTable1(), titles1) {//ʹ��Vectorװ�ر������ģ�ͣ���дgetColumnClass������ʵ�ְ���ֵ����
		  public Class getColumnClass(int column) {
		    Class returnValue;
		    if ((column >= 0) && (column < getColumnCount())) {
		      returnValue = getValueAt(0, column).getClass();
		    } else { returnValue = Object.class; }
		       return returnValue;
		   }
	    };// ����������ģ�͵ı����������(Ϊ0��)
		return model1;
	}
	public static void sortTable(JTable table)
	{
		TableRowSorter sorter = new TableRowSorter<DefaultTableModel>(FileOperate.getModel());//���ñ��ģ��������
		table.setRowSorter(sorter);//���ñ��������
		table.setModel(FileOperate.getModel());//���ñ������ģ��
	}*/
	public static void newFile(JTable table)
	   {
		   
		   DefaultTableModel model=(DefaultTableModel)table.getModel();
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(FileOperate.getFile()))) {
				  for (int i = 0; i < model.getRowCount(); i++) {// ����������ݣ����뼯����
			  StringBuffer row=new StringBuffer(); 	    
			  for(int j=0;j<model.getColumnCount();j++)
			  { row.append(model.getValueAt(i, j).toString() + "\t");}
			    String str=row.toString();
				    bw.write(str);// ���ı��ļ�����������
				    bw.newLine();// д��س�����
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
