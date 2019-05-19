package core;

import core.ICoordinate;
import core.IMoveCoordinate;
import core.IPlayer;

public interface IView {
	public void drawBoardView();
	public IMoveCoordinate getNextMove(IPlayer currentPlayer, ICoordinate newSourceCoordinate);
	public IMoveCoordinate getNextMove(IPlayer currentPlayer);
	public void printMessage(String message);
}