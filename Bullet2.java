import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet2 extends Projectiles
{
    boolean initLoc = false;
    private int hitCount = 2;
    private Actor ownActor; 
    public Bullet2(String img, double initx, double inity, Actor a){
        x = initx;
        y = inity;
        ownActor = a;
        if(img.equals("bulletL")){
            Vx = -3.0;
            Vy = 0.0;
        }else if(img.equals("bulletR")){
            Vx = 3.0;
            Vy = 0.0;
        }else if(img.equals("bulletUp")){
            Vy = -3.0;
            Vx = 0.0;
        }else if(img.equals("bulletDown")){
            Vy = 3.0;
            Vx = 0.0;
        }else if(img.equals("bulletUpR")){
            Vx = 2.0;
            Vy = -2.0;
        }else if(img.equals("bulletUpL")){
            Vx = -2.0;
            Vy = -2.0;
        }else if(img.equals("bulletBotR")){
            Vx = 2.0;
            Vy = 2.0;
        }else if(img.equals("bulletBotL")){
            Vx = -2.0;
            Vy = 2.0;
        }
        w = 6.0;
        h = 6.0;
        //setImage(img+".gif");
    }

    public void act()
    {
        if(initLoc == false){
            setLocation(x+w/2, y+h/2);
            initLoc = true;
        }   
        x += Vx;
        y += Vy;
        setLocation(x,y);
        Actor collidedObj = checkHit();
        if(collidedObj != null && collidedObj != this){
            hitObject((PhysicsObject)collidedObj);
        }
    }

    public void hitObject(PhysicsObject collidedObj){
        if(collidedObj instanceof Entity){
            Hero hero = getWorld().getObjects(Hero.class).get(0);
            hero.health= hero.health-1;
            Health health = getWorld().getObjects(Health.class).get(0);
            health.updateHealth(hero.health);
            hitCount--;
            if(hitCount == 0){
                //((Entity)collidedObj).changeHealth(-5);
                if (getWorld() instanceof Editor){
                    Editor m = (Editor)getWorld();
                } else {
                    Main m = (Main)getWorld();
                    m.addObject(new BulletHit(0.2, 0.0, x, y), 0,0);
                    m.addObject(new BulletHit(-0.2, 0.0, x, y), 0,0);
                    m.addObject(new BulletHit(0.0, 0.2, x, y), 0,0);
                    m.addObject(new BulletHit(0.0, -0.2, x, y), 0,0);
                    m.addObject(new BulletHit(0.1, 0.1, x, y), 0,0);
                    m.addObject(new BulletHit(-0.1, 0.1, x, y), 0,0);
                    m.addObject(new BulletHit(0.1, -0.1, x, y), 0,0);
                    m.addObject(new BulletHit(-0.1, -0.1, x, y), 0,0);
                    m.removeObject(this);
                }
            }
        }else if (collidedObj instanceof PhysicsObject && collidedObj != ownActor){
            hitCount--;
            if(hitCount == 0){
                if (getWorld() instanceof Editor){
                    Editor m = (Editor)getWorld();
                } else {
                    Main m = (Main)getWorld();
                    m.addObject(new BulletHit(0.2, 0.0, x, y), 0,0);
                    m.addObject(new BulletHit(-0.2, 0.0, x, y), 0,0);
                    m.addObject(new BulletHit(0.0, 0.2, x, y), 0,0);
                    m.addObject(new BulletHit(0.0, -0.2, x, y), 0,0);
                    m.addObject(new BulletHit(0.1, 0.1, x, y), 0,0);
                    m.addObject(new BulletHit(-0.1, 0.1, x, y), 0,0);
                    m.addObject(new BulletHit(0.1, -0.1, x, y), 0,0);
                    m.addObject(new BulletHit(-0.1, -0.1, x, y), 0,0);
                    m.removeObject(this);
                }
            }
        }
    }   
}  

