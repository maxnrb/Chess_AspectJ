package fr.maximeNarbaud.chess.piece;

import fr.maximeNarbaud.chess.agent.Move;
import fr.maximeNarbaud.chess.agent.Player;

public class Queen extends Piece {

	public Queen(int player) {
		super(player);
	}

	@Override
	public String toString() {
		return ((this.player == Player.WHITE) ? "Q" : "\u001B[31mQ\u001B[0m");
	}

	@Override
	public boolean isMoveLegal(Move mv) {
		// TODO Auto-generated method stub
		Rook r = new Rook();
		Bishop b = new Bishop();
		return r.isMoveLegal(mv) || b.isMoveLegal(mv);
	}
}