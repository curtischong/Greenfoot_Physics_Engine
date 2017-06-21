import greenfoot.*;
import java.awt.Color;

public class Menu extends BuildBar
{
    String[] subs = new String[20];
    SubMenu[] subMenu = { };
    int subCount;
    int width;
    int height;
    String mainText = "";
    boolean active;
    String returnValue = "";
    int fontSize = 16;
    Color backgroundColor = new Color (52, 152, 219);
    Color textColor = Color.white;
    boolean suspended;
    Color frameColor = new Color (41, 128, 185);
    Color mouseOnBackgroundColor = new Color(41, 128, 185);
    Color suspendedBackgroundColor = Color.gray;
    boolean mouseOn;

    public Menu() { }

    public Menu(String main)
    {
        mainText = main;
        GreenfootImage gi = new GreenfootImage(" "+mainText+" ", fontSize, textColor, backgroundColor);
        width = gi.getWidth();
        height = gi.getHeight();
        gi.drawRect(0, 0, width-1, height-1);
        setImage(gi);
        updateImage();
    }

    public void act() 
    {   
        if(!(returnValue.equals(""))){
            this.setText(returnValue);
        }
        if (Greenfoot.mouseClicked(this))
        {
            if (!active && !suspended) activate(); else deactivate();
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

    private void updateImage()
    {
        Color bgColor = (suspended) ? suspendedBackgroundColor : (mouseOn) ? mouseOnBackgroundColor : backgroundColor;
        GreenfootImage gi = new GreenfootImage(" "+mainText+" ", fontSize, textColor, bgColor);
        gi.setColor(frameColor);
        gi.drawRect(0, 0, gi.getWidth()-1, gi.getHeight()-1);
        setImage(gi);
        if (active) for (int i=0; i<subCount; i++) subMenu[i].updateImage();
    }

    public void add(String text)
    {
        subs[subCount++] = text;
        GreenfootImage gi = new GreenfootImage(" "+text+" ", fontSize, Color.black, Color.cyan);
        width = gi.getWidth() > width ? gi.getWidth() : width;
        height = gi.getHeight() > height ? gi.getHeight() : height;
    }

    private void evaluateSize()
    {
        width = 0;
        height = 0;
        for (int i=0; i<subCount; i++)
        {
            GreenfootImage gi = new GreenfootImage(" "+subs[i]+" ", fontSize, Color.black, Color.cyan);
            width = gi.getWidth() > width ? gi.getWidth() : width;
            height = gi.getHeight() > height ? gi.getHeight() : height;
        }
    }

    private void activate()
    {
        subMenu = new SubMenu[subCount];
        for (int i=0; i<subCount; i++)
        {
            subMenu[i] = new SubMenu(this, subs[i], i);
            getWorld().addObject(subMenu[i], getX(), getY()+(fontSize+1)*(i+1));
        }
        active = true;
    }

    private void deactivate()
    {
        for (int i=0; i<subCount; i++) getWorld().removeObject(subMenu[i]);
        active = false;
    }

    public void setReturnValue(String val)
    {
        returnValue = val;
        deactivate();
    }

    public String getReturnValue()
    {
        String retVal = returnValue;
        returnValue = "";
        return retVal;
    }

    public void setBackgroundColor(Color color) { backgroundColor = color; updateImage(); }

    public Color getBackgroundColor() { return backgroundColor; }

    public void setTextColor(Color color) { textColor = color; updateImage(); }

    public Color getTextColor() { return textColor; }

    public void setMouseOnBackgroundColor(Color color) { mouseOnBackgroundColor = color; updateImage(); }

    public Color getMouseOnBackgroundColor() { return mouseOnBackgroundColor; }

    public void setFrameColor(Color color) { frameColor = color; updateImage(); }

    public Color getFrameColor() { return frameColor; }

    public void setSuspendedBackgroundColor(Color color) { suspendedBackgroundColor = color; updateImage(); }

    public Color getSuspendedBackgroundColor() { return suspendedBackgroundColor; }

    public void setSuspended(boolean suspend) { suspended = suspend; updateImage(); if (active) deactivate(); }

    public boolean getSuspended() { return suspended; }

    public void setFontSize(int fSize) { if (fSize > 11) { fontSize = fSize; evaluateSize(); updateImage(); } }

    public int getFontSize() { return fontSize; }

    public void setText(String newText) {
        mainText = newText;
        updateImage();
    }

    public String getText() { return mainText; }

    public String getSubText(int index) { return ((index >= 0 && index < subCount) ? subs[index] : null); }

    public boolean getActive() { return active; }

    public void setActive(boolean action)
    {
        if (!active && action) activate();
        if (active && !action) deactivate();
    }

    public void removeSub(int index)
    {
        if (active) return;
        if (index >= 0 && index < subCount)
        {
            for (int i=index+1; i<subCount; i++) subs[i-1] = subs[i];
            subCount--;
        }
    }

    public int getIndex(String txt)
    {
        for (int index = 0; index < subCount; index++) if (txt.equals(subs[index])) return index;
        return -1;
    }

    public void removeSub(String txt)
    {
        int index = getIndex(txt);
        if (index == -1) return;
        removeSub(index);
    }

    public void clear()
    {
        if (!active) subCount = 0;
    }

    public void sortSubs(boolean ascending)
    {
        if (subCount < 2 || active) return;
        for (int j=0; j<subCount-1; j++)
        {
            int index = j;
            for (int i=j+1; i<subCount; i++)
            {
                if (ascending) { if (subs[i].compareTo(subs[index]) < 0) index = i; }
                else { if (subs[i].compareTo(subs[index]) > 0) index = i; }
            }
            String hold = subs[index];
            subs[index] = subs[j];
            subs[j] = hold;
        }
    }
}