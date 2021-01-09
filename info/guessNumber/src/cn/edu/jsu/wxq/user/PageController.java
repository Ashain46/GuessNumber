 package cn.edu.jsu.wxq.user;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import cn.edu.jsu.wxq.dao.database.SqlDao;

public class PageController {
	private static Vector<Vector> bigList ; // �󼯺ϣ�������ȡ����
	private Vector<Vector> smallList = new Vector<Vector>(); // С���ϣ����ظ�����������
	private static int curentPageIndex = 1; // ��ǰҳ��
	private static int  countPerpage = 15; // ÿҳ��ʾ����
	private int pageCount; // ��ҳ��
	private int recordCount; // �ܼ�¼����
	String str1="integral";
	String str2="flower";
	{// ��ʼ�������
		if(PageController.bigList==null) {
			PageController.bigList=SqlDao.getAll();// ���ò�ѯ���ݿ�ķ������������е���
		}
		//��ȡ��ҳ��
		if(bigList.size()%countPerpage==0) {
			pageCount=bigList.size()/countPerpage; 
		}else {
			pageCount=bigList.size()/countPerpage+1;
		} 
	}
	public void initSort1() {
		String sql="select * from user order by integral DESC, flower DESC limit 0,200";
			PageController.bigList=SqlDao.getSort(sql);// ���ò�ѯ���ݿ�ķ������������е���
			//��ȡ��ҳ��
			if(bigList.size()%countPerpage==0) {
				pageCount=bigList.size()/countPerpage;
			}else {
				pageCount=bigList.size()/countPerpage+1;
			}
			
	}
	public void initSort2() {
		String sql="select * from user order by flower DESC,integral DESC limit 0,200";
		PageController.bigList=SqlDao.getSort(sql);// ���ò�ѯ���ݿ�ķ������������е���
		//��ȡ��ҳ��
				if(bigList.size()%countPerpage==0) {
					pageCount=bigList.size()/countPerpage;
				}else {
					pageCount=bigList.size()/countPerpage+1;
				}
   }
	public PageController() {}//�޲ι��췽��

	public PageController(int curentPageIndex) {//���췽�����õ�ǰҳ
		this.curentPageIndex = curentPageIndex;
	}
	public void setCountPerpage(int countPerpage) {//����ÿҳ��ʾ�ļ�¼��
		this.countPerpage=countPerpage;
	}

	//�����˺Ż�ȡ�����Ϣ
	public Vector<Vector> getAllUser() {// ���ݵ�ǰҳ����ɸѡ��¼
		Vector<Vector> rows=new Vector<Vector>();//����Ҫ���ص����м�¼����
		rows=SqlDao.getAll();
		return rows;
	}
	
	public Vector<Vector> getAllRecord() {
		Vector<Vector> rows=new Vector<Vector>();//����Ҫ���ص����м�¼����
		String sql="select * from record";
		rows=SqlDao.getAll1(sql,null);
		return rows;
	}
	
	public Vector<Vector> getPaegData() {// ���ݵ�ǰҳ����ɸѡ��¼
		recordCount = bigList.size();//�����¼��Ϊ���ݿ��б����������
		for (int i = (curentPageIndex - 1) * countPerpage; i < curentPageIndex * countPerpage && i < recordCount; i++) {//ȡ�õ�ǰҳ���ļ�¼��curentPageIndex��ǰҳ����countPerpageÿҳ��ʾ�ļ�¼��
			smallList.add(bigList.get(i));//����¼���뵽С������
		}
		return smallList;//����С���ϣ���ǰҳ�����ݣ�
	}
	public Vector<Vector> nextPage(){//��һҳ
		if(curentPageIndex<pageCount) {
			curentPageIndex++;
		}else {
			curentPageIndex=1;
		}
		return getPaegData();//������һҳ������
	}
	public Vector<Vector> prePage(){//��һҳ
		if(curentPageIndex>1) {
			curentPageIndex--;
		}else {
			curentPageIndex=pageCount;
		}
		return getPaegData();//������һҳ������
	}
	public Vector<Vector> appointPage(int page){
		if(pageCount>=page)
		curentPageIndex=page;
		else
			curentPageIndex=1;;
		return getPaegData();
	}
}
