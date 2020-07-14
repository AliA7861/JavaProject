package Levels;

import EnemyBodies.IceDemon;
import BodyCollisions.characterCollision;
import BodyCollisions.iceDemonCollide;
import PlayerCharacteristics.Heart;
import PlayerCharacteristics.Key;
import PlayerCharacteristics.PowerUp;
import StaticBodies.IcePlatform;
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
 * Level 2 of the game extending GameLevel
 */
public class Level2 extends GameLevel {

    private static final int numKeys = 29; //Initialise variable to store higher collection of keys than previous level
    private static final BodyImage icePlatform = new BodyImage("data/ice-platform.png", 4.5f); //Initialise variable storing image for platforms
    private PowerUp star;   //Powerups initialised
    private Heart heart;    //Health boosters initialised
    private IceDemon iceDemon;  //Initialise IceDemon enemies into current level
    private Image background2; //Initialise variable storing new background image for level2

    private Body platform1, platform2, platform3, platform4, platform5, platform6,
    platform7, platform8, platform9, platform10, platform11, platform12, platform13;

    private Platform platform;
    private static SoundClip backgroundSound2;

    /** Populate the world */
    @Override
    public void populate(Game game) {
        super.populate(game);
        this.background2 = new ImageIcon("data/ice-background.jpg").getImage(); //Accessor method returns new background image to replace previous background

        /** Vertical arena created - boundary walls set accordingly */

        //creating ground platform using BoxShape
        Body groundFloor = new Platform(this, 31, 0.4f);
        groundFloor.setPosition(new Vec2(0, -25f));
        groundFloor.setFillColor(Color.black);

        //Ceiling shape created to contain bodies within specific area
        Body ceilingWall = new Platform(this, 31, 0.4f);
        ceilingWall.setPosition(new Vec2(0,51f));
        ceilingWall.setFillColor(Color.black);

        //Right wall is a StaticBody allowing bodies to interact with it
        platform1 = new Platform(this, 1, 38f);
        platform1.setPosition(new Vec2(30, 12.6f));
        platform1.setFillColor(Color.black);

        //Left wall is a StaticBody allowing bodies to interact with it
        platform2 = new Platform(this, 1, 38f);
        platform2.setPosition(new Vec2(-30, 12.6f));
        platform2.setFillColor(Color.black);

         //horizontal platforms

        //Create Platform3
        platform3 = new IcePlatform(this, 4, 1f);
        platform3.setPosition(new Vec2(5, 6f));

        //Create Platform4
        platform4 = new IcePlatform(this, 4, 1f);
        platform4.setPosition(new Vec2(-24, 8f));

        //Create Platform5
        platform5 = new IcePlatform(this, 4, 1f);
        platform5.setPosition(new Vec2(0, -8f));

        //Create Platform6
        platform6 = new IcePlatform(this, 4, 1f);
        platform6.setPosition(new Vec2(7, 17f));

        //Create Platform7
        platform7 = new IcePlatform(this, 4, 1f);
        platform7.setPosition(new Vec2(24, -15f));

        //Create Platform8
        platform8 =new IcePlatform(this, 4, 1f);
        platform8.setPosition(new Vec2(24, 44f));

        //Create Platform9
        platform9 = new IcePlatform(this, 4, 1f);
        platform9.setPosition(new Vec2(-15, 26f));

        //Create Platform10
        platform10 = new IcePlatform(this, 4, 1f);
        platform10.setPosition(new Vec2(9, 33f));

        //Creates Platform11
        platform11 = new IcePlatform(this, 4, 1f);
        platform11.setPosition(new Vec2(-8, 44f));

        //Create Platform12
        platform12 = new IcePlatform(this, 4, 1f);
        platform12.setPosition(new Vec2(-12, -20f));

        //Create Platform13
        platform13 = new IcePlatform(this, 4, 1f);
        platform13.setPosition(new Vec2(23, -4f));

        //create keys within the world
        for(int i = -4; i < 15; i++) {  //Creates 10 keys in the map (only 12 will spawn inside contained area)
            Body key = new Key(this); //Initialise every key as a Dynamic body
            key.setPosition(new Vec2((float)(i*3.5-20), 38f)); //x position of every key changes in each iteration
            key.addCollisionListener(new characterCollision(getPlayer()));   //Collision listener added so that upon collision, the characterCollision class is called with the Character to increment keys collected
        }

        //Spawn IceDemon Enemies into Level2

        iceDemon = new IceDemon(this, -15); //new IceDemon created with a higher speed than previous enemy
        iceDemon.setPosition(new Vec2(3, 0f)); //sets IceDemon body in the current level
        this.iceDemon.addCollisionListener(new characterCollision(this.getPlayer())); //Adds a collision listener so that upon collision, it follows the characterCollision class with the Character connected to it to deduct two life from the Character
        iceDemon.startWalking(iceDemon.getSpeed()); //Returns value of current speed in accessor method getSpeed() to move the iceDemon in the world
        this.iceDemon.addCollisionListener(new iceDemonCollide(platform, this.iceDemon)); //adds a collision listener so that the iceDemon

        iceDemon = new IceDemon(this, -15);//new IceDemon created with a higher speed than previous enemy
        iceDemon.setPosition(new Vec2(-8, 22f));//sets IceDemon body in the current level
        this.iceDemon.addCollisionListener(new characterCollision(this.getPlayer())); //Adds a collision listener so that upon collision, it follows the characterCollision class with the Character connected to it to deduct two life from the Character
        iceDemon.startWalking(iceDemon.getSpeed()); //Returns value of current speed in accessor method getSpeed() to move the iceDemon in the world
        this.iceDemon.addCollisionListener(new iceDemonCollide(platform, this.iceDemon)); //adds a collision listener so that the iceDemon

        iceDemon = new IceDemon(this, -15);
        iceDemon.setPosition(new Vec2(23, 38f));
        this.iceDemon.addCollisionListener(new characterCollision(this.getPlayer())); //Adds a collision listener so that upon collision, it follows the characterCollision class with the Character connected to it to deduct two life from the Character
        iceDemon.startWalking(iceDemon.getSpeed()); //Returns value of current speed in accessor method getSpeed() to move the iceDemon in the world
        this.iceDemon.addCollisionListener(new iceDemonCollide(platform, this.iceDemon));


        //create hearts around the game to boost character life count
        //Heart spawned as DynamicBody
        heart = new Heart(this);
        heart.setPosition(new Vec2(-23, -3f)); //position set in game
        this.heart.addCollisionListener(new characterCollision(getPlayer())); //Collision listener to call characterCollision class with the Character to increase the number of lives left

        heart = new Heart(this);
        heart.setPosition(new Vec2(-23, 42f)); //position set in game
        this.heart.addCollisionListener(new characterCollision(getPlayer())); //Collision listener to call characterCollision class with the Character to increase the number of lives left

        //Powerup spawned as a Walker body (Dynamic body that stays upright)
        star = new PowerUp(this);
        star.setPosition(new Vec2(-9, -12f));  //position set
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
        return new Vec2(27, 48f);
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
        if (background2 == null) {
            background2 = new ImageIcon("data/ice-background.jpg").getImage();
        }
        return this.background2;
    }

    /**
     * <p>Abstract function that will retrieve the integer representing the level number the user is in</p>
     * @return integer value of 2
     */
    @Override
    public int getLevelNumber() {
        return 2;
    }

    /**
     * <p>Abstract function that will get the background sound associated with the level the player is in</p>
     * @return soundclip stored in backgroundSound2
     */
    @Override
    public SoundClip getBackgroundSound() {
        try {
            backgroundSound2 = new SoundClip("data/background-iceLevel.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
        return backgroundSound2;
    }

    /**
     * Stops the current audio file playing in level 2 to transition the the next
     * audio file when moving onto the next level
     */
    @Override
    public void stopMusic() {
        backgroundSound2.stop();
    }
}
