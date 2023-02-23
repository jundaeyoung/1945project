package project;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
적군이 폭발돼서 죽으면 아이템을 랜덤으로 
패스트 아이템은 2분의 1 확률로
목숨 아이템은 5분의 1 확률로 뿌리기
*/

public class Item extends JLabel implements Moveable {

	private AirplaneFrame mContext;
	private BackgroundItemService backgroundItemService;
	Enemy enemy = new Enemy(mContext);
	
	int itemX;
	int itemY;

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
		getInitLayout();
		// 만들면 생성하기
//		BackgroundItemService = new BackgroundItemService(this);
		initThread();
	}

	// 임시 이미지
	private void initData() {
		fastIcon = new ImageIcon("images/fastIcon.png");
		recoverIcon = new ImageIcon("images/recoverIcon.png");
	}

	private void getInitLayout() {
		itemX = mContext.getEnemy().getX();
		itemY = mContext.getEnemy().getY();

		setIcon(fastIcon);
		setIcon(recoverIcon);
		setSize(30, 30);
	}

	private void initThread() {

	}
	
	public void randomItem(){
		
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

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
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