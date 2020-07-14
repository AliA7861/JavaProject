package StaticBodies;

import city.cs.engine.BodyImage;
import city.cs.engine.World;

/**
 * Create a stone platform in Level1 extending the attributes of the superclass
 * Platform
 */
public class StonePlatform extends Platform {

    //initialise platform dimensions and image to be render to the platform fixture
    private float width, height;
    private BodyImage stonePlatform;

    /**
     * Constructor method for the stone platform extending attributes in the Platform class and
     * providing additional attributes to the class.
     * @param w the game world that needs to be generated for the platforms to exist in
     * @param width the width of the platform
     * @param height the height of the platform
     */
    public StonePlatform(World w, float width, float height) {
        super(w, width, height);
        stonePlatform = new BodyImage("data/platform2.png", 2.25f);
        this.addImage(stonePlatform); //attaches the stone platform image to the shape
    }
}
