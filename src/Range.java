import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Range here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Range extends Enemy
{
    boolean left = true;
    List <Platform> platforms;
    private boolean initLoc = false;
    int speed = 1;
    int range = 50;
    int actCounter = 0;
    int shootCount = 0;
    Cube cube;

    public Range(double xLoc, double yLoc){
        health = 1;
        movable = true;
        x=xLoc;
        y=yLoc;
    }

    public void act() 
    {  

        if(initLoc == false){
            setLocation(x+(w/2), y+(h/2));
            initLoc = true;
        }

        if (getWorld() instanceof Editor){
            Editor m = (Editor)getWorld();
            if(m.returnBuildState() == false){
                if (actCounter >= range){
                    if (left){
                        left = false;
                    } else {
                        left = true;
                    }
                    actCounter=0;
                }else{
                    if (left){
                        moving(true);

                    } else {

                        moving(false);

                    }
                    actCounter ++;
                    //}
                }
                if (shootCount>=25){
                    shoot();
                    shootCount=0;
                }else{
                    shootCount++;
                }
                checkPlatforms();

                checkHit();
                checkDeath();
            }
        } else{
            if (actCounter >= range){
                if (left){
                    left = false;
                } else {
                    left = true;
                }
                actCounter=0;
            }else{
                if (left){
                    moving(true);

                } else {

                    moving(false);

                }
                actCounter ++;
                //}
            }
            if (shootCount>=25){
                shoot();
                shootCount=0;
            }else{
                shootCount++;
            }
            checkPlatforms();

            checkHit();
            checkDeath();
        }

    }  

    private void moving (boolean facingLeft){
        if (facingLeft){
            this.move(-speed);

        } else{
            this.move(speed);
        }
    }

    private void checkPlatforms (){
        platforms = getWorld().getObjects(Platform.class);
        for (Platform p : platforms){
            if (this.getX()-(p.getX()+((p.w)/2))<30 && this.getX()-(p.getX()+((p.w)/2))>0) {
                if (left){
                    left = false;
                    //actCounter=0; 
                    //p.getY()>= this.getY()-5 && p.getY()<= this.getY()+5
                } 

            }

            if ((p.getX()-((p.w)/2))-this.getX()<30 && (p.getX()-((p.w)/2))-this.getX()>0){
                if(left==false){
                    left = true;
                    //actCounter = 0;
                }
            }
        }
    }

    private void shoot(){
        if (getWorld() instanceof Editor){
            Editor m = (Editor)getWorld();
        } else {
            Main m = (Main)getWorld();
            m.addObject(new Bullet2("bulletL", x, y, this), (int)x, (int)y);
            m.addObject(new Bullet2("bulletR", x, y, this), (int)x, (int)y);
            m.addObject(new Bullet2("bulletUp", x, y, this), (int)x, (int)y);

        }
    }
}
