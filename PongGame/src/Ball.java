import java.awt.*;
import java.util.*;
/**
 * Ball is a subclass to the super-class Rectangle, it sets the properties of the ball component in the game
 * @author Ahlam Banu
 * @version 1.0
 *
 */
public class Ball extends Rectangle{
	Random random;
	int xVelocity;
	int yVelocity;
	int initialSpeed = 2;
	
	private static final long serialVersionUID = 1L;
	/**
	 * We call super constructor to assign the following arguments for us
	 * @param x Movement of the ball along the x-axis
	 * @param y Movement of the ball along the y-axis
	 * @param width The width of the ball
	 * @param height The height of the ball
	 */
	Ball(int x, int y, int width, int height){ 
		super(x,y,width,height);
		random = new Random();
		int randomXDirection = random.nextInt(2); //can have only one of the two values: 0 or 1
		if(randomXDirection == 0)
			randomXDirection--; 				//decrease by 1 so that the ball moves along the negative x axis
		setXDirection(randomXDirection*initialSpeed);
		int randomYDirection = random.nextInt(2);
		if(randomYDirection == 0)
			randomYDirection--;
		setYDirection(randomYDirection*initialSpeed);
	}

	/**
	 *  These two methods, setXDirection & setYDirection set the x and y axis direction 
	 *  movement of the ball which is random for each axis of the ball in this case 
	 * @param randomXDirection Random distance that will be covered by ball along the x-axis
	 */
	public void setXDirection(int randomXDirection) {
		xVelocity = randomXDirection;					
	}			
	
	/**
	 * 
	 * @param randomYDirection Random distance that will be covered by ball along the y-axis
	 */
	public void setYDirection(int randomYDirection) {	
		yVelocity = randomYDirection;					
	}										

	/**
	 * This method increments the x and y position of the ball causing movement of the ball
	 */
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	/**
	 * 
	 * @param g This parameter represents the visual properties of the ball
	 */
	public void draw(Graphics g) {	//This method sets the graphics of the ball
		g.setColor(Color.white);
		g.fillOval(x, y, width, height);
	}

}
