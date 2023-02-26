package project_list;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Score extends JFrame {

	private AirplaneFrame mContext;

	private int score;
	MyDrawPanel scoreTitle;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public MyDrawPanel getScoreTitle() {
		return scoreTitle;
	}

	public void setScoreTitle(MyDrawPanel scoreTitle) {
		this.scoreTitle = scoreTitle;
	}

	class MyDrawPanel extends JPanel {

		public void paint(Graphics g) {
			super.paint(g);
			

		}
	}

	public Score() {
		initData();
		setInitLayot();
	}

	private void initData() {

		setVisible(true);
		scoreTitle = new MyDrawPanel();
	}

	private void setInitLayot() {
		add(scoreTitle);
	}

}