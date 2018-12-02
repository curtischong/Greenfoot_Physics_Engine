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
public class Platform extends ScrollingObjects
{

    /**
     * Constructor for objects of class cube
     */
    private boolean initLoc = false;
    private GreenfootImage image;private Editor e = (Editor)getWorld();
    private Main m = (Main)getWorld();

    public Platform(double portalable,double xLoc, double yLoc, double width, double height, double setAbsorbEnergy)
    {
        movable = false;

        canPortal = portalable;
        w = width;
        h = height;
        x = xLoc;
        y = yLoc;
        Vx = 0.0;
        Vy = 0.0;
        aE = setAbsorbEnergy;
        image = new GreenfootImage((int) w+1, (int) h+1);
        changeColour((int)portalable);
    }

    private void changeColour(int newColour){
        image.clear();
        image = new GreenfootImage((int) w+1, (int) h+1);
        if(newColour == 1){
            image.setColor(new Color(138, 173, 141));
            image.fill();
            if(w>h){
                int count = (int)(w/25);
                if((count*5) + ((count-1)*25) > w){
                    count--;
                }
                int extra = (int)(w-((count*5) + ((count-1)*25)))/2;
                for(int i = 0 ; i < count ; i++){
                    image.setColor(new Color(107, 147, 110));
                    image.fillRect((i*5) + (i*25) +extra , (int)(h/4), 5, 5);
                }
            }else{
                int count = (int)(h/25);
                if((count*5) + ((count-1)*25) > h){
                    count--;
                }
                int extra = (int)(h-((count*5) + ((count-1)*25)))/2;
                for(int i = 0 ; i < count ; i++){
                    image.setColor(new Color(107, 147, 110));
                    image.fillRect((int)(w/4)-3, (i*5) + (i*25) +extra, 5, 5);
                    image.fillRect((int)(w-(w/4))-3, (i*5) + (i*25) +extra, 5, 5);
                }
            }
            setImage(image);
        }else{
            image.setColor(new Color(229, 210, 172));
            image.fill();
            if(w>h){
                int count = (int)(w/25);
                if((count*5) + ((count-1)*25) > w){
                    count--;
                }
                int extra = (int)(w-((count*5) + ((count-1)*25)))/2;
                for(int i = 0 ; i < count ; i++){
                    image.setColor(new Color(204, 182, 140));
                    image.fillRect((i*5) + (i*25) +extra , (int)(h/4), 5, 5);
                }
            }else{
                int count = (int)(h/25);
                if((count*5) + ((count-1)*25) > h){
                    count--;
                }
                int extra = (int)(h-((count*5) + ((count-1)*25)))/2;
                for(int i = 0 ; i < count ; i++){
                    image.setColor(new Color(204, 182, 140));
                    image.fillRect((int)(w/4)-3, (i*5) + (i*25) +extra, 5, 5);
                    image.fillRect((int)(w-(w/4))-3, (i*5) + (i*25) +extra, 5, 5);
                }
            }
            setImage(image);
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
        if(Greenfoot.mousePressed(this)){
            if (getWorld() instanceof Editor){
                Editor m = (Editor)getWorld();

                if(m.returnBuildState() == false){
                    MouseInfo mouse = Greenfoot.getMouseInfo();
                    m.clickedObject(mouse.getX(), mouse.getY());
                }
            }
        }
    }


    public void changeDimensions(double width, double height, String canPortal){
        w = width;
        h = height;
        if(canPortal.equals("Can't Portal")){
            changeColour(0);
        }else{
            changeColour(1);    
        }
    }
}
