package cn.edu.jsu.wxq.itface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.jsu.wxq.dao.database.SqlIInfo;
import cn.edu.jsu.wxq.IO.FileOperate;
import cn.edu.jsu.wxq.dao.database.ConnectionDatabase;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;

public class Registered extends JDialog {

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/** 
	 * 注册.
	 * @param args the String[]注册
	 */
	public static void main(String[] args) {
		try {
			Registered dialog = new Registered();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 注册.
	 */
	public Registered() {
		setTitle("数字猜一猜-注册");
		setBounds(720, 350, 500, 420);
		setModal(true);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("新用户注册");
		lblNewLabel.setFont(new Font("仿宋", Font.PLAIN, 30));
		lblNewLabel.setBounds(155, 26, 150, 42);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("用户名:");
		lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(99, 93, 59, 36);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {//增加用户名输入框键盘事件焦点
			@Override
			  public void keyPressed(KeyEvent e) {//监听键盘键入内容
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//如果是回车键
			    	  textField_1.requestFocus();
			    }
			   }

		});
		textField.setBounds(167, 99, 164, 24);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("账号:");
		lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(115, 130, 43, 36);
		getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {//增加用户名输入框键盘事件焦点
			@Override
			  public void keyPressed(KeyEvent e) {//监听键盘键入内容
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//如果是回车键
			    	  textField_2.requestFocus();
			    }
			   }

		});
		textField_1.setBounds(167, 136, 164, 24);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("密码:");
		lblNewLabel_3.setFont(new Font("楷体", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(115, 167, 43, 36);
		getContentPane().add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {//增加用户名输入框键盘事件焦点
			@Override
			  public void keyPressed(KeyEvent e) {//监听键盘键入内容
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//如果是回车键
			    	  textField_3.requestFocus();
			    }
			   }

		});
		textField_2.setBounds(167, 173, 164, 24);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("确认密码:");
		lblNewLabel_4.setFont(new Font("楷体", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(81, 206, 77, 36);
		getContentPane().add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {//增加用户名输入框键盘事件焦点
			@Override
			  public void keyPressed(KeyEvent e) {//监听键盘键入内容
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//如果是回车键
			    	String name=textField.getText();
					String password1=textField_2.getText();
					String account=textField_1.getText();
					String password2=textField_3.getText();
					
					if(!SqlIInfo.isAccount(account)||!SqlIInfo.isPassword(password1)) {
						JOptionPane.showMessageDialog(null, "账号或密码不合法");//弹出提示窗口
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textField.requestFocus();
					}
					else {
					if(!SqlIInfo.isSame(password1, password2)) {
							JOptionPane.showMessageDialog(null, "两次密码不一致,请重新输入");//弹出提示窗口
							textField_3.setText("");
							textField_2.setText("");
							textField_2.requestFocus();
						}
					//判断数据库是否已存在该账号
					else if(!SqlIInfo.isExist(account)) {
						 String[] str = new String[] {name, account, password1};
						 int[] str1= {0,10};
						 String sql="insert into user(name,account,password,integral,flower) values(?,?,?,?,?)";
						 SqlIInfo.addUser(sql,str,str1);
						 String user=name+"\t"+account+"\t"+password1+"0\t10\r\n";
						 FileOperate.addUser(user);
						 //注册成提示界面
	             	    Object[] options = {"登录", "退出"};
	             	    int i = JOptionPane.showOptionDialog(null, "注册成功！","注册提示框",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
							if(i == 0) {
								dispose();
								LoginAccount loginaccount=new LoginAccount();
								loginaccount.setVisible(true);
							}
							else
								dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "该账号已存在");//弹出提示窗口
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textField.requestFocus();
					} 
			    }
			    }
			 }
		});
		textField_3.setBounds(167, 212, 164, 24);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("注册");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=textField.getText();
				String password1=textField_2.getText();
				String account=textField_1.getText();
				String password2=textField_3.getText();
				//连接数据库
				if(!SqlIInfo.isAccount(account)||!SqlIInfo.isPassword(password1)) {
					JOptionPane.showMessageDialog(null, "账号或密码不合法");//弹出提示窗口
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField.requestFocus();
				}
				else {
				if(!SqlIInfo.isSame(password1, password2)) {
						JOptionPane.showMessageDialog(null, "两次密码不一致,请重新输入");//弹出提示窗口
						textField_3.setText("");
						textField_2.setText("");
						textField_2.requestFocus();
					}
				//判断数据库是否已存在该账号
				else if(!SqlIInfo.isExist(account)) {
					 String[] str = new String[] {name, account, password1};
					 int []str1= {0,10};
					 String sql="insert into user(name,account,password,integral,flower) values(?,?,?,?,?)";
					 SqlIInfo.addUser(sql,str,str1);
					 String user=name+"\t"+account+"\t"+password1+"0\t10\r\n";
					 FileOperate.addUser(user);
					 //注册成提示界面
             	    Object[] options = {"登录", "退出"};
             	    int i = JOptionPane.showOptionDialog(null, "注册成功！","注册提示框",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
						if(i == 0) {
							dispose();
							LoginAccount loginaccount=new LoginAccount();
							loginaccount.setVisible(true);
						}
						else
							dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "该账号已存在");//弹出提示窗口
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField.requestFocus();
				}
				}
				
			}
		});
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		
		JButton button = new JButton("返回登录");
		button.setFont(new Font("楷体", Font.PLAIN, 18));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginAccount loginaccount=new LoginAccount();
				loginaccount.setVisible(true);
			}
		});
		button.setBounds(177, 252, 113, 27);
		getContentPane().add(button);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
	
		
		
		btnNewButton.setBounds(155, 292, 164, 51);
		getContentPane().add(btnNewButton);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Registered.class.getResource("/img/zzpic17012.jpg")));
		label_1.setBounds(155, 292, 164, 51);
		getContentPane().add(label_1);
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Registered.class.getResource("/img/apic29455.jpg")));
		label.setBounds(-11, -36, 493, 409);
		getContentPane().add(label);
		
		
	}


}

