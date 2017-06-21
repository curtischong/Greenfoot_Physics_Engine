import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Random;

//You'll need these import statements:
import greenfoot.core.*;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.Point;

/**
 * Write a description of class Main here.
 * 
 * @author Jennifer Ye
 * @version December 2016
 * 
 */
public class Main extends ScrollSuper
{
    Hero hero = new Hero(200.0,0.0);

    ScoreBoard score = new ScoreBoard();

    private int imageCount = 0;
    private GreenfootImage bgImage = new GreenfootImage("Level1.png");
    private GreenfootImage cursorImg = new GreenfootImage("cursor1.png");
    private String [] gunArray = new String[5];
    
    GreenfootSound rocketSound = new GreenfootSound("rocket.mp3");
    GreenfootSound shootSound = new GreenfootSound("shoot.mp3");
    //private 

    private Platform ground;
    private Platform desk;

    private Cube cube;
    private int actCounter;
    private int pickupCounter;
    private int platCounter;
    private MouseInfo mouse;

    private int holdingObj = 0;
    private Platform holdPlat;

    private int originX = 0;
    private int originY = 0;

    private int rightKey = 0;
    private boolean canMove;
    private double heightLimit;
    private double heightLimittemp;
    private boolean isJumping;
    private List <Platform> platforms;
    private int scrollSpeed = 1;
    private boolean moveHero = false;
    private int scrollCounter = 0;
    private boolean needCenter;
    private Pointer pointer = new Pointer();
    private int gunType = 0;
    private boolean isAtEdgeRight =true;
    private boolean isAtEdgeLeft = false;
    private boolean isOffScreen = false;
    private Button exit;

    private Pistol gun2 = new Pistol(450.0,100.0);
    private RocketLauncher gun = new RocketLauncher(400.0, 100.0);
    private Laser gun3 = new Laser(500.0, 100.0);

    Health health;
    Enemy1 test;
    public Main()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(); 

        ground = new Platform(0.0,0.0,540.0,6000.0,90.0,0.0);
        //desk = new Platform(0.0,600.0,496.0,480.0,100.0,0.0);
        //exit = new Button(3);
        //wallStart = new Platform (0.0,-10.0,0.0,10.0,800.0,0.0);
        //wallEnd = new Platform (0.0,1000.0,0.0,990.0,800.0,0.0);

        //test = new Enemy1(0, 0, 25, 25, 0, 0);
        //addObject(test,0,0);
        addObject(score, 0,0);
        addObject(pointer, 0,0);
        //addObject(desk, 0,0);
        // addObject(wallStart, -100, 300);
        // addObject(wallEnd, 0,0);
        //desk.getImage().setTransparency(0);
        ground.getImage().setTransparency(0);
        addObject(ground, 0,0);
        addObject(hero, 20,0);
        health = new Health(hero.health);
        addObject(health,0,0);
        drawBackgroundImage();
        mouse = Greenfoot.getMouseInfo();

