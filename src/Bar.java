import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
/**
 * Write a description of class BuildBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bar extends Actor
{
    //create the scoreboard
    private GreenfootImage scoreBoard;
    //iniitalise the colors of the image and the font
    private Color backgroundColor = new Color (26, 188, 156);
    private Color textColor = Color.WHITE;
    private Font textFont = new Font ("Arial", Font.BOLD, 18);
    public Bar(){
        //state size of image
        scoreBoard = new GreenfootImage(1000, 40);
        //set font
        scoreBoard.setFont (textFont);
        //set background color
        scoreBoard.setColor (backgroundColor);
        //fill the board up
        scoreBoard.fill();
        //place the image of the scoreboard ontop of the object
        this.setImage(scoreBoard);
    }
}
