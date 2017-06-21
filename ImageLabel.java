import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class ImageLabel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ImageLabel extends Actor
{
    String text;
    int x;
    int y;
    int size;
    public ImageLabel(String txt, int xLoc, int yLoc, int size1)
    {
         text = txt;
         x = xLoc;
         y =yLoc;
         size=size1;
        // Image of Text is 18 font size, White Text, Blue background 
        
    }
    
    public void act(){
        GreenfootImage  ImageText = new GreenfootImage(text,size, Color.WHITE, null); 
       
        GreenfootImage bg = getWorld().getBackground();
        bg.drawImage(ImageText, x,y); 
        
    }

}

