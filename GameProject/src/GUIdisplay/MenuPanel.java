package GUIdisplay;

import Levels.GameLevel;
import game.Game;
import SaveLoadGame.GameLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Demonstration of how a new menu panel GUI will be displayed in the view that provide the user with additional
 * controls over the functionality of the game
 */
public class MenuPanel extends JPanel {
    private JPanel menuPanel; //initialise new JPanel into game
    private Game game; //Initialise variable for the main game class

    //each button is assigned an icon representing its function in the game

    //Initialise buttons visible on user's screen
    private JButton PlayBtn;
    private JButton PauseBtn;
    private JButton skipBtn;
    private JButton QuitBtn;
    private JButton BackBtn;
    private JButton restartBtn;
    private JButton SaveBtn;
    private JButton LoadBtn;
    private JButton ScoreBtn;

    /**
     * Returns the menu panel upon declaration outside of the class
     * @return JPanel representing the menu
     */
    public JPanel getMenuPanel() {
        return menuPanel;
    }

    /**
     * <p>Initialise objects that will exist in the menu control panel, containing the
     * button controls that affect the game different</p>
     * @param game game variable parsed into the constructor
     */
    public MenuPanel(Game game) {
        this.game = game;

        //actionListener attached to resume game when user clicks the play button in game view
        PlayBtn.addActionListener(new ActionListener() {
            /**
             * Event is captured by the listener interface, taking a user's actions e.g.
             * clicking a button, causes an action to occur.
             * @param actionEvent action event descriptor
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                game.resumeGame();  //declares method in game class to trigger gameplay
            }
        });

        //actionListener attached to stop game when user clicks the pause button in game view
        PauseBtn.addActionListener(new ActionListener() {
            /**
             * Event is captured by the listener interface, taking a user's actions e.g.
             * clicking a button, causes an action to occur.
             * @param actionEvent action event descriptor
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                game.pauseGame(); //declares method in game class to stop gameplay
            }
        });

        //actionListener attached to terminate game when user clicks the quit button in game view
        QuitBtn.addActionListener(new ActionListener() {
            /**
             * Event is captured by the listener interface, taking a user's actions e.g.
             * clicking a button, causes an action to occur.
             * @param actionEvent action event descriptor
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                game.quitGame();  //declares method in game class to terminate gameplay
            }
        });

        //actionListener attached to skip current level forward when user clicks the skip forward button in game view
        skipBtn.addActionListener(new ActionListener() {
            /**
             * Event is captured by the listener interface, taking a user's actions e.g.
             * clicking a button, causes an action to occur.
             * @param e action event descriptor
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                game.goLevelForward();  //declares method in game class to progress gameplay forward
            }
        });

        restartBtn.addActionListener(new ActionListener() {
            /**
             * Event is captured by the listener interface, taking a user's actions e.g.
             * clicking a button, causes an action to occur.
             * @param e action event descriptor
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getRestart(); //declares method in game class to restart whole game
            }
        });

        //actionListener attached to skip back a level when user clicks the skip back button in game view
        BackBtn.addActionListener(new ActionListener() {
            /**
             * Event is captured by the listener interface, taking a user's actions e.g.
             * clicking a button, causes an action to occur.
             * @param e action event descriptor
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                game.goLevelBack();  //declares method in game class to move back a level in gameplay
            }
        });

        SaveBtn.addActionListener(new ActionListener() {
            /**
             * Event is captured by the listener interface, taking a user's actions e.g.
             * clicking a button, causes an action to occur.
             * @param e action event descriptor
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                game.saveGame();
            }
        });

        LoadBtn.addActionListener(new ActionListener() {
            /**
             * Event is captured by the listener interface, taking a user's actions e.g.
             * clicking a button, causes an action to occur.
             * @param e action event descriptor
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                GameLoader sr = new GameLoader("data/positions.txt", game);
                try {
                    GameLevel loadedGame = sr.loadGame();
                    game.goToLevel(loadedGame);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        ScoreBtn.addActionListener(new ActionListener() {
            /**
             * Event is captured by the listener interface, taking a user's actions e.g.
             * clicking a button, causes an action to occur.
             * @param e action event descriptor
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame displayScore = new JFrame("High Scores"); //new JFrame to display scores
                HighScore highScore = new HighScore(game); //attaches the game world to the highScore panel
                displayScore.getContentPane().add(highScore.getPnlScores()); //add high score panel to the JFrame
                displayScore.pack(); //packs the display within the view of the frame
                displayScore.setSize(500, 500); //set size of the frame
                displayScore.setLocationRelativeTo(null); //centre the frame in the middle of the screen when pressed
                displayScore.setVisible(true); //make the frame visible to the user
                displayScore.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //hides the display out of view when closed
            }
        });
    }
}

