package project;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
적 비행기가 죽으면 아이템을 랜덤한 값에 따라 밑으로 뿌림
그 아이템을 Player가 먹었을 때 ( fastIcon을 먹으면 fast가 증가, 
recoverIcon 먹으면 생명력 추가 : 최대 목숨 3개 !상수로 선언!)
*/

public class Item extends JLabel implements Moveable {

	private AirplaneFrame mContext;
	private Item item;

	private int itemX;
	private int itemY;

	private boolean down;

	// 적군이 살아있는 상태 : 0, 죽었을 때 : 1
	private int alive;

	private ImageIcon fastIcon;
	private ImageIcon recoverIcon;

	public Item(AirplaneFrame mContext) {
		this.mContext = mContext;

		initData();
		setInitLayout();
//		createRandomItem();
		initThread();
	}

	private void initData() {
		fastIcon = new ImageIcon("imagesProject/fastIcon.png");
		recoverIcon = new ImageIcon("imagesProject/recoverIcon.png");
	}

	private void setInitLayout() {
		itemX = mContext.getEnemy().getX();
		itemY = mContext.getEnemy().getY();

//		int limitMin = 50;
//		int limitMax = 900;
//
//		itemX = (int) (Math.random() * limitMax - limitMin);
//		if (itemX <= limitMin) {
//			itemX = limitMin;
//		}
//		itemY = (int) (Math.random() * limitMax);

		setLocation(itemX, itemY);
		setSize(80, 80);
	}

	private void initThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				itemDirection();
				down();
			}
		}).start();
	}

	// 랜덤 아이템 생성
	public void itemDirection() {

		while (true) {
			if (mContext.getEnemy().getAlive() == 1) {
				// ItemX, ItemY 좌표값을 Enemy X, Y좌표값으로 세팅
				this.setItemX(mContext.getEnemy().getX());
				this.setItemY(mContext.getEnemy().getY());

				setLocation(itemX, itemY);
				mContext.add(this);

				// 확률적으로 최소 20
				int itemChance = (int) (Math.random() * 100) + 20;
				System.out.println("적 잡을 때 생기는 값 : " + itemChance);

				// 확률적으로 fastItemVal가 recoveItemVal보다 더 많이 나옴
				// 확률이 20보다 작으면 아이템 나오지 않음
				int fastItemVal = 40;
				int recoverItemVal = 80;

				if (itemChance >= fastItemVal) {
					setIcon(fastIcon);
					return;
				} else if (itemChance >= recoverItemVal) {
					setIcon(recoverIcon);
				}

				down();

			}
		}

	}

	public void plusSpeed() {
		if (itemX == mContext.getPlayer().getX() && itemY == mContext.getPlayer().getY()) {
			mContext.getPlayer().setSpeed(5);
			setIcon(null);
		}
	}

	// 라이프 이미지 아이콘 다시 추가하는 방법
	public void plusRecover() {
		if (itemX == mContext.getPlayer().getX() && itemY == mContext.getPlayer().getY()) {
//			mContext.getLife(); 
//			mContext.getPlayer().setLife(life++);
//			mContext.getPlayer().setLife(alive);
			setIcon(null);
		}
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public void left() {
		// TODO Auto-generated method stub

	}

	@Override
	public void right() {
		// TODO Auto-generated method stub

	}

	@Override
	public void up() {
		// TODO Auto-generated method stub

	}

	@Override
	public void down() {
		itemDirection();

		down = true;

		while (true) {
			itemY++;
			setLocation(itemX, itemY);
			if (Math.abs(itemX - mContext.getEnemy().getX()) < 10
					&& Math.abs(itemY - mContext.getEnemy().getY()) < 50) {
				if (mContext.getEnemy().getAlive() == 1) {
					down();
				}
			}

			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public int getItemX() {
		return itemX;
	}

	public void setItemX(int itemX) {
		this.itemX = itemX;
	}

	public int getItemY() {
		return itemY;
	}

	public void setItemY(int itemY) {
		this.itemY = itemY;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public int getAlive() {
		return alive;
	}

	public void setAlive(int alive) {
		this.alive = alive;
	}

	public ImageIcon getFastIcon() {
		return fastIcon;
	}

	public void setFastIcon(ImageIcon fastIcon) {
		this.fastIcon = fastIcon;
	}

}
