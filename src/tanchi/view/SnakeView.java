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
	
	//��ͼ�Ŀ�
	public static final int WIDTH = 40;
	//��ͼ�ĸ�
	public static final int HEIGHT = 30;
	
	//���ӿ�
	public static final int CELLWIDTH = 20;
	//���Ӹ�
	public static final int CELLHEIGHT = 20;
	

	//��ͼ
	private boolean[][] background = new boolean[HEIGHT][WIDTH];
	
	//ʹ�ü��ϱ����߽ڵ��������Ϣ
	LinkedList<Point> snake = new LinkedList<Point>();
	
	//��ʼ����
	public void initSnake(){
		int x = WIDTH/2;
		int y = HEIGHT/2;
		snake.addFirst(new Point(x-1,y));
		snake.addFirst(new Point(x,y));
		snake.addFirst(new Point(x+1,y));
	}
	
	//ʹ���ĸ�������ʾ�ĸ�����
	public static final int UP_DIRECTION = 1;	//��
	
	public static final int DOWN_DIRECTION = -1;	//��
	
	public static final int LEFT_DIRECTION = 2;	//��
	
	public static final int RIGHT_DIRECTION = -2;	//��
	
	//�ߵ�ǰ�ķ���
	int currentDirection = -2;	//��Ĭ������������
	
	//��¼��Ϸ�Ƿ����
	static boolean isGameOver = false;	//Ĭ����Ϸû�н���
	//ʳ��
	Point food;
	
	//����ʳ��
	public void createFood(){
		Random  random = new Random();
		while(true){
			int x = random.nextInt(WIDTH);



			int y = random.nextInt(HEIGHT);
			
			if(background[y][x]!=background[HEIGHT-1][0]||background[y][x]!=background[0][WIDTH-1]||background[y][x]!=background[HEIGHT-1][WIDTH-1]){
				//System.out.println(x + " ," +y);
				food = new Point(y,x);
				//System.out.println("ʳ��x:"+x+"ʳ��y:"+y);
				break;
			}
		}
	}
	
	//�ı䵱ǰ����ķ���
	public void changeDirection(int newDirection){
		//�ж��·����Ƿ��뵱ǰ�����Ƿ����෴����������ı�
		if(newDirection+currentDirection!=0){
			this.currentDirection = newDirection;
		}
	}

	//��ʳ��
	public boolean eat(){

		//��ȡ��ԭ������ͷ
		Point head = snake.getFirst();
		//System.out.println("x�����꣺"+head.x+"y�����꣺"+head.y);
		//System.out.println("ʳ��x:"+food.x+"ʳ��y:"+food.y);
		if(food.y ==head.x&&food.x ==head.y){
			return true;
		}
		return false;
	}
	//���ƶ��ķ���
	public void move(){
		Point head = snake.getFirst();
		//���Ǹ��ݵ�ǰ�ķ����ƶ���
	
		switch (currentDirection) {
			case UP_DIRECTION:
				//����µ���ͷ
				if(head.y == 0){
					//����µ���ͷ
					snake.addFirst(new Point(head.x,HEIGHT-1));
				}else{
					snake.addFirst(new Point(head.x,head.y-1));
				}
				break;
			case DOWN_DIRECTION:
				if(head.y == HEIGHT-1){
					//����µ���ͷ
					snake.addFirst(new Point(head.x,0));
				}else{
					snake.addFirst(new Point(head.x,head.y+1));
				}
				break;
			case LEFT_DIRECTION:
				if(head.x == 0){
					//����µ���ͷ
					snake.addFirst(new Point(WIDTH-1,head.y));
				}else{
					snake.addFirst(new Point(head.x-1,head.y));
				}
				break;
			case RIGHT_DIRECTION:
				if(head.x == WIDTH-1){
					snake.addFirst(new Point(0,head.y));
				}else{
					//����µ���ͷ
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
			//ɾ����β
			snake.removeLast();
		}
		
	}
	
	

	//��ʼ����ͼ
	public void iniBackground(){
		for(int rows = 0; rows<background.length; rows++){
			for(int cols = 0; cols<background[rows].length; cols++){
				if(rows==0||rows==HEIGHT-1||cols==0||cols==(WIDTH-1)){//��һ�������һ�У���һ�������һ��
					background[rows][cols] = true;
				}
			}
		}
	}
	
	
	@Override
	public void paint(Graphics g) {
		
		//����ͼ
		for(int rows = 0; rows<background.length; rows++){
			for(int cols = 0; cols<background[rows].length; cols++){
				if(background[rows][cols]){
					//ʯͷ
					g.setColor(Color.gray);
				}else{
					//�յ�
					g.setColor(Color.WHITE);
				}
				//������
				g.fill3DRect(cols*CELLWIDTH, rows*CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
				
			}
		}
				
		//����
		//������
		g.setColor(Color.GREEN);
		for(int i = 1; i<snake.size(); i++){
			Point body = snake.get(i);
			g.fill3DRect(body.x*CELLWIDTH, body.y*CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
		}
		//ȡ����ͷ
		Point head = snake.getFirst();
		g.setColor(Color.RED);
		g.fill3DRect(head.x*CELLWIDTH, head.y*CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
		
		//��ʳ��
		g.setColor(Color.GREEN);
		g.fill3DRect(food.y*CELLWIDTH, food.x*CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
		
		
		if(isGameOver){
			g.setColor(Color.RED);
			g.setFont(new Font("����", Font.BOLD, 50));
			g.drawString("GAME OVER", 250, 300);
		}
		
		
		/*if(eat()){
			g.setColor(Color.GREEN);
			g.fill3DRect(food.y*CELLWIDTH, food.x*CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
			
		}else{
			//ɾ����β
			snake.removeLast();
		}
		*/
	}
	
	//��Ϸ��������
	public void isGameOver(){
		//ײǽ����
		Point head = snake.getFirst();
		if(background[head.y][head.x]==true){
			isGameOver = true;
		}
		
		//��ҧ���Լ�
		for(int i = 1; i<snake.size(); i++){
			Point body = snake.get(i);
			if(head.equals(body)){
				isGameOver = true;
			}
		}
		
		
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("̰����");
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
					//дÿ���ƶ��ķ�������뷽��
					snakeView.move();
					snakeView.repaint();
					snakeView.isGameOver();
					if(isGameOver){
						snakeView.isGameOver = true;
					}
					try{
						//��ѭ���ڣ�ÿ��100��������һ��
						Thread.sleep(380);
					}catch(Exception e){
						
					}
				}
			}
		}).start();
		

		//����������¼�����
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				switch (code) {
					case KeyEvent.VK_UP:
						snakeView.changeDirection(UP_DIRECTION);
						//�ػ���Ϸ
					
						break;
					case KeyEvent.VK_DOWN:
						snakeView.changeDirection(DOWN_DIRECTION);
						//�ػ���Ϸ
//						snakeView.move();
//						snakeView.repaint();
						break;
					case KeyEvent.VK_LEFT:
						snakeView.changeDirection(LEFT_DIRECTION);
//						snakeView.move();
//						//�ػ���Ϸ
//						snakeView.repaint();
						break;
					case KeyEvent.VK_RIGHT:
						snakeView.changeDirection(RIGHT_DIRECTION);
//						snakeView.move();
//						//�ػ���Ϸ
//						snakeView.repaint();
						break;
					default:
						break;
					}
				//snakeView.repaint();//����repaint()������ʱ��ʵ���Ͼ��ǵ�����paint����	
				
				
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
