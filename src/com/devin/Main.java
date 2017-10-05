package com.devin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) {
		final String INPUT_FILE = "3x3_test.txt";
		Matrix matrix = new Matrix(readMatrixFromFile(INPUT_FILE));
		matrix.print_matrix();
		matrix.rref();
		matrix.print_matrix();
	}

	private static float[][] readMatrixFromFile(String inputFile) {
		float[][] matrix = null;
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			String s = "";
			s = br.readLine();
			String[] parts = s.split("\\s+");
			int num_rows = Integer.parseInt(parts[0]);
			int num_cols = Integer.parseInt(parts[1]);
			matrix = new float[num_rows][num_cols];
			int cur_row = 0;
			int cur_col = 0;
			while ((s = br.readLine()) != null) {
				parts = s.split("\\s+");
				if (parts.length != num_cols) {
					System.err.printf("incorrect number of elements in row %d\n", cur_row);
					continue;
				}
				for (cur_col = 0; cur_col < num_cols; cur_col++) {
					float f = Float.parseFloat(parts[cur_col]);
					matrix[cur_row][cur_col] = f;
				}
				cur_row++;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return matrix;
	}

}
