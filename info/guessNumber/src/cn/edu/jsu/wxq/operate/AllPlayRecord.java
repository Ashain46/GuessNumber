package cn.edu.jsu.wxq.operate;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cn.edu.jsu.wxq.dao.database.SqlIInfo;
import javax.swing.JTextField;

public class AllPlayRecord extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	

	/**
	 * 显示全部游戏记录
	 */
	public AllPlayRecord() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600,200, 615, 456);// 设置窗体位置与大小
		contentPane = new JPanel();// 实例化内容面板
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置面板边框
		contentPane.setLayout(null);// 设置面板布局为绝对布局
		setContentPane(contentPane);// 将窗体默认面板
		
		JLabel label_1 = new JLabel("历史记录");
		label_1.setFont(new Font("楷体", Font.PLAIN, 35));
		label_1.setBounds(219, 13, 149, 57);
		contentPane.add(label_1);
		
		// 设置滚动面板
				JScrollPane scrollPane = new JScrollPane();// 创建滚动面板
				scrollPane.setBounds(44, 124, 507, 272);// 设置大小与位置
				contentPane.add(scrollPane);// 将滚动面板加入到内容面板中
				scrollPane.setOpaque(false);//背景透明
				scrollPane.getViewport().setOpaque(false); 
				
				Vector<String> titles = new Vector<String>();
				Collections.addAll(titles, "昵称","积分","时间");//定义表格列头
				Vector<Vector> record=new Vector<Vector>();//定义要返回的所有记录集合
				String sql="select * from record";
				record=SqlIInfo.getAll1(sql,null);
				
				DefaultTableModel model = new DefaultTableModel(record, titles) {// 使用Vector装载表格数据模型，覆写getColumnClass方法，实现按各列的数据类型排序
				};
				JTable table = new JTable(model);// 使用DefaultTableModel数据模型实例化表格
				
				scrollPane.setViewportView(table);// 设置使用滚动面板显示表格，如果不使用滚动面板显示，则表格的列标题无法显示
				
				JButton button_1 = new JButton("返回");
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				button_1.setFont(new Font("楷体", Font.PLAIN, 20));
				button_1.setBounds(494, 12, 89, 40);
				contentPane.add(button_1);
				button_1.setContentAreaFilled(false);
				button_1.setBorderPainted(false);
				
				JLabel lblNewLabel = new JLabel("输入昵称查询:");
				lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 25));
				lblNewLabel.setBounds(85, 72, 172, 40);
				contentPane.add(lblNewLabel);
				
				textField = new JTextField("");
				textField.addKeyListener(new KeyAdapter() {//增加用户名输入框键盘事件焦点
					@Override
					  public void keyPressed(KeyEvent e) {//监听键盘键入内容
						 if(e.getKeyChar()==KeyEvent.VK_ENTER) {//如果是回车键
						if(textField.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "请输入昵称");return;
				    	}
						else {
							PlayRecord playRecord=new PlayRecord(textField.getText());
							playRecord.setVisible(true);
							textField.setText("");
							//scrollPane.setViewportView(table);// 设置使用滚动面板显示表格，如果不使用滚动面板显示，则表格的列标题无法显示
						}
						 }
					}
				});
				textField.setBounds(253, 72, 259, 40);
				contentPane.add(textField);
				textField.setColumns(10);
				
				JLabel label_3 = new JLabel("");
				label_3.setIcon(new ImageIcon(RankIntegral.class.getResource("/img/QQ\u56FE\u724720210108111614.png")));
				label_3.setBounds(484, 9, 28, 35);
				contentPane.add(label_3);
				
				JLabel label_2 = new JLabel("");
				label_2.setIcon(new ImageIcon(RankIntegral.class.getResource("/img/11 (2).jpg")));
				label_2.setBounds(0, -25, 595, 434);
				contentPane.add(label_2);
		
	}

}
