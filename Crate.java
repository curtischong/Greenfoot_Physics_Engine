/**
 * Write a description of class cube here.
 * 
 * @author Curtis Chong
 * @version (a version number or a date)
 */
import greenfoot.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;
import java.util.Random;
public class Crate extends ScrollingObjects
{

    /**
     * Constructor for objects of class cube
     */
    private boolean initLoc = false;
    private GreenfootImage image;
    
    private Editor e = (Editor)getWorld();
    private Main m = (Main)getWorld();
    public Crate(String canMove, double xLoc, double yLoc, double width, double height, double Vxamount, double Vyamount, double Axamount, double Ayamount, double setAbsorbEnergy)
    {
        if(canMove.equals("Can Move")){
            movable = true;
        }else{
            movable = false;
        }
        canPortal = 0.0;
        gravity = 0.1;
        w = width;
        h = height;
        x = xLoc;
        y = yLoc;
        Vx = 0.0;
        Vy = 0.0;
        Ax = Axamount;
        Ay = Ayamount;
        aE = setAbsorbEnergy;
        createSkin();
    }

    private void createSkin(){
        image = new GreenfootImage((int) w+1, (int) h+1);
        image.setColor(new Color(234, 170, 79));
        image.fill();
        if(w>h){
            image.setColor(new Color(188, 138, 67));
            image.fillRect(0 , 0, (int)w, 6);
            image.fillRect(0 , (int)h-6, (int)w, 6);
            image.fillRect(0 , 6, 6, (int)h);
            image.fillRect((int)w-6 , 6, 6, (int)h);

            image.setColor(new Color(183, 140, 80));
            image.fillRect((int)w/3 , (int)(h/2)-2, 4, 4);
            image.fillRect((int)w/2 , (int)(h/2)-2, 4, 4);
            image.fillRect(((int)w/3) *2 , (int)(h/2)-2, 4, 4);
        }else{
            image.setColor(new Color(188, 138, 67));
            image.fillRect(0 , 0, (int)w, 6);
            image.fillRect(0 , (int)h-6, (int)w, 6);
            image.fillRect(0 , 6, 6, (int)h);
            image.fillRect((int)w-6 , 6, 6, (int)h);

            image.setColor(new Color(183, 140, 80));
            image.fillRect((int)(w/2)-2, (int)h/3 , 4, 4);
            image.fillRect((int)(w/2)-2, (int)h/2 , 4, 4);
            image.fillRect((int)(w/2)-2, (int)h/3 *2 , 4, 4);
        }
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

        Editor m = (Editor)getWorld();

        if(m.returnBuildState() == false && movable == true){
            moveObject(0.0,0.0);
        }

        if(m.returnBuildState() == false){
            if(Greenfoot.mousePressed(this)){
                MouseInfo mouse = Greenfoot.getMouseInfo();
                m.clickedObject(mouse.getX(), mouse.getY());
            }
        }
    }

    public void destroy(){
        Editor m = (Editor)getWorld();
        for(int b = 0 ; b < 25; b++){
            Random rand = new Random();
            double placeX = x+ (rand.nextInt((int)w));
            double placeY = y+ (rand.nextInt((int)h));
            m.addObject(new CrateParticle(placeX,placeY),0,0);
        }
        m.removeObject(this);
    }

    public void changeDimensions(double width, double height){
        w = width;
        h = height;
        createSkin();
    }
}
