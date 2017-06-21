/**
 * Write a description of class cube here.
 * 
 * @author Curtis Chong
 * @version (a version number or a date)
 */
import greenfoot.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Cube extends ScrollingObjects
{

    /**
     * Constructor for objects of class cube
     */
    private boolean initLoc = false;
    private GreenfootImage image;
    
    private Editor e = (Editor)getWorld();
    private Main m = (Main)getWorld();
    public Cube(double xLoc, double yLoc, double Vxamount, double Vyamount, double Axamount, double Ayamount, double setAbsorbEnergy)
    {
        movable = true;
        canPortal = 0.0;
        gravity = 0.1;
        w = 10.0;
        h = 10.0;
        x = xLoc;
        y = yLoc;
        Vx = Vxamount;
        Vy = Vyamount;
        Ax = Axamount;
        Ay = Ayamount;
        aE = setAbsorbEnergy;
        GreenfootImage image = new GreenfootImage(10, 10);
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

        if (getWorld() instanceof Editor){
            Editor m = (Editor)getWorld();
            setLocation( x+w/2 - m.getOriginOffsetX(), y+h/2 - m.getOriginOffsetY());
            if(m.returnBuildState() == false && movable == true){
                moveObject(0.0,0.0);
            }

        } else {
            Main m = (Main)getWorld();
            moveObject(0.0,0.0);
        }
        //weakenForces(0.9896, 1.0);
    }

    public void shoot()
    {
        /*double angle = Math.tan((targetY-this.y)/(targetX-this.x));
        System.out.println("Targets:" + targetX + " " + targetY);
        System.out.println("Spot:" + this.x + " "+ this.y);
        this.Ay = 1.2*angle;
        this.Ax = 0.3*angle;
        double startx= this.x;
        actCounter = 0;

        if (angle>0){
        this.Vx = this.speed*Math.cos(angle*(Math.PI/180.0));
        this.Vy = this.speed*Math.sin(angle*(Math.PI/180.0));

        if (facingLeft){
        if (this.y <= targetY || this.x <= (targetX-startx)/2){
        this.x += this.Vx;
        this.y += this.Vy;

        this.Vx += this.Ax;
        this.Vy += this.Ay;

        } else if(this.y > targetY || this.x > (targetX-startx)/2){

        this.x -= this.Vx;
        this.y -= this.Vy;

        this.Vx -= this.Ax;
        this.Vy -= this.Ay;

        }

        shoot(targetX,targetY, facingLeft, ++actCounter);

        }else{
        if (this.y < targetY || this.x < (targetX-startx)/2){
        this.x += this.Vx;
        this.y += this.Vy;

        this.Vx += this.Ax;
        this.Vy += this.Ay;

        } else if (this.y > targetY || this.x > (targetX-startx)/2){
        this.x -= this.Vx;
        this.y -= this.Vy;

        this.Vx -= this.Ax;
        this.Vy -= this.Ay;

        }
        shoot(targetX,targetY, facingLeft, ++actCounter);
        }
        }
        /*double speed=5;  //you can change this
        double theta = Math.PI/50; //the initial angle
        double deltax = speed*Math.cos(theta);
        double deltay = speed*Math.sin(theta); 
        double distance = Math.pow((Math.pow(deltax,2)+Math.pow(deltay,2)),0.5);
        double dx= deltax / distance;
        double dy= deltay / distance;
        double accelerationX = 0;   //the acceleration on the x axis
        double accelerationY = 10;  //the acceleration on the y axis

        this.x += speed*dx + accelerationX /2;
        this.y += speed*dy + accelerationY /2;*/

        /* double y; 
        double x2; 
        double y2; 

        for ( x = this.x; x >=0 ; x++) { 
        y = (0.5* x * x) + (2.0 * x)+1; 
        x2 = x + 10.0; 
        y2 = (0.5 * x2 * x2) + (2.0 * x2)+1; 
        this.x = x2;
        this.y = y2;
        } */

        if(this.getY() >= 400)
            Vx = Vx*.9f;
        if(getY() <= 400){

            setLocation(getX()+(int)Math.round(Vx),getY()+(int)Math.round(Vy));

        }
        /*if(roll = true){
        setLocation(getX() + (int)Math.round(Vx),getY());

        }*/
    }

}
