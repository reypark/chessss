package chess;

import chess.movecalculators.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory to retrieve the appropriate PieceMoveCalculator for each PieceType
 */
public class MoveCalculatorFactory {

    private static final Map<ChessPiece.PieceType, PieceMoveCalculator> calculatorMap = new HashMap<>();

    static {
        calculatorMap.put(ChessPiece.PieceType.KING, new KingMoveCalculator());
        calculatorMap.put(ChessPiece.PieceType.BISHOP, new BishopMoveCalculator());
        calculatorMap.put(ChessPiece.PieceType.KNIGHT, new KnightMoveCalculator());
        calculatorMap.put(ChessPiece.PieceType.PAWN, new PawnMoveCalculator());
//        calculatorMap.put(ChessPiece.PieceType.QUEEN, new QueenMoveCalculator());
//        calculatorMap.put(ChessPiece.PieceType.ROOK, new RookMoveCalculator());

    }

    public static PieceMoveCalculator getCalculator(ChessPiece.PieceType pieceType) {
        return calculatorMap.get(pieceType);
    }
}
