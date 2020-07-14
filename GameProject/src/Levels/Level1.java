package Levels;

import EnemyBodies.Reaper;
import BodyCollisions.characterCollision;
import BodyCollisions.reaperCollision;
import PlayerCharacteristics.Heart;
import PlayerCharacteristics.Key;
import StaticBodies.LavaPlatform;
import StaticBodies.Platform;
import StaticBodies.StonePlatform;
import city.cs.engine.*;
import game.Game;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Level 1 of the game extending GameLevel
 */
public class Level1 extends GameLevel {

    private static final int numKeys = 12;   //Initialise variable to set number of keys to collected in current level
    private Reaper reaper;          //Initialises a Reaper into the game
    private Image background1;  //Initialise variable to store background image for Level1

    private Heart heart;            //Health Perks initialised

    private Body platform1, platform2, platform3, platform4,
            platform5, platform6, platform7, platform8, platform9, platform10, lavaPlatform1, lavaPlatform2, lavaPlatform3, lavaPlatform4, lavaPlatform5;

    private Platform platform;
    private static SoundClip backgroundSound1;

    /**
     * Populate the world
     * @param game initialise the constructor that is of type Game
     */
    @Override
    public void populate(Game game) {
        super.populate(game);
        this.background1 = new ImageIcon("data/fire-background.png").getImage(); //uses accessor method to return the image stored in background1

        //Initialised StaticBodies using BoxShape to contain bodies in a specific area
        //All bodies positioned accordingly using the Vec2 method

        //creating ground platform using BoxShape
        Body groundFloor = new Platform(this, 39, 0.4f);
        groundFloor.setPosition(new Vec2(0, -17f));
        groundFloor.setFillColor(Color.black);

        //Ceiling shape created to contain bodies within specific area
        Body ceilingWall = new Platform(this, 39, 0.4f);
        ceilingWall.setPosition(new Vec2(0,17f));
        ceilingWall.setFillColor(Color.black);

        //Right wall is a StaticBody allowing bodies to interact with it
        platform9 = new Platform(this, 1, 17.4f);
        platform9.setPosition(new Vec2(40, 0f));
        platform9.setFillColor(Color.black);

        //Left wall is a StaticBody allowing bodies to interact with it
        platform10 = new Platform(this, 1, 17.4f);
        platform10.setPosition(new Vec2(-40, 0f));
        platform10.setFillColor(Color.black);

        //horizontal aerial platforms as StaticBodies- positions set accordingly

        //Create PLatform 1
        platform1 = new StonePlatform(this, 4, 0.75f);
        platform1.setPosition(new Vec2(-25, 3f));

        //Creates Platform 2
        platform2 = new StonePlatform(this, 4, 0.75f);
        platform2.setPosition(new Vec2(-8, -10f));

        //Create Platform 3
        platform3 = new StonePlatform(this, 4, 0.75f);
        platform3.setPosition(new Vec2(8, -4f));

        //Create Platform 4
        platform4 = new StonePlatform(this, 4, 0.75f);
        platform4.setPosition(new Vec2(10, 11f));

        //Create Platform 5
        platform5 = new StonePlatform(this, 4, 0.75f);
        platform5.setPosition(new Vec2(27, 5f));

        //Create Platform 6
        platform6 = new StonePlatform(this, 4, 0.75f);
        platform6.setPosition(new Vec2(-15, 10f));

        //Create Platform 7
        lavaPlatform1 = new LavaPlatform(this, 2, 0.5f);
        lavaPlatform1.setPosition(new Vec2(-7, -2f));

        //Create Platform 8
        lavaPlatform2 = new LavaPlatform(this, 2, 0.5f);
        lavaPlatform2.setPosition(new Vec2(-22, -6f));

        //Create Platform 9
        lavaPlatform3 = new LavaPlatform(this, 2, 0.5f);
        lavaPlatform3.setPosition(new Vec2(23, -8f));

        //Create Platform 10
        lavaPlatform4 = new LavaPlatform(this, 2, 0.5f);
        lavaPlatform4.setPosition(new Vec2(31, -13f));

        //Create Platform 11
        lavaPlatform5 = new LavaPlatform(this, 2, 0.5f);
        lavaPlatform5.setPosition(new Vec2(-34, -12f));

        //Vertical aerial platforms as StaticBodies - create obstacles for the player when moving between platforms
        //Create Platform 12
        platform7 = new Platform(this, 1, 3.5f);
        platform7.setPosition(new Vec2(9, -13f));
        //Wall colour set to black
        platform7.setFillColor(Color.black);

        //Create Platform 13
        platform8 = new Platform(this, 1, 2.8f);
        platform8.setPosition(new Vec2(3, 4f));
        //Wall colour set to black
        platform8.setFillColor(Color.black);

        //create keys within the world
        for(int i = -3; i < 14; i++) {  //Creates 14 keys in the map (only 12 will spawn inside contained area)
            Body key = new Key(this); //Initialise every key as a Dynamic body
            key.setPosition(new Vec2((float)(i*6.5-50), 15.0F)); //x position of every key changes in each iteration
            key.addCollisionListener(new characterCollision(getPlayer()));   //Collision listener added so that upon collision, the characterCollision class is called with the Character to increment keys collected
        }

        //Spawn Health boosters in Level1 with positions set accordingly

        //Heart spawned as DynamicBody - boosts character life count
        heart = new Heart(this);
        heart.setPosition(new Vec2(29, 14f)); //position set in game
        this.heart.addCollisionListener(new characterCollision(getPlayer())); //Collision listener to call characterCollision class with the Character to increase the number of lives left

        //Second heart spawned as a Walker body (Dynamic body that stays upright)
        heart = new Heart(this);
        heart.setPosition(new Vec2(-26, 13f)); //position set
        this.heart.addCollisionListener(new characterCollision(getPlayer())); //Collision listener to call characterCollision class with the Character to increase the number of lives left

        //Spawn enemy reapers into Level1

        //Implement reaper bodies into game setting its initial speed & position within the world
        reaper = new Reaper(this, -10); //sets Reaper's speed in a specific direction
        reaper.setPosition(new Vec2(-23, -13f));
        this.reaper.addCollisionListener(new characterCollision(this.getPlayer()));   //Adds a collision listener so that upon collision, it follows the characterCollision class with the Character connected to it to deduct a life from the Character
        reaper.startWalking(reaper.getSpeed()); //Returns value of current speed in accessor method getSpeed() to move the reaper in the world
        this.reaper.addCollisionListener(new reaperCollision(platform, this.reaper));

        //Implement a second reaper into the world - initial speed and position set
        reaper = new Reaper(this, 10);
        reaper.setPosition(new Vec2(13, 2f));
        this.reaper.addCollisionListener(new characterCollision(this.getPlayer())); //Adds a collision listener so that upon collision, it follows the characterCollision class with the Character attached to it to deduct a life from the Character
        reaper.startWalking(reaper.getSpeed()); //Returns value of current speed in accessor method getSpeed() to move the reaper in the world
        this.reaper.addCollisionListener(new reaperCollision(platform, this.reaper));

        //Implement a third reaper into the world - initial speed and position set
        reaper = new Reaper(this, 10);
        reaper.setPosition(new Vec2(-24, -2f));
        this.reaper.addCollisionListener(new characterCollision(this.getPlayer())); //Adds a collision listener so that upon collision, it follows the characterCollision class with the Character connected to it to deduct a life from the Character
        reaper.startWalking(reaper.getSpeed());//Returns value of current speed in accessor method getSpeed() to move the reaper in the world
        this.reaper.addCollisionListener(new reaperCollision(platform, this.reaper));

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
        return new Vec2(18, -3f);
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
        if (background1 == null) {
            background1 = new ImageIcon("data/fire-background.png").getImage();
        }
        return this.background1; }

    /**
     * <p>Abstract function that will retrieve the integer representing the level number the user is in</p>
     * @return integer value of 1
     */
    @Override
    public int getLevelNumber() {
        return 1;
    }

    /**
     * <p>Abstract function that will get the background sound associated with the level the player is in</p>
     * @return soundclip stored in backgroundSound1
     */
    @Override
    public SoundClip getBackgroundSound() {
        try {
            backgroundSound1 = new SoundClip("data/backgroundMusic.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
        return backgroundSound1;
    }

    /**
     * Stops the current audio file playing in level 1 to transition the the next
     * audio file when moving onto the next level
     */
    @Override
    public void stopMusic() {
        backgroundSound1.stop();
    }
}
