import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;
import java.security.DigestInputStream;



public class Blob {
    private File objects;
    private File index;

    public Blob(String git) {
        File gitDir = new File(git, "git");
        this.objects = new File(gitDir, "objects");
        this.index = new File(gitDir, "index");
    }

    public String generateFileName(String path) throws IOException, NoSuchAlgorithmException {

        //if file is not a directory:
        if (!new File(path).isDirectory()){
            FileInputStream fileInputStream = new FileInputStream(path);
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, digest);

            //not sure what these two lines do
            byte[] bytes = new byte[1024];
            // read all file content
            while (digestInputStream.read(bytes) > 0);

    //        digest = digestInputStream.getMessageDigest();
            byte[] resultByteArry = digest.digest();
            return bytesToHexString(resultByteArry);
        }
        //if file is a directory:
        else{
            File file = new File(path);
            File[] contents = file.listFiles();
            StringBuilder sb = new StringBuilder();
            for (File temp : contents) {
                sb.append(temp.getName());
            }
            String fileNames = sb.toString();
            byte[] bytes = fileNames.getBytes(StandardCharsets.UTF_8);
            return bytesToHexString(bytes);
            
        }
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            int value = b & 0xFF;
            if (value < 16) {
                // if value less than 16, then it's hex String will be only
                // one character, so we need to append a character of '0'
                sb.append("0");
            }
            sb.append(Integer.toHexString(value).toUpperCase());
        }
        return sb.toString();
    }

    //takes in a path that might be a directory or normal file
    public void createNewBlob(String path) throws IOException, NoSuchAlgorithmException {
        //initialize original fole
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File DNE.");
            return;
        }

        //generate file name string
        String hash = generateFileName(path);

        //initialize copy file
        File newBlob = new File("git/objects/", hash);
        //copy the file
        if (!newBlob.exists()){
            File original = new File(path);
            File location = new File("git/objects", hash);
            Files.copy(original.toPath(), location.toPath());
        }



        //if file is not a directory:
        String fileName = file.getName();
        //i liteally have no idea what this reader does
        BufferedReader reader = new BufferedReader(new FileReader("git/index"));
        while (reader.ready()) {
            String line = reader.readLine();
            if (Objects.equals(line.substring(line.length() - fileName.length()), fileName)) {
                reader.close();
            }
        }
        reader.close();

        File index = new File("git/index");
        FileWriter writer = new FileWriter(index, true);
        File temp = new File(path);
        if (temp.isDirectory()){
            
            writer.write("tree " + hash + " " + path.substring(path.lastIndexOf("git-project-Luca/") + 1));
        }
        else{
            writer.write("blob " + hash + " " + path.substring(path.lastIndexOf("git-project-Luca/") + 1));
        }
        writer.write(System.lineSeparator());
        writer.close();
    }
}
