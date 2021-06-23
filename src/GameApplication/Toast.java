/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameApplication;

/**
 * Toast class is used to create a pop up message that is displayed for a few seconds for a better visual.
 * It fades in and fades out using the corresponding functions to create a visually exciting element for messages.
 * 
 * @author prayagshethia
 * @author taylor
 * @author daniel
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.Border;

class Toast extends JFrame {

    private final float MAX_OPACITY = 0.8f;
    private final float OPACITY_INCREMENT = 0.05f;
    private final int FADE_REFRESH_RATE = 20;
    private final int WINDOW_RADIUS = 15;

    
    public Toast(String toastText) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setFocusableWindowState(false);
        setOpacity(0.4f);

        // setup the toast label
        JLabel b1 = new JLabel(toastText);
        b1.setForeground(Color.WHITE);
        b1.setHorizontalAlignment(JLabel.CENTER);
        b1.setVerticalAlignment(JLabel.CENTER);
        b1.setOpaque(false);
        b1.setFont(new Font("Arial", Font.BOLD, 18));
        add(b1);
        
        //padding for label
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        b1.setBorder(padding);
        
        //setSize(toastText.length() * CHARACTER_LENGTH_MULTIPLIER, 50);
        setPreferredSize(new Dimension(300,100));
        pack();
        setLocationRelativeTo(null);
        
        // configure frame
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), WINDOW_RADIUS, WINDOW_RADIUS));
        getContentPane().setBackground(new Color(0, 0, 0, 170));

    }

    public void fadeIn() {
        setOpacity(0);
        setVisible(true);

        final Timer timer = new Timer(FADE_REFRESH_RATE, null);
        timer.setRepeats(true);
        timer.addActionListener(new ActionListener() {
            private float opacity = 0;


            @Override
            public void actionPerformed(ActionEvent e) {
                opacity += OPACITY_INCREMENT;
                setOpacity(Math.min(opacity, MAX_OPACITY));
                if (opacity >= MAX_OPACITY) {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    public void fadeOut() {
        final Timer timer = new Timer(FADE_REFRESH_RATE, null);
        timer.setRepeats(true);
        timer.addActionListener(new ActionListener() {
            private float opacity = MAX_OPACITY;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                opacity -= OPACITY_INCREMENT;
                setOpacity(Math.max(opacity, 0));
                if (opacity <= 0) {
                    timer.stop();
                    setVisible(false);
                    dispose();
                }
            }
        });
        setOpacity(MAX_OPACITY);
        timer.start();
    }

    public void makeToast(final String toastText, final int durationSec) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Toast toastFrame = new Toast(toastText);
                    toastFrame.fadeIn();
                    Thread.sleep(durationSec * 1000);
                    toastFrame.fadeOut();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
}