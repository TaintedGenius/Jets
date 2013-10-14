import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created with IntelliJ IDEA.
 * User: Vlad
 * Date: 14.10.13
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */
public class Map {
    private Image image;
    private float shiftX;
    private float shiftY;
    private float shipVelocity;

    private double calCos;
    private double calSin;

    public Map ( float shipVelocity, String mapPath ) throws SlickException {
        image = new Image( mapPath );
        shiftX = 0;
        shiftY = 0;

        this.shipVelocity = shipVelocity;
    }

    public void updateMap() {
        image.draw( (int) shiftX, (int) shiftY );
    }

    public void moveForward( float shipAngle ) {
        calCos = Math.cos( shipAngle ) * shipVelocity;
        calSin = Math.sin( shipAngle ) * shipVelocity;
                                                        //weight
        if ( shiftX - calCos <= 0 && shiftX - calCos > -1920 + 500 ) {
            shiftX -= calCos;
        }
                                                        //height
        if ( shiftY + calSin <= 0 && shiftY + calSin > -1200 + 400 ) {
            shiftY += calSin;
        }
    }

}
