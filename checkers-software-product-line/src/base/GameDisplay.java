package base;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

import core.AbstractReferee;
import core.AbstractPiece;
import core.Coordinate;

import java.io.File;
import java.io.IOException;

/** A widget for the display of the Chess GUI.
 *  @author Wan Fung Chui
 */
class GameDisplay extends Pad {

    /** A factor that can be changed to alter the size of the GUI. */
    public static final double MULTIPLIER = 0.7;

    /** Length and width of the square playing surface. */
    public static final int BOARD = (int) Math.round(700 * MULTIPLIER);

    /** Length and width of a single cell in the chessboard. */
    public static final int CELL = (int) Math.round(74 * MULTIPLIER);

    /** Distance from the chessboard edge to the first cell. */
    public static final int MARGIN = (int) Math.round(53 * MULTIPLIER);
    AbstractReferee referee;
    private GUI gui;
    /** Constructs a graphical representation of GAME. */
    public GameDisplay(AbstractReferee referee, GUI gui) {
    	this.referee = referee;
        setPreferredSize(BOARD, BOARD);
        this.gui = gui;
    }

    /** Return an Image read from the resource named NAME. */
    private Image getImage(String name) {
    	String path ="src/base/images/" + name ;
    	File file = new File(path);
        try {
            return ImageIO.read(file);
        } catch (IOException excp) {
            return null;
        }
    }

    /** Return an Image of PIECE. */
    private Image getPieceImage(AbstractPiece piece) {
        return getImage("pieces/"+piece.getId()+".png");
    }

    /** Draw PIECE at X, Y on G. */
    private void paintPiece(Graphics2D g, AbstractPiece piece, int x, int y) {
        if (piece != null) {
            g.drawImage(getPieceImage(piece), x, y, CELL, CELL, null);
        }
    }

    @Override
    public synchronized void paintComponent(Graphics2D g) {
        Rectangle b = g.getClipBounds();
        g.fillRect(0, 0, b.width, b.height);
        g.drawImage(getImage("chessboard.jpg"), 0, 0, BOARD, BOARD, null);
        

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                paintPiece(g, referee.getBoard().getCoordinatePieceMap().getPieceAtCoordinate(new Coordinate(i, j)),
                    CELL * i + MARGIN, CELL * j + MARGIN);
            }
        }
        
        if (gui.selectedX != -1) {
            g.drawImage(getImage("selected.png"),
                CELL * gui.selectedX + MARGIN,
                CELL * gui.selectedY + MARGIN, CELL, CELL, null);
        }
        
    }



}