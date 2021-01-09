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
	 * ��ʾȫ����Ϸ��¼
	 */
	public AllPlayRecord() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600,200, 615, 456);// ���ô���λ�����С
		contentPane = new JPanel();// ʵ�����������
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// �������߿�
		contentPane.setLayout(null);// ������岼��Ϊ���Բ���
		setContentPane(contentPane);// ������Ĭ�����
		
		JLabel label_1 = new JLabel("��ʷ��¼");
		label_1.setFont(new Font("����", Font.PLAIN, 35));
		label_1.setBounds(219, 13, 149, 57);
		contentPane.add(label_1);
		
		// ���ù������
				JScrollPane scrollPane = new JScrollPane();// �����������
				scrollPane.setBounds(44, 124, 507, 272);// ���ô�С��λ��
				contentPane.add(scrollPane);// �����������뵽���������
				scrollPane.setOpaque(false);//����͸��
				scrollPane.getViewport().setOpaque(false); 
				
				Vector<String> titles = new Vector<String>();
				Collections.addAll(titles, "�ǳ�","����","ʱ��");//��������ͷ
				Vector<Vector> record=new Vector<Vector>();//����Ҫ���ص����м�¼����
				String sql="select * from record";
				record=SqlIInfo.getAll1(sql,null);
				
				DefaultTableModel model = new DefaultTableModel(record, titles) {// ʹ��Vectorװ�ر������ģ�ͣ���дgetColumnClass������ʵ�ְ����е�������������
				};
				JTable table = new JTable(model);// ʹ��DefaultTableModel����ģ��ʵ�������
				
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
				
				JLabel lblNewLabel = new JLabel("�����ǳƲ�ѯ:");
				lblNewLabel.setFont(new Font("����", Font.PLAIN, 25));
				lblNewLabel.setBounds(85, 72, 172, 40);
				contentPane.add(lblNewLabel);
				
				textField = new JTextField("");
				textField.addKeyListener(new KeyAdapter() {//�����û������������¼�����
					@Override
					  public void keyPressed(KeyEvent e) {//�������̼�������
						 if(e.getKeyChar()==KeyEvent.VK_ENTER) {//����ǻس���
						if(textField.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "�������ǳ�");return;
				    	}
						else {
							PlayRecord playRecord=new PlayRecord(textField.getText());
							playRecord.setVisible(true);
							textField.setText("");
							//scrollPane.setViewportView(table);// ����ʹ�ù��������ʾ��������ʹ�ù��������ʾ��������б����޷���ʾ
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
