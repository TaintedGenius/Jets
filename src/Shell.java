import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created with IntelliJ IDEA.
 * User: Vlad
 * Date: 19.10.13
 * Time: 13:16
 * To change this template use File | Settings | File Templates.
 */
public class Shell extends BasicObject {
    private int timeToDestroy;
    private int timer;

    private double calCos;
    private double calSin;

    private float speed;

    public Shell ( double currentAngle, float x, float y, int timeToDestroy, float speed, Image image ) throws SlickException {
        super( x, y, image );
        this.timeToDestroy = timeToDestroy;
        this.speed = speed;
        this.image = image;

        calCos = Math.cos( currentAngle ) * speed;
        calSin = Math.cos( currentAngle ) * speed;

        image.setRotation( (float) -currentAngle );
        timer = 0;
    }

    public boolean move() {
        timer++;
        x += calCos;
        y -= calSin;
        if ( timer > timeToDestroy ) {
            try {
                image.destroy();
            } catch ( SlickException e ) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }


    @Override
    void draw () {
        image.draw();
    }
}
