import java.io.File;

public class Tester {
    public static void main (String [] args) {
        File git = new File("git");
        File objects = new File(git, "objects");
        File index = new File(objects, "index");
        if (git.exists() && objects.exists() && index.exists()) {
            System.out.println ("All files and folders are there!!");
            index.delete(); objects.delete(); git.delete();
        }
        else {
            System.out.println ("The folders and files are not there!! Something is wrong in your code.");
        }
    }
}
