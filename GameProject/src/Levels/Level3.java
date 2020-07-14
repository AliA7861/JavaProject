package Levels;

import EnemyBodies.AirEnemy;
import BodyCollisions.AirEnemyCollision;
import BodyCollisions.characterCollision;
import PlayerCharacteristics.Heart;
import PlayerCharacteristics.Key;
import PlayerCharacteristics.PowerUp;
import StaticBodies.CloudPlatform;
import StaticBodies.Platform;
import city.cs.engine.*;
import game.Game;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Level 3 of the game extending GameLevel
 */
public class Level3 extends GameLevel {

    private static final int numKeys = 42; //Initialise variable to set total collected keys higher than previous levels
    private static final BodyImage cloudPlatform = new BodyImage("data/cloud-platform.png", 8f); //Initialise variable storing image for platforms
    private Image background3; //Initialise new variables storing background image for level3
    private PowerUp star;   //Powerups initialised
    private Heart heart;    //Health boosters initialised
    private AirEnemy airEnemy; //Initialise AirEnemy enemies into current level

    private Body platform1, platform2, platform3, platform4, platform5, platform6, platform7, platform8,
    platform9, platform10;

    private Platform platform;
    private static SoundClip backgroundSound3;

    /** Populate the world */
    @Override
    public void populate(Game game) {
        super.populate(game);
        this.background3 = new ImageIcon("data/air-background.jpg").getImage();//Accessor method returns new background image to replace previous background

        //creating ground platform using BoxShape
        Body groundFloor = new Platform(this, 46, 0.4f);
        groundFloor.setPosition(new Vec2(0, -17f));
        groundFloor.setFillColor(Color.black);

        //Ceiling shape created to contain bodies within specific area
        Body ceilingWall = new Platform(this, 46, 0.4f);
        ceilingWall.setPosition(new Vec2(0,23f));
        ceilingWall.setFillColor(Color.black);

        //Right wall is a StaticBody allowing bodies to interact with it
        platform1 = new Platform(this, 1, 20f);
        platform1.setPosition(new Vec2(45, 2.6f));
        platform1.setFillColor(Color.black);

        //Left wall is a StaticBody allowing bodies to interact with it
        platform2 = new Platform(this, 1, 20f);
        platform2.setPosition(new Vec2(-45, 2.6f));
        platform2.setFillColor(Color.black);


       //horizontal platforms

        //Create Platform3
        platform3 = new CloudPlatform(this, 4, 0.75f);
        platform3.setPosition(new Vec2(-32, -5f));

        //Create Platform4
        platform4 = new CloudPlatform(this, 4, 0.75f);
        platform4.setPosition(new Vec2(-24, 8f));

        //Create Platform5
        platform5 = new CloudPlatform(this, 4, 0.75f);
        platform5.setPosition(new Vec2(0, -8f));

        //Create Platform6
        platform6 = new CloudPlatform(this, 4, 0.75f);
        platform6.setPosition(new Vec2(7, 10f));

        //Create Platform7
        platform7 = new CloudPlatform(this, 4, 0.75f);
        platform7.setPosition(new Vec2(15, -12f));

        //Create Platform8
        platform8 = new CloudPlatform(this, 4, 0.75f);
        platform8.setPosition(new Vec2(29, 11f));

        //Create Platform9
        platform9 = new CloudPlatform(this, 4, 0.75f);
        platform9.setPosition(new Vec2(35, -6f));

        //Create Platform10
        platform10 = new CloudPlatform(this, 4, 0.75f);
        platform10.setPosition(new Vec2(-8, 0f));

        //create keys within the world
        for(int i = 2; i < 15; i++) {  //Creates 10 keys in the map (only 12 will spawn inside contained area)
            Body key = new Key(this); //Initialise every key as a Dynamic body
            key.setPosition(new Vec2((float)(i*6.5-50), 15.0F)); //x position of every key changes in each iteration
            key.addCollisionListener(new characterCollision(getPlayer()));   //Collision listener added so that upon collision, the characterCollision class is called with the Character to increment keys collected
        }

        //Spawning AirEnemy bodies into the level at different positions
        //Enemies speed increased to 20

        airEnemy = new AirEnemy(this, -20); //new AirEnemy created
        airEnemy.setPosition(new Vec2(3, -14f));  //set position of airEnemy body
        this.airEnemy.addCollisionListener(new characterCollision(this.getPlayer())); //Adds a collision listener so that upon collision, it follows the characterCollision class with the Character connected to it to deduct three life from the Character
        this.airEnemy.addCollisionListener(new AirEnemyCollision(platform, this.airEnemy));

        airEnemy = new AirEnemy(this, -20);
        airEnemy.setPosition(new Vec2(20, 16f));
        this.airEnemy.addCollisionListener(new characterCollision(this.getPlayer())); //Adds a collision listener so that upon collision, it follows the characterCollision class with the Character connected to it to deduct three life from the Character
        this.airEnemy.addCollisionListener(new AirEnemyCollision(platform, this.airEnemy));

        airEnemy = new AirEnemy(this, -20);
        airEnemy.setPosition(new Vec2(32, 5f));
        this.airEnemy.addCollisionListener(new characterCollision(this.getPlayer())); //Adds a collision listener so that upon collision, it follows the characterCollision class with the Character connected to it to deduct three life from the Character
        this.airEnemy.addCollisionListener(new AirEnemyCollision(platform, this.airEnemy));


        //create hearts around the game to boost character life count
        //Heart spawned as DynamicBody
        heart = new Heart(this);
        heart.setPosition(new Vec2(5, 10f)); //position set in game
        this.heart.addCollisionListener(new characterCollision(getPlayer())); //Collision listener to call characterCollision class with the Character to increase the number of lives left

        heart = new Heart(this);
        heart.setPosition(new Vec2(35, -9f)); //position set in game
        this.heart.addCollisionListener(new characterCollision(getPlayer())); //Collision listener to call characterCollision class with the Character to increase the number of lives left

        heart = new Heart(this);
        heart.setPosition(new Vec2(-28, 10f)); //position set in game
        this.heart.addCollisionListener(new characterCollision(getPlayer())); //Collision listener to call characterCollision class with the Character to increase the number of lives left

        //Powerup spawned as a Walker body (Dynamic body that stays upright)
        star = new PowerUp(this);
        star.setPosition(new Vec2(-23, -2f));  //position set
        this.star.addCollisionListener(new characterCollision(getPlayer())); //Collision listener added to call characterCollision class with the Character to increase the number of powerups they have
    }

