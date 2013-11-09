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

    public Coordinates( int number, float x, float y, float angle ) {
        this.number = number;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    @Override
    public boolean equals( Object o ) {
        return number == ( (Coordinates) o ).number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf( number ) + " " + String.valueOf( x ) + " " + String.valueOf( y ) + " " + String.valueOf( angle );
    }
    public Coordinates (  ) {}
}
