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
public class GameTest {
    UnoGUI unoGUI;
    Game game;
    
    public GameTest() {
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of startGame method, of class Game.
     */
    @Test
    public void testStartGameStartingPlayer() {
        System.out.println("StartGameStartingPlayer");
        Game.player1 = new Player("Bob",Game.drawPile);
        Game.player2 = new Player("Patrick",Game.drawPile);
        Game.currentPlayer = Game.player1;
        game.startGame();
        String expectedCurrentPlayerName = Game.player1.getUserName();
        assertEquals(expectedCurrentPlayerName,Game.currentPlayer.getUserName());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of startGame method, of class Game.
     */
    @Test
    public void testStartGameFirstShowCard() {
        System.out.println("StartGameFirstShowCard");
        Game.player1 = new Player("Bob",Game.drawPile);
        Game.player2 = new Player("Patrick",Game.drawPile);
        game.startGame();
        Card expectedCard = Game.discardPile.deck.get(0);
        assertEquals(expectedCard,Game.playedCard);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of endGame method, of class Game.
     */
    @Test
    public void testEndGameForfeit() {
        System.out.println("EndGameForfeit");
        Game.forfeit = true;
        Game.player1 = new Player("Bob",Game.drawPile);
        Game.player2 = new Player("Patrick",Game.drawPile);
        Game.currentPlayer = Game.player1;
        Game.endGame();
        Player oppositeforfeitingPlayer = Game.player2; //player who opposed the forfeited player
        Player winningPlayer = Game.winner; 
        assertEquals(oppositeforfeitingPlayer,winningPlayer);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }  
}
