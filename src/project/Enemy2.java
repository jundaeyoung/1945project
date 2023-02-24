package project;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy2 extends JLabel implements Moveable {

	protected EnemyTestFrame mContext;

	// 프레임 사이즈
	final int FRAME_SIZE_X = 950;
	final int FRAME_SIZE_Y = 950;

	// 적군 이동속도 -> 생성자에서 초기화
	protected int speed;
	
//	// 적군 공격속도
//	private int attackSpeed;
	
	// 적군 생명력 -> 생성자에서 초기화
	protected int hp;
	
	// 피격 횟수
	protected int beattackedCount;

	// 생존 여부 (살아 있음 : 0, 죽음 : 1)
	protected int alive;

	// 이미지 -> 생성자에서 초기화
	protected ImageIcon enemyImage;

	// 위치 상태 -> 생성자에서 초기화
	protected int x;
	protected int y;

	// 적군의 방향 (지워도 될듯)
	protected EnemyWay enemyWay;

	// 움직임 상태 (우선 좌우만 구현 // 움직이지 않음 : false)
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;

	// 벽에 충돌한 상태
	protected boolean leftWallCrash;
	protected boolean rightWallCrash;
	protected boolean downWallCrash;
	protected boolean upWallCrash;
	

//	// 생성자 메서드 1
//	private void initData() {
//		enemyImage = new ImageIcon("imagesProject/enemy.png");
//		// 초기 x 위치는 랜덤
//		double randomX = Math.random(); // 0~1 범위의 소수 난수 생성
//		x = (int) ((FRAME_SIZE_X - 200) * randomX);
//
//		// 초기 y 위치는 일단 고정 (임시)
//		y = 80;
//
//		// 초기 값 세팅 (기본값이라서 생략 가능)
//		left = false;
//		right = false;
//		alive = 0; // 살아 있음
//	}
//
//	// 생성자 메서드 2
//	private void setInitLayout() {
//		setSize(150, 150); // 임시 크기
//		setLocation(x, y);
//		setIcon(enemyImage);
//
//	}

	
	protected void randomDirection() {
		Random random = new Random();

		new Thread(() -> {
			// enemy가 살아있는 동안
			while (alive == 0) {

				// 아래쪽 벽에 충돌하면 up()
				if (downWallCrash == true) {
					up(speed);
				}

				// 이동 방향을 랜덤으로 선택함
				int randomDirection = random.nextInt(2); // 0 또는 1 생성

				// 이동 메서드 안에 적군이 죽으면 중간에 중단하라는 if 문이 있어서
				// 죽으면 left(), right() 메서드를 빠져나간 뒤 반복이 종료됨

				// 값이 0인 경우 왼쪽으로
				if (randomDirection == 0) {
					// 왼쪽 벽에 부딪친 상태면 left() 실행 X
					if (leftWallCrash == true) {
						continue;
					}
					left(speed);
//					if (downWallCrash == true) {
//						up();
//					}
					down(speed);

					// 값이 1인 경우 오른쪽으로
				} else {
					// 오른쪽 벽에 부딪친 상태면 right() 실행 X
					if (rightWallCrash == true) {
						continue;
					}
					right(speed);
//					if (downWallCrash == true) {
//						up();
//					}
					down(speed);
				}

				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} // end of while

		}).start();

	}

	// 이동 메서드

	@Override
	public void left(int speed) {

		// 적군의 현재 x 좌표 저장
		int currentX = this.getX();

		// 최대 이동 거리 (여유 50)
		int tempX = currentX - 50;

		// 이동할 거리
		int goX = (int) (currentX * Math.random());

		new Thread(() -> {
			left = true;
			for (int i = 0; i < (goX / speed); i++) {
				// 적군이 죽었거나, 왼쪽 벽에 부딪치면 중단
				if (alive == 1 || left == false) {
					return;
				}
				x = x - speed;
				setLocation(x, y);

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		left = false;
	} // end of left

	@Override
	public void right(int speed) {

		int currentX = this.getX();

		// 최대 이동 거리 (200 = 아이콘 크기 + 여유 50)
		int tempX = (FRAME_SIZE_X - 200) - currentX;

		// 이동할 거리
		int goX = (int) (tempX * Math.random());

		new Thread(() -> {
			right = true;
			for (int i = 0; i < (goX / speed); i++) {
				// 적군이 죽었거나, 오른쪽 벽에 부딪치면 중단
				if (alive == 1 || right == false) {
					return;
				}
				x = x + speed;
				setLocation(x, y);

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
//			attack();
		}).start();
		right = false;
	} // end of right

	// up 메서드는 아래 벽과 충돌했거나, 플레이어보다 적군이 아래쪽에 있을 때만 실행
	@Override
	public void up(int speed) {
		new Thread(() -> {
			up = true;
			for (int i = 0; i < (400 / speed); i++) {
				// 적군이 죽었거나, 위쪽 벽에 부딪치면 중단
				if (alive == 1 || up == false) {
					return;
				}
				y = y - speed;
				setLocation(x, y);

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
//			attack();
			up = false;
		}).start();
	}

	@Override
	public void down(int speed) {
		new Thread(() -> {
			down = true;
			for (int i = 0; i < 20; i++) {
				// 적군이 죽었거나, 아래쪽 벽에 부딪치면 중단
				if (alive == 1 || down == false) {
					return;
				}
				y = y + speed;
				setLocation(x, y);

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			down = false;
		}).start();
	} // end of down

	
	// 공격속도는 나중에 고려
//	public void attack() {
//		// 게임 중일 때만 공격함
//		if (mContext.getGameState() == 1) {
//			EnemyBullet enemyBullet = new EnemyBullet(mContext);
//			mContext.add(enemyBullet);
//		}
//	}

} // end of class
