package chess.movecalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

public class KnightMoveCalculator extends PieceMoveCalculator {
    @Override
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();

        int[][] directions = {
                {2, 1},   // 2 up, 1 right
                {2, -1},  // 2 up, 1 left
                {-2, 1},  // 2 down, 1 right
                {-2, -1}, // 2 down, 1 left
                {1, 2},   // 1 up, 2 right
                {1, -2},  // 1 up, 2 left
                {-1, 2},  // 1 down, 2 right
                {-1, -2}  // 1 down, 2 left
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
