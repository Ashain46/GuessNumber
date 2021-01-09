package cn.edu.jsu.wxq.operate;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.junit.Test;

import cn.edu.jsu.wxq.dao.database.SqlIInfo;
import cn.edu.jsu.wxq.user.User;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class ModifyInformation extends JFrame {
	User user1;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JLabel newLabel;
	private JLabel label;
	private JLabel label_2;
	private JLabel label_5;
	private JButton button_1;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DefityInformation frame = new DefityInformation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * �޸��û���Ϣ����.
	 * @param user the User�����û���Ϣ
	 */
	public ModifyInformation(User user) {
		user1=user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(720,450, 393, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		radioButton = new JRadioButton("�޸��û���");
		radioButton.setFont(new Font("����", Font.PLAIN, 18));
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radioButton.isSelected()&&!radioButton_1.isSelected()) {
					label.setText("ԭ�û���:");
					newLabel.setText("���û���:");
				}
			}
		});
		radioButton.setBounds(66, 28, 157, 27);
		contentPane.add(radioButton);
		radioButton.setContentAreaFilled(false);
		radioButton.setBorderPainted(false);
		
		radioButton_1 = new JRadioButton("�޸�����");
		radioButton_1.setFont(new Font("����", Font.PLAIN, 18));
		radioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radioButton_1.isSelected()&&!radioButton.isSelected()) {
					label.setText("ԭ����:");
					newLabel.setText("������:");
				}
				else if (radioButton_1.isSelected()&&radioButton.isSelected()) {
					JOptionPane.showMessageDialog(null, "����һ��һ������");//������ʾ����
				}
			}
		});
		radioButton_1.setBounds(208, 28, 157, 27);
		contentPane.add(radioButton_1);
		radioButton_1.setContentAreaFilled(false);
		radioButton_1.setBorderPainted(false);
		
		label = new JLabel("");
		label.setFont(new Font("����", Font.PLAIN, 20));
		label.setBounds(32, 65, 101, 31);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {//�����û������������¼�����
			@Override
			  public void keyPressed(KeyEvent e) {//�������̼�������
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//����ǻس���
			    	 textField_1.requestFocus();
			    }
			   }

		});
		textField.setFont(new Font("����", Font.PLAIN, 20));
		textField.setBounds(147, 65, 182, 31);
		contentPane.add(textField);
		textField.setColumns(10);

		newLabel = new JLabel("");
		newLabel.setFont(new Font("����", Font.PLAIN, 20));
		newLabel.setBounds(32, 109, 101, 33);
		contentPane.add(newLabel);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {//�����û������������¼�����
			@Override
			  public void keyPressed(KeyEvent e) {//�������̼�������
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//����ǻس���
			    	 textField_2.requestFocus();
			    }
			   }
 
		});
		textField_1.setFont(new Font("����", Font.PLAIN, 20));
		textField_1.setBounds(147, 110, 182, 33);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_1 = new JLabel("ԭ������֤:");
		label_1.setFont(new Font("����", Font.PLAIN, 20));
		label_1.setBounds(14, 154, 119, 35);
		contentPane.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("����", Font.PLAIN, 20));
		textField_2.setBounds(147, 156, 182, 31);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton button = new JButton("ȷ��");
		button.setFont(new Font("����", Font.PLAIN, 25));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(user.getPassword().equals(textField_2.getText()))) {
					JOptionPane.showMessageDialog(null, "�������!����ʧ��");//������ʾ����
				}
				else {
					String sql=null;
					if(radioButton.isSelected()) {
						sql="update user set name =? where account=?";
						JOptionPane.showMessageDialog(null, "�޸ĳɹ�!��ǰ�û���Ϊ"+textField_1.getText());//������ʾ����
						user1.setName(textField_1.getText());
					}
					else {
						sql="update user set password =? where account=?";
						JOptionPane.showMessageDialog(null, "�޸ĳɹ�!��ǰ����Ϊ"+textField_1.getText());//������ʾ����
						user1.setName(textField_1.getText());
					}
					String []str= {textField_1.getText(),user.getAccount()};
					SqlIInfo.modifyUser1(sql,str);
				}
			}
		});
		button.setBounds(81, 200, 113, 35);
		contentPane.add(button);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		
		button_1 = new JButton("����");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setFont(new Font("����", Font.PLAIN, 25));
		button_1.setBounds(208, 200, 113, 35);
		contentPane.add(button_1);
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		
		
		label_2 = new JLabel("");
		label_2.setBounds(126, 206, 119, 36);
		contentPane.add(label_2);
		
		label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(ModifyInformation.class.getResource("/img/xpic7629.jpg")));
		label_5.setBounds(0, 0, 387, 266);
		contentPane.add(label_5);
		
	}
	public User getUser() {
		return user1;
	}
}
