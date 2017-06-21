import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends ScrollingObjects
{
    //if true, will attack
    protected boolean attackState = false;

    protected int damage;
    protected int health;
    protected int attackCounter;
    protected int attackTimer;
     public void checkDeath(){
        if (this.health <=0){
            ScoreBoard score = getWorld().getObjects(ScoreBoard.class).get(0);
            score.points++;
            getWorld().removeObject(this);
        }
    }

    public void checkHit(){
        List objectList = this.getIntersectingObjects(RocketB.class);
        List objectList2 = this.getIntersectingObjects(PistolB.class);
        List objectList3 = this.getIntersectingObjects(LaserB.class);
        List objectList4 = this.getIntersectingObjects(Cube.class);
        
        gotHit(objectList);
        gotHit(objectList2);
        gotHit(objectList3);
        gotHit(objectList4);


    }

    public void gotHit(List objectList){
        for (int i = 0; i < objectList.size(); i++) {
            this.health--;

        }
    }
}

