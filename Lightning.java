import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lightning here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lightning extends EnemyAttacks
{
    private GifImage bolt = new GifImage("lightning.gif");
    private GreenfootImage notMoving = new GreenfootImage("lightningstill.png");
   

    
    /**
     * Act - do whatever the Lightning wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Hero hero = getWorld().getObjects(Hero.class).get(0);
        this.setLocation(hero.getX(), hero.getY()-50);
    }
    
    public void animate (){
        setImage(bolt.getCurrentImage());
        
    }
    
    public void hit(){
        Hero hero = getWorld().getObjects(Hero.class).get(0);
        hero.health--;
        Health health = getWorld().getObjects(Health.class).get(0);
        health.updateHealth(hero.health);
    }
    
    
    public void still(){
        this.setImage(notMoving);
    }
}
