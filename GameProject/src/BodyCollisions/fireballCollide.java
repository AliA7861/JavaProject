package BodyCollisions;

import EnemyBodies.Fireball;
import StaticBodies.Platform;
import StaticBodies.RockPlatform;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Create a new listener interface to enable the fireballs to roll of rock platforms when spawned,
 * and setting a new position every time it collides with the StaticBodies that are an instance
 * of the 'Platform' class.
 */

public class fireballCollide implements CollisionListener {

    //initialise body planes that will be interacting with each upon collisions
    private Fireball fireball;
    private Platform platform;

    /**
     * <p> Initialise a new CollisionListener involving the Fireball and Platform bodies </p>
     * @param platform A platform body
     * @param fireball A fireball body
     */
    public fireballCollide(Platform platform, Fireball fireball) {
        //assign body planes to itself
        this.platform = platform;
        this.fireball = fireball;
    }

    /**
     * <p> Receives and responds to collisions where fireballs will roll on its own ability, falling downwards towards the ground which
     * will reset the fireball's position elsewhere in the world and repeat</p>
     * @param e Collision event description
     */
    @Override
    public void collide(CollisionEvent e) {
        //if the fireball collides with rock platforms in the world
        if(e.getOtherBody() instanceof RockPlatform) {
            fireball.setAngularVelocity(-(fireball.getFireballSpeed())); //return value for fireball speed, invert it and set as the angular velocity
            fireball.setFireballSpeed(fireball.getFireballSpeed()); //modify new fireball speed by declaring accessor method in Fireball class
        }
        //otherwise, collisions between the fireball and other types of platforms
        else if (e.getReportingBody() instanceof Fireball && e.getOtherBody() instanceof Platform) {
            fireball.setPosition(new Vec2(fireball.getFireballPosition())); //reset its position by calling accessor method in Fireball class to retrieve the position
            fireball.setAngularVelocity(-(fireball.getFireballSpeed())); //declares fireball speed method to set angular velocity
            fireball.setFireballSpeed(-fireball.getFireballSpeed()); //modifies fireball speed by inverting value when returning the value
        }
    }

}
