package EnemyBodies;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.World;

/**
 * Create a new enemy subclass extending the superclass EnemyBodies (a Walker Body)
 */
public class IceDemon extends Enemy {

    //Initialises coordinates for IceDemon's shape using PolygonShape
    private static final Shape shape = new PolygonShape(
            1.23f,-1.42f, -0.91f,-1.48f, -2.04f,0.34f, -0.88f,1.69f, 0.96f,1.7f, 1.88f,-0.02f);
    private static final BodyImage demonLeft = new BodyImage("data/ice-creature.gif", 5f); //Initialise variable storing image of IceDemon facing left direction

    /**
     * Initialises a new IceDemon into the world
     * @param world instance of the world
     * @param speed speed of the IceDemon
     */
    public IceDemon(World world, int speed) {
        super(world, shape, demonLeft, speed);
        addImage(demonLeft); //add initial starting image of IceDemon in first instance
        setGravityScale(0); //sets gravity of the IceDemon to remain floating/aerial in the world
        startWalking(getSpeed()); //declares method in superclass to set the walking speed of the IceDemon
    }
}
