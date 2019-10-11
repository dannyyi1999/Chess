import javafx.scene.paint.Color;


public class Rook extends Piece{
	

	
	public Rook(int row, int col, Color color){
		super(row, col, color);

	}
	
	public boolean isMoveValid(int rowTo, int colTo, Spot[][] b) {
		if(super.isMoveValid(rowTo, colTo, b)){
			if(rowTo == getRow() && colTo != getCol() || 
			   colTo == getCol() && rowTo != getRow()) {
				if(!isSameColor(rowTo, colTo, b) && !isMoveBlocked(rowTo, colTo, b)) {
					return true;
				}
			}
		}
		return false;
	}
	

	@Override
	public boolean isMoveBlocked(int rowTo, int colTo, Spot[][] board) {
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
		return false;
	}
}
