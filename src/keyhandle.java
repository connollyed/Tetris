import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
public class keyhandle implements KeyListener
{
	TetrisGUI GUI;

	public keyhandle(TetrisGUI p , JFrame tetris)
	{
		GUI = p;
		tetris.addKeyListener( this );
	}

	// This is called when a key is pressed
	public void keyPressed( KeyEvent event )
	{
		int key = event.getKeyCode();

		if( key == KeyEvent.VK_LEFT )		// If left button was pressed
		{
			GUI.cur_piece.moveLeft();		// MOVE LEFT
		}

		if (key == KeyEvent.VK_RIGHT )	// If right button was pressed
		{
			GUI.cur_piece.moveRight();		// MOVE RIGHT
		}

		if (key == KeyEvent.VK_DOWN )		// If Down was pressed
		{
			GUI.cur_piece.moveDown();		// quicker
		}
//		panel.repaint();
	}
	// This is called when a key is released
	public void keyReleased (KeyEvent event )
	{
//		GUI.repaint();
	}

	// This is called when a key is typed
	// e.g. not called when shift or control is pressed
	public void keyTyped(KeyEvent event)
	{
		int key = event.getKeyChar();

		if (key == 'a' || key == 'A')
		{
			GUI.cur_piece.rotateLeft();			// ROTATE SHAPE LEFT
		}
		if (key == 'd' || key == 'D')
		{
			GUI.cur_piece.rotateRight();		// ROTATE SHAPE RIGHT
		}
	}
}