import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import sun.plugin.javascript.navig.ImageArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vlad
 * Date: 12.10.13
 * Time: 13:30
 * To change this template use File | Settings | File Templates.
 */
public class Game extends BasicGameState {
    private int ID;

    private List<Integer> keyPressList = new ArrayList<Integer>( 4 );
    private List<Integer> keyPressedList = new ArrayList<Integer>( 4 );
    private List<Integer> currentKey = new ArrayList<Integer>( 4 );




    private Ship ship;
    private ShellContainer shellContainer;
    private Image shellImage;
    private Map map;


    private int timeBetweenShot = 0;

    public Game ( int ID ) {
        this.ID = ID;
    }

    @Override
    public int getID () {
        return ID;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void init ( GameContainer gameContainer, StateBasedGame stateBasedGame ) throws SlickException {
        // float speed, float angleSpeed, String fileName, String mapPath
        //gameContainer.setFullscreen( true );
        map = new Map( "map.jpg", gameContainer.getHeight(), gameContainer.getWidth() );
        ship = new Ship( 5.0f, 3.0f, "ship.png", gameContainer.getHeight(), gameContainer.getWidth(), map );
        shellContainer = new ShellContainer();
        shellImage = new Image("laser.png");
    }

    @Override
    public void render ( GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics ) throws SlickException {
        //image.draw();
        //map.updateMap();
        ship.draw();
        ship.updateAngle( Mouse.getX(), Mouse.getY() );
        shellContainer.updateShells( map.getShiftX(), map.getShiftY() );
    }


    public void editNew( Input input ) {
        keyPressList.removeAll( keyPressList );
        if (input.isKeyDown( Input.KEY_A ) ) {
            keyPressList.add( Input.KEY_A );
        }
        if ( input.isKeyDown( Input.KEY_S ) ) {
            keyPressList.add( Input.KEY_S );
        }
        if ( input.isKeyDown( Input.KEY_W ) ) {
            keyPressList.add( Input.KEY_W );
        }
        if ( input.isKeyDown( Input.KEY_D ) ) {
            keyPressList.add( Input.KEY_D );
        }
    }

    public void editOld( Input input ) {
        keyPressedList.removeAll( keyPressedList );
        if (input.isKeyDown( Input.KEY_A ) ) {
            keyPressedList.add( Input.KEY_A );
        }
        if ( input.isKeyDown( Input.KEY_S ) ) {
            keyPressedList.add( Input.KEY_S );
        }
        if ( input.isKeyDown( Input.KEY_W ) ) {
            keyPressedList.add( Input.KEY_W );
        }
        if ( input.isKeyDown( Input.KEY_D ) ) {
            keyPressedList.add( Input.KEY_D );
        }
    }

    @Override
    public void update ( GameContainer gameContainer, StateBasedGame stateBasedGame, int i ) throws SlickException {
        Input input = gameContainer.getInput();

        editNew( input );

        if ( keyPressList.isEmpty() ) {
            currentKey.removeAll( currentKey );
        } else {
            if ( keyPressList.size() > keyPressedList.size() ) {
                for ( Integer keyPress : keyPressList ) {
                    if ( !keyPressedList.contains( keyPress ) ) {
                        currentKey.add( keyPress );
                    }
                }
            } else if ( keyPressList.size() < keyPressedList.size() ) {
                for ( Integer keyPressed : keyPressedList ) {
                    if ( !keyPressList.contains( keyPressed ) ) {
                        currentKey.remove( keyPressed );
                    }
                }
            }
            if ( currentKey.get( currentKey.size() - 1 ) == Input.KEY_A ) {
                ship.moveLeft();
            }
            if ( currentKey.get( currentKey.size() - 1 ) == Input.KEY_S ) {
                ship.moveBack();
            }
            if ( currentKey.get( currentKey.size() - 1 ) == Input.KEY_W ) {
                ship.moveForward();
            }
            if ( currentKey.get( currentKey.size() - 1 ) == Input.KEY_D ) {
                ship.moveRight();
            }
        }

        if ( timeBetweenShot > 0 ) {
            timeBetweenShot--;
        }
        if ( input.isMouseButtonDown( Input.MOUSE_LEFT_BUTTON ) && timeBetweenShot == 0 ) {
            timeBetweenShot = 6;
            ship.changeRadius();
            shellContainer.add( new Shell( ship.getCurrentAngle() + ship.getAccuracy(),
                    ship.getRadius() ,
                    ship.getX(), ship.getY(),
                    30, 14.0f,
                    shellImage.copy() ) );
        }

        editOld( input );
    }
}
