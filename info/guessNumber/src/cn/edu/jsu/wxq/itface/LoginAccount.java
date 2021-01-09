package cn.edu.jsu.wxq.itface;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import cn.edu.jsu.wxq.dao.database.ConnectionDatabase;
import cn.edu.jsu.wxq.dao.database.SqlDao;
import cn.edu.jsu.wxq.user.User;

import javax.swing.JPasswordField;

public class LoginAccount extends JFrame {
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JButton btnNewButton;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginAccount frame = new LoginAccount();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginAccount() {
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setForeground(Color.WHITE);
		setResizable(false);
		setTitle("���ֲ�һ��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(720, 350, 527, 420);
		getContentPane().setLayout(null);
		
		String result = "";
		  Random ran=new Random();
		  for(int i = 0 ; i < 6 ; i ++)
		  {
		  int intVal =ran.nextInt (0+9);//�������0-9�������
		  result = result + intVal;
		  }
		
		JLabel lblNewLabel = new JLabel("�˺�:");
		lblNewLabel.setBounds(114, 117, 56, 34);
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {//�����û������������¼�����
			@Override 
			  public void keyPressed(KeyEvent e) {//�������̼�������
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//����ǻس���
			    	  passwordField.requestFocus();
			    }
			   }

		});
		textField_1.setBounds(172, 119, 220, 34);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("����:");
		lblNewLabel_1.setBounds(114, 164, 56, 39);
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 20));
		getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {//�����û������������¼�����
			@Override
			  public void keyPressed(KeyEvent e) {//�������̼�������
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//����ǻس���
			    	textField.requestFocus();
			   }
			}
		});
		passwordField.setBounds(172, 168, 220, 34);
		getContentPane().add(passwordField);
		
		JLabel label_2 = new JLabel("��֤��:");
		label_2.setFont(new Font("����", Font.PLAIN, 20));
		label_2.setBounds(96, 219, 73, 31);
		getContentPane().add(label_2);
		
		JLabel label_4 = new JLabel(""+result);
		label_4.setFont(new Font("����", Font.PLAIN, 23));
		label_4.setBounds(328, 215, 113, 35);
		getContentPane().add(label_4);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(LoginAccount.class.getResource("/img/QQ\u56FE\u724720210106210215.jpg")));
		label_3.setFont(new Font("����", Font.PLAIN, 20));
		label_3.setBounds(328, 219, 113, 31);
		getContentPane().add(label_3);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {//�����û������������¼�����
			@Override
			  public void keyPressed(KeyEvent e) {//�������̼�������
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//����ǻس���
			    	String account=textField_1.getText();
					String password=new String(passwordField.getPassword());
					if (!SqlDao.isExist(account)) {
						JOptionPane.showMessageDialog(null, "���˺Ų�����");//������ʾ����
						textField_1.setText("");
						passwordField.setText("");
						textField.setText("");
						textField_1.requestFocus();
					}
					else {
					User user=new User();
					try {
						user=SqlDao.selectUser(account);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					String password1=user.getPassword();
					if(password.equals(password1)&&textField.getText().equals(label_4.getText())) {
						JOptionPane.showMessageDialog(null, "��¼�ɹ�");//������ʾ����
						dispose();
						PersonalInterface personalInterface=new PersonalInterface(user);
						personalInterface.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "��֤����������");//������ʾ����
					passwordField.setText("");
					textField.setText("");
					passwordField.requestFocus();
					}
					}
			    }
			   }

		});
		textField.setBounds(172, 217, 142, 33);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_31 = new JLabel(""+result);
		label_31.setIcon(new ImageIcon(LoginAccount.class.getResource("/img/QQ\u56FE\u724720210106210215.jpg")));
		label_31.setFont(new Font("����", Font.PLAIN, 20));
		label_31.setBounds(328, 215, 113, 35);
		getContentPane().add(label_31);
		
		btnNewButton = new JButton("��¼");//��¼
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String account=textField_1.getText();
				String password=new String(passwordField.getPassword());
				if (!SqlDao.isExist(account)) {
					JOptionPane.showMessageDialog(null, "���˺Ų�����");//������ʾ����
					textField_1.setText("");
					passwordField.setText("");
					textField.setText("");
					textField_1.requestFocus();
				}
				else {
				User user=new User();
				try {
					user=SqlDao.selectUser(account);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String password1=user.getPassword();
				if(password.equals(password1)&&label_2.equals(label_31)) {
					JOptionPane.showMessageDialog(null, "��¼�ɹ�");//������ʾ����
					dispose();
					PersonalInterface personalInterface=new PersonalInterface(user);
					personalInterface.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "��֤����������");//������ʾ����
				passwordField.setText("");
				passwordField.requestFocus();
				}
				}
			}
		});

		btnNewButton.setFont(new Font("����", Font.PLAIN, 30));
		btnNewButton.setBounds(184, 288, 156, 44);
		getContentPane().add(btnNewButton);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		
		JLabel lblNewLabel_4 = new JLabel("���ֲ�һ��");
		lblNewLabel_4.setFont(new Font("����", Font.PLAIN, 60));
		lblNewLabel_4.setBounds(109, 13, 323, 115);
		getContentPane().add(lblNewLabel_4);
		
		JButton button_1 = new JButton("��������");
		button_1.setBounds(418, 338, 93, 34);
		getContentPane().add(button_1);
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		
		JButton button_2 = new JButton("����Ա��¼");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginAdministrator loginAdministrator=new LoginAdministrator();
				loginAdministrator.setVisible(true);
			}
		});
		button_2.setBounds(210, 254, 107, 28);
		getContentPane().add(button_2);
		button_2.setContentAreaFilled(false);//��ť͸��
		button_2.setBorderPainted(false);
		
		
		JButton button_4 = new JButton("ע���˺�");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Registered child01=new Registered();
				child01.setVisible(true);
			}
		});
		button_4.setBounds(14, 338, 113, 35);
		getContentPane().add(button_4);
		button_4.setContentAreaFilled(false);
		button_4.setBorderPainted(false);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(LoginAccount.class.getResource("/img/zzpic17012.jpg")));
		label_1.setBounds(184, 288, 156, 44);
		getContentPane().add(label_1);
	
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LoginAccount.class.getResource("/img/apic29455.jpg")));
		label.setBounds(0, -29, 521, 414);
		getContentPane().add(label);
		
	}
}
