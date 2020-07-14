package PlayerCharacteristics;

import city.cs.engine.*;
import game.*;
import Levels.*;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Create a new KeyAdapter interface that provides the player with the
 * operation to control the character body in the game.
 */
public class characterMovement extends KeyAdapter {

    private Game game;
    private static final float movementSpeed = 9.0F; //sets a constant movement speed
    private static final float jumpSpeed = 15.0F;    //sets a constant jumping speed

    //sets all the images that will be used when the character changes its direction
    BodyImage characterRight = new BodyImage("data/characterSprite.png", 2.5F);
    BodyImage characterLeft = new BodyImage("data/characterLeft.png", 2.5F);

    private Walker body;
    private GameLevel currentLevel;

    /**
     * Constructor that initialises the
     * @param body a Walker body that needs to be generated in order to control the character
     * @param level generates the level that enables the player to control the body within the level
     * @param game a game world that needs to be generated in order for the controls to work
     */
    public characterMovement(Walker body, GameLevel level, Game game) {
        this.body = body; //only controls the Character body in the game
        currentLevel = level;
        this.game = game;
    }

    /**
     * Function that will record the actions made by the player when pressing a specific key,
     * providing the controls for the character body
     * @param e event description
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //stores value of the key when pressed by the user
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Q) { //Q = Quit game
            System.exit(0);
        } else if (code == KeyEvent.VK_SPACE) { //SPACE = jump
            Vec2 v = body.getLinearVelocity();
            //only jump if body is not already jumping
            if (Math.abs(v.y) < 0.01f) {
                body.jump(jumpSpeed); //calls variable to set jump speed
            }
        }
        else if (code == KeyEvent.VK_LEFT) { //LEFT arrow = moves character left
            body.startWalking(-movementSpeed);
            body.removeAllImages(); //removes current image of character
            body.addImage(characterLeft); //replaces character image facing in the corresponding direction (left)
        } else if (code == KeyEvent.VK_RIGHT) { //RIGHT arrow = moves character right
            body.startWalking(movementSpeed);
            body.removeAllImages(); //removes current image of character
            body.addImage(characterRight); //replaces character image facing in the corresponding direction (right)
        } else if (code == KeyEvent.VK_Z) { //Z = Dash left
            body.startWalking(-movementSpeed*3); //increases speed of character by 3 times its set value
            body.removeAllImages(); //removes current image of character
            body.addImage(characterLeft); //replaces image facing in the corresponding direction (left)
        } else if (code == KeyEvent.VK_X) { //X = Dash right
            body.startWalking(movementSpeed*3); //increases speed of character by 3 times its set value
            body.removeAllImages(); //removes current image of character
            body.addImage(characterRight); //replaces image facing in the corresponding direction (right)
        }
    }

    /**
     * Function that registers when the action of keys being released during the course
     * of the game, stopping the player's movement in the view upon release.
     * @param e event description
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 37) {
            this.body.stopWalking();
        } else if (code == 39) {
            this.body.stopWalking();
        } else if (code == 90) {
            this.body.stopWalking();
        } else if (code == 88) {
            this.body.stopWalking();
        }
    }

    /**
     * Function that will set the current player body in the game view whilst being used to
     * set the player bodies when loading/transitioning levels to provide full control
     * @param body body variable initialised as the Character
     */
    public void setBody(Walker body) {
        this.body = body;
    }

    /**
     * Function to determine the level the game is current playing in
     * @return integer value from the GameLevel class
     */
    public GameLevel getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Mutator method used in conjunction with the loading method to parse
     * the level number into the world
     * @param level assigned to the currentLevel variable
     */
    public void createWorld(GameLevel level) {
        this.currentLevel = level;
    }

}
