// Backtracking approach to solve Sudoku
// Abhishek Ramesh

import java.util.Scanner;

public class Sudoku {

	// Create the Sudoku grid with pre-given values
	public static int [][] Sudoku_Puzzle = {
		{5,3,0,0,7,0,0,0,0},
		{6,0,0,1,9,5,0,0,0},
		{0,9,8,0,0,0,0,6,0},
		{8,0,0,0,6,0,0,0,3},
		{4,0,0,8,0,3,0,0,1},
		{7,0,0,0,2,0,0,0,6},
		{0,6,0,0,0,0,2,8,0},
		{0,0,0,4,1,9,0,0,5},
		{0,0,0,0,8,0,0,7,9}
	};


	// Initialize the board, empty values, and size of Sudoku board
	public int [][] board;
	public int size = 9;
	public int emptyVal = 0;

	// Sudoku Board checking with the original grid
	public Sudoku(int [][] board) {
		this.board = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}

	// Check if the sudoku value proposed already exists in the row
	public boolean existInRow(int row, int num) {
		for (int i = 0; i < size; i++)
			if (board[row][i] == num)
				return true;

			return false;
	}

	// Check if the sudoku value proposed already exists in the column
	public boolean existInCol(int col, int num) {
		for (int i = 0; i < size; i++)
			if (board[i][col] == num)
				return true;

			return false;
	}

	// Check if the sudoku value proposed already exists in the mini grid
	public boolean existInBox(int row, int col, int num) {
		int rownum = row - row % 3;
		int colnum = col - col % 3;

		for (int i = rownum; i < rownum + 3; i++)
			for (int j = colnum; j < colnum + 3; j++)
				if (board[i][j] == num)
					return true;

				return false;
	}

	// Check if number can exist in the certain position
	public boolean canExist(int row, int col, int num) {
		return (!existInRow(row, num)) && (!existInCol(col, num)) && (!existInBox(row, col, num));
	}


	// Fill the Sudoku Puzzle using backtracking
	public boolean fillBoard() {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (board[row][col] == emptyVal){
					for (int num = 1; num <= size; num++) {
						if (canExist(row, col, num)) {
							board[row][col] = num;

							if (fillBoard()) {
								return true;
							}
							else {
								board[row][col] = emptyVal;
							}
						}
					}
					return false; 
				}
			}
		}
		return true;
	}

	// Display the Sudoku Board with the values
	public void printBoard() {
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++) {
				System.out.print(" " + board[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	// Main function which shows the unsolved Sudoku Board and the solved Sudoku Board
	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku(Sudoku_Puzzle);
		System.out.println("Solve this Sudoku Grid!");
		sudoku.printBoard();

		if (sudoku.fillBoard()) {
			System.out.println("Good job, your program solved the Sudoku puzzle :)");
			sudoku.printBoard();
		} 

		else {
			System.out.println("The program couldn't solve the Sudoku puzzle :(");
		}
	}
}