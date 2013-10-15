import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Image;

import java.awt.*;
import java.awt.Graphics;

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

    private double angleSpeed;
    private float speedRotate;

    private double finalAngle;
    private double currentAngle;

    public Ship( float speed, float angleSpeed, String fileName ) throws SlickException {
        super( 250, 200, 1920, 1200, fileName );

        this.angleSpeed =  angleSpeed * ONE_DEGREE;
        this.speedRotate = angleSpeed;

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

    public void updateAngle( int mouseX, int mouseY,org.newdawn.slick.Graphics graphics) {
        finalAngle =  Math.atan( ( (400 - mouseY ) - y ) / ( mouseX - x ) );
        if ( mouseX >= x ) {
            finalAngle = -finalAngle;
            if ( (400 - mouseY ) >= y ) {
                finalAngle = Math.PI * 2 + finalAngle;
            }
        } else {
            finalAngle = Math.PI - finalAngle;
        }
       // finalAngle = makeDiv( finalAngle, angleSpeed );
        double temporaryAngle = Math.abs( finalAngle - currentAngle );

        graphics.drawString( "Final: " + String.valueOf( finalAngle ), 0, 0 );
        graphics.drawString( "Current: " + String.valueOf( currentAngle ), 0, 15 );
        graphics.drawString( "Temo: " + String.valueOf( temporaryAngle ), 0, 30 );
        //graphics.drawString( "x : " + String.valueOf( x ) + "y: " + String.valueOf( y ), 0, 45 );



        if ( temporaryAngle > angleSpeed ) {
            if ( temporaryAngle < CIRCLE_DEGREE - temporaryAngle ) {
                if ( currentAngle < finalAngle ) {
                    currentAngle += angleSpeed;
                    image.rotate(  -speedRotate );
                } else if ( currentAngle > finalAngle ) {
                    currentAngle -= angleSpeed;
                    image.rotate( speedRotate );
                }
            } else {
                if ( currentAngle < finalAngle ) {
                    if ( currentAngle >= 0 && currentAngle < angleSpeed ) {
                        currentAngle = CIRCLE_DEGREE;
                    }
                    currentAngle -= angleSpeed;
                    image.rotate( speedRotate );
                } else if ( currentAngle > finalAngle ) {
                    if ( currentAngle >= CIRCLE_DEGREE - angleSpeed ) {
                        currentAngle = -angleSpeed;
                    }
                    currentAngle += angleSpeed;
                    image.rotate( -speedRotate );
                }
            }
        }
    }

    void moveForward( ) {
        double calCos = Math.cos( currentAngle ) * 2;
        double calSin = Math.sin( currentAngle ) * 2;

        if ( ( x + calCos >= image.getCenterOfRotationX() && x + calCos < 1920 - image.getCenterOfRotationX() )  &&
                ( y - calSin >= image.getCenterOfRotationY() && y - calSin < 1200 - image.getCenterOfRotationY() ) ) {
            x += calCos;
            y -= calSin;
        }
    }

    public float getX() {
        return (float) Math.toRadians( currentAngle );
    }

    public void draw( ) {
        image.draw( x - image.getCenterOfRotationX(),  y - image.getCenterOfRotationY() );
    }
}
