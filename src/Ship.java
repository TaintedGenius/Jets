import org.newdawn.slick.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vlad
 * Date: 14.10.13
 * Time: 0:16
 * To change this template use File | Settings | File Templates.
 */
public class Ship extends BasicObject {
    private final double CIRCLE_DEGREE = Math.PI * 2;
    private final double ONE_DEGREE = 0.01745329252;
    private final double HALF_PI = Math.PI / 2;

    private double angleSpeed;
    private double speed;

    private double finalAngle;

    private int height;
    private int weight;
    private Map map;

    private float accurent;

    public Ship( float speed, float angleSpeed, Image image, int height, int weight, Map map ) throws SlickException {
        super( 0, weight / 2, height / 2, image );

        this.map = map;
        this.angleSpeed =  angleSpeed * ONE_DEGREE;
        this.speed = speed;
        this.weight = weight;
        this.height = height;
                     //String mapPath, int screenWidth, int screenHeight
        super.setMapSize( map.getMapHeight(), map.getMapWidth() );

        finalAngle = 0;
        accurent = 0.051f;
    }


    public void updateAngle( int mouseX, int mouseY ) {
        finalAngle =  Math.atan( ( ( height - mouseY + map.getShiftY() ) - y ) / ( ( mouseX + map.getShiftX()) - x ) );
        if ( mouseX + map.getShiftX() >= x ) {
            finalAngle = -finalAngle;
            if ( ( height - mouseY + map.getShiftY() ) >= y ) {
                finalAngle = Math.PI * 2 + finalAngle;
            }
        } else {
            finalAngle = Math.PI - finalAngle;
        }

        double deltaAngle = Math.abs( finalAngle - currentAngle );
        if ( deltaAngle > angleSpeed ) {
            if ( deltaAngle < CIRCLE_DEGREE - deltaAngle ) {
                if ( currentAngle < finalAngle ) {
                    currentAngle += angleSpeed;
                } else if ( currentAngle > finalAngle ) {
                    currentAngle -= angleSpeed;
                }
            } else {
                if ( currentAngle < finalAngle ) {
                    if ( currentAngle >= 0 && currentAngle < angleSpeed ) {
                        currentAngle = (float) CIRCLE_DEGREE + currentAngle;
                    }
                    currentAngle -= angleSpeed;
                } else if ( currentAngle > finalAngle ) {
                    if ( currentAngle >= CIRCLE_DEGREE - angleSpeed ) {
                        currentAngle = (float) ( -angleSpeed + ( CIRCLE_DEGREE - currentAngle ) );
                    }
                    currentAngle += angleSpeed;
                }
            }
        } else if ( deltaAngle < angleSpeed ) {
            currentAngle = (float) finalAngle;
        }
    }

    public void moveForward( ) {
        double calCos = Math.cos( currentAngle ) * speed;
        double calSin = Math.sin( currentAngle ) * speed;

        if ( canMove( x + calCos, y - calSin ) ) {
            x += calCos;
            y -= calSin;
        }
    }

    public double getAccuracy () {
        return  Math.random() * accurent * 2 - accurent;
    }

    public void moveBack( ) {
        double calCos = Math.cos( currentAngle ) * speed;
        double calSin = Math.sin( currentAngle ) * speed;

        if ( canMove( x - calCos, y + calSin ) ) {
            x -= calCos;
            y += calSin;
        }
    }

    public void moveLeft( ) {
        double calCos = Math.cos( currentAngle - HALF_PI ) * speed;
        double calSin = Math.sin( currentAngle - HALF_PI ) * speed;

        if ( canMove( x - calCos, y + calSin ) ) {
            x -= calCos;
            y += calSin;
        }
    }


    public void moveRight( ) {
        double calCos = Math.cos( currentAngle + HALF_PI ) * speed;
        double calSin = Math.sin( currentAngle + HALF_PI ) * speed;

        if ( canMove( x - calCos, y + calSin ) ) {
            x -= calCos;
            y += calSin;
        }
    }

    public void draw( ) {
        map.draw( x, y );
        float dX, dY;
            if ( map.getShiftX() > 0 ) {
                dX = weight / 2;
                if ( map.getShiftX() == map.getMapWidth() - weight ) {
                    dX = x - ( map.getMapWidth() - weight );
                }
            } else {
                dX = x;
            }
            if ( map.getShiftY() > 0 ) {
                dY = height / 2;
                if ( map.getShiftY() == map.getMapHeight() - height ) {
                    dY = y - ( map.getMapHeight() - height );
                }
            } else {
                dY = y;
            }
        image.setRotation( (float) Math.toDegrees( -currentAngle ) );
        image.draw( dX - image.getCenterOfRotationX(), dY - image.getCenterOfRotationY() );
    }

    public float getShiftX() {
        return x - weight / 2;
    }

    public float getShiftY() {
        return y - height / 2;
    }
}
