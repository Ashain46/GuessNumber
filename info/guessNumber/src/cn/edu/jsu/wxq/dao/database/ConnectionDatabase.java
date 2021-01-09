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
 * ���ݿ�����
 *
 */
public class ConnectionDatabase {
	
		// TODO Auto-generated method stub
	private static final String DATABASE_DRVIER = "com.mysql.cj.jdbc.Driver";
    // ���ݿ��λ�ã�url��ͳһ��Դ��λ��
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
	 * �������ݿ����Ӷ���
	 * @return this.con the Connection�������ݿ����Ӷ���
	 */
	public Connection getConnection() {//�������ݿ����Ӷ���
		return this.con;
	}
	/**
	 * �ر����ݿ�
	 */
	@Test
	public void close() {//�ر����ݿ�
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
