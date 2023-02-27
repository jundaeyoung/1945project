package project_list;

import javax.swing.ImageIcon;

public class EnemyUnit1 extends Enemy {

	AirplaneFrame mContext;

	public EnemyUnit1(AirplaneFrame mContext) {
		super(mContext);
		this.mContext = mContext;
		initData();
		setInitLayout();
		
		myIndex = enemyCount;
		enemyCount++;
	}

	public void initData() {
		x = 0;
		y = 0;
		hp = 5;
		speed = 1;
		downSpeed = 1;
		attackSpeed = 1000;
		point = 2000;
		alive = 0;
		enemyImage = new ImageIcon("imagesProject/enemy1.png");
	}

	private void setInitLayout() {
		setSize(480, 170);
		setIcon(enemyImage);
	}

	@Override
	public void down() {
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
					Thread.sleep(30);
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

	@Override
	public void attack(int attackSpeed) {
		// 게임 중일 때만 공격함
//		if (mContext.getGameState() == 1) {
			new Thread(() -> {
				while (alive == 0) {
					for (int i = 0; i < 5; i++) {
						EnemyBullet enemyBullet = new EnemyBullet(mContext, myIndex);
						mContext.add(enemyBullet);
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					try {
						Thread.sleep(attackSpeed);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}).start();
//		}
	}

	
}