    /**
     * <p>Override abstract function in superclass of the starting position of the character body</p>
     * @return vector with given components i.e. the x and y coordinate of the player body
     */
    @Override
    public Vec2 startPosition() {
        return new Vec2(3, -5f);
    }

    /**
     * <p>Override abstract function in superclass to set the portal position</p>
     * @return vector with given components i.e. the x and y coordinate of the portal body
     */
    @Override
    public Vec2 portalPosition() {
        return new Vec2(-42, 20f);
    }

    /**
     * <p>Override abstract function in superclass to determine if the player's key count matches the integer value in numKeys</p>
     * @return Will return true if the player has collected the specified number of keys in the current level
     */
    @Override
    public boolean isCompleted() {
        return getPlayer().getKeyCount() >= numKeys;
    }

    /**
     * <p>Override abstract function that will pass the image for the background specific to the level number. If the value
     * passed is null, it will re-assign the ImageIcon of the background variable</p>
     * @return image value
     */
    @Override
    public Image getBackgroundImage() {
        if (background3 == null) {
            background3 = new ImageIcon("data/air-background.jpg").getImage();
        }
        return this.background3;
    }

    /**
     * <p>Abstract function that will retrieve the integer representing the level number the user is in</p>
     * @return integer value of 3
     */
    @Override
    public int getLevelNumber() {
        return 3;
    }

    /**
     * <p>Abstract function that will get the background sound associated with the level the player is in</p>
     * @return soundclip stored in backgroundSound3
     */
    @Override
    public SoundClip getBackgroundSound() {
        try {
            backgroundSound3 = new SoundClip("data/background-airLevel.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
        return backgroundSound3;
    }

    /**
     * Stops the current audio file playing in level 3 to transition the the next
     * audio file when moving onto the next level
     */
    @Override
    public void stopMusic() {
        backgroundSound3.stop();
    }

}
