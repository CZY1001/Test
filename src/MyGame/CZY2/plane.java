
package MyGame.CZY2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 * @author 陈忠原
 * @date 2020年4月20日
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
	
	//按下某个键，增加相应的方向
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
	//按下某个键，取消相应的方向
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
