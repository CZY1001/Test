package tanchi.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import tanchi.cn.itcast.util.FrameUtil;

public class Demo1 extends JPanel{	//Demo1Ҳ��һ�����
	
	@Override
	public void paint(Graphics g) {	//Graphics g ���ʣ�ʹ�øû��ʿ��Ի��κζ���
		//���û��ʵ���ɫ
		g.setColor(Color.red);
		//������
		g.fill3DRect(0, 0, 20, 20, true);
		g.fill3DRect(20, 0, 20, 20, true);
		g.setColor(Color.green);
		g.fill3DRect(20, 20, 20, 20, true);


		//д��
		g.setColor(Color.red);
		//���û��ʵ�����
		g.setFont(new Font("����", Font.BOLD, 30));
		g.drawString("GAME OVER", 250, 200);
	}
	

	
	public static void main(String[] args) {
		/*JFrame frame = new JFrame("����");
		Demo1 d = new Demo1();
		frame.add(d);
		FrameUtil.initFrame(frame, 700, 500);*/
		
		boolean[] b = new boolean[2];
		for(int i = 0; i<b.length; i++){
			System.out.print(b[i]+" ");
		}
	}

}
