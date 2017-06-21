import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class PortalParticle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PortalParticle extends Particles
{
    //type 0 = orange, type 1 = blue
    private int originalTransparency = 175;
    private int transparencyCooldown = 2;
    private boolean isOrangePortal;
    //0 = left, 1 = right, 2 = top, 3 = bot
    public PortalParticle(double xLoc, double yLoc, int movement, boolean type){
        isOrangePortal = type;
        Random rand = new Random();
        GreenfootImage image = new GreenfootImage(5, 5);
        if(isOrangePortal){
            image.setColor(java.awt.Color.ORANGE);
        }else{
            image.setColor(java.awt.Color.BLUE);
        }
        image.fill();
        image.setTransparency(originalTransparency);
        setImage(image);
        
        if(movement == 0){
            Vx = -((rand.nextInt(30) + 5)/50.0);
            Vy = 0.0;
        }else if(movement == 1){
            Vx = ((rand.nextInt(30) + 5)/50.0);
            Vy = 0.0;
        }else if(movement == 2){
            Vx = 0.0;
            Vy = -((rand.nextInt(30) + 5)/50.0);
        }else if(movement == 3){
            Vx = 0.0;
            Vy = ((rand.nextInt(30) + 5)/50.0);
        }
        
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
            getImage().setTransparency(--originalTransparency);
            --originalTransparency;
            --originalTransparency;
            transparencyCooldown = 2;
        }else{
            transparencyCooldown--;
        }
    }    
}
