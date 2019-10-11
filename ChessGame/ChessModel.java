import java.util.ArrayList;

import javafx.scene.paint.Color;

public class ChessModel {
	private final int SIZE = 8;
	private Spot[][] board;
	private Player black;
	private Player white;
	private int moveCount;
	
	public ChessModel(){
		//Initializes board of size 8x8 and creates a spot at each location.
		board = new Spot[SIZE][SIZE];
		for(int row = 0; row < SIZE; row++){
			for(int col = 0; col < SIZE; col++){
				board[row][col] = new Spot(row, col);
			}
		}
		moveCount = 0;
		black = new Player(Color.BLACK);
		white = new Player(Color.WHITE);
		initializeBoard();
	}
	
	public void initializeBoard(){
		ArrayList<Piece> w = white.getCurrentPieces();
		ArrayList<Piece> b = black.getCurrentPieces();
		
		for(int i = 0; i < b.size(); i++){
			board[w.get(i).getRow()][w.get(i).getCol()].setPiece(w.get(i)); 
			board[b.get(i).getRow()][b.get(i).getCol()].setPiece(b.get(i)); 
		}
	}
	
	public void updateBoard(){
		ArrayList<Piece> w = white.getCurrentPieces();
		ArrayList<Piece> b = black.getCurrentPieces();
		refreshSpots();
		
		for(int wPiece = 0; wPiece < w.size(); wPiece++){
			board[w.get(wPiece).getRow()][w.get(wPiece).getCol()].setPiece(w.get(wPiece));
		}
		for(int bPiece = 0; bPiece < b.size(); bPiece++){
			board[b.get(bPiece).getRow()][b.get(bPiece).getCol()].setPiece(b.get(bPiece));
		}
	}
	
	public void incMoveCount() {
		moveCount++;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	public void movePieceTo(int rowFrom, int colFrom, int rowTo, int colTo, Spot[][] b) {
		if(getPlayerTurn().containsPieceAt(rowFrom, colFrom)) {
			Piece p = getPlayerTurn().getPieceAt(rowFrom, colFrom);
			if(p.isMoveValid(rowTo, colTo, board)) {
				if(p.getClass() == King.class && ((King)(p)).legalCastle(rowTo, colTo, b)) {
					((King)(p)).castle(rowTo, colTo, b);
				}else {
					p.moveTo(rowTo, colTo);
				}
				removeDeadPiece();
				incMoveCount();
				updateBoard();
			}
		}
	}
	
	
	public void removeDeadPiece() {
		//Looks through the board and finds if there's any two pieces on the same
		//spot. If so, remove the piece that is underneath it.
		for(int row = 0; row < SIZE; row++) {
			for(int col = 0; col < SIZE; col++) {
				if(white.getPieceAt(row, col) != null && black.getPieceAt(row, col) != null) {
					if(getPlayerTurn() == white) {
						black.removePieceAt(row, col);
					}else {
						white.removePieceAt(row, col);
					}
				}
			}
		}
	}
	
	public boolean isSpotCovered(int row, int col, Player p) {
		return true;
	}
	
	public Spot[][] getBoard(){
		return board;
	}
	
	
	public Player getWhite(){
		return white;
	}
	
	
	public Player getBlack(){
		return black;
	}
	

	
	public Player getPlayerTurn(){
		if(moveCount % 2 == 0){
			return white;
		}
		return black;
	}
	
	
	public void refreshSpots(){
		for(int row = 0; row < SIZE; row++){
			for(int col = 0; col < SIZE; col++){
				board[row][col].setPiece(null);
			}
		}
	}
	
	public void printBoard() {
		for(int row = 0; row < SIZE; row++) {
			for(int col = 0; col < SIZE; col++) {
				if(board[row][col].getPiece() == null) {
					System.out.print("X ");
				}else if(board[row][col].getPiece().getClass() == Bishop.class) {
					System.out.print("B ");
				}else if(board[row][col].getPiece().getClass() == Knight.class) {
					System.out.print("N ");
				}else if(board[row][col].getPiece().getClass() == Rook.class) {
					System.out.print("R ");
				}else if(board[row][col].getPiece().getClass() == Pawn.class) {
					System.out.print("P ");
				}else if(board[row][col].getPiece().getClass() == King.class) {
					System.out.print("K ");
				}else if(board[row][col].getPiece().getClass() == Queen.class) {
					System.out.print("Q ");
				}
			}
			System.out.println();
		}
	}

}



