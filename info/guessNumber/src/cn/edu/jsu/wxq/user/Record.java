package cn.edu.jsu.wxq.user;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
/**
 * ��װrecord��
 * @author 86135
 *
 */
public class Record {
	private String name;//�û���
	private int integral;//�û�����
	private String playtime;//�û���Ϸ��¼
	public Record() {};
	/**
	 * �û���Ϸ��¼
	 * @param name the String�ǳ�
	 * @param integral ����
	 * @param playtime the Sting��Ϸʱ��
	 */
	public Record(String name,int integral,String playtime) {
		this.name=name;
		this.integral=integral;
		this.playtime=playtime;//���û�����ʮ���ʻ�;
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
	 * ��ȡ����ʱ��
	 *@return time the String���ر���ʱ��
	 */
		public static String getTime() {
			Calendar c = new GregorianCalendar();

			// ��ȡ����
			String time = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE) + " ";
			// ��ȡʱ��
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			int second = c.get(Calendar.SECOND);
			// ��ʱ�䲻��10��ʱ���ڸ�λ��ǰ��һ��0��ʹʱ������
			String ph = hour < 10 ? "0" : "";
			String pm = minute < 10 ? "0" : "";
			String ps = second < 10 ? "0" : "";

			time += ph + hour + ":" + pm + minute + ":" + ps + second;
			return time;
		}

}
