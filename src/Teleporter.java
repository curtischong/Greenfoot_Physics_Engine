import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Teleporter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Teleporter extends Enemy
{
    boolean canXleft;
    boolean canXright;
    int heroX;
    int targetXright;
    int targetXleft;
    int targetY;
    int actCounter = 0;

    List <Platform> platforms;
    private GifImage tele = new GifImage("teleport.gif");
    private GifImage still = new GifImage("enemy0.gif");

    public Teleporter(){
        health = 1;

    }

    /**
     * Act - do whatever the Teleporter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        this.setImage(still.getCurrentImage());
        findTarget();
        checkPlatforms();
        teleport();
        this.setImage(still.getCurrentImage());
        if (actCounter==50){
            attack();
            actCounter=0;
        }
        actCounter++;

        checkHit();
        checkDeath();
    } 

    private void findTarget(){
        Hero hero = getWorld().getObjects(Hero.class).get(0);
        heroX = hero.getX();
        targetXright = heroX+100;
        targetXleft = heroX-100;
    }

    private void teleport(){

        Hero hero = getWorld().getObjects(Hero.class).get(0);
        if (canXleft){
            setImage(tele.getCurrentImage());
            setLocation(targetXleft, hero.getY());
            this.x = targetXleft;
            this.y = hero.getY();

        } else if (canXright){
            setImage(tele.getCurrentImage());
            setLocation(targetXright, hero.getY());
            this.x = targetXright;
            this.y = hero.getY();

        }

    }

    private void checkPlatforms (){
        platforms = getWorld().getObjects(Platform.class);
        canXright = true;
        canXleft = true;
        Hero hero = getWorld().getObjects(Hero.class).get(0);
        heroX = hero.getX();
        for (Platform p : platforms){
            if (heroX-(p.getX()+((p.w)/2))<100 && heroX-(p.getX()+((p.w)/2))>-100) {
                canXleft = false;

            } else if ((p.getX()-((p.w)/2))-heroX<100 && (p.getX()-((p.w)/2))-heroX>-100){
                canXright = false;

            }
        }
    }

    private void attack(){
        if (getWorld() instanceof Editor){
            Editor m = (Editor)getWorld();
        } else {
            Main m = (Main)getWorld();
            m.addObject(new Bullet2("bulletL", x, y, this), (int)x, (int)y);
            m.addObject(new Bullet2("bulletR", x, y, this), (int)x, (int)y);
            

        }
    }
}

