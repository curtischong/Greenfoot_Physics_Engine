import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PickUp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Pickup extends PhysicsObject
{
    protected int type;
    public int returnType(){
        return type;
    }

}
