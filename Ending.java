import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ending here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ending extends PhysicsObject
{
    private boolean initLoc = false;
    private Editor e = (Editor)getWorld();
    private Main m = (Main)getWorld();
    public Ending(String canMove, double xLoc, double yLoc, double Vxamount, double Vyamount, double Axamount, double Ayamount, double setAbsorbEnergy){
        if(canMove.equals("Can Move")){
            movable = true;
        }else{
            movable = false;
        }
        canPortal = 0.0;
        gravity = 0.1;
        w = 30.0;
        h = 90.0;
        x = xLoc;
        y = yLoc;
        Vx = Vxamount;
        Vy = Vyamount;
        Ax = Axamount;
        Ay = Ayamount;
        aE = setAbsorbEnergy;
        GreenfootImage image = new GreenfootImage(30, 90);
        image.setColor(java.awt.Color.BLACK);
        image.fill();
        setImage(image);
    }
    public void act() 
    {
        if(initLoc == false){
            if (getWorld() instanceof Editor){
                e = (Editor)getWorld();
                setLocation( x+w/2 - e.getOriginOffsetX(), y+h/2 - e.getOriginOffsetY());
            } else {
                m = (Main)getWorld();
                setLocation( x+w/2 - m.getOriginOffsetX(), y+h/2 - m.getOriginOffsetY());
            }
            
            initLoc = true;
        }
    }    
}
