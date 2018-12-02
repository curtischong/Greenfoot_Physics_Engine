import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class PistolB here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PistolB extends Projectiles
{
    private Actor initActor;
    boolean initLoc = false;
    private int actCount = 0;
    public PistolB(double initx, double inity, double toX, double toY, Actor originalActor){
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
        GreenfootImage image = new GreenfootImage(5, 5);
        image.setColor(java.awt.Color.BLACK);
        image.fill();
        setImage(image);
    }

    public void act(){
        actCount++;
        if(initLoc == false){
            setLocation(x+w/2, y+h/2);
            initLoc = true;
        }  
        if(getWorld() instanceof Editor){
            Editor m = (Editor)getWorld();
            if(actCount>5){
                actCount = 0;
                m.addObject(new PistolTrail(x, y),0,0);
            }
            moveProjectile();
            Actor collidedObj = checkHit();
            if(collidedObj != null && collidedObj != initActor && collidedObj.getClass() != Gun.class  && collidedObj.getClass() != PistolB.class){
                //System.out.println(collidedObj.getClass());
                hitObject(collidedObj);
            } else if(getX() < 0 || getX() > 1000 || getY() < 40 || getY() > 600){
                m.removeObject(this);
            }
        }else{
            Main m = (Main)getWorld();
            if(actCount>5){
                actCount = 0;
                m.addObject(new PistolTrail(x, y),0,0);
            }
            moveProjectile();
            Actor collidedObj = checkHit();
            if(collidedObj != null && collidedObj != initActor && collidedObj.getClass() != Gun.class  && collidedObj.getClass() != PistolB.class){
                //System.out.println(collidedObj.getClass());
                hitObject(collidedObj);
            } else if(getX() < 0 || getX() > 1000 || getY() < 40 || getY() > 600){
                m.removeObject(this);
            }
        }
    }

    public void hitObject(Actor collidedObj){
        if(getWorld() instanceof Editor){
            Editor m = (Editor)getWorld();
            m.removeObject(this);
            if(collidedObj instanceof TNT){
                ((TNT)collidedObj).explode();
            }
            if(collidedObj instanceof Crate){
                ((Crate)collidedObj).destroy();
            }
        }else {
            Main m = (Main)getWorld();
            m.removeObject(this);
        }
    }
}
