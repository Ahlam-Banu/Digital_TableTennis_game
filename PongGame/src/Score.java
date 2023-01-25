import java.awt.*;
/**
 * Score sets the properties of the score board component in the game, it calculates and displays the scores of each player
 * @author Ahlam Banu
 *@version 1.0
 */
public class Score extends Rectangle{
	
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int player1;
	int player2;
	static String name1;
	static String name2;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @param GAME_WIDTH This parameter sets width of score board
	 * @param GAME_HEIGHT This parameter sets height of score board
	 * @param name1 This parameter is the name of player 1 
	 * @param name2 This parameter is the name of player 2
	 */
	Score(int GAME_WIDTH, int GAME_HEIGHT, String name1, String name2){
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
		Score.name1 = name1;
		Score.name2 = name2;
	}
	/**
	 * This method displays the scores of the players and controls the visual properties of the scores' display
	 * @param g This parameter represents the visual properties of Score 
	 */
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Consolas",Font.PLAIN,60)); //font name, font size
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		//g.drawLine(0,GAME_HEIGHT/2,GAME_WIDTH, GAME_HEIGHT/2);
		g.drawString(name1+" "+String.valueOf(player1/10) + String.valueOf(player1 % 10), GAME_WIDTH/2 - 345,45);
		g.drawString(String.valueOf(player2/10) + String.valueOf(player2 % 10)+" "+name2, GAME_WIDTH/2 + 15,45);
	}

}
