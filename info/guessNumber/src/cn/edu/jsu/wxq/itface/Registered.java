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
	 * ע��.
	 * @param args the String[]ע��
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
	 * ע��.
	 */
	public Registered() {
		setTitle("���ֲ�һ��-ע��");
		setBounds(720, 350, 500, 420);
		setModal(true);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("���û�ע��");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 30));
		lblNewLabel.setBounds(155, 26, 150, 42);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("�û���:");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(99, 93, 59, 36);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {//�����û������������¼�����
			@Override
			  public void keyPressed(KeyEvent e) {//�������̼�������
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//����ǻس���
			    	  textField_1.requestFocus();
			    }
			   }

		});
		textField.setBounds(167, 99, 164, 24);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("�˺�:");
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(115, 130, 43, 36);
		getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {//�����û������������¼�����
			@Override
			  public void keyPressed(KeyEvent e) {//�������̼�������
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//����ǻس���
			    	  textField_2.requestFocus();
			    }
			   }

		});
		textField_1.setBounds(167, 136, 164, 24);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("����:");
		lblNewLabel_3.setFont(new Font("����", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(115, 167, 43, 36);
		getContentPane().add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {//�����û������������¼�����
			@Override
			  public void keyPressed(KeyEvent e) {//�������̼�������
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//����ǻس���
			    	  textField_3.requestFocus();
			    }
			   }

		});
		textField_2.setBounds(167, 173, 164, 24);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("ȷ������:");
		lblNewLabel_4.setFont(new Font("����", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(81, 206, 77, 36);
		getContentPane().add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {//�����û������������¼�����
			@Override
			  public void keyPressed(KeyEvent e) {//�������̼�������
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//����ǻس���
			    	String name=textField.getText();
					String password1=textField_2.getText();
					String account=textField_1.getText();
					String password2=textField_3.getText();
					
					if(!SqlIInfo.isAccount(account)||!SqlIInfo.isPassword(password1)) {
						JOptionPane.showMessageDialog(null, "�˺Ż����벻�Ϸ�");//������ʾ����
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textField.requestFocus();
					}
					else {
					if(!SqlIInfo.isSame(password1, password2)) {
							JOptionPane.showMessageDialog(null, "�������벻һ��,����������");//������ʾ����
							textField_3.setText("");
							textField_2.setText("");
							textField_2.requestFocus();
						}
					//�ж����ݿ��Ƿ��Ѵ��ڸ��˺�
					else if(!SqlIInfo.isExist(account)) {
						 String[] str = new String[] {name, account, password1};
						 int[] str1= {0,10};
						 String sql="insert into user(name,account,password,integral,flower) values(?,?,?,?,?)";
						 SqlIInfo.addUser(sql,str,str1);
						 String user=name+"\t"+account+"\t"+password1+"0\t10\r\n";
						 FileOperate.addUser(user);
						 //ע�����ʾ����
	             	    Object[] options = {"��¼", "�˳�"};
	             	    int i = JOptionPane.showOptionDialog(null, "ע��ɹ���","ע����ʾ��",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
							if(i == 0) {
								dispose();
								LoginAccount loginaccount=new LoginAccount();
								loginaccount.setVisible(true);
							}
							else
								dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "���˺��Ѵ���");//������ʾ����
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
		
		JButton btnNewButton = new JButton("ע��");
		btnNewButton.setFont(new Font("����", Font.PLAIN, 30));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=textField.getText();
				String password1=textField_2.getText();
				String account=textField_1.getText();
				String password2=textField_3.getText();
				//�������ݿ�
				if(!SqlIInfo.isAccount(account)||!SqlIInfo.isPassword(password1)) {
					JOptionPane.showMessageDialog(null, "�˺Ż����벻�Ϸ�");//������ʾ����
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField.requestFocus();
				}
				else {
				if(!SqlIInfo.isSame(password1, password2)) {
						JOptionPane.showMessageDialog(null, "�������벻һ��,����������");//������ʾ����
						textField_3.setText("");
						textField_2.setText("");
						textField_2.requestFocus();
					}
				//�ж����ݿ��Ƿ��Ѵ��ڸ��˺�
				else if(!SqlIInfo.isExist(account)) {
					 String[] str = new String[] {name, account, password1};
					 int []str1= {0,10};
					 String sql="insert into user(name,account,password,integral,flower) values(?,?,?,?,?)";
					 SqlIInfo.addUser(sql,str,str1);
					 String user=name+"\t"+account+"\t"+password1+"0\t10\r\n";
					 FileOperate.addUser(user);
					 //ע�����ʾ����
             	    Object[] options = {"��¼", "�˳�"};
             	    int i = JOptionPane.showOptionDialog(null, "ע��ɹ���","ע����ʾ��",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
						if(i == 0) {
							dispose();
							LoginAccount loginaccount=new LoginAccount();
							loginaccount.setVisible(true);
						}
						else
							dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "���˺��Ѵ���");//������ʾ����
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
		
		JButton button = new JButton("���ص�¼");
		button.setFont(new Font("����", Font.PLAIN, 18));
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

