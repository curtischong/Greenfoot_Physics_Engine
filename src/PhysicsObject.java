/**
 * This class will describe the location vectors, movement functions, and collision detection functions of a cube-like objects
 * 
 * @author Curtis Chong
 * @version 1
 */
import java.util.HashMap;
import java.util.ArrayList;
public abstract class PhysicsObject extends SmoothMover
{
    //x, y coords of the object
    protected double x;
    protected double y;

    //width, height of the object
    protected double w;
    protected double h;

    //x, y, z acceleration of the object
    protected double Ax = 0.0;
    protected double Ay = 0.0;

    //x, y, z velocity of the object
    protected double Vx;
    protected double Vy;

    protected boolean movable;
    protected double aE;
    protected double gravity;   

    protected double canPortal;

    protected int recentlyPortalled = 0;
    /**
     * Constructor for objects of class physics
     */
    //these forces will be added onto the acceleration
    protected void changeSpeed(double fx, double fy){
        if(fx + Vx > 10){
            Vx = 10;
        }else if(fx + Vx < -10){
            Vx = -10;
        }else{
            Vx += fx;
        }
        if(fy + Vy > 10){
            Vy = 10;
        }else if(fy + Vy < -10){
            Vy = -10;
        }else{
            Vy += fy;
        }
    }

    public void setVelocity(double newVx, double newVy){
        Vx = newVx;
        Vy = newVy;
    }

    protected void changeLocation(double xVal, double yVal){
        x += xVal;
        y += yVal;
    }

    protected void changeAbsoluteLocation(double xVal, double yVal){
        x = xVal;
        y = yVal;
    }

    protected double changeSpeedX(double xForces){
        //sum up all of the y forces
        double proposedX = x +Vx + xForces;
        return proposedX;
    }

    protected double changeSpeedY(double yForces){
        //sum up all of the y forces
        double proposedY = y +Vy + yForces;
        return proposedY;
    }

    protected void applyForce(double fx, double fy){
        Ax += fx;
        Ay += fy;
    }

    protected HashMap returnVars(){
        /*
        ArrayList[0: x, 1: y, 2: w, 3: h, 4: Ax, 5: Ay, 6: Vx, 7: Vy]
         */
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
        info.put("canPortal",canPortal);
        info.put("recentlyPortalled",recentlyPortalled);
        if(movable == true){
            info.put("canMove","Can Move");
        }else{
            info.put("canMove","Can't Move");
        }
        return info;
    }

    protected void weakenForces(double Fx, double Fy){
        Ax = Ax*Fx;
        Ay = Ay*Fy;
    }

    private void resetVelocity(){
        Vx = 0.0;
        Vy = 0.0;
    }

    public void setRecentlyPortalled(){
        recentlyPortalled = 15;
    }

    protected void moveObject(Double Fx, Double Fy){
        double proposedY = changeSpeedY(Fy);
        double proposedX = changeSpeedX(Fx);
        moveXY(proposedX, proposedY);
    }

    protected boolean teleportCheck(double xVar, double yVar){
        double dist = Math.sqrt(Math.pow(xVar - x,2) + Math.pow(yVar - y, 2));
        if(dist > 15.0 && recentlyPortalled == 0){
            if(Vy > 7.0){
                Vy = 0.0;
                y = y-1.0;
            }
            return false;
        }
        return true;
    }

