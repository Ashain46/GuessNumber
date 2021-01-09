 package cn.edu.jsu.wxq.dao.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.mysql.cj.exceptions.RSAException;

import cn.edu.jsu.wxq.user.Record;
import cn.edu.jsu.wxq.user.User;
/**
 * 对数据库进行操作
 * @author 文雪茜
 *
 */
public class SqlIInfo {
	/**
	 * 获取数据库中user表的数据
	 * @return 保存获取的结果
	 */
	public static Vector<Vector> getAll(){
		Vector<Vector> rows=new Vector<Vector>();//定义要返回的所有记录集合
		try {
			ConnectionDatabase conn=new ConnectionDatabase();
		    String sql="select * from user";
		    ResultSet result=SqlIInfo.search(sql,null);
			while(result.next()) {
				Vector row=new Vector();//定义行数据
				row.add(result.getString(1));
				row.add(result.getString(2));
				row.add(result.getString(3));
				row.add(result.getInt(4));
				row.add(result.getInt(5));
				rows.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;//返回所有行数据
	}
	/**
	 * 获取数据库中record表的数据
	  * @return 是用来返回排序后的结果
	 * @param sql the String传递sql语句
	 * @param str the String传递账号
	 */
	public static Vector<Vector> getAll1(String sql,String str){
		Vector<Vector> rows=new Vector<Vector>();//定义要返回的所有记录集合
		try {
			ConnectionDatabase conn=new ConnectionDatabase();
			Connection con=conn.getConnection();
		    ResultSet result=SqlIInfo.search(sql,str);
			while(result.next()) {
				Vector row=new Vector();//定义行数据
				row.add(result.getString(1));
				row.add(result.getInt(2));
				row.add(result.getString(3));
				rows.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;//返回所有行数据
	}
	
	/**
	 * 获取数据库中user表的数据
	* @return 保存获取的结果
	 */
	public static List<User> getAllUser(){
		List<User> list=new ArrayList<>();//定义要返回的所有记录集合
		try {
			ConnectionDatabase conn=new ConnectionDatabase();
		    String sql="select * from user";
		    ResultSet result=SqlIInfo.search(sql,null);
			while(result.next()) {
				String name = result.getString("name");
				String account = result.getString("account");
				String password = result.getString("password");
    			int integral = result.getInt("integral");
    		    int flower=result.getInt("flower");
    			list.add(new User(name, account, password, integral, flower));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;//返回所有行数据
	}
	
	/**
	 * 获取数据库中user表的数据
	 * @return 保存获取的结果
	 */
	public static List<Record> getAllRecord(){
		List<Record> list=new ArrayList<>();//定义要返回的所有记录集合
		try {
			ConnectionDatabase conn=new ConnectionDatabase();
		    String sql="select * from record";
		    ResultSet result=SqlIInfo.search(sql,null);
			while(result.next()) {
				String name = result.getString("name");
    			int integral = result.getInt("integral");
    		    String playtime = result.getString("playtime");
    			list.add(new Record(name,integral,playtime));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;//返回所有行数据
	}
	/**
	 * 获取数据库中user表的name,integral,flower,并按积分或鲜花排序
	 * @return 是用来返回排序后的结果
	 * @param sql the String 是用来传递sql语句
	 */
	public static Vector<Vector> getSort(String sql){
		Vector<Vector> rows=new Vector<Vector>();//定义要返回的所有记录集合
		int i=0;
		try {
			ConnectionDatabase conn=new ConnectionDatabase();
		    ResultSet result=SqlIInfo.search(sql,null);
			while(result.next()) {
				Vector row=new Vector();//定义行数据
				row.add(++i);
				row.add(result.getString(1));
				row.add(result.getInt(4));
				row.add(result.getInt(5));
				rows.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;//返回所有行数据
	}
	/**
	 * 根据账号查询user表
	 * @return user the User是用来返回查询用户表的结果
	 * @param account the String是用来传递用户账号
	 * @exception SQLException On input error.
     * @see SQLException
     * @exception IOException On input error.
     * @see IOException
	 */
	public static User selectUser(String account) throws SQLException ,IOException{
		User user=null; 
		ConnectionDatabase conn=new ConnectionDatabase();
		Connection con=conn.getConnection();
			PreparedStatement pre=con.prepareStatement("select *from user where account=?");
				pre.setString(1, account);
			ResultSet res=pre.executeQuery();
			if(res.next()) {
				user=new User(res.getString(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5));
		     return user;
			}
			return null;
	}
	/**
	 * 根据账号查询user表
	 * @return res the ResultSet是用来返回查询结果
	 * @param sql the String用来传递sql语句
	 * @param str the String用来传递账号信息
	 */
	public static ResultSet search(String sql,String str) {
		// TODO Auto-generated method stub
		//String sql="select * from user where account=?";
		ConnectionDatabase conn=new ConnectionDatabase();
		Connection con=conn.getConnection();
		ResultSet res=null;
		try {
			PreparedStatement pre=con.prepareStatement(sql);//数据库操作对象
			if(str!=null) {
				pre.setString(1, str);
			}
			res=pre.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * 根据昵称和游戏时间查询record表
	 * @return res the ResultSet是用来返回查询结果
	 * @param sql the String用来传递sql语句
	 * @param name the String昵称
	 * @param playtime the String游戏时间
	 */
	public static ResultSet searchRecord(String sql, String name, String playtime) {
		// TODO Auto-generated method stub
		ConnectionDatabase conn=new ConnectionDatabase();
		Connection con=conn.getConnection();
		ResultSet res=null;
		try {
			PreparedStatement pre=con.prepareStatement(sql);//数据库操作对象
			if(name!=null&&playtime!=null) {
				pre.setString(1, name);
				pre.setString(2, playtime);
			}
			res=pre.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * 获取数据库中user表所有数据
	 * @return res the ResultSet是用来返回查询结果
	 */
	public static ResultSet searchAll() {
		// TODO Auto-generated method stub
		//String sql="select * from user where account=?";
		ConnectionDatabase conn=new ConnectionDatabase();
		Connection con=conn.getConnection();
		String sql="select * from user";
		ResultSet res=null;
			try {
				res=con.createStatement().executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn.close();
		return res;
	}
	/**
	 * 添加user表数据到数据库
	 * @param sql the String用来传递sql语句;
	 * @param str the String[]用来传递用户信息
	 * @param str1 用来传递用户的鲜花和积分
	 * @return 返回添加结果
	 */
	public static boolean addUser(String sql,String[] str,int[] str1) {
		// TODO Auto-generated method stub
		ConnectionDatabase conn=new ConnectionDatabase();
		Connection con=conn.getConnection();
		try {
			PreparedStatement pre=con.prepareStatement(sql);//数据库操作对象
			if(str!=null) {
				pre.setString(1, str[0]);
		     	pre.setString(2, str[1]);
		    	pre.setString(3, str[2]);
			    pre.setInt(4, str1[0]);
			    pre.setInt(5, str1[1]);
			}
			if(pre.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 添加record表数据到数据库
	 * @param sql the String用来传递sql语句
	 * @param  name the String用来传递用户名
	 * @param integral 用来传递积分
	 * @param playtime the String用来传递游戏时间
	 * @return boolean 
	 */
	public static boolean addRecord(String sql,String name,int integral,String playtime) {
		ConnectionDatabase conn=new ConnectionDatabase();
		Connection con=conn.getConnection();
		try {
			PreparedStatement pre=con.prepareStatement(sql);//数据库操作对象
			if(name!=null&&playtime!=null) {
				pre.setString(1,name);
			    pre.setInt(2, integral);
			    pre.setString(3, playtime);
			}
			if(pre.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 实时更新积分和鲜花
	 * @param str1 传递修改的鲜花或积分
	 * @param str the String[]传递账号
	 */
	public static void modifyUser(int[] str1,String str[]) {
		// TODO Auto-generated method stub
		ConnectionDatabase conn=new ConnectionDatabase();
		Connection con=conn.getConnection();
		String sql="update user set integral =?,flower=? where account=?";
		try {
			PreparedStatement pre=con.prepareStatement(sql);//数据库操作对象
				pre.setInt(1, str1[0]);
				pre.setInt(2, str1[1]);
				pre.setString(3, str[0]);
				pre.executeUpdate();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
	
	/**
	 * 更改用户信息
	 * @param sql the String传递sql语句
	 * @param str the String[]传递修改的信息
	 */
	public static void modifyUser1(String sql,String str[]) {
		// TODO Auto-generated method stub
		ConnectionDatabase conn=new ConnectionDatabase();
		Connection con=conn.getConnection();
		//String sql="update user set name =?,password=? where account=?";
		try {
			PreparedStatement pre=con.prepareStatement(sql);//数据库操作对象
				pre.setString(1, str[0]);
				pre.setString(2, str[1]);
				pre.executeUpdate();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
	
	/**
	 * 从数据库删除信息
	 * @param sql the String传递sql语句
	 * @param str the String[]传递修改的信息
	 */
	public static void delectUser1(String sql,String str[]) {
		// TODO Auto-generated method stub
		ConnectionDatabase conn=new ConnectionDatabase();
		Connection con=conn.getConnection();
		//String sql="update user set name =?,password=? where account=?";
		try {
			PreparedStatement pre=con.prepareStatement(sql);//数据库操作对象
				pre.setString(1, str[0]);
				pre.executeUpdate();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
				
	/**
	 * 判断新账号是否已存在
	 * @param account the String是用来传账号
	 * @return 返回判断是否存在的结果
	 */
	public static boolean isExist(String account){
        try {
        	ConnectionDatabase con = new ConnectionDatabase();
            ResultSet result = SqlIInfo.search("select * from user where account=?",account);
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	/**
	 * 判断两次密码是否相同
	 * @param password1 the String是第一次输入的密码
	 * @param password2 the String是第二次输入的密码
	 * @return boolean the boolean是用来判断对错
	 */
	public static boolean isSame(String password1,String password2) {
		if(password1.equals(password2)) {
			return true;
		}
		else
			return false;
	}
	/**
	 * 判断账号是否合法
	 * @return 账号是否合法的结果
	 * @param account the String是用来传账号
	 */
	public static boolean isAccount(String account) {
		if(account.length()==10) {
			return true;
		}
		else
			return false;
	}
	
	/**
	 * 判断密码是否合法
	 * @param password the String是密码
	 * @return 返回密码是否合法的结果
	 */
	public static boolean isPassword(String password) {
		if(password.length()>=6&&password.length()<=16) {
			return true;
		}
		else
			return false;
	}
}
