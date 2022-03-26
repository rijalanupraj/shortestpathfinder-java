package backend;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Perform User related Task
 */
public class User {
    String username, password;
    static String FILENAME = "users.txt";

    // Constructor
    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Create new file if it doesn't exists
    private static void createFileIfNotExists() {
        try {
            File f1 = new File(FILENAME);
            if (!f1.exists()) {
                f1.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add new user to the file
    public static boolean registerUser(String username, String password) {
        createFileIfNotExists();
        boolean successful = false;
        try {
            FileWriter fileWritter = new FileWriter(FILENAME, true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            String data = username + "~" + password + "\n";
            bw.write(data);
            bw.close();
            successful = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return successful;
    }

    // Check if the username already exists
    public static boolean usernameExists(String username) {
        ArrayList<ArrayList<String>> usersList = getAllUsers();
        for (int i = 0; i < usersList.size(); i++) {
            boolean match = usersList.get(i).get(0).equals(username);
            if (match) {
                return true;
            }
        }
        return false;
    }

    // Check user Password and Username and return true if it is valid
    public static boolean checkCredentials(String username, String password) {
        ArrayList<ArrayList<String>> usersList = getAllUsers();
        for (int i = 0; i < usersList.size(); i++) {
            boolean isCorrect = usersList.get(i).get(0).equals(username) && usersList.get(i).get(1).equals(password);
            if (isCorrect) {
                return true;
            }
        }
        return false;
    }

    // Read and stores users data in ArrayList
    private static ArrayList<ArrayList<String>> getAllUsers() {
        createFileIfNotExists();
        ArrayList<ArrayList<String>> usersList = new ArrayList<ArrayList<String>>();
        try {
            File myObj = new File(FILENAME);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                ArrayList<String> a1 = new ArrayList<String>();
                String data = myReader.nextLine();
                String[] dataString = data.split("~");
                for (String x : dataString) {
                    a1.add(x);
                }
                usersList.add(a1);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return usersList;
    }

}
