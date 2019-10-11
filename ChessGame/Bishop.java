import javafx.scene.paint.Color;

public class Bishop extends Piece{
	
	public Bishop(int row, int col, Color color){
		super(row, col, color);
	}
	
	public boolean isMoveValid(int rowTo, int colTo, Spot[][] b) {
		if(super.isMoveValid(rowTo, colTo, b)) {
			if(Math.abs(rowTo - getRow()) == Math.abs(colTo - getCol())) {
				if(!isSameColor(rowTo, colTo, b) && !isMoveBlocked(rowTo, colTo, b)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isMoveBlocked(int rowTo, int colTo, Spot[][] board) {
		if(Math.abs(rowTo - getRow()) <= 1 && Math.abs(colTo - getCol()) <= 1) {
			return false;
		}else {
			if(getRow() > rowTo) {
				if(getCol() > colTo) {
					if(board[rowTo + 1][colTo + 1].getPiece() != null) {
						return true;
					}else {
						return isMoveBlocked(rowTo + 1, colTo + 1, board);
					}
				}else {
					if(board[rowTo + 1][colTo - 1].getPiece() != null) {
						return true;
					}else {
						return isMoveBlocked(rowTo + 1, colTo - 1, board);
					}
				}
			}else if(getCol() > colTo) {
				if(board[rowTo - 1][colTo + 1].getPiece() != null) {
					return true;
				}else {
					return isMoveBlocked(rowTo - 1, colTo + 1, board);
				}
			}else {
				if(board[rowTo - 1][colTo - 1].getPiece() != null) {
					return true;
				}else {
					return isMoveBlocked(rowTo - 1, colTo - 1, board);
				}
			}
		}
	}
	
	
}
