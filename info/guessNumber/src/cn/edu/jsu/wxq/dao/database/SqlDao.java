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
	 * ��ȡ���ݿ���user�������
	 * @return �����ȡ�Ľ��
	 */
	public Vector<Vector> getAll();
	/**
	 * ��ȡ���ݿ���record�������
	  * @return ���������������Ľ��
	 * @param sql the String����sql���
	 * @param str the String�����˺�
	 */
	public Vector<Vector> getAll1(String sql,String str);
	
	/**
	 * ��ȡ���ݿ���user�������
	* @return �����ȡ�Ľ��
	 */
	public List<User> getAllUser();
	
	/**
	 * ��ȡ���ݿ���user�������
	 * @return �����ȡ�Ľ��
	 */
	public List<Record> getAllRecord();
	/**
	 * ��ȡ���ݿ���user���name,integral,flower,�������ֻ��ʻ�����
	 * @return ���������������Ľ��
	 * @param sql the String ����������sql���
	 */
	public Vector<Vector> getSort(String sql);
	/**
	 * �����˺Ų�ѯuser��
	 * @return user the User���������ز�ѯ�û���Ľ��
	 * @param account the String�����������û��˺�
	 * @exception SQLException On input error.
     * @see SQLException
     * @exception IOException On input error.
     * @see IOException
	 */
	public User selectUser(String account);
	/**
	 * �����˺Ų�ѯuser��
	 * @return res the ResultSet���������ز�ѯ���
	 * @param sql the String��������sql���
	 * @param str the String���������˺���Ϣ
	 */
	public ResultSet search(String sql,String str);
	/**
	 * ��ȡ���ݿ���user����������
	 * @return res the ResultSet���������ز�ѯ���
	 */
	public ResultSet searchAll();
	/**
	 * ���user�����ݵ����ݿ�
	 * @param sql the String��������sql���;
	 * @param str the String[]���������û���Ϣ
	 * @param str1 ���������û����ʻ��ͻ���
	 * @return ������ӽ��
	 */
	public boolean addUser(String sql,String[] str,int[] str1);
	/**
	 * ���record�����ݵ����ݿ�
	 * @param sql the String��������sql���
	 * @param  name the String���������û���
	 * @param integral �������ݻ���
	 * @param playtime the String����������Ϸʱ��
	 * @return boolean 
	 */
	public boolean addRecord(String sql,String name,int integral,String playtime);
	
	/**
	 * ʵʱ���»��ֺ��ʻ�
	 * @param str1 �����޸ĵ��ʻ������
	 * @param str the String[]�����˺�
	 */
	public void modifyUser(int[] str1,String str[]);
	
	/**
	 * �����û���Ϣ
	 * @param sql the String����sql���
	 * @param str the String[]�����޸ĵ���Ϣ
	 */
	public void modifyUser1(String sql,String str[]) ;
	
	/**
	 * �����ݿ�ɾ����Ϣ
	 * @param sql the String����sql���
	 * @param str the String[]�����޸ĵ���Ϣ
	 */
	public void delectUser1(String sql,String str[]) ;
				
	/**
	 * �ж����˺��Ƿ��Ѵ���
	 * @param account the String���������˺�
	 * @return �����ж��Ƿ���ڵĽ��
	 */
	public boolean isExist(String account);
	
	/**
	 * �ж����������Ƿ���ͬ
	 * @param password1 the String�ǵ�һ�����������
	 * @param password2 the String�ǵڶ������������
	 * @return boolean the boolean�������ж϶Դ�
	 */
	public boolean isSame(String password1,String password2) ;
	/**
	 * �ж��˺��Ƿ�Ϸ�
	 * @return �˺��Ƿ�Ϸ��Ľ��
	 * @param account the String���������˺�
	 */
	public boolean isAccount(String account) ;
	
	/**
	 * �ж������Ƿ�Ϸ�
	 * @param password the String������
	 * @return ���������Ƿ�Ϸ��Ľ��
	 */
	public boolean isPassword(String password) ;
}
