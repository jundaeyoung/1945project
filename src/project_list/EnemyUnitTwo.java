package project_list;

import javax.swing.ImageIcon;

public class EnemyUnitTwo extends Enemy{

	AirplaneFrame mContext;

	public EnemyUnitTwo(AirplaneFrame mContext) {
		super(mContext);
		this.mContext = mContext;
		initData();
		setInitLayout();
		
		myIndex = enemyCount;
		enemyCount++;
	}

	public void initData() {
		x = 0;
		y = 0;
		hp = 1;
		speed = 2;
		attackSpeed = 2500;
		point = 100;
		alive = 0;
		enemyImage = new ImageIcon("imagesProject/enemy2.png");
	}

	private void setInitLayout() {
		setSize(40, 40);
		setIcon(enemyImage);
	}

}
