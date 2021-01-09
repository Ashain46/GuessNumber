package cn.edu.jsu.wxq.user;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Record {
	private String name;//�û���
	private int integral;//�û�����
	private String playtime;//�û���Ϸ��¼
	public Record() {};
	/**
	 * @param name
	 * @param account
	 * @param password
	 * @param integral
	 * @param flower
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

	//��ȡ����ʱ��
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
