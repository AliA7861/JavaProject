package EnemyBodies;

import city.cs.engine.BodyImage;
import city.cs.engine.Shape;
import city.cs.engine.World;
import city.cs.engine.Walker;

/**
 * Initialise a superclass for all enemy bodies set as a Walker
 * to remain upright
 */
public class Enemy extends Walker {

    private int speed; //Initialise variable storing speed of of the enemy body

    /**
     * Initialise a new EnemyBodies
     * @param world instance of the world
     * @param shape convex shape of the EnemyBodies body
     * @param image image of the EnemyBodies body
     * @param speed speed the EnemyBodies body will move in the world
     */

    //constructor method for the enemy
    public Enemy(World world, Shape shape, BodyImage image, int speed) {
        super(world, shape);
        addImage(image);        //attach the image representing the enemy
        this.speed = speed;
    }

    /**
     * <p>Accessor method to retrieve the EnemyBodies body's speed</p>
     * @return the value of EnemyBodies's speed as an integer
     */
    public int getSpeed() {
        return speed;     //accessor method to return value of speed when declared
    }

    /**
     * <p>Mutator method that will set the current speed of the enemy</p>
     * @param speed Set current speed of the EnemyBodies of an integer type
     */

    public void setSpeed(int speed) {
        this.speed = speed;     //mutator method that sets the current speed of the reaper that is of an integer type
    }
}
