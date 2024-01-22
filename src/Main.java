import java.io.IOException;
import java.util.List;
import domain.RobotEvent;
import domain.Visit;

/**
 * Основной класс.
 *
 * @author Vadim Podogov
 * @since 2024.01.21
 */
public class Main {

    public static void main(String[] args) throws IOException {
        process();
    }

    private static void process() throws IOException {
        List<RobotEvent> robotEvents = DataLoader.getRobotInfosFromFile();
        List<Visit> visits = DataLoader.getVisitsFromFile();

        var eventProcessor = new EventProcessor();
        var nameToNumberOfInterferences = eventProcessor.calculateInterruptions(robotEvents, visits);

        var reportService = new InterruptionReportService();
        reportService.writeSortedInterruptions(reportService.sortInterruptions(nameToNumberOfInterferences));
    }

}
