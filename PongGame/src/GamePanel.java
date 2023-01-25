import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.sound.sampled.*;
import java.io.IOException;
/**
 * GamePanel is a class that represents JPanel. It can group or store a set of components together, mainly for creating
 * a user interface.
 * @author Ahlam Banu
 * @version 1.0
 *
 */

public class GamePanel extends JPanel implements Runnable{
	
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int BALL_DIAMETER = 20; //20 pixels
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	static String name1 = JOptionPane.showInputDialog("Player 1's Name :");
	static String name2 = JOptionPane.showInputDialog("Player 2's Name :");
	
	private static final long serialVersionUID = 1L;
	
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	
	GamePanel() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT, name1, name2);
		this.setFocusable(true); // to read key strokes
		this.addKeyListener(new AL()); // to respond to key strokes; inner class Action Listener
		this.setPreferredSize(SCREEN_SIZE); // set a Dimension; SCREEN_SIZE
		if(name1 == null)name1 = "";
		if(name2 == null)name2 = "";
		gameThread = new Thread(this);
		gameThread.start(); // to start this thread
	}
	
/* This method creates a ping-pong ball with it's properties and positions it at the center of the window 
 * when the game starts and/or restarts */
	public void newBall() {
		random = new Random();
		ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),(GAME_HEIGHT/2)-(BALL_DIAMETER/2),BALL_DIAMETER,BALL_DIAMETER); 
		//BALL_DIAMETR for width and height as last two parameters
	}
	
/* This method creates two paddles and positions each on either side of the game panel/ping-pong table */
	public void newPaddles() {
		paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
		paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2); //2  unique id number
	}

/* This method is used to create an image that has the same width and height as the game panel and create a graphics context 
 * for drawing to an off-screen image
 * It also draws as much of the specified image as is currently available.The image is drawn with its top-left corner at(x, y) (which is 
 * (0,0) in this case) in this graphics context's coordinate-space.
 * If the image has completely loaded, then drawImage returns true
 */
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight()); //creating an image that has the same width and height as our game panel
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this); // drawImage returns true if the image has completely loaded and its pixels are no longer being changed
	}
	
/* This method controls the "bouncing off" movement of the ball when it collides with the edges of the game panel or each paddle 
 * It also controls/ limits the paddles from moving beyond the top and bottom edges of the game panel  
 * It also allows us give each player a score when the other player misses the ball and then reset game by  creating new paddles 
 * and ball
*/
	public void checkCollision(){
		if(ball.y <= 0) {		// Bounces the ball off the top window edge

			ball.setYDirection(-ball.yVelocity);
			SoundHandler.RunMusic("C:\\\\Users\\\\Ahlam\\\\eclipse-workspace\\\\GameWorkspace\\\\PongGame\\\\FirstOOPsProject\\\\src\\\\Sound\\\\PingPongBall1.wav");
		}
		if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {		// Bounces the ball off the bottom window edge
			ball.setYDirection(-ball.yVelocity);
			SoundHandler.RunMusic("C:\\\\Users\\\\Ahlam\\\\eclipse-workspace\\\\GameWorkspace\\\\PongGame\\\\FirstOOPsProject\\\\src\\\\Sound\\\\PingPongBall1.wav");
		}
		
		if(paddle1.y <= 0) // To stop green paddle at top window edge
			paddle1.y = 0;

		if(paddle1.y >= GAME_HEIGHT-PADDLE_HEIGHT) //To stop green paddle at bottom window edge
			paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
		
		if(paddle2.y <= 0) //stops yellow paddle at top window edge
			paddle2.y = 0;
		
		if(paddle2.y >= GAME_HEIGHT-PADDLE_HEIGHT) //stops yellow paddle at bottom window edge
			paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
		//to bounce the ball off the paddle:
		if(ball.intersects(paddle1)){//we are able to use this method because Ball is a subclass of superclass Rectangle
			ball.setXDirection(-ball.xVelocity);
			ball.xVelocity++; //optional: increases the speed of the ball after it bounces off the paddle
			SoundHandler.RunMusic("C:\\Users\\Ahlam\\eclipse-workspace\\GameWorkspace\\PongGame\\FirstOOPsProject\\src\\Sound\\PingPongBall2.wav");
		}
			
		if(ball.intersects(paddle2)){
			ball.setXDirection(-ball.xVelocity);
			ball.xVelocity-- ;
			SoundHandler.RunMusic("C:\\Users\\Ahlam\\eclipse-workspace\\GameWorkspace\\PongGame\\FirstOOPsProject\\src\\Sound\\PingPongBall2.wav");
			}

// To give each player a score when the other player misses the ball and then reset game i.e. create new paddles and ball
		if(ball.x <= 0){
			score.player2++;
			newPaddles();
			newBall();			
			//System.out.println("Player 2 score -> "+score.player2);
		}
		if(ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			score.player1++;
			newPaddles();
			newBall();
			}
			//System.out.println("Player 1 score -> "+score.player1);
	}
	
/* This method is used to draw the components of this game: ball, paddle 1, paddle 2, score 
 * It is used to call the draw() method from each of these components' class
*/
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	
/* This method is used to handle the game loop for this game */	
	public void run() {
		long lastTime = System.nanoTime();
		double amountofTicks = 60.0; //60 frames per second
		double ns = 1000000000 / amountofTicks;
		double delta = 0;
		//game loop
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				move(); //move all of the components
				checkCollision(); // check for any collisions
				//GamePanel.audio();
				repaint(); //repaint everything
				delta--;				
			}			
		}		
	}
/* This method is used to call other methods such as Paddle.move() for the paddles and Ball.move() from classes Paddle
 *  and Ball respectively */
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
/* Then we use an inner class AL (Action Listener) with 2 methods that are called in class Paddle 
 * to control the movement of each paddle depending on the keystrokes */
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}
