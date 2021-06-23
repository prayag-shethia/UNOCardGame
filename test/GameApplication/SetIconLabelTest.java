/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameApplication;

import javax.swing.JLabel;
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
public class SetIconLabelTest {
    SetIconLabel setIconLabel;
    static String iconAddress;
    JLabel label;
    
    public SetIconLabelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        setIconLabel = new SetIconLabel(iconAddress, label);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setIcon method, of class SetIconLabel.
     */
    @Test(expected = NullPointerException.class)
    public void testSetIconUnInitialisedLabel() {
        System.out.println("setIcon");
        iconAddress = "./Images/uno-logo.png";
        setIconLabel.setIcon();
        String expOutput="",output="";
        assertEquals(expOutput,output);
        
        //fail("The test case is a prototype.");
    }
    
}
