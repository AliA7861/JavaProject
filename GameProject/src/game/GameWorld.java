package game;

import EnemyBodies.Reaper;
import BodyCollisions.characterCollision;
import Player.Character;
import PlayerCharacteristics.Heart;
import PlayerCharacteristics.Key;
import PlayerCharacteristics.PowerUp;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;


public class GameWorld extends World {

    //Initialising bodies in the game to interact with
    private Character character;    //Initialises a new Player into the game
    private Reaper reaper;          //Initialises a Reaper into the game

    private Heart heart;            //Health Perks initialised
    private PowerUp star;           //PowerUp booster initialised

    //Initialise two new variables storing images for specific platform types in the game
    private static final BodyImage stonePlatform = new BodyImage("data/platform2.png", 2.25f);
    private static final BodyImage lavaPlatform = new BodyImage("data/platform.png", 2.25f);

    public GameWorld() {
        //superclass constructor - reduced confusion with subclasses that have same named methods
        super();

        //Initialised StaticBodies using BoxShape to contain bodies in a specific area
        //All bodies positioned accordingly using the Vec2 method

        //creating ground platform using BoxShape
        Shape ground = new BoxShape(39, 0.4f);
        Body groundFloor = new StaticBody(this, ground);
        groundFloor.setPosition(new Vec2(0, -17f));
        groundFloor.setFillColor(Color.black);

        //Ceiling shape created to contain bodies within specific area
        Shape ceiling = new BoxShape(39,0.4f);
        Body ceilingWall = new StaticBody(this, ceiling);
        ceilingWall.setPosition(new Vec2(0,17f));
        ceilingWall.setFillColor(Color.black);

        //Right wall is a StaticBody allowing bodies to interact with it
        Shape right = new BoxShape(1,17.4f);
        Body rightWall = new StaticBody(this, right);
        rightWall.setPosition(new Vec2(40, 0f));
        rightWall.setFillColor(Color.black);

        //Left wall is a StaticBody allowing bodies to interact with it
        Shape left = new BoxShape(1, 17.4f);
        Body leftWall = new StaticBody(this, left);
        leftWall.setPosition(new Vec2(-40, 0f));
        leftWall.setFillColor(Color.black);


        //horizontal aerial platforms as StaticBodies- positions set accordingly

        //Create PLatform 1
        Shape shape1 = new BoxShape(4, 0.75f);
        Body platform1 = new StaticBody(this, shape1);
        platform1.setPosition(new Vec2(-25, 3f));
        //Parse image of stone platform stored in variable 'stonePlatform'
        platform1.addImage(stonePlatform);

        //Creates Platform 2
        Shape shape2 = new BoxShape(4, 0.75f);
        Body platform2 = new StaticBody(this, shape2);
        platform2.setPosition(new Vec2(-8, -10f));
        //Parse image of stone platform stored in variable 'stonePlatform'
        platform2.addImage(stonePlatform);

        //Create Platform 3
        Shape shape3 = new BoxShape(4, 0.75f);
        Body platform3 = new StaticBody(this, shape3);
        platform3.setPosition(new Vec2(8, -4f));
        //Parse image of stone platform stored in variable 'stonePlatform'
        platform3.addImage(stonePlatform);

        //Create Platform 4
        Shape shape4 = new BoxShape(4, 0.75f);
        Body platform4 = new StaticBody(this, shape4);
        platform4.setPosition(new Vec2(10, 11f));
        //Parse image of stone platform stored in variable 'stonePlatform'
        platform4.addImage(stonePlatform);

        //Create Platform 5
        Shape shape5 = new BoxShape(4, 0.75f);
        Body platform5 = new StaticBody(this, shape5);
        platform5.setPosition(new Vec2(27, 5f));
        //Parse image of stone platform stored in variable 'stonePlatform'
        platform5.addImage(stonePlatform);

        //Create Platform 6
        Shape shape6 = new BoxShape(4, 0.75f);
        Body platform6 = new StaticBody(this, shape6);
        platform6.setPosition(new Vec2(-15, 10f));
        //Parse image of stone platform stored in variable 'stonePlatform'
        platform6.addImage(stonePlatform);

        //Create Platform 7
        Shape shape7 = new BoxShape(2, 0.5f);
        Body lavaPlatform1 = new StaticBody(this, shape7);
        lavaPlatform1.setPosition(new Vec2(-7, -2f));
        //Parse image of lava platform stored in variable 'lavaPlatform'
        lavaPlatform1.addImage(lavaPlatform);

        //Create Platform 8
        Shape shape8 = new BoxShape(2, 0.5f);
        Body lavaPlatform2 = new StaticBody(this, shape8);
        lavaPlatform2.setPosition(new Vec2(-22, -6f));
        //Parse image of lava platform stored in variable 'lavaPlatform'
        lavaPlatform2.addImage(lavaPlatform);

        //Create Platform 9
        Shape shape9 = new BoxShape(2, 0.5f);
        Body lavaPlatform3 = new StaticBody(this, shape9);
        lavaPlatform3.setPosition(new Vec2(23, -8f));
        //Parse image of lava platform stored in variable 'lavaPlatform'
        lavaPlatform3.addImage(lavaPlatform);

        //Create Platform 10
        Shape shape10 = new BoxShape(2, 0.5f);
        Body lavaPlatform4 = new StaticBody(this, shape10);
        lavaPlatform4.setPosition(new Vec2(31, -13f));
        //Parse image of lava platform stored in variable 'lavaPlatform'
        lavaPlatform4.addImage(lavaPlatform);

        //Create Platform 11
        Shape shape11 = new BoxShape(2,0.5f);
        Body lavaPlatform5 = new StaticBody(this, shape11);
        lavaPlatform5.setPosition(new Vec2(-34, -12f));
        //Parse image of lava platform stored in variable 'lavaPlatform'
        lavaPlatform5.addImage(lavaPlatform);

        //Vertical aerial platforms as StaticBodies - create obstacles for the player when moving between platforms
        //Create Platform 12
        Shape shape12 = new BoxShape(1, 3.5f);
        Body wall1 = new StaticBody(this, shape12);
        wall1.setPosition(new Vec2(9, -13f));
        //Wall colour set to black
        wall1.setFillColor(Color.black);

        //Create Platform 13
        Shape shape13 = new BoxShape(1, 2.8f);
        Body wall2 = new StaticBody(this, shape13);
        wall2.setPosition(new Vec2(3, 4f));
        //Wall colour set to black
        wall2.setFillColor(Color.black);


        //Implement Player character into the game and set its position within the world created
        character = new Character(this);
        character.setPosition(new Vec2(3, -5f));


        //Implement reaper bodies into game setting its initial speed & position within the world
        reaper = new Reaper(this, -15); //sets Reaper's speed in a specific direction
        reaper.setPosition(new Vec2(-23, -13f));
        reaper.setGravityScale(0);  //Sets gravity to 0 so the current body remains in its specified position without falling
        this.reaper.addCollisionListener(new characterCollision(this.character));   //Adds a collision listener so that upon collision, it follows the Pickup class with the Character connected to it to deduct a life from the Character
        reaper.startWalking(reaper.getSpeed()); //Returns value of current speed in accessor method getSpeed() to move the reaper in the world
        //Add a collision listener between the reaper and other entities in the game to change direction such as the walls, platforms and character perks
//        this.reaper.addCollisionListener(new reaperCollision(leftWall, rightWall, wall1, wall2, platform1, platform2, platform3, platform4, platform5, platform6, lavaPlatform1, lavaPlatform2, lavaPlatform3, lavaPlatform4, lavaPlatform5,this.reaper, heart));

        //Implement a second reaper into the world - initial speed and position set
        reaper = new Reaper(this, 15);
        reaper.setPosition(new Vec2(13, 2f));
        reaper.setGravityScale(0);  //Sets gravity to 0 so that current reaper abides by position set
        this.reaper.addCollisionListener(new characterCollision(this.character)); //Adds a collision listener so that upon collision, it follows the Pickup class with the Character attached to it to deduct a life from the Character
        reaper.startWalking(reaper.getSpeed()); //Returns value of current speed in accessor method getSpeed() to move the reaper in the world
        //Add a collision listener between the reaper and other entities in the game to change direction such as the walls, platforms and character perks
//        this.reaper.addCollisionListener(new reaperCollision(leftWall, rightWall, wall1, wall2, platform1, platform2, platform3, platform4, platform5, platform6, lavaPlatform1, lavaPlatform2, lavaPlatform3, lavaPlatform4, lavaPlatform5,this.reaper, heart));

        //Implement a third reaper into the world - initial speed and position set
        reaper = new Reaper(this, 15);
        reaper.setPosition(new Vec2(-24, -2f));
        reaper.setGravityScale(0);  //Sets gravity to 0 so the current body remains in its specified position without falling
        this.reaper.addCollisionListener(new characterCollision(this.character)); //Adds a collision listener so that upon collision, it follows the Pickup class with the Character connected to it to deduct a life from the Character
        reaper.startWalking(reaper.getSpeed());//Returns value of current speed in accessor method getSpeed() to move the reaper in the world
        //Add a collision listener between the reaper and other entities in the game to change direction such as the walls, platforms and character perks
//        this.reaper.addCollisionListener(new reaperCollision(leftWall, rightWall, wall1, wall2, platform1, platform2, platform3, platform4, platform5, platform6, lavaPlatform1, lavaPlatform2, lavaPlatform3, lavaPlatform4, lavaPlatform5,this.reaper, heart));


        //create keys within the world
        for(int i = -3; i < 14; i++) {  //Creates 14 keys in the map (only 12 will spawn inside contained area)
            Body key = new Key(this); //Initialise every key as a Dynamic body
            key.setPosition(new Vec2((float)(i*6.5-50), 15.0F)); //x position of every key changes in each iteration
            key.addCollisionListener(new characterCollision(this.character));   //Collision listener added so that upon collision, the Pickup class is called with the Character to increment keys collected
        }

        //create hearts around the game to boost character life count
        //First heart spawned as a Walker body (Dynamic body that stays upright)
        heart = new Heart(this);
        heart.setPosition(new Vec2(20, 10f)); //position set in game
        heart.setGravityScale(0);   //gravity set to 0 to stop body from falling to the ground
        this.heart.addCollisionListener(new characterCollision(this.character)); //Collision listener to call Pickup class with the Character to increase the number of lives left

        //Second heart spawned as a Walker body (Dynamic body that stays upright)
        heart = new Heart(this);
        heart.setPosition(new Vec2(-26, 13f)); //position set
        heart.setGravityScale(0); //gravity set to 0 to stop body from falling to the ground
        this.heart.addCollisionListener(new characterCollision(this.character)); //Collision listener to call Pickup class with the Character to increase the number of lives left

        //Powerup spawned as a Walker body (Dynamic body that stays upright)
        star = new PowerUp(this);
        star.setPosition(new Vec2(-9, 4));  //position set
        star.setGravityScale(0); //gravity set to 0 to stop body from falling to the ground
        this.star.addCollisionListener(new characterCollision(this.character)); //Collision listener added to call Pickup class with the Character to increase the number of powerups they have
    }

    //accessor method returning a character when declared by another class
    public Character getPlayer() {

        return character;
    }

    //accessor method returning a reaper when declared by another class
    public Reaper getReaper() {

        return reaper;
    }

}
