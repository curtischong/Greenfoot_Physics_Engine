import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class FileNameHolderBack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FileNameHolderBack extends UIItems
{
    public FileNameHolderBack(int height){
        GreenfootImage image = new GreenfootImage(200, height);
        image.setColor(Color.black);
        image.fill();
        setImage(image);
    }   
}
