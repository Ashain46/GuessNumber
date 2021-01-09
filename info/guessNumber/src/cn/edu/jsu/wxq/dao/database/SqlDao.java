package cn.edu.jsu.wxq.dao.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import cn.edu.jsu.wxq.user.Record;
import cn.edu.jsu.wxq.user.User;

public interface SqlDao {
	/**
	 * 获取数据库中user表的数据
	 * @return 保存获取的结果
	 */
	public Vector<Vector> getAll();
	/**
	 * 获取数据库中record表的数据
	  * @return 是用来返回排序后的结果
	 * @param sql the String传递sql语句
	 * @param str the String传递账号
	 */
	public Vector<Vector> getAll1(String sql,String str);
	
	/**
	 * 获取数据库中user表的数据
	* @return 保存获取的结果
	 */
	public List<User> getAllUser();
	
	/**
	 * 获取数据库中user表的数据
	 * @return 保存获取的结果
	 */
	public List<Record> getAllRecord();
	/**
	 * 获取数据库中user表的name,integral,flower,并按积分或鲜花排序
	 * @return 是用来返回排序后的结果
	 * @param sql the String 是用来传递sql语句
	 */
	public Vector<Vector> getSort(String sql);
	/**
	 * 根据账号查询user表
	 * @return user the User是用来返回查询用户表的结果
	 * @param account the String是用来传递用户账号
	 * @exception SQLException On input error.
     * @see SQLException
     * @exception IOException On input error.
     * @see IOException
	 */
	public User selectUser(String account);
	/**
	 * 根据账号查询user表
	 * @return res the ResultSet是用来返回查询结果
	 * @param sql the String用来传递sql语句
	 * @param str the String用来传递账号信息
	 */
	public ResultSet search(String sql,String str);
	/**
	 * 获取数据库中user表所有数据
	 * @return res the ResultSet是用来返回查询结果
	 */
	public ResultSet searchAll();
	/**
	 * 添加user表数据到数据库
	 * @param sql the String用来传递sql语句;
	 * @param str the String[]用来传递用户信息
	 * @param str1 用来传递用户的鲜花和积分
	 * @return 返回添加结果
	 */
	public boolean addUser(String sql,String[] str,int[] str1);
	/**
	 * 添加record表数据到数据库
	 * @param sql the String用来传递sql语句
	 * @param  name the String用来传递用户名
	 * @param integral 用来传递积分
	 * @param playtime the String用来传递游戏时间
	 * @return boolean 
	 */
	public boolean addRecord(String sql,String name,int integral,String playtime);
	
	/**
	 * 实时更新积分和鲜花
	 * @param str1 传递修改的鲜花或积分
	 * @param str the String[]传递账号
	 */
	public void modifyUser(int[] str1,String str[]);
	
	/**
	 * 更改用户信息
	 * @param sql the String传递sql语句
	 * @param str the String[]传递修改的信息
	 */
	public void modifyUser1(String sql,String str[]) ;
	
	/**
	 * 从数据库删除信息
	 * @param sql the String传递sql语句
	 * @param str the String[]传递修改的信息
	 */
	public void delectUser1(String sql,String str[]) ;
				
	/**
	 * 判断新账号是否已存在
	 * @param account the String是用来传账号
	 * @return 返回判断是否存在的结果
	 */
	public boolean isExist(String account);
	
	/**
	 * 判断两次密码是否相同
	 * @param password1 the String是第一次输入的密码
	 * @param password2 the String是第二次输入的密码
	 * @return boolean the boolean是用来判断对错
	 */
	public boolean isSame(String password1,String password2) ;
	/**
	 * 判断账号是否合法
	 * @return 账号是否合法的结果
	 * @param account the String是用来传账号
	 */
	public boolean isAccount(String account) ;
	
	/**
	 * 判断密码是否合法
	 * @param password the String是密码
	 * @return 返回密码是否合法的结果
	 */
	public boolean isPassword(String password) ;
}
