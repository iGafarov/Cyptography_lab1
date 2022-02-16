package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileService {

    public static String readFile(String filePath) {
        String text = null;
        try {
            text = Files.lines(Paths.get(filePath)).reduce("", (a,b) -> a + "\n" + b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.substring(1);
    }

    public static void createFile(String resultPath, String resultText) {
        try (FileWriter fileWriter = new FileWriter(resultPath)) {
            fileWriter.write(resultText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
