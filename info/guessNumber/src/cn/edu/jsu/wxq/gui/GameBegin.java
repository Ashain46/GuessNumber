package cn.edu.jsu.wxq.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.css.ElementCSSInlineStyle;

import com.mysql.cj.xdevapi.Table;

import cn.edu.jsu.wxq.dao.database.DatabaseConnection;
import cn.edu.jsu.wxq.dao.database.SqlIInfo;
import cn.edu.jsu.wxq.user.Record;
import cn.edu.jsu.wxq.user.User;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;

public class GameBegin extends JFrame {
	int number=0,random,counter=1;
	JLabel welcomeJLabel;
	JTextField guessField;
	JButton returnButton;
	JButton lookIntegral;
	JButton lookflower;
	JRadioButton difficult;
	JRadioButton easy;
	JButton okButton;
	int N=0;
	int a=0,b=0;
	private JPanel contentPane;
	private JTextField textField;
	private JButton button;
	
	int gameIntegral;
	int integral;
	int flower;
	private JLabel label_2;
	private JLabel label_1;
	private JLabel label_3;


	/**
	 * ��Ϸ
	 * @param user the User���������û���Ϣ
	 */
	public GameBegin(User user) {
		integral=user.getIntegral();
		flower=user.getFlower();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(710, 450, 506, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		welcomeJLabel = new JLabel("��Ϸ����:���������һ����,�²����Ǽ�");
		welcomeJLabel.setFont(new Font("����", Font.PLAIN, 20));
		welcomeJLabel.setBounds(78, 13, 383, 31);
		contentPane.add(welcomeJLabel);
		
		easy = new JRadioButton("��ģʽ:����һ����λ����");
		easy.setFont(new Font("����", Font.PLAIN, 18));
		easy.setBounds(78, 53, 270, 27);
		contentPane.add(easy);
		easy.setContentAreaFilled(false);
		easy.setBorderPainted(false);
		
		difficult = new JRadioButton("����ģʽ:����һ����λ����");
		difficult.setFont(new Font("����", Font.PLAIN, 18));
		difficult.setBounds(78, 85, 253, 27);
		contentPane.add(difficult);
		difficult.setContentAreaFilled(false);
		difficult.setBorderPainted(false);
		
		okButton = new JButton("ȷ��");
		okButton.setFont(new Font("����", Font.PLAIN, 20));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(easy.isSelected()) {
					random=easy();
					N=2;
					JOptionPane.showMessageDialog(null, "�ѿ۳�50�����,��Ϸ��ʼ");
					integral-=50;
				}
				else {
					random=difficult();
					JOptionPane.showMessageDialog(null, "�ѿ۳�100�����,��Ϸ��ʼ");
					integral-=100;
					N=3;
				}
				guessField.requestFocus(); 
			}
		});
		okButton.setBounds(155, 123, 113, 41);
		contentPane.add(okButton);
		okButton.setContentAreaFilled(false);
		okButton.setBorderPainted(false);
		
		guessField = new JTextField("");
		guessField.addKeyListener(new KeyAdapter() {//���Ӳ��������������¼�����
			@Override
			  public void keyPressed(KeyEvent e) {//�������̼�������
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//����ǻس���
			    	int a=0,b=0;
			    	if(guessField.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "����������");return;
			    	}
					else{
						//�ж�������Ƿ�������
						char[] c=guessField.getText().toCharArray();          //���ַ���ת��Ϊ�ַ�����
						for (int i=0;i<c.length;i++) {
							if (c[i]>='0'&&c[i]<='9')  a++;
							else b++;
						}
						if(a==c.length){
							if(c.length!=N){
								JOptionPane.showMessageDialog(null, "��˵���Ǹ�"+N+"λ����");
								guessField.setText("");
								guessField.requestFocus(); 
							     a=0;b=0;
						         }
						    else {
					    		number=Integer.parseInt(guessField.getText().trim());
								if(number>random){
									JOptionPane.showMessageDialog(null, "������̫����,���������԰�");
									guessField.setText("");
									++counter;
									guessField.requestFocus();                 //�ı����ȡ����
								}
								else if(number<random){
									JOptionPane.showMessageDialog(null, "�������ҵķ�����С�أ�����һ�ΰ�");
									++counter;
									guessField.setText("");
									guessField.requestFocus(); 
								}
								if(random==number){
									if(N==2) {
										if(counter==1) {
									      JOptionPane.showMessageDialog(null, "̫���˰�,�������ҵ�����ʻ���\n"
											+"��û���:"+(108-8*counter));
									       flower+=5;
										}
										else if(counter>2&&counter<=3) {
									    JOptionPane.showMessageDialog(null, "����ѽ,���������仨����\n"
											+ "��һ������"+counter+"��\n"
											+"��û���:"+(108-8*counter));flower+=3;
											}
										else if(counter<=5&&counter>=4) {
									          JOptionPane.showMessageDialog(null, "����Ŭ����,����һ�仨\n"+ "��һ������"+counter+"��\n"
											+"��û���:"+(108-8*counter));flower+=1;
									          }
										else {
											JOptionPane.showMessageDialog(null, "emmmm��Щ��̫�Ծ���\n"+"��һ������"+counter+"��\n"
											+"��û���:"+(108-8*counter));
										}
										integral+=(108-8*counter);
										gameIntegral=108-8*counter;
									}
										else {
											if(counter<=1) {
											      JOptionPane.showMessageDialog(null, "̫���˰�,�������ҵ�����ʻ���\n"
													+"��û���:"+(515-15*counter));
											       flower+=5;
												}
												else if(counter>2&&counter<=3) {
											    JOptionPane.showMessageDialog(null, "����ѽ,���������仨����\n"
													+ "��һ������"+counter+"��\n"
													+"��û���:"+(515-15*counter));flower+=3;
													}
												else if(counter<=5&&counter>=4) {
											          JOptionPane.showMessageDialog(null, "����Ŭ����,����һ�仨\n"+ "��һ������"+counter+"��\n"
													+"��û���:"+(515-15*counter));flower+=1;
											          }
												else {
													JOptionPane.showMessageDialog(null, "emmmm��Щ��̫�Ծ���\n"+"��һ������"+counter+"��\n"
													+"��û���:"+(515-15*counter));
												}
										integral+=(515-15*counter);
										gameIntegral=515-15*counter;
									}
									label_1.setText("���ֻ���:"+gameIntegral);
									int []str1= {integral,flower};
									String[] str= {user.getAccount()};
									SqlIInfo.modifyUser(str1, str);
									String sql="insert into record(name,integral,playtime) values(?,?,?)";
									SqlIInfo.addRecord(sql,user.getName(), gameIntegral,Record.getTime());
									counter=1;
									guessField.setText("");
									guessField.requestFocus(); 
								}
						   }
					     }
					
						else{
							JOptionPane.showMessageDialog(null, "�ˣ�����������");
							guessField.setText("");
							guessField.requestFocus(); 
							a=0;b=0;
						}
			      }
			   } 
			}
		});
		guessField.setBounds(174, 177, 164, 31);
		contentPane.add(guessField);
		guessField.setColumns(10);
		
		returnButton = new JButton("������ҳ");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				user.setFlower(flower);
				user.setIntegral(integral);
				PersonalInterface personalInterface=new PersonalInterface(user);
				personalInterface.setVisible(true);
			}
		});
		returnButton.setFont(new Font("����", Font.PLAIN, 20));
		returnButton.setBounds(340, 253, 134, 41);
		contentPane.add(returnButton);
		returnButton.setContentAreaFilled(false);
		returnButton.setBorderPainted(false);
		
		// again = new JButton("���¿�ʼ");
		 
		 
		lookIntegral = new JButton("�鿴����");
		lookIntegral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "�㵱ǰ�Ļ�����Ϊ:"+integral);
			}
		});
		lookIntegral.setFont(new Font("����", Font.PLAIN, 20));
		lookIntegral.setBounds(35, 253, 134, 41);
		contentPane.add(lookIntegral);
		lookIntegral.setContentAreaFilled(false);
		lookIntegral.setBorderPainted(false);
		
		button = new JButton("�鿴��");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Object[] options = {"ȷ��", "ȡ��"};
				 if(flower>=3) {
					  int i = JOptionPane.showOptionDialog(null, "�㽫���������ʻ��鿴","�鿴����ʾ��",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
						if(i == 0) {
							JOptionPane.showMessageDialog(null, "��:"+random);
							flower-=3;
						}
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "����ʻ�����̫����");
				 }
			}
		});
		button.setFont(new Font("����", Font.PLAIN, 20));
		button.setBounds(340, 218, 134, 41);
		contentPane.add(button);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		
		lookflower = new JButton("�鿴�ʻ�");
		lookflower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "�㵱ǰ���ʻ���Ϊ:"+flower);
			}
		});
		lookflower.setFont(new Font("����", Font.PLAIN, 20));
		lookflower.setBounds(35, 218, 134, 41);
		contentPane.add(lookflower);
		lookflower.setContentAreaFilled(false);
		lookflower.setBorderPainted(false);
		
		label_2 = new JLabel("�÷ּ�¼");
		label_2.setFont(new Font("����", Font.PLAIN, 20));
		label_2.setBounds(368, 107, 87, 27);
		contentPane.add(label_2);
		
		label_1 = new JLabel("���ֻ���:0");
		label_1.setFont(new Font("����", Font.PLAIN, 17));
		label_1.setBounds(354, 137, 134, 27);
		contentPane.add(label_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GameBegin.class.getResource("/img/QQ\u56FE\u724720210106210454.jpg")));
		label.setBounds(222, 221, 76, 80);
		contentPane.add(label);
		
		label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(GameBegin.class.getResource("/img/6.jpg")));
		label_3.setBounds(-42, -38, 540, 351);
		contentPane.add(label_3);
	}
	public int easy(){
		Random random=new Random();
		int n=random.nextInt(100);
		while(n<10){
			n=random.nextInt(100);
		}
		return n;
	}
	public int difficult(){
		Random random=new Random();
		int n=random.nextInt(1000);
		while(n<100){
			n=random.nextInt(1000);
		}
		return n;
	}
}
