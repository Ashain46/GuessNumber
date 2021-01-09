package cn.edu.jsu.wxq.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.jsu.wxq.dao.database.DatabaseConnection;
import cn.edu.jsu.wxq.dao.database.SqlIInfo;
import cn.edu.jsu.wxq.operate.ModifyInformation;
import cn.edu.jsu.wxq.operate.PlayRecord;
import cn.edu.jsu.wxq.operate.RankFlower;
import cn.edu.jsu.wxq.operate.RankIntegral;
import cn.edu.jsu.wxq.user.Music;
import cn.edu.jsu.wxq.user.User;

import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PersonalInterface extends JFrame {
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonalInterface frame = new PersonalInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * 用户登录进入后,个人界面
	 * @param user the User用户
	 *  
	 */
	
	public PersonalInterface(User user){
		// 音乐多线程开始
		Music m1=new Music();
		m1.setFileName("C:\\Users\\86135\\Music\\大鱼.mp3");//文件路径
		Thread t1 = m1;
		t1.start();
		getContentPane().setForeground(Color.ORANGE);
		setTitle("数字猜一猜-主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(720, 350, 515, 387);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel(user.getName());
		label.setIcon(new ImageIcon(PersonalInterface.class.getResource("/img/\u5FAE\u7B11\u8138.png")));
		label.setFont(new Font("楷体", Font.PLAIN, 20));
		label.setForeground(Color.BLACK);
		label.setBounds(0, 13, 149, 37);
		getContentPane().add(label);
		
		JLabel label_2 = new JLabel(user.getIntegral()+"");
		label_2.setIcon(new ImageIcon(PersonalInterface.class.getResource("/img/\u5976\u74F6.png")));
		label_2.setFont(new Font("楷体", Font.PLAIN, 20));
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setBounds(229, 13, 122, 37);
		getContentPane().add(label_2);
		
		JLabel label_4 = new JLabel(user.getFlower()+"");
		label_4.setIcon(new ImageIcon(PersonalInterface.class.getResource("/img/\u9C9C\u82B1.png")));
		label_4.setFont(new Font("楷体", Font.PLAIN, 20));
		label_4.setForeground(Color.DARK_GRAY);
		label_4.setBounds(375, 13, 122, 37);
		getContentPane().add(label_4);
		
		JButton btnNewButton = new JButton("开始游戏");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				GameBegin gameTest=new GameBegin(user);
				gameTest.setVisible(true);
			}
		});
		btnNewButton.setForeground(new Color(178, 34, 34));
		btnNewButton.setFont(new Font("楷体", Font.PLAIN, 30));
		btnNewButton.setBounds(169, 65, 160, 55);
		getContentPane().add(btnNewButton);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		
		JButton btnNewButton_1 = new JButton("积分排行榜");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RankIntegral rankIntegral=new RankIntegral(user);
				rankIntegral.setVisible(true);
			}
		});
		btnNewButton_1.setForeground(new Color(255, 127, 80));
		btnNewButton_1.setFont(new Font("楷体", Font.PLAIN, 25));
		btnNewButton_1.setBounds(169, 116, 160, 55);
		getContentPane().add(btnNewButton_1);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		
		JButton btnNewButton_2 = new JButton("鲜花排行榜");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RankFlower rankFlower=new RankFlower(user);
				rankFlower.setVisible(true);
			}
		});
		btnNewButton_2.setForeground(new Color(255, 127, 80));
		btnNewButton_2.setFont(new Font("楷体", Font.PLAIN, 25));
		btnNewButton_2.setBounds(173, 168, 160, 55);
		getContentPane().add(btnNewButton_2);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBorderPainted(false);
		
		JButton button = new JButton("历史记录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PlayRecord playRecord=new PlayRecord(user.getName());
				playRecord.setVisible(true);
			}
		});
		button.setFont(new Font("楷体", Font.PLAIN, 25));
		button.setBounds(169, 227, 160, 49);
		getContentPane().add(button);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		
		JButton btnNewButton_3 = new JButton("");//修改资料
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifyInformation modifyInformation=new ModifyInformation(user);
				modifyInformation.setVisible(true);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(PersonalInterface.class.getResource("/img/QQ\u56FE\u724720210106210116.gif")));
		btnNewButton_3.setForeground(new Color(139, 0, 0));
		btnNewButton_3.setFont(new Font("楷体", Font.PLAIN, 30));
		btnNewButton_3.setBounds(72, 236, 83, 40);
		getContentPane().add(btnNewButton_3);
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorderPainted(false);
		
		JButton btnNewButton_4 = new JButton("");//退出登录
		btnNewButton_4.setIcon(new ImageIcon(PersonalInterface.class.getResource("/img/QQ\u56FE\u724720210106210235.png")));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				LoginAccount loginAccount=new LoginAccount();
				loginAccount.setVisible(true);
			}
		});
		btnNewButton_4.setForeground(new Color(139, 0, 0));
		btnNewButton_4.setFont(new Font("楷体", Font.PLAIN, 30));
		btnNewButton_4.setBounds(349, 236, 83, 40);
		getContentPane().add(btnNewButton_4);
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4.setBorderPainted(false);
		
		JButton button_1 = new JButton("刷新");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				PersonalInterface personalInterface=new PersonalInterface(new ModifyInformation(user).getUser());
				personalInterface.setVisible(true);
			}
		});
		button_1.setFont(new Font("楷体", Font.PLAIN, 20));
		button_1.setBounds(384, 300, 113, 27);
		getContentPane().add(button_1);
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(PersonalInterface.class.getResource("/img/zzpic17012.jpg")));
		label_3.setBounds(72, 63, 159, 57);
		getContentPane().add(label_3);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(PersonalInterface.class.getResource("/img/zzpic17012.jpg")));
		label_1.setBounds(229, 63, 203, 57);
		getContentPane().add(label_1);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(PersonalInterface.class.getResource("/img/zzpic17012.jpg")));
		label_5.setBounds(72, 116, 203, 55);
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon(PersonalInterface.class.getResource("/img/zzpic17012.jpg")));
		label_6.setBounds(273, 116, 159, 55);
		getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("");
		label_7.setIcon(new ImageIcon(PersonalInterface.class.getResource("/img/zzpic17012.jpg")));
		label_7.setBounds(72, 168, 360, 55);
		getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon(PersonalInterface.class.getResource("/img/zzpic17012.jpg")));
		label_8.setBounds(72, 221, 360, 55);
		getContentPane().add(label_8);
		
		JLabel label_10 = new JLabel("");
		label_10.setIcon(new ImageIcon(PersonalInterface.class.getResource("/img/14.jpg")));
		label_10.setBounds(0, -18, 497, 376);
		getContentPane().add(label_10);
		
	}
}
