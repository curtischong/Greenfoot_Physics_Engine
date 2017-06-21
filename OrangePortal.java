import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/**
 * Write a description of class OrangePortal here.
 * 
 * @author Curtis Chong
 * @version 1
 */
public class OrangePortal extends SmoothMover
{
    /**
     * Act - do whatever the OrangePortal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private double x;
    private double y;
    private double w;
    private double h;
    private int direction;
    boolean initLoc = false;
    private int spawnParticleCount = 10;
    public OrangePortal(int dir, double xLoc, double yLoc){
        x = xLoc;
        y = yLoc;

        direction = dir;
        //0 = left, 1 = right, 2 = top, 3 = bot
        if(dir == 0){
            w = 2.0;
            h = 100.0;
        }else if(dir == 1){
            w = 2.0;
            h = 100.0;
        }else if(dir == 2){
            w = 100.0;
            h = 2.0;
        }else if(dir == 3){
            w = 100.0;
            h = 2.0;
        }
        /*setImage("portal1.png");
        setLocation(x,y);*/
        GreenfootImage image = new GreenfootImage((int)w, (int)h);
        image.setColor(java.awt.Color.ORANGE);
        image.fill();
        setImage(image);
    }

    public void act() 
    {
        Editor m = (Editor)getWorld();
        if(initLoc == false){
            setLocation(x+w/2, y+h/2);
            initLoc = true;
        }

        if(m.returnBuildState() == false){
            if(spawnParticleCount == 0){
                if(direction == 0){
                    Random rand = new Random();
                    m.addObject(new PortalParticle(x-2.0, y + rand.nextInt(95) + 5, 0, true),0,0);
                }else if(direction == 1){
                    Random rand = new Random();
                    m.addObject(new PortalParticle(x+2.0, y + rand.nextInt(95) + 5, 1, true),0,0);
                }else if(direction == 2){
                    Random rand = new Random();
                    m.addObject(new PortalParticle(x + rand.nextInt(95) + 5,y-2.0, 2, true),0,0);
                }else if(direction == 3){
                    Random rand = new Random();
                    m.addObject(new PortalParticle(x + rand.nextInt(95) + 5,y+2.0, 3, true),0,0);
                }
                spawnParticleCount = 50;
            }else{
                spawnParticleCount--;
            }
        }

        String theBlueData = m.getBluePortalData();
        if(theBlueData.equals("nope")){

        }else{
            String[] blueData = m.getBluePortalData().split(",");
            ArrayList physicsObjects = (ArrayList)(getWorld().getObjects(PhysicsObject.class));
            for(Object i : physicsObjects){
                HashMap info = ((PhysicsObject)i).returnVars();
                PhysicsObject currentObject = (PhysicsObject)i;
                double objY = (double)info.get("y");
                double objH = (double)info.get("h");

                double objVx = (double)info.get("Vx");
                double objVy = (double)info.get("Vy");

                double objX = (double)info.get("x");
                double objW = (double)info.get("w");

                int objRecentlyPortalled = (int)info.get("recentlyPortalled");
                if(objRecentlyPortalled -5 < 0){
                    if(direction == 2){
                        if(objVy < 5.0){
                            if((objX > x) && ((objX+objW) < x+w) && (objY + objH > y-5) && objY < y){
                                currentObject.setRecentlyPortalled();
                                if(blueData[2].equals("3")){
                                    currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + 50.0 - (objW/2), Double.valueOf(blueData[1]));
                                }else if(blueData[2].equals("2")){
                                    currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + 50.0 - (objW/2), Double.valueOf(blueData[1]) - objH -3);
                                    currentObject.setVelocity(objVx, -objVy+0.15);
                                    if(Math.abs(objVy) < 5 && Math.abs(objVx) < 5){
                                        currentObject.setVelocity(objVx, -3.0);
                                    }
                                }else if(blueData[2].equals("0")){
                                    currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) - objW, Double.valueOf(blueData[1]) + 50.0 - (objH/2));
                                    currentObject.setVelocity(-objVy, objVx);
                                }else if(blueData[2].equals("1")){
                                    currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]), Double.valueOf(blueData[1]) + 50.0 - (objH/2));
                                    currentObject.setVelocity(objVy, -objVx);
                                }
                            }
                        }else{
                            if((objX > x) && ((objX+objW) < x+w) && (objY + objH > y-20) && objY < y){
                                currentObject.setRecentlyPortalled();
                                if(blueData[2].equals("3")){
                                    currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + 50.0 - (objW/2), Double.valueOf(blueData[1]));
                                }else if(blueData[2].equals("2")){
                                    currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + 50.0 - (objW/2), Double.valueOf(blueData[1]) - objH -3);
                                    currentObject.setVelocity(objVx, -objVy+0.15);
                                }else if(blueData[2].equals("0")){
                                    currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) - objW, Double.valueOf(blueData[1]) + 50.0 - (objH/2));
                                    currentObject.setVelocity(-objVy, objVx);
                                }else if(blueData[2].equals("1")){
                                    currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]), Double.valueOf(blueData[1]) + 50.0 - (objH/2));
                                    currentObject.setVelocity(objVy, -objVx);
                                }
                            }
                        }
                    }else if(direction == 3){
                        if((objX > x) && ((objX+objW) < x+w) && (objY > y) && objY-5 < y){
                            currentObject.setRecentlyPortalled();
                            if(blueData[2].equals("3")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + 50.0 - (objW/2), Double.valueOf(blueData[1]));
                                currentObject.setVelocity(objVx, -objVy);
                            }else if(blueData[2].equals("2")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + 50.0 - (objW/2), Double.valueOf(blueData[1]) - objH -3);
                                currentObject.setVelocity(objVx, -objVy-7);
                            }else if(blueData[2].equals("0")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) - objW, Double.valueOf(blueData[1]) + 50.0 - (objH/2));
                                currentObject.setVelocity(objVy, objVx);
                            }else if(blueData[2].equals("1")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + objW, Double.valueOf(blueData[1]) + 50.0 - (objH/2));
                                currentObject.setVelocity(-objVy, objVx);
                            }
                        }
                    }else if(direction == 0){
                        if((objY > y) && ((objY+objH) < y+h) && (objX + objW > x) && (objX < x)){
                            currentObject.setRecentlyPortalled();
                            if(blueData[2].equals("1")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]), Double.valueOf(blueData[1]) + 50.0 - (objH/2));
                                currentObject.setVelocity(objVx, objVy);
                            }else if(blueData[2].equals("0")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0])  - objW, Double.valueOf(blueData[1]) + 50.0 - (objH/2));
                                currentObject.setVelocity(-objVx, objVy);
                            }else if(blueData[2].equals("2")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + 50.0 - (objW/2), Double.valueOf(blueData[1]) - objH -1);
                                currentObject.setVelocity(-objVy, -objVx-2);
                            }else if(blueData[2].equals("3")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + 50.0 - (objW/2), Double.valueOf(blueData[1]));
                                currentObject.setVelocity(objVy, objVx);
                            }
                        }
                    }else if(direction == 1){
                        if((objY > y) && ((objY+objH) < y+h) && (objX + objW > x) && (objX < x)){
                            currentObject.setRecentlyPortalled();
                            if(blueData[2].equals("1")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]), Double.valueOf(blueData[1]) + 50.0 - (objH/2));
                                currentObject.setVelocity(-objVx, objVy);
                            }else if(blueData[2].equals("0")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0])  - objW, Double.valueOf(blueData[1]) + 50.0 - (objH/2));
                                currentObject.setVelocity(objVx, objVy);
                            }else if(blueData[2].equals("2")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + 50.0 - (objW/2), Double.valueOf(blueData[1]) - objH -1);
                                currentObject.setVelocity(objVy, (objVx)-2);
                            }else if(blueData[2].equals("3")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + 50.0 - (objW/2), Double.valueOf(blueData[1]));
                                currentObject.setVelocity(objVy, -objVx);
                            }
                        }
                    }
                }
            }

            ArrayList projectiles = (ArrayList)(getWorld().getObjects(Projectiles.class));
            for(Object i : projectiles){
                HashMap info = ((Projectiles)i).returnVars();
                Projectiles currentObject = (Projectiles)i;
                double objY = (double)info.get("y");
                double objH = (double)info.get("h");

                double objVx = (double)info.get("Vx");
                double objVy = (double)info.get("Vy");

                double objX = (double)info.get("x");
                double objW = (double)info.get("w");
                int objRecentlyPortalled = (int)info.get("recentlyPortalled");
                if(objRecentlyPortalled -5 < 0){
                    if(direction == 2){
                        if((objX > x) && ((objX+objW) < x+w) && (objY + objH > y-5) && objY < y){
                            currentObject.setRecentlyPortalled();
                            if(blueData[2].equals("3")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + 50.0 - (objW/2), Double.valueOf(blueData[1]));
                            }else if(blueData[2].equals("2")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + 50.0 - (objW/2), Double.valueOf(blueData[1]) - objH -3);
                                currentObject.setVelocity(objVx, -objVy);
                            }else if(blueData[2].equals("0")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) - objW, Double.valueOf(blueData[1]) + 50.0 - (objH/2));
                                currentObject.setVelocity(-objVy, objVx);
                            }else if(blueData[2].equals("1")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]), Double.valueOf(blueData[1]) + 50.0 - (objH/2));
                                currentObject.setVelocity(objVy, -objVx);
                            }
                        }
                    }else if(direction == 3){
                        if((objX > x) && ((objX+objW) < x+w) && (objY > y) && objY-5 < y){
                            currentObject.setRecentlyPortalled();
                            if(blueData[2].equals("3")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + 50.0 - (objW/2), Double.valueOf(blueData[1]));
                                currentObject.setVelocity(objVx, -objVy);
                            }else if(blueData[2].equals("2")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + 50.0 - (objW/2), Double.valueOf(blueData[1]) - objH -3);
                                currentObject.setVelocity(objVx, -objVy);
                            }else if(blueData[2].equals("0")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) - objW, Double.valueOf(blueData[1]) + 50.0 - (objH/2));
                                currentObject.setVelocity(objVy, objVx);
                            }else if(blueData[2].equals("1")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + objW, Double.valueOf(blueData[1]) + 50.0 - (objH/2));
                                currentObject.setVelocity(-objVy, objVx);
                            }
                        }
                    }else if(direction == 0){
                        if((objY > y) && ((objY+objH) < y+h) && (objX + objW > x-4) && (objX < x+4)){
                            currentObject.setRecentlyPortalled();
                            if(blueData[2].equals("1")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]), Double.valueOf(blueData[1]) + 50.0 - (objH/2));
                                currentObject.setVelocity(objVx, objVy);
                            }else if(blueData[2].equals("0")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0])  - objW, Double.valueOf(blueData[1]) + 50.0 - (objH/2));
                                currentObject.setVelocity(-objVx, objVy);
                            }else if(blueData[2].equals("2")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + 50.0 - (objW/2), Double.valueOf(blueData[1]) - objH -2);
                                currentObject.setVelocity(-objVy, -objVx);
                            }else if(blueData[2].equals("3")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + 50.0 - (objW/2), Double.valueOf(blueData[1])+2);
                                currentObject.setVelocity(objVy, objVx);
                            }
                        }
                    }else if(direction == 1){
                        if((objY > y) && ((objY+objH) < y+h) && (objX + objW > x-4) && (objX < x+4)){
                            currentObject.setRecentlyPortalled();
                            if(blueData[2].equals("1")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]), Double.valueOf(blueData[1]) + 50.0 - (objH/2));
                                currentObject.setVelocity(-objVx, objVy);
                            }else if(blueData[2].equals("0")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0])  - objW, Double.valueOf(blueData[1]) + 50.0 - (objH/2));
                                currentObject.setVelocity(objVx, objVy);
                            }else if(blueData[2].equals("2")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + 50.0 - (objW/2), Double.valueOf(blueData[1]) - objH - 2);
                                currentObject.setVelocity(objVy, (objVx));
                            }else if(blueData[2].equals("3")){
                                currentObject.changeAbsoluteLocation(Double.valueOf(blueData[0]) + 50.0 - (objW/2), Double.valueOf(blueData[1]));
                                currentObject.setVelocity(objVy, -objVx);
                            }
                        }
                    }
                }
            }
        }
    }

    public String getData(){
        return x+","+y+","+direction;
    }
}
