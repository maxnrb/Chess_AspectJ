package fr.maximeNarbaud.chess.Aspect;

import fr.maximeNarbaud.chess.Board;
import fr.maximeNarbaud.chess.agent.Move;
import fr.maximeNarbaud.chess.agent.Player;
import fr.maximeNarbaud.chess.piece.Knight;

public aspect MoveValidationAspect {

    // Pointcut+Advice to check if a move is valid
    boolean around(Player player, Move mv) : call (boolean fr.maximeNarbaud.chess.agent.Player+.isValidMove(Move)) && target(player) && args(mv) {
        if (mv == null) { return false; }

        // Check that a piece is present and that it belongs to the current player (Validation a + b)
        if (!player.getPlayGround().getGrid()[mv.initialX][mv.initialY].isOccupied() ||
                player.getPlayGround().getGrid()[mv.initialX][mv.initialY].getPiece().getPlayer() != player.getColor()) { return false; }

        // Check if move is valid for the current piece (Validation c)
        if (!player.getPlayGround().getGrid()[mv.initialX][mv.initialY].getPiece().isMoveLegal(mv)) { return false; }

        // Check if initial and final position is valid (in the board) (Validation d)
        if (mv.initialX < 0 || mv.initialX >= Board.SIZE || mv.finalX < 0 || mv.finalX >= Board.SIZE) { return false; }
        if (mv.initialY < 0 || mv.initialY >= Board.SIZE || mv.finalY < 0 || mv.finalY >= Board.SIZE) { return false; }

        // Check if there is a piece of the current player on the destination case (Validation e)
        if (player.getPlayGround().getGrid()[mv.finalX][mv.finalY].isOccupied() &&
                player.getPlayGround().getGrid()[mv.finalX][mv.finalY].getPiece().getPlayer() == player.getColor()) { return false; }

        // Check if the piece doesn't skip an other pieces, except for the knight (Validation f)
        if (player.getPlayGround().getGrid()[mv.initialX][mv.initialY].getPiece().getClass() != Knight.class) {

            // Check for horizontal move (ex: a1 => d1)
            if (mv.initialY == mv.finalY) {
                return isHorizontalValid(player, mv);
            }

            // Check for a vertical move (ex: a1 => a4)
            if (mv.initialX == mv.finalX) {
                return isVerticalValid(player, mv);
            }

            // Check for diagonal move (ex: c1 => e3)
            else {
                return isDiagonalValid(player, mv);
            }

        }

        // Returns true if the move has passed all the validations
        return true;
    }

    // Checks if there are any pieces present for an horizontal movement
    private boolean isHorizontalValid(Player player, Move mv) {

        // Do mv.initialY'+1' so as not to take the initial case (we don't take final case later in for)
        int actualX = mv.initialX+1, maxX = mv.finalX;

        // If initial position is higher than destination (ex: d1 => a1)
        // We invert the initial position and the destination
        if (mv.initialX > mv.finalX) {
            actualX = mv.finalX+1;
            maxX = mv.initialX;
        }

        // We check all cases between the initial and the final position, except initial and final cases
        // Do actualX '<' maxX so as not to take the final case
        for (; actualX < maxX; actualX++) {

            // Return false if a piece is present
            if (player.getPlayGround().getGrid()[actualX][mv.initialY].isOccupied()) { return false; }
        }

        // Return true if there is no piece present
        return true;
    }

    // Checks if there are any pieces present for a vertical movement
    private boolean isVerticalValid(Player player, Move mv) {

        // Do mv.initialY'+1' so as not to take the initial case (we don't take final case later in for)
        int actualY = mv.initialY+1, maxY = mv.finalY;

        // If initial position is higher than destination (ex: a4 => a1)
        // We invert the initial position and the destination
        if (mv.initialY > mv.finalY) {
            actualY = mv.finalY+1;
            maxY = mv.initialY;
        }

        // We check all cases between the initial and the final position, except initial and final cases
        // Do actualY '<' maxY so as not to take the final case
        for (; actualY < maxY; actualY++) {

            // Return false if a piece is present
            if (player.getPlayGround().getGrid()[mv.initialX][actualY].isOccupied()) { return false; }
        }

        // Return true if there is no piece present
        return true;
    }

    // Checks if there are any pieces present for a diagonal movement
    private boolean isDiagonalValid(Player player, Move mv) {
        int actualX = mv.initialX+1, maxX = mv.finalX;
        int actualY = mv.initialY+1, maxY = mv.finalY;

        // If initial position is higher than destination (ex: e3 => c1)
        // We invert the initial position and the destination
        if (mv.initialX > mv.finalX && mv.initialY > mv.finalY) {
            actualY = mv.finalY+1;
            maxY = mv.initialY;
            actualX = mv.finalX+1;
            maxX = mv.initialX;

        // If initial X is higher than destination X and initial Y is lower than final Y (ex: d1 => a4)
        } else if (mv.initialX > mv.finalX && mv.initialY < mv.finalY) {
            int minX = mv.finalX;

            // We can do mv.initialX'-1' because mv.initialX is already > 0, according to mv.initialX > mv.finalX
            actualX = mv.initialX-1;

            // We check all cases between the initial and the final position, except initial and final cases
            for (; actualX > minX && actualY < maxY; actualX--, actualY++) {
                if (player.getPlayGround().getGrid()[actualX][actualY].isOccupied()) { return false; }
            }

            // Return true if there is no piece present
            return true;

        // If initial X is lower than destination X and initial Y is higher than final Y (ex: a4 => d1)
        } else if (mv.initialX < mv.finalX && mv.initialY > mv.finalY) {
            int minY = mv.finalY;

            // We can do mv.initialY'-1' because mv.initialY is already > 0, according to mv.initialY > mv.finalY
            actualY = mv.initialY-1;

            // We check all cases between the initial and the final position, except initial and final cases
            for (; actualX < maxX && actualY > minY; actualX++, actualY--) {
                if (player.getPlayGround().getGrid()[actualX][actualY].isOccupied()) { return false; }
            }

            // Return true if there is no piece present
            return true;
        }

        for (; actualX < maxX && actualY < maxY; actualX++, actualY++) {
            if (player.getPlayGround().getGrid()[actualX][actualY].isOccupied()) { return false; }
        }

        // Return true if there is no piece present
        return true;
    }
}
