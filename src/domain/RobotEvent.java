package domain;

import java.time.LocalDateTime;
import enums.Action;

/**
 * Сущность события робота.
 *
 * @author Vadim Podogov
 * @since 2024.01.21
 */
public class RobotEvent {

    private final LocalDateTime actionDate;
    private final Action action;

    public RobotEvent(LocalDateTime actionDate, Action action) {
        this.actionDate = actionDate;
        this.action = action;
    }

    public LocalDateTime getActionDate() {
        return actionDate;
    }

    public Action getAction() {
        return action;
    }
}
