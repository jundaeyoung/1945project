package project_list;

import javax.swing.JFrame;

public class EnemyTestFrame2 extends JFrame {

	private EnemyTestFrame2 mContext = this;

	// private EnemyUnit1 enemy;
	// private EnemyUnit1_1 enemyUnit1_1;

	// 생성자
	public EnemyTestFrame2() {
		initData();
		setInitLayout();
		// unit1arrayMove();
		unit2arrayMove();
	} // end of 생성자

	// 생성자 메서드 1
	private void initData() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(950, 950);

		// enemy = new EnemyUnit1(this);
		// enemyUnit1_1 = new EnemyUnit1_1(this, -80);
	}

	// 생성자 메서드 2
	public void setInitLayout() {
		setLayout(null); // 좌표기반
		setResizable(false); // 창 크기 조절 기능( 거짓 )
		setLocationRelativeTo(null); // JFrame 가운데 배치
		setVisible(true);

		// add(enemy);
		// add(enemyUnit1_1);
	}

	public void unit1arrayMove() {
		EnemyUnit1[] units = new EnemyUnit1[2];

		int[] intArr1 = { 5, 470 };
		int[] intArr2 = { 100, -50 };

		for (int i = 0; i < units.length; i++) {
//			units[i] = new EnemyUnit1(mContext);
			units[i].setX(intArr1[i]);
			units[i].setY(intArr2[i]);
			units[i].setLocation(units[i].getX(), units[i].getY());
			mContext.add(units[i]);
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					for (int i = 0; i < units.length; i++) {
						units[i].down(units[i].getSpeed());
						try {
							Thread.sleep(150);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}

	public void unit2arrayMove() {
		EnemyUnit2[] units = new EnemyUnit2[4];

		int[] intArr1 = { 100, 150, 200, 250 };
		int[] intArr2 = { 80, 110, 140, 170 };

		for (int i = 0; i < units.length; i++) {
//			units[i] = new EnemyUnit2(mContext);
			units[i].setX(intArr1[i]);
			units[i].setY(intArr2[i]);
			units[i].setLocation(units[i].getX(), units[i].getY());
			mContext.add(units[i]);
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					for (int i = 0; i < units.length; i++) {
						units[i].down(units[i].getSpeed());
						try {
							Thread.sleep(35);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}

	public static void main(String[] args) {
		new EnemyTestFrame2();

	}

}
