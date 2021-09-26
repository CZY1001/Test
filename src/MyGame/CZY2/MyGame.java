 package MyGame.CZY2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;


/**
 * �ɻ���Ϸ��������
 * @author ����ԭ
 * @date 2020��4��16��
 * @version 1.0
 */
public class MyGame extends Frame{

	Image fj = GameUtil.getImage("D:/java/IDEAproject/Study/src/MyGame/tu/fj.png");//��ͼƬ����Ϊ��Ա����
	Image bj = GameUtil.getImage("D:/java/IDEAproject/Study/src/MyGame/tu/bj.jpg");
	
	plane pl =new plane(fj,250,20);


	 ArrayList<paodan>  paodans= new ArrayList<paodan>();//��������洢�෢�ڵ�
	
	baozha bao;//������ը����
	Date playTime = new Date();//������ʼʱ��
	Date dieTime;
	int time;//��Ϸ������ʱ��
	
	@Override
	public void paint(Graphics g) {//paint�������ǻ����������ڼ��ڲ����ݡ���ϵͳ�Զ����á�g�൱�ڻ���
		Color c = g.getColor();
		
		g.drawImage(bj, 0, 0, null);
		pl.drawSelf(g);//���ɻ�
		
		//�������������е��ڵ�
		for(int i=0;i<paodans.size();i++) {
			paodan p = paodans.get(i);
			p.draw(g);
			
			//�ɻ����ڵ�����ײ���
		boolean peng =  p.getPeng().intersects(pl.getPeng());
		
		if(peng) {
			pl.die   = false ;
		
			if(bao ==null) {
			bao = new baozha(pl.x, pl.y);
			
			dieTime = new Date();
			time = (int)((dieTime.getTime()-playTime.getTime())/1000);			
			}
			bao.draw(g);			
			}
		if(!pl.die) {
			g.setColor(Color.blue);
			Font f = new Font("����", Font.BOLD, 60);
			g.setFont(f);
			g.drawString("����˼�����ˣ�"+time+"��", 300, 300);
		}
		}
		g.setColor(c);
	}
	
	class xiancheng extends Thread{//����߳̿��԰����Ƿ������ػ�����
		@Override
		public void run() {
		while(true) {
			repaint();//�ػ��������Ե��ϱߵ�paint����Ϊ�����Զ���ϵͳ���ģ��Ҳ��ܵ�
			
			try {
				Thread.sleep(40);//1s = 1000ms	40ms��һ��
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			}
		}
	}
	//���̼������ڲ���
	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			pl.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			pl.minusDirection(e);
		}
		
	}
	
	/**
	 * ��ʼ������
	 */
	public void chuang() {
		this.setTitle("���ߣ�С��");//���ڱ���
		//this.setVisible(true);//����ԭ��Ĭ���ǲ��ɼ��ģ�Ϊ���ô��ڿɼ�
		this.setSize(Constant.GAME_GAO,Constant.GAME_KUAN);// ���ڵĴ�С
		this.setLocation(100, 10);//���ڵĳ���λ��
		this.setVisible(true);//����ԭ��Ĭ���ǲ��ɼ��ģ�Ϊ���ô��ڿɼ�
        //���ӹرմ��ڼ����������û�������Ͻǹر�ͼ�꣬���Թر���Ϸ����
		this.addWindowListener/*��ʾ���Ӵ��ڼ����¼�*/(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);//��ʾӦ����������
			}
		});
		new xiancheng().start();//�����߳�
		addKeyListener(new KeyMonitor());//���������Ӽ��̵ļ���
		
		//���ڵ���ʼ��
		for(int i = 0; i<50;i++) {
			paodan p= new paodan();
			paodans.add(p);
			}
	}
	public static void main(String[] args) {
		System.out.println("123");
		MyGame zz = new MyGame();//��������
		zz.chuang();//��������ĳ�ʼ������
	}
	
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(Constant.GAME_GAO,Constant.GAME_KUAN);//������Ϸ���ڵĿ�Ⱥ͸߶�
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}  
	
}









