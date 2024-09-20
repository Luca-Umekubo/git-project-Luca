import java.io.*;
import java.io.IOException;
public class Git{
    public static void main(String[] args) throws IOException {
        initializeRepo();
    }

    public static boolean initializeRepo() throws IOException{
        File git = new File("git");
        File objects = new File(git, "objects");
        File index = new File(git, "index");
        if (git.exists() && objects.exists() && index.exists()) {
            System.out.println ("Git Repository already exists");
            return true;
        }
        git.mkdir(); objects.mkdirs(); index.createNewFile();
        return false;
    }

    public static void deleteRepo() {
        File git = new File("git");
        File objects = new File(git, "objects");
        File index = new File(git, "index");
        index.delete(); objects.delete(); git.delete();
    }
}