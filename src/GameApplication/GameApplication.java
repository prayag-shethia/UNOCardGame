package GameApplication;

import java.util.Scanner;
import javax.swing.JTextArea;

/**
 * Represents a GameApplication object. This object creates the static GameLog and PlayerRecords 
 * objects used throughout the program.
 * Saves the player names by checking the DB.
 * Once the game is finished, it saves the player records as a log to the gameLog GUI.
 * 
 * @author Daniel Kathiresan
 * @author Taylor Pringle
 * @author Prayag Shethia
 */
public class GameApplication {
    
    static PlayerRecords uno;
    static Scanner scan = new Scanner(System.in);
    static GameLog log;
    static ReadRules rules;
    static Game game;
    
    public GameApplication(){
        uno = new PlayerRecords();
        log = new GameLog();
        rules = new ReadRules();
    }
    
    /**
     * Saves the player1 and player2 usernames by checking the DB for existing names or creating new ones.
     * The players are created and game is initialized.
     * 
     * @param name1 as a String for player 1 name.
     * @param name2 as a String for player 2 name.
     */
    public void savePlayerNames(String name1, String name2){
        uno.checkPlayerDB(name1.trim());
        uno.checkPlayerDB(name2.trim());
        Game.player1=new Player(name1.toUpperCase(), Game.drawPile);
        Game.player2=new Player(name2.toUpperCase(), Game.drawPile);
        game = new Game();
    }
    
    /**
     * Reads text from GameLogDB and prints it to the TextArea.
     * @param gameLogTextArea
     */
    public void displayGameLogNew (JTextArea gameLogTextArea) {
        String logString = log.gameLogToString(log.readGameLogDB());
        gameLogTextArea.setText(logString);
        //gameLogTextField.setText(Game.gameLog.toString());
    }
}