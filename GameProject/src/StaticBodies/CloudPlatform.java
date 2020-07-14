package StaticBodies;

import city.cs.engine.BodyImage;
import city.cs.engine.World;

/**
 * Create a cloud platform in Level3 extending the attributes of the superclass
 * Platform
 */
public class CloudPlatform extends Platform {

    //initialise platform dimensions and image to be render to the platform fixture
    private float width, height;
    private BodyImage cloudPlatform;

    /**
     * Constructor method for the cloud platform extending attributes in the Platform class and
     * providing additional attributes to the class.
     * @param w the game world that needs to be generated for the platforms to exist in
     * @param width the width of the platform
     * @param height the height of the platform
     */
    public CloudPlatform(World w, float width, float height) {
        super(w, width, height);
        cloudPlatform = new BodyImage("data/cloud-platform.png", 8f);
        this.addImage(cloudPlatform); //attaches the cloud platform image to the shape
    }
}
