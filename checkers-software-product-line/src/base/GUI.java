package base;


import java.awt.event.MouseEvent;
import core.AbstractReferee;
import core.Coordinate;
import core.ICoordinate;
import core.IMoveCoordinate;
import core.IPlayer;
import core.IView;
import core.MoveCoordinate;

/** A top-level GUI for Chess.
 *  @author Wan Fung Chui
 */

public class GUI extends TopLevel implements IView {
	    /** A factor that can be changed to alter the size of the GUI. */
    public static final double MULTIPLIER = 0.7;
    /** Length and width of the square playing surface. */
    public static final int BOARD = (int) Math.round(700 * MULTIPLIER);
    /** Length and width of a single cell in the chessboard. */
    public static final int CELL = (int) Math.round(74 * MULTIPLIER);
    /** Distance from the chessboard edge to the first cell. */
    public static final int MARGIN = (int) Math.round(53 * MULTIPLIER);
    
	/** The chessboard widget. */
	private final GameDisplay display;

    protected int selectedX,selectedY;
	private int X1=-1, Y1=-1,X2=-1, Y2=-1;
	private boolean clicked = true;
	private boolean sourceCoord = false, destinationCoord = false;
	
    /** A new window with given TITLE and displaying GAME. */
    public GUI(String title, AbstractReferee game) {
        super(title, true);
        selectedX = -1;
        selectedY = -1;
        addLabel("Welcome to 2-Player Chess. "
            + "Click a piece and then its destination to play! "
            + "WHITE's turn.", "turn",
            new LayoutSpec("y", 0, "x", 0));
//        addMenuButton("Options->Quit", "quit");
//        addMenuButton("Options->Undo", "undo");
//        addMenuButton("Options->New Game", "newGame");
        display = new GameDisplay(game, this);
        add(display, new LayoutSpec("y", 2, "width", 2));
        display.setMouseHandler("press", this, "mousePressed");
        display(true);
    }
/*
    // Respond to the "New Game" button. 
    public void newGame(String dummy) {
       // _game.newGame();
       // repaint(true);
    }

    // Respond to the "Quit" button. 
    public void quit(String dummy) {
        
    }

    // Respond to the "Undo" button. 
    public void undo(String dummy) {
     //   _game.undoMove();
     //   _game.setSelectedX(-1);
    //    _game.setSelectedY(-1);
      //  repaint(true);
    }
 
*/

    /** Action in response to mouse-pressed event EVENT. */
    public synchronized void mousePressed(MouseEvent event) {   	
    	int pressedX = (event.getX() - MARGIN) / CELL;
        int pressedY = (event.getY() - MARGIN) / CELL;
        
        if(sourceCoord == true && destinationCoord == true) {
        	if(clicked) {// one click
        		this.X1 = pressedX;
        		this.Y1 = pressedY;    		
                //System.out.println("source X: "+pressedX);
                //System.out.println("source Y: "+pressedY);
                selectedX = pressedX;
                selectedY = pressedY;
                clicked = false;
        	}else {	// click after other click
	     		this.X2 = pressedX;
	    		this.Y2 = pressedY;
	            //System.out.println("dest X: "+pressedX);
	            //System.out.println("dest Y: "+pressedY);
                selectedX = -1;
                selectedY = -1;
                clicked = true;
                notify();
        	}
        }else if(sourceCoord == true) {
        	this.X1 = pressedX;
    		this.Y1 = pressedY;    		
            //System.out.println("only source X: "+pressedX);
            //System.out.println("only source Y: "+pressedY);
            selectedX = pressedX;
            selectedY = pressedY;
            notify();
        }else if(destinationCoord == true) {
     		this.X2 = pressedX;
    		this.Y2 = pressedY;
            //System.out.println("only dest X: "+pressedX);
            //System.out.println("only dest Y: "+pressedY); 
            selectedX = -1;
            selectedY = -1;            
            notify();
        }
        display.repaint();
    }


	@Override
	public void drawBoardView() {
		display.repaint();		
	}

	@Override
	public synchronized IMoveCoordinate getNextMove(IPlayer currentPlayer, ICoordinate newSourceCoordinate) {
    	try {  		
    		sourceCoord = false;
    		destinationCoord = true;
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	ICoordinate destinationCoordinate = new Coordinate(X2,Y2);
    	IMoveCoordinate moveCoordinate = new MoveCoordinate(newSourceCoordinate, destinationCoordinate);

    	display.repaint();
		sourceCoord = false;
		destinationCoord = false;   	
		return moveCoordinate;
	}
	
   @Override
    public synchronized IMoveCoordinate getNextMove(IPlayer currentPlayer) {
    	try {
    		sourceCoord = true;
    		destinationCoord = true;
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	ICoordinate sourceCoordinate = new Coordinate(X1,Y1);
    	ICoordinate destinationCoordinate = new Coordinate(X2,Y2);
    	IMoveCoordinate moveCoordinate = new MoveCoordinate(sourceCoordinate, destinationCoordinate);

    	display.repaint();
		sourceCoord = false;
		destinationCoord = false;
		return moveCoordinate;  	
    }	

	@Override
	public void printMessage(String message) {
		setLabel("turn", message);
		display.repaint();
	}



}