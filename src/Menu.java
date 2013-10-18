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
           // float speed, float angleSpeed, String fileName, String mapPath
        //gameContainer.setFullscreen( true );
        ship = new Ship( 5.0f, 3.0f, "ship.png", "map.jpg", gameContainer.getHeight(), gameContainer.getWidth() );
    }

    @Override
    public void render ( GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics ) throws SlickException {
        //image.draw();
        //map.updateMap();
        ship.draw();
        ship.updateAngle( Mouse.getX(), Mouse.getY(), graphics );
        graphics.drawString( String.valueOf( gameContainer.getHeight() ), 200, 0 );
    }

    @Override
    public void update ( GameContainer gameContainer, StateBasedGame stateBasedGame, int i ) throws SlickException {
        Input input = gameContainer.getInput();

        boolean isPress = false;
        if ( input.isKeyDown( Input.KEY_W ) ) {
            ship.moveForward();
            isPress = true;
        }
        if ( input.isKeyDown( Input.KEY_S ) ) {
            ship.moveBack();
            isPress = true;
        }

        if ( !isPress ) {
            if ( input.isKeyDown( Input.KEY_A ) ) {
                ship.moveLeft();
            }
            if ( input.isKeyDown( Input.KEY_D ) ) {
                ship.moveRight();
            }
        }
    }
}
