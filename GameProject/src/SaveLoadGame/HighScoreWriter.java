package SaveLoadGame;

import Levels.GameLevel;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Demonstrates how high-score data can be written to a text file.
 */
public class HighScoreWriter {

    private String fileName; //initialise a new string variable storing text file name

    /**
     * @param fileName string value for the file name being saved to
     */
    public HighScoreWriter(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Function writing the name and score to the scores text file
     * @param name the name that will be written to the scores text file
     * @param score the score to be written into the file
     * @throws IOException explicitly inform compiler that the program may throw an exception as a result of an Input/Output operation
     */
    public void writeHighScore(String name, int score) throws IOException {
        boolean append = true;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append); //create a new FileWriter method
            writer.write(name + "," + score + "\n"); //declare name and score to write to the text file
        } finally {
            //close() stream which will then throw an Exception
            if (writer != null) {
                writer.close();
            }
        }
    }
}
