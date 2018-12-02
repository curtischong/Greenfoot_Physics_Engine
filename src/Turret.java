/**
 * A block the sends out a stream of bullets
 * 
 * @author Curtis Chong
 * @version Dec 22
 */
import greenfoot.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;
public class Turret extends PhysicsObject
{
    private boolean initLoc = false;
    private String dir, direc1, direc2;
    private double shootCooldown = 200.0;
    private String bullDir = "";
    private int fireRate;
    private String typeTurret;
    private double bulletX, bulletY;
    //0 = left, 1 = right, 2 = top, 3 = down, 4 = topLeft, 5 = topRight, 6 = botRight, 7 = botLeft
    public Turret(String canMove, double xLoc, double yLoc, double Vxamount, double Vyamount, double Axamount, double Ayamount, double setAbsorbEnergy, String dir1, String dir2, int initFireRate, String type)
    {
        if(canMove.equals("Can Move")){
            movable = true;
        }else{
            movable = false;
        }
        canPortal = 0.0;
        gravity = 0.1;
        w = 32.0;
        h = 24.0;
        x = xLoc;
        y = yLoc;
        Vx = Vxamount;
        Vy = Vyamount;
        Ax = Axamount;
        Ay = Ayamount;
        aE = setAbsorbEnergy;
        fireRate = initFireRate;
        typeTurret = type;
        direc1 = dir1;
        direc2 = dir2;
        if(typeTurret.equals("Shoots Bullets")){
            if(dir2.equals("Up")){
                if(dir1.equals("Facing Down")){
                    dir = "turret-up-down.png";
                    bullDir = "bulletDown";
                    bulletX = 16.0;
                    bulletY = 8.0;
                }else if(dir1.equals("Facing Left")){
                    dir = "turret-up-left.png";
                    bullDir = "bulletL";
                    bulletX = 0.0;
                    bulletY = 16.0;
                }else if(dir1.equals("Facing Right")){
                    dir = "turret-up-right.png";
                    bullDir = "bulletR";
                    bulletX = 0.0;
                    bulletY = 16.0;
                }else if(dir1.equals("Facing Down Left")){
                    dir = "turret-up-down-left.png";
                    bullDir = "bulletBotL";
                    bulletX = 18.0;
                    bulletY = 14.0;
                }else if(dir1.equals("Facing Down Right")){
                    dir = "turret-up-down-right.png";
                    bullDir = "bulletBotR";
                    bulletX = 14.0;
                    bulletY = 14.0;
                }
            }else{
                if(dir1.equals("Facing Up")){
                    dir = "turret-down-up.png";
                    bullDir = "bulletUp";
                    bulletX = 16.0;
                    bulletY = -6.0;
                }else if(dir1.equals("Facing Right")){
                    dir = "turret-down-right.png";
                    bullDir = "bulletR";
                    bulletX = 0.0;
                    bulletY = 10.0;
                }else if(dir1.equals("Facing Left")){
                    dir = "turret-down-left.png";
                    bullDir = "bulletL";
                    bulletX = 0.0;
                    bulletY = 9.0;
                }else if(dir1.equals("Facing Up Left")){
                    dir = "turret-down-up-left.png";
                    bullDir = "bulletUpL";
                    bulletX = 6.0;
                    bulletY = 0.0;
                }else if(dir1.equals("Facing Up Right")){
                    dir = "turret-down-up-right.png";
                    bullDir = "bulletUpR";
                    bulletX = 22.0;
                    bulletY = 4.0;
                }
            }
        }
        setImage(dir);
    }

    public HashMap getData(){
        HashMap info = new HashMap();
        info.put("x", x);
        info.put("y", y);
        info.put("w", w);
        info.put("h", h);
        info.put("Ax", Ax);
        info.put("Ay", Ay);
        info.put("Vx", Vx);
        info.put("Vy", Vy);
        info.put("aE", aE);
        info.put("dir1", direc1);
        info.put("dir2", direc2);
        if(movable == false){
            info.put("canMove", "Can't Move");
        }else{
            info.put("canMove", "Can Move");
        }
        info.put("fireRate",fireRate);
        info.put("type",typeTurret);
        return info;
    }

    public void act() 
    {
        if(initLoc == false){
            if(direc2.equals("Down")){
                setLocation(x+w/2, (y+h/2)-3);
            }else{
                setLocation(x+w/2, (y+h/2)+3);
            }
            initLoc = true;
        }

        Editor m = (Editor)getWorld();
        if(m.returnBuildState() == false){
            if(movable == true){
                moveObject(0.0,0.0);
                if(direc2.equals("Down")){
                    setLocation(x+w/2, (y+h/2)-3);
                }else{
                    setLocation(x+w/2, (y+h/2)+3);
                }
            }
            if(shootCooldown <= 0.0){
                shootCooldown = 200.0;
                //we need to offset the bulletX and Y for every single turret direction
                m.addObject(new Bullet(bullDir, x+bulletX, y+bulletY, this), 0, 0);
            }else{
                if(fireRate == 0){
                    shootCooldown-=1;
                }else{
                    shootCooldown -= Math.log10(fireRate);
                }
            }
        }
        //weakenForces(0.9896, 1.0);
    }
}
