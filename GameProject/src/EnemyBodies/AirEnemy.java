package EnemyBodies;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.World;

/**
 * Creates a new enemy subclass extending the superclass EnemyBodies (a Walker body)
 */

public class AirEnemy extends Enemy {

    //Initialises coordinates for AirEnemy's shape using PolygonShape
    private static final Shape shape = new PolygonShape(
            -0.05f,-1.14f, -1.46f,0.16f, -0.3f,0.85f, 0.51f,0.86f, 1.54f,0.13f, 0.19f,-1.05f);
    private static final BodyImage image = new BodyImage("data/air-enemy.gif", 7f); //Initialise variable storing image for airEnemy

    /**
     * Initialises a new AirEnemy into the world
     * @param world create an instance of a world
     * @param speed speed the AirEnemy will move in the game
     */

    public AirEnemy(World world, int speed) {
        super(world, shape, image, speed);
        addImage(image); //adds image to PolygonShape
        setGravityScale(0); //set the gravity of the AirEnemy
        startWalking(-20); // initialise the walking speed the AirEnemy in the world
    }
}
