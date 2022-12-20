package Audio;

import java.io.File;

import javax.sound.sampled.*;

//Note: Volume 0 ~ 100 = 6.0206 ~ 0
public class Audio {
	Clip clip;
	float volume = 0;
	FloatControl fc;
	
	public Audio(String source) {
	    try {
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("res/Music/" + source));
	        clip = AudioSystem.getClip();
	        clip.open(audioIn);
	        
		    fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	    }
	    
	}
	
	public Audio(String source, double volume) {
	    this(source);
	    this.volume = (float) ((1 - volume) * 6.0206);
	    System.out.println(this.volume);
	    fc.setValue(this.volume);
	}
	
	public void play() {
		if(clip != null) {
			stop();
			clip.setFramePosition(0);
			clip.start();
		}
	}
	
	public void bgPlay() {
		if(clip != null) {
			stop();
			clip.setFramePosition(0);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	
	public void stop() {
		if(clip.isRunning()) clip.stop();
	}
	
	public void setVolume(float volume) {
	    this.volume = (float) ((1 - volume) * 6.0206);
		fc.setValue(this.volume);
	}
	
	public float getVolume() {
		return this.volume;
	}
}
