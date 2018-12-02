import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.Random;
/**
 * Write a description of class CrateParticle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CrateParticle extends Particles
{
    private int originalTransparency = 125;
    public CrateParticle(double xLoc, double yLoc){
        GreenfootImage image = new GreenfootImage(5, 2);
        image.setColor(new Color(168, 95, 0));
        image.fill();
        image.setTransparency(originalTransparency);
        setImage(image);
        x = xLoc;
        y = yLoc;
        Random rand = new Random();
        Vx = ((rand.nextInt(5)-2.5)/2);
        Vy = (-rand.nextInt(2)/13);
    }

    public void act() 
    {
        moveParticle();
        if(originalTransparency == 1){
            Editor m = (Editor)getWorld();
            m.removeObject(this);
        }
        getImage().setTransparency(--originalTransparency);
    }  
}
