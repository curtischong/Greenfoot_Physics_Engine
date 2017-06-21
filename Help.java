import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Help here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Help extends Actor
{
    GreenfootImage ins0 = new GreenfootImage("ins0.png");
    GreenfootImage ins1 = new GreenfootImage("ins1.png");
    GreenfootImage ins2 = new GreenfootImage("ins2.png");
    GreenfootImage ins3 = new GreenfootImage("ins3.png");
    GreenfootImage ins4 = new GreenfootImage("ins4.png");
    GreenfootImage ins5 = new GreenfootImage("ins5.png");
    Button next = new Button(1);
    Button back = new Button(2);
    Button exit = new Button(3);

    int currentIns = 0;
    /**
     * Act - do whatever the Help wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (currentIns==0){
            this.setImage(ins0);
            getWorld().addObject(next,865,425);
            getWorld().addObject(exit,865,85);
            if(Greenfoot.mouseClicked(next)){
                

                getWorld().removeObject(next);
           
                currentIns = 1;
                getWorld().removeObject(exit);

            }
        }else if (currentIns==1){
            this.setImage(ins1);
            getWorld().addObject(next,865,425);
            getWorld().addObject(back, 135, 425);
            getWorld().addObject(exit,865,85);
            if(Greenfoot.mouseClicked(back)){

                getWorld().removeObject(next);
                getWorld().removeObject(back);

                currentIns = 0;
                getWorld().removeObject(exit);

            }
            if(Greenfoot.mouseClicked(next)){

                getWorld().removeObject(next);
                getWorld().removeObject(back);
                currentIns = 2;
                getWorld().removeObject(exit);

            }
        } else if (currentIns==2){
            this.setImage(ins2);
            getWorld().addObject(exit,865,85);
            getWorld().addObject(next,865,425);
            getWorld().addObject(back, 135, 425);

            if(Greenfoot.mouseClicked(back)){

                getWorld().removeObject(next);
                getWorld().removeObject(back);

                currentIns = 1;
                getWorld().removeObject(exit);

            }
            if(Greenfoot.mouseClicked(next)){

                getWorld().removeObject(next);
                getWorld().removeObject(back);

                currentIns = 3;
                getWorld().removeObject(exit);

            }
        }else if (currentIns==3){
            this.setImage(ins3);
            getWorld().addObject(exit,865,85);
            getWorld().addObject(next,865,425);
            getWorld().addObject(back, 135, 425);

            if(Greenfoot.mouseClicked(back)){

                getWorld().removeObject(next);
                getWorld().removeObject(back);

                currentIns = 2;
                getWorld().removeObject(exit);

            }
            if(Greenfoot.mouseClicked(next)){
                getWorld().removeObject(next);
                getWorld().removeObject(back);

                currentIns = 4;
                getWorld().removeObject(exit);

            }

        }else if (currentIns==4){
            this.setImage(ins4);
            getWorld().addObject(exit,865,85);
            getWorld().addObject(next,865,425);
            getWorld().addObject(back, 135, 425);
            if(Greenfoot.mouseClicked(back)){

                getWorld().removeObject(next);
                getWorld().removeObject(back);

                currentIns = 3;
                getWorld().removeObject(exit);

            }
            if(Greenfoot.mouseClicked(next)){
                getWorld().removeObject(next);
                getWorld().removeObject(back);

                currentIns = 5;
                getWorld().removeObject(exit);

            }
            //this.getWorld().getBackground().drawImage(back, 125,470);
        } else if (currentIns==5){
            this.setImage(ins5);
            getWorld().addObject(exit,865,85);
            getWorld().addObject(back, 135, 425);
            if(Greenfoot.mouseClicked(back)){

                getWorld().removeObject(next);
                getWorld().removeObject(back);

                currentIns = 4;
                getWorld().removeObject(exit);

            }
            //this.getWorld().getBackground().drawImage(back, 125,470);
        } 

        if(Greenfoot.mouseClicked(exit)){
            currentIns = 1;
            getWorld().removeObject(next);
            getWorld().removeObject(back);
            getWorld().removeObject(exit);
            getWorld().removeObject(this);
        }
       
    }
}
