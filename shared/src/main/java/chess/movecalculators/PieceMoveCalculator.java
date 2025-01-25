package chess.movecalculators;

import chess.*;
import java.util.Collection;

public abstract class PieceMoveCalculator {
    public abstract Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition myPosition);
}
