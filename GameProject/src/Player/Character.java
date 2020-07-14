package Player;

import city.cs.engine.*;
import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.World;
import city.cs.engine.Walker;
import GraphicsInterface.GameView;
import SaveLoadGame.HighScoreWriter;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

/**
 * Create a new Character representing the body the player will have control over,
 * extending a Walker type to remain upright throughout the game.
 */

public class Character extends Walker {

    //Initialise and create a new PolygonShape storing the coordinates representing the character's shape using the PolygonEditor
    private static final Shape shape = new PolygonShape(
            0.695f,-1.135f, -0.71f,-1.07f, -1.005f,0.83f, 0.215f,1.105f, 0.99f,0.62f, 0.85f,-0.72f);

    //Initialise a BodyImage that renders an image to the PolygonShape, calling the file and setting its height
    private static final BodyImage image =
            new BodyImage("data/characterSprite.png", 2.5f);


    //Initialise character attributes
    private int keyCount; //stores the number of keys collected by the character
    private int liveCount; //stores the number of lives the character has
    private int powerUp; //stores the number of powerups the character collected

    private static SoundClip boostSound, keySound, enemyContact;

    private GameView time;

    /**
     * Initialises objects for the main player body, setting their attributes at specific integer values when
     * they first start playing the game
     * @param world the game world that will be generated to enable the character's movement and interactions
     *              with different bodies
     */
    public Character(World world) {
        //attaches shape and image of the character together
        super(world, shape);
        addImage(image);
        keyCount = 0; //sets initial key count to 0 when starting the game
        liveCount = 7; //sets character's live count to 7 when starting
        powerUp = 0; //set to 0 as they need to be collected within the game
    }

    //initialise new sounds based on interactions between certain objects in the game
    static {
        try {
            boostSound = new SoundClip("data/boosterSound.wav");
            keySound = new SoundClip("data/keySound.wav");
            enemyContact = new SoundClip("data/enemyContact.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }


    /**
     * Function that access the number of keys the player collects during the course of the game
     * @return integer value for the number of keys
     */
    public int getKeyCount() {
        return keyCount;
    }

    /**
     * Function that can be declared outside of the class to set the key count
     * to a different integer value
     * @param count variable assigned to the keyCount variable
     */
    public void setKeyCount(int count) { keyCount = count; }

    /**
     * Function that will return the number of lives the user has during the course of the game
     * @return integer value for the number of lives
     */
    public int getLiveCount() {
        return liveCount;
    }


    /**
     * Function that can be declared outside of the class to set the live count
     * to a different integer value
     * @param lives variable assigned to the liveCount variable
     */
    public void setLiveCount(int lives) { liveCount = lives; }

    /**
     * Increments the value in the keyCount variable every time the player collects a key
     */
    public void incrementKeyCount() {
        keyCount++; //increments count by 1
        keySound.play(); //play audio file when the player collects a key
    }

    //Level 1 - reapers deduct 1 life from player
    /**
     * increment the live count every time the player interacts with a heart body
     */
    public void incrementLiveCount() {
        this.liveCount++; //lives increase by 1
        boostSound.play(); //play audio file when collecting a heart
    }

    /**
     * Function to decrement lives every time the player interacts with a reaper body
     */
    public void decrementLiveCount() {
        --this.liveCount; //decrement lives by 1
        enemyContact.play(); //sound played to register player and enemy collision
        //conditional statement checking if lives is less than or equal to 0
        if (liveCount <=0)
        {
            //Message box appears in game window informing user they lost and the number of keys they collected
            JOptionPane.showMessageDialog(null, "All lives lost. GAME OVER! Keys collected = " + keyCount + ".");

            //initialise automatic save if the user loses all lives
            String name = JOptionPane.showInputDialog("Add name here: "); //allow user to enter a name when saving
            HighScoreWriter hsWrite = new HighScoreWriter("data/scores.txt"); //create a highScoreWriter to save attributes to scores text file
            try {
                hsWrite.writeHighScore(name, getKeyCount()); //write the inputted name and return key count
                System.exit(0); //terminate session
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            time.stopTime(); //stop the timer

            //closes game using exit code 0
            System.exit(0);
        }
    }

    //Level 2 - ice demons deduct 2 lives from player
    /**
     * Function to decrement lives every time the player interacts with a IceDemon body
     */
    public void demonLiveCount() {
        liveCount = liveCount - 2; //decrease player life count by 2
        enemyContact.play(); //register player and enemy collision by playing audio file
//        System.out.println("Contact with icedemon. Lives left = " + liveCount);
        if (liveCount <= 0) {
            JOptionPane.showMessageDialog(null, "Game over! All lives lost!");

            //automatic save if the user loses all lives
            String name = JOptionPane.showInputDialog("Add name here: ");
            HighScoreWriter hsWrite = new HighScoreWriter("data/scores.txt");
            try {
                hsWrite.writeHighScore(name, getKeyCount());
                System.exit(0);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.exit(0);
        }
    }

    //Level 3 - airEnemy can deduct 3 lives from player
    /**
     * Function to decrement lives every time the player interacts with a airEnemy
     */
    public void airEnemyLiveCount() {
        liveCount = liveCount - 3; //decrease player life count by 3
        enemyContact.play(); //register player and enemy collision through audio file
//        System.out.println("Contact with airEnemy. Lives left = " + liveCount);
        if (liveCount <= 0) {
            JOptionPane.showMessageDialog(null, "Game over! All lives lost!");

            //automatic save to scores text file if player loses all lives
            String name = JOptionPane.showInputDialog("Add name here: ");
            HighScoreWriter hsWrite = new HighScoreWriter("data/scores.txt");
            try {
                hsWrite.writeHighScore(name, getKeyCount());
                System.exit(0);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    }

    //Level 4 - fireballs deduct all lives from player
    /**
     * Function to decrement lives every time the player interacts with a fireball
     */
    public void fireballLiveCount() {
        this.liveCount = 0; //set player lives to 0
        enemyContact.play(); //register player and enemy collision by playing audio file
//        System.out.println("Contact registered! Lives left = " + liveCount);
        if (liveCount <= 0) {
            JOptionPane.showMessageDialog(null, "Game Over! All lives lost!");

            //automatic save if player loses all lives - saved to scores text file
            String name = JOptionPane.showInputDialog("Add name here: ");
            HighScoreWriter hsWrite = new HighScoreWriter("data/scores.txt");
            try {
                hsWrite.writeHighScore(name, getKeyCount());
                System.exit(0);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.exit(0);
        }
    }

    /**
     * Function to return the number of powerups the player collects in the game
     * @return integer value for the powerup count
     */
    public int getPowerUp() {
        return powerUp;
    }

    /**
     * Function that can be declared outside to set the number of powerups collected as the player progress,
     * in conjunction with being altered when the user skips between levels
     * @param powers integer value that assigns the number of powerups collected
     */
    public void setPowerUp(int powers) { powerUp = powers; }

    /**
     * Function that will increment the number of powerups every time the player interacts
     * with a powerup body in the game
     */
    public void incrementPowerUp() {
        this.powerUp++; //incremented by 1
        boostSound.play(); //register collision between player and powerup by playing an audio file
    }

    /**
     * Function that will decrement the number of powerups the player has every time they
     * collide with an enemy body in the game
     */
    public void decrementPowerUp() {
        --this.powerUp; //decrements by 1
    }
}
