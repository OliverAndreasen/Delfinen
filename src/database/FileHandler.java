package database;

import java.io.*;
import java.util.Scanner;

public class FileHandler {
    public BufferedWriter writer(String fileName, boolean b) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        return writer;
    }

    public Scanner reader(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        return sc;
    }

    public void overwriteFile() {
        try {
            FileWriter fileWriter = new FileWriter("data/Members.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
