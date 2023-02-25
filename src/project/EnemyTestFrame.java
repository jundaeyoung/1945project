package project;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class EnemyTestFrame extends JFrame {

	private EnemyTestFrame mContext = this;

	Timer timer;
	long delay; // 메서드 호출 지연 시간

	// 생성자
	public EnemyTestFrame() {
		initData();
		setInitLayout();
		createEnemy();
		createEnemy2();
		
		

	} // end of 생성자

	// 생성자 메서드 1
	private void initData() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(950, 950);

		// 메서드 호출 지연시간을 주기 위해 Timer 메서드 활용
		timer = new Timer();

	}

	// 생성자 메서드 2
	public void setInitLayout() {
		setLayout(null); // 좌표기반
		setResizable(false); // 창 크기 조절 기능( 거짓 )
		setLocationRelativeTo(null); // JFrame 가운데 배치
		setVisible(true);
	}

	// 생성자 메서드 - 적군 소환 흐름
	public void createEnemy() {
//		// 1개의 enemyUnit3 생성
//		unit3OneMove();

		delay = 1000L;
		TimerTask task1 = new TimerTask() {
			@Override
			public void run() {
//				 1개의 enemyUnit3 생성
				unit3OneMove();
				
//				unit3ArrayMove();
			}
		};
		timer.schedule(task1, delay);
	}

	// 생성자 메서드 - 적군 소환 흐름
	public void createEnemy2() {

		TimerTask task2 = new TimerTask() {
			@Override
			public void run() {
				// 4개의 enemyUnit4 생성
				unit4ArrayMove();
				
			}
		};
		delay = 1000L;
		//
		timer.schedule(task2, delay, 1000);
	}

	// 유닛3 하나를 소환하는 메서드
	public void unit3OneMove() {
		EnemyUnit3 unit3 = new EnemyUnit3(mContext);

		// y 위치 고정
		unit3.setY(70);

		// 초기 x 위치는 랜덤
		Random random = new Random();
		unit3.setX(random.nextInt(700) + 50);
		unit3.setLocation(unit3.getX(), unit3.getY());

		mContext.add(unit3);

		// 랜덤한 방향으로 이동
		unit3.randomDirection();

	}

	// 유닛3 배열을 소환하는 메서드
	public void unit3ArrayMove() {
		// 한 번에 3개씩 소환됨
		EnemyUnit3[] units = new EnemyUnit3[3];

		// x 위치를 배열 값으로 넣기
		int[] intArrX = { 200, 500, 350 };

		// y 위치를 배열 값으로 넣기
		int[] intArrY = { 150, 150, 50 };

		for (int i = 0; i < units.length; i++) {
			// 인스턴스화
			units[i] = new EnemyUnit3(mContext);
			// x 값 세팅
			units[i].setX(intArrX[i]);
			// y 값 세팅 (동일하게)
			units[i].setY(intArrY[i]);
			// 위치시키기
			units[i].setLocation(units[i].getX(), units[i].getY());
			mContext.add(units[i]);
		}
		for (int i = 0; i < units.length; i++) {
			units[i].down(units[i].getSpeed());
		}
		
	}

	// 유닛4 배열을 소환하는 메서드
	public void unit4ArrayMove() {
		// 한 번에 4개씩 소환됨
		EnemyUnit4[] units = new EnemyUnit4[4];

		// x 위치를 배열 값으로 넣기
		int[] intArrX = { 110, 310, 510, 710 };

		for (int i = 0; i < units.length; i++) {
			// 인스턴스화
			units[i] = new EnemyUnit4(mContext);
			// x 값 세팅
			units[i].setX(intArrX[i]);
			// y 값 세팅 (동일하게)
			units[i].setY(80);
			// 위치시키기
			units[i].setLocation(units[i].getX(), units[i].getY());
			mContext.add(units[i]);
		}

		// 백그라운드서비스

		for (int i = 0; i < units.length; i++) {
			units[i].down(units[i].getSpeed());
		}
		
	}

	public static void main(String[] args) {
		new EnemyTestFrame();

	}

}
