/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameApplication;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author prayagshethia
 */
public class TurnsTest {
    UnoGUI unoGUI;
    Turns turns;
    Game game;
    
    public TurnsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        unoGUI = new UnoGUI();
        game = new Game();
       // turns = new Turns();
        Turns.playingScreen = new UnoPlayScreen(unoGUI);
        Game.playing = true;
        Game.player1 = new Player("Bob",Game.drawPile);
        Game.player2 = new Player("Patrick",Game.drawPile);
        Game.currentPlayer = Game.player1;
        Game.player1.turn = true;
        Game.PLAYER1NAME = Game.player1.getUserName();
        Game.PLAYER2NAME = Game.player2.getUserName();
    }
    
    @After
    public void tearDown() {
    }
    

    /**
     * Test of endTurn method, of class Turns.
     */
    @Test
    public void testEndTurnGameWinner() {
        System.out.println("GameWinner");
        //unoGUI.unoGame.savePlayerNames("Bob","Patrick");
        Game.player1.setPoints(250);
        Turns.endTurn();
        Player expectedWinner = Game.player1;
        assertEquals(expectedWinner,Game.winner);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of endTurn method, of class Turns.
     */
    @Test
    public void testEndTurnEndOfGameTurnChangedSuccessful() {
        System.out.println("EndOfGameSuccessfulTurnChanged");
        //unoGUI.unoGame.savePlayerNames("Bob","Patrick");
        Game.skipNextTurn = false;
        Turns.endTurn();
        String currentPlayerName = Game.currentPlayer.getUserName();
        String expectedPlayerName = Game.player2.getUserName();
        assertEquals(expectedPlayerName,currentPlayerName);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of endTurn method, of class Turns.
     */
    @Test
    public void testEndTurnSkipTurn() {
        System.out.println("SkipTurn");
        Game.skipNextTurn = true;
        Turns.endTurn();
        String currentPlayerName = Game.currentPlayer.getUserName();
        String expectedPlayerName = Game.player1.getUserName();
        assertEquals(expectedPlayerName,currentPlayerName);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
