package project_list;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundPlayerService implements Runnable {

	private BufferedImage image;
	private Player player;

	public BackgroundPlayerService(Player player) {
		this.player = player;

		try {
			image = ImageIO.read(new File("imagesProject/backgroundService.png"));
		} catch (IOException e) {
			System.out.println("백그라운드 플레이어 서비스 객체에 사용하는 이미지 경로 및 파일명 확인!");

		}

	}

	@Override
	public void run() {
		while (true) {
			Color leftColor = new Color(image.getRGB(player.getX() - 20, player.getY()));
			Color rightColor = new Color(image.getRGB(player.getX() + 40, player.getY()));
			Color bottomColor = new Color(image.getRGB(player.getX() - 20, player.getY() + 60));
			Color highColor = new Color(image.getRGB(player.getX() + 40, player.getY()));

			if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0
					&& player.playerWay == PlayerWay.LEFT) {
//				System.out.println("현재 상태는 왼쪽 벽에 충돌했어요!!!");
				// 벽에 충돌했다.
				player.setLeft(false);
				player.setLeftWallCrash(true);
			} else if ((rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0
					&& player.playerWay == PlayerWay.RIGHT)) {
//				System.out.println("현재 상태는 오른쪽 벽에 충돌 했어요");
				player.setRight(false);
				player.setRightWallCrash(true);
			} else if (bottomColor.getRed() == 255 && bottomColor.getGreen() == 0 && bottomColor.getBlue() == 0
					&& player.playerWay == PlayerWay.DOWN) {
//				System.out.println("현재 상태는 바닥벽에 충돌했어요");
				player.setDown(false);
				player.setDownWallCrash(true);

			} else if (highColor.getRed() == 255 && highColor.getGreen() == 0 && highColor.getBlue() == 0
					&& player.playerWay == PlayerWay.UP) {
//				System.out.println("현재 위벽에 충돌했어요");
				player.setUp(false);
				player.setUpWallCrash(true);
			} else {
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
				player.setUpWallCrash(false);
				player.setDownWallCrash(false);

			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}// end