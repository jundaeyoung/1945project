package project_list;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
적 비행기가 죽으면 아이템을 랜덤한 값에 따라 밑으로 뿌림
그 아이템을 Player가 먹었을 때 ( fastIcon을 먹으면 fast가 증가, 
recoverIcon 먹으면 생명력 추가 : 최대 목숨 3개 !상수로 선언!)
*/

// Bullet 클래스에 적군과 충돌했을 때 아이템 객체를 new 하고 화면에 붙여야 됨
public class Item extends JLabel implements Moveable {

	Random rd = new Random();
	private AirplaneFrame mContext;
	ItemWay itemWay;

	private Item item;

	private int itemX;
	private int itemY;

	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	private boolean leftWallCrash;
	private boolean rightWallCrash;
	private boolean upWallCrash;
	private boolean downWallCrash;

	// 적군이 살아있는 상태 : 0, 죽었을 때 : 1
	private int alive;

	private ImageIcon fastIcon;
	private ImageIcon recoverIcon;
	
	private Enemy targetEnemy;

	int itemChance = rd.nextInt(110);
	// 확률적으로 fastItemVal가 recoveItemVal보다 더 많이 나옴
	// 확률이 20보다 작으면 아이템 나오지 않음
	int fastItemVal = 80;
	int recoverItemVal = 50;

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	// 생성자
	public Item(AirplaneFrame mContext, Enemy targetEnemy) {
		this.mContext = mContext;
		this.targetEnemy = targetEnemy;
		
		initData();
		setInitLayout();
		
		// 유닛별로 아이템 위치 보정해야 함
		if (targetEnemy instanceof EnemyUnitOne) {
			itemX = targetEnemy.getX() + 220;
			itemY = targetEnemy.getY();			
		} else if (targetEnemy instanceof EnemyUnitTwo) {
			itemX = targetEnemy.getX();
			itemY = targetEnemy.getY();	
		} else if (targetEnemy instanceof EnemyUnitThree) {
			itemX = targetEnemy.getX() + 100;
			itemY = targetEnemy.getY();	
		} else if (targetEnemy instanceof EnemyUnitFour) {
			itemX = targetEnemy.getX() + 20;
			itemY = targetEnemy.getY();	
		}
		

//		createRandomItem();
		initThread();
	}

	private void initData() {
		down = false;
		fastIcon = new ImageIcon("imagesProject/fastIcon.png");
		recoverIcon = new ImageIcon("imagesProject/recoverIcon.png");
	}

	private void setInitLayout() {

	}

	public void initThread() {
		new Thread(new Runnable() {
			public void run() {
				down();
			}
		}).start();
	}

	@Override
	public void down() {
//		itemDirection();

		if (itemChance >= fastItemVal) {
			setIcon(fastIcon);
			setSize(100, 100);
			setLocation(itemX, itemY);
		} else if (itemChance >= recoverItemVal) {
			setIcon(recoverIcon);
			setSize(100, 100);
			setLocation(itemX, itemY);

		}
		down = true;
		mContext.add(this);

		while (down) {
			setLocation(itemX, itemY++);
//			System.out.println("2313123");
			if (Math.abs(itemX - mContext.getPlayer().getX()) < 50
					&& Math.abs(itemY - mContext.getPlayer().getY()) < 50) {
				setIcon(null);
				if (itemChance >= fastItemVal) {
					mContext.getPlayer().setSpeed(2);
					down = false;
					return;
				} else if (itemChance >= recoverItemVal) {
					if (mContext.getPlayer().getLife() == 2) {
						mContext.getLife2().lifeUp();
						System.out.println("생명력 1번");

					} else if (mContext.getPlayer().getLife() == 1) {
						mContext.getLife1().lifeUp();
						System.out.println("생명력 2번째 ");

					}
					down = false;

				}


			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
			if (itemY == 840) {
				System.out.println("0999");
				setIcon(null);
				down = false;
				return;
			}
//			mContext.add(this);

		}
	}
}
