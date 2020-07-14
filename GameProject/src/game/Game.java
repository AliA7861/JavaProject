package game;

import GUIdisplay.MenuPanel;
import GraphicsInterface.GameView;
import GraphicsInterface.Track;
import Levels.*;
import Player.Character;
import PlayerCharacteristics.characterMovement;
import SaveLoadGame.GameSaver;
import SaveLoadGame.HighScoreWriter;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

/**
 * Class operating the main functionality of the game in the user's view enabling features such as menu controls, animation/sound
 * transitions between levels, transferring of player attributes and game level generation.
 */

public class Game {

    //Declaring global functions
    private GameLevel world; //create world where bodies interact and move
    private GameView view; //graphical display of the world (a specialised JPanel)
    private int level; //initialising a variable representing the level number
    private characterMovement move; //initialise character collision class into the main game
    private final JFrame frame; //initiliase a new JFrame to display the game and its contents

    /**
     * Initialising a new game
     */
    public Game() {

        //create a world
        level = 1;
        world = new Level1();
        world.populate(this);
        world.getBackgroundSound().loop(); //background music is set to play continuously

        //make a view
        view = new GameView(world, world.getPlayer(), 1200, 600);
        //zoom scale to make game closer to user screen/clearer images
        view.setZoom(15);

        //display view in a frame
        frame = new JFrame("Elemental Attack!");

        //display menu panel in the frame
        MenuPanel buttons = new MenuPanel(this);
        frame.add(buttons.getMenuPanel(), BorderLayout.NORTH); //position menu controls at the top of the game

        //quits the running application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);

        //displays the world in the application window
        frame.add(view);
        //prevents game window from being resized
        frame.setResizable(false);
        //sizes the game window to fit the world view
        frame.pack();
        //set the game window in centre of the user's screen
        frame.setLocationRelativeTo(null);
        //make the window visible to the user
        frame.setVisible(true);
        //get keyboard focus
        frame.requestFocus();

        world.addStepListener(new Track(view, world.getPlayer()));        //Tracking effect

        //initiates KeyListener to provide character movement
        move = new characterMovement(world.getPlayer(), world, this);
        frame.addKeyListener(move);

        //Debug Viewer (uncomment if necessary)
        //JFrame debugView = new DebugViewer(world, 1200, 600);

        //start!
        world.start();
    }

    /**
     * <p>Returns the character body (i.e. main player) by declaring it from the
     * GameLevel class.</p>
     * @return the existing character body in the game
     */
    public Character getPlayer() {
        return world.getPlayer(); //return the current player in the game
    }

    /**
     * <p>Returns a boolean value to determine if the player has met the objective of completing the level
     * they are in correctly.</p>
     * @return Will return true if the player has met the objective within the level
     */
    public boolean isCurrentLevelCompleted() {
        return world.isCompleted(); //returns true if the current level is completed
    }

    /**
     * Method to allow bodies on continuing to move in the current level and resuming the timer display.
     */
    public void resumeGame() {
        world.start(); //start game in view
        world.getBackgroundSound().loop(); //music file restarted and playing again
        view.startTime(); //starts the timer display to increment by 1 from the value it was paused at
    }

    /**
     * Method to stop all bodies and movement in the current level at the position they are at
     * and freezing the timer display.
     */
    public void pauseGame() {
        world.stop(); //stops game in view
        world.stopMusic(); //stops background music from playing upon pressing the button
        view.stopTime(); //pauses the timer at the current value it is at
    }

    /**
     * Method to terminate current game session.
     */
    public void quitGame() {
        System.exit(0); //terminates session and removes game view
    }

    /**
     * Enable player to use skip forward button to move onto the next level
     */
    public void goLevelForward() {
        goNextLevel(); //declare method to skip forward levels
    }

    /**
     * Enables player to use skip back button to move back levels
     */
    public void goLevelBack() {
        goPreviousLevel(); //declare method to skip back levels
    }

    /**
     * Enables controlling the restart button to reset the game session state back to the very beginning.
     */
    public void getRestart() {
        //create a world
        level = 0; //initialise level variable to 0
        view.setTime(0); //resets the game timer
        goRestart(); //declare method to determine if level needs restarting
    }

