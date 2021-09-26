
package MyGame.CZY2;

import java.awt.Graphics;
import java.awt.Image;

/**
 * @author 陈忠原
 * @date 2020年4月29日
 * @version 1.0
 */
public class baozha {
	 double x,y;
	 
	    static Image[] imgs = new Image[16];
	    static {
	        for(int i=0;i<16;i++){
	            imgs[i] = GameUtil.getImage("D:/java/IDEAproject/Study/src/MyGame/tu/explode/e"+(i+1)+".gif");
	            imgs[i].getWidth(null);
	        }
	    }
	     
	    int count;
	     
	    public void draw(Graphics g){
	        if(count<=15){
	            g.drawImage(imgs[count], (int)x, (int)y, null);
	            count++;
	        }
	    }
	     
	    public baozha(double x,double y){
	        this.x = x;
	        this.y = y;
	    }
}
