package MyGame.CZY2;
 
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
 
/**
 * @author ����ԭ
 * @date 2020��4��16��
 * @version 1.0
 * GameUtil�ࣺ����ͼƬ����
 */
public class GameUtil {
    // ��������ý�������˽�л���
    private GameUtil() {
     
    } 
 /*
  * ����ָ��·���ļ���ͼƬ����
  */
    public static Image getImage(String path) {
        BufferedImage bi = null;
        try {
            URL u = GameUtil.class.getClassLoader().getResource(path);
            bi = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }
}