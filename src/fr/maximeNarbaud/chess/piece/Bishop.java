package fr.maximeNarbaud.chess.piece;

import fr.maximeNarbaud.chess.agent.Move;
import fr.maximeNarbaud.chess.agent.Player;

public class Bishop extends Piece {

    public Bishop(int player) {
        super(player);
    }

    public Bishop() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return ((this.player == Player.WHITE) ? "B" : "\u001B[31mB\u001B[0m");
    }

    @Override
    public boolean isMoveLegal(Move mv) {
        float x = mv.initialX - mv.finalX;
        float y = mv.initialY - mv.finalY;

        if (x == 0) {
            return false;
        }

        return Math.abs(y / x) == 1.0;
    }
}
