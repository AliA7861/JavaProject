package SaveLoadGame;

import EnemyBodies.AirEnemy;
import EnemyBodies.Fireball;
import EnemyBodies.IceDemon;
import EnemyBodies.Reaper;
import Levels.GameLevel;
import StaticBodies.Platform;
import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;
import Player.Character;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Demonstration of the game state data can be saved to a text file whilst playing the game
 */

public class GameSaver {
    private String fileName; //initialise a new string variable storing text file name

    /**
     * Initialise a new GameSaver
     * @param fileName string value for the name of the text file being saved to
     */
    public GameSaver(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Utilises the GameLevel class to return data about the level and the character
     * @param gameLevel generates the instance of the GameLevel
     * @throws IOException explicitly inform compiler that the program may throw an exception as a result of an Input/Output operation
     */
    public void saveGame(GameLevel gameLevel) throws IOException {
        boolean append = false; //local boolean variable initially set so that data is replaced every time the game is saved
        FileWriter writer = null; //initial FileWriter set as empty

        try {
            writer = new FileWriter(fileName, append); //initialise a new FileWriter
            writer.write(gameLevel.getLevelNumber() + "\n"); //first line saving the level number

            //save the body representing the character and make it second line in saving method
            //.simpleName() used for easily retrieval when loading the bodies back into the game
            writer.write(gameLevel.getPlayer().getClass().getSimpleName() +
                    "," + gameLevel.getPlayer().getPosition().x + "," +
                    gameLevel.getPlayer().getPosition().y + "," +
                    gameLevel.getPlayer().getKeyCount() + "," +
                    gameLevel.getPlayer().getLiveCount() + "," +
                    gameLevel.getPlayer().getPowerUp() +  "," + "\n");

            //save all bodies that are a StaticBody
            for (StaticBody body: gameLevel.getStaticBodies()) {
                //if the body is an instance of the Platform superclass
                if (body instanceof Platform) {
                    //write a new lines saving the static bodies that are platforms using .simpleName to load bodies easily
                    writer.write(body.getClass().getSimpleName() + "," +
                            body.getPosition().x + "," + body.getPosition().y + "," +
                            ((Platform) body).getWidth() + "," + ((Platform) body).getHeight() + "\n");
                }
                //otherwise if the staticBody is the portal
                else {
                    writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y + "\n");
                }
            }

            //else, save all bodies not the player body and are DynamicBodies
            for (DynamicBody body: gameLevel.getDynamicBodies()) {
                if (!(body instanceof Character)) {
                    //conditional statements writing to the textfile depending on the type of enemy they are
                    //characteristics/attributes of the enemies are saved
                    if (body instanceof Reaper) {
                        writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y +
                                "," + ((Reaper) body).getSpeed() + "," + body.getGravityScale() + "\n");
                    }
                    else if (body instanceof IceDemon) {
                        writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y +
                                "," + ((IceDemon) body).getSpeed() + "," + body.getGravityScale() + "\n");
                    }
                    else if (body instanceof AirEnemy) {
                        writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y +
                                "," + ((AirEnemy) body).getSpeed() + "," + body.getGravityScale() + "\n");
                    }
                    else if (body instanceof Fireball) {
                        writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y +
                                "," + body.getAngularVelocity() + "," + body.getGravityScale() + "\n");
                    }
                    else {
                        writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y + "\n");
                    }
                }
            }

        } finally {
            //close the FileWriter stream once all the data is written to the text file
            //.close() will throw IOException after the stream is closed
            if (writer != null) {
                writer.close();
            }
        }
    }

}