    /**
     * Saves the current game session into separate files, one to record the scores and the other to record the game state
     */
    public void saveGame() {
        //Saving the name and scores when button is pressed
        String name = JOptionPane.showInputDialog("Add name here: "); //input to save a name associated with the score
        HighScoreWriter hsWrite = new HighScoreWriter("data/scores.txt"); //create a new HighScoreWriter to save attributes in the scores text file
        try {
            hsWrite.writeHighScore(name, (world.getPlayer().getKeyCount())); //writes the inputted name and retrieve the player's score to the text file
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Saving the current game state when button is pressed
        GameSaver sw = new GameSaver("data/positions.txt"); //create a new GameSaver to save values in positions text file
        try {
            sw.saveGame(move.getCurrentLevel()); //characterMovement class declared to assign the current level of the game from GameLevel
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *Restart the entire game back to level 1
     */
    public void goRestart() {
        world.stop(); //stop game
        world.stopMusic(); //stop current background music from playing
        Character oldPlayer = world.getPlayer(); //local variable storing attributes of the previous player
        if (level == 0) {
            level++; //increment value by 1
            world = new Level1(); //create level 1 in the world
            world.populate(this);
            //switch keyboard control to new player
            move.setBody(world.getPlayer());
            //set key count to maximum achieved in previous level
            world.getPlayer().setKeyCount(0); //prevents user cheating levels by collecting keys in only one level and passing though the portal
            //pass values over into next world - reset all player attributes
            world.getPlayer().setLiveCount(getPlayer().getLiveCount());
            world.getPlayer().setPowerUp(0); //user can possess one powerup in each level
            //show new world in user view
            view.setWorld(world);
            view.setZoom(15);
            view.setCharacter(world.getPlayer());
            world.addStepListener(new Track(view, world.getPlayer()));        //Tracking effect
            world.start();
        }
        world.getBackgroundSound().loop(); //loop background music in the current level
    }

    /**
     * <p>Returns an index value accessed from the superclass GameLevel to determine
     * the value stored in the accessor methods of associated level subclasses</p>
     * @param levelNo level number within the game
     */
    public void goToLevel(GameLevel levelNo) {
        world.stop();
        world.stopMusic();
        level = levelNo.getLevelNumber();
        world = levelNo;
        world.getBackgroundSound().loop();
        move.setBody(world.getPlayer());
        //parse level number into world
        move.createWorld(world);
        view.setWorld(world);
        view.setZoom(15);
        view.setCharacter(world.getPlayer());
        world.addStepListener(new Track(view, world.getPlayer()));
        world.start();
    }

    /**
     * Function enabling the player to move forward a level
     */
    public void goNextLevel() {
        world.stop(); //stop game
        world.stopMusic();
        Character oldPlayer = world.getPlayer(); //local variable storing attributes of the previous player
        //if user skips level 4 (final level)
        if (level == 4) {
            JOptionPane.showMessageDialog(null, "Game Complete! Keys: " + world.getPlayer().getKeyCount() +
                    "Lives left: " + world.getPlayer().getLiveCount() + "PowerUps left: " + world.getPlayer().getPowerUp());
            System.exit(0); //terminate session and close game view
        }
        //skipping level 1
        else if (level == 1) {
            level++; //increment level by 1
            //get new world
            world = new Level2();
            //populate world with bodies
            world.populate(this);
            //switch keyboard control to new player
            move.setBody(world.getPlayer());
            //parse level number into world
            move.createWorld(world);
            //set key count to maximum achieved in previous level
            world.getPlayer().setKeyCount(12); //prevents user cheating levels by collecting keys in only one level and passing though the portal
            //pass values over into next world
            world.getPlayer().setLiveCount(oldPlayer.getLiveCount());
            world.getPlayer().setPowerUp(0); //user can possess one powerup in each level
            //show new world in user view
            view.setWorld(world);
            view.setZoom(15);
            view.setCharacter(world.getPlayer());
            world.addStepListener(new Track(view, world.getPlayer()));        //Tracking effect
            world.start();
        } else if (level == 2) {
            level++;
            //get new world
            world = new Level3();
            //populate world with bodies
            world.populate(this);
            //switch keyboard control to new player
            move.setBody(world.getPlayer());
            //parse level number into world
            move.createWorld(world);
            //set key count to maximum achieved in previous level
            world.getPlayer().setKeyCount(29); //prevents user cheating levels by collecting keys in only one level and passing though the portal
            //pass values over into next world
            world.getPlayer().setLiveCount(oldPlayer.getLiveCount());
            world.getPlayer().setPowerUp(0); //user can possess one powerup in each level
            //show new world in user view
            view.setWorld(world);
            view.setZoom(15);
            view.setCharacter(world.getPlayer());
            world.addStepListener(new Track(view, world.getPlayer()));        //Tracking effect
            world.start();
        } else if (level == 3) {
            level++;
            //get new world
            world = new Level4();
            //populate world with bodies
            world.populate(this);
            //switch keyboard control to new player
            move.setBody(world.getPlayer());
            //parse level number into world
            move.createWorld(world);
            world.getPlayer().setKeyCount(42);
            //pass values over into next world
            world.getPlayer().setLiveCount(oldPlayer.getLiveCount());
            world.getPlayer().setPowerUp(0); //user can only possess one powerup in a level
            //show new world in user view
            view.setWorld(world);
            view.setZoom(15);
            view.setCharacter(world.getPlayer());
            world.addStepListener(new Track(view, world.getPlayer()));        //Tracking effect
            world.start();
        }
        world.getBackgroundSound().loop();
    }

    /**
     * Identifies the current level the game is in to determine which level to skip back to, resetting certain game attributes
     * and state accordingly.
     */
    public void goPreviousLevel() {
        world.stop(); //stop game
        world.stopMusic();
        Character oldPlayer = world.getPlayer(); //local variable storing attributes of the previous player
        if (level == 1) {
            System.exit(0); //close application down if user skips back from level1
        } else if (level == 2) {
            --level; //decrement level
            //get new world
            world = new Level1();
            //populate world with bodies
            world.populate(this);
            //switch keyboard control to new player
            move.setBody(world.getPlayer());
            //parse level number into world
            move.createWorld(world);
            world.getPlayer().setKeyCount(0); //set key count back to initial count
            //instruction to pass values over
            world.getPlayer().setLiveCount(oldPlayer.getLiveCount());
            world.getPlayer().setPowerUp(0); //user can possess only one powerup in each level
            //show new world in user view
            view.setWorld(world);
            view.setZoom(15);
            view.setCharacter(world.getPlayer());
            world.addStepListener(new Track(view, world.getPlayer()));        //Tracking effect
            world.start();
        } else if (level == 3) {
            --level; //decrement level
            //get new world
            world = new Level2();
            //populate world with bodies
            world.populate(this);
            //switch keyboard control to new player
            move.setBody(world.getPlayer());
            //parse level number into world
            move.createWorld(world);
            world.getPlayer().setKeyCount(12); //set key count to maximum keys in previous arena as initial if skipped
            //instruction to pass values over
            world.getPlayer().setLiveCount(oldPlayer.getLiveCount());
            world.getPlayer().setPowerUp(0); //user can possess only one powerup in each level
            //show new world in user view
            view.setWorld(world);
            view.setZoom(15);
            view.setCharacter(world.getPlayer());
            world.addStepListener(new Track(view, world.getPlayer()));        //Tracking effect
            world.start();
        } else if (level == 4) {
            --level; //decrement level
            //get new world
            world = new Level3();
            //populate world with bodies
            world.populate(this);
            //switch keyboard control to new player
            move.setBody(world.getPlayer());
            //parse level number into world
            move.createWorld(world);
            world.getPlayer().setKeyCount(29); //set key count to total collected in each level combined
            //instruction to pass values over
            world.getPlayer().setLiveCount(oldPlayer.getLiveCount());
            world.getPlayer().setPowerUp(0); //user can possess only one powerup in each level
            //show new world in user view
            view.setWorld(world);
            view.setZoom(15);
            view.setCharacter(world.getPlayer());
            world.addStepListener(new Track(view, world.getPlayer()));        //Tracking effect
            world.start();
        }
        world.getBackgroundSound().loop();
    }

    /**
     * Returns the JFrame so that it can be utilised outside of the main game class
     * @return returns the frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     *<p>Create and run a new game</P>
     * @param args supply command line arguments as an array of Strings
     * @throws IOException explicitly inform compiler that the program may throw an exception as a result of an Input/Output operation
     */
    public static void main (String args[]) throws IOException {
        new Game(); //Initialise a new game
    }
}
