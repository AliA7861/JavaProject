package StaticBodies;

import city.cs.engine.BodyImage;
import city.cs.engine.World;

/**
 * Create a lava platform in Level1 extending the attributes of the superclass
 * Platform
 */
public class LavaPlatform extends Platform {

    //initialise platform dimensions and image to be render to the platform fixture
    private float width, height;
    private BodyImage lavaPlatform;

    /**
     * Constructor method for the lava platform extending attributes in the Platform class and
     * providing additional attributes to the class.
     * @param w the game world that needs to be generated for the platforms to exist in
     * @param width the width of the platform
     * @param height the height of the platform
     */
    public LavaPlatform(World w, float width, float height) {
        super(w, width, height);
        lavaPlatform = new BodyImage("data/platform.png", 2.25f);
        this.addImage(lavaPlatform); //attaches the lava platform image to the shape
    }
}
