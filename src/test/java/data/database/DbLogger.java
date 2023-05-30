package data.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DbLogger {
public static final String PATH = "C:/Users/Воронина/IdeaProjects/testTESTNGdevend/src/test/java/data/database/log.txt";
    public static void log(String message){
        Path path = Paths.get(PATH);
        try {
            Files.write(path, message.getBytes(), StandardOpenOption.APPEND);
           // System.out.println("Successfully written bytes to the file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
