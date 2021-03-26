package fr.maximeNarbaud.chess.piece;

import fr.maximeNarbaud.chess.agent.Move;
import fr.maximeNarbaud.chess.agent.Player;

public class Rook extends Piece {

	public Rook(int player) {
		super(player);
	}

	public Rook() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return ((this.player == Player.WHITE) ? "R" : "\u001B[31mR\u001B[0m");
	}

	@Override
	public boolean isMoveLegal(Move mv) {
		// TODO Auto-generated method stub
		return (( mv.initialY - mv.finalY) == 0) || ((mv.initialX - mv.finalX) == 0);
	}
}