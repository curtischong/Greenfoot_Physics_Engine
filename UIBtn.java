import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class UIBtn here.
 * 
 * @author Curtis Chong
 * @version (a version number or a date)
 */
public class UIBtn extends BuildBar
{
    private String text;
    private boolean mouseOn = false;
    public UIBtn(String word){
        text = word;
        GreenfootImage image = new GreenfootImage(150, 20);
        image.setColor(java.awt.Color.WHITE);
        image.drawString(text,75,10);
        setImage(image);
    }

    public void act() 
    {
        if (Greenfoot.mousePressed(this))
        {
            Editor m = (Editor)getWorld();
            m.clickedBtn(text);
        }
        if (Greenfoot.mouseMoved(null))
        {
            if (Greenfoot.mouseMoved(this) && !mouseOn)
            {
                mouseOn = true;
                updateImage();
            }
            if (!Greenfoot.mouseMoved(this) && mouseOn)
            {
                mouseOn = false;
                updateImage();
            }
        }
    }

    private void updateImage(){
        GreenfootImage image = new GreenfootImage(150, 20);
        if(mouseOn == false){
            image.setColor(Color.WHITE);
        }else{
            image.setColor(new Color (192, 57, 43));
        }
        image.drawString(text,75,10);
        setImage(image);
    }
}
