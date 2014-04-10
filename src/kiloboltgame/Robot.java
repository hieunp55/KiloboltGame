/**
 * 
 */
package kiloboltgame;

//import java.awt.Graphics;
import java.util.ArrayList;

/**
 * @author Lion
 * 
 */
public class Robot {

	// Constants
	final int JUMPSPEED = -15;
	final int MOVESPEED = 5;
	final int GROUND = 382;

	private int centerX = 100;
	private int centerY = 382;
	private boolean jumped = false;

	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;

	private static Background bg1 = StartingClass.getBg1();
	private static Background bg2 = StartingClass.getBg2();

	private int speedX = 0;
	private int speedY = 1;
	
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	public void update() {

		// move character or scrolls background accordingly
		if (speedX < 0) {
			centerX += speedX; // This changes centerX by adding speedX
		}
		if (speedX == 0 || speedX < 0) {
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);
		}
		if (centerX <= 200 && speedX > 0) {
			centerX += speedX;
		}
		if (speedX > 0 && centerX > 200) {
			bg1.setSpeedX(-MOVESPEED);
			bg2.setSpeedX(-MOVESPEED);
		}
		// Updates Y Position
		// 382 is where the character's centerY would be if he were standing on
		// the ground
		if (centerY + speedY >= GROUND) {
			centerY = GROUND;
		} else {
			centerY += speedY;
		}

		// Handles Jumping
		if (jumped == true) {
			speedY += 1;
			// WHile the character is in the air, add 1 to his speedY
			// NOTE: This will bring the character downwards!
			if (centerY + speedY >= GROUND) {
				centerY = GROUND;
				speedY = 0;
				jumped = false;
			}
		}

		// Prevents going beyond X coordinate of 0
		if (centerX + speedX <= 60) {
			centerX = 61;
		}

	}

	public void moveRight() {
		if (ducked == false) {
			speedX = MOVESPEED;
		}
	}

	public void moveLeft() {
		if (ducked == false) {
			speedX = -MOVESPEED;
		}
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	public void stop() {
		if(isMovingRight() == false && isMovingLeft() == false){
			speedX = 0;
		}
		if (isMovingRight() == false && isMovingLeft() == true){
			moveLeft();
		}
		if (isMovingRight() == true && isMovingLeft() == false){
			moveRight();
		}
	}

	public void jump() {
		if (jumped == false) {
			speedY = JUMPSPEED;
			jumped = true;
		}
	}
	
	public void shoot() {
		// TODO Auto-generated method stub
		Projectile p = new Projectile(centerX + 50, centerY - 25);
		projectiles.add(p);
	}

	/**
	 * @return the centerX
	 */
	public int getCenterX() {
		return centerX;
	}

	/**
	 * @param centerX
	 *            the centerX to set
	 */
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	/**
	 * @return the centerY
	 */
	public int getCenterY() {
		return centerY;
	}

	/**
	 * @param centerY
	 *            the centerY to set
	 */
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	/**
	 * @return the jumped
	 */
	public boolean isJumped() {
		return jumped;
	}

	/**
	 * @param jumped
	 *            the jumped to set
	 */
	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	/**
	 * @return the speedX
	 */
	public int getSpeedX() {
		return speedX;
	}

	/**
	 * @param speedX
	 *            the speedX to set
	 */
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	/**
	 * @return the speedY
	 */
	public int getSpeedY() {
		return speedY;
	}

	/**
	 * @param speedY
	 *            the speedY to set
	 */
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	/**
	 * @return the movingLeft
	 */
	public boolean isMovingLeft() {
		return movingLeft;
	}

	/**
	 * @param movingLeft the movingLeft to set
	 */
	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	/**
	 * @return the movingRight
	 */
	public boolean isMovingRight() {
		return movingRight;
	}

	/**
	 * @param movingRight the movingRight to set
	 */
	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	/**
	 * @return the ducked
	 */
	public boolean isDucked() {
		return ducked;
	}

	/**
	 * @param ducked the ducked to set
	 */
	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}
	
	public ArrayList getProjectiles(){
		return projectiles;
	}

}
