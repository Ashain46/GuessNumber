package cn.edu.jsu.wxq.user;

import org.junit.Test;

/**
 * 封装user表
 * @author 86135
 *
 */
public class User {
	private String name;//用户名
	private String password;//用户密码
	private String account;//用户账号
	private int integral;//用户积分
	private int flower;//用户鲜花
	public User() {};
	public User(String name,String account,String password) {
		this.name=name;
		this.account=account;
		this.integral=0;
		this.flower=10;//新用户赠送十朵鲜花;
	}
	/**
	 * 用户信息
	 * @param name the String用户名
	 * @param account the String账号
	 * @param password the String密码
	 * @param integral 积分
	 * @param flower 鲜花
	 */
	public User(String name,String account,String password,int integral,int flower) {
		this.name=name;
		this.account=account;
		this.password=password;
		this.integral=integral;
		this.flower=flower;//新用户赠送十朵鲜花;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account=account;
	}
	public int getFlower() {
		return flower;
	}
	public void setFlower(int flower) {
		this.flower=flower;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral=integral;
	}

}
