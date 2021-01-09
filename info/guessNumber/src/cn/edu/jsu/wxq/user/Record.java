package cn.edu.jsu.wxq.user;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
/**
 * 封装record表
 * @author 86135
 *
 */
public class Record {
	private String name;//用户名
	private int integral;//用户积分
	private String playtime;//用户游戏记录
	public Record() {};
	/**
	 * 用户游戏记录
	 * @param name the String昵称
	 * @param integral 积分
	 * @param playtime the Sting游戏时间
	 */
	public Record(String name,int integral,String playtime) {
		this.name=name;
		this.integral=integral;
		this.playtime=playtime;//新用户赠送十朵鲜花;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public String getPlaytime() {
		return playtime;
	}
	public void setPlaytime(String playtime) {
		this.playtime=playtime;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral=integral;
	}

	/**
	 * 获取本地时间
	 *@return time the String返回本地时间
	 */
		public static String getTime() {
			Calendar c = new GregorianCalendar();

			// 获取日期
			String time = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE) + " ";
			// 获取时刻
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			int second = c.get(Calendar.SECOND);
			// 当时间不足10的时候，在个位数前加一个0，使时间整齐
			String ph = hour < 10 ? "0" : "";
			String pm = minute < 10 ? "0" : "";
			String ps = second < 10 ? "0" : "";

			time += ph + hour + ":" + pm + minute + ":" + ps + second;
			return time;
		}

}
