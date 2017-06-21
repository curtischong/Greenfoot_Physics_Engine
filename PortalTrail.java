import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class PortalTrail here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PortalTrail extends Particles
{
    //type 0 = orange, type 1 = blue
    private int originalTransparency = 255;
    private int transparencyCooldown = 2;
    public PortalTrail(double xLoc, double yLoc, boolean isOrange){

        GreenfootImage image = new GreenfootImage(2, 2);
        if(isOrange){
            image.setColor(java.awt.Color.ORANGE);
        }else{
            image.setColor(java.awt.Color.BLUE);
        }
        image.fill();
        setImage(image);
        Random rand = new Random();
        Vx = ((rand.nextInt(500)-500)/400);
        Vy = ((rand.nextInt(500)-500)/400);
        x = xLoc;
        y = yLoc;
    }

    public void act() 
    {
        moveParticle();
        if(originalTransparency <= 0){
            Editor m = (Editor)getWorld();
            m.removeObject(this);
        }else if(transparencyCooldown == 0){
            getImage().setTransparency(originalTransparency);
            originalTransparency-=7;    
            transparencyCooldown = 2;
        }else{
            transparencyCooldown--;
        }
    }    
}
