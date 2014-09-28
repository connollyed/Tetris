import java.awt.Color;
import java.util.Random;
public class Shape extends Grid2d
{
	final int ARRAY_ROWS = 13;	// Specifies Number of Rows
	final int ARRAY_COLS = 8;	// Specifies Number of Cols

	private Color myCol;
	private boolean moveable;				// Determines whether shape can move or not
	int Shapetype;								// Determines what type of shape
	int Orientation;						// Determines Orientation of shape

	int row_pos,col_pos;					// row_position & col_position
	public Shape(int row, int col)
	{
		super(row,col);						// Set Superclasses Constructor
		moveable = true;					// The shape can move

		Random Generator = new Random();	//Generate Random Numbers
		Orientation = 1 ;					// Set Default Orientation

		int randomShape = 1 + Generator.nextInt(7);

		Shapetype = randomShape;
		switch (randomShape)
		{
			case 1:		// Box Shape
				myCol = Color.red;
				this.setElement(0,0,true);
				this.setElement(0,1,true);
				this.setElement(1,0,true);
				this.setElement(1,1,true);
			break;

			case 2:		// Shape
				myCol = Color.green;
				this.setElement(1,0,true);			// 00 XX
				this.setElement(0,1,true);			// XX XX
				this.setElement(1,1,true);			// 00 XX
				this.setElement(2,1,true);
			break;

			case 3:		// J Shape
				myCol = Color.orange;
				this.setElement(1,0,true);
				this.setElement(0,1,true);
				this.setElement(1,1,true);
				this.setElement(0,2,true);
			break;

			case 4:		// Z Shape
				myCol = Color.yellow;
				this.setElement(0,0,true);
				this.setElement(0,1,true);
				this.setElement(1,1,true);
				this.setElement(2,1,true);
			break;

			case 5:		// I Shape
				myCol = Color.blue;
				this.setElement(1,0,true);
				this.setElement(1,1,true);
				this.setElement(1,2,true);
				this.setElement(1,3,true);

			break;

			case 6:		// J Shape
				myCol = Color.black;
				this.setElement(2,0,true);
				this.setElement(0,1,true);
				this.setElement(1,1,true);
				this.setElement(2,1,true);

			break;

			case 7:		// S Shape
				myCol = Color.cyan;
				this.setElement(1,0,true);
				this.setElement(1,1,true);
				this.setElement(2,1,true);
				this.setElement(2,2,true);
			break;
		}
		CentreShape();
	}

	public void CentreShape()					// Move Shape to Centre
	{
		row_pos = 0; col_pos = 0;				// Initalise row_pos & col_pos these
												// Are used for rotations
		moveRight();
		moveRight();
		moveRight();
	}

	public void moveDown()
	{
		Grid2d Temp;						//TEMPORARY SHAPE that contains new position
		Temp = new Grid2d(ARRAY_ROWS,ARRAY_COLS);

		if (moveable == true)
		{
			for ( int r=0; r<this.maxRow(); r++)
			{
				for (int c=0; c < this.maxCol(); c++)
				{
					boolean value = this.getElement(r,c);
					if ( value == true)				// If shape is there
					{
						if (r+1 < maxRow())
						{
							Temp.setElement(r+1,c,value);				// Move shape down one
						}
						else
						{
							moveable = false;
							break;
						}
					}
				}
			}
		}

		// If moveable is true at this point, shape can move furthur down
		// Copy new position of shape stored in Temp into the actual shape object

		if(moveable == true)
		{
			for ( int r=0; r<maxRow(); r++)
			{
				for ( int c = 0; c<maxCol(); c++)
				{
					boolean value = Temp.getElement(r,c);
					this.setElement(r,c,value);
				}
			}
		}
		row_pos++;		// Move Shape down by 1 for rotations
	}

