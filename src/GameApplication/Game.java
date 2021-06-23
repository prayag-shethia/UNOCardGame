package GameApplication;

import java.util.ArrayList;

/**
 *
 * Represents a game object. The game object runs the core functionality and logic for the program.
 * The 2 players, drawPile and discardPile are created in this class.
 * 
 * @author Daniel Kathiresan
 * @author Taylor Pringle
 * @author Prayag Shethia
 */
public class Game {

    static Player player1;
    static String PLAYER1NAME="";
    static Player player2;
    static String PLAYER2NAME="";
    static Player winner=null;
    
    static Player currentPlayer;
    
    static ArrayList<String> gameLog = new ArrayList(); // Keeps a record of the moves throughout the game
    static GameLog log;
    static DrawPile drawPile = new DrawPile();
    static DiscardPile discardPile;
    Turns turn;

    static Card playedCard;
    static boolean skipNextTurn;
    static boolean playing = true;
    static boolean forfeit = false; 
    
    public Game(){
        log = new GameLog();
        discardPile = new DiscardPile();
        turn = new Turns();
    }

    /**
     * Prepares the game by assigning player1 the turn, logs the start of the 
     * game in the game log and calls drawFirstCard function.
     */
    public void startGame(){
        player1.setTurn(true);
        player2.setTurn(false);
        PLAYER1NAME = player1.getUserName();
        PLAYER2NAME = player2.getUserName();
        currentPlayer = player1;
        log.writeGameLogDB("Uno Game between Player 1: " + PLAYER1NAME + " and Player 2: " + PLAYER2NAME + " has started!");
        playedCard = drawPile.drawFirstCard();       
    }
    
    /**
     * Determines the winner after game ends either by points or by forfeit. 
     * Logs the end of the game in the game log
     */
    public static void endGame(){
        //Game.playing = false;
        if(forfeit){ //Current player looses if game has been forfeited
            Game.log.writeGameLogDB((Game.player1.turn? Game.PLAYER1NAME : Game.PLAYER2NAME) + " has forfeited the game!");
            if(currentPlayer.getUserName().equalsIgnoreCase(player1.getUserName())){
                winner = player2;
            }
            else{
                winner = player1;
            }
            log.writeGameLogDB((!Game.player1.turn? Game.PLAYER1NAME : Game.PLAYER2NAME) + " won the game!");
        }else{
            if(currentPlayer.getUserName().equalsIgnoreCase(player1.getUserName())){
                 winner = player1;
             }
             else{
                 winner = player2;
             }
            log.writeGameLogDB((Game.player1.turn? Game.PLAYER1NAME : Game.PLAYER2NAME) + " won the game!");
        }
        log.writeGameLogDB("Player 1: "+Game.PLAYER1NAME+" has "+Game.player1.getPoints()+" points.");
        log.writeGameLogDB("Player 2: "+Game.PLAYER2NAME+" has "+Game.player2.getPoints()+" points.");
    }
    
}
