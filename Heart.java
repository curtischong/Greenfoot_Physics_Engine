import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Heart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Heart extends Actor
{
    private GreenfootImage left = new GreenfootImage("leftHeart.png");
    private GreenfootImage right = new GreenfootImage("rightHeart.png");
    /**
     * Act - do whatever the Heart wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Heart(boolean faceLeft)
    {
        if (faceLeft){
            this.setImage(left);
        }else{
            this.setImage(right);
        }
        
    }    
}
