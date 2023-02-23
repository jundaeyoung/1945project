package project;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundEnemyService implements Runnable {
	
	private BufferedImage image;
	private Enemy enemy;

	public BackgroundEnemyService(Enemy enemy) {
		this.enemy = enemy;

		try {
			image = ImageIO.read(new File("imagesProject/backgroundService.png"));
		} catch (IOException e) {
			System.out.println("백그라운드 플레이어 서비스 객체에 사용하는 이미지 경로 및 파일명 확인!");
		}
	}

	@Override
	public void run() {
		while (true) {
			Color leftColor = new Color(image.getRGB(enemy.getX() - 20, enemy.getY()));
			Color rightColor = new Color(image.getRGB(enemy.getX() + 55, enemy.getY()));
			Color bottomColor = new Color(image.getRGB(enemy.getX() + 10, enemy.getY() + 150));
//			Color highColor = new Color(image.getRGB(enemy.getX() + 10, enemy.getY() + 20));

			if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
//				System.out.println("현재 상태는 왼쪽 벽에 충돌했어요!!!");
				enemy.setLeft(false);
				enemy.setLeftWallCrash(true);
			} else if ((rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0)) {
//				System.out.println("현재 상태는 오른쪽 벽에 충돌 했어요");
				enemy.setRight(false);
				enemy.setRightWallCrash(true);
			} else if (bottomColor.getRed() == 255 && bottomColor.getGreen() == 0 && bottomColor.getBlue() == 0) {
//				System.out.println("현재 상태는 바닥벽에 충돌했어요");
				enemy.setDown(false);
				enemy.setDownWallCrash(true);
//			} else if (highColor.getRed() == 255 && highColor.getGreen() == 0 && highColor.getBlue() == 0) {
//				System.out.println("현재 위벽에 충돌했어요");
//				enemy.setUp(false);
//				enemy.setUpWallCrash(true);
			} else {
				enemy.setLeftWallCrash(false);
				enemy.setRightWallCrash(false);
				enemy.setDownWallCrash(false);
			}
		
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}// end