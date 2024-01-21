import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import domain.RobotEvent;
import domain.Visit;
import enums.Action;

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
        List<RobotEvent> robotEvents = ReadingData.getRobotInfosFromFile();
        List<Visit> visits = ReadingData.getVisitsFromFile();

        var nameToNumberOfInterferences = new HashMap<String, Integer>();

        for (int i = 0; i < robotEvents.size(); i++) {
            if (robotEvents.get(i).getAction().equals(Action.INTERFERED)) {
                RobotEvent previousEvent = robotEvents.get(i - 1);
                LocalDateTime previousEventTime = previousEvent.getActionDate();
                String person = findFirstVisitedPerson(previousEventTime, visits);
                nameToNumberOfInterferences.merge(person, 1, Integer::sum);
            }
        }

        InterruptionsProcessing
            .writeSortedInterruptions(InterruptionsProcessing.sortInterruptions(nameToNumberOfInterferences));

    }

    private static String findFirstVisitedPerson(LocalDateTime dateTime, List<Visit> visits) {

        for (Visit visit : visits) {
            if (visit.getVisitDate().isAfter(dateTime)) {
                return visit.getVisitPerson();
            }
        }
        return null;
    }

}
