/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameApplication;

import java.awt.Image;
import java.awt.MouseInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * UnoPlayScreen is a separate frame that solely manages the playing of the game.
 * The screen frame manages the entire functionality of the game from buttons, cards,
 * Pop ups for turn changes, skips, reverses, drawing action, as well as holds the menu frame button too.
 * It manages the changing of card icons for better playing of the game as well as the discard pile.
 * 
 * @author prayagshethia
 * @author taylor
 * @author daniel
 */
public class UnoPlayScreen extends javax.swing.JFrame {

    UnoGUI unoGUIForm;
    Menu menu;
    WildColour wildColour;
    GameApplication gameApplication;
    Game game = GameApplication.game; 
    ArrayList<JButton> cardButtons = new ArrayList<JButton>();
    ArrayList<String> cardIDs;
    CardAction cardActionListener = new CardAction(cardButtons,this);
    
    /**
     * Creates new form UnoPlayScreen
     */
    public UnoPlayScreen(UnoGUI unoGUI) {
        initComponents();
        this.unoGUIForm = unoGUI;
        Card.playingScreen = this;
        GameLog.playingScreen = this;
        Turns.playingScreen = this;
        unoGUIForm.setVisible(false);
        menu = new Menu(unoGUI,this);
        populateArrayList();
        
        SetIconLabel setIconLabel = new SetIconLabel("./resources/uno-cards/card_back.png",player2HandLabel);
        setIconLabel.setIcon();
        
        SetIconButton setIconDrawPile = new SetIconButton("./resources/uno-cards/card_back_draw.png",drawButton);
        setIconDrawPile.setIcon();
    }
    
    /**
     * Updates the playing screen log to display every new activity.
     * 
     * @param log as String
     */
    public void updateLog(String log){
        playingLogTextField.setText(log);
    }
    
    /**
     * Stores the buttons in an array and links all to the CardAction action listener.
     */
    public void populateArrayList(){
        cardButtons.add(jButton1);
        cardButtons.add(jButton2);
        cardButtons.add(jButton3);
        cardButtons.add(jButton4);
        cardButtons.add(jButton5);
        cardButtons.add(jButton6);
        cardButtons.add(jButton7);
        cardButtons.add(jButton8);
        cardButtons.add(jButton9);
        cardButtons.add(jButton10);
        cardButtons.add(jButton11);
        cardButtons.add(jButton12);
        cardButtons.add(jButton13);
        cardButtons.add(jButton14);
        cardButtons.add(jButton15);
        cardButtons.add(jButton16);
        cardButtons.add(jButton17);
        cardButtons.add(jButton18);
        cardButtons.add(jButton19);
        cardButtons.add(jButton20);
        cardButtons.add(jButton21);
        cardButtons.add(jButton22);
        for(JButton actionButton:cardButtons){
            actionButton.setBorder(null);
            actionButton.addActionListener(cardActionListener);
        }
    }
    
    /**
     * SetButtons function is called every time a turn changes to update the icons of the buttons as per cards in the player hand.
     * It also sets the icon for the discard Pile as every card played. 
     */
    public void setButtons(){
        setPlayerNames();
        String listString = Game.currentPlayer.hand.hand.stream().map(Object::toString).collect(Collectors.joining(","));
        String [] cardNames = listString.split(",");
        cardIDs = new ArrayList<>(Arrays.asList(cardNames));
        System.out.println(Game.currentPlayer.hand.hand);
        for(int i=0; i<cardIDs.size();i++){
            SetIconButton setIconButton = new SetIconButton(Game.currentPlayer.hand.hand.get(i).imageRef,cardButtons.get(i));
            setIconButton.setIcon();
            cardButtons.get(i).setEnabled(true);
        }
        for(int i = cardIDs.size(); i<cardButtons.size();i++){
            cardButtons.get(i).setIcon(null);
            cardButtons.get(i).setEnabled(false);
            cardButtons.get(i).setOpaque(false);
        }
        discardPileButton.setBorder(null);
        SetIconButton setIconButton1 = new SetIconButton(Game.playedCard.imageRef,discardPileButton);
        setIconButton1.setIcon();
    }
  
