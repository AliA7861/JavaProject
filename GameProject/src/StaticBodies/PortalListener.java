package StaticBodies;

import Player.Character;
import city.cs.engine.*;
import game.Game;

/**
 * Application of a portal collision listener that will register the collision between the main player and
 * the portal body
 */
public class PortalListener implements CollisionListener {
    private Game game; //initialising game as a variable

    //constructor assigning the game variable to itself

    /**
     * Portal listener that takes the main Game class as the variable for it to receive and respond to specific collisions
     * @param game the game world that the collision interface will be generated in
     */
    public PortalListener(Game game) {
        this.game = game;
    }

    /**
     * Method to create specific behaviours when the player collides with the portal body,
     * verifying the player has completed the level objective and the body is of type Character.
     * @param e event description
     */
    @Override
    public void collide(CollisionEvent e) {
        //declares accessor method to return the current player and stores it locally
        Character player = game.getPlayer();
        //if the current player has completed the level upon colliding with the portal
        if (e.getOtherBody() == player && game.isCurrentLevelCompleted()) {
            System.out.println("Moving on to next level..."); //register successful collision between character and portal
            game.goNextLevel(); //game declares method to progress forward with the game
        }
    }
}
