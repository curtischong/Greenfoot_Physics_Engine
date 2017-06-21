import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class FireballB here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FireballB extends Projectiles
{
    private Actor initActor;
    boolean initLoc = false;
    private int actCount = 0;
    public FireballB(double initx, double inity, double toX, double toY, Actor originalActor){
        double rise = toY - inity;
        double run = toX - initx;
        if(run == 0){
            run++;
        }
        double slope = rise/run;
        x = initx;
        y = inity;
        Vx = (run/30);
        Vy = Vx*slope;
        w = 8.0;
        h = 8.0;
        initActor = originalActor;
        GreenfootImage image = new GreenfootImage(10, 10);
        image.setColor(java.awt.Color.RED);
        image.fill();
        setImage(image);
    }

    public void act(){
        actCount++;
        if(initLoc == false){
            setLocation(x+w/2, y+h/2);
            initLoc = true;
        }   
        Editor m = (Editor)getWorld();

        m.addObject(new FireballTrail(x, y),0,0);
        m.addObject(new FireballTrail(x + 4, y),0,0);
        m.addObject(new FireballTrail(x - 4, y),0,0);
        m.addObject(new FireballTrail(x, y + 4),0,0);
        m.addObject(new FireballTrail(x, y - 4),0,0);
        m.addObject(new FireballTrail(x + 4, y + 4),0,0);
        m.addObject(new FireballTrail(x - 4, y - 4),0,0);
        m.addObject(new FireballTrail(x + 4, y - 4),0,0);
        m.addObject(new FireballTrail(x - 4, y + 4),0,0);
        Random random = new Random();
        int tempX = random.nextInt(100);
        int tempY = random.nextInt(100);
        m.addObject(new FireballTrail(x * tempX, y * tempY),0,0);
        tempX = random.nextInt(100);
        tempY = random.nextInt(100);
        m.addObject(new FireballTrail(x * tempX, y * tempY),0,0);
        tempX = random.nextInt(100);
        tempY = random.nextInt(100);
        m.addObject(new FireballTrail(x * tempX, y * tempY),0,0);
        tempX = random.nextInt(100);
        tempY = random.nextInt(100);
        m.addObject(new FireballTrail(x * tempX, y * tempY),0,0);
        
        moveProjectile();
        Actor collidedObj = checkHit();
        if(collidedObj != null && collidedObj != initActor && collidedObj.getClass() != Gun.class  && collidedObj.getClass() != FireballB.class){
            //System.out.println(collidedObj.getClass());
            hitObject(collidedObj);
        } else if(getX() < 0 || getX() > 1000 || getY() < 40 || getY() > 600){
            m.removeObject(this);
        }
    }

    public void hitObject(Actor collidedObj){
        Editor m = (Editor)getWorld();
        //m.changePortal(x, y, isOrangePortal, collidedObj);
        m.removeObject(this);
    }
}
