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
 * �����ݿ���в���
 * @author ��ѩ��
 *
 */
public class SqlIInfo {
	/**
	 * ��ȡ���ݿ���user�������
	 * @return �����ȡ�Ľ��
	 */
	public static Vector<Vector> getAll(){
		Vector<Vector> rows=new Vector<Vector>();//����Ҫ���ص����м�¼����
		try {
			ConnectionDatabase conn=new ConnectionDatabase();
		    String sql="select * from user";
		    ResultSet result=SqlIInfo.search(sql,null);
			while(result.next()) {
				Vector row=new Vector();//����������
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
		return rows;//��������������
	}
	/**
	 * ��ȡ���ݿ���record�������
	  * @return ���������������Ľ��
	 * @param sql the String����sql���
	 * @param str the String�����˺�
	 */
	public static Vector<Vector> getAll1(String sql,String str){
		Vector<Vector> rows=new Vector<Vector>();//����Ҫ���ص����м�¼����
		try {
			ConnectionDatabase conn=new ConnectionDatabase();
			Connection con=conn.getConnection();
		    ResultSet result=SqlIInfo.search(sql,str);
			while(result.next()) {
				Vector row=new Vector();//����������
				row.add(result.getString(1));
				row.add(result.getInt(2));
				row.add(result.getString(3));
				rows.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;//��������������
	}
	
	/**
	 * ��ȡ���ݿ���user�������
	* @return �����ȡ�Ľ��
	 */
	public static List<User> getAllUser(){
		List<User> list=new ArrayList<>();//����Ҫ���ص����м�¼����
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
		return list;//��������������
	}
	
	/**
	 * ��ȡ���ݿ���user�������
	 * @return �����ȡ�Ľ��
	 */
	public static List<Record> getAllRecord(){
		List<Record> list=new ArrayList<>();//����Ҫ���ص����м�¼����
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
		return list;//��������������
	}
	/**
	 * ��ȡ���ݿ���user���name,integral,flower,�������ֻ��ʻ�����
	 * @return ���������������Ľ��
	 * @param sql the String ����������sql���
	 */
	public static Vector<Vector> getSort(String sql){
		Vector<Vector> rows=new Vector<Vector>();//����Ҫ���ص����м�¼����
		int i=0;
		try {
			ConnectionDatabase conn=new ConnectionDatabase();
		    ResultSet result=SqlIInfo.search(sql,null);
			while(result.next()) {
				Vector row=new Vector();//����������
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
		return rows;//��������������
	}
	/**
	 * �����˺Ų�ѯuser��
	 * @return user the User���������ز�ѯ�û���Ľ��
	 * @param account the String�����������û��˺�
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
	 * �����˺Ų�ѯuser��
	 * @return res the ResultSet���������ز�ѯ���
	 * @param sql the String��������sql���
	 * @param str the String���������˺���Ϣ
	 */
	public static ResultSet search(String sql,String str) {
		// TODO Auto-generated method stub
		//String sql="select * from user where account=?";
		ConnectionDatabase conn=new ConnectionDatabase();
		Connection con=conn.getConnection();
		ResultSet res=null;
		try {
			PreparedStatement pre=con.prepareStatement(sql);//���ݿ��������
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
	 * �����ǳƺ���Ϸʱ���ѯrecord��
	 * @return res the ResultSet���������ز�ѯ���
	 * @param sql the String��������sql���
	 * @param name the String�ǳ�
	 * @param playtime the String��Ϸʱ��
	 */
	public static ResultSet searchRecord(String sql, String name, String playtime) {
		// TODO Auto-generated method stub
		ConnectionDatabase conn=new ConnectionDatabase();
		Connection con=conn.getConnection();
		ResultSet res=null;
		try {
			PreparedStatement pre=con.prepareStatement(sql);//���ݿ��������
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
	 * ��ȡ���ݿ���user����������
	 * @return res the ResultSet���������ز�ѯ���
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
	 * ���user�����ݵ����ݿ�
	 * @param sql the String��������sql���;
	 * @param str the String[]���������û���Ϣ
	 * @param str1 ���������û����ʻ��ͻ���
	 * @return ������ӽ��
	 */
	public static boolean addUser(String sql,String[] str,int[] str1) {
		// TODO Auto-generated method stub
		ConnectionDatabase conn=new ConnectionDatabase();
		Connection con=conn.getConnection();
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
	/**
	 * ���record�����ݵ����ݿ�
	 * @param sql the String��������sql���
	 * @param  name the String���������û���
	 * @param integral �������ݻ���
	 * @param playtime the String����������Ϸʱ��
	 * @return boolean 
	 */
	public static boolean addRecord(String sql,String name,int integral,String playtime) {
		ConnectionDatabase conn=new ConnectionDatabase();
		Connection con=conn.getConnection();
		try {
			PreparedStatement pre=con.prepareStatement(sql);//���ݿ��������
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
	 * ʵʱ���»��ֺ��ʻ�
	 * @param str1 �����޸ĵ��ʻ������
	 * @param str the String[]�����˺�
	 */
	public static void modifyUser(int[] str1,String str[]) {
		// TODO Auto-generated method stub
		ConnectionDatabase conn=new ConnectionDatabase();
		Connection con=conn.getConnection();
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
		}
	
	/**
	 * �����û���Ϣ
	 * @param sql the String����sql���
	 * @param str the String[]�����޸ĵ���Ϣ
	 */
	public static void modifyUser1(String sql,String str[]) {
		// TODO Auto-generated method stub
		ConnectionDatabase conn=new ConnectionDatabase();
		Connection con=conn.getConnection();
		//String sql="update user set name =?,password=? where account=?";
		try {
			PreparedStatement pre=con.prepareStatement(sql);//���ݿ��������
				pre.setString(1, str[0]);
				pre.setString(2, str[1]);
				pre.executeUpdate();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
	
	/**
	 * �����ݿ�ɾ����Ϣ
	 * @param sql the String����sql���
	 * @param str the String[]�����޸ĵ���Ϣ
	 */
	public static void delectUser1(String sql,String str[]) {
		// TODO Auto-generated method stub
		ConnectionDatabase conn=new ConnectionDatabase();
		Connection con=conn.getConnection();
		//String sql="update user set name =?,password=? where account=?";
		try {
			PreparedStatement pre=con.prepareStatement(sql);//���ݿ��������
				pre.setString(1, str[0]);
				pre.executeUpdate();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
				
	/**
	 * �ж����˺��Ƿ��Ѵ���
	 * @param account the String���������˺�
	 * @return �����ж��Ƿ���ڵĽ��
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
	 * �ж����������Ƿ���ͬ
	 * @param password1 the String�ǵ�һ�����������
	 * @param password2 the String�ǵڶ������������
	 * @return boolean the boolean�������ж϶Դ�
	 */
	public static boolean isSame(String password1,String password2) {
		if(password1.equals(password2)) {
			return true;
		}
		else
			return false;
	}
	/**
	 * �ж��˺��Ƿ�Ϸ�
	 * @return �˺��Ƿ�Ϸ��Ľ��
	 * @param account the String���������˺�
	 */
	public static boolean isAccount(String account) {
		if(account.length()==10) {
			return true;
		}
		else
			return false;
	}
	
	/**
	 * �ж������Ƿ�Ϸ�
	 * @param password the String������
	 * @return ���������Ƿ�Ϸ��Ľ��
	 */
	public static boolean isPassword(String password) {
		if(password.length()>=6&&password.length()<=16) {
			return true;
		}
		else
			return false;
	}
}
