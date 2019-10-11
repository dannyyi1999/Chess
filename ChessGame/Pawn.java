import javafx.scene.paint.Color;

public class Pawn extends Piece{
	
	public Pawn(int row, int col, Color color){
		super(row, col, color);
	}
	
	public boolean isMoveValid(int rowTo, int colTo, Spot[][] b) {
		if(super.isMoveValid(rowTo, colTo, b)) {
			if(getColor() == Color.BLACK) {
				if((rowTo == getRow() + 2 && colTo == getCol() && getRow() == 1) || rowTo == getRow() + 1 && (colTo == getCol() ||
				   (b[rowTo][colTo].getPiece() != null && 
				   ((colTo == getCol() + 1 || colTo == getCol() - 1) &&
				    b[rowTo][colTo].getPiece().getColor() == Color.WHITE)))) {
					if(!isMoveBlocked(rowTo, colTo, b)) {
						return true;
					}
				}
			}else if(getColor() == Color.WHITE){
				if((rowTo == getRow() - 2 && colTo == getCol() && getRow() == 6) || rowTo == getRow() - 1 && (colTo == getCol() ||
				  (b[rowTo][colTo].getPiece() != null && 
			      ((colTo == getCol() + 1 || colTo == getCol() - 1) &&
				  b[rowTo][colTo].getPiece().getColor() == Color.BLACK)))) {
					if(!isMoveBlocked(rowTo, colTo, b)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean isMoveBlocked(int rowTo, int colTo, Spot[][] board) {
		if(getCol() == colTo) {
			if(Math.abs(rowTo - getRow()) == 1) {
				if(board[rowTo][colTo].getPiece() != null) {
					return true;
				}
			}else if(Math.abs(rowTo - getRow()) == 2) {
				if(board[rowTo][colTo].getPiece() != null || board[(rowTo + getRow())/2][colTo].getPiece() != null){
					return true;
				}
			}
		}
		return false;
	}
	

}
