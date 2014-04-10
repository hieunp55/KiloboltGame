/**
 * 
 */
package kiloboltgame;

/**
 * @author Lion
 *
 */
public class Projectile {
	
	private int x, y, speedX;
	private boolean visible;
	/**
	 * 
	 */
	public Projectile(int startX, int startY) {
		// TODO Auto-generated constructor stub
		x = startX;
		y = startY;
		speedX = 7;
		visible = true;
	}
	public void update() {
		// TODO Auto-generated method stub
		 x+= speedX;
		 if (x > 800){
			 visible = false;
		 }
	}
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
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
	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}
	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
