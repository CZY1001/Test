package zq.zq;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


public class qiuqiu extends JFrame {
	
		Image ball =Toolkit.getDefaultToolkit().getImage("tu/ball.png");//加入图片
		Image desk =Toolkit.getDefaultToolkit().getImage("tu/desk.jpg");
	
		double x=100;//球的横纵坐标
		double y=100;
		double degree = 3.14/3;//表示弧度，此处表示60度
		
	//将图片弄进窗口，称为画窗口
	public void paint(Graphics g) {
		g.drawImage(desk,0,0,null);
		g.drawImage(ball,(int)x,(int)y,null);
		
		x = x + 10*Math.cos(degree);
		y = y + 10*Math.sin(degree);
		
		//碰上下壁转向
		if(y>500-40-30||y<40+40) {
			degree = -degree;
		}
		//碰左右壁
		if(x>865-40-30||x<40) {
			degree = 3.14  -degree;
			
		}
			
		}
		
	
	
	//窗口加载
	void launchFrame() {
		setSize(865,500);
		setLocation(300,300);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}		
		});
		

		
		//每秒显示多少次
		while(true) {
			repaint();
			try {
				Thread.sleep(15);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	
	public static void main(String[]args) {
		
		System.out.println("作者：小陈");
		qiuqiu game = new qiuqiu();
		game.launchFrame();
		
		
		}

}
