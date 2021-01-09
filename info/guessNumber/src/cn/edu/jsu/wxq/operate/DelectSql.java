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
	 *@param args the String[]打开删除数据库的窗口
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
		
		JButton btnNewButton = new JButton("请输入要删除的账号");
		btnNewButton.setFont(new Font("楷体", Font.PLAIN, 25));
		btnNewButton.setBounds(58, 27, 317, 61);
		contentPane.add(btnNewButton);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {//增加用户名输入框键盘事件焦点
			@Override 
			  public void keyPressed(KeyEvent e) {//监听键盘键入内容
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//如果是回车键
			    	  String account=textField.getText();
			    	  if(SqlIInfo.isExist(account)) {
			    		  String sql="delect from user where account=?";
			    		  SqlIInfo.delectUser(sql, account);
			    		  JOptionPane.showMessageDialog(null, "删除成功");//弹出提示窗口
			    	  }
			    	  else
			    		  JOptionPane.showMessageDialog(null, "无该数据");//弹出提示窗口
			    }
			 }
		});
		textField.setBounds(109, 112, 221, 43);
		contentPane.add(textField);
		textField.setColumns(10);
		
		button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setFont(new Font("楷体", Font.PLAIN, 20));
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
