import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Notification here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Notification extends Actor
{

    private int transparency = 255;
    private int showLength = 50;
    public Notification(String word){
        //sets background colour and places text
        GreenfootImage image = new GreenfootImage(180, 30);
        image.setColor(new Color(230, 126, 34));
        image.fill();
        GreenfootImage textImage = new GreenfootImage(word, 15, Color.white, null);
        //centers the text
        image.drawImage(textImage, (image.getWidth()-textImage.getWidth())/2, 5);
        setImage(image);
    }
    public void act() 
    {
        Editor m = (Editor)getWorld();
        if(transparency <= 0){
            m.removeObject(this);
        }else if(showLength > 0){
            showLength--;
        }else if(showLength == 0){
             getImage().setTransparency(transparency);
             transparency-=4;
        }
    }    
}
