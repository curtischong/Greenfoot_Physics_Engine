import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TrashCan here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TrashCan extends Actor
{
    
    public TrashCan(){
        hide();
    }
    
    public void hide(){
        getImage().setTransparency(0);
    }
    public void show(){
        getImage().setTransparency(250);
    }
}

