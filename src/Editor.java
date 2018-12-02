import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Stack;
import java.io.*;
import java.util.Scanner;
import java.util.*;

//You'll need these import statements:
import greenfoot.core.*;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.Point;

/**
 * This world is where users will go and create Levels to play
 */
public class Editor extends World
{

    private Health health;
    private PausePlay pausePlay = new PausePlay();
    private Reset reset = new Reset();
    // 0 = pause, 1 = play
    private boolean buildState = true;
    private boolean pauseState = true;
    // 0 = nothing, 1 = cube, 2 = platform
    private int holdingObj = 0;

    GreenfootSound rocketSound = new GreenfootSound("rocket.mp3");
    GreenfootSound shootSound = new GreenfootSound("shoot.mp3");

    private GreenfootImage pointerImg = new GreenfootImage("mouse.png");
    private GreenfootImage cursorImg = new GreenfootImage("cursor1.png");

    private Cube top;
    private Cube bottom;
    private Cube derp;
    private Cube twig;
    private Platform plat;

    private Bar Bar = new Bar();
    private MakeObjectBtn makeCubeBtn = new MakeObjectBtn(1,"makeCubeBtn.png");
    private MakeObjectBtn makePlatBtn = new MakeObjectBtn(2, "slice16@2x.png");
    private MakeObjectBtn makeTurretBtn = new MakeObjectBtn(3, "bee.png");
    private MakeObjectBtn makeCrateBtn = new MakeObjectBtn(4, "bee.png");
    private MakeObjectBtn makeTNTBtn = new MakeObjectBtn(5, "tnt.gif");
    private MakeObjectBtn makeEndingBtn = new MakeObjectBtn(11, "bee.png");
    private MakeObjectBtn makePGunBtn = new MakeObjectBtn(6, "portalGun.gif");
    private MakeObjectBtn makePistolBtn = new MakeObjectBtn(7, "pistol.png");
    private MakeObjectBtn makeRLBtn = new MakeObjectBtn(8, "rocketLauncher.png");
    private MakeObjectBtn makeCGBtn = new MakeObjectBtn(9, "cubeGun.png");
    private MakeObjectBtn makeLBtn = new MakeObjectBtn(10, "laser.png");
    private Slider Sw = new Slider(190, 20, "Width");
    private Slider Sh = new Slider(190, 20, "Height");
    private Slider SVx = new Slider(190, 20, "SpeedX");
    private Slider SVy = new Slider(190, 20, "SpeedY");
    private Slider SAx = new Slider(190, 20, "AccelX");
    private Slider SAy = new Slider(190, 20, "AccelY");
    private Slider fireRate = new Slider(190, 20, "Fire Rate");
    private Slider fuseTimer = new Slider(190, 20, "Fuse");

    private SaveGameButton saveGameIcon = new SaveGameButton();
    private LoadGameButton loadGameIcon = new LoadGameButton();
    private FileName fileName = new FileName();
    private GreyBackdrop greyBackdrop = new GreyBackdrop();
    private StringInputBox stringInputBox;
    private FileNameHolderBack fileNameHolderBack;
    private String nameOfFile = "Unamed";
    private boolean isWritingFileNameVar = false;

    private Xicon xIcon = new Xicon();

    private Menu directionMenu = new Menu("Facing Left");
    private Menu directionMenu2 = new Menu("Down");
    private Menu typeTurret = new Menu("Shoots Bullets");
    private Menu movement = new Menu("Can't Move");
    private Menu canPortal = new Menu("Can't Portal");

    private UIBtn movementItems = new UIBtn("Movement");
    private UIBtn back = new UIBtn("Back");
    private Actor holdActor;
    private Stack lastObj = new Stack();
    private int undoCooldown = 10;
    private boolean firstPlay = true;

    private boolean dragState = false;

    private Hero hero = new Hero(300.0,200.0);

    private Actor currentActor = hero;
    private String lastDir1 = "";
    private String lastDir2 = "";

    //new variables
    private double dimensionx = 1000;
    private double dimensiony = 600;

    ArrayList<String> fileContents = new ArrayList<String>();
    private String[] saved = new String[3];

    private int clickActCount = 0;
    private TrashCan trashCan = new TrashCan();    

    private OrangePortal orangePortal = new OrangePortal(2,200.0,400.0);
    private BluePortal bluePortal = new BluePortal(2,550.0,250.0);

    private int originX = 0;
    private int originY= 0;

    private int platW = 50;
    private int platH = 10;
    private int crateW = 20;
    private int crateH = 19;
    //private Pointer pointer = new Pointer();
    //private RocketLauncher gun = new RocketLauncher(450.0,250.0);
    /**
     * The main constructor for the Editor Class
     */
    public Editor(){    
        // Create a new world with 1000x600 cells with a cell size of 1x1 pixels.
        super(950, 600, 1, false);
        setPaintOrder(Heart.class, BuildBar.class, MainBar.class, Bar.class, Hero.class, Gun.class, PhysicsObject.class);
        //setPaintOrder(Cube.class, BuildBar.class, MainBar.class);
        plat = new Platform(0.0,300.0, 300.0, 400.0, 30.0, 0.0);
        //addObject(orangePortal,0,0);
        //addObject(bluePortal,0,0);
        health = new Health(hero.health);
        addObject(health,0,0);
        health.updateHealth(hero.health);

        Sw.setValue(50);
        Sh.setValue(5);
        SVx.setValue(50);
        SVy.setValue(50);
        SAx.setValue(50);
        SAy.setValue(100);
        fireRate.setValue(0);

        addObject(Bar,500,20);
        addObject(makeCubeBtn,100, 20);
        addObject(makePlatBtn,150, 20);
        addObject(pausePlay,30, 20);
        addObject(reset,60, 20);
        addObject(plat,0, 0);
        addObject(hero, 0,0);
        addObject(trashCan, 970, 570);
        addObject(makeTurretBtn,200, 20);
        addObject(makeCrateBtn,250,20);
        addObject(makeTNTBtn,300,20);
        addObject(makeEndingBtn, 350, 20);
        addObject(makeLBtn, 550, 20);
        addObject(makePGunBtn,600,20);
        addObject(makePistolBtn, 650, 20);
        addObject(makeRLBtn, 700, 20);
        addObject(makeCGBtn, 750, 20);

        //addObject(pointer,0,0);

        directionMenu.add("Facing Left");
        directionMenu.add("Facing Right");
        directionMenu.add("Facing Up");
        directionMenu.add("Facing Down");
        directionMenu.add("Facing Down Left");
        directionMenu.add("Facing Down Right");
        directionMenu.add("Facing Up Left");
        directionMenu.add("Facing Up Right");

        directionMenu2.add("Up");
        directionMenu2.add("Down");

        typeTurret.add("Shoots Bullets");

        movement.add("Can Move");
        movement.add("Can't Move");

        canPortal.add("Can Portal");
        canPortal.add("Can't Portal");
        //addObject(directionMenu,400,20);
        //Then just do this:

        addObject(saveGameIcon, 800,20);
        addObject(loadGameIcon,840,20);
    }

