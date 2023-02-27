package project_list;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Bgm {
	private Clip clip;
	private FloatControl gainControl;
	
	public Bgm() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("BGM/bgm.wav"));
			clip = AudioSystem.getClip();
			clip.open(ais);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-20.0f);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (Exception e) {
			System.out.println("사운드 파일 경로 확인");
			e.printStackTrace();
		}
	}
}
