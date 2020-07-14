package EnemyBodies;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.World;

/**
 * Create a new enemy subclass extending the superclass EnemyBodies (a Walker Body)
 */

public class Reaper extends Enemy {

    //Initialises coordinates for Reaper's shape using PolygonShape
    private static final Shape shape = new PolygonShape(
            1.5f,-1.5f, 0.46f,-1.94f, -1.35f,-1.54f, -1.64f,1.04f, -0.67f,1.94f, 1.22f,1.89f, 1.86f,0.49f);

    //stores the image that will be attached to the convex shape
    private static final BodyImage image = new BodyImage("data/reaper.png", 4f);

    /**
     * Initialises a new Reaper into the world
     * @param world instance of the world
     * @param speed speed of the Reaper enemy
     */
    public Reaper (World world, int speed) {
        super(world, shape, image, speed);
        addImage(image);//add image to shape
        setGravityScale(0); //set gravity of the Reaper to remain floating/aerial
        startWalking(getSpeed()); //declares method in superclass to set the walking speed of the Reaper
    }
}
