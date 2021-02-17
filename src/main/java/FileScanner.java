import java.io.File;
import java.util.List;

public class FileScanner {

    public void collectFiles(File root, List<File> result) {
        if (root == null) {
            return;
        }

        for (File file : root.listFiles()) {
            if (file.isFile()) {
                result.add(file);
            } else {
                collectFiles(file, result);
            }
        }
    }
}
