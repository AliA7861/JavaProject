package Levels;

import Player.Character;
import city.cs.engine.*;
import game.Game;
import StaticBodies.Portal;
import StaticBodies.PortalListener;
import org.jbox2d.common.Vec2;

import java.awt.*;

/**
 * <p>Superclass for all levels extending the World that enables motion and interactions to be
 * simulated in the game.</p>
 */
public abstract class GameLevel extends World {

    private Character player; //Initialised variable to add play in the level

    /**
     * Return that gets the current instance of the character body
     * @return variable storing the character body
     */
    public Character getPlayer() {
        return player; //returns player when declared in another class
    }
    /**
     * Function used to set the instance of a character that will be declared when
     * controlling the character bodies as they transition between levels in the game class
     * @param p assigns the character body to the variable
     */
    public void setPlayer(Character p) {
        player = p;
    }

    /**
     * Function that will set the character and portal bodies in the view of the current level
     * within the game
     * @param game initialise the constructor that is of type Game
     */
    public void populate(Game game) {
        player = new Character(this); //create a new player body in game
        player.setPosition(startPosition()); //set position of player by declaring method storing the start position in each level
        Portal portal = new Portal(this); //create a new portal in the level
        portal.setPosition(portalPosition()); //set position of portal in level by declaring method storing the value in each level
        portal.addCollisionListener(new PortalListener(game)); // collision listener to determine if player has total keys need to pass through the portal upon collision
    }

    /**
     * <p>Abstract function that will pass the starting position of the character body specified in each level class</p>
     * @return vector with given components i.e. the x and y coordinate of the player body
     */
    public abstract Vec2 startPosition(); //method determining start position of player

    /**
     * <p>Abstract function that will pass the portal position specified in each level class</p>
     * @return vector with given components i.e. the x and y coordinate of the portal body
     */
    public abstract Vec2 portalPosition(); //method determining the position of the portal

    /**
     * <p>Abstract function that will determine if the level has been completed as a boolean value</p>
     * @return Will return true if the player has collected the specified number of keys in the current level
     */
    public abstract boolean isCompleted(); //returns true if the total key count matches value set in private variable

    /**
     * <p>Abstract function that will pass the image for the background specified in each level class</p>
     * @return image value
     */
    public abstract Image getBackgroundImage(); //returns the background image for each level

    /**
     * <p>Abstract function that will retrieve the integer representing the level number the user is in</p>
     * @return integer value
     */
    public abstract int getLevelNumber();//returns the level number from the level sub-classes

    /**
     * <p>Abstract function that will get the background sound specified in each level class</p>
     * @return soundclip specified in each level
     */
    public abstract SoundClip getBackgroundSound();

    /**
     * Stops the current audio file playing to transition to next audio file as the
     * player passes through each level.
     */
    public abstract void stopMusic();
}
