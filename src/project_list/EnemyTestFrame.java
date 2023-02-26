package project_list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class EnemyTestFrame extends JFrame {

	private EnemyTestFrame mContext = this;

	Timer timer;
	long delay; // 메서드 호출 지연 시간
	
	
	// ########## 메인 프레임의 멤버변수 #############
	// 이 두 변수 외에 enemy 관련 다른 변수는 선언하지 않아도 됨
	// 생성된 적군 객체들을 담을 리스트
	// 생성될 때마다 순서대로 리스트에 담김
	ArrayList<Enemy> enemyList = new ArrayList<>();

	// 다른 클래스에서 적군 객체 각각에 접근할 수 있어야함
	// (현재 전부 mContext.getEnemy()로 적군 각각에 접근해서 사용하고 있으므로)
	
	// 그러려면 각각의 인덱스를 알아야 하기 때문에,
	// key와 value를 사용하는 Map에 인덱스 정보를 저장하면 어떨까 생각해봄
	HashMap<String, int[]> enemyIndexMap = new HashMap<>();
	
	
	
	// 첫 번째 적군 소환 메서드 
	private void FirstEnemy() {
		// 먼저, 해당 적군 소환 메서드가 인덱스 몇 번부터 몇 번까지의 적군을 다루는 것인지 맵에 저장하기 위해
		
		// 적군 객체를 담기 전에 지금 리스트의 사이즈를 확인함
		// (사이즈 == 0이면 인덱스 0번부터 객체가 추가될 것이고, 사이즈 == 1이면 인덱스 1번부터 객체가 추가될 것임)
		int index1 = enemyList.size();
		
		// 만약 이 메서드에서 적군을 3마리 소환할거라면
		int enemyCount = 3;
		int index2 = index1 + (enemyCount - 1);
		
		// 인덱스를 저장할 배열 (선언과 동시에 초기화)
		int[] indexArray = {index1, index2};

		// 맵에 인덱스 정보를 저장함
		enemyIndexMap.put("1번째 적군", indexArray);
		
//		// 적군 객체 생성
//		for (int i = 0; i < enemyCount; i++) {
//			enemyList.add(new EnemyUnit3(mContext));  // 객체를 리스트에 하나씩 넣어줌
//		}
//		
		// 적군 이동 패턴 코드 작성
		
		// ~~~~~~~~~
	}
	
	
	// 이 방법으로 했을 때, 다른 클래스에서 특정 Enemy에 접근하는 방법
	
	
	// key가 "1번째 적군"인 value {index1, index2}를 가져와서, 인덱스 0번인 index1을 가져와서 저장함
	int index1 = mContext.getEnemyIndexMap().get("1번째 적군")[0]; // 시작 인덱스
	int index2 = mContext.getEnemyIndexMap().get("1번째 적군")[1]; // 끝 인덱스
 	
	// 가져온 인덱스를 이용해서 객체에 접근하기
	// index1부터 index2까지
	
//	for (int i = index1; i <= index2; i++) {
		
		// mContext.getEnemyList()[i] <- 이렇게 하면 각 Enemy에 접근 가능
//	};
	
	
	
	
	
	
	
	
	

	// 생성자
	public EnemyTestFrame() {
		initData();
		setInitLayout();
		
		

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
//	public void createEnemy() {
////		// 1개의 enemyUnit3 생성
////		unit3OneMove();
//
//		delay = 1000L;
//		TimerTask task1 = new TimerTask() {
//			@Override
//			public void run() {
////				 1개의 enemyUnit3 생성
//				unit3OneMove();
//				
////				unit3ArrayMove();
//			}
//		};
//		timer.schedule(task1, delay);
//	}

//	// 생성자 메서드 - 적군 소환 흐름
//	public void createEnemy2() {
//
//		TimerTask task2 = new TimerTask() {
//			@Override
//			public void run() {
//				// 4개의 enemyUnit4 생성
//				unit4ArrayMove();
//				
//			}
//		};
//		delay = 1000L;
//		//
//		timer.schedule(task2, delay, 1000);
//	}

//	// 유닛3 하나를 소환하는 메서드
//	public void unit3OneMove() {
//		EnemyUnit3 unit3 = new EnemyUnit3(mContext);
//
//		// y 위치 고정
//		unit3.setY(70);
//
//		// 초기 x 위치는 랜덤
//		Random random = new Random();
//		unit3.setX(random.nextInt(700) + 50);
//		unit3.setLocation(unit3.getX(), unit3.getY());
//
//		mContext.add(unit3);
//
//		// 랜덤한 방향으로 이동
//		unit3.randomDirection();
//
//	}

//	// 유닛3 배열을 소환하는 메서드
//	public void unit3ArrayMove() {
//		// 한 번에 3개씩 소환됨
//		EnemyUnit3[] units = new EnemyUnit3[3];
//
//		// x 위치를 배열 값으로 넣기
//		int[] intArrX = { 200, 500, 350 };
//
//		// y 위치를 배열 값으로 넣기
//		int[] intArrY = { 150, 150, 50 };
//
//		for (int i = 0; i < units.length; i++) {
//			// 인스턴스화
//			units[i] = new EnemyUnit3(mContext);
//			// x 값 세팅
//			units[i].setX(intArrX[i]);
//			// y 값 세팅 (동일하게)
//			units[i].setY(intArrY[i]);
//			// 위치시키기
//			units[i].setLocation(units[i].getX(), units[i].getY());
//			mContext.add(units[i]);
//		}
//		for (int i = 0; i < units.length; i++) {
//			units[i].down(units[i].getSpeed());
//		}
//		
//	}

//	// 유닛4 배열을 소환하는 메서드
//	public void unit4ArrayMove() {
//		// 한 번에 4개씩 소환됨
//		EnemyUnit4[] units = new EnemyUnit4[4];
//
//		// x 위치를 배열 값으로 넣기
//		int[] intArrX = { 110, 310, 510, 710 };
//
//		for (int i = 0; i < units.length; i++) {
//			// 인스턴스화
//			units[i] = new EnemyUnit4(mContext);
//			// x 값 세팅
//			units[i].setX(intArrX[i]);
//			// y 값 세팅 (동일하게)
//			units[i].setY(80);
//			// 위치시키기
//			units[i].setLocation(units[i].getX(), units[i].getY());
//			mContext.add(units[i]);
//		}
//
//		// 백그라운드서비스
//
//		for (int i = 0; i < units.length; i++) {
//			units[i].down(units[i].getSpeed());
//		}
//		
//	}
	
	

	public ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(ArrayList<Enemy> enemyList) {
		this.enemyList = enemyList;
	}

	public HashMap<String, int[]> getEnemyIndexMap() {
		return enemyIndexMap;
	}

	public void setEnemyIndexMap(HashMap<String, int[]> enemyIndexMap) {
		this.enemyIndexMap = enemyIndexMap;
	}

	public static void main(String[] args) {
		new EnemyTestFrame();

	}

}
