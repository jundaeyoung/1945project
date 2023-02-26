package project_list;

import javax.swing.ImageIcon;

public class EnemyUnit3 extends Enemy {
	
	AirplaneFrame mContext;
	
	
	// 생성자
	public EnemyUnit3(AirplaneFrame mContext) {
		super(mContext);
		this.mContext = mContext;
		initData();
		setInitLayout();
		
		myIndex = enemyCount;
		enemyCount++;
	}
	
	private void initData() {
		hp = 2;
		speed = 2; // 이동속도
		attackSpeed = 3000;
		point = 1000;
		alive = 0; // 살아 있는 상태
		point = 1000;
		enemyImage = new ImageIcon("imagesProject/enemy3.png");
		
	}
	
	private void setInitLayout() {
		setSize(225, 160);
		setLocation(x, y);
		setIcon(enemyImage);
	}
	
	@Override
	public void attack(int attackSpeed) {
		super.attack(attackSpeed);
		// 게임 중일 때만 공격함
		if (mContext.getGameState() == 1) {
			new Thread(() -> {
				while (alive == 0) {
					// 연발 쏘고 3초 쉬기
					EnemyBullet enemyBullet;
					
					for (int i = 0; i < 2; i++) {
						enemyBullet = new EnemyBullet(mContext, myIndex);
						mContext.add(enemyBullet);
						
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					try {
						Thread.sleep(attackSpeed);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}).start();
		}
	}
}
