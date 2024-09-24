import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
public class Tester {
    public static void main (String [] args) throws IOException, NoSuchAlgorithmException{
        File git = new File("git");
        File index = new File(git, "index");
        if (Git.initializeRepo()) {
            System.out.println ("All files and folders are there!!");
            Git.deleteRepo();
        }
        else {
            System.out.println ("The folders and files are not there!! Something is wrong in your code.");
        }

        //assumes milestone 1 works
        Git.initializeRepo();


        File sampleFile = new File("sample.txt");
        FileWriter writer = new FileWriter(sampleFile);
        writer.write("TEST TES");
        writer.close();

        Blob blob = new Blob("git");
        blob.createNewBlob("sample.txt");

        // Makes sures blob was made in the right place
        File blobFile = new File("git/objects/" + blob.generateFileName("sample.txt"));
        if (blobFile.exists()) 
            System.out.println("YES!! blob was made in objects.");
        else 
            System.out.println("NOO!! blob wasn't created in objects and code is wrong :()");
        

        // Makes sure index was updated correctly
        String currentIndex = new String(Files.readAllBytes(Path.of("git/index")));
        if (currentIndex.contains(blob.generateFileName("sample.txt")) && currentIndex.contains("sample.txt")) {
            System.out.println("Yup, it worked!!");
        }
        else {
            System.out.println("womp womp it faild and index wasnt updated properly");
        }

        //resets index so the tester can run again
        index.delete();
        index.createNewFile();


    }
}