import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LoadGameButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LoadGameButton extends MainBar
{
    public LoadGameButton(){
        //setImage("load.png");
        GreenfootImage image = getImage();
        image.scale(image.getWidth(), image.getHeight());
        setImage(image);
    }
    public void act() 
    {
        if (Greenfoot.mousePressed(this))
        {
            Editor m = (Editor)getWorld();
            m.loadGame();
        }
    }
}
