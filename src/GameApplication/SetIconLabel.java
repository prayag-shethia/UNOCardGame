/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameApplication;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * SetIconLabel is a child class from the interface SetIcon.
 * It is used to set the icon for a label by scaling it into the label icon.
 * It has image reference and the label as parameters.
 * 
 * @author prayagshethia
 * @author taylor
 * @author daniel
 */
public class SetIconLabel implements SetIcon{

    String imageRef;
    JLabel label;
    
    public SetIconLabel(String imageRef,JLabel label){
        this.imageRef = imageRef;
        this.label = label;
    }
    
    /**
     *
     * The method setIcon uses the functionality to set the corresponding image as icon for the label.
     * If the image reference or label is uninitialized it throws an exception.
     *
     */
    @Override
    public void setIcon() {
        if(imageRef == null){
            throw new NullPointerException("Image reference is not initialised!");
        }
        if(label == null){
            throw new NullPointerException("Label is not initialised!");
        }
        ImageIcon icon1 = new ImageIcon(imageRef);
        Image image1 = icon1.getImage();
        Image imageScale1 = image1.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon1 = new ImageIcon(imageScale1);
        label.setIcon(scaledIcon1);
    }
}
