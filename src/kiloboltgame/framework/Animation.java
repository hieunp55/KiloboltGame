/**
 * 
 */
package kiloboltgame.framework;

import java.awt.Image;
import java.util.ArrayList;

/**
 * @author lileeon
 *
 */
public class Animation {
	private ArrayList frames;
	private int currentFrame;
	private long animTime; //long takes up more memory than int but can hold more accurate numbers
	private long totalDuration;
	
	public Animation() {
		frames = new ArrayList();
		totalDuration = 0;
		
		synchronized(this) {
			animTime = 0;
			currentFrame = 0;
		}
	}
	
	public synchronized void addFramme(Image image, long duration) {
		totalDuration += duration;
		frames.add(new AnimFrame(image, totalDuration));
	}
	
	public synchronized void update(long elapsedTime) {
		if(frames.size() > 1) {
			animTime += elapsedTime;
			if(animTime >= totalDuration) {
				animTime = animTime % totalDuration;
				currentFrame = 0;
			}
		}
		while(animTime > getFrame(currentFrame).endTime) {
			currentFrame++;
		}
	}
	
}
