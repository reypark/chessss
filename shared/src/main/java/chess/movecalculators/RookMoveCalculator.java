package chess.movecalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

public class RookMoveCalculator extends PieceMoveCalculator {
    @Override
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();

        int[][] directions = {
                {1, 0},   // Down
                {-1, 0},  // Up
                {0, 1},   // Right
                {0, -1}   // Left
        };

        for (int[] direction : directions) {
            int row = myPosition.getRow();
            int col = myPosition.getColumn();

            while (true) {
                row += direction[0];
                col += direction[1];

                ChessPosition newPosition = new ChessPosition(row, col);

                if (!board.isValidPosition(newPosition)) {
                    break;
                }

                if (board.isOccupied(newPosition)) {
                    ChessPiece targetPiece = board.getPiece(newPosition);

                    if (targetPiece.getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                        moves.add(new ChessMove(myPosition, newPosition, null));
                    }
                    break;
                }

                moves.add(new ChessMove(myPosition, newPosition, null));
            }
        }

        return moves;
    }
}