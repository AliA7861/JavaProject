package GUIdisplay;

import game.Game;
import SaveLoadGame.HighScoreReader;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * This will create a new JFrame to display the high scores that will display a list of strings
 * when the scores button is pressed
 */

public class HighScore {
    private JPanel pnlScores;
    private JList<String> lstScores;
    private JScrollPane scrScores;

    private Game game;
    private HighScoreReader highScoreReader;
    private final String fileName = "data/scores.txt";

    /**
     * Game variable initialised
     * @param game generate the game
     */
    public HighScore(Game game) {
        this.game = game;

        File scores = new File(fileName); //initialise a new file to hold the scores
        try {
            scores.createNewFile(); //create a new empty file
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        highScoreReader = new HighScoreReader(fileName); //initialise a new highscoreReader parsing in the new file

        DefaultListModel<String> model = new DefaultListModel<>(); //implement a new list model used ot manage items displayed in a JList
        try {
            model.addAll(highScoreReader.readScores());//reads the scores and add this to the model to make it visible in the display
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        lstScores.setModel(model); //adds the list of scores and sets the model
    }

    /**
     * <p>Accessor method to return the panel that will display the scores</p>
     * @return JPanel for the scores
     */
    public JPanel getPnlScores() {
        return pnlScores;
    }
}
