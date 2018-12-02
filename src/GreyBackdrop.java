import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GreyBackdrop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GreyBackdrop extends Actor
{
    public GreyBackdrop(){
        GreenfootImage image = new GreenfootImage(2000, 1200);
        image.setColor(java.awt.Color.BLACK);
        image.fill();
        image.setTransparency(130);
        setImage(image);        
    }
}
