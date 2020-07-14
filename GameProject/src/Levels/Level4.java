package Levels;

import EnemyBodies.Fireball;
import BodyCollisions.characterCollision;
import BodyCollisions.fireballCollide;
import StaticBodies.Platform;
import StaticBodies.RockPlatform;
import city.cs.engine.*;
import game.Game;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Level 4 of the game extending GameLevel
 */
public class Level4 extends GameLevel implements ActionListener {

    private static final int numKeys = 42;  //Initialise variable to set total collected keys higher than previous levels
    private Image background4; //Initialise new variables storing background image for level4
    private Fireball fireball; //Initialise Fireball bodies into current level

    private Body groundFloor, ceilingWall, platform1, platform2, platform3, platform4, platform5, platform6,
            platform7, platform8, platform9, platform10, platform11, platform12, platform13;

    private Platform platform;
    private static SoundClip backgroundSound4;

    /**
     * Populate the world
     */
    @Override
    public void populate(Game game) {
        super.populate(game);
        this.background4 = new ImageIcon("data/earth-background.jpg").getImage(); //Accessor method returns new background image to replace previous background

        //creating ground platform using BoxShape
        groundFloor = new Platform(this, 31, 0.4f);
        groundFloor.setPosition(new Vec2(0, -25f));
        groundFloor.setFillColor(Color.black);

        //Ceiling shape created to contain bodies within specific area
        ceilingWall = new Platform(this, 31, 0.4f);
        ceilingWall.setPosition(new Vec2(0,51f));
        ceilingWall.setFillColor(Color.black);

        //Right wall is a StaticBody allowing bodies to interact with it
        platform12 = new Platform(this, 1, 38f);
        platform12.setPosition(new Vec2(30, 12.6f));
        platform12.setFillColor(Color.black);

        //Left wall is a StaticBody allowing bodies to interact with it
        platform13 = new Platform(this, 1, 38f);
        platform13.setPosition(new Vec2(-30, 12.6f));
        platform13.setFillColor(Color.black);

        //horizontal platforms

        //Create Platform1
        platform1 = new RockPlatform(this, 6, 0.8f);
        platform1.setPosition(new Vec2(5, 6f));

        //Create Platform2
        platform2 = new RockPlatform(this, 6, 0.8f);
        platform2.setPosition(new Vec2(-24, 8f));

        //Create Platform3
        platform3 = new RockPlatform(this, 6, 0.8f);
        platform3.setPosition(new Vec2(0, -8f));

        //Create Platform4
        platform4 = new RockPlatform(this, 6, 0.8f);
        platform4.setPosition(new Vec2(7, 17f));

        //Create Platform5
        platform5 = new RockPlatform(this, 6, 0.8f);
        platform5.setPosition(new Vec2(24, -15f));

        //Create Platform6
        platform6 = new RockPlatform(this, 6, 0.8f);
        platform6.setPosition(new Vec2(24, 44f));

        //Create Platform7
        platform7 = new RockPlatform(this, 6, 0.8f);
        platform7.setPosition(new Vec2(-15, 26f));

        //Create Platform8
        platform8 = new RockPlatform(this, 6, 0.8f);
        platform8.setPosition(new Vec2(9, 33f));

        //Create Platform9
        platform9 = new RockPlatform(this, 6, 0.8f);
        platform9.setPosition(new Vec2(-8, 44f));

        //Create Platform10
        platform10 = new RockPlatform(this, 6, 0.8f);
        platform10.setPosition(new Vec2(-12, -20f));

        //Create Platform11
        platform11 = new RockPlatform(this, 6, 0.8f);
        platform11.setPosition(new Vec2(23, -4f));

        //create a new timer that spawns a new fireball every 6 seconds
        Timer t = new Timer(6000, this);
        t.start();
    }

    /**
     * <p>Override abstract function in superclass of the starting position of the character body</p>
     * @return vector with given components i.e. the x and y coordinate of the player body
     */
    @Override
    public Vec2 startPosition() {
        return new Vec2(3, -10f);
    }

    /**
     * <p>Override abstract function in superclass to set the portal position</p>
     * @return vector with given components i.e. the x and y coordinate of the portal body
     */
    @Override
    public Vec2 portalPosition() {
        return new Vec2(20, 48f);
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
        if (background4 == null) {
            background4 = new ImageIcon("data/earth-background.jpg").getImage();
        }
        return this.background4;
    }

    /**
     * <p>Abstract function that will retrieve the integer representing the level number the user is in</p>
     * @return integer value of 4
     */
    @Override
    public int getLevelNumber() {
        return 4;
    }

    /**
     * Records the action performed when fireballs are colliding with specific platform bodies in the world,
     * using the actionPerformed() function to spawn a fireball based on a timer
     * @param e action event descriptor
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //create fireballs within the world
        for(int i = 0; i < 1; i++) { //Spawns 2 fireballs into level4
            fireball = new Fireball(this, new Vec2((float)(i*5), i*3+10), 7); //creates a fireball into the level with a set speed, altering its position in each iteration
            this.fireball.addCollisionListener(new characterCollision(this.getPlayer())); //Adds a collision listener so that upon collision, it follows the characterCollision class with the Character connected to it to deduct all lives from the Character
            fireball.setAngularVelocity(fireball.getFireballSpeed()); //declares accessor method in Fireball class to set the angular velocity of each fireball
            this.fireball.addCollisionListener(new fireballCollide(platform, this.fireball)); //add collision listener between the fireball and platform bodies by declaring the fireballCollide class
        }
    }

    /**
     * <p>Abstract function that will get the background sound associated with the level the player is in</p>
     * @return soundclip stored in backgroundSound4
     */
    @Override
    public SoundClip getBackgroundSound() {
        try {
            backgroundSound4 = new SoundClip("data/background-earthLevel.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
        return backgroundSound4;
    }

    /**
     * Stops the current audio file playing in level 4 to transition the the next
     * audio file when moving onto the next level
     */
    @Override
    public void stopMusic() {
        backgroundSound4.stop();
    }
}
