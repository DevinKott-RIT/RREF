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

	public void swapRows(int row1, int row2) {
		if (row1 < 0 || row2 < 0 || row1 >= numRows || row2 >= numCols) {
			System.err.printf("Matrix does not have rows: %d and/or %d. Available: 0-%d rows, 0-%d cols.\n", row1, row2,
					numRows, numCols);
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
				System.out.printf("%.2f ", matrix[cur_row][cur_col]);
			}
			System.out.printf("%.2f\n", matrix[cur_row][numCols - 1]);
		}
		System.out.println();
	}

}