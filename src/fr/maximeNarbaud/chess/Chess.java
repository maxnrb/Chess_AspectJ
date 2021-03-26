package fr.maximeNarbaud.chess;

import fr.maximeNarbaud.chess.agent.AiPlayer;
import fr.maximeNarbaud.chess.agent.HumanPlayer;
import fr.maximeNarbaud.chess.agent.Player;

public class Chess {
	public static void main(String[] args) {
		new Chess().play();
	}

	protected Board board;

	public Chess() {
		board = new Board();
		board.setupChessBoard();
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	private void play() {
		Player hp = new HumanPlayer(Player.WHITE, board);
		Player ap = new AiPlayer(Player.BLACK, board);

		while (true) {
			board.print();
			hp.makeMove();
			board.print();
			ap.makeMove();
		}
	}
}
