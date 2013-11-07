import org.newdawn.slick.geom.Point;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: Vlad
 * Date: 21.10.13
 * Time: 17:27
 * To change this template use File | Settings | File Templates.
 */
public class Server {
    private Socket socket;
    private DataInputStream streamIn;
    private DataOutputStream streamOut;

    public Server() {
        try {

            socket = new Socket( "94.178.41.38", Code.PORT );
            socket.setSoTimeout( 19 );
            socket.setTcpNoDelay( true );
            streamIn = new DataInputStream( new BufferedInputStream( socket.getInputStream() ) );
            streamOut = new DataOutputStream( socket.getOutputStream() );
        } catch ( UnknownHostException uhe ) {
            System.err.println("Host unknown: " + uhe.getMessage());
        } catch (IOException ioe ) {
            System.err.println("Unexpected exception: " + ioe.getMessage());
        } /*catch ( InterruptedException itEx ) {
            System.err.println( " stream wait exception " );
        }*/
    }

    public void sendData( int number, float x, float y, float currentAngle ) {
        StringBuilder sb = new StringBuilder(  );
        try {
            sb.append( String.valueOf( number ) ).append( ";" ).
               append( String.valueOf( x ) ).append( ";" ).
               append( String.valueOf( y ) ).append( ";" ).
               append( String.valueOf( currentAngle ) ).append( ";" );
            streamOut.writeUTF( sb.toString() );
            streamOut.flush();
        } catch ( IOException ioEx ) {
            System.err.println( "Can't send new coordinates of ship" );
        }
    }

    public void sendData( int number, float x, float y, float currentAngle, long currentTime ) {
        StringBuilder sb = new StringBuilder(  );
        try {
            sb.append( String.valueOf( number ) ).append( ";" ).
                    append( String.valueOf( x ) ).append( ";" ).
                    append( String.valueOf( y ) ).append( ";" ).
                    append( String.valueOf( currentAngle ) ).append( ";" ).
                    append( String.valueOf( currentTime ) ).append( ";");
            streamOut.writeUTF( sb.toString() );
            streamOut.flush();
        } catch ( IOException ioEx ) {
            System.err.println( "Can't send new coordinates of ship" );
        }
    }

    public String relieveData() {
        try {
            return streamIn.readUTF();
        } catch ( IOException ioEx ) {
            System.err.println( "line = null" );
            return null;
        }
    }
}