    /**
     * This method gets called when a user clicks on an object in the top bar and will generate a copy of that object for them to hold
     */
    public void holdObj(int itemId){
        holdingObj = itemId;
        for (Actor i : getObjects(MainBar.class)){
            removeObject(i);
        }
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(holdingObj == 2){
            double platCanPortal;
            if(canPortal.getText().equals("Can't Portal")){
                platCanPortal = 0.0;
            }else{
                platCanPortal = 1.0;
            }
            holdActor = new Platform(platCanPortal,(double) mouse.getX(), (double) mouse.getY(), 100.0, 10.0, 2.0);
            addObject(holdActor,0,0);
            Sh.setValue(platH);
            Sw.setValue(platW);
            addObject(Sh, 143, 18);
            addObject(Sw, 388, 18);
            addObject(canPortal,550,20);
        }else if(holdingObj == 1){
            holdActor = new Platform(0.0, (double) mouse.getX(), (double) mouse.getY(), 10.0, 10.0, 2.0);
            addObject(holdActor,0,0);
            addObject(movementItems, 16,25);
        }else if(holdingObj == 3){
            holdActor = new ImageType("turret-down-left.png");
            addObject(holdActor,0,0);
            changeDirectionMenu();
            addObject(movementItems, 16,25);
            addObject(movement, 150, 20);
            addObject(directionMenu,286,20);
            addObject(directionMenu2, 406,20);
            addObject(typeTurret, 510, 20);
            addObject(fireRate, 726, 20);
        }else if(holdingObj == 4){
            holdActor = new Crate("Can't Move",(double) mouse.getX(), (double) mouse.getY(), 60, 59, 0.0,0.0,0.0,0.0,2.0);
            addObject(holdActor,0,0);
            Sh.setValue(crateH);
            Sw.setValue(crateW);
            addObject(Sh, 350, 18);
            addObject(Sw, 600, 18);
            addObject(movement, 150, 20);
            addObject(movementItems, 16,25);
        }else if(holdingObj == 5){
            holdActor = new TNT((double) mouse.getX(), (double) mouse.getY(), 15.0, 15.0, 0.0,0.0,0.0,0);
            addObject(holdActor,0,0);
            addObject(fuseTimer,230,18);
            addObject(movementItems, 16, 25);
        }else if(holdingObj == 6){
            holdActor = new PortalGun((double) mouse.getX(), (double) mouse.getY());
            addObject(holdActor,0,0);
        }else if(holdingObj == 7){
            holdActor = new Pistol((double) mouse.getX(), (double) mouse.getY());
            addObject(holdActor,0,0);
        }else if(holdingObj == 8){
            holdActor = new RocketLauncher((double) mouse.getX(), (double) mouse.getY());
            addObject(holdActor,0,0);
        }else if(holdingObj == 9){
            holdActor = new CubeGun((double) mouse.getX(), (double) mouse.getY());
            addObject(holdActor,0,0);
        }else if(holdingObj == 10){
            holdActor = new Laser((double) mouse.getX(), (double) mouse.getY());
            addObject(holdActor,0,0);
        }else if(holdingObj == 11){
            holdActor = new Ending("Can't Move",(double) mouse.getX(), (double) mouse.getY(),0.0,0.0,0.0,0.0,2.0);
            addObject(holdActor,0,0);
            addObject(movement, 150, 20);
            addObject(movementItems, 16,25);
        }
    }

    /**
     * when the user right clicks on a ui word, this method will place all of the ui elements.
     */
    public void clickedBtn(String word){
        if(word.equals("Movement")){
            removeObject(movementItems);
            if(holdingObj == 2){
                removeObject(canPortal);
            }else if(holdingObj == 3){
                removeObject(movement);
                removeObject(fireRate);
                removeObject(directionMenu);
                removeObject(directionMenu2);
                removeObject(typeTurret);
            }else if(holdingObj == 4){
                removeObject(movement);
                removeObject(Sh);
                removeObject(Sw);
            }else if(holdingObj == 5){
                removeObject(fuseTimer);
            }else if(holdingObj == 11){
                removeObject(movement);
            }
            addObject(SVx, 178, 18);
            addObject(SVy, 414, 18);
            addObject(SAx, 650, 18);
            addObject(SAy, 878, 18);
            addObject(back,16,25);
        }
        if(word.equals("Back")){
            addObject(movementItems,16,25);
            removeObject(SVx);
            removeObject(SVy);
            removeObject(SAx);
            removeObject(SAy);
            removeObject(back);
            if(holdingObj == 2){
                platH = Sh.getValue();
                platW = Sw.getValue();
                addObject(canPortal,550,20);
            }else if(holdingObj == 3){
                addObject(movementItems, 16,25);
                addObject(movement, 150, 20);
                addObject(directionMenu,286,20);
                addObject(directionMenu2, 406,20);
                addObject(typeTurret, 510, 20);
                addObject(fireRate, 726, 20);
            }else if(holdingObj == 4){
                crateH = Sh.getValue();
                crateW = Sw.getValue();
                addObject(Sh, 350, 18);
                addObject(Sw, 600, 18);
                addObject(movement, 150, 20);
                addObject(movementItems, 16,25);
            }else if(holdingObj == 5){
                addObject(fuseTimer,230,18);
            }else if(holdingObj == 11){
                addObject(movement, 150, 20);
            }
        }
    }

    /**
     * This method is used by the portal gun to determine where to shoot a portal.
     */
    public void clickedObject(double toX, double toY){
        //shoot portal
        MouseInfo mouse = Greenfoot.getMouseInfo();
        //right click
        boolean typePortal;
        if(mouse.getButton() == 3){
            typePortal = true;
        }else{
            typePortal = false;
        }
        addObject(new PortalHead(typePortal,hero.retrieveX()+13.0, hero.retrieveY()+32.0, toX, toY, hero), 0,0);
    }

    public void act(){
        setPaintOrder(Pointer.class, BuildBar.class, MainBar.class, Bar.class, Gun.class, Hero.class, PhysicsObject.class);
        if(returnBuildState() == false){
            checkKeys();
        }else {
            List objectList = getObjects(SmoothMover.class);
            moveOrigin(objectList);
        }
        checkMouse();
    }

    /**
     * these two portals are used to communicate the location of the other portal so we know where to teleport the object
     */
    public String getOrangePortalData(){
        return orangePortal.getData();
    }

    public String getBluePortalData(){
        return bluePortal.getData();
    }

