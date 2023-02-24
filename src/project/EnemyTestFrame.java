package project;

import javax.swing.JFrame;

public class EnemyTestFrame extends JFrame {

	private EnemyTestFrame mContext = this;

	private EnemyUnit3 enemy3;
	private EnemyUnit4 enemy4;

	// 생성자
	public EnemyTestFrame() {
		initData();
		setInitLayout();

	} // end of 생성자
	
	// 생성자 메서드 1
	private void initData() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(950, 950);

		// 객체 생성 테스트
		
		enemy3 = new EnemyUnit3(this);
		enemy4 = new EnemyUnit4(this);
		
	}

	// 생성자 메서드 2
	public void setInitLayout() {
		setLayout(null); // 좌표기반
		setResizable(false); // 창 크기 조절 기능( 거짓 )
		setLocationRelativeTo(null); // JFrame 가운데 배치
		setVisible(true);
		
		add(enemy3);
		add(enemy4);
	}
	
	public static void main(String[] args) {
		new EnemyTestFrame();
		
	}

}