    protected void moveXY(double proposedX, double proposedY){
        if(recentlyPortalled > 0){
            recentlyPortalled--;
        }
        ArrayList physicsObjects = (ArrayList)(getWorld().getObjects(PhysicsObject.class));
        boolean canMove = true;
        boolean[] hitloc = {false,false,false,false};
        //0 = left, 1 = right, 2 = top, 3 = bot
        //note: objects can go through other objects. but I fear for the game's performance if I include those checks (because objects shouldn't fly that fast)
        for(Object i : physicsObjects){
            PhysicsObject currentObject = (PhysicsObject)i;
            HashMap info = currentObject.returnVars();
            double objY = (double)info.get("y");
            double objH = (double)info.get("h");

            double objVx = (double)info.get("Vx");
            double objVy = (double)info.get("Vy");

            double objX = (double)info.get("x");
            double objW = (double)info.get("w");
            double objaE = (double)info.get("aE");
            //this first if statement accound for an object directly intersecting with the second object when impacting
            if( this != currentObject && (proposedY > objY - h && proposedY < objY + objH)){
                if(proposedX >= (objX - w) && proposedX <= (objX + objW)-1){
                    canMove = false;
                    //the cube is hitting the object from above
                    //cube is hitting cube on the right side
                    if(x+1 > (objX + objW)){
                        Vy+= Ay + gravity;
                        if(teleportCheck(objX + objW, y) && ((int)y != ((int)(objY + objH)))){
                            if(((int)y != ((int)(objY + objH))) && ((int)y != ((int)(objY -h)))){
                                if(hitloc[2] == false && hitloc[3] == false){
                                    y = proposedY;
                                }
                                x = objX + objW;
                                //give the colliding object some of our kinetic energy
                                currentObject.changeSpeed(Vx*objaE*0.8, 0.0);
                                Vx = -Vx*objaE*0.5;
                                //slow down both objects because of friction
                                this.changeSpeed(0.0,-Vy*0.005);
                                currentObject.changeSpeed(0.0,-objVy*objaE*0.05);
                                setLocation(x + w/2, y + h/2);
                                hitloc[1] = true;
                            }
                        }
                        //cube is hitting cube on left side
                    }else if (x-1 < (objX - w)){
                        Vy+= Ay + gravity;
                        if(teleportCheck(objX - w, y)){
                            if(hitloc[2] == false && hitloc[3] == false){
                                y = proposedY;
                            }
                            x = objX - w;
                            currentObject.changeSpeed(Vx*objaE*0.8,0.0);
                            Vx = -Vx*objaE*0.5;
                            //slow down both objects because of friction
                            this.changeSpeed(0.0,-Vy*0.005);
                            currentObject.changeSpeed(0.0,-objVy*objaE*0.05);
                            //cube is hitting cube on top
                            setLocation(x + w/2, y + h/2);
                            hitloc[0] = true;
                        }
                    }else if(y-1 < objY ){
                        //set on top if the box is landing on the second box
                        if(teleportCheck(x, objY - h) && hitloc[3] == false){
                            y = objY - h;
                            currentObject.changeSpeed(0.0,Vy*objaE*0.7);
                            Vy = -Vy*objaE*0.5;
                            //slow down both objects because of friction
                            if(Vx < 0.0){
                                this.changeSpeed(-Vx*aE*0.05,0.0);
                            }
                            currentObject.changeSpeed(-objVx*objaE*0.05,0.0);
                            setLocation(proposedX + w/2, y + h/2);
                            hitloc[3] = true;
                        }
                        //the cube is hitting the object from below
                    }else if(y+1 > objY){
                        //absorb upwards kinetic friction and apply a downwards force onto it
                        if(teleportCheck(x, objY + objH)){
                            y = objY + objH;
                            currentObject.changeSpeed(0.0,Vy*objaE*0.7);
                            Vy = -Vy*objaE;
                            //slow down both objects because of friction
                            this.changeSpeed(-Vx*aE*0.05,0.0);
                            currentObject.changeSpeed(-objVx*objaE*0.002,0.0);
                            setLocation(proposedX + w/2, y + h/2);
                            hitloc[2] = true;
                        }
                        //here the proposed position doesn't satisfy any conditions so we just place it at the location of where it should be
                    }else{
                        setLocation(proposedX + w/2,proposedY + h/2);
                        x = proposedX;
                        y = proposedY;
                    }
                }
            }
            objY = 0.0;
            objH = 0.0;

            objX = 0.0;
            objW = 0.0;
            objaE = 0.0;
        }
        if(canMove == true){
            Vy+= Ay + gravity;
            Vx+= Ax;
            /*if(this instanceof Hero){
                Editor e = (Editor)getWorld();
                e.moveEverything(proposedX-x, proposedY-y);
                y = proposedY;
            x = proposedX;
            }else{*/
                y = proposedY;
            x = proposedX;
                setLocation(proposedX + w/2, y + h/2);
                setLocation(x + w/2, proposedY + h/2);
            //}
        }
    }

    public void resetLocation(double x, double y){
        this.x = x - (double) returnVars().get("w")/2;
        this.y = y - (double) returnVars().get("h") / 2;
    }

    public boolean checkTrash(){
        if(getOneIntersectingObject(TrashCan.class) != null){
            return true;
        }
        return false;
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
}
