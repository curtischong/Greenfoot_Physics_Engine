import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class FireballTrail here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FireballTrail extends Particles
{
    //type 0 = orange, type 1 = blue
    private int originalTransparency = 255;
    private int transparencyCooldown = 2;
    public FireballTrail(double xLoc, double yLoc){
        GreenfootImage image = new GreenfootImage(3, 3);

        Random random = new Random();
        int temp = random.nextInt(100);

        if(temp < 30){
            image.setColor(java.awt.Color.ORANGE);
        } else if (temp < 50) {
            image.setColor(java.awt.Color.RED);
        }else {
            image.setColor(java.awt.Color.YELLOW);
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
            originalTransparency-=30;    
            transparencyCooldown = 2;
        }else{
            transparencyCooldown--;
        }
    }    
}
