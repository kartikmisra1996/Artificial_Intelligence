package student_player;

import java.util.ArrayList;

import boardgame.*;
import pentago_swap.PentagoBoardState;
import pentago_swap.PentagoBoardState.Piece;
import pentago_swap.PentagoMove;
import boardgame.*;

public class MyTools {
	
	static int boardSize = PentagoBoardState.BOARD_SIZE;

	public static double getSomething() {
		return Math.random();
	}

	public static int evaluateMove(PentagoBoardState state) {
		ArrayList<PentagoMove> allMoves = state.getAllLegalMoves();
		if(allMoves.size() == 0) {
			return 0;
		}
		int plusValue = 0;
		int player = state.getTurnPlayer();
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		if (player == 0) {
			for (int i = 0; i<boardSize; i++) {
				for (int j = 0; j<boardSize; j++) {
					Piece piece = state.getPieceAt(i, j);
					if(piece == PentagoBoardState.Piece.WHITE) {
						pieces.add(piece);
					}
				}
			}
			if (pieces.size() == 0) {
				state.getRandomMove();
			}	
		}
		return 0;
	}
	
}