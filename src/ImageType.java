import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ImageType here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ImageType extends SmoothMover
{
    public ImageType(String img){
        setImage(new GreenfootImage(img));
    }

    public void changeImage(String newImage){
        setImage(new GreenfootImage(newImage));
    }
}
