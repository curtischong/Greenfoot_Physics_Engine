import greenfoot.*;
import java.awt.Color;

public class SubMenu extends Menu
{
    int value = -1;
    Menu parent;
    boolean mouseOn;
    String text = "";

    public SubMenu(Menu creator, String txt, int val)
    {
        parent = creator;
        text = txt;
        value = val;
        updateImage();
    }

    public void act() 
    {        
        if (Greenfoot.mouseClicked(this)){
            parent.setReturnValue(text);
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
        setLocation(parent.getX(), parent.getY()+(parent.height+1)*(value+1));
    }

    public void updateImage()
    {
        GreenfootImage gi = new GreenfootImage(parent.width, parent.height);
        Color bgColor = mouseOn ? parent.mouseOnBackgroundColor : parent.backgroundColor;
        gi.setColor(bgColor);
        gi.fill();
        GreenfootImage img = new GreenfootImage(text, parent.fontSize, parent.textColor, bgColor);
        gi.drawImage(img, (parent.width-img.getWidth())/2, (parent.height-img.getHeight())/2);
        gi.setColor(parent.frameColor);
        gi.drawRect(0, 0, parent.width-1, parent.height-1);
        setImage(gi);
    }
}