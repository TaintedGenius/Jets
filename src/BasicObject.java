import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created with IntelliJ IDEA.
 * User: Vlad
 * Date: 15.10.13
 * Time: 20:26
 * To change this template use File | Settings | File Templates.
 */
public abstract class BasicObject {
    protected float x;
    protected float y;
    private int radius;

    private int mapHeight;
    private int mapWeight;
    protected Image image;

    public BasicObject ( float x, float y, String imagePath ) throws SlickException  {
        this.x = x;
        this.y = y;
        image = new Image( imagePath );
    }

    public BasicObject ( float x, float y, int mapHeight, int mapWeight, String imagePath ) throws SlickException {
        this.x = x;
        this.y = y;
        this.mapHeight = mapHeight;
        this.mapWeight = mapWeight;
        image = new Image( imagePath );
    }

}
