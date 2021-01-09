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
	 * 游戏
	 * @param user the User用来传递用户信息
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
		
		welcomeJLabel = new JLabel("游戏规则:已随机生成一个数,猜猜它是几");
		welcomeJLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		welcomeJLabel.setBounds(78, 13, 383, 31);
		contentPane.add(welcomeJLabel);
		
		easy = new JRadioButton("简单模式:这是一个两位数噢");
		easy.setFont(new Font("楷体", Font.PLAIN, 18));
		easy.setBounds(78, 53, 270, 27);
		contentPane.add(easy);
		easy.setContentAreaFilled(false);
		easy.setBorderPainted(false);
		
		difficult = new JRadioButton("困难模式:这是一个三位数噢");
		difficult.setFont(new Font("楷体", Font.PLAIN, 18));
		difficult.setBounds(78, 85, 253, 27);
		contentPane.add(difficult);
		difficult.setContentAreaFilled(false);
		difficult.setBorderPainted(false);
		
		okButton = new JButton("确定");
		okButton.setFont(new Font("楷体", Font.PLAIN, 20));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(easy.isSelected()) {
					random=easy();
					N=2;
					JOptionPane.showMessageDialog(null, "已扣除50点积分,游戏开始");
					integral-=50;
				}
				else {
					random=difficult();
					JOptionPane.showMessageDialog(null, "已扣除100点积分,游戏开始");
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
		guessField.addKeyListener(new KeyAdapter() {//增加猜数字输入框键盘事件焦点
			@Override
			  public void keyPressed(KeyEvent e) {//监听键盘键入内容
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//如果是回车键
			    	int a=0,b=0;
			    	if(guessField.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "请输入数字");return;
			    	}
					else{
						//判断输入的是否是数字
						char[] c=guessField.getText().toCharArray();          //将字符串转换为字符数组
						for (int i=0;i<c.length;i++) {
							if (c[i]>='0'&&c[i]<='9')  a++;
							else b++;
						}
						if(a==c.length){
							if(c.length!=N){
								JOptionPane.showMessageDialog(null, "都说了是个"+N+"位数嘛");
								guessField.setText("");
								guessField.requestFocus(); 
							     a=0;b=0;
						         }
						    else {
					    		number=Integer.parseInt(guessField.getText().trim());
								if(number>random){
									JOptionPane.showMessageDialog(null, "哇这数太大了,重新再试试吧");
									guessField.setText("");
									++counter;
									guessField.requestFocus();                 //文本框获取焦点
								}
								else if(number<random){
									JOptionPane.showMessageDialog(null, "这好像比我的饭量还小呢，再来一次吧");
									++counter;
									guessField.setText("");
									guessField.requestFocus(); 
								}
								if(random==number){
									if(N==2) {
										if(counter==1) {
									      JOptionPane.showMessageDialog(null, "太棒了吧,请收下我的五朵鲜花吧\n"
											+"获得积分:"+(108-8*counter));
									       flower+=5;
										}
										else if(counter>2&&counter<=3) {
									    JOptionPane.showMessageDialog(null, "不错呀,奖励你三朵花花吧\n"
											+ "你一共猜了"+counter+"次\n"
											+"获得积分:"+(108-8*counter));flower+=3;
											}
										else if(counter<=5&&counter>=4) {
									          JOptionPane.showMessageDialog(null, "继续努力噢,送你一朵花\n"+ "你一共猜了"+counter+"次\n"
											+"获得积分:"+(108-8*counter));flower+=1;
									          }
										else {
											JOptionPane.showMessageDialog(null, "emmmm有些不太对劲呢\n"+"你一共猜了"+counter+"次\n"
											+"获得积分:"+(108-8*counter));
										}
										integral+=(108-8*counter);
										gameIntegral=108-8*counter;
									}
										else {
											if(counter<=1) {
											      JOptionPane.showMessageDialog(null, "太棒了吧,请收下我的五朵鲜花吧\n"
													+"获得积分:"+(515-15*counter));
											       flower+=5;
												}
												else if(counter>2&&counter<=3) {
											    JOptionPane.showMessageDialog(null, "不错呀,奖励你三朵花花吧\n"
													+ "你一共猜了"+counter+"次\n"
													+"获得积分:"+(515-15*counter));flower+=3;
													}
												else if(counter<=5&&counter>=4) {
											          JOptionPane.showMessageDialog(null, "继续努力噢,送你一朵花\n"+ "你一共猜了"+counter+"次\n"
													+"获得积分:"+(515-15*counter));flower+=1;
											          }
												else {
													JOptionPane.showMessageDialog(null, "emmmm有些不太对劲呢\n"+"你一共猜了"+counter+"次\n"
													+"获得积分:"+(515-15*counter));
												}
										integral+=(515-15*counter);
										gameIntegral=515-15*counter;
									}
									label_1.setText("本局积分:"+gameIntegral);
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
							JOptionPane.showMessageDialog(null, "嗨，请输入数字");
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
		
		returnButton = new JButton("返回主页");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				user.setFlower(flower);
				user.setIntegral(integral);
				PersonalInterface personalInterface=new PersonalInterface(user);
				personalInterface.setVisible(true);
			}
		});
		returnButton.setFont(new Font("楷体", Font.PLAIN, 20));
		returnButton.setBounds(340, 253, 134, 41);
		contentPane.add(returnButton);
		returnButton.setContentAreaFilled(false);
		returnButton.setBorderPainted(false);
		
		// again = new JButton("重新开始");
		 
		 
		lookIntegral = new JButton("查看积分");
		lookIntegral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "你当前的积分数为:"+integral);
			}
		});
		lookIntegral.setFont(new Font("楷体", Font.PLAIN, 20));
		lookIntegral.setBounds(35, 253, 134, 41);
		contentPane.add(lookIntegral);
		lookIntegral.setContentAreaFilled(false);
		lookIntegral.setBorderPainted(false);
		
		button = new JButton("查看答案");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Object[] options = {"确定", "取消"};
				 if(flower>=3) {
					  int i = JOptionPane.showOptionDialog(null, "你将花费三朵鲜花查看","查看答案提示框",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
						if(i == 0) {
							JOptionPane.showMessageDialog(null, "答案:"+random);
							flower-=3;
						}
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "你的鲜花好像不太够呢");
				 }
			}
		});
		button.setFont(new Font("楷体", Font.PLAIN, 20));
		button.setBounds(340, 218, 134, 41);
		contentPane.add(button);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		
		lookflower = new JButton("查看鲜花");
		lookflower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "你当前的鲜花数为:"+flower);
			}
		});
		lookflower.setFont(new Font("楷体", Font.PLAIN, 20));
		lookflower.setBounds(35, 218, 134, 41);
		contentPane.add(lookflower);
		lookflower.setContentAreaFilled(false);
		lookflower.setBorderPainted(false);
		
		label_2 = new JLabel("得分记录");
		label_2.setFont(new Font("楷体", Font.PLAIN, 20));
		label_2.setBounds(368, 107, 87, 27);
		contentPane.add(label_2);
		
		label_1 = new JLabel("本局积分:0");
		label_1.setFont(new Font("楷体", Font.PLAIN, 17));
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
