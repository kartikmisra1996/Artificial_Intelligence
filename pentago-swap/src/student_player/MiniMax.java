// Kartik Misra 260663577

package student_player;

import java.util.ArrayList;

import boardgame.*;
import javafx.util.Pair;
import pentago_swap.*;

public class MiniMax {
	static int size = PentagoBoardState.BOARD_SIZE;

	//implementation of the minimax algorithm 
	public static Pair<Integer, PentagoMove> miniMaxAlgo(PentagoBoardState boardState, int depth, int playerTurn) {
		
		ArrayList<PentagoMove> possibleMoves = boardState.getAllLegalMoves();
		int score = 0;
		
		//assign a high or low value to begin with, no move can have that high/low a value
		if (playerTurn == 1) {
			score = Integer.MIN_VALUE;
		}
		else {
			score = Integer.MAX_VALUE;
		}
		
		int currentScore;
		PentagoMove chosenMove = null;
		
		//when all moves have been propagated up to the depth desired, find the most valuated move
		if (possibleMoves.size() == 0 || depth == 0) {
			score = heuristic(boardState);
		}
		else {
			//iterating through all possible moves
			for(int i = 0; i < possibleMoves.size(); i++) {
				
				//cloning board to predict 2 moves ahead (depth is 2)
				PentagoBoardState clone = (PentagoBoardState) boardState.clone();
				clone.processMove(possibleMoves.get(i));
				int player = 1 - playerTurn;
				
				//0 = white, trying to maximize
				if (player == 0) {
					
					//if a found score is higher, make it the new current score, then use minimize at the lower depth
					//player will be "swapped" (if opponent is current player, we'll simulate the player's turn) when making the recursive call
					currentScore = miniMaxAlgo(clone, depth - 1, player).getKey();
					
					if (currentScore >= score) {
						chosenMove = possibleMoves.get(i);
						score = currentScore;
					}
				} 
				
				//1 = black, trying to minimize
				else if (player == 1) {
					
					//if a found score is lower, make it the new current score, then use maximize at the lower depth
					//player will be "swapped" (if opponent is current player, we'll simulate the player's turn) when making the recursive call
					currentScore = miniMaxAlgo(clone, depth - 1, player).getKey();
					
					if (currentScore <= score) {
						chosenMove = possibleMoves.get(i);
						score = currentScore;
					}
				}
			}
		}
		//decided to go with pair to avoid having separated methods for the score and the move
		//it is easy to extract the required value using .getKey() or .getValue()
		Pair<Integer, PentagoMove> answer = new Pair<>(score, chosenMove);
		return answer;
	}

	//move evaluation: if a move is adjacent to another piece of the same color, pick that move
	public static int heuristic(PentagoBoardState boardState) {
		int value = 0;
		
		for (int i = 0; i<size; i++) {
			for (int j = 0; j<size; j++) {
				if (boardState.getPieceAt(i, j) == PentagoBoardState.Piece.BLACK) {
					value += 10;
				}
			}
		}
		return value;
	}

	/* 	All the code below doesn't work
	 	Was kept as to not lose my "progress" 
		It is required for the report, to explain my thought process (excuse the mess)
	*/
	public static PentagoMove excecuteMiniMax(PentagoBoardState bs, int depth) {
		long timerStart = System.currentTimeMillis();
		PentagoMove bestMove = null;
		int highestVal = Integer.MIN_VALUE;
		int lowestVal = Integer.MAX_VALUE;
		int currentVal = 0;
		int possibleMoves = bs.getAllLegalMoves().size();
		for (PentagoMove move : bs.getAllLegalMoves()) {
			PentagoBoardState stateClone = (PentagoBoardState) bs.clone();
			stateClone.processMove(move);
			if(bs.getTurnPlayer() == 0) {
				Max(stateClone, depth-1);
			}
			else if(bs.getTurnPlayer() == 1) {
				Min(stateClone, depth-1);
			}
			if (bs.getTurnPlayer() == 0 && currentVal >= highestVal) {
				highestVal = currentVal;
				bestMove = move;
			}
			else if (bs.getTurnPlayer() == 1 && currentVal <= lowestVal) {
				lowestVal = currentVal;
				bestMove = move;
			}
			if (System.currentTimeMillis() - timerStart >= 1900) {
				break;
			}
		}
		long totalTime = System.currentTimeMillis() - timerStart;
		System.out.println(totalTime);
		return bestMove;
	}

	public static int Min(PentagoBoardState boardState, int depth) {
		long timerStart = System.currentTimeMillis();
		if (depth == 0 || boardState.gameOver()) {
			return MoveEvaluation.evalBlack(boardState, depth);
		}
		int lowestVal = Integer.MAX_VALUE;
		int currentVal;
		ArrayList<PentagoMove> allMoves = boardState.getAllLegalMoves();
		int sizeOfMoves = allMoves.size();
		for (int i = 0; i<sizeOfMoves; i++) {
			PentagoBoardState stateClone = (PentagoBoardState) boardState.clone();
			PentagoMove move = allMoves.get(i);
			stateClone.processMove(move);
			currentVal = Max(boardState, depth-1);
			if (currentVal <= lowestVal) {
				lowestVal = currentVal;
			}
			if (System.currentTimeMillis() - timerStart >= 1900) {
				break;
			}
		}
		return lowestVal;
	}

	public static int Max(PentagoBoardState boardState, int depth) {
		long timerStart = System.currentTimeMillis();
		if (depth == 0 || boardState.gameOver()) {
			return MoveEvaluation.evalBlack(boardState, depth);
		}
		int highestVal = Integer.MIN_VALUE;
		int currentVal;
		ArrayList<PentagoMove> allMoves = boardState.getAllLegalMoves();
		int sizeOfMoves = allMoves.size();
		for (int i = 0; i<sizeOfMoves; i++) {
			PentagoBoardState stateClone = (PentagoBoardState) boardState.clone();
			PentagoMove move = allMoves.get(i);
			stateClone.processMove(move);
			currentVal = Min(boardState, depth-1);
			if (currentVal >= highestVal) {
				highestVal = currentVal;
			}
			if (System.currentTimeMillis() - timerStart >= 1900) {
				break;
			}
		}
		return highestVal;
	}

}
