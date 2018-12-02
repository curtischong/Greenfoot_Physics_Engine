import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;
import java.util.ArrayList;
/**
 * Projectiles are objects that fly across the screen
 * 
 * @author Curtis Chong
 * @version (a version number or a date)
 */
public abstract class Projectiles extends SmoothMover
{
    protected double x;
    protected double y;

    protected double h = 1.0;
    protected double w = 1.0;

    protected double Vx;
    protected double Vy;
    
    protected int recentlyPortalled = 0;
    protected Actor checkHit(){
        double proposedX = x + Vx;
        double proposedY = y + Vy;

        ArrayList physicsObjects = (ArrayList)(getWorld().getObjects(PhysicsObject.class));
        for(Object i : physicsObjects){
            PhysicsObject currentObject = (PhysicsObject)i;
            HashMap info = currentObject.returnVars();

            double objX = (double)info.get("x");
            double objW = (double)info.get("w");

            double objY = (double)info.get("y");
            double objH = (double)info.get("h");
            if( (proposedY > objY - h && proposedY < objY + objH)){
                if(proposedX >= (objX - w) && proposedX <= (objX + objW)-1){
                    return currentObject;
                }
            }
        }
        return null;
    }
    
    public void setRecentlyPortalled(){
        recentlyPortalled = 15;
    }
    
    public void changeAbsoluteLocation(double xVal, double yVal){
        x = xVal;
        y = yVal;
    }
    
    public void setVelocity(double newVx, double newVy){
        Vx = newVx;
        Vy = newVy;
    }

    public HashMap returnVars(){
        HashMap info = new HashMap();
        info.put("x", x);
        info.put("y", y);
        info.put("w", w);
        info.put("h", h);
        info.put("Vx", Vx);
        info.put("Vy", Vy);
        info.put("recentlyPortalled",recentlyPortalled);
        return info;
    }

    protected void moveProjectile(){
        if(recentlyPortalled > 0){
            recentlyPortalled--;
        }
        x += Vx;
        y += Vy;
        setLocation(x,y);
    }
}