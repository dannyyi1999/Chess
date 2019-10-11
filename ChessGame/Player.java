import java.util.ArrayList;

import javafx.scene.paint.Color;


public class Player {
	private final int PAWNS = 8;
	private final int ROOKS = 2;
	private final int QUEEN = 1;
	private final int KING = 1;
	private final int BISHOPS = 2;
	private final int KNIGHTS = 2;
	
	private ArrayList<Piece> currentPieces;
	private Color color;
	
	public Player(Color color){
		this.color = color;
		currentPieces = new ArrayList<Piece>();
		initializePieces();
	}
	
	public Color getPlayerColor(){
		return color;
	}
	
	public ArrayList<Piece> getCurrentPieces(){
		return currentPieces;
	}
	
	//Checks if the position at row and col contains a piece.
	public boolean containsPieceAt(int row, int col){
		for(int i = 0; i < currentPieces.size(); i++){
			if(currentPieces.get(i).getRow() == row && currentPieces.get(i).getCol() == col){
				return true;
			}
		}
		return false;
	}
	
	//returns the piece at the location. If no piece is there,
	//returns null.
	public Piece getPieceAt(int row, int col){
		for(int i = 0; i < currentPieces.size(); i++){
			if(currentPieces.get(i).getRow() == row && currentPieces.get(i).getCol() == col){
				return currentPieces.get(i);
			}
		}
		return null;
	}
	
	public void removePieceAt(int row, int col){
		for(int i = 0; i < currentPieces.size(); i++){
			if(currentPieces.get(i).getRow() == row && currentPieces.get(i).getCol() == col){
				currentPieces.remove(i);
			}
		}
	}
	
	public void initializePieces(){
		//Initializes the standard pieces and position for a player depending
		//on their color.
		if(getPlayerColor() == Color.WHITE){
			for(int i = 0; i < PAWNS; i++){
				currentPieces.add(new Pawn(6, i, Color.WHITE));
			}
			currentPieces.add(new Rook(7, 0, Color.WHITE));
			currentPieces.add(new Rook(7, 7, Color.WHITE));
			currentPieces.add(new Knight(7, 1, Color.WHITE));
			currentPieces.add(new Knight(7, 6, Color.WHITE));
			currentPieces.add(new Bishop(7, 2, Color.WHITE));
			currentPieces.add(new Bishop(7, 5, Color.WHITE));
			currentPieces.add(new Queen(7, 3, Color.WHITE));
			currentPieces.add(new King(7, 4, Color.WHITE));
		}else {
			for(int i = 0; i < PAWNS; i++){
				currentPieces.add(new Pawn(1, i, Color.BLACK));
			}
			currentPieces.add(new Rook(0, 0, Color.BLACK));
			currentPieces.add(new Rook(0, 7, Color.BLACK));
			currentPieces.add(new Knight(0, 1, Color.BLACK));
			currentPieces.add(new Knight(0, 6, Color.BLACK));
			currentPieces.add(new Bishop(0, 2, Color.BLACK));
			currentPieces.add(new Bishop(0, 5, Color.BLACK));
			currentPieces.add(new Queen(0, 3, Color.BLACK));
			currentPieces.add(new King(0, 4, Color.BLACK));
		}
	}
}