    /**
     * setPlayerNames is called in every new turn to set the labels of the players and their total points.
     */
    public void setPlayerNames(){
        currentPlayerUserNameLabel.setText(Game.currentPlayer.getUserName());
        currentPlayerPointsLabel.setText(String.valueOf(Game.currentPlayer.getPoints()));
        if(Game.currentPlayer==Game.player1){
            otherPlayerUsernameLabel.setText(Game.player2.getUserName());
            otherPlayerPointsLabel.setText(String.valueOf(Game.player2.getPoints()));
        }else{
            otherPlayerUsernameLabel.setText(Game.player1.getUserName());
            otherPlayerPointsLabel.setText(String.valueOf(Game.player1.getPoints()));
        }    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        playingPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        currentPlayerLabel = new javax.swing.JLabel();
        currentPlayerUserNameLabel = new javax.swing.JLabel();
        pointsLabel = new javax.swing.JLabel();
        currentPlayerPointsLabel = new javax.swing.JLabel();
        drawButton = new javax.swing.JButton();
        player2HandLabel = new javax.swing.JLabel();
        otherPlayerLabel = new javax.swing.JLabel();
        otherPlayerUsernameLabel = new javax.swing.JLabel();
        pointsLabel2 = new javax.swing.JLabel();
        otherPlayerPointsLabel = new javax.swing.JLabel();
        menuButton = new javax.swing.JButton();
        playingLogTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        discardPileButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UNO");

        jLayeredPane1.setLayout(new java.awt.CardLayout());

        playingPanel.setBackground(new java.awt.Color(89, 89, 89));
        playingPanel.setForeground(new java.awt.Color(255, 255, 255));

        jButton1.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton3.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton4.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton5.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton6.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton7.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton8.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton9.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton10.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton11.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton12.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton14.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton15.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton16.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton17.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton18.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton19.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton20.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton21.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton22.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        jButton13.setFont(new java.awt.Font(".SF NS Text", 0, 8)); // NOI18N

        currentPlayerLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        currentPlayerLabel.setForeground(new java.awt.Color(255, 255, 255));
        currentPlayerLabel.setText("Current Player:");

        currentPlayerUserNameLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        currentPlayerUserNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        currentPlayerUserNameLabel.setText("-");

        pointsLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        pointsLabel.setForeground(new java.awt.Color(255, 255, 255));
        pointsLabel.setText("Points:");

        currentPlayerPointsLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        currentPlayerPointsLabel.setForeground(new java.awt.Color(255, 255, 255));
        currentPlayerPointsLabel.setText("0");

        drawButton.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        drawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawButtonActionPerformed(evt);
            }
        });

        player2HandLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        player2HandLabel.setText("Player 2's Hand");

        otherPlayerLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        otherPlayerLabel.setForeground(new java.awt.Color(255, 255, 255));
        otherPlayerLabel.setText("Other Player:");

        otherPlayerUsernameLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        otherPlayerUsernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        otherPlayerUsernameLabel.setText("-");

        pointsLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        pointsLabel2.setForeground(new java.awt.Color(255, 255, 255));
        pointsLabel2.setText("Points:");

        otherPlayerPointsLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        otherPlayerPointsLabel.setForeground(new java.awt.Color(255, 255, 255));
        otherPlayerPointsLabel.setText("0");
        otherPlayerPointsLabel.setToolTipText("");

        menuButton.setBackground(new java.awt.Color(255, 255, 255));
        menuButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        menuButton.setText("MENU");
        menuButton.setBorder(null);
        menuButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuButton.setFocusable(false);
        menuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButtonActionPerformed(evt);
            }
        });

        playingLogTextField.setEditable(false);
        playingLogTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        playingLogTextField.setText("Log");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout playingPanelLayout = new javax.swing.GroupLayout(playingPanel);
        playingPanel.setLayout(playingPanelLayout);
        playingPanelLayout.setHorizontalGroup(
            playingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playingPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(playingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playingPanelLayout.createSequentialGroup()
                        .addComponent(otherPlayerLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(otherPlayerUsernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(playingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(playingPanelLayout.createSequentialGroup()
                                .addComponent(discardPileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(drawButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(474, 474, 474))
                            .addGroup(playingPanelLayout.createSequentialGroup()
                                .addGroup(playingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(pointsLabel)
                                    .addComponent(pointsLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(playingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(currentPlayerPointsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(otherPlayerPointsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(788, 788, 788))))
                    .addGroup(playingPanelLayout.createSequentialGroup()
                        .addComponent(currentPlayerLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentPlayerUserNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(playingPanelLayout.createSequentialGroup()
                        .addGroup(playingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playingPanelLayout.createSequentialGroup()
                                .addComponent(playingLogTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(170, 170, 170))
                            .addGroup(playingPanelLayout.createSequentialGroup()
                                .addComponent(player2HandLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(528, 528, 528)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(menuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
            .addGroup(playingPanelLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addGroup(playingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(playingPanelLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playingPanelLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        playingPanelLayout.setVerticalGroup(
            playingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playingPanelLayout.createSequentialGroup()
                .addGroup(playingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(playingPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(playingLogTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(playingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(player2HandLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(playingPanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(menuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(playingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(otherPlayerLabel)
                    .addGroup(playingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pointsLabel2)
                        .addComponent(otherPlayerPointsLabel)
                        .addComponent(otherPlayerUsernameLabel)))
                .addGap(43, 43, 43)
                .addGroup(playingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(discardPileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(drawButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(playingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentPlayerUserNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(currentPlayerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pointsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(currentPlayerPointsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(playingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(playingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(playingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jLayeredPane1.add(playingPanel, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1151, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButtonActionPerformed
        int x = MouseInfo.getPointerInfo().getLocation().x;
        int y = MouseInfo.getPointerInfo().getLocation().y;
        menu.setLocation(this.getLocation().x+(this.getWidth()-menu.getWidth()), this.getLocation().y);
        menu.setVisible(true);
    }//GEN-LAST:event_menuButtonActionPerformed

    private void drawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawButtonActionPerformed
        //JOptionPane.showMessageDialog(null, (Game.currentPlayer.getUserName()+" drew a card!"),"Draw Card",JOptionPane.INFORMATION_MESSAGE);
        Game.drawPile.drawCard(1);
        Turns.endTurn();
        setButtons();
    }//GEN-LAST:event_drawButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel currentPlayerLabel;
    private javax.swing.JLabel currentPlayerPointsLabel;
    private javax.swing.JLabel currentPlayerUserNameLabel;
    private javax.swing.JButton discardPileButton;
    private javax.swing.JButton drawButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JButton menuButton;
    private javax.swing.JLabel otherPlayerLabel;
    private javax.swing.JLabel otherPlayerPointsLabel;
    private javax.swing.JLabel otherPlayerUsernameLabel;
    private javax.swing.JLabel player2HandLabel;
    private javax.swing.JTextField playingLogTextField;
    private javax.swing.JPanel playingPanel;
    private javax.swing.JLabel pointsLabel;
    private javax.swing.JLabel pointsLabel2;
    // End of variables declaration//GEN-END:variables
}
