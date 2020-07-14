package GraphicsInterface;

import Levels.GameLevel;
import Player.Character;
import city.cs.engine.UserView;
import city.cs.engine.World;
import java.awt.Image;
import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Creates a view interface to display the game to the user including attributes displays to keep track
 */

public class GameView extends UserView implements ActionListener {

    Character character; //initialise new character into the view

    /**
     * Sets the character body in the existing game view
     * @param character initialise a new character object that the view will focus on
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    //initialised variables storing images of each icon in display
    private Image background, heart, key, powerup, gameTimer;
    private int time = 0; //initial value of timer set at 0
    private Timer gameTime; //Timer variable initialised

    /**
     * Initialises the necessary objects and attributes needed to create the game view
     * @param w game world for which a view will need to be generate in
     * @param character a character body that will be generated within the world
     * @param width integer representing the width of the view created
     * @param height integer representing the height of the view created
     */
    public GameView(World w, Character character, int width, int height) {
        super(w, width, height);
        this.character = character;
        background = new ImageIcon("data/fire-background.png").getImage(); //returns value for background image for initial level
        heart = new ImageIcon("data/heart.gif").getImage(); //returns image for the heart icon
        key = new ImageIcon("data/key.png").getImage(); //returns image for the key icon
        powerup = new ImageIcon("data/star.gif").getImage(); //returns image for the powerup icon
        gameTimer = new ImageIcon("data/timer.png").getImage(); //returns the image representing the timer icon

        //initialise a new timer with a delay of 1 second
        gameTime = new Timer(1000, this);
        gameTime.start();

        //sets current background to be scaled correctly in game view
        this.background = background.getScaledInstance(1200, 600, Image.SCALE_SMOOTH);
        //image icons for the heart, key, powerup and timer scaled smaller to represent counters the player needs to collect in view
        this.heart = heart.getScaledInstance(40, 36, Image.SCALE_SMOOTH);
        this.key = key.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        this.powerup = powerup.getScaledInstance(35, 38, Image.SCALE_SMOOTH);
        this.gameTimer = gameTimer.getScaledInstance(35, 32, Image.SCALE_SMOOTH);
    }

    /**
     * <p>Displays the images that need to be rendered and attached to the view based on the coordinates set</p>
     * @param g variable treated as a Graphics2D object and draw images
     */
    @Override
    protected void paintBackground(Graphics2D g) {
        super.paintBackground(g);
        //draws image associated with each level using the accessor method to return the background image in each level
        g.drawImage(((GameLevel)this.getWorld()).getBackgroundImage(), 0, 0, this);
        //image drawn in user view with positions set accordingly towards left-hand side of game view
        g.drawImage(heart, 25, 50, null);
        g.drawImage(key, 24, 8, null);
        g.drawImage(powerup, 26, 90, null);
        g.drawImage(gameTimer, 24, 150, null);
    }

    /**
     * <p>Demonstrates how strings can be displayed in the game view, containing character attributes
     * generated in the view which can change and be updated.</p>
     * @param g variable treated as a Graphics2D object and draw strings
     */
    @Override
    protected void paintForeground(Graphics2D g) {
        //font of icons set to BOLD - more visbile for the user
        g.setFont(new Font("Rockwell", Font.BOLD, 29));
        //draws a string attached to related icon and returns the total for the keys, lives and powerups to allow users to track their progress in each level
        g.drawString(": " + character.getKeyCount(), 70, 38);
        g.drawString("x" + character.getLiveCount(), 70, 75);
        g.drawString("x" + character.getPowerUp(), 70, 120);
        g.drawString(":" + time, 70, 175);
    }

    /**
     * <p>Returns current value in the time variable upon declaration</p>
     * @return integer representing the current timer value
     */
    public int getTime() {
        return time;
    }

    /**
     * <p>Mutator method to assign the current time value to itself</p>
     * @param time value for the time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Method to halt the timer in the display
     */
    public void stopTime() {
        gameTime.stop();
    }

    /**
     * Method starting the timer value in the display
     */
    public void startTime() {
        gameTime.start();
    }

    /**
     * ActionListener method to update the timer display, incrementing by 1 and
     * updating the display by 1 second
     * @param e Action event description
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        time++; //increment time value by 1
        System.out.println(time); //indication timer is being updated in terminal by declaring the 'time' variable
    }
}
