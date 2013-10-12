import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
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

    public Menu ( int ID ) {
        this.ID = ID;
    }

    @Override
    public int getID () {
        return ID;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void init ( GameContainer gameContainer, StateBasedGame stateBasedGame ) throws SlickException {
        image = new Image( "ship.png" );
        textField = new TextField(gameContainer, gameContainer.getDefaultFont(),0, 0, 200, 40);
        textField.setBackgroundColor( Color.gray );

        textFieldTwo = new TextField(gameContainer, gameContainer.getDefaultFont(),0, 60, 200, 40);
        textFieldTwo.setBackgroundColor( Color.gray );
        textField.setFocus( true );
    }

    int x, y;
    @Override
    public void render ( GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics ) throws SlickException {
        image.draw();
        textField.render( gameContainer, graphics );
        textFieldTwo.render( gameContainer, graphics );
        graphics.drawString( x + " " + y, 200, 200 );
        if ( textOneSelect ) {
            textField.setFocus( true );
        } else {
            textFieldTwo.setFocus( true );
        }

    }

    @Override
    public void update ( GameContainer gameContainer, StateBasedGame stateBasedGame, int i ) throws SlickException {
        x = Mouse.getX();
        y = Mouse.getY();


        if ( Mouse.getX() > 0 && Mouse.getX() < 200 && Mouse.getY() > 360 && Mouse.getY() < 400) {
            if ( Mouse.isButtonDown( 0 )) {
                textOneSelect = true;
            }
        }

        if ( Mouse.getX() > 0 && Mouse.getX() < 200 && Mouse.getY() > 300 && Mouse.getY() < 340) {
            if ( Mouse.isButtonDown( 0 )) {
                textOneSelect = false;
            }
        }
    }
}
