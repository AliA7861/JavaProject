package StaticBodies;

import city.cs.engine.BodyImage;
import city.cs.engine.World;

/**
 * Create an ice platform in Level2 extending the attributes of the superclass
 * Platform
 */
public class IcePlatform extends Platform {

    //initialise platform dimensions and image to be render to the platform fixture
    private float width, height;
    private BodyImage icePlatform;

    /**
     * Constructor method for the ice platform extending attributes in the Platform class and
     * providing additional attributes to the class.
     * @param w the game world that needs to be generated for the platforms to exist in
     * @param width the width of the platform
     * @param height the height of the platform
     */
    public IcePlatform(World w, float width, float height) {
        super(w, width, height);
        icePlatform = new BodyImage("data/ice-platform.png", 4.5f);
        this.addImage(icePlatform); //attaches the ice platform image to the shape
    }
}
