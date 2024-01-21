import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import domain.RobotEvent;
import domain.Visit;
import enums.Action;

/**
 * Класс для чтения данных.
 *
 * @author Vadim Podogov
 * @since 2024.01.21
 */
public class ReadingData {

    private static final String VISITS_FILENAME = "src\\data\\visits.csv";
    private static final String ROBOT_INFO_FILENAME = "src\\data\\robot_events.csv";

    public static List<Visit> getVisitsFromFile() throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(VISITS_FILENAME))) {
            return lines.map(line -> {
                String[] data = line.split(";");
                return new Visit(data[0], LocalDateTime.parse(data[1], ISO_LOCAL_DATE_TIME));
            }).collect(Collectors.toList());
        }
    }

    public static List<RobotEvent> getRobotInfosFromFile() throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(ROBOT_INFO_FILENAME))) {
            return lines.map(line -> {
                String[] data = line.split(";");
                return new RobotEvent(LocalDateTime.parse(data[0], ISO_LOCAL_DATE_TIME),
                    Action.getAction(Integer.parseInt(data[1])));
            }).collect(Collectors.toList());
        }
    }
}