	public void moveLeft()
	{
		Grid2d Temp;						//TEMPORARY SHAPE
		Temp = new Grid2d(ARRAY_ROWS,ARRAY_COLS);
		boolean value;
		boolean inBounds = true;
		if (moveable == true)
		{
			for ( int r=0; r<this.maxRow(); r++)
			{
				for (int c=0; c < this.maxCol(); c++)
				{
					value = this.getElement(r,c);
					if ( value == true)				// If shape is there
					{
						if (c > 0)		// Dont let piece go off too far left
						{
							Temp.setElement(r,c-1,value);				// Move shape left one
						}
						else
						{
							inBounds = false;
							break;
						}
					}
				}
			}
		}

		if(inBounds == true)
		{
			for ( int r=0; r<maxRow(); r++)
			{
				for ( int c = 0; c<maxCol(); c++)
				{
					value = Temp.getElement(r,c);
					this.setElement(r,c,value);
				}
			}
		}
		col_pos--;					// Move Shape Column Position Left for rotations
	}

	public void moveRight()
	{
		Grid2d Temp;						//TEMPORARY SHAPE
		Temp = new Grid2d(ARRAY_ROWS,ARRAY_COLS);
		boolean value;
		boolean inBounds = true;

		if (moveable == true)
			{
			for ( int r=0; r<this.maxRow(); r++)
			{
				for (int c=0; c < this.maxCol(); c++)
				{
					value = this.getElement(r,c);
					if ( value == true)				// If shape is there
					{
						if (c+1 < maxCol())
						{
							Temp.setElement(r,c+1,value);				// Move shape right one
						}
						else
						{
							inBounds = false;
							break;
						}
					}
				}
			}
		}

		if( inBounds == true)
		{
			for ( int r=0; r<maxRow(); r++)
			{
				for ( int c = 0; c<maxCol(); c++)
				{
					value = Temp.getElement(r,c);
					this.setElement(r,c,value);
				}
			}
		}
		col_pos++;
	}
	public void rotateRight()			// If shape is rotated right
	{
	//	row_pos = 0; col_pos = 0;
		Orientation++;					// Increment Orientation

		switch( Shapetype )					// Determine what shape we are
		{

/*			BOX SHAPE CAN'T BE ROTATED
			case 1:						// Box Shape
			break;
*/
			case 2:		// Shape
			fillGrid(false);
			switch(Orientation)			// Determine what Orientation we are
			{
				case 1:
					setElement(row_pos+1,col_pos+0,true);			// 00 XX
					setElement(row_pos+0,col_pos+1,true);			// XX XX
					setElement(row_pos+1,col_pos+1,true);			// 00 XX
					setElement(row_pos+2,col_pos+1,true);
				break;
				case 2:
					setElement(row_pos+1,col_pos+0,true);			// 00 XX
					setElement(row_pos+1,col_pos+2,true);			// XX XX
					setElement(row_pos+1,col_pos+1,true);			// 00 XX
					setElement(row_pos+2,col_pos+1,true);
				break;
				case 3:
					setElement(row_pos+1,col_pos+2,true);			// 00 XX
					setElement(row_pos+0,col_pos+1,true);			// XX XX
					setElement(row_pos+1,col_pos+1,true);			// 00 XX
					setElement(row_pos+2,col_pos+1,true);
				break;
				case 4:
					setElement(row_pos+1,col_pos+0,true);			// 00 XX
					setElement(row_pos+1,col_pos+2,true);			// XX XX
					setElement(row_pos+1,col_pos+1,true);			// 00 XX
					setElement(row_pos+0,col_pos+1,true);
				break;

				default:
					Orientation = 0;
					rotateRight();
				break;
			}
			break;

			case 3:		// S Shape

			fillGrid( false );
			switch(Orientation)
			{
				case 1:
					setElement(row_pos+1,col_pos+0,true);	//	00 XX XX
					setElement(row_pos+0,col_pos+1,true);	//	XX XX 00
					setElement(row_pos+1,col_pos+1,true);	//	00 00 00
					setElement(row_pos+0,col_pos+2,true);
				break;

				case 2:
					setElement(row_pos+0,col_pos+1,true);	//	00 XX 00
					setElement(row_pos+1,col_pos+1,true);	//	00 XX XX
					setElement(row_pos+1,col_pos+2,true);	//	00 00 XX
					setElement(row_pos+2,col_pos+2,true);
				break;

				default:
					Orientation = 0;
					rotateRight();
				break;
			}
			break;

			case 4:		//  Shape
			fillGrid(false);
			switch (Orientation)
			{
				case 1:
					setElement(row_pos+0,col_pos+0,true);	// XX XX 00
					setElement(row_pos+0,col_pos+1,true);	// 00 XX 00
					setElement(row_pos+1,col_pos+1,true);	// 00 XX 00
					setElement(row_pos+2,col_pos+1,true);
				break;

				case 2:
					setElement(row_pos+0,col_pos+2,true);	// 00 00 XX
					setElement(row_pos+1,col_pos+0,true);	// XX XX XX
					setElement(row_pos+1,col_pos+1,true);	// 00 00 00
					setElement(row_pos+1,col_pos+2,true);
				break;

				case 3:
					setElement(row_pos+0,col_pos+1,true);	// 00 XX 00
					setElement(row_pos+1,col_pos+1,true);	// 00 XX 00
					setElement(row_pos+2,col_pos+1,true);	// 00 XX XX
					setElement(row_pos+2,col_pos+2,true);
				break;

				case 4:
					setElement(row_pos+1,col_pos+0,true);	// 00 00 00
					setElement(row_pos+1,col_pos+1,true);	// XX XX XX
					setElement(row_pos+1,col_pos+2,true);	// XX 00 00
					setElement(row_pos+2,col_pos+0,true);
				break;

				default:
				Orientation = 0;
				rotateRight();
				break;
			}
			break;

			case 5:		// I Shape
			fillGrid(false);
			switch (Orientation)
			{
				case 1:
					setElement(row_pos+0,col_pos+0,true);		// XX XX XX XX
					setElement(row_pos+0,col_pos+1,true);
					setElement(row_pos+0,col_pos+2,true);
					setElement(row_pos+0,col_pos+3,true);
				break;

				case 2:
					setElement(row_pos+0,col_pos+0,true);	// XX
					setElement(row_pos+1,col_pos+0,true);	// XX
					setElement(row_pos+2,col_pos+0,true);	// XX
					setElement(row_pos+3,col_pos+0,true);	// XX
				break;

				default:
					Orientation = 0;
					rotateRight();
				break;
			}
			break;

			case 6:		// J Shape
			fillGrid(false);
			switch(Orientation)
			{
				case 1:
					setElement(row_pos+0,col_pos+1,true);		// 00 XX
					setElement(row_pos+1,col_pos+1,true);		// 00 XX
					setElement(row_pos+2,col_pos+0,true);		// XX XX
					setElement(row_pos+2,col_pos+1,true);
				break;

				case 2:
					setElement(row_pos+1,col_pos+0,true);		// 00 00 00
					setElement(row_pos+1,col_pos+1,true);		// XX XX XX
					setElement(row_pos+1,col_pos+2,true);		// 00 00 XX
					setElement(row_pos+2,col_pos+2,true);
				break;

				case 3:
					setElement(row_pos+0,col_pos+2,true);		// 00 XX XX
					setElement(row_pos+0,col_pos+1,true);		// 00 XX
					setElement(row_pos+1,col_pos+1,true);		// 00 XX
					setElement(row_pos+2,col_pos+1,true);
				break;

				case 4:
					setElement(row_pos+0,col_pos+0,true);		// XX 00 00
					setElement(row_pos+1,col_pos+0,true);		// XX XX XX
					setElement(row_pos+1,col_pos+1,true);
					setElement(row_pos+1,col_pos+2,true);
				break;

				default:
					Orientation = 0;
					rotateRight();
				break;
			}
			break;

			case 7:		// Z Shape
			fillGrid(false);
			switch ( Orientation)
			{
				case 1:
					setElement(row_pos+1,col_pos+0,true);		// 00 00 00
					setElement(row_pos+1,col_pos+1,true);		// XX XX 00
					setElement(row_pos+2,col_pos+1,true);		// 00 XX XX
					setElement(row_pos+2,col_pos+2,true);
				break;

				case 2:
					setElement(row_pos+0,col_pos+1,true);		// 00 XX
					setElement(row_pos+1,col_pos+1,true);		// XX XX
					setElement(row_pos+1,col_pos+0,true);		// XX 00
					setElement(row_pos+2,col_pos+0,true);
				break;

				default:
				Orientation = 0;
				rotateRight();
				break;
			}
			break;
		}
	}

