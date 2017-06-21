import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gun here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gun extends Actor
{
    private GreenfootImage pistol = new GreenfootImage("pistol.png");
    private GreenfootImage rocketLauncher = new GreenfootImage("rocketLauncher.png");
    private GreenfootImage shotgun = new GreenfootImage("shotgun.png");
    private GreenfootImage laser = new GreenfootImage("laser.png");
    private GreenfootImage pistol2 = new GreenfootImage("pistol 2.png");
    private GreenfootImage rocketLauncher2 = new GreenfootImage("rocketLauncher 2.png");
    private GreenfootImage shotgun2 = new GreenfootImage("shotgun 2.png");
    private GreenfootImage laser2 = new GreenfootImage("laser 2.png");
    private GreenfootImage portal = new GreenfootImage("portal.png");
    private GreenfootImage portal2 = new GreenfootImage("portal 2.png");
    private GreenfootImage cubeGun = new GreenfootImage("cubeGun.png");
    private GreenfootImage cubeGun2 = new GreenfootImage("cubeGun 2.png");

    private GreenfootImage set1;
    private GreenfootImage set2;

    private int offsetX;
    private int offsetY;
    public Gun(int image){

        if(image == 1){
            setImage(pistol);
            set1 = pistol;
            set2 = pistol2;
            offsetX = 10;
            offsetY = -3;
        } else if(image == 2){
            setImage(rocketLauncher);
            set1 = rocketLauncher;
            set2 = rocketLauncher2;
            offsetX = 10;
            offsetY = -3;
        }else if(image == 3){
            setImage(laser);
            set1 = laser;
            set2 = laser2;
            offsetX = 10;
            offsetY = -3;
        }else if(image == 5){
            setImage(portal);
            set1 = portal;
            set2 = portal2;
            offsetX = 10;
            offsetY = -3;
        }else if(image == 6){
            setImage(cubeGun);
            set1 = cubeGun;
            set2 = cubeGun2;
            offsetX = 10;
            offsetY = -3;
        }
    }

    /**
     * Act - do whatever the Gun wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {

        if(getWorld() instanceof Editor){
            Editor m = (Editor)getWorld();
            if(m.returnHeroDirection()){
                setLocation(m.returnHeroX()-offsetX, m.returnHeroY()+offsetY);
            } else {
                setLocation(m.returnHeroX()+offsetX, m.returnHeroY()+offsetY);
            }
            if(m.returnHeroDirection()){
                setImage(set2);
                MouseInfo mouse = Greenfoot.getMouseInfo();
                if(m.returnBuildState() == false && mouse != null && mouse.getX() < m.returnHeroX() && mouse.getX() > 0 && mouse.getY() > 40 && mouse.getY() < 600){
                    turnTowards(mouse.getX(),mouse.getY());
                } else{
                    setRotation(180);
                }
            } else {
                setImage(set1);
                MouseInfo mouse = Greenfoot.getMouseInfo();
                if(m.returnBuildState() == false && mouse != null && mouse.getX() > m.returnHeroX() && mouse.getX() < 1000 && mouse.getY() > 40 && mouse.getY() < 600){
                    turnTowards(mouse.getX(),mouse.getY());
                }else{
                    setRotation(0);
                }
            }
            
        } else {
            Main m = (Main)getWorld();
            if(m.returnHeroDirection()){
                setLocation(m.returnHeroX()-offsetX, m.returnHeroY()+offsetY);
            } else {
                setLocation(m.returnHeroX()+offsetX, m.returnHeroY()+offsetY);
            }
            if(m.returnHeroDirection()){
                setImage(set2);
            } else {
                setImage(set1);
            }
        }

    } 

}

