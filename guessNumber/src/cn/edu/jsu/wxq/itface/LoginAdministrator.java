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
		setTitle("���ֲ�һ��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(720, 350, 527, 420);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("�û���:");
		lblNewLabel.setBounds(99, 107, 81, 34);
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {//�����û������������¼�����
			@Override
			  public void keyPressed(KeyEvent e) {//�������̼�������
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//����ǻس���
			    	accountField.requestFocus();
			    }
			   }

		});
		textField.setBounds(184, 109, 220, 34);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel accountLabel = new JLabel("�˺�:");
		accountLabel.setBounds(124, 152, 56, 34);
		accountLabel.setFont(new Font("����", Font.PLAIN, 20));
		getContentPane().add(accountLabel);
		
		accountField = new JTextField();
		accountField.addKeyListener(new KeyAdapter() {//�����û������������¼�����
			@Override
			  public void keyPressed(KeyEvent e) {//�������̼�������
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//����ǻس���
			    	  passwordField.requestFocus();
			    }
			   }

		});
		accountField.setBounds(184, 154, 220, 34);
		getContentPane().add(accountField);
		accountField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("����:");
		lblNewLabel_1.setBounds(124, 197, 56, 39);
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 20));
		getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {//�����û������������¼�����
			@Override
			  public void keyPressed(KeyEvent e) {//�������̼�������
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//����ǻس���
			    	String name=textField.getText();
			    	String account=accountField.getText();
					String password=new String(passwordField.getPassword());
					if(name.equals("��ѩ��")&&account.equals("123456789")&&password.equals("123456789")) {
						JOptionPane.showMessageDialog(null, "��½�ɹ�");//������ʾ����
						dispose();
						Administrator administrator=new Administrator();///////////////////////////////////////////////////////////
						administrator.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "�û������������");//������ʾ����
						textField.setText("");
						passwordField.setText("");
						textField.requestFocus();
					}
			    }
			   }

		});
		passwordField.setBounds(184, 201, 220, 34);
		getContentPane().add(passwordField);
		
		btnNewButton = new JButton("��¼");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=textField.getText();
				String password=new String(passwordField.getPassword());
				if(name.equals("��ѩ��")&&password.equals("123456789")) {
					JOptionPane.showMessageDialog(null, "��½�ɹ�");//������ʾ����
					dispose();
					Administrator administrator=new Administrator();///////////////////////////////////////////////////////////
					administrator.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "�û������������");//������ʾ����
				}
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 30));
		btnNewButton.setBounds(184, 288, 156, 44);
		getContentPane().add(btnNewButton);     
		
		JLabel lblNewLabel_4 = new JLabel("���ֲ�һ��");
		lblNewLabel_4.setFont(new Font("����", Font.PLAIN, 60));
		lblNewLabel_4.setBounds(109, 13, 323, 115);
		getContentPane().add(lblNewLabel_4);
		
		JButton button_2 = new JButton("�����û���¼");
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
