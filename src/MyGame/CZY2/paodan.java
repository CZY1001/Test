/**
 * 
 */
package MyGame.CZY2;

import java.awt.Color;
import java.awt.Graphics;


/**
 * �ڵ���
 * @author ����ԭ
 * @date 2020��4��26��
 * @version 1.0
 */
public class paodan extends GameObject {
	
	double degree;    //����

	public paodan() {
		x=500;
		y= 400;
		kuan = 10;
		gao = 10;
		sudu = 3;
		degree = Math.random()*Math.PI*2;  //0��2PI�������
	}
	public void draw(Graphics g) {
		Color c  = g.getColor();
		g.setColor(Color.red);
	
		g.fillOval((int)x, (int)y, kuan, gao);
		
		//�ڵ���������Ƕȷ�
		x += sudu*Math.cos(degree);
		y += sudu*Math.sin(degree);
		
		if(x<0||x>Constant.GAME_KUAN-kuan) {
			degree = Math.PI-degree;
		}
		
		if(y<30||y>Constant.GAME_GAO-gao) {
			degree = -degree;
		}
		
		
		g.setColor(c);
	
	}
	
}



















