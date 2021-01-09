package cn.edu.jsu.wxq.dao.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.edu.jsu.wxq.user.User;

public class DataInsert {
	public static void InsertUser(Connection con,RandomDatabase rdd) throws SQLException {
		String sql="insert into user(name,account,password,integral,flower) values(?,?,?,?,?)";
		SqlDao sqlDao=new SqlDao();
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
	public static void InsertRecord(Connection con,RandomDatabase rdd) throws SQLException {
		List<User> list=new SqlDao().getAllUser();
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
			//InsertUser(connection, randomDatabase);
			InsertRecord(connection, randomDatabase);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
