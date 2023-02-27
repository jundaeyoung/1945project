package project_list;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bullet extends JLabel implements Moveable {

	private int x;
	private int y;

	private boolean up;

	// 메인 프레임의 score에 추가될 점수
	private int score;

	private int state;
	private ImageIcon bullet;
	private ImageIcon boom;

	private AirplaneFrame mContext;

//	private BackgroundBulletService backgroundBulletService;

	private Item item;
	private Enemy targetEnemy;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Bullet(AirplaneFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
//		backgroundBulletService = new BackgroundBulletService(this);
		initThread();
	}

	public void initData() {
		bullet = new ImageIcon("imagesProject/PlayerBullet1.png");
		boom = new ImageIcon("imagesProject/explosion.gif");
		state = 0;
		score = 0;
		up = false;
	}

	public void setInitLayout() {
		x = mContext.getPlayer().getX() + 20;
		y = mContext.getPlayer().getY();
		setIcon(bullet);
		setSize(80, 60);
		setLocation(x, y);
	}

	public synchronized void initThread() {
		new Thread(new Runnable() {
			public void run() {
				up();
			}
		}).start();
	}

	public void crash() {

		// 한 대 맞으면 Hp 1 감소
		targetEnemy.setHp(targetEnemy.getHp() - 1);
		// 총알이 사라진 상태
		state = 1;
		System.out.println("적군 피격");
		setIcon(null);

		// Hp가 0이 된다면
		if (targetEnemy.getHp() == 0) {
			targetEnemy.setAlive(1);
			mContext.remove(targetEnemy);
			System.out.println("적군 사망");

			// 확률에 따라 아이템 생성됨
			item();

			// 점수 : 현재 점수 + 잡은 유닛의 점수
			score = mContext.getScore() + targetEnemy.getPoint();
			mContext.setScore(score);
//			mContext.setScore(score);
			System.out.println("score : " + score);
		}

//		System.out.println("");
//		setIcon(boom);
//		mContext.getItem().setDown(true);
//		mContext.getItem().down();
//		mContext.repaint();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void item() {
		item = new Item(mContext, targetEnemy);
		item.initThread();

	}

	@Override
	public void left() {

	}

	@Override
	public void right() {

	}

	@Override
	public synchronized void up() {
		up = true;
		while (true) {
			y--;
			setLocation(x, y);

			for (Enemy enemy : mContext.getEnemyList()) {
				if (enemy instanceof EnemyUnit1 && enemy.getAlive() == 0) {
					if (Math.abs(x - (enemy.getX() + 250)) < 230 && Math.abs(y - (enemy.getY() + 60)) < 30) {
						enemy.beattacked();
						targetEnemy = enemy;
						if (this.state == 0) {

							crash();
							setIcon(boom);
						}
					}

				} else if (enemy instanceof EnemyUnit2 && enemy.getAlive() == 0) {
					if (Math.abs(x - (enemy.getX() + 10)) < 15 && Math.abs(y - enemy.getY()) < 15) {
						enemy.beattacked();
						targetEnemy = enemy;
						if (this.state == 0) {

							crash();
							setIcon(boom);
						}
					}

				} else if (enemy instanceof EnemyUnit3 && enemy.getAlive() == 0) {
					if (Math.abs(x - (enemy.getX() + 100)) < 100 && Math.abs(y - (enemy.getY() + 80)) < 30) {
						enemy.beattacked();
						targetEnemy = enemy;
						if (this.state == 0) {

							crash();
							setIcon(boom);
						}
					}

				} else if (enemy instanceof EnemyUnit4 && enemy.getAlive() == 0) {
					if (Math.abs(x - (enemy.getX() + 43)) < 45 && Math.abs(y - enemy.getY()) < 30) {
						enemy.beattacked();
						targetEnemy = enemy;
						if (this.state == 0) {

							crash();
							setIcon(boom);
						}
					}
				}
			} // end of for

			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (y == 0) {
				setIcon(null);
				break;
			}
		}
	}

	@Override
	public void down() {

	}

}