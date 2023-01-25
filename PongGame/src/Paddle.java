import java.awt.*;
import java.awt.event.*;
/**
 * Paddle is a subclass to the super-class Rectangle, it sets the properties of the paddle components in the game
 * @author Ahlam Banu
 * @version 1.0
 */
public class Paddle extends Rectangle{
	
	int id; 
	int yVelocity;
	int speed = 10;
	
	private static final long serialVersionUID = 1L;
	
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		this.id=id;
	}

/* This method handles the motion of the paddles with respect to the keys pressed
 * If the key is pressed, motions of the paddle starts in this case*/
	/**
	 * 
	 * @param e An event which indicates that a keystroke occurred in a component. 
	 */
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W) { // key = W = up
				setYDirection(-speed); //speed = speed; in this case it is = -10 which will be upwards
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_S) { // key = S = down
				setYDirection(speed);
				move();
			}
			/* 
			if(e.getKeyCode() == KeyEvent.VK_A) { // when key is pressed to move the paddle left along the x-axis as well
				setXDirection(-speed);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_D) { // when key is pressed to move the paddle right along the x-axis as well
				setXDirection(speed);
				move();
			} */
			break;
			
		case 2:
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(-speed); //speed = speed; in this case it is = -10 which will be upwards
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(speed);
				move();
			}
			/*
			if(e.getKeyCode() == KeyEvent.VK_LEFT) { // when key is pressed to move the paddle left along the x-axis as well
				setXDirection(-speed);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) { // when key is pressed to move the paddle right along the x-axis as well
				setXDirection(speed);
				move();
			} */
			break;
		}
	}
	
	/**
	 * This method handles the motion of the paddles with respect to the keys released 
	 * If the key is released, motions of the paddle stops in this case 
	 * @param e An event which indicates that a keystroke occurred in a component. 
	 */
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1: //paddle 1; green
			if(e.getKeyCode() == KeyEvent.VK_W) { // key = W = up
				setYDirection(0); //speed = 0 otherwise it will move forever
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_S) { // key = S = down
				setYDirection(0);
				move();
			}
			/*
			if(e.getKeyCode() == KeyEvent.VK_A) { //extra
				setXDirection(0);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_D) { //extra
				setXDirection(0);
				move();
			} */
			break;
			
		case 2: //paddle 2; yellow
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(0);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(0);
				move();
			}
			/*
			if(e.getKeyCode() == KeyEvent.VK_LEFT) { //extra
				setXDirection(0);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) { //extra
				setXDirection(0);
				move();
			} */
			break;
		}
		
	}
/* The paddles will only move along the y-axis, therefore, only 1 method is created to set the direction of the y-axis 
 * for the paddles*/
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
//To be able to move the paddles along the x-axis as well, we can use the following method: 
/*
	public void setXDirection(int xDirection) {
		xVelocity = xDirection;
	}
*/
	
// This method the paddles' motion which is along the y-axis		
	public void move() {
		y += yVelocity;
		//x += xVelocity; // x-axis movement of paddles
	}

	/**
	 * This method sets the paddles' display
	 * @param g This parameter represents the visual properties of the paddles
	 */
	public void draw(Graphics g) {
		if(id == 1)
			g.setColor(Color.green); // paddle 1
		else
			g.setColor(Color.yellow); //paddle 2
		g.fillRect(x,y,width,height); //fills in the color; makes the paddles visible
	}
}
