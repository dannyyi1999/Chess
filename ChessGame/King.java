import javafx.scene.paint.Color;

public class King extends Piece{
	
	
	public King(int row, int col, Color color){
		super(row, col, color);
	}
	
	public boolean isMoveValid(int rowTo, int colTo, Spot[][] b){
		if(super.isMoveValid(colTo, rowTo, b)){
			if(getRow() - rowTo <= 1 && getRow() - rowTo >= -1 &&
			   getCol() - colTo <= 1 && getCol() - colTo >= -1 &&
			   (getRow() != rowTo || getCol() != colTo)){
				if(!isSameColor(rowTo, colTo, b) && !isSpotCovered(rowTo, colTo, b)) {
					return true;
				}
			}else if(legalCastle(rowTo, colTo, b)) {
				return true;
			}
		}
		return false;
	}
	

	@Override
	public boolean isMoveBlocked(int rowTo, int colTo, Spot[][] board) {
		return false;
	}
	
	public void castle(int rowTo, int colTo, Spot[][] board) {
		moveTo(rowTo, colTo);
		if(getColor() == Color.BLACK) {
			if(colTo == 6) {
				board[0][7].getPiece().moveTo(0, 5);
			}else if(colTo == 2) {
				board[0][0].getPiece().moveTo(0, 3);
			}
		}else if(getColor() == Color.WHITE) {
			if(colTo == 6) {
				board[7][7].getPiece().moveTo(7, 5);
			}else if(colTo == 2) {
				board[7][0].getPiece().moveTo(7, 3);
			}
		}
	}
	
	public boolean isSpotCovered(int rowTo, int colTo, Spot[][] board) {
		Piece temp = board[rowTo][colTo].getPiece();
		board[rowTo][colTo].setPiece(null);
		for(int row = 0; row < 7; row++) {
			for(int col = 0; col < 7; col++) {
				if(board[row][col].getPiece() != null && board[row][col].getPiece().getColor() != getColor()) {
					if(board[row][col].getPiece().isMoveValid(rowTo, colTo, board)) {
						board[rowTo][colTo].setPiece(temp);
						return true;
					}
				}
			}
		}
		board[rowTo][colTo].setPiece(temp);
		return false;
	}
	
	public boolean legalCastle(int rowTo, int colTo, Spot[][] board) {
		if(getColor() == Color.BLACK) {
			if(rowTo == 0) {
				if(colTo == 6) {
					if(board[0][7].getPiece().getClass() == Rook.class && !(board[0][7].getPiece()).hasMoved() && !hasMoved()) {
						if(board[0][6].getPiece() == null && board[0][5].getPiece() == null) {
							if(!isSpotCovered(0, 6, board) && !isSpotCovered(0, 5, board)) {
								return true;
							}
						}
					}
				}else if(colTo == 2) {
					if(board[0][0].getPiece().getClass() == Rook.class && !((Rook) board[0][0].getPiece()).hasMoved() && !hasMoved()) {
						if(board[0][1].getPiece() == null && board[0][2].getPiece() == null && board[0][3].getPiece() == null) {
							if(!isSpotCovered(0, 1, board) && !isSpotCovered(0, 2, board) && !isSpotCovered(0, 3, board)) {
								return true;
							}
						}
					}
				}
			}
		}else if(getColor() == Color.WHITE) {
			if(rowTo == 7) {
				if(colTo == 6) {
					
					if(board[7][7].getPiece().getClass() == Rook.class && !((Rook) board[7][7].getPiece()).hasMoved() && !hasMoved()) {
						if(board[7][6].getPiece() == null && board[7][5].getPiece() == null) {
							if(!isSpotCovered(7, 6, board) && !isSpotCovered(7, 5, board)) {
								return true;
							}
						}
					}
				}else if(colTo == 2){
					if(board[7][0].getPiece().getClass() == Rook.class && !((Rook) board[7][0].getPiece()).hasMoved() && !hasMoved()){
						if(board[7][1].getPiece() == null && board[7][2].getPiece() == null && board[7][3].getPiece() == null) {
							if(!isSpotCovered(7, 1, board) && !isSpotCovered(7, 2, board) && !isSpotCovered(7, 3, board)) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
}
