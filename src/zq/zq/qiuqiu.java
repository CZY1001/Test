package zq.zq;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


public class qiuqiu extends JFrame {
	
		Image ball =Toolkit.getDefaultToolkit().getImage("tu/ball.png");//����ͼƬ
		Image desk =Toolkit.getDefaultToolkit().getImage("tu/desk.jpg");
	
		double x=100;//��ĺ�������
		double y=100;
		double degree = 3.14/3;//��ʾ���ȣ��˴���ʾ60��
		
	//��ͼƬŪ�����ڣ���Ϊ������
	public void paint(Graphics g) {
		g.drawImage(desk,0,0,null);
		g.drawImage(ball,(int)x,(int)y,null);
		
		x = x + 10*Math.cos(degree);
		y = y + 10*Math.sin(degree);
		
		//�����±�ת��
		if(y>500-40-30||y<40+40) {
			degree = -degree;
		}
		//�����ұ�
		if(x>865-40-30||x<40) {
			degree = 3.14  -degree;
			
		}
			
		}
		
	
	
	//���ڼ���
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
		

		
		//ÿ����ʾ���ٴ�
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
		
		System.out.println("���ߣ�С��");
		qiuqiu game = new qiuqiu();
		game.launchFrame();
		
		
		}

}
