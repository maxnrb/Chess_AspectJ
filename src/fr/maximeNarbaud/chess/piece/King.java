package fr.maximeNarbaud.chess.piece;

import fr.maximeNarbaud.chess.agent.Move;
import fr.maximeNarbaud.chess.agent.Player;

public class King extends Piece {

	public King(int player) {
		super(player);
	}

	@Override
	public String toString() {
		return ((this.player == Player.WHITE) ? "k" : "\u001B[31mk\u001B[0m");
	}

	@Override
	public boolean isMoveLegal(Move mv) {
		// TODO Auto-generated method stub
		return (Math.abs(mv.initialX - mv.finalX) <= 1) && (Math.abs(mv.initialY - mv.finalY) <= 1);
	}
}