import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class LogOperations {

    public static void writeToFile(String text) {
        try(FileWriter writer = new FileWriter("log.txt", true))
        {
            writer.write(text + "\n");
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("log.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
