package tanchi.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tanchi.cn.itcast.util.FrameUtil;

public class SnakeView extends JPanel {
	
	//地图的宽
	public static final int WIDTH = 40;
	//地图的高
	public static final int HEIGHT = 30;
	
	//格子宽
	public static final int CELLWIDTH = 20;
	//格子高
	public static final int CELLHEIGHT = 20;
	

	//地图
	private boolean[][] background = new boolean[HEIGHT][WIDTH];
	
	//使用集合保存蛇节点的所有信息
	LinkedList<Point> snake = new LinkedList<Point>();
	
	//初始化蛇
	public void initSnake(){
		int x = WIDTH/2;
		int y = HEIGHT/2;
		snake.addFirst(new Point(x-1,y));
		snake.addFirst(new Point(x,y));
		snake.addFirst(new Point(x+1,y));
	}
	
	//使用四个常量表示四个方向
	public static final int UP_DIRECTION = 1;	//上
	
	public static final int DOWN_DIRECTION = -1;	//下
	
	public static final int LEFT_DIRECTION = 2;	//左
	
	public static final int RIGHT_DIRECTION = -2;	//右
	
	//蛇当前的方向
	int currentDirection = -2;	//蛇默认是向右行走
	
	//记录游戏是否结束
	static boolean isGameOver = false;	//默认游戏没有结束
	//食物
	Point food;
	
	//生成食物
	public void createFood(){
		Random  random = new Random();
		while(true){
			int x = random.nextInt(WIDTH);



			int y = random.nextInt(HEIGHT);
			
			if(background[y][x]!=background[HEIGHT-1][0]||background[y][x]!=background[0][WIDTH-1]||background[y][x]!=background[HEIGHT-1][WIDTH-1]){
				//System.out.println(x + " ," +y);
				food = new Point(y,x);
				//System.out.println("食物x:"+x+"食物y:"+y);
				break;
			}
		}
	}
	
	//改变当前方向的方法
	public void changeDirection(int newDirection){
		//判断新方向是否与当前方向是否是相反，才允许其改变
		if(newDirection+currentDirection!=0){
			this.currentDirection = newDirection;
		}
	}

	//吃食物
	public boolean eat(){

		//获取到原来的蛇头
		Point head = snake.getFirst();
		//System.out.println("x的坐标："+head.x+"y的坐标："+head.y);
		//System.out.println("食物x:"+food.x+"食物y:"+food.y);
		if(food.y ==head.x&&food.x ==head.y){
			return true;
		}
		return false;
	}
	//蛇移动的方法
	public void move(){
		Point head = snake.getFirst();
		//蛇是根据当前的方向移动的
	
		switch (currentDirection) {
			case UP_DIRECTION:
				//添加新的蛇头
				if(head.y == 0){
					//添加新的蛇头
					snake.addFirst(new Point(head.x,HEIGHT-1));
				}else{
					snake.addFirst(new Point(head.x,head.y-1));
				}
				break;
			case DOWN_DIRECTION:
				if(head.y == HEIGHT-1){
					//添加新的蛇头
					snake.addFirst(new Point(head.x,0));
				}else{
					snake.addFirst(new Point(head.x,head.y+1));
				}
				break;
			case LEFT_DIRECTION:
				if(head.x == 0){
					//添加新的蛇头
					snake.addFirst(new Point(WIDTH-1,head.y));
				}else{
					snake.addFirst(new Point(head.x-1,head.y));
				}
				break;
			case RIGHT_DIRECTION:
				if(head.x == WIDTH-1){
					snake.addFirst(new Point(0,head.y));
				}else{
					//添加新的蛇头
					snake.addFirst(new Point(head.x+1,head.y));
				}
				break;
			default:
				break;
		}
		
		if(eat()){
			createFood();
			//repaint();
		}else{
			//删除蛇尾
			snake.removeLast();
		}
		
	}
	
	

