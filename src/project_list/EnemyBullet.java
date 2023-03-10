package project_list;

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

	private Enemy targetEnemy;

	public ImageIcon getEnemyBullet() {
		return enemyBullet;
	}

	public void setEnemyBullet(ImageIcon enemyBullet) {
		this.enemyBullet = enemyBullet;
	}

	public EnemyBullet(AirplaneFrame mContext, int targetEnemyIndex) {
		this.mContext = mContext;
		try {
			this.targetEnemy = this.mContext.getEnemyList().get(targetEnemyIndex);			
		} catch (Exception e) {
			System.out.println("게임 종료 후 적군 총 발사");
		}
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
		setIcon(enemyBullet);
		setSize(100, 100);

	}

	public void setInitLayout() {

		// + 수정 : 자료형에 따라 보정 (발사 위치)
		if (targetEnemy instanceof EnemyUnitOne) {
			x = targetEnemy.getX() + 240;
			y = targetEnemy.getY() + 80;
		

		} else if (targetEnemy instanceof EnemyUnitTwo) {
			x = targetEnemy.getX() + 10;
			y = targetEnemy.getY() + 10;
	

		} else if (targetEnemy instanceof EnemyUnitThree) {
			x = targetEnemy.getX() + 107;
			y = targetEnemy.getY() + 80;
		

		} else if (targetEnemy instanceof EnemyUnitFour) {
			x = targetEnemy.getX() + 43;
			y = targetEnemy.getY() + 24;
		
		}
		

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
				mContext.getEnemyList().clear();
				Enemy.enemyCount = 0;
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
		while (down) {
			y++;
			
			// 화면 밖으로 총알이 나가면 사라짐
			if (y == 840) {
				setIcon(null);
				down = false;
				return;
			}
			
			setLocation(x, y);
			if (Math.abs(x - mContext.getPlayer().getX() - 30) < 40 && Math.abs(y - mContext.getPlayer().getY() - 10) < 30) {
				if (mContext.getPlayer().getAlive() == 0) {
					crash();
				}
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();

			}
		}

	}
}