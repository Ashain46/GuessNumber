package cn.edu.jsu.wxq.dao.database;
import java.awt.List;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.mysql.cj.jdbc.ConnectionImpl;

import cn.edu.jsu.wxq.user.User;
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
	
	public Connection getConnection() {//�������ݿ����Ӷ���
		return this.con;
	}
	
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
	
	
	/**
	 * �����Ƕ����ݿ����ɾ�Ĳ���
	 * 
	 **/
	/*public boolean addUser(String[] str,int[] str1) {
		// TODO Auto-generated method stub
		String sql="insert into user(name,account,password,integral,flower) values(?,?,?,?,?)";
		try {
			PreparedStatement pre=con.prepareStatement(sql);//���ݿ��������
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
	public void modifyUser(int[] str1,String str[]) {
		// TODO Auto-generated method stub
		String sql="update user set integral =?,flower=? where account=?";
		try {
			PreparedStatement pre=con.prepareStatement(sql);//���ݿ��������
				pre.setInt(1, str1[0]);
				pre.setInt(2, str1[1]);
				pre.setString(3, str[0]);
				pre.executeUpdate();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}		
	}*/
}