        //addObject(gun2,0,0);
        //addObject(gun,0,0);
        addObject(gun3,0,0);
        //addObject(exit, 500,30);
        //addObject(breturn, 960,560);

    }

    public void act(){
        ChangeMouseImage(cursorImg);
        checkKeys();
        UI();

        scroll();

        MouseInfo mouse = Greenfoot.getMouseInfo();
        //clickedExit();
        if(actCounter>= 300){
            spawnEnemy();
            actCounter = 0;
        }

        if (pickupCounter >= 800){
            spawnPickup();
            pickupCounter = 0;
        }

        if (platCounter >= 300){
            randPlatform();
            platCounter = 0;
        }
        checkGameOver();
        platCounter++;
        actCounter++;
        pickupCounter++;

    }

    private void checkKeys(){
        //check which keys are pressed
        if (Greenfoot.isKeyDown("left")) {
            //changeing the direction
            //true = left
            //false = right
            //hero.direction = true;
            //changing the image
            //hero.changeImage(true);
            //moving

            /* if (returnHeroX()<100){
            isOffScreen = true;

            }else{
            isOffScreen = false;
            }*/

            /*if (imageCount >=-20){
            isAtEdgeLeft=true;
            }else{
            isAtEdgeLeft = false;
            }*/

            hero.facingLeft(true);

            //if (isAtEdgeLeft || returnHeroX()>700){ 
            hero.moveLeft(); 

            //}

            hero.animate();
            //hero.facingLeft = true;

        }else if (Greenfoot.isKeyDown("right")) {
            //hero.direction = false;
            //hero.changeImage(false);
            /*if (imageCount<=-5650){
            //cat
            isAtEdge=true;
            }else{
            isAtEdge=false;
            }*/

            /*if (imageCount <=-5050){
            isAtEdgeRight=true;
            }else{
            isAtEdgeRight = false;
            }*/

            hero.facingLeft(false);

            //if (isAtEdgeRight || returnHeroX()<200){ 
            hero.moveRight(); 

            //}
            hero.animate();
            //hero.facingLeft = false;

            if(!(imageCount==-5650)){
                platforms = getObjects(Platform.class);
                canMove= true;

                for (Platform p : platforms){
                    heightLimit=(600-(p.getY()-((p.h)/2)));

                    if ((p.getX()-((p.w)/2))-hero.getX()<50 && (p.getX()-((p.w)/2))-hero.getX()>-5 && 600-hero.getY() < heightLimit){
                        //&& hero.getY() < heightLimittemp
                        canMove = false;

                    }

                }

                /*if (canMove){

                imageCount -= scrollSpeed; 
                drawBackgroundImage();
                rightKey=1;
                List objectList = getObjects(ScrollingObjects.class);
                List objectList2 = getObjects(Particles.class);
                List objectList3 = getObjects(Projectiles.class);
                List objectList4 = getObjects(Pickup.class);
                moveActors(objectList4, rightKey, 4);
                moveActors(objectList3, rightKey, 3);
                moveActors(objectList2, rightKey, 2);
                moveActors(objectList, rightKey, 1);//
                //moveOrigin(rightKey);
                }*/
            }

        } else {
            hero.notMoving();
        }
        if (Greenfoot.isKeyDown("up")){
            //check if hero is on a platform then jump
            //  if (hero.onPlatform()){
            /*if (hero.getY() +50 > heightLimit){
            canMove = true;
            }*/
            hero.jump();

            // }
        }

        /*if (Greenfoot.isKeyDown("space")){
        //call the shoot method
        if (actCounter>=10){
        shoot(hero.facingLeft);
        actCounter=0;
        }
        }*/
        /*if(Greenfoot.mouseClicked(null)){
        int x = mouse.getX();
        int y = mouse.getY();
        shoot(x,y,hero.facingLeft);
        }*/
        //hero.notMoving();

        JPanel panel = WorldHandler.getInstance().getWorldCanvas();
        GreenfootImage image = new GreenfootImage(1, 1);
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(image.getAwtImage(), new Point(0, 0), "");
        panel.setCursor(cursor);
        mouse = Greenfoot.getMouseInfo();     
        if(mouse!=null){  
            mouse = Greenfoot.getMouseInfo();
            if(Greenfoot.mouseClicked(null)) 
            {

                if (mouse == null){return;}
                if(hero.heldItem() != 0){
                    ChangeMouseImage(cursorImg);
                }
                if((mouse.getButton() != 0) && !Greenfoot.mouseClicked(Bar.class)){
                    //if(mouse.getButton() == 3){
                    if(hero.heldItem() == 1){
                        shootSound.play();
                        if (mouse.getX() > hero.getX() && hero.returnDirection() == false){
                            addObject(new PistolB(hero.retrieveX()+20.0, hero.retrieveY()+25.0, mouse.getX(), mouse.getY(), hero), 0,0);
                        }else if (mouse.getX() < hero.getX() && hero.returnDirection() == true){
                            addObject(new PistolB(hero.retrieveX()-10.0, hero.retrieveY()+25.0, mouse.getX(), mouse.getY(), hero), 0,0);
                        }

                    } else if (hero.heldItem() == 2){
                        rocketSound.play();
                        if (mouse.getX() > hero.getX() && hero.returnDirection() == false){
                            addObject(new RocketB(hero.retrieveX()+20.0, hero.retrieveY()+25.0, mouse.getX(), mouse.getY(), hero), 0,0);
                        }else if (mouse.getX() < hero.getX() && hero.returnDirection() == true){
                            addObject(new RocketB(hero.retrieveX()-10.0, hero.retrieveY()+25.0, mouse.getX(), mouse.getY(), hero), 0,0);
                        }
                        //shootRocketLauncher();
                    } else if (hero.heldItem() == 3){
                        if (mouse.getX() > hero.getX() && hero.returnDirection() == false){
                            addObject(new LaserB(hero.retrieveX()+20.0, hero.retrieveY()+25.0, mouse.getX(), mouse.getY(), hero), 0,0);
                        }else if (mouse.getX() < hero.getX() && hero.returnDirection() == true){
                            addObject(new LaserB(hero.retrieveX()-10.0, hero.retrieveY()+25.0, mouse.getX(), mouse.getY(), hero), 0,0);
                        }
                        //shootLaser();
                    }else if (hero.heldItem() == 4){
                        //shootShotGun();
                    }else if (hero.heldItem() == 5){
                        //clickedObject(mouse.getX(), mouse.getY());
                        /*
                        //shoot portal
                        boolean typePortal;
                        if(mouse.getButton() == 3){
                        typePortal = true;
                        }else{
                        typePortal = false;
                        }
                        addObject(new PortalHead(typePortal,hero.retrieveX()+13.0, hero.retrieveY()+32.0, mouse.getX(), mouse.getY(), hero), 0,0);
                         */
                    }else if (hero.heldItem() == 6){
                        double rise = mouse.getY() - hero.retrieveY();
                        double run = mouse.getX() - hero.retrieveX();
                        if(run == 0){
                            run++;
                        }
                        double slope = rise/run;
                        if (mouse.getX() > hero.getX() && hero.returnDirection() == false){
                            addObject(new Cube(hero.retrieveX()+30.0, hero.retrieveY()+25.0, (run/30), (run/30)*slope, 0, 0, 1), 0, 0);
                        }else if (mouse.getX() < hero.getX() && hero.returnDirection() == true){
                            addObject(new Cube(hero.retrieveX()-30.0, hero.retrieveY()+25.0, (run/30), (run/30)*slope, 0, 0, 1), 0, 0);
                        }

                    }
                }
            }

        }

    }

    private void checkGameOver(){

        if (hero.health <=0){
            GameOver over = new GameOver(score.points);
            Greenfoot.setWorld(over);
        } 
        if (hero.getX()<=-100){
            GameOver over = new GameOver(score.points);
            Greenfoot.setWorld(over);
        }
    }

    /*private void clickedExit(){

    if(Greenfoot.mouseClicked(null))
    {
    MouseInfo mouse = Greenfoot.getMouseInfo();
    System.out.println("Mouse X:" + mouse.getX());
    System.out.println("Mouse Y:" + mouse.getY());

    }
    if (Greenfoot.mouseClicked(exit)){
    addObject(returnMenu, 500,300);
    }

    }*/

    private void moveActors(List objectList,  int classType){
        //check which keys are pressed

        for (int i = 0; i < objectList.size(); i++) {
            //((Actor)objectList.get(i)).setLocation(((Actor)objectList.get(i)).getX() - scrollSpeed, ((Actor)objectList.get(i)).getY());
            if ((!(objectList.get(i).getClass() == Hero.class)) && (!(objectList.get(i)== ground))){
                ((Actor)objectList.get(i)).setLocation(((Actor)objectList.get(i)).getX() - scrollSpeed, ((Actor)objectList.get(i)).getY());
                //System.out.println("X Location: " + ((Actor)objectList.get(i)).returnX() + " Y Location: " + ((Actor)objectList.get(i)).returnY());
                if (classType==2){

                    ((Particles)objectList.get(i)).x = ((Particles)objectList.get(i)).x - scrollSpeed;  
                }else if (classType==1){

                    ((ScrollingObjects)objectList.get(i)).x = ((ScrollingObjects)objectList.get(i)).x - scrollSpeed;

                }else if (classType==3){
                    ((Projectiles)objectList.get(i)).x = ((Projectiles)objectList.get(i)).x - scrollSpeed;  
                } else if (classType==4){
                    ((Pickup)objectList.get(i)).x = ((Pickup)objectList.get(i)).x - scrollSpeed; 
                }

                //System.out.println("X Location: " + ((ScrollingObjects)objectList.get(i)).returnX() + " Y Location: " + ((ScrollingObjects)objectList.get(i)).returnY());
            }

            if ((objectList.get(i).getClass() == Hero.class)){
                ((Actor)objectList.get(i)).setLocation(((Actor)objectList.get(i)).getX() + scrollSpeed, ((Actor)objectList.get(i)).getY());
                ((Hero)objectList.get(i)).x = ((Hero)objectList.get(i)).x - scrollSpeed;
            }

        }

        if (Greenfoot.isKeyDown("space")){
        }
    }

    private void scroll(){
        if(!(imageCount==0)){
            platforms = getObjects(Platform.class);

            canMove = true;

            for (Platform p : platforms){
                heightLimit=(600-(p.getY()-((p.h)/2)));

                if (hero.getX()-(p.getX()+((p.w)/2))<50 && hero.getX()-(p.getX()+((p.w)/2))>-5 && 600-hero.getY() < heightLimit ) {
                    //&& hero.getY() < heightLimittemp
                    //canMove = false;

                }
            }

        }
        if (canMove){
            imageCount -= scrollSpeed; 
            drawBackgroundImage();
            rightKey=2;
            List objectList = getObjects(ScrollingObjects.class);
            List objectList2 = getObjects(Particles.class);
            List objectList3 = getObjects(Projectiles.class);
            List objectList4 = getObjects(Pickup.class);
            moveActors(objectList4, 4);
            moveActors(objectList3, 3);
            moveActors(objectList2, 2);

            moveActors(objectList, 1);
            //moveOrigin(rightKey);
        }
    }

    /*private void moveOrigin(int key){
    if (key==1) {
    originX -= scrollSpeed;
    }else if (key==2) {
    originX += scrollSpeed;
    }
    key=0;
    }*/

    public void drawBackgroundImage() {
        if (imageCount < -bgImage.getWidth()) {
            imageCount += bgImage.getWidth();
        }
        int temp = imageCount;
        getBackground().drawImage(bgImage, temp, 0);
        getBackground().drawImage(bgImage, temp + bgImage.getWidth(), 0);
    }

    public int getOriginOffsetX(){
        return originX * (-1);
    }

    public int getOriginOffsetY(){
        return originY * (-1);
    }

    public void UI(){
       
    

        showText("Health", 950, 20);

        health.updateHealth(hero.health);

        showText("Score:", 900, 80);
        showText(Integer.toString(score.returnPoints()), 950, 80);
    }

    public int returnHeroX(){
        return hero.getX();
    }

    public int returnHeroY(){
        return hero.getY();
    }

    public boolean returnHeroDirection(){
        return hero.returnDirection();
    }

    public void spawnEnemy(){

        Random random = new Random();
        int rand = random.nextInt(5);

        if (rand==0){
            Short enemy = new Short(1200,510);
            addObject(enemy, 1200,510);
        }else if (rand ==1){
            Range enemy = new Range(1200,510);
            addObject(enemy, 1200,510);
        }else if (rand ==2){
            Enemy1 enemy = new Enemy1(25, 25, 0, 0);
            addObject(enemy, 0,0);
        } else if (rand ==3){
            Mage enemy = new Mage();
            addObject(enemy, 1200,510);

        } else if (rand ==4){
            Teleporter enemy = new Teleporter();
            addObject(enemy, 1200,510);

        }

    }

    public void spawnPickup(){
        Random random = new Random();
        int rand = random.nextInt(5);

        if (rand==0){
            CubeGun guns = new CubeGun(1200,300);
            addObject(guns, 100,200);
        }else if (rand ==1){
            Pistol guns = new Pistol(1200,300);
            addObject(guns, 100,200);
        } else if (rand ==2){
            RocketLauncher guns = new RocketLauncher(1200,300);
            addObject(guns, 100,200);
        } else if (rand ==4){
            Laser guns = new Laser(1200,300);
            addObject(guns,100,200);
        }
    }

    public void randPlatform(){
        Random random = new Random();
        int rY = random.nextInt(200)+301;
        int rX = 1301;
        int rW = 200;
        int rH = 30;

        Platform rand = new Platform(0.0,rX,rY,rW,rH,0.0);
        addObject(rand, rX,rY);

    }
    
    public void ChangeMouseImage(GreenfootImage image){
        JPanel Panel = WorldHandler.getInstance().getWorldCanvas();
        Cursor Cursor;
        Toolkit Tk = Toolkit.getDefaultToolkit();
        Point CursorPoint= new Point(11,11);
        Cursor = Tk.createCustomCursor(image.getAwtImage(),CursorPoint,"Somehow");
        Panel.setCursor(Cursor);
    }
}