	public void rotateLeft()			// If shape is rotated left
	{
	//	row_pos=0;col_pos=0;
		Orientation--;					// Decrement Orientation

		switch( Shapetype )					// Determine what shape we are
		{

/*			BOX SHAPE CAN'T BE ROTATED
			case 1:						// Box Shape
			break;
*/
			case 2:		// Shape
			fillGrid(false);
			switch(Orientation)			// Determine what Orientation we are
			{
				case 1:
					setElement(row_pos+1,col_pos+0,true);			// 00 XX
					setElement(row_pos+0,col_pos+1,true);			// XX XX
					setElement(row_pos+1,col_pos+1,true);			// 00 XX
					setElement(row_pos+2,col_pos+1,true);
				break;
				case 2:
					setElement(row_pos+1,col_pos+0,true);			// 00 XX
					setElement(row_pos+1,col_pos+2,true);			// XX XX
					setElement(row_pos+1,col_pos+1,true);			// 00 XX
					setElement(row_pos+2,col_pos+1,true);
				break;
				case 3:
					setElement(row_pos+1,col_pos+2,true);			// 00 XX
					setElement(row_pos+0,col_pos+1,true);			// XX XX
					setElement(row_pos+1,col_pos+1,true);			// 00 XX
					setElement(row_pos+2,col_pos+1,true);
				break;
				case 4:
					setElement(row_pos+1,col_pos+0,true);			// 00 XX
					setElement(row_pos+1,col_pos+2,true);			// XX XX
					setElement(row_pos+1,col_pos+1,true);			// 00 XX
					setElement(row_pos+0,col_pos+1,true);
				break;

				default:
					Orientation = 5;
					rotateLeft();
				break;
			}
			break;

			case 3:		// S Shape

			fillGrid( false );
			switch(Orientation)
			{
				case 1:
					setElement(row_pos+1,col_pos+0,true);	//	00 XX XX
					setElement(row_pos+0,col_pos+1,true);	//	XX XX 00
					setElement(row_pos+1,col_pos+1,true);	//	00 00 00
					setElement(row_pos+0,col_pos+2,true);
				break;

				case 2:
					setElement(row_pos+0,col_pos+1,true);	//	00 XX 00
					setElement(row_pos+1,col_pos+1,true);	//	00 XX XX
					setElement(row_pos+1,col_pos+2,true);	//	00 00 XX
					setElement(row_pos+2,col_pos+2,true);
				break;

				default:
					Orientation = 3;
					rotateLeft();
				break;
			}
			break;

			case 4:		//  Shape
			fillGrid(false);
			switch (Orientation)
			{
				case 1:
					setElement(row_pos+0,col_pos+0,true);	// XX XX 00
					setElement(row_pos+0,col_pos+1,true);	// 00 XX 00
					setElement(row_pos+1,col_pos+1,true);	// 00 XX 00
					setElement(row_pos+2,col_pos+1,true);
				break;

				case 2:
					setElement(row_pos+0,col_pos+2,true);	// 00 00 XX
					setElement(row_pos+1,col_pos+0,true);	// XX XX XX
					setElement(row_pos+1,col_pos+1,true);	// 00 00 00
					setElement(row_pos+1,col_pos+2,true);
				break;

				case 3:
					setElement(row_pos+0,col_pos+1,true);	// 00 XX 00
					setElement(row_pos+1,col_pos+1,true);	// 00 XX 00
					setElement(row_pos+2,col_pos+1,true);	// 00 XX XX
					setElement(row_pos+2,col_pos+2,true);
				break;

				case 4:
					setElement(row_pos+1,col_pos+0,true);	// 00 00 00
					setElement(row_pos+1,col_pos+1,true);	// XX XX XX
					setElement(row_pos+1,col_pos+2,true);	// XX 00 00
					setElement(row_pos+2,col_pos+0,true);
				break;

				default:
				Orientation = 5;
				rotateLeft();
				break;
			}
			break;

			case 5:		// I Shape
			fillGrid(false);
			switch (Orientation)
			{
				case 1:
					setElement(row_pos+0,col_pos+0,true);	// XX XX XX XX
					setElement(row_pos+0,col_pos+1,true);
					setElement(row_pos+0,col_pos+2,true);
					setElement(row_pos+0,col_pos+3,true);
				break;

				case 2:
					setElement(row_pos+0,col_pos+0,true);	// XX
					setElement(row_pos+1,col_pos+0,true);	// XX
					setElement(row_pos+2,col_pos+0,true);	// XX
					setElement(row_pos+3,col_pos+0,true);	// XX
				break;

				default:
					Orientation = 3;
					rotateLeft();
				break;
			}
			break;

			case 6:		// J Shape
			fillGrid(false);
			switch(Orientation)
			{
				case 1:
					setElement(row_pos+0,col_pos+1,true);		// 00 XX
					setElement(row_pos+1,col_pos+1,true);		// 00 XX
					setElement(row_pos+2,col_pos+0,true);		// XX XX
					setElement(row_pos+2,col_pos+1,true);
				break;

				case 2:
					setElement(row_pos+1,col_pos+0,true);		// 00 00 00
					setElement(row_pos+1,col_pos+1,true);		// XX XX XX
					setElement(row_pos+1,col_pos+2,true);		// 00 00 XX
					setElement(row_pos+2,col_pos+2,true);
				break;

				case 3:
					setElement(row_pos+0,col_pos+2,true);		// 00 XX XX
					setElement(row_pos+0,col_pos+1,true);		// 00 XX
					setElement(row_pos+1,col_pos+1,true);		// 00 XX
					setElement(row_pos+2,col_pos+1,true);
				break;

				case 4:
					setElement(row_pos+0,col_pos+0,true);		// XX 00 00
					setElement(row_pos+1,col_pos+0,true);		// XX XX XX
					setElement(row_pos+1,col_pos+1,true);
					setElement(row_pos+1,col_pos+2,true);
				break;

				default:
					Orientation = 5;
					rotateLeft();
				break;
			}
			break;

			case 7:		// Z Shape
			fillGrid(false);
			switch ( Orientation)
			{
				case 1:
					setElement(row_pos+1,col_pos+0,true);		// 00 00 00
					setElement(row_pos+1,col_pos+1,true);		// XX XX 00
					setElement(row_pos+2,col_pos+1,true);		// 00 XX XX
					setElement(row_pos+2,col_pos+2,true);
				break;

				case 2:
					setElement(row_pos+0,col_pos+1,true);		// 00 XX
					setElement(row_pos+1,col_pos+1,true);		// XX XX
					setElement(row_pos+1,col_pos+0,true);		// XX 00
					setElement(row_pos+2,col_pos+0,true);
				break;

				default:
				Orientation = 3;
				rotateLeft();
				break;
			}
			break;
		}
	}

	public boolean isMoveable()
	{
		return moveable;
	}

	public Color getColor()
	{
		return this.myCol;
	}
}