import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
    private final static int menu = 0;
    private final static int game = 0;

    public Main ( String name ) {
        super( name );
        addState( new Menu(menu) );
    }

    public static void main(String [] arguments) {
        try {
            AppGameContainer app = new AppGameContainer( new Main( "Jets" ) );
            app.setDisplayMode( 1024, 768, false );
            app.setTargetFrameRate( 60 );
            app.setVSync( true );
            app.setShowFPS( true );
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initStatesList ( GameContainer gameContainer ) throws SlickException {
        this.getState( menu ).init( gameContainer, this );
        this.enterState( menu );
    }
}