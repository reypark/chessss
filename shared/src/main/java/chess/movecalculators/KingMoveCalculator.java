package chess.movecalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

public class KingMoveCalculator extends PieceMoveCalculator {
    @Override
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();

        int[][] directions = {
                {1, 0},   // Down
                {-1, 0},  // Up
                {0, 1},   // Right
                {0, -1},  // Left
                {1, 1},   // Down-right
                {1, -1},  // Down-left
                {-1, 1},  // Up-right
                {-1, -1}  // Up-left
        };

        for (int[] direction : directions) {
            int newRow = myPosition.getRow() + direction[0];
            int newCol = myPosition.getColumn() + direction[1];

            ChessPosition newPosition = new ChessPosition(newRow, newCol);

            if (!board.isValidPosition(newPosition)) {
                continue;
            }

            if (board.isOccupied(newPosition)) {
                ChessPiece targetPiece = board.getPiece(newPosition);

                if (targetPiece.getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                    moves.add(new ChessMove(myPosition, newPosition, null));
                }
            } else {
                moves.add(new ChessMove(myPosition, newPosition, null));
            }
        }

        return moves;
    }
}
