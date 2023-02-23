package project;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Background extends JLabel {

	AirplaneFrame mContext;

	private int x;
	private int y;
	private ImageIcon background;

	private boolean up;

	private int speed = 4;

	public Background(AirplaneFrame mContext) {
		this.mContext = mContext;

		initData();
		setInitLayout();

	}

	public void initData() {
		background = new ImageIcon("imagesProject/stage1.png");
		x = 0;
		y = -17307;
	}

	public void setInitLayout() {
		setSize(950, 18327);
		setLocation(x, y);
		setIcon(background);

	}

	public void up() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					y += 1000;
					setLocation(x, y);

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
