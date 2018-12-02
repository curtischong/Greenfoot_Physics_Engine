import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class will display the title screen, along with buttons that direct the
 * player to the instructions, story mode, and level building mode.
 * 
 * @author Jennifer Ye
 * @version December 2016
 */
public class IntroScreen extends World
{
    IntroButton storyMode = new IntroButton(0);
    IntroButton builderMode = new IntroButton(1);
    IntroButton help = new IntroButton(2);
    Help ins;
    Main main = new Main();
    Editor edit = new Editor();
      

    /**
     * Constructor for objects of class IntroScreen.
     * 
     */
    public IntroScreen()
    {            
        super(1000, 600, 1,false);   
        addObject(storyMode, 740,500);
        addObject(builderMode, 250,500);
        ins = new Help();
        addObject(help, 50,50);       
    }

    public void act(){
        MouseInfo mouse = Greenfoot.getMouseInfo();  
        
        if (Greenfoot.mouseClicked(storyMode)){
            Greenfoot.setWorld(main);
        } else if (Greenfoot.mouseClicked(builderMode)){
            Greenfoot.setWorld(edit);         
        }else if (Greenfoot.mouseClicked(help)){
            addObject(ins,500,250);
           
            //Greenfoot.delay(100);
            //removeObject(ins);
        } 
        
       
    }
    
    

}
