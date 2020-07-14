package BodyCollisions;

import EnemyBodies.AirEnemy;
import EnemyBodies.Fireball;
import EnemyBodies.IceDemon;
import EnemyBodies.Reaper;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import Player.Character;
import PlayerCharacteristics.Heart;
import PlayerCharacteristics.Key;
import PlayerCharacteristics.PowerUp;

/**
 * Creates a new listener interface to enable the Character body to interact with specific bodies differently, from
 * losing lives when colliding with enemies to destroying bodies that provide the player with an advantage.
 */
public class characterCollision implements CollisionListener {

    //initialise body plane of the main player
    private Character character;

    /**
     * <p>Initialise a new CollisionListener for the Character</p>
     * @param character instance of the main player created upon declaring character class
     */
    public characterCollision(Character character) {
        //Initiate characterCollision class by linking it to the Character
        this.character = character;
    }

    /**
     * <p>Receive and respond to a collision event with an object i.e. the player and other bodies in the world</p>
     * @param e Collision event description
     */
    public void collide(CollisionEvent e) {
        //When the character body collides with the Key body
        if (e.getReportingBody() instanceof Key && e.getOtherBody() == this.character) {
            this.character.incrementKeyCount(); //perform incrementKeyCount() to increase key counter by the number of keys the character collected
            e.getReportingBody().destroy(); //eliminates current body from view upon collision
        }
        //When the character body collides with the Reaper body
        else if (e.getReportingBody() instanceof Reaper && e.getOtherBody() == this.character) {
            this.character.decrementLiveCount(); //perform decrementLiveCount() to decrease life counter every time character collides with the Reaper
        }
        //when the character collides with a IceDemon body in Level 2
        else if (e.getReportingBody() instanceof IceDemon && e.getOtherBody() == this.character) {
            if (this.character.getPowerUp() > 0) {
                this.character.decrementPowerUp(); //performs decrementPowerUp() to decrease powerups the character has left
                e.getReportingBody().destroy(); //eliminate current body from view upon collision i.e. the IceDemon
            }
            else {
                this.character.demonLiveCount(); //otherwise, decrement the player's lives by 2
            }
        }
        //When the character collides with airEnemy in Level 3
        else if (e.getReportingBody() instanceof AirEnemy && e.getOtherBody() == this.character) {
            if (this.character.getPowerUp() > 0) { // if the player's powerup count is greater than 0
                this.character.decrementPowerUp(); //performs decrementPowerup() to decrease the number of powerups character has left upon collisions with the enemy
                e.getReportingBody().destroy(); //eliminates current enemy body from view upon collision i.e. the AirEnemy
            }
            else {
                this.character.airEnemyLiveCount(); //otherwise, decrement the player's lives by 3
            }
        }
        //when the character collides with a fireball in Level 4
        else if (e.getReportingBody() instanceof Fireball && e.getOtherBody() == this.character) {
            this.character.fireballLiveCount(); //decrements the player's lives to equal 0
        }
        //When the character body collides with the Heart body
        else if (e.getReportingBody() instanceof Heart && e.getOtherBody() == this.character) {
            this.character.incrementLiveCount(); //perform incrementLiveCount() to increase life counter upon colliding with the heart
            e.getReportingBody().destroy(); //eliminate current body from view upon collision
        }
        //When the character body collides with the Powerup body
        else if (e.getReportingBody() instanceof PowerUp && e.getOtherBody() == this.character) {
            this.character.incrementPowerUp(); //perform incrementPowerUp() to increase number of powerups by how many powerups collected by the character
            e.getReportingBody().destroy(); //eliminate current body from view upon collision
        }
    }
}
