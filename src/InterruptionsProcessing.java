import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Обработка данных о прерывании работы робота.
 *
 * @author Vadim Podogov
 * @since 2024.01.21
 */
public class InterruptionsProcessing {

    private static final String RESULT_FILENAME = "src\\result.txt";

    public static Map<String, Integer> sortInterruptions(Map<String, Integer> nameToNumberOfInterferences) {
        return nameToNumberOfInterferences.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldVal, newVal) -> oldVal, LinkedHashMap::new));
    }

    public static void writeSortedInterruptions(Map<String, Integer> sortedMap) throws IOException {
        try (var bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(RESULT_FILENAME)))) {
            for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
                bw.write(entry.getKey() + " - " + entry.getValue());
                bw.newLine();
            }
        }
    }
}
