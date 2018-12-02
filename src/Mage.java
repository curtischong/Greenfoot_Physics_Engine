import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mage extends Enemy
{
    int attackCount = 25;
    int attackLimit = 200;

    Lightning bolt = new Lightning();

    public Mage(){
        health = 1;
    }

    /**
     * Act - do whatever the Mage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Hero hero = getWorld().getObjects(Hero.class).get(0);
        getWorld().addObject(bolt, hero.getX(), hero.getY() - 100);
        if (attackCount == attackLimit){
            attackCount = 0;
            bolt.animate();
            bolt.hit();

        } else {
            bolt.still();
            attackCount++;
        }

        checkHit();
        if (this.getX()<-10 || this.getX() >1010 || health<=0){
            getWorld().removeObject(bolt);
        }

        
        checkDeath();
    }    
}

