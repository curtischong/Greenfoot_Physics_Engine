import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class LazerTrail here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LaserTrail extends Particles
{
    //type 0 = orange, type 1 = blue
    private int originalTransparency = 255;
    private int transparencyCooldown = 2;
    public LaserTrail(double xLoc, double yLoc){

        GreenfootImage image = new GreenfootImage(10, 10);

        image.setColor(java.awt.Color.RED);

        image.fill();
        setImage(image);
        //Random rand = new Random();
        //Vx = ((rand.nextInt(500)-500)/400);
        //Vy = ((rand.nextInt(500)-500)/400);
        x = xLoc;
        y = yLoc;
    }

    public void act() 
    {
        moveParticle();
        if(originalTransparency <= 0){
            if (getWorld() instanceof Editor){
                Editor m = (Editor)getWorld();
                m.removeObject(this);
            } else{
                Main m = (Main)getWorld();
                m.removeObject(this);
            }
        }else if(transparencyCooldown == 0){
            getImage().setTransparency(originalTransparency);
            originalTransparency-=7;    
            transparencyCooldown = 2;
        }else{
            transparencyCooldown--;
        }
    }    
}
