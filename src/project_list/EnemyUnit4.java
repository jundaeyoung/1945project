package project_list;

import java.util.Random;

import javax.swing.ImageIcon;

public class EnemyUnit4 extends Enemy {
	
	AirplaneFrame mContext;
	
	// 생성자
	public EnemyUnit4(AirplaneFrame mContext) {
		super(mContext);
		this.mContext = mContext;
		initData();
		setInitLayout();
		
		myIndex = enemyCount;
		enemyCount++;
	}
	
	private void initData() {
		hp = 2;
		speed = 3; // 이동속도
		attackSpeed = 3000;
		point = 500;
		alive = 0; // 살아 있는 상태
		enemyImage = new ImageIcon("imagesProject/enemy4.png");
		
	}
	
	private void setInitLayout() {
		setSize(100, 100);
		// 다른 메서드에서 setLocation(x, y);
		setIcon(enemyImage);
	}
	
	
}
