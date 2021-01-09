 package cn.edu.jsu.wxq.operate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.commons.collections4.map.StaticBucketMap;

import cn.edu.jsu.wxq.IO.FileOperate;
import cn.edu.jsu.wxq.dao.database.SqlIInfo;
import cn.edu.jsu.wxq.itface.Administrator;
import cn.edu.jsu.wxq.user.PageController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import java.awt.Font;

public class AllUser extends JFrame {
	private JPanel contentPane;// 定义窗体内容面板，放置各组件
	private JTable table;// 定义表格
	private JTextField txtKey;//定义查找关键字文本框
	private DefaultTableModel model;// 定义表格数据模型
	private TableRowSorter<DefaultTableModel> sorter;//定义排序器
	private ArrayList<RowSorter.SortKey> sortKeys;//设置排序规则
	

	public static void main(String[] args) {
		AllUser frame = new AllUser();// 实例化窗体
		frame.setLocationRelativeTo(null);// 窗体居中
		frame.setVisible(true);// 窗体可见
	}

	/**
	 * 显示全部用户信息.
	 */
	public AllUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置窗体关闭按钮
		setBounds(600,200, 615, 456);// 设置窗体位置与大小
		contentPane = new JPanel();// 实例化内容面板
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置面板边框
		contentPane.setLayout(null);// 设置面板布局为绝对布局
		setContentPane(contentPane);// 将窗体默认面板

		// 设置滚动面板 
		JScrollPane scrollPane = new JScrollPane();// 创建滚动面板
		scrollPane.setBounds(44, 75, 507, 262);// 设置大小与位置
		contentPane.add(scrollPane);// 将滚动面板加入到内容面板中
		scrollPane.setOpaque(false);//背景透明
		scrollPane.getViewport().setOpaque(false); 

		// 使用动态数组数据（列标题与行数据）
		Vector<String> titles = new Vector<String>();
		Collections.addAll(titles,  "昵称", "账号", "密码","积分","鲜花");//定义表格列头
		Vector<Vector> user = new PageController().getPaegData();//获取第一页的数据
//		使用静态数据创建DefaultTableModel数据模型
		
		model = new DefaultTableModel(user, titles) {// 使用Vector装载表格数据模型，覆写getColumnClass方法，实现按各列的数据类型排序
			public Class getColumnClass(int column) {//获取列的类型
				Class returnValue;
				if ((column >= 0) && (column < getColumnCount())) {
					returnValue = getValueAt(0, column).getClass();
				} else {
					returnValue = Object.class;
				}
				return returnValue;
			}

		};
		table = new JTable(model);// 使用DefaultTableModel数据模型实例化表格
		sorter = new TableRowSorter<DefaultTableModel>(model);//设置排序器
		
	
		table.setAutoCreateRowSorter(true);//设置表格自动排序

		scrollPane.setViewportView(table);// 设置使用滚动面板显示表格，如果不使用滚动面板显示，则表格的列标题无法显示
		
		JLabel lblKey = new JLabel("输入关键字：");
		lblKey.setFont(new Font("楷体", Font.PLAIN, 20));
		lblKey.setBounds(42, 36, 120, 26);
		contentPane.add(lblKey);
		
		txtKey = new JTextField();
		txtKey.addKeyListener(new KeyAdapter() {//增加用户名输入框键盘事件焦点
			@Override
			  public void keyPressed(KeyEvent e) {//监听键盘键入内容
				 if(e.getKeyChar()==KeyEvent.VK_ENTER) {//如果是回车键
					 String key=txtKey.getText().trim();//获取输入关键字文本框的值
						if(key.length()!=0) {
							sorter.setRowFilter(RowFilter.regexFilter(key));//是否包含输入框的值
						    table.setRowSorter(sorter);   
						}else {
							//JOptionPane.showMessageDialog(null, "本页无相关信息");//弹出提示窗口
							sorter.setRowFilter(null);//不过滤，显示所有数据
						}
				}
			}
		});
		txtKey.setFont(new Font("楷体", Font.PLAIN, 20));
		txtKey.setBounds(165, 35, 157, 27);
		contentPane.add(txtKey);
		txtKey.setColumns(10);
		
		//定义查找按钮
		JButton btnFind = new JButton("查找");
		btnFind.setFont(new Font("楷体", Font.PLAIN, 19));
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key=txtKey.getText().trim();//获取输入关键字文本框的值
				if(key.length()!=0) {
					sorter.setRowFilter(RowFilter.regexFilter(key));//是否包含输入框的值
				    table.setRowSorter(sorter);   
				}else {
					//JOptionPane.showMessageDialog(null, "本页无相关信息");//弹出提示窗口
					sorter.setRowFilter(null);//不过滤，显示所有数据
				}

			} 
		});
		btnFind.setBounds(336, 36, 95, 26);
		contentPane.add(btnFind);
		
		JButton btnPre = new JButton("上一页");
		btnPre.setFont(new Font("楷体", Font.PLAIN, 20));
		btnPre.addActionListener(new ActionListener() {//上一页单击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				model=new DefaultTableModel(new PageController().prePage(),titles);//设置数据模型中的数据为上一页内容
				table.setModel(model);//设置表格的数据模型
				
			}
		});
		btnPre.setBounds(84, 350, 120, 35);
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
		btnNext.setBounds(236, 350, 115, 35);
		contentPane.add(btnNext);
		
		JLabel lblMsg = new JLabel("每页显示：");
		lblMsg.setFont(new Font("楷体", Font.PLAIN, 20));
		lblMsg.setBounds(376, 353, 100, 35);
		contentPane.add(lblMsg);
		
		
		JButton button = new JButton("删除");
		button.setFont(new Font("楷体", Font.PLAIN, 20));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 DefaultTableModel model=(DefaultTableModel)table.getModel();
					if (table.getSelectedRow() != -1) {// 是否选择了要删除的行
						
						  if (JOptionPane.showConfirmDialog(null, "确定要删除数据吗？", "",    JOptionPane.YES_NO_OPTION) == 0) {// 确定对话框
							
							  while(table.getSelectedRow() != -1)//删除多行数据
									 model.removeRow(table.convertRowIndexToModel(table.getSelectedRow()));
						  } else {
						    JOptionPane.showMessageDialog(null, "请选择要删除的行");
						  }
						}
	                  FileOperate.newFile(table);
			}
		});
		button.setBounds(445, 35, 95, 27);
		contentPane.add(button);
		
		JButton button_1 = new JButton("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Administrator administrator=new Administrator();
				administrator.setVisible(true);
			}
		});
		button_1.setFont(new Font("楷体", Font.PLAIN, 18));
		button_1.setBounds(497, 0, 83, 45);
		contentPane.add(button_1);
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		
		JLabel label = new JLabel("15行");
		label.setFont(new Font("楷体", Font.PLAIN, 20));
		label.setBounds(472, 350, 50, 35);
		contentPane.add(label);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(RankIntegral.class.getResource("/img/QQ\u56FE\u724720210108111614.png")));
		label_3.setBounds(487, 0, 28, 35);
		contentPane.add(label_3);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(AllUser.class.getResource("/img/5.jpg")));
		label_2.setBounds(0, 0, 595, 434);
		contentPane.add(label_2);

	}
}
