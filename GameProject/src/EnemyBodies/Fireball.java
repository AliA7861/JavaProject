package EnemyBodies;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Create a new Fireball extending DynamicBody
 */

public class Fireball extends DynamicBody {

    private static final Shape fireball = new PolygonShape(
            -1.22f,-0.16f, -1.05f,0.62f, 0.0f,1.22f, 1.03f,0.56f, 1.07f,-0.73f, 0.07f,-1.23f, -0.86f,-0.82f);
    private static final BodyImage image = new BodyImage("data/fireball.png", 3.5f);

    private Vec2 fireballPosition; //initialise variable to store to store fireball's position in level
    private float fireballSpeed; //initialise variable to store fireball's speed

    /**
     * Initialise a new Fireball body into the world
     * @param world instance of a world
     * @param fireballPosition coordinates of the body in world
     * @param fireballSpeed speed the body will move in the world
     */

    public Fireball(World world, Vec2 fireballPosition, float fireballSpeed) {
        super(world, fireball);
        this.addImage(image); //adds image to the PolygonShape
        this.setGravityScale(1.5f); //gravity effect stronger making fireball fall faster to the ground
        this.fireballSpeed = fireballSpeed; //sets fireball speed
        this.fireballPosition = fireballPosition; //sets fireball position
        this.setAngularVelocity(fireballSpeed); //sets angular velocity of fireball using the variable for fireball speed

    }

    /**
     * @return Coordinates of a fireball body
     */
    public Vec2 getFireballPosition() {
        return fireballPosition;     //return fireball coordinates upon declaration outside of class
    }

    /**
     * @return Speed of the fireball
     */
    public float getFireballSpeed() {
        return fireballSpeed; //return fireball speed upon declaration outside of class
    }

    /**
     * @param fireballSpeed Set the current speed of the fireball as a float
     */
    public void setFireballSpeed(float fireballSpeed) {
        this.fireballSpeed = fireballSpeed;     //set current speed of fireball as itself
    }
}
