package project_list;

import javax.swing.ImageIcon;

public class EnemyUnit3 extends Enemy {
	
	AirplaneFrame mContext;
	
	// 생성자
	public EnemyUnit3(AirplaneFrame mContext) {
		super(mContext);
		this.mContext = mContext;
		initData();
		setInitLayout();
		
		myIndex = enemyCount;
		enemyCount++;
	}
	
	private void initData() {
		hp = 2;
		speed = 2; // 이동속도
		alive = 0; // 살아 있는 상태
		enemyImage = new ImageIcon("imagesProject/enemy3.png");
		
		// 초기 x 위치는 랜덤
		double randomX = Math.random(); // 0~1 범위의 소수 난수 생성
		x = (int) ((FRAME_SIZE_X - 200) * randomX);

		// 초기 y 위치는 일단 고정 (임시)
		y = 80;
	}
	
	private void setInitLayout() {
		setSize(225, 160);
		setLocation(x, y);
		setIcon(enemyImage);
	}
	
	
}
