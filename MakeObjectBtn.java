import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MakeObjectBtn here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MakeObjectBtn extends MainBar
{
    /**
     * Act - do whatever the MakeObjectBtn wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int typeOfBtn;
    public MakeObjectBtn(int holdObjType, String img){
        typeOfBtn = holdObjType;
        setImage(img);
    }
    public void act() 
    {
        if (Greenfoot.mousePressed(this))
        {
            Editor m = (Editor)getWorld();
            m.holdObj(typeOfBtn);
        }
    }    
}
