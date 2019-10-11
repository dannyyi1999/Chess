import javafx.scene.paint.Color;

public class Queen extends Piece{
	
	public Queen(int row, int col, Color color){
		super(row, col, color);
	}
	
	public boolean isMoveValid(int rowTo, int colTo, Spot[][] b) {
		if(super.isMoveValid(rowTo, colTo, b)) {
			if(Math.abs(rowTo - getRow()) == Math.abs(colTo - getCol()) ||
			  (rowTo == getRow() && colTo != getCol() || 
			   colTo == getCol() && rowTo != getRow())) {
				if(!isSameColor(rowTo, colTo, b) && !isMoveBlocked(rowTo, colTo, b)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isMoveBlocked(int rowTo, int colTo, Spot[][] board) {
		//Bishop case (diagonal movement)
		if(getRow() != rowTo && getCol() != colTo){
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
		//Rook case (horizontal/vertical movement)
		}else {
			if(getRow() != rowTo) {
				int low = Math.min(getRow(), rowTo);
				int high = Math.max(getRow(), rowTo);
				for(int r = low + 1; r < high; r++) {
					if(board[r][getCol()].getPiece() != null) {
						return true;
					}
				}
			}else if(getCol() != colTo){
				int low = Math.min(getCol(), colTo);
				int high = Math.max(getCol(), colTo);
				for(int c = low + 1; c < high; c++) {
					if(board[getRow()][c].getPiece() != null) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
}
