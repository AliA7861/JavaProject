package BodyCollisions;

import EnemyBodies.AirEnemy;
import StaticBodies.CloudPlatform;
import StaticBodies.Platform;
import city.cs.engine.*;

/**
 * Create a new listener interface to enable the AirEnemy creatures to bounce between boundary walls and
 * platforms in the world.
 */

public class AirEnemyCollision implements CollisionListener {

    //Initialise body planes which will interact with each other
    private AirEnemy airEnemy;
    private Platform platform;

    /**
     * <p> Initialise a new CollisionListener involving the AirEnemy and Platform bodies </p>
     * @param platform a platform body
     * @param airEnemy an AirEnemy body
     */
    public AirEnemyCollision(Platform platform, AirEnemy airEnemy) {
        //body planes assigned to itself
        this.platform = platform;
        this.airEnemy = airEnemy;
    }

    /**
     * <p> Receives and responds to collisions where the AirEnemy records the collision event between platforms and
     * inverting their speed to alter the direction of their travel.</p>
     * @param e Collision event description
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Platform) {
            airEnemy.startWalking(-(airEnemy.getSpeed())); //airEnemy starts moving in the opposite direction by returning current speed and inverting it
            airEnemy.setSpeed(-airEnemy.getSpeed()); //modifies the AirEnemy's direction speed using setSpeed()
        }
        //if the airEnemy collides with any of the cloud platforms
        //allows AirEnemy to change direction on its own every time it interacts with a platform body
        else if(e.getOtherBody() instanceof CloudPlatform) {
            airEnemy.startWalking(-(airEnemy.getSpeed())); //airEnemy starts moving in the opposite direction by returning current speed and inverting it
            airEnemy.setSpeed(-airEnemy.getSpeed()); //modifies the AirEnemy's direction speed using setSpeed()
        }
    }
}
