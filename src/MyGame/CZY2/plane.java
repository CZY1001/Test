
package MyGame.CZY2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 * @author ����ԭ
 * @date 2020��4��20��
 * @version 1.0
 */
public class plane extends GameObject{
	
	
	 boolean shang,xia,zuo,you;
	 
	 boolean die = true ;
	public void drawSelf(Graphics g) {
		
		if(die) {
		g.drawImage(img, (int)x,(int) y, null);
		
		if(zuo) {
			x-= sudu;
		}
		if(you) {
			x+=sudu;
		}
		if(shang) {
			 y-= sudu;
		}
		if(xia) {
			y+= sudu;
		}
	}else {
		
	}
}
	public plane(Image img,double x,double y) {
		this.img = img;
		this.x = x;
		this .y = y;
		this.sudu = 10;
		this.kuan = img .getWidth(null);
		this.gao = img .getHeight(null);
	}
	
	//����ĳ������������Ӧ�ķ���
	public void addDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			zuo = true;
			break;
		case KeyEvent.VK_UP:
			shang = true;
			break;
		case KeyEvent.VK_RIGHT:
			you = true;
			break;
		case KeyEvent.VK_DOWN:
			xia = true;
			break;

		}
	}
	//����ĳ������ȡ����Ӧ�ķ���
	public void minusDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			zuo = false;
			break;
		case KeyEvent.VK_UP:
			shang = false; 
			break;
		case KeyEvent.VK_RIGHT:
			you = false;
			break;
		case KeyEvent.VK_DOWN:
			xia = false;
			break;

		}
	}
	
}
