/**
 * 
 */
package kiloboltgame;

/**
 * @author Lion
 *
 */
public class Background {
	private int bgX, bgY, speedX;
	
	public Background(int x, int y){
		bgX = x;
		bgY = y;
		speedX = 0;
	}
	
	public void update() {
		// TODO Auto-generated method stub
		bgX += speedX;
		
		if(bgX <= -2160){
			bgX += 4320;
		}
	}

	/**
	 * @return the bgX
	 */
	public int getBgX() {
		return bgX;
	}

	/**
	 * @param bgX the bgX to set
	 */
	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	/**
	 * @return the bgY
	 */
	public int getBgY() {
		return bgY;
	}

	/**
	 * @param bgY the bgY to set
	 */
	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	/**
	 * @return the speedX
	 */
	public int getSpeedX() {
		return speedX;
	}

	/**
	 * @param speedX the speedX to set
	 */
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
}
