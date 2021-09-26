/**
 * 
 */
package MyGame.CZY2;

import java.awt.Color;
import java.awt.Graphics;


/**
 * 炮弹类
 * @author 陈忠原
 * @date 2020年4月26日
 * @version 1.0
 */
public class paodan extends GameObject {
	
	double degree;    //弧度

	public paodan() {
		x=500;
		y= 400;
		kuan = 10;
		gao = 10;
		sudu = 3;
		degree = Math.random()*Math.PI*2;  //0到2PI的随机数
	}
	public void draw(Graphics g) {
		Color c  = g.getColor();
		g.setColor(Color.red);
	
		g.fillOval((int)x, (int)y, kuan, gao);
		
		//炮弹沿着任意角度飞
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



















