import javafx.scene.paint.Color;

public class Knight extends Piece{
	
	public Knight(int row, int col, Color color){
		super(row, col, color);
	}
	
	public boolean isMoveValid(int rowTo, int colTo, Spot[][] b) {
		if(super.isMoveValid(rowTo, colTo, b)) {
			if(Math.abs(rowTo - getRow()) == 1 && Math.abs(colTo - getCol()) == 2 ||
			   Math.abs(rowTo - getRow()) == 2 && Math.abs(colTo - getCol()) == 1) {
				if(!isSameColor(rowTo, colTo, b)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isMoveBlocked(int rowTo, int colTo, Spot[][] board) {
		return false;
	}
}
