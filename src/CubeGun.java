import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RocketLauncher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CubeGun extends Pickup
{
    private boolean initLoc = false;
    public CubeGun(double x,double y){
        canPortal = 0.0;
        //type = 1;
        gravity = 0.01;
        movable = true;
        this.x = x;
        this.y = y;
        w = 22.0;
        h = 20.0;
        aE = 0.1;

        type = 6;
    }

    public void act(){
        if(initLoc == false){
            setLocation(x+w/2, y+h/2);
            initLoc = true;
        }
        if(getWorld() instanceof Editor){
            Editor m = (Editor)getWorld();

            if(m.returnBuildState() == false){
                moveObject(0.0,0.0);
            }
        } else {
            moveObject(0.0,0.0);
        }
    }   
}
