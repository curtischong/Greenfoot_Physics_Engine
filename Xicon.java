import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Xicon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Xicon extends UIItems
{
    public Xicon(){
        GreenfootImage image = new GreenfootImage(100, 100);
        image.setColor(java.awt.Color.RED);
        image.setFont(image.getFont().deriveFont(18f));
        image.drawString("X",50,50);
        setImage(image);    
    }
    public void act() 
    {
        if (Greenfoot.mousePressed(this))
        {
            Editor m = (Editor)getWorld();
            m.closeUI();
        }
    }    
}
