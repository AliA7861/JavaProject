package StaticBodies;

import city.cs.engine.*;

/**
 * Create a new superclass for the platforms that will be generated
 * in the game view of each level
 */
public class Platform extends StaticBody {

    //initialise variables representing the platform dimensions
    private float width;
    private float height;

    /**
     * Base attributes platform subclasses will conform to, setting the
     * initial dimension and the type of fixture they are.
     * @param w the game world that needs to be generated for the platforms to exist in
     * @param width the width of the platform
     * @param height the height of the platform
     */
    public Platform(World w , float width, float height) {
        super(w);
        Shape s = new BoxShape(width, height);
        Fixture f = new SolidFixture(this, s);
        this.width =  width;
        this.height = height;
    }

    /**
     * Function that can be declared outside of class, mostly used in the GameSaver and GameLoader class
     * to return the width of each platform in the game.
     * @return float value for the platform width
     */
    public float getWidth() {
        return width;
    }

    /**
     * Function that can be declared outside of class, mostly used in the GameSaver and GameLoader class
     * to return the height of each platform in the game.
     * @return float value for the platform height
     */
    public float getHeight() {
        return height;
    }
}
