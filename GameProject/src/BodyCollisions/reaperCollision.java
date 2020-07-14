package BodyCollisions;

import EnemyBodies.Reaper;
import StaticBodies.LavaPlatform;
import StaticBodies.Platform;
import StaticBodies.StonePlatform;
import city.cs.engine.*;

/**
 * Create a new listener interface to allow the Reaper to move between set boundaries/platforms.
 */

public class reaperCollision implements CollisionListener {

    //Initialise body planes that will interact with each other upon collisions
    private Platform platform;
    private Reaper reaper;

    /**
     * <p> Initialise a new CollisionListener involving the Reaper and Platform bodies </p>
     * @param platform A platform body
     * @param reaper A Reaper body
     */
    public reaperCollision(Platform platform, Reaper reaper) {
        this.platform = platform;   //assigns the current platform body to itself
        this.reaper = reaper; //assigns the current reaper body to itself
    }

    /**
     * <p> Receives and responds to collisions when the Reaper interacts with specific types of platforms,
     * changing their directional movement every time they collide with the wall.</p>
     * @param e Collision event description
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Platform) {
            reaper.startWalking(-(reaper.getSpeed())); //reaper starts moving in the opposite direction by returning current speed and inverting it
            reaper.setSpeed(-reaper.getSpeed()); //modifies the Reaper's direction speed using setSpeed()
        }
        //if the reaper collides with any of the stone platforms or lava platforms
        //allows Reaper to change direction on its own every time it interacts with a platform body
        else if(e.getOtherBody() instanceof StonePlatform || e.getOtherBody() instanceof LavaPlatform) {
            //System.out.println("Reaper collision. Change direction!"); //output register successful collision in console
            reaper.startWalking(-(reaper.getSpeed())); //reaper starts moving in the opposite direction by returning current speed and inverting it
            reaper.setSpeed(-reaper.getSpeed()); //modifies the Reaper's direction speed using setSpeed()
        }
    }
}
