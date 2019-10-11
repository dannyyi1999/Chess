import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ChessPane extends Group{
	
	private final int SIZE = 8;
	private final double TILE_SIZE = 50;
	
	private Rectangle[][] chessBoard;
	private ImageView[][] boardPiece;
	private ChessModel model;
	
	public ChessPane(ChessModel model){
		chessBoard = new Rectangle[SIZE][SIZE];
		boardPiece = new ImageView[SIZE][SIZE];
		this.model = model;
		updateBoard();
	}
	
	public ImageView[][] getBoardPieces(){
		return boardPiece;
	}
	
	public void setModel(ChessModel model){
		this.model = model;
	}
	
	public ChessModel getModel(){
		return model;
	}
	
	public Rectangle[][] getBoard(){
		return chessBoard;
	}
	
	public int colForXPos(double x){
		return (int)(x / TILE_SIZE);
	}
	
	public int rowForYPos(double y){
		return (int)(y / TILE_SIZE);
	}
	
	public void updateBoard(){
		getChildren().clear();
		for(int row = 0; row < SIZE; row++){
			for(int col = 0; col < SIZE; col++){				
				chessBoard[row][col] = new Rectangle(TILE_SIZE, TILE_SIZE);
				if((row + col) % 2 == 0){
					chessBoard[row][col].setFill(Color.BEIGE);
				}else {
					chessBoard[row][col].setFill(Color.BURLYWOOD);
				}
				chessBoard[row][col].setX(col * TILE_SIZE);
				chessBoard[row][col].setY(row * TILE_SIZE);
				getChildren().add(chessBoard[row][col]);
			}
		}
		updatePiecePositions();
	}
	
	public void updatePiecePositions(){
		getChildren().remove(64, getChildren().size());
		Player w = model.getWhite();
		Player b = model.getBlack();
		for(int row = 0; row < SIZE; row++){
			for(int col = 0; col < SIZE; col++){
				if(model.getBlack().containsPieceAt(row, col)){
					if(b.getPieceAt(row, col).getClass() == King.class){
						boardPiece[row][col] = new ImageView("file:chess/black_king.png");
					}else if(b.getPieceAt(row, col).getClass() == Queen.class){
						boardPiece[row][col] = new ImageView("file:chess/black_queen.png");
					}else if(b.getPieceAt(row, col).getClass() == Rook.class){
						boardPiece[row][col] = new ImageView("file:chess/black_rook.png");
					}else if(b.getPieceAt(row, col).getClass() == Bishop.class){
						boardPiece[row][col] = new ImageView("file:chess/black_bishop.png");
					}else if(b.getPieceAt(row, col).getClass() == Knight.class){
						boardPiece[row][col] = new ImageView("file:chess/black_knight.png");
					}else if(b.getPieceAt(row, col).getClass() == Pawn.class){
						boardPiece[row][col] = new ImageView("file:chess/black_pawn.png");
					}
				}else if(model.getWhite().containsPieceAt(row, col)){
					if(w.getPieceAt(row, col).getClass() == King.class){
						boardPiece[row][col] = new ImageView("file:chess/white_king.png");
					}else if(w.getPieceAt(row, col).getClass() == Queen.class){
						boardPiece[row][col] = new ImageView("file:chess/white_queen.png");
					}else if(w.getPieceAt(row, col).getClass() == Rook.class){
						boardPiece[row][col] = new ImageView("file:chess/white_rook.png");
					}else if(w.getPieceAt(row, col).getClass() == Bishop.class){
						boardPiece[row][col] = new ImageView("file:chess/white_bishop.png");
					}else if(w.getPieceAt(row, col).getClass() == Knight.class){
						boardPiece[row][col] = new ImageView("file:chess/white_knight.png");
					}else if(w.getPieceAt(row, col).getClass() == Pawn.class){
						boardPiece[row][col] = new ImageView("file:chess/white_pawn.png");
					}
				}else {
					boardPiece[row][col] = new ImageView();
				}
				
				boardPiece[row][col].setX(col * TILE_SIZE);
				boardPiece[row][col].setY(row * TILE_SIZE);
				getChildren().add(boardPiece[row][col]);
			}
		}
	}
	
	public void highlightValidMoves(int row, int col) {
		if(model.getBoard()[row][col].getPiece() == null) {
			updateBoard();
		}else if(model.getPlayerTurn().containsPieceAt(row, col)) {
			getChildren().clear();
			for(int r = 0; r < SIZE; r++){
				for(int c = 0; c < SIZE; c++){				
					chessBoard[r][c] = new Rectangle(TILE_SIZE, TILE_SIZE);
					if(model.getPlayerTurn().getPieceAt(row, col).isMoveValid(r, c, model.getBoard())||
							r == row && c == col) {
						chessBoard[r][c].setFill(Color.YELLOW);
					}else {
						if((r + c) % 2 == 0){
							chessBoard[r][c].setFill(Color.BEIGE);
						}else {
							chessBoard[r][c].setFill(Color.BURLYWOOD);
						}
					}
					chessBoard[r][c].setX(c * TILE_SIZE);
					chessBoard[r][c].setY(r * TILE_SIZE);
					getChildren().add(chessBoard[r][c]);
				}
			}
		}
		updatePiecePositions();
	}
	
}
