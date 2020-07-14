package GraphicsInterface;

import city.cs.engine.Body;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.WorldView;
import org.jbox2d.common.Vec2;

/**
 * This class is used to listen to steps taken in the program and will be used to
 * track the specific bodies in the game i.e. the main character
 */
public class Track implements StepListener {

    //initialise view and body planes
    private WorldView view;
    private Body body;

    /**
     *
     * @param view the view generated in the world that will be tracked
     * @param body the body generated in the world that will be tracked
     */
    public Track(WorldView view, Body body) {
        this.view = view;
        this.body = body;
    }

    /**
     * Called immediately before each simulation step
     * @param e event descriptor
     */
    @Override
    public void preStep(StepEvent e) {

    }

    /**
     * Called immediately after each simulation step
     * @param e event descriptor
     */
    @Override
    public void postStep(StepEvent e) {
        //set the view to follow the position of the main body player
        view.setCentre(new Vec2(body.getPosition())); //sets view to follow/track the player body in the game rather than a static view
    }

}