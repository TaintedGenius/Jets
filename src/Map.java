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
}
