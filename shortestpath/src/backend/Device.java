package backend;

import java.io.*;
import java.util.*;

/**
 * This class is a model for Device Everything related to device is performed
 * here. ex: CRUD, writing/ reading to file
 */
public class Device {
    // Device information
    public String id, modelId, name, description;
    // File to write the information of device
    static String FILENAME = "device.txt";

    // Constructor for device
    public Device(String id, String modelId, String name, String description) {
        this.id = id;
        this.modelId = modelId;
        this.name = name;
        this.description = description;
    }

    private static void createFileIfNotExists() {
        // Create File device.txt if it doesn't exist
        try {
            File f1 = new File(FILENAME);
            if (!f1.exists()) {
                f1.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean deviceIdExists(String id) {
        // Check if the device id already exists or not
        createFileIfNotExists();
        ArrayList<Device> usersList = getAllDevices();
        for (int i = 0; i < usersList.size(); i++) {
            Device instance = usersList.get(i);
            boolean match = instance.id.equals(id);
            if (match) {
                return true;
            }
        }
        return false;
    }

    // Delete device using device id
    public static boolean deleteDevice(String deviceId) {
        boolean runSmoothly = false;
        String dataToWrite = "";
        try {
            File myObj = new File(FILENAME);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataString = data.split("~");
                String id = dataString[0];
                if (!id.equals(deviceId)) {
                    dataToWrite = dataToWrite + data + "\n";
                }
            }
            myReader.close();
            writeToFile(dataToWrite);
            // Deleting in Relation txt file also
            int valueOfId = getIndexOfIdRelation(deviceId);
            deleteRelation(deviceId);
            // Changing Matrix After the device is deleted
            Utils.changeMatrixAfterDeviceDeletion(valueOfId);
            runSmoothly = true;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return runSmoothly;
    }

    // Update device using device id
    public static boolean updateDevice(Device oldDevice) {
        boolean runSmoothly = false;
        String dataToWrite = "";
        try {
            File myObj = new File(FILENAME);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataString = data.split("~");
                String id = dataString[0];
                if (!id.equals(oldDevice.id)) {
                    dataToWrite = dataToWrite + data + "\n";
                } else {
                    String toReplace = oldDevice.id + "~" + oldDevice.modelId + "~" + oldDevice.name + "~"
                            + oldDevice.description + "\n";

                    dataToWrite = dataToWrite + toReplace;
                }
            }
            myReader.close();
            writeToFile(dataToWrite);
            runSmoothly = true;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return runSmoothly;
    }

    // Write certain text to .txt file
    public static void writeToFile(String dataToWrite) {
        try {
            FileWriter fw = new FileWriter(FILENAME);
            fw.write(dataToWrite);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add New Device
    public static void registerNewDevice(Device newDevice) {
        createFileIfNotExists();
        try {
            FileWriter fileWriter = new FileWriter(FILENAME, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            String data = newDevice.id + "~" + newDevice.modelId + "~" + newDevice.name + "~" + newDevice.description
                    + "\n";
            bw.write(data);
            // Write to relations.txt also
            addRelation(newDevice.id);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read all devices info from txt file and store in Array List
    public static ArrayList<Device> getAllDevices() {
        createFileIfNotExists();
        ArrayList<Device> deviceList = new ArrayList<Device>();
        try {
            File myObj = new File(FILENAME);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataString = data.split("~");
                String id = dataString[0];
                String modelId = dataString[1];
                String name = dataString[2];
                String description = dataString[3];
                Device newDeviceObj = new Device(id, modelId, name, description);
                deviceList.add(newDeviceObj);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return deviceList;
    }

    // Get devices by Id
    public static Device getDeviceById(String id) {
        ArrayList<Device> allDevice = getAllDevices();
        for (int i = 0; i < allDevice.size(); i++) {
            Device current = allDevice.get(i);
            if (current.id.equals(id)) {
                return current;
            }
        }
        return new Device("", "", "", "");
    }

    // Get number of total device
    // Have not used this currently
    public static int getNumberOfDevice() {
        ArrayList<Device> device = getAllDevices();
        return device.size();
    }

    // Create file relations.txt if not exist
    private static void createRelationFileIfNotExists() {
        try {
            File f1 = new File("relations.txt");
            if (!f1.exists()) {
                f1.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read relations.txt file and store it in linkedHashMap
    public static LinkedHashMap<String, Integer> getAllRelations() {
        createRelationFileIfNotExists();
        LinkedHashMap<String, Integer> relationHashMap = new LinkedHashMap<String, Integer>();
        try {
            File myObj = new File("relations.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataString = data.split("~");
                String id = dataString[0];
                String index = dataString[1];
                relationHashMap.put(id, Integer.parseInt(index));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return relationHashMap;
    }

    // Write to relations.txt file
    public static void writeRelation(HashMap<String, Integer> relationHashMap) {
        try {
            FileWriter fileWriter = new FileWriter("relations.txt", false);
            String dataToWrite = "";
            for (String key : relationHashMap.keySet()) {
                dataToWrite += key + "~" + relationHashMap.get(key) + "\n";
            }
            fileWriter.write(dataToWrite);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add new relation when device is added
    public static void addRelation(String id) {
        LinkedHashMap<String, Integer> allRelations = getAllRelations();
        int lastIndex = 0;
        for (String key : allRelations.keySet()) {
            lastIndex = allRelations.get(key);
            lastIndex++;
        }
        allRelations.put(id, lastIndex);
        writeRelation(allRelations);

    }

    // Delete relation when device is deleted
    public static void deleteRelation(String id) {
        LinkedHashMap<String, Integer> allRelations = getAllRelations();
        allRelations.remove(id);
        int i = 0;
        for (String key : allRelations.keySet()) {
            allRelations.put(key, i);
            i++;
        }
        writeRelation(allRelations);

    }

    // Return the value of the key or index of device id
    public static int getIndexOfIdRelation(String id) {
        LinkedHashMap<String, Integer> allRelations = getAllRelations();
        return allRelations.get(id);

    }

    // Return the key of the value or Device id for matrix Index
    public static String getKeyFromValue(int value) {
        LinkedHashMap<String, Integer> allRelations = getAllRelations();
        for (String key : allRelations.keySet()) {
            int eachValue = allRelations.get(key);
            if (eachValue == value) {
                return key;
            }
        }
        return "not found";

    }

}
