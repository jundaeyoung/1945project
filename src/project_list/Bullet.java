package project_list;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bullet extends JLabel implements Moveable {

	private int x;
	private int y;

	private boolean up;

	private int state;
	private ImageIcon bullet;
	private ImageIcon boom;

	private AirplaneFrame mContext;

//	private BackgroundBulletService backgroundBulletService;

	private Item item;
	private Enemy targetEnemy;

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
		targetEnemy.setAlive(1);
		state = 1;
		mContext.remove(targetEnemy);
		System.out.println("3333");
//		setIcon(boom);
		System.out.println("111111");
//		mContext.getItem().setDown(true);
//		mContext.getItem().down();
//		mContext.repaint();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setIcon(null);

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
					if (Math.abs(x - (enemy.getX()+250)) < 230 && (y - enemy.getY()) < 100) {
						enemy.beattacked();
						targetEnemy = enemy;
						crash();
						setIcon(boom);
						item();
					}

				} else if (enemy instanceof EnemyUnit2 && enemy.getAlive() == 0) {
					if (Math.abs(x - (enemy.getX() + 15)) < 15 && (y - enemy.getY()) < 15) {
						enemy.beattacked();
						targetEnemy = enemy;
						crash();
						setIcon(boom);
						item();
					}

				} else if (enemy instanceof EnemyUnit3 && enemy.getAlive() == 0) {
					if (Math.abs(x - (enemy.getX() + 100)) < 100 && (y - enemy.getY()) < 90) {
						enemy.beattacked();
						targetEnemy = enemy;
						crash();
						setIcon(boom);
						item();
					}	

				} else if (enemy instanceof EnemyUnit4 && enemy.getAlive() == 0) {
					if (Math.abs(x - (enemy.getX() + 43)) < 45 && (y - enemy.getY()) < 45) {
						enemy.beattacked();
						targetEnemy = enemy;
						crash();
						setIcon(boom);
						item();
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
