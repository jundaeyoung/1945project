package project;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
적 비행기가 죽으면 아이템을 랜덤한 값에 따라 밑으로 뿌림
그 아이템을 Player가 먹었을 때 ( fastIcon을 먹으면 fast가 증가, 
recoverIcon 먹으면 생명력 추가 : 최대 목숨 3개 !상수로 선언!)
*/

// Bullet 클래스에 적군과 충돌했을 때 아이템 객체를 new 하고 화면에 붙여야 됨
public class Item extends JLabel implements Moveable {

	ItemWay itemWay;

	private AirplaneFrame mContext;
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

	public Item(AirplaneFrame mContext) {
		this.mContext = mContext;

		initData();
		setInitLayout();

//		createRandomItem();
//		initThread();
	}

	private void initData() {
		fastIcon = new ImageIcon("imagesProject/fastIcon.png");
		recoverIcon = new ImageIcon("imagesProject/recoverIcon.png");
	}

	private void setInitLayout() {
		itemX = mContext.getEnemy().getX();
		itemY = mContext.getEnemy().getY();

		setLocation(itemX, itemY);
		setSize(100, 100);
	}

	// 랜덤 아이템 생성
	public void itemDirection() {
		while (true) {
			if (mContext.getEnemy().getAlive() == 1) {
				setLocation(itemX, itemY);
				mContext.add(this);

				// 확률적으로 최소 20
				int itemChance = (int) (Math.random() * 100) + 20;
				System.out.println("적 잡을 때 생기는 값 : " + itemChance);

				// 확률적으로 fastItemVal가 recoveItemVal보다 더 많이 나옴
				// 확률이 20보다 작으면 아이템 나오지 않음
				int fastItemVal = 80;
				int recoverItemVal = 50;

				if (itemChance >= fastItemVal) {
					setLocation(itemX, itemY);
					setIcon(fastIcon);
					down();

					return;
				} else if (itemChance >= recoverItemVal) {
					setLocation(itemX, itemY);
					setIcon(recoverIcon);
					down();

					return;
				}

			}
		}
	}

	public boolean isLeftWallCrash() {
		return leftWallCrash;
	}

	public void setLeftWallCrash(boolean leftWallCrash) {
		this.leftWallCrash = leftWallCrash;
	}

	public boolean isRightWallCrash() {
		return rightWallCrash;
	}

	public void setRightWallCrash(boolean rightWallCrash) {
		this.rightWallCrash = rightWallCrash;
	}

	public boolean isUpWallCrash() {
		return upWallCrash;
	}

	public void setUpWallCrash(boolean upWallCrash) {
		this.upWallCrash = upWallCrash;
	}

	public boolean isDownWallCrash() {
		return downWallCrash;
	}

	public void setDownWallCrash(boolean downWallCrash) {
		this.downWallCrash = downWallCrash;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
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
	public synchronized void down() {
//		itemDirection();

		down = true;

		while (true) {
			itemY++;
			setLocation(itemX, itemY);

			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (Math.abs(itemX - mContext.getPlayer().getX()) < 50
					&& Math.abs(itemY - mContext.getPlayer().getY()) < 50) {

				// setSpeed에서 += 사용하기
				mContext.getPlayer().setSpeed(5);
				setIcon(null);
				return;
			}
			if (Math.abs(itemX - mContext.getPlayer().getX()) < 50
					&& Math.abs(itemY - mContext.getPlayer().getY()) < 50) {
//				mContext.getLife(); 
//				mContext.getPlayer().setLife(life++);
//				mContext.getPlayer().setLife(alive);
				setIcon(null);
				return;
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