    /**
     * This method calculates where to place a portal that has intercepted a portalable platform. (AKA the orientation and location of the portal.)
     */
    public void changePortal(double xLoc, double yLoc, double rise, double run, boolean isOrangePortal, Actor collidedObj){
        HashMap holdData = ((PhysicsObject)collidedObj).returnVars();
        if((collidedObj instanceof Platform) && ((double)holdData.get("canPortal") == 1.0)){
            double objY = (double)holdData.get("y");

            double objH = (double)holdData.get("h");

            double objX = (double)holdData.get("x");
            double objW = (double)holdData.get("w");

            if(isOrangePortal){
                //projectile is moving down
                if(rise > 0){
                    //check which side the bullet is closest to
                    //can use the bullet's rise value to narrow it down to three sides. then use it's run value to narrow it down to two sides.
                    //then I see whether the obj x is closer to the collieded x or the obj y is closer to the collided y (use an if else statement not else if)
                    //projectile is moving right
                    if(run > 0){
                        double targetY = objY;
                        double targetX = objX;
                        double distX = Math.abs(targetX - xLoc);
                        double distY = Math.abs(targetY - yLoc);
                        //projectile is hitting the left side
                        if(distX < distY){
                            if(objH > 100.0){
                                removeObject(orangePortal);
                                if(yLoc - 50 < objY){
                                    orangePortal = new OrangePortal(0, objX-2, objY);
                                }else if(yLoc+51 > objY + objH){
                                    orangePortal = new OrangePortal(0, objX-2, objY+objH - 100);
                                }else{
                                    orangePortal = new OrangePortal(0, objX-2, yLoc-50);
                                }
                                addObject(orangePortal,0,0);
                            }
                            //projectile is hitting the top side
                        }else{
                            if(objW > 100.0){
                                removeObject(orangePortal);
                                if(xLoc - 50 < objX){
                                    orangePortal = new OrangePortal(2, objX, objY-2);
                                }else if(xLoc+51 > objX + objW){
                                    orangePortal = new OrangePortal(2, objX+objW-100, objY-2);
                                }else{
                                    orangePortal = new OrangePortal(2, xLoc - 50, objY-2);
                                }
                                addObject(orangePortal,0,0);
                            }
                        }
                        //projectile is moving left
                    }else{
                        double targetY = objY;
                        double targetX = objX + objW;
                        double distX = Math.abs(targetX - xLoc);
                        double distY = Math.abs(targetY - yLoc);

                        //projectile is hitting the right side
                        if(distX < distY){
                            if(objH > 100.0){
                                removeObject(orangePortal);
                                if(yLoc - 50 < objY){
                                    orangePortal = new OrangePortal(1, objX+objW+1, objY);
                                }else if(yLoc+51 > objY + objH){
                                    orangePortal = new OrangePortal(1, objX+objW+1, objY+objH - 100);
                                }else{
                                    orangePortal = new OrangePortal(1, objX+objW+1, yLoc-50);
                                }
                                addObject(orangePortal,0,0);
                            }
                            //projectile is hitting the top side
                        }else{
                            if(objW > 100.0){
                                removeObject(orangePortal);
                                if(xLoc - 50 < objX){
                                    orangePortal = new OrangePortal(2, objX, objY-2);
                                }else if(xLoc+51 > objX + objW){
                                    orangePortal = new OrangePortal(2, objX+objW-100, objY-2);
                                }else{
                                    orangePortal = new OrangePortal(2, xLoc - 50, objY-2);
                                }
                                addObject(orangePortal,0,0);
                            }
                        }
                    }
                    //projectile is moving up
                }else{
                    //projectile is moving right
                    if(run > 0){
                        double targetY = objY + objH;
                        double targetX = objX;
                        double distX = Math.abs(targetX - xLoc);
                        double distY = Math.abs(targetY - yLoc);
                        //projectile is hitting the left side
                        if(distX < distY){
                            if(objH > 100.0){
                                removeObject(orangePortal);
                                if(yLoc - 50 < objY){
                                    orangePortal = new OrangePortal(0, objX-2, objY);
                                }else if(yLoc+51 > objY + objH){
                                    orangePortal = new OrangePortal(0, objX-2, objY+objH - 100);
                                }else{
                                    orangePortal = new OrangePortal(0, objX-2, yLoc-50);
                                }
                                addObject(orangePortal,0,0);
                            }
                            //projectile is hitting the bottom side
                        }else{
                            if(objW > 100.0){
                                removeObject(orangePortal);
                                if(xLoc - 50 < objX){
                                    orangePortal = new OrangePortal(3, objX, objY+objH+1);
                                }else if(xLoc+51 > objX + objW){
                                    orangePortal = new OrangePortal(3, objX+objW-100, objY+objH+1);
                                }else{
                                    orangePortal = new OrangePortal(3, xLoc - 50, objY+objH+1);
                                }
                                addObject(orangePortal,0,0);
                            }
                        }
                        //projectile is moving left
                    }else{
                        double targetY = objY+ objH;
                        double targetX = objX + objW;
                        double distX = Math.abs(targetX - xLoc);
                        double distY = Math.abs(targetY - yLoc);

                        //projectile is hitting the right side
                        if(distX < distY){
                            if(objH > 100.0){
                                removeObject(orangePortal);
                                if(yLoc - 50 < objY){
                                    orangePortal = new OrangePortal(1, objX+objW+1, objY);
                                }else if(yLoc+51 > objY + objH){
                                    orangePortal = new OrangePortal(1, objX+objW+1, objY+objH - 100);
                                }else{
                                    orangePortal = new OrangePortal(1, objX+objW+1, yLoc-50);
                                }
                                addObject(orangePortal,0,0);
                            }
                            //projectile is hitting the bottom side
                        }else{
                            if(objW > 100.0){
                                removeObject(orangePortal);
                                if(xLoc - 50 < objX){
                                    orangePortal = new OrangePortal(3, objX, objY+objH+1);
                                }else if(xLoc+51 > objX + objW){
                                    orangePortal = new OrangePortal(3, objX+objW-100, objY+objH+1);
                                }else{
                                    orangePortal = new OrangePortal(3, xLoc - 50, objY+objH+1);
                                }
                                addObject(orangePortal,0,0);
                            }
                        }
                    }
                }
            }else{
                //projectile is moving down
                if(rise > 0){
                    //check which side the bullet is closest to
                    //can use the bullet's rise value to narrow it down to three sides. then use it's run value to narrow it down to two sides.
                    //then I see whether the obj x is closer to the collieded x or the obj y is closer to the collided y (use an if else statement not else if)
                    //projectile is moving right
                    if(run > 0){
                        double targetY = objY;
                        double targetX = objX;
                        double distX = Math.abs(targetX - xLoc);
                        double distY = Math.abs(targetY - yLoc);
                        //projectile is hitting the left side
                        if(distX < distY){
                            if(objH > 100.0){
                                removeObject(bluePortal);
                                if(yLoc - 50 < objY){
                                    bluePortal = new BluePortal(0, objX-2, objY);
                                }else if(yLoc+51 > objY + objH){
                                    bluePortal = new BluePortal(0, objX-2, objY+objH - 100);
                                }else{
                                    bluePortal = new BluePortal(0, objX-2, yLoc-50);
                                }
                                addObject(bluePortal,0,0);
                            }
                            //projectile is hitting the top side
                        }else{
                            if(objW > 100.0){
                                removeObject(bluePortal);
                                if(xLoc - 50 < objX){
                                    bluePortal = new BluePortal(2, objX, objY-2);
                                }else if(xLoc+51 > objX + objW){
                                    bluePortal = new BluePortal(2, objX+objW-100, objY-2);
                                }else{
                                    bluePortal = new BluePortal(2, xLoc - 50, objY-2);
                                }
                                addObject(bluePortal,0,0);
                            }
                        }
                        //projectile is moving left
                    }else{
                        double targetY = objY;
                        double targetX = objX + objW;
                        double distX = Math.abs(targetX - xLoc);
                        double distY = Math.abs(targetY - yLoc);

                        //projectile is hitting the right side
                        if(distX < distY){
                            if(objH > 100.0){
                                removeObject(bluePortal);
                                if(yLoc - 50 < objY){
                                    bluePortal = new BluePortal(1, objX+objW+1, objY);
                                }else if(yLoc+51 > objY + objH){
                                    bluePortal = new BluePortal(1, objX+objW+1, objY+objH - 100);
                                }else{
                                    bluePortal = new BluePortal(1, objX+objW+1, yLoc-50);
                                }
                                addObject(bluePortal,0,0);
                            }
                            //projectile is hitting the top side
                        }else{
                            if(objW > 100.0){
                                removeObject(bluePortal);
                                if(xLoc - 50 < objX){
                                    bluePortal = new BluePortal(2, objX, objY-2);
                                }else if(xLoc+51 > objX + objW){
                                    bluePortal = new BluePortal(2, objX+objW-100, objY-2);
                                }else{
                                    bluePortal = new BluePortal(2, xLoc - 50, objY-2);
                                }
                                addObject(bluePortal,0,0);
                            }
                        }
                    }
                    //projectile is moving up
                }else{
                    //projectile is moving right
                    if(run > 0){
                        double targetY = objY + objH;
                        double targetX = objX;
                        double distX = Math.abs(targetX - xLoc);
                        double distY = Math.abs(targetY - yLoc);
                        //projectile is hitting the left side
                        if(distX < distY){
                            if(objH > 100.0){
                                removeObject(bluePortal);
                                if(yLoc - 50 < objY){
                                    bluePortal = new BluePortal(0, objX-2, objY);
                                }else if(yLoc+51 > objY + objH){
                                    bluePortal = new BluePortal(0, objX-2, objY+objH - 100);
                                }else{
                                    bluePortal = new BluePortal(0, objX-2, yLoc-50);
                                }
                                addObject(bluePortal,0,0);
                            }
                            //projectile is hitting the bottom side
                        }else{
                            if(objW > 100.0){
                                removeObject(bluePortal);
                                if(xLoc - 50 < objX){
                                    bluePortal = new BluePortal(3, objX, objY+objH+1);
                                }else if(xLoc+51 > objX + objW){
                                    bluePortal = new BluePortal(3, objX+objW-100, objY+objH+1);
                                }else{
                                    bluePortal = new BluePortal(3, xLoc - 50, objY+objH+1);
                                }
                                addObject(bluePortal,0,0);
                            }
                        }
                        //projectile is moving left
                    }else{
                        double targetY = objY+ objH;
                        double targetX = objX + objW;
                        double distX = Math.abs(targetX - xLoc);
                        double distY = Math.abs(targetY - yLoc);

                        //projectile is hitting the right side
                        if(distX < distY){
                            if(objH > 100.0){
                                removeObject(bluePortal);
                                if(yLoc - 50 < objY){
                                    bluePortal = new BluePortal(1, objX+objW+1, objY);
                                }else if(yLoc+51 > objY + objH){
                                    bluePortal = new BluePortal(1, objX+objW+1, objY+objH - 100);
                                }else{
                                    bluePortal = new BluePortal(1, objX+objW+1, yLoc-50);
                                }
                                addObject(bluePortal,0,0);
                            }
                            //projectile is hitting the bottom side
                        }else{
                            if(objW > 100.0){
                                removeObject(bluePortal);
                                if(xLoc - 50 < objX){
                                    bluePortal = new BluePortal(3, objX, objY+objH+1);
                                }else if(xLoc+51 > objX + objW){
                                    bluePortal = new BluePortal(3, objX+objW-100, objY+objH+1);
                                }else{
                                    bluePortal = new BluePortal(3, xLoc - 50, objY+objH+1);
                                }
                                addObject(bluePortal,0,0);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * This changes the cursor image.
     */
    public void ChangeMouseImage(GreenfootImage image){
        JPanel Panel = WorldHandler.getInstance().getWorldCanvas();
        Cursor Cursor;
        Toolkit Tk = Toolkit.getDefaultToolkit();
        Point CursorPoint= new Point(11,11);
        Cursor = Tk.createCustomCursor(image.getAwtImage(),CursorPoint,"Somehow");
        Panel.setCursor(Cursor);
    }

    /**
     * This method will listen to the mouse's actions and will call other functions to perform the mouse cactions.
     * It takes into account the location of the mouse, the current editing state, and the objects that the mouse is holding.
     */
    private void checkMouse(){
        if(pauseState == false){
            if(Greenfoot.mousePressed(this)){
                MouseInfo mouse = Greenfoot.getMouseInfo();

                if (mouse == null){return;}
                if(hero.heldItem() != 0){
                    ChangeMouseImage(cursorImg);
                }
                if((mouse.getButton() != 0) && !Greenfoot.mouseClicked(Bar.class)){
                    //if(mouse.getButton() == 3){
                    if(hero.heldItem() == 1){
                        /*if(shootSound.isPlaying()){
                            shootSound.stop();
                        }else{
                            shootSound.play();
                        }*/
                        if (mouse.getX() > hero.getX() && hero.returnDirection() == false){
                            addObject(new PistolB(hero.retrieveX()+20.0, hero.retrieveY()+25.0, mouse.getX(), mouse.getY(), hero), 0,0);
                        }else if (mouse.getX() < hero.getX() && hero.returnDirection() == true){
                            addObject(new PistolB(hero.retrieveX()-10.0, hero.retrieveY()+25.0, mouse.getX(), mouse.getY(), hero), 0,0);
                        }

                    } else if (hero.heldItem() == 2){
                        /*if(shootSound.isPlaying()){
                            rocketSound.stop();
                        }else{
                            rocketSound.play();
                        }*/
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
                        clickedObject(mouse.getX(), mouse.getY());
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
        }else if(buildState == true){
            ChangeMouseImage(pointerImg);
            if(holdingObj!= 0){
                //cube
                if(holdingObj == 1){
                    MouseInfo mouse = Greenfoot.getMouseInfo();
                    if(mouse != null){
                        if(mouse.getY() > 45){
                            holdActor.setLocation(mouse.getX(),mouse.getY());
                        }else{
                            holdActor.setLocation(mouse.getX(), 45);
                        }
                    }
                    //plat
                }else if(holdingObj == 2){
                    MouseInfo mouse = Greenfoot.getMouseInfo();
                    HashMap holdData = ((PhysicsObject)holdActor).returnVars();
                    double holdWidth = Math.pow(Sw.getValue(),1.3);
                    double holdHeight = Math.pow(Sh.getValue(),1.3);
                    if(mouse != null){
                        if(mouse.getY() - (int)((double)Math.pow(Sh.getValue(),1.3))/2 > 40){
                            holdActor.setLocation(mouse.getX(),mouse.getY());
                        }else{
                            holdActor.setLocation(mouse.getX(),40 + (int)((double)Math.pow(Sh.getValue(),1.3))/2);
                        }
                    }
                    if(holdWidth > 0 && holdHeight > 0){
                        ((Platform)holdActor).changeDimensions(holdWidth, holdHeight, canPortal.getText());
                    }
                    //turret
                }else if(holdingObj == 3){
                    changeDirectionMenu();
                    MouseInfo mouse = Greenfoot.getMouseInfo();
                    if(mouse != null){
                        if(mouse.getY() > 55){
                            holdActor.setLocation(mouse.getX(),mouse.getY());
                        }else{
                            holdActor.setLocation(mouse.getX(), 55);
                        }
                    }
                    //crate
                }else if(holdingObj == 4){
                    MouseInfo mouse = Greenfoot.getMouseInfo();
                    HashMap holdData = ((PhysicsObject)holdActor).returnVars();
                    double holdWidth = Math.pow(Sw.getValue(),1.3);
                    double holdHeight = Math.pow(Sh.getValue(),1.3);
                    if(mouse != null){
                        if(mouse.getY() - (int)((double)Math.pow(Sh.getValue(),1.3))/2 > 40){
                            holdActor.setLocation(mouse.getX(),mouse.getY());
                        }else{
                            holdActor.setLocation(mouse.getX(),40 + (int)((double)Math.pow(Sh.getValue(),1.3))/2);
                        }
                    }
                    if(holdWidth > 0 && holdHeight > 0){
                        ((Crate)holdActor).changeDimensions(holdWidth, holdHeight);
                    }
                    //TNT
                }else if(holdingObj == 5){
                    MouseInfo mouse = Greenfoot.getMouseInfo();
                    if(mouse != null){
                        if(mouse.getY() > 50){
                            holdActor.setLocation(mouse.getX(),mouse.getY());
                        }else{
                            holdActor.setLocation(mouse.getX(), 50);
                        }
                    }
                    //Pgun
                }else if(holdingObj == 6){
                    MouseInfo mouse = Greenfoot.getMouseInfo();
                    if(mouse != null){
                        if(mouse.getY() > 50){
                            holdActor.setLocation(mouse.getX(),mouse.getY());
                        }else{
                            holdActor.setLocation(mouse.getX(), 50);
                        }
                    }
                    //pistol
                }else if(holdingObj == 7){
                    MouseInfo mouse = Greenfoot.getMouseInfo();
                    if(mouse != null){
                        if(mouse.getY() > 50){
                            holdActor.setLocation(mouse.getX(),mouse.getY());
                        }else{
                            holdActor.setLocation(mouse.getX(), 50);
                        }
                    }
                    //Rocket Launcher
                }else if(holdingObj == 8){
                    MouseInfo mouse = Greenfoot.getMouseInfo();
                    if(mouse != null){
                        if(mouse.getY() > 50){
                            holdActor.setLocation(mouse.getX(),mouse.getY());
                        }else{
                            holdActor.setLocation(mouse.getX(), 50);
                        }
                    }
                    //Cube gun
                }else if(holdingObj == 9){
                    MouseInfo mouse = Greenfoot.getMouseInfo();
                    if(mouse != null){
                        if(mouse.getY() > 50){
                            holdActor.setLocation(mouse.getX(),mouse.getY());
                        }else{
                            holdActor.setLocation(mouse.getX(), 50);
                        }
                    }
                    //laser
                }else if(holdingObj == 10){
                    MouseInfo mouse = Greenfoot.getMouseInfo();
                    if(mouse != null){
                        if(mouse.getY() > 50){
                            holdActor.setLocation(mouse.getX(),mouse.getY());
                        }else{
                            holdActor.setLocation(mouse.getX(), 50);
                        }
                    }
                    //ending
                }else if(holdingObj == 11){
                    MouseInfo mouse = Greenfoot.getMouseInfo();
                    if(mouse != null){
                        if(mouse.getY() > 83){
                            holdActor.setLocation(mouse.getX(),mouse.getY());
                        }else{
                            holdActor.setLocation(mouse.getX(), 83);
                        }
                    }
                }
            }else{
                movePlacedObjects();
            }
            if(Greenfoot.mousePressed(holdActor)){
                MouseInfo mouse = Greenfoot.getMouseInfo();
                int mouseX=mouse.getX();
                int mouseY=mouse.getY();
                //user right clicked
                if(mouse.getButton() == 3){
                    //reset the build bar
                    if(holdingObj != 0){
                        removeObject(holdActor);
                    }
                    holdingObj = 0;
                    addObject(makeCubeBtn,100, 20);
                    addObject(makePlatBtn,150, 20);
                    addObject(makeTurretBtn, 200, 20);
                    addObject(makeCrateBtn,250,20);
                    addObject(makeTNTBtn,300,20);
                    addObject(makeEndingBtn, 350, 20);
                    addObject(makeLBtn, 550, 20);
                    addObject(makePGunBtn,600,20);
                    addObject(makePistolBtn, 650, 20);
                    addObject(makeRLBtn, 700, 20);
                    addObject(makeCGBtn, 750, 20);
                    addObject(pausePlay,30, 20);
                    addObject(reset,60, 20);
                    addObject(saveGameIcon, 800,20);
                    addObject(loadGameIcon,840,20);
                    addObject(fileName, 860, 25);

                    for (Actor i : getObjects(BuildBar.class)){
                        removeObject(i);
                    }
                }else{

                    if(holdingObj!= 0){
                        //check sliders
                        double Ow = Math.pow(Sw.getValue(),1.3);
                        double Oh = Math.pow(Sh.getValue(),1.3);
                        double OVx = (SVx.getValue()-50)/5;
                        double OVy = -(SVy.getValue()-50)/5;
                        double OAx = (SAx.getValue()-50)/5;
                        double OAy = ((SAy.getValue()-100.0)/1000.0);
                        int fuseCount = fuseTimer.getValue();
                        String canMove = movement.getText();

                        int amountMovedX = originX * (-1);
                        int amountMovedY = originY * (-1);

                        if(holdingObj == 1){
                            PhysicsObject addedCube = new Cube((double)mouseX-5 + amountMovedX, (double)mouseY-5 + amountMovedY, OVx, OVy, OAx, OAy, 0.3);
                            addObject(addedCube,0,0);
                            lastObj.push(addedCube);
                        }else if(holdingObj == 2){
                            if(Ow != 0.0 && Oh != 0.0){
                                double platCanPortal;
                                if(canPortal.getText().equals("Can't Portal")){
                                    platCanPortal = 0.0;
                                }else{
                                    platCanPortal = 1.0;
                                }
                                PhysicsObject addedPlat = new Platform(platCanPortal,(double)mouseX - ((int)((double)Math.pow(Sw.getValue(),1.3))/2) + amountMovedX, (double)mouseY - ((int)((double)Math.pow(Sh.getValue(),1.3))/2) + amountMovedY, Ow, Oh, 0.0);
                                addObject(addedPlat,0,0);
                                lastObj.push(addedPlat);
                            }
                        }else if(holdingObj == 3){
                            PhysicsObject addedTurret = new Turret(canMove, (double)mouseX-16 + amountMovedX, (double)mouseY-9 + amountMovedY, OVx, OVy, OAx, OAy, 0.3, directionMenu.getText(), directionMenu2.getText(),fireRate.getValue(), typeTurret.getText());
                            addObject(addedTurret,0,0);
                            lastObj.push(addedTurret);
                        }else if(holdingObj == 4){
                            if(Ow != 0.0 && Oh != 0.0){
                                PhysicsObject addedCrate = new Crate(canMove,(double)mouseX - ((int)((double)Math.pow(Sw.getValue(),1.3))/2) + amountMovedX, (double)mouseY - ((int)((double)Math.pow(Sh.getValue(),1.3))/2) + amountMovedY, Ow, Oh, OVx, OVy, OAx, OAy, 0.3);
                                addObject(addedCrate,0,0);
                                lastObj.push(addedCrate);
                            }
                        }else if(holdingObj == 5){
                            PhysicsObject addedTNT = new TNT((double)mouseX-5 + amountMovedX, (double)mouseY-5 + amountMovedY, OVx, OVy, OAx, OAy, 0.3, fuseCount);
                            addObject(addedTNT,0,0);
                            lastObj.push(addedTNT);
                        }
                        else if(holdingObj == 6){
                            PhysicsObject addedPgun = new PortalGun((double)mouseX-5 + amountMovedX, (double)mouseY-5 + amountMovedY);
                            addObject(addedPgun,0,0);
                            lastObj.push(addedPgun);
                        }
                        else if(holdingObj == 7){
                            PhysicsObject addedPistol = new Pistol((double)mouseX-5 + amountMovedX, (double)mouseY-5 + amountMovedY);
                            addObject(addedPistol,0,0);
                            lastObj.push(addedPistol);
                        }
                        else if(holdingObj == 8){
                            PhysicsObject addedRL = new RocketLauncher((double)mouseX-5 + amountMovedX, (double)mouseY-5 + amountMovedY);
                            addObject(addedRL,0,0);
                            lastObj.push(addedRL);
                        }
                        else if(holdingObj == 9){
                            PhysicsObject addedCG = new CubeGun((double)mouseX-5 + amountMovedX, (double)mouseY-5 + amountMovedY);
                            addObject(addedCG,0,0);
                            lastObj.push(addedCG);
                        }
                        else if(holdingObj == 10){
                            PhysicsObject addedLaser = new Laser((double)mouseX-5 + amountMovedX, (double)mouseY-5 + amountMovedY);
                            addObject(addedLaser,0,0);
                            lastObj.push(addedLaser);
                        }else if(holdingObj == 11){
                            PhysicsObject addedEnding = new Ending(canMove,(double)mouseX - ((int)((double)Math.pow(Sw.getValue(),1.3))/2) + amountMovedX, (double)mouseY - ((int)((double)Math.pow(Sh.getValue(),1.3))/2) + amountMovedY, OVx, OVy, OAx, OAy, 0.3);
                            addObject(addedEnding,0,0);
                            lastObj.push(addedEnding);
                        }
                    }
                }
            }
        }
    }

    /**
     * This method is used to modify the direction of the Turrets.
     */
    private void changeDirectionMenu(){
        String dir1 = directionMenu.getText();
        String dir2 = directionMenu2.getText();
        if(!(lastDir1.equals(dir1) && lastDir2.equals(dir2))){
            lastDir1 = dir1;
            lastDir2 = dir2;
            //if(Greenfoot.mousePressed(holdActor)){
            if(dir2.equals("Up")){
                if(!(dir1.equals("Facing Up") || dir1.equals("Facing Up Left") || dir1.equals("Facing Up Right"))){
                    if(dir1.equals("Facing Down")){
                        ((ImageType)holdActor).changeImage("turret-up-down.png");
                    }else if(dir1.equals("Facing Left")){
                        ((ImageType)holdActor).changeImage("turret-up-left.png");
                    }else if(dir1.equals("Facing Right")){
                        ((ImageType)holdActor).changeImage("turret-up-right.png");
                    }else if(dir1.equals("Facing Down Left")){
                        ((ImageType)holdActor).changeImage("turret-up-down-left.png");
                    }else if(dir1.equals("Facing Down Right")){
                        ((ImageType)holdActor).changeImage("turret-up-down-right.png");
                    }
                }else{
                    ((ImageType)holdActor).changeImage("turret-up-left.png");
                    directionMenu.setReturnValue("Facing Left");
                }
            }else if(dir2.equals("Down")){
                if(!(dir1.equals("Facing Down") || dir1.equals("Facing Down Left") || dir1.equals("Facing Down Right"))){
                    if(dir1.equals("Facing Up")){
                        ((ImageType)holdActor).changeImage("turret-down-up.png");
                    }else if(dir1.equals("Facing Right")){
                        ((ImageType)holdActor).changeImage("turret-down-right.png");
                    }else if(dir1.equals("Facing Left")){
                        ((ImageType)holdActor).changeImage("turret-down-left.png");
                    }else if(dir1.equals("Facing Up Left")){
                        ((ImageType)holdActor).changeImage("turret-down-up-left.png");
                    }else if(dir1.equals("Facing Up Right")){
                        ((ImageType)holdActor).changeImage("turret-down-up-right.png");
                    }
                }else{
                    ((ImageType)holdActor).changeImage("turret-down-left.png");
                    directionMenu.setReturnValue("Facing Left");
                }
            }
        }
    }

    /**
     * This is to move objects when the mouse is dragging it
     */
    private void movePlacedObjects(){
        //getting the mouse
        MouseInfo m = Greenfoot.getMouseInfo();
        //if there is no mouse, return
        if(m == null) return;
        //Click count ensure the that the user doesn't accidentally hold the down button too long
        clickActCount++;
        if(m.getButton() == 1 && clickActCount > 20 && m.getY() > 25){
            //if mouse is clicked, toggle draging
            toggleDrag(m);
            clickActCount = 0;
        }
        checkDrag();
    }

    /**
     * This is used to determine when the mouse is dragging and object.
     */
    private void checkDrag(){
        if (dragState){
            drag(currentActor);
            MouseInfo m = Greenfoot.getMouseInfo();
            //if the user bringst the object near the trash, remove the object 
            if(currentActor != null && currentActor != hero  && ((PhysicsObject)currentActor).checkTrash()){
                removeObject(currentActor);
                dragState = false;
                currentActor = null;
                trashCan.hide();
            }
        }
    }

    /**
     * method checks if the mouse if clicking on an actor and toggles the dragging for the actor based on whether of not it is already being dragged 
     */
    private void toggleDrag(MouseInfo m){
        Actor a = m.getActor();
        if (a == null){
            return;
        }
        if(a.getClass() == Platform.class || a.getClass() == Cube.class || a.getClass() == Hero.class || a.getClass() == Turret.class || a.getClass() == TNT.class || a.getClass() == Crate.class || a.getClass() == PortalGun.class || a.getClass() == Pistol.class || a.getClass() == RocketLauncher.class || a.getClass() == CubeGun.class || a.getClass() == Laser.class){
            if (dragState == false){
                dragState = true;
                currentActor = m.getActor();
                //can't see the trashcan if the hero is selected
                if (currentActor != hero){
                    trashCan.show();
                }

            } else {
                dragState = false;
                //if(currentActor == hero){
                //hero.checkFalling(true);
                ((PhysicsObject)currentActor).checkFalling(true);
                //}
                currentActor = null;
                trashCan.hide();
            }
        }
    }

    /**
     * the code the actually drags the actor and moves it based on the location of the mouse 
     */
    private void drag(Actor beingDragged){

        if(beingDragged == null)return;
        //if(beingDragged.getClass() == Hero.class){
        ((PhysicsObject) beingDragged).checkFalling(false);
        //hero.checkFalling(false);
        //}
        MouseInfo mouse = Greenfoot.getMouseInfo();
        int mouseX=mouse.getX();
        int mouseY=mouse.getY();
        if(mouse.getY() > 40){
            beingDragged.setLocation(mouse.getX(),mouse.getY());
            ((PhysicsObject) beingDragged).resetLocation((double) mouse.getX(), (double) mouse.getY());
        }else{
            beingDragged.setLocation(mouse.getX(),40);
        }
    }

    /**
     * controls the movement of the hero
     */
    private void checkKeys(){
        //check which keys are pressed
        if (Greenfoot.isKeyDown("a")) {

            hero.facingLeft(false);
            hero.moveLeft(); 
            hero.animate();
            //List objectList = getObjects(SmoothMover.class);
            //moveOrigin(objectList);

        } else if (Greenfoot.isKeyDown("d")) {

            hero.facingLeft(true);
            hero.moveRight();
            //List objectList = getObjects(SmoothMover.class);
            //moveOrigin(objectList);
            hero.animate();

        } else {
            hero.notMoving();
        }
        if (Greenfoot.isKeyDown("w")){
            hero.jump();
        }

    }

    /**
     * This method will be called by external classes to ensure that objects will only move when the Editor is in the "play" mode
     */
    public boolean returnBuildState(){
        return buildState;
    }

    /**
     * This is reset and place all of the elements in the top bar to turn the "play" mode into "editing" mode.
     */
    public void toggleBuildState(){
        if(pauseState == true){
            if(firstPlay == true){
                saveToFile(false);
                firstPlay = false;
                removeObject(makePlatBtn);
                removeObject(makeCubeBtn);
                removeObject(makeTurretBtn);
                removeObject(makeCrateBtn);
                removeObject(makeTNTBtn);
                removeObject(makeEndingBtn);
                removeObject(makePGunBtn);
                removeObject(makePistolBtn);
                removeObject(makeRLBtn);
                removeObject(makeCGBtn);
                removeObject(makeLBtn);

            }
            buildState = false;
            pauseState = false;
        }else{
            buildState = false;
            pauseState = true;
        }
    }

    /**
     * This method will take all of the different objects in the world and save it into a txt file for later use.
     */
    public void saveToFile(){
        recenterOrigin();
        try{
            PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
            //global locations: spawnlocx,spawnlocy,dimensionx,dimensiony
            writer.print(hero.returnVars().get("x") + "~" + hero.returnVars().get("y") + "~" + dimensionx + "~" + dimensiony);
            //I include the "~" here to occupy a line in the file
            writer.print("\n~");
            //location of platforms: 
            for (Actor i : getObjects(PhysicsObject.class)){
                if(i instanceof Platform){
                    HashMap data = ((PhysicsObject)i).returnVars();
                    writer.print(data.get("canPortal") + "!" + data.get("x") + "!" + data.get("y") + "!" + data.get("w") + "!" + data.get("h") + "!" + data.get("aE"));
                    writer.print("~");
                }
            }
            writer.print("\n~");
            //location of cubes: 
            for (Actor i : getObjects(PhysicsObject.class)){
                if(i instanceof Cube){
                    HashMap data = ((PhysicsObject)i).returnVars();
                    writer.print(data.get("x") + "!" + data.get("y") + "!" + data.get("Vx") + "!" + data.get("Vy") + "!" + data.get("Ax") + "!" + data.get("Ay") + "!" + data.get("aE"));
                    writer.print("~");
                }
            }
            writer.print("\n~");
            //location of cubes: 
            for (Actor i : getObjects(PhysicsObject.class)){
                if(i instanceof Turret){
                    HashMap data = ((Turret)i).getData();
                    writer.print(data.get("canMove") + "!" + data.get("x") + "!" + data.get("y") + "!" + data.get("Vx") + "!" + data.get("Vy") + "!" + data.get("Ax") + "!" + data.get("Ay") + "!" + data.get("aE") + "!" + data.get("dir1") + "!" + data.get("dir2") + "!" + data.get("fireRate") + "!" + data.get("type"));
                    writer.print("~");
                }
            }
            writer.close();
        } catch (Exception e) {
            // do something
        }
    }

    /**
     * This method is the ui that will prompt hte user to save their game.
     */
    public void saveGameUI(){
        addObject(greyBackdrop,0,0);
        stringInputBox = new StringInputBox(nameOfFile);
        addObject(stringInputBox,500,300);
        addObject(xIcon,731,288);
        isWritingFileNameVar = true;
    }

    /**
     * This method is used by the player to select a game save. It loads the UI to do this functionaility. 
     */
    public void loadGame(){
        addObject(greyBackdrop,0,0);
        File folder = new File("saves");
        File[] listOfFiles = folder.listFiles();
        int numFiles = listOfFiles.length;
        int filesHeight = numFiles*30;
        for(int i = 0 ; i < numFiles; i++){
            addObject(new FileNameHolder(listOfFiles[i].getName()),500,300-(filesHeight/2) + i*30);
        }
        fileNameHolderBack = new FileNameHolderBack(filesHeight+40);
        addObject(xIcon,582,300-(filesHeight/2)+5);
        //addObject(fileNameHolderBack,500,300 - (filesHeight+40)/2);
    }

    /**
     * This will close the UI popup. 
     */
    public void closeUI(){
        removeObject(greyBackdrop);
        removeObject(stringInputBox);
        removeObject(xIcon);
        for (Actor i : getObjects(FileNameHolder.class)){
            removeObject(i);
        }
        removeObject(fileNameHolderBack);
        isWritingFileNameVar = false;
    }

    public String getFileName(){
        return nameOfFile;
    }

    public boolean isWritingFileName(){
        return isWritingFileNameVar;
    }

    public void enterSaveFile(String word){
        closeUI();
        nameOfFile = word;
        fileName.changeName(word);
        saveToFile(true);
    }

    public void loadSelectedWorld(String name){
        closeUI();
        fileName.changeName(name.substring(0,name.length()-4));
        readFile(name.substring(0,name.length()-4),true);
    }

    /**
     * This method will take all objects in the Editor and save it to a txt file.
     */
    public void saveToFile(boolean showSaved){
        recenterOrigin();
        try{
            PrintWriter writer = new PrintWriter("saves/"+nameOfFile+".txt", "UTF-8");
            //global locations: spawnlocx,spawnlocy,dimensionx,dimensiony
            writer.print(hero.returnVars().get("x") + "~" + hero.returnVars().get("y") + "~" + dimensionx + "~" + dimensiony);
            //I include the "~" here to occupy a line in the file
            writer.print("\n~");
            //location of platforms: 
            for (Actor i : getObjects(PhysicsObject.class)){
                if(i instanceof Platform){
                    HashMap data = ((PhysicsObject)i).returnVars();
                    writer.print(data.get("canPortal") + "!" + data.get("x") + "!" + data.get("y") + "!" + data.get("w") + "!" + data.get("h") + "!" + data.get("aE"));
                    writer.print("~");
                }
            }
            writer.print("\n~");
            //location of cubes: 
            for (Actor i : getObjects(PhysicsObject.class)){
                if(i instanceof Cube){
                    HashMap data = ((PhysicsObject)i).returnVars();
                    writer.print(data.get("x") + "!" + data.get("y") + "!" + data.get("Vx") + "!" + data.get("Vy") + "!" + data.get("Ax") + "!" + data.get("Ay") + "!" + data.get("aE"));
                    writer.print("~");
                }
            }
            writer.print("\n~");
            //location of turret: 
            for (Actor i : getObjects(PhysicsObject.class)){
                if(i instanceof Turret){
                    HashMap data = ((Turret)i).getData();
                    writer.print(data.get("canMove") + "!" + data.get("x") + "!" + data.get("y") + "!" + data.get("Vx") + "!" + data.get("Vy") + "!" + data.get("Ax") + "!" + data.get("Ay") + "!" + data.get("aE") + "!" + data.get("dir1") + "!" + data.get("dir2") + "!" + data.get("fireRate") + "!" + data.get("type"));
                    writer.print("~");
                }
            }
            writer.print("\n~");
            //location of crates: 
            for (Actor i : getObjects(PhysicsObject.class)){
                if(i instanceof Crate){
                    HashMap data = ((PhysicsObject)i).returnVars();
                    writer.print(data.get("canMove") + "!" + data.get("x") + "!" + data.get("y") + "!" + data.get("w") + "!" + data.get("h") + "!" + data.get("Vx") + "!" + data.get("Vy") + "!" + data.get("Ax") + "!" + data.get("Ay") + "!" +  data.get("aE"));
                    writer.print("~");
                }
            }
            writer.print("\n~");
            //location of TNT: 
            for (Actor i : getObjects(PhysicsObject.class)){
                if(i instanceof TNT){
                    HashMap data = ((PhysicsObject)i).returnVars();
                    double fuseLength = ((TNT)i).returnFuse();
                    writer.print(data.get("x") + "!" + data.get("y") + "!" + data.get("Vx") + "!" + data.get("Vy") + "!" + data.get("Ax") + "!" + data.get("Ay") + "!" + data.get("aE") + "!" + fuseLength);
                    writer.print("~");
                }
            }
            writer.print("\n~");
            //location of Pgun: 
            for (Actor i : getObjects(PhysicsObject.class)){
                if(i instanceof PortalGun){
                    HashMap data = ((PhysicsObject)i).returnVars();
                    writer.print(data.get("x") + "!" + data.get("y"));
                    writer.print("~");
                }
            }
            writer.print("\n~");
            //location of Pistol: 
            for (Actor i : getObjects(PhysicsObject.class)){
                if(i instanceof Pistol){
                    HashMap data = ((PhysicsObject)i).returnVars();
                    writer.print(data.get("x") + "!" + data.get("y"));
                    writer.print("~");
                }
            }
            writer.print("\n~");
            //location of RocketLauncher: 
            for (Actor i : getObjects(PhysicsObject.class)){
                if(i instanceof RocketLauncher){
                    HashMap data = ((PhysicsObject)i).returnVars();
                    writer.print(data.get("x") + "!" + data.get("y"));
                    writer.print("~");
                }
            }
            writer.print("\n~");
            //location of CubeGun: 
            for (Actor i : getObjects(PhysicsObject.class)){
                if(i instanceof CubeGun){
                    HashMap data = ((PhysicsObject)i).returnVars();
                    writer.print(data.get("x") + "!" + data.get("y"));
                    writer.print("~");
                }
            }
            writer.print("\n~");
            //location of Laser: 
            for (Actor i : getObjects(PhysicsObject.class)){
                if(i instanceof Laser){
                    HashMap data = ((PhysicsObject)i).returnVars();
                    writer.print(data.get("x") + "!" + data.get("y"));
                    writer.print("~");
                }
            }
            writer.print("\n~");
            //location of ending: 
            for (Actor i : getObjects(PhysicsObject.class)){
                if(i instanceof Ending){
                    HashMap data = ((PhysicsObject)i).returnVars();
                    writer.print(data.get("canMove") + "!" + data.get("x") + "!" + data.get("y") + "!" + data.get("Vx") + "!" + data.get("Vy") + "!" + data.get("Ax") + "!" + data.get("Ay") + "!" +  data.get("aE"));
                    writer.print("~");
                }
            }
            writer.close();
        } catch (Exception e) {
            // do something
        }
        if(showSaved){
            addObject(new Notification("File Saved"),920,80);
        }
    }

    /**
     * A helper method for the readFile method.
     */
    private double[] stringArrToDouble(String[] arr){
        double[] newArr = new double[arr.length];
        for(int i = 0 ; i < arr.length; i++){
            newArr[i] = Double.parseDouble(arr[i]);
        }
        return newArr;
    }

    private Scanner getTextFile(String fileName){
        InputStream myFile = getClass().getResourceAsStream(fileName);
        if(myFile != null){
            return new Scanner(myFile);
        }
        return null;
    }

    /**
     * This will read a file and construct all of the elements into the screen.
     */
    public void readFile(String nameOfThisFile, boolean showNotification){
        nameOfFile = nameOfThisFile;
        recenterOrigin();
        closeUI();
        firstPlay = true;
        pauseState = true;
        buildState = true;
        addObject(makeCubeBtn,100, 20);
        addObject(makePlatBtn,150, 20);
        addObject(makeTurretBtn,200, 20);
        addObject(makeCrateBtn,250,20);
        addObject(makeTNTBtn,300,20);
        addObject(makeEndingBtn, 350, 20);
        addObject(makeLBtn, 550, 20);
        addObject(makePGunBtn,600,20);
        addObject(makePistolBtn, 650, 20);
        addObject(makeRLBtn, 700, 20);
        addObject(makeCGBtn, 750, 20);
        fileContents.clear();
        for (Actor i : getObjects(Actor.class)){
            if(i instanceof PhysicsObject){
                removeObject(i);
            }else if(i instanceof Projectiles){
                removeObject(i);
            }else if(i == bluePortal){
                removeObject(i);
                bluePortal = null;
            }else if(i == orangePortal){
                removeObject(i);
                bluePortal = null;
            }
        }
        Scanner in = getTextFile("saves/"+nameOfThisFile+".txt");
        while(in.hasNext()){
            fileContents.add(in.nextLine());
        }
        double[] worldVars = stringArrToDouble(fileContents.get(0).split("~"));
        hero = new Hero(worldVars[0],worldVars[1]);
        String[] platforms = fileContents.get(1).split("~");
        String[] cubes = fileContents.get(2).split("~");
        String[] turrets = fileContents.get(3).split("~");
        String[] crates = fileContents.get(4).split("~");
        String[] Tnt = fileContents.get(5).split("~");
        String[] ending = fileContents.get(5).split("~");
        String[] Pgun = fileContents.get(6).split("~");
        String[] pistol = fileContents.get(7).split("~");
        String[] rocketLauncher = fileContents.get(8).split("~");
        String[] cubeGun = fileContents.get(9).split("~");
        String[] laser = fileContents.get(10).split("~");

        addObject(hero,0,0);
        for(String i : platforms){
            //this makes sure that the line it's reading isn't just a "~"
            if(i.length() > 1){
                double[] p = stringArrToDouble(i.split("!"));
                addObject(new Platform(p[0],p[1],p[2],p[3],p[4],p[5]), 0, 0);
            }
        }

        for(String i : cubes){
            if(i.length() > 1){
                double[] p = stringArrToDouble(i.split("!"));
                addObject(new Cube(p[0],p[1],p[2],p[3],p[4],p[5],p[6]), 0, 0);
            }
        }

        for(String i : turrets){
            if(i.length() > 1){
                String[] p = i.split("!");
                addObject(new Turret(p[0],Double.parseDouble(p[1]),Double.parseDouble(p[2]),Double.parseDouble(p[3]),Double.parseDouble(p[4]),Double.parseDouble(p[5]),Double.parseDouble(p[6]),Double.parseDouble(p[7]),p[8],p[9],Integer.parseInt(p[10]),p[11]), 0, 0);
            }
        }

        for(String i : crates){
            //this makes sure that the line it's reading isn't just a "~"
            if(i.length() > 1){
                String[] p = i.split("!");
                addObject(new Crate(p[0],Double.parseDouble(p[1]),Double.parseDouble(p[2]),Double.parseDouble(p[3]),Double.parseDouble(p[4]),Double.parseDouble(p[5]),Double.parseDouble(p[6]),Double.parseDouble(p[7]),Double.parseDouble(p[8]),Double.parseDouble(p[9])), 0, 0);
            }
        }

        for(String i : Tnt){
            //this makes sure that the line it's reading isn't just a "~"
            if(i.length() > 1){
                double[] p = stringArrToDouble(i.split("!"));
                addObject(new TNT(p[0],p[1],p[2],p[3],p[4],p[5],p[6],p[7]), 0, 0);
            }
        }

        for(String i : Pgun){
            //this makes sure that the line it's reading isn't just a "~"
            if(i.length() > 1){
                double[] p = stringArrToDouble(i.split("!"));
                addObject(new PortalGun(p[0],p[1]), 0, 0);
            }
        }

        for(String i : pistol){
            //this makes sure that the line it's reading isn't just a "~"
            if(i.length() > 1){
                double[] p = stringArrToDouble(i.split("!"));
                addObject(new Pistol(p[0],p[1]), 0, 0);
            }
        }

        for(String i : rocketLauncher){
            //this makes sure that the line it's reading isn't just a "~"
            if(i.length() > 1){
                double[] p = stringArrToDouble(i.split("!"));
                addObject(new RocketLauncher(p[0],p[1]), 0, 0);
            }
        }

        for(String i : cubeGun){
            //this makes sure that the line it's reading isn't just a "~"
            if(i.length() > 1){
                double[] p = stringArrToDouble(i.split("!"));
                addObject(new CubeGun(p[0],p[1]), 0, 0);
            }
        }

        for(String i : laser){
            //this makes sure that the line it's reading isn't just a "~"
            if(i.length() > 1){
                double[] p = stringArrToDouble(i.split("!"));
                addObject(new Laser(p[0],p[1]), 0, 0);
            }
        }
        
        for(String i : ending){
            //this makes sure that the line it's reading isn't just a "~"
            if(i.length() > 1){
                String[] p = i.split("!");
                addObject(new Ending(p[0],Double.parseDouble(p[1]),Double.parseDouble(p[2]),Double.parseDouble(p[3]),Double.parseDouble(p[4]),Double.parseDouble(p[5]),Double.parseDouble(p[6]),Double.parseDouble(p[7])), 0, 0);
            }
        }

        if(showNotification){
            addObject(new Notification("Loaded "+nameOfThisFile),920,80);
        }
    }

    public int getOriginOffsetX(){
        return originX * (-1);
    }

    public int getOriginOffsetY(){
        return originY * (-1);
    }

    /**
     * this is used to pan the camera around when someone is building the world.
     */
    private void moveOrigin(List objectList){
        //check which keys are pressed
        if (Greenfoot.isKeyDown("d")) {
            originX -= 3;
            for (int i = 0; i < objectList.size(); i++) {
                ((Actor)objectList.get(i)).setLocation(((Actor)objectList.get(i)).getX() - 3, ((Actor)objectList.get(i)).getY());
            }
        } else if (Greenfoot.isKeyDown("a")) {
            originX += 3;
            for (int i = 0; i < objectList.size(); i++) {
                ((Actor)objectList.get(i)).setLocation(((Actor)objectList.get(i)).getX() + 3, ((Actor)objectList.get(i)).getY());
            }
        } else {

        }
        if (Greenfoot.isKeyDown("s")){
            originY -= 3;
            for (int i = 0; i < objectList.size(); i++) {
                ((Actor)objectList.get(i)).setLocation(((Actor)objectList.get(i)).getX(), ((Actor)objectList.get(i)).getY() - 3);
            }
        } else if (Greenfoot.isKeyDown("w")){
            originY += 3;
            for (int i = 0; i < objectList.size(); i++) {
                ((Actor)objectList.get(i)).setLocation(((Actor)objectList.get(i)).getX(), ((Actor)objectList.get(i)).getY() + 3);
            }
        }
        if (Greenfoot.isKeyDown("space")){

        }
    }

    public void moveEverything(double xAmount, double yAmount){
        originX +=xAmount;
        originY +=yAmount;
        List objectList = getObjects(SmoothMover.class);
        for (int i = 0; i < objectList.size(); i++) {
            Actor theObject = (Actor)objectList.get(i);
            if(!(theObject instanceof Hero)){
                theObject.setLocation(((Actor)objectList.get(i)).getX() + (int)Math.round(xAmount), ((Actor)objectList.get(i)).getY());
            }
            /*if(theObject instanceof Platform){
                System.out.println
            }*/
        }
    }
    
    /**
     * This is re calibrate the panning mechanism described above.
     */
    public void recenterOrigin(){
        int amountMovedX = originX * (-1);
        int amountMovedY = originY * (-1);
        for (Actor i : getObjects(SmoothMover.class)){
            i.setLocation(i.getX() + amountMovedX, i.getY() + amountMovedY );
        }
        originX = 0;
        originY = 0;
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
}   