/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameApplication;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * SetIconButton is a child class from the interface SetIcon.
 * It is used to set the icon for a button by scaling it into the button icon.
 * It has image reference and the button as parameters.
 * 
 * @author prayagshethia
 * @author taylor
 * @author daniel
 * 
 */
public class SetIconButton implements SetIcon{

    String imageRef;
    JButton button;
    
    public SetIconButton(String imageRef,JButton button){
        this.imageRef = imageRef;
        this.button = button;
    }
    
    /**
     * The method setIcon uses the functionality to set the corresponding image as icon for the button.
     * If the image reference or button is uninitialized it throws an exception.
     */
    @Override
    public void setIcon() {
        if(imageRef == null){
            throw new NullPointerException("Image reference is not initialised!");
        }
        if(button == null){
            throw new NullPointerException("Button is not initialised!");
        }
        ImageIcon icon = new ImageIcon(imageRef);
        Image image = icon.getImage();
        Image imageScale = image.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imageScale);
        button.setIcon(scaledIcon);
    }
}
