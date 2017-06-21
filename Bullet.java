import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Projectiles
{
    boolean initLoc = false;
    Turret ownTurret;
    private int hitCount = 2;
    public Bullet(String img, double initx, double inity, Turret originalTurret){
        x = initx;
        y = inity;
        ownTurret = originalTurret;
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
        setImage(img+".gif");
    }

    public void act()
    {
        if(initLoc == false){
            setLocation(x+w/2, y+h/2);
            initLoc = true;
        }   
        moveProjectile();
        Actor collidedObj = checkHit();
        if(collidedObj != null && collidedObj != this){
            hitObject((PhysicsObject)collidedObj);
        }
    }

    public void hitObject(PhysicsObject collidedObj){
        if(collidedObj instanceof Entity){
            hitCount--;
            if(hitCount == 0){
                //((Entity)collidedObj).changeHealth(-5);
                Hero hero = getWorld().getObjects(Hero.class).get(0);
                hero.health= hero.health-1;
                Health health = getWorld().getObjects(Health.class).get(0);
                health.updateHealth(hero.health);
                if(getWorld() instanceof Editor){
                    Editor m = (Editor)getWorld();
                    m.addObject(new BulletHit(0.2, 0.0, x, y), 0,0);
                    m.addObject(new BulletHit(-0.2, 0.0, x, y), 0,0);
                    m.addObject(new BulletHit(0.0, 0.2, x, y), 0,0);
                    m.addObject(new BulletHit(0.0, -0.2, x, y), 0,0);
                    m.addObject(new BulletHit(0.1, 0.1, x, y), 0,0);
                    m.addObject(new BulletHit(-0.1, 0.1, x, y), 0,0);
                    m.addObject(new BulletHit(0.1, -0.1, x, y), 0,0);
                    m.addObject(new BulletHit(-0.1, -0.1, x, y), 0,0);
                    m.removeObject(this);
                }else{
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
        }else if (collidedObj instanceof PhysicsObject && collidedObj != ownTurret){
            hitCount--;
            if(hitCount == 0){
                if(collidedObj instanceof TNT){
                    ((TNT)collidedObj).explode();
                }
                if(collidedObj instanceof Crate){
                    ((Crate)collidedObj).destroy();
                }
                if(getWorld() instanceof Editor){
                    Editor m = (Editor)getWorld();
                    m.addObject(new BulletHit(0.2, 0.0, x, y), 0,0);
                    m.addObject(new BulletHit(-0.2, 0.0, x, y), 0,0);
                    m.addObject(new BulletHit(0.0, 0.2, x, y), 0,0);
                    m.addObject(new BulletHit(0.0, -0.2, x, y), 0,0);
                    m.addObject(new BulletHit(0.1, 0.1, x, y), 0,0);
                    m.addObject(new BulletHit(-0.1, 0.1, x, y), 0,0);
                    m.addObject(new BulletHit(0.1, -0.1, x, y), 0,0);
                    m.addObject(new BulletHit(-0.1, -0.1, x, y), 0,0);
                    m.removeObject(this);
                }else{
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
