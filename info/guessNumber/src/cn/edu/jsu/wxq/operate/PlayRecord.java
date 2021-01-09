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
	 * ��ʾ������ʷ��Ϸ��¼.
	 * @param name the String�����û���
	 */
	public PlayRecord(String name) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600,200, 615, 456);// ���ô���λ�����С
		contentPane = new JPanel();// ʵ�����������
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// �������߿�
		contentPane.setLayout(null);// ������岼��Ϊ���Բ���
		setContentPane(contentPane);// ������Ĭ�����
		
		JLabel label_1 = new JLabel("��ʷ��¼");
		label_1.setFont(new Font("����", Font.PLAIN, 30));
		label_1.setBounds(212, 12, 151, 47);
		contentPane.add(label_1);
		
		// ���ù������
				JScrollPane scrollPane = new JScrollPane();// �����������
				scrollPane.setBounds(44, 72, 507, 285);// ���ô�С��λ��
				contentPane.add(scrollPane);// �����������뵽���������
				scrollPane.setOpaque(false);//����͸��
				scrollPane.getViewport().setOpaque(false); 
				
				Vector<String> titles = new Vector<String>();
				Collections.addAll(titles, "�ǳ�","����","ʱ��");//��������ͷ
				Vector<Vector> record=new Vector<Vector>();//����Ҫ���ص����м�¼����
				String sql="select * from record where name=?";
				record=SqlIInfo.getAll1(sql,name);
				
				DefaultTableModel model = new DefaultTableModel(record, titles) {// ʹ��Vectorװ�ر������ģ�ͣ���дgetColumnClass������ʵ�ְ����е�������������
					public Class getColumnClass(int column) {//��ȡ�е�����
						Class returnValue;
						if ((column >= 0) && (column < getColumnCount())) {
							returnValue = getValueAt(0, column).getClass();
						} else {
							returnValue = Object.class;
						}
						return returnValue;
					}
				};
				JTable table = new JTable(model);// ʹ��DefaultTableModel����ģ��ʵ�������
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);//����������;
				table.setAutoCreateRowSorter(true);//���ñ���Զ�����
				
				scrollPane.setViewportView(table);// ����ʹ�ù��������ʾ��������ʹ�ù��������ʾ��������б����޷���ʾ
				
				JButton button_1 = new JButton("����");
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				button_1.setFont(new Font("����", Font.PLAIN, 20));
				button_1.setBounds(494, 12, 89, 40);
				contentPane.add(button_1);
				button_1.setContentAreaFilled(false);
				button_1.setBorderPainted(false);
				
				JButton btnNewButton = new JButton("ɾ���ü�¼");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						 DefaultTableModel model=(DefaultTableModel)table.getModel();
							if (table.getSelectedRow() != -1) {// �Ƿ�ѡ����Ҫɾ������
								
								  if (JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ��������", "",    JOptionPane.YES_NO_OPTION) == 0) {// ȷ���Ի���
									
									  while(table.getSelectedRow() != -1)//ɾ����������
											 model.removeRow(table.convertRowIndexToModel(table.getSelectedRow()));
								    //�ӱ��������ɾ����,model1ΪDefaultTableModel���ͣ��������ֱ��ʹ�ñ���getSelectedRow������ȡ��ѡ�е���
								  } else {
								    JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ������");
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
