package project;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundEnemyService implements Runnable {
	
	private BufferedImage image;
	private Enemy enemy;
	private Color leftColor;
	private Color rightColor;
	private Color bottomColor;

	public BackgroundEnemyService(Enemy enemy) {
		this.enemy = enemy;

		try {
			image = ImageIO.read(new File("imagesProject/backgroundService.png"));
		} catch (IOException e) {
			System.out.println("백그라운드 플레이어 서비스 객체에 사용하는 이미지 경로 및 파일명 확인!");
		}
	}

	// + 수정 : enemy는 기본적으로 up()을 사용하지 않고 바닥에 닿을 때만 up()을 사용하도록 설계했으므로, 
	// 위쪽 벽은 확인할 필요가 없어 삭제함
	
	// + 수정 : if문에 따라 보정 값을 조정했더니, 충돌 여부 확인하는 부분에서 해당 변수를 인식하지 못해서
	// 지역 변수 대신 멤벼 변수로 바꿈
	
	@Override
	public void run() {
		
		
		// + 수정 : enemy가 살아있는 동안에만 감시함
		while (enemy.getAlive() == 0) {
			
			// + 수정 : 자료형에 따라 보정 값 조정 (enemy2 기준)
			
//			if (enemy instanceof EnemyUnit1) {
//				leftColor = new Color(image.getRGB(enemy.getX() + 10, enemy.getY()));
//				rightColor = new Color(image.getRGB(enemy.getX() + 440, enemy.getY()));
//				bottomColor = new Color(image.getRGB(enemy.getX() + 10, enemy.getY() + 130));
				
//			} else if (enemy instanceof EnemyUnit2) {
//				leftColor = new Color(image.getRGB(enemy.getX() - 20, enemy.getY()));
//				rightColor = new Color(image.getRGB(enemy.getX() + 10, enemy.getY()));
//				bottomColor = new Color(image.getRGB(enemy.getX() -20, enemy.getY() + 30));
//			
//			} else if (enemy instanceof EnemyUnit3) {
//				leftColor = new Color(image.getRGB(enemy.getX() - 8, enemy.getY()));
//				rightColor = new Color(image.getRGB(enemy.getX() + 200, enemy.getY()));
//				bottomColor = new Color(image.getRGB(enemy.getX() + 10, enemy.getY() + 150));
//				
//			} else if (enemy instanceof EnemyUnit4) {
//				leftColor = new Color(image.getRGB(enemy.getX() - 20, enemy.getY()));
//				rightColor = new Color(image.getRGB(enemy.getX() + 55, enemy.getY()));
//				bottomColor = new Color(image.getRGB(enemy.getX() + 10, enemy.getY() + 70));
//				
//			}
			
			// 테스트용 enemy.png (기존 버전) 
			leftColor = new Color(image.getRGB(enemy.getX() - 20, enemy.getY()));
			rightColor = new Color(image.getRGB(enemy.getX() + 55, enemy.getY()));
			bottomColor = new Color(image.getRGB(enemy.getX() + 10, enemy.getY() + 50));

			// 충돌 여부 확인
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