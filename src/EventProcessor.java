import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import domain.RobotEvent;
import domain.Visit;
import enums.Action;

/**
 * Обработчик событий робота.
 *
 * @author Vadim Podogov
 * @since 2024.01.22
 */
public class EventProcessor {

    public Map<String, Integer> calculateInterruptions(List<RobotEvent> robotEvents, List<Visit> visits) {
        var nameToNumberOfInterferences = new HashMap<String, Integer>();

        for (int i = 0; i < robotEvents.size(); i++) {
            if (robotEvents.get(i).getAction().equals(Action.INTERFERED)) {
                RobotEvent previousEvent = robotEvents.get(i - 1);
                LocalDateTime previousEventTime = previousEvent.getActionDate();
                String person = findFirstVisitedPerson(previousEventTime, visits);
                nameToNumberOfInterferences.merge(person, 1, Integer::sum);
            }
        }
        return nameToNumberOfInterferences;
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
