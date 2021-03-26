package fr.maximeNarbaud.chess;

import fr.maximeNarbaud.chess.agent.Move;
import fr.maximeNarbaud.chess.agent.Player;
import fr.maximeNarbaud.chess.piece.Bishop;
import fr.maximeNarbaud.chess.piece.King;
import fr.maximeNarbaud.chess.piece.Knight;
import fr.maximeNarbaud.chess.piece.Pawn;
import fr.maximeNarbaud.chess.piece.Piece;
import fr.maximeNarbaud.chess.piece.Queen;
import fr.maximeNarbaud.chess.piece.Rook;

public class Board {
	private final Spot[][] grid;
	public static final int SIZE = 8; // size of board

	public Board() {
		grid = new Spot[SIZE][SIZE];

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				grid[j][i] = new Spot(j, i);
				grid[j][i].setOccupied(false);
			}
		}
	}

	public void setupChessBoard() {
		for (int x = 0; x < SIZE; x++) {
			grid[x][1].setPiece(new Pawn(Player.WHITE));
			grid[x][6].setPiece(new Pawn(Player.BLACK));
		}

		for (int x = 2; x < 8; x += 3) {
			grid[x][0].setPiece(new Bishop(Player.WHITE));
			grid[x][7].setPiece(new Bishop(Player.BLACK));
		}

		for (int x = 1; x < 8; x += 5) {
			grid[x][0].setPiece(new Knight(Player.WHITE));
			grid[x][7].setPiece(new Knight(Player.BLACK));
		}

		for (int x = 0; x < 8; x += 7) {
			grid[x][0].setPiece(new Rook(Player.WHITE));
			grid[x][7].setPiece(new Rook(Player.BLACK));
		}

		grid[3][0].setPiece(new Queen(Player.WHITE));
		grid[3][7].setPiece(new Queen(Player.BLACK));

		grid[4][0].setPiece(new King(Player.WHITE));
		grid[4][7].setPiece(new King(Player.BLACK));
	}

	public void movePiece(Move mv) {
		grid[mv.finalX][mv.finalY].setPiece(grid[mv.initialX][mv.initialY].getPiece());
		grid[mv.initialX][mv.initialY].release();
	}

	public Spot[][] getGrid() {
		return grid;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		for (int y = 0; y < SIZE; y++) {
			s.append((char) ('1' + y)).append("| ");

			for (int x = 0; x < SIZE; x++) {
				if (grid[x][y].isOccupied()) {
					s.append(grid[x][y].getPiece()).append(" ");
				} else
					s.append("  ");
			}
			s.append("\n");
		}

		s.append("  ");

		for (int x = 0; x < SIZE; x++)
			s.append("--");

		s.append("\n");
		s.append("   ");

		for (int x = 0; x < SIZE; x++)
			s.append((char) ('a' + x)).append(" ");
		s.append("\n");

		return s.toString();
	}

	public void print() {
		System.out.println(this.toString());
	}

	@Override
	public Object clone() {
		Board clonedBoard = new Board();

		for (int y = 0; y < SIZE; y++)
			for (int x = 0; x < SIZE; x++)
				clonedBoard.getGrid()[y][x].setPiece((Piece) grid[y][x].getPiece().clone());

		return clonedBoard;
	}

}
