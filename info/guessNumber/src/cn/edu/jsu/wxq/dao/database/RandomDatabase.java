package cn.edu.jsu.wxq.dao.database;

import java.util.Random;

/**
 ������ɸ�������
 */
public class RandomDatabase {
	// �ټ���
		private String[] userName = { "��", "Ǯ", "��", "��", "��", "��", "֣", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
				"��", "��", "��", "��", "��", "ʩ", "��", "��", "��", "��", "��", "��", "κ", "��", "��", "��", "л", "��", "��", "��", "ˮ",
				"�", "��", "��", "��", "��", "��", "��", "��", "��", "��", "³", "Τ", "��", "��", "��", "��", "��", "��", "��", "��", "Ԭ",
				"��", "ۺ", "��", "ʷ", "��", "��", "��", "�", "Ѧ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
				"��", "��", "ʱ", "��", "Ƥ", "��", "��", "��", "��", "��", "Ԫ", "��", "��", "��", "ƽ", "��", "��", "��", "��", "��", "Ҧ",
				"��", "տ", "��", "��", "ë", "��", "��", "��", "��", "��", "��", "��", "��", "��", "̸", "��", "é", "��", "��", "��",
				"��", "��", "��", "ף", "��", "��", "��", "��", "��", "��", "ϯ", "��", "��", "ǿ", "��", "·", "¦", "Σ", "��", "ͯ", "��",
				"��", "÷", "ʢ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "֧", "��",
				"��", "��", "¬", "Ī", "��", "��", "��", "��", "��", "��", "Ӧ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
				"��", "��", "ʯ", "��", "��", "ť", "��", "��", "��", "��", "��", "��", "½", "��", "��", "��", "��", "��", "��", "��", "��",
				"��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "ɽ",
				"��", "��", "��", "�", "��", "ȫ", "ۭ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
				"��", "��", "��", "��", "��", "ղ", "��", "��", "Ҷ", "��", "˾", "��", "۬", "��", "��", "��", "ӡ", "��", "��", "��", "��",
				"ۢ", "��", "��", "��", "��", "��", "��", "׿", "��", "��", "��", "��", "��", "��", "��", "��", "��", "˫", "��", "ݷ",
				"��", "��", "̷", "��", "��", "��", "��", "��", "��", "��", "Ƚ", "��", "۪", "Ӻ", "ȴ", "�", "ɣ", "��", "�", "ţ", "��",
				"ͨ", "��", "��", "��", "��", "��", "ũ", "��", "��", "ׯ", "��", "��", "��", "��", "��", "Ľ", "��", "��", "ϰ", "��",
				"��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
				"��", "��", "��", "»", "��", "��", "ŷ", "�", "��", "��", "ε", "Խ", "¡", "ʦ", "��", "��", "��", "��", "��", "��",
				"��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "ɳ", "ؿ", "��", "��", "��", "��", "��", "��", "��",
				"��", "��", "��", "��", "��", "ۣ", "��", "Ȩ", "��", "��", "��", "��", "��", "��", "��", "��", "˧", "��", "��", "��", "�C",
				"��", "��", "��", "��", "��", "��", "��", "��", "��", "۳", "Ϳ", "��", "��", "Ĳ", "��", "٦", "��", "��", "ī", "��", "��",
				"��", "��", "��", "��", "١", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "չ", "��", "̴", "��", "��", "��",
				"��", "˴", "¥", "��", "ð", "��", "ֿ", "��", "��", "��", "��", "ԭ", "��", "��", "��", "��", "��", "��", "��",
			    "��", "��", "��", "��", "��", "��", "��", "ľ", "��", "��", "ۨ", "��", "ö", "��", "��", "�", "��", "��", "��", "��",
				"��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "�G", "��ٹ", "˾��", "�Ϲ�", "ŷ��",
				"�ĺ�", "���", "����", "����", "����", "�ʸ�", "����", "ξ��", "����", "�̨", "��ұ", "����", "���", "����", "����", "̫��", "����", "����",
				"����", "��ԯ", "���", "����", "����", "����", "Ľ��", "����", "˾ͽ", "˾��", "أ��", "˾��", "����", "����", "�ӳ�", "���", "��ľ",
				"����", "����", "���", "����", "����", "����", "�ذ�", "�й�", "�׸�", "����", "�θ�", "����", "����", "΢��", "����", "����", "����", "����",
				"�Ϲ�", "����", "����", "����", "̫ʷ", "�ٳ�", "����", "��ͻ", "����", "����", "����", "��ĸ", "˾��", "����", "Ӻ��", "����", "����", "����",
				"��®", "����", "�Ϲ�", "����", "����" };
		/**
		 * �����������
		 * 
		 * @return ���ؽ��
		 */
		public String getName() {
			String str = null;
			String name = null;
			int highPos, lowPos;
			Random random = new Random();
			// ���룬0xA0��ͷ���ӵ�16����ʼ����0xB0=11*16=176,16~55һ�����֣�56~87��������
			highPos = (176 + Math.abs(random.nextInt(72)));
			random = new Random();
			// λ�룬0xA0��ͷ����Χ��1~94��
			lowPos = 161 + Math.abs(random.nextInt(94));

			byte[] bArr = new byte[2];
			bArr[0] = (new Integer(highPos)).byteValue();
			bArr[1] = (new Integer(lowPos)).byteValue();
			try {
				// ��λ����ϳɺ���
				str = new String(bArr, "GB2312");
				int index = random.nextInt(userName.length - 1);
				// ���һ�����������
				name = userName[index] + str;

			} catch (Exception e) {
				e.printStackTrace();
			}
			return name;
		}
		
