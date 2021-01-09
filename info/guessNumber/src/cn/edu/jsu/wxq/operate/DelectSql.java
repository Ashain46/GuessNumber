package cn.edu.jsu.wxq.operate;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.jsu.wxq.dao.database.SqlIInfo;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class DelectSql extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton button;
	private JLabel label;

	/**
	 *@param args the String[]��ɾ�����ݿ�Ĵ���
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DelectSql frame = new DelectSql();
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
	public DelectSql() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600,200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("������Ҫɾ�����˺�");
		btnNewButton.setFont(new Font("����", Font.PLAIN, 25));
		btnNewButton.setBounds(58, 27, 317, 61);
		contentPane.add(btnNewButton);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {//�����û������������¼�����
			@Override 
			  public void keyPressed(KeyEvent e) {//�������̼�������
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//����ǻس���
			    	  String account=textField.getText();
			    	  if(SqlIInfo.isExist(account)) {
			    		  String sql="delect from user where account=?";
			    		  SqlIInfo.delectUser(sql, account);
			    		  JOptionPane.showMessageDialog(null, "ɾ���ɹ�");//������ʾ����
			    	  }
			    	  else
			    		  JOptionPane.showMessageDialog(null, "�޸�����");//������ʾ����
			    }
			 }
		});
		textField.setBounds(109, 112, 221, 43);
		contentPane.add(textField);
		textField.setColumns(10);
		
		button = new JButton("����");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setFont(new Font("����", Font.PLAIN, 20));
		button.setBounds(158, 182, 127, 43);
		contentPane.add(button);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(DelectSql.class.getResource("/img/zzpic17012.jpg")));
		label.setBounds(0, 0, 432, 253);
		contentPane.add(label);
	}
}
