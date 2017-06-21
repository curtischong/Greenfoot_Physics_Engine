import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Image found here http://www.iconarchive.com/tag/save-file    
 * 
 * @author Curtis Chong
 * @version (a version number or a date)
 */
public class SaveGameButton extends MainBar
{
    public SaveGameButton(){
        //setImage("save.png");
    }

    public void act() 
    {
        if (Greenfoot.mousePressed(this))
        {
            Editor m = (Editor)getWorld();
            if(m.getFileName().equals("Unamed")){
                m.saveGameUI();
            }else{
                m.saveToFile(true);
            }
        }
    }    
}
