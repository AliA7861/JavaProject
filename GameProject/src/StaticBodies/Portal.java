package StaticBodies;

import city.cs.engine.*;

/**
 * Create a new portal body in the level for the player to transition between each level that remains static
 * in the game view
 */
public class Portal extends StaticBody {

    /**
     * Constructor initialising the shape and image rendered to it to create the
     * body
     * @param world the game world for the portal body to be generated in
     */
    public Portal(World world) {
        //superclass creating the world and the PolygonShape
        super(world, new PolygonShape(
                0.91f,-1.49f, -0.85f,-1.5f, -1.23f,0.49f, -0.38f,1.63f, 1.07f,1.4f, 1.1f,-1.09f));
        addImage(new BodyImage("data/portal.png", 3.5f));//attaches image to the polygonShape
    }
}
