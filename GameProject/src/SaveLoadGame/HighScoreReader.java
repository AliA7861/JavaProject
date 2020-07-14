package SaveLoadGame;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates how high-score data can be read from a text
 * file and printed to the terminal.
 */
public class HighScoreReader {

    private String fileName; //initialise a new string variable storing text file name

    /**
     * Initialise a new HighScoreReader
     * @param fileName string value storing the file name being read
     */
    public HighScoreReader(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Read the high-score data from the high-score file and print it to
     * the terminal window.
     * @return array list of strings containing the high scores read from the text file
     * @throws IOException explicitly inform compiler that the program may throw an exception as a result of an Input/Output operation
     */
    public List<String> readScores() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        List<String> scores = new ArrayList<>(); //create a new variable for a new array list of strings
        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName); //assign scores text file to a new FileReader
            reader = new BufferedReader(fr); //assign variable storing new fileReader into a BufferReader to analyse character-input streams
            String line = reader.readLine(); //new String variable reading strings of text in the scores file
            while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split(",");//read each token in the line where it split up by "," in the stream
                String name = tokens[0]; //first item in the String represents the saved player name
                int score = Integer.parseInt(tokens[1]); //second item in the String parses the integer representing the user's score
                scores.add("Name:     " + name + "  " + "       Score:     " + score);
                line = reader.readLine(); //reads the next line in the list of strings
            }
            System.out.println("...done.");
        } finally {
            // FileReader and BufferReader are closed after the scores text file is finished being used
            //.close() will throw IOException after the stream is closed
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
        return scores; //return the array list of strings from the scores text file
    }
}
