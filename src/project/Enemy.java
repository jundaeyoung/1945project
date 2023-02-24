package project;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy extends JLabel implements Moveable {

	private AirplaneFrame mContext;

	// 프레임 사이즈
	final int FRAME_SIZE_X = 950;
	final int FRAME_SIZE_Y = 950;

	// 생존 여부 (살아 있음 : 0, 죽음 : 1)
	private int alive;

	// 위치 상태
	private int x;
	private int y;

	// 플레이어가 한번에 죽는 현상 방지
	private int attackCount;

	// 적군의 방향
	private EnemyWay enemyWay;

	// 움직임 상태 (우선 좌우만 구현 // 움직이지 않음 : false)
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	private boolean downWallCrash;
	private boolean upWallCrash;

	// 적군 속도 상태
	private final int SPEED = 2;

	// 이미지
	private ImageIcon enemyImage;

	// 생성자
	public Enemy(AirplaneFrame mContext) {
		this.mContext = mContext;

		initData();
		setInitLayout();

		// 적군의 방향 결정 + 이동 메서드 호출
		enemyDirection();

	}

<<<<<<< HEAD
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

=======
>>>>>>> 89b8daa3faa4770108969c32b4457a0f6d9d7787
	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setLeftWallCrash(boolean leftWallCrash) {
		this.leftWallCrash = leftWallCrash;
	}

	public void setRightWallCrash(boolean rightWallCrash) {
		this.rightWallCrash = rightWallCrash;
	}

	public void setUpWallCrash(boolean upWallCrash) {
		this.upWallCrash = upWallCrash;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getAlive() {
		return alive;
	}

	public void setAlive(int alive) {
		this.alive = alive;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isDownWallCrash() {
		return downWallCrash;
	}

	public void setDownWallCrash(boolean downWallCrash) {
		this.downWallCrash = downWallCrash;
	}

	// 생성자 메서드 1
	private void initData() {
		enemyImage = new ImageIcon("imagesProject/enemy.png");
		// 초기 x 위치는 랜덤
		double randomX = Math.random(); // 0~1 범위의 소수 난수 생성
		x = (int) ((FRAME_SIZE_X - 200) * randomX);

		// 초기 y 위치는 일단 고정 (임시)
		y = 80;

		// 초기 값 세팅 (기본값이라서 생략 가능)
		left = false;
		right = false;
		alive = 0; // 살아 있음
		attackCount = 1;
	}

	// 생성자 메서드 2
	private void setInitLayout() {
		setSize(150, 150); // 임시 크기
		setLocation(x, y);
		setIcon(enemyImage);

	}

//	 생성자 메서드 3
	private void enemyDirection() {

		Random random = new Random();

		new Thread(() -> {
			// enemy가 살아있는 동안
			while (alive == 0) {

				// 플레이어보다 적군이 더 아래쪽에 있으면 up()
				if (mContext.getPlayer().getY() < this.getY()) {
					up();
					if (upWallCrash == true) {
						down();
					}
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
					left();
//					if (downWallCrash == true) {
//						up();
//					}
					down();

					// 값이 1인 경우 오른쪽으로
				} else {
					// 오른쪽 벽에 부딪친 상태면 right() 실행 X
					if (rightWallCrash == true) {
						continue;
					}
					right();
//					if (downWallCrash == true) {
//						up();
//					}
					down();
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
	public void left() {

		// 적군의 현재 x 좌표 저장
		int currentX = this.getX();

		// 최대 이동 거리 (여유 50)
		int tempX = currentX - 50;

		// 이동할 거리
		int goX = (int) (currentX * Math.random());

		new Thread(() -> {
			left = true;
			for (int i = 0; i < (goX / SPEED); i++) {
				// 적군이 죽었거나, 왼쪽 벽에 부딪치면 중단
				if (alive == 1 || left == false) {
					return;
				}
				x = x - SPEED;
				setLocation(x, y);

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		left = false;
		// player와 부딧히면 player공격
		if (Math.abs(x - mContext.getPlayer().getX() - 40) < 50 && Math.abs(y - mContext.getPlayer().getY()) < 50) {
			if (mContext.getPlayer().getAlive() == 0) {
				crash();

			}
		}
	} // end of left

	@Override
	public void right() {

		int currentX = this.getX();

		// 최대 이동 거리 (200 = 아이콘 크기 + 여유 50)
		int tempX = (FRAME_SIZE_X - 200) - currentX;

		// 이동할 거리
		int goX = (int) (tempX * Math.random());

		new Thread(() -> {
			right = true;
			for (int i = 0; i < (goX / SPEED); i++) {
				// 적군이 죽었거나, 오른쪽 벽에 부딪치면 중단
				if (alive == 1 || right == false) {
					return;
				}
				x = x + SPEED;
				setLocation(x, y);

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			attack();
		}).start();
		right = false;
		if (Math.abs(x - mContext.getPlayer().getX() - 40) < 50 && Math.abs(y - mContext.getPlayer().getY()) < 50) {
			if (mContext.getPlayer().getAlive() == 0) {
				crash();

			}
		}
	} // end of right

	// up 메서드는 아래 벽과 충돌했거나, 플레이어보다 적군이 아래쪽에 있을 때만 실행
	@Override
	public void up() {

		new Thread(() -> {
			up = true;
			for (int i = 0; i < (100 / SPEED); i++) {
				// 적군이 죽었거나, 위쪽 벽에 부딪치면 중단
				if (alive == 1 || up == false) {
					return;
				}
				y = y - SPEED;
				setLocation(x, y);

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			attack();
			up = false;
		}).start();
		if (Math.abs(x - mContext.getPlayer().getX() - 40) < 50 && Math.abs(y - mContext.getPlayer().getY()) < 50) {
			if (mContext.getPlayer().getAlive() == 0) {
				crash();

			}
		}
	}

	@Override
	public void down() {
		new Thread(() -> {
			down = true;
			for (int i = 0; i < 20; i++) {
				// 적군이 죽었거나, 아래쪽 벽에 부딪치면 중단
				if (alive == 1 || down == false) {
					return;
				}
				y = y + SPEED;
				setLocation(x, y);

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			down = false;
		}).start();
		if (Math.abs(x - mContext.getPlayer().getX() - 40) < 50 && Math.abs(y - mContext.getPlayer().getY()) < 50) {
			if (mContext.getPlayer().getAlive() == 0) {
				crash();

			}
		}
	} // end of down

	public void crash() {
		if (attackCount == 1) {
			attackCount--;
			mContext.getPlayer().beAttack();
			mContext.repaint();

			if (mContext.getPlayer().getLife() == 0) {
				mContext.remove(mContext.getPlayer());
				mContext.repaint();
			}
		}
	}

	public void attack() {
<<<<<<< HEAD
		// 게임 중일 때만 공격함
		if (mContext.getGameState() == 1) {
			EnemyBullet enemyBullet = new EnemyBullet(mContext);
			mContext.add(enemyBullet);
		}

=======
		EnemyBullet enemyBullet = new EnemyBullet(mContext);
		mContext.add(enemyBullet);
>>>>>>> 89b8daa3faa4770108969c32b4457a0f6d9d7787
	}

} // end of class
