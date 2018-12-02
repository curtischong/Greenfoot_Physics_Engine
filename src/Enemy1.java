import greenfoot.*;  
import java.util.Random;
/**
 * Write a description of class Enemy1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy1 extends Enemy
{
    private GifImage notAttackingGif = new GifImage("enemy1.gif");

    private int resCount = 0;

    private int travelX;
    private int travelY;

    private boolean firstTime = true;

    private boolean xReached = false;
    private boolean yReached = false;
    private boolean initLoc = false;

    private double speed = 0.0045;
    public Enemy1(int aC, int aT, double xloc, double yloc){
        movable = true;
        attackCounter = aC;
        attackTimer = aT;
        health = 1;

        x = xloc;
        y = yloc;
        Vx = 0.0;
        Vy = 0.0;
        aE = 0.0;
        h = 64.0;
        w = 54.0;

        setImage(notAttackingGif.getCurrentImage());
    }

    public void act(){
        resCount++;
        if(initLoc == false){
            setLocation(x+(w/2), y+(h/2));
            initLoc = true;
        }
        if (getWorld() instanceof Editor){
            Editor m = (Editor)getWorld();
            if(m.returnBuildState() == false){
                flyToLocation();
                setImage(notAttackingGif.getCurrentImage());
            }
        } else {
            flyToLocation();
            setImage(notAttackingGif.getCurrentImage());
        }
        
        checkHit();
        checkDeath();
    }

    private void flyToLocation(){
        //moveRight();

        if(firstTime){
            firstTime = false;
            getNewLocation();
        }

        //int x = getX();
        //int y = getY();

        if(!(x >= (travelX - 50) && x <= (travelX + 50))){
            if(x < (travelX - 50)){
                moveRight();
            } else if (x > (travelX + 50)) {
                moveLeft();
            }

            moveObject(Vx, Vy);
        }else{
            xReached = true;
            Vx = 0.0;
            //resetVelocity();
        }

        if (!(y >= (travelY - 50) && y <= (travelY + 50))){

            if(y < (travelY - 50)){
                moveDown();
            } else if(y > (travelY + 50)){
                moveUp();
            } 
            moveObject(Vx, Vy);
        }else{
            yReached = true;
            Vy = 0.0;
            //resetVelocity();
        }
        //System.out.println(resCount);

        if(getObjectsInRange(25, PhysicsObject.class) != null && resCount >= 70){
            resCount = 0;
            getNewLocation();
        }
        //System.out.println("X: " + x + " Y: " + y);

        /*
        //setLocation(x+(int)Math.round(Vx),y+(int)Math.round(Vy));
        System.out.println("tX: " + travelX + " tY: " + travelY);
        System.out.println("Xrange: " + (travelX - 50) + " - " + (travelX + 50));
        System.out.println("Yrange: " + (travelY - 50) + " - " + (travelY + 50));

         */
        //System.out.println(xReached + "     " + yReached);
        if (xReached == true && yReached == true){
            xReached = false;
            yReached = false;
            attack();
            getNewLocation();
        }
    } 

    private void attack(){
        if (getWorld() instanceof Editor){
            Editor m = (Editor)getWorld();
        } else {
            Main m = (Main)getWorld();
            m.addObject(new Bullet2("bulletL", x, y, this), (int)x, (int)y);
            m.addObject(new Bullet2("bulletR", x, y, this), (int)x, (int)y);
            m.addObject(new Bullet2("bulletUp", x, y, this), (int)x, (int)y);
            m.addObject(new Bullet2("bulletDown", x, y, this), (int)x, (int)y);
            m.addObject(new Bullet2("bulletUpR", x, y, this), (int)x, (int)y);
            m.addObject(new Bullet2("bulletUpL", x, y, this), (int)x, (int)y);
            m.addObject(new Bullet2("bulletBotR", x, y, this), (int)x, (int)y);
            m.addObject(new Bullet2("bulletBotL", x, y, this), (int)x, (int)y);
        }
    }

    private void getNewLocation(){
        Random r = new Random();
        travelX = r.nextInt(700) + 150;
        r = new Random();
        travelY = r.nextInt(350) + 50;
    }

    /*
    //move right by adding the walking speed
    private void moveRight(){

    if(Vx < 3){
    //moveObject(0.1,0.0);    
    applyForce(0.4, 0.0);
    }

    //applyForce(0.4, 0.0);

    //setLocation(getX() + 1, getY());
    }

    //move left by subtracting walking speed

    private void moveRight(){
    if(Vx < 3){
    moveObject(0.1,0.0);
    }

    //applyForce(-0.4, 0.0);
    //setLocation(getX() - 1, getY());
    }
    private void moveLeft(){

    if(Vx > -3){
    moveObject(-0.1,0.0);
    }

    //applyForce(-0.4, 0.0);
    //setLocation(getX() - 1, getY());
    }

    private void moveDown(){

    if(Vx < 3){
    moveObject(0.0,0.1);   
    }

    //applyForce(0.0, 0.4);
    //setLocation(getX(), getY()+1);
    }

    private void moveUp(){

    if(Vx > -3){
    moveObject(0.0,-0.1); 
    }

    //applyForce(0.0, -0.4);
    //setLocation(getX(), getY()-1);
    }*/
    //move left by subtracting walking speed
    private void moveRight(){

        if(Vx < 3){
            changeSpeed(speed,0.0);
        }

        //applyForce(-0.4, 0.0);
        //setLocation(getX() - 1, getY());
    }

    private void moveLeft(){

        if(Vx > -3){
            changeSpeed(-1*speed,0.0);
        }

        //applyForce(-0.4, 0.0);
        //setLocation(getX() - 1, getY());
    }

    private void moveDown(){

        if(Vx < 3){
            changeSpeed(0.0,speed);   
        }

        //applyForce(0.0, 0.4);
        //setLocation(getX(), getY()+1);
    }

    private void moveUp(){

        if(Vx > -3){
            changeSpeed(0.0,-1*speed); 
        }

        //applyForce(0.0, -0.4);
        //setLocation(getX(), getY()-1);
    }
}