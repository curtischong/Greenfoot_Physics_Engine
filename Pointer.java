import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class cursor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pointer extends Actor
{
    private GreenfootImage norm = new GreenfootImage("mouse.png");
    private GreenfootImage shootImg = new GreenfootImage("cursor1.png");
    /**
     * Act - do whatever the cursor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null && mouse.getX() > 0 && mouse.getX() < 1000 && mouse.getY() > 0 && mouse.getY() < 600){
            setLocation(mouse.getX(), mouse.getY());
        } 
        
    }   
    
    public void setShoot(){
        setImage(shootImg);
    }
    
    public void resetShoot(){
        setImage(norm);
    }
}
