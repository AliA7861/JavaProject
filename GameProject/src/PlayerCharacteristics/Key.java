package PlayerCharacteristics;

import city.cs.engine.*;

/**
 * Create a new body for the player to collect in the game in order to progress
 * through the different levels
 */
public class Key extends DynamicBody {

    //Initialising shape's coordinates using the PolygonEditor
    private static final Shape key = new PolygonShape(
            0.707f,-0.371f, -0.665f,-0.388f, -0.798f,0.179f, -0.042f,0.347f, 0.609f,0.287f, 0.774f,-0.035f);
    //Initialise image to attach to the shape,setting its height
    private static final BodyImage image = new BodyImage("data/key.png", 1.75F);

    /**
     * Constructor method to initialise base class for the keys, assigning and rendering the image
     * and shape of the key in the game world
     * @param world a game world for which the keys will need to be generated in
     */
    public Key(World world) {
        super(world, key);
        this.addImage(image);
    }
}
