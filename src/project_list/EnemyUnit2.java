package project_list;

import javax.swing.ImageIcon;

public class EnemyUnit2 extends Enemy{

	AirplaneFrame mContext;

	public EnemyUnit2(AirplaneFrame mContext) {
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
		hp = 1;
		speed = 2;
		downSpeed = 2;
		attackSpeed = 2500;
		point = 100;
		alive = 0;
		enemyImage = new ImageIcon("imagesProject/enemy2.png");
	}

	private void setInitLayout() {
		setSize(40, 40);
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
}
