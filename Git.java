import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
public class Git{
    public static void main(String[] args) throws IOException {
        File git = new File("git");
        File objects = new File(git, "objects");
        File index = new File(objects, "index");
        if (git.exists() && objects.exists() && index.exists())
            System.out.println ("Git Repository already exists");
        else
            git.mkdir(); objects.mkdirs(); index.createNewFile();
    }
}