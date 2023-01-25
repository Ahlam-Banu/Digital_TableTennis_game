import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * SoundHandler class governs sound related features of the game
 * @author Ahlam Banu
 * @version 1.0
 */
public class SoundHandler {
	/**
	 *
	 * RunMusic method takes the input file of the desired sound to be used in the game, opens the file and plays the sound by 
	 * running the file
	 * @param path File path of the audio file used in the game
	 */
	public static void RunMusic(String path) {
		try {
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));		
			Clip clip = AudioSystem.getClip();
			clip.open(inputStream); // opens file
			clip.start(); // plays sound
		}catch (UnsupportedAudioFileException e) {
			e.printStackTrace();	
		}catch (IOException e) {
			e.printStackTrace();	
		}catch (LineUnavailableException e) {
			e.printStackTrace();
		}		
	} 
}
