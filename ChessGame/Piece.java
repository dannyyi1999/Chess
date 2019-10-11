import javafx.scene.paint.Color;

public abstract class Piece {
	private int row;
	private int col;
	private Color color;
	private boolean moved;
	
	public Piece(int row, int col, Color color){
		this.row = row;
		this.col = col;
		this.color = color;
		moved = false;
	}
	
	public Color getColor(){
		return color;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}
	
	public boolean hasMoved() {
		return moved;
	}
	
	public void setMoved(boolean moved) {
		this.moved = moved;
	}
	
	public void moveTo(int rowTo, int colTo){
		row = rowTo;
		col = colTo;
		moved = true;
	}
	
	public boolean isMoveValid(int rowTo, int colTo, Spot[][] board){
		if(rowTo > 7 || rowTo < 0 || colTo > 7 || colTo < 0){
			return false;
		}
		return true;
	}
	
	public int findKingRow(Color color, Spot[][] board) {
		for(int row = 0; row < 7; row++) {
			for(int col = 0; col < 7; col++) {
				if(board[row][col].getPiece() != null && board[row][col].getPiece().getClass() == King.class) {
					if(board[row][col].getPiece().getColor() == color) {
						return row;
					}
				}
			}
		}
		return -1;
	}
	
	public int findKingCol(Color color, Spot[][] board) {
		for(int row = 0; row < 7; row++) {
			for(int col = 0; col < 7; col++) {
				if(board[row][col].getPiece() != null && board[row][col].getPiece().getClass() == King.class) {
					if(board[row][col].getPiece().getColor() == color) {
						return col;
					}
				}
			}
		}
		return -1;
	}
	
	public abstract boolean isMoveBlocked(int rowTo, int colTo, Spot[][] board);
	
	public boolean isSameColor(int rowTo, int colTo, Spot[][] board) {
		return board[rowTo][colTo].getPiece() != null && board[rowTo][colTo].getPiece().getColor() == getColor();
	}

}
