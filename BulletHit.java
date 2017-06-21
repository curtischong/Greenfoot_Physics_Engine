import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BulletHit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BulletHit extends Particles
{
    private int originalTransparency = 75;

    public BulletHit(double moveX, double moveY, double xLoc, double yLoc){
        GreenfootImage image = new GreenfootImage(5, 5);
        image.setColor(java.awt.Color.BLACK);
        image.fill();
        image.setTransparency(originalTransparency);
        setImage(image);
        x = xLoc;
        y = yLoc;
        Vx = moveX;
        Vy = moveY;
    }

    public void act() 
    {
        moveParticle();
        if(originalTransparency == 1){
            if (getWorld() instanceof Editor){
                Editor m = (Editor)getWorld();
            } else {
                Main m = (Main)getWorld();
                m.removeObject(this);
            }
        }
        if(originalTransparency > 4){
            getImage().setTransparency(--originalTransparency);
        }

    }      
}
