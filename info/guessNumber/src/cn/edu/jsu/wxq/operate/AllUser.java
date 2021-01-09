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
	private JPanel contentPane;// ���崰��������壬���ø����
	private JTable table;// ������
	private JTextField txtKey;//������ҹؼ����ı���
	private DefaultTableModel model;// ����������ģ��
	private TableRowSorter<DefaultTableModel> sorter;//����������
	private ArrayList<RowSorter.SortKey> sortKeys;//�����������
	

	public static void main(String[] args) {
		AllUser frame = new AllUser();// ʵ��������
		frame.setLocationRelativeTo(null);// �������
		frame.setVisible(true);// ����ɼ�
	}

	/**
	 * ��ʾȫ���û���Ϣ.
	 */
	public AllUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ô���رհ�ť
		setBounds(600,200, 615, 456);// ���ô���λ�����С
		contentPane = new JPanel();// ʵ�����������
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// �������߿�
		contentPane.setLayout(null);// ������岼��Ϊ���Բ���
		setContentPane(contentPane);// ������Ĭ�����

		// ���ù������ 
		JScrollPane scrollPane = new JScrollPane();// �����������
		scrollPane.setBounds(44, 75, 507, 262);// ���ô�С��λ��
		contentPane.add(scrollPane);// �����������뵽���������
		scrollPane.setOpaque(false);//����͸��
		scrollPane.getViewport().setOpaque(false); 

		// ʹ�ö�̬�������ݣ��б����������ݣ�
		Vector<String> titles = new Vector<String>();
		Collections.addAll(titles,  "�ǳ�", "�˺�", "����","����","�ʻ�");//��������ͷ
		Vector<Vector> user = new PageController().getPaegData();//��ȡ��һҳ������
//		ʹ�þ�̬���ݴ���DefaultTableModel����ģ��
		
		model = new DefaultTableModel(user, titles) {// ʹ��Vectorװ�ر������ģ�ͣ���дgetColumnClass������ʵ�ְ����е�������������
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
		table = new JTable(model);// ʹ��DefaultTableModel����ģ��ʵ�������
		sorter = new TableRowSorter<DefaultTableModel>(model);//����������
		
	
		table.setAutoCreateRowSorter(true);//���ñ���Զ�����

		scrollPane.setViewportView(table);// ����ʹ�ù��������ʾ��������ʹ�ù��������ʾ��������б����޷���ʾ
		
		JLabel lblKey = new JLabel("����ؼ��֣�");
		lblKey.setFont(new Font("����", Font.PLAIN, 20));
		lblKey.setBounds(42, 36, 120, 26);
		contentPane.add(lblKey);
		
		txtKey = new JTextField();
		txtKey.addKeyListener(new KeyAdapter() {//�����û������������¼�����
			@Override
			  public void keyPressed(KeyEvent e) {//�������̼�������
				 if(e.getKeyChar()==KeyEvent.VK_ENTER) {//����ǻس���
					 String key=txtKey.getText().trim();//��ȡ����ؼ����ı����ֵ
						if(key.length()!=0) {
							sorter.setRowFilter(RowFilter.regexFilter(key));//�Ƿ����������ֵ
						    table.setRowSorter(sorter);   
						}else {
							//JOptionPane.showMessageDialog(null, "��ҳ�������Ϣ");//������ʾ����
							sorter.setRowFilter(null);//�����ˣ���ʾ��������
						}
				}
			}
		});
		txtKey.setFont(new Font("����", Font.PLAIN, 20));
		txtKey.setBounds(165, 35, 157, 27);
		contentPane.add(txtKey);
		txtKey.setColumns(10);
		
		//������Ұ�ť
		JButton btnFind = new JButton("����");
		btnFind.setFont(new Font("����", Font.PLAIN, 19));
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key=txtKey.getText().trim();//��ȡ����ؼ����ı����ֵ
				if(key.length()!=0) {
					sorter.setRowFilter(RowFilter.regexFilter(key));//�Ƿ����������ֵ
				    table.setRowSorter(sorter);   
				}else {
					//JOptionPane.showMessageDialog(null, "��ҳ�������Ϣ");//������ʾ����
					sorter.setRowFilter(null);//�����ˣ���ʾ��������
				}

			} 
		});
		btnFind.setBounds(336, 36, 95, 26);
		contentPane.add(btnFind);
		
		JButton btnPre = new JButton("��һҳ");
		btnPre.setFont(new Font("����", Font.PLAIN, 20));
		btnPre.addActionListener(new ActionListener() {//��һҳ�����¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				model=new DefaultTableModel(new PageController().prePage(),titles);//��������ģ���е�����Ϊ��һҳ����
				table.setModel(model);//���ñ�������ģ��
				
			}
		});
		btnPre.setBounds(84, 350, 120, 35);
		contentPane.add(btnPre);
		
		JButton btnNext = new JButton("��һҳ");
		btnNext.setFont(new Font("����", Font.PLAIN, 20));
		btnNext.addActionListener(new ActionListener() {//��һҳ�����¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				model=new DefaultTableModel(new PageController().nextPage(),titles);//��������ģ���е�����Ϊ��һҳ����
				table.setModel(model);//���ñ�������ģ��
			}
		});
		btnNext.setBounds(236, 350, 115, 35);
		contentPane.add(btnNext);
		
		JLabel lblMsg = new JLabel("ÿҳ��ʾ��");
		lblMsg.setFont(new Font("����", Font.PLAIN, 20));
		lblMsg.setBounds(376, 353, 100, 35);
		contentPane.add(lblMsg);
		
		
		JButton button = new JButton("ɾ��");
		button.setFont(new Font("����", Font.PLAIN, 20));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 DefaultTableModel model=(DefaultTableModel)table.getModel();
					if (table.getSelectedRow() != -1) {// �Ƿ�ѡ����Ҫɾ������
						
						  if (JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ��������", "",    JOptionPane.YES_NO_OPTION) == 0) {// ȷ���Ի���
							
							  while(table.getSelectedRow() != -1)//ɾ����������
									 model.removeRow(table.convertRowIndexToModel(table.getSelectedRow()));
						  } else {
						    JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ������");
						  }
						}
	                  FileOperate.newFile(table);
			}
		});
		button.setBounds(445, 35, 95, 27);
		contentPane.add(button);
		
		JButton button_1 = new JButton("����");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Administrator administrator=new Administrator();
				administrator.setVisible(true);
			}
		});
		button_1.setFont(new Font("����", Font.PLAIN, 18));
		button_1.setBounds(497, 0, 83, 45);
		contentPane.add(button_1);
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		
		JLabel label = new JLabel("15��");
		label.setFont(new Font("����", Font.PLAIN, 20));
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
