package domain;

import java.time.LocalDateTime;

/**
 * Сущность посещения кабинета сотрудником.
 *
 * @author Vadim Podogov
 * @since 2024.01.21
 */
public class Visit {

    private final String personName;

    private final LocalDateTime date;

    public String getVisitPerson() {
        return personName;
    }

    public LocalDateTime getVisitDate() {
        return date;
    }

    public Visit(String personName, LocalDateTime date) {
        this.personName = personName;
        this.date = date;
    }
}
