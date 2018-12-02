import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.*;
import java.awt.Color;

/**
 * Receives an input from user and shows what the user types in a text box as the user is typing. 
 * Code from: http://www.greenfoot.org/topics/5532
 * @author N/A
 * @version N/A
 */
public class StringInputBox extends UIItems
{
    //instantiating private variables
    final int MAX_INPUT_LENGTH = 20;
    String text;
    /**
     * constructor class for StringInputBox
     */
    public StringInputBox(String word)
    {
        text = word;
        updateImage();
    }

    /**
     * This method sets the text of the box to the variable that is kept track of in the method.
     * We also set the background colour and center the word in the middle of hte box
     */
    private void updateImage()
    {   
        //sets background colour and places text
        GreenfootImage image = new GreenfootImage(500, 60);
        image.setColor(Color.lightGray);
        image.fill();
        image.fillRect(3, 3, image.getWidth(), 30);
        image.setColor(Color.white);
        image.fillRect(24, 17, image.getWidth()-48, 24);
        GreenfootImage textImage = new GreenfootImage(text, 24, Color.black, null);
        //centers the text
        image.drawImage(textImage, (image.getWidth()-textImage.getWidth())/2, 17);
        setImage(image);
    }

    /**
     * every ack we check for key presses and when the enter key is pressed, we send the string to the world class to see if the word has been spelled right
     */
   public void act()
    {
        //get key. Most acts don't have a keypress so we have a catch for that below
        Editor m = (Editor)getWorld();

        String key = Greenfoot.getKey();
        if (key == null) return;
        if(m.isWritingFileName() == false) return;
        if ("enter".equals(key) && text.length() > 0)
        {
            //send the word typed to the world class for checks and then reset the word box
            m.enterSaveFile(text);
            updateImage();
            return;
        }
        //check to see the types of input
        //backspace removes the last character from the variable text
        if ("backspace".equals(key) && text.length() > 0) text = text.substring(0, text.length() - 1);
        if ("escape".equals(key)) text = "";
        if ("space".equals(key)) key = " ";
        //add to the current string
        if (key.length() == 1 && text.length() < MAX_INPUT_LENGTH) text += key;
        updateImage();
    }
}
