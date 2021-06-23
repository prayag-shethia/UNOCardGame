/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameApplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 * Represents a class implementing the Action Listener
 * Card Action is used to hear any card buttons clicked and thus, 
 * choosing the corresponding card from the player hand.
 * 
 * @author Daniel Kathiresan
 * @author Taylor Pringle
 * @author Prayag Shethia
 */
public class CardAction implements ActionListener{
    
    public static int index;
    ArrayList<JButton> cardButtons;
    UnoPlayScreen playScreen;
    
    public CardAction(ArrayList<JButton> cardButtons,UnoPlayScreen playScreen){
        this.cardButtons = cardButtons;
        this.playScreen = playScreen;
    }

    /**
     * ActionPerformed is an method overridden to get the button source,
     * see its position in the buttons array and use that position to determine the 
     * card from the players hand that it has been linked to.
     * 
     * @param e as an Action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0;i<cardButtons.size();i++){
            if(cardButtons.get(i) == e.getSource()){
                index = i;
            }
        }
        Game.currentPlayer.hand.selectCard(index);
    }
}
