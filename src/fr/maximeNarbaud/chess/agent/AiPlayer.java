package fr.maximeNarbaud.chess.agent;

import java.util.Random;
import fr.maximeNarbaud.chess.Board;

public class AiPlayer extends Player {
	// dies roooooll
	private final Random Dies = new Random();

	public AiPlayer(int arg, Board board) {
		setColor(arg);
		this.playGround = board;
	}

	@Override
	public boolean isValidMove(Move mv) {
		return true;
	}

	@Override
	public Move makeMove() {
		Move mv;

		do {
			mv = new Move(Dies.nextInt(8), Dies.nextInt(8), Dies.nextInt(8), Dies.nextInt(8));

		} while (!isValidMove(mv));

		playGround.movePiece(mv);
		System.out.println("AI played: " + mv.toString());

		return mv;
	}
}
