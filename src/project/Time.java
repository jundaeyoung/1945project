package project;

import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class Time {

	// 위치 (initData 메서드에서 일단 임의로 지정, 나중에 필요하면 수정)
	private int x;
	private int y;

	// 시간을 표시할 라벨
	JLabel timeLabel;

	// 시간
	int timeS; // 초
	int timeM; // 분

	public Time() {
		initData();
		setInitLayout();
		timerStart();
	}

	private void initData() {
		x = 800;
		y = 25;
		
		// 초기 값 세팅 (임시)
		timeM = 2;
		timeS = 0;

		timeLabel = new JLabel();
	}

	// 임시 디자인, 나중에 마무리할 때 꾸미기
	private void setInitLayout() {
		Font f = new Font("맑은고딕", Font.BOLD, 20);
		timeLabel.setFont(f);
		timeLabel.setHorizontalAlignment(JLabel.CENTER); // 텍스트 가운데 정렬

		timeLabel.setSize(90, 40);
		timeLabel.setOpaque(true); // 배경색 보이게
		timeLabel.setBackground(Color.white);
		timeLabel.setLocation(x, y);
	}

	private void timerStart() {

		Timer timer = new Timer();

		TimerTask timerTask = new TimerTask() {
			String textTimeM;
			String textTimeS;

			@Override
			public void run() {
				
				// 게임 종료
				if (timeS == 0 && timeM == 0) {
					cancel();
				}

				timeS--;

				if (timeS < 0) {
					timeM--;
					timeS += 60;
				}

				// 분이 1 자릿수인 경우 01 이런 식으로 표시
				if (timeM < 10) {
					textTimeM = "0" + timeM;
				} else {
					textTimeM = "" + timeM;
				}

				// 초가 1 자릿수인 경우
				if (timeS < 10) {
					textTimeS = "0" + timeS;
				} else {
					textTimeS = "" + timeS;
				}

				timeLabel.setText(textTimeM + " : " + textTimeS);
			}
		}; // end of timerTask

		// 1초마다 run 메서드 실행
		timer.schedule(timerTask, 0, 1000);

	} // end of timerStart

}
