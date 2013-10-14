import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created with IntelliJ IDEA.
 * User: Vlad
 * Date: 12.10.13
 * Time: 13:30
 * To change this template use File | Settings | File Templates.
 */
public class Menu extends BasicGameState {
    private int ID;
    private Image image;
    private TextField textField;
    private TextField textFieldTwo;
    private boolean textOneSelect = true;

    private Ship ship;
    private Map map;

    public Menu ( int ID ) {
        this.ID = ID;
    }

    @Override
    public int getID () {
        return ID;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void init ( GameContainer gameContainer, StateBasedGame stateBasedGame ) throws SlickException {
        textField = new TextField(gameContainer, gameContainer.getDefaultFont(),0, 0, 200, 40);
        textField.setBackgroundColor( Color.gray );

        textFieldTwo = new TextField(gameContainer, gameContainer.getDefaultFont(),0, 60, 200, 40);
        textFieldTwo.setBackgroundColor( Color.gray );
        textField.setFocus( true );

        ship = new Ship( 5, 2, "ship.png" );

        map = new Map( 5, "map.jpg" );
    }

    @Override
    public void render ( GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics ) throws SlickException {
        //image.draw();
        //map.updateMap();
        ship.draw();

        ship.updateAngle( Mouse.getX(), Mouse.getY() );
    }

    @Override
    public void update ( GameContainer gameContainer, StateBasedGame stateBasedGame, int i ) throws SlickException {
        Input input = gameContainer.getInput();


        if ( input.isKeyDown( Input.KEY_W ) ) {
            //map.moveForward( ship.getCurrentAngle() );
        }
    }
}
