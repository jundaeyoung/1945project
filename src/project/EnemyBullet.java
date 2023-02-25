package project;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EnemyBullet extends JLabel implements Moveable {

	private int x;
	private int y;

	private boolean down;

	private int state;
	private int attackCount;

	private ImageIcon enemyBullet;
	private ImageIcon boom;
	private ImageIcon player;

	private AirplaneFrame mContext;
	private BackgroundEnemyBulletService backgroundenemyBulletService;

	public ImageIcon getEnemyBullet() {
		return enemyBullet;
	}

	public void setEnemyBullet(ImageIcon enemyBullet) {
		this.enemyBullet = enemyBullet;
	}

	public EnemyBullet(AirplaneFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		backgroundenemyBulletService = new BackgroundEnemyBulletService(this);
		initThread();
	}

	public void initData() {
		enemyBullet = new ImageIcon("imagesProject/bullet4.png");
		boom = new ImageIcon("imagesProject/explosion.gif");
		player = new ImageIcon("imagesProject/BigPlane2.png");
		state = 0;
		// 1로 계속 초기화
		attackCount = 1;
		mContext.getPlayer().setIcon(player);

	}

	public void setInitLayout() {
		
		// + 수정 : 자료형에 따라 보정
//		if (mContext.getEnemy() instanceof EnemyUnit1) {
//			x = mContext.getEnemy().getX() + 240;
//			y = mContext.getEnemy().getY() + 80;
			
//		} else if (mContext.getEnemy() instanceof EnemyUnit2) {
//			x = mContext.getEnemy().getX() + 10;
//			y = mContext.getEnemy().getY() + 10;
//			
//		} else if (mContext.getEnemy() instanceof EnemyUnit3) {
//			x = mContext.getEnemy().getX() + 107;
//			y = mContext.getEnemy().getY() + 80;
//			
//		} else if (mContext.getEnemy() instanceof EnemyUnit4) {
//			x = mContext.getEnemy().getX() + 43;
//			y = mContext.getEnemy().getY() + 24;
//			
//		}
		
		// 기존 버전
		x = mContext.getEnemy().getX() + 27;
		y = mContext.getEnemy().getY() + 45;
		setIcon(enemyBullet);
		setSize(100, 100);
		setLocation(x, y);
	}

	public void initThread() {
		new Thread(new Runnable() {
			public void run() {
				down();
			}
		}).start();
	}

	public void crash() {
//      Player player = new Player(mContext);

		if (attackCount == 1) {
			attackCount--;
			mContext.getPlayer().beAttack();
			System.out.println("공격?");
			setIcon(null);
			if (mContext.getPlayer().getLife() == 0) {
				setIcon(boom);
				state = 1;
				mContext.remove(mContext.getPlayer());
			}
		}

	}

	@Override
	public void left() {

	}

	@Override
	public void right() {

	}

	@Override
	public void up() {

	}

	@Override
	public void down() {
		down = true;
		while (true) {
			y++;
			setLocation(x, y);
			if (Math.abs(x - mContext.getPlayer().getX() - 40) < 50 && Math.abs(y - mContext.getPlayer().getY()) < 50) {
				if (mContext.getPlayer().getAlive() == 0) {
				crash();
				}
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();

			}
			// 화면 밖으로 총알이 나가면 사라짐
			if (y == 840) {
				setIcon(null);
			}
		}

	}
}