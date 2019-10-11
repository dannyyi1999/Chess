
public class Spot {
	private int row;
	private int col;
	private Piece piece;
	
	public Spot(int row, int col){
		this.row = row;
		this.col = col;
		this.piece = null;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}
	
	public Piece getPiece(){
		return piece;
	}
	
	public void setPiece(Piece piece){
		this.piece = piece;
	}
}
