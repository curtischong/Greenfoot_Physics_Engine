import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class buttons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    GreenfootImage next = new GreenfootImage("next.png");
    GreenfootImage back = new GreenfootImage("back.png");
    GreenfootImage exit = new GreenfootImage("exit.png");
    int button;

    public Button(int i){ 
         if (i ==1){
            this.setImage(next);
            //, 865,470);
        }else if (i==2) {
            this.setImage(back);
            //, 125,470);
        }else {
            this.setImage(exit);
        }
    }


    /**
     * Act - do whatever the buttons wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       
    }    
    
    public boolean isClicked(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (Greenfoot.mouseClicked(this)){
            return true;
        }
        return false;
    }
}