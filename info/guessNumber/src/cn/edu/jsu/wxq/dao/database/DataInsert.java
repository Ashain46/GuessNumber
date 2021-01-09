package cn.edu.jsu.wxq.dao.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.edu.jsu.wxq.user.User;
/**
 * 
 * 增加一万条数据到数据库中
 *@author 文雪茜
 */
public class DataInsert {
	/**
	 * 增加一万条数据到user表中
	 * @param rdd the Connection用来连接数据库
	 * @param con the RandomDatabase传递随机生成的数* 
	 * @exception SQLException On input error.
     * @see SQLException
	 */
	public static void insertUser(Connection con,RandomDatabase rdd) throws SQLException {
		
		String sql="insert into user(name,account,password,integral,flower) values(?,?,?,?,?)";
		SqlIInfo sqlDao=new SqlIInfo();
			PreparedStatement pre = con.prepareStatement(sql);
			for (int i =1 ; i <=10000; i++) {
			pre.setString(1, rdd.getName());
	     	pre.setString(2, rdd.getAccount());
	    	pre.setString(3, rdd.getPassword());
		    pre.setInt(4, rdd.getInteral());
		    pre.setInt(5, rdd.getFlower());
		    pre.addBatch();
			}
			pre.executeBatch();
	}
	/**
	 * 增加一万条数据到record表中,record表和user表的共同信息为name
	 * @param rdd the Connection用来连接数据库
	 * @param con the RandomDatabase传递随机生成的数据
	 * @exception SQLException On input error.
     * @see SQLException
	 */
	public static void insertRecord(Connection con,RandomDatabase rdd) throws SQLException {
		List<User> list=new SqlIInfo().getAllUser();
		String sql="insert into record(name,integral,playtime) values(?,?,?)";
			PreparedStatement pre = con.prepareStatement(sql);
			for (int i =1 ; i <=10000; i++) {
			int index=new Random().nextInt(list.size())+0;
			pre.setString(1, list.get(index).getName());
		    pre.setInt(2, rdd.getInteralOne());
		    pre.setString(3, rdd.getTime());
		    pre.addBatch();
			}
			pre.executeBatch();
	}
	/**
	 * 
	 * @param args the String增加一万条数据执行
	 */
	public static void main(String[] args) {
		RandomDatabase randomDatabase=new RandomDatabase();
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/game?serverTimezone=GMT%2B8","root", "123456");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//insertUser(connection, randomDatabase);
			insertRecord(connection, randomDatabase);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
