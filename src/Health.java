import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Health here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Health extends Actor
{

    Heart[] heart;
    private int leftMost;
    private int rightMost;
    private int health;

    public Health (int hearts){
        heart = new  Heart[hearts];
        health = hearts;
        leftMost = 1000 - (16*health);
        rightMost = leftMost + 16;

        for (int i = 0; i<health; i++){
            if (i%2 == 0){
                heart[i] = new Heart(true);
                
                heart[i].setLocation (leftMost, 40);
                leftMost += 32;
            }else {
                heart[i] = new Heart(false);
                heart[i].setLocation (rightMost, 40);
                rightMost += 32;
            }
        }
    }

    /**
     * Act - do whatever the Health wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {

    }  

    public void updateHealth(int newHealth){
        for (int i = 0; i<health; i++){
            getWorld().removeObject(heart[i]);
        }

        leftMost = 950 - (16*newHealth);
        rightMost = leftMost + 16;
        //cat
        for (int i = 0; i<newHealth; i++){
            if (i%2 == 0){
                heart[i] = new Heart(true);
                this.getWorld().addObject(heart[i], leftMost, 70);
                
                leftMost += 32;
            }else {
                heart[i] = new Heart(false);
                //cat
                this.getWorld().addObject(heart[i], rightMost, 70);
                rightMost += 32;
            }
        }
    }
}
