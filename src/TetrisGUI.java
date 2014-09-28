import java.awt.*;
import javax.swing.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

import javax.swing.JOptionPane;
//		JOptionPane.showMessageDialog(null,"GRID OKAY");

public class TetrisGUI extends JPanel
{
	/////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////
	// THESE 3 CAN STAY HERE
	final int ARRAY_ROWS = 13;	// Specifies Number of Rows
	final int ARRAY_COLS = 8;	// Specifies Number of Cols
	final int BOX_SIZE = 50;	// Specifies Box Size
	//////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////

	Grid2d playfield;			// Playingfield
	Grid2d testfield;			// This is the test playfield

	Shape cur_piece;
	Shape Preview;

	int score;					// Players Score
	int linesfound=0;

	public TetrisGUI()	// Constructor this is called when object is created
	{
		playfield = new Grid2d(ARRAY_ROWS,ARRAY_COLS);	// This creates an empty Grid
		testfield = new Grid2d(ARRAY_ROWS,ARRAY_COLS);
		score = 0;		//Initalise Score to 0

		// GENERATE NEW SHAPE
		Preview = new Shape(ARRAY_ROWS,ARRAY_COLS);
		cur_piece = Preview;
		Preview = new Shape(ARRAY_ROWS,ARRAY_COLS);
	//	testfield = playfield;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent( g );
		// Ensures that JPanel is properly shown on the
		// screen before we start drawing on it

		////////////////////////////////////
		// Place what you want to draw here
		////////////////////////////////////

		drawPlayField(g);			// Draw Current State of PlayingField
		drawPlayArea(g);			// Draw Rectangle & Grid it

		// First AND grids to see there are no overlaps
		// Moveable is set false when shape goes as far down as it can
		// If no overlaps or grids to combine grids to display to user

		if( testfield.andGrid2d (cur_piece)
			//cur_piece.canMoveDown(testfield)
			&& cur_piece.isMoveable() == true)
			// SHAPE CAN MOVE HERE
		{
			playfield = testfield.orGrid2d(cur_piece);
			cur_piece.moveDown();
		}
		else									// Shape cant move anymore
		{
			testfield =(playfield);		// Save contents of playfield int testfield
			linesfound = checkLine(testfield);
			if(linesfound > 0)					// Test for line
			{
				score=score+linesfound;
			}

			playfield= (testfield);
			cur_piece = Preview;							// Take next piece
			Preview = new Shape(ARRAY_ROWS,ARRAY_COLS);		// Genereate new next piece
		}

		drawPlayField(g);			// Draw Current State of PlayingField
		drawPlayArea(g);			// Draw Rectangle & Grid it
		drawScore(g, 500, 300);

		drawPreview(g,30,400);

	}

	public void drawPlayField(Graphics g)			// Draws Playing Field
	{
		for ( int col_search = 0; col_search < playfield.maxCol(); col_search++)
		{
			for( int row_search = 0; row_search < playfield.maxRow(); row_search++)
			{

				int x = row_search;
				int y = col_search;

				if ( playfield.getElement(x,y) == true )
				{
					g.setColor(cur_piece.getColor());
				}
				else
				{
					g.setColor(Color.white);
				}

/*				switch(playfield.getElement(x,y))		// Determine Color
				{
					case 0:			// Empty
						g.setColor(Color.white);
					break;

					case 1:
						g.setColor(Color.red);
					break;

					case 2:
						g.setColor(Color.blue);
					break;
					case 3:
						g.setColor(Color.green);
					break;
					case 4:
						g.setColor(Color.orange);
					break;

					case 5:
						g.setColor(Color.yellow);
					break;

					case 6:
						g.setColor(Color.black);
					break;

					case 7:
						g.setColor(Color.cyan);
					break;
				}*/
				g.fillRect(y*(BOX_SIZE),x*(BOX_SIZE),BOX_SIZE,BOX_SIZE);		// Draw Box
			}
		}
	}

	public void drawPlayArea(Graphics g)	// Draw Rectangle & Grid it
	{
		g.setColor(Color.black);
		g.drawRect(0,0,BOX_SIZE*ARRAY_COLS,BOX_SIZE*ARRAY_ROWS);

		for(int line = 0; line < BOX_SIZE*ARRAY_COLS; line = line + BOX_SIZE)		// draw Y lines (UP/DOWN)
		{
			//x1,y1,x2,y2
			g.drawLine(line,0,line,BOX_SIZE*ARRAY_ROWS);
		}

		for(int line = 0; line < BOX_SIZE*ARRAY_ROWS; line = line + BOX_SIZE)
		{
			g.drawLine(0,line,BOX_SIZE*ARRAY_COLS,line);					// draw X lines (LEFT/RIGHT)
		}
	}
	public void drawScore(Graphics g, int x_pos, int y_pos)		// Draws a scorebox
	{
		int x,y;
		FontMetrics fonts;
		String msg = " Score = ";

		g.setFont(new java.awt.Font("TimesRoman",Font.BOLD,22));
		fonts=g.getFontMetrics();
		msg=msg+score;
		y=fonts.getHeight();
		x=fonts.stringWidth(msg);
		g.drawString(msg,x_pos,y_pos);
		g.drawRect(x_pos, (y_pos++ - y),x,y);
	}

	public void drawPreview(Graphics g, int x_pos, int y_pos)	// Draws Preview Piece
	{
		g.setColor(Preview.getColor());
		for ( int r = 0; r < Preview.maxRow(); r++ )
		{
			for ( int c = 0; c < Preview.maxCol(); c++ )
			{
				if( Preview.getElement(r,c) == true )
				{
					g.fillRect(y_pos + c*(50), x_pos + r*(50),50,50);
				}
			}
		}
	}
///////////////////////////////////////////////////////////////////////////////////
	public int checkLine(Grid2d a)
	{	// CHECK FOR A LINE, REMOVE IT & MOVE ALL ELEMENTS DOWN ONE
		int linecount=0;	// Counts number of lines
		boolean line;		// Set if a line is found
		int row,column;

		for(row = 0 ; row < a.maxRow(); row++)
		{
			line = true;
			for(column = 0; column < a.maxCol(); column++)
			{
				// Go through each column and if true at end delete line
				line = line && a.getElement(row,column);
			}
			if(line == true)		// Found a line
			{
				linecount++;
				removeLine(row,a);
			}
		}
		return linecount;			// returns number of lines found
	}

	public void removeLine(int row,Grid2d a)
	{
		// Remove it & Move everything ABOVE!!! down 1
		// SET TOP ELEMENTS TO 0
		for ( row = row; row >= 0 ; row--)
		{
			for( int col = 0; col < a.maxCol(); col++)
			{
				// IF LINE 5 WAS CLEARED WE WANT TO SET LINE 5
				// WITH LINE 4 etc,. this is we want to set r = r-1
				if( row == 0 )
				{
					a.setElement(0,col,false);		// CLEAR TOP LINE
				}
				else
				{
					boolean value_above = a.getElement(row-1,col);
					a.setElement(row,col,value_above);
				}
			}
		}
	}
}