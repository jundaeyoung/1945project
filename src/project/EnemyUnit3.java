package project;

import javax.swing.ImageIcon;

public class EnemyUnit3 extends Enemy2 {
	
	// 생성자
	public EnemyUnit3(EnemyTestFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		
		// 이동 패턴 : 랜덤으로 움직임
		this.randomDirection();
		
	}
	
	private void initData() {
		speed = 1; // 이동속도
		hp = 3; // 생명력, 0이 되면 alive = 1;
		alive = 0; // 살아 있는 상태
		enemyImage = new ImageIcon("imagesProject/enemy3.png");
		
		// 초기 x 위치는 랜덤
		double randomX = Math.random(); // 0~1 범위의 소수 난수 생성
		x = (int) ((FRAME_SIZE_X - 200) * randomX);

		// 초기 y 위치는 일단 고정 (임시)
		y = 80;
	}
	
	private void setInitLayout() {
		setSize(220, 160);
		setLocation(x, y);
		setIcon(enemyImage);
	}
	
	
}
