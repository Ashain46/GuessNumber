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
	 * ��ʾ�������а�.
	 * @param user1 the User�����û���Ϣ
	 */
	public RankIntegral(User user1) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600,200, 613, 481);// ���ô���λ�����С
		contentPane = new JPanel();// ʵ�����������
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// �������߿�
		contentPane.setLayout(null);// ������岼��Ϊ���Բ���
		setContentPane(contentPane);// ������Ĭ�����
		
		JLabel label_1 = new JLabel("�������а�");
		label_1.setFont(new Font("����", Font.PLAIN, 30));
		label_1.setBounds(217, 9, 156, 47);
		contentPane.add(label_1);
		
		// ���ù������
		JScrollPane scrollPane = new JScrollPane();// �����������
		scrollPane.setBounds(44, 69, 507, 266);// ���ô�С��λ��
		contentPane.add(scrollPane);// �����������뵽���������
		scrollPane.setOpaque(false);//����͸��
		scrollPane.getViewport().setOpaque(false); 
		
		Vector<String> titles = new Vector<String>();
		Collections.addAll(titles, "����", "�ǳ�","����","�ʻ�");//��������ͷ
		new PageController().initSort1();
		Vector<Vector> user = new PageController().getPaegData();//��ȡ��һҳ������
		
		model = new DefaultTableModel(user, titles) {// ʹ��Vectorװ�ر������ģ�ͣ���дgetColumnClass������ʵ�ְ����е�������������
		};
		JTable table = new JTable(model);// ʹ��DefaultTableModel����ģ��ʵ�������
		
		scrollPane.setViewportView(table);// ����ʹ�ù��������ʾ��������ʹ�ù��������ʾ��������б����޷���ʾ
		
		JButton btnPre = new JButton("��һҳ");
		btnPre.setFont(new Font("����", Font.PLAIN, 20));
		btnPre.addActionListener(new ActionListener() {//��һҳ�����¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				model=new DefaultTableModel(new PageController().prePage(),titles);//��������ģ���е�����Ϊ��һҳ����
				table.setModel(model);//���ñ�������ģ��
				
			}
		});
		btnPre.setBounds(44, 348, 120, 35);
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
		btnNext.setBounds(173, 348, 115, 35);
		contentPane.add(btnNext);
		
		//��תָ��ҳ
	    JTextField textField = new JTextField("");
		textField.addKeyListener(new KeyAdapter() {//�����û������������¼�����
			@Override
			  public void keyPressed(KeyEvent e) {//�������̼�������
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//����ǻس���
			    	int a=0,b=0,page;
			    	if(textField.getText().trim().equals("")) {
			    		JOptionPane.showMessageDialog(null, "����������");
			    		return;
			    	} 
					else{
						//�ж�������Ƿ�������
						char[] c=textField.getText().toCharArray();          //���ַ���ת��Ϊ�ַ�����
						for (int i=0;i<c.length;i++) {
							if (c[i]>='0'&&c[i]<='9')  a++;
							else b++;
						}
						if(a==c.length){
							page=Integer.parseInt(textField.getText());
							model=new DefaultTableModel(new PageController().appointPage(page),titles);//��������ģ���е�����Ϊָ��ҳ����
				        	table.setModel(model);//���ñ�������ģ��
						}
						else {
							JOptionPane.showMessageDialog(null, "����������");
						}
					}
			    	textField.setText("");
			    	 
			    }
			}

		});
		textField.setBounds(302, 348, 101, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JLabel lblMsg = new JLabel("ÿҳ��ʾ��");
		lblMsg.setFont(new Font("����", Font.PLAIN, 20));
		lblMsg.setBounds(415, 348, 100, 35);
		contentPane.add(lblMsg);
		
		JLabel label = new JLabel("15��");
		label.setFont(new Font("����", Font.PLAIN, 20));
		label.setBounds(511, 348, 40, 35);
		contentPane.add(label);
		
		JButton button_1 = new JButton("����");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setFont(new Font("����", Font.PLAIN, 20));
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
	
		//button_2.setContentAreaFilled(false);//��ť͸��
		//button_2.setBorderPainted(false);
	}
}
