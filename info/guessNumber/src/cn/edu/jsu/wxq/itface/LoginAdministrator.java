package cn.edu.jsu.wxq.itface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class LoginAdministrator extends JFrame {
	private JButton btnNewButton;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JTextField accountField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginAdministrator frame = new LoginAdministrator();
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
	public LoginAdministrator() {
		
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setForeground(Color.WHITE);
		setResizable(false);
		setTitle("数字猜一猜");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(720, 350, 527, 420);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("用户名:");
		lblNewLabel.setBounds(99, 107, 81, 34);
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {//增加用户名输入框键盘事件焦点
			@Override
			  public void keyPressed(KeyEvent e) {//监听键盘键入内容
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//如果是回车键
			    	accountField.requestFocus();
			    }
			   }

		});
		textField.setBounds(184, 109, 220, 34);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel accountLabel = new JLabel("账号:");
		accountLabel.setBounds(124, 152, 56, 34);
		accountLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		getContentPane().add(accountLabel);
		
		accountField = new JTextField();
		accountField.addKeyListener(new KeyAdapter() {//增加用户名输入框键盘事件焦点
			@Override
			  public void keyPressed(KeyEvent e) {//监听键盘键入内容
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//如果是回车键
			    	  passwordField.requestFocus();
			    }
			   }

		});
		accountField.setBounds(184, 154, 220, 34);
		getContentPane().add(accountField);
		accountField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("密码:");
		lblNewLabel_1.setBounds(124, 197, 56, 39);
		lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 20));
		getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {//增加用户名输入框键盘事件焦点
			@Override
			  public void keyPressed(KeyEvent e) {//监听键盘键入内容
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//如果是回车键
			    	String name=textField.getText();
			    	String account=accountField.getText();
					String password=new String(passwordField.getPassword());
					if(name.equals("文雪茜")&&account.equals("123456789")&&password.equals("123456789")) {
						JOptionPane.showMessageDialog(null, "登陆成功");//弹出提示窗口
						dispose();
						Administrator administrator=new Administrator();///////////////////////////////////////////////////////////
						administrator.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "用户名或密码错误");//弹出提示窗口
						textField.setText("");
						passwordField.setText("");
						textField.requestFocus();
					}
			    }
			   }

		});
		passwordField.setBounds(184, 201, 220, 34);
		getContentPane().add(passwordField);
		
		btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=textField.getText();
				String password=new String(passwordField.getPassword());
				if(name.equals("文雪茜")&&password.equals("123456789")) {
					JOptionPane.showMessageDialog(null, "登陆成功");//弹出提示窗口
					dispose();
					Administrator administrator=new Administrator();///////////////////////////////////////////////////////////
					administrator.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误");//弹出提示窗口
				}
			}
		});
		btnNewButton.setFont(new Font("等线", Font.PLAIN, 30));
		btnNewButton.setBounds(184, 288, 156, 44);
		getContentPane().add(btnNewButton);     
		
		JLabel lblNewLabel_4 = new JLabel("数字猜一猜");
		lblNewLabel_4.setFont(new Font("楷体", Font.PLAIN, 60));
		lblNewLabel_4.setBounds(109, 13, 323, 115);
		getContentPane().add(lblNewLabel_4);
		
		JButton button_2 = new JButton("返回用户登录");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				LoginAccount loginAccount=new LoginAccount();
				loginAccount.setVisible(true);
			}
		});
		button_2.setBounds(184, 250, 156, 25);
		getContentPane().add(button_2);
		button_2.setContentAreaFilled(false);
		button_2.setBorderPainted(false);
		
		
		JLabel label =  new JLabel("");
		label.setIcon(new ImageIcon(LoginAccount.class.getResource("/img/apic29455.jpg")));
		label.setBounds(0, -29, 521, 414);
		getContentPane().add(label);
	
		
		
	}

}
