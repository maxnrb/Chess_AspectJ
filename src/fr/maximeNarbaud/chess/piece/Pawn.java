package fr.maximeNarbaud.chess.piece;

import fr.maximeNarbaud.chess.agent.Move;
import fr.maximeNarbaud.chess.agent.Player;

public class Pawn extends Piece {

	public Pawn(int player) {
		super(player);
	}

	@Override
	public String toString() {
		return ((this.player == Player.WHITE) ? "P" : "\u001B[31mP\u001B[0m");
	}

	@Override
	public boolean isMoveLegal(Move mv) {
		boolean special = false;

		// player1
		if (player == Player.WHITE) {
			if ((Math.abs(mv.finalX - mv.initialX) == 0) && (Math.abs(mv.finalY - mv.initialY) < 3)) {
				special = true;
			}
			return (((mv.finalY - mv.initialY) == 1) && (Math.abs(mv.finalX - mv.initialX) <= 1)) || special;
		}

		// player 2
		if ((Math.abs(mv.finalX - mv.initialX) == 0) && (Math.abs(mv.finalY - mv.initialY) < 3)) {
			special = true;
		}

		return (((mv.finalY - mv.initialY) == -1) && (Math.abs(mv.finalX - mv.initialX) <= 1)) || special;

	}

}
