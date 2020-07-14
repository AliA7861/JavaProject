package PlayerCharacteristics;

import city.cs.engine.*;

/**
 * Create a new body for the player to collect in the game in order to destroy enemy
 * bodies that exist in the level.
 */
public class PowerUp extends DynamicBody {

    //Initialise shape's coordinates using the PolygonEditor
    private static final PolygonShape star = new PolygonShape(
            0.776f,-0.976f, -0.72f,-0.94f, -0.884f,0.244f, 0.02f,0.956f, 0.836f,0.18f, 0.824f,-0.64f);
    //Initialise image to attach to shape, setting its height
    private static final BodyImage starImage = new BodyImage("data/star.gif", 2.0F); //image of GIF type to create interactive game world

    /**
     * Constructor method to initialise the base attributes of the powerup body, parsing its shape and image
     * into the world. Angular velocity and gravity scale initialised to preserve game feel and dynamic.
     * @param world a game world for which the heart will need to be generated in
     */
    public PowerUp(World world) {
        super(world, star);
        this.addImage(starImage);
        setGravityScale(0); //gravity set to 0 to stop body from falling to the ground
        setAngularVelocity(6); //enables powerups to rotate on its axis in user view
    }
}
