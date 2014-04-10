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
	
<<<<<<< HEAD
	public synchronized void addFrame(Image image, long duration) {
=======
	public synchronized void addFramme(Image image, long duration) {
>>>>>>> bedf4cb1ec98157f7247c99434185811806b38f8
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
	
<<<<<<< HEAD
	public synchronized Image getImage(){
		if (frames.size() == 0){
			return null;
		}else {
			return getFrame(currentFrame).image;
		}
	}
	
	private AnimFrame getFrame(int i){
		return (AnimFrame) frames.get(i);
	}
	
	private class AnimFrame {
		Image image;
		long endTime;
		
		public AnimFrame(Image image, long endTime){
			this.image = image;
			this.endTime = endTime;
		}
	}
=======
>>>>>>> bedf4cb1ec98157f7247c99434185811806b38f8
}
