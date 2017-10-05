package com.devin;

public class Matrix {

	private int numRows;
	private int numCols;

	private float[][] matrix;

	public Matrix(float[][] matrix) {
		this.matrix = matrix;
		this.numRows = this.matrix.length;
		this.numCols = this.matrix[0].length;
	}
	
	public void rref() {
		System.out.println("Starting Matrix:");
		this.print_matrix();
		moveZeroRowsToBottom();
		int colHead = 0;
		int curRow = 0;
		while (curRow < numRows) {
			if (colHead >= numCols) {
				break;
			}
			if (matrix[curRow][colHead] == 0) {
				// go find a row and swap it
				boolean foundRow = false;
				for (int i = curRow + 1; i < numRows; i++) {
					if (matrix[i][colHead] != 0) {
						System.out.printf("Swapped rows %d and %d:\n", curRow, i);
						this.swapRows(curRow, i);
						this.print_matrix();
						foundRow = true;
						break;
					}
				}
				if (!foundRow) {
					colHead++;
				}
			} else {
				System.out.printf("Multiplied row %d by %.2f\n", curRow, 1 / matrix[curRow][colHead]);
				this.multiplyRow(curRow, 1 / matrix[curRow][colHead]);
				this.print_matrix();
				for (int i = curRow + 1; i < numRows; i++) {
					if (matrix[i][colHead] != 0) {
						System.out.printf("Added %.2f of row %d to row %d:\n", -matrix[i][colHead], curRow, i);
						this.addRowToRow(curRow, i, -matrix[i][colHead]);
						this.print_matrix();
					} else {
						break;
					}
				}
				curRow++;
				colHead++;
			}
		}
		
		curRow = numRows - 1;
		while (curRow >= 0) {
			if (this.isZeroRow(curRow)) {
				curRow--;
				continue;
			}
			colHead = 0;
			while (matrix[curRow][colHead] != 1) {
				colHead++;
			}
			for (int i = curRow - 1; i >= 0; i--) {
				if (matrix[i][colHead] != 0) {
					System.out.printf("Added %.2f of row %d to row %d:\n", -matrix[i][colHead], curRow, i);
					this.addRowToRow(curRow, i, -matrix[i][colHead]);
					this.print_matrix();
				}
			}
			curRow--;
		}
	}
	
	private void moveZeroRowsToBottom() {
		int moveHere = numRows - 1;
		int rowIndex = 0;
		do {
			if (isZeroRow(rowIndex)) {
				this.swapRows(rowIndex, moveHere);
				moveHere--;
				rowIndex--;
			}
			rowIndex++;
		} while (rowIndex < moveHere);
	}
	
	private boolean isZeroRow(int row) {
		if (row < 0 || row >= numRows) {
			System.err.printf("Invalid row: %d\n", row);
			return false;
		}
		int colIndex = 0;
		do {
			if (matrix[row][colIndex] != 0)
				return false;
			colIndex++;
		} while (colIndex < numCols);
		return true;
	}
	
	public void addRowToRow(int row1, int row2, float value) {
		if (row1 < 0 || row2 < 0 || row1 >= numRows || row2 >= numCols) {
			System.err.printf("Matrix does not have rows: %d and/or %d. Available: 0-%d rows, 0-%d cols.\n", row1, row2,
					numRows - 1, numCols - 1);
			return;
		}
		int colIndex = 0;
		do {
			matrix[row2][colIndex] += matrix[row1][colIndex] * value;
			colIndex++;
		} while (colIndex < numCols);
	}
	
	public void multiplyRow(int row, float value) {
		if (row < 0 || row >= numRows) {
			System.err.printf("Invalid row: %d. Available: 0-%d rows\n", row, numRows - 1);
			return;
		}
		
		int colIndex = 0;
		do {
			matrix[row][colIndex] *= value;
			colIndex++;
		} while (colIndex < numCols);
	}

	public void swapRows(int row1, int row2) {
		if (row1 < 0 || row2 < 0 || row1 >= numRows || row2 >= numRows) {
			System.err.printf("Matrix does not have rows: %d and/or %d. Available: 0-%d rows, 0-%d cols.\n", row1, row2,
					numRows - 1, numCols - 1);
			return;
		}
		int colIndex = 0;
		do {
			float temp = matrix[row2][colIndex];
			matrix[row2][colIndex] = matrix[row1][colIndex];
			matrix[row1][colIndex] = temp;
			colIndex++;
		} while (colIndex < numCols);
	}

	public void print_matrix() {
		int cur_row = 0, cur_col = 0;
		for (; cur_row < numRows; cur_row++) {
			for (cur_col = 0; cur_col < numCols - 1; cur_col++) {
				float num = matrix[cur_row][cur_col];
				if (num == -0) {
					num = Math.abs(num);
				}
				System.out.printf("%.2f ", num);
			}
			float num = matrix[cur_row][cur_col];
			if (num == -0) {
				num = Math.abs(num);
			}
			System.out.printf("%.2f\n", num);
		}
		System.out.println();
	}

}
