package project_list;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundBulletService {

	private BufferedImage image;
	private Bullet bullet;

	public BackgroundBulletService(Bullet bullet) {
		this.bullet = bullet;
 
		try {
			image = ImageIO.read(new File("images/backgroundService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // end of 생성자
	
	public boolean leftWall() {
		// RGB 값을 int 값으로 확인할 수 있다.
		Color leftColor = new Color(image.getRGB(bullet.getX() + 10, bullet.getY()));
		// R 255, G 0, B 0 <-- 빨간색
		if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
			// 빨간색
			return true;
		}
		return false;
	}

	public boolean rightWall() {
		Color rightColor = new Color(image.getRGB(bullet.getX() + 60, bullet.getY()));
		if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
			// 빨간색
			return true;
		}
		return false;
	}

	public boolean topWall() {
		Color topColor = new Color(image.getRGB(bullet.getX() + 25, bullet.getY()));
		if (topColor.getRed() == 255 && topColor.getGreen() == 0 && topColor.getBlue() == 0) {
			// 빨간색
			return true;
		
			}
		return false;
	}

//	@Override
//	public void run() {
//		while (true) {
//			Color leftColor = new Color(image.getRGB(bullet.getX() + 50, bullet.getY()));
//			Color rightColor = new Color(image.getRGB(bullet.getX() + 40, bullet.getY()));
//			Color bottomColor = new Color(image.getRGB(bullet.getX() - 20, bullet.getY() + 60));
//			Color highColor = new Color(image.getRGB(bullet.getX() + 40, bullet.getY() + 10));
//
//			if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
//				System.out.println("왼쪽 벽");
//				bullet.setLeft(false);
//				bullet.setLeftWallCrash(true);
//
//			} else if ((rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0)) {
//				System.out.println("오른쪽 벽");
//				bullet.setRight(false);
//				bullet.setRightWallCrash(true);
//
//			} else if (highColor.getRed() == 255 && highColor.getGreen() == 0 && highColor.getBlue() == 0) {
//				System.out.println("위쪽 벽");
//				bullet.setUp(false);
//				bullet.setUpWallCrash(true);
//
//			} else if (bottomColor.getRed() == 255 && bottomColor.getGreen() == 0 && bottomColor.getBlue() == 0) {
//				System.out.println("아래쪽 벽");
//				bullet.setDown(false);
//				bullet.setDownWallCrash(true);
//
//			} else {
//				bullet.setLeftWallCrash(false);
//				bullet.setRightWallCrash(false);
//				bullet.setUpWallCrash(false);
//				bullet.setDownWallCrash(false);
//
//			}
//			try {
//				Thread.sleep(5);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	
	
}