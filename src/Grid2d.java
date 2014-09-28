import java.awt.Graphics;
import javax.swing.JPanel;

import javax.swing.JOptionPane;
public class Grid2d
{
	public boolean grid[][];					// Contains Array for Grid
	private int row,col;						// Row & Column values for Grid

	public Grid2d( int xvalue, int yvalue)		// Called when grid is created
	{
		row = xvalue;
		col = yvalue;
		grid = new boolean[row][col];

		fillGrid(false);			// Initalise Grid with 0's
	}	// END OF CONSTRUCTOR

	public void fillGrid(boolean num)
	{
		for( int row_search = 0; row_search < row; row_search++)
		{
			for ( int col_search = 0; col_search < col; col_search++)
			{
				grid[row_search][col_search] = num;		// Nothing in Grid
			}
		}
	}

	public Grid2d orGrid2d(Grid2d a)		//OR's 2 Grids & returns a grid
	{
		// This is used to combine playfield & shape
		Grid2d or_output = new Grid2d(13,8);

		for(int r = 0 ; r < a.maxRow() ; r++)
		{
			for(int c = 0; c < a.maxCol(); c++)
			{
				// Go through each element bitwise or'ing every element
				boolean num=a.getElement(r,c) || this.getElement(r,c);
				or_output.setElement(r,c,num);
			}
		}

		return or_output;
	}

	public boolean andGrid2d( Grid2d a )
	{
		boolean canmove = true;					// Shape can move here

		for(int r = 0 ; r < a.maxRow() ; r++)
		{
			for(int c = 0; c < a.maxCol(); c++)
			{
				// Go through each element bitwise and'ing every element
				if( (this.getElement(r,c) && a.getElement(r,c)) == true)
				{
					canmove = false;
				}
			}
		}
		return canmove;
	}

/*	public boolean checkLine()
	{	// CHECK FOR A LINE, REMOVE IT & MOVE ALL ELEMENTS DOWN ONE
		boolean line=false;	// Set if a line is found
		int row,column;

		for(row = 0 ; row < maxRow(); row++)
		{
			line = true;
			for(column = 0; column < maxCol(); column++)
			{
				// Go through each column and if true at end delete line
				line = line && getElement(row,column);
			}
			if(line == true)		// Found a line
			{
				removeLine(row,column);
//				checkLine();		// Check for another line cos there might be one
			}
		}
		setElement(0,0,true);
		return line;
	}

	public void removeLine(int row, int col)
	{
		// Remove it & Move everything ABOVE!!! down 1
		// SET TOP ELEMENTS TO 0
		for ( row = row; row >= 0 ; row--)
		{
			for( col = col; col < maxCol(); col++)
			{
				// IF LINE 5 WAS CLEARED WE WANT TO SET LINE 5
				// WITH LINE 4 etc,.
				if( row-1 < 0 )
				{
					setElement(0,col,false);		// CLEAR TOP LINE
				}
				else
				{
					boolean value_above = getElement(row-1,col);
					setElement(row,col,value_above);
				}
			}
		}
	}
*/
	public void copyGrid(Grid2d tobecopied)
	{
		for (int r = 0; r<maxRow(); r++)
		{
			for(int c =0; c<maxCol(); c++)
			{
				boolean value = getElement(r,c);
				setElement(r,c,value);
			}
		}
	}
	public boolean gameOver()			//Tests whether game should continue or end
	{
		boolean gameOver = false;
		return gameOver;
	}
	public void setElement(int row_s,int col_s, boolean num)
	{
		grid[row_s][col_s] = num;
	}
	public boolean getElement(int row_s,int col_s)
	{
		return grid[row_s][col_s];
	}
	public int maxRow(){return row;}
	public int maxCol(){return col;}
}