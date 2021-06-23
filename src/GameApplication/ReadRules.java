/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Represents a ReadRules object.
 *
 * 
 * @author Daniel Kathiresan
 * @author Taylor Pringle
 * @author Prayag Shethia
 */
public class ReadRules {

    File rules = new File("./resources/readRules.txt");

    /**
     * ReadRules reads the text file to read the rules of the game.
     * It is used to display on the GUI.
     * 
     * @return text as a String
     */
    public String readRules(){
        String line,text = "";
        try{
            FileReader fr = new FileReader(rules);
            BufferedReader inputStream = new BufferedReader(fr);
            while((line=inputStream.readLine())!=null) {
               text+=line+"\n";
            }
            fr.close();
            inputStream.close();
        }catch(FileNotFoundException ex){
            System.out.println("Error. File has not been found.");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return text;
    }
}
