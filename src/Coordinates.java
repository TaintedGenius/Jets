/**
 * Created with IntelliJ IDEA.
 * User: Vlad
 * Date: 21.10.13
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */
public class Coordinates {
    public int number;
    public float x;
    public float y;
    public float angle;
    public long time;

    public Coordinates ( float x, float y, float currentAngle ) {
        this.x = x;
        this.y = y;
        this.angle = currentAngle;
    }
    public Coordinates (  ) {}
}
