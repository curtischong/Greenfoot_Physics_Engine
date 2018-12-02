import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends World
{
    ImageLabel image1;
    ImageLabel image2;
    ImageLabel image3;
    int endScore;
    /**
     * Constructor for objects of class GameOver.
     * 
     */
    public GameOver(int score)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        endScore = score;
        ImageLabel image1 = new ImageLabel("Score: " + Integer.toString(endScore), 400,550, 50);
        ImageLabel image2 = new ImageLabel("Menu", 200,450, 80);
        ImageLabel image3 = new ImageLabel("Restart", 560,450, 80);
        addObject(image1,0,0);
        addObject(image2,0,0);
        addObject(image3,0,0);
    }

    public void act(){

        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse==null)return;
        if(Greenfoot.mouseClicked(image2)){
            IntroScreen m = new IntroScreen();
            Greenfoot.setWorld(m);

        } else if(Greenfoot.mouseClicked(image3)){
            
            Main m= new Main();
            Greenfoot.setWorld(m);

        }
    }
}
