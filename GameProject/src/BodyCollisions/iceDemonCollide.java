package BodyCollisions;

import EnemyBodies.IceDemon;
import StaticBodies.Platform;
import city.cs.engine.*;

/**
 * Create a new listener interface to allow the IceDemon creatures to move between the set boundaries
 * in the world.
 */

public class iceDemonCollide implements CollisionListener {

    //initialise body planes that will interact with each other
    private Platform platform;
    private IceDemon iceDemon;

    /**
     * <p> Initialise a new CollisionListener involving the IceDemon and Platform bodies </p>
     * @param platform A platform body
     * @param iceDemon A iceDemon body
     */
    public iceDemonCollide(Platform platform, IceDemon iceDemon) {
        this.platform = platform;
        this.iceDemon = iceDemon;
    }

    /**
     * <p> Receives and responds to collisions when the IceDemon interacts with specific types of platforms,
     * changing their directional movement every time a collision is record between a platform/boundary.</p>
     * @param e Collision event description
     */
    @Override
    public void collide(CollisionEvent e) {
        //if the IceDemon interacts with a platform type and has a positive speed i.e. moving towards the right
        if (e.getOtherBody() instanceof Platform && iceDemon.getSpeed() == 15) {
            iceDemon.removeAllImages(); //remove the current image attached to the IceDemon shape
            iceDemon.startWalking(-(iceDemon.getSpeed())); //IceDemon moves in the opposite direction by returning current speed and inverting it
            iceDemon.setSpeed(-iceDemon.getSpeed()); //modifies the IceDemon's direction speed using setSpeed()
            iceDemon.addImage(new BodyImage("data/ice-creature.gif", 5f)); //reapplies a new image to the shape to face left
        }
        //otherwise, if the IceDemon interacts with a platform type and has a negative speed i.e. moving towards the left
        else if (e.getOtherBody() instanceof Platform && iceDemon.getSpeed() == -15) {
            iceDemon.removeAllImages(); //remove the current image attached to the IceDemon shape
            iceDemon.startWalking(-(iceDemon.getSpeed())); //IceDemon moves the opposite direction by returning current speed and inverting it
            iceDemon.setSpeed(-iceDemon.getSpeed()); //modifies the IceDemon's direction speed using setSpeed()
            iceDemon.addImage(new BodyImage("data/ice-creatureRight.gif", 5f)); //reapplies a new image to the shape to face right
        }
    }
}
