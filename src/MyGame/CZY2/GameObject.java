/**
 * 
 */
package MyGame.CZY2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import org.w3c.dom.css.Rect;

/**
 * ��Ϸ����ĸ���
 * @author ����ԭ
 * @date 2020��4��19��
 * @version 1.0
 */
public class GameObject {
	 Image img;
	 double x,y;
	 int sudu;
	 int kuan,gao;
	public void drawSelf(Graphics g) {
		g.drawImage(img, (int)x,(int) y, null);
	}
	public GameObject(Image img, double x, double y, int sudu, int kuan, int gao) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		this.sudu = sudu;
		this.kuan = kuan;
		this.gao = gao;
	}
	public GameObject(Image img, double x, double y) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
	}
	
	public GameObject() {
	}
	/*
	 * �����������ڵľ��Σ����ں�������ײ���
	 */
	public Rectangle getPeng() {
		return new Rectangle((int)x,(int) y, kuan, gao);
	}
}









