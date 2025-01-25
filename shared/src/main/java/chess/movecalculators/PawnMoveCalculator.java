package chess.movecalculators;

import chess.*;

import java.util.ArrayList;
import java.util.Collection;

public class PawnMoveCalculator extends PieceMoveCalculator {
    @Override
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();

        ChessPiece pawn = board.getPiece(myPosition);
        ChessGame.TeamColor teamColor = pawn.getTeamColor();

        int direction = (teamColor == ChessGame.TeamColor.WHITE) ? 1 : -1;
        int startRow = (teamColor == ChessGame.TeamColor.WHITE) ? 2 : 7;

        ChessPosition oneForward = new ChessPosition(myPosition.getRow() + direction, myPosition.getColumn());
        if (board.isValidPosition(oneForward) && !board.isOccupied(oneForward)) {
            addMoveIfPromotion(moves, myPosition, oneForward, teamColor);

            if (myPosition.getRow() == startRow) {
                ChessPosition twoForward = new ChessPosition(myPosition.getRow() + 2 * direction, myPosition.getColumn());
                if (board.isValidPosition(twoForward) && !board.isOccupied(twoForward)) {
                    moves.add(new ChessMove(myPosition, twoForward, null));
                }
            }
        }

        int[] captureColumns = {myPosition.getColumn() - 1, myPosition.getColumn() + 1};
        for (int col : captureColumns) {
            ChessPosition capturePosition = new ChessPosition(myPosition.getRow() + direction, col);
            if (board.isValidPosition(capturePosition) && board.isOccupied(capturePosition)) {
                ChessPiece targetPiece = board.getPiece(capturePosition);
                if (targetPiece.getTeamColor() != teamColor) {
                    addMoveIfPromotion(moves, myPosition, capturePosition, teamColor);
                }
            }
        }

        return moves;
    }

    private void addMoveIfPromotion(Collection<ChessMove> moves, ChessPosition start, ChessPosition end, ChessGame.TeamColor teamColor) {
        int promotionRow = (teamColor == ChessGame.TeamColor.WHITE) ? 8 : 1;
        if (end.getRow() == promotionRow) {
            moves.add(new ChessMove(start, end, ChessPiece.PieceType.QUEEN));
            moves.add(new ChessMove(start, end, ChessPiece.PieceType.ROOK));
            moves.add(new ChessMove(start, end, ChessPiece.PieceType.BISHOP));
            moves.add(new ChessMove(start, end, ChessPiece.PieceType.KNIGHT));
        } else {
            moves.add(new ChessMove(start, end, null));
        }
    }
}