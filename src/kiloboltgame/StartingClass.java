/**
 * 
 */
package kiloboltgame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

import kiloboltgame.framework.Animation;

/**
 * @author Lion
 * 
 */
public class StartingClass extends Applet implements Runnable, KeyListener {

	private Robot robot;
	private Heliboy hb, hb2;
	private Animation anim, hanim;
	private Image image, currentSprite, character, character2, character3, characterDown,
			characterJump, background, heliboy, heliboy2, heliboy3, heliboy4, heliboy5;
	private URL base;
	private Graphics second;
	private static Background bg1, bg2;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		setSize(800, 480);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Q-Bot Alpha");

		try {
			base = getDocumentBase();
		} catch (Exception e) {

		}

		// image setups
		character = getImage(base, "data/character.png");
		character2 = getImage(base, "data/character2.png");
		character3 = getImage(base, "data/character3.png");
		
		characterDown = getImage(base, "data/down.png");
		characterJump = getImage(base, "data/jumped.png");
		
		background = getImage(base, "data/background.png");
		
		heliboy = getImage(base, "data/heliboy.png");
		heliboy2 = getImage(base, "data/heliboy2.png");
		heliboy3 = getImage(base, "data/heliboy3.png");
		heliboy4 = getImage(base, "data/heliboy4.png");
		heliboy5 = getImage(base, "data/heliboy5.png");
		
		anim = new Animation();
		anim.addFrame(character, 1250);
		anim.addFrame(character2, 50);
		anim.addFrame(character3, 50);
		anim.addFrame(character2, 50);
		
		hanim = new Animation();
		hanim.addFrame(heliboy, 100);
		hanim.addFrame(heliboy2, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy5, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy2, 100);
		
		currentSprite = anim.getImage();
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		hb = new Heliboy(340, 360);
		hb2 = new Heliboy(700, 360);
		robot = new Robot();

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			robot.update();
			if (robot.isJumped()) {
				currentSprite = characterJump;
			} else if (robot.isJumped() == false && robot.isDucked() == false) {
				currentSprite = anim.getImage();
			}
			
			ArrayList projectiles = robot.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++){
				Projectile p = (Projectile) projectiles.get(i);
				if(p.isVisible() == true){
					p.update();
				}else{
					projectiles.remove(i);
				}
			}
			hb.update();
			hb2.update();
			bg1.update();
			bg2.update();
			
			animate();
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
		g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
		
		ArrayList projectiles = robot.getProjectiles();
		for(int i = 0; i <projectiles.size(); i++){
			Projectile p = (Projectile) projectiles.get(i);
			g.setColor(Color.YELLOW);
			g.fillRect(p.getX(), p.getY(), 10, 5);
		}
		
		g.drawImage(currentSprite, robot.getCenterX() - 61,
				robot.getCenterY() - 63, this);
		g.drawImage(hanim.getImage(), hb.getCenterX() - 48, hb.getCenterY() - 48, this);
		g.drawImage(hanim.getImage(), hb2.getCenterX() - 48, hb2.getCenterY() - 48, this);
	}

	@Override
	public void update(Graphics arg0) {
		// TODO Auto-generated method stub
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		arg0.drawImage(image, 0, 0, this);
	}
	
	public void animate(){
		anim.update(10);
		hanim.update(50);
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Move up");
			break;
		case KeyEvent.VK_DOWN:
			currentSprite = characterDown;
			if (robot.isJumped() == false) {
				robot.setDucked(true);
				robot.setSpeedX(0);
			}
			break;
		case KeyEvent.VK_LEFT:
			robot.moveLeft();
			robot.setMovingLeft(true);
			break;
		case KeyEvent.VK_RIGHT:
			robot.moveRight();
			robot.setMovingRight(true);
			break;
		case KeyEvent.VK_SPACE:
			robot.jump();
			break;
		case KeyEvent.VK_CONTROL:
			if(robot.isDucked() == false && robot.isJumped() == false){
				robot.shoot();
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Stop moving up");
			break;
		case KeyEvent.VK_DOWN:
			currentSprite = anim.getImage();
			robot.setDucked(false);
			break;
		case KeyEvent.VK_LEFT:
			robot.stopLeft();
			break;
		case KeyEvent.VK_RIGHT:
			robot.stopRight();
			break;
		case KeyEvent.VK_SPACE:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the bg1
	 */
	public static Background getBg1() {
		return bg1;
	}

	/**
	 * @param bg1
	 *            the bg1 to set
	 */
	public static void setBg1(Background bg1) {
		StartingClass.bg1 = bg1;
	}

	/**
	 * @return the bg2
	 */
	public static Background getBg2() {
		return bg2;
	}

	/**
	 * @param bg2
	 *            the bg2 to set
	 */
	public static void setBg2(Background bg2) {
		StartingClass.bg2 = bg2;
	}
}
