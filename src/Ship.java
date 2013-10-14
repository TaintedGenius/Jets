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
    private float velocity;

    private int angleSpeed;

    private int finalAngle;
    private int currentAngle;

    private Image image;

    public Ship( float velocity, int angleSpeed, String fileName ) throws SlickException {
        image = new Image( fileName );

        this.velocity = velocity;
        this.angleSpeed = angleSpeed;

        x = 182;
        y = 132;
        finalAngle = 0;
        currentAngle = 0;
    }

    private int makeDiv ( int value, int div ) {
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

    public float updateAngle( int mouseX, int mouseY ) {
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

        return currentAngle;
    }

    public void moveForward() {

    }

    public void draw( ) {
        image.draw( (int) x, (int) y );
    }
}
