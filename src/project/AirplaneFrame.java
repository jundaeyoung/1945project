package project;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	private Enemy enemy;
	private EnemyBullet enemyBullet;

	// 플레이어 목숨 개수 아이콘
	private Life life0;
	private Life life1;
	private Life life2;

	// 게임 종료 여부 : 객체 생성하지 않고 사용 가능하게 (0 : 실행, 1 : 종료)
	private static int gameState = 0;

// 여러 적군 만들기
//	private Enemy[] enemies = new Enemy[10];

	// 생성자
	public AirplaneFrame() {
		initData();
		setInitLayout();
		addEventListener();

		new Thread(new BackgroundPlayerService(player)).start();
		new Thread(new BackgroundEnemyService(enemy)).start();

//		// 적군 배열 각각에 백그라운드서비스 적용
//		for (int i = 0; i < enemies.length; i++) {
//			new Thread(new BackgroundEnemyService(enemies[i])).start();			
//		}
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

	public Enemy getEnemy() {
		return enemy;
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
		enemy = new Enemy(mContext);
		life0 = new Life(mContext);
		life1 = new Life(mContext);
		life2 = new Life(mContext);
		time = new Time();
//		// 테스트
//		for (int i = 0; i < enemies.length; i++) {
//			enemies[i] = new Enemy(mContext);
//		}
	}

	// 생성자 메서드 2
	public void setInitLayout() {
		setLayout(null); // 좌표기반
		setResizable(false); // 창 크기 조절 기능( 거짓 )
		setLocationRelativeTo(null); // JFrame 가운데 배치
		setVisible(true);
		if (gameState == 0) {
			setContentPane(gameStart);
			add(time.timeLabel);
		} else {
			setContentPane(new MyPanel());
			add(player);
			add(enemy);
			add(time.timeLabel);

			add(life0);
			add(life1);
			add(life2);

		}
//		// 여러 적군 만들기
//		for (int i = 0; i < enemies.length; i++) {
//			add(enemies[i]);
//		}
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
				case KeyEvent.VK_SPACE:

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

	public static void main(String[] args) {
		new AirplaneFrame();

	}

	class MyPanel extends JPanel {
		public MyPanel() {

			setFocusable(true);
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (true) {
						backY++;
						repaint();
						try {
							Thread.sleep(80);
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
			life0.setLocation(20, 10);
			life1.setLocation(60, 10);
			life2.setLocation(100, 10);
			time.timeLabel.setLocation(830, 20);

		}
	}
}