package cn.edu.jsu.wxq.operate;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cn.edu.jsu.wxq.itface.Administrator;
import cn.edu.jsu.wxq.itface.PersonalInterface;
import cn.edu.jsu.wxq.user.PageController;
import cn.edu.jsu.wxq.user.User;

import javax.swing.ImageIcon;

public class RankIntegral extends JFrame {
	TableModel model;
	private JPanel contentPane;

	

	/**
	 * 显示积分排行榜.
	 * @param user1 the User传递用户信息
	 */
	public RankIntegral(User user1) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600,200, 613, 481);// 设置窗体位置与大小
		contentPane = new JPanel();// 实例化内容面板
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置面板边框
		contentPane.setLayout(null);// 设置面板布局为绝对布局
		setContentPane(contentPane);// 将窗体默认面板
		
		JLabel label_1 = new JLabel("积分排行榜");
		label_1.setFont(new Font("楷体", Font.PLAIN, 30));
		label_1.setBounds(217, 9, 156, 47);
		contentPane.add(label_1);
		
		// 设置滚动面板
		JScrollPane scrollPane = new JScrollPane();// 创建滚动面板
		scrollPane.setBounds(44, 69, 507, 266);// 设置大小与位置
		contentPane.add(scrollPane);// 将滚动面板加入到内容面板中
		scrollPane.setOpaque(false);//背景透明
		scrollPane.getViewport().setOpaque(false); 
		
		Vector<String> titles = new Vector<String>();
		Collections.addAll(titles, "排名", "昵称","积分","鲜花");//定义表格列头
		new PageController().initSort1();
		Vector<Vector> user = new PageController().getPaegData();//获取第一页的数据
		
		model = new DefaultTableModel(user, titles) {// 使用Vector装载表格数据模型，覆写getColumnClass方法，实现按各列的数据类型排序
		};
		JTable table = new JTable(model);// 使用DefaultTableModel数据模型实例化表格
		
		scrollPane.setViewportView(table);// 设置使用滚动面板显示表格，如果不使用滚动面板显示，则表格的列标题无法显示
		
		JButton btnPre = new JButton("上一页");
		btnPre.setFont(new Font("楷体", Font.PLAIN, 20));
		btnPre.addActionListener(new ActionListener() {//上一页单击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				model=new DefaultTableModel(new PageController().prePage(),titles);//设置数据模型中的数据为上一页内容
				table.setModel(model);//设置表格的数据模型
				
			}
		});
		btnPre.setBounds(44, 348, 120, 35);
		contentPane.add(btnPre);
		
		JButton btnNext = new JButton("下一页");
		btnNext.setFont(new Font("楷体", Font.PLAIN, 20));
		btnNext.addActionListener(new ActionListener() {//下一页单击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				model=new DefaultTableModel(new PageController().nextPage(),titles);//设置数据模型中的数据为下一页内容
				table.setModel(model);//设置表格的数据模型
			}
		});
		btnNext.setBounds(173, 348, 115, 35);
		contentPane.add(btnNext);
		
		//跳转指定页
	    JTextField textField = new JTextField("");
		textField.addKeyListener(new KeyAdapter() {//增加用户名输入框键盘事件焦点
			@Override
			  public void keyPressed(KeyEvent e) {//监听键盘键入内容
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//如果是回车键
			    	int a=0,b=0,page;
			    	if(textField.getText().trim().equals("")) {
			    		JOptionPane.showMessageDialog(null, "请输入数字");
			    		return;
			    	} 
					else{
						//判断输入的是否是数字
						char[] c=textField.getText().toCharArray();          //将字符串转换为字符数组
						for (int i=0;i<c.length;i++) {
							if (c[i]>='0'&&c[i]<='9')  a++;
							else b++;
						}
						if(a==c.length){
							page=Integer.parseInt(textField.getText());
							model=new DefaultTableModel(new PageController().appointPage(page),titles);//设置数据模型中的数据为指定页内容
				        	table.setModel(model);//设置表格的数据模型
						}
						else {
							JOptionPane.showMessageDialog(null, "请输入数字");
						}
					}
			    	textField.setText("");
			    	 
			    }
			}

		});
		textField.setBounds(302, 348, 101, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JLabel lblMsg = new JLabel("每页显示：");
		lblMsg.setFont(new Font("楷体", Font.PLAIN, 20));
		lblMsg.setBounds(415, 348, 100, 35);
		contentPane.add(lblMsg);
		
		JLabel label = new JLabel("15行");
		label.setFont(new Font("楷体", Font.PLAIN, 20));
		label.setBounds(511, 348, 40, 35);
		contentPane.add(label);
		
		JButton button_1 = new JButton("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setFont(new Font("楷体", Font.PLAIN, 20));
		button_1.setBounds(484, 13, 97, 35);
		contentPane.add(button_1);
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(RankIntegral.class.getResource("/img/QQ\u56FE\u724720210108111614.png")));
		label_3.setBounds(484, 9, 28, 35);
		contentPane.add(label_3);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(RankIntegral.class.getResource("/img/11 (2).jpg")));
		label_2.setBounds(0, 0, 595, 434);
		contentPane.add(label_2);
	
		//button_2.setContentAreaFilled(false);//按钮透明
		//button_2.setBorderPainted(false);
	}
}
