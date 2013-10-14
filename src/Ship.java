import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created with IntelliJ IDEA.
 * User: Vlad
 * Date: 14.10.13
 * Time: 0:16
 * To change this template use File | Settings | File Templates.
 */
public class Ship {
    private final int CIRCLE_DEGREE = 360;

    private float x;
    private float y;

    private int angleSpeed;
    private float speed;

    private int finalAngle;
    private int currentAngle;

    private Image image;

    public Ship( float speed, int angleSpeed, String fileName ) throws SlickException {
        image = new Image( fileName );

        this.speed = speed;
        this.angleSpeed = angleSpeed;

        x = 182;
        y = 132;
        finalAngle = 0;
        currentAngle = 0;
    }

    private int makeDiv( int value, int div ) {
        if ( value % div == 0 ) {
            return value;
        } else {
            if ( value - ( value / div ) * div < ( value / div + 1 ) * div - value ) {
                return ( value / div ) * div;
            } else {
                return ( value / div + 1 ) * div;
            }
        }
    }

    public void updateAngle( int mouseX, int mouseY ) {
        finalAngle = (int) Math.round( Math.toDegrees( Math.atan( (float) ( mouseY - 200 ) / ( mouseX - 250 ) ) ) );
        finalAngle = makeDiv( finalAngle, angleSpeed );

        if ( mouseX < 250 ) {
            finalAngle = CIRCLE_DEGREE / 2 + finalAngle;
        } else if ( mouseY < 200 ) {
            finalAngle = CIRCLE_DEGREE + finalAngle;
        }

        if ( finalAngle == CIRCLE_DEGREE ) {
            finalAngle = 0;
        }

        if ( currentAngle > finalAngle ) {
            if ( currentAngle - finalAngle < ( CIRCLE_DEGREE - currentAngle ) + finalAngle ) {
                currentAngle -= angleSpeed;
                image.rotate( angleSpeed );
            } else {
                if ( currentAngle >= CIRCLE_DEGREE - angleSpeed ) {
                    currentAngle = -angleSpeed;
                }
                currentAngle += angleSpeed;
                image.rotate( -angleSpeed );
            }
        } else if ( currentAngle < finalAngle ) {
            if ( finalAngle - currentAngle < ( CIRCLE_DEGREE - finalAngle ) + currentAngle ) {
                if ( currentAngle >= CIRCLE_DEGREE - angleSpeed ) {
                    currentAngle = -angleSpeed;
                }
                currentAngle += angleSpeed;
                image.rotate( -angleSpeed );
            } else {
                if ( currentAngle == 0 ) {
                    currentAngle = CIRCLE_DEGREE;
                }
                currentAngle -= angleSpeed;
                image.rotate( angleSpeed );
            }
        }
    }

    public void moveForward() {
        double calCos = Math.cos( currentAngle ) * speed;
        double calSin = Math.sin( currentAngle ) * speed;
                                            //weight
        if ( x - calCos >= 0 && x - calCos < 1920 - 500 ) {
            x -= calCos;
        }
                                            //height
        if ( y + calSin >= 0 && y + calSin < 1200 - 400 ) {
            y += calSin;
        }
    }

    public float getX() {
        return (float) Math.toRadians( currentAngle );
    }

    public void draw( ) {
        image.draw( (int) x, (int) y );
    }
}
