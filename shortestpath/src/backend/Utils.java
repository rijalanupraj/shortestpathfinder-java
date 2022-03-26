package backend;

import java.io.*;

// Extra functionality required for the project in present here
public class Utils {
    static int vertex = 100;
    static String FILENAME = "matrix.txt";

    // Read Matrix to File
    public static int[][] readMatrixFromFile() {
        int[][] matrix = new int[vertex][vertex];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
            String line = "";
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] cols = line.split(",");
                int col = 0;
                for (String c : cols) {
                    matrix[row][col] = Integer.parseInt(c);
                    col++;
                }
                row++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matrix;
    }

    // Write matrix to file
    public static void writeMatrixToFile(int[][] matrix) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < matrix.length; i++)// for each row
        {
            for (int j = 0; j < matrix.length; j++)// for each column
            {
                builder.append(matrix[i][j] + "");// append to the output string
                if (j < matrix.length - 1)// if this is not the last row element
                    builder.append(",");// then add comma (if you don't like commas you can use spaces)
            }
            builder.append("\n");// append new line at the end of the row
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME));
            writer.write(builder.toString());// save the string representation of the board
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Change the matrix after Deletion of device
    public static void changeMatrixAfterDeviceDeletion(int positionIndex) {
        int[][] matrix = readMatrixFromFile();
        int[][] newMatrix = new int[vertex][vertex];
        for (int i = positionIndex; i < matrix.length - 1; i++) {

            for (int j = positionIndex; j < matrix.length - 1; j++) {
                newMatrix[i][j] = matrix[i + 1][j + 1];
            }
        }
        writeMatrixToFile(newMatrix);

    }
}
