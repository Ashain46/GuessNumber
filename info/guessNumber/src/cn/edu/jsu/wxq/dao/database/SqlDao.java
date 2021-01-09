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

public class SqlDao {
	/*
	 * 获取数据库中的数据
	 */
	public static Vector<Vector> getAll(){
		Vector<Vector> rows=new Vector<Vector>();//定义要返回的所有记录集合
		try {
			ConnectionDatabase conn=new ConnectionDatabase();
		    String sql="select * from user";
		    ResultSet result=SqlDao.search(sql,null);
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
	
	public static Vector<Vector> getAll1(String sql,String str){
		Vector<Vector> rows=new Vector<Vector>();//定义要返回的所有记录集合
		try {
			ConnectionDatabase conn=new ConnectionDatabase();
			Connection con=conn.getConnection();
		    ResultSet result=SqlDao.search(sql,str);
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
	
	
	public static List<User> getAllUser(){
		List<User> list=new ArrayList<>();//定义要返回的所有记录集合
		try {
			ConnectionDatabase conn=new ConnectionDatabase();
		    String sql="select * from user";
		    ResultSet result=SqlDao.search(sql,null);
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
	
	public static List<Record> getAllRecord(){
		List<Record> list=new ArrayList<>();//定义要返回的所有记录集合
		try {
			ConnectionDatabase conn=new ConnectionDatabase();
		    String sql="select * from record";
		    ResultSet result=SqlDao.search(sql,null);
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
	//按积分排序 
	public static Vector<Vector> getSort(String sql){
		Vector<Vector> rows=new Vector<Vector>();//定义要返回的所有记录集合
		int i=0;
		try {
			ConnectionDatabase conn=new ConnectionDatabase();
		    ResultSet result=SqlDao.search(sql,null);
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
	
	//按积分排序 
		public static Vector<Vector> getVagueAll(String sql,String str){
			Vector<Vector> rows=new Vector<Vector>();//定义要返回的所有记录集合
			int i=0;
			try {
				ConnectionDatabase conn=new ConnectionDatabase();
				Connection con=conn.getConnection();
				PreparedStatement pre=con.prepareStatement(sql);
				pre.setString(1, str);
				ResultSet result=pre.executeQuery();
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
	 * 下面是对数据库的增删改操作
	 * 
	 **/
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
	
	public static boolean addRecord(String sql,String name,int integral,String playtime) {
		// TODO Auto-generated method stub
		ConnectionDatabase conn=new ConnectionDatabase();
		Connection con=conn.getConnection();
		try {
			PreparedStatement pre=con.prepareStatement(sql);//数据库操作对象
				pre.setString(1,name);
			    pre.setInt(2, integral);
			    pre.setString(3, playtime);
			if(pre.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//玩游戏更新积分和鲜花
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
	
	//更改用户信息:用户名和密码
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
				
	/*
	 * 判断新账号是否已存在
	 */
	public static boolean isExist(String account){
        try {
        	ConnectionDatabase con = new ConnectionDatabase();
            ResultSet result = SqlDao.search("select * from user where account=?",account);
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	/*
	 * 判断两次密码是否相同
	 */
	public static boolean isSame(String password1,String password2) {
		if(password1.equals(password2)) {
			return true;
		}
		else
			return false;
	}
	/*
	 * 判断账号是否合法
	 */
	public static boolean isAccount(String account) {
		if(account.length()==10) {
			return true;
		}
		else
			return false;
	}
	
	/*
	 * 判断密码是否合法
	 */
	public static boolean isPassword(String password) {
		if(password.length()>=6&&password.length()<=16) {
			return true;
		}
		else
			return false;
	}
}
