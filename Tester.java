import java.io.File;
import java.io.*;
import java.io.IOException;
public class Tester {
    public static void main (String [] args) throws IOException{
        // File git = new File("git");
        // File objects = new File(git, "objects");
        // File index = new File(objects, "index");
        if (Git.initializeRepo()) {
            System.out.println ("All files and folders are there!!");
            Git.deleteRepo();
        }
        else {
            System.out.println ("The folders and files are not there!! Something is wrong in your code.");
        }
    }
}
