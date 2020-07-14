package StaticBodies;

import city.cs.engine.BodyImage;
import city.cs.engine.World;

/**
 * Create a rock platform in Level4 extending the attributes of the superclass
 * Platform
 */
public class RockPlatform extends Platform {

    //initialise platform dimensions and image to be render to the platform fixture
    private float width, height;
    private BodyImage rockPlatform;

    /**
     * Constructor method for the rock platform extending attributes in the Platform class and
     * providing additional attributes to the class.
     * @param w the game world that needs to be generated for the platforms to exist in
     * @param width the width of the platform
     * @param height the height of the platform
     */
    public RockPlatform(World w, float width, float height) {
        super(w, width, height);
        rockPlatform = new BodyImage("data/earth-platform.png", 2.6f);
        this.addImage(rockPlatform); //attaches the rock platform image to the shape
    }
}
