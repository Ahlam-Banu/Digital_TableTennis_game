import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
/**
 * GameFrame is treated the same as JFrame. It is the top-level window, with border and a title bar. This class has various
 * methods which are used to customize it.
 * @author Ahlam Banu
 * @version 1.0
 *
 */
public class GameFrame extends JFrame {
	GamePanel panel;
	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * @throws UnsupportedAudioFileException if the File does not point to valid audio file data recognized by the system
	 * @throws IOException if an I/O exception occurs
	 * @throws LineUnavailableException if a line cannot be opened because it is unavailable.
	 */
	GameFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		panel = new GamePanel();
		
		this.add(panel);
		this.setTitle("Ahlam's Ping Pong Game");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack(); //this will adjust the size of our JFrame accordingly to accommodate size of GamePanel
		this.setVisible(true); //to be able to see the GameFrame
		this.setLocationRelativeTo(null); //set the GameFrame to the center of our window
	}
}
