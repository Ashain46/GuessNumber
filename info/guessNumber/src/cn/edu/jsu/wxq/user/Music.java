package cn.edu.jsu.wxq.user;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import org.junit.Test;

import javazoom.jl.player.Player;
/**
 * �������ֲ����߳�
 * @author 86135
 *
 */
public class Music extends Thread {
	private String fileName;
	public void setFileName(String fileName1) {
		this.fileName=fileName1;
	}
	public String getFileName() {
		return fileName;
	}
	public void run() {
		String fileName=getFileName();
				//"C:\\Users\\86135\\Music\\����.mp3";//�ļ�·��
        try { 
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(fileName));
            Player player = new Player(buffer);//����
            player.play();//��ʼ����
        } catch (Exception e) {
            System.out.println(e);
        }
	}
}