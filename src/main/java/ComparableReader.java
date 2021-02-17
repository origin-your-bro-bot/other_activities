import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class ComparableReader {

    private static final String STORE = "%5Fstore";
    private static final String UNDERSCORE_CODE = "%5F";
    private static final String UNDERSCORE = "_";

    private Matcher matcher = new Matcher();

    public String readAndCompare(String filePath, List<String> codesToCompare) {
        System.out.println(filePath);
        StringBuilder content = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> {
                String transactionCode = matcher.getTransactionCode(s);
                if (transactionCode != null ) {
                    String digitalCode = transactionCode.substring(transactionCode.indexOf(STORE) + STORE.length());

                    if (codesToCompare.contains(digitalCode)) {
                        transactionCode = transactionCode.replace(UNDERSCORE_CODE, UNDERSCORE);
                        content.append(transactionCode).append("\n");
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }
}
