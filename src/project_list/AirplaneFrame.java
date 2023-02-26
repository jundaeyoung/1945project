package project_list;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AirplaneFrame extends JFrame {

	private AirplaneFrame mContext = this;

	// 배경 흐르기 실험

	ImageIcon backIc = new ImageIcon("imagesProject/stage1.png");
	Image backImg = backIc.getImage();

	int backY = -17200;

	private Background background;
	private JLabel backgroundMap;
	private JLabel gameStart;
	private Time time;
	private Player player;
	private EnemyBullet enemyBullet;
//	private Item item;

	// 적군 관련
	// 이 두 변수 외에 enemy 관련 다른 변수는 선언하지 않아도 됨
	// 생성된 적군 객체들을 담을 리스트
	// 생성될 때마다 순서대로 리스트에 담김
	ArrayList<Enemy> enemyList = new ArrayList<>();

	ArrayList<Item> itemList = new ArrayList<>();

	// 플레이어 목숨 개수 아이콘
	private Life life0;
	private Life life1;
	private Life life2;

	// 점수
	private Score scoreTitle;
	private int score;

	// 게임 종료 여부 : 객체 생성하지 않고 사용 가능하게 (0 : 실행, 1 : 종료)
	private static int gameState = 0;

	// 생성자
	public AirplaneFrame() {
		initData();
		setInitLayout();
		addEventListener();
//		mContext.getItem().setDown(false);

		new Thread(new BackgroundPlayerService(player)).start();

	} // end of 생성자

	public EnemyBullet getEnemyBullet() {
		return enemyBullet;
	}

	public void setEnemyBullet(EnemyBullet enemyBullet) {
		this.enemyBullet = enemyBullet;
	}

	public static int getGameState() {
		return gameState;
	}

	public static void setGameState(int gameState) {
		AirplaneFrame.gameState = gameState;
	}

	public Life getLife0() {
		return life0;
	}

	public void setLife(Life life0) {
		this.life0 = life0;
	}

	public Life getLife1() {
		return life1;
	}

	public void setLife1(Life life1) {
		this.life1 = life1;
	}

	public Life getLife2() {
		return life2;
	}

	public void setLife2(Life life2) {
		this.life2 = life2;
	}

	public AirplaneFrame getmContext() {
		return mContext;
	}

	public void setmContext(AirplaneFrame mContext) {
		this.mContext = mContext;
	}

	public JLabel getBackgroundMap() {
		return backgroundMap;
	}

	public void setBackgroundMap(JLabel backgroundMap) {
		this.backgroundMap = backgroundMap;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(ArrayList<Enemy> enemyList) {
		this.enemyList = enemyList;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	// 생성자 메서드 1
	private void initData() {
		gameStart = new JLabel(new ImageIcon("imagesProject/GameTitle.gif"));
		backgroundMap = new JLabel(new ImageIcon("imagesProject/backgroundservice.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(950, 950);

//		JPanel panel = new  MyPanel();
		background = new Background(mContext);
		player = new Player(mContext);
		life0 = new Life(mContext);
		life1 = new Life(mContext);
		life2 = new Life(mContext);
//		item = new Item(mContext);

//		// 테스트
//		for (int i = 0; i < enemies.length; i++) {
//			enemies[i] = new Enemy(mContext);
//		}
	}

	// 생성자 메서드 2
	public void setInitLayout() {
		setLayout(null); // 좌표기반
		setResizable(false); // 창 크기 조절 기능 (불가능)
		setLocationRelativeTo(null); // JFrame 가운데 배치
		setVisible(true);

		if (gameState == 0) {
			setContentPane(gameStart);
		} else {
			setContentPane(new MyPanel());
			add(player);
			add(life0);
			add(life1);
			add(life2);
//			mContext.getItem().setDown(false);
			time = new Time();
			add(time.timeLabel);
			createEnemy();

		}
	}

	private void addEventListener() {
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// 37,38,39,40
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					// 여러 번 누르더라도 한 번만 추가될수 있도록 코드 추가
					if (player.isLeft() == false && player.isLeftWallCrash() == false) {
						player.left();
					}
					break;
				case KeyEvent.VK_RIGHT:
					if (player.isRight() == false && player.isRightWallCrash() == false) {
						player.right();
					}
					break;
				case KeyEvent.VK_UP:
					if (!player.isUp() && !player.isUpWallCrash()) {
						player.up();
					}
					break;
				case KeyEvent.VK_DOWN:
					if (player.isDown() == false && player.isDownWallCrash() == false) {
						player.down();
					}
					break;
				case KeyEvent.VK_ENTER:
					gameState = 1;
					setInitLayout();
					break;
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					// 왼쪽 버튼을 떼면 player는 멈춰야해
					player.setLeft(false);
					break;

				case KeyEvent.VK_RIGHT:
					// 오른쪽 버튼을 떼면 player는 멈춰야해
					player.setRight(false);
					break;

				case KeyEvent.VK_UP:
					player.setUp(false);
					break;

				case KeyEvent.VK_DOWN:
					player.setDown(false);
					break;
				case KeyEvent.VK_SPACE:
					player.attack();
					// 연속공격 안되게 하기 아직 보류
//					 Timer t = new Timer(true);
//					    TimerTask task1 = new TimerTask() {
//							
//							@Override
//							public void run() {
//								// TODO Auto-generated method stub
//								player.attack();
//							}
//						};
//						t.schedule(task1, 3000);

					break;
				}
			}

		});

	}

	// 적군 소환 흐름 1
	public void createEnemy() {
//		unit4ArrayMove();

		Timer timer = new Timer();
		long delay;

		delay = 1000L;
		TimerTask task1 = new TimerTask() {
			@Override
			public void run() {
				unit3OneMove();
			}
		};
		timer.schedule(task1, delay);
	}

	// 유닛3 하나를 소환하는 메서드
	public void unit3OneMove() {
		int index1 = enemyList.size();

		int enemyCount = 1;
		int index2 = index1 + (enemyCount - 1);

		// 객체 생성 및 리스트에 추가
		enemyList.add(new EnemyUnit3(mContext));

		// 알아보기 쉽게 변수로 선언
		Enemy targetEnemy = enemyList.get(index1);

		// 초기 x 위치는 랜덤
		Random random = new Random();
		targetEnemy.setX(random.nextInt(500) + 50);
		targetEnemy.setY(200);
		targetEnemy.setLocation(targetEnemy.getX(), targetEnemy.getY());

		// 추가
		mContext.add(targetEnemy);

		// 백그라운드 서비스 적용
		new Thread(new BackgroundEnemyService(targetEnemy)).start();

		// 랜덤한 방향으로 이동
//		targetEnemy.randomDirection();
		targetEnemy.attack(targetEnemy.getAttackSpeed());
	}

	// 유닛4 배열을 소환하는 메서드
	public void unit4ArrayMove() {
		// 적군 객체를 담기 전에 지금 리스트의 사이즈를 확인함
		// (사이즈 == 0이면 인덱스 0번부터 객체가 추가될 것이고, 사이즈 == 1이면 인덱스 1번부터 객체가 추가될 것임)
		int index1 = enemyList.size();

		// 소환할 객체 수 지정
		int enemyCount = 4;
		int index2 = index1 + (enemyCount - 1);

		Random random = new Random();
		// 첫 객체의 위치 왼쪽 OR 오른쪽 랜덤
		int num = random.nextInt(2);
		int initX;

		if (num == 0) {
			initX = 50;
		} else {
			initX = 550;
		}

		// x 위치를 배열 값으로 지정해두기
		int[] intArrX = { initX, initX += 80, initX += 80, initX += 80 };

		for (int i = index1; i <= index2; i++) {
			// 인스턴스화 및 리스트에 추가
			enemyList.add(new EnemyUnit4(mContext));

			// 알아보기 쉽게 변수로 선언
			Enemy targetEnemy = enemyList.get(i);

			// x 값 세팅
			targetEnemy.setX(intArrX[i]);

			// y 값 세팅 (모두 동일하게)
			targetEnemy.setY(80);

			// 위치 시키기
			targetEnemy.setLocation(targetEnemy.getX(), targetEnemy.getY());

			// 추가
			mContext.add(targetEnemy);

			// 백그라운드 서비스 적용
			new Thread(new BackgroundEnemyService(targetEnemy)).start();
			targetEnemy.attack(targetEnemy.getAttackSpeed());

		}

		// 아래로만 움직임
		for (int i = index1; i <= index2; i++) {
			enemyList.get(i).down(enemyList.get(i).getSpeed());

		}
	}

	// 유닛3 배열을 소환하는 메서드
	public void unit3ArrayMove() {
		int index1 = enemyList.size();

		// 소환할 객체 수 지정
		int enemyCount = 3;
		int index2 = index1 + (enemyCount - 1);

		// x 위치를 배열 값으로 지정해두기
		int[] intArrX = { 200, 500, 350 };

		// y 위치를 배열 값으로 지정해두기
		int[] intArrY = { 150, 150, 50 };

		for (int i = index1; i <= index2; i++) {
			// 인스턴스화 및 리스트에 추가
			enemyList.add(new EnemyUnit3(mContext));

			// 알아보기 쉽게 변수로 선언
			Enemy targetEnemy = enemyList.get(i);

			// x 값 세팅
			targetEnemy.setX(intArrX[i]);
			// y 값 세팅 (모두 동일하게)
			targetEnemy.setY(intArrY[i]);
			// 위치 시키기
			targetEnemy.setLocation(targetEnemy.getX(), targetEnemy.getY());
			// 추가
			mContext.add(targetEnemy);

			// 백그라운드 서비스 적용
			new Thread(new BackgroundEnemyService(targetEnemy)).start();

		}

		// 아래로만 움직임
		for (int i = index1; i <= index2; i++) {
			// 알아보기 쉽게 변수로 선언
			Enemy targetEnemy = enemyList.get(i);

			targetEnemy.down(targetEnemy.getSpeed());
			targetEnemy.attack(targetEnemy.getAttackSpeed());

		}

	}

	// 배경
	class MyPanel extends JPanel {
		public MyPanel() {

			setFocusable(true);
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						backY++;
						repaint();
						try {
							Thread.sleep(7);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}
			}).start();

		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backImg, -10, backY, 950, 18327, this);
			g.setColor(new Color(255, 255, 255));
			g.setFont(new Font(null, 30, 30));
			g.drawString("SCORE : ", 380, 45);
			g.drawString(Integer.toString(mContext.getScore()), 530, 45);

			life0.setLocation(20, 10);
			life1.setLocation(60, 10);
			life2.setLocation(100, 10);
			time.timeLabel.setLocation(840, 20);

		}
	}

	public static void main(String[] args) {
		new AirplaneFrame();

	}

}
