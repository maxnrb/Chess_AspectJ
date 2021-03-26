package fr.maximeNarbaud.chess.piece;

import fr.maximeNarbaud.chess.agent.Move;
import fr.maximeNarbaud.chess.agent.Player;

public class Knight extends Piece {

	public Knight(int player) {
		super(player);
	}

	@Override
	public String toString() {
		return ((this.player == Player.WHITE) ? "K" : "\u001B[31mK\u001B[0m");
	}

	@Override
	public boolean isMoveLegal(Move mv) {
		// TODO Auto-generated method stub
		if (Math.abs(mv.initialX - mv.finalX) == 2) {
			return Math.abs((mv.initialY - mv.finalY)) == 1;
		} else if (Math.abs(mv.initialX - mv.finalX) == 1) {
			return Math.abs((mv.initialY - mv.finalY)) == 2;
		}
		return false;
	}
}