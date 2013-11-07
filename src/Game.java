import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

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

    private Image shipImage;
    private int number;

    private Server server;

    private int timeBetweenShoot = 0;

    public Game ( int ID ) {
        this.ID = ID;

        server = new Server();
        shellContainer = new ShellContainer();
    }

    @Override
    public int getID () {
        return ID;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void init ( GameContainer gameContainer, StateBasedGame stateBasedGame ) throws SlickException {
        shipImage = new Image( "ship.png" );
        map = new Map( "map.jpg", gameContainer.getHeight(), gameContainer.getWidth() );
        ship = new Ship( 5.0f, 3.0f, shipImage, gameContainer.getHeight(), gameContainer.getWidth(), map );
        shellImage = new Image( "shell.png" );
    }

    private ArrayList<Coordinates> getShipsPosition( String line ) {
        if ( line == null ) {
            return null;
        }
        String[] splitLine = line.split( ";" );
        ArrayList<Coordinates> coordinatesArrayList = new ArrayList<Coordinates> ();
        for ( int i = 0; i < splitLine.length; ) {
            int num = Integer.parseInt( splitLine[i++] );
            if ( num == number ) {
                i += 3;
                continue;
            }
            Coordinates coordinates = new Coordinates();
            coordinates.number = num;
            coordinates.x = Float.parseFloat( splitLine[i++] );
            coordinates.y = Float.parseFloat( splitLine[i++] );
            coordinates.angle = Float.parseFloat(splitLine[i++]);
            coordinatesArrayList.add( coordinates );
        }
        return  coordinatesArrayList;
    }

    private void drawShips( ArrayList<Coordinates> coordinatesArrayList ) {
        if ( coordinatesArrayList == null || coordinatesArrayList.size() == 0 ) return;
        for ( Coordinates coordinates : coordinatesArrayList ) {
            Image tempImg = shipImage.copy();
            tempImg.setRotation( (float) Math.toDegrees( -coordinates.angle ) );
            float dX, dY;
            if ( ship.getX() < ship.getHalfWidth() ) {
                dX = coordinates.x - tempImg.getCenterOfRotationX();
            } else if ( ship.getX() >= map.getMapWidth() - ship.getHalfWidth() ) {
                dX = coordinates.x - map.getMapWidth() + ship.getWidth() - tempImg.getCenterOfRotationX();
            } else {
                dX = coordinates.x - ship.getShiftX() - tempImg.getCenterOfRotationX();
            }
            if ( ship.getY() < ship.getHalfHeight() ) {
                dY = coordinates.y - tempImg.getCenterOfRotationY();
            } else if ( ship.getY() >= map.getMapHeight() - ship.getHalfHeight() ) {
                dY = coordinates.y + ship.getHeight() - map.getMapHeight() - tempImg.getCenterOfRotationY();
            } else {
                dY = coordinates.y - ship.getShiftY() - tempImg.getCenterOfRotationY();
            }
            tempImg.draw( dX, dY );
        }
    }

    private boolean isCode( String line ) {
        if ( line == null ) return true;
        String[] splitLine = line.split( ";" );
        if ( splitLine[0].equals( Code.SET_ID ) ) {
            number = Integer.parseInt( String.valueOf( splitLine[1] ) );
            return true;
        }
        return false;
    }

    @Override
    public void render ( GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics ) throws SlickException {
        //image.draw();
        //map.updateMap();

        String line = server.relieveData();
        ship.draw();
        graphics.drawString( line == null ? "null" : line, 0, 50 );
        if ( !isCode( line ) ) {
            drawShips( getShipsPosition( line ) );
        }
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

        if ( timeBetweenShoot > 0 ) {
            timeBetweenShoot--;
        }
        if ( input.isMouseButtonDown( Input.MOUSE_LEFT_BUTTON ) && timeBetweenShoot == 0 ) {
            timeBetweenShoot = 6;
            ship.changeRadius();
            shellContainer.add( new Shell(
                    ship.getCurrentAngle() + ship.getAccuracy(),
                    ship.getRadius(),
                    ship.getX(), ship.getY(),
                    30, 14.0f,
                    shellImage.copy() ) );
        }
        ship.updateAngle( Mouse.getX(), Mouse.getY() );
        server.sendData( number, ship.getX(), ship.getY(), ship.getCurrentAngle() );

        editOld( input );
    }
}
