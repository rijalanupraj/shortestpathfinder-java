package backend;

import java.util.*;
import java.io.*;

/**
 * This class use 2d Array as graph
 */

public class GraphMatrix {
    // Number of vertex of graph
    int vertex = 100;
    int matrix[][];
    int prevPath[];
    // Shortest distance from source to destination
    public int shortestDistance = 0;
    int minDistance[];
    // File to write 2d array
    static String FILENAME = "matrix.txt";

    public GraphMatrix() {
        matrix = new int[vertex][vertex];
    }

    // Add Edge and write to file
    public void addEdges(int source, int destination, int cost) {
        matrix[source][destination] = cost;
        matrix[destination][source] = cost;
        writeMatrixToFile();
    }

    // Remove Edges and write to file
    public void removeEdges(int source, int destination) {
        matrix[source][destination] = 0;
        matrix[destination][source] = 0;
        writeMatrixToFile();
    }

    // Get all connected points of a point in graph
    public ArrayList<Integer> getConnectedPoint(int point) {
        ArrayList<Integer> connectedPoints = new ArrayList<Integer>();

        for (int j = 0; j < vertex; j++) {
            if (matrix[point][j] > 0) {
                connectedPoints.add(j);
            }

        }
        return connectedPoints;
    }

    // Find shorted path for all points
    public void shortestPath(int source, int destination) {

        boolean visited[] = new boolean[vertex];
        minDistance = new int[vertex];
        prevPath = new int[vertex];

        for (int i = 0; i < vertex; i++) {

            minDistance[i] = Integer.MAX_VALUE;
            prevPath[i] = -1;
        }

        minDistance[source] = 0;

        for (int i = 0; i < matrix.length; i++) {

            int minVertex = findMinimumVertex(minDistance, visited);
            visited[minVertex] = true;

            for (int j = 0; j < matrix.length; j++) {

                if (matrix[minVertex][j] != 0 && !visited[j]) {

                    int newDistance = minDistance[minVertex] + matrix[minVertex][j];

                    if (newDistance < minDistance[j]) {
                        minDistance[j] = newDistance;
                        prevPath[j] = minVertex;

                    }
                }
            }
        }
    }

    // shortest path method uses this
    public int findMinimumVertex(int minDistance[], boolean visited[]) {
        int minVertex = -1;
        for (int i = 0; i < minDistance.length; i++) {

            if (!visited[i] && (minVertex == -1 || minDistance[i] < minDistance[minVertex])) {

                minVertex = i;
            }
        }

        return minVertex;
    }

    // Print graph - Not used
    public void printGraph() {

        System.out.println("Graph is");
        for (int i = 0; i < vertex; i++) {

            for (int j = 0; j < vertex; j++) {

                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }

    // Print Edges - Not Used
    public void printEdges() {

        for (int i = 0; i < vertex; i++) {

            System.out.print("vertex " + i + " is connected to ");

            for (int j = 0; j < vertex; j++) {

                if (matrix[i][j] == 1) {
                    System.out.print(j + " ");
                }

            }
            System.out.println("");
        }
    }

    // Make the use of shortest path and give the shortest path from source to
    // destination
    public ArrayList<Integer> getShortestPath(int source, int destination) {

        ArrayList<Integer> path = new ArrayList<Integer>();
        if (source < destination) {
            int y = destination;
            shortestPath(source, destination);
            shortestDistance = minDistance[y];
            while (prevPath[y] != -1) {
                path.add(y);
                y = prevPath[y];
            }
            path.add(source);
        } else {
            int y = source;
            shortestPath(destination, source);
            shortestDistance = minDistance[y];
            while (prevPath[y] != -1) {
                path.add(y);
                y = prevPath[y];
            }
            path.add(destination);
            Collections.reverse(path);

        }
        return path;

    }

    // Writing matrix to file
    public void writeMatrixToFile() {
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
            writer.write(builder.toString());// save the string representation of the matrix
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createIfFileDoesnotExist() {
        File f = new File(FILENAME);
        if (!f.exists()) {
            writeMatrixToFile();
        }
    }

    // Reading from matrix
    public void readMatrixFromFile() {
        int[][] fileMatrix = new int[matrix.length][matrix.length];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
            String line = "";
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] cols = line.split(",");
                int col = 0;
                for (String c : cols) {
                    fileMatrix[row][col] = Integer.parseInt(c);
                    col++;
                }
                row++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        matrix = fileMatrix;
    }

    // Change the matrix after the device is deleted
    public void changeMatrixAfterDeviceDeletion(int positionIndex) {
        int[][] newMatrix = new int[this.vertex][this.vertex];
        for (int i = positionIndex; i < this.matrix.length - 1; i++) {

            for (int j = positionIndex; j < this.matrix.length - 1; j++) {
                newMatrix[i][j] = this.matrix[i + 1][j + 1];
            }
        }
        matrix = newMatrix;
        writeMatrixToFile();

    }

}
