import javax.swing.JFrame;
import javax.swing.*;

public class TetrisGame
{
	public static void main ( String args[] )
	{
		TetrisGUI myGUI = new TetrisGUI();
		JFrame tetris = new JFrame();
		keyhandle keyhandler = new keyhandle(myGUI, tetris);

		tetris.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		tetris.getContentPane().add( myGUI );
		tetris.setSize ( 800,700 );
		tetris.setVisible ( true );

 		int p = 140;

		while(p > 0)			// CHANGE TO NOT GAMEOVER
		{
			pause(1000);			//Based on level
			tetris.repaint();	//DO STEP		which will call repaint
			p--;
		}
	}
//////////////////////// method pause /////////////////////////
	private static void pause(int numMilliseconds)
	{
		// use a Java libary routine to pause a little
		try
		{
			Thread.sleep( numMilliseconds );
		}
		catch (InterruptedException e)
		{
			//
		}
	}
}