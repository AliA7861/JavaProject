package SaveLoadGame;

import EnemyBodies.*;
import BodyCollisions.*;
import Levels.GameLevel;
import Levels.*;
import Player.Character;
import PlayerCharacteristics.*;
import StaticBodies.*;
import city.cs.engine.Body;
import game.Game;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Demonstration of the game state data saved can be read from a text file and reproduced in the view when loaded back
 */
public class GameLoader {

    private String fileName; //initialise a new string variable storing text file name
    private Game game; //initialise the game to be generated in the loader

    //platform body planes initialised
    private Platform platform;
    private StonePlatform stoneplatform;
    private CloudPlatform cloudplatform;
    private LavaPlatform lavaplatform;
    private IcePlatform iceplatform;
    private RockPlatform rockplatform;

    /**
     * Initialise a new GameLoader
     * @param fileName string value storing the file name of the data being read
     * @param g initialise the game variable as part of the loader
     */
    public GameLoader(String fileName, Game g) {
        this.fileName = fileName;
        game = g;
    }

    /**
     * Reads the game state data from the position file and display the output in a JFrame
     * @return value representing the level number
     * @throws IOException explicitly inform compiler that the program may throw an exception as a result of an Input/Output operation
     */
    public GameLevel loadGame() throws IOException {
        FileReader fr = null; //data read from text file is initially set to null
        BufferedReader reader = null; //data read as a character-input stream initially set to null
        try {
            System.out.println("Reading " + fileName + " ..."); //register the file is being read in console
            fr = new FileReader(fileName); //assign position text file to a new FileReader
            reader = new BufferedReader(fr); //variable stored in new FileReader assigned to the buffer reader to read character-input streams

            //read the level in the first line
            String line = reader.readLine(); //new String variable reading text from the positions file
            int levelNumber = Integer.parseInt(line); //parses value in the first line representing the level as an integer

            //populating the game with the correct level depending on the integer stored in levelNumber
            GameLevel level = null; //initially set the level number variable to null
            if (levelNumber == 1) {
                level = new Level1(); //create a new Level1
                level.getBackgroundImage(); //retrieve background image for the first level when the accessor method returns 1
            }
            else if (levelNumber == 2) {
                level = new Level2(); //create a new Level2
                level.getBackgroundImage(); //retrieve background image for the first level when the accessor method returns 1
            }
            else if (levelNumber == 3) {
                level = new Level3(); //create a new Level3
                level.getBackgroundImage(); //retrieve background image for the first level when the accessor method returns 1
            }
            else if (levelNumber == 4) {
                level = new Level4(); //create a new Level4
                level.getBackgroundImage(); //retrieve background image for the first level when the accessor method returns 1
            }

            //read the player's position in the second line
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(","); //read each token in the line where it split up by ","
                String className = tokens[0]; //first item of the line representing the class name of the body saved
                float xPlayer = Float.parseFloat(tokens[1]); //second item in the string saved as the x-coordinate of the body
                float yPlayer = Float.parseFloat(tokens[2]); //third item in the string saved as the y-coordinate of the body
                Vec2 position = new Vec2(xPlayer, yPlayer); //vector position created to reset the body's coordinate in the place they were initially saved

                //if the class name represents the main player
                if (className.equals("Character")) {
                    int count = Integer.parseInt(tokens[3]); //integer variable parsing the third item in string as the key count saved
                    int lives = Integer.parseInt(tokens[4]); //integer variable parsing the fourth item in string as the live count saved
                    int powerups = Integer.parseInt(tokens[5]); //integer variable parsing the fifth item in string as the powerup count saved
                    Character b = new Character(level); //initialise a new character body in the level loaded
                    b.setPosition(position); //parse saved position and set it in the level
                    b.setKeyCount(count); //display saved key count in display
                    b.setLiveCount(lives); //display saved live count in display
                    b.setPowerUp(powerups); //display saved number of powerups in display
                    level.setPlayer(b); //replaces character body and set it with the new loaded Character body
                }
                //if the class name represents the key
                else if (className.equals("Key")) {
                    Body b = new Key(level); //create key bodies in the level
                    b.setPosition(position); //set position of the keys using saved coordinates in positions text file
                    b.addCollisionListener(new characterCollision(level.getPlayer())); //Collision listener added so that upon collision, the characterCollision class is called with the Character to increment keys collected
                }
                //if the class name represents the heart
                else if (className.equals("Heart")) {
                    Body b = new Heart(level); //create a new heart body in the level loaded
                    b.setPosition(position); //set position of the heart using saved coordinates in positions text file
                    b.addCollisionListener(new characterCollision(level.getPlayer())); //Collision listener added so that upon collision, the characterCollision class is called with the Character to increment the life count
                }
                //if the class name represents the powerup body
                else if (className.equals("PowerUp")) {
                    Body b = new PowerUp(level); //create a new powerup body
                    b.setPosition(position); //set position of the powerup using saved coordinates in positions text file
                    b.addCollisionListener(new characterCollision(level.getPlayer())); //Collision listener added to call characterCollision class with the Character to increase the number of powerups they have
                }
                //if the class name represents the Reaper body
                else if (className.equals("Reaper")) {
                    int speed = Integer.parseInt(tokens[3]);
                    Body b = new Reaper(level, speed);
                    b.setPosition(position); //set position of the Reaper using saved coordinates in positions text file
                    b.addCollisionListener(new characterCollision(level.getPlayer())); //Collision listener added to call characterCollision class with the Character to manipulate live count of player when colliding with the reaper
                    b.addCollisionListener(new reaperCollision(platform, (Reaper) b)); //collision listener added to call reaperCollision class controlling the enemy's behaviour when colliding with platforms
                }
                //if the class name represents the IceDemon body
                else if (className.equals("IceDemon")) {
                    int speed = Integer.parseInt(tokens[3]);
                    Body b = new IceDemon(level, speed);
                    b.setPosition(position); //set position of the IceDemon using saved coordinates in positions text file
                    b.addCollisionListener(new characterCollision(level.getPlayer())); //Collision listener added to call characterCollision class with the Character to manipulate live count of player when colliding with the iceDemon
                    b.addCollisionListener(new iceDemonCollide(platform, (IceDemon) b)); //collision listener added to call iceDemonCollide class controlling the enemy's behaviour when colliding with platforms
                }
                //if the class name represents the AirEnemy body
                else if (className.equals("AirEnemy")) {
                    int speed = Integer.parseInt(tokens[3]);
                    Body b = new AirEnemy(level, speed); //new airEnemy created
                    b.setPosition(position); //set position of the AirEnemy using saved coordinates in positions text file
                    b.addCollisionListener(new characterCollision(level.getPlayer())); //Collision listener added to call characterCollision class with the Character to manipulate live count of player when colliding with the airEnemy
                    b.addCollisionListener(new AirEnemyCollision(platform, (AirEnemy) b)); //collision listener added to call AirEnemyCollision class controlling the enemy's behaviour when colliding with platforms
                }
                //if the class name represents the Fireball body
                else if (className.equals("Fireball")) {
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    Vec2 fireballPos = new Vec2(x, y); //parse tokens for grid coordinates of fireball
                    float speed = Float.parseFloat(tokens[3]); //variable representing saved angular speed
                    Body b = new Fireball(level, fireballPos, speed); //new fireball created
                    b.setPosition(position); //set position of the fireball using saved coordinates in positions text file
                    b.addCollisionListener(new characterCollision(level.getPlayer())); //Collision listener added to call characterCollision class with the Character to manipulate live count of player when colliding with the fireball
                    b.addCollisionListener(new fireballCollide(platform, (Fireball) b)); //collision listener added to call fireballCollide class controlling the enemy's behaviour when colliding with platforms
                }
                //if the class name represents a platform
                else if (className.equals("Platform")) {
                    //parse the tokens for the width and heights of each platform into the constructor of a new platform
                    float w = Float.parseFloat(tokens[3]);
                    float h = Float.parseFloat(tokens[4]);
                    platform = new Platform(level, w, h); //new platform initialised
                    platform.setFillColor(Color.black); //set colour of bodies that are 'Platform' types
                    platform.setPosition(position); //set position of the platform using saved coordinates in positions text file
                }
                //if the class name represents a stone platform
                else if (className.equals("StonePlatform")) {
                    //parse the tokens for the width and heights of each platform into the constructor of a new stone platform
                    float w = Float.parseFloat(tokens[3]);
                    float h = Float.parseFloat(tokens[4]);
                    stoneplatform = new StonePlatform(level, w, h); //new stone platform initialised
                    stoneplatform.setPosition(position); //set position of the stone platform using saved coordinates in positions text file
                }
                //if the class name represents a lava platform
                else if (className.equals("LavaPlatform")) {
                    //parse the tokens for the width and heights of each platform into the constructor of a new lava platform
                    float w = Float.parseFloat(tokens[3]);
                    float h = Float.parseFloat(tokens[4]);
                    lavaplatform = new LavaPlatform(level, w, h); //new lava platform initialised
                    lavaplatform.setPosition(position); //set position of the lava platform using saved coordinates in positions text file
                }
                //if the class name represents an ice platform
                else if (className.equals("IcePlatform")) {
                    //parse the tokens for the width and heights of each platform into the constructor of a new ice platform
                    float w = Float.parseFloat(tokens[3]);
                    float h = Float.parseFloat(tokens[4]);
                    iceplatform = new IcePlatform(level, w, h); //new ice platform initialised
                    iceplatform.setPosition(position); //set position of the ice platform using saved coordinates in positions text file
                }
                //if the class name represents a cloud platform
                else if (className.equals("CloudPlatform")) {
                    //parse the tokens for the width and heights of each platform into the constructor of a new cloud platform
                    float w = Float.parseFloat(tokens[3]);
                    float h = Float.parseFloat(tokens[4]);
                    cloudplatform = new CloudPlatform(level, w, h); //new cloud platform initialised
                    cloudplatform.setPosition(position); //set position of the cloud platform using saved coordinates in positions text file
                }
                //if the class name represents a rock platform
                else if (className.equals("RockPlatform")) {
                    //parse the tokens for the width and heights of each platform into the constructor of a new rock platform
                    float w = Float.parseFloat(tokens[3]);
                    float h = Float.parseFloat(tokens[4]);
                    rockplatform = new RockPlatform(level, w, h); //new rock platform created
                    rockplatform.setPosition(position); //set position of the rock platform using saved coordinates in positions text file
                }
                else if (className.equals("Portal")) {
                    Body b = new Portal(level); //new portal body created
                    b.setPosition(position); //set position of the portal using saved coordinates in positions text file
                    b.addCollisionListener(new PortalListener(game)); // collision listener to determine if player has total keys need to pass through the portal upon collision
                }
            }
            return level; //return the value for the level number

        } finally {
            // FileReader and BufferReader are closed after the positions text file is finished being used
            //.close() will throw IOException after the stream is closed
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }
}
