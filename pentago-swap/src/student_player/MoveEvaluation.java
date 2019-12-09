// Kartik Misra 260663577

package student_player;

import boardgame.*;
import pentago_swap.*;
import pentago_swap.PentagoBoardState.Piece;

import java.util.ArrayList;

/* 	All the code below doesn't work
	Was kept as to not lose my "progress" 
	It is required for the report, to explain my thought process (excuse the mess)
*/

public class MoveEvaluation {

	PentagoBoard thisBoard;
	PentagoPlayer thisPlayer;
	int thisDepth;
	static int size = PentagoBoardState.BOARD_SIZE;

	public MoveEvaluation (PentagoBoard b, int d) {
		thisBoard = b;
		thisDepth = d;
	}
	
	
	static int evalBlack(PentagoBoardState boardState, int dept) {

		ArrayList <PentagoMove> allMoves = boardState.getAllLegalMoves();

		// Black player (the one that goes second)
		int value = 0;
		ArrayList<Piece> blackPieces = evaluateBlackPiece(boardState);
		int allPieces = evaluateAllPieces(boardState);

		for (int x = 0; x<size; x++) {
			for (int y = 0; y<size; y++) {
				if (boardState.getPieceAt(x, y) == PentagoBoardState.Piece.BLACK) {

				}
			}
		}
		return 0;
	}

	int evalWhite(PentagoBoardState boardState, int dept) {
		ArrayList<Piece> whitePieces = evaluateWhitePiece(boardState);
		int allPieces = evaluateAllPieces(boardState);
		return 0;
	}



	public double calculateScore(PentagoBoardState thisboard, PentagoPlayer player, int depth) {

		//int scorePlayer = evaluatePiece(thisBoard, thisBoard.getTurnPlayer());
		//int scoreOpponent = evaluatePiece(thisBoard, ((PentagoBoardState) thisBoard.getBoardState()).getOpponent());

		//return scorePlayer/(scoreOpponent+scorePlayer);

		
		//0 means it is the white player, which starts first and wishes to maximize
		if (thisBoard.getBoardState().getTurnPlayer() == 0) {

		}

		//otherwise we have 1, the black player, which starts second and wishes to minimize
		else {

		}

		return 0;
		 
	}

	public static ArrayList<Piece> evaluateWhitePiece(PentagoBoardState boardS) {
		int whiteCount = 0;
		ArrayList<Piece> result = new ArrayList<Piece>();
		for (int i = 0; i<size; i++) {
			for (int j = 0; j<size ; j++) {
				Piece piece = boardS.getPieceAt(i,j);
				if (piece.equals(Piece.WHITE)){
					whiteCount++;
					result.add(piece);
				}
			}
		}
		return result;
	}

	public static ArrayList<Piece> evaluateBlackPiece(PentagoBoardState boardS) {
		int blackCount = 0;
		ArrayList<Piece> result = new ArrayList<Piece>();
		for (int i = 0; i<size; i++) {
			for (int j = 0; j<size ; j++) {
				Piece piece = boardS.getPieceAt(i,j);
				if (piece.equals(Piece.BLACK)){
					blackCount++;
					result.add(piece);
				}
			}
		}
		return result;
	}

	public static int evaluateAllPieces(PentagoBoardState boardS) {
		//return evaluateBlackPiece(boardS) + evaluateWhitePiece(boardS);
		return 0;
	}
	
}
