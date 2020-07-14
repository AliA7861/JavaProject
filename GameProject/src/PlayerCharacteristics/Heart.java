package PlayerCharacteristics;

import city.cs.engine.*;

/**
 * Create a new body for the player to increase the number of
 * lives they have in the game
 */
public class Heart extends DynamicBody {

    //Initialises coordinates of the body's shape using the PolygonEditor
    private static final Shape heart = new PolygonShape(
            0.46f,-0.979f, -0.747f,-0.924f, -1.129f,-0.013f, -0.505f,1.046f, 0.653f,1.016f, 1.02f,-0.236f);
    //Initialises the image that will attach to the shape, setting its height in the game view
    private static final BodyImage image = new BodyImage("data/heart.gif", 2.25F); //file of GIF type to create interactive world

    /**
     * Constructor method to initialise the base attributes of the heart body, parsing its shape and image
     * into the world. Angular velocity and gravity scale initialised to preserve game feel and dynamic.
     * @param world a game world for which the heart will need to be generated in
     */
    public Heart(World world) {
        super(world, heart);
        this.addImage(image);
        setGravityScale(0); //enables body to remain fixed/aerial in the game world
        setAngularVelocity(6); //provides the heart body to rotate on its own axis
    }
}
