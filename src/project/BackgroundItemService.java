package project;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundItemService implements Runnable {

	private BufferedImage image;
	private Item item;

	public BackgroundItemService(Item item) {
		this.item = item;

		try {
			image = ImageIO.read(new File("imagesProject/backgroundService.png"));
		} catch (IOException e) {
			System.out.println("백그라운드 플레이어 서비스 객체에 사용하는 이미지 경로 및 파일명 확인!");

		}

	}

	@Override
	public void run() {
		while (true) {
			Color leftColor = new Color(image.getRGB(item.getX() - 20, item.getY()));
			Color rightColor = new Color(image.getRGB(item.getX() + 40, item.getY()));
			Color bottomColor = new Color(image.getRGB(item.getX() - 20, item.getY() + 60));
			Color highColor = new Color(image.getRGB(item.getX() + 40, item.getY() + 10));

			if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0
					&& item.itemWay == ItemWay.LEFT) {
				System.out.println("현재 상태는 왼쪽 벽에 충돌했어요!!!");
				// 벽에 충돌했다.
				item.setLeft(false);
				item.setLeftWallCrash(true);
			} else if ((rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0
					&& item.itemWay == ItemWay.RIGHT)) {
				System.out.println("현재 상태는 오른쪽 벽에 충돌 했어요");// 오른쪽벽에 충돌함 !
				item.setRight(false);
				item.setRightWallCrash(true);
			} else if (bottomColor.getRed() == 255 && bottomColor.getGreen() == 0 && bottomColor.getBlue() == 0
					&& item.itemWay == ItemWay.DOWN) {
				System.out.println("현재 상태는 바닥벽에 충돌했어요");
				item.setDown(false);
				item.setDownWallCrash(true);

			} else if (highColor.getRed() == 255 && highColor.getGreen() == 0 && highColor.getBlue() == 0
					&& item.itemWay == ItemWay.UP) {
				System.out.println("현재 위벽에 충돌했어요");
				item.setUp(false);
				item.setUpWallCrash(true);
			} else {
				item.setLeftWallCrash(false);
				item.setRightWallCrash(false);
				item.setUpWallCrash(false);
				item.setDownWallCrash(false);

			}
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}// end
