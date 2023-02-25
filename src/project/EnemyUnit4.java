package project;

import java.util.Random;

import javax.swing.ImageIcon;

public class EnemyUnit4 extends Enemy2 {
	
	
	// 생성자
	public EnemyUnit4(EnemyTestFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		
	}
	
	private void initData() {
		speed = 3; // 이동속도
		hp = 2; // 생명력, 0이 되면 alive = 1;
		alive = 0; // 살아 있는 상태
		enemyImage = new ImageIcon("imagesProject/enemy4.png");
		
		// x 위치는 arrayMove 메서드에서
		
		// 초기 y 위치는 일단 고정 (임시)
	}
	
	private void setInitLayout() {
		setSize(100, 100);
		// 다른 메서드에서 setLocation(x, y);
		setIcon(enemyImage);
	}
	
}
