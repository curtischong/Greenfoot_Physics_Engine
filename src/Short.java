import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Short here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Short extends Enemy
{
    boolean left = true;
    private boolean initLoc = false;
    List <Platform> platforms;
    int speed = 1;
    int range = 10000;
    int actCounter = 0;

    public Short(double xloc, double yloc){
        health=1;
        movable = true;
        x= xloc;
        y=yloc;
    }

    /**
     * Act - do whatever the Short wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
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

}
