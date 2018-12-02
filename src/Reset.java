import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Reset here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Reset extends MainBar
{
    public Reset(){
        GreenfootImage image = new  GreenfootImage(10, 10);
        image.setColor(java.awt.Color.YELLOW);
        image.fill();
        setImage(image);
    }
    public void act() 
    {
        if (Greenfoot.mousePressed(this))
        {
            Editor m = (Editor)getWorld();
            m.readFile(m.getFileName(), false);
        }
    }    
}
