package project_list;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.AbstractDocument.Content;

public class AirplaneFrame extends JFrame {

	private AirplaneFrame mContext = this;

	// 배경 흐르기 실험

	ImageIcon backIc = new ImageIcon("imagesProject/stage1.png");
	Image backImg = backIc.getImage();

	int backY = -17200;

	private Background background;
	private JLabel backgroundMap;
	private JLabel gameStart;
	private JLabel gameOver;
	private JLabel gameClear;
	private Time time;
	private Player player;
	private EnemyBullet enemyBullet;
	private Bullet bullet;
	private JLabel label;
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

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

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

	public void gameOver() {
		add(gameOver);
	}

	public void gameClear() {
		add(gameClear);
	}

	// 생성자 메서드 1
	private void initData() {
		gameStart = new JLabel(new ImageIcon("imagesProject/GameTitle.gif"));
		gameOver = new JLabel(new ImageIcon("imagesProject/GameOver.png"));
		gameClear = new JLabel(new ImageIcon("imagesProject/GameClear.png"));
		backgroundMap = new JLabel(new ImageIcon("imagesProject/backgroundservice.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(950, 950);

//		JPanel panel = new  MyPanel();
		background = new Background(mContext);
		player = new Player(mContext);
		life0 = new Life(mContext);
		life1 = new Life(mContext);
		life2 = new Life(mContext);
		label = new JLabel(new ImageIcon("imagesProject/enter.png"));
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

			add(label);
			setContentPane(gameStart);
		} else if (gameState == 1) {
			setContentPane(new MyPanel());
//			setContentPane(backgroundMap);
			add(player);
			add(life0);
			add(life1);
			add(life2);
//			mContext.getItem().setDown(false);
			time = new Time(this);
			add(time.timeLabel);
			createEnemy();

		} else if (gameState == 2) {
			mContext.getPlayer().gameOver();
		} else if (gameState == 3) {
			mContext.getPlayer().gameClear();
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
					if (gameState == 0 || gameState == 2) {
						gameState = 1;
						setInitLayout();
					}
					break;
				}

			}

			public void reStart() {
				System.out.println("---------- 게임 재 시작 하기 ----------");
				// 초기화 해야 될 부분추가
				new AirplaneFrame();
				dispose();
//				setInitLayout();
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

	// 적군 소환 흐름
	public void createEnemy() {
		// 1번째 적군 소환
		unit2ArrayLeftMove();

		Timer timer1 = new Timer();
		long delay1 = 3000L;

		// 2번째 적군 소환
		TimerTask task1 = new TimerTask() {
			@Override
			public void run() {
				unit3OneMove();
			}
		};
		timer1.schedule(task1, delay1);

		// 3번째 적군 소환
		long delay2 = 6000L;
		TimerTask task2 = new TimerTask() {
			@Override
			public void run() {
				unit4ArrayRightMove();
			}
		};
		timer1.schedule(task2, delay2);

		// 4번째 적군 소환
		long delay3 = 9000L;
		TimerTask task3 = new TimerTask() {

			@Override
			public void run() {
				unit4OneMove();
				unit2ArrayLeftMove();
			}
		};
		timer1.schedule(task3, delay3);

		// 5번째 적군 소환
		long delay4 = 13000L;
		TimerTask task4 = new TimerTask() {

			@Override
			public void run() {
				unit3ArrayMove();
			}
		};
		timer1.schedule(task4, delay4);

		// 6번째 적군 소환
		long delay5 = 17000L;
		TimerTask task5 = new TimerTask() {

			@Override
			public void run() {
				unit1OneMove();
			}
		};
		timer1.schedule(task5, delay5);

		// 7번째 적군 소환
		long delay6 = 21000L;
		TimerTask task6 = new TimerTask() {

			@Override
			public void run() {
				unit4OneMove();

			}
		};
		timer1.schedule(task6, delay6);

		// 8번째 적군 소환
		long delay7 = 25000L;
		TimerTask task7 = new TimerTask() {

			@Override
			public void run() {
				unit2ArrayRightMove();
			}
		};
		timer1.schedule(task7, delay7);

		// 9번째 적군 소환
		long delay8 = 29000L;
		TimerTask task8 = new TimerTask() {

			@Override
			public void run() {
				unit3OneMove();
			}
		};
		timer1.schedule(task8, delay8);

		// 10번째 적군 소환
		long delay9 = 32000L;
		TimerTask task9 = new TimerTask() {

			@Override
			public void run() {
				unit2ArrayRightMove();
				unit2ArrayRightMove();
			}
		};
		timer1.schedule(task9, delay9);

		// 11번째 적군 소환
		long delay10 = 36000L;
		TimerTask task10 = new TimerTask() {

			@Override
			public void run() {
				unit4ArrayLeftMove();
			}
		};
		timer1.schedule(task10, delay10);

		// 12번째 적군 소환
		long delay11 = 38000L;
		TimerTask task11 = new TimerTask() {

			@Override
			public void run() {
				unit1OneMove();
				unit4OneMove();
			}
		};
		timer1.schedule(task11, delay11);

		// 13번째 적군 소환
		long delay12 = 41000L;
		TimerTask task12 = new TimerTask() {

			@Override
			public void run() {
				unit2ArrayLeftMove();
			}
		};
		timer1.schedule(task12, delay12);

		// 14번째 적군 소환
		long delay13 = 42000L;
		TimerTask task13 = new TimerTask() {

			@Override
			public void run() {
				unit3OneMove();
			}
		};
		timer1.schedule(task13, delay13);

		// 15번째 적군 소환
		long delay14 = 45000L;
		TimerTask task14 = new TimerTask() {

			@Override
			public void run() {
				unit4OneMove();
			}
		};
		timer1.schedule(task14, delay14);

		// 16번째 적군 소환
		long delay15 = 46000L;
		TimerTask task15 = new TimerTask() {

			@Override
			public void run() {
				unit3OneMove();
			}
		};
		timer1.schedule(task15, delay15);

		// 17번째 적군 소환
		long delay16 = 48000L;
		TimerTask task16 = new TimerTask() {

			@Override
			public void run() {
				unit4ArrayRightMove();
			}
		};
		timer1.schedule(task16, delay16);

		// 18번째 적군 소환
		long delay17 = 50000L;
		TimerTask task17 = new TimerTask() {

			@Override
			public void run() {
				unit1ArrayMove();
				unit2ArrayLeftMove();
			}
		};
		timer1.schedule(task17, delay17);

		// 게임 클리어 출력
		long delayClear = 58000L;
		TimerTask taskClear = new TimerTask() {

			@Override
			public void run() {
				mContext.setGameState(3);
				mContext.gameClear();

			}
		};

	}

	// 유닛3 하나를 소환하는 메서드
	public void unit3OneMove() {
		int index1 = enemyList.size();

//		int enemyCount = 1;

		// 객체 생성 및 리스트에 추가
		enemyList.add(new EnemyUnit3(mContext));

		// 알아보기 쉽게 변수로 선언
		Enemy targetEnemy = enemyList.get(index1);

		// 초기 x 위치는 랜덤
		Random random = new Random();
		targetEnemy.setX(random.nextInt(500) + 50);
		targetEnemy.setY(10);
		targetEnemy.setLocation(targetEnemy.getX(), targetEnemy.getY());

		// 추가
		mContext.add(targetEnemy);

		// 백그라운드 서비스 적용
		new Thread(new BackgroundEnemyService(targetEnemy)).start();

		// 랜덤한 방향으로 이동
		targetEnemy.randomDirection();
		targetEnemy.attack(targetEnemy.getAttackSpeed());
	}

	// 유닛1 하나를 소환하는 메서드
	public void unit1OneMove() {
		int index1 = enemyList.size();

//		int enemyCount = 1;

		// 객체 생성 및 리스트에 추가
		enemyList.add(new EnemyUnit1(mContext));

		// 알아보기 쉽게 변수로 선언
		Enemy targetEnemy = enemyList.get(index1);

		// 초기 x 위치는 랜덤
		Random random = new Random();
		targetEnemy.setX(random.nextInt(300) + 50);
		targetEnemy.setY(10);
		targetEnemy.setLocation(targetEnemy.getX(), targetEnemy.getY());

		// 추가
		mContext.add(targetEnemy);

		// 백그라운드 서비스 적용
		new Thread(new BackgroundEnemyService(targetEnemy)).start();

		// 랜덤한 방향으로 이동
		targetEnemy.randomDirection();
		targetEnemy.attack(targetEnemy.getAttackSpeed());
	}

	// 유닛4 하나를 소환하는 메서드
	public void unit4OneMove() {
		int index1 = enemyList.size();

//		int enemyCount = 1;

		// 객체 생성 및 리스트에 추가
		enemyList.add(new EnemyUnit4(mContext));

		// 알아보기 쉽게 변수로 선언
		Enemy targetEnemy = enemyList.get(index1);

		// 초기 x 위치는 랜덤
		Random random = new Random();
		targetEnemy.setX(random.nextInt(500) + 50);
		targetEnemy.setY(10);
		targetEnemy.setLocation(targetEnemy.getX(), targetEnemy.getY());

		// 추가
		mContext.add(targetEnemy);

		// 백그라운드 서비스 적용
		new Thread(new BackgroundEnemyService(targetEnemy)).start();

		// 랜덤한 방향으로 이동
		targetEnemy.randomDirection();
		targetEnemy.attack(targetEnemy.getAttackSpeed());
	}

	// 유닛4 배열을 왼쪽에 소환하는 메서드
	public void unit4ArrayLeftMove() {
		// 적군 객체를 담기 전에 지금 리스트의 사이즈를 확인함
		// (사이즈 == 0이면 인덱스 0번부터 객체가 추가될 것이고, 사이즈 == 1이면 인덱스 1번부터 객체가 추가될 것임)
		int index1 = enemyList.size();

		// 소환할 객체 수 지정
		int enemyCount = 3;
		int index2 = index1 + (enemyCount - 1);

		Random random = new Random();
		// 첫 객체의 위치 왼쪽 OR 오른쪽 랜덤
		int num = random.nextInt(2);
		int initX = 50;

		// x 위치를 배열 값으로 지정해두기
		int[] intArrX = { initX, initX += 100, initX += 100 };

		for (int i = 0; i < enemyCount; i++) {
			// 인스턴스화 및 리스트에 추가
			enemyList.add(new EnemyUnit4(mContext));

			// 알아보기 쉽게 변수로 선언
			Enemy targetEnemy = enemyList.get(index1 + i);

			// x 값 세팅
			targetEnemy.setX(intArrX[i]);

			// y 값 세팅 (모두 동일하게)
			targetEnemy.setY(50);

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
			enemyList.get(i).down();

		}
	}

	// 유닛4 배열을 오른쪽에 소환하는 메서드
	public void unit4ArrayRightMove() {
		// 적군 객체를 담기 전에 지금 리스트의 사이즈를 확인함
		// (사이즈 == 0이면 인덱스 0번부터 객체가 추가될 것이고, 사이즈 == 1이면 인덱스 1번부터 객체가 추가될 것임)
		int index1 = enemyList.size();

		// 소환할 객체 수 지정
		int enemyCount = 3;
		int index2 = index1 + (enemyCount - 1);
		int initX = 560;

		// x 위치를 배열 값으로 지정해두기
		int[] intArrX = { initX, initX += 100, initX += 100 };

		for (int i = 0; i < enemyCount; i++) {
			// 인스턴스화 및 리스트에 추가
			enemyList.add(new EnemyUnit4(mContext));

			// 알아보기 쉽게 변수로 선언
			Enemy targetEnemy = enemyList.get(index1 + i);

			// x 값 세팅
			targetEnemy.setX(intArrX[i]);

			// y 값 세팅 (모두 동일하게)
			targetEnemy.setY(50);

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
			enemyList.get(i).down();

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
		int[] intArrY = { 100, 100, 10 };

		for (int i = 0; i < enemyCount; i++) {
			// 인스턴스화 및 리스트에 추가
			enemyList.add(new EnemyUnit3(mContext));

			// 알아보기 쉽게 변수로 선언
			Enemy targetEnemy = enemyList.get(index1 + i);

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
			targetEnemy.attack(targetEnemy.getAttackSpeed());

		}

		// 아래로만 움직임
		for (int i = index1; i <= index2; i++) {
			// 알아보기 쉽게 변수로 선언
			Enemy targetEnemy = enemyList.get(i);
			targetEnemy.down();

		}

	}

	// 유닛1 배열을 소환하는 메서드
	private void unit1ArrayMove() {
		int index1 = enemyList.size();

		int enemyCount = 2;
		int index2 = index1 + (enemyCount - 1);
		int[] intArrX = { 5, 400 };
		int[] intArrY = { 100, -50 };

		for (int i = 0; i < enemyCount; i++) {
			enemyList.add(new EnemyUnit1(mContext));

			Enemy targetEnemy = enemyList.get(i + index1);
			targetEnemy.setX(intArrX[i]);
			targetEnemy.setY(intArrY[i]);
			targetEnemy.setLocation(targetEnemy.getX(), targetEnemy.getY());
			add(targetEnemy);
			new Thread(new BackgroundEnemyService(targetEnemy)).start();
			targetEnemy.attack(targetEnemy.getAttackSpeed());
		}

		// 아래로만 움직임
		for (int i = index1; i <= index2; i++) {
			// 알아보기 쉽게 변수로 선언
			Enemy targetEnemy = enemyList.get(i);
			targetEnemy.down();

		}
	}

	// 유닛2 배열을 소환하는 메서드
	public void unit2ArrayLeftMove() {
		int index1 = enemyList.size();

		int createCount = 4;
		int[] intArrX = { 100, 150, 200, 250 };
		int[] intArrY = { 80, 110, 140, 170 };

		for (int i = 0; i < createCount; i++) {
			enemyList.add(new EnemyUnit2(mContext));

			Enemy targetEnemy = enemyList.get(i + index1);
			targetEnemy.setX(intArrX[i]);
			targetEnemy.setY(intArrY[i]);
			targetEnemy.setLocation(targetEnemy.getX(), targetEnemy.getY());
			add(targetEnemy);
		}
		for (int i = 0; i < createCount; i++) {
			Enemy targetEnemy = enemyList.get(i);
			// 메서드 오버라이드 부분에서 매개변수를 없게 해서 멤버변수 speed를 바로 받게 함
//		         int speed = enemyList.get(i + index1).getSpeed();
			enemyList.get(i + index1).down();
			targetEnemy.attack(targetEnemy.getAttackSpeed());
		}
	}

	// 유닛2 배열을 소환하는 메서드
	private void unit2ArrayRightMove() {
		int index1 = enemyList.size();

		int createCount = 4;
		int[] intArrX = { 400, 450, 500, 550 };
		int[] intArrY = { 80, 110, 140, 170 };

		for (int i = 0; i < createCount; i++) {
			enemyList.add(new EnemyUnit2(mContext));

			Enemy targetEnemy = enemyList.get(i + index1);
			targetEnemy.setX(intArrX[i]);
			targetEnemy.setY(intArrY[i]);
			targetEnemy.setLocation(targetEnemy.getX(), targetEnemy.getY());
			add(targetEnemy);
		}
		for (int i = 0; i < createCount; i++) {
			Enemy targetEnemy = enemyList.get(i);
			// 메서드 오버라이드 부분에서 매개변수를 없게 해서 멤버변수 speed를 바로 받게 함
//		         int speed = enemyList.get(i + index1).getSpeed();
			enemyList.get(i + index1).down();
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
			gameOver.setLocation(250, 300);
			gameClear.setLocation(200, 250);

		}
	}

	public static void main(String[] args) {
		new Bgm();
		new AirplaneFrame();

	}

}
