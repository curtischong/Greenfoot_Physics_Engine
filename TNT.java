/**
 * Write a description of class TNT here.
 * 
 * @author Curtis Chong
 * @version (a version number or a date)
 */
import greenfoot.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;
public class TNT extends ScrollingObjects
{

    /**
     * Constructor for objects of class TNT
     */
    private boolean initLoc = false;
    private double fuse;
    private double initFuse;
    private boolean isDark = false;
    GreenfootImage image;
    private boolean alreadyExploded = false;
    
    private Editor e = (Editor)getWorld();
    private Main m = (Main)getWorld();
    public TNT(double xLoc, double yLoc, double Vxamount, double Vyamount, double Axamount, double Ayamount, double setAbsorbEnergy, double timer)
    {
        movable = true;
        canPortal = 0.0;
        gravity = 0.1;
        initFuse = timer;
        if(timer == 0.0){
            fuse = 999.0;
        }else{
            fuse = Math.pow(timer,1.4);
        }
        w = 20.0;
        h = 20.0;
        x = xLoc;
        y = yLoc;
        Vx = Vxamount;
        Vy = Vyamount;
        Ax = Axamount;
        Ay = Ayamount;
        aE = setAbsorbEnergy;
        image = new GreenfootImage(20, 20);
        image.setColor(new Color(239, 35, 35));
        image.fill();

        image.setColor(java.awt.Color.white);
        image.fillRect(2, 2, 6, 2);
        image.fillRect(4, 4, 2, 4);
        image.fillRect(7, 7, 2, 5);
        image.fillRect(9, 7, 2, 2);
        image.fillRect(11, 7, 2, 5);
        image.fillRect(12, 14, 6, 2);
        image.fillRect(14, 15, 2, 4);
        //image.fillRect(11 , 10, 2, 7);

        setImage(image);
    }

    private void toggleColor(){
        image.clear();
        image = new GreenfootImage(20, 20);
        if(isDark){
            image.setColor(new Color(239, 35, 35));
            image.fill();

            image.setColor(java.awt.Color.white);
            image.fillRect(2, 2, 6, 2);
            image.fillRect(4, 4, 2, 4);
            image.fillRect(7, 7, 2, 5);
            image.fillRect(9, 7, 2, 2);
            image.fillRect(11, 7, 2, 5);
            image.fillRect(12, 14, 6, 2);
            image.fillRect(14, 15, 2, 4);
            isDark = false;
        }else{
            image.setColor(new Color(160, 11, 11));
            image.fill();

            image.setColor(java.awt.Color.white);
            image.fillRect(2, 2, 6, 2);
            image.fillRect(4, 4, 2, 4);
            image.fillRect(7, 7, 2, 5);
            image.fillRect(9, 7, 2, 2);
            image.fillRect(11, 7, 2, 5);
            image.fillRect(12, 14, 6, 2);
            image.fillRect(14, 15, 2, 4);
            isDark = true;
        }
        setImage(image);
    }

    public double returnFuse(){
        return initFuse;
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
        if(m.returnBuildState() == false){
            moveObject(0.0,0.0);
            if(fuse == 999.0){

            }else if(fuse != 1.0){
                if(fuse <= 0.0){
                    explode();
                }else if(fuse < 30){
                    if(((int)fuse)%4==0){
                        toggleColor();
                    }
                }else if(fuse <60){
                    if(((int)fuse)%10==0){
                        toggleColor();
                    }
                }else if(fuse <110){
                    if(((int)fuse)%18==0){
                        toggleColor();
                    }
                }else if(fuse <500){
                    if(((int)fuse)%26==0){
                        toggleColor();
                    }
                }
                fuse-= 1.0;
            }
        }
    }

    public void explode(){
        if(alreadyExploded == false){
            alreadyExploded = true;
            Editor m = (Editor)getWorld();
            ArrayList physicsObjects = (ArrayList)(getWorld().getObjects(PhysicsObject.class));
            for(Object i : physicsObjects){
                if(i != this){
                    PhysicsObject currentObject = (PhysicsObject)i;
                    HashMap info = currentObject.returnVars();

                    double objX = (double)info.get("x");
                    double objW = (double)info.get("w");

                    double objY = (double)info.get("y");
                    double objH = (double)info.get("h");
                    if(objX + objW/2 < (x+w + 100) && objX + objW/2 > (x - 100) && objY + objH/2 > (y-100) && objY + objH/2 < (y+ h+100)){
                        if(currentObject instanceof TNT){
                            ((TNT)currentObject).explode();
                        }else if(currentObject instanceof Crate){
                            ((Crate)currentObject).destroy();
                        }
                        double changeX = (objX + objW/2 - x)/5;
                        double changeY = (objY + objH/2 - y)/5;
                        if(changeX < 0){
                            if(changeY < 0){
                                currentObject.changeSpeed(-(15 + changeX),-(15+changeY));
                            }else{
                                currentObject.changeSpeed(-(15+changeX),15-changeY);
                            }
                        }else{
                            if(changeY < 0){
                                currentObject.changeSpeed(15-changeX,-(15-changeY));
                            }else{
                                currentObject.changeSpeed(15-changeX,15-changeY);
                            }
                        }
                    }
                }
            }
            m.addObject(new BulletHit(0.0,0.0, x+(w/2), y+(h/2)),0,0);
            m.removeObject(this);
        }
    }
}
