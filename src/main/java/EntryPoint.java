import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class EntryPoint {

    private static final String ROOT_PATH = "a";
    private static final String PATH_RESULT = ROOT_PATH + "a\\result\\a.txt"; // need to create a result folder

    private static final String TO_COMPARE = "b";

    public static void main(String[] args) throws IOException {
        File directoryToScan = new File(ROOT_PATH);

        FileScanner fileScanner = new FileScanner();
        List<File> files = new ArrayList<>();
        fileScanner.collectFiles(directoryToScan, files);

        ComparableReader comparableReader = new ComparableReader();
        List<String> codesToCompare = Files.readAllLines(Paths.get(TO_COMPARE));

        File res = new File(ROOT_PATH + "\\result\\a.txt");
        res.createNewFile();

        files.forEach(file -> {
            if (file.isFile()) {
                String codes = comparableReader.readAndCompare(file.getPath(), codesToCompare);
                try {
                    Files.write(Paths.get(PATH_RESULT), codes.getBytes(), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}




