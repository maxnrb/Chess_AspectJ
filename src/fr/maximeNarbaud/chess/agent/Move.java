package fr.maximeNarbaud.chess.agent;

public class Move {
	public int initialX, initialY, finalX, finalY;

	public Move(int initialX, int initialY, int finalX, int finalY) {
		this.initialX = initialX;
		this.initialY = initialY;

		this.finalX = finalX;
		this.finalY = finalY;
	}

	public String toString() {
		return (char)('a' + initialX) + "" + (initialY+1) + " => " + (char)('a' + finalX) + "" + (finalY+1);
	}
}
