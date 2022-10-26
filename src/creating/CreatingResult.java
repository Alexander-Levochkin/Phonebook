package creating;

import java.time.Duration;

public record CreatingResult(Duration duration) {
    public static final String MESSAGE = "Creating time: %d min. %d sec. %d ms.";

    @Override
    public String toString() {
        return String.format(MESSAGE, duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());
    }
}
