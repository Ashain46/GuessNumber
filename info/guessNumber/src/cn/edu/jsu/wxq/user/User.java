package cn.edu.jsu.wxq.user;

public class User {
	private String name;//�û���
	private String password;//�û�����
	private String account;//�û��˺�
	private int integral;//�û�����
	private int flower;//�û��ʻ�
	public User() {};
	public User(String name,String account,String password) {
		this.name=name;
		this.account=account;
		this.integral=0;
		this.flower=10;//���û�����ʮ���ʻ�;
	}
	/**
	 * @param name
	 * @param account
	 * @param password
	 * @param integral
	 * @param flower
	 */
	public User(String name,String account,String password,int integral,int flower) {
		this.name=name;
		this.account=account;
		this.password=password;
		this.integral=integral;
		this.flower=flower;//���û�����ʮ���ʻ�;
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
