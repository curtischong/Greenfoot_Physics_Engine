import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class FileNameHolder here.
 * 
 * @author Curtis Chong
 * @version (a version number or a date)
 */
public class FileNameHolder extends UIItems
{
    private String fileName;
    
    public FileNameHolder(String name){
        fileName = name;
        //sets background colour and places text
        GreenfootImage image = new GreenfootImage(200, 30);
        image.setColor(Color.lightGray);
        image.fill();
        image.fillRect(0, 0, image.getWidth(), 30);
        image.setColor(Color.white);
        image.fillRect(24, 4, image.getWidth()-48, 22);
        GreenfootImage textImage = new GreenfootImage(fileName.substring(0, name.length()-4), 18, Color.black, null);
        //centers the text
        image.drawImage(textImage, (image.getWidth()-textImage.getWidth())/2, 6);
        setImage(image);
    }
    
    public void act() 
    {
        if (Greenfoot.mousePressed(this))
        {
            Editor m = (Editor)getWorld();
            m.loadSelectedWorld(fileName);
        }
    }    
}
