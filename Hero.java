import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends Entity
{
    GreenfootSound pickUpSound = new GreenfootSound("loadGun.mp3");
    GreenfootSound loadLaserSound = new GreenfootSound("highTech.mp3");
    
    private GifImage movingL = new GifImage("hero2L.gif");
    private GifImage movingR = new GifImage("hero2R.gif");
    private GreenfootImage notMovingL = new GreenfootImage("notMoving2.png");
    private GreenfootImage notMovingR = new GreenfootImage("notMoving2R.png");

    private GreenfootImage jumpL = new GreenfootImage("jumpL.png");
    private GreenfootImage jumpR = new GreenfootImage("jumpR.png");

    private boolean facingLeft = true;
    private boolean initLoc = false;
    private int jumpTimer = 10;

    private Gun displayGun = new Gun(1);
    public Pickup heldItem = null;
    private ArrayList inventory= new ArrayList();
    //1 = pistol 
    //2 = rocketlauncher
    //3 = laser
    //4 = shotgun
    //5 = portal gun

    public Hero(double xVal, double yVal){
        canPortal = 0.0;
        movable = true;
        gravity = 0.1;
        w = 26.0;
        h = 64.0;//make sure this isn't an odd number
        x = xVal;
        y = yVal;
        Vx = 0.0;
        Vy = 0.0;
        aE = 0.05;
        //setImage(notMovingR);
        health = 10;
        GreenfootImage image = new  GreenfootImage(26, 64);
        image.setColor(java.awt.Color.BLACK);
        image.fill();
        setImage(image);
    }
    //changes the direction of the hero
    public void facingLeft(boolean val){
        facingLeft = val;
    }

    public boolean returnDirection(){
        return facingLeft;
    }

    public double retrieveX(){
        return x;
    }

    public double retrieveY(){
        return y;
    }

    /**
     * Act - do whatever the Dude wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        getWorld().setPaintOrder(Notification.class, Xicon.class, FileNameHolder.class, FileNameHolderBack.class, UIItems.class, GreyBackdrop.class, BuildBar.class, MainBar.class, Bar.class, Gun.class, Hero.class, Cube.class, Platform.class, PhysicsObject.class);
        if(initLoc == false){
            setLocation(x+w/2, y+h/2);
            initLoc = true;
        }
        if(getWorld() instanceof Editor){
            Editor m = (Editor)getWorld();
            if(m.returnBuildState() == false){
                moveObject(0.0,0.0);
            }
        } else {
            moveObject(0.0,0.0);
            Main m = (Main)getWorld();
        }

        if(jumpTimer != 0){
            jumpTimer--;
        }

        if(!this.isTouching(PhysicsObject.class)) {
            setJumpImage();
        } //else {
        //notMoving();
        //}

        checkPickup();
    }   

    //move right by adding the walking speed
    public void moveRight(){
        if(Vx < 3){
            changeSpeed(0.4,0.0);
        }
        facingLeft = false;
    }

    //move left by subtracting walking speed
    public void moveLeft(){
        if(Vx > -3){
            changeSpeed(-0.4,0.0);
        }
        facingLeft = true;
    }

    public void jump(){
        ArrayList physicsObjects = (ArrayList)(getWorld().getObjects(PhysicsObject.class));
        for(int i = 0 ; i < physicsObjects.size(); i++){
            HashMap info = ((PhysicsObject)physicsObjects.get(i)).returnVars();
            if(((double) info.get("y") == y+h) && (x < (double) info.get("x") + (double) info.get("w")) && (x > (double) info.get("x") - w)){
                changeSpeed(0.0,-5.4);
            }

        }
    }

    public void animate(){
        //facing left = true

        if(facingLeft){
            setImage(movingR.getCurrentImage());
        } else {
            setImage(movingL.getCurrentImage());
        }

    }

    public void checkFalling(boolean flag){
        //if flag = true, fall, if false, don't fall

        if(flag){
            gravity = 0.1;
            //System.out.println("gavity on");
        } else {
            Vx = 0.0;
            Vy = 0.0;
            gravity = 0.0;
            //System.out.println("gravity off");
        }

    }

    public void setJumpImage(){
        if(facingLeft){
            setImage(jumpL);
        }else{
            setImage(jumpR);
        }
    }

    public void notMoving(){

        if(facingLeft){
            setImage(notMovingR);
        }else{
            setImage(notMovingL);
        }

    }
    
    public int heldItem(){
        if(heldItem == null){
            return 0;
        }
        return heldItem.type;
    }
    
    
    
    private void checkPickup(){
        double proposedX = x + Vx;
        double proposedY = y + Vy;

        ArrayList physicsObjects = (ArrayList)(getWorld().getObjects(Pickup.class));
        for(Object i : physicsObjects){
            Pickup currentObject = (Pickup)i;
            HashMap info = currentObject.returnVars();

            double objX = (double)info.get("x");
            double objW = (double)info.get("w");

            double objY = (double)info.get("y");
            double objH = (double)info.get("h");

            if( (proposedY > objY - h && proposedY < objY + objH)){
                if(proposedX >= (objX - w) && proposedX <= (objX + objW)-1){
                    
                    World w = getWorld(); 
                    if(heldItem != null){
                        heldItem.changeAbsoluteLocation(x, y-40);
                        w.addObject(heldItem, 0, 0);
                    }
                    w.removeObject(displayGun);
                    if(currentObject.getClass() == Pistol.class){
                        pickUpSound.play();
                        displayGun = new Gun(1);
                    } else if(currentObject.getClass() == RocketLauncher.class){
                        pickUpSound.play();
                        displayGun = new Gun(2);
                    } else if(currentObject.getClass() == Laser.class){
                        loadLaserSound.play();
                        displayGun = new Gun(3);
                    } else if(currentObject.getClass() == Shotgun.class){
                        pickUpSound.play();
                        displayGun = new Gun(4);
                    } else if(currentObject.getClass() == PortalGun.class){
                        loadLaserSound.play();
                        displayGun = new Gun(5);
                    } else if(currentObject.getClass() == CubeGun.class){
                        loadLaserSound.play();
                        displayGun = new Gun(6);
                    }
                    heldItem = currentObject;
                    //System.out.println(heldItem.type);
                    w.removeObject(currentObject);
                    w.addObject(displayGun,0,0);
                    //System.out.println(itemBeingHeld);
                }
            }
        }
    } 
}

