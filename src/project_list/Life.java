package project_list;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Life extends JLabel {
	AirplaneFrame mContext;

	private int x;
	private int y;

	private ImageIcon life;

	public Life(AirplaneFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
	}

	public void initData() {
		setSize(50, 50);
		setVisible(true);
		life = new ImageIcon("imagesProject/LifeCount.png");

	}

	public void setInitLayout() {
		setIcon(life);

	}

	public void crash(Life life) {
		setIcon(null);
		mContext.remove(mContext.getPlayer());
		mContext.repaint();
	}

	public void lifeUp() {
		mContext.getPlayer().setLife(mContext.getPlayer().getLife() + 1);
			setIcon(life);
	}
}
