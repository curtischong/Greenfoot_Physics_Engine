import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class sets the image for the buttons in the introduction screen.
 * 
 * @author Jennifer Ye
 * @version December 2016
 */
public class IntroButton extends Actor
{
    public IntroButton(int i){
        if(i==0){
            setImage("StoryModeButton.png");    
        } else if (i==1){
            setImage("LevelBuilderButton.png");
        } else if (i==2){
            setImage("HelpButton.png");
        }
        
    }
    
    /**
     * Act - do whatever the IntroButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
