/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameApplication;

import javax.swing.JButton;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author prayagshethia
 */
public class SetIconButtonTest {
    SetIconButton setIconButton;
    static String iconAddress;
    JButton button;
    
    public SetIconButtonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        setIconButton = new SetIconButton(iconAddress,button);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setIcon method, of class SetIconButton.
     */
    @Test(expected = NullPointerException.class)
    public void testSetIconUnInitialisedInstances() {
        System.out.println("setIcon");
         String expOutput="",output="";
        setIconButton.setIcon();
        assertEquals(expOutput,output);

        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of setIcon method, of class SetIconButton.
     */
    @Test(expected = NullPointerException.class)
    public void testSetIconEmptyString() {
        System.out.println("setIcon2");
        this.iconAddress = "";
        String expOutput="Image reference is empty. Please add directory.";
        this.button = new JButton();
        String output="";
        if(iconAddress.isEmpty()){
            output = "Image reference is empty. Please add directory.";
        }
        assertEquals(expOutput,output);
        
        setIconButton.setIcon();

        //fail("The test case is a prototype.");
    }
    
}
