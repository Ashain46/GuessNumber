 package cn.edu.jsu.wxq.user;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.junit.Test;

import cn.edu.jsu.wxq.dao.database.SqlIInfo;
/**
 * 设置页码
 * @author 文雪茜
 *
 */
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
			PageController.bigList=SqlIInfo.getAll();// 调用查询数据库的方法，返回所有的行
		}
		//获取总页数
		if(bigList.size()%countPerpage==0) {
			pageCount=bigList.size()/countPerpage; 
		}else {
			pageCount=bigList.size()/countPerpage+1;
		} 
	}
	/**
	 * 初始化,使获取的数据库数据按积分排序
	 */
	public void initSort1() {
		String sql="select * from user order by integral DESC, flower DESC limit 0,200";
			PageController.bigList=SqlIInfo.getSort(sql);// 调用查询数据库的方法，返回所有的行
			//获取总页数
			if(bigList.size()%countPerpage==0) {
				pageCount=bigList.size()/countPerpage;
			}else {
				pageCount=bigList.size()/countPerpage+1;
			}
			
	}
	/**
	 * 初始化,使获取的数据库数据按鲜花排序
	 */
	public void initSort2() {
		String sql="select * from user order by flower DESC,integral DESC limit 0,200";
		PageController.bigList=SqlIInfo.getSort(sql);// 调用查询数据库的方法，返回所有的行
		//获取总页数
				if(bigList.size()%countPerpage==0) {
					pageCount=bigList.size()/countPerpage;
				}else {
					pageCount=bigList.size()/countPerpage+1;
				}
   }
	public PageController() {}//无参构造方法
	/**
	 * 构造方法设置当前页
	 * @param curentPageIndex 当前页
	 */
	public PageController(int curentPageIndex) {//构造方法设置当前页
		this.curentPageIndex = curentPageIndex;
	}
	/**
	 *设置每页显示的记录数
	 *@param countPerpage 当前页
	 */
	public void setCountPerpage(int countPerpage) {//设置每页显示的记录数
		this.countPerpage=countPerpage;
	}

	/**
	 *获取数据库所有用户信息
	 *@return 返回所有用户信息
	 */
	public Vector<Vector> getAllUser() {
		Vector<Vector> rows=new Vector<Vector>();//定义要返回的所有记录集合
		rows=SqlIInfo.getAll();
		return rows;
	}
	/**
	 *获取数据库所有用户游戏记录
	 *@return 返回所有游戏记录
	 */
	public Vector<Vector> getAllRecord() {
		Vector<Vector> rows=new Vector<Vector>();//定义要返回的所有记录集合
		String sql="select * from record";
		rows=SqlIInfo.getAll1(sql,null);
		return rows;
	}
	/**
	 *根据当前页数，筛选记录
	 *@return 返回当前页数据
	 */
	public Vector<Vector> getPaegData() {// 根据当前页数，筛选记录
		recordCount = bigList.size();//定义记录数为数据库中表的所有数据
		for (int i = (curentPageIndex - 1) * countPerpage; i < curentPageIndex * countPerpage && i < recordCount; i++) {//取得当前页数的记录，curentPageIndex当前页数，countPerpage每页显示的记录数
			smallList.add(bigList.get(i));//将记录加入到小集合中
		}
		return smallList;//返回小集合（当前页的数据）
	}
	/**
	 *下一页用户信息
	 *@return 返回下一页用户信息
	 */
	public Vector<Vector> nextPage(){//下一页
		if(curentPageIndex<pageCount) {
			curentPageIndex++;
		}else {
			curentPageIndex=1;
		}
		return getPaegData();//返回下一页的数据
	}
	/**
	 *上一页用户信息
	 **@return 返回上一页用户信息
	 */
	public Vector<Vector> prePage(){//上一页
		if(curentPageIndex>1) {
			curentPageIndex--;
		}else {
			curentPageIndex=pageCount;
		}
		return getPaegData();//返回上一页的数据
	}
	/**
	 *指定页用户信息
	 **@return 返回指定页用户信息
	 *@param page 指定页数
	 */
	public Vector<Vector> appointPage(int page){
		if(pageCount>=page)
		curentPageIndex=page;
		else
			curentPageIndex=1;;
		return getPaegData();
	}
}
