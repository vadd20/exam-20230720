package enums;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2024.01.21
 */
public enum Action {
    STARTED(0),
    COMPLETED(1),
    INTERFERED(2);

    Action(int actionCode) {
        this.actionCode = actionCode;
    }

    private final int actionCode;

    public static Action getAction(int actionCode) {
        for (Action action : values()) {
            if (actionCode == action.actionCode) {
                return action;
            }
        }
        return null;
    }
}
