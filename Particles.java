import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Particles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Particles extends SmoothMover
{
    protected double x;
    protected double y;
    
    protected double Vx;
    protected double Vy;
    protected void moveParticle(){
        x+=Vx;
        y+=Vy;
        setLocation(x,y);
    }  
}
