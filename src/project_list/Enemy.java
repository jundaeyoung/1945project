package project_list;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy extends JLabel implements Moveable {

	protected AirplaneFrame mContext;

	// 프레임 사이즈
	final int FRAME_SIZE_X = 950;
	final int FRAME_SIZE_Y = 950;

	// 적군 이동속도 -> 생성자에서 초기화
	protected int speed;

	protected int downSpeed = 1;

	// 적군 공격속도 (attack 메서드의 쓰레드 sleep 값으로 조정)
	protected int attackSpeed;

	// 적군 생명력
	protected int hp;
	
	// 적군 점수
	protected int point;

	// 생존 여부 (살아 있음 : 0, 죽음 : 1, 범위 벗어나서 사라짐 : 2)
	protected int alive;

	// 이미지 -> 생성자에서 초기화
	protected ImageIcon enemyImage;

	// 위치 상태 -> 생성자에서 초기화
	protected int x;
	protected int y;

	// 움직임 상태 (움직이지 않음 : false)
	protected boolean left;
	protected boolean right;
	protected boolean down;
	protected boolean up;

	// 벽에 충돌한 상태
	protected boolean leftWallCrash;
	protected boolean rightWallCrash;
	protected boolean downWallCrash;

	protected static int enemyCount; // 생성된 적군의 총 숫자
	protected int myIndex; // 자신이 생성된 순서

	public Enemy(AirplaneFrame mContext) {
		this.mContext = mContext;
	}

	protected void randomDirection() {
		Random random = new Random();

		new Thread(() -> {
			// enemy가 살아있는 동안
			while (alive == 0) {

				// 이동 방향을 랜덤으로 선택함
				int randomDirection = random.nextInt(3); // 0~2 생성

				// 이동 메서드 안에 적군이 죽으면 중간에 중단하라는 if 문이 있어서
				// 죽으면 left(), right() 메서드를 빠져나간 뒤 반복이 종료됨

				// 값이 0인 경우 왼쪽으로
				if (randomDirection == 0) {
					// 왼쪽 벽에 부딪친 상태면 left() 실행 X
					if (leftWallCrash == true) {
						continue;
					}
					left(speed);

					// 값이 1인 경우 오른쪽으로
				} else if (randomDirection == 1) {
					// 오른쪽 벽에 부딪친 상태면 right() 실행 X
					if (rightWallCrash == true) {
						continue;
					}
					right(speed);

					// 그 외의 값은 아래쪽으로
				} else {
					down(speed);
				}

				try {
					Thread.sleep(600);
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

		// 최대 이동 거리
		int tempX = currentX - 100;

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
					Thread.sleep(5);
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

		// 최대 이동 거리
		int tempX = (FRAME_SIZE_X - 250) - currentX;

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
			right = false;
		}).start();
	} // end of right

	@Override
	public void down(int speed) {
		new Thread(() -> {
			down = true;
			while (down) {
				// 적군이 죽으면 중단
				if (alive == 1) {
					return;
				}
				y = y + downSpeed;
				setLocation(x, y);

				contact();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (downWallCrash == true) {
					alive = 2;
				}
				if (y == 950) {
					setIcon(null);
				}
			}
//			down = false;
		}).start();
	} // end of down

	public void crash() {
		mContext.getPlayer().beAttack();

		if (mContext.getPlayer().getLife() == 0) {
			mContext.remove(mContext.getPlayer());
		}
	}

	public void attack(int attackSpeed) {
		// 게임 중일 때만 공격함
		if (mContext.getGameState() == 1) {
			new Thread(() -> {
				while (alive == 0) {
					EnemyBullet enemyBullet = new EnemyBullet(mContext, myIndex);
					mContext.add(enemyBullet);
					try {
						Thread.sleep(attackSpeed);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}).start();
		}
	}

	public void beattacked() {
//		setIcon(boom);
		setLocation(x, y);
	}

	// 적군과 플레이어가 접촉하면 플레이어의 목숨이 하나 감소함
	public void contact() {
		if (this instanceof EnemyUnit1 && mContext.getPlayer().getAlive() == 0) {
			if (Math.abs((x + 220) - mContext.getPlayer().getX()) < 200
					&& Math.abs((y + 50) - mContext.getPlayer().getY()) < 40) {
				crash();
			}

		} else if (this instanceof EnemyUnit2 && mContext.getPlayer().getAlive() == 0) {
			if (Math.abs(x - (mContext.getPlayer().getX())) < 30
					&& Math.abs((y + 10) - mContext.getPlayer().getY()) < 20) {
				crash();
			}

		} else if (this instanceof EnemyUnit3 && mContext.getPlayer().getAlive() == 0) {
			if (Math.abs((x + 100) - mContext.getPlayer().getX()) < 100
					&& Math.abs((y + 40) - mContext.getPlayer().getY()) < 40) {
				crash();
			}

		} else if (this instanceof EnemyUnit4 && mContext.getPlayer().getAlive() == 0) {
			if (Math.abs(x - mContext.getPlayer().getX()) < 40
					&& Math.abs((y + 25) - mContext.getPlayer().getY()) < 30) {
				crash();
			}

		}

	} // end of method

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getAlive() {
		return alive;
	}
	
	public void setAlive(int alive) {
		this.alive = alive;
	}
	
	public boolean isLeft() {
		return left;
	}
	
	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public boolean isRight() {
		return right;
	}
	
	public void setRight(boolean right) {
		this.right = right;
	}
	
	public boolean isUp() {
		return up;
	}
	
	public void setUp(boolean up) {
		this.up = up;
	}
	
	public boolean isDown() {
		return down;
	}
	
	public void setDown(boolean down) {
		this.down = down;
	}
	
	public boolean isLeftWallCrash() {
		return leftWallCrash;
	}
	
	public void setLeftWallCrash(boolean leftWallCrash) {
		this.leftWallCrash = leftWallCrash;
	}
	
	public boolean isRightWallCrash() {
		return rightWallCrash;
	}
	
	public void setRightWallCrash(boolean rightWallCrash) {
		this.rightWallCrash = rightWallCrash;
	}
	
	public int getMyIndex() {
		return myIndex;
	}
	
	public void setMyIndex(int myIndex) {
		this.myIndex = myIndex;
	}
	
	public boolean isDownWallCrash() {
		return downWallCrash;
	}
	
	public void setDownWallCrash(boolean downWallCrash) {
		this.downWallCrash = downWallCrash;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getAttackSpeed() {
		return attackSpeed;
	}
	
	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	
} // end of class
