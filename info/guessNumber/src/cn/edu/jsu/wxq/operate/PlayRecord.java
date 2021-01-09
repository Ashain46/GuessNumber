package cn.edu.jsu.wxq.operate;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.TableRowSorter;

import cn.edu.jsu.wxq.dao.database.SqlIInfo;
import cn.edu.jsu.wxq.io.FileOperate;
import cn.edu.jsu.wxq.user.PageController;
import cn.edu.jsu.wxq.user.User;

public class PlayRecord extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayRecord frame = new PlayRecord();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * 显示个人历史游戏记录.
	 * @param name the String传递用户名
	 */
	public PlayRecord(String name) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600,200, 615, 456);// 设置窗体位置与大小
		contentPane = new JPanel();// 实例化内容面板
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置面板边框
		contentPane.setLayout(null);// 设置面板布局为绝对布局
		setContentPane(contentPane);// 将窗体默认面板
		
		JLabel label_1 = new JLabel("历史记录");
		label_1.setFont(new Font("楷体", Font.PLAIN, 30));
		label_1.setBounds(212, 12, 151, 47);
		contentPane.add(label_1);
		
		// 设置滚动面板
				JScrollPane scrollPane = new JScrollPane();// 创建滚动面板
				scrollPane.setBounds(44, 72, 507, 285);// 设置大小与位置
				contentPane.add(scrollPane);// 将滚动面板加入到内容面板中
				scrollPane.setOpaque(false);//背景透明
				scrollPane.getViewport().setOpaque(false); 
				
				Vector<String> titles = new Vector<String>();
				Collections.addAll(titles, "昵称","积分","时间");//定义表格列头
				Vector<Vector> record=new Vector<Vector>();//定义要返回的所有记录集合
				String sql="select * from record where name=?";
				record=SqlIInfo.getAll1(sql,name);
				
				DefaultTableModel model = new DefaultTableModel(record, titles) {// 使用Vector装载表格数据模型，覆写getColumnClass方法，实现按各列的数据类型排序
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
				JTable table = new JTable(model);// 使用DefaultTableModel数据模型实例化表格
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);//设置排序器;
				table.setAutoCreateRowSorter(true);//设置表格自动排序
				
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
				
				JButton btnNewButton = new JButton("删除该记录");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						 DefaultTableModel model=(DefaultTableModel)table.getModel();
							if (table.getSelectedRow() != -1) {// 是否选择了要删除的行
								
								  if (JOptionPane.showConfirmDialog(null, "确定要删除数据吗？", "",    JOptionPane.YES_NO_OPTION) == 0) {// 确定对话框
									
									  while(table.getSelectedRow() != -1)//删除多行数据
											 model.removeRow(table.convertRowIndexToModel(table.getSelectedRow()));
								    //从表格数据中删除行,model1为DefaultTableModel类型，排序后不能直接使用表格的getSelectedRow方法获取被选中的行
								  } else {
								    JOptionPane.showMessageDialog(null, "请选择要删除的行");
								  }
								}
			                  FileOperate.newFile(table);
					}
				});
				btnNewButton.setBounds(229, 370, 113, 27);
				contentPane.add(btnNewButton);
				
				JLabel label_3 = new JLabel("");
				label_3.setIcon(new ImageIcon(RankIntegral.class.getResource("/img/QQ\u56FE\u724720210108111614.png")));
				label_3.setBounds(484, 9, 28, 35);
				contentPane.add(label_3);
				
				JLabel label_2 = new JLabel("");
				label_2.setIcon(new ImageIcon(RankIntegral.class.getResource("/img/11 (2).jpg")));
				label_2.setBounds(0, 0, 595, 434);
				contentPane.add(label_2);
		
	}
}
