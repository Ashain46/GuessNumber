package cn.edu.jsu.wxq.user;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import org.junit.Test;

import javazoom.jl.player.Player;
/**
 * 设置音乐播放线程
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
				//"C:\\Users\\86135\\Music\\大鱼.mp3";//文件路径
        try { 
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(fileName));
            Player player = new Player(buffer);//加载
            player.play();//开始播放
        } catch (Exception e) {
            System.out.println(e);
        }
	}
}