		/**
		 * ��������˺�
		 * 
		 * @return ���ؽ��
		 */
		public String getAccount() {
			String[] userAccount={"0","1","2","3","4","5","6","7","8","9"};
			Random random=new Random();
			int length=userAccount.length;
			String randomString="";
			for(int i=0;i<length;i++) {
				randomString+=userAccount[random.nextInt(length)];
			}
			random =new Random(System.currentTimeMillis());
			String resultStr="";
			for(int i=0;i<10;i++) {
				resultStr+=randomString.charAt(random.nextInt(randomString.length()-1));
			}
			return resultStr;
		}
		
		
		/**
	     * �������6-16λ����
	     * @return ���ؽ��
	     */
	    public static String getPassword() {
	        String val = "";
	        Random random = new Random();
	        int numbers = random.nextInt(10) + 6; // 6��16λ;
	        for (int i = 0; i < numbers; i++) {
	            // �����ĸ��������
	            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
	            // �ַ���
	            if ("char".equalsIgnoreCase(charOrNum)) {
	                //ȡ�ô�д��ĸ����Сд��ĸ
	                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
	                val += (char) (choice + random.nextInt(26));
	            } else if ("num".equalsIgnoreCase(charOrNum)) {
	                // ����
	                val += String.valueOf(random.nextInt(10));
	            }
	        }
	        return  val.toLowerCase();
	    }
	    
	    /**
	     * ������ɻ���
	     * @return ���ؽ��
	     */
	    public int getInteral() {
	    	Random number=new Random();
	    	return number.nextInt(70000)+(-30000);
		}
	    /**
	     * ��������ʻ�
	     * @return ���ؽ��
	     */
	    public int getFlower() {
	    	Random number=new Random();
	    	return number.nextInt(100000)+0;
		}
	    
	    /**
	     * ������ɵ�����Ϸ����
	     * @return ���ؽ��
	     */
	    public int getInteralOne() {
	    	Random number=new Random();
	    	return number.nextInt(500)+0;
		}
	    /**
	     * ���������Ϸʱ��
	     * @return ���ؽ��
	     */
	    public String getTime() {
	    	//�������������
	    	Random number=new Random();
	    	int year=number.nextInt(2021-2015+1)+2015;
	    	int month=number.nextInt(12)+1;
	    	int day,b=0;
	    	int[] a= {1,3,5,7,8,10,12};
	    	for(int i=0;i<7;i++) {
	    		if (month==a[i]) {
					b=1;
				}
	    	}
	    	if(month==2)
	    	   day=number.nextInt(29-1)+1;
	    	else if (b==1) {
	    		day=number.nextInt(32-1)+1;
			}
	    	else day=number.nextInt(31-1)+1;
	    	String time = year + "-" + month + "-" + day + " ";
	 			// ��ȡʱ��
	    	int hour = number.nextInt(24)+0;
			int minute = number.nextInt(60)+0;
	  		int second = number.nextInt(60)+0;
	    	// ��ʱ�䲻��10��ʱ���ڸ�λ��ǰ��һ��0��ʹʱ������
	    	String ph = hour < 10 ? "0" : "";
	    	String pm = minute < 10 ? "0" : "";
	    	String ps = second < 10 ? "0" : "";

	    	time += ph + hour + ":" + pm + minute + ":" + ps + second;
	    	return time;
		}
}
