package cn.edu.jsu.wxq.itface;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DesktopPaneUI;

import cn.edu.jsu.wxq.operate.AllUser;
import cn.edu.jsu.wxq.operate.DelectSql;
import cn.edu.jsu.wxq.IO.ExcelToFile;
import cn.edu.jsu.wxq.IO.ExcelToSql;
import cn.edu.jsu.wxq.IO.MysqlExcel;
import cn.edu.jsu.wxq.operate.AllPlayRecord;
import cn.edu.jsu.wxq.operate.RankFlower;
import cn.edu.jsu.wxq.operate.RankIntegral;
import cn.edu.jsu.wxq.user.Music;
import cn.edu.jsu.wxq.user.User;

import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JDesktopPane;

public class Administrator extends JFrame {
	private String music;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 * @param args the String[]����Ա������
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrator frame = new Administrator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 *����Ա������
	 */
	public Administrator() {
		
		
		setTitle("����Ա");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 624, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("�鿴�û���Ϣ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllUser adm=new AllUser(); 
				adm.setVisible(true);
			}
		});
		button.setFont(new Font("����", Font.PLAIN, 20));
		button.setBounds(197, 39, 210, 42);
		contentPane.add(button);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		
		JButton button_2 = new JButton("�������ݵ�Excel��");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MysqlExcel mysqlExcel=new MysqlExcel();
				mysqlExcel.toWriteExcel();
				mysqlExcel.toWriteExcel1();
				JOptionPane.showMessageDialog(null, "�����ɹ�!\n"
						+ "user����E:\\���ֲ�һ��-�û���.xls\n"
						+ "record����E:\\���ֲ�һ��-��Ϸ��¼��.xls");//������ʾ����
			}
		});
		button_2.setFont(new Font("����", Font.PLAIN, 20));
		button_2.setBounds(42, 94, 210, 42);
		contentPane.add(button_2);
		button_2.setContentAreaFilled(false);
		button_2.setBorderPainted(false);
		
		JButton button_7 = new JButton("�鿴��Ϸ��¼");
		button_7.setFont(new Font("����", Font.PLAIN, 20));
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AllPlayRecord allPlayRecord=new AllPlayRecord();
				allPlayRecord.setVisible(true);
			}
		});
		button_7.setBounds(359, 94, 210, 42);
		contentPane.add(button_7);
		button_7.setContentAreaFilled(false);
		button_7.setBorderPainted(false);
		
		JButton button_3 = new JButton("�鿴�������а�");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name="��ѩ��";
				String account="123456789";
				String password="123456789";
				User user=new User(name,account,password,0,0);
				RankIntegral rankIntegral=new RankIntegral(user);
				rankIntegral.setVisible(true);
			}
		});
		button_3.setFont(new Font("����", Font.PLAIN, 20));
		button_3.setBounds(42, 194, 211, 42);
		contentPane.add(button_3);
		button_3.setContentAreaFilled(false);
		button_3.setBorderPainted(false);
		
		JButton button_4 = new JButton("�鿴�ʻ����а�");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name="��ѩ��";
				String account="123456789";
				String password="123456789";
				User user=new User(name,account,password,0,0);
				RankFlower rankFlower=new RankFlower(user);
				rankFlower.setVisible(true);
			}
		});
		button_4.setFont(new Font("����", Font.PLAIN, 20));
		button_4.setBounds(359, 194, 210, 42);
		contentPane.add(button_4);
		button_4.setContentAreaFilled(false);
		button_4.setBorderPainted(false);
		
		JButton button_5 = new JButton("��������");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���ֶ��߳̿�ʼ
				Object[] options = {"���ȵĳ���", "����"};
         	    int i = JOptionPane.showOptionDialog(null, "���ųɹ�","���ֲ��ſ�",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
					if(i == 0) {
						music="C:\\Users\\86135\\Music\\���ȵĳ���.mp3";
					}
					else {
						music="C:\\Users\\86135\\Music\\����.mp3";
					}
				// ���ֶ��߳̿�ʼ
				Music m1=new Music();
				m1.setFileName(music);//�ļ�·��
				Thread t1 = m1;
				t1.start();
			}
		});
		button_5.setFont(new Font("����", Font.PLAIN, 20));
		button_5.setBounds(42, 279, 210, 42);
		contentPane.add(button_5);
		button_5.setContentAreaFilled(false);
		button_5.setBorderPainted(false);
		
		JButton button_6 = new JButton("�û���¼");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			LoginAccount loginAccount=new LoginAccount();
			loginAccount.setVisible(true);
			}
		});
		button_6.setFont(new Font("����", Font.PLAIN, 20));
		button_6.setBounds(359, 279, 210, 42);
		contentPane.add(button_6);
		button_6.setContentAreaFilled(false);
		button_6.setBorderPainted(false);
		
		JButton btnExcel = new JButton("Excel->���ݿ�");
		btnExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ExcelToSql.getAllRecordByExcel();
			}
		});
		btnExcel.setFont(new Font("����", Font.PLAIN, 20));
		btnExcel.setBounds(186, 138, 221, 47);
		contentPane.add(btnExcel);
		btnExcel.setContentAreaFilled(false);
		btnExcel.setBorderPainted(false);
		
		JButton btnExcel_1 = new JButton("Excel->�ı��ļ�");
		btnExcel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"��Ϸ��¼��", "�û���"};
         	    int i = JOptionPane.showOptionDialog(null, "�����ĸ��ĵ�","ע����ʾ��",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
					if(i == 0) {
					ExcelToFile.getAllRecordByExcel();
					}
					else
						ExcelToFile.getAllRecordByExcel();
			}
		});
		btnExcel_1.setFont(new Font("����", Font.PLAIN, 20));
		btnExcel_1.setBounds(186, 237, 221, 47);
		contentPane.add(btnExcel_1);
		btnExcel_1.setContentAreaFilled(false);
		btnExcel_1.setBorderPainted(false);
		
		JButton btnS = new JButton("ɾ�����ݿ���Ϣ");
		btnS.setFont(new Font("����", Font.PLAIN, 20));
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelectSql delectSql=new DelectSql();
				delectSql.setVisible(true);
			}
		});
		btnS.setBounds(197, 316, 193, 42);
		contentPane.add(btnS);
		btnS.setContentAreaFilled(false);
		btnS.setBorderPainted(false);
		
		JLabel label_9 = new JLabel("");
		label_9.setIcon(new ImageIcon(Administrator.class.getResource("/img/zzpic17012.jpg")));
		label_9.setBounds(197, 138, 193, 47);
		contentPane.add(label_9);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Administrator.class.getResource("/img/QQ\u56FE\u724720210106210134.jpg")));
		label_1.setBounds(142, 27, 53, 54);
		contentPane.add(label_1);
		
		JLabel label_10 = new JLabel("");
		label_10.setIcon(new ImageIcon(Administrator.class.getResource("/img/zzpic17012.jpg")));
		label_10.setBounds(197, 237, 193, 42);
		contentPane.add(label_10);
		
		JLabel label1 = new JLabel("");
		label1.setIcon(new ImageIcon(Administrator.class.getResource("/img/QQ\u56FE\u724720210106210454.jpg")));
		label1.setBounds(411, 27, 66, 54);
		contentPane.add(label1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Administrator.class.getResource("/img/xpic7629.jpg")));
		label.setBounds(197, 39, 210, 42);
		contentPane.add(label);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Administrator.class.getResource("/img/xpic7629.jpg")));
		label_2.setBounds(42, 94, 210, 42);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(Administrator.class.getResource("/img/xpic7629.jpg")));
		label_3.setBounds(359, 94, 210, 42);
		contentPane.add(label_3);
		
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(Administrator.class.getResource("/img/xpic7629.jpg")));
		label_4.setBounds(42, 194, 210, 42);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(Administrator.class.getResource("/img/xpic7629.jpg")));
		label_5.setBounds(359, 194, 210, 42);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon(Administrator.class.getResource("/img/xpic7629.jpg")));
		label_6.setBounds(42, 279, 210, 42);
		contentPane.add(label_6);
		
		JLabel label_12 = new JLabel("");
		label_12.setIcon(new ImageIcon(Administrator.class.getResource("/img/zzpic17012.jpg")));
		label_12.setBounds(197, 316, 193, 42);
		contentPane.add(label_12);
		
		JLabel label_7 = new JLabel("");
		label_7.setIcon(new ImageIcon(Administrator.class.getResource("/img/xpic7629.jpg")));
		label_7.setBounds(359, 279, 210, 42);
		contentPane.add(label_7);
		
		JLabel label_11 = new JLabel("");
		label_11.setIcon(new ImageIcon(Administrator.class.getResource("/img/2.jpg")));
		label_11.setBounds(0, 66, 606, 292);
		contentPane.add(label_11);
		
		JLabel label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon(Administrator.class.getResource("/img/7.jpg")));
		label_8.setBounds(0, -40, 616, 398);
		contentPane.add(label_8);
		
	}
}
