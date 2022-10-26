package searching;

import java.time.Duration;

public class SearchResult {
    public static final String MESSAGE = "Found %d / %d entries. Time taken: %d min. %d sec. %d ms.";
    private final int found;
    private final int outOf;
    private final Duration duration;
    public final String durationInfo;
    private final long lengthInMillis;

    public SearchResult(int found, int outOf, Duration duration) {
        this.found = found;
        this.outOf = outOf;
        this.duration = duration;
        durationInfo = String.format("Searching time: %d min. %d sec. %d ms.",
                duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());
        lengthInMillis = this.duration.toMillis();
    }

    public int getFound() {
        return found;
    }

    public int getOutOf() {
        return outOf;
    }

    public Duration getDuration() {
        return duration;
    }

    public long getLengthInMillis() {
        return lengthInMillis;
    }

    @Override
    public String toString() {
        return String.format(MESSAGE, found, outOf,
                duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());
    }
}
