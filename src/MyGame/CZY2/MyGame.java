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
 * 飞机游戏的主窗口
 * @author 陈忠原
 * @date 2020年4月16日
 * @version 1.0
 */
public class MyGame extends Frame{

	Image fj = GameUtil.getImage("D:/java/IDEAproject/Study/src/MyGame/tu/fj.png");//将图片定义为成员变量
	Image bj = GameUtil.getImage("D:/java/IDEAproject/Study/src/MyGame/tu/bj.jpg");
	
	plane pl =new plane(fj,250,20);


	 ArrayList<paodan>  paodans= new ArrayList<paodan>();//容器对象存储多发炮弹
	
	baozha bao;//创建爆炸对象
	Date playTime = new Date();//创建开始时间
	Date dieTime;
	int time;//游戏持续的时间
	
	@Override
	public void paint(Graphics g) {//paint的作用是画出整个窗口及内部内容。被系统自动调用。g相当于画笔
		Color c = g.getColor();
		
		g.drawImage(bj, 0, 0, null);
		pl.drawSelf(g);//画飞机
		
		//画出容器中所有的炮弹
		for(int i=0;i<paodans.size();i++) {
			paodan p = paodans.get(i);
			p.draw(g);
			
			//飞机与炮弹的碰撞检测
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
			Font f = new Font("黑体", Font.BOLD, 60);
			g.setFont(f);
			g.drawString("你个菜鸡存活了："+time+"秒", 300, 300);
		}
		}
		g.setColor(c);
	}
	
	class xiancheng extends Thread{//这个线程可以帮我们反复的重画窗口
		@Override
		public void run() {
		while(true) {
			repaint();//重画，不可以调上边的paint，因为他是自动被系统调的，我不能调
			
			try {
				Thread.sleep(40);//1s = 1000ms	40ms画一次
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			}
		}
	}
	//键盘监听的内部类
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
	 * 初始化窗口
	 */
	public void chuang() {
		this.setTitle("作者：小陈");//窗口标题
		//this.setVisible(true);//窗口原本默认是不可见的，为了让窗口可见
		this.setSize(Constant.GAME_GAO,Constant.GAME_KUAN);// 窗口的大小
		this.setLocation(100, 10);//窗口的出现位置
		this.setVisible(true);//窗口原本默认是不可见的，为了让窗口可见
        //增加关闭窗口监听，这样用户点击右上角关闭图标，可以关闭游戏程序
		this.addWindowListener/*表示增加窗口监听事件*/(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);//表示应用正常结束
			}
		});
		new xiancheng().start();//启动线程
		addKeyListener(new KeyMonitor());//给窗口增加键盘的监听
		
		//给炮弹初始化
		for(int i = 0; i<50;i++) {
			paodan p= new paodan();
			paodans.add(p);
			}
	}
	public static void main(String[] args) {
		System.out.println("123");
		MyGame zz = new MyGame();//创建对象
		zz.chuang();//调用上面的初始化窗口
	}
	
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(Constant.GAME_GAO,Constant.GAME_KUAN);//这是游戏窗口的宽度和高度
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}  
	
}









