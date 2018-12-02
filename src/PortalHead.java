import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class PortalHead here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PortalHead extends Projectiles
{
    private Actor initActor;
    private boolean isOrangePortal;
    boolean initLoc = false;
    double rise;
    double run;
    public PortalHead(boolean canOrange, double initx, double inity, double toX, double toY, Actor originalActor){
        rise = toY - inity;
        run = toX - initx;
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
        isOrangePortal = canOrange;
        initActor = originalActor;
        GreenfootImage image = new GreenfootImage(5, 5);
        image.setColor(java.awt.Color.RED);
        image.fill();
        setImage(image);
    }

    public void act(){
        if(initLoc == false){
            setLocation(x+w/2, y+h/2);
            initLoc = true;
        }   
        if(getWorld() instanceof Editor){
            Editor m = (Editor)getWorld();
            if(m.returnBuildState() == false){
                m.addObject(new PortalTrail(x, y, isOrangePortal),0,0);
                moveProjectile();
                Actor collidedObj = checkHit();
                if((collidedObj != null) && (collidedObj != initActor)){
                    hitObject(collidedObj);
                }
            }
        }else{
            Main m = (Main)getWorld();

            m.addObject(new PortalTrail(x, y, isOrangePortal),0,0);
            moveProjectile();
            Actor collidedObj = checkHit();
            if((collidedObj != null) && (collidedObj != initActor)){
                hitObject(collidedObj);
            }

        }
    }

    public void hitObject(Actor collidedObj){
        Editor m = (Editor)getWorld();
        m.changePortal(x, y, rise, run, isOrangePortal, collidedObj);
        m.removeObject(this);
    }
}