package project;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EnemyBullet extends JLabel implements Moveable {

	private int x;
	private int y;

	private boolean down;

	private int state;
	private ImageIcon enemyBullet;
	private ImageIcon boom;
	private ImageIcon gost;

	private AirplaneFrame mContext;
	private BackgroundEnemyBulletService backgroundenemyBulletService;

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
		state = 0;

	}

	public void setInitLayout() {
		x = mContext.getEnemy().getX() + 30;
		y = mContext.getEnemy().getY() + 50;
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
		Life life = new Life(mContext);
		Player player = new Player(mContext);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player.beAttack();
		mContext.repaint();
		System.out.println("추락합니다.");
		if (mContext.getPlayer().getLife() == 0) {
			setIcon(boom);
			state = 1;
			mContext.remove(mContext.getPlayer());
			mContext.repaint();
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
			if (Math.abs(x - mContext.getPlayer().getX()) < 10 && Math.abs(y - mContext.getPlayer().getY()) < 50) {
				if (mContext.getPlayer().getStatus() == 0) {
					crash();
				}
			}
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}