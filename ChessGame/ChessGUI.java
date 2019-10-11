import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ChessGUI extends Application{
	
	private int row;
	private int col;
	private Piece thisPiece = null;
	@Override
	public void start(Stage primaryStage) throws Exception {
		ChessModel model = new ChessModel();
		ChessPane pane = new ChessPane(model);
		
		pane.setOnMouseClicked((e) ->{
			row = pane.rowForYPos(e.getY());
			col = pane.colForXPos(e.getX());
			if(pane.getBoard()[row][col].getFill().equals(Color.YELLOW)) {
				model.movePieceTo(thisPiece.getRow(), thisPiece.getCol(), row, col, model.getBoard());
				pane.setModel(model);
				pane.updateBoard();
				thisPiece = null;
			}else {
				pane.highlightValidMoves(row, col);
				thisPiece = pane.getModel().getBoard()[row][col].getPiece();
			}
			pane.updatePiecePositions();
		});

		
		Scene scene = new Scene(pane, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show(); 
	}

	public static void main(String[] args) {
		launch(args);
	}
}
