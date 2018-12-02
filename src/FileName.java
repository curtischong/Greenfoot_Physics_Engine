import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FileName here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FileName extends MainBar
{
    public FileName(){
        GreenfootImage image = new GreenfootImage(150, 20);
        image.setColor(java.awt.Color.WHITE);
        image.drawString("Unamed",75,10);
        setImage(image);
    }
    public void changeName(String name){
        GreenfootImage image = new GreenfootImage(150, 20);
        image.setColor(java.awt.Color.WHITE);
        image.drawString(name,75,10);
        setImage(image);    
    }
}
