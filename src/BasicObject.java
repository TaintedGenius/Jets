import org.newdawn.slick.Graphics;
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
    private float radius;

    protected double currentAngle;

    private int mapHeight;
    private int mapWeight;
    protected Image image;


    public BasicObject ( double currentAngle, float x, float y, String imagePath ) throws SlickException {
        this.x = x;
        this.y = y;
        this.currentAngle = currentAngle;
        image = new Image( imagePath );
        radius = x - ( x / 10 );
    }

    public BasicObject ( float x, float y, Image image ) throws SlickException {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public void changeRadius () {
        radius = (float) Math.random() * ( image.getCenterOfRotationX() / 2) + image.getCenterOfRotationX() / 2;
    }

    public float getRadius() {
        return radius;
    }

    public void setMapSize ( int mapHeight, int mapWeight ) {
        this.mapHeight = mapHeight;
        this.mapWeight = mapWeight;
    }

    public boolean canMove ( double x, double y ) {
        return x >= image.getCenterOfRotationX() && x < mapWeight - image.getCenterOfRotationX()
                && y >= image.getCenterOfRotationY() && y < mapHeight - image.getCenterOfRotationY();
    }


    public double getCurrentAngle() {
        return currentAngle;
    }

}
