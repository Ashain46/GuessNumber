 package cn.edu.jsu.wxq.user;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import cn.edu.jsu.wxq.dao.database.SqlDao;

public class PageController {
	private static Vector<Vector> bigList ; // 大集合，从外界获取数据
	private Vector<Vector> smallList = new Vector<Vector>(); // 小集合，返回给调用它的类
	private static int curentPageIndex = 1; // 当前页码
	private static int  countPerpage = 15; // 每页显示条数
	private int pageCount; // 总页数
	private int recordCount; // 总记录条数
	String str1="integral";
	String str2="flower";
	{// 初始化代码块
		if(PageController.bigList==null) {
			PageController.bigList=SqlDao.getAll();// 调用查询数据库的方法，返回所有的行
		}
		//获取总页数
		if(bigList.size()%countPerpage==0) {
			pageCount=bigList.size()/countPerpage; 
		}else {
			pageCount=bigList.size()/countPerpage+1;
		} 
	}
	public void initSort1() {
		String sql="select * from user order by integral DESC, flower DESC limit 0,200";
			PageController.bigList=SqlDao.getSort(sql);// 调用查询数据库的方法，返回所有的行
			//获取总页数
			if(bigList.size()%countPerpage==0) {
				pageCount=bigList.size()/countPerpage;
			}else {
				pageCount=bigList.size()/countPerpage+1;
			}
			
	}
	public void initSort2() {
		String sql="select * from user order by flower DESC,integral DESC limit 0,200";
		PageController.bigList=SqlDao.getSort(sql);// 调用查询数据库的方法，返回所有的行
		//获取总页数
				if(bigList.size()%countPerpage==0) {
					pageCount=bigList.size()/countPerpage;
				}else {
					pageCount=bigList.size()/countPerpage+1;
				}
   }
	public PageController() {}//无参构造方法

	public PageController(int curentPageIndex) {//构造方法设置当前页
		this.curentPageIndex = curentPageIndex;
	}
	public void setCountPerpage(int countPerpage) {//设置每页显示的记录数
		this.countPerpage=countPerpage;
	}

	//根据账号获取相关信息
	public Vector<Vector> getAllUser() {// 根据当前页数，筛选记录
		Vector<Vector> rows=new Vector<Vector>();//定义要返回的所有记录集合
		rows=SqlDao.getAll();
		return rows;
	}
	
	public Vector<Vector> getAllRecord() {
		Vector<Vector> rows=new Vector<Vector>();//定义要返回的所有记录集合
		String sql="select * from record";
		rows=SqlDao.getAll1(sql,null);
		return rows;
	}
	
	public Vector<Vector> getPaegData() {// 根据当前页数，筛选记录
		recordCount = bigList.size();//定义记录数为数据库中表的所有数据
		for (int i = (curentPageIndex - 1) * countPerpage; i < curentPageIndex * countPerpage && i < recordCount; i++) {//取得当前页数的记录，curentPageIndex当前页数，countPerpage每页显示的记录数
			smallList.add(bigList.get(i));//将记录加入到小集合中
		}
		return smallList;//返回小集合（当前页的数据）
	}
	public Vector<Vector> nextPage(){//下一页
		if(curentPageIndex<pageCount) {
			curentPageIndex++;
		}else {
			curentPageIndex=1;
		}
		return getPaegData();//返回下一页的数据
	}
	public Vector<Vector> prePage(){//上一页
		if(curentPageIndex>1) {
			curentPageIndex--;
		}else {
			curentPageIndex=pageCount;
		}
		return getPaegData();//返回上一页的数据
	}
	public Vector<Vector> appointPage(int page){
		if(pageCount>=page)
		curentPageIndex=page;
		else
			curentPageIndex=1;;
		return getPaegData();
	}
}
