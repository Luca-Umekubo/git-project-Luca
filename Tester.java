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
    }
}