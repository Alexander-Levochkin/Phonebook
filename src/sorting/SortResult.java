package sorting;

import java.time.Duration;

public record SortResult(Duration duration, SortStrategy sort, boolean isFailed) {
    public static final String MESSAGE = "Sorting time: %d min. %d sec. %d ms.";

    @Override
    public String toString() {
        return String.format(MESSAGE, duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());
    }
}
