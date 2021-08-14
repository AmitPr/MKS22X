import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;

public class QueenBoard {
	private int[][] board;
	// The danger determining matrix -- twice as effecient as before!
	private ArrayList<Boolean> occupiedRow;
	private boolean[] occupiedDiagUp;
	private boolean[] occupiedDiagDown;
	private int size = 0;
	/*
	 * Constructor
	 */
	public QueenBoard(int size) {
		this.size = size;
		board = new int[size][size];
		occupiedRow = new ArrayList<Boolean>();
		occupiedDiagUp = new boolean[size * 2];
		occupiedDiagDown = new boolean[size * 2];
		for (int i = 0; i < size; i++) {
			occupiedRow.add(i, false);
			for (int j = 0; j < size; j++) {
				board[i][j] = 0;
			}
		}
	}

	/*
	 * Adds a queen to the board.
	 */
	public void addQueen(int r, int c) {
		board[c][r] = -1;
		occupiedRow.set(r, true);
		occupiedDiagDown[c - r + size] = true;
		occupiedDiagUp[(2 * size) - c - r - 1] = true;
	}

	/*
	 * Removes a queen if there is one at the location specified.
	 */
	public void removeQueen(int r, int c) {
		board[c][r] = 0;
		occupiedRow.set(r, false);
		occupiedDiagDown[c - r + size] = false;
		occupiedDiagUp[(2 * size) - c - r - 1] = false;
	}

	private boolean canPut(int r, int c) {
		return !(occupiedRow.get(r) || occupiedDiagUp[(2 * size) - c - r - 1] || occupiedDiagDown[c - r + size]);
	}

	/**
	 * @return false when the board is not solveable and leaves the board filled
	 *         with zeros;
	 * 
	 *         true when the board is solveable, and leaves the board in a solved
	 *         state
	 * 
	 * @throws IllegalStateException when the board starts with any non-zero value
	 * 
	 */
	public boolean solve() {
		return helper(0, 0, false, new AtomicInteger(0));
	}

	/**
	 * @return the number of solutions found, and leaves the board filled with only
	 *         0's
	 * @throws IllegalStateException when the board starts with any non-zero value
	 */
	public int countSolutions() {
		AtomicInteger ai = new AtomicInteger(0);
		helper(0, 0, true, ai);
		return ai.get();
	}

	private boolean helper(int curCol, int numQ, boolean allSolutions, AtomicInteger ai) {
		if (curCol < size) {
			int index = occupiedRow.indexOf(false);
			for (int i = index; i < size; i++) {
				if (canPut(i, curCol)) {
					addQueen(i, curCol);
					if (helper(curCol + 1, numQ + 1, allSolutions, ai)) {
						if (allSolutions) {
							ai.set(ai.get() + 1);
						} else {
							return true;
						}
					}
					removeQueen(i, curCol);
				}
			}
			return false;
		}
		if (numQ == size) {
			return true;
		}
		return false;
	}

	/**
	 * @return The output string formatted as follows: All numbers that represent
	 *         queens are replaced with 'Q' all others are displayed as underscores
	 *         '_' There are spaces between each symbol: """_ _ Q _ Q _ _ _
	 * 
	 *         _ _ _ Q
	 * 
	 *         _ Q _ _""" (pythonic string notation for clarity, excludes the
	 *         character up to the *)
	 */
	public String toString() {
		String b = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] == -1) {
					b = b + "Q ";
				} else {
					b = b + "_ ";
				}
			}
			b += "\n";
		}
		return b;
	}
}
