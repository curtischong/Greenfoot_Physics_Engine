import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This button will 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PausePlay extends MainBar
{
    /**
     * Act - do whatever the PausePlay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public PausePlay(){
        GreenfootImage image = new  GreenfootImage(10, 10);
        image.setColor(java.awt.Color.GREEN);
        image.fill();
        setImage(image);
    }

    public void act() 
    {
        if (Greenfoot.mousePressed(this))
        {
            Editor m = (Editor)getWorld();
            //m.saveToFile();
            m.toggleBuildState();
            
        }
    }    
}