	//初始化地图
	public void iniBackground(){
		for(int rows = 0; rows<background.length; rows++){
			for(int cols = 0; cols<background[rows].length; cols++){
				if(rows==0||rows==HEIGHT-1||cols==0||cols==(WIDTH-1)){//第一行与最后一行，第一列与最后一列
					background[rows][cols] = true;
				}
			}
		}
	}
	
	
	@Override
	public void paint(Graphics g) {
		
		//画地图
		for(int rows = 0; rows<background.length; rows++){
			for(int cols = 0; cols<background[rows].length; cols++){
				if(background[rows][cols]){
					//石头
					g.setColor(Color.gray);
				}else{
					//空地
					g.setColor(Color.WHITE);
				}
				//画矩形
				g.fill3DRect(cols*CELLWIDTH, rows*CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
				
			}
		}
				
		//画蛇
		//画蛇身
		g.setColor(Color.GREEN);
		for(int i = 1; i<snake.size(); i++){
			Point body = snake.get(i);
			g.fill3DRect(body.x*CELLWIDTH, body.y*CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
		}
		//取出蛇头
		Point head = snake.getFirst();
		g.setColor(Color.RED);
		g.fill3DRect(head.x*CELLWIDTH, head.y*CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
		
		//画食物
		g.setColor(Color.GREEN);
		g.fill3DRect(food.y*CELLWIDTH, food.x*CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
		
		
		if(isGameOver){
			g.setColor(Color.RED);
			g.setFont(new Font("正楷", Font.BOLD, 50));
			g.drawString("GAME OVER", 250, 300);
		}
		
		
		/*if(eat()){
			g.setColor(Color.GREEN);
			g.fill3DRect(food.y*CELLWIDTH, food.x*CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
			
		}else{
			//删除蛇尾
			snake.removeLast();
		}
		*/
	}
	
	//游戏结束方法
	public void isGameOver(){
		//撞墙死亡
		Point head = snake.getFirst();
		if(background[head.y][head.x]==true){
			isGameOver = true;
		}
		
		//蛇咬到自己
		for(int i = 1; i<snake.size(); i++){
			Point body = snake.get(i);
			if(head.equals(body)){
				isGameOver = true;
			}
		}
		
		
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("贪吃蛇");
		final SnakeView  snakeView = new SnakeView();
		snakeView.iniBackground();
		snakeView.initSnake();
		snakeView.createFood();
		//snakeView.repaint();
		
		frame.add(snakeView);
		FrameUtil.initFrame(frame, WIDTH*CELLWIDTH+19, HEIGHT*CELLHEIGHT+48);
	
		new Thread(new Runnable(){
			public synchronized void run(){
				while(!isGameOver){
					//写每次移动的方向与距离方法
					snakeView.move();
					snakeView.repaint();
					snakeView.isGameOver();
					if(isGameOver){
						snakeView.isGameOver = true;
					}
					try{
						//死循环内，每隔100毫秒运行一次
						Thread.sleep(380);
					}catch(Exception e){
						
					}
				}
			}
		}).start();
		

		//给窗口添加事件监听
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				switch (code) {
					case KeyEvent.VK_UP:
						snakeView.changeDirection(UP_DIRECTION);
						//重画游戏
					
						break;
					case KeyEvent.VK_DOWN:
						snakeView.changeDirection(DOWN_DIRECTION);
						//重画游戏
//						snakeView.move();
//						snakeView.repaint();
						break;
					case KeyEvent.VK_LEFT:
						snakeView.changeDirection(LEFT_DIRECTION);
//						snakeView.move();
//						//重画游戏
//						snakeView.repaint();
						break;
					case KeyEvent.VK_RIGHT:
						snakeView.changeDirection(RIGHT_DIRECTION);
//						snakeView.move();
//						//重画游戏
//						snakeView.repaint();
						break;
					default:
						break;
					}
				//snakeView.repaint();//调用repaint()方法的时候实际上就是调用了paint方法	
				
				
			if(!snakeView.isGameOver){
				snakeView.move();
				snakeView.repaint();
				snakeView.isGameOver();
					if(isGameOver){
						snakeView.isGameOver = true;
					}
				}
			else{
				System.exit(0);
			}
				
			
			}
			
			
			
		});
		
	}
	
	
}
