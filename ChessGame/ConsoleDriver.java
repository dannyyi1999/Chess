import java.awt.Color;

public class ConsoleDriver {

	public static void main(String[] args) {
		ChessModel model = new ChessModel();
		model.printBoard();
		Boolean[][] valMoves = new Boolean[8][8];
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				valMoves[row][col] = model.getBlack().getPieceAt(4, 0).isMoveValid(row, col, model.getBoard());
			}
		}
		for(int row = 0; row < 8; row++) {
			System.out.println();
			for(int col = 0; col < 8; col++) {
				System.out.print(valMoves[row][col]);
			}
		}
		
	}

}
