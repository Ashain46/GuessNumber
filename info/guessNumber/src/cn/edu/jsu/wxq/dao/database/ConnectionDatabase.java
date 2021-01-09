package cn.edu.jsu.wxq.dao.database;
import java.awt.List;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.junit.Test;
import org.omg.CORBA.PRIVATE_MEMBER;

import com.mysql.cj.jdbc.ConnectionImpl;

import cn.edu.jsu.wxq.user.User;
/**
 * 数据库连接
 *
 */
public class ConnectionDatabase {
	
		// TODO Auto-generated method stub
	private static final String DATABASE_DRVIER = "com.mysql.cj.jdbc.Driver";
    // 数据库的位置，url是统一资源定位符
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/game?serverTimezone=GMT%2B8";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "123456";
   
	private Connection con = null;
	ResultSet res = null;
	
	public ConnectionDatabase() {
		try {
			Class.forName(DATABASE_DRVIER);
			this.con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 返回数据库连接对象
	 * @return this.con the Connection返回数据库连接对象
	 */
	public Connection getConnection() {//返回数据库连接对象
		return this.con;
	}
	/**
	 * 关闭数据库
	 */
	@Test
	public void close() {//关闭数据库
		if(this.con!=null) {
			try {
				this.con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
