package sorting;

import java.util.List;

@FunctionalInterface
public interface SortStrategy<T> {
    SortResult sort(List<T> directory, long maxTime);
}
